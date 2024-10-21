/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;

/**
 * The Class FirmarCuentasViewRequest.
 */
public class FirmarCuentasViewRequest {

	/** The email. */
	private String email;

	/** The cuentasPorFirmarList. */
	@NotNull
	private List<CuentaCustodiaView> cuentasCustodia = new ArrayList<CuentaCustodiaView>();
	
	private boolean esCanje;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the cuentas custodia.
	 *
	 * @return the cuentas custodia
	 */
	public List<CuentaCustodiaView> getCuentasCustodia() {
		return cuentasCustodia;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the cuentas custodia.
	 *
	 * @param cuentasCustodia
	 *            the new cuentas custodia
	 */
	public void setCuentasCustodia(List<CuentaCustodiaView> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	public boolean isEsCanje() {
		return esCanje;
	}

	public void setEsCanje(boolean esCanje) {
		this.esCanje = esCanje;
	}
	
}
