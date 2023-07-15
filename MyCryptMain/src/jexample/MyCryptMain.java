package jexample;

import java.util.Vector;

/**
 * Main-Klasse
 *
 * @author Liffecs
 */
public class MyCryptMain {

    // namer des Programms als string
    static final String PROG_NAME = "MyCryptMain";

    static final String SCOPE = "jexample.";

    // Zuordnungsliste von Kommando und Klasse
    public static final String[][] MY_ARRAY
            = {
            {"-genkeys", SCOPE + "RunGenKeys"},
            {"-encrypt", SCOPE + "RunEncrypt"},
            {"-decrypt", SCOPE + "RunDecrypt"},
            {"-copy", SCOPE + "RunCopy"}
    };

    // Anzeige des Hinweistextes und Abbruch
    public static void usage() {
        String msg[] = {
                "Program '" + PROG_NAME + "'",
                "usage:",
                "\t" + PROG_NAME + " " + MY_ARRAY[0][0]
                        + " [priv_keyfile] [pub_keyfile]",
                "\t" + PROG_NAME + " " + MY_ARRAY[1][0]
                        + " [pub_keyfile] [ifile] [ofile] ",
                "\t" + PROG_NAME + " " + MY_ARRAY[2][0]
                        + " [privkeyfile] [ifile] [ofile] ",
                "\t" + PROG_NAME + " " + MY_ARRAY[3][0]
                        + " [dummyword] [ifile]  [ofile] ",
                ""
        };

        // Ausgabe der Strings
        for (int i = 0; i < msg.length; ++i) {
            System.err.println(msg[i]);
        }

        // Beendigung des Programms mit Fehlercode
        System.exit(0);
    }

    // ==========================================================
    // Einstieg ins Hauptprogramm
    public static void main(String[] args) throws Exception {

        // Fabrik-Instanz
        RunnableKeyValueFactory runnableKeyValueFactory
                = new RunnableKeyValueFactory(MY_ARRAY);

        // Ermittlung der Anzahl der Kommandozeile-Argumente
        int argSize = args.length;

        if (argSize <= 0) {
            usage(); // static
        }

        // Holen des Steuerarguments
        String cmdKey = new String(args[0]);

        // Ueberpruefung des Existenz des Kommandos
        if (!runnableKeyValueFactory.containsKey(cmdKey)) {
            usage();
        }

        // Ausfuehren der Shift-Operation
        Vector<String> optArgVector = MyUtils.shiftArgs(args, 5);

        // Instanzierung per Fabrikmethode:
        RunnableInterface myRun
                = runnableKeyValueFactory
                .getInstanceFromKey(cmdKey);

        // Aufruf der Run-Methode
        myRun.run(optArgVector);

        System.out.println("Ende des Programms.");
    }

}
