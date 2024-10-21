/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class PreguntaAutenticacionView.
 */
public class PreguntaAutenticacionView extends MetodoAutenticacionView {

	/** The Constant serialVersionUID. */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/** The cantidad preguntas. */
	private Integer cantidadPreguntas;

	/** The pregunta. */
	private PreguntaView pregunta;

	/** The cantidad respuestas. */
	private Integer cantidadRespuestas;

	/** The respuestas. */
	private List<RespuestaAutenticacionView> respuestas;
	
	/** The check whatsapp habilitado */
	private Boolean checkWhatsappHabilitado;
	
	/** The legal */
	private String legal;
	
	@JsonSerialize(include = Inclusion.NON_NULL)
	private List<CompaniaCelularView> companias;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * Gets the pregunta.
	 *
	 * @return the pregunta
	 */
	public PreguntaView getPregunta() {
		return pregunta;
	}

	/**
	 * Sets the pregunta.
	 *
	 * @param pregunta
	 *            the pregunta to set
	 */
	public void setPregunta(PreguntaView pregunta) {
		this.pregunta = pregunta;
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
	public List<RespuestaAutenticacionView> getRespuestas() {
		return respuestas;
	}

	/**
	 * Sets the respuestas.
	 *
	 * @param respuestas
	 *            the respuestas to set
	 */
	public void setRespuestas(List<RespuestaAutenticacionView> respuestas) {
		this.respuestas = respuestas;
	}


	/**
	 * Gets the check whatsapp habilitado.
	 *
	 * @return the checkWhatsappHabilitado
	 */
	public Boolean getCheckWhatsappHabilitado() {
		return checkWhatsappHabilitado;
	}
	
	/**
	 * Sets the check whatsapp habilitado.
	 *
	 * @param checkWhatsappHabilitado
	 *            the checkWhatsappHabilitado to set
	 */
	public void setCheckWhatsappHabilitado(Boolean checkWhatsappHabilitado) {
		this.checkWhatsappHabilitado = checkWhatsappHabilitado;
	}
	
	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}
	
	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	public List<CompaniaCelularView> getCompanias() {
		return companias;
	}

	public void setCompanias(List<CompaniaCelularView> companias) {
		this.companias = companias;
	}
	
}
