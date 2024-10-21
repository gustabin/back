package ar.com.santanderrio.obp.servicios.tarjetas.util;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * A factory for creating TokenW2W objects.
 *
 */
public interface TokenW2WFactory {

	/**
	 * Creates a new TokenW2W object.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the TokenW2W
	 */
	TokenW2W createToken(Cuenta cuenta, Cliente cliente);
}
