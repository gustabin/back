package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity;

import org.codehaus.jackson.annotate.JsonProperty;


public class VentaMepRequest{
	
	@JsonProperty("monto")
	private float amount;
	
	@JsonProperty("cuenta")
	private String operativeAccount;
	
	@JsonProperty("nup")
	private String nup;
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String getOperativeAccount() {
		return operativeAccount;
	}
	
	public void setOperativeAccount(String operativeAccount) {
		this.operativeAccount = operativeAccount;
	}
	
	public String getNup() {
		return nup;
	}
	
	public void setNup(String nup) {
		this.nup = nup;
	}
	
	
}
