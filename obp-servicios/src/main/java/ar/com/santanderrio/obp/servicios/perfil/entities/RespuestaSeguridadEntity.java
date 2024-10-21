/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * RespuestaSeguridadEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class RespuestaSeguridadEntity {

	/** The id pregunta. */
	@Field
	private String idPregunta;

	/** The id respuesta. */
	@Field
	private String idRespuesta;

	/** The descripcion respuesta. */
	@Field
	private String descripcionRespuesta;

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
	 * Gets the id respuesta.
	 *
	 * @return the id respuesta
	 */
	public String getIdRespuesta() {
		return idRespuesta;
	}

	/**
	 * Sets the id respuesta.
	 *
	 * @param idRespuesta
	 *            the new id respuesta
	 */
	public void setIdRespuesta(String idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	/**
	 * Gets the descripcion respuesta.
	 *
	 * @return the descripcion respuesta
	 */
	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}

	/**
	 * Sets the descripcion respuesta.
	 *
	 * @param descripcionRespuesta
	 *            the new descripcion respuesta
	 */
	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}

}
