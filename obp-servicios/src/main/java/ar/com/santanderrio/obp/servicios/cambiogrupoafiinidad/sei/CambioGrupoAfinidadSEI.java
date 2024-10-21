/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.AltaSolicitudAdhesionView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConfirmarCambioAfinidadInView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConsultaGrupoAfinidadView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.SolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface CambioGrupoAfinidadSEI.
 */
@Path("cambioGrupoAfinidad")
public interface CambioGrupoAfinidadSEI {

	/**
	 * Consultar grupo afinidad.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarGrupoAfinidad")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ConsultaGrupoAfinidadView> consultarGrupoAfinidad();
	
	/**
	 * Obtener datos iniciales solicitud.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarCambioAfinidad")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<SolicitudCambioAfinidadView> obtenerDatosInicialesSolicitud();
	
	/**
	 * Confirmar cambio afinidad.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarCambioAfinidad")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AltaSolicitudAdhesionView> confirmarCambioAfinidad(ConfirmarCambioAfinidadInView inView);
	
	/**
	 * Generar comprobante cambio afinidad.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/generarComprobante")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarComprobanteCambioAfinidad();
	
}
