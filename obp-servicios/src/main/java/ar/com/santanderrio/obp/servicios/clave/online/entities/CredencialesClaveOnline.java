/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

/**
 * The Class CredencialesClaveOnline.
 */
public class CredencialesClaveOnline {

	/** The dni. */
	private String dni;

	/** The dni ori. */
	private String dniOri;

	/** The fecha de nacimiento. */
	private String fechaDeNacimiento;

	/** The ingreso solucionar. */
	private Boolean ingresoSolucionar = Boolean.FALSE;

	/** The ingreso registrar. */
	private Boolean ingresoRegistrar = Boolean.FALSE;

	/** The ingreso renovar. */
	private Boolean ingresoRenovar = Boolean.FALSE;

	/** The respuesta. */
	private String respuesta;

	/** The dispositivo. */
	private String dispositivo;

	/** The navegador. */
	private String navegador;

	/** The nup. */
	private String nup;

	/** The prefijo. */
	private String prefijo;

	/** The sufijo. */
	private String sufijo;
	
	/** The device print. */
	private String devicePrint;
	
	/** The tipo teclado. */
	private String tipoTeclado;

	/** The host name. */
	private String hostName;

	/** The ciclo. */
	private Integer ciclo;

	/** The clave. */
	private String clave;
	
	/** The check whatsapp. */
	private Boolean checkWhatsapp;
	
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
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the fecha de nacimiento.
	 *
	 * @return the fecha de nacimiento
	 */
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Sets the fecha de nacimiento.
	 *
	 * @param fechaDeNacimiento
	 *            the new fecha de nacimiento
	 */
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Checks if is ingreso solucionar.
	 *
	 * @return the boolean
	 */
	public Boolean isIngresoSolucionar() {
		return ingresoSolucionar;
	}

	/**
	 * Sets the ingreso solucionar.
	 *
	 * @param ingresoSolucionar
	 *            the new ingreso solucionar
	 */
	public void setIngresoSolucionar(Boolean ingresoSolucionar) {
		this.ingresoSolucionar = ingresoSolucionar;
	}

	/**
	 * Checks if is ingreso registrar.
	 *
	 * @return the boolean
	 */
	public Boolean isIngresoRegistrar() {
		return ingresoRegistrar;
	}

	/**
	 * Sets the ingreso registrar.
	 *
	 * @param ingresoRegistrar
	 *            the new ingreso registrar
	 */
	public void setIngresoRegistrar(Boolean ingresoRegistrar) {
		this.ingresoRegistrar = ingresoRegistrar;
	}

	/**
	 * Checks if is ingreso renovar.
	 *
	 * @return the boolean
	 */
	public Boolean isIngresoRenovar() {
		return ingresoRenovar;
	}

	/**
	 * Sets the ingreso renovar.
	 *
	 * @param ingresoRenovar
	 *            the new ingreso renovar
	 */
	public void setIngresoRenovar(Boolean ingresoRenovar) {
		this.ingresoRenovar = ingresoRenovar;
	}

	/**
	 * Gets the dni ori.
	 *
	 * @return the dni ori
	 */
	public String getDniOri() {
		return dniOri;
	}

	/**
	 * Sets the dni ori.
	 *
	 * @param dniOri
	 *            the new dni ori
	 */
	public void setDniOri(String dniOri) {
		this.dniOri = dniOri;
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
	 * Gets the dispositivo.
	 *
	 * @return the dispositivo
	 */
	public String getDispositivo() {
		return dispositivo;
	}

	/**
	 * Sets the dispositivo.
	 *
	 * @param dispositivo
	 *            the dispositivo to set
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 * Gets the navegador.
	 *
	 * @return the navegador
	 */
	public String getNavegador() {
		return navegador;
	}

	/**
	 * Sets the navegador.
	 *
	 * @param navegador
	 *            the navegador to set
	 */
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	/**
	 * Gets the prefijo.
	 *
	 * @return the prefijo
	 */
	public String getPrefijo() {
		return prefijo;
	}

	/**
	 * Sets the prefijo.
	 *
	 * @param prefijo
	 *            the prefijo to set
	 */
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	/**
	 * Gets the sufijo.
	 *
	 * @return the sufijo
	 */
	public String getSufijo() {
		return sufijo;
	}

	/**
	 * Sets the sufijo.
	 *
	 * @param sufijo
	 *            the sufijo to set
	 */
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	/**
	 * Gets the device print.
	 *
	 * @return the devicePrint
	 */
	public String getDevicePrint() {
		return devicePrint;
	}

	/**
	 * Sets the device print.
	 *
	 * @param devicePrint
	 *            the devicePrint to set
	 */
	public void setDevicePrint(String devicePrint) {
		this.devicePrint = devicePrint;
	}

	/**
	 * Gets the tipo teclado.
	 *
	 * @return the tipoTeclado
	 */
	public String getTipoTeclado() {
		return tipoTeclado;
	}

	/**
	 * Sets the tipo teclado.
	 *
	 * @param tipoTeclado
	 *            the tipoTeclado to set
	 */
	public void setTipoTeclado(String tipoTeclado) {
		this.tipoTeclado = tipoTeclado;
	}

	/**
	 * Gets the host name.
	 *
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Sets the host name.
	 *
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
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
	 *            the ciclo to set
	 */
	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave
	 *            the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the check whatsapp.
	 *
	 * @return the checkWhatsapp
	 */
	public Boolean getCheckWhatsapp() {
		return checkWhatsapp;
	}

	/**
	 * Sets the check whatsapp.
	 *
	 * @param checkWhatsapp
	 *            the new check whatsapp
	 */
	public void setCheckWhatsapp(Boolean checkWhatsapp) {
		this.checkWhatsapp = checkWhatsapp;
	}
		
}
