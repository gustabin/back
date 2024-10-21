/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CrearAdhesionDatosRequest.
 */
public class CrearAdhesionDatosRequest{

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The id servicio. */
	@JsonProperty("IdServicio")
	private String idServicio;
	
	/** The form completed. */
	@JsonProperty("FormCompleted")
	private int formCompleted;
	
	/** The operacion. */
	@JsonProperty("Operacion")
	private String operacion;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario;
	
	/** The ip. */
	@JsonProperty("Ip")
	private String ip;
	
	/** The form campos. */
	@JsonProperty("FormCampos")
	private List<FormCamposAdhesionRequest> formCampos = new ArrayList<FormCamposAdhesionRequest>();
	
	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the id servicio.
	 *
	 * @return the id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Sets the id servicio.
	 *
	 * @param idServicio
	 *            the new id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Gets the form completed.
	 *
	 * @return the form completed
	 */
	public int getFormCompleted() {
		return formCompleted;
	}

	/**
	 * Sets the form completed.
	 *
	 * @param formCompleted
	 *            the new form completed
	 */
	public void setFormCompleted(int formCompleted) {
		this.formCompleted = formCompleted;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion
	 *            the new operacion
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the form campos.
	 *
	 * @return the form campos
	 */
	public List<FormCamposAdhesionRequest> getFormCampos() {
		return formCampos;
	}

	/**
	 * Sets the form campos.
	 *
	 * @param formCampos
	 *            the new form campos
	 */
	public void setFormCampos(List<FormCamposAdhesionRequest> formCampos) {
		this.formCampos = formCampos;
	}
	
	
}
