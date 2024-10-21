/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CnsDetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;

/**
 * PrestamoOpenCreditManager OLYMPUS.
 *
 * @author Silvina_Luque
 */
@Component
public interface PrestamoOpenCreditManager {

	/**
	 * ObtenerPrestamosOpenCredit.
	 *
	 * @return the respuesta
	 */
	Respuesta<PrestamosOpenCreditView> obtenerPrestamosOpenCredit();

	/**
	 * Obtener historial de pagos minimos.
	 *
	 * @param consultaDetallePagoMinimo
	 *            the consulta detalle pago minimo
	 * @return the respuesta
	 */
	Respuesta<DetallePagosMinimosOpenCreditView> obtenerDetallePagosMinimos(
			CnsDetallePagosMinimosOpenCreditView consultaDetallePagoMinimo);

	/**
	 * ExportarHistorialDePagosMinimos.
	 *
	 * @param exportarPagosMinimosOpenCreditView
	 *            the exportar pagos minimos open credit view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> exportarHistorialPagosMinimos(
			ReportePagosMinimosOpenCreditInView exportarPagosMinimosOpenCreditView);

}
