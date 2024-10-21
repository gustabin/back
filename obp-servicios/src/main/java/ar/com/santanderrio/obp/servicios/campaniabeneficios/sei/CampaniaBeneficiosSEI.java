package ar.com.santanderrio.obp.servicios.campaniabeneficios.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosInView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosOutView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaSuscripcionOutView;

/**
 * The Interface CampaniaBeneficiosSEI.
 *
 * @author A279017
 */
@Path("/campaniaBeneficios")
public interface CampaniaBeneficiosSEI {

    /** The Constant SUSC_CAMPANIA_BENEFIOS. */
    String SUSC_CAMPANIA_BENEFIOS = "/suscribirCampaniaBeneficios";

    /** The cons susc campania benefios. */
    String CONS_SUSC_CAMPANIA_BENEFIOS = "/consultaSuscCampaniaBeneficios";

    /**
     * Suscribir campania beneficios.
     *
     * @param inView
     *            the in view
     * @return Respuesta
     */
    @POST
    @Path(SUSC_CAMPANIA_BENEFIOS)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<CampaniaSuscripcionOutView> suscCampaniaBeneficios(CampaniaBeneficiosInView inView);

    /**
     * Cons susc campania beneficios.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    @POST
    @Path(CONS_SUSC_CAMPANIA_BENEFIOS)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<CampaniaBeneficiosOutView> consSuscCampaniaBeneficios(CampaniaBeneficiosInView inView);
}