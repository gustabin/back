/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;

/**
 * Manager de Segmente Cliente.
 * 
 * @author B025331
 *
 */
public interface SegmentoClienteManager {

	/**
	 * Obtencion del segmento de cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Segmento> obtenerSegmento(Cliente cliente);

}
