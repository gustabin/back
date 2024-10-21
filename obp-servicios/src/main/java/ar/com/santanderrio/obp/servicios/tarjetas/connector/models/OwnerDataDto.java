package ar.com.santanderrio.obp.servicios.tarjetas.connector.models;

public class OwnerDataDto {

    private Long activeCardNumber;
    private String category;
    private String ownerName;
    private String cardHolderName;
    private String dateSince;

    public Long getActiveCardNumber() {
        return activeCardNumber;
    }
    public void setActiveCardNumber(Long activeCardNumber) {
        this.activeCardNumber = activeCardNumber;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getDateSince() {
        return dateSince;
    }
    public void setDateSince(String dateSince) {
        this.dateSince = dateSince;
    }
	@Override
	public String toString() {
		return "OwnerDataDto [activeCardNumber=" + activeCardNumber + ", category=" + category + ", ownerName="
				+ ownerName + ", cardHolderName=" + cardHolderName + ", dateSince=" + dateSince + "]";
	}
    
}