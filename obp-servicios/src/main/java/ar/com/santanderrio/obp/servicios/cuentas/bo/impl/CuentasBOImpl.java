/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentasBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentasBOImpl.
 *
 * @author florencia.n.martinez
 */
// @Component
public class CuentasBOImpl implements CuentasBO {

	/**
	 * Es caja ahorro. TipoCuenta.CAJA_AHORRO_PESOS (1).
	 * TipoCuenta.CAJA_AHORRO_DOLARES (4).
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCajaAhorro(TipoCuenta tipoCuenta) {
		if (esCajaAhorroPesos(tipoCuenta) || esCajaAhorroDolares(tipoCuenta)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Es cuenta corriente. TipoCuenta.CUENTA_CORRIENTE_PESOS (0).
	 * TipoCuenta.CUENTA_CORRIENTE_DOLARES (3).
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCuentaCorriente(TipoCuenta tipoCuenta) {
		if (esCuentaCtePesos(tipoCuenta) || esCuentaCteDolares(tipoCuenta)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Es cuenta unica. TipoCuenta.CUENTA_UNICA (2).
	 * TipoCuenta.CUENTA_UNICA_PESOS (9). TipoCuenta.CUENTA_UNICA_DOLARES (10).
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCuentaUnica(TipoCuenta tipoCuenta) {
		if (tipoCuenta.equals(TipoCuenta.CUENTA_UNICA) || esCuentaUnicaPesos(tipoCuenta)
				|| esCuentaUnicaDolares(tipoCuenta)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Es caja ahorro en dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCajaAhorroDolares(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES);
	}

	/**
	 * Es cuenta corriente en dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCuentaCteDolares(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
	}

	/**
	 * Es caja ahorro en pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return true, if successful
	 */
	@Override
	public Boolean esCajaAhorroPesos(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS);
	}

	/**
	 * Es cuenta corriente en pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCuentaCtePesos(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS);
	}

	/**
	 * Es cuenta unica en pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCuentaUnicaPesos(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS);
	}

	/**
	 * Es cuenta unica en dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esCuentaUnicaDolares(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES);
	}
}
