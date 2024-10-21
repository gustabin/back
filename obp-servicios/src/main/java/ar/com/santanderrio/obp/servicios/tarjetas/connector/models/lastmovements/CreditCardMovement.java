package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements;

public class CreditCardMovement {

    private String commercialEstablishment;
    private String commercialEstablishmentCode;
    private Double amount;
    private String date;
    private String ticketNumber;
    private String currency;
    private LastMovementsDetailPromoDto promoDetail;

    public String getCommercialEstablishment() {
        return commercialEstablishment;
    }

    public void setCommercialEstablishment(String commercialEstablishment) {
        this.commercialEstablishment = commercialEstablishment;
    }

    public String getCommercialEstablishmentCode() {
        return commercialEstablishmentCode;
    }

    public void setCommercialEstablishmentCode(String commercialEstablishmentCode) {
        this.commercialEstablishmentCode = commercialEstablishmentCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LastMovementsDetailPromoDto getPromoDetail() {
        return promoDetail;
    }

    public void setPromoDetail(LastMovementsDetailPromoDto promoDetail) {
        this.promoDetail = promoDetail;
    }

	@Override
	public String toString() {
		return "CreditCardMovement [commercialEstablishment=" + commercialEstablishment
				+ ", commercialEstablishmentCode=" + commercialEstablishmentCode + ", amount=" + amount + ", date="
				+ date + ", ticketNumber=" + ticketNumber + ", currency=" + currency + ", promoDetail=" + promoDetail
				+ "]";
	}
    
}