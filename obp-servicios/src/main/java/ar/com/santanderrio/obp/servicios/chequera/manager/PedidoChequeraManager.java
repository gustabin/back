/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionInView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraViewResponse;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;

/**
 * The Interface PedidoChequeraManager.
 */
public interface PedidoChequeraManager {

	/**
	 * Gets the datos de chequera.
	 *
	 * @return the datos chequera
	 */
	Respuesta<ChequeraViewResponse> getDatosChequera();

	/**
	 * Gets the confirmacion chequera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the confirmacion chequera.
	 */
	Respuesta<ChequeraConfirmacionViewResponse> confirmarPedidoChequera(ChequeraConfirmacionInView viewRequest);

	/**
	 * Gets the comprobante chequera.
	 *
	 * @param chequeraConfirmacionView
	 *            the chequera confirmacion view
	 * @return the comprobante chequera.
	 */
	Respuesta<ReporteView> generarComprobanteChequera(ChequeraConfirmacionViewResponse chequeraConfirmacionView);
}
