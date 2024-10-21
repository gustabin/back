/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.beanio.annotation.Field;

/**
 * The Class PaqueteEntity.
 */
public class PaqueteEntity implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 7407548613204447730L;

    /** The tipo cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
	private String tipoCuenta;

	/** The sucursal cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{3}|[ ]{3}$")
	private String sucursalCuenta;

	/** The numero cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{7}|[ ]{7}$")
	private String numeroCuenta;

	/** The entidad. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String entidad;

	/** The sucursal. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String sucursal;

	/** The producto. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
	private String producto;

	/** The sub producto. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String subProducto;

	/** The numero cuenta altair. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{12}|[ ]{12}$")
	private String numeroCuentaAltair;

	/** The divisa. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{3}|[ ]{3}$")
	private String divisa;

	/** The indicador paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{1}|[ ]{1}$")
	private String indicadorPaquete;

	/** The entidad paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String entidadPaquete;

	/** The centro altair paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String centroAltairPaquete;

	/** The paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{12}|[ ]{12}$")
	private String paquete;

	/** The producto paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
	private String productoPaquete;

	/** The sub producto paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String subProductoPaquete;

	/** The indicador estado paquete. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{1}|[ ]{1}$")
	private String indicadorEstadoPaquete;

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
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the entidad.
	 *
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * Sets the entidad.
	 *
	 * @param entidad
	 *            the new entidad
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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
	 * @param producto
	 *            the new producto
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
	 * @param subProducto
	 *            the new sub producto
	 */
	public void setSubProducto(String subProducto) {
		this.subProducto = subProducto;
	}

	/**
	 * Gets the numero cuenta altair.
	 *
	 * @return the numero cuenta altair
	 */
	public String getNumeroCuentaAltair() {
		return numeroCuentaAltair;
	}

	/**
	 * Sets the numero cuenta altair.
	 *
	 * @param numeroCuentaAltair
	 *            the new numero cuenta altair
	 */
	public void setNumeroCuentaAltair(String numeroCuentaAltair) {
		this.numeroCuentaAltair = numeroCuentaAltair;
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
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the indicador paquete.
	 *
	 * @return the indicador paquete
	 */
	public String getIndicadorPaquete() {
		return indicadorPaquete;
	}

	/**
	 * Sets the indicador paquete.
	 *
	 * @param indicadorPaquete
	 *            the new indicador paquete
	 */
	public void setIndicadorPaquete(String indicadorPaquete) {
		this.indicadorPaquete = indicadorPaquete;
	}

	/**
	 * Gets the entidad paquete.
	 *
	 * @return the entidad paquete
	 */
	public String getEntidadPaquete() {
		return entidadPaquete;
	}

	/**
	 * Sets the entidad paquete.
	 *
	 * @param entidadPaquete
	 *            the new entidad paquete
	 */
	public void setEntidadPaquete(String entidadPaquete) {
		this.entidadPaquete = entidadPaquete;
	}

	/**
	 * Gets the centro altair paquete.
	 *
	 * @return the centro altair paquete
	 */
	public String getCentroAltairPaquete() {
		return centroAltairPaquete;
	}

	/**
	 * Sets the centro altair paquete.
	 *
	 * @param centroAltairPaquete
	 *            the new centro altair paquete
	 */
	public void setCentroAltairPaquete(String centroAltairPaquete) {
		this.centroAltairPaquete = centroAltairPaquete;
	}

	/**
	 * Gets the paquete.
	 *
	 * @return the paquete
	 */
	public String getPaquete() {
		return paquete;
	}

	/**
	 * Sets the paquete.
	 *
	 * @param paquete
	 *            the new paquete
	 */
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	/**
	 * Gets the producto paquete.
	 *
	 * @return the producto paquete
	 */
	public String getProductoPaquete() {
		return productoPaquete;
	}

	/**
	 * Sets the producto paquete.
	 *
	 * @param productoPaquete
	 *            the new producto paquete
	 */
	public void setProductoPaquete(String productoPaquete) {
		this.productoPaquete = productoPaquete;
	}

	/**
	 * Gets the sub producto paquete.
	 *
	 * @return the sub producto paquete
	 */
	public String getSubProductoPaquete() {
		return subProductoPaquete;
	}

	/**
	 * Sets the sub producto paquete.
	 *
	 * @param subProductoPaquete
	 *            the new sub producto paquete
	 */
	public void setSubProductoPaquete(String subProductoPaquete) {
		this.subProductoPaquete = subProductoPaquete;
	}

	/**
	 * Gets the indicador estado paquete.
	 *
	 * @return the indicador estado paquete
	 */
	public String getIndicadorEstadoPaquete() {
		return indicadorEstadoPaquete;
	}

	/**
	 * Sets the indicador estado paquete.
	 *
	 * @param indicadorEstadoPaquete
	 *            the new indicador estado paquete
	 */
	public void setIndicadorEstadoPaquete(String indicadorEstadoPaquete) {
		this.indicadorEstadoPaquete = indicadorEstadoPaquete;
	}

}
