/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.controller;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class TipoCuentaPredicate.
 */
public class TipoCuentaPredicate implements Predicate {

	/** The Constant INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO. */
	private static final String INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO = "01";

	/** The Constant INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE. */
	private static final String INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE = "00";

	/** The Constant INDICADOR_MOVIMIENTO_CUENTA_UNICA. */
	private static final String INDICADOR_MOVIMIENTO_CUENTA_UNICA = "02";

	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;

	/**
	 * Instantiates a new tipo cuenta predicate.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 */
	public TipoCuentaPredicate(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
	 */
	@Override
	public boolean evaluate(Object object) {
		Movimiento detalleMovimiento = (Movimiento) object;
		boolean flag = false;
		switch (tipoCuenta) {
		case CAJA_AHORRO_PESOS:
			flag = Boolean.TRUE.equals(
					detalleMovimiento.getIndicadorMovimiento().trim().equals(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO));
			break;
		case CUENTA_CORRIENTE_PESOS:
			flag = Boolean.TRUE.equals(
					detalleMovimiento.getIndicadorMovimiento().trim().equals(INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE));
			break;
		case CUENTA_UNICA:
			flag = Boolean.TRUE.equals(
					detalleMovimiento.getIndicadorMovimiento().trim().equals(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO))
					|| Boolean.TRUE.equals(detalleMovimiento.getIndicadorMovimiento().trim()
							.equals(INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE));
			break;
		default:
			break;
		}

		return flag;
	}

}
