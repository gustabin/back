/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;

/**
 * The Interface SimulacionClienteCompraManager.
 *
 * @author sabrina.cis
 */
public interface SimulacionCompraVentaManager {

	/**
	 * Continuar compra dolares.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	Respuesta<SimulacionCompraVentaDolarView> continuarCompraDolares(ContinuarCompraVentaEntity continuarCompraVenta);

	/**
	 * Continuar venta dolares.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	Respuesta<SimulacionCompraVentaDolarView> continuarVentaDolares(ContinuarCompraVentaEntity continuarCompraVenta);

}
