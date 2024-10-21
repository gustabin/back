package ar.com.santanderrio.obp.servicios.queue.bo.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.queue.bo.QueueSTBO;
import ar.com.santanderrio.obp.servicios.queue.dao.QueueSTDAO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTDTO;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnResponse;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnStatesEnum;

/**
 * The Class QueueSTBOImpl.
 */
@Component
public class QueueSTBOImpl implements QueueSTBO {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSTBOImpl.class);

	/** The Constant DNI_SIZE. */
	private static final int DNI_SIZE = 8;

	/** The queue STDAO. */
    @Autowired
    private QueueSTDAO queueSTDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

	/**
	 * Crear turno.
	 *
	 * @param clientID the client ID
	 * @return the respuesta
	 */
	@Override
	public Respuesta<QueueSTDTO> crearTurno(String clientID) {
		TurnResponse turnResponse = null;
		try {
			String dni = ISBANStringUtils.fillNum(clientID, DNI_SIZE);
			turnResponse = queueSTDAO.crearTurno(dni);
			if (turnResponse != null && StringUtils.isBlank(turnResponse.getCode())) {
				return crearRespuesta(turnResponse);
			}
		} catch (Exception e) {
			LOGGER.info("Error al crear turno");
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_GENERICO);
	}

	/**
	 * Verificar turno.
	 *
	 * @param turnId the turn id
	 * @return the respuesta
	 */
	@Override
	public Respuesta<QueueSTDTO> verificarTurno(String turnId) {
		TurnResponse turnResponse = null;
		try {
			turnResponse = queueSTDAO.verificarTurno(turnId);
			if (turnResponse != null) {
				if (StringUtils.isBlank(turnResponse.getCode())) {
					return crearRespuesta(turnResponse);
				} else if ("turn.not.exists".equals(turnResponse.getCode())) {
					return respuestaFactory.crearRespuestaError("", TipoError.QUEUE_ERROR_TURN_NOT_FOUND,
							CodigoMensajeConstantes.ERROR_GENERICO);
				}					
			}
		} catch (Exception e) {
			LOGGER.info("Error al verificar turno", e);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_GENERICO);
	}

	/**
	 * Crear respuesta.
	 *
	 * @param turnResponse the turn response
	 * @return the respuesta
	 */
	private Respuesta<QueueSTDTO> crearRespuesta(TurnResponse turnResponse) {
		QueueSTDTO queueSTDTO = new QueueSTDTO();
		try {
			BeanUtils.copyProperties(queueSTDTO, turnResponse);
			return respuestaFactory.crearRespuestaOk(queueSTDTO);
		} catch (Exception e) {
			LOGGER.info("Error al obtener queueSTDTO", e);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_GENERICO);
	}

	/**
	 * Actualizar turno.
	 *
	 * @param turnId the turn id
	 * @param turnStatus the turn status
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> actualizarTurno(String turnId, TurnStatesEnum turnStatus) {
		try {
			queueSTDAO.actualizarTurno(turnId, turnStatus.getCodigo());
			return respuestaFactory.crearRespuestaOk(Void.class);
		} catch (Exception e) {
			LOGGER.info("Error al actualizar turno", e);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_GENERICO);
	}

}
