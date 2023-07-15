/*
 * File: MyUtils.java
 * Package: javacrypt
 * Author: Liffecs
 * Created: 10.06.2018
 * Modified: 16.07.2023
 * Version: 1.0.0
 */

package javacrypt;

import java.util.List;

/**
 * Utility class for various helper methods.
 *
 * This class provides various utility methods for the MyCrypt program.
 *
 * @author Liffecs
 */
public class MyUtils {

    /**
     * Copies a specified number of bytes from a byte array.
     *
     * @param arr    The byte array to be copied.
     * @param length The number of bytes to be copied.
     * @return A new byte array containing the copied bytes.
     */
    public static byte[] copyBytes(byte[] arr, int length) {
        if (arr.length == length) {
            return arr.clone();
        } else {
            byte[] newArr = new byte[length];
            System.arraycopy(arr, 0, newArr, 0, length);
            return newArr;
        }
    }

    /**
     * Shell-like shift operation.
     * Shifts the arguments to the left by one position and returns the resulting list.
     *
     * @param argsSrc The original list of arguments.
     * @param maxArgs The maximum number of arguments to be considered.
     * @return A new list of shifted arguments.
     */
    public static List<String> shiftArgs(List<String> argsSrc, int maxArgs) {
        return argsSrc.subList(1, Math.min(argsSrc.size(), maxArgs));
    }

    /**
     * Shell-like shift operation.
     * Shifts the arguments to the left by one position and returns the resulting list.
     *
     * @param argsSrc The original array of arguments.
     * @param maxArgs The maximum number of arguments to be considered.
     * @return A new list of shifted arguments.
     */
    public static List<String> shiftArgs(String[] argsSrc, int maxArgs) {
        return List.of(argsSrc).subList(1, Math.min(argsSrc.length, maxArgs));
    }

}
