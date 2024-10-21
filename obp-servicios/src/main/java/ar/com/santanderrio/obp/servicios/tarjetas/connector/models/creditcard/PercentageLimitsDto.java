package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard;


public class PercentageLimitsDto {
    private Double purchaseLimitPercentage;

    private Double financialLimitPercentage;

    private Double installmentLimitPercentage;

	public Double getPurchaseLimitPercentage() {
		return purchaseLimitPercentage;
	}

	public void setPurchaseLimitPercentage(Double purchaseLimitPercentage) {
		this.purchaseLimitPercentage = purchaseLimitPercentage;
	}

	public Double getFinancialLimitPercentage() {
		return financialLimitPercentage;
	}

	public void setFinancialLimitPercentage(Double financialLimitPercentage) {
		this.financialLimitPercentage = financialLimitPercentage;
	}

	public Double getInstallmentLimitPercentage() {
		return installmentLimitPercentage;
	}

	public void setInstallmentLimitPercentage(Double installmentLimitPercentage) {
		this.installmentLimitPercentage = installmentLimitPercentage;
	}
}