package javacrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Vector;
import javax.crypto.Cipher;

/**
 *
 * @author Liffecs
 */
public class RunGenKeys extends RunnableBase {

    // Input-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_FILE_PRIV = "priv.key";

    // Output-Dateiname, wenn er nicht per Kommandozeile angegeben wird
    public static String DEFAULT_FILE_PUB = "pub.key";

    @Override
    public void run(Vector<String> args) {

        // 1. Erste Datei
        File file1 = null;

        // 2. Erste Datei
        File file2 = null;

        // 3. Erste Datei
        File file3 = null;

        // Meldung
        System.out.println("RunGenkeys");

        if (args.size() > 0) {
            file1 = new File(args.get(0));
        } else {
            file1 = new File(DEFAULT_FILE_PRIV);
        }

        if (args.size() > 1) {
            file2 = new File(args.get(1));
        } else {
            file2 = new File(DEFAULT_FILE_PUB);
        }

        if (args.size() > 2) {
            file3 = new File(args.get(2));
        } else {
            file3 = new File(DEFAULT_FILE_PUB);
        }

        KeyPair myKeyPair;

        try {
            // Holen der Instanz des Schluesselpaar-Generators:
            KeyPairGenerator keygen
                    = KeyPairGenerator
                    .getInstance(CRYPTO_ALGORITHMUS);
            // Initialisierung des Schluesselpaar-Generators:
            keygen.initialize(KEY_LENGTH);

            // Festlegung des Schluesselpaares:
            myKeyPair = keygen.genKeyPair();

            PrivateKey privKey = myKeyPair.getPrivate();
            PublicKey pubKey = myKeyPair.getPublic();

            // =============================================
            writeToFile(privKey, file1);
            writeToFile(pubKey, file2);
            // ==============================================

            System.out.println("Private      Schluesseldatei : "
                    + file1.getCanonicalPath());
            System.out.println("Oeffentliche Schluesseldatei : "
                    + file2.getCanonicalPath());

        } catch (NoSuchAlgorithmException eNsM) {
            System.err.println("Der Algorithmus '"
                    + CRYPTO_ALGORITHMUS
                    + "' ist nicht implementiert!"
            );
            System.exit(2);
        } catch (IOException ex) {
            System.err.println("IOException : " + ex.getMessage());
        }

    }

    /**
     * Serialisierung des privaten Schluessel-Objekts
     *
     * @param pk privates Schluesselobjekt
     */
    private void writeToFile(PrivateKey pk, File privateKeyFile) {
        FileOutputStream out = null;
        ObjectOutputStream oout = null;
        // Serialisiere den privaten Schluessel
        try {

            // super.dumpKey(pk);
            out = new FileOutputStream(privateKeyFile);
            // write something in the file
            oout = new ObjectOutputStream(out);
            // write something in the file

            oout.writeObject(pk);
            // close the stream

            out.close();
            oout.close();

            out = null;
            oout = null;
        } catch (Exception ex) {
            System.out.println("Exception(writeToFile(PrivateKey)): "
                    + ex.getMessage());
            System.exit(2);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (oout != null) {
                    oout.close();
                }
            } catch (IOException ex2) {
                System.err.println("EX2: Exception(writeToFile(PrivateKey)): "
                        + ex2.getMessage());
                System.exit(2);

            }
        }
    }

    /**
     * Serialsierung der oeffentlichen Schluessel-Objekts
     *
     * @param pk oeffentliches schuessel-Objekt
     */
    private void writeToFile(PublicKey pk, File publicKeyFile) {
        FileOutputStream out = null;
        ObjectOutputStream oout = null;
        try {

            // super.dumpKey(pk);
            out = new FileOutputStream(publicKeyFile);
            oout = new ObjectOutputStream(out);
            // write something in the file
            oout.writeObject(pk);
            // close the stream

            out.close();
            oout.close();

            out = null;
            oout = null;
        } catch (Exception ex) {
            System.out.println("Exception(writeToFile(PublicKey)): "
                    + ex.getMessage());
            System.exit(2);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (oout != null) {
                    oout.close();
                }
            } catch (IOException ex2) {
                System.err.println("EX2: Exception(writeToFile(PublicKey)): "
                        + ex2.getMessage());
                System.exit(2);

            }
        }
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

    @Override
    public byte[] crypt(byte[] text, Key key, Cipher cipher) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCryptoBufSize() {
        return 100;
    }

}
