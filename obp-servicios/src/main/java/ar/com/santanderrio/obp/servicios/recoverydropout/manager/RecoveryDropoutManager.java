package ar.com.santanderrio.obp.servicios.recoverydropout.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

public interface RecoveryDropoutManager {

	public Respuesta<FlowExecutionResponse> startNewFlowExecution(String flowNameKey, FlowExecutionRequest bodyRequest);
	
	public Respuesta<FlowExecutionResponse> updateFlowExecution(String flowNameKey, String flowExecutionId, FlowExecutionRequest bodyRequest);
}
