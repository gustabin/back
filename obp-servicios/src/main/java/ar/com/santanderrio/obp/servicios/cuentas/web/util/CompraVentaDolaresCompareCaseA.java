/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CompraVentaDolaresCompareCaseA.
 */
public class CompraVentaDolaresCompareCaseA extends AbstractStrategyCompareCompraVentaDolares {

	/**
	 * Instantiates a new compra venta dolares compare case A.
	 *
	 * @param ob1
	 *            the ob 1
	 * @param obj2
	 *            the obj 2
	 */
	public CompraVentaDolaresCompareCaseA(ResumenDetalleCuenta ob1, ResumenDetalleCuenta obj2) {
		super(ob1, obj2);
	}

	/** The valor menos uno. */
	// Aca van los casos a ordenar
	Boolean valorMenosUno = (!esCuentaFavorita(obj2))
			&& (esCuentaFavorita(ob1) || esCuentaTitular(ob1) && esCuentaTitular(obj2) && esCuentaPrincipal(ob1)
					|| esCuentaTitular(ob1) && !esCuentaTitular(obj2)
					|| esCuentaCotitular(ob1) && esCuentaCotitular(obj2) && esCuentaPrincipal(ob1));

	/**
	 * Gets the valor menos uno.
	 *
	 * @return the valor menos uno
	 */
	public Boolean getValorMenosUno() {
		return valorMenosUno;
	}

}
