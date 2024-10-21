/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaDebitoDirectoDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ConsultaDebitoDirectoDAOImpl.
 */
@Component
public class ConsultaDebitoDirectoDAOImpl extends IatxBaseDAO implements ConsultaDebitoDirectoDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDebitoDirectoDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ConsultaDebitoDirectoDAO#consultarAsync(ar.com.santanderrio.obp.servicios
	 * .comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity)
	 */
	@Async
	@Override
	public Future<ComprobanteDebitoAutomaticoOutEntity> consultarAsync(ComprobanteDebitoAutomaticoInEntity inEntity) {
		return new AsyncResult<ComprobanteDebitoAutomaticoOutEntity>(consultar(inEntity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ConsultaDebitoDirectoDAO#consultar(ar.com.santanderrio.obp.servicios.
	 * comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity)
	 */
	@Cacheable(cacheNames = CacheConstants.Names.CACHE_NYO_CNSDDIDERE, key = "#inEntity.obtenerCacheKey()")
	@Override
	public ComprobanteDebitoAutomaticoOutEntity consultar(ComprobanteDebitoAutomaticoInEntity inEntity) {
		ComprobanteDebitoAutomaticoOutEntity outEntityFinal = new ComprobanteDebitoAutomaticoOutEntity();
		String servicio = "CNSDDIDERE";
		String version = "100";
		LOGGER.info("Se cachea por nup {} flujo {}.", inEntity.getCliente().getNup(), servicio);
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		Boolean masRegistros = Boolean.TRUE;
		while (masRegistros) {
			ComprobanteDebitoAutomaticoOutEntity outEntityTemporal = new ComprobanteDebitoAutomaticoOutEntity();
			try {
				IatxRequestData iatxRequestData = generateRequestDataCNSDDIDERE(inEntity);
				iatxRequest.setData(iatxRequestData);
				IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
				int errorCode = iatxResponse.getErrorCode();

				if (OK_CODIGO_RETORNO == errorCode) {
					outEntityTemporal = processTrama(iatxResponse.getTrama(),
							ComprobanteDebitoAutomaticoOutEntity.class);
					masRegistros = outEntityTemporal.getHayMasRegistros();
				} else {
					LOGGER.error("Codigo de error no esperado de iatx en servicio CNSDDIDERE. ");
					return returnOutEntityError(esRellamada(outEntityFinal), outEntityFinal, errorCode);
				}
			} catch (IatxException e) {
				LOGGER.error("Error al consultar las deudas para el cliente {}", inEntity.getCliente().getNup(), e);
				return returnOutEntityError(esRellamada(outEntityFinal), outEntityFinal, 1);
			}
			outEntityFinal.getComprobantes().addAll(outEntityTemporal.getComprobantes());
			if (masRegistros) {
				recargar(inEntity, outEntityTemporal.getComprobantes());
			}
		}
		outEntityFinal.setCodigoRetornoExtendido(String.valueOf(0));
		return outEntityFinal;
	}

	/**
	 * Generate request data CNSDDIDERE.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSDDIDERE(ComprobanteDebitoAutomaticoInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(inEntity.getCliente());
		// N11
		iatxRequestData.addBodyValue(inEntity.getEmpresa().getCuitEmpresa());
		// C10
		iatxRequestData.addBodyValue(StringUtils.rightPad(inEntity.getEmpresa().getNombreServicioEmpresa(), 10, " ")); 
		// C22
		iatxRequestData
				.addBodyValue(StringUtils.rightPad(inEntity.getEmpresa().getNroPartidaServicioEmpresa(), 22, " "));
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaDesde());
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaHasta());
		// A15
		iatxRequestData.addBodyValue("");
		// C06
		if(inEntity.isFlujoDDI()) {
			iatxRequestData.addBodyValue("011000");
		} else {
			iatxRequestData.addBodyValue("100100");
		}
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaContinuacion());
		// A15
		iatxRequestData.addBodyValue(inEntity.getIDDebitoDDICont());
		return iatxRequestData;
	}

	/**
	 * Return out entity error.
	 *
	 * @param esRellamada
	 *            the es rellamada
	 * @param comprobanteDebitoAutomaticoOutEntity
	 *            the comprobante debito automatico out entity
	 * @param errorCode
	 *            the error code
	 * @return the comprobante debito automatico out entity
	 */
	private ComprobanteDebitoAutomaticoOutEntity returnOutEntityError(Boolean esRellamada,
			ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity, int errorCode) {
		if (esRellamada) {
			LOGGER.debug("Error del servio iatx CNSDDIDERE en una re-invocacion. ");
			comprobanteDebitoAutomaticoOutEntity.setErrorRellamada(Boolean.TRUE);
		}
		comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido(String.valueOf(errorCode));
		return comprobanteDebitoAutomaticoOutEntity;
	}

	/**
	 * Es rellamada.
	 *
	 * @param out
	 *            the out
	 * @return the boolean
	 */
	private Boolean esRellamada(ComprobanteDebitoAutomaticoOutEntity out) {
		return !out.getComprobantes().isEmpty();
	}

	/**
	 * Recargar.
	 *
	 * @param entity
	 *            the entity
	 * @param comprobantes
	 *            the comprobantes
	 */
	private void recargar(ComprobanteDebitoAutomaticoInEntity entity, List<ComprobanteDebitoAutomatico> comprobantes) {
		ComprobanteDebitoAutomatico comprobanteEntity = comprobantes.get(comprobantes.size() - NumberUtils.INTEGER_ONE);
		entity.setFechaContinuacion(comprobanteEntity.getFechaVencimientoDDI());
		entity.setIDDebitoDDICont(comprobanteEntity.getIdDebitoDDI());
	}
}
