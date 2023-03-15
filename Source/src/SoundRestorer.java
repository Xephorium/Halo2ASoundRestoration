
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
    private final boolean FULL_RESTORATION = true;



    /*--- General Constants ---*/

    private final String[] TAG_DELETE_SUBSTRINGS = {"swtnr", "lfe", "lod.", "_lod"};
    private final String[] TAG_IGNORE_SUBSTRINGS = {"sound_looping"};
    private final String[] EMPTY = {};



    /*--- Weapon Constants ---*/

    private final String WEAPONS_SUBDIR_PATH = "\\/sound_remastered\\/weapons";
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

            // Turret NPC Fire
            "\\/sound_remastered\\/weapons\\/chain_gun\\/turret_fire.sound",
            "\\/sound_remastered\\/weapons\\/chain_gun\\/turret_fire_mono.sound",

            // Sword Ready
            "\\/sound\\/weapons\\/energy_sword\\/sword_ready.sound",
            "\\/sound_remastered\\/weapons\\/energy_sword\\/energy_sword_ready.sound",

            // Regret Throne Cannon (Yes, the directories are right here - 343i is an anthill.)
            "\\/sound\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_gravitythrone\\/in.sound",
            "\\/sound_remastered\\/vehicles\\/gravity_throne\\/weapon_fire\\/in.sound",
            "\\/sound\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_gravitythrone\\/loop.sound",
            "\\/sound_remastered\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_prophet\\/loop.sound",
            "\\/sound\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_gravitythrone\\/out.sound",
            "\\/sound_remastered\\/vehicles\\/gravity_throne\\/weapon_fire\\/out.sound",

            // Regret Throne Cannon Safety Fix (Just in case 343i audits their tiny ant tunnels.)
            "\\/sound\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_gravitythrone\\/in.sound",
            "\\/sound_remastered\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_prophet\\/in.sound",
            "\\/sound\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_gravitythrone\\/out.sound",
            "\\/sound_remastered\\/weapons\\/hunter_cannon\\/hunter_cannon_loop\\/hunter_cannon_prophet\\/out.sound",

    };
    private final String[] WEAPON_DELETE_PATHS = {

            // Plasma Rifle Bottom Fire
            "\\/sound_remastered\\/weapons\\/plasma_rifle\\/plas_rifle_fire_bottom.sound",

            // Covenant Carbine Impact Sound
            "\\/sound_remastered\\/weapons\\/impacts_riccs\\/cov_carbine_imp.sound",

            // Warthog Turret Spin
            "\\/sound_remastered\\/weapons\\/chain_gun\\/chaingun_spin.sound",
    };



    /*--- Vehicle Constants ---*/

    private final String VEHICLE_SOUND_PATH = "\\/sound_remastered\\/vehicles";
    private final String[] VEHICLE_REPLACE_PATHS = {

            // Warthog Crash
            "\\/sound\\/vehicles\\/warthog\\/jeep_crash_hard.sound",
            "\\/sound_remastered\\/vehicles\\/warthog\\/warthog_suspension.sound",

            // Scorpion Fire
            "\\/sound\\/vehicles\\/scorpion\\/scorpion_explosion_up.sound",
            "\\/sound_remastered\\/vehicles\\/scorpion\\/projectile_exp\\/close.sound",

            // Scorpion Crash
            "\\/sound\\/vehicles\\/scorpion\\/scorpion_crash.sound",
            "\\/sound_remastered\\/vehicles\\/scorpion\\/scorpion_suspension.sound",

            // Ghost Fire
            "\\/sound\\/vehicles\\/ghost\\/ghost_fire.sound",
            "\\/sound_remastered\\/vehicles\\/ghost\\/ghost_fire\\/fire.sound",

            // Wraith Fire
            "\\/sound\\/vehicles\\/wraith\\/wraith_fire_mortar.sound",
            "\\/sound_remastered\\/vehicles\\/wraith\\/wraith_fire_mortar\\/fire.sound",

            // Wraith Explode
            "\\/sound\\/vehicles\\/wraith\\/wraith_plasma_expl.sound",
            "\\/sound_remastered\\/vehicles\\/wraith\\/wraith_mortar_explode\\/explode.sound",

            // Banshee Fire
            "\\/sound\\/vehicles\\/banshee\\/banshee_fire.sound",
            "\\/sound_remastered\\/vehicles\\/banshee\\/banshee_fire\\/fire.sound",

            // Phantom Extra Fire
            "\\/sound_remastered\\/vehicles\\/phantom\\/phantom_turret_fire.sound",
            "\\/sound_remastered\\/vehicles\\/phantom\\/phantom_turret_fire_turret.sound"

    };
    private final String[] VEHICLE_DELETE_PATHS = {

            // Scorpion Reload
            "\\/sound_remastered\\/vehicles\\/scorpion\\/scorpion_reload.sound",

            // Scorpion Turret Move
            "\\/sound_remastered\\/vehicles\\/scorpion\\/scorp_turret_move.sound_looping",
            "\\/sound_remastered\\/vehicles\\/scorpion\\/turret_move\\/in.sound",
            "\\/sound_remastered\\/vehicles\\/scorpion\\/turret_move\\/loop.sound",
            "\\/sound_remastered\\/vehicles\\/scorpion\\/turret_move\\/out.sound",

            // Ghost Extra Boost
            "\\/sound_remastered\\/vehicles\\/ghost\\/ghost_boost_left\\/track2\\/loop.sound",
            "\\/sound_remastered\\/vehicles\\/ghost\\/ghost_boost_right\\/track2\\/loop.sound",

            // Spectre Extra Boost
            "\\/sound_remastered\\/vehicles\\/spectre\\/spectre_boost_l\\/track2\\/loop.sound",
            "\\/sound_remastered\\/vehicles\\/spectre\\/spectre_boost_r\\/track2\\/loop.sound",

            // Phantom Turret Animation
            "\\/sound_remastered\\/vehicles\\/phantom\\/phantom_turret_anims\\/lower.sound",
            "\\/sound_remastered\\/vehicles\\/phantom\\/phantom_turret_anims\\/raise.sound",

            // Regret Extra Sounds
            "\\/sound_remastered\\/vehicles\\/gravity_throne\\/regret_teleport_reflection.sound",
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



    /*--- Effects Constants ---*/

    private final String EFFECTS_SOUND_PATH = "\\/sound_remastered\\/visual_effects";
    private final String[] EFFECTS_PRESERVE_PATHS = {

            // Cairo Welder
            "welder\\in.sound",
            "welder\\loop.sound",

            // Malta Explosion
            "malta_explode.sound",

            // Cable Cutting
            "alphacable_swtnr",

            // Airlock
            "airlock.sound",
            "airlock_repressurize.sound",
            "ss_airlock_explode.sound",

            // Steam
            "scenery_steam\\loop.sound",

            // Sentinel Factory Explosion
            "factory_explode.sound"
    };
    private final String[] EFFECTS_REPLACE_PATHS = {

            // In Amber Clad Slipspace Rupture
            "\\/sound\\/visual_effects\\/inamberclad_flyby\\/slipspace.sound",
            "\\/sound_remastered\\/visual_effects\\/inamberclad_flyby\\/slipspace\\/highcharity_slipspace.sound",

    };
    private final String[] EFFECTS_DELETE_PATHS = {};



    /*--- Ambience Constants ---*/

    private final String AMBIENCE_SOUND_PATH = "\\/sound_remastered\\/ambience\\/spacestation";
    private final String[] AMBIENCE_PRESERVE_PATHS = {};
    private final String[] AMBIENCE_REPLACE_PATHS = {};
    private final String[] AMBIENCE_DELETE_PATHS = {};



    /*--- Music Constants ---*/

    private final String MUSIC_SOUND_PATH = "\\/sound_remastered\\/music";



    /*--- Variables ---*/

    private File rootTagDirectory = null;
    private int totalTagsModified = 0;
    private int totalTagsReplaced = 0;
    private int totalTagsDeleted = 0;
    private int totalTagsPreserved = 0;



    /*--- Public Methods ---*/

    public SoundRestorer() {
        initializeRootTagDirectory();
    }

    public void restoreSound() {

        // Restore Classic Audio
        restoreWeaponAudio();
        restoreVehicleAudio();
        restoreCharacterAudio();
        restoreUIAudio();
        restoreEffectsAudio();
        restoreAmbienceAudio();
        restoreMusic();

        // Print Statistics
        printStatistics();

        //checkDirectoryForClassicTags(FileManager.createSubdirectoryFile(rootTagDirectory, EFFECTS_SOUND_PATH), 0);
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



    /*--- Recursive Tag Cleanup Methods ---*/

    /* Recursively walks the remastered tag directory, replacing each with their classic
     * counterpart and deleting those that aren't needed.
     */
    private void walkTagDirectory(File remasteredFile, String[] ignorePaths) {

        if (FileManager.isValidFile(remasteredFile)) {
            ProcessTag(remasteredFile, ignorePaths);

        } else if(FileManager.isValidDirectory(remasteredFile)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(remasteredFile);
            for (File dir: subDirs) walkTagDirectory(dir, ignorePaths);

            // Recourse Through Tags
            List<File> files = FileManager.getDirectoryFiles(remasteredFile);
            for (File file: files) walkTagDirectory(file, ignorePaths);
        }
    }

    /* Ignores, replaces, or deletes a single tag based on whether its
     * name matches any of the TAG_SUBSTRING constants above.
     */
    private void ProcessTag(File remasteredTag, String[] ignorePaths) {
        File classicTag = getClassicFile(remasteredTag);
        String remasteredTagName = FileManager.getFileOrDirectoryName(remasteredTag);
        if (FileManager.exists(classicTag)) {

            // Check Whether to Ignore Tag
            assert remasteredTagName != null;
            if (Arrays.stream(TAG_IGNORE_SUBSTRINGS).noneMatch(remasteredTagName::contains) &&
                    Arrays.stream(ignorePaths).noneMatch(remasteredTag.toString()::contains)) {

                // Replace Tag / Classic
                if (FileManager.deleteFile(remasteredTag)) {
                    FileManager.copyFile(classicTag, remasteredTag);
                    totalTagsModified++;
                    totalTagsReplaced++;
                }
            } else {

                // Preserve Tag
                totalTagsPreserved++;
            }

        } else if (Arrays.stream(ignorePaths).anyMatch(remasteredTag.toString()::contains)) {

            // Preserve Tag
            totalTagsPreserved++;

        } else {

            // Delete Tag
            assert remasteredTagName != null;
            if (Arrays.stream(TAG_DELETE_SUBSTRINGS).anyMatch(remasteredTagName::contains)) {
                FileManager.deleteFile(remasteredTag);
                totalTagsModified++;
                totalTagsDeleted++;
            }
        }
    }



    /*--- Weapon Audio Restoration Methods ---*/

    private void restoreWeaponAudio() {
        File remasteredWeaponDir = FileManager.createSubdirectoryFile(rootTagDirectory, WEAPONS_SUBDIR_PATH);
        if (FileManager.isValidDirectory(remasteredWeaponDir)) {
            walkTagDirectory(remasteredWeaponDir, EMPTY);
            performManualWeaponTagFixes();
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



    /*--- Vehicle Audio Restoration Methods ---*/

    private void restoreVehicleAudio() {
        File vehicleTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, VEHICLE_SOUND_PATH);
        if (FileManager.isValidDirectory(vehicleTagDir)) {
            walkTagDirectory(vehicleTagDir, EMPTY);
            performManualVehicleTagFixes();
        }
    }

    /* Handles one-off cases where vehicle tag files are inconsistently
     * named or otherwise need special attention.
     */
    private void performManualVehicleTagFixes() {

        // Replace Necessary Vehicle Tags
        for (int x = 1; x < VEHICLE_REPLACE_PATHS.length; x += 2) {
            replaceTag(VEHICLE_REPLACE_PATHS[x], VEHICLE_REPLACE_PATHS[x-1]);
        }

        // Delete Necessary Vehicle Tags
        for (String vehicleDeletePath : VEHICLE_DELETE_PATHS) {
            deleteTag(vehicleDeletePath);
        }
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
        if (FileManager.isValidDirectory(sentinelTagDir)) walkTagDirectory(sentinelTagDir, EMPTY);

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
        if (FileManager.isValidDirectory(uiTagDir)) walkTagDirectory(uiTagDir, EMPTY);

        // Delete Necessary UI Tags
        for (String uiDeletePath : UI_DELETE_PATHS) {
            deleteTag(uiDeletePath);
        }
    }



    /*--- Effects Restoration Methods ---*/

    private void restoreEffectsAudio() {

        // Update Effects
        File effectsTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, EFFECTS_SOUND_PATH);
        if (FileManager.isValidDirectory(effectsTagDir)) walkTagDirectory(effectsTagDir, EFFECTS_PRESERVE_PATHS);

        performManualEffectsTagFixes();
    }

    /* Handles one-off cases where effect tag files are inconsistently
     * named or otherwise need special attention.
     */
    private void performManualEffectsTagFixes() {

        // Replace Necessary Effects Tags
        for (int x = 1; x < EFFECTS_REPLACE_PATHS.length; x += 2) {
            replaceTag(EFFECTS_REPLACE_PATHS[x], EFFECTS_REPLACE_PATHS[x-1]);
        }

        // Delete Necessary Effects Tags
        for (String path : EFFECTS_DELETE_PATHS) {
            deleteTag(path);
        }
    }



    /*--- Ambience Restoration Methods ---*/

    private void restoreAmbienceAudio() {

        // Update Ambience
        File ambienceTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, AMBIENCE_SOUND_PATH);
        if (FileManager.isValidDirectory(ambienceTagDir)) walkTagDirectory(ambienceTagDir, AMBIENCE_PRESERVE_PATHS);

        performManualEffectsTagFixes();
    }

    /* Handles one-off cases where ambience tag files are inconsistently
     * named or otherwise need special attention.
     */
    private void performManualAmbienceTagFixes() {

        // Replace Necessary Ambience Tags
        for (int x = 1; x < AMBIENCE_REPLACE_PATHS.length; x += 2) {
            replaceTag(AMBIENCE_REPLACE_PATHS[x], AMBIENCE_REPLACE_PATHS[x-1]);
        }

        // Delete Necessary Ambience Tags
        for (String path : AMBIENCE_DELETE_PATHS) {
            deleteTag(path);
        }
    }



    /*--- Music Restoration Methods ---*/

    private void restoreMusic() {

        // Update Music
        File musicTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, MUSIC_SOUND_PATH);
        if (FileManager.isValidDirectory(musicTagDir)) walkTagDirectory(musicTagDir, EMPTY);
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

    /* Recursively walks directory, printing whether tag name matches string query.
     * Invocation:
     *   checkDirectoryForNameEdgeCases(
     *     FileManager.createSubdirectoryFile(rootTagDirectory, "\\/sound_remastered")
     *   );
     */
    private void checkDirectoryForNameEdgeCases(File file) {

        if (FileManager.isValidFile(file)) {

            // Print Tag Status
            String name = FileManager.getFileOrDirectoryName(file);
            assert name != null;
            if (name.contains("lod") && !name.contains("lod.") && !name.contains("_lod")) {
                System.out.println(file.toString());
            }

        } else if(FileManager.isValidDirectory(file)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(file);
            for (File dir: subDirs) {
                checkDirectoryForNameEdgeCases(dir);
            }

            // Recourse Through Files
            List<File> files = FileManager.getDirectoryFiles(file);
            for (File f: files) {
                checkDirectoryForNameEdgeCases(f);
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
        if (FileManager.isValidFile(sourceTag)) FileManager.deleteFile(sourceTag);
        if (FileManager.isValidFile(replacementTag)) {
            FileManager.copyFile(replacementTag, sourceTag);
            totalTagsModified++;
            totalTagsReplaced++;
        }
    }

    private void deleteTag(String tagPath) {
        File tag = FileManager.createSubdirectoryFile(rootTagDirectory, tagPath);
        if (FileManager.isValidFile(tag)) {
            FileManager.deleteFile(tag);
            totalTagsModified++;
            totalTagsDeleted++;
        }
    }

    private File getClassicFile(File file) {
        return new File(file.getPath().replace("sound_remastered", "sound"));
    }



    /*--- Statistic Methods ---*/

    private void printStatistics() {
        System.out.println("\nClassic Audio Restored");
        System.out.println("----------------------");
        System.out.printf("Updated            %d\n", totalTagsModified);
        System.out.printf("Replaced           %d\n", totalTagsReplaced);
        System.out.printf("Deleted            %d\n", totalTagsDeleted);
        System.out.printf("Preserved          %d\n", totalTagsPreserved);
        System.out.println("----------------------");
    }
}
