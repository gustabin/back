/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ClaveOnlineUsuRepeException.
 */
public class ClaveOnlineUsuRepeException extends ErrorClaveOnlineConReintentoException {

    /**
     * The serial uid.
     */
    private static final long serialVersionUID = 1953374897429569060L;

    /**
	 * Instantiates a new clave online usu repe exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
    public ClaveOnlineUsuRepeException(String mensaje) {
        super(mensaje);
    }

}
