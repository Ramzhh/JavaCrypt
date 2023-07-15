package jexample;

import java.util.Vector;

/**
 * Klasse fuer verschiedene Hilfsmethoden
 *
 * @author Liffecs
 */
public class MyUtils {

    /**
     * Intern verwendete Hilfsmethode
     *
     * @param arr    zu kopierende Bytes
     * @param length Anzahl der zu kopierenden Bytes
     * @return kopierte Bytes
     */
    public static byte[] copyBytes(byte[] arr, int length) {
        byte[] newArr = null;
        if (arr.length == length) {
            newArr = arr;
        } else {
            newArr = new byte[length];
            for (int i = 0; i < length; i++) {
                newArr[i] = (byte) arr[i];
            }
        }
        return newArr;
    }

    /**
     * Shift-Operation aehnlich wie bei der Shell
     *
     * @param argsSrc bisherige Argumentenliste
     * @param maxArgs Begrenzung auf die maximale Zahl von Argumenten
     * @return neue Argumentliste
     */
    public static Vector<String> shiftArgs(Vector<String> argsSrc, int maxArgs) {
        Vector<String> argsDst = new Vector<>();
        // Holen der uebrigen Argumente
        for (int i = 1; (i < argsSrc.size()) && (i < maxArgs); ++i) {
            argsDst.add(argsSrc.get(i));
        }
        return argsDst;
    }

    /**
     * Shift-Operation aehnlich wie bei der Shell
     *
     * @param argsSrc bisherige Argumentenliste
     * @param maxArgs Begrenzung auf die maximale Zahl von Argumenten
     * @return neue Argumentliste
     */
    public static Vector<String> shiftArgs(String[] argsSrc, int maxArgs) {
        Vector<String> argsDst = new Vector<>();
        // Holen der uebrigen Argumente
        for (int i = 1; (i < argsSrc.length) && (i < maxArgs); ++i) {
            argsDst.add(argsSrc[i]);
        }
        return argsDst;
    }

}
