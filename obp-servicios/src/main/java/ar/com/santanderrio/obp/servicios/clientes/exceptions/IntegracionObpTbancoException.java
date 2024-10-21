/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;

/**
 * Si Seginform devuelve un nup el cual se debe redirigir a obp cortar el flujo
 * de trabajo.
 * 
 * @author sergio.e.goldentair
 *
 */
public class IntegracionObpTbancoException extends RuntimeException {
    /** Informacion necesaria para armar el token. **/
    private final ResumenCliente resumenCliente;
    /** Informacion necesaria para armar el token parte dos. **/
    private final CredencialCliente credencialCliente;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6925979303269377575L;

    /**
	 * Instantiates a new integracion obp tbanco exception.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @param credencialCliente
	 *            the credencial cliente
	 */
    public IntegracionObpTbancoException(ResumenCliente resumenCliente, CredencialCliente credencialCliente) {
        super();
        this.resumenCliente = resumenCliente;
        this.credencialCliente = credencialCliente;
    }

    /**
     * Gets the resumen cliente.
     *
     * @return the resumenCliente
     */
    public ResumenCliente getResumenCliente() {
        return resumenCliente;
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
