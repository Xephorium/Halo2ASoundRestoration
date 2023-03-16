
import io.FileManager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
    private final String CONFIG_PRES_PREFIX = "DISCRETIONARY_PRESERVATION";
    private final String CONFIG_DELIMITER = "=";



    /*--- General Constants ---*/

    private final String[] TAG_DELETE_SUBSTRINGS = {"swtnr", "lfe", "lod.", "_lod"};
    private final String[] TAG_IGNORE_SUBSTRINGS = {"sound_looping"};
    private final String[] EMPTY = {};



    /*--- Weapon Constants ---*/

    private final String WEAPONS_SUBDIR_PATH = "/sound_remastered/weapons";
    private final String[] WEAPON_REPLACE_PATHS = {

            // Beam Rifle NPC Fire
            "/sound_remastered/weapons/beam_rifle/beam_rifle_fire.sound",
            "/sound_remastered/weapons/beam_rifle/beam_rifle_fire_noplayer.sound",

            // Beam Rifle Zoom In/Out
            "/sound/weapons/beam_rifle/beam_rifle_zoom_in.sound",
            "/sound_remastered/weapons/beam_rifle/beamrifle_zoom/in.sound",
            "/sound/weapons/beam_rifle/beam_rifle_zoom_out.sound",
            "/sound_remastered/weapons/beam_rifle/beamrifle_zoom/out.sound",

            // Rocket Explosion
            "/sound/weapons/rocket_launcher/rocket_expl.sound",
            "/sound_remastered/weapons/rocket_launcher/projectile_exp/close.sound",

            // Sentinel Beam Fire
            "/sound/characters/sentinel/sentinel_gun/sent_gun/in.sound",
            "/sound_remastered/characters/sentinel/sentinel_gun_lod/sent_gun/in.sound",
            "/sound/characters/sentinel/sentinel_gun/sent_gun/loop.sound",
            "/sound_remastered/characters/sentinel/sentinel_gun_lod/sent_gun/loop.sound",
            "/sound/characters/sentinel/sentinel_gun/sent_gun/out.sound",
            "/sound_remastered/characters/sentinel/sentinel_gun_lod/sent_gun/out.sound",

            // Turret NPC Fire
            "/sound_remastered/weapons/chain_gun/turret_fire.sound",
            "/sound_remastered/weapons/chain_gun/turret_fire_mono.sound",

            // Sword Ready
            "/sound/weapons/energy_sword/sword_ready.sound",
            "/sound_remastered/weapons/energy_sword/energy_sword_ready.sound",

            // Regret Throne Cannon (Yes, the directories are right here - 343i is an anthill.)
            "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/in.sound",
            "/sound_remastered/vehicles/gravity_throne/weapon_fire/in.sound",
            "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/loop.sound",
            "/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/loop.sound",
            "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/out.sound",
            "/sound_remastered/vehicles/gravity_throne/weapon_fire/out.sound",

            // Regret Throne Cannon Safety Fix (Just in case 343i audits their tiny ant tunnels.)
            "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/in.sound",
            "/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/in.sound",
            "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/out.sound",
            "/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/out.sound",

    };
    private final String[] WEAPON_DELETE_PATHS = {

            // Plasma Rifle Bottom Fire
            "/sound_remastered/weapons/plasma_rifle/plas_rifle_fire_bottom.sound",

            // Covenant Carbine Impact Sound
            "/sound_remastered/weapons/impacts_riccs/cov_carbine_imp.sound",

            // Warthog Turret Spin
            "/sound_remastered/weapons/chain_gun/chaingun_spin.sound",
    };



    /*--- Vehicle Constants ---*/

    private final String VEHICLE_SOUND_PATH = "/sound_remastered/vehicles";
    private final String[] VEHICLE_REPLACE_PATHS = {

            // Warthog Crash
            "/sound/vehicles/warthog/jeep_crash_hard.sound",
            "/sound_remastered/vehicles/warthog/warthog_suspension.sound",

            // Scorpion Fire
            "/sound/vehicles/scorpion/scorpion_explosion_up.sound",
            "/sound_remastered/vehicles/scorpion/projectile_exp/close.sound",

            // Scorpion Crash
            "/sound/vehicles/scorpion/scorpion_crash.sound",
            "/sound_remastered/vehicles/scorpion/scorpion_suspension.sound",

            // Ghost Fire
            "/sound/vehicles/ghost/ghost_fire.sound",
            "/sound_remastered/vehicles/ghost/ghost_fire/fire.sound",

            // Wraith Fire
            "/sound/vehicles/wraith/wraith_fire_mortar.sound",
            "/sound_remastered/vehicles/wraith/wraith_fire_mortar/fire.sound",

            // Wraith Explode
            "/sound/vehicles/wraith/wraith_plasma_expl.sound",
            "/sound_remastered/vehicles/wraith/wraith_mortar_explode/explode.sound",

            // Banshee Fire
            "/sound/vehicles/banshee/banshee_fire.sound",
            "/sound_remastered/vehicles/banshee/banshee_fire/fire.sound",

            // Phantom Extra Fire
            "/sound_remastered/vehicles/phantom/phantom_turret_fire.sound",
            "/sound_remastered/vehicles/phantom/phantom_turret_fire_turret.sound"

    };
    private final String[] VEHICLE_DELETE_PATHS = {

            // Scorpion Reload
            "/sound_remastered/vehicles/scorpion/scorpion_reload.sound",

            // Scorpion Turret Move
            "/sound_remastered/vehicles/scorpion/scorp_turret_move.sound_looping",
            "/sound_remastered/vehicles/scorpion/turret_move/in.sound",
            "/sound_remastered/vehicles/scorpion/turret_move/loop.sound",
            "/sound_remastered/vehicles/scorpion/turret_move/out.sound",

            // Ghost Extra Boost
            "/sound_remastered/vehicles/ghost/ghost_boost_left/track2/loop.sound",
            "/sound_remastered/vehicles/ghost/ghost_boost_right/track2/loop.sound",

            // Spectre Extra Boost
            "/sound_remastered/vehicles/spectre/spectre_boost_l/track2/loop.sound",
            "/sound_remastered/vehicles/spectre/spectre_boost_r/track2/loop.sound",

            // Phantom Turret Animation
            "/sound_remastered/vehicles/phantom/phantom_turret_anims/lower.sound",
            "/sound_remastered/vehicles/phantom/phantom_turret_anims/raise.sound",

            // Regret Extra Sounds
            "/sound_remastered/vehicles/gravity_throne/regret_teleport_reflection.sound",
    };



    /*--- Character Constants ---*/

    private final String SENTINEL_SOUND_PATH = "/sound_remastered/characters/sentinel";
    private final String[] CHARACTER_REPLACE_PATHS = {

            // Sentinel Enforcer Needles
            "/sound/characters/sentinel/needler_fire_sentinel_enforcer.sound",
            "/sound_remastered/characters/sentinel/sentinel_enforcer_laser.sound"

    };
    private final String[] CHARACTER_DELETE_PATHS = {

            // Sentinel Enforcer Rocket
            "/sound_remastered/characters/sentinel/sentinel_rocket_launcher/sentinel_rocket/in.sound"
    };



    /*--- UI Constants ---*/

    private final String UI_SOUND_PATH = "/sound_remastered/ui";
    private final String[] UI_DELETE_PATHS = {

            // Shield Pop
            "/sound_remastered/ui/shield_pop.sound"
    };



    /*--- Effects Constants ---*/

    private final String EFFECTS_SOUND_PATH = "/sound_remastered/visual_effects";
    private final String[] EFFECTS_PRESERVE_PATHS = {

            // Cairo Welder
            "ss_arc_welder\\welder\\in.sound",
            "ss_arc_welder\\welder\\loop.sound",

            // Orbital Platform Explosions
            "malta_explode.sound",
            "ss_distship_explode.sound",

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
            "/sound/visual_effects/inamberclad_flyby/slipspace.sound",
            "/sound_remastered/visual_effects/inamberclad_flyby/slipspace/highcharity_slipspace.sound",

            // Perplexingly, removing these sounds will brick the build? Replacing
            // them with the quietest sound I can find in the classic tags.
            // TODO: Add silent tag to project.
            "/sound/materials/soft/cloth_hits/cloth_hits.sound",
            "/sound_remastered/visual_effects/ss_smalldoor_explode.sound",

};
    private final String[] EFFECTS_DELETE_PATHS = {};



    /*--- Ambience Constants ---*/

    private final String[] AMBIENCE_SOUND_PATHS = {

            // General
            "/sound_remastered/ambience/device_machines",

            // Cairo Station
            "/sound_remastered/ambience/spacestation",
            "/sound_remastered/ambience/sound_scenery/computer1",
            "/sound_remastered/ambience/sound_scenery/computer2",
            "/sound_remastered/ambience/sound_scenery/computer3",
            "/sound_remastered/ambience/sound_scenery/computer4",
            "/sound_remastered/ambience/sound_scenery/computer5",
            "/sound_remastered/ambience/sound_scenery/computer6",


    };
    private final String[] AMBIENCE_PRESERVE_PATHS = {

            // Cairo Station Background Loops
            "spacestation\\loop.sound",
            "smallroom\\loop.sound",

            // Cairo Station Tech
            "ss_"

    };
    private final String[] AMBIENCE_REPLACE_PATHS = {

            // Perplexingly, removing this sound will brick the build? Replacing
            // it with the quietest sound I can find in the classic tags.
            // TODO: Add silent tag to project.
            "/sound/materials/soft/cloth_hits/cloth_hits.sound",
            "/sound_remastered/ambience/spacestation/docking_clangs/dockingcov_incoming.sound",

            // Cairo Station Muffled Boarding Action
            "/sound/ambience/spacestation/ss_behind_bulkhead/behind_bulkhead/loop.sound",
            "/sound_remastered/ambience/spacestation/behind_bulkhead/loop.sound",

            // Cairo Station Shield Charger
            "/sound/ambience/device_machines/ss_armory_shield_charger/track2/in.sound",
            "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/in.sound",
            "/sound/ambience/device_machines/ss_armory_shield_charger/track2/loop.sound",
            "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/loop.sound",
            "/sound/ambience/device_machines/ss_armory_shield_charger/track2/out.sound",
            "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/out.sound",

            // High Charity Doors
            "/sound/ambience/device_machines/high_door_delay/a50_door_delay.sound",
            "/sound_remastered/ambience/device_machines/high_door_delay/high_door_delay.sound",

            // High Charity Lift Doors
            "/sound/ambience/device_machines/high_lift_doors_open_close/lift_doors_close.sound",
            "/sound_remastered/ambience/device_machines/high_lift_doors_open_close/close/highcharity_liftdoor_close.sound",
            "/sound/ambience/device_machines/high_lift_doors_open_close/lift_doors_open.sound",
            "/sound_remastered/ambience/device_machines/high_lift_doors_open_close/open/highcharity_liftdoor_open.sound",

            // High Sanctum Doors
            "/sound/ambience/device_machines/high_sanctum_door/close1.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_door/close1/sanctumdoor_close1.sound",
            "/sound/ambience/device_machines/high_sanctum_door/close2.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_door/close2/sanctumdoor_close2.sound",
            "/sound/ambience/device_machines/high_sanctum_door/closed.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_door/closed/sanctumdoor_closed.sound",
            "/sound/ambience/device_machines/high_sanctum_door/open1.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_door/open1/sanctumdoor_open1.sound",
            "/sound/ambience/device_machines/high_sanctum_door/open2.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_door/open2/sanctumdoor_open2.sound",
            "/sound/ambience/device_machines/high_sanctum_door/opened.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_door/opened/sanctumdoor_opened.sound",

            // High Sanctum Lift Stop
            "/sound/ambience/device_machines/high_sanctum_lift_stop/high_sanctum_lift_stop.sound",
            "/sound_remastered/ambience/device_machines/high_sanctum_lift_stop/sanctum_lift_stop.sound",

    };
    private final String[] AMBIENCE_DELETE_PATHS = {};



    /*--- Music Constants ---*/

    private final String MUSIC_SOUND_PATH = "/sound_remastered/music";



    /*--- Variables ---*/

    private File rootTagDirectory = null;
    private boolean discretionaryPreservation = true;

    private int totalTagsModified = 0;
    private int totalTagsReplaced = 0;
    private int totalTagsDeleted = 0;
    private int totalTagsPreserved = 0;



    /*--- Public Methods ---*/

    public SoundRestorer() {
        checkForConfigFile();
        initializeRootTagDirectory();
        initializePreservationPreference();
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

//        System.out.println(createTagSubdir( "/test"));
//        System.out.println(createTagSubdir( escapeSlashes("/test")));

        //checkDirectoryForClassicTags(createTagSubdir( "/sound_remastered/ambience/device_machines"), 0);
    }



    /*--- Config Initialization Methods ---*/

    private void checkForConfigFile() {
        if (FileManager.readFileContents(new File(CONFIG_FILE_PATH)) == null) {
            System.out.println("CONFIG ERROR: Configuration file missing.");
            System.exit(1);
        }
    }

    private void initializeRootTagDirectory() {
        rootTagDirectory = new File(readValueFromConfig(CONFIG_DIR_PREFIX));
        if (!FileManager.isValidDirectory(rootTagDirectory)) {
            System.out.printf(
                    "CONFIG ERROR: Tag directory '%s' does not exist.%n",
                    rootTagDirectory.getPath()
            );
            System.exit(1);
        }
    }

    private void initializePreservationPreference() {
        String preservationValue = readValueFromConfig(CONFIG_PRES_PREFIX).toLowerCase();
        discretionaryPreservation = preservationValue.equals("true");
    }

    private String readValueFromConfig(String key) {
        List<String> dirFileContents = FileManager.readFileContents(new File(CONFIG_FILE_PATH));

        if (dirFileContents != null) {
            for (String line : dirFileContents)
                if (line.contains(key)) {
                    return line.split(CONFIG_DELIMITER)[1].trim();
                }
        } else {
            System.out.println("CONFIG ERROR: Configuration file disappeared suddenly. O.O");
            System.exit(1);
        }

        System.out.printf("CONFIG ERROR: variable '%s' not found in config file.%n", key);
        System.exit(1);
        return "";
    }



    /*--- Recursive Tag Cleanup Methods ---*/

    /* Recursively walks the remastered tag directory, replacing each with their classic
     * counterpart and deleting those that aren't needed.
     */
    private void walkTagDirectory(File remasteredFile, String[] preservePaths) {

        if (FileManager.isValidFile(remasteredFile)) {
            ProcessTag(remasteredFile, preservePaths);

        } else if(FileManager.isValidDirectory(remasteredFile)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(remasteredFile);
            for (File dir: subDirs) walkTagDirectory(dir, preservePaths);

            // Recourse Through Tags
            List<File> files = FileManager.getDirectoryFiles(remasteredFile);
            for (File file: files) walkTagDirectory(file, preservePaths);
        }
    }

    /* Replaces, deletes, or preserves a single tag based on substring checks
     * and whether discretionary preservation is enabled in the project.
     */
    private void ProcessTag(File remasteredTag, String[] preservePaths) {
        File classicTag = getClassicFile(remasteredTag);
        String remasteredTagName = FileManager.getFileOrDirectoryName(remasteredTag);
        assert remasteredTagName != null;

        boolean shouldBeIgnored = Arrays.stream(TAG_IGNORE_SUBSTRINGS).anyMatch(remasteredTagName::contains);
        boolean shouldBePreserved = Arrays.stream(preservePaths).anyMatch(remasteredTag.toString()::contains);
        boolean shouldBeDeleted = Arrays.stream(TAG_DELETE_SUBSTRINGS).anyMatch(remasteredTagName::contains);

        if (!shouldBeIgnored && !(discretionaryPreservation && shouldBePreserved)) {
            if (FileManager.exists(classicTag)) {

                // Replace Tag w/ Classic
                FileManager.deleteFile(remasteredTag);
                FileManager.copyFile(classicTag, remasteredTag);
                totalTagsModified++;
                totalTagsReplaced++;

            } else {
                if (discretionaryPreservation && !shouldBeDeleted) {

                    // Preserve Tag
                    totalTagsPreserved++;

                } else {

                    // Delete Tag
                    FileManager.deleteFile(remasteredTag);
                    totalTagsModified++;
                    totalTagsDeleted++;
                }
            }
        } else {

            // Preserve Tag
            totalTagsPreserved++;
        }
    }



    /*--- Weapon Audio Restoration Methods ---*/

    private void restoreWeaponAudio() {
        File remasteredWeaponDir = createTagSubdir( WEAPONS_SUBDIR_PATH);
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
        File vehicleTagDir = createTagSubdir( VEHICLE_SOUND_PATH);
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
        File sentinelTagDir = createTagSubdir( SENTINEL_SOUND_PATH);
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
        File uiTagDir = createTagSubdir( UI_SOUND_PATH);
        if (FileManager.isValidDirectory(uiTagDir)) walkTagDirectory(uiTagDir, EMPTY);

        // Delete Necessary UI Tags
        for (String uiDeletePath : UI_DELETE_PATHS) {
            deleteTag(uiDeletePath);
        }
    }



    /*--- Effects Restoration Methods ---*/

    private void restoreEffectsAudio() {

        // Update Effects
        File effectsTagDir = createTagSubdir( EFFECTS_SOUND_PATH);
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

        // Update Each Ambience Directory
        for (String subdir: AMBIENCE_SOUND_PATHS) {
            File ambienceTagDir = createTagSubdir( subdir);
            if (FileManager.isValidDirectory(ambienceTagDir)) walkTagDirectory(ambienceTagDir, AMBIENCE_PRESERVE_PATHS);
        }

        performManualAmbienceTagFixes();
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
        File musicTagDir = createTagSubdir( MUSIC_SOUND_PATH);
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
     *     createTagSubdir( "/sound_remastered")
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
        File sourceTag = createTagSubdir( sourcePath);
        File replacementTag = createTagSubdir( replacementPath);
        if (FileManager.isValidFile(sourceTag)) FileManager.deleteFile(sourceTag);
        if (FileManager.isValidFile(replacementTag)) {
            FileManager.copyFile(replacementTag, sourceTag);
            totalTagsModified++;
            totalTagsReplaced++;
        }
    }

    private void deleteTag(String tagPath) {
        File tag = createTagSubdir( tagPath);
        if (FileManager.isValidFile(tag)) {
            FileManager.deleteFile(tag);
            totalTagsModified++;
            totalTagsDeleted++;
        }
    }

    private File getClassicFile(File file) {
        return new File(file.getPath().replace("sound_remastered", "sound"));
    }

    private File createTagSubdir(String path) {
        return FileManager.createSubdirectoryFile(rootTagDirectory, escapeForwardSlashes(path));
    }

    private String escapeForwardSlashes(String path) {
        return path.replaceAll("/", "\\/");
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
