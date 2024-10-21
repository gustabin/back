package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CustomerSearch {
    private String id;
    private CustomerConditionEnum condition;
    private HumanPersonSearch humanPerson;
    private LegalPersonSearch legalPerson;

    //Chained setters methods
    public CustomerSearch id(String id) {
        this.id = id;
        return this;
    }

    public CustomerSearch condition(CustomerConditionEnum condition) {
        this.condition = condition;
        return this;
    }

    public CustomerSearch humanPerson(HumanPersonSearch humanPerson) {
        this.humanPerson = humanPerson;
        return this;
    }

    public CustomerSearch legalPerson(LegalPersonSearch legalPerson) {
        this.legalPerson = legalPerson;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(CustomerConditionEnum condition) {
        this.condition = condition;
    }

    public HumanPersonSearch getHumanPerson() {
        return humanPerson;
    }

    public void setHumanPerson(HumanPersonSearch humanPerson) {
        this.humanPerson = humanPerson;
    }

    public LegalPersonSearch getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPersonSearch legalPerson) {
        this.legalPerson = legalPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CustomerSearch)) return false;

        CustomerSearch that = (CustomerSearch) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(condition, that.condition)
                .append(humanPerson, that.humanPerson)
                .append(legalPerson, that.legalPerson)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(condition)
                .append(humanPerson)
                .append(legalPerson)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CustomerSearch{" +
                "id='" + id + '\'' +
                ", condition=" + condition +
                ", humanPerson=" + humanPerson +
                ", legalPerson=" + legalPerson +
                '}';
    }
}
