/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.onboarding.bo.OnboardingBO;
import ar.com.santanderrio.obp.servicios.onboarding.view.OnboardingView;

/**
 * The Class OnboardingManagerImpl.
 */
@Component
public class OnboardingManagerImpl implements OnboardingManager {
	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnboardingManagerImpl.class);

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The onboarding BO. */
	@Autowired
	OnboardingBO onboardingBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.onboarding.manager.OnboardingManager#
	 * obtenerSeccionesActivas()
	 */
	@Override
	public Respuesta<OnboardingView> obtenerSeccionesActivas() {
		LOGGER.info("Entro al manager para obtener el estado de los onboarding.");
		OnboardingView estadosOnboarding = new OnboardingView();
		if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.ONBOARDING).tienePermisoDeVisibilidad()) {
			estadosOnboarding.setSecciones(onboardingBO.obtenerSeccionesActivas(sesionCliente.getCliente(),
					sesionParametros.getRegistroSession().getDispositivo()));
		}
		return respuestaFactory.crearRespuestaOk(estadosOnboarding);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.onboarding.manager.OnboardingManager#
	 * informarListo(ar.com.santanderrio.obp.servicios.onboarding.view.
	 * OnboardingView)
	 */
	@Override
	public void informarListo(OnboardingView onboarding) {
		LOGGER.info("Entro al manager para informar el listo de un onboarding.");
		onboardingBO.informarListo(sesionCliente.getCliente(), sesionParametros.getRegistroSession().getDispositivo(),
				onboarding.getSeccion());
	}

}
