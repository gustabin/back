/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.Date;

/**
 * The Class ValidacionPreguntaIn.
 */
public class ValidacionPreguntaIn {

	/** The dni. */
	private String dni;

	/** The id sesion. */
	private String idSesion;

	/** The ciclo. */
	private Integer ciclo;

	/** The cantidad ocurrencias. */
	private Integer cantidadOcurrencias;

	/** The orden pregunta. */
	private Integer ordenPregunta;

	/** The ip. */
	private String ip;

	/** The id pregunta. */
	private String idPregunta;

	/** The respuesta. */
	private String respuesta;

	/** The opcion correcta. */
	private String opcionCorrecta;

	/** The validacion. */
	private String validacion;

	/** The nup. */
	private String nup;

	/** The fecha nacimiento. */
	private Date fechaNacimiento;
	
	/** The check whatsapp. */
	private Boolean checkWhatsapp;

	/** The checkWhatsappHabilitado. */
	private Boolean checkWhatsappHabilitado;

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the id sesion.
	 *
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * Sets the id sesion.
	 *
	 * @param idSesion
	 *            the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Gets the ciclo.
	 *
	 * @return the ciclo
	 */
	public Integer getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo.
	 *
	 * @param ciclo
	 *            the new ciclo
	 */
	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidadOcurrencias
	 */
	public Integer getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the cantidadOcurrencias to set
	 */
	public void setCantidadOcurrencias(Integer cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the orden pregunta.
	 *
	 * @return the ordenPregunta
	 */
	public Integer getOrdenPregunta() {
		return ordenPregunta;
	}

	/**
	 * Sets the orden pregunta.
	 *
	 * @param ordenPregunta
	 *            the ordenPregunta to set
	 */
	public void setOrdenPregunta(Integer ordenPregunta) {
		this.ordenPregunta = ordenPregunta;
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
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
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
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
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
	 * Gets the validacion.
	 *
	 * @return the validacion
	 */
	public String getValidacion() {
		return validacion;
	}

	/**
	 * Sets the validacion.
	 *
	 * @param validacion
	 *            the validacion to set
	 */
	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

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
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the checkWhatsapp
	 */
	public Boolean getCheckWhatsapp() {
		return checkWhatsapp;
	}

	/**
	 * @param checkWhatsapp
	 *  the checkWhatsapp to set
	 */
	public void setCheckWhatsapp(Boolean checkWhatsapp) {
		this.checkWhatsapp = checkWhatsapp;
	}
	
	/**
	 * @return the checkWhatsappHabilitado
	 */
	public Boolean getCheckWhatsappHabilitado() {
		return checkWhatsappHabilitado;
	}

	/**
	 * @param checkWhatsappHabilitado
	 *  the checkWhatsappHabilitado to set
	 */
	public void setCheckWhatsappHabilitado(Boolean checkWhatsappHabilitado) {
		this.checkWhatsappHabilitado = checkWhatsappHabilitado;
	}
}