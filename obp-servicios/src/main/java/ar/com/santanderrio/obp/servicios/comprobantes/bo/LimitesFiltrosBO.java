/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;

/**
 * The Interface LimitesFiltrosBO.
 */
public interface LimitesFiltrosBO {

	/**
	 * Obtener filtro comprobante DTO.
	 *
	 * @param viewIn
	 *            the view in
	 * @param cliente
	 *            the cliente
	 * @return the filtro comprobante DTO
	 */
	FiltroComprobanteDTO obtenerFiltroComprobanteDTO(ComprobantesViewIn viewIn, Cliente cliente);

	/**
	 * Obtener limites de fecha.
	 *
	 * @return the list
	 */
	List<String> obtenerLimitesDeFecha();

}
