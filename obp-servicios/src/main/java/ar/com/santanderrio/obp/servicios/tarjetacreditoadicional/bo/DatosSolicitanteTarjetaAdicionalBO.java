/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;

/**
 * The Interface DatosSolicitanteBO.
 */
public interface DatosSolicitanteTarjetaAdicionalBO {

	/**
	 * Gets the datos padron BO.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param cliente
	 *            the cliente
	 * @return the datos padron BO
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<DatosSolicitanteDTO> getDatosPadronBO(DatosSolicitanteCriterioDTO datosSolicitante, Cliente cliente)
			throws DAOException;

	/**
	 * Ejecutar alta canales automaticos.
	 *
	 * @param altaCanalAutomaticoInEntity
	 *            the alta canal automatico in entity
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity, Cliente cliente);

}
