package io;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import io.utility.OperatingSystemUtility;
import org.apache.commons.io.FileUtils;


/* Halo 2A Sound Restoration                    Chris Cruzen
 * FileManager                                    03.10.2023
 *
 * FileManager is a utility class that encapsulates behavior
 * for interacting with external files and directories.
 *
 * Note: This class relies on the apache.commons.io library.
 *       To compile, the package must be included as a
 *       project dependency.
 */

public class FileManager {
  
  
    /*--- Read Methods ---*/
    
    public static List<File> getDirectoryContents(File directory) {
    	File[] fileArray = directory.listFiles();
    	List<File> fileList = new ArrayList<File>();

    	for (int x = 0; x < fileArray.length; x++)
    		fileList.add(fileArray[x]);
    	
    	return fileList;
    }
    
    public static List<File> getDirectoryFiles(File dir) { 
      File[] fileArray = dir.listFiles();
      List<File> fileList = new ArrayList<File>();
      
      for (int x = 0; x < fileArray.length; x++)
          if (fileArray[x].isFile())
              fileList.add(fileArray[x]);
      
      return fileList;
    }
    
    public static List<File> getSubdirectories(File dir) {
      File[] fileArray = dir.listFiles();
      List<File> fileList = new ArrayList<File>();
      
      for (int x = 0; x < fileArray.length; x++)
          if (fileArray[x].isDirectory())
              fileList.add(fileArray[x]);
      
      return fileList;
    }

//    public static List<String> readConfigFileContents(File file) {
//      try {
//        List<String> lines = Files.readAllLines(Paths.get(file.toString()), StandardCharsets.UTF_8);
//        List<String> filteredLines = new ArrayList<String>();
//        for (int x = 0; x < lines.size(); x++) {
//          String cleanLine = CodeReader.removeComments(lines.get(x), "#");
//          if (cleanLine.length() > 0)
//            filteredLines.add(cleanLine);
//        }
//        return filteredLines;
//      } catch (Exception e) {
//        return null;
//      }
//    }
    
    
    /*--- Write Methods ---*/
    
    public static boolean renameFileOrDirectory(File oldDir, File newDir) {
      try {
          oldDir.renameTo(newDir);
          return true;
      } catch (Exception e) {
          return false;
      }
    }
    
    public static boolean deleteFileOrDirectory(File targetFile) { 
      try {
          FileUtils.deleteDirectory(targetFile);
          return true;
      } catch (Exception e) {
          return false;
      }
    };

    public static boolean copyFileOrDirectory(File sourceFile, File destinationFile) {
      try {
        FileUtils.copyDirectory(sourceFile, destinationFile);
        return true;
      } catch (Exception e) {
        return false;
      }
    }
    
    
    /*--- Utility Methods ---*/

    public static boolean isValidDirectory(File file) {
      return file.isDirectory();
    }

    public static String getFileOrDirectoryName(File file) {
      Pattern regex = Pattern.compile("[\\\\/][^\\\\/]+$");
      Matcher m = regex.matcher(file.toString());
      
      if(m.find())
        return m.group(0).substring(1, m.group(0).length());
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
      Matcher m = regex.matcher(lastElement);
      
      if (m.find())
          return true;
      else
          return false;
    }
}
