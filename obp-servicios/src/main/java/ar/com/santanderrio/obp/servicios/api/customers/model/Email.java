package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Email {
    private Boolean isActive;
    private String address;
    private EmailSourceEnum source;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmailSourceEnum getSource() {
        return source;
    }

    public void setSource(EmailSourceEnum source) {
        this.source = source;
    }

    //Chained Setters methods
    public Email source(EmailSourceEnum source) {
        this.source = source;
        return this;
    }

    public Email isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Email address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;

        Email email = (Email) o;
        return new EqualsBuilder()
                .append(isActive, email.isActive)
                .append(address, email.address)
                .append(source, email.source)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isActive)
                .append(address)
                .append(source)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Email{" +
                "isActive=" + isActive +
                ", address='" + address + '\'' +
                ", source=" + source +
                '}';
    }
}
