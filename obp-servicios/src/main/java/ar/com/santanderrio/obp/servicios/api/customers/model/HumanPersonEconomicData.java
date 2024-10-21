package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class HumanPersonEconomicData {
    private List<HumanPersonEconomic> economicData = null;

    public HumanPersonEconomicData addEconomicDataItem(HumanPersonEconomic economicDataItem) {
        if (this.economicData == null) {
            this.economicData = new ArrayList<HumanPersonEconomic>();
        }
        this.economicData.add(economicDataItem);
        return this;
    }

    public List<HumanPersonEconomic> getEconomicData() {
        return economicData;
    }

    public void setEconomicData(List<HumanPersonEconomic> economicData) {
        this.economicData = economicData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HumanPersonEconomicData)) return false;

        HumanPersonEconomicData that = (HumanPersonEconomicData) o;
        return new EqualsBuilder()
                .append(economicData, that.economicData)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(economicData)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "HumanPersonEconomicData{" +
                "economicData=" + economicData +
                '}';
    }
}
