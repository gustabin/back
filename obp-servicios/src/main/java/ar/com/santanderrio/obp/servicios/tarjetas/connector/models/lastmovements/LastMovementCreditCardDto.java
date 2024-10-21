package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements;

import java.util.ArrayList;
import java.util.List;

public class LastMovementCreditCardDto {
    private Long cardNumber;
    private Double usdTotal;
    private Double localCurrencyTotal;
    private String category;
    private String cardHolderName;
    private String dateSince;
    
    private List<CreditCardMovement> movements = new ArrayList<CreditCardMovement>();

    public Long getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getUsdTotal() {
        return usdTotal;
    }
    public void setUsdTotal(Double usdTotal) {
        this.usdTotal = usdTotal;
    }

    public Double getLocalCurrencyTotal() {
        return localCurrencyTotal;
    }
    public void setLocalCurrencyTotal(Double localCurrencyTotal) {
        this.localCurrencyTotal = localCurrencyTotal;
    }

    public List<CreditCardMovement> getMovements() {
        return movements;
    }
    public void setMovements(List<CreditCardMovement> movements) {
        this.movements = movements;
    }
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
		return "LastMovementCreditCardDto [cardNumber=" + cardNumber + ", usdTotal=" + usdTotal
				+ ", localCurrencyTotal=" + localCurrencyTotal + ", category=" + category + ", cardHolderName="
				+ cardHolderName + ", dateSince=" + dateSince + ", movements=" + movements + "]";
	}
	
}