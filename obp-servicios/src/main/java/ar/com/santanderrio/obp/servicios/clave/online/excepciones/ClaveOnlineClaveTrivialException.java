/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ClaveOnlineClaveTrivialException.
 */
/**
 * @author sergio.e.goldentair
 *
 */
public class ClaveOnlineClaveTrivialException extends ErrorClaveOnlineConReintentoException {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -8879410501888513555L;

    /**
	 * Instantiates a new clave online clave trivial exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
    public ClaveOnlineClaveTrivialException(String mensaje) {
        super(mensaje);
    }

}
