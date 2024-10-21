package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomersSearch {
    private List<CustomerSearch> customers = null;

    public CustomersSearch addCustomersItem(CustomerSearch customersItem) {
        if (this.customers == null) {
            this.customers = new ArrayList<CustomerSearch>();
        }
        this.customers.add(customersItem);
        return this;
    }

    public List<CustomerSearch> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSearch> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomersSearch)) return false;

        CustomersSearch that = (CustomersSearch) o;
        return new EqualsBuilder()
                .append(customers, that.customers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customers)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CustomersSearch{" +
                "customers=" + customers +
                '}';
    }
}
