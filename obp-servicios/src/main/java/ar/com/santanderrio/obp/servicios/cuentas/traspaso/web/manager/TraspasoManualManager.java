/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoManualView;

/**
 * The Interface TraspasoManualManager.
 */
public interface TraspasoManualManager {

	/**
	 * Solicitar traspaso manual.
	 *
	 * @param traspaso
	 *            the traspaso
	 * @return the respuesta
	 */
	Respuesta<TraspasoManualView> realizarTraspasoManual(TraspasoManualView traspaso);

	/**
	 * Cancelar traspaso manual.
	 *
	 * @param traspaso
	 *            the traspaso
	 * @return the respuesta
	 */
	Respuesta<TraspasoManualView> configurarTraspasoManual(TraspasoManualView traspaso);

	/**
	 * Confirmar traspasp manual.
	 *
	 * @param traspaso
	 *            the traspaso
	 * @return the respuesta
	 */
	Respuesta<TraspasoManualView> confirmarTraspasoManual(TraspasoManualView traspaso);

	/**
	 * Obtener comprobante.
	 */
	void obtenerComprobante();

}