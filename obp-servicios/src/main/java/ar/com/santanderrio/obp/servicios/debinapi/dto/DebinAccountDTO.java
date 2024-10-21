package ar.com.santanderrio.obp.servicios.debinapi.dto;

import java.util.List;

public class DebinAccountDTO {
    private String bank;
    private String branch;
    private String terminal;
    private String alias;
    private String cbu;
    private Boolean owner;
    private List<String> owners;
    private CurrencyType currency;
    private String type;
    private String endpoint;

    public String getBank() {
        return bank;
    }

    public String getBranch() {
        return branch;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getAlias() {
        return alias;
    }

    public String getCbu() {
        return cbu;
    }

    public Boolean isOwner() {
        return owner;
    }

    public List<String> getOwners() {
        return owners;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
