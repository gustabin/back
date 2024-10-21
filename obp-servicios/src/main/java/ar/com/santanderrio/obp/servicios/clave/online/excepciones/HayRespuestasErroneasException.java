package ar.com.santanderrio.obp.servicios.clave.online.excepciones;
/**
 * 
 * @author Itr
 *
 */
public class HayRespuestasErroneasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instancia una nueva HayRespuestasErroneasException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public HayRespuestasErroneasException(String mensaje) {
		super(mensaje);
	}

}
