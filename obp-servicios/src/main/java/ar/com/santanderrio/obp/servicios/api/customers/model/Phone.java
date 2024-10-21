package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Phone {
    private Boolean isActive;
    private TelephoneTypeEnum type;
    private TelephonePropertyClassEnum propertyClass;
    private String areaCode;
    private String number;
    private String internal;
    private TelephoneCompanyEnum company;
    private String source;
    private Date updatedAt;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public TelephoneTypeEnum getType() {
        return type;
    }

    public void setType(TelephoneTypeEnum type) {
        this.type = type;
    }

    public TelephonePropertyClassEnum getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(TelephonePropertyClassEnum propertyClass) {
        this.propertyClass = propertyClass;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public TelephoneCompanyEnum getCompany() {
        return company;
    }

    public void setCompany(TelephoneCompanyEnum company) {
        this.company = company;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    //Chained Setter Methods
    public Phone isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Phone type(TelephoneTypeEnum type) {
        this.type = type;
        return this;
    }

    public Phone propertyClass(TelephonePropertyClassEnum propertyClass) {
        this.propertyClass = propertyClass;
        return this;
    }

    public Phone areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Phone number(String number) {
        this.number = number;
        return this;
    }

    public Phone internal(String internal) {
        this.internal = internal;
        return this;
    }

    public Phone company(TelephoneCompanyEnum company) {
        this.company = company;
        return this;
    }

    public Phone source(String source) {
        this.source = source;
        return this;
    }

    public Phone updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Phone)) return false;

        Phone phone = (Phone) o;

        return new EqualsBuilder()
                .append(isActive, phone.isActive)
                .append(type, phone.type)
                .append(propertyClass, phone.propertyClass)
                .append(areaCode, phone.areaCode)
                .append(number, phone.number)
                .append(internal, phone.internal)
                .append(company, phone.company)
                .append(source, phone.source)
                .append(updatedAt, phone.updatedAt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isActive)
                .append(type)
                .append(propertyClass)
                .append(areaCode)
                .append(number)
                .append(internal)
                .append(company)
                .append(source)
                .append(updatedAt)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Phone{" +
                "isActive=" + isActive +
                ", type=" + type +
                ", propertyClass=" + propertyClass +
                ", areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                ", internal='" + internal + '\'' +
                ", company=" + company +
                ", source='" + source + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
