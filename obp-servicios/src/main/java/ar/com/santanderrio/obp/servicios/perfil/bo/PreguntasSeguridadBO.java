/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntaSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

/**
 * Interface PreguntasSeguridadBO.
 *
 * @author Silvina_Luque
 */
public interface PreguntasSeguridadBO {

	/**
	 * Consulta de preguntas de seguridad.
	 *
	 * @return Preguntas de Seguridad
	 */
	Respuesta<PreguntasSeguridadView> consultarPreguntasSeguridad();

	/**
	 * Guardad respuesta de preguntas de seguridad.
	 *
	 * @param preguntasSeguridadView
	 *            the preguntas seguridad view
	 * @return the respuesta
	 */
	Respuesta<ModificacionPreguntasSeguridadOutEntity> guardarPreguntasSeguridad(
			List<PreguntaSeguridadView> preguntasSeguridadView);

	/**
	 * Comprobante de preguntas de seguridad.
	 *
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	Respuesta<Reporte> descargarComprobante(DatosComprobanteEntity datos);

}
