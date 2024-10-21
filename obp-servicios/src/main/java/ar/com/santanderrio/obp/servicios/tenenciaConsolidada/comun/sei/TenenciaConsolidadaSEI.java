/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface TenenciaConsolidadaSEI.
 */
@Path("/tenenciaConsolidada")
public interface TenenciaConsolidadaSEI {
	

		
	/**
	 * Obtener rendimiento.
	 *
	 * @return the respuesta
	 */
	@POST
    @Path("/obtenerRendimiento")
    @CustomPreAuthorize(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RendimientoView>obtenerRendimiento();
	
	
	/**
	 * Obtener rendimiento B priv.
	 *
	 * @return the respuesta
	 */
	@POST
    @Path("/obtenerRendimientoBPriv")
    @CustomPreAuthorize(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RendimientoBPrivView>obtenerRendimientoBPriv();
	

}
