package exceptions;

/**
 *
 * @author João Sousa
 * 
 */
public class IdadeException extends Exception{

    /**
     * Creates a new instance of <code>IdadeException</code> without detail
     * message.
     */
    public IdadeException() {
    }

    /**
     * Constructs an instance of <code>IdadeException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public IdadeException(String msg) {
        super(msg);
    }
}
