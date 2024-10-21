/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.sei;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.onboarding.manager.OnboardingManager;
import ar.com.santanderrio.obp.servicios.onboarding.view.OnboardingView;

/**
 * The Class OnboardingSEIImpl.
 */
@Component
public class OnboardingSEIImpl implements OnboardingSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnboardingSEIImpl.class);

	/** The onboarding manager. */
	@Autowired
	OnboardingManager onboardingManager;
	
	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.onboarding.sei.OnboardingSEI#
	 * obtenerSeccionesActivas()
	 */
	@Override
	public Respuesta<OnboardingView> obtenerSeccionesActivas() {
		LOGGER.info("Post OK: /obtenerSeccionesActivas.");
		return onboardingManager.obtenerSeccionesActivas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.onboarding.sei.OnboardingSEI#
	 * informarListo(ar.com.santanderrio.obp.servicios.onboarding.view.
	 * OnboardingView)
	 */
	@Override
	public void informarListo(OnboardingView onboarding) {
		LOGGER.info("Post OK: /informarListo.");
		onboardingManager.informarListo(onboarding);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.onboarding.sei.OnboardingSEI#grabarEstadisticaIngreso()
	 */
	@Override
	public void grabarEstadisticaIngreso() {
		estadisticaManager.add(CodigoMensajeConstantes.ACCESO_ONBOARDING_DE_CENTRO_AYUDA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

}
