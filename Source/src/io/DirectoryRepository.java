package io;


import java.io.File;
import java.util.List;

import static javafx.application.Platform.exit;

public class DirectoryRepository {


    /*--- Constants ---*/

    private final String CONFIG_FILE_PATH = "input\\/Config.txt";
    private final String DIR_CONFIG_PREFIX = "TAGS_DIRECTORY=";
    private final String CONFIG_DELIMITER = "=";


    /*--- Variables ---*/

    private File rootTagDirectory = null;


    /*--- Public Methods ---*/

    public DirectoryRepository() {
        initializeRootTagDirectory();
    }


    /*--- Private Methods ---*/

    private void initializeRootTagDirectory() {
        List<String> dirFileContents = FileManager.readFileContents(new File(CONFIG_FILE_PATH));

        if (dirFileContents != null) {
            for (String line : dirFileContents)
                if (line.contains(DIR_CONFIG_PREFIX)) {
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
}
