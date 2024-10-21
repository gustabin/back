/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

/**
 * Clase ImporteInvalidoException.
 *
 * @author mariano.g.pulera
 */
public class ImporteInvalidoException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia un nuevo ImporteInvalidoException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ImporteInvalidoException(String mensaje) {
		super(mensaje);
	}
}
