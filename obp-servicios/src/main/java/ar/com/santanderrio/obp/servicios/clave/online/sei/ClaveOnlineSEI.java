/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.view.HashView;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.TarjetaBanelcoView;
import ar.com.santanderrio.obp.servicios.clientes.entities.CelularMyaYCompaniasTelResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;

/**
 * The Interface ClaveOnlineSEI.
 *
 * @author B043069
 */
@Path("/claveOnline")
public interface ClaveOnlineSEI {

    /**
     * Operacion invocada al renovar la clave.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/irSolucionar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Mensaje> solucionarProblemas(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

    /**
     * Operacion invocada al renovar la clave.
     *
     * @param mcs
     *            the mcs
     * @return the respuesta
     */
    @POST
    @Path("/identificarClienteSolucionar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> identificarClienteSolucionar(@Context org.apache.cxf.jaxrs.ext.MessageContext mcs);

    /**
     * Operacion invocada al renovar la clave.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/identificarClienteRegistrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> identificarClienteRegistrar(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

    /**
     * Operacion invocada al renovar la clave.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/identificarClienteRenovar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> identificarClienteRenovar(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

    /**
	 * Operacion invocada al renovar la clave.
	 *
	 * @param mc
	 *            the mc
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    @POST
    @Path("/confirmarDatos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<MetodoAutenticacionView> confirmarDatos(@Context MessageContext mc,
            @FormParam("datoEntrada") String datoEntrada);

    /**
	 * Confirmar respuesta.
	 *
	 * @param mc
	 *            the mc
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    @POST
    @Path("/confirmarRespuesta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<MetodoAutenticacionView> confirmarRespuesta(@Context MessageContext mc,
            @FormParam("datoEntrada") String datoEntrada);
    
    /**
     * Confirmar respuesta.
     *
     * @param mc
     *            the mc
     * @param datoEntrada
     * @return the respuesta
     */
    @POST
    @Path("/confirmarRespuestaTelefono")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<MetodoAutenticacionView> confirmarRespuestaTelefono(@Context MessageContext mc,
            @FormParam("datoEntrada") String datoEntrada);

    /**
	 * Confirmar metodo autenticacion.
	 *
	 * @param mc
	 *            the mc
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    @POST
    @Path("/confirmarMetodoAutenticacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<MetodoAutenticacionView> confirmarMetodoAutenticacion(@Context org.apache.cxf.jaxrs.ext.MessageContext mc,
            @FormParam("datoEntrada") String datoEntrada);

    /**
     * Reenviar sms.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/reenviarSms")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<MetodoAutenticacionView> reenviarSms(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

    /**
     * reintentar preguntas.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/reintentarPreguntas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<MetodoAutenticacionView> reintentarPreguntas(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

    /**
     * Validar hash.
     *
     * @param mc
     *            the mc
     * @param hash
     *            the hash
     * @return the respuesta
     */
    @POST
    @Path("/validarHash")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> validarHash(@Context org.apache.cxf.jaxrs.ext.MessageContext mc, HashView hash);

    /**
     * Obtener hash.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/obtenerHash")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<HashView> obtenerHash(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

    

    /**
	 * 
	 * @return
	 */
    @POST
    @Path("/actualizarNumeroCelular")
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<CambioDatosContactoView> actualizarNumeroCelular(CambioDatosContactoView cambioDatosContacto);


	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/obtenerNumeroCelularYcompanias")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CelularMyaYCompaniasTelResponse> obtenerNumeroCelularYcompanias();
	
	@POST
	@Path("/identificacionTarjetaBanelco")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TarjetaBanelcoView> obtenerTarjetaParaValidarPin(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);
    
	@POST
	@Path("/validarPinBanelco")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TarjetaBanelcoView> validarPinBanelco(@Context org.apache.cxf.jaxrs.ext.MessageContext mc, TarjetaBanelcoView banelcoView);
	
}
