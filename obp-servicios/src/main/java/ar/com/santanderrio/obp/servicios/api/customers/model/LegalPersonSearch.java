package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class LegalPersonSearch {
    private String fantasyName;
    private String businessName;
    private Date startDate;

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    //Chained Setter methods
    public LegalPersonSearch fantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
        return this;
    }

    public LegalPersonSearch businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public LegalPersonSearch startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalPersonSearch)) return false;

        LegalPersonSearch that = (LegalPersonSearch) o;
        return new EqualsBuilder()
                .append(fantasyName, that.fantasyName)
                .append(businessName, that.businessName)
                .append(startDate, that.startDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fantasyName)
                .append(businessName)
                .append(startDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "LegalPersonSearch{" +
                "fantasyName='" + fantasyName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
