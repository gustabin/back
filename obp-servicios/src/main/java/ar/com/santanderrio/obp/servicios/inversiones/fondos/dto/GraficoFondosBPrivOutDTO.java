/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.List;

/**
 * The Class GraficoFondosBPrivOutDTO.
 */
public class GraficoFondosBPrivOutDTO {

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
