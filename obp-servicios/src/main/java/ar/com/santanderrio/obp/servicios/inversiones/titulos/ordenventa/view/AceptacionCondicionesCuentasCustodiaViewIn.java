/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;

/**
 * The Class AceptacionCondicionesCuentasCustodiaViewIn.
 */
public class AceptacionCondicionesCuentasCustodiaViewIn {

	/** The email. */
	private String email;

	/** The cuentasCustodia. */
	private List<CuentaCustodiaView> cuentasCustodia = new ArrayList<CuentaCustodiaView>();

	/** The esBancaPrivada. */
	private Boolean esBancaPrivada = false;

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
	 * Gets the es banca privada.
	 *
	 * @return the esBancaPrivada
	 */
	public Boolean getEsBancaPrivada() {
		return esBancaPrivada;
	}

	/**
	 * Sets the es banca privada.
	 *
	 * @param esBancaPrivada
	 *            the esBancaPrivada to set
	 */
	public void setEsBancaPrivada(Boolean esBancaPrivada) {
		this.esBancaPrivada = esBancaPrivada;
	}

}
