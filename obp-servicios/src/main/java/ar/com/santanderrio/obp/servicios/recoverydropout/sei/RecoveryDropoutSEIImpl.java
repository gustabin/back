package ar.com.santanderrio.obp.servicios.recoverydropout.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.recoverydropout.manager.RecoveryDropoutManager;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

@Component("recoveryDropoutSEI")
public class RecoveryDropoutSEIImpl implements RecoveryDropoutSEI {
	
	@Autowired
    private RecoveryDropoutManager recoveryDropoutManager;

	@Override
	public Respuesta<FlowExecutionResponse> postFlowExecution(String flowNameKey, FlowExecutionRequest bodyRequest) {
		return recoveryDropoutManager.startNewFlowExecution(flowNameKey, bodyRequest);
	}
	
	@Override
	public Respuesta<FlowExecutionResponse> updateFlowExecution(String flowNameKey, String flowExecutionId, FlowExecutionRequest bodyRequest) {
		return recoveryDropoutManager.updateFlowExecution(flowNameKey, flowExecutionId, bodyRequest);
	}
}
