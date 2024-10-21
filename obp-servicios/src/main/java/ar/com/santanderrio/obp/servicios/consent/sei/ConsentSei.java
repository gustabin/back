package ar.com.santanderrio.obp.servicios.consent.sei;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.consent.sei.view.UserConsentsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/consent")
public interface ConsentSei {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<UserConsentsResponse> getUserConsents();

    @POST
    @Path("/{consentedClientId}")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> revokeUserConsent(@PathParam("consentedClientId") String consentedClientId);
}
