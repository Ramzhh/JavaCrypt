package javacrypt;

import java.util.Vector;

/**
 * The main class for the MyCrypt program.
 * This class serves as the entry point and controls the execution of various cryptography functions.
 * The program supports key pair generation, encryption, decryption, and file copying.
 *
 * Command-line arguments:
 * -genkeys [priv_keyfile] [pub_keyfile]: Generates a key pair and stores it in the specified files.
 * -encrypt [pub_keyfile] [ifile] [ofile]: Encrypts the input file using the public key and saves the result to the output file.
 * -decrypt [privkeyfile] [ifile] [ofile]: Decrypts the input file using the private key and saves the result to the output file.
 * -copy [dummyword] [ifile] [ofile]: Copies the input file to the output file (without encryption).
 */
public class MyCryptMain {

    // Name of the program as a string
    static final String PROG_NAME = "MyCryptMain";

    static final String SCOPE = "jexample.";

    // Mapping of command and class
    public static final String[][] MY_ARRAY = {
        {"-genkeys", SCOPE + "RunGenKeys"},
        {"-encrypt", SCOPE + "RunEncrypt"},
        {"-decrypt", SCOPE + "RunDecrypt"},
        {"-copy", SCOPE + "RunCopy"}
    };

    /**
     * Displays the usage of the program and exits.
     */
    public static void usage() {
        String msg[] = {
            "Program '" + PROG_NAME + "'",
            "Usage:",
            "\t" + PROG_NAME + " " + MY_ARRAY[0][0] + " [priv_keyfile] [pub_keyfile]",
            "\t" + PROG_NAME + " " + MY_ARRAY[1][0] + " [pub_keyfile] [ifile] [ofile] ",
            "\t" + PROG_NAME + " " + MY_ARRAY[2][0] + " [privkeyfile] [ifile] [ofile] ",
            "\t" + PROG_NAME + " " + MY_ARRAY[3][0] + " [dummyword] [ifile]  [ofile] ",
            ""
        };

        // Output the strings
        for (int i = 0; i < msg.length; ++i) {
            System.err.println(msg[i]);
        }

        // Exit the program with an error code
        System.exit(0);
    }

    /**
     * Entry point of the main program.
     * @param args The command-line arguments.
     * @throws Exception If an error occurs.
     */
    public static void main(String[] args) throws Exception {

        // Factory instance
        RunnableKeyValueFactory runnableKeyValueFactory = new RunnableKeyValueFactory(MY_ARRAY);

        // Get the number of command-line arguments
        int argSize = args.length;

        if (argSize <= 0) {
            usage(); // Static method
        }

        // Get the control argument
        String cmdKey = new String(args[0]);

        // Check if the command exists
        if (!runnableKeyValueFactory.containsKey(cmdKey)) {
            usage();
        }

        // Perform the shift operation
        Vector<String> optArgVector = MyUtils.shiftArgs(args, 5);

        // Instantiation using the factory method:
        RunnableInterface myRun = runnableKeyValueFactory.getInstanceFromKey(cmdKey);

        // Call the run method
        myRun.run(optArgVector);

        System.out.println("End of the program.");
    }
}
