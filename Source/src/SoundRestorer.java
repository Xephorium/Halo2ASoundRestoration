
import io.FileManager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static javafx.application.Platform.exit;

/* Halo 2A Sound Restoration                         Chris Cruzen
 * SoundRestorer                                       03.10.2023
 *
 * SoundRestorer is the primary driver class of the Halo 2A Sound
 * Restoration project.
 *
 * This file restores the campaign's classic audio in 3 steps:
 *
 *   1. Read the user-specified tag directory from the project's
 *      configuration file.
 *
 *   2. Iterate through that directory's `sound` folder, replacing
 *      and deleting tags as necessary to restore the campaign's
 *      original audio.
 *
 *   3. Use the abysmally named command line utility `tool` to
 *      package the updated tags into .map files that can finally
 *      be played in the Master Chief Collection.
 *
 * For more info: `Project Resources/Notes/Halo 2 MCC Modding.txt`.
 */

public class SoundRestorer {



    /*--- Config Constants ---*/

    private final String CONFIG_FILE_PATH = "input\\/Config.txt";
    private final String CONFIG_DIR_PREFIX = "TAGS_DIRECTORY";
    private final String CONFIG_DELIMITER = "=";



    /*--- Weapon Constants ---*/

    private final String WEAPONS_SUBDIR_PATH = "\\/sound_remastered\\/weapons";
    private final String[] WEAPON_DELETE_SUBSTRINGS = {"swtnr", "lfe", "lod"};
    private final String[] WEAPON_IGNORE_SUBSTRINGS = {"sound_looping"};
    private final String[] WEAPON_REPLACE_PATHS = {

            // Beam Rifle NPC Fire
            "\\/sound_remastered\\/weapons\\/beam_rifle\\/beam_rifle_fire.sound",
            "\\/sound_remastered\\/weapons\\/beam_rifle\\/beam_rifle_fire_noplayer.sound",

            // Beam Rifle Zoom In/Out
            "\\/sound\\/weapons\\/beam_rifle\\/beam_rifle_zoom_in.sound",
            "\\/sound_remastered\\/weapons\\/beam_rifle\\/beamrifle_zoom\\/in.sound",
            "\\/sound\\/weapons\\/beam_rifle\\/beam_rifle_zoom_out.sound",
            "\\/sound_remastered\\/weapons\\/beam_rifle\\/beamrifle_zoom\\/out.sound",

            // Rocket Explosion
            "\\/sound\\/weapons\\/rocket_launcher\\/rocket_expl.sound",
            "\\/sound_remastered\\/weapons\\/rocket_launcher\\/projectile_exp\\/close.sound",

            // Sentinel Beam Fire
            "\\/sound\\/characters\\/sentinel\\/sentinel_gun\\/sent_gun\\/in.sound",
            "\\/sound_remastered\\/characters\\/sentinel\\/sentinel_gun_lod\\/sent_gun\\/in.sound",
            "\\/sound\\/characters\\/sentinel\\/sentinel_gun\\/sent_gun\\/loop.sound",
            "\\/sound_remastered\\/characters\\/sentinel\\/sentinel_gun_lod\\/sent_gun\\/loop.sound",
            "\\/sound\\/characters\\/sentinel\\/sentinel_gun\\/sent_gun\\/out.sound",
            "\\/sound_remastered\\/characters\\/sentinel\\/sentinel_gun_lod\\/sent_gun\\/out.sound",

            // Sword Ready
            "\\/sound\\/weapons\\/energy_sword\\/sword_ready.sound",
            "\\/sound_remastered\\/weapons\\/energy_sword\\/energy_sword_ready.sound"
    };
    private final String[] WEAPON_DELETE_PATHS = {

            // Plasma Rifle Bottom Fire
            "\\/sound_remastered\\/weapons\\/plasma_rifle\\/plas_rifle_fire_bottom.sound",

            // Warthog Turret Spin
            "\\/sound_remastered\\/weapons\\/chain_gun\\/chaingun_spin.sound"
    };



    /*--- Character Constants ---*/

    private final String SENTINEL_SOUND_PATH = "\\/sound_remastered\\/characters\\/sentinel";
    private final String[] CHARACTER_REPLACE_PATHS = {

            // Sentinel Enforcer Needles
            "\\/sound\\/characters\\/sentinel\\/needler_fire_sentinel_enforcer.sound",
            "\\/sound_remastered\\/characters\\/sentinel\\/sentinel_enforcer_laser.sound"

    };
    private final String[] CHARACTER_DELETE_PATHS = {

            // Sentinel Enforcer Rocket
            "\\/sound_remastered\\/characters\\/sentinel\\/sentinel_rocket_launcher\\/sentinel_rocket\\/in.sound"
    };



    /*--- UI Constants ---*/

    private final String UI_SOUND_PATH = "\\/sound_remastered\\/ui";
    private final String[] UI_DELETE_PATHS = {

            // Shield Pop
            "\\/sound_remastered\\/ui\\/shield_pop.sound"
    };



    /*--- Music Constants ---*/

    private final String MUSIC_SOUND_PATH = "\\/sound_remastered\\/music";



    /*--- Variables ---*/

    private File rootTagDirectory = null;



    /*--- Public Methods ---*/

    public SoundRestorer() {
        initializeRootTagDirectory();
    }

    public void restoreSound() {
        restoreWeaponAudio();
        restoreCharacterAudio();
        restoreUIAudio();
        restoreMusic();
    }


    /*--- Config Initialization Method ---*/

    private void initializeRootTagDirectory() {
        List<String> dirFileContents = FileManager.readFileContents(new File(CONFIG_FILE_PATH));

        if (dirFileContents != null) {
            for (String line : dirFileContents)
                if (line.contains(CONFIG_DIR_PREFIX)) {
                    rootTagDirectory = new File(line.split(CONFIG_DELIMITER)[1].trim());

                    if (!FileManager.isValidDirectory(rootTagDirectory)) {
                        System.out.printf(
                                "CONFIG ERROR: Tag directory '%s' does not exist.%n",
                                rootTagDirectory.getPath()
                        );
                        exit();
                    }
                }
        } else {
            System.out.println("CONFIG ERROR: Configuration file missing.");
            exit();
        }

        if (rootTagDirectory == null) {
            System.out.println("CONFIG ERROR: Problem with Configuration file format.");
            exit();
        }
    }


    /*--- Weapon Audio Restoration Methods ---*/

    private void restoreWeaponAudio() {
        File remasteredWeaponDir = FileManager.createSubdirectoryFile(rootTagDirectory, WEAPONS_SUBDIR_PATH);
        if (FileManager.isValidDirectory(remasteredWeaponDir)) {
            walkWeaponDirectory(remasteredWeaponDir);
            performManualWeaponTagFixes();

            //checkDirectoryForClassicTags(remasteredWeaponDir, 0);
        }
    }

    /* Recursively walks the remastered weapon tag directory, replacing each with their classic
     * counterpart and deleting those that aren't needed.
     */
    private void walkWeaponDirectory(File remasteredFile) {

        if (FileManager.isValidFile(remasteredFile)) {
            RestoreClassicWeaponTag(remasteredFile);

        } else if(FileManager.isValidDirectory(remasteredFile)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(remasteredFile);
            for (File dir: subDirs) walkWeaponDirectory(dir);

            // Recourse Through Tags
            List<File> files = FileManager.getDirectoryFiles(remasteredFile);
            for (File file: files) walkWeaponDirectory(file);
        }
    }

    private void RestoreClassicWeaponTag(File remasteredTag) {
        File classicTag = getClassicFile(remasteredTag);
        String remasteredTagName = FileManager.getFileOrDirectoryName(remasteredTag);
        if (FileManager.exists(classicTag)) {

            // Check Whether to Ignore Tag
            assert remasteredTagName != null;
            if (Arrays.stream(WEAPON_IGNORE_SUBSTRINGS).noneMatch(remasteredTagName::contains)) {

                // Replace w/ Classic Tag
                if (FileManager.deleteFile(remasteredTag)) {
                    FileManager.copyFile(classicTag, remasteredTag);
                }
            }

        } else {

            // Delete Unnecessary Tag
            assert remasteredTagName != null;
            if (Arrays.stream(WEAPON_DELETE_SUBSTRINGS).anyMatch(remasteredTagName::contains)) {
                FileManager.deleteFile(remasteredTag);
            }
        }
    }

    /* Handles one-off cases where weapon tag files are inconsistently
     * named or otherwise need special attention.
     */
    private void performManualWeaponTagFixes() {

        // Replace Necessary Weapon Tags
        for (int x = 1; x < WEAPON_REPLACE_PATHS.length; x += 2) {
            replaceTag(WEAPON_REPLACE_PATHS[x], WEAPON_REPLACE_PATHS[x-1]);
        }

        // Delete Necessary Weapon Tags
        for (String weaponDeletePath : WEAPON_DELETE_PATHS) {
            deleteTag(weaponDeletePath);
        }

        /* NOTE: Sentinel Beam weapon sounds are stored in the character directory
         *       and handled in the performManualCharacterTagFixes() method.
         */
    }


    /*--- Character Audio Restoration Methods ---*/

    private void restoreCharacterAudio() {
        performManualCharacterTagFixes();
    }

    /* Handles one-off cases where character tag files are inconsistently
     * named or otherwise need special attention.
     */
    private void performManualCharacterTagFixes() {

        // Update Sentinel Sounds
        File sentinelTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, SENTINEL_SOUND_PATH);
        if (FileManager.isValidDirectory(sentinelTagDir)) walkWeaponDirectory(sentinelTagDir);

        // Replace Necessary Character Tags
        for (int x = 1; x < CHARACTER_REPLACE_PATHS.length; x += 2) {
            replaceTag(CHARACTER_REPLACE_PATHS[x], CHARACTER_REPLACE_PATHS[x-1]);
        }

        // Delete Necessary Character Tags
        for (String characterDeletePath : CHARACTER_DELETE_PATHS) {
            deleteTag(characterDeletePath);
        }
    }


    /*--- UI Audio Restoration Methods ---*/

    private void restoreUIAudio() {

        // Update UI Sounds
        File uiTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, UI_SOUND_PATH);
        if (FileManager.isValidDirectory(uiTagDir)) walkWeaponDirectory(uiTagDir);

        // Delete Necessary UI Tags
        for (String uiDeletePath : UI_DELETE_PATHS) {
            deleteTag(uiDeletePath);
        }
    }

    /*--- Music Restoration Methods ---*/

    private void restoreMusic() {

        // Update Music
        File musicTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, MUSIC_SOUND_PATH);
        if (FileManager.isValidDirectory(musicTagDir)) walkWeaponDirectory(musicTagDir);
    }


    /*--- Utility Methods ---*/

    /* Recursively walks directory, printing whether a corresponding classic tag exists for each remastered tag.
     * KEY: [x] = Tag Exists, [ ] = Tag Absent
     */
    private void checkDirectoryForClassicTags(File remasteredFile, int depth) {

        if (FileManager.isValidFile(remasteredFile)) {

            // Print Tag Status
            File classicSoundTag = getClassicFile(remasteredFile);
            if (FileManager.exists(classicSoundTag)) {
                System.out.println(
                        getRecursiveIndentation(depth) + "[x] " + FileManager.getFileOrDirectoryName(remasteredFile)
                );
            } else {
                System.out.println(
                        getRecursiveIndentation(depth) + "[ ] " + FileManager.getFileOrDirectoryName(remasteredFile)
                );
            }

        } else if(FileManager.isValidDirectory(remasteredFile)) {

            // Print Directory Name
            System.out.println(
                    getRecursiveIndentation(depth) + FileManager.getFileOrDirectoryName(remasteredFile)
            );

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(remasteredFile);
            for (File dir: subDirs) {
                checkDirectoryForClassicTags(dir, depth + 1);
            }

            // Recourse Through Files
            List<File> files = FileManager.getDirectoryFiles(remasteredFile);
            for (File file: files) {
                checkDirectoryForClassicTags(file, depth + 1);
            }
        }

    }

    private String getRecursiveIndentation(int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int x = 0; x < depth; x++) {
            indentation.append("\t");;
        }
        return indentation.toString();
    }

    private void replaceTag(String sourcePath, String replacementPath) {
        File sourceTag = FileManager.createSubdirectoryFile(rootTagDirectory, sourcePath);
        File replacementTag = FileManager.createSubdirectoryFile(rootTagDirectory, replacementPath);
        if (FileManager.isValidFile(replacementTag) && FileManager.isValidFile(sourceTag)) {
            if (FileManager.deleteFile(sourceTag)) {
                FileManager.copyFile(replacementTag, sourceTag);
            }
        }
    }

    private void deleteTag(String tagPath) {
        File tag = FileManager.createSubdirectoryFile(rootTagDirectory, tagPath);
        if (FileManager.isValidFile(tag)) FileManager.deleteFile(tag);
    }

    private File getClassicFile(File file) {
        return new File(file.getPath().replace("sound_remastered", "sound"));
    }

}
