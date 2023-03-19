
import io.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tags.*;
import tags.general.*;
import tags.levels.*;

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

    private final String REMASTERED_SOUND_PATH = "/sound_remastered";

    private final String[] TAG_DELETE_SUBSTRINGS = {"swtnr", "lfe", "lod.", "_lod"};
    private final String[] TAG_IGNORE_SUBSTRINGS = {"sound_looping"};
    private final TagGroup[] TAG_GROUPS = {

            // Universal Campaign Sounds
            new AmbienceTags(),
            new CharacterTags(),
            new InterfaceTags(),
            new MusicTags(),
            new VehicleTags(),
            new WeaponTags(),

            // Specific Levels
            new TheArmoryTags(),
            new CairoStationTags(),
            new OutskirtsTags(),
            new MetropolisTags(),
            new TheArbiterTags(),
            new TheOracleTags(),
            new DeltaHaloTags(),
            new RegretTags(),
            new SacredIconTags(),
            new GravemindTags(),
            // Uprising Fine
            // High Charity Fine
            new TheGreatJourneyTags()
    };



    /*--- Variables ---*/

    private File rootTagDirectory = null;
    private boolean discretionaryPreservation = true;

    private List<File> allSoundFiles;

    private int totalTagsModified = 0;
    private int totalTagsReplaced = 0;
    private int totalTagsDeleted = 0;
    private int totalTagsPreserved = 0;
    private int totalProblems = 0;



    /*--- Public Methods ---*/

    public SoundRestorer() {
        checkForConfigFile();

        initializeRootTagDirectory();
        initializePreservationPreference();

        initializeSoundFileList();
    }

    public void restoreSound() {
        printStartMessage();

        // Restore Each Tag Group
        for (int x = 0; x < TAG_GROUPS.length; x++) {
            restoreAudioTags(TAG_GROUPS[x]);
            if (x == 5) System.out.println("  ---");
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

    private void initializeSoundFileList() {
        allSoundFiles = new ArrayList<>();
        buildSoundFileList(createTagSubdir(REMASTERED_SOUND_PATH));
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
                allSoundFiles.remove(remasteredTag);

            } else {
                if (discretionaryPreservation && !shouldBeDeleted) {

                    // Preserve Tag
                    totalTagsPreserved++;
                    allSoundFiles.remove(remasteredTag);

                } else {

                    // Delete Tag
                    FileManager.deleteFile(remasteredTag);
                    totalTagsModified++;
                    totalTagsDeleted++;
                    allSoundFiles.remove(remasteredTag);
                }
            }
        } else {

            // Preserve Tag
            totalTagsPreserved++;
            allSoundFiles.remove(remasteredTag);
        }
    }



    /*--- Utility Methods ---*/

    /* Recursively walks `sound_remastered` directory, building a list of
     * all sound tags to be referenced later when generating statistics.
     */
    private void buildSoundFileList(File file) {

        if (FileManager.isValidFile(file)) {

            // Print Tag Status
            allSoundFiles.add(file);

        } else if(FileManager.isValidDirectory(file)) {

            // Recourse Through Subdirectories
            List<File> subDirs = FileManager.getSubdirectories(file);
            for (File dir: subDirs) {
                buildSoundFileList(dir);
            }

            // Recourse Through Files
            List<File> files = FileManager.getDirectoryFiles(file);
            for (File f: files) {
                buildSoundFileList(f);
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

    private void replaceTag(String sourcePath, String replacementPath) {
        File sourceTag = createTagSubdir( sourcePath);
        File replacementTag = createTagSubdir( replacementPath);
        if (FileManager.isValidFile(sourceTag) && FileManager.isValidFile(replacementTag)) {
            FileManager.deleteFile(sourceTag);
            FileManager.copyFile(replacementTag, sourceTag);
            totalTagsModified++;
            totalTagsReplaced++;
        } else {
            System.out.printf("    Error replacing '%s'%n", sourcePath);
            totalProblems++;
        }
    }

    private void deleteTag(String tagPath) {
        File tag = createTagSubdir(tagPath);
        if (FileManager.isValidFile(tag)) {
            FileManager.deleteFile(tag);
            totalTagsModified++;
            totalTagsDeleted++;
        } else {
            System.out.printf("    Error deleting '%s'%n", tag);
            totalProblems++;
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
        totalTagsPreserved += allSoundFiles.size();

        System.out.printf("\nClassic Audio Restored!%n");
        System.out.printf("  .------------------------.%n");
        System.out.printf("  | Updated%s%d |\n", getPadding(15, totalTagsModified), totalTagsModified);
        System.out.printf("  | Replaced%s%d |\n", getPadding(14, totalTagsReplaced), totalTagsReplaced);
        System.out.printf("  | Deleted%s%d |\n", getPadding(15, totalTagsDeleted), totalTagsDeleted);
        System.out.printf("  | Preserved%s%d |\n", getPadding(13, totalTagsPreserved), totalTagsPreserved);
        System.out.printf("  |------------------------|%n");
        System.out.printf("  | Problems%s%d |\n", getPadding(14, totalProblems), totalProblems);
        System.out.printf("  '------------------------'%n");

        System.out.printf("%nFinal Steps");
        if (totalProblems == 0) {
            System.out.printf("%n  1. Follow the directions outlined in '\\Project Resources\\Notes\\Manual Fixes.txt'.");
            System.out.printf("%n  2. Copy '\\Source\\utility\\BuildMaps.bat' to your H2EK installation folder");
            System.out.printf("%n     and run to generate the final map files. This will take a few minutes.");
            System.out.printf("%n  3. Copy generated maps to the following folder and you're ready to boot up MCC!%n");
            System.out.printf("%n     '<MCC installation>\\halo2\\h2_maps_win64_dx11'%n");

        } else {
            System.out.printf("%n  Looks like there were some issues restoring audio.");
            System.out.printf("%n  Either the tags directory wasn't freshly extracted");
            System.out.printf("%n  or MCC has been updated. If you've extracted the tags");
            System.out.printf("%n  again and are still seeing this issue, 343i renamed");
            System.out.printf("%n  or moved a tag file and this script will need an update.%n");
        }

    }

    private String getPadding(int fullLine, int number) {
        int numSpaces = fullLine - String.valueOf(number).length();
        StringBuilder padding = new StringBuilder();
        for (int x = 0; x < numSpaces; x++) {
            padding.append(" ");
        }

        return padding.toString();
    }
}
