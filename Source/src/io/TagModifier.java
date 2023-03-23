package io;

import io.utility.BinaryTypeConverter;
import tags.TagModification;

import java.io.File;

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
    private static final int GAIN_INDEX_SOUND = 120;         // 4 byte float - Row 8, column 5
    private static final int GAIN_INDEX_SOUND_LOOPING = 180; // 4 byte float - Row 12, column 3

    // Exist only in `*.sound` files
    private static final int MIN_DIST_INDEX_SOUND = 104;     // 4 byte float - Row 7, column 5
    private static final int MAX_DIST_INDEX_SOUND = 108;     // 4 byte float - Row 7, column 7
    private static final int CLASSIC_ONLY_INDEX = 97;        // 1 byte bool  - Row 7, column 1.5


    /*--- Modify Method ---*/

    public static boolean modifyTag(File tagFile, TagModification tagMod) {

        // Read File Contents
        byte[] byteArray;
        if (FileManager.isValidFile(tagFile)) {
            byteArray = FileManager.readBinaryFileContents(tagFile);
            if (byteArray == null) return false;
        } else {
            return false;
        }

        // Identify File Type
        boolean isLoopFile = FileManager.getFileOrDirectoryName(tagFile).contains(".sound_looping");

        // Update Gain
        if (tagMod.gain != TagModification.NO_CHANGE) {
            updateFloat(byteArray, isLoopFile ? GAIN_INDEX_SOUND : GAIN_INDEX_SOUND_LOOPING, tagMod.gain);
        }

        // Update Min Distance
        if (tagMod.minDist != TagModification.NO_CHANGE) {
            updateFloat(byteArray, MIN_DIST_INDEX_SOUND, tagMod.minDist);
        }

        // Update Max Distance
        if (tagMod.maxDist != TagModification.NO_CHANGE) {
            updateFloat(byteArray, MAX_DIST_INDEX_SOUND, tagMod.maxDist);
        }

        // Update Classic Only Flag
        if (tagMod.classicOnly != TagModification.NO_CHANGE) {
            updateBoolean(byteArray, CLASSIC_ONLY_INDEX, tagMod.classicOnly == 1);
        }

        // Write Changes to File
        return FileManager.deleteFile(tagFile) && FileManager.writeToBinaryFile(tagFile, byteArray);

    }


    /*--- Public Accessor Methods ---*/

    public static void printTagContents(File file) {
        byte[] array = FileManager.readBinaryFileContents(file);
        if (array != null) {
            printByteArray(array);
        }
    }

    public static Float getTagGain(File file) {
        byte[] array = FileManager.readBinaryFileContents(file);
        if (array != null) {
            boolean isLoopFile = FileManager.getFileOrDirectoryName(file).contains(".sound_looping");
            return readFloat(array, isLoopFile ? GAIN_INDEX_SOUND_LOOPING : GAIN_INDEX_SOUND);
        } else {
            return null;
        }
    }


    /*--- Private Methods ---*/

    private static void updateFloat(byte[] bytes, int index, int value) {
        byte[] newBytes = BinaryTypeConverter.floatToBytes(value);

        // Replace 4 Bytes Containing Float
        bytes[index] = newBytes[0];
        bytes[index + 1] = newBytes[1];
        bytes[index + 2] = newBytes[2];
        bytes[index + 3] = newBytes[3];
    }

    private static void updateBoolean(byte[] bytes, int index, boolean value) {
        byte[] newBytes = BinaryTypeConverter.booleanToBytes(value);

        // Replace 1 Byte Containing Boolean
        bytes[index] = newBytes[0];
    }

    private static float readFloat(byte[] bytes, int index) {
        byte[] newArray = { bytes[index], bytes[index + 1], bytes[index + 2], bytes[index + 3] };
        return BinaryTypeConverter.bytesToFloat(newArray);
    }

    private static void printByteArray(byte[] array) {
        String hexValue = BinaryTypeConverter.bytesToHex(array);
        for (int x = 0; x < hexValue.length(); x += 2) {
            if (x != 0 && x % 32 == 0) System.out.println();
            if (x % 4 == 0) System.out.print(" ");
            System.out.printf("%s", hexValue.substring(x, x + 2));
        }
    }
}
