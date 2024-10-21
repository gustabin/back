/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.AceptacionCondicionesCuentasCustodiaViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaView;

/**
 * The Interface ConfiguracionOrdenVentaTituloManager.
 */
public interface ConfiguracionOrdenVentaTituloManager {

	/**
	 * Configuracion orden venta.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionOrdenVentaView> configuracionOrdenVenta(ConfiguracionOrdenVentaInView datosEntrada);

	/**
	 * Aceptacion resolucion origen de fondos.
	 *
	 * @param esBancaPrivada
	 *            the es banca privada
	 */
	void aceptacionResolucionOrigenDeFondos(Boolean esBancaPrivada);

	/**
	 * Aceptacion condiciones cuentas orden venta.
	 *
	 * @param aceptacionView
	 *            the aceptacion view
	 * @return the respuesta
	 */
	Respuesta<Void> aceptacionCondicionesCuentasOrdenVenta(AceptacionCondicionesCuentasCustodiaViewIn aceptacionView);

	/**
	 * Descargar comprobante aceptacion CNV orden venta.
	 *
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteAceptacionCNVOrdenVenta(Boolean esBancaPrivada);

}
