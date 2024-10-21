/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class PreguntaAutenticacion.
 */
public class PreguntaAutenticacion extends MetodoAutenticacion {

	/** The pregunta. */
	private String pregunta;

	/** The id pregunta. */
	private String idPregunta;

	/** The indice. */
	private Integer indice;

	/** The cantidad respuestas. */
	private Integer cantidadRespuestas;

	/** The respuestas. */
	private List<RespuestaAutenticacion> respuestas;

	/** The opcion correcta. */
	private String opcionCorrecta;

	/**
	 * Instantiates a new pregunta autenticacion.
	 */
	public PreguntaAutenticacion() {
		super();
		respuestas = new ArrayList<RespuestaAutenticacion>();
	}

	/**
	 * Gets the pregunta.
	 *
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * Sets the pregunta.
	 *
	 * @param pregunta
	 *            the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * Gets the id pregunta.
	 *
	 * @return the idPregunta
	 */
	public String getIdPregunta() {
		return idPregunta;
	}

	/**
	 * Sets the id pregunta.
	 *
	 * @param idPregunta
	 *            the idPregunta to set
	 */
	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}

	/**
	 * Gets the cantidad respuestas.
	 *
	 * @return the cantidadRespuestas
	 */
	public Integer getCantidadRespuestas() {
		return cantidadRespuestas;
	}

	/**
	 * Sets the cantidad respuestas.
	 *
	 * @param cantidadRespuestas
	 *            the cantidadRespuestas to set
	 */
	public void setCantidadRespuestas(Integer cantidadRespuestas) {
		this.cantidadRespuestas = cantidadRespuestas;
	}

	/**
	 * Gets the respuestas.
	 *
	 * @return the respuestas
	 */
	public List<RespuestaAutenticacion> getRespuestas() {
		return respuestas;
	}

	/**
	 * Sets the respuestas.
	 *
	 * @param respuestas
	 *            the respuestas to set
	 */
	public void setRespuestas(List<RespuestaAutenticacion> respuestas) {
		this.respuestas = respuestas;
	}

	/**
	 * Gets the opcion correcta.
	 *
	 * @return the opcionCorrecta
	 */
	public String getOpcionCorrecta() {
		return opcionCorrecta;
	}

	/**
	 * Sets the opcion correcta.
	 *
	 * @param opcionCorrecta
	 *            the opcionCorrecta to set
	 */
	public void setOpcionCorrecta(String opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

	/**
	 * Gets the indice.
	 *
	 * @return the indice
	 */
	public Integer getIndice() {
		return indice;
	}

	/**
	 * Sets the indice.
	 *
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(Integer indice) {
		this.indice = indice;
	}

}
