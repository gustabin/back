/*
 *
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.*;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.PrestamosDisponibles;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.InfoEmpleadoPrestamoTasaSubsidiada;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldoTasaSubsidiadaEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosAgregarCBUEntity;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelarPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmacionStopDebitDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoSueldosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ObtenerPrestamosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.OrdenPrestamos;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoSueldosPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CompStopDebitPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.LegalesException;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamoPreaprobadoUtils;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoOpenCreditManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SimuladorPrestamoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;

/**
 * The Class InicioPrestamoManagerImpl.
 *
 * @author
 */
@Component
public class InicioPrestamoManagerImpl implements InicioPrestamoManager {

	private static final String PESOS = "$";
	private static final String PORCENTAJE = "%";
	private static final String CUOTA_DETALLE_PRESTAMO_TASA_SUBS = "12 Mensuales";
	private static final String AFIP_PRESTAMO_SUELDOS = "AFIP";
	private static final String PYME_PRESTAMO_SUELDOS = "PYME";
	private static final String FORM_PYME_PRESTAMO_SUELDOS = "_Formulario_PYME";
	private static final String FORM_AFIP_PRESTAMO_SUELDOS = "_Formulario_AFIP";
	private static final String ADJUNTA_DOC_PRESTAMO_SUELDOS = "T";
	private static final String PRESTAMO_SUELDOS_ERROR = "ERROR";
	private static final String PRESTAMO_SUELDOS_INHABILITADO = "INHABILITADO";
	private static final String TOMADO = "T";
	private static final String MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA = "Obteniendo tarjetas banelcos activas...";
	private static final String TARJETA_BANELCO_ACTIVA = "01";
	private static final String MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS = "El cliente no tiene una tarjeta banelco activa.";
	private static final int TIPO_CUENTA_BANELCO = 5;
	private static final String PRODUCTO_ALTAIR_PRESTAMO_REFINANCIACION = "71";
	private static final String PRODUCTO_ALTAIR_PRESTAMO_PERSONAL = "35";
	private static final String PRODUCTO_ALTAIR_PRESTAMO_HIPOTECARIO = "37";
	private static final String PRODUCTO_ALTAIR_PRESTAMO_PRENDARIO = "38";
	private static final String PRODUCTO_CLASE_CUENTA = "4";
	private static final String NUMERO_PRESTAMO_PLACEHOLDER = "#XXXXX";

	/** The Constant DESCRIPCION_REFINANCIACION. */
	private static final String DESCRIPCION_REFINANCIACION = "Refinanciación";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InicioPrestamoManagerImpl.class);

	/** The const super. */
	private static final String CONST_SUPER = "Súper ";

	/** The const UVA. */
	private static final String CONST_UVA = " UVA";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The Constant CUENTA. */
	private static final String CUENTA = "Cuenta";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant SIN_PLAZO. */
	private static final String NO_HAY_VALOR = "-";

	/** The Constant FORMAT_FECHA_MOBILE. */
	private static final String FORMAT_FECHA_MOBILE = "dd/MM/yy";

	/** The Constant SIN_PRESTAMOS_CANCELADOS. */
	public static final String SIN_PRESTAMOS_CANCELADOS = "1160";

	/** The Constant ERROR_GENERICO_PRESTAMOS_CANCELADOS. */
	public static final String ERROR_GENERICO_PRESTAMOS_CANCELADOS = "1137";

	/** The Constant OPERACION_INHABILITADA. */
	private static final String OPERACION_INHABILITADA = "OPERACION_INHABILITADA";

    private static final String PRESTAMO_ATP6 = "202009";

    private static final String PRESTAMO_ATP7 = "202010";

    private static final String PRESTAMO_ATP8 = "202011";

    private static final String NUMERO_ATP6 = "7130";

    private static final String NUMERO_ATP7 = "7157";

    private static final String NUMERO_ATP8 = "7173";

    private static final String NUMERO_ATP9 = "7184";

    private static final String TASAS_INTERES_15 = "16,08";

    private static final String TASAS_INTERES_27 = "30,60";

    private static final String TASAS_INTERES_33 = "38,48";

    private static final double MIN_TOPE_SIN_SELECT = 10000;

    private static final double MIN_TOPE_CON_SELECT = 20000;


	/** The sin prestamos hipotecarios url. */
	@Value("${SIN_PRESTAMOS_HIPOTECARIOS.URL}")
	private String sinPrestamosHipotecariosUrl;

	/** The sin prestamos prendarios url. */
	@Value("${SIN_PRESTAMOS_PRENDARIOS.URL}")
	private String sinPrestamosPrendariosUrl;

	/** The max file size. */
	@Value("${PRESTAMOSUELDOS.MAX.FILESIZE}")
	private String maxFileSize;

	/** The tipos de archivos permitidos. */
	@Value("${PRESTAMOSUELDOS.TIPO.ARCHIVOS.ADJ}")
	private String tiposDeArchivosPermitidos;

	/** The cantidad max de archivos posibles ajuntos. */
	@Value("${PRESTAMOSUELDOS.MAX.FILES.ATACHED}")
	private String cantidadMaxDeArchivosPosiblesAjuntos;

	@Value("${PRESTAMO.SUELDO.TASA.SUBSIDIADA.TYC.ATP7}")
	private String urlPrestamoTasaSubsidiadaTyCATP7;

	@Value("${PRESTAMO.SUELDO.TASA.SUBSIDIADA.TYC.ATP8.TASA27}")
	private String urlPrestamoTasaSubsidiadaTyCATP8Tasa27;

	@Value("${PRESTAMO.SUELDO.TASA.SUBSIDIADA.TYC.ATP8.TASA33}")
	private String urlPrestamoTasaSubsidiadaTyCATP8Tasa33;

	@Value("${PRESTAMO.SUELDO.TASA.SUBSIDIADA.TYC.ATP9.TASA27}")
	private String urlPrestamoTasaSubsidiadaTyCATP9Tasa27;

	@Value("${PRESTAMO.SUELDO.TASA.SUBSIDIADA.TYC.ATP9.TASA33}")
	private String urlPrestamoTasaSubsidiadaTyCATP9Tasa33;

	@Value("${PRESTAMO.SUELDO.TASA.SUBSIDIADA.TYC.ATP6}")
	private String urlPrestamoTasaSubsidiadaTyCATP6;

	/** The prestamo BO. */
	@Autowired
	private PrestamoBO prestamoBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The pre formalizacion prestamo BO. */
	@Autowired
	private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The prestamo manager. */
	@Autowired
	private PrestamoManager prestamoManager;

	/** The simulador prestamo manager. */
	@Autowired
	private SimuladorPrestamoManager simuladorPrestamoManager;

	/** The destino prestamo BO. */
	@Autowired
	private DestinoPrestamoBO destinoPrestamoBO;

    /** The estadistica bo. */
    @Autowired
    private EstadisticaBO estadisticaBo;

	/** OLYMPUS prestamoOpenCreditManager. */
	@Autowired
	private PrestamoOpenCreditManager prestamoOpenCreditManager;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	@Autowired
	private ModuloPermisoBOImpl moduloPermisoBOImpl;



	/**
	 * Obtiene los prestamos.
	 *
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager#obtenerPrestamos(ObtenerPrestamoInView)
	 */
	@Override
	public Respuesta<PrestamosView> obtenerPrestamos(ObtenerPrestamoInView obtenerPrestamoInView) {
		Respuesta<PrestamosView> respuesta = new Respuesta<PrestamosView>();
		PrestamosView prestamosView = new PrestamosView();
		List<PrestamoView> prestamos = new ArrayList<PrestamoView>();

		Respuesta<List<PrestamoDTO>> respuestaPrestamos = prestamoBO
				.obtenerPrestamos(buildObtenerPrestamosInDTO(obtenerPrestamoInView));

		// OLYMPUS - CARGA DE PRESTAMOS CITI OPEN CREDIT
		Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit = new Respuesta<PrestamosOpenCreditView>();
		respuestaPrestamosOpenCredit.setEstadoRespuesta(EstadoRespuesta.OK);

		if (obtenerPrestamoInView.getIsPersonal()) {
			respuestaPrestamosOpenCredit = prestamoOpenCreditManager.obtenerPrestamosOpenCredit();
			if (!EstadoRespuesta.ERROR.equals(respuestaPrestamosOpenCredit.getEstadoRespuesta())) {
				prestamosView.setPrestamosOpenCredit(respuestaPrestamosOpenCredit.getRespuesta());
			}
		}

		if (noContienePrestamos(respuestaPrestamos) && noContienePrestamosOpenCredit(respuestaPrestamosOpenCredit)) {
			return generarWarningSinPrestamos(obtenerPrestamoInView, prestamosView);
		}

		if (EstadoRespuesta.OK.equals(respuestaPrestamos.getEstadoRespuesta())
				|| EstadoRespuesta.WARNING.equals(respuestaPrestamos.getEstadoRespuesta())) {
			for (PrestamoDTO prestamoDTO : respuestaPrestamos.getRespuesta()) {
				Prestamo prestamo = prestamoDTO.getPrestamo();
				PreFormalizacion preFormalizacion = prestamoDTO.getPreFormalizacion();
				Respuesta<PrestamoView> respuestaPrestamoView = obtenerPrestamoView(prestamo, preFormalizacion);
				if (!EstadoRespuesta.OK.equals(respuestaPrestamoView.getEstadoRespuesta())
						&& !EstadoRespuesta.OK.equals(respuestaPrestamos.getEstadoRespuesta())) {
					if (respuestaPrestamoView.getItemsMensajeRespuesta().size() == 1
							&& prestamoTieneErrorServicio(respuestaPrestamos, respuestaPrestamoView)) {
						respuestaPrestamos.add(respuestaPrestamoView.getItemsMensajeRespuesta().get(0));
					}
					respuestaPrestamos.setEstadoRespuesta(EstadoRespuesta.WARNING);
				}
				prestamos.add(respuestaPrestamoView.getRespuesta());
			}
			prestamosView.setPrestamos(prestamos);
			respuesta.setEstadoRespuesta(respuestaPrestamos.getEstadoRespuesta());
			respuesta.setItemMensajeRespuesta(respuestaPrestamos.getItemsMensajeRespuesta());
			respuesta.setRespuesta(prestamosView);

			if (obtenerPrestamoInView.getIsPersonal()) {
				if (!EstadoRespuesta.OK.equals(respuestaPrestamosOpenCredit.getEstadoRespuesta())) {
					respuesta.addAll(respuestaPrestamosOpenCredit.getItemsMensajeRespuesta());
					respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				}

				if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
							EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
				}

				respuesta = validarLineaCrediticia(respuesta, prestamos.size());

				if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
					if (!EstadoRespuesta.ERROR.equals(respuestaPrestamosOpenCredit.getEstadoRespuesta())) {
						respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
					} else {
						// respuesta = respuestaFactory.crearRespuestaError(PrestamosView.class,
						// respuestaPrestamos.getItemsMensajeRespuesta());
						estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS,
								EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					}
				}
				Cliente cliente = sesionCliente.getCliente();
				if (prestamosView.getPrestamos() != null && !prestamosView.getPrestamos().isEmpty() && sesionParametros.getDisponiblePrestamos() != null) {
					if (sesionParametros.getDisponiblePrestamos() > MIN_TOPE_CON_SELECT && cliente.getClienteSelect()
							|| sesionParametros.getDisponiblePrestamos() > MIN_TOPE_SIN_SELECT && !cliente.getClienteSelect()) {
						prestamosView.setMensajeContextual(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_CONTEXTUAL_OFERTA).getMensaje());
					}
					if (sesionParametros.getMaxOfertaPrestamoPreaprobado() != null && prestamosView.getMensajeContextual() == null) {
						LimitePrestamoPreaprobadoView lim = PrestamoPreaprobadoUtils.obtenerOfertaSeleccionada(sesionParametros.getLimitesPreaprobado(), sesionParametros.getMaxOfertaPrestamoPreaprobado(), null);
						prestamosView.setMensajeContextual(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_CONTEXTUAL_PREAPROBADOS).getMensaje());
					}
				}

			} else if (obtenerPrestamoInView.getIsHipotecario()) {
				if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_HIPOTECARIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_HIPOTECARIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
				}
				if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
					respuesta = respuestaFactory.crearRespuestaError(PrestamosView.class,
							respuestaPrestamos.getItemsMensajeRespuesta());
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_HIPOTECARIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
			} else {
				if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_PRENDARIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_PRENDARIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
				}
				if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
					respuesta = respuestaFactory.crearRespuestaError(PrestamosView.class,
							respuestaPrestamos.getItemsMensajeRespuesta());
					estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_GRILLA_PRESTAMOS_PRENDARIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
			}
		} else {
			if (obtenerPrestamoInView.getIsPersonal()
					&& !EstadoRespuesta.ERROR.equals(respuestaPrestamosOpenCredit.getEstadoRespuesta())) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuesta.setItemMensajeRespuesta(respuestaPrestamosOpenCredit.getItemsMensajeRespuesta());
				if (respuestaPrestamos.getItemsMensajeRespuesta() != null) {
					respuesta.addAll(respuestaPrestamos.getItemsMensajeRespuesta());
				}
				respuesta.setRespuesta(prestamosView);
			} else {
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TOTAL_BUSCAR_PRESTAMOS,
						CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
			}
		}
		cargarLegalPrestamosPersonales(respuesta, obtenerPrestamoInView);
		if (obtenerPrestamoInView.getIsStopDebitPrestamo()){
			cargarLegalStopDebitPrestamos(respuesta, obtenerPrestamoInView);

		}

		return respuesta;
	}

	@Override
	public Respuesta<PrestamosEncoladosView> obtenerPrestamosEncolados() {
		PrestamosEncoladosEntity prestamosEncoladosEntity = prestamoBO.obtenerPrestamosEncolados();
		PrestamosEncoladosView prestamosEncoladosView = mapPrestamosEncolados(prestamosEncoladosEntity);
		return respuestaFactory.crearRespuestaOk(prestamosEncoladosView);
	}

	private PrestamosEncoladosView mapPrestamosEncolados(PrestamosEncoladosEntity prestamosEncoladosEntity) {
		PrestamosEncoladosView prestamosEncoladosView = new PrestamosEncoladosView();
		final String sinDatos = "-";
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);

		for (PrestamoEncoladoEntity prestamoEncoladoEntity : prestamosEncoladosEntity.getPrestamosEncolados()) {
			PrestamoEncoladoView prestamoEncoladoView = new PrestamoEncoladoView();
			prestamoEncoladoView.setId(prestamoEncoladoEntity.getId());
			prestamoEncoladoView.setMonto(prestamoEncoladoEntity.getAmount() == null ? sinDatos
					: ISBANStringUtils.formatearSaldo(prestamoEncoladoEntity.getAmount()));
			prestamoEncoladoView.setFechaCreacion(prestamoEncoladoEntity.getCreationDate() == null ? sinDatos : sdf.format((prestamoEncoladoEntity.getCreationDate())));

			prestamoEncoladoView.setUva(Boolean.TRUE.equals(prestamoEncoladoEntity.getLineUva()));
			TipoPrestamoEnum tipoDePrestamo = prestamoEncoladoView.getUva() ? TipoPrestamoEnum.UVA : TipoPrestamoEnum.PERSONAL;
			prestamoEncoladoView.setTipoPrestamo(prestamoEncoladoEntity.getLoanType());
			prestamoEncoladoView.setDescripcion(CONST_SUPER + tipoDePrestamo.getDescripcion());

			prestamoEncoladoView.setNroPrestamo(prestamoEncoladoEntity.getDebitAccount() == null ? sinDatos : prestamoEncoladoEntity.getDebitAccount().getNroPrestamo());
			prestamoEncoladoView.setCuotas(String.valueOf(prestamoEncoladoEntity.getQuotas()));
			prestamoEncoladoView.setFechaPrimerVencimiento(prestamoEncoladoEntity.getFirstExpirationDate() == null ? sinDatos : sdf.format((prestamoEncoladoEntity.getFirstExpirationDate())));
			prestamoEncoladoView.setTna(prestamoEncoladoEntity.getTna());

			prestamosEncoladosView.getPrestamos().add(prestamoEncoladoView);
		}
		return prestamosEncoladosView;
	}


	/**
	 * Obtener prestamo sueldos.
	 *
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PrestamoSueldosView> obtenerPrestamoSueldos(ObtenerPrestamoInView obtenerPrestamoInView) {
		Respuesta<PrestamoSueldosView> respuesta = new Respuesta<PrestamoSueldosView>();
		Cliente cliente = sesionCliente.getCliente();
		String email = sesionCliente.getCliente().getEmailLoginMya();
		List<Cuenta> cuentasPesos = cliente.getCuentasEnPesos();
		List<CuentaView> cuentasViewPesos = obtenerCuentasViewPesos(cuentasPesos);
		PrestamoSueldosView prestamoSueldosView = new PrestamoSueldosView();
		Respuesta<PrestamoSueldosPermitidoDTO> prestamoPermitidoSueldosDTO = prestamoBO
				.obtenerPrestamoSueldo(buildObtenerPrestamoSueldosInDTO(obtenerPrestamoInView));

		if (EstadoRespuesta.OK.equals(prestamoPermitidoSueldosDTO.getEstadoRespuesta())
				&& (prestamoPermitidoSueldosDTO.getRespuesta() != null)) {
			prestamoSueldosView.setMontoPrestamo(ISBANStringUtils.formatearConComaYDosDecimales(
					String.valueOf(prestamoPermitidoSueldosDTO.getRespuesta().getMontoPrestamo())));
			prestamoSueldosView.setSolicitado(prestamoPermitidoSueldosDTO.getRespuesta().getSolicitado());
			prestamoSueldosView.setCuentasPesos(cuentasViewPesos);
			prestamoSueldosView.setEmail(email);
			prestamoSueldosView
					.setAdjuntaDocumentacion(prestamoPermitidoSueldosDTO.getRespuesta().getAdjuntaDocumentacion());
			respuesta = respuestaFactory.crearRespuestaOk(prestamoSueldosView);
			estadisticaManager.add(EstadisticasConstants.CONSULTA_PRESTAMO_SUELDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		} else if (EstadoRespuesta.ERROR.equals(prestamoPermitidoSueldosDTO.getEstadoRespuesta())) {
			String tipoError = prestamoPermitidoSueldosDTO.getItemsMensajeRespuesta().get(0).getTipoError();
			if (TipoError.ERROR_PRESTAMOS_FOREX.getDescripcion().equals(tipoError)
					|| TipoError.ERROR_GENERICO.getDescripcion().equals(tipoError)) {
				estadisticaManager.add(EstadisticasConstants.CONSULTA_PRESTAMO_SUELDOS_ERROR_FOREX,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(PrestamoSueldosView.class,
						prestamoPermitidoSueldosDTO.getItemsMensajeRespuesta());
			}
			respuesta = respuestaFactory.crearRespuestaError(PrestamoSueldosView.class,
					prestamoPermitidoSueldosDTO.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.CONSULTA_PRESTAMO_SUELDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Confirmar prestamo sueldos.
	 *
	 * @param prestamoSueldosConfirmacionView the prestamo sueldos confirmacion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PrestamoSueldosView> confirmarPrestamoSueldos(
			PrestamoSueldosConfirmacionView prestamoSueldosConfirmacionView) {
		Respuesta<PrestamoSueldosView> respuesta = new Respuesta<PrestamoSueldosView>();
		PrestamoSueldosView prestamoSueldosView = new PrestamoSueldosView();
		Respuesta<PrestamoSueldosPermitidoDTO> prestamoPermitidoSueldosDTO = prestamoBO
				.confirmarPrestamoSueldo(buildConfirmarPrestamoSueldosInDTO(prestamoSueldosConfirmacionView));

		if (EstadoRespuesta.OK.equals(prestamoPermitidoSueldosDTO.getEstadoRespuesta())
				&& (prestamoPermitidoSueldosDTO.getRespuesta() != null)) {
			prestamoSueldosView.setNroComprobante(prestamoPermitidoSueldosDTO.getRespuesta().getNroComprobante());
			respuesta = respuestaFactory.crearRespuestaOk(prestamoSueldosView);
			if (ADJUNTA_DOC_PRESTAMO_SUELDOS.equals(prestamoSueldosConfirmacionView.getAdjuntaDocumentacion())) {
				estadisticaManager.add(EstadisticasConstants.CARGO_DOC_PRESTAMO_SUELDOS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			estadisticaManager.add(EstadisticasConstants.INSERTAR_PRESTAMO_SUELDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, prestamoSueldosConfirmacionView.getIdcuentasPesos(),
					prestamoSueldosConfirmacionView.getMontoPrestamoSeleccion(), PESOS);

		} else if (EstadoRespuesta.ERROR.equals(prestamoPermitidoSueldosDTO.getEstadoRespuesta())) {
			respuesta = respuestaFactory.crearRespuestaError(PrestamoSueldosView.class,
					prestamoPermitidoSueldosDTO.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.INSERTAR_PRESTAMO_SUELDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
					prestamoSueldosConfirmacionView.getIdcuentasPesos(),
					prestamoSueldosConfirmacionView.getMontoPrestamoSeleccion(), PESOS);
		}

		return respuesta;
	}

	/**
	 * Cargar legal prestamos personales.
	 *
	 * @param respuesta             the respuesta
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 */
	private void cargarLegalPrestamosPersonales(Respuesta<PrestamosView> respuesta,
			ObtenerPrestamoInView obtenerPrestamoInView) {

		if (obtenerPrestamoInView.getIsPersonal() && !respuesta.getRespuesta().getPrestamos().isEmpty()) {
			try {
				respuesta.getRespuesta().setLegalPrestamosPersonales(
						legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_PRESTAMOS_PERSONALES));
			} catch (DAOException e) {
				LOGGER.error("No se encontró el legal de Préstamos Personales");
			}
		}
	}

	private void cargarLegalStopDebitPrestamos(Respuesta<PrestamosView> respuesta,
			ObtenerPrestamoInView obtenerPrestamoInView) {
		estadisticaManager.add(EstadisticasConstants.INICIO_STOPDEBIT_PRESTAMOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		if (obtenerPrestamoInView.getIsStopDebitPrestamo() && !respuesta.getRespuesta().getPrestamos().isEmpty()) {
			try {
				respuesta.getRespuesta().setLegalStopDebitPrestamos(
						legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_STOP_DEBIT_PRESTAMOS));
			} catch (DAOException e) {
				estadisticaManager.add(EstadisticasConstants.INICIO_STOPDEBIT_PRESTAMOS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				LOGGER.error("No se encontró el legal de Stop Debit Préstamos");
			}
		}
	}

	/**
	 * Generar warning sin prestamos.
	 *
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 * @param prestamosView         the prestamos view
	 * @return the respuesta
	 */
	private Respuesta<PrestamosView> generarWarningSinPrestamos(ObtenerPrestamoInView obtenerPrestamoInView,
			PrestamosView prestamosView) {
		String codigoMensaje;
		if (obtenerPrestamoInView.getIsHipotecario()) {
			codigoMensaje = CodigoMensajeConstantes.NO_REGISTROS_PRESTAMOS_HIPOTECARIOS;
			prestamosView.setSinPrestamosHipotecariosUrl(sinPrestamosHipotecariosUrl);
		} else if (obtenerPrestamoInView.getIsPersonal() && sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
			codigoMensaje = CodigoMensajeConstantes.MENSAJE_CONTEXTUAL_PREAPROBADOS_SIN_TENENCIA;
		} else if (obtenerPrestamoInView.getIsPersonal()) {
			codigoMensaje = CodigoMensajeConstantes.NO_REGISTROS_PRESTAMOS_PERSONALES;
		} else {
			codigoMensaje = CodigoMensajeConstantes.NO_REGISTROS_PRESTAMOS_PRENDARIOS;
			prestamosView.setSinPrestamosPrendariosUrl(sinPrestamosPrendariosUrl);
		}
		return respuestaFactory.crearRespuestaWarning(prestamosView, "", TipoError.ERROR_VALIDACION_NO_REGISTROS,
				codigoMensaje);
	}

	/**
	 * Valida si la respuesta es OK y no se encontraron prestamos OpenCredit.
	 *
	 * @param respuestaPrestamosOpenCredit the respuesta prestamos open credit
	 * @return true, if successful
	 */
	private boolean noContienePrestamosOpenCredit(Respuesta<PrestamosOpenCreditView> respuestaPrestamosOpenCredit) {
		return (EstadoRespuesta.OK.equals(respuestaPrestamosOpenCredit.getEstadoRespuesta())
				&& (respuestaPrestamosOpenCredit.getRespuesta() == null
						|| CollectionUtils.isEmpty(respuestaPrestamosOpenCredit.getRespuesta().getPrestamos())));
	}

	/**
	 * Verifica si el prestamo tiene error servicio.
	 *
	 * @param respuestaPrestamos    the respuesta prestamos
	 * @param respuestaPrestamoView the respuesta prestamo view
	 * @return the boolean
	 */
	private Boolean prestamoTieneErrorServicio(Respuesta<List<PrestamoDTO>> respuestaPrestamos,
			Respuesta<PrestamoView> respuestaPrestamoView) {
		for (ItemMensajeRespuesta item : respuestaPrestamos.getItemsMensajeRespuesta()) {
			if (StringUtils.equals(item.getMensaje(),
					respuestaPrestamoView.getItemsMensajeRespuesta().get(0).getMensaje())) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * No contiene prestamos.
	 *
	 * @param respuestaPrestamos the respuesta prestamos
	 * @return true, if successful
	 */
	// Valida sobre una respuesta correcta si no encuentra prestamos
	private boolean noContienePrestamos(Respuesta<List<PrestamoDTO>> respuestaPrestamos) {
		return EstadoRespuesta.OK.equals(respuestaPrestamos.getEstadoRespuesta())
				&& CollectionUtils.isEmpty(respuestaPrestamos.getRespuesta());
	}

	/**
	 * Validar cuentas monetarias activas en pesos.
	 *
	 * @param cabeceraPrestamosView the cabecera prestamos view
	 */
	private void validarCuentasMonetariasActivasEnPesos(CabeceraPrestamosView cabeceraPrestamosView) {
		if (cuentaManager.hasCuentasMonetariasActivasEnPesos()) {
			cabeceraPrestamosView.setHabilitarPagoPrestamo(true);
		} else {
			cabeceraPrestamosView.setHabilitarPagoPrestamo(false);
		}
	}

	/**
	 * Builds the obtener prestamos in DTO.
	 *
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 * @return the obtener prestamos in DTO
	 */
	private ObtenerPrestamosInDTO buildObtenerPrestamosInDTO(ObtenerPrestamoInView obtenerPrestamoInView) {
		ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
		Cliente cliente = sesionCliente.getCliente();
		obtenerPrestamosInDTO.setCliente(cliente);

		obtenerPrestamosInDTO.setIsHipotecario(obtenerPrestamoInView.getIsHipotecario());
		obtenerPrestamosInDTO.setIsPersonal(obtenerPrestamoInView.getIsPersonal());
		obtenerPrestamosInDTO.setIsPrendario(obtenerPrestamoInView.getIsPrendario());

//		obtenerPrestamosInDTO.setIsTodos(!(obtenerPrestamosInDTO.getIsHipotecario() || obtenerPrestamosInDTO.getIsPrendario()
//				|| obtenerPrestamosInDTO.getIsPersonal()));
		if (!(obtenerPrestamosInDTO.getIsHipotecario() || obtenerPrestamosInDTO.getIsPrendario()
				|| obtenerPrestamosInDTO.getIsPersonal())) {
			obtenerPrestamosInDTO.setIsTodos(true);
		}
		obtenerPrestamosInDTO.setOrdenPrestamos(OrdenPrestamos.ASCENSDENTE);
		obtenerPrestamosInDTO.setCalificaciones(sesionParametros.getCalificacionesCrediticiasDTO());
		return obtenerPrestamosInDTO;
	}

	/**
	 * Builds the obtener prestamo sueldos in DTO.
	 *
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 * @return the obtener prestamos in DTO
	 */
	private ObtenerPrestamosInDTO buildObtenerPrestamoSueldosInDTO(ObtenerPrestamoInView obtenerPrestamoInView) {
		ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
		Cliente cliente = sesionCliente.getCliente();
		obtenerPrestamosInDTO.setCliente(cliente);
		obtenerPrestamosInDTO.setIsPrestamoSueldo(obtenerPrestamoInView.getIsPrestamoSueldo());
		return obtenerPrestamosInDTO;
	}

	/**
	 * Builds the confirmar prestamo sueldos in DTO.
	 *
	 * @param prestamoSueldosConfirmacionView the prestamo sueldos confirmacion view
	 * @return the confirmar prestamo sueldos in DTO
	 */
	private ConfirmarPrestamoSueldosInDTO buildConfirmarPrestamoSueldosInDTO(
			PrestamoSueldosConfirmacionView prestamoSueldosConfirmacionView) {
		ConfirmarPrestamoSueldosInDTO confirmarPrestamoSueldosInDTO = new ConfirmarPrestamoSueldosInDTO();
		Cliente cliente = sesionCliente.getCliente();
		confirmarPrestamoSueldosInDTO.setCliente(cliente);
		confirmarPrestamoSueldosInDTO
				.setMontoPrestamoSeleccion(prestamoSueldosConfirmacionView.getMontoPrestamoSeleccion());
		confirmarPrestamoSueldosInDTO.setEmail(prestamoSueldosConfirmacionView.getEmail());
		confirmarPrestamoSueldosInDTO
				.setAdjuntaDocumentacion(prestamoSueldosConfirmacionView.getAdjuntaDocumentacion());

		String[] numeroCuenta = prestamoSueldosConfirmacionView.getIdcuentasPesos().split(GUION);
		// StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
		// cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);
		// String nroCuenta = cuentaProducto.toString();
		String nroCuenta = numeroCuenta[1];// 0638080/1
		List<Cuenta> cuentasPesos = cliente.getCuentasEnPesos();
		// Cuenta ctaSeleccionada = cliente.getCuentaPorNumero(nroCuenta);
		Cuenta cuenta = obtenerCuenta(cuentasPesos, nroCuenta);
		if (cuenta != null) {
			confirmarPrestamoSueldosInDTO.setCuentasPesos(cuenta);
		}
		return confirmarPrestamoSueldosInDTO;
	}

	/**
	 * Checks if is mobile.
	 *
	 * @return true, if is mobile
	 */
	private boolean isMobile() {
		return sesionParametros.getRegistroSession().isMobile();
	}

	/**
	 * Obtener prestamo view.
	 *
	 * @param prestamo the prestamo
	 * @return the respuesta
	 */
	private Respuesta<PrestamoView> obtenerPrestamoView(Prestamo prestamo, PreFormalizacion preFormalizacion) {
		PrestamoView prestamoView = new PrestamoView();
		Respuesta<PrestamoView> respuestaPrestamoView = new Respuesta<PrestamoView>();
		respuestaPrestamoView.setRespuesta(prestamoView);

		if (prestamo.getAlias() != null) {
			prestamoView.setAlias(prestamo.getAlias());
			prestamoView.setHasAlias(Boolean.TRUE);
		}

		if (prestamo.isInformeCoeficienteNoDisponible()) {
			prestamoView.setId(String.valueOf(prestamo.getCuenta().getIndex()));
			prestamoView.setCuota("-");
			prestamoView.setFechaVencimiento("-");
			prestamoView.setImporteMaximaCuota("-");
			prestamoView.setPlazo("-");
			prestamoView.setIsUva(true);
			// En caso de que sea coeficiente no disponible se le concatena la palabra UVA
			if (prestamo.getTipoPrestamoEnum().equals(TipoPrestamoEnum.HIPOTECARIOS)
					|| prestamo.getTipoPrestamoEnum().equals(TipoPrestamoEnum.PRENDARIO)) {
				prestamoView.setTipoPrestamo(CONST_SUPER + prestamo.getTipoPrestamoEnum().getDescripcion() + CONST_UVA);
			} else {
				prestamoView.setTipoPrestamo(obtenerTipoPrestamo(prestamo));
			}
			respuestaPrestamoView.setEstadoRespuesta(EstadoRespuesta.OK);
			prestamoView.setTipoError(TipoError.AVISO_PROXIMA_CUOTA_NO_DISPONIBLE.getDescripcion());
			prestamoView.setNroPrestamo(ISBANStringUtils.formatearNroPrestamo(prestamo.getCuenta().getNroSucursal(),
					prestamo.getNumeroCuentaProducto()));
		} else {
			prestamoView.setId(String.valueOf(prestamo.getCuenta().getIndex()));

			prestamoView.setTipoPrestamo(obtenerTipoPrestamo(prestamo));
			prestamoView.setNroPrestamo(ISBANStringUtils.formatearNroPrestamo(prestamo.getCuenta().getNroSucursal(),
					prestamo.getNumeroCuentaProducto()));
			prestamoView.setCuota(ISBANStringUtils.eliminarCeros(prestamo.getNumeroRecibo()));
			prestamoView
					.setImporteMaximaCuota(ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteTotalCuota()));
			if (isMobile()) {
				prestamoView.setFechaVencimiento(
						ISBANStringUtils.formatearFecha(prestamo.getVencimientoCuota(), FORMAT_FECHA_MOBILE));
			} else {
				prestamoView.setFechaVencimiento(ISBANStringUtils.formatearFecha(prestamo.getVencimientoCuota()));
			}
			if (preFormalizacion != null) {
				prestamoView.setPlazo(preFormalizacion.getPlazo());
				prestamoView.setFechaInicio(
						preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio().replace("-", "/"));
				prestamoView.setNumVin(preFormalizacion.getPrestamoDebitoAdherido().getNumero());
				String codigoDestinoFondo = preFormalizacion.getCodigoDestinoPrestamo();
				String destinoPrestamo = destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(codigoDestinoFondo);
				if (TipoPrestamoEnum.PERSONAL.equals(prestamo.getTipoPrestamoEnum())) {
					prestamoView.setMotivoPrestamo(destinoPrestamo != null ? destinoPrestamo : NO_HAY_VALOR);
				}
				String montoAPagar = "$ " + ISBANStringUtils.formatearSaldo(ISBANStringUtils.stringToBigDecimal(
						preFormalizacion.getPrestamoDebitoAdherido().getMontoAPagar(), 13, 4, false));
				prestamoView.setMontoPrestamo(montoAPagar);
				int cuota = Integer.parseInt(ISBANStringUtils.eliminarCeros(prestamo.getNumeroRecibo()));
				int plazo = Integer.parseInt(preFormalizacion.getPlazo());
				prestamoView.setNoTieneCuotasPagas(cuota > 1 ? false : true);
				prestamoView.setIsUltimaCuota(cuota < plazo ? false : true);
				respuestaPrestamoView.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				prestamoView.setPlazo(NO_HAY_VALOR);
				prestamoView.setFechaInicio(NO_HAY_VALOR);
				if (TipoPrestamoEnum.PERSONAL.equals(prestamo.getTipoPrestamoEnum())) {
					prestamoView.setMotivoPrestamo(NO_HAY_VALOR);
				}
				prestamoView.setMontoPrestamo(NO_HAY_VALOR);
				respuestaPrestamoView = respuestaFactory.crearRespuestaWarning(prestamoView, "",
						TipoError.ERROR_PLAZO_PRESTAMOS, CodigoMensajeConstantes.ERROR_SIN_PLAZO_PRESTAMOS);
			}
			if (!StringUtils.defaultString(prestamo.getNroExp()).trim().isEmpty()) {
				prestamoView.setIsUva(true);
			}
			prestamoView.setIsCuotaVencida(prestamo.getIsCuotaVencida());
			respuestaPrestamoView.setRespuesta(prestamoView);
		}

		if (prestamo.getCodigoPrestamoNormativo() != null && prestamo.getIsPrestamoNormativo()) {
			prestamoView.setTipoPrestamo(MessageFormat.format(prestamo.getCodigoPrestamoNormativo().getLabel(),
					prestamo.getNumeroPrestamoViejo()) + CONST_SUPER + prestamo.getTipoPrestamoEnum().getDescripcion());
		}
		prestamoView.setCancelarEnabled(isCancelarAplicable().evaluate(prestamo));
		prestamoView.setShowHelpEnabled(isShowHelpAplicable().evaluate(prestamo));
		return respuestaPrestamoView;
	}

	private Predicate isCancelarAplicable() {
		return new Predicate () {
			@Override
			public boolean evaluate(Object object) {
				Prestamo prestamo = (Prestamo) object;
				String claseCuenta = prestamo.getCuenta().getClaseCuenta();
				String productoAltair = prestamo.getCuenta().getProductoAltair();
				
				Boolean esSkipCuota = prestamo.getCodigoPrestamoNormativo() != null && prestamo.getIsPrestamoNormativo();
				Boolean esPrestamoPersonal = PRODUCTO_ALTAIR_PRESTAMO_PERSONAL.equals(productoAltair);
				Boolean esPrestamoPrendario = PRODUCTO_ALTAIR_PRESTAMO_PRENDARIO.equals(productoAltair);
				Boolean esPrestamoHipoteracario = PRODUCTO_ALTAIR_PRESTAMO_HIPOTECARIO.equals(productoAltair);
				Boolean esRefinanciacion = PRODUCTO_ALTAIR_PRESTAMO_REFINANCIACION.equals(productoAltair) && PRODUCTO_CLASE_CUENTA.equals(claseCuenta);
				
				
				return (esRefinanciacion || esPrestamoPersonal || (esSkipCuota && (esPrestamoPrendario || esPrestamoHipoteracario)));
			}
		};
	}

	private Predicate isShowHelpAplicable() {
		return new Predicate () {
			@Override
			public boolean evaluate(Object object) {
				Prestamo prestamo = (Prestamo) object;
				return !StringUtils.isBlank(prestamo.getNroExp())
						&& prestamoBO.getPrestamoCom12123(prestamo);
			}
		};
	}

	/**
	 * Obtener tipo prestamo.
	 *
	 * @param prestamo the prestamo
	 * @return the string
	 */
	private String obtenerTipoPrestamo(Prestamo prestamo) {
		if (prestamo.getIsRefinanciacion()) {
			return DESCRIPCION_REFINANCIACION;
		}
		return CONST_SUPER + prestamo.getTipoPrestamoEnum().getDescripcion();
	}

	/**
	 * crear un View para retornar al PrestamoSEI.
	 *
	 * @param prestamosDisponibles   the prestamos disponibles
	 * @param calificacionCrediticia the dto
	 * @return view.
	 */
	private CabeceraPrestamosView crearCabeceraPrestamosView(PrestamosDisponibles prestamosDisponibles,
			CalificacionCrediticiaDTO calificacionCrediticia) {

		CabeceraPrestamosView prestamo = new CabeceraPrestamosView();
		PrestamosEncoladosEntity prestamosEncoladosEntity = prestamoBO.obtenerPrestamosEncolados();
		prestamosDisponibles.setTieneEncolados(!prestamosEncoladosEntity.getPrestamosEncolados().isEmpty());
		prestamo.setPrestamosDisponibles(prestamosDisponibles);
		prestamo.setAyudaLineaDisponible(prestamoBO.obtenerMensajeAyudaLineaActualDisponible());
		prestamo.setAyudaLineaTotal(prestamoBO.obtenerMensajeAyudaLineaTotalCrediticia());
		prestamo.setMensajeAyudaSimulador1(
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.AYUDA_PRESTAMO_TASA_FIJA));
		prestamo.setMensajeAyudaSimulador2(
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.AYUDA_PRESTAMO_TASA_VARIABLE));
		prestamo.setMensajeAyudaSimulador3(
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.AYUDA_PRESTAMO_TASA_UVA));

		prestamo.setMensajeSolicitarAyudaUva(new String[] { prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_SUBHEADER),
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_BODY),
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_INFO),
				legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_LEGAL)});

		// BUSCAMOS SI TIENE OFERTA DE PREAPROBADO MONO
		boolean permitePreaprobadoMonoproducto = moduloPermisoBOImpl.tienePermisoMostrar(AccionController.SOLICITUD_PRESTAMO_PREAPROBADO_MONOPRODUCTO);
		if(permitePreaprobadoMonoproducto &&
				sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
			prestamosDisponibles.setTienePrearpobadoMonoproducto(true);
			prestamo.setLineaPreaprobadoMonoproducto(
					ISBANStringUtils.formatearSaldo(sesionParametros.getMaxOfertaPrestamoPreaprobado()));
		}
		if (calificacionCrediticia != null) {
			if(permitePreaprobadoMonoproducto && sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
				calificacionCrediticia.setLineaTotalCrediticia(calificacionCrediticia.getLineaTotalCrediticia()
						.add(sesionParametros.getMaxOfertaPrestamoPreaprobado()));
			}
			prestamo.setLineaDisponible(
					ISBANStringUtils.formatearSaldo(calificacionCrediticia.getLineaActualDisponible()));
			prestamo.setLineaTotal(ISBANStringUtils.formatearSaldo(calificacionCrediticia.getLineaTotalCrediticia()));
			prestamo.setLineaDisponibleValue(calificacionCrediticia.getLineaActualDisponible().doubleValue());
			prestamo.setLineaTotalValue(calificacionCrediticia.getLineaTotalCrediticia().doubleValue());

		} else if(permitePreaprobadoMonoproducto && sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
			prestamo.setLineaTotalValue(sesionParametros.getMaxOfertaPrestamoPreaprobado().doubleValue());
		}

		sesionParametros.setDisponiblePrestamos(prestamo.getLineaTotalValue());

		validarCuentasMonetariasActivasEnPesos(prestamo);

		prestamo.setFueraHorarioOperaciones(!simuladorPrestamoManager.chequearSiEstaEnHorarioOperacion());

		return prestamo;
	}

	/**
	 * crear un View representando un error para retornar al PrestamoSEI.
	 *
	 * @param prestamosDisponibles the prestamos disponibles
	 * @return view.
	 */
	private CabeceraPrestamosView crearCabeceraPrestamosViewConError(PrestamosDisponibles prestamosDisponibles) {

		CabeceraPrestamosView prestamo = new CabeceraPrestamosView();
		// BUSCAMOS SI TIENE OFERTA DE PREAPROBADO MONO
		if(sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
			prestamosDisponibles.setTienePrearpobadoMonoproducto(true);
			prestamo.setLineaPreaprobadoMonoproducto(
					ISBANStringUtils.formatearSaldo(sesionParametros.getMaxOfertaPrestamoPreaprobado()));
		}
		PrestamosEncoladosEntity prestamosEncoladosEntity = prestamoBO.obtenerPrestamosEncolados();
		prestamosDisponibles.setTieneEncolados(!prestamosEncoladosEntity.getPrestamosEncolados().isEmpty());
		prestamo.setPrestamosDisponibles(prestamosDisponibles);
		prestamo.setLineaDisponible("-");
		prestamo.setLineaTotal("-");
		prestamo.setLineaDisponibleValue(0.0);
		prestamo.setLineaTotalValue(0.0);
		prestamo.setAyudaLineaDisponible(prestamoBO.obtenerMensajeAyudaLineaActualDisponible());
		prestamo.setAyudaLineaTotal(prestamoBO.obtenerMensajeAyudaLineaTotalCrediticia());
		prestamo.setMensajeAyudaSimulador1(
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.AYUDA_PRESTAMO_TASA_FIJA));
		prestamo.setMensajeAyudaSimulador2(
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.AYUDA_PRESTAMO_TASA_VARIABLE));
		prestamo.setMensajeAyudaSimulador3(
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.AYUDA_PRESTAMO_TASA_UVA));

		prestamo.setMensajeSolicitarAyudaUva(new String[] { prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_SUBHEADER),
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_BODY),
				prestamoBO.obtenerMensajeAyuda(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_INFO),
				legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.SOLICITAR_AYUDA_UVA_LEGAL)});

		validarCuentasMonetariasActivasEnPesos(prestamo);

		prestamo.setFueraHorarioOperaciones(!simuladorPrestamoManager.chequearSiEstaEnHorarioOperacion());

		return prestamo;
	}

	/**
	 * crear respuesta view para devolver al sei.
	 *
	 * @return respuesta view.
	 */
	@Override
	public Respuesta<CabeceraPrestamosView> obtenerCabecera() {

        Cliente cliente = sesionCliente.getCliente();
        CabeceraPrestamosView prestamoView;
        Respuesta<CabeceraPrestamosView> respuesta;
        Segmento segmento = cliente.getSegmento();
        Boolean isAdvance = segmento.isDuo() || segmento.isPyme();
        try {
			LOGGER.info("MALUG-2276 Obteniendo situacion crediticia de las cuentas: {}",cliente.getCuentas());
			Respuesta<List<CalificacionCrediticiaDTO>> respuestaBO = prestamoBO.obtenerSituacionCrediticia(cliente);
        	sesionParametros.setCalificacionesCrediticiasDTO(respuestaBO.getRespuesta());


			LOGGER.info("MALUG-2276 respuesta: {} , Situacion crediticia: {}",respuestaBO, respuestaBO.getRespuesta());


			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())
                    || EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {

				prestamoView = crearCabeceraPrestamosView(cliente.obtenerPrestamosDisponibles(),
						getCalificacionPrincipal(respuestaBO.getRespuesta()));
				prestamoView.setIsAdvance(isAdvance);
				estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_AGENDA_PRESTAMOS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				respuesta = respuestaFactory.crearRespuestaOk(CabeceraPrestamosView.class, prestamoView);
			} else {
				prestamoView = crearCabeceraPrestamosViewConError(cliente.obtenerPrestamosDisponibles());
				estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_AGENDA_PRESTAMOS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

				if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())
						&& TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion()
								.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
					ItemMensajeRespuesta item = new ItemMensajeRespuesta();
					item.setTag(OPERACION_INHABILITADA);
					item.setTipoError("WARNING_INHABILITADO_PRESTAMO");
					item.setMensaje(respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje());
					respuesta = respuestaFactory.crearRespuestaWarning(prestamoView, Arrays.asList(item));
				} else if (prestamoView.getPrestamosDisponibles().tienePrestamo()) {
					respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(prestamoView,
							"WARNING_INHABILITADO_PRESTAMO_CON_PRESTAMOS",
							StringUtils.endsWith(TipoError.ERROR_INICIO_PRESTAMOS_SIN_LINEA_DISPONIBLE.getDescripcion(),
									respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())
											? CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO
											: CodigoMensajeConstantes.ERROR_LINEA_DISPONIBLE_PRESTAMO,
							null);
				} else {
					respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(prestamoView,
							"WARNING_INHABILITADO_PRESTAMO", "1669", null);
				}
			}
			prestamoView.setLegalesSimuladorPrestamo(
					legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_SIMULADOR_PRESTAMO));
			prestamoView.setLegalesSimuladorPrestamo2(
					legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_SIMULADOR_PRESTAMO_2));
			prestamoView.setLegalesSimuladorPrestamoConfirmacion(
					legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_SIMULADOR_PRESTAMO_CONFIRMACION));
			prestamoView.setLegalesSimuladorPrestamoConfirmacion2(legalBO
					.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_SIMULADOR_PRESTAMO_CONFIRMACION_ADVANCE));
			prestamoView.setLegalesTerminosYCondiciones(
					legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGALES_PPP_UVA_TERMINOS_Y_CONDICIONES));
			prestamoView.setLegalAplazamientoDeVencimientos(
					legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_APLAZAMIENTO_VENCIMIENTOS));
			prestamoView.setConfiguracionSimulador(simuladorPrestamoManager.obtenerDatosSimulacion());

			if(sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
				prestamoView.setAyudaLineaPreaprobado(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRESTAMO_PREAPROBADO_AYUDA_LINEA).getMensaje());
				prestamoView.setMensajeAumentoLineaCrediticia(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRESTAMO_PREAPROBADO_AUMENTO_LINEA).getMensaje());
				prestamoView.setMensajeAyudaPreaprobado(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_AYUDA_PRESTAMO_PREAPROBADO).getMensaje());
			}

			if (prestamoView.errorConLegales()) {
				throw new LegalesException();
			}
		} catch (LegalesException le) {
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
			item.setMensaje(mensaje.getMensaje());
			item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			prestamoView = crearCabeceraPrestamosViewConError(cliente.obtenerPrestamosDisponibles());
			prestamoView.setConfiguracionSimulador(simuladorPrestamoManager.obtenerDatosSimulacion());
			prestamoView.getConfiguracionSimulador().setEstadoRespuesta(EstadoRespuesta.ERROR);
			prestamoView.getConfiguracionSimulador().add(item);
			respuesta = respuestaFactory.crearRespuestaOk(CabeceraPrestamosView.class, prestamoView);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
			item.setMensaje(mensaje.getMensaje());
			item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			prestamoView = crearCabeceraPrestamosViewConError(cliente.obtenerPrestamosDisponibles());
			prestamoView.setConfiguracionSimulador(simuladorPrestamoManager.obtenerDatosSimulacion());
			prestamoView.getConfiguracionSimulador().setEstadoRespuesta(EstadoRespuesta.ERROR);
			prestamoView.getConfiguracionSimulador().add(item);
			estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_AGENDA_PRESTAMOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaOk(CabeceraPrestamosView.class, prestamoView);
		}

		PrestamoSueldoTasaSubsidiadaEntity datosPrestamo = prestamoBO.consultarPrestamoATPVigente(cliente);
		if (!(PRESTAMO_SUELDOS_ERROR.equals(datosPrestamo.getEstado()) ||
				PRESTAMO_SUELDOS_INHABILITADO.equals(datosPrestamo.getEstado()) ||
						TOMADO.equals(datosPrestamo.getEstado()))) {
			if (PRESTAMO_ATP6.equals(datosPrestamo.getPeriodo())) {
				respuesta.getRespuesta().setTipoDeATP(NUMERO_ATP6);
				sesionParametros.setTipoDePrestamoATP(NUMERO_ATP6);
			} else if (PRESTAMO_ATP7.equals(datosPrestamo.getPeriodo())) {
				respuesta.getRespuesta().setTipoDeATP(NUMERO_ATP7);
				sesionParametros.setTipoDePrestamoATP(NUMERO_ATP7);
			} else if (PRESTAMO_ATP8.equals(datosPrestamo.getPeriodo())) {
				respuesta.getRespuesta().setTipoDeATP(NUMERO_ATP8);
				sesionParametros.setTipoDePrestamoATP(NUMERO_ATP8);
			} else {
				respuesta.getRespuesta().setTipoDeATP(NUMERO_ATP9);
				sesionParametros.setTipoDePrestamoATP(NUMERO_ATP9);
			}
		} else {
			respuesta.getRespuesta().setTipoDeATP(NUMERO_ATP9);
			sesionParametros.setTipoDePrestamoATP(NUMERO_ATP9);
		}
		return respuesta;
	}


	/**
	 * Notificar acceso desde home.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#notificarAccesoDesdeHome()
	 */
	@Override
	public Respuesta<Void> notificarAccesoDesdeHome() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_DESDE_HOME_PRESTAMOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Metodo del manager que manda al BO la decision de la validacion crediticia.
	 *
	 * @param respuesta         the respuesta
	 * @param cantidadPrestamos the cantidad prestamos
	 * @return Respuesta<PrestamosView>
	 */
	private Respuesta<PrestamosView> validarLineaCrediticia(Respuesta<PrestamosView> respuesta, int cantidadPrestamos) {

		LOGGER.info("Valido linea crediticia del cliente");

		Cliente cliente = sesionCliente.getCliente();
//OJO hacerlo para todas las cuentas
		CalificacionCrediticiaDTO calificacionCrediticiaDTO = null;
		if ((sesionParametros.getCalificacionesCrediticiasDTO() != null)
				&& (sesionParametros.getCalificacionesCrediticiasDTO().size() > 0)) {
			calificacionCrediticiaDTO = sesionParametros.getCalificacionesCrediticiasDTO().get(0);
		}
		Respuesta<CalificacionCrediticiaDTO> respuestaCalificacion = prestamoBO
				.validarLineaCrediticia(calificacionCrediticiaDTO, cantidadPrestamos, cliente);

		if (!EstadoRespuesta.OK.equals(respuestaCalificacion.getEstadoRespuesta())) {
			respuesta.setEstadoRespuesta(respuestaCalificacion.getEstadoRespuesta());
			if (respuestaCalificacion.getItemsMensajeRespuesta() != null) {
				respuesta.addAll(respuestaCalificacion.getItemsMensajeRespuesta());
				revisarSiHayErrorRedundante(respuesta.getItemsMensajeRespuesta());
			}
		}
		return respuesta;
	}

	/**
	 * Revisar si hay error redundante.
	 *
	 * @param mensajesError the mensajes error
	 */
	private void revisarSiHayErrorRedundante(List<ItemMensajeRespuesta> mensajesError) {
		boolean errorParcial = false;
		boolean errorTotal = false;
		int posicion = 0;

		for (ItemMensajeRespuesta itemMensaje : mensajesError) {
			if (itemMensaje.getTipoError().equals(TipoError.ERROR_PARCIAL_BUSCAR_PRESTAMOS.getDescripcion())) {
				errorParcial = true;
			}
		}

		for (ItemMensajeRespuesta itemMensaje : mensajesError) {
			if (itemMensaje.getTipoError().equals(TipoError.ERROR_BUSCAR_PRESTAMOS.getDescripcion())) {
				errorTotal = true;
			}
		}

		if (errorParcial && errorTotal) {
			for (ItemMensajeRespuesta itemMensaje : mensajesError) {
				if (itemMensaje.getTipoError().equals(TipoError.ERROR_PARCIAL_BUSCAR_PRESTAMOS.getDescripcion())) {
					posicion = mensajesError.indexOf(itemMensaje);
				}
			}
			mensajesError.remove(posicion);
		}
	}

	/**
	 * Gets the detalle prestamos.
	 *
	 * @param consultaPrestamo    the consulta prestamo
	 * @param desdeInicioPrestamo the desde inicio prestamo
	 * @return the detalle prestamos
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#getDetallePrestamos(java.lang.String,
	 * java.lang.Boolean)
	 */
	@Override
	public Respuesta<DetallePrestamoView> getDetallePrestamos(ConsultaPrestamo consultaPrestamo,
			Boolean desdeInicioPrestamo) {
		return prestamoManager.getDetallePrestamos(consultaPrestamo, desdeInicioPrestamo);
	}

	/**
	 * Obtener configuracion pago cuota prestamo.
	 *
	 * @param consultaPrestamo    the consulta prestamo
	 * @param desdeInicioPrestamo the desde inicio prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#obtenerConfiguracionPagoCuotaPrestamo(ar.com.
	 * santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo,
	 * java.lang.Boolean)
	 */
	@Override
	public Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(
			ConsultaPrestamo consultaPrestamo, Boolean desdeInicioPrestamo) {
		return prestamoManager.obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, desdeInicioPrestamo);
	}

	/**
	 * Obtener prestamos cancelados.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#obtenerPrestamosCancelados()
	 */
	@Override
	public Respuesta<PrestamosCanceladosView> obtenerPrestamosCancelados() {
		List<PrestamoCanceladoDTO> prestamos = null;
		try {
			prestamos = prestamoBO.obtenerPrestamoCancelado(sesionCliente.getCliente());
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener prestamos cancelados: ", e);
			return respuestaFactory.crearRespuestaError(PrestamosCanceladosView.class, null, null,
					TipoError.ERROR_GENERICO, ERROR_GENERICO_PRESTAMOS_CANCELADOS);
		}

		if (CollectionUtils.isEmpty(prestamos)) {
			estadisticaManager.add(EstadisticasConstants.INICIO_GRILLA_PRESTAMOS_CANCELADOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaWarning(null, TipoError.WARNING, SIN_PRESTAMOS_CANCELADOS);
		}

		PrestamosCanceladosView prestamosCanceladosView = new PrestamosCanceladosView();
		for (PrestamoCanceladoDTO prestamo : prestamos) {
			prestamosCanceladosView.agregarPrestamo(prestamo);
		}

		estadisticaManager.add(EstadisticasConstants.INICIO_GRILLA_PRESTAMOS_CANCELADOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(PrestamosCanceladosView.class, prestamosCanceladosView);
	}

	/**
	 * Ver detalle prestamo.
	 *
	 * @param consultaPrestamo the consulta prestamo
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#verDetallePrestamo(ar.com.santanderrio.obp.
	 * servicios.pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<DetalleCuotaPrestamoView> verDetallePrestamo(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.verDetallePrestamo(consultaPrestamo);
	}

	/**
	 * Descargar PDF calculador prestamos.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#descargarPDFCalculadorPrestamos()
	 */
	@Override
	public Respuesta<ReporteView> descargarPDFCalculadorPrestamos() {
		Respuesta<Reporte> reporte = prestamoBO.obtenerReportePdf(sesionParametros.getReportSimulacion(),
				sesionParametros.getComprobantePPPIsUva());
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_CALCULADOR_PRESTAMOS_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_CALCULADOR_PRESTAMOS_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Descargar cuotas pagas PDF.
	 *
	 * @param proximaCuotaView the proxima cuota view
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#descargarCuotasPagasPDF(ar.com.santanderrio.obp.
	 * servicios.prestamos.view.ProximaCuotaView)
	 */
	@Override
	public Respuesta<ReporteView> descargarCuotasPagasPDF(ProximaCuotaView proximaCuotaView) {
		Respuesta<Reporte> reporte = prestamoBO.obtenerReportePdf(proximaCuotaView);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_DETALLE_CUOTA_PAGA_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_DETALLE_CUOTA_PAGA_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Descargar comprobante detalle prestamo.
	 *
	 * @param detalleCuotaPrestamoView the detalle cuota prestamo view
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager
	 * #descargarComprobanteDetallePrestamo(ar.com.santanderrio.obp.servicios.
	 * prestamos.view.DetalleCuotaPrestamoView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteDetallePrestamo(
			DetalleCuotaPrestamoView detalleCuotaPrestamoView) {
		Respuesta<Reporte> reporte = prestamoBO.obtenerReportePdf(detalleCuotaPrestamoView);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_DETALLE_CUOTA_PAGA_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_DETALLE_CUOTA_PAGA_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Descargar proximas cuotas PDF.
	 *
	 * @param proximaCuotaView the proxima cuota view
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * InicioPrestamoManager#descargarProximasCuotasPDF(ar.com.santanderrio.obp.
	 * servicios.prestamos.view.CuotaView)
	 */
	@Override
	public Respuesta<ReporteView> descargarProximasCuotasPDF(CuotaView proximaCuotaView) {
		Respuesta<Reporte> reporte = prestamoBO.obtenerReportePdf(proximaCuotaView);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Descargar detalle prestamo PDF.
	 *
	 * @param detallePrestamosView the detalle prestamos view
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager
	 * #descargarDetallePrestamoPDF(ar.com.santanderrio.obp.servicios.prestamos.view
	 * .DetallePrestamosView)
	 */
	@Override
	public Respuesta<ReporteView> descargarDetallePrestamoPDF(DetallePrestamosView detallePrestamosView) {
		Respuesta<Reporte> reporte = prestamoBO.obtenerReportePdf(detallePrestamosView);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Obtengo la calificacion crediticia de la cuenta principal, si es que existe
	 * sino retorna null.
	 *
	 * @param calificaciones the calificaciones
	 * @return the calificacion principal
	 */
	private CalificacionCrediticiaDTO getCalificacionPrincipal(List<CalificacionCrediticiaDTO> calificaciones) {

		for (CalificacionCrediticiaDTO calificacion : calificaciones) {

			LOGGER.info("MALUG-2276 La cuenta {} es cuenta principal {}, con calificacion: {}", calificacion.getCuenta(), calificacion.getCuenta().esCuentaPrincipal(), calificacion);
			if (calificacion.getCuenta().esCuentaPrincipal()) {
				return calificacion;
			}
		}
		return null;
	}

	/**
	 * Obtener cuentas view pesos.
	 *
	 * @param cuentasPesos the cuentas pesos
	 * @return the list
	 */
	private List<CuentaView> obtenerCuentasViewPesos(List<Cuenta> cuentasPesos) {
		List<CuentaView> cuentasViewPesos = new ArrayList<CuentaView>();
		for (int i = 0; i < cuentasPesos.size(); i++) {
			int tipoCuenta = Integer.valueOf(cuentasPesos.get(i).getTipoCuenta());
			CuentaView cuentaView = new CuentaView();
			cuentaView.setId(i);
			cuentaView.setAlias(cuentasPesos.get(i).getAlias());
			cuentaView.setDescripcionTipoCuenta(TipoCuenta.fromCodigo(tipoCuenta).getDescripcionConMoneda());
			cuentaView.setNumero(CUENTA.concat(ESPACIO).concat(ISBANStringUtils
					.formatearSucursal(cuentasPesos.get(i).getNroSucursal()).concat(ISBANStringUtils.GUION_STRING)
					.concat(ISBANStringUtils.formatearNumeroCuenta(cuentasPesos.get(i).getNroCuentaProducto()))));
			if (TipoCuenta.CUENTA_UNICA.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasPesos.get(i).getSaldoCUPesos())));
				cuentaView.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda());
			} else if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo() == tipoCuenta
					|| TipoCuenta.CAJA_AHORRO_PESOS.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasPesos.get(i).getSaldoCuenta())));
			}
			cuentasViewPesos.add(cuentaView);
		}
		return cuentasViewPesos;
	}

	/**
	 * Obtener cuenta.
	 *
	 * @param cuentas      the cuentas
	 * @param numeroCuenta the numero cuenta
	 * @return the cuenta
	 */
	protected Cuenta obtenerCuenta(List<Cuenta> cuentas, String numeroCuenta) {
		for (Cuenta cuenta : cuentas) {
			if (ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()).equals(numeroCuenta)) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Adjuntar archivo prestamo sueldos.
	 *
	 * @param documentacionAdjuntaView the documentacion adjunta view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ArchivoAdjuntoPrestamoSueldosView> adjuntarArchivoPrestamoSueldos(
			DocumentacionAdjuntaView documentacionAdjuntaView) {
		// valido el tamanio del archivo y el tipo
		if (documentacionAdjuntaView.getArchivos().get(0).getByteArray() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ARCHIVO_VACIO, "");
		}
		if (!this.validarMaxFileSizeAllowed(documentacionAdjuntaView.getArchivos().get(0).getByteArray())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIZE_EXCEDIDO,
					CodigoMensajeConstantes.TAMANIO_MAXIMO_EXCEDIDO);
		}
		if (!this.validarTipoDeArchivoValido(documentacionAdjuntaView.getArchivos().get(0).getTipoArchivo())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_EXTENSION_INVALIDA,
					CodigoMensajeConstantes.EXTENSION_INVALIDA);
		}

		String nombreArchivo = "";
		AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
		adjuntarArchivosInDto.setArchivo(documentacionAdjuntaView.getArchivos().get(0));

		if (AFIP_PRESTAMO_SUELDOS.equals(adjuntarArchivosInDto.getArchivo().getId())) {
			nombreArchivo = sesionCliente.getCliente().getNup() + FORM_AFIP_PRESTAMO_SUELDOS;
		} else if (PYME_PRESTAMO_SUELDOS.equals(adjuntarArchivosInDto.getArchivo().getId())) {
			nombreArchivo = sesionCliente.getCliente().getNup() + FORM_PYME_PRESTAMO_SUELDOS;
		}

		adjuntarArchivosInDto.getArchivo().setNombre(nombreArchivo);

		// verifico si el archivo tiene virus
		Respuesta<Boolean> respuesta = this.prestamoBO.verificarArchivoPrestamoSueldo(adjuntarArchivosInDto);
		// si tiene virus se devuelve error
		if (!respuesta.getRespuesta()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDAR_ARCHIVO_VIRUS, "");
		}

		boolean rsp = respuesta.getRespuesta().booleanValue();

		if (rsp) {
			ArchivoAdjuntoPrestamoSueldosView archivoAdjuntoPrestamoSueldosView = new ArchivoAdjuntoPrestamoSueldosView();
			archivoAdjuntoPrestamoSueldosView.setArchivo(documentacionAdjuntaView.getArchivos().get(0));
			return respuestaFactory.crearRespuestaOk(archivoAdjuntoPrestamoSueldosView);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ADJUNTAR_ARCHIVO,
					CodigoMensajeConstantes.ERROR_SERVICIO_ADJUNTAR);
		}
	}

	@Override
	public Respuesta<Void> borrarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView) {
		String nombreArchivo = "";
		AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
		adjuntarArchivosInDto.setArchivo(documentacionAdjuntaView.getArchivos().get(0));

		if (AFIP_PRESTAMO_SUELDOS.equals(adjuntarArchivosInDto.getArchivo().getId())) {
			nombreArchivo = sesionCliente.getCliente().getNup() + FORM_AFIP_PRESTAMO_SUELDOS;
		} else if (PYME_PRESTAMO_SUELDOS.equals(adjuntarArchivosInDto.getArchivo().getId())) {
			nombreArchivo = sesionCliente.getCliente().getNup() + FORM_PYME_PRESTAMO_SUELDOS;
		}

		adjuntarArchivosInDto.getArchivo().setNombre(nombreArchivo);

		Respuesta<Boolean> responseBorrarDoc = this.prestamoBO.borrarDoc(adjuntarArchivosInDto);
		if (responseBorrarDoc.getRespuesta()) {
			return this.respuestaFactory.crearRespuestaOk(null);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ELIMINAR_ARCHIVO_ADJUNTO,
					CodigoMensajeConstantes.ERROR_SERVICIO_ELIMINAR_ADJUNTO);
		}
	}

	/**
	 * Validar max file size allowed.
	 *
	 * @param archivo the archivo
	 * @return true, if successful
	 */
	private boolean validarMaxFileSizeAllowed(byte[] archivo) {
		try {
			Integer tamanioMaximoArchivo = Integer.parseInt(this.maxFileSize);
			Integer tamanioArchivoEnKb = (int) Math.ceil((double) archivo.length / 1024);
			if (tamanioArchivoEnKb <= tamanioMaximoArchivo) {
				return true;
			} else {
				LOGGER.error("Error el archivo a adjuntar supera el tamaño maximo permitido de: " + tamanioArchivoEnKb);

			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * Validar tipo de archivo valido.
	 *
	 * @param tipoArchivo the tipo archivo
	 * @return true, if successful
	 */
	private boolean validarTipoDeArchivoValido(String tipoArchivo) {
		String archivosPermitidos[] = this.tiposDeArchivosPermitidos.split(",");
		for (int i = 0; i < archivosPermitidos.length; i++) {
			if (archivosPermitidos[i].equals(tipoArchivo)) {
				return true;
			}
		}
		LOGGER.error("Error el archivo a adjuntar no posee un tipo permitido");

		return false;
	}

	@Override
	public Respuesta<ComprobanteStopDebitPrestamoView> confirmarPrestamosStopDebit(ConfirmacionStopDebitView confirmacionStopDebitView) {
		Respuesta<ComprobanteStopDebitPrestamoView> respuesta = new Respuesta<ComprobanteStopDebitPrestamoView>();
		ConfirmacionStopDebitDTO dto = new ConfirmacionStopDebitDTO();
		dto.setPeriodosStopDebitPrestamos(confirmacionStopDebitView.getPeriodosStopDebitPrestamos());
		Respuesta<CompStopDebitPrestamoOutEntity> comp = prestamoBO.confirmarPrestamosStopDebit(dto, sesionCliente);
		ComprobanteStopDebitPrestamoView viewRsp = new ComprobanteStopDebitPrestamoView();
		if (comp.getRespuesta() != null ) {
			viewRsp.setComprobante(comp.getRespuesta().getComprobante());
			viewRsp.setFecha(comp.getRespuesta().getFecha());
			viewRsp.setLegalStopDebitPrestamos(confirmacionStopDebitView.getLegalStopDebitPrestamos());
			viewRsp.setPeriodosStopDebitPrestamos(confirmacionStopDebitView.getPeriodosStopDebitPrestamos());
			respuesta = respuestaFactory.crearRespuestaOk(viewRsp);
        	estadisticaManager.add(EstadisticasConstants.COMPROBANTE_STOPDEBIT_PRESTAMOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    	} else {
            respuesta = respuestaFactory.crearRespuestaError(ComprobanteStopDebitPrestamoView.class, "", "");
            estadisticaManager.add(EstadisticasConstants.COMPROBANTE_STOPDEBIT_PRESTAMOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
		return respuesta;
	}

	@Override
	public Respuesta<ReporteView> descargarCompStopDebitPrestamo(ComprobanteStopDebitPrestamoView comprobanteStopDebitPrestamoView) {
		Respuesta<Reporte> reporte = prestamoBO.descargarCompStopDebitPrestamo(comprobanteStopDebitPrestamoView);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> consultarPrestamoATPVigente() {

		PrestamoSueldoTasaSubsidiadaView prestamoSueldoView = new PrestamoSueldoTasaSubsidiadaView();
		Cliente cliente = sesionCliente.getCliente();
		PrestamoSueldoTasaSubsidiadaEntity datosPrestamo = prestamoBO.consultarPrestamoATPVigente(cliente);

		if (PRESTAMO_SUELDOS_ERROR.equals(datosPrestamo.getEstado())
				|| PRESTAMO_SUELDOS_INHABILITADO.equals(datosPrestamo.getEstado())) {  // NO TIENE SOLICITUD CARGADA

			estadisticaManager.add(EstadisticasConstants.ERROR_SIN_SOL_CARGADA_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_SOLICITAR_PRESTAMO_PAGO_SUELDOS_SUBSIDIADO);
		} else if(TOMADO.equalsIgnoreCase(datosPrestamo.getEstado())) { // TIENE PRESTAMO EN PROCESO

			estadisticaManager.add(EstadisticasConstants.ERROR_YA_SOLICITADO_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_PRESTAMO_TASA_SUBSIDIADA_SOLICITADO,
					CodigoMensajeConstantes.FEEDBACK_ERROR_PRESTAMO_TASA_SUBSIDIADA_SOLICITADO);
		}
		ComprobantePrestamoTasaSubsidiadaView comprobante = new ComprobantePrestamoTasaSubsidiadaView();
		comprobante.setIdPrestamos(datosPrestamo.getId());
		comprobante.setIdSolicitud(datosPrestamo.getSolicitud());
		comprobante.setDatos(datosPrestamo.getDatos());
		sesionParametros.setComprobantePrestamoTasaSub(comprobante);

		prestamoSueldoView.setMensajeAyudaCbu(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.AYUDA_CBU_PRESTAMO_TASA_SUBSIDIADA).getMensaje());
		prestamoSueldoView.setLegalConformidad(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFORMIDAD_PRESTAMO_TASA_SUBSIDIADA));
		prestamoSueldoView.setMensajeCbusIncompletos(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.WARNING_EMPLEADOS_SIN_CBU_PRESTAMO_TASA_SUBSIDIADA).getMensaje());
		prestamoSueldoView.setMail(cliente.getEmailLoginMya() != null ? cliente.getEmailLoginMya() : StringUtils.EMPTY);
		prestamoSueldoView.setTasaInteres(datosPrestamo.getTasa().toString());
		if (cliente.getCuentas() == null) {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS);
		}

		prestamoSueldoView.setListaCuentasPesos(cargarCuentasCliente());
		prestamoSueldoView.setListaEmpleados(armarListaEmpleados(datosPrestamo, prestamoSueldoView));

		int empleadosSinCbu = 0;
		for (InfoEmpleadoPrestamoTasaSubsidiada empleado : prestamoSueldoView.getListaEmpleados()) {
			BigDecimal bigCbu = BigDecimal.ZERO;
			if (StringUtils.isNotEmpty(empleado.getCbu())) {
				bigCbu = new BigDecimal(empleado.getCbu());
			}
			if (StringUtils.isEmpty(empleado.getCbu()) || BigDecimal.ZERO.compareTo(bigCbu) == 0) {
				prestamoSueldoView.setEmpleadoSinCBU(Boolean.TRUE);
				empleadosSinCbu++;
			}
		}

		if (prestamoSueldoView.getEmpleadoSinCBU()) {
			estadisticaManager.add(EstadisticasConstants.INGRESO_CARGA_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		prestamoSueldoView.setMensajeInformativoConfiguracionSinCbu(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.WARNING_EMPLEADO_SIN_CUENTA_PRESTAMO_TASA_SUBSIDIADA).getMensaje());

		revisarATP(datosPrestamo, prestamoSueldoView);

		prestamoSueldoView.setCantEmpleadosSinCbu(empleadosSinCbu);

		return respuestaFactory.crearRespuestaOk(prestamoSueldoView);
	}

	private void revisarATP(PrestamoSueldoTasaSubsidiadaEntity datosPrestamo, PrestamoSueldoTasaSubsidiadaView prestamoSueldoView) {

		if (PRESTAMO_ATP6.equals(datosPrestamo.getPeriodo())) {
			prestamoSueldoView.setMensajeInformativoConfiguracion(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIGURACION_PRESTAMO_TASA_SUBSIDIADA), "2", "tercer", prestamoSueldoView.getTasaInteres()));
			prestamoSueldoView.setTyc(urlPrestamoTasaSubsidiadaTyCATP6);
			prestamoSueldoView.setTipoDeATP("ATP6");
			prestamoSueldoView.setMensajeLegalTasasConfirmacion(armarLegalesTasas(prestamoSueldoView.getTipoDeATP(), datosPrestamo.getTasa()));
		} else if (PRESTAMO_ATP7.equals(datosPrestamo.getPeriodo())){
			prestamoSueldoView.setMensajeInformativoConfiguracion(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIGURACION_PRESTAMO_TASA_SUBSIDIADA), "3", "cuarto", prestamoSueldoView.getTasaInteres()));
			prestamoSueldoView.setTyc(urlPrestamoTasaSubsidiadaTyCATP7);
			prestamoSueldoView.setTipoDeATP("ATP7");
			prestamoSueldoView.setMensajeLegalTasasConfirmacion(armarLegalesTasas(prestamoSueldoView.getTipoDeATP(), datosPrestamo.getTasa()));
		} else if (PRESTAMO_ATP8.equals(datosPrestamo.getPeriodo())) {
			prestamoSueldoView.setMensajeInformativoConfiguracion(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIGURACION_PRESTAMO_TASA_SUBSIDIADA), "3", "cuarto", prestamoSueldoView.getTasaInteres()));
			prestamoSueldoView.setTyc(new BigDecimal(27).compareTo(datosPrestamo.getTasa()) == 0 ? urlPrestamoTasaSubsidiadaTyCATP8Tasa27 : urlPrestamoTasaSubsidiadaTyCATP8Tasa33);
			prestamoSueldoView.setTipoDeATP("ATP8");
			prestamoSueldoView.setMensajeLegalTasasConfirmacion(armarLegalesTasas(prestamoSueldoView.getTipoDeATP(), datosPrestamo.getTasa()));
		}  else {
			prestamoSueldoView.setMensajeInformativoConfiguracion(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIGURACION_PRESTAMO_TASA_SUBSIDIADA), "3", "cuarto", prestamoSueldoView.getTasaInteres()));
			prestamoSueldoView.setTyc(new BigDecimal(27).compareTo(datosPrestamo.getTasa()) == 0 ? urlPrestamoTasaSubsidiadaTyCATP9Tasa27 : urlPrestamoTasaSubsidiadaTyCATP9Tasa33);
			prestamoSueldoView.setTipoDeATP("ATP9");
			prestamoSueldoView.setMensajeLegalTasasConfirmacion(armarLegalesTasas(prestamoSueldoView.getTipoDeATP(), datosPrestamo.getTasa()));
		}

	}

	private String armarLegalesTasas(String tipoDeATP, BigDecimal tasa) {

		StringBuilder legalSB = new StringBuilder();

		String tasaBonificada = "(*) Para tasas bonificadas:";

		legalSB.append(tasaBonificada);

		if ("ATP6".equals(tipoDeATP)) {
			String legalTasaAtp6 = MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIRMACION_PRESTAMO_TASA_SUBSIDIADA), "15", TASAS_INTERES_15, TASAS_INTERES_15);
			legalSB.append(legalTasaAtp6);
		} else if ("ATP7".equals(tipoDeATP)) {
			String legalTasaAtp7Tasa27 = MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIRMACION_PRESTAMO_TASA_SUBSIDIADA), "27", TASAS_INTERES_27, TASAS_INTERES_27);
			String legalTasaAtp7Tasa33 = MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIRMACION_PRESTAMO_TASA_SUBSIDIADA), "33", TASAS_INTERES_33, TASAS_INTERES_33);
			legalSB.append(legalTasaAtp7Tasa27);
			legalSB.append(legalTasaAtp7Tasa33);
		} else if (new BigDecimal(27).compareTo(tasa) == 0){
			String legalTasaAtp7Tasa27 = MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIRMACION_PRESTAMO_TASA_SUBSIDIADA), "27", TASAS_INTERES_27, TASAS_INTERES_27);
			legalSB.append(legalTasaAtp7Tasa27);
		} else {
			String legalTasaAtp7Tasa33 = MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_CONFIRMACION_PRESTAMO_TASA_SUBSIDIADA), "33", TASAS_INTERES_33, TASAS_INTERES_33);
			legalSB.append(legalTasaAtp7Tasa33);
		}

		return legalSB.toString();

	}

	private List<CuentaView> cargarCuentasCliente() {

		List<CuentaView> listaCuentasPesos = new ArrayList<CuentaView>();
		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
			if (TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ||
				TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ||
				TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar()))) {

				CuentaView cuentaPesos = new CuentaView();
				cuentaPesos.setAlias(cuenta.getAlias());
				cuentaPesos.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
				cuentaPesos.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
				cuentaPesos.setSaldoPesos(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ? ISBANStringUtils.formatearConComaYDosDecimales(cuenta.getSaldoCUPesos()) : ISBANStringUtils.formatearConComaYDosDecimales(cuenta.getSaldoCuenta()));
				listaCuentasPesos.add(cuentaPesos);
			}
		}
		return listaCuentasPesos;
	}

	private List<InfoEmpleadoPrestamoTasaSubsidiada> armarListaEmpleados(PrestamoSueldoTasaSubsidiadaEntity datosPrestamo, PrestamoSueldoTasaSubsidiadaView prestamoSueldoView) {

		BigDecimal montoTotal = new BigDecimal("0");
		List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados = new ArrayList<InfoEmpleadoPrestamoTasaSubsidiada>();
		for (InfoEmpleadoPrestamoTasaSubsidiada item : datosPrestamo.getEmpleados()) {
			BigDecimal bigCbu = BigDecimal.ZERO;
			if (StringUtils.isNotEmpty(item.getCbu())) {
				bigCbu = new BigDecimal(item.getCbu());
			}

			if (!BigDecimal.ZERO.toString().equals(item.getMonto())) {
				if (StringUtils.isNotEmpty(item.getCbu()) && BigDecimal.ZERO.compareTo(bigCbu) != 0) {
					montoTotal = montoTotal.add(new BigDecimal(item.getMonto()));
				}
				InfoEmpleadoPrestamoTasaSubsidiada empleado = new InfoEmpleadoPrestamoTasaSubsidiada();
				empleado.setId(item.getId());
				empleado.setCbu(StringUtils.isEmpty(item.getCbu()) || BigDecimal.ZERO.compareTo(bigCbu) == 0  ? "0" : item.getCbu());
				empleado.setCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(item.getCuit()));
				empleado.setMonto(PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(item.getMonto())));
				listaEmpleados.add(empleado);
			}
		}

		Collections.sort(listaEmpleados);

		for (InfoEmpleadoPrestamoTasaSubsidiada item : listaEmpleados) {
			item.setCbu(BigDecimal.ZERO.toString().equals(item.getCbu()) ? null : item.getCbu());
		}

		String mensajeMontoPrestamo = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TITULO_PRESTAMO_TASA_SUBSIDIADA_MONTO).getMensaje();

		String montoString = ISBANStringUtils.formatearSaldoSinAbs(montoTotal);
		sesionParametros.getComprobantePrestamoTasaSub().setMonto(montoTotal);
		sesionParametros.getComprobantePrestamoTasaSub().setMontoFormateado(montoString);
		prestamoSueldoView.setMontoFormateado(PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(montoTotal)));
		prestamoSueldoView.setMensajeMontoTotalPrestamo(MessageFormat.format(mensajeMontoPrestamo, prestamoSueldoView.getMontoFormateado()));
		return listaEmpleados;
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> getPreparacionPrestamoTasaSubsidiada() {
		PrestamoSueldoTasaSubsidiadaView prestamoSueldoView = new PrestamoSueldoTasaSubsidiadaView();
		String tipoPrestamoATP = sesionParametros.getTipoDePrestamoATP();

		if(NUMERO_ATP6.equals(tipoPrestamoATP)) {
			prestamoSueldoView.setLegalInicio(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_INICIO_PRESTAMO_TASA_SUBSIDIADA), NUMERO_ATP6));
			prestamoSueldoView.setTipoDeATP(NUMERO_ATP6);
		} else if (NUMERO_ATP7.equals(tipoPrestamoATP)) {
			prestamoSueldoView.setLegalInicio(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_INICIO_PRESTAMO_TASA_SUBSIDIADA), NUMERO_ATP7));
			prestamoSueldoView.setTipoDeATP(NUMERO_ATP7);
		} else if (NUMERO_ATP8.equals(tipoPrestamoATP)) {
			prestamoSueldoView.setLegalInicio(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_INICIO_PRESTAMO_TASA_SUBSIDIADA), NUMERO_ATP8));
			prestamoSueldoView.setTipoDeATP(NUMERO_ATP8);
		} else {
			prestamoSueldoView.setLegalInicio(MessageFormat.format(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_INICIO_PRESTAMO_TASA_SUBSIDIADA), NUMERO_ATP9));
			prestamoSueldoView.setTipoDeATP(NUMERO_ATP9);
		}
		estadisticaManager.add(EstadisticasConstants.INICIO_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(prestamoSueldoView);
	}

	@Override
	public Respuesta<Void> grabarEstadisticaConfiguracionPrestamoTasaSubsidiada() {
		estadisticaManager.add(EstadisticasConstants.CONFIGURACION_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return this.respuestaFactory.crearRespuestaOk(null);
	}

	@Override
	public Respuesta<Void> grabarEstadisticaConfirmacionPrestamoTasaSubsidiada() {
		estadisticaManager.add(EstadisticasConstants.CONFIRMACION_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return this.respuestaFactory.crearRespuestaOk(null);
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> agregarNuevoCBU(PrestamoSueldoTasaSubsidiadaView prestamoSueldoTasaSubsidiadaView) {
		String respuestaAgregarCBU;
		String numeroTarjetaActiva = obtenerTarjetaBanelcoActiva();
        if (numeroTarjetaActiva == null) {
        	//ERROR OBTENIENDO LA TARJETA DE DEBITO
        	estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_CBU_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	return respuestaFactory.crearRespuestaError(null, null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
        }
        ValidarCbuInDTO validarCbuInDTO = generarValidarCbuInDTO(prestamoSueldoTasaSubsidiadaView);
        if (validarCbuInDTO == null) {
        	//ERROR OBTENIENDO CUENTAS
        	estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_CBU_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
        }
        validarCbuInDTO.setNroTarjeta(numeroTarjetaActiva);
        try {
			Respuesta<ConsultaCbuEntityOut> entityOut = prestamoBO.validarCbuPrestamos(validarCbuInDTO);
			String cuitSinFormato = prestamoSueldoTasaSubsidiadaView.getCuitParaCargar().replaceAll("-", "");
			if (cuitSinFormato.equals(entityOut.getRespuesta().getCuit1()) ||
				cuitSinFormato.equals(entityOut.getRespuesta().getCuit2()) ||
				cuitSinFormato.equals(entityOut.getRespuesta().getCuit3())) {

				PrestamoSueldosAgregarCBUEntity agregarCBUEntity = new PrestamoSueldosAgregarCBUEntity();
				agregarCBUEntity.setId(prestamoSueldoTasaSubsidiadaView.getIdParaCargar());
				agregarCBUEntity.setCbu(prestamoSueldoTasaSubsidiadaView.getCbuParaCargar());
				agregarCBUEntity.setDatos(sesionParametros.getDatosPrestamoSueldosTasaSubsidiada());

				respuestaAgregarCBU = prestamoBO.agregarCBU(agregarCBUEntity);

			} else {
				//NINGUN CUIL COINCIDE CON EL DEL EMPLEADO
				estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_CBU_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.PRESTAMO_TASA_SUBSIDIADA_ERROR_CBU_NO_CORRESPONDE);
			}
		} catch (Exception e) {
			//FALLO EN LA CONSULTA
			estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_CBU_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}

        if (BigDecimal.ZERO.toString().equals(respuestaAgregarCBU)) {
        	PrestamoSueldoTasaSubsidiadaView respuestaView = new PrestamoSueldoTasaSubsidiadaView();
        	respuestaView.setMensajeFeedbackOKCBUAgregado(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PRESTAMO_TASA_SUBSIDIADA_FEEDBACK_OK_CBU_AGREGADO).getMensaje());
        	estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_CBU_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        	return respuestaFactory.crearRespuestaOk(respuestaView);
        }
        //ERROR EN LA CONSULTA
        estadisticaManager.add(EstadisticasConstants.ACTUALIZACION_CBU_PRESTAMO_TASA_SUBSIDIADA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
    }

    private String obtenerTarjetaBanelcoActiva() {
        LOGGER.info(MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA);
        List<Cuenta> listaCuentas = sesionCliente.getCliente().getCuentas();
        for (Cuenta cuenta : listaCuentas) {
            if (TARJETA_BANELCO_ACTIVA.equals(cuenta.getEstadoTarjetaCredito())) {
                int tipocuenta = TipoCuenta.fromAbreviatura(cuenta.getTipoCuentaEnum().getAbreviatura()).getCodigo();
                if (tipocuenta == TIPO_CUENTA_BANELCO) {
                    return cuenta.getNroTarjetaCredito();
                }
            }
        }
        LOGGER.info(MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS);
        return null;
    }

    private ValidarCbuInDTO generarValidarCbuInDTO(PrestamoSueldoTasaSubsidiadaView prestamoSueldoTasaSubsidiadaView) {
        ValidarCbuInDTO validarCbuInDTO = new ValidarCbuInDTO();
        validarCbuInDTO.setCbuDestino(prestamoSueldoTasaSubsidiadaView.getCbuParaCargar());
        try {
        	Cuenta cuentaCliente = new Cuenta();
    		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
    			if (TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ||
    				TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ||
    				TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar()))) {
    				cuentaCliente = cuenta;
    				break;
    			}
    		}
            validarCbuInDTO.setNroCuenta(cuentaCliente.getNroCuentaProducto());
            validarCbuInDTO.setTipoCuenta(cuentaCliente.getTipoCuenta());
            validarCbuInDTO.setNroSucursal(cuentaCliente.getNroSucursal());
            return validarCbuInDTO;
        } catch (Exception e) {
            LOGGER.error("ERROR obteniendo cuenta con moneda ingresada");
            return null;
        }
    }

	@Override
	public Respuesta<ReporteView> descargarPrestamoTasaSubsidiada() {
		ComprobantePrestamoTasaSubsidiadaView comprobante = sesionParametros.getComprobantePrestamoTasaSub();
		Respuesta<Reporte> reporte = prestamoBO.descargarCompPrestamoTasaSub(comprobante);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> confirmarPrestamoTasaSubsidiada(
			PrestamoSueldoTasaSubsidiadaView prestamoSueldoView) {

		Cliente cliente = sesionCliente.getCliente();
		// Buscamos la cuenta seleccionada en sesion
		String[] cuentaPartes = prestamoSueldoView.getCuentaSeleccionada().getNumero().split("\\-");
		Cuenta cuenta = cliente.getCuentaSiContieneNumero(cuentaPartes[1].replaceAll("/", ""));

		recalcularMonto(prestamoSueldoView);

		Respuesta<String> respuesta = prestamoBO.altaPrestamoSueldoTasaSubsidiada(prestamoSueldoView, cliente, cuenta);

		if(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

			prestamoSueldoView.setNroComprobante(respuesta.getRespuesta());
			prestamoSueldoView.setMontoFormateado(PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(sesionParametros.getComprobantePrestamoTasaSub().getMonto())));

			// guardamos comprobante en sesion
			sesionParametros.setComprobantePrestamoTasaSub(armarComprobante(prestamoSueldoView));

			// buscamos mensaje de feedback ok
			prestamoSueldoView.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK_PRESTAMO_TASA_SUBSIDIADA).getMensaje());
			prestamoSueldoView.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
			// grabamos estadística ok
			grabarEstadistica(String.valueOf(sesionParametros.getComprobantePrestamoTasaSub().getMonto()), EstadisticasConstants.FEEDBACK_OPERACION_PRESTAMO_TASA_SUBSIDIADA, prestamoSueldoView.getNroComprobante(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

			return respuestaFactory.crearRespuestaOk(prestamoSueldoView);
		}

		// grabamos estadística error
		grabarEstadistica(String.valueOf(sesionParametros.getComprobantePrestamoTasaSub().getMonto()), EstadisticasConstants.FEEDBACK_OPERACION_PRESTAMO_TASA_SUBSIDIADA, null, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_PRESTAMO_TASA_SUBSIDIADA);
	}

	private void recalcularMonto(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView) {
		BigDecimal montoTotal = BigDecimal.valueOf(0);

		List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleadosCorregida = new ArrayList<InfoEmpleadoPrestamoTasaSubsidiada>();
		for (InfoEmpleadoPrestamoTasaSubsidiada empleado : prestamoSueldoView.getListaEmpleados()) {
			if (StringUtils.isNotEmpty(empleado.getCbu())) {
				listaEmpleadosCorregida.add(empleado);
				montoTotal = montoTotal.add(new BigDecimal(empleado.getMonto().replaceAll("\\$", "").replaceAll("\\.", "").replaceAll(",", ".")));
			}
		}

		prestamoSueldoView.setListaEmpleados(listaEmpleadosCorregida);

		String montoString = ISBANStringUtils.formatearSaldoSinAbs(montoTotal);
		sesionParametros.getComprobantePrestamoTasaSub().setMonto(montoTotal);
		sesionParametros.getComprobantePrestamoTasaSub().setMontoFormateado(montoString);
	}

	/**
	 * Devuelve el comprobante para guardar en sesion
	 * @param prestamoSueldoView
	 * @return
	 */
	private ComprobantePrestamoTasaSubsidiadaView armarComprobante(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView) {

		ComprobantePrestamoTasaSubsidiadaView comprobante = sesionParametros.getComprobantePrestamoTasaSub();
		comprobante.setCuotasDetalle(CUOTA_DETALLE_PRESTAMO_TASA_SUBS);
		comprobante.setTasaInteres(prestamoSueldoView.getTasaInteres() + PORCENTAJE);
		comprobante.setCuentaSeleccionada(prestamoSueldoView.getCuentaSeleccionada());
		comprobante.setEmailContacto(prestamoSueldoView.getMail());
		comprobante.setNroComprobante(prestamoSueldoView.getNroComprobante());
		comprobante.setListaEmpleados(prestamoSueldoView.getListaEmpleados());
		return comprobante;
	}

    /**
	 * Grabar estadistica.
	 *
	 * @param importe
	 *            the importe
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param numeroComprobante
	 *            the numero comprobante
	 * @param codigoError
	 *            the codigo error
	 */
    private void grabarEstadistica(String importe, String codigoTransaccion, String numeroComprobante, String codigoError) {
        Estadistica estadistica = new Estadistica();
        estadistica.setImporte(importe);
        estadistica.setMoneda("$");
        estadistica.setCodigoError(codigoError);
        estadistica.setCodigoTransaccion(codigoTransaccion);
        estadistica.setNroComprobante(numeroComprobante);
        try {
            estadisticaBo.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
        } catch (BusinessException e) {
            LOGGER.error("Error al intentar grabar la estadistica: {}, {}", codigoTransaccion, e);
        }
    }

	@Override
	public Respuesta<CancelacionPrestamoView> simularCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView) {
		Cliente cliente = sesionCliente.getCliente();
		List<CuentaView> listaCuentasPesos = mapCuentaViewCuentaPesos(cliente.getCuentas());

		if (listaCuentasPesos.isEmpty()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS);
		}

		Collections.sort(listaCuentasPesos);
		cancelacionView.setNumeroCuenta(listaCuentasPesos.get(listaCuentasPesos.size()-1).getNumero());

		try {
			final Respuesta<CancelarPrestamoDTO> prestamosServiceResponse = prestamoBO.consultarCancelarPrestamos(cancelacionView, true);
			final Respuesta<CancelacionPrestamoView> serviceResponse = Respuesta.copy(CancelacionPrestamoView.class, prestamosServiceResponse);

			if (EstadoRespuesta.OK.equals(prestamosServiceResponse.getEstadoRespuesta())){
				String mensajeInformativo = mensajeBO.obtenerMensajePorCodigo(
						CodigoMensajeConstantes.CANCELACION_TOTAL_REFINANCIACION_MENSAJE_INFORMATIVO).getMensaje();
				CancelarPrestamoDTO respuestaEntity = prestamosServiceResponse.getRespuesta();
				mapCuentaSinSaldo(listaCuentasPesos, respuestaEntity.getIatxResponse().getTotal());
				CancelacionPrestamoView respuestaView = CancelacionPrestamoView.builder()
						.montoTotal(formatearMonto(respuestaEntity.getIatxResponse().getTotal(), 4))
						.capital(formatearMonto(respuestaEntity.getIatxResponse().getImpCap(), 4))
						.intereses(formatearMonto(respuestaEntity.getIatxResponse().getImpInt(), 4))
						.iva(formatearMonto(respuestaEntity.getIatxResponse().getIva1(), 4))
						.ingresosBrutos(formatearMonto(respuestaEntity.getIatxResponse().getIngresosBrutos(), 4))
						.otrosImpuestos(formatearMonto(respuestaEntity.getIatxResponse().getRestoImpuestos(), 4))
						.comisiones(formatearMonto(respuestaEntity.getIatxResponse().getTotComision(), 4))
						.gastos(formatearMonto(respuestaEntity.getIatxResponse().getTotGastos(), 4))
						.seguro(formatearMonto(respuestaEntity.getIatxResponse().getTotSeguros(), 4))
						.listaCuentas(listaCuentasPesos)
						.mostrarPrimerStack(listaCuentasPesos.size() > 1)
						.mensajeInformativo(mensajeInformativo)
						.fechaCotizacionCoeficiente(StringUtils.isBlank(respuestaEntity.getIatxResponse().getFechaCotizacion()) ?
								null : respuestaEntity.getIatxResponse().getFechaCotizacion())
//						.legalOtrosImpuestos(StringUtils.isNotBlank(respuestaEntity.getIatxResponse().getUnidadExposicion()) ? legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_4) : legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_3))
						.legalOtrosImpuestos(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_3))
						.build();

				ComprobanteCancelacionTotalPrestamoView comprobanteCancelacion = new ComprobanteCancelacionTotalPrestamoView();
				comprobanteCancelacion.setMonto(respuestaView.getMontoTotal());
				comprobanteCancelacion.setNumeroPrestamo(cancelacionView.getNumeroPrestamo());
				comprobanteCancelacion.setCapital(respuestaView.getCapital());
				comprobanteCancelacion.setIntereses(respuestaView.getIntereses());
				comprobanteCancelacion.setIva(respuestaView.getIva());
				comprobanteCancelacion.setIngresosBrutos(respuestaView.getIngresosBrutos());
				comprobanteCancelacion.setOtrosImpuestos(respuestaView.getOtrosImpuestos());
				comprobanteCancelacion.setComisiones(respuestaView.getComisiones());
				comprobanteCancelacion.setGastos(respuestaView.getGastos());
				comprobanteCancelacion.setSeguro(respuestaView.getSeguro());
				comprobanteCancelacion.setMensajeRefinanciacion(mensajeInformativo);
				comprobanteCancelacion.setFechaCotizacionCoeficiente(respuestaView.getFechaCotizacionCoeficiente());
				comprobanteCancelacion.setLegalOtrosImpuestos(respuestaView.getLegalOtrosImpuestos());

				if (StringUtils.isNotBlank(respuestaEntity.getIatxResponse().getUnidadExposicion())) {
					respuestaView.setCapitalUVA(
							formatearMonto(respuestaEntity.getIatxResponse().getImporteCapital(), 4).replaceAll("\\$ ", ""));
					respuestaView.setInteresUVA(
							formatearMonto(respuestaEntity.getIatxResponse().getImporteIntereses(), 4).replaceAll("\\$ ", ""));
					respuestaView
							.setCotizacionCoeficiente(formatearMonto(respuestaEntity.getIatxResponse().getCotCambioUniExpo(), 5));
					respuestaView.setImporteTotalUVA(
							formatearMonto(respuestaEntity.getIatxResponse().getImporteTotal(), 4).replaceAll("\\$ ", ""));
					comprobanteCancelacion.setCapitalUVA(respuestaView.getCapitalUVA());
					comprobanteCancelacion.setInteresUVA(respuestaView.getInteresUVA());
					comprobanteCancelacion.setCotizacionCoeficiente(respuestaView.getCotizacionCoeficiente());
					comprobanteCancelacion.setFechaCotizacionCoeficiente(respuestaView.getFechaCotizacionCoeficiente());
				}

				sesionParametros.setComprobanteCancelacionTotalPrestamo(comprobanteCancelacion);
				serviceResponse.setRespuesta(respuestaView);

				if (listaCuentasPesos.get(listaCuentasPesos.size()-1).getSaldoInsuficiente()) {
					estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIGURACION,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					serviceResponse.setRespuesta(null);
					serviceResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
					serviceResponse.setItemMensajeRespuesta(Collections.singletonList(respuestaFactory.crearItemMensajeRespuesta(
							"", TipoError.PRESTAMO_CANCELACION_ERROR_SALDO_INSUFICIENTE,
							CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_ERROR_SALDO_INSUFICIENTE)));
				}
			}
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIGURACION,
					listaCuentasPesos.size() > 1 ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
							: EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			return serviceResponse;
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIGURACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					e.getMessage() != null ? e.getMessage() : CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS);
		}
	}

	@Override
	public Respuesta<CancelacionPrestamoView> ejecutarCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView) {
		try {
			final Respuesta<CancelarPrestamoDTO> prestamosServiceResponse = prestamoBO.consultarCancelarPrestamos(cancelacionView);
			final Respuesta<CancelacionPrestamoView> serviceResponse = Respuesta.copy(CancelacionPrestamoView.class, prestamosServiceResponse);

			if (EstadoRespuesta.OK.equals(prestamosServiceResponse.getEstadoRespuesta())){
				CancelarPrestamoDTO prestamosServiceResponseEntity = prestamosServiceResponse.getRespuesta();
				String feedbackMsg = mensajeBO.obtenerMensajePorCodigo(
						CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_FEEDBACK_OK).getMensaje();
				String mensajeInformativo = mensajeBO.obtenerMensajePorCodigo(
						CodigoMensajeConstantes.CANCELACION_TOTAL_REFINANCIACION_MENSAJE_INFORMATIVO).getMensaje();

				DateTime date = new DateTime();
				String formattedDate = date.toString("dd/MM/yyyy") + " - " + date.toString("HH:mm:ss") + " hs.";
				CancelacionPrestamoView respuestaView = CancelacionPrestamoView.builder()
						.numeroComprobante(prestamosServiceResponseEntity.getIatxResponse().getNroComprobante())
						.fechaHora(formattedDate)
						.mensajeFeedbackOK(feedbackMsg)
						.mensajeInformativo(mensajeInformativo)
						.fechaCotizacionCoeficiente(StringUtils.isBlank(prestamosServiceResponseEntity.getIatxResponse().getFechaCotizacion()) ?
							null : prestamosServiceResponseEntity.getIatxResponse().getFechaCotizacion())
						.build();

				ComprobanteCancelacionTotalPrestamoView comprobanteCancelacion = sesionParametros.getComprobanteCancelacionTotalPrestamo();
				comprobanteCancelacion.setNroComprobante(prestamosServiceResponseEntity.getIatxResponse().getNroComprobante());
				comprobanteCancelacion.setFechaHoraComprobante(formattedDate);
				comprobanteCancelacion.setFechaCotizacionCoeficiente(respuestaView.getFechaCotizacionCoeficiente());
				comprobanteCancelacion.setMensajeRefinanciacion(mensajeInformativo);
				sesionParametros.setComprobanteCancelacionTotalPrestamo(comprobanteCancelacion);
				estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIRMACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				serviceResponse.setRespuesta(respuestaView);
			}
			return serviceResponse;
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS);
		}
	}

	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		ComprobanteCancelacionTotalPrestamoView datosComprobante = sesionParametros.getComprobanteCancelacionTotalPrestamo();

		Respuesta<Reporte> reporteRespuesta = prestamoBO.generarComprobantePDF(datosComprobante);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_DESCARGAR_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_GENERICO",
					CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_DESCARGA_PDF_ERROR);
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_DESCARGAR_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	@Override
	public Respuesta<Void> grabarEstadisticaVisualizacionComprobanteCancelacionPrestamo() {
		estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_VER_COMPROBANTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return this.respuestaFactory.crearRespuestaOk(null);
	}

	private List<CuentaView> mapCuentaViewCuentaPesos(List<Cuenta> cuentas) {
		final List<CuentaView> result = new ArrayList<CuentaView>();
		if(cuentas != null) {
			for (Cuenta cuenta : cuentas) {
				if (StringUtils.isNotBlank(cuenta.getTipoCuentaSinUnificar()) &&
						(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar()))
								|| TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar()))
								|| TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())))) {

					CuentaView cuentaPesos = new CuentaView();
					cuentaPesos.setAlias(cuenta.getAlias());
					cuentaPesos.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
					cuentaPesos.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
					cuentaPesos.setSaldoPesosSinFormato(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString()
							.equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ? cuenta.getSaldoCUPesos() : cuenta.getSaldoCuenta());
					cuentaPesos.setSaldoPesos(ISBANStringUtils.formatearConComaYDosDecimales2(cuentaPesos.getSaldoPesosSinFormato()));
					result.add(cuentaPesos);
				}
			}
		}

		return result;
	}

	private void mapCuentaSinSaldo(List<CuentaView> listaCuentas, String montoTotal) {
		String montoFormateado = ISBANStringUtils.eliminarCeros(montoTotal.replaceAll("\\+", ""));
		BigDecimal bigMontoTotal = formatearMontoParaOperar(montoFormateado);
		for (CuentaView cuenta : listaCuentas) {
			BigDecimal bigSaldo = new BigDecimal(cuenta.getSaldoPesosSinFormato());
			if (bigMontoTotal.compareTo(bigSaldo) == (1) ) {
				cuenta.setSaldoInsuficiente(Boolean.TRUE);
			}
		}
	}

	private String formatearMonto(String monto, int cantidadDecimales) {
		BigInteger unscaledMonto = new BigInteger(monto.replaceAll("\\+", ""));
		BigDecimal bigMonto = new BigDecimal(unscaledMonto);

		if (BigDecimal.ZERO.compareTo(bigMonto) == 0) {
			return DivisaEnum.PESO.getSimbolo() + " " + "0,00";
		}

		if (bigMonto.toString().length() > cantidadDecimales) {
			String montoFormateado = ISBANStringUtils.formatearSaldosConCerosYSignos(monto, cantidadDecimales);
			return DivisaEnum.PESO.getSimbolo() + " " + montoFormateado.substring(0, montoFormateado.length()-2);
		} else {
			String decimalPart = new BigDecimal(unscaledMonto, cantidadDecimales).toPlainString().split("\\.")[1];
			return DivisaEnum.PESO.getSimbolo() + " 0," + decimalPart;
		}
	}

	private BigDecimal formatearMontoParaOperar(String montoSinFormato) {

		BigDecimal bigMonto = new BigDecimal(montoSinFormato.replaceAll("\\+", ""));
		if (BigDecimal.ZERO.compareTo(bigMonto) == 0) {
			return new BigDecimal("0.00");
		}

		BigDecimal bigNumero = ISBANStringUtils.formatearSaldoString(montoSinFormato);
		String numString = bigNumero.toString();

		String partesNumero1 = numString.substring(0, numString.length() - 4);
		String partesNumero2 = numString.substring(numString.length()-4, numString.length());
		String numeroFormado = partesNumero1 + "." + partesNumero2;

		return new BigDecimal(numeroFormado);
	}
}
