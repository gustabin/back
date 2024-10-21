/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CompraVentaDolaresCompareCaseC.
 */
public class CompraVentaDolaresCompareCaseC extends AbstractStrategyCompareCompraVentaDolares {

	/**
	 * Instantiates a new compra venta dolares compare case C.
	 *
	 * @param ob1
	 *            the ob 1
	 * @param obj2
	 *            the obj 2
	 */
	public CompraVentaDolaresCompareCaseC(ResumenDetalleCuenta ob1, ResumenDetalleCuenta obj2) {
		super(ob1, obj2);
	}

	/** The valor cero. */
	Boolean valorCero = esCuentaTitular(ob1) && esCuentaTitular(obj2) && !esCuentaPrincipal(ob1)
			&& !esCuentaPrincipal(obj2)
			|| esCuentaCotitular(ob1) && esCuentaCotitular(obj2) && !esCuentaPrincipal(ob1) && !esCuentaPrincipal(obj2);

	/**
	 * Gets the valor cero.
	 *
	 * @return the valor cero
	 */
	public Boolean getValorCero() {
		return valorCero;
	}

}
