package ar.com.santanderrio.obp.servicios.extraccionefectivo.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class CardlessWithdrawal {

	@JsonProperty("amount")
	private BigDecimal amount; 
	
	@JsonProperty("origin")	
	private Origin origin;
	
	@JsonProperty("destination")
	private Destination destination;
	
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destiny) {
		this.destination = destiny;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}