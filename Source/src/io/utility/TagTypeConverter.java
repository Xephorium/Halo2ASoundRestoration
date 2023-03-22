package io.utility;


import javafx.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TagTypeConverter {


    /*--- Constants ---*/

    // Used to Convert Bytes to Hex
    private static final byte[] HEX_BYTE_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    // Used to Convert Java Types to Tag Bytes
    private static final List<Pair<Integer, String>> NUMERIC_HEX_VALUES = new ArrayList<>();
    private static final String TRUE_HEX_VALUE = "0c";
    private static final String FALSE_HEX_VALUE = "08";

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


    /*--- Public Conversion Methods ---*/

    public static byte[] intToBytes(int integer) {
        String hexValue = intToHex(integer);
        if (hexValue != null) {
            return hexToBytes(hexValue);
        }
        System.out.printf("    Error generating tag byte value for '%d'%n", integer);
        return null;
    }

    public static byte booleanToByte(boolean value) {
        String hexValue = FALSE_HEX_VALUE;
        if (value) hexValue = TRUE_HEX_VALUE;
        return hexToBytes(hexValue)[0];
    }

    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_BYTE_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_BYTE_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }


    /*--- Private Utility Methods ---*/

    private static String intToHex(int integer) {
        if (NUMERIC_HEX_VALUES.stream().anyMatch(x -> x.getKey() == integer)) {
            return NUMERIC_HEX_VALUES.stream().filter(x -> x.getKey() == integer).findFirst().get().getValue();
        }
        return null;
    }

    private static byte[] hexToBytes(String string) {
        byte[] byteArray = new byte[string.length() / 2];
        for (int x = 0; x < string.length(); x += 2) {
            byteArray[x/2] =
                    (byte) ((Character.digit(string.charAt(x), 16) << 4) + Character.digit(string.charAt(x+1), 16));
        }
        return byteArray;
    }
}
