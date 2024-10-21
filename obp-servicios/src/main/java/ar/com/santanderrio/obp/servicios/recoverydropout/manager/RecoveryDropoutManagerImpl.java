package ar.com.santanderrio.obp.servicios.recoverydropout.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.recoverydropout.bo.RecoveryDropoutBO;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

@Component
public class RecoveryDropoutManagerImpl implements RecoveryDropoutManager {
	
	@Autowired
    private RecoveryDropoutBO recoveryDropoutBo;

	@Override
	public Respuesta<FlowExecutionResponse> startNewFlowExecution(String flowNameKey, FlowExecutionRequest bodyRequest) {
		
		return recoveryDropoutBo.startNewFlowExecution(flowNameKey, bodyRequest);
	}

	@Override
	public Respuesta<FlowExecutionResponse> updateFlowExecution(String flowNameKey, String flowExecutionId, FlowExecutionRequest bodyRequest) {
		return recoveryDropoutBo.updateStepFlowExecution(flowNameKey, flowExecutionId, bodyRequest);
	}

}
