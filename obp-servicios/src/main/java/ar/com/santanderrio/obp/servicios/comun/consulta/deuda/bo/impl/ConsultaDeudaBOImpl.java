/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.bo.ConsultaDeudaBO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.ConsultaDeudaDAO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ConsultaDeudaBOImpl.
 */
@Component
public class ConsultaDeudaBOImpl implements ConsultaDeudaBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDeudaBOImpl.class);

	/** The consulta deuda DAO. */
	@Autowired
	private ConsultaDeudaDAO consultaDeudaDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.consulta.deuda.bo.ConsultaDeudaBO
	 * #consultarDeuda(java.lang.String)
	 */
	@Override
	public Respuesta<ClasificacionDeuda> consultarDeuda(String nup) {

		ClasificacionDeuda clasificacionDeuda = new ClasificacionDeuda();
		try {
			clasificacionDeuda = consultaDeudaDAO.consultaDeuda(nup);
		} catch (DAOException e) {
			LOGGER.error("Error al consultar el estado deudor del cliente", e);
			return respuestaFactory.crearRespuestaError(ClasificacionDeuda.class, StringUtils.EMPTY, StringUtils.EMPTY);
		}
		return respuestaFactory.crearRespuestaOk(clasificacionDeuda);
	}

}
