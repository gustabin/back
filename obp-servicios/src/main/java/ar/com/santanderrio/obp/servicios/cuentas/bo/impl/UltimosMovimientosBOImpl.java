package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.entities.RangoImporte;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientosPDF;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoExcelItem;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcel;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcelCabecera;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoConsultaMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.controller.TipoCuentaPredicate;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.DivisaPredicate;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.PalabraClavePredicate;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.RangoImportePredicate;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.MotivoRechazoChequeEnum;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class UltimosMovimientosBOImpl.
 */
@Component
public class UltimosMovimientosBOImpl implements UltimosMovimientosBO {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The ultimos movimientos dao. */
	@Autowired
	private MovimientosBO movimientosBO;

	/** The reporte dao. */
	@Autowired
	private ReporteDAO reporteDAO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The sucursal BO. */
	@Autowired
	private ConsultarSucursalesBO sucursalBO;

	/** The codigos cheque list. */
	@Value("#{'${CUENTAS.ULTMOV.TMOV.CHEQUES}'.split(',')}")
	private List<String> codigosChequeList;

	/** The codigos cheque rechazado list. */
	@Value("#{'${CUENTAS.ULTMOV.TMOV.CHEQUES.RECHAZADOS}'.split(',')}")
	private List<String> codigosChequeRechazadoList;

	/** The Constant CANT_DIAS_CIERRE. */
	private static final int CANT_DIAS_CIERRE = 60;

	/** The Constant AYER. */
	private static final int AYER = 1;

	/** The Constant NUM_TRES_DIAS. */
	private static final int NUM_TRES_DIAS = 3;

	/** The Constant NUM_SIETE_DIAS. */
	private static final int NUM_SIETE_DIAS = 7;

	/** The Constant NUM_QUINCE_DIAS. */
	private static final int NUM_QUINCE_DIAS = 15;

	/** The Constant NUM_TREINTA_DIAS. */
	private static final int NUM_TREINTA_DIAS = 30;

	/** The Constant NUM_SESENTA_DIAS. */
	private static final int NUM_SESENTA_DIAS = 60;

	/** The Constant CANT_DECIMALES_IMPORTE_VALIDO. */
	private static final int CANT_DECIMALES_IMPORTE_VALIDO = 2;

	/** The Constant BIG_DECIMAL_INFINITO. */
	private static final BigDecimal BIG_DECIMAL_INFINITO = new BigDecimal("999999999.99");

	/** The Constant VALOR_CERO. */
	private static final int VALOR_CERO = 0;

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "-";

	/** The Constant IMPORTE_VACIO. */
	private static final String IMPORTE_VACIO = "";

	/** The Constant INDICADOR_SOBREGIRO_ACTIVO. */
	private static final String INDICADOR_SOBREGIRO_ACTIVO = "S";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UltimosMovimientosBOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.bo.UltimosMovimientosBO#
	 * obtenerUltimosMovimientos(ar.com.santanderrio.obp.cuentas.entities.
	 * ConsultaUltimosMovimientos)
	 */
	@Override
	public Respuesta<DetalleUltimosMovimientos> obtenerUltimosMovimientos(ConsultaUltimosMovimientos filtroMovimientos,
			ConsultaUltimosMovimientos filtroSession) {

		try {
			Respuesta<UltimosMovimientosDTO> respuestaUltimosMovimientos = movimientosBO
					.obtenerMovimientos(filtroMovimientos);
			String codigoMensaje = StringUtils.EMPTY;

			if (EstadoRespuesta.ERROR.equals(respuestaUltimosMovimientos.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}
			codigoMensaje = obtenerMensajeNoHayMovimientos(filtroMovimientos);
			UltimosMovimientosDTO ultimosMovimientos = respuestaUltimosMovimientos.getRespuesta();

			/** validamos que la cuenta presente movimientos */
			if (ultimosMovimientos.getMovimientos() == null || ultimosMovimientos.getMovimientos().isEmpty()) {

				Respuesta<DetalleUltimosMovimientos> respuesta = respuestaFactory
						.crearRespuestaWarning(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS, codigoMensaje);
				/** Crear respusta vacia */
				if (EstadoRespuesta.WARNING.equals(respuestaUltimosMovimientos.getEstadoRespuesta())
						&& !TipoError.SIN_MOVIMIENTOS.getDescripcion()
								.equals(respuestaUltimosMovimientos.getItemsMensajeRespuesta().get(0).getTipoError())) {
					respuesta.getItemsMensajeRespuesta().addAll(respuestaUltimosMovimientos.getItemsMensajeRespuesta());
				}
				return respuesta;
			}
			/** Ver si tengo movimientos segun los filtros. */
			List<Movimiento> movimientoFiltrados = aplicarFiltros(ultimosMovimientos, filtroMovimientos, filtroSession);
			if (movimientoFiltrados.isEmpty()) {
				/** Crear respusta vacia */
				return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
						codigoMensaje);
			}
			DetalleUltimosMovimientos detalleUltimosMovimientos = cargarListaMovimientos(filtroMovimientos,
					movimientoFiltrados);
			filtroSession = filtroMovimientos;
			Respuesta<DetalleUltimosMovimientos> respuesta = Respuesta.copy(DetalleUltimosMovimientos.class,
					respuestaUltimosMovimientos);
			respuesta.setRespuesta(detalleUltimosMovimientos);
			return respuesta;
		} catch (ParseException e) {
			LOGGER.error("Error al parsear movimientos {}", filtroMovimientos, e);
			// Crear respueta con error generico
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		} catch (Exception e) {
			LOGGER.error("Error general al obtener los movimientos {}", filtroMovimientos, e);
			// Crear respueta con error generico
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO#
	 * validarFiltros(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * ConsultaUltimosMovimientos,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * ConsultaUltimosMovimientos)
	 */
	@Override
	public Respuesta<ConsultaUltimosMovimientos> validarFiltros(ConsultaUltimosMovimientos filtroMovimientos,
			ConsultaUltimosMovimientos filtroSession, Boolean primerIngreso) {

		AbstractCuenta cuenta = filtroMovimientos.getCuenta();
		if (!validateFechasyRangos(filtroMovimientos, cuenta.isCuentaCerrada()) || !validarImporte(filtroMovimientos)) {
			/** Crear respusta vacia */
			return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
					CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS);
		}

		if (!cuenta.isCuentaCerrada()) {
			setearFechasParaIntervalos(filtroMovimientos);
		}
		if ((primerIngreso != null && primerIngreso) || filtroSession == null || (cambioFiltroFecha(filtroMovimientos, filtroSession) || !filtroMovimientos
				.getCuenta().getNroCuentaProducto().equals(filtroSession.getCuenta().getNroCuentaProducto()))) {
			movimientosBO.limpiarCache(filtroMovimientos);
		}
		return respuestaFactory.crearRespuestaOk(filtroMovimientos);
	}

	/**
	 * Permite realizar el filtrado de los datos.
	 *
	 * @param ultimosMovimientos
	 *            the ultimos movimientos
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param filtroSession
	 *            the filtro session
	 * @return the list
	 */
	private List<Movimiento> aplicarFiltros(UltimosMovimientosDTO ultimosMovimientos,
			ConsultaUltimosMovimientos consultaUltimosMovimientos, ConsultaUltimosMovimientos filtroSession) {
		List<Movimiento> filtrado = new ArrayList<Movimiento>(ultimosMovimientos.getMovimientos());

		if (consultaUltimosMovimientos.getCuenta().isCuentaUnica()
				&& DivisaEnum.PESO.equals(consultaUltimosMovimientos.getMoneda())) {
			filtrado = filtrarPorTipoCuenta(consultaUltimosMovimientos.getTipoCuenta(), filtrado);
		}
		if (cambioFiltro(consultaUltimosMovimientos, filtroSession)) {
			RangoImporte rangoImporte = new RangoImporte(consultaUltimosMovimientos.getImporteDesde(),
					consultaUltimosMovimientos.getImporteHasta());
			String palabraClave = consultaUltimosMovimientos.getPalabraClave();
			if (consultaUltimosMovimientos.getCuenta().isCuentaUnica()) {
				filtrado = filtrarPorMoneda(consultaUltimosMovimientos.getMoneda(), filtrado);
			}
			filtrado = filtrarPorImporte(rangoImporte, filtrado);
			filtrado = filtrarPorPalabraClave(palabraClave, filtrado);
		}
		return filtrado;
	}

	/**
	 * Cambio filtro.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param oldFiltro
	 *            the old filtro
	 * @return true, if successful
	 */
	private boolean cambioFiltro(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			ConsultaUltimosMovimientos oldFiltro) {
		TipoCuenta tc = consultaUltimosMovimientos.getCuenta().isCuentaUnica()
				? consultaUltimosMovimientos.getTipoCuenta()
				: consultaUltimosMovimientos.getCuenta().getTipoCuentaEnum();
		boolean cambioFiltro = true;
		// Solucionar nulleable
		if (oldFiltro != null) {
			cambioFiltro = cambioImporte(oldFiltro.getImporteDesde(), consultaUltimosMovimientos.getImporteDesde());
			cambioFiltro = cambioFiltro
					|| cambioImporte(oldFiltro.getImporteHasta(), consultaUltimosMovimientos.getImporteHasta());
			cambioFiltro = cambioFiltro
					|| cambioFecha(oldFiltro.getFechaHasta(), consultaUltimosMovimientos.getFechaHasta());
			cambioFiltro = cambioFiltro
					|| cambioFecha(oldFiltro.getFechaDesde(), consultaUltimosMovimientos.getFechaDesde());
			cambioFiltro = cambioFiltro
					|| cambioString(oldFiltro.getPalabraClave(), consultaUltimosMovimientos.getPalabraClave());
			cambioFiltro = cambioFiltro || cambioMoneda(oldFiltro.getMoneda(), consultaUltimosMovimientos.getMoneda());
			cambioFiltro = cambioFiltro || cambioTipoCuenta(oldFiltro.getTipoCuenta(), tc);
		}
		return cambioFiltro;
	}

	/**
	 * si ocurre cun cambio en el filtrado por fecha.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param oldFiltro
	 *            the old filtro
	 * @return true, if successful
	 */
	private boolean cambioFiltroFecha(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			ConsultaUltimosMovimientos oldFiltro) {
		boolean cambioFiltro = true;
		// Solucionar nulleable
		if (oldFiltro != null) {
			cambioFiltro = cambioFecha(oldFiltro.getFechaHasta(), consultaUltimosMovimientos.getFechaHasta());
			cambioFiltro = cambioFiltro
					|| cambioFecha(oldFiltro.getFechaDesde(), consultaUltimosMovimientos.getFechaDesde());
		}
		return cambioFiltro;
	}

	/**
	 * Cambio tipo cuenta.
	 *
	 * @param viejo
	 *            the viejo
	 * @param nuevo
	 *            the nuevo
	 * @return true, if successful
	 */
	private boolean cambioTipoCuenta(TipoCuenta viejo, TipoCuenta nuevo) {
		return cambioObjeto(viejo, nuevo);
	}

	/**
	 * Cambio string.
	 *
	 * @param viejo
	 *            the viejo
	 * @param nuevo
	 *            the nuevo
	 * @return true, if successful
	 */
	private boolean cambioString(String viejo, String nuevo) {
		boolean cambio = false;
		// ambos nulos o distintos
		if (viejo == null) {
			if (nuevo != null) {
				cambio = true;
			}
		} else {
			if (nuevo == null) {
				cambio = true;
			} else if (ISBANStringUtils.normalizarCaraceteres(viejo)
					.equalsIgnoreCase(ISBANStringUtils.normalizarCaraceteres(nuevo))) {
				cambio = true;
			}
		}
		return cambio;
	}

	/**
	 * Cambio objeto.
	 *
	 * @param <T>
	 *            the generic type
	 * @param viejo
	 *            the viejo
	 * @param nuevo
	 *            the nuevo
	 * @return true, if successful
	 */
	private <T> boolean cambioObjeto(T viejo, T nuevo) {
		boolean cambio = false;
		// ambos nulos o distintos
		if (viejo == null) {
			if (nuevo != null) {
				cambio = true;
			}
		} else {
			if (nuevo == null) {
				cambio = true;
			} else if (viejo.equals(nuevo)) {
				cambio = true;
			}
		}
		return cambio;
	}

	/**
	 * Cambio comparable.
	 *
	 * @param <T>
	 *            the generic type
	 * @param viejo
	 *            the viejo
	 * @param nuevo
	 *            the nuevo
	 * @return true, if successful
	 */
	private <T> boolean cambioComparable(Comparable<T> viejo, T nuevo) {
		boolean cambio = false;
		// ambos nulos o distintos
		if (viejo == null) {
			if (nuevo != null) {
				cambio = true;
			}
		} else {
			if (nuevo == null) {
				cambio = true;
			} else if (viejo.compareTo(nuevo) != 0) {
				cambio = true;
			}
		}
		return cambio;
	}

	/**
	 * Cambio fecha.
	 *
	 * @param vieja
	 *            the vieja
	 * @param nueva
	 *            the nueva
	 * @return true, if successful
	 */
	private boolean cambioFecha(Date vieja, Date nueva) {
		return cambioComparable(vieja, nueva);
	}

	/**
	 * Cambio importe.
	 *
	 * @param viejo
	 *            the viejo
	 * @param nuevo
	 *            the nuevo
	 * @return true, if successful
	 */
	private boolean cambioImporte(BigDecimal viejo, BigDecimal nuevo) {
		return cambioComparable(viejo, nuevo);
	}

	/**
	 * Cambio moneda.
	 *
	 * @param vieja
	 *            the vieja
	 * @param nueva
	 *            the nueva
	 * @return true, if successful
	 */
	private boolean cambioMoneda(DivisaEnum vieja, DivisaEnum nueva) {
		return cambioObjeto(vieja, nueva);
	}

	/**
	 * Cargar lista movimientos.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param listMovimientos
	 *            the list movimientos
	 * @return the detalle ultimos movimientos
	 * @throws ParseException
	 *             the parse exception
	 */
	private DetalleUltimosMovimientos cargarListaMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			List<Movimiento> listMovimientos) throws ParseException {
		/** Ubicamos el cursor segun la paginacion */
		DetalleUltimosMovimientos detallesUltimosMovimientos = new DetalleUltimosMovimientos();
		detallesUltimosMovimientos.setDetalleMovimiento(new ArrayList<DetalleMovimientoEntity>());
		for (Movimiento movimiento : listMovimientos) {
			DetalleMovimientoEntity detalleMovimiento = DetalleMovimientoBuilder
					.build(consultaUltimosMovimientos.getCuenta().isCuentaUnica(), movimiento);
			detalleMovimiento.setDescripcionSucursal(obtenerDescripcionSucursal(movimiento));
			if (isMovimientoChequeRechazado(movimiento, codigosChequeRechazadoList)) {
				detalleMovimiento.setCheque(true);
				detalleMovimiento.setRechazado(true);
				detalleMovimiento.setMotivoRechazo(obtenerDescripcionMotivoRechazo(movimiento.getReferenciaInterna()));
			} else if (isMovimientoChequeNoRechazado(movimiento, codigosChequeList)) {
				detalleMovimiento.setCheque(true);
				detalleMovimiento.setRechazado(false);
				detalleMovimiento.setDescripcionAdicional(StringUtils.trim(detalleMovimiento.getDescripcionAdicional())
						+ " " + detalleMovimiento.getNumeroReferencia());
			}
			detallesUltimosMovimientos.getDetalleMovimiento().add(detalleMovimiento);
		}
		return detallesUltimosMovimientos;
	}

	/**
	 * Obtener descripcion sucursal.
	 *
	 * @param movimiento
	 *            the movimiento
	 * @return the string
	 */
	private String obtenerDescripcionSucursal(Movimiento movimiento) {
		Respuesta<Sucursal> sucursal = sucursalBO.consultarSucursalPorId(movimiento.getSucursalOrigen());
		if (sucursal != null && sucursal.getRespuesta() != null) {
			return sucursal.getRespuesta().getDescripcion();
		} else {
			return "";
		}
	}

	/**
	 * Checks if is movimiento cheque rechazado.
	 *
	 * @param movimiento
	 *            the movimiento
	 * @param codigosChequeRechazadoList
	 *            the codigos cheque rechazado list
	 * @return true, if is movimiento cheque rechazado
	 */
	private boolean isMovimientoChequeRechazado(Movimiento movimiento, List<String> codigosChequeRechazadoList) {
		for (String codCheque : codigosChequeRechazadoList) {
			if (codCheque.equals(movimiento.getCodigoAltaIr())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is movimiento cheque no rechazado.
	 *
	 * @param movimiento
	 *            the movimiento
	 * @param codigosChequeRechazadoList
	 *            the codigos cheque rechazado list
	 * @return true, if is movimiento cheque rechazado
	 */
	private boolean isMovimientoChequeNoRechazado(Movimiento movimiento, List<String> codigosChequeList) {
		for (String codCheque : codigosChequeList) {
			if (codCheque.equals(movimiento.getCodigoAltaIr())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtener descripcion motivo rechazo.
	 *
	 * @param referenciaInterna
	 *            the referencia interna
	 * @return the string
	 */
	private String obtenerDescripcionMotivoRechazo(String referenciaInterna) {
		String motivoRechazo = MotivoRechazoChequeEnum.fromReferenciaInterna(referenciaInterna.trim());
		return (motivoRechazo != null) ? motivoRechazo : "";
	}

	/**
	 * Validate range fecha.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param isCuentaCerrada
	 *            the is cuenta cerrada
	 * @return true, if successful
	 */
	public static boolean validateFechasyRangos(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			boolean isCuentaCerrada) {
		boolean result = false;
		Date fechaHoy = limpiarHoras(new Date());
		if (consultaUltimosMovimientos.getRangoFecha().equals(RangoFechaEnum.PERSONALIZADO)) {

			if (TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA.equals(consultaUltimosMovimientos.getTipoConsulta())
					&& limpiarHoras(consultaUltimosMovimientos.getFechaHasta()).compareTo(fechaHoy) < 0) {
				return false;
			}

			// Si es PERSONALIZADO, FechaDesde y FechaHasta no pueden ser null
			if (distanciaEntreDiasMenorASesenta(consultaUltimosMovimientos, fechaHoy)) {
				result = true;
			} else {
				return false;
			}
		}

		if (!isCuentaCerrada) {
			// Si es cuenta abierta, termino la validacion.
			result = true;
		} else {
			CuentaCerrada cuentaCerrada = (CuentaCerrada) consultaUltimosMovimientos.getCuenta(); 
			Date fechaCierre;
			try {
				SimpleDateFormat sfCerrada = new SimpleDateFormat("yyyy-MM-dd");
				// Busco la FechaCierre de la cuenta.
				fechaCierre = sfCerrada.parse(cuentaCerrada.getFechaBajaContrato());
			} catch (ParseException e) {
				return false;
			}
			int cantDiasConLaCuentaCerrada = diferenciaFechaEnDias(fechaCierre, fechaHoy);
			// La cuenta no puede estar mas de 60 dias cerradas
			if (!validacionesNoEnRangoFechaPersonalizado(cantDiasConLaCuentaCerrada, consultaUltimosMovimientos,
					fechaCierre, fechaHoy)) {
				return false;
			} else {
				result = true;
			}

		}
		return result;
	}

	/**
	 * Limpiar horas.
	 *
	 * @param parsearFecha
	 *            the parsear fecha
	 * @return the date
	 */
	public static Date limpiarHoras(Date parsearFecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parsearFecha);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Validaciones no en rango fecha personalizado.
	 *
	 * @param cantDiasConLaCuentaCerrada
	 *            the cant dias con la cuenta cerrada
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param fechaCierre
	 *            the fecha cierre
	 * @param fechaHoy
	 *            the fecha hoy
	 * @return true, if successful
	 */
	private static boolean validacionesNoEnRangoFechaPersonalizado(int cantDiasConLaCuentaCerrada,
			ConsultaUltimosMovimientos consultaUltimosMovimientos, Date fechaCierre, Date fechaHoy) {
		if (!(cantDiasConLaCuentaCerrada <= CANT_DIAS_CIERRE)) {
			return Boolean.FALSE;
		}

		if (!fechaNoMayorAFechaDeCierre(consultaUltimosMovimientos, fechaCierre)) {
			return Boolean.FALSE;
		}

		if (consultaUltimosMovimientos.getRangoFecha().equals(RangoFechaEnum.DEFAULT)) {
			setearFechasDefaultCuentaCerrada(consultaUltimosMovimientos, fechaHoy, fechaCierre);
		}
		return Boolean.TRUE;
	}

	/**
	 * Fecha no mayor A fecha de cierre.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param fechaCierre
	 *            the fecha cierre
	 * @return true, if successful
	 */
	private static boolean fechaNoMayorAFechaDeCierre(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			Date fechaCierre) {

		if (consultaUltimosMovimientos.getRangoFecha().equals(RangoFechaEnum.DEFAULT)) {
			return Boolean.TRUE;
		}

		if (consultaUltimosMovimientos.getRangoFecha().equals(RangoFechaEnum.PERSONALIZADO)) {
			// La FechaHasta no puede ser mayor a la FechaCierre
			if (consultaUltimosMovimientos.getFechaHasta().compareTo(fechaCierre) <= 0) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * Distancia entre dias menor A sesenta.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param fechaHoy
	 *            the fecha hoy
	 * @return true, if successful
	 */
	private static boolean distanciaEntreDiasMenorASesenta(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			Date fechaHoy) {
		Boolean result = Boolean.FALSE;
		if (consultaUltimosMovimientos.getFechaDesde() != null && consultaUltimosMovimientos.getFechaHasta() != null) {
			// FechaDesde no puede ser mayor a FechaHasta
			if (consultaUltimosMovimientos.getFechaDesde().compareTo(consultaUltimosMovimientos.getFechaHasta()) <= 0) {
				int cantDiasFechaDesdeAFechaHoy = diferenciaFechaEnDias(consultaUltimosMovimientos.getFechaDesde(),
						fechaHoy);
				// Desde la FechaDesde a FechaHoy no deben haber mas de 60
				// dias.
				if (cantDiasFechaDesdeAFechaHoy <= CANT_DIAS_CIERRE) {
					result = Boolean.TRUE;
				}
			}
		}
		return result;
	}

	/**
	 * Setear fechas default cuenta cerrada.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param fechaHoy
	 *            the fecha hoy
	 * @param fechaCierre
	 *            the fecha cierre
	 */
	private static void setearFechasDefaultCuentaCerrada(ConsultaUltimosMovimientos consultaUltimosMovimientos,
			Date fechaHoy, Date fechaCierre) {
		// Si el rango es DEFAULT, FechaHasta es la FechaCierre y FechaDesde son
		// 7 dias antes.
		consultaUltimosMovimientos.setFechaHasta(fechaCierre);
		consultaUltimosMovimientos.setFechaDesde(restarDias(fechaCierre, NUM_SIETE_DIAS));
		int cantDiasFechaDesdeAFechaHoy = diferenciaFechaEnDias(consultaUltimosMovimientos.getFechaDesde(), fechaHoy);
		if (cantDiasFechaDesdeAFechaHoy > CANT_DIAS_CIERRE) {
			// Si la FechaDese se paso de los 60 dias permitidos para consultar.
			// FechaDesde es FechaHoy menos 60.
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaHoy, CANT_DIAS_CIERRE));
		}
	}

	/**
	 * Diferencia fecha en dias.
	 *
	 * @param fechaInicial
	 *            the fecha inicial
	 * @param fechaFinal
	 *            the fecha final
	 * @return the int
	 */
	public static int diferenciaFechaEnDias(Date fechaInicial, Date fechaFinal) {
		long diff = fechaFinal.getTime() - fechaInicial.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	/**
	 * Setter para ear fecha desde hasta.
	 *
	 * @param consultaUltimosMovimientos
	 *            el nuevo ear fecha desde hasta
	 */
	@SuppressWarnings("static-access")
	private void setearFechasParaIntervalos(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
		Date fechaBase = new Date();
		Calendar cal = Calendar.getInstance();
		int dias8 = -8;
		cal.setTime(fechaBase);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		fechaBase = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		Date fechaHoy = cal.getTime();
		cal.add(cal.DAY_OF_YEAR, dias8);
		Date fecha8dias = cal.getTime();
		switch (consultaUltimosMovimientos.getRangoFecha()) {
		case AYER:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaBase, AYER));
			consultaUltimosMovimientos.setFechaHasta(fechaHoy);
			break;
		case TRES_DIAS:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaBase, NUM_TRES_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoy);
			break;
		case SIETE_DIAS:
		case DEFAULT:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaBase, NUM_SIETE_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoy);
			break;
		case QUINCE_DIAS:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaBase, NUM_QUINCE_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoy);
			break;
		case TREINTA_DIAS:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaBase, NUM_TREINTA_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoy);
			break;
		case SESENTA_DIAS:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaHoy, NUM_SESENTA_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fechaHoy);
			break;
		// Ver mas en la grilla se le suma 53 dias a los 7 ya soliciado
		case CINCUENTA_Y_TRES_DIAS:
			consultaUltimosMovimientos.setFechaDesde(restarDias(fechaHoy, NUM_SESENTA_DIAS));
			consultaUltimosMovimientos.setFechaHasta(fecha8dias);
			break;
		// utiliza los filtros de fecha desde y hasta de la consulta
		case PERSONALIZADO:
			break;
		default:
			break;
		}
	}

	/**
	 * Restar dias.
	 *
	 * @param fecha
	 *            the fecha
	 * @param cantDias
	 *            the cant dias
	 * @return the date
	 */
	public static Date restarDias(Date fecha, int cantDias) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);
		fechaCalendar.set(Calendar.HOUR, 0);
		fechaCalendar.set(Calendar.MINUTE, 0);
		fechaCalendar.set(Calendar.SECOND, 0);
		fechaCalendar.set(Calendar.MILLISECOND, 0);
		fechaCalendar.add(Calendar.DAY_OF_YEAR, -cantDias);
		return fechaCalendar.getTime();
	}

	/**
	 * Validar importe.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return true, if successful
	 */
	private boolean validarImporte(ConsultaUltimosMovimientos consultaUltimosMovimientos) {

		BigDecimal importeDesde = consultaUltimosMovimientos.getImporteDesde();

		if (importeDesde == null) {
			consultaUltimosMovimientos.setImporteDesde(BigDecimal.ZERO);
		} else {
			if (importeDesde.scale() > CANT_DECIMALES_IMPORTE_VALIDO) {
				return false;
			}
		}
		BigDecimal importeHasta = consultaUltimosMovimientos.getImporteHasta();
		if (importeHasta == null) {
			consultaUltimosMovimientos.setImporteHasta(BIG_DECIMAL_INFINITO);
		} else {
			if (importeHasta.scale() > CANT_DECIMALES_IMPORTE_VALIDO) {
				return false;
			}
		}
		boolean resultadoValido = true;
		// compareTo retorna 1 si es mayor que el comparado, -1 si es menor y 0
		// si es igual
		if (consultaUltimosMovimientos.getImporteDesde().compareTo(BigDecimal.ZERO) < VALOR_CERO
				|| consultaUltimosMovimientos.getImporteHasta().compareTo(BigDecimal.ZERO) < VALOR_CERO) {
			resultadoValido = false;
		} else {
			if (consultaUltimosMovimientos.getImporteHasta()
					.compareTo(consultaUltimosMovimientos.getImporteDesde()) < VALOR_CERO) {
				resultadoValido = false;
			}
		}
		return resultadoValido;
	}

	/**
	 * Obtener mensaje no hay movimientos.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return the string
	 */
	@Override
	public String obtenerMensajeNoHayMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos) {

		boolean isCuentaCerrada = consultaUltimosMovimientos.getCuenta().isCuentaCerrada();
		RangoFechaEnum rangoFecha = consultaUltimosMovimientos.getRangoFecha();
		boolean filtrosUtilizados = seModificaronLosFiltrosPalabraClaveOImporte(consultaUltimosMovimientos);
		if (consultaUltimosMovimientos.getDesdeDefault()) {
			return CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS_ULTIMOS_SESENTA_DIAS;
		}
		if (rangoFecha == RangoFechaEnum.DEFAULT) {
			if (filtrosUtilizados) {
				return CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS;
			}
			if (!isCuentaCerrada) {
				return CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS_SIETE_DIAS;
			}
			Calendar fechaHoy = Calendar.getInstance();
			CuentaCerrada cuentaCerrada = (CuentaCerrada) consultaUltimosMovimientos.getCuenta();
			setearFechasDefaultCuentaCerrada(consultaUltimosMovimientos, fechaHoy.getTime(),
					ISBANStringUtils.formatearFecha(cuentaCerrada.getFechaBajaContrato(), "yyyyMMdd"));

			int cantDiasFechaDesdeAFechaHasta = diferenciaFechaEnDias(consultaUltimosMovimientos.getFechaDesde(),
					consultaUltimosMovimientos.getFechaHasta());
			if (cantDiasFechaDesdeAFechaHasta < NUM_SIETE_DIAS) {
				return CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS_DEFAULT_CERRADA_MENOR_7_DIAS;
			}
			return CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS_SIETE_DIAS_CERRADA;
		}
		return CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS;
	}

	/**
	 * Se modificaron los filtros palabra clave O importe.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return true, if successful
	 */
	private boolean seModificaronLosFiltrosPalabraClaveOImporte(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
		// compareTo retorna 1 si es mayor que el comparado, -1 si es menor y 0
		// si es igual
		if (consultaUltimosMovimientos.getImporteDesde() != null
				&& consultaUltimosMovimientos.getImporteDesde().compareTo(BigDecimal.ZERO) != 0) {
			return true;
		}
		if (consultaUltimosMovimientos.getImporteHasta() != null
				&& consultaUltimosMovimientos.getImporteHasta().compareTo(BIG_DECIMAL_INFINITO) != 0) {
			return true;
		}

		if (!consultaUltimosMovimientos.getPalabraClave().isEmpty()) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.bo.UltimosMovimientosBO#
	 * exportarUltimosMovimientos(java.util.List,
	 * ar.com.santanderrio.obp.cuentas.entities.ConsultaUltimosMovimientos)
	 */
	@Override
	public Respuesta<Reporte> exportarUltimosMovimientos(ConsultaUltimosMovimientos filtro, String dispositivo) {
	    MovimientosExcel movimientosExcel = obtenerDatosExcel(filtro, dispositivo);
		Cliente cliente = filtro.getCuenta().getCliente();
		formatearMontos(movimientosExcel);
		return reporteDAO.obtenerReporte(movimientosExcel, ExcelBuilderHelper.ULTIMOS_MOVIMIENTOS, cliente, true);

	}
	
	@Override
    public DetalleComprobanteView exportarUltimosMovimientosPDF(ConsultaUltimosMovimientos filtro, String dispositivo) {
        MovimientosExcel movimientosExcel = obtenerDatosExcel(filtro, dispositivo);
        return new DetalleUltimosMovimientosPDF(movimientosExcel);
    }
	
	private MovimientosExcel obtenerDatosExcel(ConsultaUltimosMovimientos filtro, String dispositivo) {
	    Respuesta<DetalleUltimosMovimientos> respuestaDetalleUltimosMovimientos = obtenerUltimosMovimientosExcel(filtro,
                filtro);
        MovimientosExcel movimientosExcel = new MovimientosExcel();
        MovimientosExcelCabecera cabecera = crearCabeceraExcel(filtro, dispositivo);

        List<MovimientoExcelItem> items = new ArrayList<MovimientoExcelItem>();
        if (respuestaDetalleUltimosMovimientos != null && respuestaDetalleUltimosMovimientos.getRespuesta() != null) {
            List<DetalleMovimientoEntity> movimientos = respuestaDetalleUltimosMovimientos.getRespuesta().getDetalleMovimiento();
            
            
            for (DetalleMovimientoEntity detalleMovimiento : movimientos) {
                MovimientoExcelItem movimiento = crearItemExcel(detalleMovimiento);
                items.add(movimiento);
            }
        }

        movimientosExcel.setCabecera(cabecera);
        movimientosExcel.setMovimientos(items);
        return movimientosExcel;
	}

	/**
	 * Crear cabecera excel.
	 *
	 * @param filtro
	 *            the filtro
	 * @param dispositivo
	 *            the dispositivo
	 * @return the movimientos excel cabecera
	 */
	private MovimientosExcelCabecera crearCabeceraExcel(ConsultaUltimosMovimientos filtro, String dispositivo) {
		MovimientosExcelCabecera cabecera = new MovimientosExcelCabecera();

		// siempre filtro por fecha
		Boolean mostrarFiltroFecha = true;
		String fechaDesde = ISBANStringUtils.formatearFecha(filtro.getFechaDesde());
		String fechaHasta = ISBANStringUtils.formatearFecha(filtro.getFechaHasta());

		cabecera.setFechaDesde(fechaDesde);
		cabecera.setFechaHasta(fechaHasta);

		if (filtro.getImporteDesde() == null || filtro.getImporteDesde().compareTo(BigDecimal.ZERO) == 0) {
			if (!importeHastaDistintoDeNullOCero(filtro.getImporteHasta())) {
				cabecera.setImporteDesde(ISBANStringUtils.formatearSaldo(new BigDecimal("0.00")));
				filtro.setImporteDesde(null);
			} else {
				cabecera.setImporteDesde(IMPORTE_VACIO);
				filtro.setImporteDesde(null);
			}
		} else {
			cabecera.setImporteDesde(ISBANStringUtils.formatearSaldo(filtro.getImporteDesde()));
		}
		if (filtro.getImporteHasta() == null || filtro.getImporteHasta().compareTo(BIG_DECIMAL_INFINITO) == 0) {
			cabecera.setImporteHasta(IMPORTE_VACIO);
			filtro.setImporteHasta(null);
		} else {
			cabecera.setImporteHasta(ISBANStringUtils.formatearSaldo(filtro.getImporteHasta()));
		}
		Boolean mostrarFiltroImportes = filtro.getImporteDesde() != null || filtro.getImporteHasta() != null;

		Boolean mostrarFiltroPalabraClave = StringUtils.isNotBlank(filtro.getPalabraClave());
		String palabraClave = filtro.getPalabraClave();

		String sucursal = ISBANStringUtils.formatearSucursal(filtro.getCuenta().getNroSucursal());
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(filtro.getCuenta().getNroCuentaProducto());
		String numeroCuentaCompleto = sucursal + SEPARATOR + numeroCuenta;

		String tipoCuenta = TipoCuenta.fromCodigo(filtro.getCuenta().getTipoCuenta()).getDescripcion();

		cabecera.setPalabraClave(palabraClave);
		cabecera.setNumeroCuenta(numeroCuentaCompleto);
		cabecera.setTipoCuenta(tipoCuenta.toUpperCase().substring(0, 1).concat(tipoCuenta.toLowerCase().substring(1)));

		Boolean mostrarSaldoAperturado = isMostrarSaldoAperturado(filtro, filtro.getCuenta());

		cabecera.setMostrarFiltroFecha(mostrarFiltroFecha);
		cabecera.setMostrarFiltroImportes(mostrarFiltroImportes);
		cabecera.setMostrarFiltroPalabraClave(mostrarFiltroPalabraClave);
		cabecera.setMostrarSaldoAperturado(mostrarSaldoAperturado);
		cabecera.setHasCtaCte(Boolean.TRUE);
		if (filtro.getCuenta().getIndicadorSobregiro() != null
				&& !INDICADOR_SOBREGIRO_ACTIVO.equals(filtro.getCuenta().getIndicadorSobregiro())) {
			cabecera.setHasCtaCte(Boolean.FALSE);
		}
		if (filtro.getMoneda() != null) {
			cabecera.setMoneda(filtro.getMoneda().equals(DivisaEnum.PESO) ? "Pesos" : "D\u00F3lares");
		} else {
			cabecera.setMoneda("");
		}
		cabecera.setDispositivo(dispositivo);

		Boolean isConvenioPAS = isConvenioPAS(filtro.getCuenta());
		cabecera.setIsConvenioPAS(isConvenioPAS);

		return cabecera;
	}

	private Boolean importeHastaDistintoDeNullOCero(BigDecimal importeHasta) {

		return importeHasta == null || importeHasta.compareTo(BIG_DECIMAL_INFINITO) == 0;

	}

	/**
	 * Checks if is convenio pas.
	 *
	 * @param abstractCuenta
	 *            the abstract cuenta
	 * @return the boolean
	 */
	private Boolean isConvenioPAS(AbstractCuenta abstractCuenta) {
		Boolean isConvenioPAS = false;
		if (!abstractCuenta.isCuentaCerrada()) {
			Cuenta cuenta = (Cuenta) abstractCuenta;
			isConvenioPAS = cuenta.isConvenioPAS();
		}
		return isConvenioPAS;
	}

	/**
	 * Crear item excel.
	 *
	 * @param detalleMovimiento
	 *            the detalle movimiento
	 * @return the movimiento excel item
	 */
	private MovimientoExcelItem crearItemExcel(DetalleMovimientoEntity detalleMovimiento) {
		MovimientoExcelItem movimiento = new MovimientoExcelItem();
		movimiento
				.setDescripcion(detalleMovimiento.getDescripcionAdicional() + " " + detalleMovimiento.getObservacion());

		String fecha = ISBANStringUtils.formatearFecha(detalleMovimiento.getFecha());

		movimiento.setFechaHora(fecha);

		movimiento.setIsCajaDeAhoroEnPesos(detalleMovimiento.getIsCajaDeAhoroEnPesos());
		movimiento.setIsCuentaCorrienteEnPesos(detalleMovimiento.getIsCuentaCorrienteEnPesos());
		movimiento.setIsMovimientoEnDolares(detalleMovimiento.getIsMovimientoEnDolares());

		movimiento.setImporte(detalleMovimiento.getImporteMovimiento().doubleValue());
		movimiento.setIsDelDia(detalleMovimiento.getIsDelDia());

		movimiento.setSaldo(detalleMovimiento.getSaldoCuenta().doubleValue());

		movimiento.setSucursalOrigen(
				detalleMovimiento.getNumeroSucursal() + " " + detalleMovimiento.getDescripcionSucursal());

		movimiento.setReferencia(detalleMovimiento.getNumeroReferencia());

		return movimiento;
	}

	/**
	 * Checks if is mostrar saldo aperturado.
	 *
	 * @param filtro
	 *            the filtro
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean isMostrarSaldoAperturado(ConsultaUltimosMovimientos filtro, AbstractCuenta cuenta) {
		return TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).equals(TipoCuenta.CUENTA_UNICA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.bo.UltimosMovimientosBO#completarFiltro(
	 * ar.com.santanderrio.obp.cuentas.entities.ConsultaUltimosMovimientos)
	 */
	@Override
	public void completarFiltro(ConsultaUltimosMovimientos consultaUltimosMovimientos) throws BusinessException {
		if (consultaUltimosMovimientos.getCuenta().isCuentaCerrada()
				&& consultaUltimosMovimientos.getRangoFecha().equals(RangoFechaEnum.DEFAULT)) {
			CuentaCerrada cuentaCerrada = (CuentaCerrada) consultaUltimosMovimientos.getCuenta();
			Date fechaCierre;
			try {
				SimpleDateFormat sfCerrada = new SimpleDateFormat("yyyy-MM-dd");
				// Busco la FechaCierre de la cuenta.
				fechaCierre = sfCerrada.parse(cuentaCerrada.getFechaBajaContrato());
			} catch (ParseException e) {
				throw new BusinessException(e);
			}
			setearFechasDefaultCuentaCerrada(consultaUltimosMovimientos, new Date(), fechaCierre);
		} else {
			setearFechasParaIntervalos(consultaUltimosMovimientos);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO#
	 * obtenerMensajeInformativo(java.lang.String, java.lang.String)
	 */
	@Override
	public String obtenerMensajeInformativo(String nroSucursal, String nroCuenta) {
		String mensajeInformativo;
		try {
			Mensaje mensaje = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_INFORMATIVO_DETALLE_MOVIMIENTOS);
			String nroSucursalProducto = ISBANStringUtils.formatearSucursal(nroSucursal);
			String nroCuentaProducto = ISBANStringUtils.formatearNumeroCuenta(nroCuenta);

			mensajeInformativo = MessageFormat.format(mensaje.getMensaje(), nroSucursalProducto, nroCuentaProducto);
		} catch (RuntimeException e) {
			mensajeInformativo = null;
		}
		return mensajeInformativo;
	}
	
	private void formatearMontos (MovimientosExcel movimientosExcel) {
		
		for (MovimientoExcelItem movimiento : movimientosExcel.getMovimientos()) {
			movimiento.setImporteString(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(movimiento.getImporte().toString())));
			movimiento.setSaldoString(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(movimiento.getSaldo().toString())));
		}
		
	}	

	/**
	 * Filtrar por moneda.
	 *
	 * @param moneda
	 *            the moneda
	 * @param filtrado
	 *            the filtrado
	 * @return the list
	 */
	private List<Movimiento> filtrarPorMoneda(final DivisaEnum moneda, List<Movimiento> filtrado) {
		List<Movimiento> newFiltrado = new ArrayList<Movimiento>(filtrado);
		if (moneda != null) {
			CollectionUtils.filter(newFiltrado, new DivisaPredicate(moneda));
		} else {
			CollectionUtils.filter(newFiltrado, new DivisaPredicate(DivisaEnum.PESO));
		}
		return newFiltrado;
	}

	/**
	 * Filtrar por palabra clave.
	 *
	 * @param palabraClave
	 *            the palabra clave
	 * @param filtrado
	 *            the filtrado
	 * @return the list
	 */
	private List<Movimiento> filtrarPorPalabraClave(String palabraClave, List<Movimiento> filtrado) {

		List<Movimiento> detalleMovimientoFiltradoList = new ArrayList<Movimiento>(filtrado);
		// Filtra objeto dependiendo la palabra clave.
		if (StringUtils.isNotBlank(palabraClave)) {
			CollectionUtils.filter(detalleMovimientoFiltradoList, new PalabraClavePredicate(palabraClave));
		}
		return detalleMovimientoFiltradoList;
	}

	/**
	 * Filtrar por importe.
	 *
	 * @param rangoImporte
	 *            the rango importe
	 * @param filtrado
	 *            the filtrado
	 * @return the list
	 */
	private List<Movimiento> filtrarPorImporte(RangoImporte rangoImporte, List<Movimiento> filtrado) {
		List<Movimiento> detalleMovimientoFiltradoList = new ArrayList<Movimiento>(filtrado);

		if (rangoImporte.getImporteDesde() != null && rangoImporte.getImporteHasta() != null) {
			CollectionUtils.filter(detalleMovimientoFiltradoList, new RangoImportePredicate(rangoImporte));
		}
		return detalleMovimientoFiltradoList;
	}

	/**
	 * Filtrar por tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param filtrado
	 *            the filtrado
	 * @return the list
	 */
	private List<Movimiento> filtrarPorTipoCuenta(TipoCuenta tipoCuenta, List<Movimiento> filtrado) {

		List<Movimiento> detalleMovimientoFiltradoList = new ArrayList<Movimiento>(filtrado);

		if (tipoCuenta != null) {
			CollectionUtils.filter(detalleMovimientoFiltradoList, new TipoCuentaPredicate(tipoCuenta));
		}
		return detalleMovimientoFiltradoList;
	}

	/**
	 * Obtener ultimos movimientos excel.
	 *
	 * @param filtroMovimientos
	 *            the filtro movimientos
	 * @param filtroSession
	 *            the filtro session
	 * @return the respuesta
	 */
	private Respuesta<DetalleUltimosMovimientos> obtenerUltimosMovimientosExcel(ConsultaUltimosMovimientos filtroMovimientos,
	           ConsultaUltimosMovimientos filtroSession) {

		try {
			setearFechasParaIntervalos(filtroMovimientos);
			Respuesta<UltimosMovimientosDTO> respuestaUltimosMovimientos = movimientosBO
					.obtenerMovimientosSinCache(filtroMovimientos);

			if (!EstadoRespuesta.OK.equals(respuestaUltimosMovimientos.getEstadoRespuesta())
					|| CollectionUtils.isEmpty(respuestaUltimosMovimientos.getRespuesta().getMovimientos())) {
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}

			/** Ver si tengo movimientos segun los filtros. */
			List<Movimiento> movimientoFiltrados = aplicarFiltros(respuestaUltimosMovimientos.getRespuesta(),
					filtroMovimientos, filtroSession);
			if (movimientoFiltrados.isEmpty()) {
				/** Crear respusta vacia */
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}
			DetalleUltimosMovimientos detalleUltimosMovimientos = cargarListaMovimientos(filtroMovimientos,
					movimientoFiltrados);
			Respuesta<DetalleUltimosMovimientos> respuesta = Respuesta.copy(DetalleUltimosMovimientos.class,
					respuestaUltimosMovimientos);
			respuesta.setRespuesta(detalleUltimosMovimientos);
			return respuesta;
		} catch (ParseException e) {
			LOGGER.error("Error al parsear movimientos {}", filtroMovimientos, e);
			// Crear respueta con error generico
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		} catch (Exception e) {
			LOGGER.error("Error general al obtener los movimientos {}", filtroMovimientos, e);
			// Crear respueta con error generico
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

	}

}
