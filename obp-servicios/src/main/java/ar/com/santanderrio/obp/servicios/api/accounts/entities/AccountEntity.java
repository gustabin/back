package ar.com.santanderrio.obp.servicios.api.accounts.entities;

import java.util.List;

public class AccountEntity {

    private String accountId;

    private String accountNumber;

    private String branch;

    private String type;

    private String currency;

    private CbuDetailsEntity cbuDetails;

    private String status;

    private String category;

    private String description;

    private String customerId;

    private String signatureType;

    private String agreementNumber;

    private String openingBranch;

    private List<BalancesEntity> balances;

    private List<String> tags;

    private List<AccountDetailsEntity> accountDetails;

    private String creationDate;

    private String closingDate;

    private String participantQuality;

    private String cbu;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }

    public CbuDetailsEntity getCbuDetails() {
        return cbuDetails;
    }

    public void setCbuDetails(CbuDetailsEntity cbuDetails) {
        this.cbuDetails = cbuDetails;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(String signatureType) {
        this.signatureType = signatureType;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getOpeningBranch() {
        return openingBranch;
    }

    public void setOpeningBranch(String openingBranch) {
        this.openingBranch = openingBranch;
    }

    public List<BalancesEntity> getBalances() {
        return balances;
    }

    public void setBalances(List<BalancesEntity> balances) {
        this.balances = balances;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<AccountDetailsEntity> getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(List<AccountDetailsEntity> accountDetails) {
        this.accountDetails = accountDetails;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getParticipantQuality() {
        return participantQuality;
    }

    public void setParticipantQuality(String participantQuality) {
        this.participantQuality = participantQuality;
    }

    public String getCbu() { return cbu; }

    public void setCbu(String cbu) { this.cbu = cbu; }

}
