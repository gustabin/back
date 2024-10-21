/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager;

import java.io.IOException;
import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.TituloView;

/**
 * The Interface TitulosOrdenVentaManager.
 */
public interface TitulosOrdenVentaManager {

	/**
	 * Ingreso orden venta.
	 *
	 * @param ingresoView
	 *            the ingreso view
	 * @return the respuesta
	 */
	Respuesta<List<TituloView>> ingresoOrdenVenta(IngresoOrdenVentaInView ingresoView);

	/**
	 * Confirmacion orden venta.
	 *
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionVentaTitulosView> confirmacionOrdenVenta(ConfirmacionVentaTitulosInView confirmacionInView);

	/**
	 * Ejecutar orden venta.
	 *
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionVentaTitulosView> ejecutarOrdenVenta(ConfirmacionVentaTitulosInView confirmacionInView);
	
	/**
	 * Descargar comprobante orden venta PDF.
	 *
	 * @param descargaInView
	 *            the descarga in view
	 * @return the respuesta
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	Respuesta<ReporteView> descargarComprobanteOrdenVentaPDF(IngresoOrdenVentaInView descargaInView) throws IOException;
}