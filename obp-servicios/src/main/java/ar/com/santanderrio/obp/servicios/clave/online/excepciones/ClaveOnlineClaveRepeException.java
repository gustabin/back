/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ClaveOnlineClaveRepeException.
 */
public class ClaveOnlineClaveRepeException extends ErrorClaveOnlineConReintentoException {

    /**
     * The serial uid.
     */
    private static final long serialVersionUID = -8061203757159740202L;

    /**
	 * Instantiates a new clave online clave repe exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
    public ClaveOnlineClaveRepeException(String mensaje) {
        super(mensaje);
    }

}
