/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.NUPBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;

/**
 * The Class NUPBOImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class NUPBOImpl extends CompraVentaDolaresBOImpl implements NUPBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NUPBOImpl.class);

	/**
	 * Obtener nup.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<NupDTO> obtenerNUP(Cliente cliente) throws BusinessException {
		try {
			return casuistica.crearRespuestaOk(NupDTO.class, getNupDAO().obtenerNUP(cliente));
		} catch (DAOException e) {
			LOGGER.error("ERROR DAO NUP.", e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		} catch (Exception e) {
			LOGGER.error("ERROR al obtener los datos del NUP.", e);
			throw new BusinessException();
		}
	}

}