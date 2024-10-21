package ar.com.santanderrio.obp.servicios.recoverydropout.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.recoverydropout.connector.RecoveryDropoutConnector;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

@Component
public class RecoveryDropoutBOImpl implements RecoveryDropoutBO {
	
	@Autowired
	private Environment environment;
	
	@Autowired
    private RespuestaFactory respuestaFactory;
	
	@Autowired
	private RecoveryDropoutConnector recoveryDropoutConnector;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecoveryDropoutBOImpl.class);
	
	private static final String FLOW_ID = "FLOW-ID."; 

	@Override
	public Respuesta<FlowExecutionResponse> startNewFlowExecution(String flowNameKey, FlowExecutionRequest bodyRequest) {
		final Respuesta<FlowExecutionResponse> serviceResponse = new Respuesta<FlowExecutionResponse>();
		try {
			String flowID = tryToGetFlowID(flowNameKey);
			if(flowID != null) {
				final FlowExecutionResponse response = recoveryDropoutConnector.createFlowExecution(flowID, bodyRequest);
				serviceResponse.setEstadoRespuesta(EstadoRespuesta.OK);
				serviceResponse.setRespuesta(response);
				
			}else {
				 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}
			
		}catch (DAOException e) {
			LOGGER.error("RecoveryDropout: Ocurrió un error al querer solicitar un flow execution ID", e.getErrorCode(), e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}	
		return serviceResponse;
	}

	@Override
	public Respuesta<FlowExecutionResponse> updateStepFlowExecution(String flowNameKey, String flowExecutionID, FlowExecutionRequest bodyRequest) {
		final Respuesta<FlowExecutionResponse> serviceResponse = new Respuesta<FlowExecutionResponse>();
		try {
			String flowID = tryToGetFlowID(flowNameKey);
			if(flowID != null) {
				final FlowExecutionResponse response = recoveryDropoutConnector.updateFlowExecution(flowID, flowExecutionID, bodyRequest);
				serviceResponse.setEstadoRespuesta(EstadoRespuesta.OK);
				serviceResponse.setRespuesta(response);
				
			}else {
				 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}
			
		}catch (DAOException e) {
			LOGGER.error("RecoveryDropout: Ocurrió un error al querer solicitar un flow execution ID", e.getErrorCode(), e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}	
		return serviceResponse;
	}
	
	private String tryToGetFlowID(String flowNameKey) {
		String flowID = null;
		try {
			String prop = environment.getProperty(FLOW_ID + flowNameKey);
			if(prop != null && !prop.trim().isEmpty()) {
				flowID = prop;
			}			
	    } catch(NullPointerException e) {
	        return null;
	    }

		return flowID;
	}

}
