/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.dao;

/**
 * The Interface SuscripcionesParametrosDAO.
 */
public interface SuscripcionesParametrosDAO {

	/**
	 * Inits the.
	 */
	void init();

	/**
	 * Obtener frecuencia.
	 *
	 * @param codigoFrecuencia
	 *            the codigo frecuencia
	 * @return the string
	 */
	String obtenerFrecuencia(String codigoFrecuencia);

	/**
	 * Obtener clave.
	 *
	 * @param frecuencia
	 *            the frecuencia
	 * @return the string
	 */
	String obtenerClave(String frecuencia);

	/**
	 * Obtener dia aviso previo.
	 *
	 * @param codigoDAP
	 *            the codigo DAP
	 * @return the string
	 */
	String obtenerDiaAvisoPrevio(String codigoDAP);

	/**
	 * Obtener codigo dia aviso previo.
	 *
	 * @param codigoDAP
	 *            the codigo DAP
	 * @return the string
	 */
	public String obtenerCodigoDiaAvisoPrevio(String codigoDAP);

}
