/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Pago;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoMultipleBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ListaPDFPagosMultiples;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class PagoMultipleBOImpl.
 */
@Component
public class PagoMultipleBOImpl implements PagoMultipleBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoMultipleBOImpl.class);

	/** The Constant ERROR al obtener medio de pago. */
	private static final String ERROR_OBTENER_MEDIO_PAGO = "Error al intentar obtener un medio de pago con codigo: %s";

	/** The Constant INICIALIZANDO. */
	private static final String INICIANDO = "Iniciando pago multiple";

	/** The Constant ERROR_EJECUCION. */
	private static final String ERROR_EJECUCION = "Ha ocurrido un error en la ejecucion de pago multiple";

	/** The Constant RSA_DENY. */
	private static final String RSA_DENY = "RSA ha informado DENY";

	/** The Constant ERROR_RSA. */
	private static final String ERROR_RSA = "Ha ocurrido un error al informar los pagos a RSA";

	/** The Constant ERROR al convertir un importe. */
	private static final String ERROR_CONVERSION_IMPORTE = "Error al convertir un importe";

	/** The Constant DATE_FORMATTER. */
	private final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The pago multiple DAO. */
	@Autowired
	private PagoMisCuentasDAO pagoMisCuentasDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The banelco DAO. */
	@Autowired
	private BanelcoDAO banelcoDAO;

	/** The detalle cuenta DAO. */
	@Autowired
	private DetalleCuentaDAO detalleCuentaDAO;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The estadistica BO. */
	@Autowired
	private EstadisticaBO estadisticaBO;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The buscador medios pago DAO. */
	@Autowired
	private BuscadorEmpresaDAO buscadorMediosPagoDAO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;
	
	@Autowired
	private SesionCliente sesionCliente;
	
	@Autowired
	private ClienteBO clienteBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.PagoMultipleBO#
	 * ejecutarPagoMultiple(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleListView)
	 */
	@Override
	public Respuesta<PagoMultipleDTO> ejecutarPagoMultiple(PagoMultipleListView pagoMultipleListView, Cliente cliente) {
		LOGGER.info(INICIANDO);
		Respuesta<PagoMultipleDTO> respuesta = new Respuesta<PagoMultipleDTO>();
		PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
		boolean tieneDeny = false;
		List<PagoInEntity> listaDePagos = armarListaPagos(pagoMultipleListView.getPagos());
		try {
			tieneDeny = informarRSA(listaDePagos, cliente);
			if (tieneDeny) {
				LOGGER.debug(RSA_DENY);
				throw new DAOException();
			}
			pagoMultipleDTO = pagoMisCuentasDAO.ejecutarPagoMultiple(listaDePagos, cliente);
			procesarRespuestaEjecucion(pagoMultipleDTO, cliente);
			respuesta = generarRespuestaEjecucion(pagoMultipleDTO);
		} catch (DAOException e) {
			LOGGER.error(ERROR_EJECUCION, e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.PAGO_MULTIPLE_EJECUCION_ERROR,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			BigDecimal totalPagos = sumarPagos(listaDePagos, false);
			grabarEstadisticaEjecucionPagoMultiple(totalPagos, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
					cliente);
		}
		return respuesta;
	}

	/**
	 * Informa a RSA la lista de pagos. Se debe ignorar la respuesta de RSA.
	 *
	 * @param pagos
	 *            the pagos
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	private boolean informarRSA(List<PagoInEntity> pagos, Cliente cliente) {
		Date fechaHoy = new Date();
		boolean tieneDeny = false;
		Iterator<PagoInEntity> pagosDTO = pagos.iterator();
		try {
			while (pagosDTO.hasNext() && tieneDeny == false) {
				PagoInEntity pagoDTO = pagosDTO.next();
				Pago pago = new Pago();
				pago.setDeliveryDate(fechaHoy);
				pago.setDueDate(pagoDTO.getFechaDeVencimiento() == null || pagoDTO.getFechaDeVencimiento() == "" ? null
						: DATE_FORMATTER.parse(pagoDTO.getFechaDeVencimiento()));
				pago.setAmount(pagoDTO.getMonto().trim());
				pago.setDivisa(DivisaEnum.PESO);
				pago.setSaldoCuentaOrigen(pagoDTO.getSaldoCuentaOrigen());
				pago.setAccountNickNameDestino(pagoDTO.getEmpresaNombreFantasia());
				pago.setAccountNickNameOrigen(cliente.getApellido1() + " " + cliente.getNombre());
				pago.setTieneCelularMyA(Boolean.TRUE);
				pago.setNombreFantasia(pagoDTO.getEmpresaNombreFantasia());
				pago.setAccountNumber(pagoDTO.getNumeroCuenta());
				pago.setIdentificacionCliente(pagoDTO.getIdentificacion());
				pago.setNumeroCuentaSinFormato(pagoDTO.getNumeroCuentaSinFormato());
				cargarUltimosDiasDesdeCambioDeClaveToken(pago);
				Respuesta<ActionCode> resp = rsaManager.analizar(pago, null);
				if (resp.getRespuesta().equals(ActionCode.DENY)) {
					tieneDeny = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(ERROR_RSA, e);
			e.printStackTrace();
		}
		return tieneDeny;
	}
	
	
	public void cargarUltimosDiasDesdeCambioDeClaveToken(Pago pago) {
		
		try {
			Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				Estadistica estadistica = new Estadistica();
				estadistica.setCodigoTransaccion(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD);
				estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				estadisticaBO.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						pago.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
						LOGGER.info("Valor de ultimos dias cambio clave: {}", antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						pago.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
						LOGGER.info("Valor de ultimos dias cambio token: {}", antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				Estadistica estadistica = new Estadistica();
				estadistica.setCodigoTransaccion(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD);
				estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				estadisticaBO.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}
		
	}

	/**
	 * Generar respuesta ejecucion.
	 *
	 * @param pagoMultipleDTO
	 *            the pago multiple DTO
	 * @return the respuesta
	 */
	private Respuesta<PagoMultipleDTO> generarRespuestaEjecucion(PagoMultipleDTO pagoMultipleDTO) {
		Respuesta<PagoMultipleDTO> respuesta = respuestaFactory.crearRespuestaOk(pagoMultipleDTO);
		respuesta.setRespuesta(pagoMultipleDTO);
		respuesta.setEstadoRespuesta(pagoMultipleDTO.isTodosOK() ? EstadoRespuesta.OK : EstadoRespuesta.WARNING);
		return respuesta;
	}

	/**
	 * Procesar respuesta ejecucion.
	 *
	 * @param pagoMultipleDTO
	 *            the pago multiple DTO
	 * @param cliente
	 *            the cliente
	 */
	private void procesarRespuestaEjecucion(PagoMultipleDTO pagoMultipleDTO, Cliente cliente) {
		if (pagoMultipleDTO.isTodosOK()) {
			pagoMultipleDTO.setMensajeCabecera(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOS_OK_CABECERA_PAGO_MULTIPLE).getMensaje());
			grabarEstadisticaOK(pagoMultipleDTO.getPagos(), cliente);
			return;
		}
		List<PagoInEntity> pagos = pagoMultipleDTO.getPagos();

		if (CollectionUtils.isEmpty(pagos)) {
			throw new RobotException("El servicio de backend no retorno los pagos");
		}

		// valido si los errores que responde el servicio son contemplados
		boolean sonContemplados = true;

		PagoInEntity pagoDTOErrorUnico = pagos.get(0);
		boolean esUnico = true;
		boolean errorParcial = false;
		if (!pagoMultipleDTO.isErrorUnico()) {
			for (PagoInEntity pagoDTO : pagos) {

				if (pagoDTO.getTipoError() == null) {
					errorParcial = true;
					esUnico = false;
				} else {
					esUnico = esUnico && pagoDTOErrorUnico.getTipoError().equals(pagoDTO.getTipoError());
					pagoDTO.setReintentar(permiteReintento(pagoDTO.getTipoError()));
					if (pagoDTOErrorUnico.isEsErrorContemplado() == false) {
						sonContemplados = false;
					}
				}
			}
		}
		if (esUnico) {
			pagoMultipleDTO.setErrorUnico(esUnico);
			pagoMultipleDTO.setTipoErrorUnico(pagoDTOErrorUnico.getTipoError());
			pagoMultipleDTO.setReintentarErrorUnico(pagoDTOErrorUnico.getReintentar());
			pagoMultipleDTO.setMensajeErrorUnico(pagoDTOErrorUnico.getMensaje());
		}
		if (errorParcial) {
			if (sonContemplados) {
				pagoMultipleDTO.setMensajeCabecera(
						mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PARCIAL_CABECERA_PAGO_MULTIPLE)
								.getMensaje());
				grabarEstadisticaErrorParcial(pagoMultipleDTO.getPagos(), cliente);
			} else {
				pagoMultipleDTO.setMensajeCabecera(
						mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_PES_SUPERADO)
								.getMensaje());
				grabarEstadisticaErrorParcial(pagoMultipleDTO.getPagos(), cliente);
			}

		} else if (sonContemplados) {
			// En caso de que el error sea contemplado y el mensaje a mostrar
			// sea por un codigo especifico.
			especificarCodigoMensajeMensajeUnico(pagoMultipleDTO, cliente);
		} else {
			pagoMultipleDTO.setMensajeCabecera(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOS_ERRORES_CABECERA_PAGO_MULTIPLE)
							.getMensaje());
			pagoMultipleDTO.setMensajeErrorUnico(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOS_ERRORES_CABECERA_PAGO_MULTIPLE)
							.getMensaje());
			grabarEstadisticaErrorTotal(pagoMultipleDTO.getPagos(), cliente);
		}
	}

	/**
	 * Permite especificar un codigo de mensaje especifico cuando el error es
	 * unico.
	 *
	 * @param pagoMultipleDTO
	 *            the tipo pagoMultipleDTO
	 * @param cliente
	 *            the tipo Cliente
	 */
	public void especificarCodigoMensajeMensajeUnico(PagoMultipleDTO pagoMultipleDTO, Cliente cliente) {
		switch (pagoMultipleDTO.getTipoErrorUnico()) {
		case ERROR_NUMERO_IDENTIFICADOR:
			pagoMultipleDTO.setMensajeCabecera(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOS_ERRORES_CODIGO_PAGO_INCORRECTO)
							.getMensaje());
			pagoMultipleDTO.setMensajeErrorUnico(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOS_ERRORES_CODIGO_PAGO_INCORRECTO)
							.getMensaje());
			break;
		default:
			pagoMultipleDTO.setMensajeCabecera(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_PES_SUPERADO).getMensaje());
			break;
		}
		grabarEstadisticaErrorTotal(pagoMultipleDTO.getPagos(), cliente);
	}

	/**
	 * Permite reintento.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @return true, if successful
	 */
	private boolean permiteReintento(TipoError tipoError) {
		boolean reintento = true;
		switch (tipoError) {
		case ERROR_PAGO_EFECTUADO:
		case ERROR_CUENTA_INVALIDA:
		case ERROR_LIMITE_MAYOR_NUEVO_PAGO:
		case DESTINATARIO_INVALIDO:
		case EMPRESA_INHABILITADA:
		case IMPORTE_ERRONEO:
			reintento = false;
			break;
		default:
			reintento = true;
		}
		return reintento;
	}

	/**
	 * Grabar estadistica error total.
	 *
	 * @param pagos
	 *            the pagos
	 * @param cliente
	 *            the cliente
	 */
	private void grabarEstadisticaErrorTotal(List<PagoInEntity> pagos, Cliente cliente) {
		BigDecimal totalPagos = sumarPagos(pagos, false);
		grabarEstadisticaEjecucionPagoMultiple(totalPagos, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, cliente);
	}

	/**
	 * Grabar estadistica ejecucion pago multiple.
	 *
	 * @param totalPagos
	 *            the total pagos
	 * @param codigoEstadisticasError
	 *            the codigo estadisticas error
	 * @param cliente
	 *            the cliente
	 */
	private void grabarEstadisticaEjecucionPagoMultiple(BigDecimal totalPagos, String codigoEstadisticasError,
			Cliente cliente) {
		try {
			Estadistica estadistica = new Estadistica();
			estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
			estadistica.setCodigoError(codigoEstadisticasError);
			estadistica.setImporte(totalPagos.toString());
			estadistica.setMoneda(DivisaEnum.PESO.getCodigo());

			estadisticaBO.add(estadistica, sesionParametros.getRegistroSession(), cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al grabar estadistica", e);
		}
	}

	/**
	 * Grabar estadistica error parcial.
	 *
	 * @param pagos
	 *            the pagos
	 * @param cliente
	 *            the cliente
	 */
	private void grabarEstadisticaErrorParcial(List<PagoInEntity> pagos, Cliente cliente) {
		BigDecimal totalPagos = sumarPagos(pagos, true);
		grabarEstadisticaEjecucionPagoMultiple(totalPagos, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL,
				cliente);
	}

	/**
	 * Grabar estadistica OK.
	 *
	 * @param pagos
	 *            the pagos
	 * @param cliente
	 *            the cliente
	 */
	private void grabarEstadisticaOK(List<PagoInEntity> pagos, Cliente cliente) {
		BigDecimal totalPagos = sumarPagos(pagos, false);
		grabarEstadisticaEjecucionPagoMultiple(totalPagos, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, cliente);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.bo.PagoMultipleBO#obtenerCuentas(
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<MedioPagoSelectionView> obtenerCuentas(Cliente cliente) {
		Respuesta<MedioPagoSelectionView> respuesta = new Respuesta<MedioPagoSelectionView>();

		MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
		try {
			List<CuentaPagoMisCuentas> listaCuentas = banelcoDAO.obtenerCuentasBanelcoHabilitadas(cliente);

			List<Cuenta> cuentas = cliente.getCuentas();
			List<Cuenta> cuentasFinal = new ArrayList<Cuenta>();
			Iterator<Cuenta> itCuentas = cuentas.iterator();
			while (itCuentas.hasNext()) {
				Cuenta cuenta = itCuentas.next();
				Iterator<CuentaPagoMisCuentas> itCuentasPago = listaCuentas.iterator();
				while (itCuentasPago.hasNext()) {
					CuentaPagoMisCuentas cuentaPagoMisCuentas = itCuentasPago.next();
					if (cuenta != null & cuentaPagoMisCuentas != null) {
						String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
						if (nroCuenta.equals(
								ISBANStringUtils.formatearNumeroCuenta(cuentaPagoMisCuentas.getNroCuentaProducto()))) {
							cuentasFinal.add(cuenta);
						}
					}
				}
			}
			detalleCuentaDAO.actualizarSaldo(cuentasFinal, cliente, cuentasFinal.size());
			medioPagoSelectionView.setListaCuentas(obtenerCuentasView(cuentasFinal));
			medioPagoSelectionView.setCantidadCuentas(cuentasFinal.size());
			respuesta.setRespuesta(medioPagoSelectionView);
			respuesta.setRespuestaVacia(false);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		} catch (DAOException e) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}

		return respuesta;

	}

	/**
	 * transforma las Cuentas a CuentasView.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	private List<CuentaSeleccionView> obtenerCuentasView(List<Cuenta> cuentas) {
		List<CuentaSeleccionView> cuentasView = new LinkedList<CuentaSeleccionView>();
		for (Cuenta c : cuentas) {
			CuentaSeleccionView cuentaSeleccionView = new CuentaSeleccionView(c);
			cuentasView.add(cuentaSeleccionView);
		}
		return cuentasView;
	}

	/**
	 * Armar lista pagos. Tipo de cuenta se setea por defecto en "09", ARS.
	 * <p>
	 * <li>COD-EMPRESA X(4) Código de empresa.</li>
	 * <li>IDENTIFICACION X(19) Identificación</li>
	 * <li>TIPO-DE-MONTO X(1) Tipo de Monto (en este caso de podrán indicar solo
	 * dos valores 3 o 0).
	 * <li>TIPO-DE_SELECCION X(1) Tipo de Selección</li>
	 * <li>NRO-DE-FACTURA X(20) Número de factura</li>
	 * <li>TIPO-CUENTA X(2) Tipo de cuenta</li>
	 * <li>SUCURSAL X(4) Sucursal de la cuenta</li>
	 * <li>CUENTA X(12) Número de cuenta</li>
	 * <li>MONEDA X(3) Moneda</li>
	 * <li>MONTO 9(12,2) Monto. Completar con 0 a la izquierda e indicar el
	 * Importe a abonar indicado por el usuario.</li>
	 * <p>
	 * 
	 * @param pagos
	 *            the pagos
	 * @return the list
	 */
	private List<PagoInEntity> armarListaPagos(List<PagoMultipleView> pagos) {
		List<PagoInEntity> lista = new ArrayList<PagoInEntity>();
		PagoInEntity pagoDTO = null;
		BigDecimal monto;

		for (PagoMultipleView pagoMultipleView : pagos) {
			MedioPago empresa = buscadorMediosPagoDAO.getByCodigo(pagoMultipleView.getCodigoEmpresa());
			pagoDTO = new PagoPMC();
			pagoDTO.setNombreEmpresa(empresa.getNombreFantasia());//
			pagoDTO.setCodigoEmpresa(pagoMultipleView.getCodigoEmpresa());//
			pagoDTO.setIdentificacion(pagoMultipleView.getIdentificacionCliente());
			armarIdentificacionYFacturacion(pagoDTO, pagoMultipleView);
			try {

				String montoPago = pagoMultipleView.getMonto().replace("$", "");

				monto = ISBANStringUtils.convertirImporte(montoPago);
				BigDecimal montoInicial = ISBANStringUtils.convertirImporte(pagoMultipleView.getMontoInicial());
				if (monto.compareTo(montoInicial) == 0) {
					pagoDTO.setTipoMonto("3");
				} else {
					pagoDTO.setTipoMonto("0");
				}
			} catch (ImporteConvertException e) {
				LOGGER.error(ERROR_CONVERSION_IMPORTE, e);
			}

			try {
				monto = ISBANStringUtils.convertirImporte(pagoMultipleView.getMontoSinFormatear());
			} catch (ImporteConvertException e) {
				LOGGER.error(ERROR_CONVERSION_IMPORTE, e);
			}

			if ("3".equals(pagoMultipleView.getTipoPago()) || parsearImporte(pagoMultipleView.getMontoInicial()).signum() != 0) {
				pagoDTO.setTipoSeleccion("F");
			} else {
				pagoDTO.setTipoSeleccion("I");
			}

			TipoCuenta tipoCuenta = TipoCuenta.fromDescripcionConMoneda(pagoMultipleView.getDescripcionTipoCuenta());
			if (tipoCuenta.equals(TipoCuenta.CUENTA_UNICA)) {
				if ("$".equals(pagoMultipleView.getSimboloMoneda())) {
					tipoCuenta = TipoCuenta.CUENTA_UNICA_PESOS;
				} else {
					tipoCuenta = TipoCuenta.CUENTA_UNICA_DOLARES;
				}
			}

			pagoDTO.setTipoCuenta(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), 2, "0"));
			pagoDTO.setNumeroCuentaSinFormato(pagoMultipleView.getNumeroCuenta());
			String[] cuentaSucurslaNumero = pagoMultipleView.getNumeroCuenta().split("-");
			pagoDTO.setSucursalCuenta(cuentaSucurslaNumero[0]);
			pagoDTO.setDescripcionTipoCuenta(pagoMultipleView.getDescripcionTipoCuenta());
			pagoDTO.setNumeroCuenta(cuentaSucurslaNumero[1].replace("/", ""));
			pagoDTO.setMoneda(pagoMultipleView.getMoneda());
			//pagoDTO.setMonto(ISBANStringUtils.agregadorDecimales(pagoMultipleView.getMontoSinFormatear().replace(".", ",")));
		
			String montoFormateado = "";
			if(pagoMultipleView.getMontoSinFormatear().contains(",")){
				montoFormateado = ISBANStringUtils.agregadorDecimales(pagoMultipleView.getMontoSinFormatear());
			} else {
				montoFormateado = ISBANStringUtils.agregadorDecimalesConPunto(pagoMultipleView.getMontoSinFormatear());
			}
			pagoDTO.setMonto(montoFormateado);
			pagoDTO.setFechaDeVencimiento(pagoMultipleView.getFechaVencimiento());
			// datos para mensajes parametrizados
			pagoDTO.setIdentificacionMensajeFeedback(pagoMultipleView.getIdentificacionCliente());
			pagoDTO.setEmpresaNombreFantasia(empresa.getNombreFantasia());
			pagoDTO.setMontoMensajeFeedback(pagoMultipleView.getMonto());
			pagoDTO.setSaldoCuentaOrigen(pagoMultipleView.getSaldoCuentaOrigen());
			lista.add(pagoDTO);
		}
		return lista;
	}

	/**
	 * Arma identificacion segun tipo de deuda. Segun tipo de Importe y segun
	 * tipo de pago segun el view. Con la informacion de medios segun codigo de
	 * empresa.
	 *
	 * @param pagoDTO
	 *            the pago DTO
	 * @param pagoMultipleView
	 *            the pago multiple view
	 */
	private void armarIdentificacionYFacturacion(PagoInEntity pagoDTO, PagoMultipleView pagoMultipleView) {
		Respuesta<MedioPago> mediosPagoRta = new Respuesta<MedioPago>();
		String tipoDeImporte = STRING_VACIO;
		try {
			mediosPagoRta = this.mediosPagoBO.getByCodigo(pagoMultipleView.getCodigoEmpresa());
			if (mediosPagoRta.isRespuestaVacia()) {
				throw new BusinessException(); // TODO: definir respuesta error
			}
			tipoDeImporte = mediosPagoRta.getRespuesta().getTipoImporte();
			// consulta por ej: DOMESTICO
			if ("2".equals(pagoMultipleView.getTipoPago()) && tipoDeImporte.contentEquals("1")) {
				pagoDTO.setIdentificacion(
						pagoMultipleView.getIdentificacionCliente() + pagoMultipleView.getPeriodoPago());
				pagoDTO.setNumeroFactura(pagoMultipleView.getCuitEmpleador());
				pagoDTO.setTipoMonto("0");
			} else if ("3".equals(pagoMultipleView.getTipoPago()) && tipoDeImporte.contentEquals("0")) {
				// caso de VEP
				String cuit = StringUtils.isNotEmpty(pagoMultipleView.getCuitCliente())
						? pagoMultipleView.getCuitCliente() : "";
				pagoDTO.setIdentificacion(pagoMultipleView.getIdentificacionCliente() + cuit);
				pagoDTO.setTipoMonto("3");
				pagoDTO.setNumeroFactura(pagoMultipleView.getNumeroFactura());
			} else if ("3".equals(pagoMultipleView.getTipoPago()) && tipoDeImporte.contentEquals("2")) {
				// ej: SPAR, con deuda.
				pagoDTO.setTipoMonto("3");
				pagoDTO.setIdentificacion(pagoMultipleView.getIdentificacionCliente());
				// TODO:codigo pago elec.
				pagoDTO.setNumeroFactura(pagoMultipleView.getNumeroFactura());
			} else if ("3".equals(pagoMultipleView.getTipoPago()) && tipoDeImporte.contentEquals("1")) {
				// ej LOSL
				pagoDTO.setTipoMonto("3");
				pagoDTO.setIdentificacion(pagoMultipleView.getIdentificacionCliente());
				pagoDTO.setNumeroFactura(pagoMultipleView.getNumeroFactura());
			} else if ("1".equals(pagoMultipleView.getTipoPago()) && tipoDeImporte.contentEquals("2")) {
				// ej UNICEF
				pagoDTO.setTipoMonto("0");
				pagoDTO.setIdentificacion(pagoMultipleView.getIdentificacionCliente());
			}
		} catch (BusinessException e) { // TODO: definir respuesta error:
			String mensajeError = String.format(ERROR_OBTENER_MEDIO_PAGO, pagoMultipleView.getCodigoEmpresa());
			LOGGER.error(mensajeError, e);
		}
	}

	/**
	 * Formatear importe. Elimina el punto de la unidad de mil y cambia la coma
	 * de los decimales por el punto.
	 *
	 * @param importe
	 *            the importe
	 * @return the double
	 */
	public static BigDecimal formatearImporte(String importe) {
		if ("".equalsIgnoreCase(importe.replaceAll(".", ""))) {
			String imp = importe.replace(".", "");
			return new BigDecimal(imp.replace(",", "."));
		} else {
			return new BigDecimal(importe.replaceAll(".", "").replace(",", "."));
		}
	}

	/**
	 * Sumar pagos.
	 *
	 * @param pagos
	 *            the pagos
	 * @param solosOK
	 *            the solos OK
	 * @return the big decimal
	 */
	private BigDecimal sumarPagos(List<PagoInEntity> pagos, boolean solosOK) {
		BigDecimal total = BigDecimal.ZERO;
		for (PagoInEntity pagoDTO : pagos) {
			if (solosOK && pagoDTO.getTipoError() != null) {
				continue;
			}
			DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(new Locale("es_ES"));
			df.setParseBigDecimal(true);
			try {
				BigDecimal bd = (BigDecimal) df.parseObject(pagoDTO.getMonto().replace(".", ""));
				total = total.add(bd);
			} catch (ParseException e) {
				LOGGER.error(
						"Error al realizar el parseo para sumar el campo al ser utilizado solo para estadistica no es necesario otro manejo, escribir 0.",
						e);
			}
		}
		return total;
	}

	/**
	 * Parsear importe.
	 *
	 * @param valor
	 *            the valor
	 * @return the big decimal
	 */
	private BigDecimal parsearImporte(String valor) {
		DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.GERMANY);
		decimalFormat.setParseBigDecimal(true);
		try {
			return (BigDecimal) decimalFormat.parse(valor);
		} catch (ParseException e) {
			LOGGER.error("Error al realizar el parseo del monto a pagar", e);
			return BigDecimal.ZERO;
		}
	}

	@Override
	public Respuesta<ReporteView> imprimirComprobantes() {
		Boolean impresionOk = Boolean.TRUE;
		List<byte[]> listaReportes = new ArrayList<byte[]>();
		for (PagoMultipleView pm : sesionParametros.getListaPagosMultiplesView()) {
			Respuesta<Reporte> respuesta = null;
			if (pm.getEsPagoOk()) {
				respuesta = reporteBO.obtenerComprobantePDF(pm, pm, sesionParametros.getFechaHoraPago(),
						pm.getCuitVep(), pm.getElementoAdicional());
				if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
					listaReportes.add(respuesta.getRespuesta().getBytes());
				} else {
					impresionOk = Boolean.FALSE;
				}
			}			

		}
		return generarReporteView(listaReportes, impresionOk);
	}

	private Respuesta<ReporteView> generarReporteView(List<byte[]> listaReportes, Boolean impresionOk) {
		ReporteView reporteView = new ReporteView();
		try {
			if (listaReportes.isEmpty()) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_MULTIPLE, "");
			}
			reporteView = mergePdfComprobantesImpresion(listaReportes);
		} catch (IOException e) {
			LOGGER.error("Error al generar la previsualizacion de impresion", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_MULTIPLE, "");
		} catch (DocumentException e) {
			LOGGER.error("Error al generar la previsualizacion de impresion", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_MULTIPLE, "");
		}

		if (impresionOk) {
			return respuestaFactory.crearRespuestaOk(ReporteView.class, reporteView);
		}

		return respuestaFactory.crearRespuestaWarning(ReporteView.class, reporteView, TipoError.ERROR_DESCARGA_MULTIPLE,
				"", "");
	}

	private ReporteView mergePdfComprobantesImpresion(List<byte[]> inputPdfList) throws IOException, DocumentException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// Create document and pdfReader objects.
		Document document = new Document();
		List<PdfReader> readers = new ArrayList<PdfReader>();
		int totalPages = 0;

		// Create pdf Iterator object using inputPdfList.
		Iterator<byte[]> pdfIterator = inputPdfList.iterator();

		// Create reader list for the input pdf files.
		while (pdfIterator.hasNext()) {
			byte[] pdf = pdfIterator.next();
			// PdfReader pdfReader = new PdfReader(pdf);
			PdfReader pdfReader = new PdfReader(pdf);
			readers.add(pdfReader);
			totalPages = totalPages + pdfReader.getNumberOfPages();
		}

		// Create writer for the outputStream
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		// Open document.
		document.open();

		// Contain the pdf data.
		PdfContentByte pageContentByte = writer.getDirectContent();

		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		// Iterate and process the reader list.
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			// Create page and add content.
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}

		// Close document and outputStream.
		outputStream.flush();
		document.close();
		outputStream.close();

		byte[] byteArray = outputStream.toByteArray();
		Reporte reporte = new Reporte();
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		reporte.setBytes(byteArray);
		reporte.setNombre("Comprobante de pago");
		ReporteView reporteView = ReporteView.fromReporteComprobantes(reporte);

		return reporteView;
	}

	@Override
	public Respuesta<ListaPDFPagosMultiples> descargaMultipleComprobantesPDF() {
		ListaPDFPagosMultiples listaPDFPagosMultiples = new ListaPDFPagosMultiples();
		List<ReporteView> listaPDF = new ArrayList<ReporteView>();
		Boolean descargaOk = Boolean.TRUE;

		for (PagoMultipleView pago : sesionParametros.getListaPagosMultiples()) {
			Respuesta<Reporte> reporte;
			if (pago.getEsPagoOk()) {
				reporte = reporteBO.obtenerComprobantePDF(pago, pago, sesionParametros.getFechaHoraPago(),
						pago.getCuitVep(), pago.getElementoAdicional());

				if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
					ReporteView reporteView = ReporteView.fromReporteComprobantes(reporte.getRespuesta());
					listaPDF.add(reporteView);
				} else {
					descargaOk = Boolean.FALSE;
				}
			}
		}

		if (listaPDF.isEmpty()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_MULTIPLE, "");
		}

		listaPDFPagosMultiples.setPdfPagos(listaPDF);
		if (descargaOk) {
			return respuestaFactory.crearRespuestaOk(listaPDFPagosMultiples);
		}

		return respuestaFactory.crearRespuestaWarning(ListaPDFPagosMultiples.class, listaPDFPagosMultiples,
				TipoError.ERROR_DESCARGA_MULTIPLE, "", "");
	}

}