/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView;

/**
 * The Interface TraspasoAutomaticoManager.
 */
public interface TraspasoAutomaticoManager {

	/**
	 * Solicitar traspaso automatico.
	 *
	 * @param traspaso
	 *            the traspaso
	 * @return the respuesta
	 */
	Respuesta<TraspasoAutomaticoView> solicitarTraspasoAutomatico(TraspasoAutomaticoView traspaso);

	/**
	 * Confirmar traspaso automatico.
	 *
	 * @param traspaso
	 *            the traspaso
	 * @return the respuesta
	 */
	Respuesta<TraspasoAutomaticoView> confirmarTraspasoAutomatico(TraspasoAutomaticoView traspaso);

}