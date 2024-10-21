/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl;

/**
 * The Class EdadIncorrectaException.
 *
 * @author florencia.n.martinez
 */
public class EdadIncorrectaException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3465386818847325434L;

    /**
     * Instantiates a new edad incorrecta exception.
     */
    public EdadIncorrectaException() {
        super();
    }

    /**
     * Instantiates a new edad incorrecta exception.
     *
     * @param cause
     *            the cause
     */
    public EdadIncorrectaException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new edad incorrecta exception.
     *
     * @param msg
     *            the msg
     */
    public EdadIncorrectaException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new edad incorrecta exception.
     *
     * @param cause
     *            the cause
     * @param msg
     *            the msg
     */
    public EdadIncorrectaException(Throwable cause, String msg) {
        super(msg, cause);
    }
}
