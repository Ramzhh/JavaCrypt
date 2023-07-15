package javacrypt;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA Verschluesselung
 *
 * @author Liffecs
 */
public class RunEncrypt extends RunnableBase {

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static final String DEFAULT_KEY_FILE = "pub.key";

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static final String DEFAULT_IFILE = "encr_inp.dat";

    // Output-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static final String DEFAULT_OFILE = "encr_outp.dat";

    // Groesse des Crypto-Puffers
    public static final int RSA_ENCRYPT_BUFSIZE = 100;

    /**
     * public constructor
     */
    public RunEncrypt() {
    }

    /**
     * Diese Methode liefert den Wert der Groesse des Cryptobuffers zurueck
     *
     * @return Puffergroesse
     */
    public int getCryptoBufSize() {
        return RSA_ENCRYPT_BUFSIZE;
    }

    /**
     * Encrypt a text using public key.
     *
     * @param text The original unencrypted text
     * @param key The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    public byte[] crypt(byte[] text, Key key, Cipher cipher)
            throws Exception {
        byte[] cipherText = null;
        try {
            // Cipher cipher = Cipher.getInstance(CRYPTO_ALGORITHMUS);

            // encrypt the plaintext using the public key
            cipher.init(Cipher.ENCRYPT_MODE, (PublicKey) key);
            cipherText = cipher.doFinal(text);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw e;
        }
        return cipherText;
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
            PublicKey pubKey = (PublicKey) key;

            // super.dumpKey(pubKey);
            // -------------------------------------------------
            cipher = Cipher.getInstance(CRYPTO_ALGORITHMUS);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
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
        // TODO
        System.out.println("RunEncrypt");
        int size = 0;

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

        @SuppressWarnings("UnusedAssignment")
        PublicKey pubKey = null;
        try {
            pubKey = (PublicKey) getKeyObjectFromFile(file1);
            size = encryptDecryptFile(pubKey, file2, file3);
        } catch (Exception ex) {
            System.err.println("EXCEPTION: run : " + ex.getMessage());
        }

        System.out.println("Anzahl der uebertragenen Bytes=" + size);
    }

}
