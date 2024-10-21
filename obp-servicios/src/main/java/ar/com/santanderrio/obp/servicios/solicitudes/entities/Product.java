package ar.com.santanderrio.obp.servicios.solicitudes.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class Product {
    @JsonProperty("accountId")
    public String accountId;
    @JsonProperty("accountNumber")
    public String accountNumber;
    @JsonProperty("branch")
    public String branch;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) { this.branch = branch; }
}
