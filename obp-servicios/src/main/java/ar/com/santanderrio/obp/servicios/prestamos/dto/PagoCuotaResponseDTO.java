package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PagoCuotaResponseDTO {
    private String extendedReturnCode;
    private BigDecimal debitAmount;
    private BigDecimal loanCotization;
    private String receiptNumber;
    private String receiptExpiration;
    private BigDecimal totalAmountReceipt;
    private String currencyCode;
    private BigDecimal loanRate;
    private BigDecimal amortizationAmount;
    private BigDecimal interestAmount;
    private BigDecimal otherTaxesAmount;
    private BigDecimal lifeInsuranceAmount;
    private BigDecimal punitiveAmount;
    private BigDecimal complementaryAmount;
    private BigDecimal iva;
    private BigDecimal ivaAdditional;
    private BigDecimal insuranceOfGoodAmount;
    private BigDecimal expenses;
    private BigDecimal commission;
    private BigDecimal insuranceTotalAmount;
    private BigDecimal capitalBalance;
    private BigDecimal financialDebtTax;
    private BigDecimal grossIncome;
    private String operationIdentificationNumber;
    private String ivaCode;
    private String ivaDescription;
    private String legend1;
    private String legend2;
    private BigDecimal tea;
    private BigDecimal previousBalance;
    private BigDecimal balanceAdjustmentAmount;
    private BigDecimal indexingFactor;
    private BigDecimal capitalAdjustmentDebt;
    private BigDecimal capitalAdjustmentAmount;
    private BigDecimal capitalAdjustmentDebtFactor;
    private String indexationCoefficient;
    private BigDecimal payCompanyLifeInsurance;
    private BigDecimal cftna;
    private BigDecimal totalTaxAmount;
    private BigDecimal externalCollectionAmount;
    private BigDecimal cftnaWithoutTax;
    private String cccUE;
    private String settlementDateUE;
    private String receiptNumber2;
    private String expositionUnitUE;
    private BigDecimal expositionUnitCotizationUE;
    private String cotizationDateUE;
    private BigDecimal capitalAmountUE;
    private BigDecimal interestAmountUE;
    private BigDecimal commissionAmountUE;
    private BigDecimal expensesAmountUE;
    private BigDecimal insuranceAmountUE;
    private BigDecimal taxAmountUE;
    private BigDecimal cobexAmountUE;
    private BigDecimal defaultAmountUE;
    private BigDecimal compensatoryAmountUE;
    private BigDecimal lifeInsuranceUE;
    private BigDecimal insuranceOfGoodUE;
    private BigDecimal ivaUE;
    private BigDecimal ivaAdditionalUE;
    private BigDecimal debtTaxUE;
    private BigDecimal grossIncomeUE;
    private BigDecimal taxWithoutIvaUE;
    private BigDecimal totalAmountUE;
    private BigDecimal previousBalanceUE;
    private BigDecimal balanceAdjustmentUE;
    private BigDecimal capitalAdjustmentUE;
    private BigDecimal capitalAdjustmentDebtUE;
    private BigDecimal payCompanyAmountUE;

    public PagoCuotaResponseDTO() {
    }

    public String getExtendedReturnCode() {
        return extendedReturnCode;
    }

    public void setExtendedReturnCode(String extendedReturnCode) {
        this.extendedReturnCode = extendedReturnCode;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getLoanCotization() {
        return loanCotization;
    }

    public void setLoanCotization(BigDecimal loanCotization) {
        this.loanCotization = loanCotization;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptExpiration() {
        return receiptExpiration;
    }

    public void setReceiptExpiration(String receiptExpiration) {
        this.receiptExpiration = receiptExpiration;
    }

    public BigDecimal getTotalAmountReceipt() {
        return totalAmountReceipt;
    }

    public void setTotalAmountReceipt(BigDecimal totalAmountReceipt) {
        this.totalAmountReceipt = totalAmountReceipt;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getAmortizationAmount() {
        return amortizationAmount;
    }

    public void setAmortizationAmount(BigDecimal amortizationAmount) {
        this.amortizationAmount = amortizationAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getOtherTaxesAmount() {
        return otherTaxesAmount;
    }

    public void setOtherTaxesAmount(BigDecimal otherTaxesAmount) {
        this.otherTaxesAmount = otherTaxesAmount;
    }

    public BigDecimal getLifeInsuranceAmount() {
        return lifeInsuranceAmount;
    }

    public void setLifeInsuranceAmount(BigDecimal lifeInsuranceAmount) {
        this.lifeInsuranceAmount = lifeInsuranceAmount;
    }

    public BigDecimal getPunitiveAmount() {
        return punitiveAmount;
    }

    public void setPunitiveAmount(BigDecimal punitiveAmount) {
        this.punitiveAmount = punitiveAmount;
    }

    public BigDecimal getComplementaryAmount() {
        return complementaryAmount;
    }

    public void setComplementaryAmount(BigDecimal complementaryAmount) {
        this.complementaryAmount = complementaryAmount;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getIvaAdditional() {
        return ivaAdditional;
    }

    public void setIvaAdditional(BigDecimal ivaAdditional) {
        this.ivaAdditional = ivaAdditional;
    }

    public BigDecimal getInsuranceOfGoodAmount() {
        return insuranceOfGoodAmount;
    }

    public void setInsuranceOfGoodAmount(BigDecimal insuranceOfGoodAmount) {
        this.insuranceOfGoodAmount = insuranceOfGoodAmount;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getInsuranceTotalAmount() {
        return insuranceTotalAmount;
    }

    public void setInsuranceTotalAmount(BigDecimal insuranceTotalAmount) {
        this.insuranceTotalAmount = insuranceTotalAmount;
    }

    public BigDecimal getCapitalBalance() {
        return capitalBalance;
    }

    public void setCapitalBalance(BigDecimal capitalBalance) {
        this.capitalBalance = capitalBalance;
    }

    public BigDecimal getFinancialDebtTax() {
        return financialDebtTax;
    }

    public void setFinancialDebtTax(BigDecimal financialDebtTax) {
        this.financialDebtTax = financialDebtTax;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public String getOperationIdentificationNumber() {
        return operationIdentificationNumber;
    }

    public void setOperationIdentificationNumber(String operationIdentificationNumber) {
        this.operationIdentificationNumber = operationIdentificationNumber;
    }

    public String getIvaCode() {
        return ivaCode;
    }

    public void setIvaCode(String ivaCode) {
        this.ivaCode = ivaCode;
    }

    public String getIvaDescription() {
        return ivaDescription;
    }

    public void setIvaDescription(String ivaDescription) {
        this.ivaDescription = ivaDescription;
    }

    public String getLegend1() {
        return legend1;
    }

    public void setLegend1(String legend1) {
        this.legend1 = legend1;
    }

    public String getLegend2() {
        return legend2;
    }

    public void setLegend2(String legend2) {
        this.legend2 = legend2;
    }

    public BigDecimal getTea() {
        return tea;
    }

    public void setTea(BigDecimal tea) {
        this.tea = tea;
    }

    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(BigDecimal previousBalance) {
        this.previousBalance = previousBalance;
    }

    public BigDecimal getBalanceAdjustmentAmount() {
        return balanceAdjustmentAmount;
    }

    public void setBalanceAdjustmentAmount(BigDecimal balanceAdjustmentAmount) {
        this.balanceAdjustmentAmount = balanceAdjustmentAmount;
    }

    public BigDecimal getIndexingFactor() {
        return indexingFactor;
    }

    public void setIndexingFactor(BigDecimal indexingFactor) {
        this.indexingFactor = indexingFactor;
    }

    public BigDecimal getCapitalAdjustmentDebt() {
        return capitalAdjustmentDebt;
    }

    public void setCapitalAdjustmentDebt(BigDecimal capitalAdjustmentDebt) {
        this.capitalAdjustmentDebt = capitalAdjustmentDebt;
    }

    public BigDecimal getCapitalAdjustmentAmount() {
        return capitalAdjustmentAmount;
    }

    public void setCapitalAdjustmentAmount(BigDecimal capitalAdjustmentAmount) {
        this.capitalAdjustmentAmount = capitalAdjustmentAmount;
    }

    public BigDecimal getCapitalAdjustmentDebtFactor() {
        return capitalAdjustmentDebtFactor;
    }

    public void setCapitalAdjustmentDebtFactor(BigDecimal capitalAdjustmentDebtFactor) {
        this.capitalAdjustmentDebtFactor = capitalAdjustmentDebtFactor;
    }

    public String getIndexationCoefficient() {
        return indexationCoefficient;
    }

    public void setIndexationCoefficient(String indexationCoefficient) {
        this.indexationCoefficient = indexationCoefficient;
    }

    public BigDecimal getPayCompanyLifeInsurance() {
        return payCompanyLifeInsurance;
    }

    public void setPayCompanyLifeInsurance(BigDecimal payCompanyLifeInsurance) {
        this.payCompanyLifeInsurance = payCompanyLifeInsurance;
    }

    public BigDecimal getCftna() {
        return cftna;
    }

    public void setCftna(BigDecimal cftna) {
        this.cftna = cftna;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getExternalCollectionAmount() {
        return externalCollectionAmount;
    }

    public void setExternalCollectionAmount(BigDecimal externalCollectionAmount) {
        this.externalCollectionAmount = externalCollectionAmount;
    }

    public BigDecimal getCftnaWithoutTax() {
        return cftnaWithoutTax;
    }

    public void setCftnaWithoutTax(BigDecimal cftnaWithoutTax) {
        this.cftnaWithoutTax = cftnaWithoutTax;
    }

    public String getCccUE() {
        return cccUE;
    }

    public void setCccUE(String cccUE) {
        this.cccUE = cccUE;
    }

    public String getSettlementDateUE() {
        return settlementDateUE;
    }

    public void setSettlementDateUE(String settlementDateUE) {
        this.settlementDateUE = settlementDateUE;
    }

    public String getReceiptNumber2() {
        return receiptNumber2;
    }

    public void setReceiptNumber2(String receiptNumber2) {
        this.receiptNumber2 = receiptNumber2;
    }

    public String getExpositionUnitUE() {
        return expositionUnitUE;
    }

    public void setExpositionUnitUE(String expositionUnitUE) {
        this.expositionUnitUE = expositionUnitUE;
    }

    public BigDecimal getExpositionUnitCotizationUE() {
        return expositionUnitCotizationUE;
    }

    public void setExpositionUnitCotizationUE(BigDecimal expositionUnitCotizationUE) {
        this.expositionUnitCotizationUE = expositionUnitCotizationUE;
    }

    public String getCotizationDateUE() {
        return cotizationDateUE;
    }

    public void setCotizationDateUE(String cotizationDateUE) {
        this.cotizationDateUE = cotizationDateUE;
    }

    public BigDecimal getCapitalAmountUE() {
        return capitalAmountUE;
    }

    public void setCapitalAmountUE(BigDecimal capitalAmountUE) {
        this.capitalAmountUE = capitalAmountUE;
    }

    public BigDecimal getInterestAmountUE() {
        return interestAmountUE;
    }

    public void setInterestAmountUE(BigDecimal interestAmountUE) {
        this.interestAmountUE = interestAmountUE;
    }

    public BigDecimal getCommissionAmountUE() {
        return commissionAmountUE;
    }

    public void setCommissionAmountUE(BigDecimal commissionAmountUE) {
        this.commissionAmountUE = commissionAmountUE;
    }

    public BigDecimal getExpensesAmountUE() {
        return expensesAmountUE;
    }

    public void setExpensesAmountUE(BigDecimal expensesAmountUE) {
        this.expensesAmountUE = expensesAmountUE;
    }

    public BigDecimal getInsuranceAmountUE() {
        return insuranceAmountUE;
    }

    public void setInsuranceAmountUE(BigDecimal insuranceAmountUE) {
        this.insuranceAmountUE = insuranceAmountUE;
    }

    public BigDecimal getTaxAmountUE() {
        return taxAmountUE;
    }

    public void setTaxAmountUE(BigDecimal taxAmountUE) {
        this.taxAmountUE = taxAmountUE;
    }

    public BigDecimal getCobexAmountUE() {
        return cobexAmountUE;
    }

    public void setCobexAmountUE(BigDecimal cobexAmountUE) {
        this.cobexAmountUE = cobexAmountUE;
    }

    public BigDecimal getDefaultAmountUE() {
        return defaultAmountUE;
    }

    public void setDefaultAmountUE(BigDecimal defaultAmountUE) {
        this.defaultAmountUE = defaultAmountUE;
    }

    public BigDecimal getCompensatoryAmountUE() {
        return compensatoryAmountUE;
    }

    public void setCompensatoryAmountUE(BigDecimal compensatoryAmountUE) {
        this.compensatoryAmountUE = compensatoryAmountUE;
    }

    public BigDecimal getLifeInsuranceUE() {
        return lifeInsuranceUE;
    }

    public void setLifeInsuranceUE(BigDecimal lifeInsuranceUE) {
        this.lifeInsuranceUE = lifeInsuranceUE;
    }

    public BigDecimal getInsuranceOfGoodUE() {
        return insuranceOfGoodUE;
    }

    public void setInsuranceOfGoodUE(BigDecimal insuranceOfGoodUE) {
        this.insuranceOfGoodUE = insuranceOfGoodUE;
    }

    public BigDecimal getIvaUE() {
        return ivaUE;
    }

    public void setIvaUE(BigDecimal ivaUE) {
        this.ivaUE = ivaUE;
    }

    public BigDecimal getIvaAdditionalUE() {
        return ivaAdditionalUE;
    }

    public void setIvaAdditionalUE(BigDecimal ivaAdditionalUE) {
        this.ivaAdditionalUE = ivaAdditionalUE;
    }

    public BigDecimal getDebtTaxUE() {
        return debtTaxUE;
    }

    public void setDebtTaxUE(BigDecimal debtTaxUE) {
        this.debtTaxUE = debtTaxUE;
    }

    public BigDecimal getGrossIncomeUE() {
        return grossIncomeUE;
    }

    public void setGrossIncomeUE(BigDecimal grossIncomeUE) {
        this.grossIncomeUE = grossIncomeUE;
    }

    public BigDecimal getTaxWithoutIvaUE() {
        return taxWithoutIvaUE;
    }

    public void setTaxWithoutIvaUE(BigDecimal taxWithoutIvaUE) {
        this.taxWithoutIvaUE = taxWithoutIvaUE;
    }

    public BigDecimal getTotalAmountUE() {
        return totalAmountUE;
    }

    public void setTotalAmountUE(BigDecimal totalAmountUE) {
        this.totalAmountUE = totalAmountUE;
    }

    public BigDecimal getPreviousBalanceUE() {
        return previousBalanceUE;
    }

    public void setPreviousBalanceUE(BigDecimal previousBalanceUE) {
        this.previousBalanceUE = previousBalanceUE;
    }

    public BigDecimal getBalanceAdjustmentUE() {
        return balanceAdjustmentUE;
    }

    public void setBalanceAdjustmentUE(BigDecimal balanceAdjustmentUE) {
        this.balanceAdjustmentUE = balanceAdjustmentUE;
    }

    public BigDecimal getCapitalAdjustmentUE() {
        return capitalAdjustmentUE;
    }

    public void setCapitalAdjustmentUE(BigDecimal capitalAdjustmentUE) {
        this.capitalAdjustmentUE = capitalAdjustmentUE;
    }

    public BigDecimal getCapitalAdjustmentDebtUE() {
        return capitalAdjustmentDebtUE;
    }

    public void setCapitalAdjustmentDebtUE(BigDecimal capitalAdjustmentDebtUE) {
        this.capitalAdjustmentDebtUE = capitalAdjustmentDebtUE;
    }

    public BigDecimal getPayCompanyAmountUE() {
        return payCompanyAmountUE;
    }

    public void setPayCompanyAmountUE(BigDecimal payCompanyAmountUE) {
        this.payCompanyAmountUE = payCompanyAmountUE;
    }
}
