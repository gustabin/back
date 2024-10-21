package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class RecurrenceBuyerItemDTO {

	@JsonProperty("sellerCuit")
	String sellerCuit;
	
	@JsonProperty("sellerName")
	String sellerName;
	
	@JsonProperty("buyerCuit")
	String buyerCuit;
	
	@JsonProperty("buyerCbu")
	String buyerCbu;
	
	@JsonProperty("debin")
	RecurrenceDebinDTO debin;
	
	@JsonProperty("recurrenceId")
	Integer recurrenceId;
	
	@JsonProperty("status")
	String status;
	
	@JsonProperty("creationTimestamp")
	String creationTimestamp;
	
	@JsonProperty("creationCuit")
	String creationCuit;
	
	public String getSellerCuit() {
		return sellerCuit;
	}
	
	public void setSellerCuit(String sellerCuit) {
		this.sellerCuit = sellerCuit;
	}
	
	public String getSellerName() {
		return sellerName;
	}
	
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public String getBuyerCuit() {
		return buyerCuit;
	}
	
	public void setBuyerCuit(String buyerCuit) {
		this.buyerCuit = buyerCuit;
	}
	
	public String getBuyerCbu() {
		return buyerCbu;
	}
	
	public void setBuyerCbu(String buyerCbu) {
		this.buyerCbu = buyerCbu;
	}
	
	public RecurrenceDebinDTO getDebin() {
		return debin;
	}
	
	public void setDebin(RecurrenceDebinDTO debin) {
		this.debin = debin;
	}

	public Integer getRecurrenceId() {
		return recurrenceId;
	}
	
	public void setRecurrenceId(Integer recurrenceId) {
		this.recurrenceId = recurrenceId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCreationTimestamp() {
		return creationTimestamp;
	}
	
	public void setCreationTimestamp(String creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public String getCreationCuit() {
		return creationCuit;
	}

	public void setCreationCuit(String creationCuit) {
		this.creationCuit = creationCuit;
	}
	
}
