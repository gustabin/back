/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobantePreguntasSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

/**
 * Interface PreguntasSeguridadManager.
 *
 * @author Silvina_Luque
 */
public interface PreguntasSeguridadManager {

	/**
	 * Consulta preguntas de seguridad.
	 *
	 * @return PreguntasSeguridadView
	 */
	Respuesta<PreguntasSeguridadView> consultaPreguntasSeguridad();

	/**
	 * Guardar preguntas de seguridad.
	 *
	 * @param preguntasSeguridadView
	 *            the preguntas seguridad view
	 * @return the respuesta
	 */
	Respuesta<PreguntasSeguridadView> guardarPreguntasSeguridad(PreguntasSeguridadView preguntasSeguridadView);

	/**
	 * Comprobante Preguntas seguridad.
	 *
	 * @return Comprobante View
	 */
	Respuesta<ComprobantePreguntasSeguridadView> verComprobante();

	/**
	 * descargarComprobante.
	 *
	 * @return ReporteView
	 */
	Respuesta<ReporteView> descargarComprobante();

}
