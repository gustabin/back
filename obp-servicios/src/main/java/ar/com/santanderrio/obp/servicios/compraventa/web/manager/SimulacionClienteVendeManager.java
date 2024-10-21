/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;

/**
 * The Interface SimulacionClienteVendeManager.
 *
 * @author sabrina.cis
 */
public interface SimulacionClienteVendeManager {

	/**
	 * Continuar venta dolares.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	Respuesta<SimulacionCompraVentaDolarView> continuarVentaDolares(ContinuarCompraVentaEntity continuarCompraVenta);

}
