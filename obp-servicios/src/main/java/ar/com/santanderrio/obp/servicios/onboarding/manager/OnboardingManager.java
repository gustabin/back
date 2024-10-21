/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.onboarding.view.OnboardingView;

/**
 * The Interface OnboardingManager.
 */
public interface OnboardingManager {

	/**
	 * Obtener secciones activas.
	 *
	 * @return the respuesta
	 */
	Respuesta<OnboardingView> obtenerSeccionesActivas();

	/**
	 * Informar listo.
	 *
	 * @param onboarding
	 *            the onboarding
	 */
	void informarListo(OnboardingView onboarding);
}
