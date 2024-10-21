package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class PhonesResponse {
    private List<Phone> phones = null;

    public PhonesResponse addPhonesItem(Phone phonesItem) {
        if (this.phones == null) {
            this.phones = new ArrayList<Phone>();
        }
        this.phones.add(phonesItem);
        return this;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PhonesResponse)) return false;

        PhonesResponse phones1 = (PhonesResponse) o;

        return new EqualsBuilder()
                .append(phones, phones1.phones)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(phones)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Phones{" +
                "phones=" + phones +
                '}';
    }
}
