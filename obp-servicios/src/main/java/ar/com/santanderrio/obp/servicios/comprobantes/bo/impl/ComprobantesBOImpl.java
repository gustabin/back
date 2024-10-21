/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class ComprobantesBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class ComprobantesBOImpl implements ComprobantesBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesBOImpl.class);

	/** The Constant ERROR_GENERICO. */
	public static final String ERROR_GENERICO = "Error Generico";

	/** The Constant INICIO_COMPROBANTES. */
	public static final String INICIO_COMPROBANTES = "Iniciando busqueda de comprobantes..";

	/** The Constant FIN_COMPROBANTES. */
	public static final String FIN_COMPROBANTES = "Finalizando busqueda de comprobantes..";

	/** The Constant CREANDO_RESPUESTA_OK. */
	public static final String CREANDO_RESPUESTA_OK = "Creando respuesta OK..";

	/** The Constant CREANDO_RESPUESTA_WARNING. */
	public static final String CREANDO_RESPUESTA_WARNING = "Creando respuesta WARNING..";

	/** The Constant CREANDO_RESPUESTA_ERROR. */
	public static final String CREANDO_RESPUESTA_ERROR = "Creando respuesta ERROR..";

	/** The Constant LLAMANDO_METODO_ASINCRONICO. */
	public static final String LLAMANDO_METODO_ASINCRONICO = "Llamando metodo asincronico ";

	/** The msj error inesperado en finalizacion thread. */
	private static final String MSJ_ERROR_INESPERADO_EN_FINAL_THREAD = "Error en la espera de la finalizacion de los Hilos para obtener los comprobantes de los diferentes backends";

	/** The Constant ERROR_VALIDACION_DTO. */
	public static final String ERROR_VALIDACION_DTO = "Error al validar DTO..";

	/** The Constant FORMATO_FECHA. */
	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The Constant ERROR. */
	public static final String ERROR = "Error";

	/** The Constant MONEDA_PESOS. */
	public static final String MONEDA_PESOS = "$";

	/** The Constant MONEDA_DOLAR. */
	public static final String MONEDA_DOLAR = "U$S";

	/** The paginado desktop. */
	@Value("${COMPROBANTES.PAGINADO.DESKTOP:10}")
	private String paginadoDesktop;

	/** The paginado mobile. */
	@Value("${COMPROBANTES.PAGINADO.MOBILE:50}")
	private String paginadoMobile;

	/** The diasFechaDesde. */
	@Value("${COMPROBANTES.FECHA.ANTERIOR}")
	private String diasFechaDesde;

	/** The respuesta factory. */
	@Autowired
	protected RespuestaFactory respuestaFactory;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The Mensaje dao. */
	@Autowired
	protected MensajeBO mensajeBO;

	/**
	 * Dormir intervalo de tiempo.
	 *
	 * @param <T>
	 *            the generic type
	 * @param listaFuture
	 *            the lista future
	 */
	public <T> void esperarHastaFinDeListaDeHilos(List<Future<T>> listaFuture,
			List<Future<Respuesta<ComprobantesDTO>>> listaFuture2) {
		while (!allThreadsAreDone(listaFuture) && !allThreadsAreDone(listaFuture2)) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error("Error esperando que terminen los hilos consultados para obtener los comprobantes", e);
			}
		}
	}

	/**
	 * Dormir intervalo de tiempo.
	 *
	 * @param <T>
	 *            the generic type
	 * @param listaFuture
	 *            the lista future
	 */
	public <T> void esperarHastaFinDeListaDeHilos(List<Future<T>> listaFuture) {
		while (!allThreadsAreDone(listaFuture)) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error("Error esperando que terminen los hilos consultados para obtener los comprobantes", e);
			}
		}
	}

	/**
	 * All threads are done.
	 *
	 * @param <T>
	 *            the generic type
	 * @param listaFuture
	 *            the lista future
	 * @return the boolean
	 */
	private <T> Boolean allThreadsAreDone(List<Future<T>> listaFuture) {
		boolean termina = true;
		for (Future<T> future : listaFuture) {
			if (!future.isDone()) {
				termina = false;
			}
		}
		return termina;
	}

	/**
	 * Obtener medio de pago. Empresa/Destinatario: Con el tag codEstablecimiento se
	 * accede al archivo MediosdePago.txt y se obtiene el campo Nombre Fantasia
	 * cuando codEstablecimiento = VISA-CodEstablecimiento (del archivo txt). Si no
	 * encuentra la empresa en el archivo Mostrar "Establecimiento " y el tag
	 * codEstablecimiento
	 *
	 * @param codEstablecimiento
	 *            the cod establecimiento
	 * @return the string
	 */
	public String obtenerMedioDePago(String codEstablecimiento) {
		MedioPago medioPago = mediosPagoBO.obtenerPorCodEstablecimiento(codEstablecimiento);
		if (medioPago != null) {
			String codigo = medioPago.getNombreFantasia();
			if (codigo != null && !"".equals(codigo)) {
				return codigo;
			}
		}
		return "Establecimiento " + codEstablecimiento;
	}

	/**
	 * Formatear fecha corta con anio (dd/MM/yyyy). Si hay error retorna exception
	 *
	 * @param date
	 *            the date
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public Date formatearFechaAnio(String date) throws ParseException {
		if (StringUtils.isNotEmpty(date)) {
			SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
			return fFecha.parse(date);
		}
		return null;
	}

	/**
	 * Es respuesta error.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return true, if successful
	 */
	public boolean esRespuestaError(Respuesta<ComprobantesDTO> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR);
	}

	/**
	 * Tiene items mensajes.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return true, if successful
	 */
	public boolean tieneItemsMensajes(Respuesta<ComprobantesDTO> respuesta) {
		return !respuesta.getItemsMensajeRespuesta().isEmpty();
	}

	/**
	 * Crear respuesta error generico.
	 *
	 * @return the respuesta
	 */
	public Respuesta<ComprobantesDTO> crearRespuestaErrorGenerico() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Obtener date.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	protected Date obtenerDate(String fecha) {
		DateTime dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(fecha);
		return dateTime.toDateTime(DateTimeZone.UTC).toDate();
	}

	/**
	 * Obtener tipo cuenta.
	 *
	 * @param tipoCuentaOrigen2
	 *            the tipo cuenta origen 2
	 * @return the tipo cuenta
	 */
	protected TipoCuenta obtenerTipoCuenta(String tipoCuentaOrigen2) {
		if ("00".equals(tipoCuentaOrigen2)) {
			return TipoCuenta.CUENTA_CORRIENTE_PESOS;
		} else if ("01".equals(tipoCuentaOrigen2)) {
			return TipoCuenta.CAJA_AHORRO_PESOS;
		} else if ("03".equals(tipoCuentaOrigen2)) {
			return TipoCuenta.CUENTA_CORRIENTE_DOLARES;
		} else if ("04".equals(tipoCuentaOrigen2)) {
			return TipoCuenta.CAJA_AHORRO_DOLARES;
		}
		return TipoCuenta.CUENTA_UNICA;
	}

	/**
	 * Obtener moneda.
	 *
	 * @param tipoCuentaDestino
	 *            the tipo cuenta destino
	 * @return the string
	 */
	protected String obtenerMoneda(String tipoCuentaDestino) {
		if (tipoCuentaDestino == null) {
			return "$";
		}
		if ((0 == Integer.valueOf(tipoCuentaDestino)) || (1 == Integer.valueOf(tipoCuentaDestino))
				|| (9 == Integer.valueOf(tipoCuentaDestino))) {
			return "$";
		}
		return "U$S";
	}

	/**
	 * Sets the importe pesos dolar.
	 *
	 * @param moneda
	 *            the moneda
	 * @param importeDebito
	 *            the importe debito
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	protected void setearImportePesosDolar(String moneda, String importeDebito, ComprobanteDTO comprobanteDTO) {
		if ("$".equals(moneda)) {
			comprobanteDTO.setImportePesos(ISBANStringUtils.convertirABigDecimal(importeDebito));
		} else {
			comprobanteDTO.setImporteDolares(ISBANStringUtils.convertirABigDecimal(importeDebito));
		}
	}

	/**
	 * Sets the destinatario by string.
	 *
	 * @param titular
	 *            the new destinatario by string
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	protected void setearDestinatarioByString(String titular, ComprobanteDTO comprobanteDTO) {
		if (titular == null || StringUtils.isBlank(titular)) {
			comprobanteDTO.setDestinatario("-");
		} else {
			comprobanteDTO.setDestinatario(
					ISBANStringUtils.convertirStringToCamelcase(cambiarACodificacionCorrecta(titular)));
		}
	}

	/**
	 * Cambiar A codificacion correcta.
	 *
	 * @param titular
	 *            the titular
	 * @return the string
	 */
	private String cambiarACodificacionCorrecta(String titular) {
		// Por un caso de formato incorrecto se pasa este formateador de
		// caracteres especiales
		if (tieneCaracteresRotos1(titular) || tieneCaracteresRotos2(titular) || tieneCaracteresRotos3(titular)) {
			byte[] bytes = Charset.forName("ISO_8859_1").encode(titular).array();
			return new String(bytes, Charset.forName("UTF-8"));
		}
		if (titular.contains("Â¿")) {
			return titular.replace("Â¿", "ó");
		} else if (titular.contains("¿lares")) {
			return titular.replace("¿lares", "ólares");
		}
		return titular;
	}

	/**
	 * Tiene caracteres rotos 1.
	 *
	 * @param titular
	 *            the titular
	 * @return true, if successful
	 */
	private boolean tieneCaracteresRotos1(String titular) {
		return titular.contains("Ã¡") || titular.contains("Ã©") || titular.contains("Ã*");
	}

	/**
	 * Tiene caracteres rotos 2.
	 *
	 * @param titular
	 *            the titular
	 * @return true, if successful
	 */
	private boolean tieneCaracteresRotos2(String titular) {
		return titular.contains("Ã³") || titular.contains("Ãº") || titular.contains("Ã");
	}

	/**
	 * Tiene caracteres rotos 3.
	 *
	 * @param titular
	 *            the titular
	 * @return true, if successful
	 */
	private boolean tieneCaracteresRotos3(String titular) {
		return titular.contains("Ã‰") || titular.contains("Ã“") || titular.contains("Ãš");
	}

	/**
	 * Obtener descripcion.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param moneda
	 *            the moneda
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	protected void obtenerDescripcion(String tipoCuenta, String moneda, ComprobanteDTO comprobanteDTO) {
		if (mismoNumero(0, tipoCuenta) || mismoNumero(3, tipoCuenta)) {
			if ("$".equals(moneda)) {
				comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_CORRIENTE_PESOS);
			} else {
				comprobanteDTO.setTipoCtaMedioDePagoDolares(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
			}
		} else if (mismoNumero(1, tipoCuenta) || mismoNumero(4, tipoCuenta)) {
			if ("$".equals(moneda)) {
				comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);
			} else {
				comprobanteDTO.setTipoCtaMedioDePagoDolares(TipoCuenta.CAJA_AHORRO_DOLARES);
			}
		} else if (mismoNumero(9, tipoCuenta) || mismoNumero(10, tipoCuenta) || mismoNumero(2, tipoCuenta)) {
			resolverCuentaUnica(moneda, comprobanteDTO);
		} else {
			comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA_PESOS);
		}
	}

	/**
	 * Resolver cuenta unica.
	 *
	 * @param moneda
	 *            the moneda
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	private void resolverCuentaUnica(String moneda, ComprobanteDTO comprobanteDTO) {
		if ("$".equals(moneda)) {
			comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA_PESOS);
		} else {
			comprobanteDTO.setTipoCtaMedioDePagoDolares(TipoCuenta.CUENTA_UNICA_DOLARES);
		}
	}

	/**
	 * Sets the numero cuenta peso dolar.
	 *
	 * @param sucursalCuentaOrigen
	 *            the sucursal cuenta origen
	 * @param numeroCuentaOrigen
	 *            the numero cuenta origen
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	protected void setearNumeroCuentaPesoDolar(String sucursalCuentaOrigen, String numeroCuentaOrigen,
			ComprobanteDTO comprobanteDTO) {
		if (comprobanteDTO.getTipoCtaMedioDePagoDolares() == null) {
			comprobanteDTO.setCtaMedioDePagoPesos("" + sucursalCuentaOrigen + "-" + numeroCuentaOrigen);
		} else {
			comprobanteDTO.setCtaMedioDePagoDolares("" + sucursalCuentaOrigen + "-" + numeroCuentaOrigen);
		}

	}

	/**
	 * Mismo numero.
	 *
	 * @param i
	 *            the i
	 * @param string
	 *            the string
	 * @return true, if successful
	 */
	private boolean mismoNumero(int i, String string) {
		return (string != null && i == Integer.valueOf(string));
	}

	/**
	 * Obtener tipo cuenta de descripcion.
	 *
	 * @param descCuenta
	 *            the desc cuenta
	 * @param isPeso
	 *            the is peso
	 * @return the tipo cuenta
	 */
	protected TipoCuenta obtenerTipoCuentaDeDescripcion(String descCuenta, boolean isPeso) {
		if ("Cuenta Unica".equals(descCuenta)) {
			return TipoCuenta.CUENTA_UNICA;
		} else if ("Caja de Ahorro".equals(descCuenta)) {
			if (isPeso) {
				return TipoCuenta.CAJA_AHORRO_PESOS;
			}
			return TipoCuenta.CAJA_AHORRO_DOLARES;
		} else {
			if (isPeso) {
				return TipoCuenta.CUENTA_CORRIENTE_PESOS;
			}
			return TipoCuenta.CUENTA_CORRIENTE_DOLARES;
		}
	}

	/**
	 * Obtener sucursal parseada.
	 *
	 * @param sucCtaDeb
	 *            the suc cta deb
	 * @param nroCtaDeb
	 *            the nro cta deb
	 * @return the string
	 */
	protected String obtenerSucursalParseada(String sucCtaDeb, String nroCtaDeb) {
		String nroSucursal = sucCtaDeb.substring(sucCtaDeb.length() - 3);
		String nroCuentaProducto = nroCtaDeb.substring(nroCtaDeb.length() - 7);
		IdentificacionCuenta id = new IdentificacionCuenta(nroSucursal, nroCuentaProducto);
		return id.toString();
	}

	/**
	 * Gets the respuesta factory.
	 *
	 * @return the respuestaFactory
	 */
	public RespuestaFactory getRespuestaFactory() {
		return respuestaFactory;
	}

	/**
	 * Sets the respuesta factory.
	 *
	 * @param respuestaFactory
	 *            the respuestaFactory to set
	 */
	public void setRespuestaFactory(RespuestaFactory respuestaFactory) {
		this.respuestaFactory = respuestaFactory;
	}

	/**
	 * Obtener nombre archivo.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param detalleEstado
	 *            the detalle estado
	 * @return the string
	 */
	@Override
	public String obtenerTituloArchivo(TipoOperacionComprobanteEnum tipoOperacion, String detalleEstado) {
		String res;
		switch (tipoOperacion) {
		case TRANSFERENCIA_PROGRAMADA:
			res = obtenerTituloTransferenciaProgramada(detalleEstado);
			break;
		case PAGO_SUELDOS:
		case PAGO_HONORARIOS:
			res = obtenerTituloPagoSueldos(detalleEstado);
			break;
		default:
			res = obtenerTituloArchivoBase(tipoOperacion);
			break;
		}
		return res;

	}

	/**
	 * Obtener titulo archivo base.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerTituloArchivoBase(TipoOperacionComprobanteEnum tipoOperacion) {
		String res;
		switch (tipoOperacion) {
		case TRANSFERENCIA_INMEDIATA:
			res = CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle();
			break;
		case PAGO_TARJETA:
			res = CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle();
			break;
		case PAGO_SERVICIOS:
			res = CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle();
			break;
		default:
			res = null;
			break;
		}
		return res;
	}

	/**
	 * Obtener titulo archivo.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the string
	 */
	@Override
	public String obtenerTituloArchivo(TipoOperacionComprobanteEnum tipoOperacion, Boolean isEfectuada) {
		switch (tipoOperacion) {
		case TRANSFERENCIA_PROGRAMADA:
			return obtenerTituloTransferenciaProgramada(isEfectuada);
		case PAGO_SUELDOS:
		case PAGO_HONORARIOS:
			return obtenerTituloPagoSueldos(isEfectuada);
		default:
			return obtenerTituloArchivoBase(tipoOperacion);
		}
	}

	/**
	 * Obtener titulo transferencia programada.
	 *
	 * @param detalleEstado
	 *            the detalle estado
	 * @return the string
	 */
	private String obtenerTituloTransferenciaProgramada(String detalleEstado) {
		if (StringUtils.equals(detalleEstado, CabeceraComprobantesEnum.RECHAZADA.getDetalle())) {
			return CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA_PROGRAMADA_RECHAZADA.getDetalle();
		}
		return CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA_PROGRAMADA.getDetalle();
	}

	/**
	 * Obtener titulo pago sueldos.
	 *
	 * @param detalleEstado
	 *            the detalle estado
	 * @return the string
	 */
	private String obtenerTituloPagoSueldos(String detalleEstado) {
		if (StringUtils.equals(detalleEstado, CabeceraComprobantesEnum.RECHAZADA.getDetalle())) {
			return CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_RECHAZADO.getDetalle();
		}
		return CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle();
	}

	/**
	 * Obtener titulo transferencia programada.
	 *
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the string
	 */
	private String obtenerTituloTransferenciaProgramada(Boolean isEfectuada) {
		if (isEfectuada) {
			return CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA_PROGRAMADA.getDetalle();
		}
		return CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA_PROGRAMADA_RECHAZADA.getDetalle();
	}

	/**
	 * Obtener titulo pago sueldos.
	 *
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the string
	 */
	private String obtenerTituloPagoSueldos(Boolean isEfectuada) {
		if (isEfectuada) {
			return CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle();
		}
		return CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_RECHAZADO.getDetalle();
	}

	/**
	 * Obtiene respuesta de llamado asincronico y en caso de ExecutionException o
	 * InterruptedException devuelve una respuesta error.
	 *
	 * @param respuestasAsync
	 *            the respuestas async
	 * @return the respuesta
	 */
	protected List<Respuesta<ComprobantesDTO>> obtenerRespuestaDeAsyncFromMap(
			List<Future<Respuesta<ComprobantesDTO>>> respuestasAsync) {
		List<Respuesta<ComprobantesDTO>> res = new ArrayList<Respuesta<ComprobantesDTO>>();
		Respuesta<ComprobantesDTO> error = new Respuesta<ComprobantesDTO>();
		error.setEstadoRespuesta(EstadoRespuesta.ERROR);
		for (Future<Respuesta<ComprobantesDTO>> asynCall : respuestasAsync) {
			try {
				res.add(asynCall.get());
			} catch (ExecutionException e) {
				if (e.getCause() instanceof DAOException) {
					LOGGER.error("Error al recuperar la respuesta del hilo de ComprobantesDAO", e.getCause());
				}
                if (e.getCause() instanceof TaskRejectedException) {
                    LOGGER.error("Error cantidad de threads activos.", e.getCause());
                }
				res.add(error);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error(MSJ_ERROR_INESPERADO_EN_FINAL_THREAD, e);
				res.add(error);
			}
		}
		return res;
	}

	/**
	 * Filtrar comprobantes por importe.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param importeDesde
	 *            the importe desde
	 * @param importeHasta
	 *            the importe hasta
	 * @return the list
	 */
	protected List<ComprobanteDTO> filtrarComprobantesPorImporte(List<ComprobanteDTO> comprobantes,
			BigDecimal importeDesde, BigDecimal importeHasta) {
		List<ComprobanteDTO> comprobantesFiltrados = new LinkedList<ComprobanteDTO>();
		for (ComprobanteDTO comprobante : comprobantes) {
			if (aplicaFiltroPesos(comprobante, importeDesde, importeHasta) || aplicaFiltroDolares(comprobante, importeDesde, importeHasta)) {
				comprobantesFiltrados.add(comprobante);
			}
		}
		return comprobantesFiltrados;
	}

	private Boolean aplicaFiltroPesos(ComprobanteDTO comprobante, BigDecimal importeDesde, BigDecimal importeHasta) {
		return comprobante.getImportePesos() != null && importeDesde.compareTo(comprobante.getImportePesos()) <= 0
				&& importeHasta.compareTo(comprobante.getImportePesos()) >= 0;
	}

	private Boolean aplicaFiltroDolares(ComprobanteDTO comprobante, BigDecimal importeDesde, BigDecimal importeHasta) {
		return comprobante.getImporteDolares() != null && importeDesde.compareTo(comprobante.getImporteDolares()) <= 0
				&& importeHasta.compareTo(comprobante.getImporteDolares()) >= 0;
	}
}
