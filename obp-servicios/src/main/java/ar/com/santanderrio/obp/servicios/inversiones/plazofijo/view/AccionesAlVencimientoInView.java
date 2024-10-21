/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class AccionesAlVencimientoInView.
 */
public class AccionesAlVencimientoInView {

	/** The codigo plazo. */
	@NotNull
	@Pattern(regexp = "[0-9]*")
	private String codigoPlazo;
	
	/** The error parcial. */
	private boolean errorParcial = false;
	
	private String cuentaPlazo;
	
	private String numeroSecuencia;
	
	private String fechaConstitucion;
	
	private String banca;
	
	private String numeroCuentaDebito;

	/**
	 * Gets the codigo plazo.
	 *
	 * @return the codigo plazo
	 */
	public String getCodigoPlazo() {
		return codigoPlazo;
	}

	/**
	 * Sets the codigo plazo.
	 *
	 * @param codigoPlazo
	 *            the new codigo plazo
	 */
	public void setCodigoPlazo(String codigoPlazo) {
		this.codigoPlazo = codigoPlazo;
	}

	/**
	 * Checks if is error parcial.
	 *
	 * @return true, if is error parcial
	 */
	public boolean isErrorParcial() {
		return errorParcial;
	}

	/**
	 * Sets the error parcial.
	 *
	 * @param errorParcial
	 *            the new error parcial
	 */
	public void setErrorParcial(boolean errorParcial) {
		this.errorParcial = errorParcial;
	}

	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
	}
	
}
