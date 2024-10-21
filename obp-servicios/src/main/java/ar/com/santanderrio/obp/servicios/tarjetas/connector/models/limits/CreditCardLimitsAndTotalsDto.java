package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits;

import java.util.List;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.OwnerDataDto;

public class CreditCardLimitsAndTotalsDto {

	private OwnerDataDto ownerData;
	private String unifiedLimits;
	private Double totalLocalCurrency;
	private Double totalDollarCurrency;
	private String deadLineDate;
	private String expirationDate;
	// Limits
	private Double buyLimit;
	private Double buyLimitAvailable;
	private Double installmentsLimit;
	private Double installmentsLimitAvailable;
	private Double financingLimit;
	private Double advanceLimit;
	private Double advanceLimitAvailable;
	
	public OwnerDataDto getOwnerData() {
		return ownerData;
	}
	public void setOwnerData(OwnerDataDto ownerData) {
		this.ownerData = ownerData;
	}
	public String getUnifiedLimits() {
		return unifiedLimits;
	}
	public void setUnifiedLimits(String unifiedLimits) {
		this.unifiedLimits = unifiedLimits;
	}
	public Double getTotalLocalCurrency() {
		return totalLocalCurrency;
	}
	public void setTotalLocalCurrency(Double totalLocalCurrency) {
		this.totalLocalCurrency = totalLocalCurrency;
	}
	public Double getTotalDollarCurrency() {
		return totalDollarCurrency;
	}
	public void setTotalDollarCurrency(Double totalDollarCurrency) {
		this.totalDollarCurrency = totalDollarCurrency;
	}
	public String getDeadLineDate() {
		return deadLineDate;
	}
	public void setDeadLineDate(String deadLineDate) {
		this.deadLineDate = deadLineDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Double getBuyLimit() {
		return buyLimit;
	}
	public void setBuyLimit(Double buyLimit) {
		this.buyLimit = buyLimit;
	}
	public Double getBuyLimitAvailable() {
		return buyLimitAvailable;
	}
	public void setBuyLimitAvailable(Double buyLimitAvailable) {
		this.buyLimitAvailable = buyLimitAvailable;
	}
	public Double getInstallmentsLimit() {
		return installmentsLimit;
	}
	public void setInstallmentsLimit(Double installmentsLimit) {
		this.installmentsLimit = installmentsLimit;
	}
	public Double getInstallmentsLimitAvailable() {
		return installmentsLimitAvailable;
	}
	public void setInstallmentsLimitAvailable(Double installmentsLimitAvailable) {
		this.installmentsLimitAvailable = installmentsLimitAvailable;
	}
	public Double getFinancingLimit() {
		return financingLimit;
	}
	public void setFinancingLimit(Double financingLimit) {
		this.financingLimit = financingLimit;
	}
	public Double getAdvanceLimit() {
		return advanceLimit;
	}
	public void setAdvanceLimit(Double advanceLimit) {
		this.advanceLimit = advanceLimit;
	}
	public Double getAdvanceLimitAvailable() {
		return advanceLimitAvailable;
	}
	public void setAdvanceLimitAvailable(Double advanceLimitAvailable) {
		this.advanceLimitAvailable = advanceLimitAvailable;
	}
	
	@Override
	public String toString() {
		return "CreditCardLimitsAndTotalsDto [ownerData=" + ownerData + ", deadLineDate="+deadLineDate+", expirationDate="+expirationDate+", buyLimit="+buyLimit+", buyLimitAvailable="+buyLimitAvailable+
				", installmentsLimit="+installmentsLimit+ ", installmentsLimitAvailable="+installmentsLimitAvailable+", financingLimit="+financingLimit+", advanceLimit="+advanceLimit+", advanceLimitAvailable="+advanceLimitAvailable+
				", totalLocalCurrency="+totalLocalCurrency+", totalDollarCurrency="+totalDollarCurrency+", unifiedLimits="+unifiedLimits+"]";
	}

}