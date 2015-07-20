package com.dozorengine.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IGOR-K
 */
public class EnumUtils {

    private static Logger log = Logger.getLogger(EnumUtils.class.getName());

    public static <T extends Enum<T>> T getEnum(final Class<T> enumClass, final String value) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (RuntimeException ex) {
            log.log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
