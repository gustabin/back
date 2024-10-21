package ar.com.santanderrio.obp.servicios.api.offersservice;


import ar.com.santanderrio.obp.servicios.api.offersservice.entities.OffersEntity;

public interface OffersServiceApi {
    OffersEntity getCommercialOffers(String customerId, String device, String channel, String subchannel);
}
