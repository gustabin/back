package ar.com.santanderrio.obp.servicios.debinws.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentaAdhesionDTO;

@SuppressWarnings("serial")
public class GestionarAdhesionDebinesView extends RsaDTOParaDesafio implements Cloneable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionarAdhesionDebinesView.class);

	/** cuentasCambiadas */
	private List<CuentaAdhesionDTO> cuentasCambiadas;

	/** fechaComprobante */
	private String fechaComprobante;

	/** validaRSA */
	private boolean validaRSA;

	/** numeroComprobante */
	private String numeroComprobante;

	/** the feedback parcial*/
	private Boolean feedbackParcial = Boolean.FALSE;

	/** the mensaje feedback*/
	private String mensajeFeedback;

	/** the titulo comprobante*/
	private String tituloComprobante;

	/** the subtitulo comprobante*/
	private String subtituloComprobante;


	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	private AutentificacionDTO desafio;

	public GestionarAdhesionDebinesView() {
		super(OperacionesRSAEnum.ADHESION_DEBIN);
	}

	/**
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * @param desafio the desafio to set
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * @return the cuentasCambiadas
	 */
	public List<CuentaAdhesionDTO> getCuentasCambiadas() {
		return cuentasCambiadas;
	}

	/**
	 * @param cuentasCambiadas the cuentasCambiadas to set
	 */
	public void setCuentasCambiadas(List<CuentaAdhesionDTO> cuentasCambiadas) {
		this.cuentasCambiadas = cuentasCambiadas;
	}

	/**
	 * @return the validaRSA
	 */
	public boolean isValidaRSA() {
		return validaRSA;
	}

	/**
	 * @param validaRSA the validaRSA to set
	 */
	public void setValidaRSA(boolean validaRSA) {
		this.validaRSA = validaRSA;
	}

	/**
	 * @return the fechaComprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * @param fechaComprobante the fechaComprobante to set
	 */
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}



	/**
	 * @return the feedbackParcial
	 */
	public Boolean isFeedbackParcial() {
		return feedbackParcial;
	}

	/**
	 * @param feedbackParcial the feedbackParcial to set
	 */
	public void setFeedbackParcial(Boolean feedbackParcial) {
		this.feedbackParcial = feedbackParcial;
	}

	/**
	 * @return the mensajeFeedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * @param mensajeFeedback the mensajeFeedback to set
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}



	/**
	 * @return the tituloComprobante
	 */
	public String getTituloComprobante() {
		return tituloComprobante;
	}

	/**
	 * @param tituloComprobante the tituloComprobante to set
	 */
	public void setTituloComprobante(String tituloComprobante) {
		this.tituloComprobante = tituloComprobante;
	}

	/**
	 * @return the subtituloComprobante
	 */
	public String getSubtituloComprobante() {
		return subtituloComprobante;
	}

	/**
	 * @param subtituloComprobante the subtituloComprobante to set
	 */
	public void setSubtituloComprobante(String subtituloComprobante) {
		this.subtituloComprobante = subtituloComprobante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public GestionarAdhesionDebinesView clone() {
		try {
			return (GestionarAdhesionDebinesView) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error clonando Objeto", e);
			return null;
		}
	}


}
