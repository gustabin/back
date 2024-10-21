package ar.com.santanderrio.obp.servicios.recoverydropout.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

public interface RecoveryDropoutConnector {

	FlowExecutionResponse createFlowExecution(String flowID, FlowExecutionRequest request) throws DAOException;
	
	FlowExecutionResponse updateFlowExecution(String flowID, String flowExecutionID, FlowExecutionRequest request) throws DAOException;
}
