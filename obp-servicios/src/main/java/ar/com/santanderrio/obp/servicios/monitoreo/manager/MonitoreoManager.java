/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.manager;

/**
 * The Interface MonitoreoManager.
 *
 * @author sergio.e.goldentair
 */
public interface MonitoreoManager {

    /**
	 * Indicar si iatx esta disponible para consultar por la app.
	 *
	 * @return true, if successful
	 */
    boolean validarIatx();

    /**
	 * Indicar si la base esta disponible para operar por la app.
	 *
	 * @return true, if successful
	 */
    boolean validarBase();

    /**
	 * Armar html con el estado del WAS, IATX y la BD.
	 *
	 * @return the string
	 */
    String obtenerHTML();
}
