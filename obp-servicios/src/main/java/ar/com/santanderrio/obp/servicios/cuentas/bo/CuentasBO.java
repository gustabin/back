/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Interface CuentasBO.
 *
 * @author florencia.n.martinez
 */
public interface CuentasBO {

	/**
	 * Es caja ahorro.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCajaAhorro(TipoCuenta tipoCuenta);

	/**
	 * Es cuenta corriente.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCuentaCorriente(TipoCuenta tipoCuenta);

	/**
	 * Es caja ahorro dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCajaAhorroDolares(TipoCuenta tipoCuenta);

	/**
	 * Es cuenta cte dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCuentaCteDolares(TipoCuenta tipoCuenta);

	/**
	 * Es cuenta unica.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCuentaUnica(TipoCuenta tipoCuenta);

	/**
	 * Es caja ahorro pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCajaAhorroPesos(TipoCuenta tipoCuenta);

	/**
	 * Es cuenta cte pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCuentaCtePesos(TipoCuenta tipoCuenta);

	/**
	 * Es cuenta unica pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCuentaUnicaPesos(TipoCuenta tipoCuenta);

	/**
	 * Es cuenta unica dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the boolean
	 */
	Boolean esCuentaUnicaDolares(TipoCuenta tipoCuenta);

}