package com.dozorengine.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Утилиты для конвертации int в byte[] и обратно, преобразование типов идет без
 * потерь
 *
 * @author IGOR-K
 */
public class ByteConverter {

    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >> 16 & 0xff),
                (byte) (value >> 8 & 0xff),
                (byte) (value & 0xff)};
    }

    public static final int byteArrayToInt(byte[] b, int start) {
        int dt = 0;
        if ((b[start] & 0x80) != 0) {
            dt = Integer.MAX_VALUE;
        }
        for (int i = 0; i < 4; i++) {
            dt = (dt << 8) + (b[start++] & 255);
        }
        return dt;
    }

    public static final byte[] intToByteArray(int n, int byteCount) {
        byte[] res = new byte[byteCount];
        for (int i = 0; i < byteCount; i++) {
            res[byteCount - i - 1] = (byte) ((n >> i * 8) & 255);
        }
        return res;
    }

    public static final int byteArrayToInt(byte[] b, int start, int length) {
        int dt = 0;
        if ((b[start] & 0x80) != 0) {
            dt = Integer.MAX_VALUE;
        }
        for (int i = 0; i < length; i++) {
            dt = (dt << 8) + (b[start++] & 255);
        }
        return dt;
    }

    public static String byteArrayToString(byte[] b) {
        try {
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ByteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String();
    }

    public static byte[] stringToByteArray(String s) {
        return s.getBytes(Charset.forName("UTF-8"));
    }
}
