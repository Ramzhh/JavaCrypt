
package javacrypt;

/**
 *
 * @author Liffecs
 */
public class RunnableFactory {

    public RunnableInterface getInstance(String className)
            throws Exception {
        RunnableInterface myRunnable = null;

        try {
            // Relection: Instanzierung per Name
            Class cls = Class.forName(className);

            myRunnable
                    = (RunnableInterface) Class.forName(className)
                    .newInstance();
            // returns the name and package of the class
            System.out.println("Class found : " + cls.getName());
            System.out.println("Package     : " + cls.getPackage());
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException: " + ex.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println("InstantiationException: " + ex.getMessage());
        }
        return myRunnable;
    }

}
