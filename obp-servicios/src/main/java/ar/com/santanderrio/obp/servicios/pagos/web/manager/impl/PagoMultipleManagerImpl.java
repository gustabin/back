/**
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO;
import ar.com.santanderrio.obp.servicios.seguros.dto.EmisionOfertaIntegradaDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.ListUtil;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoMultipleBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ListaPDFPagosMultiples;
import ar.com.santanderrio.obp.servicios.pagos.web.view.MontoValorView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Interface PagoMultiple.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
@Component
public class PagoMultipleManagerImpl implements PagoMultipleManager {

	/** The Constant OBTENIENDO_CUENTAS_DEL_CLIENTE. */
	private static final String OBTENIENDO_CUENTAS_DEL_CLIENTE = "Obteniendo las cuentas del cliente";

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoMultipleManagerImpl.class);

	/** The Constant MONTOS_REGARGA. */
	private static final List<MontoValorView> MONTOS_REGARGA = generarListaMontosRecarga();
	
	/** The Constant MONTOS_RECARGA_NEXTEL. */
	private static final List<MontoValorView> MONTOS_RECARGA_NEXTEL = cargarMontosRecargaTelefonoNextel();

	/** The Constant MONTOS_REGARGA. */
	private static final List<MontoValorView> MONTOS_RECARGA_SUBE = generarListaMontosRecargaSube();
	
	private static final List<MontoValorView> MONTOS_RECARGA_MOVI = generarListaMontosRecargaMovi();

	/** The Constant ERROR_OBTENER_CUENTAS. */
	private static final String ERROR_OBTENER_CUENTAS = "Ha ocurrido un error al obtener las cuentas del cliente.";

	/** The Constant ERROR_VALIDACION_PAGOS. */
	private static final String ERROR_VALIDACION_PAGOS = "Error de validacion de pagos.";

	/** The Constant ERROR_EJECUCION_PAGOS. */
	private static final String ERROR_EJECUCION_PAGOS = "Ha ocurrido un error al ejecutar los pagos multiples";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The Constant MSJ_INFO_VALIDANDO_HASH. */
	private static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";

	/** The Constant CODIGO_MSG_LEGALES. */
	private static final String CODIGO_MSG_LEGALES = "1005";

	/** The Constant dateFormatter. */
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyy");

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The nuevo pago BO. */
	@Autowired
	NuevoPagoBO nuevoPagoBO;

	/** The mensaje BO. */
	@Autowired
	MensajeBO mensajeBO;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;

	/** The alta scomp bo. */
	@Autowired
	private ScompBO altaScompBO;

	/** The pago multiple BO. */
	@Autowired
	PagoMultipleBO pagoMultipleBO;

	/** The pagos pendientes BO. Para consultar CNSPESDEUD.120 */
	@Autowired
	PagosPendientesBO pagosPendientesBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The legal BO. */
	@Autowired
	LegalBO legalBO;

	@Autowired
	SeguroTenenciasBO seguroTenenciasBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultiple#
	 * solicitarPagoMultiple(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleView)
	 */
	@Override
	public Respuesta<PagoMultipleListView> solicitarPagoMultiple(PagoMultipleListView pagoMultipleListView) {
		// ----- NEW:
		Respuesta<PagoMultipleListView> respuesta = new Respuesta<PagoMultipleListView>();
		try {
			LOGGER.info(OBTENIENDO_CUENTAS_DEL_CLIENTE);
			MedioPagoSelectionView medios = nuevoPagoBO.obtenerCuentas(sesionCliente.getCliente());
			if (null == medios.getListaCuentas() || medios.getListaCuentas().isEmpty()) {
				throw new BusinessException();
			}
			PagoMultipleListView respuestaView = new PagoMultipleListView();
			respuestaView.generarNuevoIdSesion();
			respuestaView.setCuentas(medios.getListaCuentas());
			respuestaView.setMontosRecarga(MONTOS_REGARGA);
			respuestaView.setMontosRecargaNextel(MONTOS_RECARGA_NEXTEL);
			respuestaView.setMontosRecargaSube(MONTOS_RECARGA_SUBE);
			respuestaView.setMontosRecargaMovi(MONTOS_RECARGA_MOVI);
			
			respuesta = respuestaFactory.crearRespuestaOk(PagoMultipleListView.class);
			respuesta.setRespuesta(respuestaView);
			estadisticaManager.add(EstadisticasConstants.ACCESO_PAGO_MULTIPLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuesta;
		} catch (BusinessException e) {
			LOGGER.debug(ERROR_OBTENER_CUENTAS);
			return errorConsultaCuentas();
		}
		//
		// ----- OLD:
		/*
		 * Respuesta<MedioPagoSelectionView> cuentas =
		 * pagoMultipleBO.obtenerCuentas(sesionCliente.getCliente()); if
		 * ((cuentas == null) ||
		 * (!EstadoRespuesta.OK.equals(cuentas.getEstadoRespuesta()))) {
		 * LOGGER.debug(ERROR_OBTENER_CUENTAS); return errorConsultaCuentas(); }
		 * Respuesta<PagoMultipleListView> respuesta =
		 * respuestaFactory.crearRespuestaOk(PagoMultipleListView.class);
		 * PagoMultipleListView respuestaView = new PagoMultipleListView();
		 * respuestaView.generarNuevoIdSesion();
		 * respuestaView.setCuentas(cuentas.getRespuesta().getListaCuentas());
		 * 
		 * respuestaView.setMontosRecarga(MONTOS_REGARGA);
		 * 
		 * respuesta.setRespuesta(respuestaView);
		 * estadisticaManager.add(EstadisticasConstants.ACCESO_PAGO_MULTIPLE,
		 * EstadisticasConstants.CODIGO_ESTADISTICAS_OK); return respuesta;
		 */
	}

	/**
	 * Generar lista montos recarga.
	 *
	 * @return the list
	 */
	private static List<MontoValorView> generarListaMontosRecarga() {
		List<MontoValorView> lista = new ArrayList<MontoValorView>();
		lista.add(new MontoValorView(BigDecimal.valueOf(20), "$ 20,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(30), "$ 30,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(40), "$ 40,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(60), "$ 60,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(70), "$ 70,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(80), "$ 80,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(90), "$ 90,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(150), "$ 150,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(200), "$ 200,00"));
		
		lista.add(new MontoValorView(BigDecimal.valueOf(250), "$ 250,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(300), "$ 300,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(400), "$ 400,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(500), "$ 500,00"));
		return lista;
	}
	
	
	public static List<MontoValorView> cargarMontosRecargaTelefonoNextel() {
		List<MontoValorView> lista = new ArrayList<MontoValorView>();
		lista.add(new MontoValorView(BigDecimal.valueOf(20), "$ 20,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(30), "$ 30,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(40), "$ 40,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(60), "$ 60,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(70), "$ 70,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(80), "$ 80,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(90), "$ 90,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(150), "$ 150,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(200), "$ 200,00"));
		return lista;
	}
	

	/**
	 * Generar lista montos recarga.
	 *
	 * @return the list
	 */
	private static List<MontoValorView> generarListaMontosRecargaSube() {
		List<MontoValorView> lista = new ArrayList<MontoValorView>();
		lista.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(150), "$ 150,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(200), "$ 200,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(250), "$ 250,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(300), "$ 300,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(400), "$ 400,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(500), "$ 500,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(600), "$ 600,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(700), "$ 700,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(800), "$ 800,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(1200), "$ 1200,00"));
		return lista;
	}
	
	private static List<MontoValorView> generarListaMontosRecargaMovi() {
		List<MontoValorView> lista = new ArrayList<MontoValorView>();
		lista.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(150), "$ 150,00"));
		return lista;
	}

	/**
	 * Obtener error view para consulta cuentas.
	 *
	 * @return the respuesta
	 */
	private Respuesta<PagoMultipleListView> errorConsultaCuentas() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_PAGO_MULTIPLE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		String mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR)
				.getMensaje();
		return respuestaFactory.crearRespuestaError(PagoMultipleListView.class, mensajeError, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager#
	 * grabarEstadisticaComprobantePagoMultiple()
	 */
	@Override
	public Respuesta<Boolean> grabarEstadisticaComprobantePagoMultiple() {
		estadisticaManager.add(EstadisticasConstants.NUEVO_PAGO_VISUALIZACION_COMPROBANTE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Respuesta<Boolean> respuesta = respuestaFactory.crearRespuestaOk(Boolean.class);
		respuesta.setRespuestaVacia(true);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager#
	 * ejecutarPagoMultiple(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleListView)
	 */
	@Override
	public Respuesta<PagoMultipleListView> ejecutarPagoMultiple(PagoMultipleListView pagoMultipleListView) {

		validarHashNuevoPago(pagoMultipleListView);
		Respuesta<PagoMultipleListView> respuestaPagoMultipleListView = respuestaFactory
				.crearRespuestaOk(PagoMultipleListView.class);
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(pagoMultipleListView.getIdSesion(), 2, "pegarElError");
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(pagoMultipleListView.getIdSesion(), 2, "pegarElError");
		}

		Cliente cliente = sesionCliente.getCliente();
		if (validarPagos(pagoMultipleListView)) {
			Respuesta<PagoMultipleDTO> respuestaPagoMultipleListDTO = pagoMultipleBO
					.ejecutarPagoMultiple(pagoMultipleListView, cliente);
			Respuesta<EmisionOfertaIntegradaDTO> emitirGastosProtegidos = null;
			if (respuestaPagoMultipleListDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				if(pagoMultipleListView != null && pagoMultipleListView.getEmisionGastosProtegido() != null) {
					Cuenta cuenta = cliente.getCuenta(pagoMultipleListView.getEmisionGastosProtegido().getCbu());
					if (cuenta == null) {
						LOGGER.info("El Cbu no pertence a la cuenta");
						pagoMultipleListView.setNroPolizaGastosProtegido(null);
					}
					String servicios = null;
					for(PagoMultipleView pagoMultipleView : pagoMultipleListView.getPagos()){
						servicios += pagoMultipleView.getDescripcionServicioPago() + " " ;
					}
					emitirGastosProtegidos = seguroTenenciasBO.emisionOfertaIntegradaGastoProtegido(pagoMultipleListView.getEmisionGastosProtegido(), cliente, cuenta.getNroSucursal(), cuenta.getTipoCuenta(), cuenta.getNroCuentaProducto(), servicios.length() > 150 ? servicios.substring(0, 149) : servicios);
				}
					Respuesta<PagoMultipleListView> res = cargarVistaConDTO(respuestaPagoMultipleListDTO,
						pagoMultipleListView, emitirGastosProtegidos);
				sesionParametros.setListaPagosMultiples(res.getRespuesta().getPagos());
				sesionParametros.setListaPagosMultiplesView(pagoMultipleListView.getPagos());
				sesionParametros.setFechaHoraPago(res.getRespuesta().getFechaHora());

				return res;
			}
			if (respuestaPagoMultipleListDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				Respuesta<PagoMultipleListView> res = sesionParametros.getContador().excedeReintentos(
						pagoMultipleListView.getIdSesion(),
						cargarVistaConDTO(respuestaPagoMultipleListDTO, pagoMultipleListView, null));
				sesionParametros.setListaPagosMultiples(res.getRespuesta().getPagos());
				sesionParametros.setListaPagosMultiplesView(pagoMultipleListView.getPagos());
				sesionParametros.setFechaHoraPago(res.getRespuesta().getFechaHora());
				return res;
			}
			if (respuestaPagoMultipleListDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				LOGGER.debug(ERROR_EJECUCION_PAGOS);
				respuestaPagoMultipleListView.setEstadoRespuesta(respuestaPagoMultipleListDTO.getEstadoRespuesta());
				respuestaPagoMultipleListView.setRespuestaVacia(respuestaPagoMultipleListDTO.isRespuestaVacia());
			}
			return sesionParametros.getContador().excedeReintentos(pagoMultipleListView.getIdSesion(),
					respuestaPagoMultipleListView);
		} else {
			LOGGER.debug(ERROR_VALIDACION_PAGOS);
			Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			return respuestaFactory.crearRespuestaError(PagoMultipleListView.class, mensaje.getMensaje(),
					TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/**
	 * Cargar vista con DTO.
	 *
	 * @param respuestaPagoMultipleListDTO
	 *            the respuesta pago multiple list DTO
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	private Respuesta<PagoMultipleListView> cargarVistaConDTO(Respuesta<PagoMultipleDTO> respuestaPagoMultipleListDTO,
			PagoMultipleListView pagoMultipleListView, Respuesta<EmisionOfertaIntegradaDTO> emitirGastosProtegidos) {

		List<PagoMultipleView> pagosListView = new ArrayList<PagoMultipleView>();
		List<PagoInEntity> pagosListDTO = respuestaPagoMultipleListDTO.getRespuesta().getPagos();
		int iterator = 0;
		for (PagoInEntity pagoDTO : ListUtil.safe(pagosListDTO)) {
			PagoMultipleView pagoView = new PagoMultipleView();
			if (esTipoAfip(pagoDTO)) {
				pagoView.setFechaVencimiento("-/-/-");
			} else {
				pagoView.setFechaVencimiento(pagoDTO.getFechaVencimiento());
			}
			pagoView.setNombreEmpresa(pagoDTO.getNombreEmpresa());
			pagoView.setCodigoEmpresa(pagoDTO.getCodigoEmpresa());
			pagoView.setReintentar(pagoDTO.getReintentar().toString());
			pagoView.setMedioDePago(pagoDTO.getSucursalCuenta() + "-" + pagoDTO.getNumeroCuenta());
			pagoView.setDescripcionTipoCuenta(pagoDTO.getDescripcionTipoCuenta());
			pagoView.setTipoCuenta(pagoDTO.getTipoCuenta());
			String identificacionCliente = pagoDTO.getIdentificacion();
			// Si es VEP, puede venir un identificador de cliente que es una
			// concatenación del CUIT
			// y de otra cosa. Por eso se recorta para que muestre solo el CUIT
			if (esVEP(pagoDTO)) {
				if (StringUtils.isNotBlank(identificacionCliente) && identificacionCliente.length() >= 11) {
					pagoView.setIdentificacionCliente(identificacionCliente.substring(0, 11));
				} else {
					pagoView.setIdentificacionCliente(identificacionCliente);
				}
				parsearFacturaNumero(pagoView);
			} else {
				pagoView.setIdentificacionCliente(identificacionCliente);
			}
			pagoView.setMensaje(pagoDTO.getMensaje());
			pagoView.setNumeroFactura(pagoDTO.getNumeroFactura());
			parsearFacturaNumero(pagoView);
			pagoView.setFechaPago(
					pagoDTO.getFechaDePago() == null ? null : dateFormatter.format(pagoDTO.getFechaDePago()));
			pagoView.setNumeroDeControl(pagoDTO.getNumeroControl());
			pagoView.setMonto(pagoDTO.getMonto());
			pagoView.setComprobantePorServicio(pagoDTO.getComprobantePorServicio());
			Integer estadoPago = pagoDTO.getEstadoPago();
			pagoView.setEsPagoOk((estadoPago == 0 ? Boolean.TRUE : Boolean.FALSE).booleanValue());
			if (pagoDTO.getTipoError() != null)
				pagoView.setTipoError(pagoDTO.getTipoError().getDescripcion());
			pagosListView.add(pagoView);
			if (pagoView.getEsPagoOk()) {
				MedioPago medioPago = mediosPagoBO.getByCodigo(pagoDTO.getCodigoEmpresa()).getRespuesta();

				TipoMedioPagoBO tipoMedioDePagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);

				if (tipoMedioDePagoBO != null) {
					altaScompBO.altaScompTransferencia(tipoMedioDePagoBO.generarAltaScompRequest(
							sesionCliente.getCliente(), (PagoPMC) pagosListDTO.get(iterator), pagoDTO, null,
							pagoMultipleListView.getPagos().get(iterator)));
				} else {
					LOGGER.info("No fue posible realizar alta scomp transferencia");
				}

			}
			iterator++;
		}
		pagoMultipleListView.setPagos(pagosListView);

		PagoMultipleDTO pagoMultipleDTO = respuestaPagoMultipleListDTO.getRespuesta();
		pagoMultipleListView.setReintentarErrorUnico(pagoMultipleDTO.isReintentarErrorUnico());
		pagoMultipleListView.setTodosPagosOk(pagoMultipleDTO.isTodosOK());
		pagoMultipleListView.setErrorUnico(pagoMultipleDTO.isErrorUnico());
		if (pagoMultipleListView.getErrorUnico()) {
			pagoMultipleListView
					.setMensajeErrorUnico(respuestaPagoMultipleListDTO.getRespuesta().getMensajeErrorUnico());
		}
		pagoMultipleListView.setLegales(obtenerLegal(CODIGO_MSG_LEGALES));
		pagoMultipleListView.setFechaHora(FechaUtils.obtenerFechaYHoraActual());
		if(emitirGastosProtegidos != null && emitirGastosProtegidos.getRespuesta() != null && emitirGastosProtegidos.getRespuesta().getNumeroPoliza() != null) {
			pagoMultipleListView.setNroPolizaGastosProtegido(emitirGastosProtegidos.getRespuesta().getNumeroPoliza().toString());
		}
		Respuesta<PagoMultipleListView> respuesta = new Respuesta<PagoMultipleListView>();
		respuesta.setRespuesta(pagoMultipleListView);
		respuesta.setEstadoRespuesta(respuestaPagoMultipleListDTO.getEstadoRespuesta());
		respuesta.setRespuestaVacia(respuestaPagoMultipleListDTO.isRespuestaVacia());
		respuesta.setItemMensajeRespuesta(respuestaPagoMultipleListDTO.getItemsMensajeRespuesta());
		return respuesta;
	}

	/**
	 * Es VEP.
	 *
	 * @param pagoDTO
	 *            the pago DTO
	 * @return true, if successful
	 */
	private boolean esVEP(PagoInEntity pagoDTO) {
		if ("SEA".equals(pagoDTO.getCodigoEmpresa())) {
			return true;
		}
		return false;
	}

	/**
	 * Es tipo afip.
	 *
	 * @param pagoDTO
	 *            the pago DTO
	 * @return true, if successful
	 */
	private boolean esTipoAfip(PagoInEntity pagoDTO) {
		MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(pagoDTO.getCodigoEmpresa());
		if (medioPago != null) {
			TipoMedioPagoBO tipoMedioDePagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);
			TipoNuevoPagoEnum enumNuevoPago = tipoMedioDePagoBO.getTipoNuevoPagoEnum();
			return esTipoPagoAfip(enumNuevoPago);
		}
		return false;
	}

	/**
	 * Es tipo pago afip.
	 *
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return true, if successful
	 */
	private boolean esTipoPagoAfip(TipoNuevoPagoEnum enumNuevoPago) {
		boolean esAfip1 = "1".equals(enumNuevoPago.getId()) || "2".equals(enumNuevoPago.getId())
				|| "3".equals(enumNuevoPago.getId());
		return esAfip1 || "4".equals(enumNuevoPago.getId()) || "5".equals(enumNuevoPago.getId());
	}

	/**
	 * Parsear factura numero.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 */
	private void parsearFacturaNumero(PagoMultipleView pagoMultipleView) {
		if (pagoMultipleView.getNumeroFactura() != null && pagoMultipleView.getNumeroFactura().length() >= 19) {
			pagoMultipleView.setNumeroVep(pagoMultipleView.getNumeroFactura().substring(0, 12));
			pagoMultipleView.setPeriodo(pagoMultipleView.getNumeroFactura().substring(12, 14) + "/"
					+ pagoMultipleView.getNumeroFactura().substring(14, 16));
			pagoMultipleView.setAnticipo(pagoMultipleView.getNumeroFactura().substring(16, 19));
		}
	}

	/**
	 * Este metodo valida los pagos multiples. Dado que la validacion la hace el
	 * front, aqui se hace una validacion de seguridad. En caso de no superar
	 * esta validacion se arroja un error generico ya que desde la configuracion
	 * el usuario no carga datos, solo los confirma.
	 * 
	 * No se usa la validacion de {@link PagoMisCuentasView} ya que no se
	 * reciben todos los datos desde el front. Solo los mas importantes.
	 *
	 *
	 * @author manuel.vargas B041299
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return Boolean
	 */
	private Boolean validarPagos(PagoMultipleListView pagoMultipleListView) {
		// FIXME errores en validaciones
		return true;
	}

	/**
	 * Leer el mensaje de la respuesta recibida.
	 * 
	 * @param codigoLegal
	 *            the codigo legal
	 * @return the string
	 */
	private String obtenerLegal(String codigoLegal) {
		Respuesta<String> respuestaLegal = legalBO.buscarLegal(codigoLegal);
		if (EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
			return respuestaLegal.getRespuesta();
		} else {
			LOGGER.debug("Ocurrio un error al obtener el mensaje de la BD de legales.");
			return STRING_VACIO;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager#
	 * descargarComprobantePDF(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(PagoMultipleView pagoMultipleView) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(
				sesionParametros.getListaPagosMultiples().get(Integer.valueOf(pagoMultipleView.getIdComprobante())),
				sesionParametros.getListaPagosMultiplesView().get(Integer.valueOf(pagoMultipleView.getIdComprobante())),
				sesionParametros.getFechaHoraPago(), pagoMultipleView.getCuitVep(),
				pagoMultipleView.getElementoAdicional());
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

	@Override
	public Respuesta<ReporteView> imprimirComprobantes() {
		Respuesta<ReporteView> respBO = pagoMultipleBO.imprimirComprobantes();
		grabarEstadisticasImpresionMultiple(respBO.getEstadoRespuesta());
		return respBO;
	}

	private void grabarEstadisticasImpresionMultiple(EstadoRespuesta estadoRespuesta) {
		String estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;

		if (EstadoRespuesta.OK.equals(estadoRespuesta)) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		} else if (EstadoRespuesta.WARNING.equals(estadoRespuesta)) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL;
		}

		estadisticaManager.add(EstadisticasConstants.IMPRESION_VARIOS_PAGO_MULTIPLE, estadistica);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
	 * estadisticaComprobanteNuevoPago()
	 */
	@Override
	public Respuesta<Void> continuarPago(PagoMultipleListView pagoMultipleListView) {
		String hashView = crearMapaDeLaVista(pagoMultipleListView);
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sesionParametros.setValidacionHash(hashView);
		return new Respuesta<Void>();
	}

	/**
	 * Crear mapa de la vista.
	 *
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the string
	 */
	private String crearMapaDeLaVista(PagoMultipleListView pagoMultipleListView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		String hashLista = "";
		for (PagoMultipleView pago : pagoMultipleListView.getPagos()) {
			Map<String, Object> mapaAtributos = new HashMap<String, Object>();
			mapaAtributos.put("numeroIdentificacion", pago.getIdentificacionCliente());
			mapaAtributos.put("numeroDeCuenta", pago.getNumeroCuenta());
			mapaAtributos.put("importe", pago.getMonto().replace("$", "").trim());
			mapaAtributos.put("codigoPagoElectronico", pago.getCuitCliente());
			mapaAtributos.put("codigoPagoElectronico2", pago.getCuitEmpleador());
			mapaAtributos.put("numeroVep", pago.getNumeroVep());
			mapaAtributos.put("numeroFactura", pago.getNumeroFactura());
			mapaAtributos.put("saldoCuentaOrigen", pago.getSaldoCuentaOrigen());
			hashLista = hashLista + HashUtils.obtenerHash(mapaAtributos);
		}

		LOGGER.info("String mapa vista: %s", hashLista);
		return hashLista;
	}

	/**
	 * Validar hash nuevo pago.
	 *
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 */
	private void validarHashNuevoPago(PagoMultipleListView pagoMultipleListView) {
		String inputHash = crearMapaDeLaVista(pagoMultipleListView);
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sesionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
	}

	@Override
	public Respuesta<ListaPDFPagosMultiples> descargaMultipleComprobantesPDF() {
		Respuesta<ListaPDFPagosMultiples> respBO = pagoMultipleBO.descargaMultipleComprobantesPDF();
		grabarEstadisticasDescargaMultiple(respBO.getEstadoRespuesta());
		return respBO;
		// ListaPDFPagosMultiples listaPDFPagosMultiples = new
		// ListaPDFPagosMultiples();
		// List<ReporteView> listaPDF = new ArrayList<ReporteView>();
		// Boolean descargaOk = Boolean.TRUE;
		// for (PagoMultipleView pago :
		// sesionParametros.getListaPagosMultiples()) {
		// Respuesta<Reporte> reporte;
		// if (pago.getEsPagoOk()) {
		// reporte = reporteBO.obtenerComprobantePDF(pago, pago,
		// sesionParametros.getFechaHoraPago(),
		// pago.getCuitVep(), pago.getElementoAdicional());
		// if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
		// ReporteView reporteView =
		// ReporteView.fromReporteComprobantes(reporte.getRespuesta());
		// listaPDF.add(reporteView);
		// } else {
		// descargaOk = Boolean.FALSE;
		// estadisticaManager.add(EstadisticasConstants.DESCARGA_VARIOS_PDF_PAGO_MULTIPLE,
		// EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		// return
		// respuestaFactory.crearRespuestaErrorPersonalizado(ListaPDFPagosMultiples.class,
		// "Ocurrio un error y no se pudo completar la descarga. Por favor,
		// volvé a intentar",
		// TipoError.ERROR_DESCARGA_MULTIPLE.getDescripcion());
		// }
		// } else {
		// continue;
		// }
		//
		// }
		// listaPDFPagosMultiples.setPdfPagos(listaPDF);
		// estadisticaManager.add(EstadisticasConstants.DESCARGA_VARIOS_PDF_PAGO_MULTIPLE,
		// EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		// return respuestaFactory.crearRespuestaOk(listaPDFPagosMultiples);
	}

	private void grabarEstadisticasDescargaMultiple(EstadoRespuesta estadoRespuesta) {
		String estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;

		if (EstadoRespuesta.OK.equals(estadoRespuesta)) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		} else if (EstadoRespuesta.WARNING.equals(estadoRespuesta)) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL;
		}

		estadisticaManager.add(EstadisticasConstants.DESCARGA_VARIOS_PDF_PAGO_MULTIPLE, estadistica);
	}

}
