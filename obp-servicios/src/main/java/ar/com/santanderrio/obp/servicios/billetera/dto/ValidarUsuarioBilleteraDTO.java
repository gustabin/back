/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ValidarUsuarioBilleteraDTO.
 */
public class ValidarUsuarioBilleteraDTO {

	/** The cuenta billetera. */
	private CuentaBilleteraDTO cuentaBilletera;

	/** The cuentas acreditacion. */
	private List<Cuenta> cuentasAcreditacion;

	/** The emails registrados. */
	private List<String> emailsRegistrados;

	/** The empresa. */
	private String empresa;

	/** The estado mail. */
	private String estadoMail;

	/** The idx cta tp. */
	private String idxCtaTp;

	/** The mail registrado. */
	private String mailRegistrado;

	/** The medios pago. */
	private List<MedioDePagoBilleteraDTO> mediosPago;

	/** The medios pago otros bancos. */
	private List<MedioDePagoBilleteraDTO> mediosPagoOtrosBancos;

	/** The mensaje. */
	private String mensaje;

	/** The pregunta seguridad. */
	private String preguntaSeguridad;

	/** The telefono. */
	private String telefono;

	/** The view name. */
	private String viewName;

	/**
	 * Gets the cuenta billetera.
	 *
	 * @return the cuentaBilletera
	 */
	public CuentaBilleteraDTO getCuentaBilletera() {
		return cuentaBilletera;
	}

	/**
	 * Gets the cuentas acreditacion.
	 *
	 * @return the cuentasAcreditacion
	 */
	public List<Cuenta> getCuentasAcreditacion() {
		return cuentasAcreditacion;
	}

	/**
	 * Gets the emails registrados.
	 *
	 * @return the emailsRegistrados
	 */
	public List<String> getEmailsRegistrados() {
		return emailsRegistrados;
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Gets the estado mail.
	 *
	 * @return the estadoMail
	 */
	public String getEstadoMail() {
		return estadoMail;
	}

	/**
	 * Gets the idx cta tp.
	 *
	 * @return the idxCtaTp
	 */
	public String getIdxCtaTp() {
		return idxCtaTp;
	}

	/**
	 * Gets the mail registrado.
	 *
	 * @return the mailRegistrado
	 */
	public String getMailRegistrado() {
		return mailRegistrado;
	}

	/**
	 * Gets the medios pago.
	 *
	 * @return the medios pago
	 */
	public List<MedioDePagoBilleteraDTO> getMediosPago() {
		return mediosPago;
	}

	/**
	 * Gets the medios pago otros bancos.
	 *
	 * @return the mediosPagoOtrosBancos
	 */
	public List<MedioDePagoBilleteraDTO> getMediosPagoOtrosBancos() {
		return mediosPagoOtrosBancos;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Gets the pregunta seguridad.
	 *
	 * @return the preguntaSeguridad
	 */
	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Gets the view name.
	 *
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * Sets the cuenta billetera.
	 *
	 * @param cuentaBilletera
	 *            the cuentaBilletera to set
	 */
	public void setCuentaBilletera(CuentaBilleteraDTO cuentaBilletera) {
		this.cuentaBilletera = cuentaBilletera;
	}

	/**
	 * Sets the cuentas acreditacion.
	 *
	 * @param cuentasAcreditacion
	 *            the cuentasAcreditacion to set
	 */
	public void setCuentasAcreditacion(List<Cuenta> cuentasAcreditacion) {
		this.cuentasAcreditacion = cuentasAcreditacion;
	}

	/**
	 * Sets the emails registrados.
	 *
	 * @param emailsRegistrados
	 *            the emailsRegistrados to set
	 */
	public void setEmailsRegistrados(List<String> emailsRegistrados) {
		this.emailsRegistrados = emailsRegistrados;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Sets the estado mail.
	 *
	 * @param estadoMail
	 *            the estadoMail to set
	 */
	public void setEstadoMail(String estadoMail) {
		this.estadoMail = estadoMail;
	}

	/**
	 * Sets the idx cta tp.
	 *
	 * @param idxCtaTp
	 *            the idxCtaTp to set
	 */
	public void setIdxCtaTp(String idxCtaTp) {
		this.idxCtaTp = idxCtaTp;
	}

	/**
	 * Sets the mail registrado.
	 *
	 * @param mailRegistrado
	 *            the mailRegistrado to set
	 */
	public void setMailRegistrado(String mailRegistrado) {
		this.mailRegistrado = mailRegistrado;
	}

	/**
	 * Sets the medios pago.
	 *
	 * @param mediosPago
	 *            the new medios pago
	 */
	public void setMediosPago(List<MedioDePagoBilleteraDTO> mediosPago) {
		this.mediosPago = mediosPago;
	}

	/**
	 * Sets the medios pago otros bancos.
	 *
	 * @param mediosPagoOtrosBancos
	 *            the mediosPagoOtrosBancos to set
	 */
	public void setMediosPagoOtrosBancos(List<MedioDePagoBilleteraDTO> mediosPagoOtrosBancos) {
		this.mediosPagoOtrosBancos = mediosPagoOtrosBancos;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Sets the pregunta seguridad.
	 *
	 * @param preguntaSeguridad
	 *            the preguntaSeguridad to set
	 */
	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Sets the view name.
	 *
	 * @param viewName
	 *            the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
