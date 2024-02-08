package exceptions;

/**
 *
 * @author João Sousa
 * 
 */
public class LimiteDeVeiculosException extends Exception{

    /**
     * Creates a new instance of <code>LimiteDeVeiculosException</code> without
     * detail message.
     */
    public LimiteDeVeiculosException() {
    }

    /**
     * Constructs an instance of <code>LimiteDeVeiculosException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LimiteDeVeiculosException(String msg) {
        super(msg);
    }
}
