/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.CuotasPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximaCuotaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.CuotasPrestamoManager;

/**
 * The Class CuotasPrestamoManagerImpl.
 */
@Component
public class CuotasPrestamoManagerImpl implements CuotasPrestamoManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuotasPrestamoManagerImpl.class);

	/** The Constant MENSAJE_ERROR_ESTADISTICAS. */
	private static final String MENSAJE_ERROR_ESTADISTICAS = "Se intenta grabar una estadistica no valida";

	/** The Constant MENSAJE_ERROR_CARGA_CUOTAS_PRESTAMO_CANCELADO. */
	private static final String MENSAJE_ERROR_CARGA_CUOTAS_PRESTAMO_CANCELADO = "1155";

	/** The Constant MENSAJE_ERROR_CARGA_CABECERA_CUOTAS_PRESTAMO_CANCELADO. */
	private static final String MENSAJE_ERROR_CARGA_CABECERA_CUOTAS_PRESTAMO_CANCELADO = "1154";
	
	/** The Constant LEGAL_SEGURO_VIDA. */
	private static final String LEGAL_SEGURO_VIDA = "1005";
	
	/** The Constant LEGAL_IMPUESTOS. */
	private static final String LEGAL_IMPUESTOS = "15022";

	/** The Constant WARNING_CABECERA. */
	private static final String WARNING_CABECERA = "WARNING_CABECERA";

	/** The Constant WARNING_GRILLA. */
	private static final String WARNING_GRILLA = "WARNING_GRILLA";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The Constant PERSONAL. */
	private static final String PERSONAL = "Super Préstamo Personal";

	/** The Constant HIPOTECARIO. */
	private static final String HIPOTECARIO = "Super Préstamo Hipotecario";
	
	private static final String MENSAJE_ERROR_EXCEL = "Ocurrió un error y no se pudo realizar la descarga. Por favor volvé a intentar";

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The prestamo BO. */
	@Autowired
	private PrestamoBO prestamoBO;

	/** The cuotas prestamo BO. */
	@Autowired
	private CuotasPrestamoBO cuotasPrestamoBO;

	/** The preformalizacion BO. */
	@Autowired
	private PreFormalizacionPrestamoBO preformalizacionBO;
	
	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;
	
	@Autowired
    private SesionParametros sesionParametros;

	/**
	 * The Constant ACCESO_PROXIMAS_CUOTAS_MAP. Indica el codigo de estadistica
	 * a grabar para el acceso a proximas cuotas.
	 */
	private static final Map<TipoPrestamoEnum, String> ACCESO_PROXIMAS_CUOTAS_MAP = new HashMap<TipoPrestamoEnum, String>();
	static {
		ACCESO_PROXIMAS_CUOTAS_MAP.put(TipoPrestamoEnum.PERSONAL, "13147");
		ACCESO_PROXIMAS_CUOTAS_MAP.put(TipoPrestamoEnum.HIPOTECARIOS, "13148");
		ACCESO_PROXIMAS_CUOTAS_MAP.put(TipoPrestamoEnum.PRENDARIO, "13149");
	}

	/**
	 * The Constant ACCESO_DETALLE_PROXIMA_CUOTA_MAP. Indica el codigo de
	 * estadistica a grabar para el acceso a detalle proxima cuota.
	 */
	private static final Map<TipoPrestamoEnum, String> ACCESO_DETALLE_PROXIMA_CUOTA_MAP = new HashMap<TipoPrestamoEnum, String>();
	static {
		ACCESO_DETALLE_PROXIMA_CUOTA_MAP.put(TipoPrestamoEnum.PERSONAL, "13360");
		ACCESO_DETALLE_PROXIMA_CUOTA_MAP.put(TipoPrestamoEnum.HIPOTECARIOS, "13361");
		ACCESO_DETALLE_PROXIMA_CUOTA_MAP.put(TipoPrestamoEnum.PRENDARIO, "13362");
	}

	/**
	 * The Constant ACCESO_CUOTAS_PAGAS_MAP. Indica el codigo de estadistica a
	 * grabar para el acceso a cuotas pagas.
	 */
	private static final Map<TipoPrestamoEnum, String> ACCESO_CUOTAS_PAGAS_MAP = new HashMap<TipoPrestamoEnum, String>();
	static {
		ACCESO_CUOTAS_PAGAS_MAP.put(TipoPrestamoEnum.PERSONAL, "13144");
		ACCESO_CUOTAS_PAGAS_MAP.put(TipoPrestamoEnum.HIPOTECARIOS, "13145");
		ACCESO_CUOTAS_PAGAS_MAP.put(TipoPrestamoEnum.PRENDARIO, "13146");
	}

	/**
	 * The Constant ACCESO_DETALLE_CUOTA_PAGA_MAP. Indica el codigo de
	 * estadistica a grabar para el acceso a detalle cuota paga.
	 */
	private static final Map<TipoPrestamoEnum, String> ACCESO_DETALLE_CUOTA_PAGA_MAP = new HashMap<TipoPrestamoEnum, String>();
	static {
		ACCESO_DETALLE_CUOTA_PAGA_MAP.put(TipoPrestamoEnum.PERSONAL, "13357");
		ACCESO_DETALLE_CUOTA_PAGA_MAP.put(TipoPrestamoEnum.HIPOTECARIOS, "13358");
		ACCESO_DETALLE_CUOTA_PAGA_MAP.put(TipoPrestamoEnum.PRENDARIO, "13359");
	}

	/** The Constant ACCESO_CUOTAS_DE_CANCELADO. */
	private static final String ACCESO_CUOTAS_DE_CANCELADO = "13173";

	/** The Constant ACCESO_DETALLE_CUOTA_DE_CANCELADO. */
	private static final String ACCESO_DETALLE_CUOTA_DE_CANCELADO = "13366";

	/**
	 * The Constant ACCESO_DETALLE_PRESTAMO. Indica el codigo de estadistica a
	 * grabar para el acceso a detalle de prestamo.
	 */
	private static final Map<TipoPrestamoEnum, String> ACCESO_DETALLE_PRESTAMO = new HashMap<TipoPrestamoEnum, String>();
	static {
		ACCESO_DETALLE_PRESTAMO.put(TipoPrestamoEnum.PERSONAL, "13363");
		ACCESO_DETALLE_PRESTAMO.put(TipoPrestamoEnum.HIPOTECARIOS, "13364");
		ACCESO_DETALLE_PRESTAMO.put(TipoPrestamoEnum.PRENDARIO, "13365");
	}

	/**
	 * Obtener proximas cuotas.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * CuotasPrestamoManager#obtenerProximasCuotas(ar.com.santanderrio.obp.
	 * servicios.pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ProximasCuotasView> obtenerProximasCuotas(ConsultaPrestamo consultaPrestamo) {
		LOGGER.info("Entro al manager para cargar los datos de la grilla de proximas cuotas");
		sesionParametros.setProximasCuotasView(null);
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaPrestamo = cliente.obtenerPrestamoPorID(consultaPrestamo.getNumeroPrestamo());
		PreFormalizacion preFormalizacion = preformalizacionBO.obtenerPreFormalizacion(sesionCliente.getCliente(),
				cuentaPrestamo.getNroSucursal(), cuentaPrestamo.getNroCuentaProducto());
		CuentaView cuentaDebitoView = null;
		if (preFormalizacion != null) {
			Cuenta cuentaDebito = sesionCliente.getCliente()
					.getCuentaSiContieneNumero(preFormalizacion.getPrestamoDebitoAdherido().getNumero());
			cuentaDebitoView = ProximaCuotaView.obtenerCuentaDebito(cuentaDebito,
					preFormalizacion.getPrestamoDebitoAdherido().getNroSucursal(),
					preFormalizacion.getPrestamoDebitoAdherido().getNumero());
		}
		Prestamo prestamo = obtenerPrestamo(consultaPrestamo, cliente);

		DeudaPrestamoEntity deudaPrestamoEntity;
		try {
			deudaPrestamoEntity = cuotasPrestamoBO.consultarProximasCuotas(cliente, cuentaPrestamo);
		} catch (BusinessException e) {
			LOGGER.error("Error en la validacion de la respuesta del servicio.", e);
			return respuestaFactory.crearRespuestaError(ProximasCuotasView.class, "", TipoError.ERROR_GENERICO, "1105");
		}
		List<CuotaPrestamoEntity> listaFiltrada = filtrarLista(deudaPrestamoEntity.getListaRepeticiones());

		ProximasCuotasView proximasCuotasView = new ProximasCuotasView(cuentaPrestamo, prestamo, consultaPrestamo);

		if ("-".equals(proximasCuotasView.getCuotasPagadas()) && !listaFiltrada.isEmpty()) {
			String cantidadCuotasPagas = String.valueOf(Integer.parseInt(listaFiltrada.get(0).getNumrecRec()) - 1);
			proximasCuotasView.setCuotasPagadas(cantidadCuotasPagas);
		}

		TipoPrestamoEnum tipoPrestamo = TipoPrestamoEnum.fromIdString(cuentaPrestamo.getClaseCuenta());
		proximasCuotasView.setProximasCuotas(listaFiltrada, tipoPrestamo, prestamo, consultaPrestamo, cuentaDebitoView);
		proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.LEGAL_PRESTAMO_IMPORTE_CUOTA));
		proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.LEGAL_PRESTAMO_SEGURO_DE_VIDA_1));
		/*if(consultaPrestamo.getIsUva()) {
			proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_3).replace("(4)", ""));      
    	}else {*/
    		proximasCuotasView.getLegales().add(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_4));
    	/*}*/
		proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_2));


		Respuesta<ProximasCuotasView> respuesta;
		if (listaFiltrada.isEmpty()) {
			respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView,
					WARNING_GRILLA, CodigoMensajeConstantes.ERROR_OBTENER_PROXIMAS_CUOTAS_PRESTAMO, null);
		} else if (prestamo == null || GUION.equals(consultaPrestamo.getMontoPrestamo())
				|| GUION.equals(consultaPrestamo.getPlazo())) {
			respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView,
					WARNING_CABECERA, CodigoMensajeConstantes.CODIGO_ERROR_OBTENER_CUENTAS_PRESTAMO, null);
		} else {
			respuesta = respuestaFactory.crearRespuestaOk(proximasCuotasView);
			sesionParametros.setProximasCuotasView(proximasCuotasView);
		}

		if (ACCESO_PROXIMAS_CUOTAS_MAP.containsKey(tipoPrestamo)) {
			estadisticaManager.add(ACCESO_PROXIMAS_CUOTAS_MAP.get(tipoPrestamo),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			LOGGER.debug(MENSAJE_ERROR_ESTADISTICAS);
		}

		return respuesta;
	}

	/**
	 * Obtener prestamo.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @param cliente
	 *            the cliente
	 * @return the prestamo
	 */
	//TODO: ADD NEW CALL TO PRESTAMODTO THAT GETS PREFORMALIZACION AND PRESTAMO DETAILS AT THE SAME TIME
	private Prestamo obtenerPrestamo(ConsultaPrestamo consultaPrestamo, Cliente cliente) {
		try {
			PrestamoDTO prestamoDTO = prestamoBO.obtenerDetallePrestamo(consultaPrestamo.getNumeroPrestamo(), cliente);
			return prestamoDTO != null ? prestamoDTO.getPrestamo() : null;
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Grabar estadistica detalle proxima cuota.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * CuotasPrestamoManager#grabarEstadisticaDetalleProximaCuota(ar.com.
	 * santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetalleProximaCuota(ConsultaPrestamo consultaPrestamo) {
		LOGGER.info("Entro al manager para grabar la estadistica de detalle de proxima cuota");
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaPrestamo = cliente.obtenerPrestamoPorID(consultaPrestamo.getNumeroPrestamo());
		TipoPrestamoEnum tipoPrestamo = TipoPrestamoEnum.fromIdString(cuentaPrestamo.getClaseCuenta());

		if (ACCESO_DETALLE_PROXIMA_CUOTA_MAP.containsKey(tipoPrestamo)) {
			estadisticaManager.add(ACCESO_DETALLE_PROXIMA_CUOTA_MAP.get(tipoPrestamo),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			LOGGER.debug(MENSAJE_ERROR_ESTADISTICAS);
		}

		return null;
	}

	/**
	 * Obtener cuotas pagas.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ProximasCuotasView> obtenerCuotasPagas(ConsultaPrestamo consultaPrestamo) {
		LOGGER.info("Entro al manager para cargar los datos de la grilla de cuotas pagas");
		sesionParametros.setProximasCuotasView(null);
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaPrestamo = (Cuenta) cuentaBO.buscarCuentaPorId(cliente, new IdentificacionCuenta(consultaPrestamo.getNumeroPrestamo()));
		PreFormalizacion preFormalizacion = preformalizacionBO.obtenerPreFormalizacion(sesionCliente.getCliente(),
				cuentaPrestamo.getNroSucursal(), cuentaPrestamo.getNroCuentaProducto());
		CuentaView cuentaDebitoView = null;
		if (preFormalizacion != null) {
			Cuenta cuentaDebito = sesionCliente.getCliente()
					.getCuentaSiContieneNumero(preFormalizacion.getPrestamoDebitoAdherido().getNumero());
			cuentaDebitoView = ProximaCuotaView.obtenerCuentaDebito(cuentaDebito,
					preFormalizacion.getPrestamoDebitoAdherido().getNroSucursal(),
					preFormalizacion.getPrestamoDebitoAdherido().getNumero());
		}
		Respuesta<ProximasCuotasView> respuesta;
		ProximasCuotasView proximasCuotasView = new ProximasCuotasView(cuentaPrestamo, consultaPrestamo);
		TipoPrestamoEnum tipoPrestamo = TipoPrestamoEnum.fromIdString(cuentaPrestamo.getClaseCuenta());
		ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = null;
		proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.LEGAL_PRESTAMO_IMPORTE_CUOTA));
		proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.LEGAL_PRESTAMO_SEGURO_DE_VIDA_2));
		/*if(consultaPrestamo.getIsUva()) {
			proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_3).replace("(4)", ""));
    	}else {*/
    		proximasCuotasView.getLegales().add(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_4));
		/*}*/

		try {
			Interviniente interviniente = cuotasPrestamoBO.consultarIntervinientePrestamo(cuentaPrestamo.getCliente(),
					cuentaPrestamo);
			consultaCuotaPagaOutEntity = cuotasPrestamoBO.consultarCuotasPagas(cliente, cuentaPrestamo,
					preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio());
			proximasCuotasView.setCuotasPagas(consultaCuotaPagaOutEntity, tipoPrestamo, interviniente,
					cuentaDebitoView);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (SinCuotasPagasException s) {
			return respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView,
					TipoError.PRESTAMO_SIN_CUOTAS_PAGAS.getDescripcion(), CodigoMensajeConstantes.CODIGO_SIN_CUOTAS_PAGAS_PRESTAMO, null);
		}

		if (consultaCuotaPagaOutEntity == null) {
			respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView,
					WARNING_GRILLA, CodigoMensajeConstantes.PRESTAMO_CUOTAS_PAGAS_ERROR_GRILLA, null);
		} else {
			respuesta = respuestaFactory.crearRespuestaOk(proximasCuotasView);
			sesionParametros.setProximasCuotasView(proximasCuotasView);
		}

		if (ACCESO_CUOTAS_PAGAS_MAP.containsKey(tipoPrestamo)) {
			estadisticaManager.add(ACCESO_CUOTAS_PAGAS_MAP.get(tipoPrestamo),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			LOGGER.debug(MENSAJE_ERROR_ESTADISTICAS);
		}

		return respuesta;
	}

	/**
	 * Obtener legales. Conulta los legales y los busca por el codigo.
	 *
	 * @param codLegal
	 *            the cod legal
	 * @return the string
	 */
	private String obtenerLegales(String codLegal) {
		return legalBO.obtenerLegalesPorCodigo(codLegal);
	}

	/**
	 * Grabar estadistica detalle cuota paga.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * CuotasPrestamoManager#grabarEstadisticaDetalleCuotaPaga(ar.com.
	 * santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetalleCuotaPaga(ConsultaPrestamo consultaPrestamo) {
		LOGGER.info("Entro al manager para grabar la estadistica de detalle de cuota paga");
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaPrestamo = cliente.obtenerPrestamoPorID(consultaPrestamo.getNumeroPrestamo());
		TipoPrestamoEnum tipoPrestamo = TipoPrestamoEnum.fromIdString(cuentaPrestamo.getClaseCuenta());

		if (ACCESO_DETALLE_CUOTA_PAGA_MAP.containsKey(tipoPrestamo)) {
			estadisticaManager.add(ACCESO_DETALLE_CUOTA_PAGA_MAP.get(tipoPrestamo),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			LOGGER.debug(MENSAJE_ERROR_ESTADISTICAS);
		}

		return null;
	}

	/**
	 * Obtener cuotas de cancelado.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * CuotasPrestamoManager#obtenerCuotasDeCancelado(ar.com.santanderrio.obp.
	 * servicios.pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ProximasCuotasView> obtenerCuotasDeCancelado(ConsultaPrestamo consultaPrestamo) {
		LOGGER.info("Entro al manager para cargar los datos de la grilla de cuotas de prestamo cancelado");

		TipoPrestamoEnum tipoPrestamo = PERSONAL.equals(consultaPrestamo.getTipoPrestamo()) ? TipoPrestamoEnum.PERSONAL
				: HIPOTECARIO.equals(consultaPrestamo.getTipoPrestamo()) ? TipoPrestamoEnum.HIPOTECARIOS
						: TipoPrestamoEnum.PRENDARIO;

//		Prestamo prestamo = new Prestamo();
//		prestamo.setIndex("    ");
//		prestamo.setNroExp("    ");
//		prestamo.setTipoPrestamoEnum(tipoPrestamo);
		Cuenta cuentaPrestamo = new Cuenta();
		String numeroCuenta = consultaPrestamo.getNumeroPrestamo();
		cuentaPrestamo.setNroSucursal(numeroCuenta.substring(0, 4));
		cuentaPrestamo.setNroCuentaProducto(numeroCuenta.substring(5, numeroCuenta.length()).replace("/", ""));

		PreFormalizacion preFormalizacion = preformalizacionBO.obtenerPreFormalizacion(sesionCliente.getCliente(),
				cuentaPrestamo.getNroSucursal(), cuentaPrestamo.getNroCuentaProducto());

		CuentaView cuentaDebitoView = null;
		if (preFormalizacion != null) {
			Cuenta cuentaDebito = sesionCliente.getCliente()
					.getCuentaSiContieneNumero(preFormalizacion.getPrestamoDebitoAdherido().getNumero());
			cuentaDebitoView = ProximaCuotaView.obtenerCuentaDebito(cuentaDebito,
					preFormalizacion.getPrestamoDebitoAdherido().getNroSucursal(),
					preFormalizacion.getPrestamoDebitoAdherido().getNumero());
		}

		String dia = consultaPrestamo.getFechaInicioPrestamo().substring(0, 2);
		String mes = consultaPrestamo.getFechaInicioPrestamo().substring(3, 5);
		String anio = consultaPrestamo.getFechaInicioPrestamo().substring(6,
				consultaPrestamo.getFechaInicioPrestamo().length());
		String fechaInicio = anio + "-" + mes + "-" + dia;

		ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = null;
		ProximasCuotasView proximasCuotasView = new ProximasCuotasView();
		proximasCuotasView.setNumero(consultaPrestamo.getNumeroPrestamo());
		proximasCuotasView.setTitulo(
				consultaPrestamo.getTipoPrestamo().substring(6, consultaPrestamo.getTipoPrestamo().length()));
		try {
			consultaCuotaPagaOutEntity = cuotasPrestamoBO.consultarCuotasPagas(sesionCliente.getCliente(),
					cuentaPrestamo, fechaInicio);
			Interviniente interviniente;
			if (TipoPrestamoEnum.PRENDARIO == tipoPrestamo) {
				interviniente = cuotasPrestamoBO.consultarIntervinientePrestamo(sesionCliente.getCliente(),
						cuentaPrestamo);
			} else {
				interviniente = new Interviniente();
			}

			proximasCuotasView.setCuotasPagadas(consultaCuotaPagaOutEntity.getCantidadOcurrencias().toString());
			proximasCuotasView.setCuotasPagas(consultaCuotaPagaOutEntity, tipoPrestamo, interviniente,
					cuentaDebitoView);
			proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.LEGAL_PRESTAMO_IMPORTE_CUOTA));
			proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.LEGAL_PRESTAMO_SEGURO_DE_VIDA_2));
			/*if(TipoPrestamoEnum.UVA.equals(tipoPrestamo)) {
				proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_3).replace("(4)", ""));
	    	}else {*/
	    		proximasCuotasView.getLegales().add(obtenerLegales(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_3)); 
			/*}*/

		} catch (BusinessException e) {
			LOGGER.error("Falla en carga de cuotas pagadas de prestamo cancelado.", e);
			estadisticaManager.add(ACCESO_CUOTAS_DE_CANCELADO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView, WARNING_GRILLA,
					MENSAJE_ERROR_CARGA_CUOTAS_PRESTAMO_CANCELADO, null);

		} catch (SinCuotasPagasException e) {
			LOGGER.error("Falla en carga de cuotas pagadas de prestamo cancelado.", e);
			estadisticaManager.add(ACCESO_CUOTAS_DE_CANCELADO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView, WARNING_GRILLA,
					MENSAJE_ERROR_CARGA_CUOTAS_PRESTAMO_CANCELADO, null);
		}
		Respuesta<ProximasCuotasView> respuesta;
		if (consultaPrestamo.getFalloPlazoImporte()) {
			estadisticaManager.add(ACCESO_CUOTAS_DE_CANCELADO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(proximasCuotasView,
					WARNING_CABECERA, MENSAJE_ERROR_CARGA_CABECERA_CUOTAS_PRESTAMO_CANCELADO, null);
		} else {
			estadisticaManager.add(ACCESO_CUOTAS_DE_CANCELADO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = respuestaFactory.crearRespuestaOk(proximasCuotasView);
		}
		return respuesta;
	}

	/**
	 * Grabar estadistica detalle cuota de cancelado.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * CuotasPrestamoManager#grabarEstadisticaDetalleCuotaDeCancelado()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetalleCuotaDeCancelado() {
		LOGGER.info("Entro al manager para grabar la estadistica de detalle de cuota de prestamo cancelado");
		estadisticaManager.add(ACCESO_DETALLE_CUOTA_DE_CANCELADO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return null;
	}

	/**
	 * Obtener detalle.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * CuotasPrestamoManager#obtenerDetalle(ar.com.santanderrio.obp.servicios.
	 * pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ProximasCuotasView> obtenerDetalle(ConsultaPrestamo consultaPrestamo) {
		LOGGER.info("Entro al manager para grabar los datos de detalle de prestamo");
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaPrestamo = cliente.obtenerPrestamoPorID(consultaPrestamo.getNumeroPrestamo());
		TipoPrestamoEnum tipoPrestamo = TipoPrestamoEnum.fromIdString(cuentaPrestamo.getClaseCuenta());

		if (ACCESO_DETALLE_PRESTAMO.containsKey(tipoPrestamo)) {
			estadisticaManager.add(ACCESO_DETALLE_PRESTAMO.get(tipoPrestamo),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			LOGGER.debug(MENSAJE_ERROR_ESTADISTICAS);
		}

		return null;
	}
	
    @Override
    public Respuesta<ReporteView> exportarCuotasPagas() {
        LOGGER.info("Entro al manager para descargar el excel de cuotas pagas");
        ProximasCuotasView cuotasPagas = sesionParametros.getProximasCuotasView();
        Cliente cliente = sesionCliente.getCliente();
        Respuesta<Reporte> reporte = cuotasPrestamoBO.exportarCuotasPagas(cliente, cuotasPagas);
        return reporteToView(reporte);
    }

    @Override
    public Respuesta<ReporteView> exportarProximasCuotas() {
        LOGGER.info("Entro al manager para descargar el excel de proximas cuotas");
        ProximasCuotasView proximasCuotas = sesionParametros.getProximasCuotasView();
        Cliente cliente = sesionCliente.getCliente();
        Respuesta<Reporte> reporte = cuotasPrestamoBO.exportarProximasCuotas(cliente, proximasCuotas);
        return reporteToView(reporte);
    }

    private Respuesta<ReporteView> reporteToView(Respuesta<Reporte> reporte) {
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            return respuestaFactory.crearRespuestaOk(reporteView);
        }
        return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(MENSAJE_ERROR_EXCEL,
                TipoError.ERROR_DESCARGA_EXCEL.getDescripcion());
    }
    
	/**
	 * Filtrar lista.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<CuotaPrestamoEntity> filtrarLista(List<CuotaPrestamoEntity> lista) {
		int tamanio = 5 < lista.size() ? 5 : lista.size();
		return lista.subList(0, tamanio);
	}

}
