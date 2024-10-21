/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface OperacionCompraVentaManager.
 *
 * @author sabrina.cis
 */
public interface OperacionCompraVentaManager {

	/**
	 * Operacion cliente compra.
	 *
	 * @param confirmarClienteCompra
	 *            the confirmar cliente compra
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCompraVentaView> operacionClienteCompra(ConfirmarClienteCompraEntity confirmarClienteCompra);

	/**
	 * Generar estadistica comprobante.
	 */
	void generarEstadisticaComprobanteClienteCompra();

	/**
	 * Generar estadistica comprobante cliente vende.
	 */
	void generarEstadisticaComprobanteClienteVende();

	/**
	 * Operacion cliente vende.
	 *
	 * @param confirmarClienteVenta
	 *            the confirmar cliente venta
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCompraVentaView> operacionClienteVende(ConfirmarClienteVentaEntity confirmarClienteVenta);

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobantePDF();
}
