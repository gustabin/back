/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.util.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.AutentificacionManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView;
import ar.com.santanderrio.obp.servicios.comun.view.FechaView;

/**
 * The Interface ComunSEI.
 */
@Path("/comun")
public interface ComunSEI {

	/** The Constant FECHA_ACTUAL. */
	String FECHA_ACTUAL = "/fechaActual";

	/** The Constant CANCELAR_DESAFIO_EN_CURSO. */
	String CANCELAR_OPERACION = "/cancelarOperacion";

	String ESTADISTICA_VISUALIZACION_RESUMEN_TYC = "/estadisticaVisualizacionResumenTyC";

	/** The Constant ACCESO_MF. */
	String ACCESO_MF = "/accesoMF";

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fecha actual
	 */
	@POST
	@Path(FECHA_ACTUAL)
	@Produces(MediaType.APPLICATION_JSON)
	FechaView getFechaActual();

	/**
	 * Este metodo se encarga de vaciar el desafio en curso de la sesion. Accion
	 * necesaria para el boton cancelar del popup de desafios.
	 *
	 * @author emilio.watemberg.
	 * @see {@link AutentificacionManagerImpl}
	 * @since Aug 7, 2017.
	 */
	@POST
	@Path(CANCELAR_OPERACION)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void cancelarDesafioEnCurso();

	@POST
	@Path(ESTADISTICA_VISUALIZACION_RESUMEN_TYC)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaVisualizacionResumenTyC();

	/**
	 * Acceso MF.
	 *
	 * @param microFEAccessView the micro FE access view
	 */
	@POST
	@Path(ACCESO_MF)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> accesoMF(MicroFEAccessView microFEAccessView);

}
