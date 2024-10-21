package ar.com.santanderrio.obp.servicios.api.offersservice.entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OffersEntity {
    private String nup;
    private List<Offer> offers;

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return offers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffersEntity)) return false;

        OffersEntity that = (OffersEntity) o;
        return new EqualsBuilder()
                .append(offers, that.offers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(offers)
                .toHashCode();
    }
}
