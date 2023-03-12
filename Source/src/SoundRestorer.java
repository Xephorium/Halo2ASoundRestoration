
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


    /*--- Constants ---*/

    private final String CONFIG_FILE_PATH = "input\\/Config.txt";
    private final String CONFIG_DIR_PREFIX = "TAGS_DIRECTORY=";
    private final String CONFIG_DELIMITER = "=";

    private final String WEAPONS_SUBDIR_PATH = "\\/sound_remastered\\/weapons";
    private final String[] WEAPON_DELETE_SUBSTRINGS = {"lod", "swtnr", "lfe", "bottom"};
    private final String[] WEAPON_IGNORE_SUBSTRINGS = {"sound_looping"};

    private final String BEAM_ZOOM_IN_PATH_CLASSIC = "\\/sound\\/weapons\\/beam_rifle\\/beam_rifle_zoom_in.sound";
    private final String BEAM_ZOOM_IN_PATH_REMASTER = "\\/sound_remastered\\/weapons\\/beam_rifle\\/beamrifle_zoom\\/in.sound";
    private final String BEAM_ZOOM_OUT_PATH_CLASSIC = "\\/sound\\/weapons\\/beam_rifle\\/beam_rifle_zoom_out.sound";
    private final String BEAM_ZOOM_OUT_PATH_REMASTER = "\\/sound_remastered\\/weapons\\/beam_rifle\\/beamrifle_zoom\\/out.sound";
    private final String WARTHOG_TURRET_SPIN_PATH_REMASTER = "\\/sound_remastered\\/weapons\\/chain_gun\\/chaingun_spin.sound";
    private final String ROCKET_EXPL_PATH_CLASSIC = "\\/sound\\/weapons\\/rocket_launcher\\/rocket_expl.sound";
    private final String ROCKET_EXPL_PATH_REMASTER = "\\/sound_remastered\\/weapons\\/rocket_launcher\\/projectile_exp\\/close.sound";
    private final String SWORD_READY_PATH_CLASSIC = "\\/sound\\/weapons\\/energy_sword\\/sword_ready.sound";
    private final String SWORD_READY_PATH_REMASTER = "\\/sound_remastered\\/weapons\\/energy_sword\\/energy_sword_ready.sound";

    private final String SENTINEL_SOUND_PATH = "\\/sound_remastered\\/characters\\/sentinel";


    /*--- Variables ---*/

    private File rootTagDirectory = null;


    /*--- Public Methods ---*/

    public SoundRestorer() {
        initializeRootTagDirectory();
    }

    public void restoreSound() {
        restoreWeaponAudio();
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

        // Beam Rifle Zoom In
        File beamZoomInClassicTag = FileManager.createSubdirectoryFile(rootTagDirectory, BEAM_ZOOM_IN_PATH_CLASSIC);
        File beamZoomInRemasterTag = FileManager.createSubdirectoryFile(rootTagDirectory, BEAM_ZOOM_IN_PATH_REMASTER);
        if (FileManager.isValidFile(beamZoomInClassicTag) && FileManager.isValidFile(beamZoomInRemasterTag)) {
            if (FileManager.deleteFile(beamZoomInRemasterTag)) {
                FileManager.copyFile(beamZoomInClassicTag, beamZoomInRemasterTag);
            }
        }

        // Warthog Turret
        File warthogTurretSpinTag = FileManager.createSubdirectoryFile(rootTagDirectory, WARTHOG_TURRET_SPIN_PATH_REMASTER);
        if (FileManager.isValidFile(warthogTurretSpinTag)) FileManager.deleteFile(warthogTurretSpinTag);

        // Beam Rifle Zoom Out
        File beamZoomOutClassicTag = FileManager.createSubdirectoryFile(rootTagDirectory, BEAM_ZOOM_OUT_PATH_CLASSIC);
        File beamZoomOutRemasterTag = FileManager.createSubdirectoryFile(rootTagDirectory, BEAM_ZOOM_OUT_PATH_REMASTER);
        if (FileManager.isValidFile(beamZoomOutClassicTag) && FileManager.isValidFile(beamZoomOutRemasterTag)) {
            if (FileManager.deleteFile(beamZoomOutRemasterTag)) {
                FileManager.copyFile(beamZoomOutClassicTag, beamZoomOutRemasterTag);
            }
        }

        // Rocket Launcher Explosion
        File rocketExplClassicTag = FileManager.createSubdirectoryFile(rootTagDirectory, ROCKET_EXPL_PATH_CLASSIC);
        File rocketExplRemasterTag = FileManager.createSubdirectoryFile(rootTagDirectory, ROCKET_EXPL_PATH_REMASTER);
        if (FileManager.isValidFile(rocketExplClassicTag) && FileManager.isValidFile(rocketExplRemasterTag)) {
            if (FileManager.deleteFile(rocketExplRemasterTag)) {
                FileManager.copyFile(rocketExplClassicTag, rocketExplRemasterTag);
            }
        }

        // Energy Sword Ready
        File swordReadyClassicTag = FileManager.createSubdirectoryFile(rootTagDirectory, SWORD_READY_PATH_CLASSIC);
        File swordReadyRemasterTag = FileManager.createSubdirectoryFile(rootTagDirectory, SWORD_READY_PATH_REMASTER);
        if (FileManager.isValidFile(swordReadyClassicTag) && FileManager.isValidFile(swordReadyRemasterTag)) {
            if (FileManager.deleteFile(swordReadyRemasterTag)) {
                FileManager.copyFile(swordReadyClassicTag, swordReadyRemasterTag);
            }
        }

        // Sentinel Beam
//        File sentinelTagDir = FileManager.createSubdirectoryFile(rootTagDirectory, SENTINEL_SOUND_PATH);
//        if (FileManager.isValidDirectory(sentinelTagDir)) {
//            walkWeaponDirectory(sentinelTagDir);
//        }
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

    private File getClassicFile(File file) {
        return new File(file.getPath().replace("sound_remastered", "sound"));
    }

}
