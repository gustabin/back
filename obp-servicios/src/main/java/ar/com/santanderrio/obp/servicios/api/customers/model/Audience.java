package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Audience {
    private String indicator;
    private String indicatorValue;
    private String createdBy;
    private Date createdAt;
    private String modificationUser;
    private String modificationTerminal;
    private String modificationBranch;
    private Date updatedAt;

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
    }

    public String getModificationTerminal() {
        return modificationTerminal;
    }

    public void setModificationTerminal(String modificationTerminal) {
        this.modificationTerminal = modificationTerminal;
    }

    public String getModificationBranch() {
        return modificationBranch;
    }

    public void setModificationBranch(String modificationBranch) {
        this.modificationBranch = modificationBranch;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    //Chained Setters Methods
    public Audience indicator(String indicator) {
        this.indicator = indicator;
        return this;
    }

    public Audience indicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
        return this;
    }

    public Audience createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Audience createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Audience modificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
        return this;
    }

    public Audience modificationTerminal(String modificationTerminal) {
        this.modificationTerminal = modificationTerminal;
        return this;
    }

    public Audience modificationBranch(String modificationBranch) {
        this.modificationBranch = modificationBranch;
        return this;
    }

    public Audience updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audience)) return false;

        Audience audience = (Audience) o;
        return new EqualsBuilder()
                .append(indicator, audience.indicator)
                .append(indicatorValue, audience.indicatorValue)
                .append(createdBy, audience.createdBy)
                .append(createdAt, audience.createdAt)
                .append(modificationUser, audience.modificationUser)
                .append(modificationTerminal, audience.modificationTerminal)
                .append(modificationBranch, audience.modificationBranch)
                .append(updatedAt, audience.updatedAt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(indicator)
                .append(indicatorValue)
                .append(createdBy)
                .append(createdAt)
                .append(modificationUser)
                .append(modificationTerminal)
                .append(modificationBranch)
                .append(updatedAt)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Audience{" +
                "indicator='" + indicator + '\'' +
                ", indicatorValue='" + indicatorValue + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modificationUser='" + modificationUser + '\'' +
                ", modificationTerminal='" + modificationTerminal + '\'' +
                ", modificationBranch='" + modificationBranch + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
