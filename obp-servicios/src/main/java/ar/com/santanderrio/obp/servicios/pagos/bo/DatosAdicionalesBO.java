/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;

/**
 * DTF: 16416.
 *
 * @author B041299
 */
public interface DatosAdicionalesBO extends BusinessObject {

	/**
	 * Alta informacion adicional.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoAdicional
	 *            the info adicional
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Boolean> altaInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws BusinessException;

	/**
	 * Modificacion informacion adicional.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoAdicional
	 *            the info adicional
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Boolean> modificacionInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws BusinessException;
}
