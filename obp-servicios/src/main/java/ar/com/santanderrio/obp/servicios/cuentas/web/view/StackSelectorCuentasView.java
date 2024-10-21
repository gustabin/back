package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;
/**
 * The Class StackSelectorCuentasView.
 */
public class StackSelectorCuentasView {
	/** The cuentas. */
	private List<StackCuentaSelectorView> cuentas;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<StackCuentaSelectorView> getCuentas() {
		return cuentas;
	}

	/**
	 * Setter para cuentas.
	 *
	 * @param cuentas
	 *            el nuevo cuentas
	 */
	public void setCuentas(List<StackCuentaSelectorView> cuentas) {
		this.cuentas = cuentas;
	}	
}
