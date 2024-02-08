/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/**
 *
 * @author João Sousa
 */
public class VeiculoNaoExiste extends Exception{

    /**
     * Creates a new instance of <code>VeiculoNaoExiste</code> without detail
     * message.
     */
    public VeiculoNaoExiste() {
    }

    /**
     * Constructs an instance of <code>VeiculoNaoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public VeiculoNaoExiste(String msg) {
        super(msg);
    }
}
