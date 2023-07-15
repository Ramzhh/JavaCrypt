package jexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 * Basisklasse fuer die anderen Run-Klassen
 *
 * @author Liffecs
 */
public abstract class RunnableBase implements RunnableInterface {

    // fixed crypto algorithmus
    public static final String CRYPTO_ALGORITHMUS = "RSA";

    /**
     * Schluessellaenge
     */
    public static final int KEY_LENGTH = 1024;

    /**
     * internally used method
     *
     * @param text der zu ver- oder entschluesselnde Text
     * @param key
     * @param cipher
     * @return
     * @throws Exception
     */
    abstract public byte[] crypt(byte[] text, Key key, Cipher cipher)
            throws Exception;

    /**
     * RSA kann nicht mit beliebigen Puffer- groessen umgehen. Deshalb muessen
     * die abgeleiteten Klassen diese Werte richtig zurueckliefern:
     *
     * RSA encryption data size limitations are slightly less than the key
     * modulus size, depending on the actual padding scheme used (e.g. with 1024
     * bit (128 byte) RSA key, the size limit is 117 bytes for PKCS#1 v 1.5
     * padding. (http://www.jensign.com/JavaScience/dotnet/RSAEncrypt/)
     *
     * @return Puffergroesse
     */
    abstract public int getCryptoBufSize();

    /**
     * Holt die Cipher des Schlüssels
     *
     * @param key key
     * @return
     */
    abstract protected Cipher getCipher(Key key);

    /**
     * Diese Datei holt das Schluesselobjekt aus der ersten Datei
     *
     * @param keyFile
     * @return
     */
    protected Object getKeyObjectFromFile(File keyFile) {
        Object keyObject = null;
        ObjectInputStream ois = null;
        // Serialisiere den privaten Schluessel
        try {
            // create an ObjectInputStream for the file we created before
            ois = new ObjectInputStream(new FileInputStream(keyFile));

            // read and print what we wrote before
            keyObject = ois.readObject();

            ois.close();
            ois = null;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Exception in getKeyObjectFromFile(): "
                    + ex.getMessage());
            System.exit(2);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException eio) {
                    System.err.println(
                            "ObjectInputStream laesst sich nicht schliessen: "
                            + eio.getMessage());
                }
            }
        }

        return keyObject;
    }

    protected void dumpKey(PublicKey pub) {
        System.out.println("Public  Key: "
                + getHexString(pub.getEncoded()));
    }

    protected void dumpKey(PrivateKey priv) {
        System.out.println("Private Key: "
                + getHexString(priv.getEncoded()));
    }

    protected String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16)
                    .substring(1);
        }
        return result;
    }

    // ============================================================
    /**
     * Encrypt and Decrypt files using 1024 RSA encryption
     *
     * @param key The key. For encryption this is the Private Key and for
     * decryption this is the public key
     * @param srcFile Quelldatei
     * @param dstFile Zieldatei
     * @return Zahl der uebertragenen Bytes
     * @throws Exception
     */
    public int encryptDecryptFile(Key key, File srcFile, File dstFile)
            throws Exception {
        OutputStream outputWriter = null;
        InputStream inputReader = null;
        int size = 0;

        boolean isCopy = (key == null);
        // boolean isEncrypt = (!isCopy && key instanceof PublicKey);

        @SuppressWarnings("UnusedAssignment")
        Cipher cipher = null;
        try {

            cipher = getCipher(key);

            String textLine = null;

            int maxBufSize = getCryptoBufSize();
            byte[] buf = new byte[maxBufSize];

            int bufl;
            /*
            if (!isCopy) {
                // init the Cipher object for Encryption...
                cipher.init((isEncrypt)
                        ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE,
                        key);
            }
             */

            // start FileIO
            outputWriter = new FileOutputStream(dstFile);
            inputReader = new FileInputStream(srcFile);

            // Kopierschleife
            while ((bufl = inputReader.read(buf)) != -1) {
                byte[] encText = null;
                encText = crypt(MyUtils.copyBytes(buf, bufl), key, cipher);
                outputWriter.write(encText);
                if (bufl > 0) {
                    size += bufl;
                }
                // System.out.println("encText = " + new String(encText));
            }

            // if (!isCopy) { outputWriter.write(cipher.doFinal()); }
            outputWriter.flush();

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            throw e;
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            } // end of inner try, catch (Exception)...
        }
        return size;
    }

    // ============================================================
}
