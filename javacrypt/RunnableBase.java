/*
 * File: RunnableBase.java
 * Package: javacrypt
 * Author: Liffecs
 * Created: 10.06.2018
 * Modified: 16.07.2023
 * Version: 1.0.0
 */

package javacrypt;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

/**
 * This abstract class serves as the base class for the other Run classes.
 * It implements the RunnableInterface interface.
 */
public abstract class RunnableBase implements RunnableInterface {

    // Fixed crypto algorithm
    public static final String CRYPTO_ALGORITHMUS = "RSA";

    /**
     * Key length
     */
    public static final int KEY_LENGTH = 1024;

    /**
     * Abstract method to encrypt or decrypt the given text using the provided key and cipher.
     *
     * @param text   The text to encrypt or decrypt.
     * @param key    The key.
     * @param cipher The cipher.
     * @return The encrypted or decrypted text.
     * @throws Exception If an error occurs during encryption or decryption.
     */
    abstract public byte[] crypt(byte[] text, Key key, Cipher cipher) throws Exception;

    /**
     * Returns the buffer size used for RSA encryption or decryption.
     *
     * @return The buffer size.
     */
    abstract public int getCryptoBufSize();

    /**
     * Returns the cipher for the given key.
     *
     * @param key The key.
     * @return The cipher.
     */
    abstract protected Cipher getCipher(Key key);

    /**
     * Reads the key object from the given key file.
     *
     * @param keyFile The key file.
     * @return The key object.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    protected Object getKeyObjectFromFile(File keyFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(keyFile))) {
            return ois.readObject();
        }
    }

    /**
     * Dumps the hexadecimal representation of a public key.
     *
     * @param pub The public key.
     */
    protected void dumpKey(PublicKey pub) {
        System.out.println("Public Key: " + getHexString(pub.getEncoded()));
    }

    /**
     * Dumps the hexadecimal representation of a private key.
     *
     * @param priv The private key.
     */
    protected void dumpKey(PrivateKey priv) {
        System.out.println("Private Key: " + getHexString(priv.getEncoded()));
    }

    /**
     * Converts a byte array to a hexadecimal string representation.
     *
     * @param b The byte array.
     * @return The hexadecimal string representation.
     */
    protected String getHexString(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (byte value : b) {
            result.append(String.format("%02X", value));
        }
        return result.toString();
    }

    /**
     * Encrypts or decrypts the source file using the provided key and writes the result to the destination file.
     *
     * @param key     The key. For encryption, this is the private key, and for decryption, this is the public key.
     * @param srcFile The source file.
     * @param dstFile The destination file.
     * @return The number of bytes transferred.
     * @throws Exception If an error occurs during encryption or decryption.
     */
    public int encryptDecryptFile(Key key, File srcFile, File dstFile) throws Exception {
        int size = 0;

        boolean isCopy = (key == null);

        Cipher cipher = getCipher(key);
        int maxBufSize = getCryptoBufSize();
        byte[] buf = new byte[maxBufSize];

        try (InputStream inputReader = new FileInputStream(srcFile);
             OutputStream outputWriter = new FileOutputStream(dstFile)) {

            int bufl;

            while ((bufl = inputReader.read(buf)) != -1) {
                byte[] encText = crypt(buf, key, cipher);
                outputWriter.write(encText);
                size += bufl;
            }

            outputWriter.flush();
        }

        return size;
    }
}
