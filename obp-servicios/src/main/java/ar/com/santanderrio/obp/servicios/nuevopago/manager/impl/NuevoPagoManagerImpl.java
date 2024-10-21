package ar.com.santanderrio.obp.servicios.nuevopago.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EmisionGastosProtegidosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.FlagCompraProtegidaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.GastoProtegidoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuit.entities.Cuit;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.CuentaOrigenRSADTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.NuevaRecargaManager;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.OfertaComercialFeedback;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO;
import ar.com.santanderrio.obp.servicios.seguros.dto.EmisionOfertaIntegradaDTO;
import ar.com.santanderrio.obp.servicios.seguros.dto.FlagCompraProtegidaDTO;
import ar.com.santanderrio.obp.servicios.seguros.dto.GastoProtegidoDTO;
import ar.com.santanderrio.obp.servicios.seguros.dto.ItemsCoberturaDTO;
import ar.com.santanderrio.obp.servicios.seguros.dto.PolizaDTO;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.seguros.entities.GastoProtegido;
import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class NuevoPagoManagerImpl.
 *
 * @author
 */
@Component
public class NuevoPagoManagerImpl implements NuevoPagoManager {
	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NuevoPagoManagerImpl.class);

	/** The Constant WARNING_MONTO. */
	private static final String WARNING_MONTO = "WARNING_MONTO";

	/** The Constant ERROR_CUENTAS. */
	private static final String ERROR_CUENTAS = "ERROR_CUENTAS";

	/** The Constant ACCESO_NUEVA_RECARGA. */
	private static final String ACCESO_NUEVA_RECARGA = "0";

	/** The Constant ACCESO_NUEVO_PAGO. */
	private static final String ACCESO_NUEVO_PAGO = "1";

	/** The Constant ACCESO_CALENDARIO. */
	private static final String ACCESO_CALENDARIO = "2";

	/** The Constant VALIDANDO_HABILITACION_DE_DESAFIOS. */
	private static final String VALIDANDO_HABILITACION_DE_DESAFIOS = "Validando habilitacion de desafios.";

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The Constant MSJ_INFO_VALIDANDO_HASH. */
	private static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";
	/** NOMBRE_FANTASIA_AFIP */
	private static final String NOMBRE_FANTASIA_AFIP = "AFIP - PAGO DE IMPUESTOS AFIP (VEP)";

	/** The Constant COD_ERROR_TIMEOUT. */
	private static final String COD_ERROR_TIMEOUT = "10000004";

	/** The Constant v. */
	private static final String GOTO_SEGUROS_LINK = "gotoSeguro()";

	/** The Constant GOTO_SEGUROS_PARAMETRO. */
	private static final String GOTO_SEGUROS_PARAMETRO = "ip";

	/** The Constant GOTO_SEGUROS_GASTOS_PROTEGIDOS_PARAMETRO. */
	private static final String GOTO_SEGUROS_GASTOS_PROTEGIDOS_PARAMETRO = "cp";


	private static final String FIID_TUENTI = "QUAM";
	private static final String FIID_RECL = "RECL";
	private static final String FIID_REPE = "REPE";
	private static final String FIID_REMO = "REMO";

	private static final String FIID_REC_MOVI_TRANSPORTE = "TSCR";

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The nuevo pago BO. */
	@Autowired
	private NuevoPagoBO nuevoPagoBO;

	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	@Autowired
	private SeguroTenenciasBO seguroTenenciasBO;

	/** The validacion. */
	@Value("${TRJCOORD.OPERAINDISTINTO.NUEVOPAGO}")
	private String valorDesafio;

	/** The validacion. */
	@Value("${NUEVOPAGO.CARGARTARJETAS:true}")
	private String cargarTajetas;
	
	@Autowired
	private NuevaRecargaManager nuevaRecargaManager;

	@Autowired
	private ClienteBO clienteBO;
	
	
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.nuevopago.manager.NuevoPagoManager#
	 * obtenerFacturas(ar.com.santanderrio.obp.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.pagos.entities.MedioPagoView)
	 */
	@Override
	public Respuesta<MedioPagoSelectionView> obtenerFacturas(MedioPagoView medioPagoView) {
		LOGGER.info("Obtengo las facturas pendientes del cliente");
		Cliente cliente = sesionCliente.getCliente();
		Respuesta<MedioPagoSelectionView> respuesta = new Respuesta<MedioPagoSelectionView>();


		if (nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView)) {
			LOGGER.info("Codigo de pago electronico {} Valido!!!.", medioPagoView.getCodigoPagoElectronico());

			respuesta = nuevoPagoBO.obtenerPagos(cliente, medioPagoView);
			if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
				if(sesionParametros.getRespuestaPagosPendientes() == null) {
					List<PagoPendiente> listaInicialPagosPendientes = Collections.emptyList();
					sesionParametros.setRespuestaPagosPendientes(respuestaFactory.crearRespuestaOk(listaInicialPagosPendientes));
				}
				Respuesta<MedioPagoSelectionView> medioPagoSelection = obtenerCuentas(
						new ConsultaConfiguracionView(medioPagoView));

				if (EstadoRespuesta.OK.equals(medioPagoSelection.getEstadoRespuesta())) {
					respuesta.getRespuesta().setListaCuentas(medioPagoSelection.getRespuesta().getListaCuentas());
					respuesta.getRespuesta().setCantidadCuentas(medioPagoSelection.getRespuesta().getCantidadCuentas());
				} else {
					respuesta = respuestaFactory.crearRespuestaError(MedioPagoSelectionView.class,
							medioPagoSelection.getItemsMensajeRespuesta());
				}
			}
		} else {
			LOGGER.info("Codigo de pago electronico {} Invalido!!!.", medioPagoView.getCodigoPagoElectronico());

			respuesta = respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.FACTURA_SIN_DEUDA);
		}
		return respuesta;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<String> validarCuit(String cuit) {

		LOGGER.info("Validando CUIT");
		Respuesta<String> respuesta = new Respuesta<String>();
		Cuit unCuit = new Cuit(cuit);
		if (unCuit.esCuitValido()) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta("CUIT valido");
		} else {
			respuesta.setRespuesta(mensajeBO.obtenerMensajePorCodigo("1206").getMensaje());
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		}
		return respuesta;
	}

	/**
	 * Obtener cuentas.
	 *
	 * @param consulta
	 *            the consulta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<MedioPagoSelectionView> obtenerCuentas(ConsultaConfiguracionView consulta) {
		Cliente cliente = sesionCliente.getCliente();
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		boolean esError = false;

		try {
			LOGGER.info("Obtengo las cuentas del cliente: {}.", cliente);
			listaCuentas = cuentaBO.obtenerCuentasBanelcoPesos(cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener las cuentas para el cliente.", e);
			if (StringUtils.contains(e.getMessage(), "Error obteniendo cuentas Banelco")) {
				return obtenerRespuestaErrorSinCuentasEnBanelco(e.getMessage());
			}
			esError = true;
		}
		
		/******
         * MOCK
         ******/
//		for (Cuenta cuenta : cliente.getCuentas()) {
//		if (TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) || 
//			TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ||
//			TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar()))) {
//			listaCuentas.add(cuenta);
//			}
//		}
		
		MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(consulta.getFiid());
		List<Cuenta> listaTarjetas = obtenerTarjetas(cliente, medioPago);

		Respuesta<MedioPagoSelectionView> respuesta;
		if (CollectionUtils.isEmpty(listaCuentas) && CollectionUtils.isEmpty(listaTarjetas)) {
			LOGGER.error("No se obtuvieron cuentas ni tarjetas para pagar el medio de pagos: {}.", medioPago);
			if (esError) {
				respuesta = respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(ERROR_CUENTAS,
						CodigoMensajeConstantes.ERROR_OBTENER_CUENTAS_BANELCO);
			} else {
				respuesta = respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(ERROR_CUENTAS,
						CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);
			}
		} else {
			MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
			medioPagoSelectionView.setListaCuentasAView(listaCuentas, listaTarjetas);
			respuesta = respuestaFactory.crearRespuestaOk(medioPagoSelectionView);
		}
		return respuesta;
	}

	/**
	 * Obtener respuesta error sin cuentas en banelco.
	 *
	 * @param mensajeError
	 *            the mensaje error
	 * @return the respuesta
	 */
	private Respuesta<MedioPagoSelectionView> obtenerRespuestaErrorSinCuentasEnBanelco(String mensajeError) {
		if (StringUtils.contains(mensajeError, COD_ERROR_TIMEOUT)) {
			return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(TipoError.TIMEOUT.getDescripcion(),
					CodigoMensajeConstantes.ERROR_OBTENER_CUENTAS_BANELCO);
		}
		return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(ERROR_CUENTAS,
				CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);
	}

	/**
	 * Obtener tarjetas.
	 *
	 * 0: No permite pagos con TC 1: VISA (solo VISA) 2: MasterCard (solo
	 * MasterCard) 3: VISA y MasterCard 4: American Express Bancarias (solo
	 * Amex) 5: American y VISA 6: American y MasterCard 7: American, VISA y
	 * MasterCard
	 *
	 * @param cliente
	 *            the cliente
	 * @param medioPago
	 *            the medio pago
	 * @return the list
	 */

	private List<Cuenta> obtenerTarjetas(Cliente cliente, MedioPago medioPago) {
		List<Cuenta> listaTarjetas = new ArrayList<Cuenta>();
		if (Boolean.TRUE.equals(Boolean.parseBoolean(cargarTajetas))) {
			LOGGER.info("Obtengo las tarjetas que el medio de pago {} permite.", medioPago);
			String marca = medioPago.getMarcaPagoTc();
			if ("1".equals(marca) || "5".equals(marca) || "3".equals(marca) || "7".equals(marca)) {
				listaTarjetas.addAll(cliente.getCuentasTarjetaDeCreditoVISA());
			}
			/*
			 * if ("4".equals(marca) || "5".equals(marca) || "6".equals(marca)
			 * || "7".equals(marca)) {
			 * listaTarjetas.addAll(cliente.getCuentasTarjetaDeCreditoAMEX()); }
			 */
		}
		return listaTarjetas;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
	 * validarImporteFactura(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago)
	 */
	@Override
	public Respuesta<Boolean> validarImporteFactura(Cliente cliente, NuevoPago nuevoPago) {
		LOGGER.info("Se valida iporte factura fiid: s% y numero de factura: s%", nuevoPago.getFiid(),
				nuevoPago.getFacturaNumero());
		return nuevoPagoBO.validarImporteFactura(cliente, nuevoPago);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
	 * estadisticaComprobanteNuevoPago()
	 */
	@Override
	public void estadisticaComprobanteNuevoPago(CuentaSeleccionView seleccion) {
		if (seleccion.isTarjeta()) {
			estadisticaManager.add(EstadisticasConstants.NUEVO_PAGO_VISUALIZACION_COMPROBANTE_CON_TARJETA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.NUEVO_PAGO_VISUALIZACION_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/** {@inheritDoc} */
	@Override
	public Respuesta<NuevoPago> pagoPuntual(NuevoPago nuevoPagoView) {

		validarHashNuevoPago(nuevoPagoView);
		inicializarReintentos(nuevoPagoView);
		Cliente cliente = sesionCliente.getCliente();

		MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(nuevoPagoView.getFiid());
		nuevoPagoView.setNombreEmpresa(NuevoPagoUtils.nombreEmpresa(medioPago.getNombreFantasia()));

		Cuenta cuenta = cliente.getCuenta(nuevoPagoView.getCuentaSeleccionada());
		if (cuenta == null) {
			LOGGER.error("No se encontro la cuenta buscada con el cbu: {}.", nuevoPagoView.getCuentaSeleccionada());
			return errorGenerico(nuevoPagoView);
		}

		Respuesta<NuevoPago> respuesta = ejecutarValidacionRSA(nuevoPagoView, nuevoPagoView.getNombreEmpresa(), cuenta);
		LOGGER.info("Ejecutando validacion de RSA con respuesta: {}.", respuesta);
		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}
		LOGGER.info("Validacion RSA Exitosa. Ejecutando la confirmacion de un pago..");

		try {
			PagoMisCuentasDTO pagoMisCuentasDTO = generarPagoMisCuentasDTO(nuevoPagoView, cuenta, medioPago, cliente);
			Respuesta<List<PagoPendiente>> pagos = sesionParametros.getRespuestaPagosPendientes();
			List<PagoPendiente> pagosPendientes = filtrarPagos(pagos.getRespuesta(), nuevoPagoView.getFiid(),
					nuevoPagoView.getCodigoPagoElectronico());
			if (!pagosPendientes.isEmpty()) {
				if(pagosPendientes.get(0).getImporte() != null) {
					pagoMisCuentasDTO.setMontoDeGrilla(pagosPendientes.get(0).getImporte().toString());
				} else {
					pagoMisCuentasDTO.setMontoDeGrilla(pagosPendientes.get(0).getImporteUSS().toString());
				}
			}
			NuevaRecargaOutDTO pagoMisCuentasOutDTO = nuevoPagoBO.pagar(medioPago, pagoMisCuentasDTO, cliente);
			parsearFacturaNumero(nuevoPagoView);
			if (pagoMisCuentasOutDTO.getRespuestaOK()) {
				respuesta = pagoPuntualRespuestaExitosa(nuevoPagoView, pagoMisCuentasDTO, pagoMisCuentasOutDTO);
			} else {
				respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(NuevoPago.class,
						pagoMisCuentasOutDTO.getMensaje(), pagoMisCuentasOutDTO.getTipoError().getDescripcion());
			}

		} catch (BusinessException e) {
			LOGGER.error("Error al tratar de efectuar un nuevo pago", e);
			if ("TIMEOUT".equals(e.getMessage())) {
				respuesta = errorTimeOutPrisma(nuevoPagoView);
			} else {
				respuesta = errorGenerico(nuevoPagoView);
			}
		}
		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && reintentosAgotados(nuevoPagoView)) {
			respuesta.getItemsMensajeRespuesta().get(0)
					.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
		}
		if (cuenta.esTarjetaDeCredito()) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_PAGOPUNTUAL_PAGAR_CON_TC,
					EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
							? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
							: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(EstadisticasConstants.CODIGO_PAGOPUNTUAL_OPCION_PAGAR,
					EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
							? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
							: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			OfertaComercialFeedback ofertaComercialFeedback = new OfertaComercialFeedback();

			for (OfertaComercialDTO oferta : sesionParametros.getOfertasComerciales().getOfertas()) {
				if (GOTO_SEGUROS_LINK.equals(oferta.getGotoLink().getLink()) && GOTO_SEGUROS_GASTOS_PROTEGIDOS_PARAMETRO
						.contentEquals(oferta.getGotoLink().getParametros().replace("\'", ""))) {
					settearOfertaComercialFeedback(ofertaComercialFeedback, oferta);
					nuevoPagoView.setTipoGestor(GOTO_SEGUROS_GASTOS_PROTEGIDOS_PARAMETRO);
					break;
				} else {
					if (GOTO_SEGUROS_LINK.equals(oferta.getGotoLink().getLink())
							&& GOTO_SEGUROS_PARAMETRO.contentEquals(oferta.getGotoLink().getParametros().replace("\'", ""))) {
						settearOfertaComercialFeedback(ofertaComercialFeedback, oferta);
						nuevoPagoView.setTipoGestor(GOTO_SEGUROS_PARAMETRO);
					}
				}
			}
			nuevoPagoView.setOfertaComercialFeedback(ofertaComercialFeedback);
		}

		respuesta.setRespuesta(nuevoPagoView);
		respuesta.setRespuestaVacia(false);
		sesionParametros.setNuevoPagoView(respuesta.getRespuesta());
		return respuesta;

	}

	private void settearOfertaComercialFeedback(OfertaComercialFeedback ofertaComercialFeedback,
												OfertaComercialDTO oferta) {
		ofertaComercialFeedback.setTitulo(oferta.getTitulo());
		ofertaComercialFeedback.setDescripcion(oferta.getDescripcion());
		ofertaComercialFeedback.setId(oferta.getIdRtd());
	}

	/**
	 * Parsear factura numero.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 */
	private void parsearFacturaNumero(NuevoPago nuevoPagoView) {
		if (StringUtils.isNotBlank(nuevoPagoView.getFacturaNumero())
				&& nuevoPagoView.getFacturaNumero().length() >= 19) {
			nuevoPagoView.setNumeroVep(nuevoPagoView.getFacturaNumero().substring(0, 12));
			nuevoPagoView.setPeriodo(nuevoPagoView.getFacturaNumero().substring(12, 14) + "/"
					+ nuevoPagoView.getFacturaNumero().substring(14, 16));
			nuevoPagoView.setAnticipo(nuevoPagoView.getFacturaNumero().substring(16, 19));
		}
	}

	/**
	 * Reintentos agotados.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 * @return the boolean
	 */
	private Boolean reintentosAgotados(NuevoPago nuevoPago) {
		Boolean permiteReintentos = sesionParametros.getContador().permiteReintentar();
		nuevoPago.setReintentar(String.valueOf(permiteReintentos));
		return !permiteReintentos;
	}

	/**
	 * Solicitude de Pago Puntual. Se graban estadisticas. DTF:9775.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@Override
	public void grabarEstadisticaPagoPuntual(PagoPendienteView pagoPendienteView) {
		if (pagoPendienteView.getIsRecargar()) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_PAGOPUNTUAL_OPCION_RECARGAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		estadisticaManager.add(EstadisticasConstants.CODIGO_PAGOPUNTUAL_OPCION_PAGAR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Estadistica Nueva recarga – Ingreso N° identificador – Acceso.
	 *
	 * @param puntoDeAcceso
	 *            the punto de acceso
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRecargaIngresoNumero(PuntoDeAccesoView puntoDeAcceso) {
		LOGGER.info("Entro al manager para grabar estadistica de acceso a ingreso de numero para recarga");
		if (ACCESO_NUEVA_RECARGA.equals(puntoDeAcceso.getPuntoDeAcceso())) {
			estadisticaManager.add(EstadisticasConstants.ACCESO_RECARGA_INGRESO_NUMERO_NUEVA_RECARGA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (ACCESO_NUEVO_PAGO.equals(puntoDeAcceso.getPuntoDeAcceso())) {
			estadisticaManager.add(EstadisticasConstants.ACCESO_RECARGA_CONFIGURACION_NUMERO_NUEVO_PAGO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return new Respuesta<Void>();
	}

	/**
	 * Obtiene la lista de cuentas e importes para realizar una recarga de
	 * telefono o sube.
	 *
	 * @param consultaConfiguracionView
	 *            the consulta configuracion recarga view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfiguracionRecargaView> obtenerConfiguracionRecarga(
			ConsultaConfiguracionView consultaConfiguracionView) {
		LOGGER.info("Entro al manager para obtener la configuracion de recarga");
		ConfiguracionRecargaView configuracionRecargaView = new ConfiguracionRecargaView();
		Respuesta<ConfiguracionRecargaView> respuesta;

		Respuesta<MedioPagoSelectionView> medioPagoSelection = obtenerCuentas(consultaConfiguracionView);

		if (EstadoRespuesta.OK.equals(medioPagoSelection.getEstadoRespuesta())) {
			configuracionRecargaView.setListaCuentas(medioPagoSelection.getRespuesta().getListaCuentas());
			MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(consultaConfiguracionView.getFiid());

			TipoMedioPagoBO tipoMedioPagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);
			if (TipoNuevoPagoEnum.SUBE_RECARGA.equals(tipoMedioPagoBO.getTipoNuevoPagoEnum())) {
				LOGGER.info("Confiruracion recarga sube");
				configuracionRecargaView.setMontosRecargaSube();
				configuracionRecargaView.setTipoNuevoPago(TipoNuevoPagoEnum.SUBE_RECARGA.getId());
				configuracionRecargaView.setMensajeRecargaSube(mensajeBO.obtenerMensajePorCodigo("1559").getMensaje());
			} else if (TipoNuevoPagoEnum.CELULAR_RECARGA.equals(tipoMedioPagoBO.getTipoNuevoPagoEnum())) {
				LOGGER.info("Configuracion recarga celular");
				configuracionRecargaView.cargarMontosRecargaTelefono(consultaConfiguracionView.getFiid());

				configuracionRecargaView.setTipoNuevoPago(TipoNuevoPagoEnum.CELULAR_RECARGA.getId());	
				if (StringUtils.isNotEmpty(consultaConfiguracionView.getNumeroCelular())) {
					configuracionRecargaView.setAliasNumeroCelular(nuevaRecargaManager.obtenerAlias(consultaConfiguracionView.getNumeroCelular()).getRespuesta());
				}
			} else if (TipoNuevoPagoEnum.MOVI_TRANSP_RECARGA.equals(tipoMedioPagoBO.getTipoNuevoPagoEnum())) {				
				LOGGER.info("Confiruracion recarga transporte movi");			
				configuracionRecargaView.setMontosRecargaTransporteMovi();
				configuracionRecargaView.setTipoNuevoPago(TipoNuevoPagoEnum.MOVI_TRANSP_RECARGA.getId());
			}

			if (ACCESO_CALENDARIO.equals(consultaConfiguracionView.getPuntoDeAcceso())
					&& !FIID_TUENTI.equals(consultaConfiguracionView.getFiid())
					&& !FIID_RECL.equals(consultaConfiguracionView.getFiid())
					&& !FIID_REPE.equals(consultaConfiguracionView.getFiid())
					&& !FIID_REMO.equals(consultaConfiguracionView.getFiid())
					&& !configuracionRecargaView.montoValido(consultaConfiguracionView.getMonto())) {
				respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(configuracionRecargaView,
						WARNING_MONTO, CodigoMensajeConstantes.WARNING_RECARGA_CONFIGURACION_MONTO, null);
			} else {
				respuesta = respuestaFactory.crearRespuestaOk(configuracionRecargaView);
			}
		} else {
			respuesta = respuestaFactory.crearRespuestaError(ConfiguracionRecargaView.class,
					medioPagoSelection.getItemsMensajeRespuesta());
		}

		grabarEstadisticasPorAcceso(consultaConfiguracionView, respuesta);
		return respuesta;
	}

	/**
	 * Error generico.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @return the respuesta
	 */
	private Respuesta<NuevoPago> errorGenerico(NuevoPago nuevoPagoView) {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.RECARGA_PAGO_ERROR_GENERICO, "Pago", nuevoPagoView.getNombreEmpresa(),
				formatearSaldo(nuevoPagoView.getMonto()));
	}

	/**
	 * Error time out prisma.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @return the respuesta
	 */
	private Respuesta<NuevoPago> errorTimeOutPrisma(NuevoPago nuevoPagoView) {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.PRISMA_TIMEOUT_PAGOS);
	}

	/**
	 * Formatear saldo.
	 *
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	private String formatearSaldo(String monto) {
		return ISBANStringUtils.formatearSaldoSinAbsConDivisa(new BigDecimal(monto), DivisaEnum.PESO);
	}

	/**
	 * Pago puntual respuesta exitosa.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param pagoMisCuentasDTO
	 *            the pago mis cuentas DTO
	 * @param pagoMisCuentasOutDTO
	 *            the pago mis cuentas out DTO
	 * @return the respuesta
	 */
	private Respuesta<NuevoPago> pagoPuntualRespuestaExitosa(NuevoPago nuevoPagoView,
															 PagoMisCuentasDTO pagoMisCuentasDTO, NuevaRecargaOutDTO pagoMisCuentasOutDTO) {

		estadisticaManager.add(EstadisticasConstants.CODIGO_PAGOPUNTUAL_OPCION_PAGAR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		String mensajeOK = this.mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_OK_PAGO_MIS_CUENTAS)
				.getMensaje();
		mensajeOK = MessageFormat.format(mensajeOK, "Pago", pagoMisCuentasDTO.getDescripcionServicioPago(),
				pagoMisCuentasDTO.getSimboloMoneda() + formatoAMostrarMonto(pagoMisCuentasDTO.getMontoSinFormatear()));

		nuevoPagoView.cargarComprobante(pagoMisCuentasOutDTO);
		nuevoPagoView.setMensajeFeedback(mensajeOK);
		nuevoPagoView.setFechaVencimiento(pagoMisCuentasOutDTO.getFechaVencimiento());
		
		if(nuevoPagoView != null && nuevoPagoView.getEmisionGastosProtegido() != null) {
			
			Cliente cliente = sesionCliente.getCliente();
			Cuenta cuenta = cliente.getCuenta(nuevoPagoView.getEmisionGastosProtegido().getCbu());
			if(cuenta == null){
				LOGGER.info("El Cbu no pertence a la cuenta");
				nuevoPagoView.setNroPolizaGastosProtegido(null);
			}

		
			Respuesta<EmisionOfertaIntegradaDTO> emitirGastosProtegidos = seguroTenenciasBO.emisionOfertaIntegradaGastoProtegido(nuevoPagoView.getEmisionGastosProtegido(), cliente, cuenta.getNroSucursal(), cuenta.getTipoCuenta(), cuenta.getNroCuentaProducto(), pagoMisCuentasDTO.getDescripcionServicioPago());

			if(emitirGastosProtegidos != null && emitirGastosProtegidos.getRespuesta() != null && emitirGastosProtegidos.getRespuesta().getNumeroPoliza() != null) {
				nuevoPagoView.setNroPolizaGastosProtegido(emitirGastosProtegidos.getRespuesta().getNumeroPoliza().toString());
			}
			if(nuevoPagoView.getAlias() != null){
				nuevoPagoView.setAlias(nuevoPagoView.getAlias());
			}
			if(nuevoPagoView.getNumero() != null){
				nuevoPagoView.setNumero(nuevoPagoView.getNumero());
			}
			if(nuevoPagoView.getDescripcionTipoCuenta() != null){
				nuevoPagoView.setDescripcionTipoCuenta(nuevoPagoView.getDescripcionTipoCuenta());
			}
		}

		return respuestaFactory.crearRespuestaOk(nuevoPagoView);
	}

	/**
	 * Genero una instancia de PagoMisCuentasDTO llamando a los servicios
	 * correspondientes.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param cuenta
	 *            the cuenta
	 * @param medioPago
	 *            the medio pago
	 * @param cliente
	 *            the cliente
	 * @return the pago mis cuentas DTO
	 */
	private PagoMisCuentasDTO generarPagoMisCuentasDTO(NuevoPago nuevoPagoView, Cuenta cuenta, MedioPago medioPago,
													   Cliente cliente) {
		boolean validarImporte = false;
		if (medioPago.getTipoPago().equals(3)) {
			Respuesta<Boolean> respuestaBoolean = nuevoPagoBO.validarImporteFactura(cliente, nuevoPagoView);
			validarImporte = BooleanUtils.toBoolean(respuestaBoolean.getRespuesta());
		}
		return new PagoMisCuentasDTO(nuevoPagoView, cuenta, medioPago, validarImporte);
	}

	/**
	 * Ejecutar validacion RSA.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param nombreFantasia
	 *            the nombre fantasia
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	private Respuesta<NuevoPago> ejecutarValidacionRSA(NuevoPago nuevoPagoView, String nombreFantasia, Cuenta cuenta) {
		LOGGER.info("Se ejecuta la validacion por RCA");
		AutentificacionDTO autentificacionDTO;

		Respuesta<NuevoPago> estadoDesafio = autentificacionManager.verificarEstadoDesafio(nuevoPagoView.getDesafio(),
				Integer.parseInt(valorDesafio));
		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = nuevoPagoView.getDesafio();
		} else if (Boolean.TRUE.equals(nuevoPagoView.getIsFromCalendario())
				|| EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = generarAutentificacionDTO(nuevoPagoView, nombreFantasia, cuenta);
		} else {
			return estadoDesafio;
		}
		// Transformacion respuesta RSA
		if (nuevoPagoView.getIsFromCalendario().equals(Boolean.TRUE)) {
			autentificacionDTO.getRsaDTO().setIgnorarRSA(Boolean.TRUE);
		}
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);
		nuevoPagoView.setDesafio(rstaAutentificacion.getRespuesta());

		Respuesta<NuevoPago> respuesta = respuestaFactory.transformar(nuevoPagoView, rstaAutentificacion);

		if (nuevoPagoView.getIsFromCalendario() && EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		}

		return respuesta;
	}

	/**
	 * Generar autentificacion DTO.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param nombreFantasia
	 *            the nombre fantasia
	 * @param cuenta
	 *            the cuenta
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO generarAutentificacionDTO(NuevoPago nuevoPagoView, String nombreFantasia,
														 Cuenta cuenta) {
		LOGGER.info("Se carga la informacion de autentificationDTO");
		Integer operacion = Integer.parseInt(valorDesafio);
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(operacion);
		autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
				new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

		// Carga de estadisticas
		autentificacionDTO.setCodigoEstadisticaSinDesafios(EstadisticasConstants.NUEVO_PAGO_VALIDACION_SIN_METODO_RSA);
		autentificacionDTO
				.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLICITUD_COORDENADAS_NUEVO_PAGO);
		autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.VALIDACION_TARJETA_COORDENADAS_NUEVO_PAGO);
		autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_NUEVO_PAGO);
		// si es afip mando otra estadistica
		autentificacionDTO.setCodigoEstadisticaValidacionToken(nombreFantasia.equalsIgnoreCase(NOMBRE_FANTASIA_AFIP)
				? EstadisticasConstants.VALIDACION_RSA_TOKEN_PAGOS_DDJJ
				: EstadisticasConstants.VALIDACION_TOKEN_NUEVO_PAGO);
		autentificacionDTO.setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.SOLICITUD_8_DIGITOS_NUEVO_PAGO);
		autentificacionDTO.setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.VALIDACION_8_DIGITOS_NUEVO_PAGO);

		// Datos informados a RSA
		
		NuevaRecargaRSADTO nuevaRecargaRSADTO = new NuevaRecargaRSADTO(operacion, obtenerMonto(nuevoPagoView.getMonto()),
				new CuentaOrigenRSADTO(cuenta, DivisaEnum.PESO), nombreFantasia, Boolean.TRUE, nuevoPagoView.getIsFromCalendario());
		nuevaRecargaRSADTO.setCodigoPagoElectronico(nuevoPagoView.getCodigoPagoElectronico());
		
		cargarUltimosDiasDesdeCambioDeClaveToken(nuevaRecargaRSADTO);

		autentificacionDTO.setRsaDTO(nuevaRecargaRSADTO);

		return autentificacionDTO;
	}

	/**
	 * Obtener monto.
	 *
	 * @param monto
	 *            the monto esperado como 1000.1 1000.11 1000
	 * @return the long
	 */
	private Long obtenerMonto(String monto) {
		if (StringUtils.isBlank(monto)) {
			return null;
		}
		return new BigDecimal(monto).movePointRight(2).longValue();
	}

	/**
	 * Inicializar reintentos.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 */
	private void inicializarReintentos(NuevoPago nuevoPagoView) {
		if (!"true".equals(nuevoPagoView.getReintentar())) {
			sesionParametros.setContador(new ContadorIntentos(2));
			nuevoPagoView.setReintentar("true");
		}
	}

	/**
	 * Se agrega la coma para q se muestre en FE.
	 *
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	private String formatoAMostrarMonto(String monto) {
		return ISBANStringUtils.formatearSaldo(new BigDecimal(monto));
	}

	/**
	 * Grabar estadisticas por acceso.
	 *
	 * @param consultaConfiguracionRecargaView
	 *            the consulta configuracion recarga view
	 * @param respuesta
	 *            the respuesta
	 */
	private void grabarEstadisticasPorAcceso(ConsultaConfiguracionView consultaConfiguracionRecargaView,
											 Respuesta<ConfiguracionRecargaView> respuesta) {
		String estadoEstadistica = EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())
				? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR : EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		if (ACCESO_NUEVA_RECARGA.equals(consultaConfiguracionRecargaView.getPuntoDeAcceso())) {
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_CONFIGURAR_CUENTA_MONTO, estadoEstadistica);
		} else if (ACCESO_CALENDARIO.equals(consultaConfiguracionRecargaView.getPuntoDeAcceso())) {
			estadisticaManager.add(EstadisticasConstants.ACCESO_RECARGA_CONFIGURACION_CALENDARIO, estadoEstadistica);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
	 * verificarFactoresAutenticacion()
	 */
	@Override
	public Respuesta<Boolean> verificarFactoresAutenticacion() {
		LOGGER.info(VALIDANDO_HABILITACION_DE_DESAFIOS);
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(Integer.parseInt(valorDesafio));
		return autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(autentificacionDTO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
	 * descargarComprobantePDF(java.lang.String)
	 */

	public Respuesta<ReporteView> descargarComprobantePDF(NuevoPago nuevoPagoView) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(sesionParametros.getNuevoPagoView(),
				nuevoPagoView.getFechaVencimiento());
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add("13685", EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add("13685", EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
	 * estadisticaComprobanteNuevoPago()
	 */
	@Override
	public Respuesta<Void> continuarPago(NuevoPago nuevoPago) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVista(nuevoPago));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sesionParametros.setValidacionHash(hashView);
		return new Respuesta<Void>();
	}

	@Override
	public Respuesta<Poliza> consultarPolizaExistente(String ramo, String tokenJwt) {
		Cliente cliente = sesionCliente.getCliente();

		Respuesta<PolizaDTO> polizasEnCurso = seguroTenenciasBO.consultarPoliza(tokenJwt, cliente);
		if (polizasEnCurso != null && polizasEnCurso.getRespuesta() != null) {
			for (Poliza polizas : polizasEnCurso.getRespuesta().getPoliza()) {
				if (polizas.getTitulo().equalsIgnoreCase(ramo) && polizas.getEstadoPoliza() == 1) {
					LOGGER.info("El cliente ya tiene un seguro de Gastos Protegidos");
					Respuesta<Poliza> respuesta = new Respuesta<Poliza>();
					respuesta.setRespuesta(polizas);
					respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
					return respuesta;
				}
			}
		}
		Respuesta<Poliza> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
					StringUtils.EMPTY, StringUtils.EMPTY);
		return respuesta;
	}

	@Override
	public Respuesta<GastoProtegidoView> consultarGastosProtegidos(GastoProtegido gastoProtegido) {
		Cliente cliente = sesionCliente.getCliente();
		Respuesta<FlagCompraProtegidaDTO> flagCompraProtegida = seguroTenenciasBO.flagCompraProtegida(cliente);
		if(flagCompraProtegida != null && flagCompraProtegida.getRespuesta() != null && !flagCompraProtegida.getRespuesta().getRespuesta()){
			Respuesta<GastoProtegidoView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
					StringUtils.EMPTY, StringUtils.EMPTY);
			return respuesta;
		}
		Respuesta<PolizaDTO> polizasEnCurso = seguroTenenciasBO.consultarPoliza(flagCompraProtegida.getRespuesta().getTokenJwt(), cliente);
		if(polizasEnCurso != null && polizasEnCurso.getRespuesta() != null) {
			for(Poliza polizas : polizasEnCurso.getRespuesta().getPoliza()) {
				if(polizas.getTitulo().equalsIgnoreCase("GASTOS PROTEGIDOS") && polizas.getEstadoPoliza() == 1){
					LOGGER.info("El cliente ya tiene un seguro de Gastos Protegidos");
					Respuesta<GastoProtegidoView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
							StringUtils.EMPTY, StringUtils.EMPTY);
					return respuesta;
				}
			}
		}
			Respuesta<GastoProtegidoView> respuesta = new Respuesta<GastoProtegidoView>();
			Respuesta<GastoProtegidoDTO> gastoProtegidoDTORespuesta = seguroTenenciasBO.consultarGastoProtegido(gastoProtegido, cliente, flagCompraProtegida.getRespuesta().getTokenJwt());
			GastoProtegidoView gastoProtegidoView = new GastoProtegidoView();
			gastoProtegidoView = generateGastosProtegidos(gastoProtegidoDTORespuesta.getRespuesta(), gastoProtegido);
			respuesta.setRespuesta(gastoProtegidoView);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			return respuesta;
	}

	private GastoProtegidoView generateGastosProtegidos(GastoProtegidoDTO gastoProtegidoDTO, GastoProtegido gastoProtegidoIn) {


		Cliente cliente = sesionCliente.getCliente();
		GastoProtegidoView gastoProtegido = new GastoProtegidoView();
		EmisionOfertaIntegrada emisionOfertaIntegrada = new EmisionOfertaIntegrada();
		String texto1 = "";
		String texto2 = "";
		emisionOfertaIntegrada.setCargoPEP(gastoProtegidoDTO.getValoresDefault().getIndicadorPEP());
		emisionOfertaIntegrada.setCodigoRamo(gastoProtegidoDTO.getDatosPlan().getCodigoRamo());
		emisionOfertaIntegrada.setOrigenFondos(gastoProtegidoDTO.getValoresDefault().getOrigenFondos());
		emisionOfertaIntegrada.setCodigoPlan(gastoProtegidoDTO.getDatosPlan().getCodigoPlan());
		emisionOfertaIntegrada.setCodigoProducto(gastoProtegidoDTO.getDatosPlan().getCodigoProducto());
		if (gastoProtegidoDTO != null && gastoProtegidoDTO.getDatosCotizacion() != null) {
			gastoProtegido.setCuota(ISBANStringUtils.formatearConComaYDosDecimales2(gastoProtegidoDTO.getDatosCotizacion().getCuota().toString()));
			gastoProtegido.setSumaAsegurada(ISBANStringUtils.formatearConComaYDosDecimales2(gastoProtegidoDTO.getDatosCotizacion().getSumaAsegurada().toString()));
			emisionOfertaIntegrada.setNumeroCotizacion(gastoProtegidoDTO.getDatosCotizacion().getNumeroCotizacion());
		}
		for (ItemsCoberturaDTO items : gastoProtegidoDTO.getTextos()) {
			if (!StringUtils.isEmpty(items.getTexto()) && items.getCodTexto() != null && items.getCodTexto().equalsIgnoreCase("1")) {
				gastoProtegido.setMensajeSwitchOff(items.getTexto());
			}
			if (!StringUtils.isEmpty(items.getTexto()) && items.getCodTexto() != null && items.getCodTexto().equalsIgnoreCase("2")) {
				texto1 = items.getTexto();
			}
			if (!StringUtils.isEmpty(items.getTexto()) && items.getCodTexto() != null && items.getCodTexto().equalsIgnoreCase("3")){
				texto2 = items.getTexto();
			}

		}

		if (gastoProtegido.getMensajeSwitchOff() != null || !StringUtils.isEmpty(gastoProtegido.getMensajeSwitchOff()) || gastoProtegidoDTO.getDatosPlan() != null || gastoProtegidoDTO.getDatosPlan().getNombrePlan() != null) {
			gastoProtegido.setShowOfertaIntegrada(true);
			gastoProtegido.setStopOfertaIntegrada(false);
			gastoProtegido.setContrato(texto1 + "\n" + texto2);
			gastoProtegido.setGastoProtegidoTitulo("Activá la protección en caso de desempleo");
			gastoProtegido.setEmisionOfertaIntegrada(emisionOfertaIntegrada);
			if(gastoProtegidoDTO.getDatosAsegurado() != null && gastoProtegidoDTO.getDatosAsegurado().getCodigoOcupacion() != null) {
				gastoProtegido.setCodigoOcupacion(gastoProtegidoDTO.getDatosAsegurado().getCodigoOcupacion());
			}
			if(gastoProtegidoDTO.getValoresDefault() != null) {
				gastoProtegido.setTipoPoliza(gastoProtegidoDTO.getValoresDefault().getTipoPoliza() != null ? gastoProtegidoDTO.getValoresDefault().getTipoPoliza() : "");
				gastoProtegido.setIndicadorPEP(gastoProtegidoDTO.getValoresDefault().getIndicadorPEP() != null ? gastoProtegidoDTO.getValoresDefault().getIndicadorPEP() : "");
				gastoProtegido.setOrigenFondos(gastoProtegidoDTO.getValoresDefault().getOrigenFondos() != null ? gastoProtegidoDTO.getValoresDefault().getOrigenFondos() : "");
			}
		} else {
			gastoProtegido.setShowOfertaIntegrada(false);
		}


		return gastoProtegido;
	}


	public Respuesta<EmisionGastosProtegidosView> emitirGastosProtegidos (EmisionOfertaIntegrada nuevaEmisioView){

		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuenta(nuevaEmisioView.getCbu());
		if(cuenta == null){
			LOGGER.info("El Cbu no pertence a la cuenta");
			Respuesta<EmisionGastosProtegidosView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY,
					StringUtils.EMPTY);
			return respuesta;
		}

		Respuesta<EmisionOfertaIntegradaDTO> emisionOfertaIntegrada = seguroTenenciasBO.emisionOfertaIntegradaGastoProtegido(nuevaEmisioView, cliente, cuenta.getNroSucursal(), cuenta.getTipoCuenta(), cuenta.getNroCuentaProducto(), "");
		Respuesta<EmisionGastosProtegidosView> respuesta = new Respuesta<EmisionGastosProtegidosView>();
		EmisionGastosProtegidosView emisionGastosProtegidosView = new EmisionGastosProtegidosView();
		emisionGastosProtegidosView = generateEmisionGastosProtegidos(emisionOfertaIntegrada.getRespuesta());
		respuesta.setRespuesta(emisionGastosProtegidosView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		return respuesta;
	}

	private EmisionGastosProtegidosView generateEmisionGastosProtegidos (EmisionOfertaIntegradaDTO
																				 emisionOfertaIntegradaDTO){
		EmisionGastosProtegidosView emisionGastosProtegidosView = new EmisionGastosProtegidosView();
		emisionGastosProtegidosView.setCodigoRamo(emisionOfertaIntegradaDTO.getCodigoRamo().toString());
		emisionGastosProtegidosView.setFecha(emisionOfertaIntegradaDTO.getFecha());
		emisionGastosProtegidosView.setHora(emisionOfertaIntegradaDTO.getHora());
		emisionGastosProtegidosView.setNumeroCertificado(emisionOfertaIntegradaDTO.getNumeroCertificado().toString());
		emisionGastosProtegidosView.setNumeroMedioPago(emisionOfertaIntegradaDTO.getNumeroMedioPago());
		emisionGastosProtegidosView.setNumeroPoliza(emisionOfertaIntegradaDTO.getNumeroPoliza().toString());
		emisionGastosProtegidosView.setTipoMedioPago(emisionOfertaIntegradaDTO.getTipoMedioPago());
		emisionGastosProtegidosView.setVigenciaHasta(emisionOfertaIntegradaDTO.getVigenciaHasta());
		emisionGastosProtegidosView.setVigenciaInicio(emisionOfertaIntegradaDTO.getVigenciaInicio());
		return emisionGastosProtegidosView;
	}

	public Respuesta<FlagCompraProtegidaView> getFlagCompraProtegida () {
		Cliente cliente = sesionCliente.getCliente();
		Respuesta<FlagCompraProtegidaDTO> compraProtegidaDTO = seguroTenenciasBO.flagCompraProtegida(cliente);
		Respuesta<FlagCompraProtegidaView> respuesta = new Respuesta<FlagCompraProtegidaView>();
		FlagCompraProtegidaView flagCompraProtegidaView = new FlagCompraProtegidaView();
		flagCompraProtegidaView = generateFlagCompraProtegidaView(compraProtegidaDTO.getRespuesta());
		respuesta.setRespuesta(flagCompraProtegidaView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		return respuesta;
	}

	private FlagCompraProtegidaView generateFlagCompraProtegidaView(FlagCompraProtegidaDTO flagCompraProtegidaDTO){
		FlagCompraProtegidaView flagCompraProtegidaView = new FlagCompraProtegidaView();
		flagCompraProtegidaView.setFlagCompraProtegida(flagCompraProtegidaDTO.getRespuesta());
		return  flagCompraProtegidaView;
	}

	/**
	 * Crear mapa de la vista.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVista(NuevoPago nuevoPago) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("numeroIdentificacion", nuevoPago.getIdentificacion());
		mapaAtributos.put("numeroDeCuenta", nuevoPago.getCuentaSeleccionada());
		mapaAtributos.put("importe", nuevoPago.getMonto());
		mapaAtributos.put("codigoPagoElectronico", nuevoPago.getCodigoPagoElectronico());
		mapaAtributos.put("codigoPagoElectronico2", nuevoPago.getCodigoPagoElectronico2());
		mapaAtributos.put("numeroVep", nuevoPago.getNumeroVep());
		LOGGER.info("String mapa vista: {}.", mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash nuevo pago.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 */
	private void validarHashNuevoPago(NuevoPago nuevoPago) {
		String inputHash = HashUtils.obtenerHash(crearMapaDeLaVista(nuevoPago));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sesionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Filtrar pagos.
	 *
	 * @param pagos
	 *            the pagos
	 * @param fiid
	 *            the fiid
	 * @param codigoPagoElectronico
	 *            the codigo pago electronico
	 * @return the list
	 */
	private List<PagoPendiente> filtrarPagos(List<PagoPendiente> pagos, String fiid, String codigoPagoElectronico) {
		List<PagoPendiente> pagosEncontrados = new ArrayList<PagoPendiente>();
		for (PagoPendiente pago : pagos) {
			if (pago.getCodigoEmpresa().trim().equals(fiid.trim())
					&& pago.getCodigoClienteEmpresa().trim().equals(codigoPagoElectronico.trim())) {
				pagosEncontrados.add(pago);
			}
		}
		return pagosEncontrados;
	}
	
	@Override
	public void cargarUltimosDiasDesdeCambioDeClaveToken(NuevaRecargaRSADTO nuevaRecargaRSADTO) {
		
		try {
			Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						nuevaRecargaRSADTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
						LOGGER.info("Valor de ultimos dias cambio clave: {}", antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						nuevaRecargaRSADTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
						LOGGER.info("Valor de ultimos dias cambio token: {}", antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}
		
	}
}