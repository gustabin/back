package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class GuarantyAccount {
    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("account_branch")
    private String accountBranch;

    @JsonProperty("account_number")
    private String accountNumber;

    public GuarantyAccount() {}

    public GuarantyAccount(Builder builder) {
        this.accountType = builder.accountType;
        this.accountBranch = builder.accountBranch;
        this.accountNumber = builder.accountNumber;
    }

    private static class Builder {
        private String accountType;
        private String accountBranch;
        private String accountNumber;

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder accountBranch(String accountBranch) {
            this.accountBranch = accountBranch;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public GuarantyAccount build() {
            return new GuarantyAccount(this);
        }
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuarantyAccount that = (GuarantyAccount) o;

        return new EqualsBuilder()
                .append(accountType, that.accountType)
                .append(accountBranch, that.accountBranch)
                .append(accountNumber, that.accountNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(accountType)
                .append(accountBranch)
                .append(accountNumber)
                .toHashCode();
    }
}
