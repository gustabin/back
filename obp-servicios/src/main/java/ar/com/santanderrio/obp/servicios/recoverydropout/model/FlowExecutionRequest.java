package ar.com.santanderrio.obp.servicios.recoverydropout.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FlowExecutionRequest {

	@JsonProperty("keys")
	private Map<String, String> keys;
	@JsonProperty("additionalData")
	private Map<String, String> additionalData;
	@JsonProperty("state")
	private String state;
	@JsonProperty("step")
	private Integer step;
	
//	public FlowExecutionRequest(Map<String, String> keys, Map<String, String> additionalData) {
//		this.keys = keys;
//		this.additionalData = additionalData;
//	}
	
	public void setStepState(Integer step, String state) {
		this.step = step;
		this.state = state;
	}
	
	public Map<String, String> getKeys() {
		return this.keys;
	}

	public Map<String, String> getAdditionalData() {
		return this.additionalData;
	}

	public String getState() {
		return this.state;
	}

	public Integer getStep() {
		return this.step;
	}
	
	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}

	public void setAdditionalData(Map<String, String> additionalData) {
		this.additionalData = additionalData;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}
