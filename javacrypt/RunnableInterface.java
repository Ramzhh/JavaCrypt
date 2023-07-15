/*
 * File: RunnableInterface.java
 * Package: javacrypt
 * Author: Liffecs
 * Created: 10.06.2018
 * Modified: 16.07.2023
 * Version: 1.0.0
 */

package javacrypt;

import java.util.List;

/**
 * Runnable Interface
 *
 * This interface defines the contract for a runnable entity in the MyCrypt program.
 * Any class that implements this interface must provide an implementation for the run() method.
 * The run() method takes a list of file names as strings and performs the processing logic accordingly.
 *
 * @args Strings of file names
 */
public interface RunnableInterface {

    /**
     * Start der Verarbeitung
     *
     * This method starts the processing logic for the implementing class.
     *
     * @param args Strings of file names
     */
    void run(List<String> args);
}
