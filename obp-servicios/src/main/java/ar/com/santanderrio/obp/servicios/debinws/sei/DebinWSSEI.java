package ar.com.santanderrio.obp.servicios.debinws.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinws.dto.RechazarDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.view.ConfiguracionGrillaDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.CuentasAdheridasDebinOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.DebinWSEliminarOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinOutView;

/**
 * DebinWSSEI.
 */
@Path("/debinws")
public interface DebinWSSEI {
    
    /**
	 * Consulta debin.
	 *
	 * @param consultaDebinInView
	 *            the consulta debin in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<ConsultaDebinWSOutView> consultaDebin(ConsultaDebinWSInView consultaDebinInView);

    /**
	 * Consulta detalle debin.
	 *
	 * @param consultaDetalleDebinWSInView
	 *            the consulta detalle debin WS in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaDetalleDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<ConsultaDetalleDebinWSOutView> consultaDetalleDebin(ConsultaDetalleDebinWSInView consultaDetalleDebinWSInView);

    /**
     * Eliminar debin
     * 
     * @return
     */
    @POST
    @Path("/eliminarDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<DebinWSEliminarOutView> eliminarDebin();   

    /**
     * Descargar comprobante eliminar debin
     * 
     * 
     * @return
     */
    @POST
    @Path("/descargarComprobanteEliminar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Reporte> descargarComprobanteEliminar();

    /**
     * Pagar debin
     * 
     * @param pagarDebinWSView
     * @return
     */
	@POST
    @Path("/pagarDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<PagarDebinWSView> pagarDebin(PagarDebinWSView pagarDebinWSView);

	/**
	 * Descargar comprobante pagar debin
	 * 
	 * @return
	 */
    @POST
    @Path("/descargarComprobantePagar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Reporte> descargarComprobantePago();

    /**
     * Rechazar debin
     * 
     * 
     * @return
     */
    @POST
    @Path("/rechazarDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<RechazarDebinWSOutDTO> rechazarDebin();

    /**
     * Descargar comprobante rechazar debin
     * 
     * @return
     */
    @POST
    @Path("/descargarComprobanteRechazar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Reporte> descargarComprobanteRechazo();

    /**
     * Buscar cuentas para adhesion de debines
     * 
     * @return
     */
	@POST
    @Path("/buscarCuentasParaAdhesionDebines")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<ConsultarAdhesionDebinesView> buscarCuentasParaAdhesionDebines();

	/**
	 * Gestiona adhesion debin
	 * 
	 * @param gestionAdhesionView
	 * @return
	 */
    @POST
    @Path("/gestionarAdhesionDebines")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<GestionarAdhesionDebinesView> gestionarAdhesionDebines(GestionarAdhesionDebinesView gestionAdhesionView);

    /**
     * Descagar comprobante adhesion cuentas debin
     * 
     * 
     * @return
     */
    @POST
    @Path("/descargarComprobanteAdhesion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Reporte> descargarComprobanteAdhesion();

    /**
     * 
     * Ingreso solicitar debin
     * 
     * @return
     */
	@POST
    @Path("/ingresoSolicitarDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<CuentasAdheridasDebinOutView> ingresoSolicitarDebin();   

	/**
	 * Validar cbu alias debin
	 * 
	 * @param validarCbuAliasInView
	 * @param mc
	 * @return
	 */
    @POST
    @Path("/validarCbuAliasDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<ValidarCbuAliasDebinOutView> validarCbuAliasDebin(ValidarCbuAliasDebinInView validarCbuAliasInView, @Context org.apache.cxf.jaxrs.ext.MessageContext mc);    

    /**
     * Solicitar debin
     * 
     * @param validarCbuAliasInView
     * @return
     */
    @POST
    @Path("/solicitarDebin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<SolicitarDebinView> solicitarDebin(SolicitarDebinView validarCbuAliasInView);    

    /**
     * Descargar comprobante solicitar debin
     * 
     * @return
     */
    @POST
    @Path("/descargarComprobanteSolcitar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Reporte> descargarComprobanteSolcitar();

    /**
     * configurar grilla principal debin
     * 
     * @return
     */
    @POST
    @Path("/configurarGrilla")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ConfiguracionGrillaDebinWSView> configuracionGrillaDebinWSView();
    
}