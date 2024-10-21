/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chances.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;

/**
 * The Interface ChancesBO
 */
public interface ChancesBO {

	/**
	 * Obtiene chances segun el mes seleccionado.
	 *
	 * @param ChanceView
	 *            the ChanceView
	 * @return the respuesta
	 */
	Respuesta<ChancesDTO> obtenerChancesMes(String dispositivo, Cliente cliente, String string);
	
}
