package exceptions;

/**
 *
 * @author João Sousa
 * 
 */
public class NomeInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>NomeInvalidoException</code> without
     * detail message.
     */
    public NomeInvalidoException() {
    }

    /**
     * Constructs an instance of <code>NomeInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NomeInvalidoException(String msg) {
        super(msg);
        // cada vez que ocorre o erro poderia aqui implementar gravar o erro para um ficheiro
        // instrução para guardar em ficheiro
        
    }
}