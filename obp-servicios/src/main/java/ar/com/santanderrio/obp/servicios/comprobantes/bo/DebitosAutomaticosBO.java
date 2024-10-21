/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Interface DebitosAutomaticosBO.
 */
public interface DebitosAutomaticosBO {

	/**
	 * Obtener debitos automaticos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente);

	/**
	 * Obtener comprobantes async.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesAsync(Cliente cliente);

	/**
	 * Obtener comprobantes.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente, TransaccionDTO dto);

	/**
	 * Obtener comprobantes async.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesAsync(Cliente cliente, TransaccionDTO dto);

}
