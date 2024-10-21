package ar.com.santanderrio.obp.servicios.api.customers.model;

public class LegalPersonEconomicData   {
  private Float netWorth;
  private Float annualInvoicing;
  private String currencyCode;

  public Float getNetWorth() {
    return netWorth;
  }

  public void setNetWorth(Float netWorth) {
    this.netWorth = netWorth;
  }

  public Float getAnnualInvoicing() {
    return annualInvoicing;
  }

  public void setAnnualInvoicing(Float annualInvoicing) {
    this.annualInvoicing = annualInvoicing;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }


  //Chained Setters Methods
  public LegalPersonEconomicData netWorth(Float netWorth) {
    this.netWorth = netWorth;
    return this;
  }

  public LegalPersonEconomicData annualInvoicing(Float annualInvoicing) {
    this.annualInvoicing = annualInvoicing;
    return this;
  }

  public LegalPersonEconomicData currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

}
