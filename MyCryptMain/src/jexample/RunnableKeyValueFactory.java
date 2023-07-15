package jexample;

import java.util.Hashtable;

/**
 *
 * @author Liffecs
 */
public class RunnableKeyValueFactory extends RunnableFactory {

    /**
     * Hashtabelle zur Zuordnung von Schluesselworten zu Klassennamen
     */
    Hashtable key2ClassnameTabelle = null;

    /**
     * Konstruktor mit Zuordnungsliste
     *
     * @param args
     */
    RunnableKeyValueFactory(String[][] args) {
        // Erzeugung der Instanz;
        key2ClassnameTabelle = new Hashtable();

        if (args != null) {
            for (int i = 0; i < args.length; ++i) {
                String[] key2Classname = args[i];
                String key = key2Classname[0];
                String val = key2Classname[1];

                // Fuellen der Hashtabelle
                key2ClassnameTabelle.put(key, val);
            }
        }
    }

    /**
     * Holt eine Klasses ueber den Schluessel
     *
     * @param key Zuordnungsschluessel zum Klassenamen
     * @return Instanz der Klasse
     * @throws Exception
     */
    public RunnableInterface getInstanceFromKey(String key)
            throws Exception {
        RunnableInterface runnableInterface = null;
        String className = getClassNameOfKey(key);
        if (className != null) {
            runnableInterface = getInstance(className);
        }
        return runnableInterface;
    }

    /**
     * Holt den fuer den Schluessel hinterlegten Namen der Klasse
     *
     * @param key Schluessel
     * @return Name der Klasse (oder Null)
     */
    public String getClassNameOfKey(String key) {
        String className = null;
        if (containsKey(key)) {
            className = (String) key2ClassnameTabelle.get(key);
        }
        return className;
    }

    /**
     * Hier wird die Existenz eines Schuessels geprueft.
     *
     * @param key Schluessel
     * @return
     */
    public boolean containsKey(String key) {
        return (key == null)
                ? false : key2ClassnameTabelle.containsKey(key);
    }
}
