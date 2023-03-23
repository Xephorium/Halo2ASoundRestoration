package io.utility;


import java.nio.charset.StandardCharsets;

/* Halo 2A Sound Restoration                      Chris Cruzen
 * BinaryTypeConverter                              03.23.2023
 *
 * BinaryTypeConverter handles the conversion of Java variable
 * types to and from byte arrays. This allows for direct read
 * and write operations to each binary tag file.
 *
 * Some Tag Format Details:
 *
 *
 */
public class BinaryTypeConverter {


    /*--- Constants ---*/

    // Used to Convert Boolean to Bytes
    private static final String TRUE_HEX_VALUE = "0c";
    private static final String FALSE_HEX_VALUE = "08";

    // Used to Convert Bytes to Hex
    private static final byte[] HEX_BYTE_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);


    /*--- Public Conversion Methods ---*/

    public static byte[] floatToBytes(float value) {
        int intBits =  Float.floatToIntBits(value);
        return new byte[] {
                (byte) (intBits),
                (byte) (intBits >> 8),
                (byte) (intBits >> 16),
                (byte) (intBits >> 24)
        };
    }

    public static float bytesToFloat(byte[] bytes) {
        int intBits = bytes[3] << 24 | (bytes[2] & 0xFF) << 16 | (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);
        return Float.intBitsToFloat(intBits);
    }

    public static byte[] booleanToBytes(boolean value) {
        return hexToBytes(value ? TRUE_HEX_VALUE : FALSE_HEX_VALUE);
    }

    public static boolean bytesToBoolean(byte[] bytes) {
        return bytesToHex(bytes).equals(TRUE_HEX_VALUE);
    }

    public static byte[] hexToBytes(String string) {
        byte[] byteArray = new byte[string.length() / 2];
        for (int x = 0; x < string.length(); x += 2) {
            byteArray[x/2] =
                    (byte) ((Character.digit(string.charAt(x), 16) << 4) + Character.digit(string.charAt(x+1), 16));
        }
        return byteArray;
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
}
