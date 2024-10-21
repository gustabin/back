package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class ControlUnit {
    private String legacyBranchCode;
    private String banking;
    private String division;
    private String teamLeader;
    private String accountExecutive;
    private String areaBoss;
    private String manager;
    private Date updatedAt;

    public String getLegacyBranchCode() {
        return legacyBranchCode;
    }

    public void setLegacyBranchCode(String legacyBranchCode) {
        this.legacyBranchCode = legacyBranchCode;
    }

    public String getBanking() {
        return banking;
    }

    public void setBanking(String banking) {
        this.banking = banking;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getAccountExecutive() {
        return accountExecutive;
    }

    public void setAccountExecutive(String accountExecutive) {
        this.accountExecutive = accountExecutive;
    }

    public String getAreaBoss() {
        return areaBoss;
    }

    public void setAreaBoss(String areaBoss) {
        this.areaBoss = areaBoss;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    //Chained Setters methods
    public ControlUnit legacyBranchCode(String legacyBranchCode) {
        this.legacyBranchCode = legacyBranchCode;
        return this;
    }

    public ControlUnit banking(String banking) {
        this.banking = banking;
        return this;
    }

    public ControlUnit division(String division) {
        this.division = division;
        return this;
    }

    public ControlUnit teamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
        return this;
    }

    public ControlUnit accountExecutive(String accountExecutive) {
        this.accountExecutive = accountExecutive;
        return this;
    }

    public ControlUnit areaBoss(String areaBoss) {
        this.areaBoss = areaBoss;
        return this;
    }

    public ControlUnit manager(String manager) {
        this.manager = manager;
        return this;
    }

    public ControlUnit updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ControlUnit)) return false;

        ControlUnit that = (ControlUnit) o;
        return new EqualsBuilder()
                .append(legacyBranchCode, that.legacyBranchCode)
                .append(banking, that.banking)
                .append(division, that.division)
                .append(teamLeader, that.teamLeader)
                .append(accountExecutive, that.accountExecutive)
                .append(areaBoss, that.areaBoss)
                .append(manager, that.manager)
                .append(updatedAt, that.updatedAt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(legacyBranchCode)
                .append(banking)
                .append(division)
                .append(teamLeader)
                .append(accountExecutive)
                .append(areaBoss)
                .append(manager)
                .append(updatedAt)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ControlUnit{" +
                "legacyBranchCode='" + legacyBranchCode + '\'' +
                ", banking='" + banking + '\'' +
                ", division='" + division + '\'' +
                ", teamLeader='" + teamLeader + '\'' +
                ", accountExecutive='" + accountExecutive + '\'' +
                ", areaBoss='" + areaBoss + '\'' +
                ", manager='" + manager + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
