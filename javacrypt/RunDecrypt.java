package javacrypt;

import java.util.Vector;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA-Entschluesselung
 *
 * @author Liffecs
 */
public class RunDecrypt extends RunnableBase {

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_KEY_FILE = "priv.key";

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_IFILE = "decrypt_inp.dat";

    // Output-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_OFILE = "decrypt_outp.dat";

    // Groesse des Crypto-Puffers
    public static final int RSA_DECRYPT_BUFSIZE = 128;

    /**
     * Constructor
     */
    public RunDecrypt() {
    }

    /**
     * Diese Methode liefert den Wert der Groesse des Cryptobuffers zurueck
     *
     * @return Puffergroesse
     */
    public int getCryptoBufSize() {
        return RSA_DECRYPT_BUFSIZE;
    }

    /**
     * Entschluesselung mit dem privaten Schluessel
     *
     * @param text verschluesselte Bytes
     * @param key  The private key
     * @return entschluesselte Bytes
     * @throws java.lang.Exception
     */
    public byte[] crypt(byte[] text, Key key, Cipher cipher)
            throws Exception {
        byte[] dectyptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, (PrivateKey) key);
            dectyptedText = cipher.doFinal(text);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            ;
            throw e;
        }
        return dectyptedText;

    }

    /**
     * get cipher
     *
     * @return cipher
     */
    @Override
    protected Cipher getCipher(Key key) {
        Cipher cipher = null;
        try {
            PrivateKey privKey = (PrivateKey) key;

            // DEBUG: super.dumpKey(privKey);
            // -------------------------------------------------
            cipher = Cipher.getInstance(CRYPTO_ALGORITHMUS);
            cipher.init(Cipher.DECRYPT_MODE, privKey);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("NoSuchAlgorithmException: " + ex.getMessage());
            System.exit(8);
        } catch (NoSuchPaddingException ex) {
            System.err.println("NoSuchPaddingException: " + ex.getMessage());
            System.exit(8);
        } catch (InvalidKeyException ex) {
            System.err.println("InvalidKeyException: " + ex.getMessage());
            System.exit(8);
        }
        return cipher;
    }

    /**
     * run method
     */
    @Override
    public void run(Vector<String> args) {
        int size = 0;
        System.out.println("RunDecrypt");

        // 1. Erste Datei
        File file1 = null;

        // 2. Erste Datei
        File file2 = null;

        // 3. Erste Datei
        File file3 = null;

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
            PrivateKey privKey = (PrivateKey) getKeyObjectFromFile(file1);
            size = encryptDecryptFile(privKey, file2, file3);
        } catch (Exception ex) {
            System.err.println("EXCEPTION: run : " + ex.getMessage());
        }

        System.out.println("Anzahl der uebertragenen Bytes=" + size);
    }

}
