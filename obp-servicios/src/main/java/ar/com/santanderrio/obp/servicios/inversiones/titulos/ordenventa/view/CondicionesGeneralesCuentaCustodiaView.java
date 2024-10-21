/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;

/**
 * The Class CondicionesGeneralesCuentaCustodiaView.
 */
public class CondicionesGeneralesCuentaCustodiaView {

	/** The condiciones aceptadas. */
	private Boolean condicionesAceptadas;
	
	/** The legal 1. */
	private String legal1;
	
	/** The legal 2. */
	private String legal2;
	
	/** The cuentas custodia. */
	private List<CuentaCustodiaView> cuentasCustodia;
	
	/** The cuentas firmadas. */
	private List<CuentaCustodiaView> cuentasFirmadas;
	
	/** The legal 3. */
	private String legal3;
	
	/** The legal adicional. */
	private String legalAdicional;
	
	/** The email. */
	private String email;
	
	/** The comprobantes CNV disponibles. */
	private boolean comprobantesCNVDisponibles;

	/**
	 * Gets the condiciones aceptadas.
	 *
	 * @return the condicionesAceptadas
	 */
	public Boolean getCondicionesAceptadas() {
		return condicionesAceptadas;
	}

	/**
	 * Sets the condiciones aceptadas.
	 *
	 * @param condicionesAceptadas
	 *            the condicionesAceptadas to set
	 */
	public void setCondicionesAceptadas(Boolean condicionesAceptadas) {
		this.condicionesAceptadas = condicionesAceptadas;
	}

	/**
	 * Gets the legal 1.
	 *
	 * @return the legal1
	 */
	public String getLegal1() {
		return legal1;
	}

	/**
	 * Sets the legal 1.
	 *
	 * @param legal1
	 *            the legal1 to set
	 */
	public void setLegal1(String legal1) {
		this.legal1 = legal1;
	}

	/**
	 * Gets the legal 2.
	 *
	 * @return the legal2
	 */
	public String getLegal2() {
		return legal2;
	}

	/**
	 * Sets the legal 2.
	 *
	 * @param legal2
	 *            the legal2 to set
	 */
	public void setLegal2(String legal2) {
		this.legal2 = legal2;
	}

	/**
	 * Gets the cuentas custodia.
	 *
	 * @return the cuentasCustodia
	 */
	public List<CuentaCustodiaView> getCuentasCustodia() {
		return cuentasCustodia;
	}

	/**
	 * Sets the cuentas custodia.
	 *
	 * @param cuentasCustodia
	 *            the cuentasCustodia to set
	 */
	public void setCuentasCustodia(List<CuentaCustodiaView> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	/**
	 * Gets the cuentas firmadas.
	 *
	 * @return the cuentasFirmadas
	 */
	public List<CuentaCustodiaView> getCuentasFirmadas() {
		return cuentasFirmadas;
	}

	/**
	 * Sets the cuentas firmadas.
	 *
	 * @param cuentasFirmadas
	 *            the cuentasFirmadas to set
	 */
	public void setCuentasFirmadas(List<CuentaCustodiaView> cuentasFirmadas) {
		this.cuentasFirmadas = cuentasFirmadas;
	}

	/**
	 * Gets the legal 3.
	 *
	 * @return the legal3
	 */
	public String getLegal3() {
		return legal3;
	}

	/**
	 * Sets the legal 3.
	 *
	 * @param legal3
	 *            the legal3 to set
	 */
	public void setLegal3(String legal3) {
		this.legal3 = legal3;
	}

	/**
	 * Gets the legal adicional.
	 *
	 * @return the legalAdicional
	 */
	public String getLegalAdicional() {
		return legalAdicional;
	}

	/**
	 * Sets the legal adicional.
	 *
	 * @param legalAdicional
	 *            the legalAdicional to set
	 */
	public void setLegalAdicional(String legalAdicional) {
		this.legalAdicional = legalAdicional;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Checks if is comprobantes CNV disponibles.
	 *
	 * @return the comprobantesCNVDisponibles
	 */
	public boolean isComprobantesCNVDisponibles() {
		return comprobantesCNVDisponibles;
	}

	/**
	 * Sets the comprobantes CNV disponibles.
	 *
	 * @param comprobantesCNVDisponibles
	 *            the comprobantesCNVDisponibles to set
	 */
	public void setComprobantesCNVDisponibles(boolean comprobantesCNVDisponibles) {
		this.comprobantesCNVDisponibles = comprobantesCNVDisponibles;
	}

}
