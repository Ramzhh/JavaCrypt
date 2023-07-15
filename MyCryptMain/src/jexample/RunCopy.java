package jexample;

import java.io.File;
import java.security.Key;
import java.util.Vector;
import javax.crypto.Cipher;

/**
 *
 * @author Liffecs
 */
public class RunCopy extends RunnableBase {

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_KEY_FILE = "usesless_keyfile.txt";

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_IFILE = "copy_inp.dat";

    // Output-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_OFILE = "copy_outp.dat";

    // Groesse des Crypto-Puffers
    public static int RSA_COPY_BUFSIZE = 4096;

    /**
     * Konstruktor
     */
    public RunCopy() {
    }

    /**
     * Diese Methode liefert den Wert der Groesse des Cryptobuffers zurueck
     *
     * @return Puffergroesse
     */
    public int getCryptoBufSize() {
        return RSA_COPY_BUFSIZE;
    }

    /**
     * Encrypt a text using public key.
     *
     * @param text The original unencrypted text
     * @param key The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    @Override
    public byte[] crypt(byte[] text, Key key, Cipher cipher)
            throws Exception {
        return text;
    }

    /**
     * get cipher
     *
     * @return cipher
     */
    @Override
    protected Cipher getCipher(Key key) {
        return null;
    }

    /**
     * run method
     */
    @Override
    public void run(Vector<String> args) {
        int size = 0;

        // 1. Erste Datei
        File file1 = null;

        // 2. Erste Datei
        File file2 = null;

        // 3. Erste Datei
        File file3 = null;

        System.out.println("RunCopy");

        if (args.size() > 0) {
            file1 = new File(args.get(0));
        } else {
            file1 = new File(DEFAULT_KEY_FILE);
        }

        if (args.size() > 1) {
            file2 = new File(args.get(1));
        } else {
            file2 = new File(DEFAULT_IFILE);
        }

        if (args.size() > 2) {
            file3 = new File(args.get(2));
        } else {
            file3 = new File(DEFAULT_OFILE);
        }

        try {

            size = encryptDecryptFile(null, file2, file3);
        } catch (Exception ex) {
            System.err.println("Exception : " + ex.toString()
                    + " : " + ex.getMessage());
        }
        System.out.println("Anzahl der uebertragenen Bytes=" + size);
    }

}
