/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class DetalleCuenta.
 */
/**
 * @author david_suyo
 *
 */
public class ConsultaDetalleCuentaInEntity {

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The cliente. */
	private Cliente cliente;

	/** The entidad. */
	private String entidad;
	
	/** The sucurusal. */
	private String sucursal;
	
	/** The nro_cuenta_add. */
	private String nroCuentaAdd;
	
	/** The divisa. */
	private String divisa;
	
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
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the nroCuentaAdd
	 */
	public String getNroCuentaAdd() {
		return nroCuentaAdd;
	}

	/**
	 * @param nroCuentaAdd the nroCuentaAdd to set
	 */
	public void setNroCuentaAdd(String nroCuentaAdd) {
		this.nroCuentaAdd = nroCuentaAdd;
	}

	/**
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * @param divisa the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaDetalleCuentaInEntity [tipoCuenta=" + tipoCuenta + ", sucursalCuenta=" + sucursalCuenta
				+ ", nroCuenta=" + nroCuenta + ", cliente=" + cliente + "]";
	}


}
