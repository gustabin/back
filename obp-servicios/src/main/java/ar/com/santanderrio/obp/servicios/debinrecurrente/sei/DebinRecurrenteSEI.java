package ar.com.santanderrio.obp.servicios.debinrecurrente.sei;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.manager.ObtenerRecurrenciasView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The Interface DebinRecurrenteSEI.
 */
@Path("/debinRecurrente")
public interface DebinRecurrenteSEI {
    
    /**
     * Obtener info cliente config.
     *
     * @return the respuesta
     */
    @POST
    @Path("/configuracion")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<DatosConfigClienteDebinRecurrenteView> obtenerInfoClienteConfig();

    @POST
    @Path("/activar")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<ActivarDebinRecurrenteView> activar();
	
    /**
     * Crear recurrencia.
     *
     * @param recurrencia the recurrencia
     * @return the respuesta
     */
    @POST
    @Path("/crearRecurrencia")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<CrearRecurrenciaView> crearRecurrencia(CrearRecurrenciaView recurrencia);
	
    /**
     * Pausar recurrencia.
     *
     * @param recurrencia the recurrencia
     * @return the respuesta
     */
    @POST
    @Path("/pausarRecurrencia")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<AccionRecurrenciaResponseView> pausarRecurrencia(RecurrenciaView recurrencia);

    /**
     * Desubscribir recurrencia.
     *
     * @param recurrencia the recurrencia
     * @return the respuesta
     */
    @POST
    @Path("/desubscribirRecurrencia")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AccionRecurrenciaResponseView> desubscribirRecurrencia(RecurrenciaView recurrencia);

    /**
     * Reanudar recurrencia.
     *
     * @param recurrencia the recurrencia
     * @return the respuesta
     */
    @POST
    @Path("/reanudarRecurrencia")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AccionRecurrenciaResponseView> reanudarRecurrencia(RecurrenciaView recurrencia);

    /**
     * Obtener recurrencias por comprador.
     *
     * @param view the view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerRecurrenciasPorComprador")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<RecurrenciasView> obtenerRecurrenciasPorComprador(ObtenerRecurrenciasView view);
    
    /**
     * Grabar estadistica detalle recurrencia.
     */
    @POST
    @Path("/grabarEstadisticaDetalleRecurrencia")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    void grabarEstadisticaDetalleRecurrencia();

    @POST
    @Path("/desconocerPago")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> desconocerPago(DebinView recurrencia);
    
    /**
     * Grabar estadistica ver comprobante adhesion.
     */
    @POST
    @Path("/grabarEstadisticaVerComprobanteAdhesion")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    void grabarEstadisticaVerComprobanteAdhesion();
    
	/**
	 * Generar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/generarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarComprobantePDF();
    
}
