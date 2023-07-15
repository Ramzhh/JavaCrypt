package jexample;

import java.util.Vector;

/**
 * Runnable Interface
 *
 * @args Strings of file names
 * @author Liffecs
 */
public interface RunnableInterface {

    /**
     * Start der Verarbeitung
     *
     * @param args Dateinames als Strings
     */
    public void run(Vector<String> args);
}
