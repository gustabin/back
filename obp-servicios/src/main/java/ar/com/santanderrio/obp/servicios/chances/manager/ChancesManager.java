package ar.com.santanderrio.obp.servicios.chances.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceView;

/**
 * The Interface ChancesManager.
 */
public interface ChancesManager {
	
	/**
	 * Obtiene chances segun el mes seleccionado.
	 *
	 * @param ChanceView
	 *            the ChanceView
	 * @return the respuesta
	 */
	Respuesta<ChanceView> obtenerChancesMes(ChanceView chanceView);

}
