/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.math.BigDecimal;

/**
 * The Class SimularPlazoFijoInDTO.
 *
 * @author juan.pablo.picate
 */
public class SimularPlazoFijoInDTO {

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/** The numero cuenta debito. */
	private String numeroCuentaDebito;

	/** The fecha alta. */
	private String fechaAlta;

	/** The plazo. */
	private String plazo;

	/** The importe plazo fijo. */
	private BigDecimal importePlazoFijo;

	/** The divisa. */
	private String divisa;

	/** The sucursal radicacion. */
	private String sucursalRadicacion;

	/** The cantidad dias. */
	private String cantidadDias;

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursalCuentaDebito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the sucursalCuentaDebito to set
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numeroCuentaDebito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the numeroCuentaDebito to set
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the importe plazo fijo.
	 *
	 * @return the importePlazoFijo
	 */
	public BigDecimal getImportePlazoFijo() {
		return importePlazoFijo;
	}

	/**
	 * Sets the importe plazo fijo.
	 *
	 * @param importePlazoFijo
	 *            the importePlazoFijo to set
	 */
	public void setImportePlazoFijo(BigDecimal importePlazoFijo) {
		this.importePlazoFijo = importePlazoFijo;
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
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the sucursal radicacion.
	 *
	 * @return the sucursalRadicacion
	 */
	public String getSucursalRadicacion() {
		return sucursalRadicacion;
	}

	/**
	 * Sets the sucursal radicacion.
	 *
	 * @param sucursalRadicacion
	 *            the sucursalRadicacion to set
	 */
	public void setSucursalRadicacion(String sucursalRadicacion) {
		this.sucursalRadicacion = sucursalRadicacion;
	}

	/**
	 * Gets the cantidad dias.
	 *
	 * @return the cantidadDias
	 */
	public String getCantidadDias() {
		return cantidadDias;
	}

	/**
	 * Sets the cantidad dias.
	 *
	 * @param cantidadDias
	 *            the cantidadDias to set
	 */
	public void setCantidadDias(String cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

}
