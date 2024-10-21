/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.bo.DatosAdicionalesBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.DatosAdicionalesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;

/**
 * DTF 16416.
 *
 * @author B041299
 */
@Component
public class DatosAdicionalesBOImpl implements DatosAdicionalesBO {

	/** The datos adicionales DAO. */
	@Autowired
	private DatosAdicionalesDAO datosAdicionalesDAO;

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
	@Override
	public Respuesta<Boolean> altaInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws BusinessException {
		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		try {
			respuesta = datosAdicionalesDAO.altaInformacionAdicional(cliente, infoAdicional);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
		return respuesta;
	}

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
	@Override
	public Respuesta<Boolean> modificacionInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws BusinessException {
		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		try {
			respuesta = datosAdicionalesDAO.modificacionInformacionAdicional(cliente, infoAdicional);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
		return respuesta;
	}
}
