/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.exceptions;

/**
 * Esta excepci�n se produce con alg�n formato inv�lido.
 */
public class FormatException extends RuntimeException {

    /**
     * Instantiates a new format exception.
     *
     * @param msg
     *            the msg
     */
    public FormatException(String msg) {
        super(msg);
    }
}