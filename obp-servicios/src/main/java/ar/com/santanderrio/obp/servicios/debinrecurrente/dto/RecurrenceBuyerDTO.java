package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonDeserialize
public class RecurrenceBuyerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("cbu")
	String cbu;
	
	@JsonProperty("cuit")
	String cuit;

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getCbu() {
		return cbu;
	}
	public String getCuit() {
		return cuit;
	}
	
	
}
