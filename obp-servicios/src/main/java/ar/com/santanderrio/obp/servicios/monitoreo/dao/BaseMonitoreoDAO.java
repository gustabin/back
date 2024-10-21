/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.dao;

/**
 * The Interface BaseMonitoreoDAO.
 *
 * @author sergio.e.goldentair
 */
public interface BaseMonitoreoDAO {
    
    /**
	 * Validar si la base responde para operar con la app.
	 *
	 * @return true, if is DB disponible
	 */
    boolean isDBDisponible();
}
