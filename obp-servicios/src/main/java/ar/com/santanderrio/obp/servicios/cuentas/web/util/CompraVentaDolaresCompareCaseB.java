/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CompraVentaDolaresCompareCaseB.
 */
public class CompraVentaDolaresCompareCaseB extends AbstractStrategyCompareCompraVentaDolares {

	/**
	 * Instantiates a new compra venta dolares compare case B.
	 *
	 * @param ob1
	 *            the ob 1
	 * @param obj2
	 *            the obj 2
	 */
	public CompraVentaDolaresCompareCaseB(ResumenDetalleCuenta ob1, ResumenDetalleCuenta obj2) {
		super(ob1, obj2);
	}

	/** The valor uno. */
	Boolean valorUno = esCuentaFavorita(obj2)
			|| esCuentaTitular(ob1) && esCuentaTitular(obj2) && !esCuentaPrincipal(ob1) && esCuentaPrincipal(obj2)
			|| !esCuentaTitular(ob1) && esCuentaTitular(obj2)
			|| esCuentaCotitular(ob1) && esCuentaCotitular(obj2) && !esCuentaPrincipal(ob1) && esCuentaPrincipal(obj2);

	/**
	 * Gets the valor uno.
	 *
	 * @return the valor uno
	 */
	public Boolean getValorUno() {
		return valorUno;
	}

}
