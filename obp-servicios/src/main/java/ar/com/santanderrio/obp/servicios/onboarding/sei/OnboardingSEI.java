/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.onboarding.view.OnboardingView;

/**
 * The Interface OnboardingSEI.
 */
@Path("/onboarding")
public interface OnboardingSEI {

	/**
	 * Obtener secciones activas.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerSeccionesActivas")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<OnboardingView> obtenerSeccionesActivas();

	/**
	 * Informar listo.
	 *
	 * @param onboarding
	 *            the onboarding
	 */
	@POST
	@Path("/informarListo")
	void informarListo(OnboardingView onboarding);

	/**
	 * Se graba la estadistica de ingreso a onboarding desde centro de ayuda.
	 */
	@POST
	@Path("/IngresoDesdeCentroAyuda")
	@Produces(MediaType.APPLICATION_JSON)
	void grabarEstadisticaIngreso();
}
