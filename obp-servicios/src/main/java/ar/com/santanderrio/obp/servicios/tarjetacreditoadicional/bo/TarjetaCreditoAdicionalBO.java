/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.EdadIncorrectaException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ComprobanteAltaTarjCredAdicionalDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomiciliosDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;

/**
 * The Interface TarjetaCreditoAdicionalBO.
 */
public interface TarjetaCreditoAdicionalBO {

    /**
     * Gets the tarjetas candidatas.
     *
     * @return the tarjetas candidatas
     */
    Respuesta<List<TarjetaCandidataDTO>> getTarjetasCandidatas();

    /**
	 * Gets the datos del solicitante.
	 *
	 * @param datosSolicitanteCriterioDTO
	 *            the datos solicitante criterio DTO
	 * @param cliente
	 *            the cliente
	 * @return the datos del solicitante
	 * @throws EdadIncorrectaException
	 *             the edad incorrecta exception
	 */
    Respuesta<DatosSolicitanteDTO> getDatosDelSolicitanteAltair(DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO,
            Cliente cliente) throws EdadIncorrectaException;

    /**
     * Es persona habilitada.
     *
     * @param entity
     *            the entity
     * @return the consulta inhabilitados out entity
     */
    ConsultaInhabilitadosOutEntity esPersonaHabilitada(ConsultaInhabilitadosInEntity entity);

    /**
     * Alta tarjeta credito adicional.
     *
     * @param datosSolicitanteConfirmadoDTO
     *            the datos solicitante confirmado DTO
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<ComprobanteAltaTarjCredAdicionalDTO> altaTarjetaCreditoAdicional(
            DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoDTO, Cliente cliente);

    /**
     * Generar comprobante.
     *
     * @param view
     *            the view
     * @return the respuesta
     */
    Respuesta<Reporte> generarComprobante(ComprobanteAltaTarjCredAdicionalView view);

    /**
	 * Gets the resultado merlin.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosDeDomicilio
	 *            the datos de domicilio
	 * @return the resultado merlin
	 * @throws BusinessException
	 *             the business exception
	 */
    DomiciliosDTO getResultadoMerlin(Cliente cliente, DatosMerlinInEntity datosDeDomicilio)
            throws BusinessException;

}
