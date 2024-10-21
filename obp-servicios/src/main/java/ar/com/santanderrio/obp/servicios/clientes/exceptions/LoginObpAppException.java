/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;

public class LoginObpAppException extends RuntimeException {
    /** Informacion necesaria para armar el token parte dos. **/
    private final CredencialCliente credencialCliente;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6925979316527377575L;

    /**
	 * Instantiates a new integracion obp tbanco exception.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @param credencialCliente
	 *            the credencial cliente
	 */
    public LoginObpAppException(CredencialCliente credencialCliente) {
        super();
        this.credencialCliente = credencialCliente;
    }

    /**
	 * Gets the credencial cliente.
	 *
	 * @return the credencialCliente
	 */
    public CredencialCliente getCredencialCliente() {
        return credencialCliente;
    }

}
