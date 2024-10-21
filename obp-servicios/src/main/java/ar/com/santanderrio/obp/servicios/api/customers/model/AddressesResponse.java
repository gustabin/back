package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddressesResponse {
    private List<Address> addresses = null;

    public AddressesResponse addAddressesItem(Address addressesItem) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<Address>();
        }
        this.addresses.add(addressesItem);
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressesResponse)) return false;

        AddressesResponse addresses1 = (AddressesResponse) o;
        return new EqualsBuilder()
                .append(addresses, addresses1.addresses)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(addresses)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Addresses{addresses=" + addresses + "}";
    }
}
