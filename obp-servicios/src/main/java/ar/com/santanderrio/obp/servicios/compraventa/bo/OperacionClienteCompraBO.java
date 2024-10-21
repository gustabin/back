/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;

/**
 * The Interface OperacionClienteCompraBO.
 *
 * @author sabrina.cis
 */
public interface OperacionClienteCompraBO {

	/**
	 * Operacion cliente compra.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ComprobanteCompraVentaDTO> operacionClienteCompra(ParametrosOperacion parametrosOperacion)
			throws BusinessException;

}