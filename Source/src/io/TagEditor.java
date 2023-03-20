package io;

import javafx.util.Pair;

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
public class TagEditor {


    /*--- Class Constants ---*/

    // Used to Convert Bytes to Hex
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    // Used to Convert Integers to Tag-Compatible Bytes
    private static List<Pair<Integer, String>> NUMERIC_HEX_VALUES = new ArrayList<>();
    static {
        NUMERIC_HEX_VALUES.add(new Pair<>(-4, "80c0"));
        NUMERIC_HEX_VALUES.add(new Pair<>(-1, "80bf"));
        NUMERIC_HEX_VALUES.add(new Pair<>(0,  "0000"));
        NUMERIC_HEX_VALUES.add(new Pair<>(1,  "803f"));
        NUMERIC_HEX_VALUES.add(new Pair<>(2,  "0040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(3,  "4040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(4,  "8040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(5,  "a040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(6,  "c040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(7,  "e040"));
        NUMERIC_HEX_VALUES.add(new Pair<>(8,  "0041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(9,  "1041"));
        NUMERIC_HEX_VALUES.add(new Pair<>(10, "2041"));
    }


    /*--- Public Methods ---*/

    public static byte[] getTagBytes(int integer) {
        String hexValue = NUMERIC_HEX_VALUES.stream().filter(x -> x.getKey() == integer).findFirst().get().getValue();
        if (hexValue != null) {
            return getBytesFromHexString(hexValue);
        } else {
            return null;
        }
    }

    public static void printTagContents(File file) {
        byte[] array = FileManager.readBinaryFileContents(file);
        if (array != null) {
            String byteValuesInHex = TagEditor.getHexStringFromBytes(array);
            for (int x = 0; x < byteValuesInHex.length(); x += 2) {
                if (x != 0 && x % 32 == 0) System.out.println();
                if (x % 4 == 0) System.out.print(" ");
                System.out.printf("%s", byteValuesInHex.substring(x, x + 2));
            }
        }
    }


    /*--- Translation Methods ---*/

    private static byte[] getBytesFromHexString(String string) {
        byte[] byteArray = new byte[string.length() / 2];
        for (int x = 0; x < string.length(); x += 2) {
            byteArray[x/2] =
                    (byte) ((Character.digit(string.charAt(x), 16) << 4) + Character.digit(string.charAt(x+1), 16));
        }
        return byteArray;
    }

    public static String getHexStringFromBytes(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
