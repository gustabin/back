/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ErrorClaveOnlineConReintentoException.
 */
public class ErrorClaveOnlineConReintentoException extends Exception {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new error clave online con reintento exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
    public ErrorClaveOnlineConReintentoException(String mensaje) {
        super(mensaje);
    }

}
