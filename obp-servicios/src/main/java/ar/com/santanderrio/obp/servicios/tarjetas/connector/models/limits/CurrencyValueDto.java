package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits;


import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;

public class CurrencyValueDto {
	private Currency currency;
	private Double value;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
