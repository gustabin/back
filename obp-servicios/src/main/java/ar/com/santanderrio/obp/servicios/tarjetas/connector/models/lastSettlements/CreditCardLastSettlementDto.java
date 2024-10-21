package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements;

import java.util.List;

public class CreditCardLastSettlementDto {
	
	private List<CreditCardSettlementDto> settlements;
	
	public void setSettlements(List<CreditCardSettlementDto> settlements) {
		this.settlements = settlements;
	}

	public List<CreditCardSettlementDto> getCreditCardSettlementsDto() {
		return settlements;
	}
	
}
