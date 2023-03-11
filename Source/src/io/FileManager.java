package io;

import io.utility.OperatingSystemUtility;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/* Halo 2A Sound Restoration                    Chris Cruzen
 * FileManager                                    03.10.2023
 *
 * FileManager is a utility class that encapsulates behavior
 * for interacting with external files and directories.
 *
 * Note: This class relies on the apache.commons.io library.
 *       To compile, the package must be included as a
 *       classpath of the project's build script.
 */

public class FileManager {


    /*--- Read Methods ---*/

    public static List<File> getDirectoryContents(File directory) {
        File[] fileArray = directory.listFiles();
        List<File> fileList = new ArrayList<>();

        if (fileArray != null) Collections.addAll(fileList, fileArray);

        return fileList;
    }

    public static List<File> getDirectoryFiles(File dir) {
        File[] fileArray = dir.listFiles();
        List<File> fileList = new ArrayList<File>();

        if (fileArray != null)
            for (File file : fileArray)
                if (file.isFile()) fileList.add(file);

        return fileList;
    }

    public static List<File> getSubdirectories(File dir) {
        File[] fileArray = dir.listFiles();
        List<File> fileList = new ArrayList<File>();

        if (fileArray != null)
            for (File file : fileArray)
                if (file.isDirectory()) fileList.add(file);

        return fileList;
    }

    public static List<String> readFileContents(File file) {
        List<String> fileContents = new ArrayList<>();
        try {
            return Files.readAllLines(Paths.get(file.toString()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }


    /*--- Write Methods ---*/

    public static boolean renameFileOrDirectory(File oldDir, File newDir) {
        try {
            return oldDir.renameTo(newDir);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteDirectory(File targetFile) {
        try {
            FileUtils.deleteDirectory(targetFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteFile(File targetFile) {
        try {
            FileUtils.deleteQuietly(targetFile);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public static boolean copyDirectory(File sourceFile, File destinationFile) {
        try {
            FileUtils.copyDirectory(sourceFile, destinationFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean copyFile(File sourceFile, File destinationFile) {
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*--- Utility Methods ---*/

    public static boolean exists(File file) {
        return file.exists();
    }

    public static boolean isValidFile(File file) {
        return file.isFile();
    }

    public static boolean isValidDirectory(File file) {
        return file.isDirectory();
    }

    public static File createSubdirectoryFile(File file, String additionalPath) {
        return new File(file.getPath() + additionalPath);
    }

    public static String getFileOrDirectoryName(File file) {
        Pattern regex = Pattern.compile("[\\\\/][^\\\\/]+$");
        Matcher m = regex.matcher(file.toString());

        if (m.find())
            return m.group(0).substring(1);
        else
            return null;
    }

    public static String getParentDirectory(File file) {
        if (OperatingSystemUtility.isWindows())
            return (file.toString()).split("[\\\\/][^\\\\/]+$")[0];
        else
            return (file.toString()).split("[\\/][^\\/]+$")[0];
    }

    private static boolean fileHasExtension(File file) {
        String lastElement = getFileOrDirectoryName(file);
        Pattern regex = Pattern.compile("\\S+\\.\\S+$");

        if (lastElement != null) {
            return regex.matcher(lastElement).find();
        } else {
            return false;
        }
    }
}
