package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Date;

public class LegalPerson {
    private String fantasyName;
    private String businessName;
    private String residenceCountry;
    private Date endDate;
    private Date startDate;
    private BigDecimal employeeQuantity;

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

    public String getResidenceCountry() {
        return residenceCountry;
    }

    public void setResidenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getEmployeeQuantity() {
        return employeeQuantity;
    }

    public void setEmployeeQuantity(BigDecimal employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
    }

    //Chained Setter methods
    public LegalPerson fantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
        return this;
    }

    public LegalPerson businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public LegalPerson residenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
        return this;
    }

    public LegalPerson endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public LegalPerson startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public LegalPerson employeeQuantity(BigDecimal employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalPerson)) return false;

        LegalPerson that = (LegalPerson) o;
        return new EqualsBuilder()
                .append(fantasyName, that.fantasyName)
                .append(businessName, that.businessName)
                .append(residenceCountry, that.residenceCountry)
                .append(endDate, that.endDate)
                .append(startDate, that.startDate)
                .append(employeeQuantity, that.employeeQuantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fantasyName)
                .append(businessName)
                .append(residenceCountry)
                .append(endDate)
                .append(startDate)
                .append(employeeQuantity)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "fantasyName='" + fantasyName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", residenceCountry='" + residenceCountry + '\'' +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                ", employeeQuantity=" + employeeQuantity +
                '}';
    }
}
