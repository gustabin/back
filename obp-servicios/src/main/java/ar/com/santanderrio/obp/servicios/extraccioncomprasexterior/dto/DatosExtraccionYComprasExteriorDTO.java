/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto;

import java.util.List;

/**
 * The Class DatosExtraccionYComprasExteriorDTO.
 */
public class DatosExtraccionYComprasExteriorDTO {

	/** The cuentas operacion exterior. */
	private List<CuentaOperacionExteriorDTO> cuentasOperacionExterior;

	/** The tarjetas operacion exterior. */
	private List<TarjetaOperacionExteriorDTO> tarjetasOperacionExterior;

	/** The cuenta seleccionada. */
	private String cuentaSeleccionada;

	/** The tarjeta seleccionada. */
	private String tarjetaSeleccionada;

	/** The descripcion cuenta. */
	private String descripcionCuenta;

	/**
	 * Gets the cuentas operacion exterior.
	 *
	 * @return the cuentas operacion exterior
	 */
	public List<CuentaOperacionExteriorDTO> getCuentasOperacionExterior() {
		return cuentasOperacionExterior;
	}

	/**
	 * Sets the cuentas operacion exterior.
	 *
	 * @param cuentasOperacionExterior
	 *            the new cuentas operacion exterior
	 */
	public void setCuentasOperacionExterior(List<CuentaOperacionExteriorDTO> cuentasOperacionExterior) {
		this.cuentasOperacionExterior = cuentasOperacionExterior;
	}

	/**
	 * Gets the tarjetas operacion exterior.
	 *
	 * @return the tarjetas operacion exterior
	 */
	public List<TarjetaOperacionExteriorDTO> getTarjetasOperacionExterior() {
		return tarjetasOperacionExterior;
	}

	/**
	 * Sets the tarjetas operacion exterior.
	 *
	 * @param tarjetasOperacionExterior
	 *            the new tarjetas operacion exterior
	 */
	public void setTarjetasOperacionExterior(List<TarjetaOperacionExteriorDTO> tarjetasOperacionExterior) {
		this.tarjetasOperacionExterior = tarjetasOperacionExterior;
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuenta seleccionada
	 */
	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuentaSeleccionada
	 *            the new cuenta seleccionada
	 */
	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * Gets the tarjeta seleccionada.
	 *
	 * @return the tarjeta seleccionada
	 */
	public String getTarjetaSeleccionada() {
		return tarjetaSeleccionada;
	}

	/**
	 * Sets the tarjeta seleccionada.
	 *
	 * @param tarjetaSeleccionada
	 *            the new tarjeta seleccionada
	 */
	public void setTarjetaSeleccionada(String tarjetaSeleccionada) {
		this.tarjetaSeleccionada = tarjetaSeleccionada;
	}

	/**
	 * Gets the descripcion cuenta.
	 *
	 * @return the descripcion cuenta
	 */
	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}

	/**
	 * Sets the descripcion cuenta.
	 *
	 * @param descripcionCuenta
	 *            the new descripcion cuenta
	 */
	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}

}
