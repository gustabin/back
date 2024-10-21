package ar.com.santanderrio.obp.servicios.inversiones.maps.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetallePerfilInversorView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.InicioServiciosDeInversionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.ObtenerDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.VerDetallePerfilInversorView;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface ServiciosDeInversion
 * 
 *
 */
@Path("/serviciosdeinversion")
public interface ServiciosDeInversionSEI {

	@POST
	@Path("/inicio")
	@CustomPreAuthorize(AccionController.IR_INICIO_SERVICIOS_DE_INVERSION)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<InicioServiciosDeInversionView> inicioServiciosDeInversion();
	
	@POST
	@Path("/obtenerDisponibles")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ObtenerDisponiblesOutView> obtenerDisponibles(BancaInversionesView inView);
	
	@POST
	@Path("/formulariosAltaInicio")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FormularioControl> altaServicio(FormulariosAltaInicioInView inView);
	
	@POST
	@Path("/formulariosAlta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FormularioControl> altaServicioFlujo(ControlMaps inView);
	
	@POST
	@Path("/verDetallePerfil")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DetallePerfilInversorView> verDetallePerfil(VerDetallePerfilInversorView inView);
	
	
	   /**
     * Notificar acceso desde home.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaGoToTenencia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> notificarGotoTenenciaConsolidada(BancaInversionesView inView);
    
    /**
     * Notificar acceso desde home.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerSuscripciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<GrillaConsultaAdhesionDTO> consultaAdhesion(BancaInversionesView inView);

    
    /**
     * Graba Estadistica Comprobante de AltaAdhesion.
     * 
     * @param inView
     * @return the respuesta
     */
    @POST
    @Path("/accesoComprobanteAltaAdhesion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> accesoComprobanteAltaAdhesion(BancaInversionesView inView);
    
    
    /**
     * Genera un reporte con el comprobante de la adhesion.
     *
     * @param comprobanteAdhesionEmpleado
     *            the comprobante adhesion empleado
     * @return the respuesta
     */
    @POST
    @Path("/descargaComprobanteAltaAdhesion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargaComprobanteAltaAdhesion(FormularioControl formularioControl);

    @POST
    @Path("/obtenerDetalleSuscripcion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<FormularioControl> obtenerDetalleSuscripcion(DetalleSuscripcionView detalleSuscripcionView);
    
    @POST
    @Path("/bajaAdhesionInicio")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<FormularioControl> bajaAdhesion(BajaAdhesionView bajaAdhesionView);
    
    @POST
    @Path("/bajaAdhesion")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<FormularioControl> bajaAdhesion(ControlMaps inView);
    
    /**
     * Graba Estadistica Comprobante de AltaAdhesion.
     * 
     * @param inView
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaComprobanteBaja")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaComprobanteBaja(BancaInversionesView inView);
    
    /**
     * Genera un reporte con el comprobante de la baja de la adhesion.
     *
     * @param comprobanteAdhesionEmpleado
     *            the comprobante adhesion empleado
     * @return the respuesta
     */
    @POST
    @Path("/descargaComprobanteBajaAdhesion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargaComprobanteBajaAdhesion(FormularioControl formularioControl);
    
}
