/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosEnum;

/**
 * The Class MovimientosPredicate.
 */
public class MovimientosPredicate implements Predicate {

	/** The movimientos. */
	private MovimientosEnum movimientos;

	/**
	 * Instantiates a new movimientos predicate.
	 *
	 * @param movimientos
	 *            the movimientos
	 */
	public MovimientosPredicate(MovimientosEnum movimientos) {
		this.movimientos = movimientos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
	 */
	@Override
	public boolean evaluate(Object object) {
		boolean flag = false;
		switch (movimientos) {
		case MOVIMIENTO_CAJA_DE_AHORRO:
			flag = MovimientosEnum.MOVIMIENTO_CAJA_DE_AHORRO
					.equals(MovimientosEnum.fromIndicadorString(((Movimiento) object).getIndicadorMovimiento()));
			break;
		case MOVIMIENTO_CUENTA_CORRIENTE:
			flag = MovimientosEnum.MOVIMIENTO_CUENTA_CORRIENTE
					.equals(MovimientosEnum.fromIndicadorString(((Movimiento) object).getIndicadorMovimiento()));
			break;
		case MOVIMIENTO_CUENTA_UNICA:
			flag = MovimientosEnum.MOVIMIENTO_CUENTA_UNICA
					.equals(MovimientosEnum.fromIndicadorString(((Movimiento) object).getIndicadorMovimiento()));
			break;
		default:
			flag = false;
		}
		return flag;
	}

}
