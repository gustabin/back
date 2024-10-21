/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;

/**
 * The Interface NUPBO.
 *
 * @author florencia.n.martinez
 */
public interface NUPBO {

	/**
	 * Obtener nup.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<NupDTO> obtenerNUP(Cliente cliente) throws BusinessException;
}
