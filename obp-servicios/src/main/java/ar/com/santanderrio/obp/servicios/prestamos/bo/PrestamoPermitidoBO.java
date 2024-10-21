/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoPermitidoDTO;

/**
 * The Interface PrestamoPermitidoBO.
 */
public interface PrestamoPermitidoBO {

	/**
	 * Obtener prestamo con saldo mayor.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	public Respuesta<PrestamoPermitidoDTO> obtenerPrestamoConSaldoMayor(Cliente cliente);

}
