/*
 * 
 */

package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPreaprobadoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CalificacionCrediticiaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.*;
import ar.com.santanderrio.obp.servicios.prestamos.sei.RangoCuota;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LimitePrestamoPreaprobadoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LimitesPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * The Class SimuladorPrestamoBOImpl.
 */
@Component
public class SimuladorPrestamoBOImpl implements SimuladorPrestamoBO {

	private static final String TASA_MONOPRODUCTO = "M";

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	private static final String PORCENTAJE = "%";

	private static final String SI = "S";

	private static final String NO = "N";

	/** The Constant LINEA_CREDITICIA_HABILITADA. */
	public static final String LINEA_CREDITICIA_HABILITADA = "001";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimuladorPrestamoBOImpl.class);

	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The Constant IND_LINEA_UVA. */
	public static final String IND_LINEA_UVA = SI;
	
	/** The Constant TASA_FIJA. */
	public static final String TIPO_TASA_FIJA = "F";
	
	/** The Constant TASA_VARIABLE. */
	public static final String TIPO_TASA_VARIABLE = "V";
	
	/** The Constant TASA_UVA. */
	public static final String TIPO_TASA_UVA = "U";
	
	/** The Constant TASA_UVA. */
	public static final String TASA_FIJA = "Fija";
	
	/** The Constant TASA_UVA. */
	public static final String TASA_VARIABLE = "Variable";
	
	private static final String SIMULAR = "S";
	
	private static final String ENCOLAR = "1";

	private static final String LIQUIDAR = "L";
	
	private static final String LIQUIDAR_ENCOLADO = "3";
	
	/** The cantidad minima dias. */
	@Value("${PRESTAMOS.CANTIDADMINIMADIAS}")
	private Integer cantidadMinimaDias;

	/** The cantidad maxima dias. */
	@Value("${PRESTAMOS.CANTIDADMAXIMADIAS}")
	private Integer cantidadMaximaDias;
	
	/** The cantidad maxima dias preaprobados. */
	@Value("${PRESTAMOS.CANTIDADMAXIMADIASSUBPROD47}")
	private Integer cantidadMaximaDiasSubprod47;

	/** The hora inicio operaciones. */
	@Value("${CREDITOS.HORAINICIOOPERACIONES}")
	private String horaInicioOperaciones;

	/** The hora fin operaciones. */
	@Value("${CREDITOS.HORAFINOPERACIONES}")
	private String horaFinOperaciones;

	/** The calificacion crediticia DAO. */
	@SuppressWarnings("unused")
	@Autowired
	private CalificacionCrediticiaDAO calificacionCrediticiaDAO;

	/** The prestamo bo. */
	@SuppressWarnings("unused")
	@Autowired
	private PrestamoBO prestamoBo;
	
	/** The prestamo bo. */
	@Autowired
	private PrestamoPreaprobadoBO prestamoPreaprobadoBO;

	/** The destino prestamo DAO. */
	@Autowired
	private DestinoPrestamoDAO destinoPrestamoDAO;

	/** The prestamo DAO. */
	@Autowired
	private PrestamoDAO prestamoDAO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

	@Autowired
	PrestamoManagerBO prestamoTokenBO;
    
	/**
	 * Gets the cantidad minima dias.
	 *
	 * @return the cantidad minima dias
	 */
	public Integer getCantidadMinimaDias() {
		return cantidadMinimaDias;
	}

	/**
	 * Sets the cantidad minima dias.
	 *
	 * @param cantidadMinimaDias
	 *            the new cantidad minima dias
	 */
	public void setCantidadMinimaDias(Integer cantidadMinimaDias) {
		this.cantidadMinimaDias = cantidadMinimaDias;
	}

	/**
	 * Gets the cantidad maxima dias.
	 *
	 * @return the cantidad maxima dias
	 */
	public Integer getCantidadMaximaDias() {
		return cantidadMaximaDias;
	}

	/**
	 * Sets the cantidad maxima dias.
	 *
	 * @param cantidadMaximaDias
	 *            the new cantidad maxima dias
	 */
	public void setCantidadMaximaDias(Integer cantidadMaximaDias) {
		this.cantidadMaximaDias = cantidadMaximaDias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO#
	 * obtenerConfiguracionSimulacionPrestamo(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public ConfiguracionPrestamoDTO obtenerConfiguracionSimulacionPrestamo(Cliente cliente, Cuenta cuenta)
			throws BusinessException {

			@SuppressWarnings("unused")
			String simulacionServicio = "C";
			//Aca debo recuprar la info del BO del cliente, cuenta
			///PrestamoPermitidoOutEntity prestamoPermitido = prestamoBo.obtenerInfoPrestamosPermitidos(cliente, cuenta);
			PrestamoPermitidoOutEntity prestamoPermitido = null; 
			List<PrestamosPorCuenta> prestamosPorCuenta = sesionParametros.getPrestamosPorCuenta();
			 for (PrestamosPorCuenta prestamo : prestamosPorCuenta) {
				 if (prestamo.getCuenta().equals(cuenta)) {
					  prestamoPermitido = prestamo.getPrestamos();
				 }
			 }
			
			
			List<RangoCuota> rangosDeCuotas = setearRangosDeCuotas(prestamoPermitido);
			
			ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(obtenerCalificacion(cuenta, sesionParametros.getCalificacionesCrediticias()),
							prestamoPermitido, rangosDeCuotas);

			if (!LINEA_CREDITICIA_HABILITADA
					.equals(configuracionPrestamoDto.getCalificacionCrediticia().getCodigoInhabilitado())) {
				throw new BusinessException(CodigoMensajeConstantes.PRESTAMO_INHABILITADO);
			}
			
			List<LimitesPrestamoView> limites = setearLimitesPrestamos(rangosDeCuotas, 
					configuracionPrestamoDto.getCalificacionCrediticia().getPorcentajeLimitePrestamo(),
					configuracionPrestamoDto.getCalificacionCrediticia().getImporteDisponiblePrestamo());
			configuracionPrestamoDto.setLimites(limites);
			
			DateTime fecha = new DateTime();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha.toDate());
			int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			DateTime fechaInicio = fecha.plusDays(daysInMonth);
			DateTime fechaFinal = fecha.plusDays(cantidadMaximaDias);

			configuracionPrestamoDto.setFechaInicioPagoMin(fechaInicio.toString(DD_MM_YYYY));
			configuracionPrestamoDto.setFechaInicioPagoMax(fechaFinal.toString(DD_MM_YYYY));
			
			if (!configuracionPrestamoDto.lineaCrediticiaSinMontoPermitido()) {
				configuracionPrestamoDto.setMensajeInhabilitado(
						mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO)
								.getMensaje());
				estadisticaManager.add(EstadisticasConstants.LINEA_CREDITICIA_NO_DISPONIBLE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}

			return configuracionPrestamoDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO#
	 * obtenerDestinosPrestamo(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DestinoPrestamoSeleccionView> obtenerDestinosPrestamo(final String producto, final String subProducto)
			throws BusinessException {
		List<DestinoPrestamo> destinosPrestamo;
		try {
			destinosPrestamo = destinoPrestamoDAO.obtener();
		} catch (DAOException e) {
			LOGGER.error("Error al tratar de obtener " + "destinos de prestamos para el producto: " + producto
					+ " y subproducto: " + subProducto, e);
			throw new BusinessException(e.getMessage());
		}

		CollectionUtils.filter(destinosPrestamo, new Predicate() {
			@Override
			public boolean evaluate(Object obj) {
				DestinoPrestamo destino = (DestinoPrestamo) obj;

				return producto.equals(destino.getProductoUG()) && subProducto.equals(destino.getSubproductoUG());
			}
		});

		List<DestinoPrestamoSeleccionView> destinos = new LinkedList<DestinoPrestamoSeleccionView>();
		for (DestinoPrestamo dp : destinosPrestamo) {
			destinos.add(new DestinoPrestamoSeleccionView(dp));
		}

		return destinos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO#
	 * adquirirPrestamo(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente,
	 * ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView,
	 * ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.
	 * ConfiguracionPrestamoDTO)
	 */
	@Override
	public PrestamoOutEntity obtenerPrestamo(Cliente cliente, SolicitudSimulacionView solicitudSimulacion,
											 ConfiguracionPrestamoDTO configuracionPrestamoDto, String fase) throws BusinessException {
		return obtenerPrestamo(fase, cliente, solicitudSimulacion, configuracionPrestamoDto);
	}

	@Override
	public PrestamoInEntity obtenerPrestamoInEntity(Cliente cliente, SolicitudSimulacionView solicitudSimulacion,
			ConfiguracionPrestamoDTO configuracionPrestamoDto) throws BusinessException {

		PrestamoInEntity prestamoInEntity = new PrestamoInEntity(ENCOLAR,
				cliente.getCuenta(solicitudSimulacion.getCbu()), solicitudSimulacion, configuracionPrestamoDto);

		return prestamoInEntity;
	}

	/**
	 * Obtener prestamo.
	 *
	 * @param fase
	 *            the fase
	 * @param cliente
	 *            the cliente
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @param configuracionPrestamoDto
	 *            the configuracion prestamo dto
	 * @return the prestamo out entity
	 * @throws BusinessException
	 *             the business exception
	 */
	private PrestamoOutEntity obtenerPrestamo(String fase, Cliente cliente,
			SolicitudSimulacionView solicitudSimulacion, ConfiguracionPrestamoDTO configuracionPrestamoDto)
			throws BusinessException {

		PrestamoInEntity prestamoInEntity = new PrestamoInEntity(fase,
				cliente.getCuenta(solicitudSimulacion.getCbu()), solicitudSimulacion, configuracionPrestamoDto);

		try {
			PrestamoOutEntity prestamoOutEntity = prestamoDAO.simularAdquirir(cliente, prestamoInEntity);

			return manejarRespuesta(prestamoOutEntity);

		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
				if (IND_LINEA_UVA.equals(prestamoInEntity.getIndLineaUVA())) {
					estadisticaManager.add(EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO_UVA, 
							EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
					throw new BusinessException(CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS_UVA);
				} 
				else {
					throw new BusinessException(CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS);
				}
			}
			throw new BusinessException(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO#chequearRangos
	 * (ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView, 
	 * ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity)
	 */
	@Override
	public List<ItemMensajeRespuesta> chequearRangos(SolicitudSimulacionView solicitudSimulacion,
			PrestamoPermitidoEntity rangoCuota) {
		List<ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();

		BigDecimal minImpPrest = formatearValor(rangoCuota.getMinImpPrest(), 4);
		BigDecimal maxImpPrest = formatearValor(rangoCuota.getMaxImpPrest(), 4);
		if (minImpPrest.compareTo(solicitudSimulacion.importeToBigDecimal()) == (1)) {
			String mensajeError = mensajeBO.obtenerMensajePorCodigo("1348").getMensaje();
			mensajeError = MessageFormat.format(mensajeError, "menor", "mínimo");
			ItemMensajeRespuesta error = new ItemMensajeRespuesta(mensajeError);
			error.setTipoError("ERROR_IMPORTE_FORMULARIO");
			errores.add(error);
		} else if (maxImpPrest.compareTo(solicitudSimulacion.importeToBigDecimal()) == (-1)) {
			String mensajeError = mensajeBO.obtenerMensajePorCodigo("1348").getMensaje();
			mensajeError = MessageFormat.format(mensajeError, "mayor", "máximo");
			ItemMensajeRespuesta error = new ItemMensajeRespuesta(mensajeError);
			error.setTipoError("ERROR_IMPORTE_FORMULARIO");
			errores.add(error);
		}

		if (Integer.valueOf(rangoCuota.getMinCantCuotas()) > Integer
				.valueOf(solicitudSimulacion.getCuotaSeleccionada())
				|| Integer.valueOf(rangoCuota.getMaxCantCuotas()) < Integer
						.valueOf(solicitudSimulacion.getCuotaSeleccionada())) {
			String mensajeError = mensajeBO.obtenerMensajePorCodigo("1349").getMensaje();
			ItemMensajeRespuesta error = new ItemMensajeRespuesta(mensajeError);
			error.setTipoError("ERROR_CUOTAS_FORMULARIO");
			errores.add(error);
		}

		return errores;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO#
	 * chequearSiEstaEnHorarioOperacion()
	 */
	@Override
	public Boolean chequearSiEstaEnHorarioOperacion() {

		DateTimeFormatter df = DateTimeFormat.forPattern("HH:mm");

		String horaInicio = horaInicioOperaciones;
		String horaFin = horaFinOperaciones;

		DateTime horarioBancarioInicio = df.parseLocalTime(horaInicio).toDateTimeToday();
		DateTime horarioBancarioFinal = df.parseLocalTime(horaFin).toDateTimeToday();

		Interval intervaloFueraHorario = new Interval(horarioBancarioInicio, horarioBancarioFinal);
		return intervaloFueraHorario.contains(new DateTime());
	}
	
	
	@Override
	public ConfiguracionPrestamoView obtenerConfiguracionSimulacionPrestamoPreaprobado(Cliente cliente) throws BusinessException {

		ConfiguracionPrestamoView configuracionPrestamo = new ConfiguracionPrestamoView();
		// buscamos los datos de la oferta
		Respuesta<List<PrestamoPermitidoEntity>> respuesta = prestamoPreaprobadoBO.consultarPrestamoPreaprobadoMonoproducto(cliente);

		List<RangoCuota> rangos = new ArrayList<RangoCuota>();
		List<LimitesPrestamoView> limites = new ArrayList<LimitesPrestamoView>();
		List<LimitePrestamoPreaprobadoView> limitesPreaprobado = new ArrayList<LimitePrestamoPreaprobadoView>();
		if(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && !respuesta.getRespuesta().isEmpty()) {
			for (PrestamoPermitidoEntity ofertaPrestamo : respuesta.getRespuesta()) {
				if (ofertaPrestamo.esUVA()) {
					continue;
				}
				RangoCuota rango = new RangoCuota();
				rango.setCantMaxCuotas(Integer.valueOf(ofertaPrestamo.getMaxCantCuotas()).toString());
				rango.setCantMinCuotas(Integer.valueOf(ofertaPrestamo.getMinCantCuotas()).toString());
				BigDecimal maxImpPrest = ISBANStringUtils.stringToBigDecimal(ofertaPrestamo.getMaxImpPrest(), 13, 4, false);
				BigDecimal minImpPrest = ISBANStringUtils.stringToBigDecimal(ofertaPrestamo.getMinImpPrest(), 13, 4, false);
				rango.setImporteMaximo(maxImpPrest);
				rango.setImporteMinimo(minImpPrest);
				rango.setTipoDeTasa(TASA_FIJA);
				BigDecimal tna = ISBANStringUtils.stringToBigDecimal(ofertaPrestamo.getValorTasa(), 3, 6, false);
				rango.setTna(ISBANStringUtils.formatearConComaYDosDecimales(tna.toString()) + PORCENTAJE);
				rango.setDestinosPrestamosSeleccion(obtenerDestinosPrestamo(StringUtils.leftPad(ofertaPrestamo.getCodProductoUg(), 2, "0"), StringUtils.leftPad(ofertaPrestamo.getCodSubpUg(), 4, "0")));
				rango.setIndLineaUva(NO);
				rango.setIndPreaprobado(SI);
				rango.setFechaSolicitudHasta(FechaUtils.obtenerFechaPlusCantDias(cantidadMaximaDias).toString(DD_MM_YYYY));
				rangos.add(rango);
				LimitePrestamoPreaprobadoView limitePreaprobado = new LimitePrestamoPreaprobadoView(null, ofertaPrestamo.getTpoTasa(), rango, new BigDecimal(ofertaPrestamo.getEntidadUg()), new BigDecimal(ofertaPrestamo.getCodProductoUg()), new BigDecimal(ofertaPrestamo.getCodSubpUg()), ofertaPrestamo.getValorTasa());
				limitesPreaprobado.add(limitePreaprobado);
			}
			configuracionPrestamo.setRangoPrestamo(rangos);
			configuracionPrestamo.setLimites(limites);
			LimitesPrestamoView limite = new LimitesPrestamoView();
			int maxIdex = rangos.size()-1;
			limite.setImporteMin(rangos.get(0).getImporteMinimo());
			limite.setImporteMax(rangos.get(maxIdex).getImporteMaximo());
			limite.setCuotaMin(rangos.get(0).getCantMinCuotas());
			limite.setCuotaMax(rangos.get(maxIdex).getCantMaxCuotas());
			limite.setTipoTasa(TASA_MONOPRODUCTO);
			limites.add(limite);
			
			configuracionPrestamo.setFechaInicioPagoMin(FechaUtils.obtenerFechaPlusCantDias(cantidadMinimaDias).toString(DD_MM_YYYY));

			if (respuesta.getRespuesta().get(0).getCodSubpUg().equals("0047")) {
				configuracionPrestamo.setFechaInicioPagoMax(FechaUtils.obtenerFechaPlusCantDias(cantidadMaximaDiasSubprod47).toString(DD_MM_YYYY));
			} else {
				configuracionPrestamo.setFechaInicioPagoMax(FechaUtils.obtenerFechaPlusCantDias(cantidadMaximaDias).toString(DD_MM_YYYY));
			}
			
			// GUARDAMOS LIMITES PARA LA SIMULACION
			sesionParametros.setLimitesPreaprobado(limitesPreaprobado);
			estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_CONSULTA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return configuracionPrestamo;
		}
		estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_CONSULTA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return null;
	}

	/**
	 * Manejar respuesta.
	 *
	 * @param prestamoOutEntity
	 *            the prestamo out entity
	 * @return the prestamo out entity
	 * @throws BusinessException
	 *             the business exception
	 */
	private PrestamoOutEntity manejarRespuesta(PrestamoOutEntity prestamoOutEntity) throws BusinessException {

		if (PrestamoDAOImpl.CODIGO_ERROR_DIA_FUERA_RANGO.equals(prestamoOutEntity.getCodigoRetornoExtendido())) {
			throw new BusinessException(CodigoMensajeConstantes.CODIGO_ERROR_DIA_FUERA_RANGO_MSG);
		}

		if (PrestamoDAOImpl.CODIGO_ERROR_DIA_NO_HABIL.equals(prestamoOutEntity.getCodigoRetornoExtendido())) {
			throw new BusinessException(CodigoMensajeConstantes.CODIGO_ERROR_DIA_NO_HABIL_MSG);
		}

		if (PrestamoDAOImpl.CODIGO_ERROR_CANTIDAD_CUOTAS.equals(prestamoOutEntity.getCodigoRetornoExtendido())) {
			throw new BusinessException(CodigoMensajeConstantes.ERROR_CANTIDAD_CUOTAS_SIMULADOR_PRESTAMOS);
		}

		if (PrestamoDAOImpl.CODIGO_ERROR_OPERACION_INHABILITADA.equals(prestamoOutEntity.getCodigoRetornoExtendido())) {
			throw new BusinessException(CodigoMensajeConstantes.ALTA_PRESTAMO_INHABILITADA);
		}

		if (PrestamoDAOImpl.CODIGO_RETORNO_OK.equals(prestamoOutEntity.getCodigoRetornoExtendido())) {
			return prestamoOutEntity;
		}
		
		if (PrestamoDAOImpl.CODIGO_ERROR_SOLICITUD_PENDIENTE_YA_EXISTE.equals(prestamoOutEntity.getCodigoRetornoExtendido())) {
			throw new BusinessException(CodigoMensajeConstantes.CODIGO_ERROR_SOLICITUD_PENDIENTE_YA_EXISTE_MSG);
		}
		
		if (LIQUIDAR_ENCOLADO.equals(prestamoOutEntity.getFase())){
			throw new BusinessException(CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO);
		}
		
		throw new BusinessException(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
	}

	/**
	 * Setear rangos de cuotas.
	 *
	 * @param prestamoPermitido
	 *            the prestamo permitido
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	private List<RangoCuota> setearRangosDeCuotas(PrestamoPermitidoOutEntity prestamoPermitido) throws BusinessException {
		
		List<RangoCuota> rangosDeCuotas = new ArrayList<RangoCuota>();
		if (prestamoPermitido != null) {
			for (PrestamoPermitidoEntity infoPrestamos : prestamoPermitido.getListaResult()) {
			List<DestinoPrestamoSeleccionView> listaDestinos = obtenerDestinosPrestamo(infoPrestamos.getCodProductoUg(),
						infoPrestamos.getCodSubpUg());
				rangosDeCuotas.add(new RangoCuota(infoPrestamos, listaDestinos));
			}
	
			Collections.sort(rangosDeCuotas, new Comparator<RangoCuota>() {
				@Override
				public int compare(RangoCuota rango1, RangoCuota rango2) {
					return Integer.parseInt(rango1.getCantMinCuotas()) - Integer.parseInt(rango2.getCantMinCuotas());
				}
			});
		}
		return rangosDeCuotas;
	}
	
	/**
	 * Este método calcula los límites disponibles para cada tipo de Préstamo
	 * (tasa fija, variable, y UVA). Valida que el importe máximo permitido no
	 * supere a la linea total crediticia de la cuenta.
	 *
	 * @param rangosDeCuotas
	 *            the rangos de cuotas
	 * @param lineaTotal
	 *            the linea disponible
	 * @param lineaDisponible
	 *            the linea disponible
	 * @return the limites prestamo view
	 * @throws BusinessException
	 *             the business exception
	 */
	private List<LimitesPrestamoView> setearLimitesPrestamos(List<RangoCuota> rangosDeCuotas, String lineaTotal, String lineaDisponible) throws BusinessException {
		
		List<LimitesPrestamoView> limites = new ArrayList<LimitesPrestamoView>();
		LimitesPrestamoView limiteFija = null;
		LimitesPrestamoView limiteVariable = null;
		LimitesPrestamoView limiteUva = null;
		BigDecimal lineaTotalCrediticia = formatearValor(lineaTotal, 2);
		BigDecimal lineaDisponibleCrediticia = formatearValor(lineaDisponible, 2);
		BigDecimal lineaPermitida = lineaTotalCrediticia;
		
		if (lineaTotalCrediticia.compareTo(lineaDisponibleCrediticia) > 0 ) { 
			lineaPermitida = lineaDisponibleCrediticia;
		}
		
		for (RangoCuota rangoCuota : rangosDeCuotas) {
			boolean rangoCuotaEsMayor = rangoCuota.getImporteMaximo().compareTo(lineaPermitida) > 0;
			
			if (IND_LINEA_UVA.equals(rangoCuota.getIndLineaUva().trim().toUpperCase())) {
				if (limiteUva == null && rangoCuotaEsMayor) {
					rangoCuota.setImporteMaximo(lineaPermitida);
					limiteUva = new LimitesPrestamoView(TIPO_TASA_UVA, rangoCuota);
				}
				else if (limiteUva == null && !rangoCuotaEsMayor) {
					limiteUva = new LimitesPrestamoView(TIPO_TASA_UVA, rangoCuota);
				}
				else {
					if (limiteUva.getImporteMin().compareTo(rangoCuota.getImporteMinimo()) > 0) {
						limiteUva.setImporteMin(rangoCuota.getImporteMinimo());
					}
					
					if (limiteUva.getImporteMax().compareTo(rangoCuota.getImporteMaximo()) < 0
							&& rangoCuotaEsMayor) {
						limiteUva.setImporteMax(lineaPermitida);
					}
					else if (limiteUva.getImporteMax().compareTo(rangoCuota.getImporteMaximo()) < 0
							&& !rangoCuotaEsMayor) {
						limiteUva.setImporteMax(rangoCuota.getImporteMaximo());
					}
					
					if (limiteUva.getCuotaMin().compareTo(rangoCuota.getCantMinCuotas()) > 0) {
						limiteUva.setCuotaMin(rangoCuota.getCantMinCuotas());
					}
					if (limiteUva.getCuotaMax().compareTo(rangoCuota.getCantMaxCuotas()) < 0) {
						limiteUva.setCuotaMax(rangoCuota.getCantMaxCuotas());
					}
				}
			} 
			else {
				if (TASA_FIJA.equals(rangoCuota.getTipoDeTasa().trim())) {
					if (limiteFija == null && rangoCuotaEsMayor) {
						rangoCuota.setImporteMaximo(lineaPermitida);
						limiteFija = new LimitesPrestamoView(TIPO_TASA_FIJA, rangoCuota);
					}
					else if (limiteFija == null && !rangoCuotaEsMayor) {
						limiteFija = new LimitesPrestamoView(TIPO_TASA_FIJA, rangoCuota);
					}
					else {
						if (limiteFija.getImporteMin().compareTo(rangoCuota.getImporteMinimo()) > 0) {
							limiteFija.setImporteMin(rangoCuota.getImporteMinimo());
						}
						
						if (limiteFija.getImporteMax().compareTo(rangoCuota.getImporteMaximo()) < 0
								&& rangoCuotaEsMayor) {
							limiteFija.setImporteMax(lineaPermitida);
						}
						else if (limiteFija.getImporteMax().compareTo(rangoCuota.getImporteMaximo()) < 0
								&& !rangoCuotaEsMayor) {
							limiteFija.setImporteMax(rangoCuota.getImporteMaximo());
						}
						
						if (limiteFija.getCuotaMin().compareTo(rangoCuota.getCantMinCuotas()) > 0) {
							limiteFija.setCuotaMin(rangoCuota.getCantMinCuotas());
						}
						if (limiteFija.getCuotaMax().compareTo(rangoCuota.getCantMaxCuotas()) < 0) {
							limiteFija.setCuotaMax(rangoCuota.getCantMaxCuotas());
						}
					}
				}
				else if (TASA_VARIABLE.equals(rangoCuota.getTipoDeTasa().trim())) {
					if (limiteVariable == null && rangoCuotaEsMayor) {
						rangoCuota.setImporteMaximo(lineaPermitida);
						limiteVariable = new LimitesPrestamoView(TIPO_TASA_VARIABLE, rangoCuota);
					}
					else if (limiteVariable == null && !rangoCuotaEsMayor) {
						limiteVariable = new LimitesPrestamoView(TIPO_TASA_VARIABLE, rangoCuota);
					}
					else {
						if (limiteVariable.getImporteMin().compareTo(rangoCuota.getImporteMinimo()) > 0) {
							limiteVariable.setImporteMin(rangoCuota.getImporteMinimo());
						}
						
						if (limiteVariable.getImporteMax().compareTo(rangoCuota.getImporteMaximo()) < 0
								&& rangoCuotaEsMayor) {
							limiteVariable.setImporteMax(lineaPermitida);
						}
						else if (limiteVariable.getImporteMax().compareTo(rangoCuota.getImporteMaximo()) < 0
								&& !rangoCuotaEsMayor) {
							limiteVariable.setImporteMax(rangoCuota.getImporteMaximo());
						}
						
						if (limiteVariable.getCuotaMin().compareTo(rangoCuota.getCantMinCuotas()) > 0) {
							limiteVariable.setCuotaMin(rangoCuota.getCantMinCuotas());
						}
						if (limiteVariable.getCuotaMax().compareTo(rangoCuota.getCantMaxCuotas()) < 0) {
							limiteVariable.setCuotaMax(rangoCuota.getCantMaxCuotas());
						}
					} // Fin del Else de Comparacion
				} // Fin de Tasa Variable
			} // Fin de PPP no UVA
		} // Fin del For
		
		if (limiteFija != null) {
		    limites.add(limiteFija);
		}
		if (limiteVariable != null) {
		    limites.add(limiteVariable);
		}
		if (limiteUva != null) {
		    limites.add(limiteUva);
		}
		return limites;
	}
	
	/**
	 * Formatear valor.
	 *
	 * @param valor
	 *            the valor
	 * @param cantidadLugares
	 *            the cantidad lugares
	 * @return the big decimal
	 */
	private BigDecimal formatearValor(String valor, int cantidadLugares) {
		if (("0").equals(ISBANStringUtils.eliminarCeros(valor))) {
			return BigDecimal.ZERO;
		}
		String numeroFormat = ISBANStringUtils.eliminarCeros(valor);
		String numeroParteUno = numeroFormat.substring(0, numeroFormat.length() - cantidadLugares);
		String numeroParteDos = numeroFormat.substring(numeroFormat.length() - cantidadLugares);
		String numeroParaConvertir = numeroParteUno + "." + numeroParteDos;
		return new BigDecimal(numeroParaConvertir);
	}
	
	private CalificacionCrediticiaOutEntity obtenerCalificacion(Cuenta cuenta, Map<Cuenta, CalificacionCrediticiaOutEntity> calificaciones) {
		return calificaciones.get(cuenta);
	}
}