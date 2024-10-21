/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DatosTransferenciaOutView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosTransferenciaOutView {

	/** The comboGastosACargo. */
	private List<ComboView> comboGastosACargo;

	/** The comboCodigoBancario. */
	private List<ComboView> comboCodigoBancario;

	/** The cuentas origen unificadas. */
	private List<CuentaView> cuentas;
	
	/** The cuentas origen dolares. */
	private List<CuentaView> cuentasEnDolares;

	/**
	 * Gets the combo gastos A cargo.
	 *
	 * @return the comboGastosACargo
	 */
	public List<ComboView> getComboGastosACargo() {
		return comboGastosACargo;
	}

	/**
	 * Sets the combo gastos A cargo.
	 *
	 * @param comboGastosACargo the comboGastosACargo to set
	 */
	public void setComboGastosACargo(List<ComboView> comboGastosACargo) {
		this.comboGastosACargo = comboGastosACargo;
	}

	/**
	 * Gets the combo codigo bancario.
	 *
	 * @return the comboCodigoBancario
	 */
	public List<ComboView> getComboCodigoBancario() {
		return comboCodigoBancario;
	}

	/**
	 * Sets the combo codigo bancario.
	 *
	 * @param comboCodigoBancario the comboCodigoBancario to set
	 */
	public void setComboCodigoBancario(List<ComboView> comboCodigoBancario) {
		this.comboCodigoBancario = comboCodigoBancario;
	}

	/**
	 * @return the cuentas
	 */
	public List<CuentaView> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas the cuentas to set
	 */
	public void setCuentas(List<CuentaView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @return the cuentasEnDolares
	 */
	public List<CuentaView> getCuentasEnDolares() {
		return cuentasEnDolares;
	}

	/**
	 * @param cuentasEnDolares the cuentasEnDolares to set
	 */
	public void setCuentasEnDolares(List<CuentaView> cuentasEnDolares) {
		this.cuentasEnDolares = cuentasEnDolares;
	}

}
