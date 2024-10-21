/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;

/**
 * The Interface OperacionClienteVendeManager.
 */
public interface OperacionClienteVendeManager {
	/**
	 * Operacion cliente compra.
	 *
	 * @param confirmarClienteVenta
	 *            the confirmar cliente venta
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCompraVentaView> operacionClienteVenta(ConfirmarClienteVentaEntity confirmarClienteVenta);

	/**
	 * Generar estadistica comprobante.
	 */
	void generarEstadisticaComprobante();
}
