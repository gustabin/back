/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;

/**
 * The Class DivisaPredicate.
 */
public class DivisaPredicate implements Predicate {

	/** The moneda. */
	private DivisaEnum moneda;

	/**
	 * Instantiates a new divisa predicate.
	 *
	 * @param moneda
	 *            the moneda
	 */
	public DivisaPredicate(DivisaEnum moneda) {
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
		Movimiento detalleMovimiento = (Movimiento) object;
		if (moneda == null) {
			flag = true;
		} else if (DivisaEnum.DOLAR.equals(moneda)) {
			flag = Boolean.TRUE
					.equals(detalleMovimiento.getMonedaMovimiento().trim().equals(DivisaEnum.DOLAR.getCodigo()));
		} else if (DivisaEnum.PESO.equals(moneda)) {
			flag = Boolean.TRUE
					.equals(detalleMovimiento.getMonedaMovimiento().trim().equals(DivisaEnum.PESO.getCodigo()));
		}
		return flag;
	}

}
