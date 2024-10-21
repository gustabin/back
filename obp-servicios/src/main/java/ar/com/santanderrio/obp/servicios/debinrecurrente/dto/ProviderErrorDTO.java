package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ProviderErrorDTO {
	
	@JsonProperty("provider")
	private String provider;
	
	@JsonProperty("rootCode")
	private Integer rootCode;
	
	@JsonProperty("rootDetail")
	private DetailErrorDTO rootDetail;
	
	@JsonProperty("rootMessage")
	private String rootMessage;
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public Integer getRootCode() {
		return rootCode;
	}
	
	public void setRootCode(Integer rootCode) {
		this.rootCode = rootCode;
	}
	
	public DetailErrorDTO getRootDetail() {
		return rootDetail;
	}
	
	public void setRootDetail(DetailErrorDTO rootDetail) {
		this.rootDetail = rootDetail;
	}
	
	public String getRootMessage() {
		return rootMessage;
	}
	
	public void setRootMessage(String rootMessage) {
		this.rootMessage = rootMessage;
	}
	
}
