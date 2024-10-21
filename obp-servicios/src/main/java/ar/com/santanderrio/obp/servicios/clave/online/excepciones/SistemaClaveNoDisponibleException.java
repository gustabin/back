package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

public class SistemaClaveNoDisponibleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public SistemaClaveNoDisponibleException(String mensaje) {
		super(mensaje);
	}
}
