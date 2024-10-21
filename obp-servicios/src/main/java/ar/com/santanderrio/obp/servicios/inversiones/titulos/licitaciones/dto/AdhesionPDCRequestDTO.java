/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class AdhesionPDCRequestDTO.
 */
public class AdhesionPDCRequestDTO extends View{

	/** The serial VersionUID. */
	private static final long serialVersionUID = -8990587491934980470L;

	/** The nro cuenta titulos. */
	private String nroCuentaTitulos;
	
	/** The nro cuenta operativa. */
	private String nroCuentaOperativa;
	
	/** The tipo cuenta. */
	private String tipoCuenta;
	
	/** The nro sucursal. */
	private String nroSucursal;
	
	/** The producto. */
	private String producto;
	
	/** The sub producto. */
	private String subProducto;
	
	/** The moneda. */
	private String moneda;
	
	/** The Id sim cuenta pdc. */
	private String IdSimCuentaPdc;

	/**
	 * Gets the nro cuenta titulos.
	 *
	 * @return the nro cuenta titulos
	 */
	public String getNroCuentaTitulos() {
		return nroCuentaTitulos;
	}

	/**
	 * Sets the nro cuenta titulos.
	 *
	 * @param nroCuentaTitulos
	 *            the new nro cuenta titulos
	 */
	public void setNroCuentaTitulos(String nroCuentaTitulos) {
		this.nroCuentaTitulos = nroCuentaTitulos;
	}

	/**
	 * Gets the nro cuenta operativa.
	 *
	 * @return the nro cuenta operativa
	 */
	public String getNroCuentaOperativa() {
		return nroCuentaOperativa;
	}

	/**
	 * Sets the nro cuenta operativa.
	 *
	 * @param nroCuentaOperativa
	 *            the new nro cuenta operativa
	 */
	public void setNroCuentaOperativa(String nroCuentaOperativa) {
		this.nroCuentaOperativa = nroCuentaOperativa;
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
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nro sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the new nro sucursal
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
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
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the id sim cuenta pdc.
	 *
	 * @return the id sim cuenta pdc
	 */
	public String getIdSimCuentaPdc() {
		return IdSimCuentaPdc;
	}

	/**
	 * Sets the id sim cuenta pdc.
	 *
	 * @param idSimCuentaPdc
	 *            the new id sim cuenta pdc
	 */
	public void setIdSimCuentaPdc(String idSimCuentaPdc) {
		IdSimCuentaPdc = idSimCuentaPdc;
	}

}
