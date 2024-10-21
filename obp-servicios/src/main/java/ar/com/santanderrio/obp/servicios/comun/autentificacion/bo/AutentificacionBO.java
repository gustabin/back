/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.bo;

/**
 * The Interface AutenticacionBO.
 *
 * @author sabrina.cis
 */
public interface AutentificacionBO {

	/**
	 * Grabar estadistica.
	 *
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param codigoError
	 *            the codigo error
	 */
	void grabarEstadistica(String codigoEstadistica, String codigoError);

}
