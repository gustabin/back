/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.service;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Interface DatosSolicitanteService.
 */
public interface DatosSolicitanteService {

	/**
	 * Gets the datos tarjeta monedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoDeConsulta
	 *            the tipo de consulta
	 * @return the datos tarjeta monedero
	 */
	Respuesta<List<MonederoDTO>> getDatosTarjetaMonedero(Cliente cliente, String tipoDeConsulta);

	/**
	 * Obtener tags.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	Respuesta<List<TagEntity>> obtenerTags(TagsDTO dto);

	/**
	 * Obtener transacciones tags.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	Respuesta<List<TransaccionEntity>> obtenerTransaccionesTags(TagsTransacDTO dto);

	/**
	 * ver como se hace con la clase TerminosCondiciones //Respuesta<Legal>
	 * cargarTerminosCondiciones() throws DAOException;.
	 *
	 * @param datosAltaCanalesAutomaticosInDTO
	 *            the datos alta canales automaticos in DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */

	Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			AltaCanalAutomaticoInEntity datosAltaCanalesAutomaticosInDTO, Cliente cliente);

	/**
	 * Gets the datos del solicitante.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param cliente
	 *            the cliente
	 * @return the datos del solicitante
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<DatosSolicitanteDTO> getDatosDelSolicitante(DatosSolicitanteCriterioDTO datosSolicitante, Cliente cliente)
			throws BusinessException;

}
