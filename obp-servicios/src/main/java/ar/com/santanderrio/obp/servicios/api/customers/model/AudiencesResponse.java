package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class AudiencesResponse {
    private List<Audience> audiences = null;

    public AudiencesResponse addAudiencesItem(Audience audiencesItem) {
        if (this.audiences == null) {
            this.audiences = new ArrayList<Audience>();
        }
        this.audiences.add(audiencesItem);
        return this;
    }

    public List<Audience> getAudiences() {
        return audiences;
    }

    public void setAudiences(List<Audience> audiences) {
        this.audiences = audiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AudiencesResponse)) return false;

        AudiencesResponse audiences1 = (AudiencesResponse) o;

        return new EqualsBuilder()
                .append(audiences, audiences1.audiences)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(audiences)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Audiences{" +
                "audiences=" + audiences +
                '}';
    }
}
