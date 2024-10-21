package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

import java.util.List;

public class HoldingsData {

    private String custodyAccount;

    private Boolean isBp;

    private String altairProduct;

    private String operativeAccount;

    private Integer branch;

    private String valueDate;

    private List<Holdings> holdings;

    private List<HoldingsTotal> holdingsTotalAmount;

    public String getCustodyAccount() { return custodyAccount; }

    public void setCustodyAccount(String value) { this.custodyAccount = value; }

    public Boolean getIsBp() {
        return isBp;
    }

    public void setBp(Boolean value) {
        isBp = value;
    }

    public String getAltairProduct() {
        return altairProduct;
    }

    public void setAltairProduct(String altairProduct) {
        this.altairProduct = altairProduct;
    }

    public String getOperativeAccount() {
        return operativeAccount;
    }

    public void setOperativeAccount(String operativeAccount) {
        this.operativeAccount = operativeAccount;
    }

    public Integer getBranch() { return branch; }

    public void setBranch(Integer value) { this.branch = value; }

    public String getValueDate() { return valueDate; }

    public void setValueDate(String value) { this.valueDate = value; }

    public List<Holdings> getHoldings() { return holdings; }

    public void setHoldings(List<Holdings> value) { this.holdings = value; }

    public List<HoldingsTotal> getHoldingsTotalAmount() { return holdingsTotalAmount; }

    public void setHoldingsTotalAmount(List<HoldingsTotal> value) { this.holdingsTotalAmount = value; }

}
