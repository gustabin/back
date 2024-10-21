/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.manager.PedidoChequeraManager;
import ar.com.santanderrio.obp.servicios.chequera.sei.ChequeraSEI;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionInView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraViewResponse;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;

/**
 * The Class ChequeraSEIImpl.
 */
@Component("chequeraSEI")
public class ChequeraSEIImpl implements ChequeraSEI {

	/** The datos solicitante manager. */
	@Autowired
	private PedidoChequeraManager pedidoChequeraManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.chequera.sei.ChequeraSEI#
	 * consultarChequera()
	 */
	@Override
	public Respuesta<ChequeraViewResponse> consultarChequera() {
		return pedidoChequeraManager.getDatosChequera();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.chequera.sei.ChequeraSEI#
	 * confirmarChequera(ar.com.santanderrio.obp.servicios.chequera.view.
	 * ChequeraConfirmacionInView)
	 */
	@Override
	public Respuesta<ChequeraConfirmacionViewResponse> confirmarChequera(ChequeraConfirmacionInView viewRequest) {
		return pedidoChequeraManager.confirmarPedidoChequera(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.chequera.sei.ChequeraSEI#
	 * generarComprobanteChequera(ar.com.santanderrio.obp.servicios.chequera.
	 * view.ChequeraConfirmacionViewResponse)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteChequera(
			ChequeraConfirmacionViewResponse chequeraConfirmacionView) {
		return pedidoChequeraManager.generarComprobanteChequera(chequeraConfirmacionView);
	}
}
