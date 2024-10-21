/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class SimularPrecancelarInView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimularPrecancelarInView {

	/** The cuenta plazo. */
	@NotNull
	private String cuentaPlazo;

	/** The secuencia. */
	@NotNull
	private String secuencia;

	/** The tipo cuenta destino. */
	@NotNull
	private String tipoCuentaDestino;

	/** The numero cuenta destino. */
	@NotNull
	private String numeroCuentaDestino;

	/** The moneda. */
	@NotNull
	@Pattern(regexp = "(ARS)|(USD)")
	private String moneda;

	/** The tipo plazo. */
	private String tipoPlazo;
	
	private boolean uva;
	
	private String banca;
	
	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuenta plazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the new cuenta plazo
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaAccionAlVencimiento
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaAccionAlVencimiento) {
		this.tipoCuentaDestino = tipoCuentaAccionAlVencimiento;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numero cuenta destino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the new numero cuenta destino
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
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
	 * Gets the tipo plazo.
	 *
	 * @return the tipo plazo
	 */
	public String getTipoPlazo() {
		return tipoPlazo;
	}

	/**
	 * Sets the tipo plazo.
	 *
	 * @param tipoPlazo
	 *            the new tipo plazo
	 */
	public void setTipoPlazo(String tipoPlazo) {
		this.tipoPlazo = tipoPlazo;
	}

	public boolean isUva() {
		return uva;
	}

	public void setUva(boolean uva) {
		this.uva = uva;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}
	
}
