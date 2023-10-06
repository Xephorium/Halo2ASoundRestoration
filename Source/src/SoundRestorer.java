
import io.TagModifier;
import io.FileManager;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.utility.BinaryTypeConverter;
import prefs.RestorationPreferences;
import tags.*;
import tags.general.*;
import tags.levels.*;

/* Halo 2A Sound Restoration                         Chris Cruzen
 * SoundRestorer                                       03.10.2023
 *
 * SoundRestorer is the primary driver class of the Halo 2A Sound
 * Restoration project.
 *
 * This file restores the campaign's classic audio in 2 steps:
 *
 *   1. Read the user-specified tag directory and other relevant
 *      restoration options from the project's config file.
 *
 *   2. Iterate through the root tag directory's `sound_remastered`
 *      folder, replacing, deleting, and modifying tags as necessary
 *      to restore the campaign's original audio. For more details on
 *      which tags replaced/deleted/modified and why, see the file
 *      `Halo 2 MCC Modding.txt`, where I recorded my findings
 *      throughout the process of building this script.
 */

public class SoundRestorer {



    /*--- Config Constants ---*/

    private final String CONFIG_FILE_PATH = "input\\/Config.txt";
    private final String CONFIG_DIR_PREFIX = "TAGS_DIRECTORY";
    private final String CONFIG_PRES_PREFIX = "DISCRETIONARY_PRESERVATION";
    private final String CONFIG_MUSIC_VOLUME_PREFIX = "MUSIC_VOLUME";
    private final String CONFIG_DELIMITER = "=";



    /*--- General Constants ---*/

    private final String REMASTERED_SOUND_PATH = "/sound_remastered";
    private final String CLASSIC_SOUND_PATH = "/sound";
    private final String CLASSIC_MUSIC_PATH = "/scenarios/solo";

    private final String[] TAG_DELETE_SUBSTRINGS = {"swtnr", "lfe", "lod.", "_lod"};
    private final String[] TAG_IGNORE_SUBSTRINGS = {"sound_looping"};
    private TagGroup[] TAG_GROUPS;



    /*--- Variables ---*/

    private File rootTagDirectory = null;
    private boolean discretionaryPreservation = true;
    private int musicVolumeModifier = 0;

    private List<File> allSoundTagsNotUpdated;
    private List<File> allSoundTagsNotModified;


    private int totalTagsUpdated = 0;
    private int totalTagsReplaced = 0;
    private int totalTagsDeleted = 0;
    private int totalTagsModified = 0;
    private int totalTagsPreserved = 0;
    private int totalProblems = 0;



    /*--- Public Methods ---*/

    public SoundRestorer() {
        checkForConfigFile();
        initializeRootTagDirectory();
        //initializePreservationPreference();
        initializeMusicGainModifier();

        initializeTagGroups();

        initializeSoundFileLists();
    }

    public void restoreSound() {
        printStartMessage();

        // Restore Each Tag Group
        for (int x = 0; x < TAG_GROUPS.length; x++) {
            restoreAudioTags(TAG_GROUPS[x]);
            if (x == 6) System.out.println("  ---");
        }

        printReport();
    }



    /*--- Initialization Methods ---*/

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

    private void initializeMusicGainModifier() {
        String modifierValue = readValueFromConfig(CONFIG_MUSIC_VOLUME_PREFIX).toLowerCase();
        musicVolumeModifier = Integer.parseInt(modifierValue);
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

    private void initializeTagGroups() {
        RestorationPreferences prefs = new RestorationPreferences(musicVolumeModifier);

        TAG_GROUPS = new TagGroup[] {

                // Universal Campaign Sounds
                new AmbienceTags(prefs),
                new CharacterTags(prefs),
                new DialogTags(prefs),
                new InterfaceTags(prefs),
                new MusicTags(prefs),
                new VehicleTags(prefs),
                new WeaponTags(prefs),

                // Specific Levels
                new TheArmoryTags(prefs),
                new CairoStationTags(prefs),
                new OutskirtsTags(prefs),
                new MetropolisTags(prefs),
                new TheArbiterTags(prefs),
                new TheOracleTags(prefs),
                new DeltaHaloTags(prefs),
                new RegretTags(prefs),
                new SacredIconTags(prefs),
                new QuarantineZoneTags(prefs),
                new GravemindTags(prefs),
                new UprisingTags(prefs),
                new HighCharityTags(prefs),
                new TheGreatJourneyTags(prefs)
        };
    }

    private void initializeSoundFileLists() {
        allSoundTagsNotUpdated = new ArrayList<>();
        allSoundTagsNotModified = new ArrayList<>();
        buildSoundFileList(createTagSubdir(REMASTERED_SOUND_PATH), allSoundTagsNotUpdated);
        buildSoundFileList(createTagSubdir(CLASSIC_SOUND_PATH), allSoundTagsNotUpdated);
        buildSoundFileList(createTagSubdir(CLASSIC_MUSIC_PATH), allSoundTagsNotUpdated);
        buildSoundFileList(createTagSubdir(REMASTERED_SOUND_PATH), allSoundTagsNotModified);
        buildSoundFileList(createTagSubdir(CLASSIC_SOUND_PATH), allSoundTagsNotModified);
        buildSoundFileList(createTagSubdir(CLASSIC_MUSIC_PATH), allSoundTagsNotModified);
    }



    /*--- Tag Restoration Methods ---*/

    private void restoreAudioTags(TagGroup tagGroup) {

        System.out.printf("  %s%n", tagGroup.groupName);

        // Walk Tag Group Directories
        for (String subdir : tagGroup.recursePaths) {
            File remasteredDir = createTagSubdir(subdir);
            if (FileManager.isValidDirectory(remasteredDir))
                walkTagDirectory(remasteredDir, tagGroup.preservePaths);
        }

        // Replace Tag Group Files
        for (int x = 1; x < tagGroup.replacePaths.length; x += 2) {
            replaceTag(tagGroup.replacePaths[x], tagGroup.replacePaths[x-1]);
        }

        // Delete Tag Group Files
        for (String deletePath : tagGroup.deletePaths) {
            deleteTag(deletePath);
        }

        // Modify Tag Group Files
        for (TagMod tagMod : tagGroup.tagMods) {
            if (tagMod.getClass() != RecursiveTagMod.class)
                modifyTag(tagMod);
            else
                modifyAllTags((RecursiveTagMod) tagMod);
        }
    }

    /* Recursively walks the remastered tag directory, replacing each with their classic
     * counterpart and deleting those that aren't needed.
     */
    private void walkTagDirectory(File remasteredFile, String[] preservePaths) {

        if (FileManager.isValidFile(remasteredFile)) {

            // Handle Individual Tag
            ProcessTag(remasteredFile, preservePaths);

        } else if(FileManager.isValidDirectory(remasteredFile)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(remasteredFile);
            for (File dir: subDirs) walkTagDirectory(dir, preservePaths);

            // Iterate Through Tags
            List<File> files = FileManager.getDirectoryFiles(remasteredFile);
            for (File file: files) walkTagDirectory(file, preservePaths);
        } else {

            // Print Error & Update Statistics
            System.out.printf("    Error finding path '%s'%n", remasteredFile);
            totalProblems++;
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
                totalTagsUpdated++;
                totalTagsReplaced++;
                allSoundTagsNotUpdated.remove(remasteredTag);

            } else {
                if (discretionaryPreservation && !shouldBeDeleted) {

                    // Preserve Tag
                    totalTagsPreserved++;
                    allSoundTagsNotUpdated.remove(remasteredTag);

                } else {

                    // Delete Tag
                    FileManager.deleteFile(remasteredTag);
                    totalTagsUpdated++;
                    totalTagsDeleted++;
                    allSoundTagsNotUpdated.remove(remasteredTag);
                }
            }
        } else {

            // Preserve Tag
            totalTagsPreserved++;
            allSoundTagsNotUpdated.remove(remasteredTag);
        }
    }

    private void replaceTag(String sourcePath, String replacementPath) {
        File sourceTag = createTagSubdir( sourcePath);
        File replacementTag = createTagSubdir( replacementPath);
        if (FileManager.isValidFile(sourceTag) && FileManager.isValidFile(replacementTag)) {
            FileManager.deleteFile(sourceTag);
            FileManager.copyFile(replacementTag, sourceTag);
            totalTagsUpdated++;
            totalTagsReplaced++;
            allSoundTagsNotUpdated.remove(replacementTag);
        } else {
            System.out.printf("    Error replacing '%s'%n", sourcePath);
            totalProblems++;
        }
    }

    private void deleteTag(String tagPath) {
        File tag = createTagSubdir(tagPath);
        if (FileManager.isValidFile(tag)) {
            FileManager.deleteFile(tag);
            totalTagsUpdated++;
            totalTagsDeleted++;
            allSoundTagsNotUpdated.remove(tag);
        } else {
            System.out.printf("    Error deleting '%s'%n", tag);
            totalProblems++;
        }
    }

    private void modifyTag(TagMod tagMod) {
        File tagFile = createTagSubdir(tagMod.path);

        // Modify Tag Values
        if (TagModifier.modifyTag(tagFile, tagMod)) {

            // Log Modification Statistics
            if (allSoundTagsNotModified.contains(tagFile)) {
                totalTagsModified++;
                allSoundTagsNotModified.remove(tagFile);
            }

            // Log Update Statistics
            if (allSoundTagsNotUpdated.contains(tagFile)) {
                totalTagsUpdated++;
                allSoundTagsNotUpdated.remove(tagFile);
            }

        } else {

            // Print Error & Update Statistics
            System.out.printf("    Error modifying '%s'%n", tagFile);
            totalProblems++;

        }
    }

    private void modifyAllTags(RecursiveTagMod recursiveTagMod) {
        File file = createTagSubdir(recursiveTagMod.path);

        // Modify Tag Values
        if (FileManager.isValidFile(file)) {

            // Modify Sound Tag
            if (recursiveTagMod.onlyWithSubstring.isEmpty()) {
                modifyTag(recursiveTagMod);
            } else if (file.toPath().toString().contains(recursiveTagMod.onlyWithSubstring)) {
                modifyTag(recursiveTagMod);
            }

        } else if(FileManager.isValidDirectory(file)) {

            // Recourse Through Subdirectories
            List<File> innerDirs = FileManager.getSubdirectories(file);
            List<RecursiveTagMod> innerDirMods = new ArrayList<>();
            for (File innerDir : innerDirs) {
                String relativePath = innerDir.getPath().replace(rootTagDirectory.getPath(), "");
                innerDirMods.add(recursiveTagMod.cloneWithPath(relativePath));
            }
            for (RecursiveTagMod mod: innerDirMods) {
                modifyAllTags(mod);
            }

            // Recourse Through Files
            List<File> innerFiles = FileManager.getDirectoryFiles(file);
            List<RecursiveTagMod> innerFileMods = new ArrayList<>();
            for (File innerFile : innerFiles) {
                String relativePath = innerFile.getPath().replace(rootTagDirectory.getPath(), "");
                innerFileMods.add(recursiveTagMod.cloneWithPath(relativePath));
            }
            for (RecursiveTagMod mod: innerFileMods) {
                modifyAllTags(mod);
            }
        } else {

            // Print Error & Update Statistics
            System.out.printf("    Error finding path '%s'%n", file);
            totalProblems++;
        }
    }



    /*--- Utility Methods ---*/

    /* Recursively walks `sound_remastered` directory, building a list of
     * all sound tags to be referenced later when generating statistics.
     */
    private void buildSoundFileList(File file, List<File> list) {

        if (FileManager.isValidFile(file)) {

            // Print Tag Status
            list.add(file);

        } else if(FileManager.isValidDirectory(file)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(file);
            for (File dir: subDirs) {
                buildSoundFileList(dir, list);
            }

            // Recourse Through Files
            List<File> files = FileManager.getDirectoryFiles(file);
            for (File f: files) {
                buildSoundFileList(f, list);
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

    private File getClassicFile(File file) {
        return new File(file.getPath().replace("sound_remastered", "sound"));
    }

    private File createTagSubdir(String path) {
        return FileManager.createSubdirectoryFile(rootTagDirectory, escapeForwardSlashes(path));
    }

    private String escapeForwardSlashes(String path) {
        return path.replaceAll("/", "\\/");
    }



    /*--- Output Methods ---*/

    private void printStartMessage() {
        System.out.println("/////////////////////////////////");
        System.out.println("// H2A Total Audio Restoration //");
        System.out.println("/////////////////////////////////");
        System.out.println("\nRestoring Tags:");
    }

    private void printReport() {
        totalTagsPreserved += allSoundTagsNotUpdated.size();

        System.out.printf("\nClassic Audio Restored!%n");
        System.out.printf("  .---------------------------.%n");
        System.out.printf("  | Total Tags Changed%s%d |\n", getPadding(7, totalTagsUpdated), totalTagsUpdated);
        System.out.printf("  | > Replaced%s%d |\n", getPadding(15, totalTagsReplaced), totalTagsReplaced);
        System.out.printf("  | > Modified%s%d |\n", getPadding(15, totalTagsModified), totalTagsModified);
        System.out.printf("  | > Deleted%s%d |\n", getPadding(16, totalTagsDeleted), totalTagsDeleted);
        System.out.printf("  | > Preserved%s%d |\n", getPadding(14, totalTagsPreserved), totalTagsPreserved);
        System.out.printf("  |---------------------------|%n");
        System.out.printf("  | Problems%s%d |\n", getPadding(17, totalProblems), totalProblems);
        System.out.printf("  '---------------------------'%n");

        System.out.printf("%nFinal Steps");
        if (totalProblems == 0) {
            System.out.printf("%n  1. Copy '\\Source\\utility\\BuildMaps.bat' to your H2EK installation folder");
            System.out.printf("%n     and run it to generate the final map files. This will take about 10 minutes.");
            System.out.printf("%n  2. Copy the generated maps to their MCC folder and you're ready to play!");
            System.out.printf("%n     Generated Maps: '<H2EK installation>\\h2_maps_win64_dx11'");
            System.out.printf("%n     MCC Folder: '<MCC installation>\\halo2\\h2_maps_win64_dx11'%n");

        } else {
            System.out.printf("%n  Looks like there were some issues restoring audio.");
            System.out.printf("%n  Either the tags directory wasn't freshly extracted");
            System.out.printf("%n  or MCC has been updated. If you've extracted the tags");
            System.out.printf("%n  again and are still seeing this issue, 343i renamed");
            System.out.printf("%n  or moved a file and this script will need an update.");
            System.out.printf("%n  The errors above should help track down the change.%n");
        }
        System.out.printf("%n%s%n", getFormattedTime());

    }

    private String getPadding(int fullLine, int number) {
        int numSpaces = fullLine - String.valueOf(number).length();
        StringBuilder padding = new StringBuilder();
        for (int x = 0; x < numSpaces; x++) {
            padding.append(" ");
        }

        return padding.toString();
    }

    private String getFormattedTime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour() > 12 ? now.getHour() - 12 : now.getHour();
        return String.format("%02d:%02d:%02d", hour, now.getMinute(), now.getSecond());
    }
}
