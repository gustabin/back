package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

public class SimulateRedemptionRequestEntity {
    private String nup;
    private String branch;
    private String investmentAccount;
    private String operativeAccount;
    private String  operativeAccountPrefix;
    private String operativeAccountType;
    private String fundCode;
    private String currency;
    private Double value;

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(String investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public String getOperativeAccount() {
        return operativeAccount;
    }

    public void setOperativeAccount(String operativeAccount) {
        this.operativeAccount = operativeAccount;
    }

    public String getOperativeAccountPrefix() {
        return operativeAccountPrefix;
    }

    public void setOperativeAccountPrefix(String operativeAccountPrefix) {
        this.operativeAccountPrefix = operativeAccountPrefix;
    }

    public String getOperativeAccountType() {
        return operativeAccountType;
    }

    public void setOperativeAccountType(String operativeAccountType) {
        this.operativeAccountType = operativeAccountType;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
