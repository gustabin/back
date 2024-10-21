package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PhoneBasic {
    private String areaCode;
    private String number;

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

    //Chained Setter Methods
    public PhoneBasic areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public PhoneBasic number(String number) {
        this.number = number;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneBasic)) return false;

        PhoneBasic that = (PhoneBasic) o;
        return new EqualsBuilder()
                .append(areaCode, that.areaCode)
                .append(number, that.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(areaCode)
                .append(number)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "PhoneBasic{" +
                "areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
