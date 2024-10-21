/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView;
import ar.com.santanderrio.obp.servicios.login.view.CsidView;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.InicioLoginView;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;

// TODO: Auto-generated Javadoc
/**
 * Migracion de LoginController.
 *
 * @author sergio.e.goldentair
 */
@Path("/login")
public interface LoginSEI {

    /**
	 * Operacion invocada al ingresar al sitio.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    @POST
    @Path("/doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<LoginResponseView> login(@FormParam("datoEntrada") String datoEntrada, @Context MessageContext mc);

    /**
	 * Punto de entrada para que OBE vuelva a tbanco sin pasar por la pantalla
	 * de login y entre directo a HOME.
	 *
	 * @param loginView
	 *            the login view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    @POST
    @Path("/doLoginOBE")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<LoginResponseView> loginOBE(LoginView loginView, @Context MessageContext mc);
    
    
    /**
     * Login APP.
     *
     * @param loginView the login view
     * @param mc the mc
     * @return the respuesta
     */
    @POST
    @Path("/doLoginApp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<LoginResponseView> loginAPP(LoginView loginView, @Context MessageContext mc);

    /**
	 * Operacion invocada al ingresar al sitio.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    @POST
    @Path("/doLoginHomonimo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<LoginResponseView> doLoginHomonimo(@FormParam("datoEntrada") String datoEntrada,
            @Context MessageContext mc);

    /**
     * Logout de la applicacion.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/doLogout")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<LogOutResponseView> logout(@Context MessageContext mc);

    /**
     * Logout de la applicacion pasos intermedios al home.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/doRelease")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<LogOutResponseView> release(@Context MessageContext mc);

    /**
     * Operacion invocada para refresco de token.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<LoginResponseView> refresh(@Context MessageContext mc);

    /**
     * Operacion invocada para entrar al login.
     *
     * @return the respuesta
     */
    @POST
    @Path("/inicio")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<InicioLoginView> obtenerInicioLogin();

    /**
     * Operacion invocada para inicio de funcionalidad de usuario y clave expirados.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    @POST
    @Path("/CUEInicio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> inicioClaveUsuarioExpirados(@Context MessageContext mc);

    /**
	 * Login CUE confirmar usuario.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    @POST
    @Path("/CUEConfirmarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<FeedbackMensajeView> loginCUEConfirmarUsuario(@FormParam("datoEntrada") String datoEntrada,
            @Context MessageContext mc);

    /**
	 * Operacion invocada confirmar clave nueva.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            the mc
	 * @return the nothing
	 */
    @POST
    @Path("/CUEConfirmarClave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<Void> loginCUEConfirmarClave(@FormParam("datoEntrada") String datoEntrada, @Context MessageContext mc);

    /**
     * Operacion invocada confirmar clave nueva.
     *
     * @param credencialesLoginView
     *            the credenciales login view
     * @param mc
     *            the mc
     * @return the nothing
     */
    @POST
    @Path("/cambioUsuarioPendiente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<FeedbackMensajeView> cambioUsuarioPendiente(CredencialesLoginView credencialesLoginView,
            @Context MessageContext mc);

    /**
     * Definicion de osuario.
     *
     * @param mc
     *            context
     * @return the respuesta
     */
    @POST
    @Path("/ingresoHome")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<LoginResponseView> loginIngresoHome(@Context MessageContext mc);

    /**
	 * Definicion de usuario.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            context
	 * @return the respuesta
	 */
    @POST
    @Path("/definirUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<FeedbackMensajeView> loginDefinirUsuario(@FormParam("datoEntrada") String datoEntrada,
            @Context MessageContext mc);

    /**
     * Definicion de usuario.
     *
     * @param datosMyaView
     *            the datos mya view
     * @param mc
     *            el contexto
     * @return the respuesta
     */
    @POST
    @Path("/confirmarDatosMya")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<LoginResponseView> confirmarDatosMya(DatosMyaView datosMyaView, @Context MessageContext mc);

    /**
     * Definicion de usuario.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerTerminosCondicionesPDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> obtenerTerminosCondicionesPDF();

    /**
     * Poder conocer si el servidor esta levantado, pedido schualle.
     *
     * @return the response
     */
    @GET
    @Path("/monitoreo")
    @Consumes(MediaType.TEXT_HTML)
    Response monitoreo();
    
    @GET
    @Path("/obtenerNuevoCsid")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<CsidView> obtenerNuevoCsid();

}
