package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements;

public class LastMovementsTotalsDto {

    private Double totalDollarCurrency;
    private Double totalLocalCurrency;

    public Double getTotalDollarCurrency() {
        return totalDollarCurrency;
    }
    public void setTotalDollarCurrency(Double totalDollarCurrency) {
        this.totalDollarCurrency = totalDollarCurrency;
    }

    public Double getTotalLocalCurrency() {
        return totalLocalCurrency;
    }
    public void setTotalLocalCurrency(Double totalLocalCurrency) {
        this.totalLocalCurrency = totalLocalCurrency;
    }
	@Override
	public String toString() {
		return "LastMovementsTotalsDto [totalDollarCurrency=" + totalDollarCurrency + ", totalLocalCurrency="
				+ totalLocalCurrency + "]";
	}
    
}