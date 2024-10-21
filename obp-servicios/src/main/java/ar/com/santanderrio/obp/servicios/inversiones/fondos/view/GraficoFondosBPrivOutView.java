/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficosFondosPorCuentaBPriv;

/**
 * The Class GraficoFondosBPrivOutView.
 *
 * @author juan.pablo.picate
 */
public class GraficoFondosBPrivOutView {

	/** The list graficos por cuenta. */
	List<GraficosFondosPorCuentaBPriv> listGraficosPorCuenta;

	/**
	 * Gets the list graficos por cuenta.
	 *
	 * @return the listGraficosPorCuenta
	 */
	public List<GraficosFondosPorCuentaBPriv> getListGraficosPorCuenta() {
		return listGraficosPorCuenta;
	}

	/**
	 * Sets the list graficos por cuenta.
	 *
	 * @param listGraficosPorCuenta
	 *            the listGraficosPorCuenta to set
	 */
	public void setListGraficosPorCuenta(List<GraficosFondosPorCuentaBPriv> listGraficosPorCuenta) {
		this.listGraficosPorCuenta = listGraficosPorCuenta;
	}

}
