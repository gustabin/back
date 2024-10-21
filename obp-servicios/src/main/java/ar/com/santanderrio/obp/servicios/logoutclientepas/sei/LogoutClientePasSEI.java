package ar.com.santanderrio.obp.servicios.logoutclientepas.sei;


import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.logoutclientepas.view.ResponseLogoutClientePas;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/logoutClientePas")
public interface LogoutClientePasSEI {

    @GET
    @Path("/getPas")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ResponseLogoutClientePas> getClietnePas();

}
