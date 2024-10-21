/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * ConsultaPreguntasSeguridadOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaPreguntasSeguridadOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The estado. */
	@Field
	private String estado; // C=Contesto NC= No Contesto

	/** The cantidad preguntas. */
	@Field
	private Long cantidadPreguntas;

	/** The preguntas seguridad. */
	@Segment(occursRef = "cantidadPreguntas")
	private List<PreguntaSeguridadEntity> preguntasSeguridad;

	/** The cantidad respuestas. */
	@Field
	private Long cantidadRespuestas;

	/** The respuestas seguridad. */
	@Segment(occursRef = "cantidadRespuestas")
	private List<RespuestaSeguridadEntity> respuestasSeguridad;

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the cantidad preguntas.
	 *
	 * @return the cantidad preguntas
	 */
	public Long getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	/**
	 * Sets the cantidad preguntas.
	 *
	 * @param cantidadPreguntas
	 *            the new cantidad preguntas
	 */
	public void setCantidadPreguntas(Long cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}

	/**
	 * Gets the preguntas seguridad.
	 *
	 * @return the preguntas seguridad
	 */
	public List<PreguntaSeguridadEntity> getPreguntasSeguridad() {
		return preguntasSeguridad;
	}

	/**
	 * Sets the preguntas seguridad.
	 *
	 * @param preguntasSeguridad
	 *            the new preguntas seguridad
	 */
	public void setPreguntasSeguridad(List<PreguntaSeguridadEntity> preguntasSeguridad) {
		this.preguntasSeguridad = preguntasSeguridad;
	}

	/**
	 * Gets the cantidad respuestas.
	 *
	 * @return the cantidad respuestas
	 */
	public Long getCantidadRespuestas() {
		return cantidadRespuestas;
	}

	/**
	 * Sets the cantidad respuestas.
	 *
	 * @param cantidadRespuestas
	 *            the new cantidad respuestas
	 */
	public void setCantidadRespuestas(Long cantidadRespuestas) {
		this.cantidadRespuestas = cantidadRespuestas;
	}

	/**
	 * Gets the respuestas seguridad.
	 *
	 * @return the respuestas seguridad
	 */
	public List<RespuestaSeguridadEntity> getRespuestasSeguridad() {
		return respuestasSeguridad;
	}

	/**
	 * Sets the respuestas seguridad.
	 *
	 * @param respuestasSeguridad
	 *            the new respuestas seguridad
	 */
	public void setRespuestasSeguridad(List<RespuestaSeguridadEntity> respuestasSeguridad) {
		this.respuestasSeguridad = respuestasSeguridad;
	}

}
