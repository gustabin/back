/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.service.DatosSolicitanteService;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.DatosSolicitanteTarjetaAdicionalBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Class DatosSolicitanteServiceImpl.
 */
@Component
public class DatosSolicitanteServiceImpl implements DatosSolicitanteService {

	/** The datos solicitante BO. */
	@Autowired
	private DatosSolicitanteBO datosSolicitanteBO;

	/** The datos solicitante BO. */
	@Autowired
	private DatosSolicitanteTarjetaAdicionalBO datosSolicitanteTarjetaAdicionalBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.service.
	 * DatosSolicitanteService#getDatosDelSolicitante(ar.com.santanderrio.obp.
	 * servicios.monedero.web.view.DatosSolicitanteCriterioView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<DatosSolicitanteDTO> getDatosDelSolicitante(DatosSolicitanteCriterioDTO datosSolicitante,
			Cliente cliente) throws BusinessException {
		return datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.service.
	 * DatosSolicitanteService#getDatosTarjetaMonedero(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<List<MonederoDTO>> getDatosTarjetaMonedero(Cliente cliente, String tipoDeConsulta) {
		return datosSolicitanteBO.getDatosTarjetaMonedero(cliente, tipoDeConsulta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.service.
	 * DatosSolicitanteService#obtenerTags(ar.com.santanderrio.obp.servicios.
	 * transferencias.entities.agenda.TagsDTO)
	 */
	@Override
	public Respuesta<List<TagEntity>> obtenerTags(TagsDTO dto) {
		return datosSolicitanteBO.obtenerTags(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.service.
	 * DatosSolicitanteService#obtenerTransaccionesTags(ar.com.santanderrio.obp.
	 * servicios.transferencias.entities.agenda.TagsTransacDTO)
	 */
	@Override
	public Respuesta<List<TransaccionEntity>> obtenerTransaccionesTags(TagsTransacDTO dto) {
		return datosSolicitanteBO.obtenerTransaccionesTags(dto);

	}

	/**
	 * //ver como se hace con la clase TerminosCondiciones.
	 *
	 * @param datosAltaCanalesAutomaticosInDTO
	 *            the datos alta canales automaticos in DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @Override public Respuesta<Legal> cargarTerminosCondiciones() throws
	 *           DAOException { return
	 *           datosSolicitanteBO.cargarTerminosCondiciones(); }
	 */
	@Override
	public Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			AltaCanalAutomaticoInEntity datosAltaCanalesAutomaticosInDTO, Cliente cliente) {
		return datosSolicitanteTarjetaAdicionalBO.ejecutarAltaCanalesAutomaticos(datosAltaCanalesAutomaticosInDTO,
				cliente);
	}

}
