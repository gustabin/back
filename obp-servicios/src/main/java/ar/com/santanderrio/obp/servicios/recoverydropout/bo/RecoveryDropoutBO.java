package ar.com.santanderrio.obp.servicios.recoverydropout.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

public interface RecoveryDropoutBO {
	
	public Respuesta<FlowExecutionResponse> startNewFlowExecution(String flowNameKey, FlowExecutionRequest bodyRequest);
	
	public Respuesta<FlowExecutionResponse> updateStepFlowExecution(String flowNameKey, String flowExecutionID, FlowExecutionRequest bodyRequest);

}
