package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomerEconomicDataResponse {
    private List<EconomicData> customerEconomicData = null;

    public CustomerEconomicDataResponse addCustomerEconomicDataItem(EconomicData customerEconomicDataItem) {
        if (this.customerEconomicData == null) {
            this.customerEconomicData = new ArrayList<EconomicData>();
        }
        this.customerEconomicData.add(customerEconomicDataItem);
        return this;
    }

    public List<EconomicData> getCustomerEconomicData() {
        return customerEconomicData;
    }

    public void setCustomerEconomicData(List<EconomicData> customerEconomicData) {
        this.customerEconomicData = customerEconomicData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEconomicDataResponse)) return false;

        CustomerEconomicDataResponse that = (CustomerEconomicDataResponse) o;
        return new EqualsBuilder()
                .append(customerEconomicData, that.customerEconomicData)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customerEconomicData)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CustomerEconomicData{" +
                "customerEconomicData=" + customerEconomicData +
                '}';
    }
}
