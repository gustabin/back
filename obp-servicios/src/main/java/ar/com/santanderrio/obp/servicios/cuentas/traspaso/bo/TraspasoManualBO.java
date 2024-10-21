/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ComprobanteTraspasoManualDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoManualDTO;

/**
 * The Interface TraspasoManualBO.
 */
public interface TraspasoManualBO {

	/**
	 * Realizar traspaso manual.
	 *
	 * @param cliente
	 *            the cliente
	 * @param traspasoManualDTO
	 *            the traspaso manual DTO
	 * @return the respuesta
	 */
	Respuesta<ComprobanteTraspasoManualDTO> realizarTraspasoManual(Cliente cliente,
			TraspasoManualDTO traspasoManualDTO);

}
