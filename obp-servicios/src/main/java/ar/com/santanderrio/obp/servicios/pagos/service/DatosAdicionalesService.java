/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;

/**
 * Informacion adicional para servicios de PagoMiscuentas DTF 16416.
 *
 * @author B041299
 */
public interface DatosAdicionalesService extends Service {

	/**
	 * Alta informacion adicional.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoAdicional
	 *            the info adicional
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Boolean> altaInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws ServiceException;

	/**
	 * Modificacion informacion adicional.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoAdicional
	 *            the info adicional
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Boolean> modificacionInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws ServiceException;
}
