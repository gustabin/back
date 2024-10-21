package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AdditionalData {
    private ControlUnit controlUnit;

    public AdditionalData controlUnit(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        return this;
    }

    public ControlUnit getControlUnit() {
        return controlUnit;
    }

    public void setControlUnit(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalData)) return false;

        AdditionalData that = (AdditionalData) o;
        return new EqualsBuilder()
                .append(controlUnit, that.controlUnit)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(controlUnit)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "AdditionalData{controlUnit=" + controlUnit + "}";
    }
}
