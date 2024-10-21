/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadistica.web.view;

import ar.com.santanderrio.obp.servicios.comun.estadistica.sei.EstadisticaSEI;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;

/**
 * The Interface EstadisticaView.
 * 
 * @author manuel.vargas
 * @author emilio.watemberg
 * @see {@link EstadisticaSEI}
 * 
 */
public interface EstadisticaView {

	/**
	 * Cargar estadistica.
	 *
	 * @return the estadistica
	 */
	Estadistica cargarEstadistica();

}
