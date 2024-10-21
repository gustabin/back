/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class AbstractStrategyCompareCompraVentaDolares.
 */
public abstract class AbstractStrategyCompareCompraVentaDolares {

	/** The obj 2. */
	ResumenDetalleCuenta ob1, obj2;

	/**
	 * Instantiates a new abstract strategy compare compra venta dolares.
	 *
	 * @param ob1
	 *            the ob 1
	 * @param obj2
	 *            the obj 2
	 */
	AbstractStrategyCompareCompraVentaDolares(ResumenDetalleCuenta ob1, ResumenDetalleCuenta obj2) {
		this.ob1 = ob1;
		this.obj2 = obj2;
	};

	/**
	 * Es cuenta favorita.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaFavorita(ResumenDetalleCuenta cuenta) {
		return cuenta.isFavorita();
	}

	/**
	 * Es cuenta titular.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaTitular(ResumenDetalleCuenta cuenta) {
		return StringUtils.equals(cuenta.getCodigoTitularidad(), "TI");
	}

	/**
	 * Es cuenta cotitular.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaCotitular(ResumenDetalleCuenta cuenta) {
		return StringUtils.equals(cuenta.getCodigoTitularidad(), "CT");
	}

	/**
	 * Es cuenta principal.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaPrincipal(ResumenDetalleCuenta cuenta) {
		return cuenta.isCuentaPrincipal();
	}

}
