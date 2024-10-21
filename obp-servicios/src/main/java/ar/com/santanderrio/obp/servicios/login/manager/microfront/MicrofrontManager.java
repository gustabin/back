package ar.com.santanderrio.obp.servicios.login.manager.microfront;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;

public interface MicrofrontManager {
    OfertaComercialDTO getCampania(String nup, String campaign);
}
