package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits;


import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.OwnerDataDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.UnifiedLimit;

import java.util.List;


public class LimitsAndTotalsDto {
	private OwnerDataDto ownerData;
	private UnifiedLimit unifiedLimits;
	private String statementClosingDate;
	private String paymentDueDate;
	// Limits
	private List<CurrencyValueDto> currentBalance;
	private CurrencyValueDto buyLimit;
	private CurrencyValueDto buyAvailableLimit;
	private CurrencyValueDto installmentsLimit;
	private CurrencyValueDto installmentsAvailableLimit;
	private CurrencyValueDto financialLimit;
	private CurrencyValueDto advanceLimit;
	private CurrencyValueDto advanceAvailableLimit;

	public OwnerDataDto getOwnerData() {
		return ownerData;
	}

	public void setOwnerData(OwnerDataDto ownerData) {
		this.ownerData = ownerData;
	}

	public UnifiedLimit getUnifiedLimits() {
		return unifiedLimits;
	}

	public void setUnifiedLimits(UnifiedLimit unifiedLimits) {
		this.unifiedLimits = unifiedLimits;
	}

	public String getStatementClosingDate() {
		return statementClosingDate;
	}

	public void setStatementClosingDate(String statementClosingDate) {
		this.statementClosingDate = statementClosingDate;
	}

	public String getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(String paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public List<CurrencyValueDto> getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(List<CurrencyValueDto> currentBalance) {
		this.currentBalance = currentBalance;
	}

	public CurrencyValueDto getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(CurrencyValueDto buyLimit) {
		this.buyLimit = buyLimit;
	}

	public CurrencyValueDto getBuyAvailableLimit() {
		return buyAvailableLimit;
	}

	public void setBuyAvailableLimit(CurrencyValueDto buyAvailableLimit) {
		this.buyAvailableLimit = buyAvailableLimit;
	}

	public CurrencyValueDto getInstallmentsLimit() {
		return installmentsLimit;
	}

	public void setInstallmentsLimit(CurrencyValueDto installmentsLimit) {
		this.installmentsLimit = installmentsLimit;
	}

	public CurrencyValueDto getInstallmentsAvailableLimit() {
		return installmentsAvailableLimit;
	}

	public void setInstallmentsAvailableLimit(CurrencyValueDto installmentsAvailableLimit) {
		this.installmentsAvailableLimit = installmentsAvailableLimit;
	}

	public CurrencyValueDto getFinancialLimit() {
		return financialLimit;
	}

	public void setFinancialLimit(CurrencyValueDto financialLimit) {
		this.financialLimit = financialLimit;
	}

	public CurrencyValueDto getAdvanceLimit() {
		return advanceLimit;
	}

	public void setAdvanceLimit(CurrencyValueDto advanceLimit) {
		this.advanceLimit = advanceLimit;
	}

	public CurrencyValueDto getAdvanceAvailableLimit() {
		return advanceAvailableLimit;
	}

	public void setAdvanceAvailableLimit(CurrencyValueDto advanceAvailableLimit) {
		this.advanceAvailableLimit = advanceAvailableLimit;
	}
}
