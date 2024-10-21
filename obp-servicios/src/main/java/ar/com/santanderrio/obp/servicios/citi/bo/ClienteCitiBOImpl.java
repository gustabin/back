/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.bo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.dao.ClienteCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.RepuestaSPClienteCitiEntity;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ClienteCitiBOImpl.
 */
@Component
public class ClienteCitiBOImpl implements ClienteCitiBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteCitiBOImpl.class);

	/** The cliente citi DAO. */
	@Autowired
	private ClienteCitiDAO clienteCitiDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.citi.ClienteCitiBO#IsExClienteCiti(java
	 * .lang.String)
	 */
	@Override
	public Respuesta<Boolean> isExCiti(String nup) {

		try {

			RepuestaSPClienteCitiEntity repuestaSPClienteCitiEntity = clienteCitiDAO
			        .consultarCitiClienteIdentificacion(nup);

			String nupRetorno = repuestaSPClienteCitiEntity != null && repuestaSPClienteCitiEntity.getPoNup() != null
			        ? repuestaSPClienteCitiEntity.getPoNup().toString() : "";

			return respuestaFactory.crearRespuestaOk(StringUtils.equals(nup, nupRetorno));

		} catch (DAOException e) {

			LOGGER.error("Error Consulta Cliente CITI");
		}
		return respuestaFactory.crearRespuestaError(Boolean.class, StringUtils.EMPTY, TipoError.ERROR_GENERICO, StringUtils.EMPTY);

	}
}
