/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ClaveOnlineUsuTrivialException.
 */
public class ClaveOnlineUsuTrivialException extends ErrorClaveOnlineConReintentoException {

    /**
     * The serial uid.
     */
    private static final long serialVersionUID = -7155364020622415552L;

    /**
	 * Instantiates a new clave online usu trivial exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
    public ClaveOnlineUsuTrivialException(String mensaje) {
        super(mensaje);
    }

}
