package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EconomicData {
    private CustomerTypeEnum type;
    private HumanPersonEconomicData humanPerson;
    private LegalPersonEconomicData legalPerson;

    public CustomerTypeEnum getType() {
        return type;
    }

    public void setType(CustomerTypeEnum type) {
        this.type = type;
    }

    public HumanPersonEconomicData getHumanPerson() {
        return humanPerson;
    }

    public void setHumanPerson(HumanPersonEconomicData humanPerson) {
        this.humanPerson = humanPerson;
    }

    public LegalPersonEconomicData getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPersonEconomicData legalPerson) {
        this.legalPerson = legalPerson;
    }

    //Chained Setters methods
    public EconomicData type(CustomerTypeEnum type) {
        this.type = type;
        return this;
    }

    public EconomicData humanPerson(HumanPersonEconomicData humanPerson) {
        this.humanPerson = humanPerson;
        return this;
    }

    public EconomicData legalPerson(LegalPersonEconomicData legalPerson) {
        this.legalPerson = legalPerson;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EconomicData)) return false;

        EconomicData that = (EconomicData) o;
        return new EqualsBuilder()
                .append(type, that.type)
                .append(humanPerson, that.humanPerson)
                .append(legalPerson, that.legalPerson)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(humanPerson)
                .append(legalPerson)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "EconomicData{" +
                "type=" + type +
                ", humanPerson=" + humanPerson +
                ", legalPerson=" + legalPerson +
                '}';
    }
}
