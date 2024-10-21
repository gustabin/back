package ar.com.santanderrio.obp.servicios.login.manager.microfront.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.servicios.login.manager.microfront.MicrofrontManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLinkMicrofront;
import ar.com.santanderrio.obp.servicios.login.manager.microfront.strategies.MicrofrontStrategy;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialMicrofrontDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MicrofrontManagerImpl implements MicrofrontManager {

    private Map<String, MicrofrontStrategy> microfrontStrategies;

    @Value("${BFF_URL.MICROFRONTEND}")
    protected String microfrontendUrl;

    @Autowired
    public MicrofrontManagerImpl(Set<MicrofrontStrategy> strategySet){
        microfrontStrategies = new HashMap<String, MicrofrontStrategy>();
        for (MicrofrontStrategy strategy : strategySet) {
            microfrontStrategies.put(strategy.obtenerLink(), strategy);
        }
    }

    @Override
    public OfertaComercialDTO getCampania(String nup, String campaign) {
        if(this.microfrontStrategies.containsKey(campaign)){
            MicrofrontStrategy strategy = this.microfrontStrategies.get(campaign);
            if(strategy.debeAgregarCampania(nup)){
                OfertaComercialDTO ofertaDTO = new OfertaComercialDTO();
                ofertaDTO.setIdRtd(campaign);
                ofertaDTO.setGotoLink(new GotoLinkMicrofront(strategy.obtenerLink()));
                ofertaDTO.setOfertaComercialMicrofrontDto(new OfertaComercialMicrofrontDto(strategy.obtenerMicrofrontName(), microfrontendUrl));
                return ofertaDTO;
            }
        }
        return null;
    }
}
