/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;

/**
 * BO de obtencion de segmento.
 * 
 * @author B025331
 *
 */
public interface SegmentoClienteBO {

	/**
	 * Obtencion del segmento del usuario.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Segmento> obtenerSegmento(Cliente cliente);

}
