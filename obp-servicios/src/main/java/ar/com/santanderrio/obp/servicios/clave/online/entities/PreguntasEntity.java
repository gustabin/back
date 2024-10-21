/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import org.beanio.annotation.Field;

/**
 * The Class PreguntasEntity.
 */
public class PreguntasEntity {

	/** The identificador pregunta. */
	@Field
	public String identificadorPregunta;

	/** The texto. */
	@Field
	public String texto;

	/**
	 * Gets the identificador pregunta.
	 *
	 * @return the identificadorPregunta
	 */
	public String getIdentificadorPregunta() {
		return identificadorPregunta;
	}

	/**
	 * Sets the identificador pregunta.
	 *
	 * @param identificadorPregunta
	 *            the identificadorPregunta to set
	 */
	public void setIdentificadorPregunta(String identificadorPregunta) {
		this.identificadorPregunta = identificadorPregunta;
	}

	/**
	 * Gets the texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Sets the texto.
	 *
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

}