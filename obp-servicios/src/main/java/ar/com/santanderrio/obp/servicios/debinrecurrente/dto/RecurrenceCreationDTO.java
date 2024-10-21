package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize
public class RecurrenceCreationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("authorCuit")
	String authorCuit;

	@JsonProperty("buyer")
	RecurrenceBuyerDTO buyer;
	
	@JsonProperty("debin")
	RecurrenceDebinDTO debin;
	
	@JsonProperty("seller")
	RecurrenceSellerDTO seller;
	
	public String getAuthorCuit() {
		return authorCuit;
	}
	public RecurrenceBuyerDTO getBuyer() {
		return buyer;
	}
	public RecurrenceDebinDTO getDebin() {
		return debin;
	}
	public RecurrenceSellerDTO getSeller() {
		return seller;
	}
	public void setAuthorCuit(String authorCuit) {
		this.authorCuit = authorCuit;
	}
	public void setBuyer(RecurrenceBuyerDTO buyer) {
		this.buyer = buyer;
	}
	public void setDebin(RecurrenceDebinDTO debin) {
		this.debin = debin;
	}
	public void setSeller(RecurrenceSellerDTO seller) {
		this.seller = seller;
	}

}
