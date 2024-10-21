package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;

import java.util.List;

public class CreditCardSettlementDto {
	
	private String id;
    private String sessionId;
    private String activeCard;
    private String account;
    private String currentDeadline;
    private String currentExpirationDate;
    private String nextDeadline;
    private String nextExpirationDate;
    private String previousDeadline;
    private String previousExpirationDate;
    private String minimumPayment;
	private String minimumDollarPayment;
    private String balanceLocalCurrency;
    private String balanceDollarCurrency;
    private String financingLimit;
    private String buyLimit;
    private String feeBuyLimit;
    private String advanceLimit;
    private String yearNominalRateLocalCurrency;
    private String yearNominalRateDollarCurrency;
    private String monthEffectiveRateLocalCurrency;
    private String monthEffectiveRateDollarCurrency;
    private List<String> transactions;
	private Currency currency;
    
	public void setId(String id) {
		this.id = id;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void setActiveCard(String activeCard) {
		this.activeCard = activeCard;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setCurrentDeadline(String currentDeadline) {
		this.currentDeadline = currentDeadline;
	}
	public void setCurrentExpirationDate(String currentExpirationDate) {
		this.currentExpirationDate = currentExpirationDate;
	}
	public void setNextDeadline(String nextDeadline) {
		this.nextDeadline = nextDeadline;
	}
	public void setNextExpirationDate(String nextExpirationDate) {
		this.nextExpirationDate = nextExpirationDate;
	}
	public void setPreviousDeadline(String previousDeadline) {
		this.previousDeadline = previousDeadline;
	}
	public void setPreviousExpirationDate(String previousExpirationDate) {
		this.previousExpirationDate = previousExpirationDate;
	}
	public void setMinimumPayment(String minimumPayment) {
		this.minimumPayment = minimumPayment;
	}
	public void setBalanceLocalCurrency(String balanceLocalCurrency) {
		this.balanceLocalCurrency = balanceLocalCurrency;
	}
	public void setBalanceDollarCurrency(String balanceDollarCurrency) {
		this.balanceDollarCurrency = balanceDollarCurrency;
	}
	public void setFinancingLimit(String financingLimit) {
		this.financingLimit = financingLimit;
	}
	public void setBuyLimit(String buyLimit) {
		this.buyLimit = buyLimit;
	}
	public void setFeeBuyLimit(String feeBuyLimit) {
		this.feeBuyLimit = feeBuyLimit;
	}
	public void setAdvanceLimit(String advanceLimit) {
		this.advanceLimit = advanceLimit;
	}
	public void setYearNominalRateLocalCurrency(String yearNominalRateLocalCurrency) {
		this.yearNominalRateLocalCurrency = yearNominalRateLocalCurrency;
	}
	public void setYearNominalRateDollarCurrency(String yearNominalRateDollarCurrency) {
		this.yearNominalRateDollarCurrency = yearNominalRateDollarCurrency;
	}
	public void setMonthEffectiveRateLocalCurrency(String monthEffectiveRateLocalCurrency) {
		this.monthEffectiveRateLocalCurrency = monthEffectiveRateLocalCurrency;
	}
	public void setMonthEffectiveRateDollarCurrency(String monthEffectiveRateDollarCurrency) {
		this.monthEffectiveRateDollarCurrency = monthEffectiveRateDollarCurrency;
	}
	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public String getId() {
		return id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public String getActiveCard() {
		return activeCard;
	}
	public String getAccount() {
		return account;
	}
	public String getCurrentDeadline() {
		return currentDeadline;
	}
	public String getCurrentExpirationDate() {
		return currentExpirationDate;
	}
	public String getNextDeadline() {
		return nextDeadline;
	}
	public String getNextExpirationDate() {
		return nextExpirationDate;
	}
	public String getPreviousDeadline() {
		return previousDeadline;
	}
	public String getPreviousExpirationDate() {
		return previousExpirationDate;
	}
	public String getMinimumPayment() {
		return minimumPayment;
	}
	public String getMinimumDollarPayment() {
		return minimumDollarPayment;
	}
	public String getBalanceLocalCurrency() {
		return balanceLocalCurrency;
	}
	public String getBalanceDollarCurrency() {
		return balanceDollarCurrency;
	}
	public String getFinancingLimit() {
		return financingLimit;
	}
	public String getBuyLimit() {
		return buyLimit;
	}
	public String getFeeBuyLimit() {
		return feeBuyLimit;
	}
	public String getAdvanceLimit() {
		return advanceLimit;
	}
	public String getYearNominalRateLocalCurrency() {
		return yearNominalRateLocalCurrency;
	}
	public String getYearNominalRateDollarCurrency() {
		return yearNominalRateDollarCurrency;
	}
	public String getMonthEffectiveRateLocalCurrency() {
		return monthEffectiveRateLocalCurrency;
	}
	public String getMonthEffectiveRateDollarCurrency() {
		return monthEffectiveRateDollarCurrency;
	}
	public List<String> getTransactions() {
		return transactions;
	}
	public Currency getCurrency() {
		return currency;
	}
}
