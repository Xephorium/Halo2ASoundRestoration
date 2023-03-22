package io;

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
 * of sound tags as programatically. However, it's a very fragile
 * approach and may need to be adjusted with future MCC updates.
 */
public class TagModifier {


    /*--- Constants ---*/

    // Byte Locations Within Byte Array
    private static final int GAIN_INDEX_SOUND = 122; // 2 bytes - Binary Row 8, column 6
    private static final int GAIN_INDEX_SOUND_LOOPING = 182; // 2 bytes - Row 12, column 4
    private static final int MIN_DIST_INDEX_SOUND = 106; // 2 bytes - Row 7, column 6
    private static final int MAX_DIST_INDEX_SOUND = 110; // 2 bytes - Row 7, column 8
    private static final int CLASSIC_ONLY_INDEX = 97; // 1 byte - Row 7, column 1.5

    // Used to Convert Bytes to Hex
    private static final byte[] HEX_BYTE_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    // Used to Convert Java Types to Tag Bytes
    private static List<Pair<Integer, String>> NUMERIC_HEX_VALUES = new ArrayList<>();
    private static String TRUE_HEX_VALUE = "0c";
    private static String FALSE_HEX_VALUE = "08";


    static {
        NUMERIC_HEX_VALUES.add(new Pair<>(-30, "40c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-25, "c8c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-20, "a0c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-19, "98c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-18, "90c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-17, "88c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-16, "80c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-15, "70c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-14, "60c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-13, "50c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-12, "40c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-11, "30c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-10, "20c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-9,  "10c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-8,  "00c1"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-7,  "e0c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-6,  "c0c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-5,  "a0c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-4,  "80c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-3,  "40c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-2,  "00c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-1,  "80bf"));
        NUMERIC_HEX_VALUES.add(new Pair<>(0,   "0000"));
        NUMERIC_HEX_VALUES.add(new Pair<>(1,   "803f"));
        NUMERIC_HEX_VALUES.add(new Pair<>(2,   "0040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(3,   "4040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(4,   "8040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(5,   "a040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(6,   "c040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(7,   "e040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(8,   "0041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(9,   "1041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(10,  "2041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(11,  "3041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(12,  "4041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(13,  "5041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(14,  "6041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(15,  "7041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(16,  "8041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(17,  "8841"));
        NUMERIC_HEX_VALUES.add(new Pair<>(18,  "9041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(19,  "9841"));
        NUMERIC_HEX_VALUES.add(new Pair<>(20,  "a041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(25,  "c841"));
        NUMERIC_HEX_VALUES.add(new Pair<>(75,  "9642"));
        NUMERIC_HEX_VALUES.add(new Pair<>(100, "c842"));
        NUMERIC_HEX_VALUES.add(new Pair<>(150, "1643"));
        NUMERIC_HEX_VALUES.add(new Pair<>(300, "9643"));
    }


    /*--- Public Methods ---*/

    public static byte[] modifyTag(File tagFile, TagModification tagModification, byte[] byteArray) {

        // Identify File Type
        boolean isLoopFile = FileManager.getFileOrDirectoryName(tagFile).contains(".sound_looping");

        // Update Gain
        if (tagModification.gain != TagModification.NO_CHANGE) {
            int gainIndex = GAIN_INDEX_SOUND;
            if (isLoopFile) gainIndex = GAIN_INDEX_SOUND_LOOPING;
            byte[] newBytes = getTagBytesFromInteger(tagModification.gain);
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
            byte[] newBytes = getTagBytesFromInteger(tagModification.minDist);
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
            byte[] newBytes = getTagBytesFromInteger(tagModification.maxDist);
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
            String newFlagValue = FALSE_HEX_VALUE;
            if (tagModification.classicOnly == 1) newFlagValue = TRUE_HEX_VALUE;
            byte newByte = getBytesFromHexString(newFlagValue)[0];
            byteArray[CLASSIC_ONLY_INDEX] = newByte;
        }

        return byteArray;
    }

    public static void printTagContents(File file) {
        byte[] array = FileManager.readBinaryFileContents(file);
        if (array != null) {
            printByteArray(array);
        }
    }


    /*--- Byte Utility Methods ---*/

    private static void printByteArray(byte[] array) {
        String byteValuesInHex = TagModifier.getHexStringFromBytes(array);
        for (int x = 0; x < byteValuesInHex.length(); x += 2) {
            if (x != 0 && x % 32 == 0) System.out.println();
            if (x % 4 == 0) System.out.print(" ");
            System.out.printf("%s", byteValuesInHex.substring(x, x + 2));
        }
    }

    private static byte[] getTagBytesFromInteger(int integer) {
        String hexValue = getTagHexStringFromInteger(integer);
        if (hexValue != null) {
            return getBytesFromHexString(hexValue);
        }
        System.out.printf("    Error finding tag byte value for '%d'%n", integer);
        return null;
    }

    private static byte[] getBytesFromHexString(String string) {
        byte[] byteArray = new byte[string.length() / 2];
        for (int x = 0; x < string.length(); x += 2) {
            byteArray[x/2] =
                    (byte) ((Character.digit(string.charAt(x), 16) << 4) + Character.digit(string.charAt(x+1), 16));
        }
        return byteArray;
    }

    private static String getTagHexStringFromInteger(int integer) {
        if (NUMERIC_HEX_VALUES.stream().anyMatch(x -> x.getKey() == integer)) {
            return NUMERIC_HEX_VALUES.stream().filter(x -> x.getKey() == integer).findFirst().get().getValue();
        }
        return null;
    }

    public static String getHexStringFromBytes(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_BYTE_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_BYTE_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
