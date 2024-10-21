/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo.DebitoAutomaticoTarjetaBO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DatoClienteDebitoTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager.DebitoAutomaticoTarjetaManager;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ParametroAdhesionDebitoTarjetaInView;
import ar.com.santanderrio.obp.servicios.exception.HashParametrosException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DebitoAutomaticoTarjetaManagerImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class DebitoAutomaticoTarjetaManagerImpl implements DebitoAutomaticoTarjetaManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitoAutomaticoTarjetaManagerImpl.class);

	/** The Constant RESPUESTA_OK. */
	private static final String RESPUESTA_OK = "Respuesta del Manager OK: {}.";

	/** The Constant RESPUESTA_ERROR. */
	private static final String RESPUESTA_ERROR = "Respuesta del Manager con error.";

	/** The Constant DOS_REINTENTOS. */
	private static final int DOS_REINTENTOS = 2;

	/** The Constant VISA. */
	private static final String VISA = "VISA";

	/** The Constant CERO_ENTERO. */
	private static final int CERO_ENTERO = 0;

	/** The debito tarjeta BO. */
	@Autowired
	private DebitoAutomaticoTarjetaBO debitoTarjetaBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/**
	 * Obtiene la confirmacion de la adhesion al debito automatico en la tarjeta
	 * de credito.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteDebitoAutomaticoTarjetaView> obtenerAdhesionDebitoTarjeta(
			ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito) {
		try {
			HashUtils.compareHash(sesionParametros.getValidacionHash(),
					HashUtils.obtenerHash(datosConfirmacionDebito.getDatosCliente()));
		} catch (HashParametrosException e) {
			LOGGER.error("el hash no coincide", e);
			// En caso de hash invalido o no encontrar cuenta en sesion.
			grabarEstadistica(getCodigoEstadistica(datosConfirmacionDebito),
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ComprobanteDebitoAutomaticoTarjetaView.class, StringUtils.EMPTY,
					TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.ERROR_FEEDBACK_ADHESION_DEBITO_TARJETA);
		}
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getTarjeta(datosConfirmacionDebito.getDatosCliente().getNumeroTarjetaEnmascarado());
		grabarReintentos(datosConfirmacionDebito);
		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = debitoTarjetaBO.obtenerAdhesionDebitoTarjeta(cliente,
				new DatoClienteDebitoTarjetaInDTO(datosConfirmacionDebito.getDatosCliente(), cuenta),
				datosConfirmacionDebito.getDatosCliente().getNumeroTarjetaEnmascarado());
		if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			grabarEstadistica(getCodigoEstadistica(datosConfirmacionDebito),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView = respuestaFactory.crearRespuestaOk(
					ComprobanteDebitoAutomaticoTarjetaView.class, new ComprobanteDebitoAutomaticoTarjetaView(
							datosConfirmacionDebito.getDatosCliente(), respuestaDTO.getRespuesta()));
			LOGGER.info(RESPUESTA_OK, respuestaView);
			return respuestaView;
		} else {
			LOGGER.info(RESPUESTA_ERROR);
			return crearRespuestaError(datosConfirmacionDebito, respuestaDTO);
		}
	}

	/**
	 * Graba la estadistica del comprobante de la ahdesion al debito automatico
	 * en la tarjeta de credito.
	 *
	 * @return the boolean
	 */
	@Override
	public Boolean grabarEstadisticaComprobante() {
		if (estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ADHESION_DEBITO_TARJETA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Graba en sesion la cantidad de reintentos.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 */
	private void grabarReintentos(ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito) {
		if (datosConfirmacionDebito.getReintentar().equals(Boolean.FALSE)) {
			sesionParametros.setContador(new ContadorIntentos(DOS_REINTENTOS));
		}
	}

	/**
	 * Crea la respuesta con error/warning.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteDebitoAutomaticoTarjetaView> crearRespuestaError(
			ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito,
			Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO) {
		grabarEstadistica(getCodigoEstadistica(datosConfirmacionDebito),
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView;
		ComprobanteDebitoAutomaticoTarjetaView view = new ComprobanteDebitoAutomaticoTarjetaView();
		if (sesionParametros.getContador().permiteReintentar()) {
			view.setReintentar("true");
			if (esErrorTimeout(respuestaDTO)) {
				return respuestaFactory.crearRespuestaWarning(ComprobanteDebitoAutomaticoTarjetaView.class, view,
						TipoError.WARNING_TIMEOUT,
						CodigoMensajeConstantes.ERROR_TIMEOUT_FEEDBACK_ADHESION_DEBITO_TARJETA, StringUtils.EMPTY);
			} else {
				respuestaView = respuestaFactory.crearRespuestaWarning(ComprobanteDebitoAutomaticoTarjetaView.class,
						view, TipoError.WARNING_REINTENTOS,
						CodigoMensajeConstantes.ERROR_FEEDBACK_ADHESION_DEBITO_TARJETA, StringUtils.EMPTY);
			}
		} else {
			if (esErrorTimeout(respuestaDTO)) {
				return respuestaFactory.crearRespuestaError(ComprobanteDebitoAutomaticoTarjetaView.class,
						respuestaDTO.getItemsMensajeRespuesta());
			} else {
				respuestaView = respuestaFactory.crearRespuestaError(ComprobanteDebitoAutomaticoTarjetaView.class,
						StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
						CodigoMensajeConstantes.ERROR_FEEDBACK_ADHESION_DEBITO_TARJETA);
			}
		}

		respuestaView.getItemsMensajeRespuesta().get(0)
				.setMensaje(respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
		return respuestaView;
	}

	/**
	 * Es error timeout. Evalua el caso de error por timeout.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the boolean
	 */
	private Boolean esErrorTimeout(Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO) {
		return StringUtils.equals(respuestaDTO.getItemsMensajeRespuesta().get(CERO_ENTERO).getTipoError(),
				TipoError.TIMEOUT.getDescripcion());
	}

	/**
	 * Grabar la estadistica.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param resultado
	 *            the resultado
	 */
	private void grabarEstadistica(String codigoTransaccion, String resultado) {
		try {
			estadisticaManager.add(codigoTransaccion, resultado);
		} catch (Exception e) {
			LOGGER.error("Error al grabar la estadistica.", e);
		}
	}

	/**
	 * Gets the codigo estadistica.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 * @return the codigo estadistica
	 */
	private static String getCodigoEstadistica(ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito) {
		if (StringUtils.contains(datosConfirmacionDebito.getDatosCliente().getNumeroTarjetaEnmascarado(), VISA)) {
			return EstadisticasConstants.FEEDBACK_ADHESION_DEBITO_TARJETA_VISA;
		} else {
			return EstadisticasConstants.FEEDBACK_ADHESION_DEBITO_TARJETA_AMEX;
		}
	}

}