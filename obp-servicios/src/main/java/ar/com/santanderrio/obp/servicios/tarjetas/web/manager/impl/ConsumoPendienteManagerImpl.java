/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsumoPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;

/**
 * The Class ConsumoPendienteManagerImpl.
 */
@Component
public class ConsumoPendienteManagerImpl implements ConsumoPendienteManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumoPendienteManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	private static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The sesion tarjetas. */
	@Autowired
	private SesionTarjetas sesionTarjetas;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * Obtener consumo pendiente.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConsumosPendientesView> obtenerConsumoPendiente() {
		ConsumosPendientesView consumosPendientesView = sesionTarjetas.getConsumoPendiente();
		if (consumosPendientesView != null && !consumosPendientesView.getConsumosPendientes().isEmpty()) {
			String marca = consumosPendientesView.getConsumosPendientes().get(0).getMarca();
			return armarRespuestaOK(marca, consumosPendientesView);
		}
		return armarRespuestaError();
	}

	/**
	 * Arma la respuesta OK.
	 *
	 * @param marca
	 *            the marca
	 * @param consumosPendientesView
	 *            the consumos pendientes view
	 * @return the respuesta
	 */
	private Respuesta<ConsumosPendientesView> armarRespuestaOK(String marca,
			ConsumosPendientesView consumosPendientesView) {
		crearEstadisticaConsumoPendientesOK(marca);
		return respuestaFactory.crearRespuestaOk(ConsumosPendientesView.class, consumosPendientesView);
	}

	/**
	 * Arma la respuesta con ERROR.
	 *
	 * @return the respuesta
	 */
	public Respuesta<ConsumosPendientesView> armarRespuestaError() {
		LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL + EstadoRespuesta.ERROR);
		crearEstadisticaConsumoPendienteError();
		return respuestaFactory.crearRespuestaError(TagItemMensajeTarjetaEnum.CONSUMOS_PENDIENTES.getDescripcionTag(),
				TipoError.ERROR_CONSUMOS_PENDIENTES,
				CodigoMensajeConstantes.CODIGO_ERROR_CONSUMOS_PENDIENTES_CONFIRMACION);
	}

	/**
	 * Arma la respuesta con ERROR.
	 *
	 * @param marca
	 *            the marca
	 * @return the respuesta
	 */
	public Respuesta<ConsumosPendientesView> armarRespuestaError(String marca) {
		LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL + EstadoRespuesta.ERROR);
		crearEstadisticaConsumoPendienteError(marca);
		// TODO Chequear codigo de mensaje para recuperar el texto de la base
		return respuestaFactory.crearRespuestaError(TagItemMensajeTarjetaEnum.CONSUMOS_PENDIENTES.getDescripcionTag(),
				TipoError.ERROR_CONSUMOS_PENDIENTES,
				CodigoMensajeConstantes.CODIGO_ERROR_CONSUMOS_PENDIENTES_CONFIRMACION);
	}

	/**
	 * Crear la estadistica de consumos pendientes de confirmacion OK.
	 *
	 * @param marca
	 *            the marca
	 */
	private void crearEstadisticaConsumoPendientesOK(String marca) {
		estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaConsumosPendientes(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear la estadistica de consumos pendientes de confirmacion con ERROR.
	 */
	private void crearEstadisticaConsumoPendienteError() {
		estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaConsumosPendientes(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear la estadistica de consumos pendientes de confirmacion con ERROR.
	 *
	 * @param marca
	 *            the marca
	 */
	private void crearEstadisticaConsumoPendienteError(String marca) {
		estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaConsumosPendientes(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

}