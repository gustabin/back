/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * A factory for creating TokenVisaAmex objects.
 *
 * @author sergio.e.goldentair
 */
public interface TokenVisaAmexFactory {

	/**
	 * Creates a new TokenVisaAmex object.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacionTarjetaWSEnum
	 *            the operacion tarjeta WS enum
	 * @return the token visa amex
	 */
	TokenVisaAmex createToken(Cuenta cuenta, OperacionTarjetaWSEnum operacionTarjetaWSEnum);
}
