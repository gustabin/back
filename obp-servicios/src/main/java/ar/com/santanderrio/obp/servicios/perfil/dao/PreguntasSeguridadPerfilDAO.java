/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;

/**
 * The interface Perfil Alta/Modificacion Preguntas Seguridad DAO.
 *
 * @author Silvina_Luque
 */
public interface PreguntasSeguridadPerfilDAO {

	/**
	 * Consulta de preguntas de seguridad.
	 *
	 * @param preguntasSeguridadInEntity
	 *            the preguntas seguridad in entity
	 * @return ConsultaPreguntasSeguridadOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaPreguntasSeguridadOutEntity consultaPreguntasSeguridad(
			ConsultaPreguntasSeguridadInEntity preguntasSeguridadInEntity) throws DAOException;

	/**
	 * Guardar de preguntas de seguridad.
	 *
	 * @param preguntasSeguridad
	 *            the preguntas seguridad
	 * @return ModificacionPreguntasSeguridadOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ModificacionPreguntasSeguridadOutEntity guardarPreguntasSeguridad(
			ModificacionPreguntasSeguridadInEntity preguntasSeguridad) throws DAOException;

	/**
	 * Reporte del comprobante de preguntas de seguridad.
	 *
	 * @param datos
	 *            the datos
	 * @return Reporte
	 */
	Reporte descargarComprobante(DatosComprobanteEntity datos);
}
