/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class PreguntasSeguridadResponse.
 */
@Record
public class PreguntasSeguridadResponse {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The cantidad preguntas. */
	@Field
	private Integer cantidadPreguntas;

	/** The preguntas. */
	@Segment(occursRef = "cantidadPreguntas")
	private List<PreguntasEntity> preguntas;

	/** The cantidad respuestas. */
	@Field
	private Integer cantidadRespuestas;

	/** The respuestas. */
	@Segment(occursRef = "cantidadPreguntas")
	private List<RespuestaEntity> respuestas;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the cantidad preguntas.
	 *
	 * @return the cantidadPreguntas
	 */
	public Integer getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	/**
	 * Sets the cantidad preguntas.
	 *
	 * @param cantidadPreguntas
	 *            the cantidadPreguntas to set
	 */
	public void setCantidadPreguntas(Integer cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}

	/**
	 * Gets the preguntas.
	 *
	 * @return the preguntas
	 */
	public List<PreguntasEntity> getPreguntas() {
		return preguntas;
	}

	/**
	 * Sets the preguntas.
	 *
	 * @param preguntas
	 *            the preguntas to set
	 */
	public void setPreguntas(List<PreguntasEntity> preguntas) {
		this.preguntas = preguntas;
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
	public List<RespuestaEntity> getRespuestas() {
		return respuestas;
	}

	/**
	 * Sets the respuestas.
	 *
	 * @param respuestas
	 *            the respuestas to set
	 */
	public void setRespuestas(List<RespuestaEntity> respuestas) {
		this.respuestas = respuestas;
	}

}
