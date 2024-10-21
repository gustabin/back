/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import org.beanio.annotation.Field;

/**
 * The Class CuentaOnDemandDTO.
 */
public class CuentaOnDemandDTO {

	/** The entidad cuenta. */
	@Field
	private String entidadCuenta;
	
	/** The sucursal de la cuenta. */
	@Field
	private String centroAlta;
	
	/** The nro de cuenta. */
	@Field
	private String cuenta;
	
	/** The calidad del cliente. */
	@Field
	private String pecalpar;
	
	/** The producto. */
	@Field
	private String producto;
	
	/** The sub producto. */
	@Field
	private String subProducto;
	
	/** The tipo cuenta. */
	@Field
	private String tipoCuenta;
	
	/** The divisa. */
	@Field
	private String divisa;
	
	/** The envio extracto S / N. */
	@Field
	private String envioExtrac;
	
	/** The periodicidad. */
	@Field
	private String periodicidad;
	
	/** The cta pivote S / N. */
	@Field
	private String ctaPivote;

	/**
	 * Gets the entidad cuenta.
	 *
	 * @return the entidad cuenta
	 */
	public String getEntidadCuenta() {
		return entidadCuenta;
	}

	/**
	 * Sets the entidad cuenta.
	 *
	 * @param entidadCuenta the new entidad cuenta
	 */
	public void setEntidadCuenta(String entidadCuenta) {
		this.entidadCuenta = entidadCuenta;
	}

	/**
	 * Gets the centro alta.
	 *
	 * @return the centro alta
	 */
	public String getCentroAlta() {
		return centroAlta;
	}

	/**
	 * Sets the centro alta.
	 *
	 * @param centroAlta the new centro alta
	 */
	public void setCentroAlta(String centroAlta) {
		this.centroAlta = centroAlta;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the pecalpar.
	 *
	 * @return the pecalpar
	 */
	public String getPecalpar() {
		return pecalpar;
	}

	/**
	 * Sets the pecalpar.
	 *
	 * @param pecalpar the new pecalpar
	 */
	public void setPecalpar(String pecalpar) {
		this.pecalpar = pecalpar;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the sub producto.
	 *
	 * @return the sub producto
	 */
	public String getSubProducto() {
		return subProducto;
	}

	/**
	 * Sets the sub producto.
	 *
	 * @param subProducto the new sub producto
	 */
	public void setSubProducto(String subProducto) {
		this.subProducto = subProducto;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the envio extrac.
	 *
	 * @return the envio extrac
	 */
	public String getEnvioExtrac() {
		return envioExtrac;
	}

	/**
	 * Sets the envio extrac.
	 *
	 * @param envioExtrac the new envio extrac
	 */
	public void setEnvioExtrac(String envioExtrac) {
		this.envioExtrac = envioExtrac;
	}

	/**
	 * Gets the periodicidad.
	 *
	 * @return the periodicidad
	 */
	public String getPeriodicidad() {
		return periodicidad;
	}

	/**
	 * Sets the periodicidad.
	 *
	 * @param periodicidad the new periodicidad
	 */
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	/**
	 * Gets the cta pivote.
	 *
	 * @return the cta pivote
	 */
	public String getCtaPivote() {
		return ctaPivote;
	}

	/**
	 * Sets the cta pivote.
	 *
	 * @param ctaPivote the new cta pivote
	 */
	public void setCtaPivote(String ctaPivote) {
		this.ctaPivote = ctaPivote;
	}
	
}
