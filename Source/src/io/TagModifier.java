package io;

import io.utility.TagTypeConverter;
import javafx.util.Pair;
import tags.TagModification;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* Halo 2A Sound Restoration                       Chris Cruzen
 * TagEditor                                         03.20.2023
 *
 * TagEditor manages read/write operations directly to Halo tag
 * files. Without a documented file structure, I've used manual
 * inspection of the binaries to reverse engineer which bytes
 * are altered via Guerilla. This allows the script to change
 * the volume, audible distance, and remaster support attributes
 * of sound tags programmatically. However, it's a pretty fragile
 * approach and may need to be adjusted with future MCC updates.
 */
public class TagModifier {


    /*--- Constants ---*/

    /* Byte Locations Within Byte Array
     *
     * Columns and rows assume binary file is printed as byte
     * pairs converted to hex, with each byte pair represented
     * by a 4 character hex string, 8 pairs to a row. This is
     * how sublime text opens binary files by default, but can
     * be reproduced with the printTagContents() method below.
     */

    // Exist in `*.sound` and `*.sound_looping` files
    private static final int GAIN_INDEX_SOUND = 122;         // 2 bytes - Row 8, column 6
    private static final int GAIN_INDEX_SOUND_LOOPING = 182; // 2 bytes - Row 12, column 4

    // Exist only in `*.sound` files
    private static final int MIN_DIST_INDEX_SOUND = 106;     // 2 bytes - Row 7, column 6
    private static final int MAX_DIST_INDEX_SOUND = 110;     // 2 bytes - Row 7, column 8
    private static final int CLASSIC_ONLY_INDEX = 97;        // 1 byte - Row 7, column 1.5


    /*--- Modify Method ---*/

    public static byte[] modifyTag(File tagFile, TagModification tagModification, byte[] byteArray) {

        // Identify File Type
        boolean isLoopFile = FileManager.getFileOrDirectoryName(tagFile).contains(".sound_looping");

        // Update Gain
        if (tagModification.gain != TagModification.NO_CHANGE) {
            int gainIndex = GAIN_INDEX_SOUND;
            if (isLoopFile) gainIndex = GAIN_INDEX_SOUND_LOOPING;
            byte[] newBytes = TagTypeConverter.intToBytes(tagModification.gain);
            if (newBytes != null) {
                byteArray[gainIndex] = newBytes[0];
                byteArray[gainIndex + 1] = newBytes[1];
            } else {
                System.out.printf("    Error updating gain for '%s'%n", tagFile);
                return null;
            }
        }

        // Update Min Distance
        if (byteArray != null && tagModification.minDist != TagModification.NO_CHANGE) {
            byte[] newBytes = TagTypeConverter.intToBytes(tagModification.minDist);
            if (newBytes != null) {
                byteArray[MIN_DIST_INDEX_SOUND] = newBytes[0];
                byteArray[MIN_DIST_INDEX_SOUND + 1] = newBytes[1];
            } else {
                System.out.printf("    Error updating min distance for '%s'%n", tagFile);
                return null;
            }
        }

        // Update Max Distance
        if (byteArray != null && tagModification.maxDist != TagModification.NO_CHANGE) {
            byte[] newBytes = TagTypeConverter.intToBytes(tagModification.maxDist);
            if (newBytes != null) {
                byteArray[MAX_DIST_INDEX_SOUND] = newBytes[0];
                byteArray[MAX_DIST_INDEX_SOUND + 1] = newBytes[1];
            } else {
                System.out.printf("    Error updating max distance for '%s'%n", tagFile);
                return null;
            }
        }

        // Update Classic Only Flag
        if (byteArray != null && tagModification.classicOnly != TagModification.NO_CHANGE) {
            byteArray[CLASSIC_ONLY_INDEX] = TagTypeConverter.boolToByte(tagModification.classicOnly == 1);
        }

        return byteArray;
    }


    /*--- Utility Methods ---*/

    public static void printTagContents(File file) {
        byte[] array = FileManager.readBinaryFileContents(file);
        if (array != null) {
            printByteArray(array);
        }
    }

    private static void printByteArray(byte[] array) {
        String hexValue = TagTypeConverter.bytesToHex(array);
        for (int x = 0; x < hexValue.length(); x += 2) {
            if (x != 0 && x % 32 == 0) System.out.println();
            if (x % 4 == 0) System.out.print(" ");
            System.out.printf("%s", hexValue.substring(x, x + 2));
        }
    }
}
