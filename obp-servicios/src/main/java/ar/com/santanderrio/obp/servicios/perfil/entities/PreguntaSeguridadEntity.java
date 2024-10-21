/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * PreguntaSeguridadEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class PreguntaSeguridadEntity {

	/** The id pregunta. */
	@Field
	private String idPregunta;

	/** The descripcion pregunta. */
	@Field
	private String descripcionPregunta;

	/**
	 * Gets the id pregunta.
	 *
	 * @return the id pregunta
	 */
	public String getIdPregunta() {
		return idPregunta;
	}

	/**
	 * Sets the id pregunta.
	 *
	 * @param idPregunta
	 *            the new id pregunta
	 */
	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}

	/**
	 * Gets the descripcion pregunta.
	 *
	 * @return the descripcion pregunta
	 */
	public String getDescripcionPregunta() {
		return descripcionPregunta;
	}

	/**
	 * Sets the descripcion pregunta.
	 *
	 * @param descripcionPregunta
	 *            the new descripcion pregunta
	 */
	public void setDescripcionPregunta(String descripcionPregunta) {
		this.descripcionPregunta = descripcionPregunta;
	}

}
