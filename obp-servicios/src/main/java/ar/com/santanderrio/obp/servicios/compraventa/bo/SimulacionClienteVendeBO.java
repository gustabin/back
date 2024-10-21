/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;

/**
 * The Interface SimulacionClienteVendeBO.
 *
 * @author sabrina.cis
 */
public interface SimulacionClienteVendeBO {

	/**
	 * Obtener simulacion cliente vende.
	 *
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<SimulacionCompraVentaDTO> obtenerSimulacionClienteVende(ParametrosSimulacion parametrosSimulacion)
			throws BusinessException;

}