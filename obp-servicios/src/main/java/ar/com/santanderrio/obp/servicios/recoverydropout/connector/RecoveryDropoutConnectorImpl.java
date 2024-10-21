package ar.com.santanderrio.obp.servicios.recoverydropout.connector;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.cxf.jaxrs.client.WebClient;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

@Component
public class RecoveryDropoutConnectorImpl implements RecoveryDropoutConnector {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecoveryDropoutConnectorImpl.class);
	private static final String RECOVERY_DROPOUT_API = "DROPOUT-RECOVERY-API";
	
	@Autowired
    private RestWebClient restWebClient;

	@Override
	public FlowExecutionResponse createFlowExecution(String flowID, FlowExecutionRequest request) throws DAOException {
		LOGGER.info("Inicio Recovery Dropout API call");
        try {
        	String path = String.format("/flows/%s/flowExecutions", flowID);
            final WebClient webClient = restWebClient.obtenerClienteRest(RECOVERY_DROPOUT_API);
            webClient.type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(path);

            return webClient.post(request).readEntity(FlowExecutionResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al crear un nuevo flow execution");
        }
	}

	@Override
	public FlowExecutionResponse updateFlowExecution(String flowID, String flowExecutionID, FlowExecutionRequest request) throws DAOException {
		LOGGER.info("Inicio Recovery Dropout API call");
        try {
        	String path = String.format("/flows/%1$s/flowExecutions/%2$s", flowID, flowExecutionID);
            final WebClient webClient = restWebClient.obtenerClienteRest(RECOVERY_DROPOUT_API);
            webClient.type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(path);
            Response response = webClient.put(request);
            FlowExecutionResponse flowResponse = new FlowExecutionResponse();
            if(response.getStatus() == Response.Status.ACCEPTED.getStatusCode()) {
            	flowResponse.setCurrentStep(request.getStep());
            	flowResponse.setFlowExecutionId(Integer.parseInt(flowExecutionID));
            	flowResponse.setStatus(request.getState());
            }
            return flowResponse;
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al crear un nuevo flow execution");
        }
	}

}
