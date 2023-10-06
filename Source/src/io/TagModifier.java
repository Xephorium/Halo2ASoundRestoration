package io;

import io.utility.BinaryTypeConverter;
import tags.RecursiveTagMod;
import tags.TagMod;

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

    // `*.sound` File Attributes
    private static final int GAIN_INDEX_SOUND = 120;     // 4 byte float - Row 8, column 5
    private static final int MIN_DIST_INDEX_SOUND = 104; // 4 byte float - Row 7, column 5
    private static final int MAX_DIST_INDEX_SOUND = 108; // 4 byte float - Row 7, column 7
    private static final int CLASSIC_ONLY_INDEX = 97;    // 1 byte bool  - Row 7, column 1.5

    // `*.sound_looping` File Attributes
    private static final byte[] SOUND_LOOPING_BYTE_PATTERN = {
            BinaryTypeConverter.hexToBytes("01")[0],
            BinaryTypeConverter.hexToBytes("00")[0],
            BinaryTypeConverter.hexToBytes("00")[0],
            BinaryTypeConverter.hexToBytes("00")[0],
            BinaryTypeConverter.hexToBytes("01")[0],
            BinaryTypeConverter.hexToBytes("00")[0],
            BinaryTypeConverter.hexToBytes("00")[0],
            BinaryTypeConverter.hexToBytes("00")[0],
            BinaryTypeConverter.hexToBytes("90")[0],
    };
    private static final int GAIN_INDEX_SOUND_LOOPING = 11; // 11 bytes after above byte sequence

    /* Halo 2 Classic Music Volume
     *
     * For some reason, all of Halo 2 Classic's music tracks
     * seem to play slightly quieter than the actual decibel
     * value they're set to. Or maybe Anniversary's music
     * tracks are boosted from what they should be? Either way,
     * the following constant is used to increase Classic music
     * volume to match the levels I've tuned for Anniversary.
     */
    public static final float CLASSIC_MUSIC_BOOST = 4f;


    /*--- Modify Method ---*/

    public static boolean modifyTag(File tagFile, TagMod tagMod) {

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

        // Identify Modification Options
        boolean shouldReplaceGain = true;
        boolean shouldModifySoundFile = true;
        boolean shouldModifyLoopFile = true;
        if (tagMod.getClass() == RecursiveTagMod.class) {
            shouldReplaceGain = false;
            shouldModifySoundFile = ((RecursiveTagMod) tagMod).shouldModifySoundFiles;
            shouldModifyLoopFile = ((RecursiveTagMod) tagMod).shouldModifyLoopFiles;
        }

        // Update Gain
        if (tagMod.gain != TagMod.NO_CHANGE &&
                ((isLoopFile && shouldModifyLoopFile) || (!isLoopFile && shouldModifySoundFile)) ) {
            float currentGain = shouldReplaceGain ? 0 : getTagGain(byteArray, tagFile);
            int gainIndex = getGainByteIndex(tagFile, byteArray);
            if (gainIndex != 0) {
                updateFloat(byteArray, gainIndex, currentGain + tagMod.gain);
            } else {
                return false;
            }
        }

        // Update Min Distance
        if (tagMod.minDist != TagMod.NO_CHANGE) {
            updateFloat(byteArray, MIN_DIST_INDEX_SOUND, tagMod.minDist);
        }

        // Update Max Distance
        if (tagMod.maxDist != TagMod.NO_CHANGE) {
            updateFloat(byteArray, MAX_DIST_INDEX_SOUND, tagMod.maxDist);
        }

        // Update Classic Only Flag
        if (tagMod.classicOnly != TagMod.NO_CHANGE) {
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

    public static float getTagGain(byte[] array, File tagFile) {
        return readFloat(array, getGainByteIndex(tagFile, array));
    }


    /*--- Private Methods ---*/

    private static void updateFloat(byte[] bytes, int index, float value) {
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

    private static int getGainByteIndex(File tagFile, byte[] bytes) {
        boolean isLoopFile = FileManager.getFileOrDirectoryName(tagFile).contains(".sound_looping");
        if (!isLoopFile) {
            return GAIN_INDEX_SOUND;
        } else {
            int index = getIndexAfterBytePatternInSoundLoopingFile(bytes);
            if (index == 0) {
                System.out.println("    Error finding byte index to modify below tag.");
                return index;
            } else {
                return index + GAIN_INDEX_SOUND_LOOPING;
            }
        }
    }

    /* Okay, this is a fun one.
     *
     * Initially, to edit *.sound_looping files, I just modified a specific
     * two bytes in each file that seemed to correspond to gain. However, as
     * I interacted with more *.sound_looping files, I realized the bytes
     * that need to change aren't always in the same place. Between anniversary
     * and classic files, the position changes. And even among classic files,
     * whether they're for music or other effects changes the position further.
     *
     * Eventually, I found a more reliable method. Some variation of the following
     * byte sequence is consistently 11 bytes before the two gain bytes in every
     * *sound_looping file.
     *
     *   01 00 00 00 01 00 00 00 90
     *               or
     *   01 00 00 00 02 00 00 00 90
     *               or
     *   01 00 00 00 01 00 00 00 0c
     *
     * The below method searches each byte array it's passed for a match of
     * the above byte sequence. If found, the method returns the index immediately
     * after the sequence. Otherwise, it just returns 0. This allows the program
     * to reliably locate the bytes that need to be changed to update a file's gain.
     */
    private static int getIndexAfterBytePatternInSoundLoopingFile(byte[] bytes) {
        for (int x = 0; x < bytes.length - SOUND_LOOPING_BYTE_PATTERN.length; x++) {
            boolean match = true;
            int issues = 0;
            for (int y = 0; y < SOUND_LOOPING_BYTE_PATTERN.length; y++) {
                if (bytes[x + y] != SOUND_LOOPING_BYTE_PATTERN[y]) {
                    if (y == 4 || y == 8) {
                        issues++;

                        if (issues >= 2) {
                            match = false;
                        }
                    } else {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return x + SOUND_LOOPING_BYTE_PATTERN.length;
            }
        }
        return 0;
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
