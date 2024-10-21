package ar.com.santanderrio.obp.servicios.echeqapi.dto;

import ar.com.santanderrio.obp.servicios.echeqapi.model.Guaranty;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantyClient;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantySigner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class GuarantyOrderCreationRequest {
    @JsonProperty("requester")
    private GuarantyClient requester;

    @JsonProperty("signers")
    private List<GuarantySigner> signers;

    @JsonProperty("guaranties")
    private List<Guaranty> guaranties;

    public GuarantyOrderCreationRequest(Builder builder) {
        this.requester = builder.requester;
        this.signers = builder.signers;
        this.guaranties = builder.guaranties;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GuarantyClient requester;
        private List<GuarantySigner> signers;
        private List<Guaranty> guaranties;

        public Builder requester(GuarantyClient requester) {
            this.requester = requester;
            return this;
        }

        public Builder signers(List<GuarantySigner> endorsementSigners) {
            this.signers = endorsementSigners;
            return this;
        }

        public Builder guaranties(List<Guaranty> guaranties) {
            this.guaranties = guaranties;
            return this;
        }

        public GuarantyOrderCreationRequest build() {
            return new GuarantyOrderCreationRequest(this);
        }
    }

    public void setRequester(GuarantyClient requester) {
        this.requester = requester;
    }

    public GuarantyClient getRequester() {
        return this.requester;
    }

    public List<GuarantySigner> getSigners() {
        return signers;
    }

    public void setSigners(List<GuarantySigner> endorsementSigners) {
        this.signers = endorsementSigners;
    }

    public List<Guaranty> getGuaranties() {
        return guaranties;
    }

    public void setGuaranties(List<Guaranty> guaranties) {
        this.guaranties = guaranties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuarantyOrderCreationRequest that = (GuarantyOrderCreationRequest) o;

        return new EqualsBuilder()
                .append(requester, that.requester)
                .append(signers, that.signers)
                .append(guaranties, that.guaranties)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(requester)
                .append(signers)
                .append(guaranties)
                .toHashCode();
    }
}
