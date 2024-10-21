package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonDeserialize
public class RecurrenceDebinDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("concept")
	String concept;
	
	@JsonProperty("currency")
	String currency;
	
	@JsonProperty("detail")
	String detail;
	
	@JsonProperty("provision")
	String provision;
	
	@JsonProperty("reference")
	String reference;

	public void setConcept(String concept) {
		this.concept = concept;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setProvision(String provision) {
		this.provision = provision;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getConcept() {
		return concept;
	}
	public String getCurrency() {
		return currency;
	}
	public String getDetail() {
		return detail;
	}
	public String getProvision() {
		return provision;
	}
	public String getReference() {
		return reference;
	}
	
}
