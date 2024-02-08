package exceptions;

/**
 *
 * @author João Sousa
 * 
 */
public class NumeroCCException extends Exception{

    /**
     * Creates a new instance of <code>NumeroCCException</code> without detail
     * message.
     */
    public NumeroCCException() {
    }

    /**
     * Constructs an instance of <code>NumeroCCException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NumeroCCException(String msg) {
        super(msg);
    }
}
