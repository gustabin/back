package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class HumanPersonEconomic {
    private FinancialIncomeTypeEnum financialIncomeType;
    private String currencyCode;
    private Float income;

    public FinancialIncomeTypeEnum getFinancialIncomeType() {
        return financialIncomeType;
    }

    public void setFinancialIncomeType(FinancialIncomeTypeEnum financialIncomeType) {
        this.financialIncomeType = financialIncomeType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }


    //Chained Setters Methods
    public HumanPersonEconomic financialIncomeType(FinancialIncomeTypeEnum financialIncomeType) {
        this.financialIncomeType = financialIncomeType;
        return this;
    }

    public HumanPersonEconomic currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public HumanPersonEconomic income(Float income) {
        this.income = income;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HumanPersonEconomic)) return false;

        HumanPersonEconomic that = (HumanPersonEconomic) o;
        return new EqualsBuilder()
                .append(financialIncomeType, that.financialIncomeType)
                .append(currencyCode, that.currencyCode)
                .append(income, that.income)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(financialIncomeType)
                .append(currencyCode)
                .append(income)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "HumanPersonEconomic{" +
                "financialIncomeType=" + financialIncomeType +
                ", currencyCode='" + currencyCode + '\'' +
                ", income=" + income +
                '}';
    }
}
