/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.AdhesionResumenView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;

/**
 * The Interface ResumenCuentaManager.
 */
public interface ResumenCuentaManager {

	/**
	 * Comprobante adhesion resumen.
	 *
	 * @param view
	 *            the view
	 */
	void comprobanteAdhesionResumen(AdhesionResumenView view);

	/**
	 * Obtener lista resumen.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 */
	Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(String nroCuenta);

	/**
	 * Obtener resumenes PDF.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerResumenesPDF(ConsultaCuentaView cuenta);

	/**
	 * Obtener resumen descarga multiple.
	 *
	 * @param fecha
	 *            the fecha
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param cantidadADescargar
	 *            the cantidad A descargar
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerResumenDescargaMultiple(List<String> fecha, String nroCuenta, int cantidadADescargar);

	/**
	 * Confirmar adhesion resumen.
	 *
	 * @param adhesionResumenView
	 *            the adhesion resumen view
	 * @return the respuesta
	 */
	Respuesta<AdhesionResumenView> confirmarAdhesionResumen(AdhesionResumenView adhesionResumenView);

}
