/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.prestamos.sei.PrestamoOpenCreditSEI;
import ar.com.santanderrio.obp.servicios.prestamos.view.CnsDetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoOpenCreditManager;

/**
 * OLYMPUS OpenCredit.
 *
 * @author Silvina_Luque
 */
@Component("prestamoOpenCreditSEI")
public class PrestamoOpenCreditSEIImpl implements PrestamoOpenCreditSEI {

	/** The prestamo manager. */
	@Autowired
	private PrestamoOpenCreditManager prestamoOpenCreditManager;

	/**
	 * obtenerDetallePagoMinimo.
	 *
	 * @param consultaDetallePagoMinimo
	 *            the consulta detalle pago minimo
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DetallePagosMinimosOpenCreditView> obtenerDetallePagoMinimo(
			CnsDetallePagosMinimosOpenCreditView consultaDetallePagoMinimo) {
		return prestamoOpenCreditManager.obtenerDetallePagosMinimos(consultaDetallePagoMinimo);
	}

	/**
	 * exportarHistorialPagosMinimos().
	 *
	 * @param exportarPagosMinimosOpenCreditView
	 *            the exportar pagos minimos open credit view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> exportarHistorialPagosMinimos(
			ReportePagosMinimosOpenCreditInView exportarPagosMinimosOpenCreditView) {
		return prestamoOpenCreditManager.exportarHistorialPagosMinimos(exportarPagosMinimosOpenCreditView);

	}

}
