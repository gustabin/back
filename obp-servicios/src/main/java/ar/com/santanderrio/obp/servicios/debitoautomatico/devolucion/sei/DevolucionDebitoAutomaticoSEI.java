package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoOutView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAInView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAOutView;

/**
 * The Interface DevolucionDebitoAutomaticoSEI.
 */
@Path("/devolucionDebitoAutomatico")
public interface DevolucionDebitoAutomaticoSEI {

	/**
	 * Solicitar devolucion debito automatico.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@POST
    @Path("/iniciarFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DevolucionDebitoAutomaticoOutView> solicitarDevolucionDebitoAutomatico(DevolucionDebitoAutomaticoView devolucionView);

	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarSolicitud")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<SolicitarDevolucionDAOutView> ejecutarSolicitudDevolucionDA(SolicitarDevolucionDAInView devolucionView);

	/**
	 * Generar comprobante PDF.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@POST
	@Path("/generarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarComprobantePDF(DevolucionDebitoAutomaticoView devolucionView);
	
	/**
	 * Grabar estadistica ver comprobante.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaVerComprobante")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> grabarEstadisticaVerComprobante();
	
	
	
	
}
