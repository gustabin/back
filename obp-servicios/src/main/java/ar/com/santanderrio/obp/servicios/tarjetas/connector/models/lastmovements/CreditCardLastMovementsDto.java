package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements;

import java.util.List;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.OwnerDataDto;

public class CreditCardLastMovementsDto {

    private OwnerDataDto ownerData;
    private LastMovementsTotalsDto movementsTotal;
    private List<LastMovementCreditCardDto> cards;
	public OwnerDataDto getOwnerData() {
		return ownerData;
	}
	public void setOwnerData(OwnerDataDto ownerData) {
		this.ownerData = ownerData;
	}
	public LastMovementsTotalsDto getMovementsTotal() {
		return movementsTotal;
	}
	public void setMovementsTotal(LastMovementsTotalsDto movementsTotal) {
		this.movementsTotal = movementsTotal;
	}
	public List<LastMovementCreditCardDto> getCards() {
		return cards;
	}
	public void setCards(List<LastMovementCreditCardDto> cards) {
		this.cards = cards;
	}
	@Override
	public String toString() {
		return "CreditCardLastMovementsDto [ownerData=" + ownerData + ", movementsTotal=" + movementsTotal + ", cards="
				+ cards + "]";
	}

}