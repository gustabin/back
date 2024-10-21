package ar.com.santanderrio.obp.servicios.campaniabeneficios.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.sei.impl.AadvantageSEIImpl;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.manager.CampaniaBeneficiosMananager;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.sei.CampaniaBeneficiosSEI;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosInView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosOutView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaSuscripcionOutView;

/**
 * The Class CampaniaBeneficiosSEIImpl.
 */
@Component("CampaniabeneficiosSEI")
public class CampaniaBeneficiosSEIImpl implements CampaniaBeneficiosSEI {

    /** The transferencia exterior manager. */
    @Autowired
    private CampaniaBeneficiosMananager campaniaBeneficiosMananager;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AadvantageSEIImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.campaniabeneficios.sei.
     * CampaniaBeneficiosSEI#suscCampaniaBeneficios(ar.com.santanderrio.obp.
     * servicios.campaniabeneficios.view.CampaniaBeneficiosInView)
     */
    @Override
    public Respuesta<CampaniaSuscripcionOutView> suscCampaniaBeneficios(CampaniaBeneficiosInView inView) {
        Respuesta<CampaniaSuscripcionOutView> respuesta = campaniaBeneficiosMananager
                .suscripcionCampaniaBeneficios(inView);
        LOGGER.info("Post {}: /suscCampaniaBeneficios.", respuesta.getEstadoRespuesta());
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.campaniabeneficios.sei.
     * CampaniaBeneficiosSEI#consSuscCampaniaBeneficios(ar.com.santanderrio.obp.
     * servicios.campaniabeneficios.view.CampaniaBeneficiosInView)
     */
    @Override
    public Respuesta<CampaniaBeneficiosOutView> consSuscCampaniaBeneficios(CampaniaBeneficiosInView inView) {
        Respuesta<CampaniaBeneficiosOutView> respuesta = campaniaBeneficiosMananager.consSuscCampaniaBeneficios(inView);
        LOGGER.info("Post {}: /consSuscCampaniaBeneficios.", respuesta.getEstadoRespuesta());
        return respuesta;
    }

}