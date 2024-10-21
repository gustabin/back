package ar.com.santanderrio.obp.servicios.recoverydropout.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FlowExecutionResponse {

	@JsonProperty("flowExecutionId")
	private Integer flowExecutionId;
	@JsonProperty("currentStep")
	private Integer currentStep;
	@JsonProperty("status")
	private String status;
	@JsonProperty("notifyDropout")
	private String notifyDropout;
	
	public void setFlowExecutionId(Integer flowExecutionId) {
		this.flowExecutionId = flowExecutionId;
	}

	public void setCurrentStep(Integer currentStep) {
		this.currentStep = currentStep;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNotifyDropout(String notifyDropout) {
		this.notifyDropout = notifyDropout;
	}

	public Integer getFlowExecutionId() {
		return flowExecutionId;
	}

	public Integer getCurrentStep() {
		return currentStep;
	}

	public String getStatus() {
		return status;
	}

	public String getNotifyDropout() {
		return notifyDropout;
	}
	
	
}
