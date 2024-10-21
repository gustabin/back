/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.ConfirmacionSolicitudPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.ListUtil;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.FinanciacionTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.FinanciacionTarjetaBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FeedbackFinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.FinanciacionTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

/**
 * The Class TarjetasManagerImpl.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 6, 2016
 */
@Component
public class FinanciacionTarjetaManagerImpl implements FinanciacionTarjetaManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FinanciacionTarjetaManagerImpl.class);

	/** The LOGGER. */
	private static final String ERROR_EJECUCION_FINANCIACION = "Ha ocurrido un error al confirmar la financiacion";

	/** The Constant ERROR_FORMATO_RESPUESTA. */
	private static final String ERROR_FORMATO_RESPUESTA = "El formato de la respuesta proveniente del servicio es incorrecto.";

	/** The mensajes de Lagales, BO. */
	@Autowired
	private LegalBO legalBO;

	/** The financiacion tarjeta BO. */
	@Autowired
	private FinanciacionTarjetaBO financiacionTarjetaBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The selector Y cabecera BO. */
	@Autowired
	private SelectorYCabeceraBO selectorYCabeceraBO;

	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.web.manager.TarjetasManager#
	 * solicitarFinanciacionTarjeta()
	 */
	@Override
	public Respuesta<FinanciacionTarjetaView> solicitarFinanciacionTarjeta(
			FinanciacionTarjetaView financiacionTarjetaView) {

		LOGGER.info("Solicitar la financiacion de tarjeta");

		financiacionTarjetaView.generarNuevoIdSesion();
		List<ResumenTarjetaDTO> tarjetas = obtenerResumenes();

		Cuenta cuenta = sesionCliente.getCliente()
				.getCuentaPorNumero(financiacionTarjetaView.getNroCuentaProductoSeleccionado());
		Respuesta<FinanciacionTarjetaDTO> financiacionTarjetaDTO = financiacionTarjetaBO
				.solicitarFinanciacionTarjeta(cuenta);
		cargarListaTarjetas(tarjetas, financiacionTarjetaView);
		financiacionTarjetaView.setNumeroTarjetaSeleccionada(
				ISBANStringUtils.mascaraTarjetaCredito(cuenta.getNroTarjetaCredito(), cuenta.getTipoCuenta()));

		Respuesta<FinanciacionTarjetaView> respuesta = new Respuesta<FinanciacionTarjetaView>();
		respuesta.setEstadoRespuesta(financiacionTarjetaDTO.getEstadoRespuesta());
		respuesta.setRespuestaVacia(financiacionTarjetaDTO.isRespuestaVacia());
		respuesta.setItemMensajeRespuesta(financiacionTarjetaDTO.getItemsMensajeRespuesta());

		if (financiacionTarjetaDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			grabarEstadisticaDeAccesoFinanciacionPorTarjeta(cuenta, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			FinanciacionTarjetaDTO dto = financiacionTarjetaDTO.getRespuesta();
			respuesta.setRespuesta(cargarVistaconDTO(dto, financiacionTarjetaView));
		} else {
			respuesta.setRespuesta(financiacionTarjetaView);
			grabarEstadisticaDeAccesoFinanciacionPorTarjeta(cuenta, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Obtener resumenes.
	 *
	 * @return the list
	 */
	private List<ResumenTarjetaDTO> obtenerResumenes() {
		LOGGER.info("Obtener tarjetas del cliente con DNI: " + sesionCliente.getCliente().getDni());
		Respuesta<DisponiblesYConsumoDTO> disponiblesYConsumoDTO = selectorYCabeceraBO
				.obtenerTarjetas(sesionCliente.getCliente());
		if (disponiblesYConsumoDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				|| disponiblesYConsumoDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return disponiblesYConsumoDTO.getRespuesta().getResumenes();
		}
		return new ArrayList<ResumenTarjetaDTO>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.web.manager.TarjetasManager#
	 * ejecutarFinanciacionTarjeta(ar.com.santanderrio.obp.servicios.tarjetas.
	 * entities.FinanciacionTarjetaView)
	 */
	@Override
	public Respuesta<FeedbackFinanciacionTarjetaView> ejecutarFinanciacionTarjeta(
			FinanciacionTarjetaView financiacionTarjetaView) {

		Respuesta<FeedbackFinanciacionTarjetaView> respuestaView = new Respuesta<FeedbackFinanciacionTarjetaView>();

		String mensajeError = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CONFIRMACION_FINANCIACION_TARJETA_ERROR).getMensaje();
		mensajeError = MessageFormat.format(mensajeError, financiacionTarjetaView.getNumeroTarjetaSeleccionada());

		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(financiacionTarjetaView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(financiacionTarjetaView.getIdSesion(), 2, mensajeError);
		}

		Respuesta<FinanciacionTarjetaDTO> financiacionTarjetaDTO;
		LOGGER.info("Ejecutar la financiacion de tarjeta");
		String nroCuentaProductoSeleccionado = financiacionTarjetaView.getNroCuentaProductoSeleccionado();

		financiacionTarjetaDTO = financiacionTarjetaBO.ejecutarFinanciacionTarjeta(
				generarDatosConfirmacionFinanciacionDTO(nroCuentaProductoSeleccionado, financiacionTarjetaView));

		respuestaView.setEstadoRespuesta(financiacionTarjetaDTO.getEstadoRespuesta());
		respuestaView.setRespuestaVacia(financiacionTarjetaDTO.isRespuestaVacia());
		respuestaView.setItemMensajeRespuesta(financiacionTarjetaDTO.getItemsMensajeRespuesta());

		switch (respuestaView.getEstadoRespuesta()) {
		case OK:
			FinanciacionTarjetaDTO dto = financiacionTarjetaDTO.getRespuesta();
			respuestaView.setRespuesta(cargarVistaconFeedbackDTO(dto, financiacionTarjetaView));
			grabarEstadisticaDeConfirmacionFinanciacionPorTarjeta(financiacionTarjetaView.getMarcaTarjetaSeleccionada(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaView;
		case WARNING:
			LOGGER.debug(ERROR_EJECUCION_FINANCIACION);
			grabarEstadisticaDeConfirmacionFinanciacionPorTarjeta(financiacionTarjetaView.getMarcaTarjetaSeleccionada(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return sesionParametros.getContador().excedeReintentos(financiacionTarjetaView.getIdSesion(),
					respuestaView);
		case ERROR:
			LOGGER.debug(ERROR_EJECUCION_FINANCIACION);
			return respuestaView;
		default:
			respuestaView.setItemMensajeRespuesta(financiacionTarjetaDTO.getItemsMensajeRespuesta());
			return respuestaView;
		}
	}

	/**
	 * Gets the message. Datos comprobante: - 0 --> Marca de la tarjeta. - 1 -->
	 * Número de la tarjeta, según manual de cultura. - 2 --> Importe a
	 * financiar. - 3 --> Cantidad de cuotas. - 4 --> Importe de la cuota.
	 * 
	 * @param dto
	 *            the dto
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the message
	 */
	private String armarMensaje(FinanciacionTarjetaDTO dto, FinanciacionTarjetaView financiacionTarjetaView) {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FINANCIACION_FEEDBACK_OK)
				.getMensaje();

		String importeFinanciar = DivisaEnum.PESO.getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldo(new BigDecimal(financiacionTarjetaView.getImporteFinanciar()));
		String importeCuota = DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils
				.formatearSaldo(FinanciacionTarjetaBOImpl.formatearImporte(financiacionTarjetaView.getImporteCuota()));
		mensaje = MessageFormat.format(mensaje, financiacionTarjetaView.getMarcaTarjetaSeleccionada(),
				financiacionTarjetaView.getNumeroTarjetaSeleccionada(), importeFinanciar,
				financiacionTarjetaView.getCantidadCuotas(), importeCuota);
		return mensaje;
	}

	/**
	 * Generar datos confirmacion financiacion DTO.
	 *
	 * @param nroCuentaProductoSeleccionado
	 *            the nro cuenta producto seleccionado
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the datos confirmacion financiacion tarjeta DTO
	 */
	private DatosConfirmacionFinanciacionTarjetaDTO generarDatosConfirmacionFinanciacionDTO(
			String nroCuentaProductoSeleccionado, FinanciacionTarjetaView financiacionTarjetaView) {
		DatosConfirmacionFinanciacionTarjetaDTO dto = new DatosConfirmacionFinanciacionTarjetaDTO();
		dto.setNroCuentaProductoSeleccionado(nroCuentaProductoSeleccionado);
		dto.setCuotas(financiacionTarjetaView.getCantidadCuotas());
		dto.setMontoFinanciacion(new BigDecimal(financiacionTarjetaView.getImporteFinanciar()));
		Cuenta cuenta = sesionCliente.getCliente()
				.getCuentaPorNumero(financiacionTarjetaView.getNroCuentaProductoSeleccionado());
		dto.setTarjeta(cuenta);
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * FinanciacionTarjetasManager#simularFinanciacionTarjeta(ar.com.
	 * santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView)
	 */
	@Override
	public Respuesta<FinanciacionTarjetaView> simularFinanciacionTarjeta(
			FinanciacionTarjetaView financiacionTarjetaView) {
		LOGGER.info("Simular la financiacion de tarjeta");

		String indexCuotaSeleccionada = financiacionTarjetaView.getIndexCuotaSeleccionada();
		Cuenta cuenta = sesionCliente.getCliente()
				.getCuentaPorNumero(financiacionTarjetaView.getNroCuentaProductoSeleccionado());
		grabarEstadisticaDeRecalcularFinanciacionPorTarjeta(cuenta, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Respuesta<FinanciacionTarjetaDTO> financiacionTarjetaDTO = financiacionTarjetaBO
				.simularFinanciacionTarjeta(financiacionTarjetaView, cuenta);
		Respuesta<FinanciacionTarjetaView> respuesta = new Respuesta<FinanciacionTarjetaView>();
		respuesta.setEstadoRespuesta(financiacionTarjetaDTO.getEstadoRespuesta());
		respuesta.setRespuestaVacia(financiacionTarjetaDTO.isRespuestaVacia());
		respuesta.setItemMensajeRespuesta(financiacionTarjetaDTO.getItemsMensajeRespuesta());
		if (financiacionTarjetaDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			FinanciacionTarjetaDTO dto = financiacionTarjetaDTO.getRespuesta();
			respuesta.setRespuesta(cargarVistaconDTO(dto, financiacionTarjetaView));
		}
		financiacionTarjetaView.setIndexCuotaSeleccionada(indexCuotaSeleccionada);
		return respuesta;

	}

	/**
	 * Carga FinanciacionTarjetaView con los datos de FinanciacionTarjetaDTO.
	 * Las cuotas/tna se cargan en la construccion de la vista con
	 * informacionPlanV.
	 * 
	 * @param dto
	 *            the dto
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the financiacion tarjeta view
	 */
	private FinanciacionTarjetaView cargarVistaconDTO(FinanciacionTarjetaDTO dto,
			FinanciacionTarjetaView financiacionTarjetaView) {
		InformacionPlanV informacionPlanV = dto.getInformacionPlanV();
		SimulacionPlanVDTO simulacionPlanVDTO = dto.getSimulacionPlanVDTO();
		if (informacionPlanV != null) {
			financiacionTarjetaView.setCuotasDisponibles(informacionPlanV);
			financiacionTarjetaView.setImporteFinanciar(ISBANStringUtils
					.formatearSaldoConSigno(BigDecimal.valueOf(informacionPlanV.getMontoMaximoAFinanciar())));
			financiacionTarjetaView.setImporteMaximoFinanciar(ISBANStringUtils
					.formatearSaldoConSigno(BigDecimal.valueOf(informacionPlanV.getMontoMaximoAFinanciar())));
			financiacionTarjetaView.setImporteMinimoFinanciar(
					ISBANStringUtils.formatearSaldoConSigno(BigDecimal.valueOf(informacionPlanV.getMontoMinimo())));
		} else {
			financiacionTarjetaView.setImporteFinanciar(ISBANStringUtils
					.formatearSaldo(BigDecimal.valueOf(new Double(financiacionTarjetaView.getImporteFinanciar()))));
			financiacionTarjetaView.setImporteMaximoFinanciar(financiacionTarjetaView.getImporteMaximoFinanciar());
			financiacionTarjetaView.setImporteMinimoFinanciar(financiacionTarjetaView.getImporteMinimoFinanciar());
		}

		String cft = simulacionPlanVDTO.getCostoFinancieroTotal();
		if (cft != null && cft.length() == 6) {
			cft = cft.substring(0, 3) + "." + cft.substring(3, 5);
		} else {
			LOGGER.debug(ERROR_FORMATO_RESPUESTA);
			throw new RobotException(ERROR_FORMATO_RESPUESTA);
		}
		financiacionTarjetaView.setCft(ISBANStringUtils.formatearSaldo(BigDecimal.valueOf(new Double(cft))));

		String importeCuota = simulacionPlanVDTO.getImporteCuota();
		if (importeCuota != null && importeCuota.length() == 9) {
			importeCuota = importeCuota.substring(0, 7) + "." + importeCuota.substring(7, 9);
		} else {
			LOGGER.debug(ERROR_FORMATO_RESPUESTA);
			throw new RobotException(ERROR_FORMATO_RESPUESTA);
		}
		financiacionTarjetaView
				.setImporteCuota(ISBANStringUtils.formatearSaldo(BigDecimal.valueOf(new Double(importeCuota))));

		financiacionTarjetaView.setMensajeInformativo(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.INFO_FINANCIACION_TARJETAS).getMensaje());
		return financiacionTarjetaView;
	}

	/**
	 * Carga FeedbackFinanciacionTarjetaView con los datos de
	 * FinanciacionTarjetaDTO.
	 * 
	 * @param dto
	 *            the dto
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the financiacion tarjeta view
	 */
	private FeedbackFinanciacionTarjetaView cargarVistaconFeedbackDTO(FinanciacionTarjetaDTO dto,
			FinanciacionTarjetaView financiacionTarjetaView) {

		ConfirmacionSolicitudPlanV confirmacionSolicitudPlanV = dto.getConfirmacionSolicitudPlanV();
		SimulacionPlanVDTO simulacionPlanVRequestDTO = dto.getSimulacionPlanVDTO();
		FeedbackFinanciacionTarjetaView feedbackFinanciacionTarjetaView = new FeedbackFinanciacionTarjetaView();
		feedbackFinanciacionTarjetaView.setTarjetaSantanderRioVisa(simulacionPlanVRequestDTO.getNumeroCuentaProducto());
		feedbackFinanciacionTarjetaView.setMoneda(confirmacionSolicitudPlanV.getMoneda());
		feedbackFinanciacionTarjetaView.setMontoAFinanciar(ISBANStringUtils
				.formatearConComaYDosDecimales(new Double(confirmacionSolicitudPlanV.getMontoSolicitud()).toString()));
		feedbackFinanciacionTarjetaView.setMontoCuota(ISBANStringUtils
				.formatearConComaYDosDecimales(new Double(confirmacionSolicitudPlanV.getImporteCuotas()).toString()));
		feedbackFinanciacionTarjetaView.setCantidadCuotas(confirmacionSolicitudPlanV.getPlanCuotas() + "");
		feedbackFinanciacionTarjetaView.setNumeroDeComprobante(confirmacionSolicitudPlanV.getNumeroComprobante());
		feedbackFinanciacionTarjetaView.setMensajeFeedback(armarMensaje(dto, financiacionTarjetaView));
		feedbackFinanciacionTarjetaView
				.setLeyenda(legalBO.obtenerTextoLegal(CodigoMensajeConstantes.LEGAL_TICKET_COMPROBANTE));
		feedbackFinanciacionTarjetaView.setTNA(ISBANStringUtils
				.formatearConComaYDosDecimales(new Double(confirmacionSolicitudPlanV.getTNA()).toString()));
		feedbackFinanciacionTarjetaView.setCFT(ISBANStringUtils.formatearConComaYDosDecimales(
				new Double(confirmacionSolicitudPlanV.getCostoFinancieroTotal()).toString()));
		Date fechaHoraActual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String fechaHoraActualFormateada = sdf.format(fechaHoraActual);
		feedbackFinanciacionTarjetaView.setFechaHora(fechaHoraActualFormateada);
		return feedbackFinanciacionTarjetaView;
	}

	/**
	 * Carga la lista de tarjetas cuyo cliente es titular en la vista, se usa
	 * para el combo selector de tarjetas.
	 *
	 * @author emilio.watemberg
	 * @param list
	 *            the list
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 */
	private void cargarListaTarjetas(List<ResumenTarjetaDTO> list, FinanciacionTarjetaView financiacionTarjetaView) {
		List<TarjetaView> tarjetas = new ArrayList<TarjetaView>();
		for (ResumenTarjetaDTO resumenTarjetaDTO : ListUtil.safe(list)) {
			DetalleTarjetaDTO detalleTarjetaDTO = resumenTarjetaDTO.getDetalle();
			if (detalleTarjetaDTO.getIsTitular()) {
				TarjetaView tarjeta = new TarjetaView();
				tarjeta.setNumero(ISBANStringUtils.mascaraTarjetaCredito(detalleTarjetaDTO.getNroTarjeta(),
						detalleTarjetaDTO.getTipoCuenta()));
				tarjeta.setNroCuentaProducto(detalleTarjetaDTO.getNroCuentaProducto());
				tarjeta.setMarca(detalleTarjetaDTO.getMarca());
				tarjeta.setAlias(detalleTarjetaDTO.getAlias());
				tarjetas.add(tarjeta);
			}
		}
		financiacionTarjetaView.setTarjetas(tarjetas);
	}

	/**
	 * Grabar estadistica de acceso financiacion por tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param estado
	 *            the estado
	 */
	private void grabarEstadisticaDeAccesoFinanciacionPorTarjeta(Cuenta cuenta, String estado) {
		if (cuenta.getTipoCuentaEnum().getAbreviatura().equals(TipoCuenta.VISA.getAbreviatura())) {
			estadisticaManager.add(EstadisticasConstants.FINANCIACION_TARJETA_VISA, estado);
		} else if (cuenta.getTipoCuentaEnum().getAbreviatura().equals(TipoCuenta.AMEX.getAbreviatura())) {
			estadisticaManager.add(EstadisticasConstants.FINANCIACION_TARJETA_AMEX, estado);
		}
	}

	/**
	 * Grabar estadistica de volver a calcular financiacion por tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param estado
	 *            the estado
	 */
	private void grabarEstadisticaDeRecalcularFinanciacionPorTarjeta(Cuenta cuenta, String estado) {
		if (cuenta.getTipoCuentaEnum().getAbreviatura().equals(TipoCuenta.VISA.getAbreviatura())) {
			estadisticaManager.add(EstadisticasConstants.FINANCIACION_RECALCULAR_TARJETA_VISA, estado);
		} else if (cuenta.getTipoCuentaEnum().getAbreviatura().equals(TipoCuenta.AMEX.getAbreviatura())) {
			estadisticaManager.add(EstadisticasConstants.FINANCIACION_RECALCULAR_TARJETA_AMEX, estado);
		}
	}

	/**
	 * Grabar estadistica para confirmacion de la financiacion por tarjeta.
	 *
	 * @param marca
	 *            the marca tarjeta.
	 * @param estado
	 *            the estado, OK.
	 */
	private void grabarEstadisticaDeConfirmacionFinanciacionPorTarjeta(String marca, String estado) {
		if (marca.equalsIgnoreCase(TipoCuenta.VISA.getDescripcion())) {
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_FINANCIACION_TARJETA_VISA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_FINANCIACION_TARJETA_AMEX, estado);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * FinanciacionTarjetaManager#comprobanteFinanciacionTarjeta(ar.com.
	 * santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView)
	 */
	@Override
	public Respuesta<FinanciacionTarjetaView> comprobanteFinanciacionTarjeta(FinanciacionTarjetaView tarjeta) {
		String codigoTransaccion = null;
		String marcaTarjetaSeleccionada = tarjeta.getMarcaTarjetaSeleccionada();
		if (ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.VISA.getDescripcion()
				.equals(marcaTarjetaSeleccionada)) {
			codigoTransaccion = EstadisticasConstants.COMPROBANTE_FINANCIACION_VISA;
		} else if (ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.AMEX.getDescripcion()
				.equals(marcaTarjetaSeleccionada)) {
			codigoTransaccion = EstadisticasConstants.COMPROBANTE_FINANCIACION_AMEX;
		} else {
			return respuestaFactory.crearRespuestaError(FinanciacionTarjetaView.class, null);
		}
		estadisticaManager.add(codigoTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(tarjeta);
	}

	
}
