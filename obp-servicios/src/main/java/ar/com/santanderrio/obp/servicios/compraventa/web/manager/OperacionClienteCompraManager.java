/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;

/**
 * The Interface OperacionClienteCompraManager.
 *
 * @author sabrina.cis
 */
public interface OperacionClienteCompraManager {

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
	void generarEstadisticaComprobante();
}
