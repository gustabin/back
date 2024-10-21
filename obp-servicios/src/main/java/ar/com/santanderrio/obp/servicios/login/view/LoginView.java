/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo intercambio entre el frontend para realizar operaciones de Login
 * Created by pablo.martin.gore on 7/21/2016.
 */
@XmlRootElement(name = "loginView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5801154847177493035L;

	/** The dni ori. */
	private String dniOri;

	/** The dni. */
	private String dni;

	/** The clave. */
	private String clave;

	/** The usuario. */
	private String usuario;

	/** The usuario vacio. */
	private String usuarioVacio;

	/** The clave vacia. */
	private String claveVacia;

	/** The prefijo. */
	private String prefijo;

	/** The sufijo. */
	private String sufijo;

	/** The dispositivo. */
	private String dispositivo;

	/** The navegador. */
	private String navegador;

	/** The device print. */
	private String devicePrint;

	/** The token. */
	private String token;

	/** The fecha de nacimiento. */
	private String fechaDeNacimiento;

	/** The tipo teclado. */
	private String tipoTeclado;
	
	/** The campaign. */
	private String campaign;

	/** The wId. */
	private String wId;
	
    private Boolean ingresoCrearUsuario;
    
    private String csid;

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
	 * Gets the usuario vacio.
	 *
	 * @return the usuario vacio
	 */
	public String getUsuarioVacio() {
		return usuarioVacio;
	}

	/**
	 * Sets the usuario vacio.
	 *
	 * @param usuarioVacio
	 *            the new usuario vacio
	 */
	public void setUsuarioVacio(String usuarioVacio) {
		this.usuarioVacio = usuarioVacio;
	}

	/**
	 * Gets the clave vacia.
	 *
	 * @return the clave vacia
	 */
	public String getClaveVacia() {
		return claveVacia;
	}

	/**
	 * Sets the clave vacia.
	 *
	 * @param claveVacia
	 *            the new clave vacia
	 */
	public void setClaveVacia(String claveVacia) {
		this.claveVacia = claveVacia;
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
	 *            the new prefijo
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
	 *            the new sufijo
	 */
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
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
	 *            the new dispositivo
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
	 *            the new navegador
	 */
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	/**
	 * Gets the device print.
	 *
	 * @return the device print
	 */
	public String getDevicePrint() {
		return devicePrint;
	}

	/**
	 * Sets the device print.
	 *
	 * @param devicePrint
	 *            the new device print
	 */
	public void setDevicePrint(String devicePrint) {
		this.devicePrint = devicePrint;
	}

	/**
	 * Gets the serial version UID.
	 *
	 * @return the serial version UID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
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
	 * Gets the tipo teclado.
	 *
	 * @return the tipo teclado
	 */
	public String getTipoTeclado() {
		return tipoTeclado;
	}

	/**
	 * Sets the tipo teclado.
	 *
	 * @param tipoTeclado
	 *            the new tipo teclado
	 */
	public void setTipoTeclado(String tipoTeclado) {
		this.tipoTeclado = tipoTeclado;
	}

	/**
	 * obtiene la campania
	 * @return
	 */
	public String getCampaign() {
		return campaign;
	}
	/**
	 * setea la campania
	 * @param campaign
	 */
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	/**
	 * Gets the w id.
	 *
	 * @return the w id
	 */
	public String getwId() {
		return wId;
	}

	/**
	 * Sets the w id.
	 *
	 * @param wId the new w id
	 */
	public void setwId(String wId) {
		this.wId = wId;
	}

	public Boolean getIngresoCrearUsuario() {
		return ingresoCrearUsuario;
	}

	public void setIngresoCrearUsuario(Boolean ingresoCrearUsuario) {
		this.ingresoCrearUsuario = ingresoCrearUsuario;
	}

	public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}


}
