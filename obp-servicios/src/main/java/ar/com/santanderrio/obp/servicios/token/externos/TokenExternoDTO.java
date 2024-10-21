/**
 * 
 */
package ar.com.santanderrio.obp.servicios.token.externos;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Poder recibir en los bo toda la info requerida por cada canal que requiera
 * hacer un salto externo.
 * 
 * @author sergio.e.goldentair
 *
 */
public class TokenExternoDTO {
    /**
     * Informacion del cliente logueado.
     */
    private Cliente cliente;

    /**
	 * Instantiates a new token externo DTO.
	 *
	 * @param cliente
	 *            the cliente
	 */
    public TokenExternoDTO(Cliente cliente) {
        super();
        this.cliente = cliente;
    }

    /**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
    public Cliente getCliente() {
        return cliente;
    }

    /**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
