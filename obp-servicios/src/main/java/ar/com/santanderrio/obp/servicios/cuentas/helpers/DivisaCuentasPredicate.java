/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.helpers;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;

/**
 * The Class DivisaCuentasPredicate.
 */
public class DivisaCuentasPredicate implements Predicate {

	/** The moneda. */
	private DivisaEnum moneda;

	/**
	 * Instantiates a new divisa cuentas predicate.
	 *
	 * @param moneda
	 *            the moneda
	 */
	public DivisaCuentasPredicate(DivisaEnum moneda) {
		this.moneda = moneda;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
	 */
	@Override
	public boolean evaluate(Object object) {
		boolean flag = false;
		Movimiento movimiento = (Movimiento) object;
		if (moneda == null) {
			flag = true;
		} else if (DivisaEnum.DOLAR.equals(moneda)) {
			flag = DivisaEnum.DOLAR.getCodigo().equals(movimiento.getMonedaMovimiento());
		} else if (DivisaEnum.PESO.equals(moneda)) {
			flag = DivisaEnum.PESO.getCodigo().equals(movimiento.getMonedaMovimiento());
		}
		return flag;
	}

}