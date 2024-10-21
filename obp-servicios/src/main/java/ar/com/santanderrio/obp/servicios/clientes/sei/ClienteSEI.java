/*
 *
 */
package ar.com.santanderrio.obp.servicios.clientes.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.MarcaAPNHResponse;

/**
 * The Interface ClienteSEI.
 */
@Path("/cliente")
public interface ClienteSEI {

    /**
     * Gets the marca APNH.
     *
     * @return the marca APNH
     */
    @POST
    @Path("/marcaAPNH")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<MarcaAPNHResponse> getMarcaAPNH();

    /**
     * Cambio clave usuario.
     *
     * @param mc
     *            contexto
     * @param cambioUsuarioView
     *            the cambio usuario view
     * @return the respuesta
     */
    @POST
    @Path("/cambioClaveUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<CambioClaveUsuarioView> cambioClaveUsuario(@Context MessageContext mc,
            CambioClaveUsuarioView cambioUsuarioView);

    /**
     * Cambio usuario.
     *
     * @param mc
     *            contexto
     * @param cambioUsuarioView
     *            the cambio usuario view
     * @return the respuesta
     */
    @POST
    @Path("/cambioUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<CambioUsuarioView> cambioUsuario(@Context MessageContext mc, CambioUsuarioView cambioUsuarioView);

    @POST
    @Path("/censoEconomico/estado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Void> getEstadoCensoEconomico();
}
