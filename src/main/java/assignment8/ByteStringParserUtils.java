package assignment8;

/**
 * Convert byte[] to String and String to byte[]
 */
public class ByteStringParserUtils {

    /**
     * String to byte byte [ ].
     *
     * @param data the data
     * @return the byte [ ]
     */
    public static byte[] stringToByte(final String data) {
        return data.getBytes();
    }

    /**
     * Byte to string string.
     *
     * @param data the data
     * @return the string
     */
    public static String byteToString(final byte[] data) {
        return new String(data);
    }
}
