package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Account {

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("account_branch")
    private String accountBranch;

    @JsonProperty("account_number")
    private String accountNumber;

    public Account(){}

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountBranch() {
        return accountBranch;
    }

    public void setAccountBranch(String accountBranch) {
        this.accountBranch = accountBranch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
