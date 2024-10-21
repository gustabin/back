/*
 *
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.*;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;

import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.config.SegurosUrls;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
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
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaPrestamosEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.LinkView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.WebContentView;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamoPreaprobadoUtils;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SimuladorPrestamoManager;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class SimuladorPrestamoManagerImpl.
 */
@Component
public class SimuladorPrestamoManagerImpl implements SimuladorPrestamoManager {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimuladorPrestamoManagerImpl.class);

    /**
     * The Constant ERROR_FEEDBACK_SOLICITUD_PRESTAMO_PERSONAL.
     */
    public static final String ERROR_FEEDBACK_SOLICITUD_PRESTAMO_PERSONAL = "ERROR_FEEDBACK_SOLICITUD_PRESTAMO_PERSONAL";

    /**
     * The Constant ACCESO_DESDE_ACTION_SHEET.
     */
    public static final String ACCESO_DESDE_ACTION_SHEET = "0";

    /**
     * The Constant ACCESO_DESDE_SOLAPA_PRESTAMOS.
     */
    public static final String ACCESO_DESDE_SOLAPA_PRESTAMOS = "1";

    /**
     * The Constant ESTADO_TC_PRESTAMO.
     */
    public static final String ESTADO_TC_PRESTAMO = "03";

    /**
     * The Constant DIECISEIS_ENTERO.
     */
    public static final int DIECISEIS_ENTERO = 16;

    /**
     * The Constant CERO_STRING.
     */
    public static final String CERO_STRING = "0";

    /**
     * The Constant CUATRO_ENTERO.
     */
    private static final int CUATRO_ENTERO = 4;

    /**
     * The Constant PUNTO_STRING.
     */
    private static final String PUNTO_STRING = ".";

    /**
     * The codigo error generico.
     */
    private String codigoErrorGenerico = "1347";

    /**
     * The Constant FUERA_DE_HORARIO.
     */
    private static final String FUERA_DE_HORARIO = "FUERA_DE_HORARIO";

    /**
     * The Constant INHABILITADO.
     */
    private static final String INHABILITADO = "INHABILITADO";

    /**
     * The Constant PUNTO_STRING.
     */
    private static final String TIPO_TASA_FIJA = "F";

    /**
     * The Constant PUNTO_STRING.
     */
    private static final String TIPO_TASA_VARIABLE = "V";

    /**
     * The Constant PUNTO_STRING.
     */
    private static final String CODIGO_ERROR = "2";

    /**
     * The Constant PUNTO_STRING.
     */
    private static final String CODIGO_WARNING = "3";

    private static final String SIMULAR = "S";

    private static final String ENCOLAR = "1";

    private static final String LIQUIDAR = "L";

    private static final String LIQUIDAR_ENCOLADO = "3";

    private static final String FASE_CANCELAR_USUARIO = "4";

    private static final String LOAN_TYPE = "PREACORDADO";

    /**
     * The seguros url.
     */
    @Autowired
    private SegurosUrls segurosUrl;

    /**
     * The Constant LABEL_BOTON.
     */
    public static final String LABEL_BOTON = "Consultar";

    /**
     * The Constant PESOS.
     */
    private static final String PESOS = "$";

    //Configs Values
    @Value("${CREDITOS.HORAINICIOOPERACIONES}")
    private String horaInicioOperaciones;

    @Value("${CREDITOS.HORAFINOPERACIONES}")
    private String horaFinOperaciones;

    @Value("${TRJCOORD.OPERAINDISTINTO.PRESTAMOS}")
    private String valorDesafioPrestamo;

    //Managers
    @Autowired
    private EstadisticaManager estadisticaManager;

    @Autowired
    private ClienteManager clienteManager;

    @Autowired
    private AutentificacionManager autentificacionManager;

    //BOs
    @Autowired
    private LegalBO legalBO;

    @Autowired
    private SimuladorPrestamoBO simuladorPrestamoBo;

    @Autowired
    private DestinoPrestamoBO destinoPrestamoBo;

    @Autowired
    private EstadisticaBO estadisticaBo;

    @Autowired
    private MensajeBO mensajeBO;

    @Autowired
    private CuentaBO cuentaBO;

    //Utils
    @Autowired
    private RespuestaFactory respuestaFactory;

    //Session Values
    @Autowired
    private SesionCliente sesionCliente;

    @Autowired
    private SesionParametros sesionParametros;

    /**
     * The rsa manager.
     */
    @Autowired
    private RsaManager rsaManager;

    @Autowired
    PrestamoManagerBO prestamoTokenBO;

    @Autowired
    private PrestamoBO prestamoBO;

    @Autowired
    private DestinoPrestamoDAO destinoPrestamoDAO;

    /**
     * The mya BO.
     */
    @Autowired
    protected MyaBO myaBO;

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
     * SimuladorPrestamoManager#inicioSimulacion()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaInicioSimuladorPrestamo(PuntoDeAccesoView puntoDeAcceso) {
        if (ACCESO_DESDE_ACTION_SHEET.equals(puntoDeAcceso.getPuntoDeAcceso())) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_SIMULADOR_ACTION_SHEET,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (ACCESO_DESDE_SOLAPA_PRESTAMOS.equals(puntoDeAcceso.getPuntoDeAcceso())) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_SIMULADOR_SOLAPA_PRESTAMOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        return respuestaFactory.crearRespuestaOk(null);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
     * SimuladorPrestamoManager#obtenerDatosSimulacion()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Respuesta<List<ConfiguracionPrestamoView>> obtenerDatosSimulacion() {

        List<ConfiguracionPrestamoView> cuentasUnicasView = new ArrayList<ConfiguracionPrestamoView>();
        Class<List<ConfiguracionPrestamoView>> cuentasUnicasClass = (Class<List<ConfiguracionPrestamoView>>) cuentasUnicasView
                .getClass();

        List<CalificacionCrediticiaDTO> calificaciones = sesionParametros.getCalificacionesCrediticiasDTO();

        Boolean flagPrestamoInhabilitado = (calificaciones == null || calificaciones.isEmpty());
        Boolean flagNoLineaCrediticia = false;

        // SI TIENE PREAPROBADO
        ConfiguracionPrestamoView configPrestamoPreaprobado = null;
        if (sesionParametros.getMaxOfertaPrestamoPreaprobado() != null) {
            try {
                configPrestamoPreaprobado = simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamoPreaprobado(sesionCliente.getCliente());
                if (configPrestamoPreaprobado != null) {
                    configPrestamoPreaprobado.setCuentas(CuentasUtils.obtenerCuentasViewPesos(sesionCliente.getCliente().getCuentasEnPesos()));
                    LimitePrestamoPreaprobadoView lim = PrestamoPreaprobadoUtils.obtenerOfertaSeleccionada(sesionParametros.getLimitesPreaprobado(), sesionParametros.getMaxOfertaPrestamoPreaprobado(), null);
                    configPrestamoPreaprobado.setMensajePreaprobado(MessageFormat.format(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRESTAMO_PREAPROBADO_PREAPROBADO).getMensaje(),
                            PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(sesionParametros.getMaxOfertaPrestamoPreaprobado()))));
                    estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_CONSULTA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                } else {
                    estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_CONSULTA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                }
            } catch (BusinessException e) {
                LOGGER.error(e.getMessage(), e);
                estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_CONSULTA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }

        if (configPrestamoPreaprobado != null) {
            cuentasUnicasView.add(configPrestamoPreaprobado);
        }

        if (calificaciones != null) {
            for (CalificacionCrediticiaDTO calificacion : calificaciones) {
                try {
                    ConfiguracionPrestamoDTO configuracionPrestamoDto = simuladorPrestamoBo
                            .obtenerConfiguracionSimulacionPrestamo(sesionCliente.getCliente(), calificacion.getCuenta());
                    if (configuracionPrestamoDto.getPrestamoPermitido() != null) {
                        ConfiguracionPrestamoView configPrestamo = new ConfiguracionPrestamoView(configuracionPrestamoDto, calificacion.getCuenta());
                        configPrestamo.setMensajeSuperPrestamos(MessageFormat.format(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRESTAMO_PREAPROBADO_SUPER_PRESTAMOS).getMensaje(),
                                configPrestamo.getImporteMaximoFormateado()));
                        cuentasUnicasView.add(configPrestamo);
                    }
                } catch (BusinessException e) {
                    LOGGER.error(e.getMessage(), e);
                    if (CodigoMensajeConstantes.PRESTAMO_INHABILITADO.equals(e.getMessage())) {
                        flagPrestamoInhabilitado = true;
                    } else if (CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO.equals(e.getMessage())) {
                        flagNoLineaCrediticia = true;
                    }
                }
            }
        }
        Respuesta<List<ConfiguracionPrestamoView>> respuesta;
        if (cuentasUnicasView.isEmpty()) {
            respuesta = manejarFlags(flagPrestamoInhabilitado, flagNoLineaCrediticia, cuentasUnicasClass);
        } else if (cuentasUnicasView.size() == 1 && cuentasUnicasView.get(0).getMensajeInhabilitado() != null
                && StringUtils.isNotEmpty(cuentasUnicasView.get(0).getMensajeInhabilitado())) {
            respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(cuentasUnicasClass,
                    cuentasUnicasView.get(0).getMensajeInhabilitado(), INHABILITADO);
        } else {
            estadisticaManager.add(EstadisticasConstants.INGRESO_SIMULADOR_PRESTAMO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            respuesta = new Respuesta<List<ConfiguracionPrestamoView>>();
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuesta(cuentasUnicasView);
            respuesta.setRespuestaVacia(false);
        }
        return respuesta;
    }


    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
     * SimuladorPrestamoManager#obtenerResultadoSimulacion(ar.com.santanderrio.
     * obp.servicios.prestamos.view.SolicitudSimulacionView)
     */
    @Override
    public Respuesta<ResultadoSimulacionView> simular(SolicitudSimulacionView solicitudSimulacionView) {

        if (!simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()) {
            return crearRespuestaErrorFueraDeHorario(ResultadoSimulacionView.class);
        }

        Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo = obtenerConfiguracionPrestamo(solicitudSimulacionView, SIMULAR);
        if (!EstadoRespuesta.OK.equals(configuracionPrestamo.getEstadoRespuesta())) {
            return Respuesta.copy(ResultadoSimulacionView.class, configuracionPrestamo);
        }
        Cuenta cuenta = sesionCliente.getCliente().getCuenta(solicitudSimulacionView.getCbu());

        try {
            //Simulamos (mainframe)
            PrestamoOutEntity simuladorPrestamoOutEntity = simuladorPrestamoBo.obtenerPrestamo(sesionCliente.getCliente(),
                    solicitudSimulacionView, configuracionPrestamo.getRespuesta(), SIMULAR);

            //Seteamos el codigo de estadistica
            String codigoTransaccion = solicitudSimulacionView.isUva() ? EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO_UVA : EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO;
            grabarEstadisticaOk(obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta, codigoTransaccion);

            //Armamos una simulacion view a partir de la cuenta y la respuesta a la simulacion
            Respuesta<ResultadoSimulacionView> resSimulacion = getSimulacionView(solicitudSimulacionView, cuenta, simuladorPrestamoOutEntity);
            sesionParametros.setReportSimulacion(new ReportSimulacionPrestamosView(resSimulacion.getRespuesta()));
            sesionParametros.setComprobantePPPIsUva(solicitudSimulacionView.isUva());
            return resSimulacion;
        } catch (BusinessException e) {
            LOGGER.error("Error al simular prestamo: ", e);
            return manejarBusinessExceptionEnSimular(e, cuenta, solicitudSimulacionView, configuracionPrestamo);
        }
    }

    private Respuesta<ResultadoSimulacionView> getSimulacionView(SolicitudSimulacionView solicitudSimulacionView, Cuenta cuenta, PrestamoOutEntity simuladorPrestamoOutEntity) throws BusinessException {

        // Si es Advance => es pack Negocio y/o Negocio Gold
        Segmento segmento = sesionCliente.getCliente().getSegmento();
        Boolean isGold = segmento.isDuo() || segmento.isPyme();
        String tipoTasa = solicitudSimulacionView.isTasaFija() ? TIPO_TASA_FIJA : TIPO_TASA_VARIABLE;
        if (solicitudSimulacionView.isUva()) {
            return getSimulacionUVA(solicitudSimulacionView, cuenta, isGold, tipoTasa, simuladorPrestamoOutEntity);
        } else {
            return getSimulacionNoUVA(solicitudSimulacionView, cuenta, isGold, tipoTasa, simuladorPrestamoOutEntity);
        }
    }

    private Respuesta<ResultadoSimulacionView> getSimulacionUVA(SolicitudSimulacionView solicitudSimulacionView, Cuenta cuenta, Boolean isGold, String tipoTasa, PrestamoOutEntity simuladorPrestamoOutEntity) throws BusinessException {
        ResultadoSimulacionView simulacion = new ResultadoSimulacionView.ResultadoSimulacionViewBuilder()
                .importe(solicitudSimulacionView.importeToBigDecimal())
                .importeEnPesosItem(solicitudSimulacionView.importeToBigDecimal())
                .importeEnUvas(simuladorPrestamoOutEntity.getImporteSolicitado())
                .importeNetoUvaItem(simuladorPrestamoOutEntity.getImporteAbono())
                .gastosDeOtorgamiento(isGold, simuladorPrestamoOutEntity.getImporteCargo())
                .cuentaDestinoItem(cuenta)
                .cantidadCuotasItem(solicitudSimulacionView.getCuotaSeleccionada())
                .motivoPrestamoItem(destinoPrestamoBo
                        .buscarPorId(solicitudSimulacionView.getMotivoSeleccionado().getId()))
                .importePrimeraCuotaEnUvas(simuladorPrestamoOutEntity.getImportePrimerCuotaPura())
                .fechaVencimientoUvaItem(solicitudSimulacionView.getFechaSeleccionada())
                .importeCuotaCte(simuladorPrestamoOutEntity.getImporteCuotaCTE())
                .cotizCambioItem(simuladorPrestamoOutEntity.getCotizCambio())
                .fechaCotizItem(simuladorPrestamoOutEntity.getFechaCotiz()).tipoTasaItem(tipoTasa)
                .tnaUvaItem(simuladorPrestamoOutEntity.getTasa())
                .teaUvaItem(simuladorPrestamoOutEntity.getTea())
                .cftnaItem(simuladorPrestamoOutEntity.getCftna())
                .cftnaSinImpItem(simuladorPrestamoOutEntity.getCftnasimp()).build();

        Respuesta<ResultadoSimulacionView> resSimulacion = respuestaFactory
                .crearRespuestaOk(simulacion);

        simulacion.setLegal1simulacionConfirmacion(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_1));
        simulacion.setLegal2simulacionConfirmacion(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_2));
        simulacion.setLegal3simulacionConfirmacion(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_3));
        return resSimulacion;
    }


    private Respuesta<ResultadoSimulacionView> getSimulacionNoUVA(SolicitudSimulacionView solicitudSimulacionView, Cuenta cuenta, Boolean isGold, String tipoTasa, PrestamoOutEntity simuladorPrestamoOutEntity) throws BusinessException {
        ResultadoSimulacionView simulacion = new ResultadoSimulacionView.ResultadoSimulacionViewBuilder()
                .importe(solicitudSimulacionView.importeToBigDecimal())
                .importeNetoItem(simuladorPrestamoOutEntity.getImporteAbono())
                .gastosDeOtorgamiento(isGold, simuladorPrestamoOutEntity.getImporteCargo())
                .cuentaDestinoItem(cuenta)
                .cantidadCuotasItem(solicitudSimulacionView.getCuotaSeleccionada())
                .motivoPrestamoItem(destinoPrestamoBo
                        .buscarPorId(solicitudSimulacionView.getMotivoSeleccionado().getId()))
                .importePrimeraCuotaItem(simuladorPrestamoOutEntity.getTotCuotaTotal())
                .fechaPrimerPagoItem(solicitudSimulacionView.getFechaSeleccionada())
                .capitalInteresesPeriodoItem(simuladorPrestamoOutEntity.getCuotaPura())
                .cargoSeguroVidaItem(simuladorPrestamoOutEntity.getTotSeguroCuota())
                .ivaItem(simuladorPrestamoOutEntity.getTotIvaCuota())
                .otrosImpuestosItem(simuladorPrestamoOutEntity.getTotRestoImpuCuota())
                .tipoTasaItem(tipoTasa).tnaItem(simuladorPrestamoOutEntity.getTasa())
                .teaItem(simuladorPrestamoOutEntity.getTea())
                .cftnaItem(simuladorPrestamoOutEntity.getCftna())
                .cftnaSinImpItem(simuladorPrestamoOutEntity.getCftnasimp()).build();

        Respuesta<ResultadoSimulacionView> resSimulacion = respuestaFactory
                .crearRespuestaOk(simulacion);

        simulacion.setLegal1simulacionConfirmacion(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_1));
        simulacion.setLegal2simulacionConfirmacion(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_2));
        simulacion.setLegal3simulacionConfirmacion(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_3));
        return resSimulacion;
    }

    /**
     * Encolar el prestamo.
     *
     * @param solicitudSimulacionView the solicitud simulacion view
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> encolarPrestamo(SolicitudSimulacionView solicitudSimulacionView) {
        String logMessage = "Encolar Prestamos {}";
        String codigoTransaccionUva = EstadisticasConstants.RESULTADO_ENCOLAR_PRESTAMO_UVA;
        String codigoMensajeNoUva = CodigoMensajeConstantes.PRESTAMO_PERSONAL_ENCOLADO;
        String codigoMensajeUva = CodigoMensajeConstantes.PRESTAMO_PERSONAL_UVA_ENCOLADO;
        String codigoTransaccionNoUva = EstadisticasConstants.RESULTADO_ENCOLAR_PRESTAMO;

        return encolarAdquirirPrestamo(ENCOLAR, solicitudSimulacionView, logMessage, codigoTransaccionUva, codigoMensajeNoUva, codigoMensajeUva, codigoTransaccionNoUva);

    }

    /**
     * Adquiere el prestamo.
     *
     * @param solicitudSimulacionView the solicitud simulacion view
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> adquirirPrestamo(SolicitudSimulacionView solicitudSimulacionView) {
        String logMessage = "Solicitar Prestamos {}";
        String codigoTransaccionUva = EstadisticasConstants.RESULTADO_ALTA_PRESTAMO_UVA;
        String codigoMensajeUva = CodigoMensajeConstantes.PRESTAMO_PERSONAL_UVA_APROBADO;
        String codigoMensajeNoUva = CodigoMensajeConstantes.PRESTAMO_PERSONAL_APROBADO;
        String codigoTransaccionNoUva = EstadisticasConstants.RESULTADO_ALTA_PRESTAMO;

        return encolarAdquirirPrestamo(LIQUIDAR, solicitudSimulacionView, logMessage, codigoTransaccionUva, codigoMensajeNoUva, codigoMensajeUva, codigoTransaccionNoUva);
    }

    /**
     * Obtiene simulacion del prestamo para visualizar
     */
    @Override
    public Respuesta<ResultadoSimulacionView> obtenerSolicitudPrestamo() {
        ReportSimulacionPrestamosView simulacion = sesionParametros.getReportSimulacion();
        return respuestaFactory.crearRespuestaOk(simulacion.getSimulacion());
    }

    /**
     * Adquiere el prestamo encolado
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> adquirirPrestamoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView) {
        String logMessage = "Solicitar Prestamos {}";
        String codigoTransaccionUva = EstadisticasConstants.RESULTADO_ALTA_PRESTAMO_UVA;
        String codigoMensajeUva = CodigoMensajeConstantes.PRESTAMO_PERSONAL_UVA_APROBADO;
        String codigoMensajeNoUva = CodigoMensajeConstantes.PRESTAMO_PERSONAL_APROBADO;
        String codigoTransaccionNoUva = EstadisticasConstants.RESULTADO_ALTA_PRESTAMO;

        //Obtenemos el prestamo encolado desde la api
        PrestamoEncoladoEntity prestamoEncoladoEntity;
        try {
            if (liquidacionPrestamoEncoladoView.getId() == null) {
                liquidacionPrestamoEncoladoView.setId(liquidacionPrestamoEncoladoView.getSolicitudId());
            }
            prestamoEncoladoEntity = prestamoBO.obtenerPrestamoEncolado(liquidacionPrestamoEncoladoView.getId());
        } catch (DAOException e) {
            return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO).getMensaje(), "CODIGO_ERROR_LIQUIDAR_ENCOLADO");
        }

        Cuenta cuenta = sesionCliente.getCliente().getCuentaSiContieneNumero(prestamoEncoladoEntity.getDebitAccount().getNroCuenta());
        SolicitudSimulacionView solicitudSimulacionView = mapPrestamoEncolado(prestamoEncoladoEntity, liquidacionPrestamoEncoladoView, cuenta, LIQUIDAR_ENCOLADO);

        return encolarAdquirirPrestamo(LIQUIDAR_ENCOLADO, solicitudSimulacionView, logMessage, codigoTransaccionUva, codigoMensajeNoUva, codigoMensajeUva, codigoTransaccionNoUva);
    }


    @Override
    public void eliminarSolicitudPendiente(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView, PrestamoEncoladoEntity prestamoEncoladoEntity) throws DAOException {
            Cuenta cuenta = sesionCliente.getCliente().getCuentaSiContieneNumero(prestamoEncoladoEntity.getDebitAccount().getNroCuenta());
            SolicitudSimulacionView solicitudSimulacionView = mapPrestamoEncolado(prestamoEncoladoEntity, liquidacionPrestamoEncoladoView, cuenta, FASE_CANCELAR_USUARIO);
            Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo = obtenerConfiguracionPrestamo(solicitudSimulacionView, FASE_CANCELAR_USUARIO);
            validarTokenRSA(solicitudSimulacionView, EstadisticasConstants.RESULTADO_ENCOLAR_PRESTAMO_UVA, EstadisticasConstants.RESULTADO_ENCOLAR_PRESTAMO_UVA, configuracionPrestamo);
            prestamoBO.cancelarPrestamoEncolado(liquidacionPrestamoEncoladoView.getSolicitudId());
    }

    private SolicitudSimulacionView mapPrestamoEncolado(PrestamoEncoladoEntity prestamoEncolado, LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView, Cuenta cuenta, String fase) {
        SolicitudSimulacionView solicitudSimulacionView = new SolicitudSimulacionView();
        solicitudSimulacionView.setCbu(cuenta.getCbu());
        solicitudSimulacionView.setImporteSeleccionado(prestamoEncolado.getAmount().toString());
        solicitudSimulacionView.setCuotaSeleccionada(String.valueOf(prestamoEncolado.getQuotas()));
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        solicitudSimulacionView.setFechaSeleccionada(sdf.format((prestamoEncolado.getFirstExpirationDate())));
        DestinoPrestamoSeleccionView motivoSeleccionado = destinoPrestamoDAO.obtenerViewPorProductoSubproductoDestino(String.valueOf(prestamoEncolado.getProductCode()), StringUtils.leftPad(String.valueOf(prestamoEncolado.getSubProductCode()), CUATRO_ENTERO, CERO_STRING), String.valueOf(prestamoEncolado.getDestinationFunds()));
        solicitudSimulacionView.setMotivoSeleccionado(motivoSeleccionado);
        solicitudSimulacionView.setReintentar("false");
        solicitudSimulacionView.setUVA(Boolean.TRUE.equals(prestamoEncolado.getLineUva()));
        solicitudSimulacionView.setTasaFija(TIPO_TASA_FIJA.equals(prestamoEncolado.getFeeType()));
        solicitudSimulacionView.setLegal(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_SIMULADOR_PRESTAMO_CONFIRMACION));
        solicitudSimulacionView.setCantiCuentas(String.valueOf(sesionCliente.getCliente().getCuentasEnPesos().size()));
        solicitudSimulacionView.setDestino("Cuenta destino");
        solicitudSimulacionView.setDesafio(liquidacionPrestamoEncoladoView.getDesafio());
        solicitudSimulacionView.setToken(liquidacionPrestamoEncoladoView.getTokenSMS());
        solicitudSimulacionView.setTna(prestamoEncolado.getTna());
        solicitudSimulacionView.setProducto(String.valueOf(prestamoEncolado.getProductCode()));
        solicitudSimulacionView.setSubproducto(String.valueOf(prestamoEncolado.getSubProductCode()));
        solicitudSimulacionView.setFase(fase);
        return solicitudSimulacionView;
    }


    private Respuesta<ComprobanteFeedbackView> encolarAdquirirPrestamo(String fase, SolicitudSimulacionView solicitudSimulacionView, String logMessage, String codigoTransaccionUva, String codigoMensajeNoUva, String codigoMensajeUva, String codigoTransaccionNoUva) {
        LOGGER.info(logMessage, solicitudSimulacionView);
        Cliente cliente = sesionCliente.getCliente();

        //Validar fuera de horario
        if (!simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()) {
            return crearRespuestaErrorFueraDeHorario(ComprobanteFeedbackView.class);
        }

        //Suma al contador si se esta reintentando
        if (!"true".equals(solicitudSimulacionView.getReintentar())) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }


        //Valida objeto de request y configura...
        Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo = obtenerConfiguracionPrestamo(solicitudSimulacionView, fase);
        if (!EstadoRespuesta.OK.equals(configuracionPrestamo.getEstadoRespuesta())) {
            return Respuesta.copy(ComprobanteFeedbackView.class, configuracionPrestamo);
        }
        Cuenta cuenta = cliente.getCuenta(solicitudSimulacionView.getCbu());

        try {
            solicitudSimulacionView.setFase(fase);
            //Validación RSA para encolar y liquidar encolados
            if (ENCOLAR.equals(fase) || LIQUIDAR_ENCOLADO.equals(fase)) {
                Respuesta<ComprobanteFeedbackView> respuestaValidacion = validarTokenRSA(solicitudSimulacionView, codigoTransaccionUva, codigoTransaccionNoUva, configuracionPrestamo);
                if (respuestaValidacion != null) {
                    return respuestaValidacion;
                }
            }

            //Validación token identidad para liquidar inmediato y encolados
            if (LIQUIDAR.equals(fase) || LIQUIDAR_ENCOLADO.equals(fase)) {
                validarTokenIdentidad(solicitudSimulacionView);
            }

            //Liquida/encola segun fase
            PrestamoOutEntity prestamoOut = simuladorPrestamoBo.obtenerPrestamo(sesionCliente.getCliente(), solicitudSimulacionView, configuracionPrestamo.getRespuesta(), fase);
            PrestamoInEntity prestamoInEntity = simuladorPrestamoBo.obtenerPrestamoInEntity(sesionCliente.getCliente(), solicitudSimulacionView, configuracionPrestamo.getRespuesta());

            //(des)Encolamiento en mongo
            if (ENCOLAR.equals(fase)) {
                prestamoTokenBO.enqueueKafka(prestamoInEntity, sesionCliente.getCliente().getNup());
            } else {
                prestamoTokenBO.dequeueKafka(LOAN_TYPE, sesionCliente.getCliente().getNup(), !simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion());
            }

            //Se arma el comprobante
            ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView(prestamoOut.getFecha(),
                    prestamoOut.getNroComprobante(), "false");

            //Se arma mensaje con datos del prestamo, segun si es Uva o no
            if (solicitudSimulacionView.isUva()) {
                comprobante.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(codigoMensajeUva).getMensaje(),
                        solicitudSimulacionView.getImporteSeleccionado(),
                        solicitudSimulacionView.getCuotaSeleccionada(), prestamoOut.getNumPrest());
                grabarEstadisticaOk(prestamoOut.getImporteAbono(), cuenta, codigoTransaccionUva);
            } else {
                comprobante.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(codigoMensajeNoUva).getMensaje(),
                        solicitudSimulacionView.getImporteSeleccionado(),
                        solicitudSimulacionView.getCuotaSeleccionada(), prestamoOut.getNumPrest());
                grabarEstadisticaOk(prestamoOut.getImporteAbono(), cuenta, codigoTransaccionNoUva);
            }

            // se agregan los datos aparte para poder mostrarlos en el FE 
            comprobante.setNroPrestamo(prestamoOut.getNumPrest());
            comprobante.setNroCuota(solicitudSimulacionView.getCuotaSeleccionada());
            comprobante.setNroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta.getContratoAltair(), cuenta.getOficinaAltair()));

            comprobante.setImporte("$ " + ISBANStringUtils.formatearConComaYDosDecimales(solicitudSimulacionView.getImporteSeleccionado()));

            Cuenta prestamoSolicitado = obtenerCuentaPrestamo(prestamoOut, cliente);
            sesionCliente.getCliente().getCuentas().add(prestamoSolicitado);

            ReportSimulacionPrestamosView simulacion;
            if (LIQUIDAR_ENCOLADO.equals(fase)) {
                Respuesta<ResultadoSimulacionView> resSimulacion = getSimulacionView(solicitudSimulacionView, cuenta, prestamoOut);
                simulacion = new ReportSimulacionPrestamosView(resSimulacion.getRespuesta());
            } else {
                simulacion = sesionParametros.getReportSimulacion();
            }
            simulacion.setFechaHora(comprobante.getFechaHora());
            simulacion.setNroDeComprobante(comprobante.getNroDeComprobante());
            simulacion.setLegalesSEUO(solicitudSimulacionView.getLegal());
            sesionParametros.setReportSimulacion(simulacion);
            WebContentView contenido = obtenerContenidoLink(solicitudSimulacionView);

            comprobante.setContenido(contenido);

            return respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobante);

        } catch (BusinessException e) {
            LOGGER.error("Error al tratar de adquirir un prestamo personal: ", e);
            return manejarBusinessExceptionEnAdquirirPrestamo(e, cuenta, solicitudSimulacionView,
                    configuracionPrestamo);
        }
    }

    private void validarTokenIdentidad(SolicitudSimulacionView solicitudSimulacionView) throws BusinessException {
        this.sesionParametros.setDesafioPrestamosActivo(true);
        if (!this.prestamoTokenBO.isValidToken(solicitudSimulacionView.getToken())) {
            if (!StringUtils.isEmpty(solicitudSimulacionView.getToken())) {
                this.sesionParametros.setDesafioPrestamosActivo(false);
                throw new BusinessException(CodigoMensajeConstantes.TOKEN_PRESTAMO_ERROR);
            }
            throw new BusinessException(CodigoMensajeConstantes.SOLICITUD_TOKEN_PRESTAMO);
        }
        this.sesionParametros.setDesafioPrestamosActivo(false);
    }

    private Respuesta<ComprobanteFeedbackView> validarTokenRSA(SolicitudSimulacionView solicitudSimulacionView, String codigoTransaccionUva, String codigoTransaccionNoUva, Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo) {
        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuenta = cliente.getCuenta(solicitudSimulacionView.getCbu());


        // Verificacion de seguridad por RSA
        if (!this.sesionParametros.getDesafioPrestamosActivo()) {
            //Valida si se tiene los datos RSA activos
            if (!sesionCliente.getTieneTokenRSA()) {
                LOGGER.info("RSA Offline no se permite completar la liquidación del prestamo.");
                if (solicitudSimulacionView.isUva()) {
                    grabarEstadisticaError(obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                            codigoTransaccionUva, CODIGO_ERROR);
                } else {
                    grabarEstadisticaError(solicitudSimulacionView.getImporteSeleccionado(), cuenta,
                            codigoTransaccionNoUva, CODIGO_ERROR);
                }
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
                        CodigoMensajeConstantes.PRESTAMO_DENY_RSA);
            }

            AutentificacionDTO autentificacionDTO;
            //Checkea si cumple el desafio actual
            Respuesta<ComprobanteFeedbackView> estadoDesafio = autentificacionManager.verificarEstadoDesafio(solicitudSimulacionView.getDesafio(), Integer.parseInt(valorDesafioPrestamo));
            if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
                //Si el desafio es correcto, usa los datos actuales de la solicitud
                autentificacionDTO = solicitudSimulacionView.getDesafio();
            } else {
                //Caso contrario, arma datos de autentificacion nuevo, consultando MyA
                MyaDTOIn myaDTOIn = new MyaDTOIn();
                myaDTOIn.setNup(cliente.getNup());
                MyaDTOOut datosMyA = myaBO.consultaWsEstadoClienteV3(cliente, myaDTOIn);
                SimuladorPrestamoDTO simuladorPrestamoDTO = crearSimuladorPrestamoDTO(solicitudSimulacionView, configuracionPrestamo, cuenta, datosMyA);
                autentificacionDTO = cargarAutentificacionDTO(simuladorPrestamoDTO);
            }

            Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager.ejecutarValidacionRSA(autentificacionDTO);

            if (respEjecucionMetodoAutentificacion == null || EstadoRespuesta.ERROR.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) {
                //Se genera un error al validar RSA
                if (TipoError.SIN_METODO_DESAFIO.toString().equals(respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError())) {
                    // El usuario no tiene desafios habilitados.
                    return respuestaFactory.crearRespuestaErrorPersonalizado(ComprobanteFeedbackView.class,
                            respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta().get(0).getMensaje(), TipoError.SIN_METODO_DESAFIO.toString());
                } else if (respEjecucionMetodoAutentificacion.getRespuesta() != null && respEjecucionMetodoAutentificacion.getRespuesta().getBloquearUsuario()) {
                    //Se debe bloquear al usuario
                    LOGGER.info("Inicio de bloqueo de usuario.");
                    Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
                    if (EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())) {
                        //Usuario bloqueado correctamente
                        return respuestaFactory.crearRespuestaError(ComprobanteFeedbackView.class, StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
                                CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
                    } else {
                        //Error al bloquear usuario
                        LOGGER.info("Error al bloquear el usuario");
                        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
                                CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
                    }
                } else {
                    return Respuesta.copy(ComprobanteFeedbackView.class, respEjecucionMetodoAutentificacion);
                }
            } else if (EstadoRespuesta.WARNING.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) {
                //Se genera un desafio para cargar un codigo de RSA
                ComprobanteFeedbackView respuestaDesafio = new ComprobanteFeedbackView();
                respuestaDesafio.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());
                setDatosRespuestaComprobante(solicitudSimulacionView, respuestaDesafio);
                return respuestaFactory.transformar(respuestaDesafio, respEjecucionMetodoAutentificacion);
            }
        }
        //Se valido correctamente RSA
        return null;
    }


    /**
     * Cargamos datos para el desafío
     *
     * @param simuladorPrestamo
     * @return
     */
    private AutentificacionDTO cargarAutentificacionDTO(SimuladorPrestamoDTO simuladorPrestamo) {

        Integer operacion = Integer.parseInt(valorDesafioPrestamo);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_PRESTAMOS);
        autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_PRESTAMOS);
        autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_PRESTAMOS);
        autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_PRESTAMOS);

        autentificacionDTO.setRsaDTO(simuladorPrestamo);

        return autentificacionDTO;
    }

    /**
     * Crear simulador prestamo DTO.
     *
     * @param solicitudSimulacionView the solicitud simulacion view
     * @param configuracionPrestamo   the configuracion prestamo
     * @param cuenta                  the cuenta
     * @return the simulador prestamo DTO
     */
    private SimuladorPrestamoDTO crearSimuladorPrestamoDTO(SolicitudSimulacionView solicitudSimulacionView,
                                                           Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo, Cuenta cuenta, MyaDTOOut datosMyA) {
        SimuladorPrestamoDTO simuladorPrestamoDTO = new SimuladorPrestamoDTO();
        simuladorPrestamoDTO.setFechaSeleccionada(solicitudSimulacionView.getFechaSeleccionada());
        simuladorPrestamoDTO.setImporteSeleccionado(solicitudSimulacionView.getImporteSeleccionado());
        PrestamoPermitidoEntity rangoCuota = configuracionPrestamo.getRespuesta().getPrestamoPermitido()
                .obtenerInfoPrestamoPorCuotas(Integer.parseInt(solicitudSimulacionView.getCuotaSeleccionada()),
                        solicitudSimulacionView.isUva(), solicitudSimulacionView.isTasaFija(), solicitudSimulacionView.getIdRangoSeleccionado());
        simuladorPrestamoDTO.setMaxImpPrest(rangoCuota.getMaxImpPrest());
        try {
            cuentaBO.obtenerSaldoActualizado(cuenta);
        } catch (BusinessException e) {
            LOGGER.error("Error al obtener saldo actualizado de la cuenta {}: {}", cuenta.getCbu(), e);
        }
        simuladorPrestamoDTO.setCuentaSeleccionada(cuenta);
        simuladorPrestamoDTO.setNyaCliente(sesionCliente.getCliente().getNombre() + " " + sesionCliente.getCliente().getApellido1());
        simuladorPrestamoDTO.setValidarBloqueo(Boolean.TRUE);

        if (solicitudSimulacionView.isUva()) {
            simuladorPrestamoDTO.setTipoPrestamo("Pre-acordado-UVA");
        } else {
            simuladorPrestamoDTO.setTipoPrestamo("Pre-acordado");
        }
        simuladorPrestamoDTO.setFase(solicitudSimulacionView.getFase());

        LOGGER.info("CodProductoUg Simulador: " + rangoCuota.getCodProductoUg());
        LOGGER.info("CodSubProductoUg Simulador: " + rangoCuota.getCodSubpUg());

        String codigoProd = rangoCuota.getCodProductoUg();
        String codigoSubProd = rangoCuota.getCodSubpUg();

        String subProdPrestamo = "";
        if (codigoProd != null && codigoSubProd != null) {
            subProdPrestamo = codigoProd.concat("-".concat(codigoSubProd));
        }

        simuladorPrestamoDTO.setSubproductoPrestamo(subProdPrestamo);

        String fechaNacimiento = cuenta.getCliente().getFechaNacimiento();
        String anioNacimiento = "";

        if (fechaNacimiento.length() > 4) {
            anioNacimiento = fechaNacimiento.substring(0, 4);
        } else {
            anioNacimiento = fechaNacimiento;
        }

        LOGGER.info("Anio de nacimiento Cliente: " + anioNacimiento);
        simuladorPrestamoDTO.setAnioNacimiento(anioNacimiento);
		
		/*try {
        	Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						simuladorPrestamoDTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						simuladorPrestamoDTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}*/

        cargarDatosClaveToken(simuladorPrestamoDTO);
        simuladorPrestamoDTO.setStopOperacionErrorRSA(true);

        if (datosMyA != null) {
            simuladorPrestamoDTO.setCantidadDiasCambioCel(this.calcularDias(datosMyA.getFechaModificadoCelular()));
            simuladorPrestamoDTO.setCantidadDiasCambioMail(this.calcularDias(datosMyA.getFechaModificadoEmail()));
        }

        return simuladorPrestamoDTO;
    }

    private void cargarDatosClaveToken(SimuladorPrestamoDTO simuladorPrestamoDTO) {
        Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
        if (antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
            if (antiguedades.getRespuesta().get(0) != null) {
                simuladorPrestamoDTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
            }
            if (antiguedades.getRespuesta().get(1) != null) {
                simuladorPrestamoDTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
            }
        }
    }

    /**
     * Calcula la cantidad de dias desde el cambio de mail/celular
     *
     * @return
     */
    private Integer calcularDias(String fecha) {
        Integer cantidadDias = null;
        if (fecha != null) {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date fechaModificacion = myFormat.parse(fecha);
                cantidadDias = ISBANStringUtils.diferenciaEnDias(new Date(), fechaModificacion);
            } catch (ParseException e) {
                LOGGER.error("Error al parsear la fecha de modicicacion de mail ");
            }
        }
        return cantidadDias;
    }

    /**
     * Obtiene el importe correcto para grabar la estadistica. Verifica si es un
     * numero decimal. Si lo es, lo deja como esta. Si no lo es, le agrega dos
     * ceros.
     *
     * @param importeSeleccionado the importe seleccionado
     * @return the string
     */
    private String obtenerImporteCorrectoParaEstadistica(String importeSeleccionado) {
        return StringUtils.contains(importeSeleccionado, PUNTO_STRING) ? agregarDecimales(importeSeleccionado)
                : importeSeleccionado.concat(".00");
    }

    /**
     * Agrega decimales si es necesario.
     *
     * @param importeSeleccionado the importe seleccionado
     * @return the string
     */
    private String agregarDecimales(String importeSeleccionado) {
        return StringUtils.substringAfter(importeSeleccionado, PUNTO_STRING).length() == 2 ? importeSeleccionado
                : importeSeleccionado.concat("0");
    }

    /**
     * Obtiene la cuenta que corresponde al prestamo para agregarla a sesion.
     *
     * @param prestamoOutEntity the prestamo out entity
     * @param cliente           the cliente
     * @return the cuenta
     */
    private Cuenta obtenerCuentaPrestamo(PrestamoOutEntity prestamoOutEntity, Cliente cliente) {
        Cuenta prestamo = new Cuenta();
        prestamo.setNroSucursal(StringUtils.leftPad(prestamoOutEntity.getSucPrest(), CUATRO_ENTERO, CERO_STRING));
        prestamo.setEstadoTarjetaCredito(ESTADO_TC_PRESTAMO);
        prestamo.setNroCuentaProducto(
                StringUtils.leftPad(prestamoOutEntity.getNumPrest(), DIECISEIS_ENTERO, CERO_STRING));
        prestamo.setClaseCuenta(String.valueOf(TipoPrestamoEnum.PERSONAL.getId()));
        prestamo.setTipoCuenta(TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30.getCodigo());
        prestamo.setTipoCuentaEnum(TipoCuenta.PRESTAMO);
        prestamo.setCliente(cliente);
        return prestamo;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
     * SimuladorPrestamoManager#grabarEstadisticaVisualizacionComprobante(ar.com
     * .santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoView)
     */
    @Override
    public Boolean grabarEstadisticaVisualizacionComprobante(ComprobantePrestamoView comprobanteView) {
        Cuenta cuenta = sesionCliente.getCliente().getCuenta(comprobanteView.getCbu());
        if (comprobanteView.isUva()) {
            grabarEstadisticaOk(comprobanteView.getImporteSeleccionado(), cuenta,
                    EstadisticasConstants.VISUALIZACION_COMPROBANTE_ACREDITACION_PRESTAMO_UVA);
            return true;
        } else {
            grabarEstadisticaOk(comprobanteView.getImporteSeleccionado(), cuenta,
                    EstadisticasConstants.VISUALIZACION_COMPROBANTE_ACREDITACION_PRESTAMO);
            return true;
        }
    }

    /**
     * Manejar flags.
     *
     * @param flagPrestamoInhabilitado the flag prestamo inhabilitado
     * @param flagNoLineaCrediticia    the flag no linea crediticia
     * @param cuentasUnicasClass       the cuentas unicas class
     * @return the respuesta
     */
    private Respuesta<List<ConfiguracionPrestamoView>> manejarFlags(Boolean flagPrestamoInhabilitado,
                                                                    Boolean flagNoLineaCrediticia, Class<List<ConfiguracionPrestamoView>> cuentasUnicasClass) {

        Respuesta<List<ConfiguracionPrestamoView>> respuesta;
        if (!flagPrestamoInhabilitado) {
            estadisticaManager.add(EstadisticasConstants.INGRESO_SIMULADOR_PRESTAMO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            respuesta = respuestaFactory.crearRespuestaError(cuentasUnicasClass,
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR).getMensaje(),
                    null);
        } else if (flagNoLineaCrediticia) {
            estadisticaManager.add(EstadisticasConstants.INGRESO_SIMULADOR_PRESTAMO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
            respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(cuentasUnicasClass, mensajeBO
                            .obtenerMensajePorCodigo(CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO).getMensaje(),
                    TipoError.ERROR_LINEA_CREDITICIA_SIN_MONTO_PERMITIDO.getDescripcion());
        } else {
            estadisticaManager.add(EstadisticasConstants.INGRESO_SIMULADOR_PRESTAMO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
            respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(cuentasUnicasClass,
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PRESTAMO_INHABILITADO).getMensaje(),
                    INHABILITADO);
        }

        return respuesta;

    }

    /**
     * Grabar estadistica ok.
     *
     * @param importe           the importe
     * @param cuenta            the cuenta
     * @param codigoTransaccion the codigo transaccion
     */
    private void grabarEstadisticaOk(String importe, Cuenta cuenta, String codigoTransaccion) {

        String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
        String nroSucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
        String nroCuentaFormateado = nroSucursal + "-" + nroCuenta;

        Estadistica estadistica = new Estadistica();
        estadistica.setImporte(importe);
        estadistica.setMoneda("$");
        estadistica.setNroCtaOrigen(nroCuentaFormateado);
        estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        estadistica.setCodigoTransaccion(codigoTransaccion);
        try {
            estadisticaBo.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
        } catch (BusinessException e) {
            LOGGER.error("Error al tratar de grabar estadistica codigo: {}, {}", codigoTransaccion, e);
        }
    }

    /**
     * Grabar estadistica error.
     *
     * @param importe           the importe
     * @param cuenta            the cuenta
     * @param codigoTransaccion the codigo transaccion
     * @param codigoError       the codigo error
     */
    private void grabarEstadisticaError(String importe, Cuenta cuenta, String codigoTransaccion, String codigoError) {

        String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
        String nroSucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
        String nroCuentaFormateado = nroSucursal + "-" + nroCuenta;

        Estadistica estadistica = new Estadistica();
        estadistica.setImporte(importe);
        estadistica.setMoneda("$");
        estadistica.setNroCtaOrigen(nroCuentaFormateado);
        if (EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING.equals(codigoError)) {
            estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
        } else {
            estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        estadistica.setCodigoTransaccion(codigoTransaccion);
        try {
            estadisticaBo.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
        } catch (BusinessException e) {
            LOGGER.error("Error al intentar grabar la estadistica: {}, {}", codigoTransaccion, e);
        }
    }

    /**
     * Validacion objeto request y obtiene la configuracion del prestamo
     *
     * @param solicitudSimulacion the solicitud simulacion
     * @return the respuesta
     */
    private Respuesta<ConfiguracionPrestamoDTO> obtenerConfiguracionPrestamo(SolicitudSimulacionView solicitudSimulacion, String fase) {

        //Valida restricciones de los campos sobre la entidad
        if (!LIQUIDAR_ENCOLADO.equals(fase) && !FASE_CANCELAR_USUARIO.equals(fase)) {
            if (!ValidationEntity.validate(solicitudSimulacion)) {
                return respuestaFactory.crearRespuestaError(ConfiguracionPrestamoDTO.class,
                        mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR).getMensaje(),
                        null);
            }
        }

        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuenta = cliente.getCuenta(solicitudSimulacion.getCbu());

        try {

            //Obtiene config de prestamo para la cuenta del cliente
            ConfiguracionPrestamoDTO configuracionPrestamoDto = simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(cliente, cuenta);

            if (LIQUIDAR_ENCOLADO.equals(fase)|| FASE_CANCELAR_USUARIO.equals(fase)) {
                PrestamoPermitidoEntity prestamoPermitido = configuracionPrestamoDto.getPrestamoPermitido().obtenerInfoPrestamoPorProducto(solicitudSimulacion.getProducto(), solicitudSimulacion.getSubproducto(), Integer.parseInt(solicitudSimulacion.getCuotaSeleccionada()));
                String idRangoSeleccionado = configuracionPrestamoDto.getPrestamoPermitido().getId(prestamoPermitido);
                solicitudSimulacion.setIdRangoSeleccionado(idRangoSeleccionado);
            }

            //Calcula rangos de cuotas segun configuracion y request
            PrestamoPermitidoEntity rangoCuota = configuracionPrestamoDto.getPrestamoPermitido()
                    .obtenerInfoPrestamoPorCuotas(Integer.parseInt(solicitudSimulacion.getCuotaSeleccionada()),
                            solicitudSimulacion.isUva(), solicitudSimulacion.isTasaFija(),
                            solicitudSimulacion.getIdRangoSeleccionado());

            //Checkea rangos y devuelve respuesta acorde
            List<ItemMensajeRespuesta> errores = simuladorPrestamoBo.chequearRangos(solicitudSimulacion, rangoCuota);
            return CollectionUtils.isEmpty(errores)
                    ? respuestaFactory.crearRespuestaOk(ConfiguracionPrestamoDTO.class, configuracionPrestamoDto)
                    : respuestaFactory.crearRespuestaWarning(ConfiguracionPrestamoDTO.class, null, errores);

        } catch (BusinessException e) {
            LOGGER.error("Error al adquirir prestamo: ", e);
            return respuestaFactory.crearRespuestaError(ConfiguracionPrestamoDTO.class,
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR).getMensaje(),
                    null);
        }
    }

    /**
     * Manejar business exception en simular.
     *
     * @param e                       the e
     * @param cuenta                  the cuenta
     * @param solicitudSimulacionView the solicitud simulacion view
     * @param configuracionPrestamo   the configuracion prestamo
     * @return the respuesta
     */
    private Respuesta<ResultadoSimulacionView> manejarBusinessExceptionEnSimular(BusinessException e, Cuenta cuenta,
                                                                                 SolicitudSimulacionView solicitudSimulacionView,
                                                                                 Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo) {
        PrestamoPermitidoEntity rangoCuota = configuracionPrestamo.getRespuesta().getPrestamoPermitido()
                .obtenerInfoPrestamoPorCuotas(Integer.parseInt(solicitudSimulacionView.getCuotaSeleccionada()),
                        solicitudSimulacionView.isUva(), solicitudSimulacionView.isTasaFija(),
                        solicitudSimulacionView.getIdRangoSeleccionado());
        if (CodigoMensajeConstantes.CODIGO_ERROR_DIA_NO_HABIL_MSG.equals(e.getMessage())
                || CodigoMensajeConstantes.CODIGO_ERROR_DIA_FUERA_RANGO_MSG.equals(e.getMessage())) {
            if (rangoCuota.esUVA()) {
                grabarEstadisticaError(
                        obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                        EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO_UVA, CODIGO_ERROR);
            } else {
                grabarEstadisticaError(
                        obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                        EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO, CODIGO_ERROR);
            }
            return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ResultadoSimulacionView.class,
                    mensajeBO.obtenerMensajePorCodigo(e.getMessage()).getMensaje(), "ERROR_FECHA_FORMULARIO");
        } else if (CodigoMensajeConstantes.ERROR_CANTIDAD_CUOTAS_SIMULADOR_PRESTAMOS.equals(e.getMessage())) {
            if (rangoCuota.esUVA()) {
                grabarEstadisticaError(
                        obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                        EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO_UVA, CODIGO_ERROR);
            } else {
                grabarEstadisticaError(
                        obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                        EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO, CODIGO_ERROR);
            }
            return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ResultadoSimulacionView.class,
                    mensajeBO.obtenerMensajePorCodigo(e.getMessage()).getMensaje(), "CANTIDAD_CUOTAS");
        } else if (CodigoMensajeConstantes.ALTA_PRESTAMO_INHABILITADA.equals(e.getMessage())) {
            LOGGER.info("Simulacion de prestamo inhabilitada");
            return respuestaFactory.crearRespuestaError(ResultadoSimulacionView.class,
                    mensajeBO.obtenerMensajePorCodigo(e.getMessage()).getMensaje(), null);
        } else if (CodigoMensajeConstantes.CODIGO_ERROR_SOLICITUD_PENDIENTE_YA_EXISTE_MSG.equals(e.getMessage())) {
//        	LOGGER.info("Simulacion de prestamo inhabilitada");
//        	return respuestaFactory.crearRespuestaError(ResultadoSimulacionView.class,
            return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ResultadoSimulacionView.class,
                    mensajeBO.obtenerMensajePorCodigo(e.getMessage()).getMensaje(), "CODIGO_ERROR_SOLICITUD_PENDIENTE_YA_EXISTE");
        }
        if (rangoCuota.esUVA()) {
            grabarEstadisticaError(
                    obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                    EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO_UVA, CODIGO_WARNING);
        } else {
            grabarEstadisticaError(
                    obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                    EstadisticasConstants.RESULTADO_SIMULACION_PRESTAMO, CODIGO_WARNING);
        }
        return respuestaFactory.crearRespuestaError(ResultadoSimulacionView.class,
                mensajeBO.obtenerMensajePorCodigo(codigoErrorGenerico).getMensaje(), null);
    }

    /**
     * Manejar business exception en adquirir prestamo.
     *
     * @param e                       the e
     * @param cuenta                  the cuenta
     * @param solicitudSimulacionView the solicitud simulacion view
     * @param configuracionPrestamo   the configuracion prestamo
     * @return the respuesta
     */
    private Respuesta<ComprobanteFeedbackView> manejarBusinessExceptionEnAdquirirPrestamo(BusinessException e,
                                                                                          Cuenta cuenta, SolicitudSimulacionView solicitudSimulacionView,
                                                                                          Respuesta<ConfiguracionPrestamoDTO> configuracionPrestamo) {
        PrestamoPermitidoEntity rangoCuota = configuracionPrestamo.getRespuesta().getPrestamoPermitido()
                .obtenerInfoPrestamoPorCuotas(Integer.parseInt(solicitudSimulacionView.getCuotaSeleccionada()),
                        solicitudSimulacionView.isUva(), solicitudSimulacionView.isTasaFija(),
                        solicitudSimulacionView.getIdRangoSeleccionado());
        if (rangoCuota.esUVA()) {
            grabarEstadisticaError(
                    obtenerImporteCorrectoParaEstadistica(solicitudSimulacionView.getImporteSeleccionado()), cuenta,
                    EstadisticasConstants.RESULTADO_ALTA_PRESTAMO_UVA, CODIGO_WARNING);
        } else {
            grabarEstadisticaError(solicitudSimulacionView.getImporteSeleccionado(), cuenta,
                    EstadisticasConstants.RESULTADO_ALTA_PRESTAMO, CODIGO_WARNING);
        }

        if (CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS.equals(e.getMessage())) {
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_PRESTAMO_48HS);
        } else if (CodigoMensajeConstantes.ALTA_PRESTAMO_INHABILITADA.equals(e.getMessage())) {
            LOGGER.info("Alta de prestamo inhabilitada");
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    e.getMessage());
        } else if (CodigoMensajeConstantes.TOKEN_PRESTAMO_ERROR.equals(e.getMessage())) {
            return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(TipoError.TOKEN_PRESTAMO_ERROR.toString(), "No pudimos validar tu identidad");
        }
        if (CodigoMensajeConstantes.SOLICITUD_TOKEN_PRESTAMO.equals(e.getMessage())) {
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SOLICITUD_TOKEN_PRESTAMO,
                    e.getMessage());
        } else if (CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO.equals(e.getMessage())) {
//        	LOGGER.info("Error en la Liquidación de prestamo encolado en ALTPMOPREA");
            return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO).getMensaje(), "CODIGO_ERROR_LIQUIDAR_ENCOLADO");
        }

        TipoError tipoError = !sesionParametros.getContador().permiteReintentar() ? TipoError.ERROR_REINTENTOS_AGOTADOS
                : TipoError.WARNING_REINTENTOS;
        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, tipoError,
                CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_PRESTAMO_48HS);

    }

    /**
     * Crear respuesta error fuera de horario.
     *
     * @param <T>            the generic type
     * @param claseRespuesta the clase respuesta
     * @return the respuesta
     */
    private <T> Respuesta<T> crearRespuestaErrorFueraDeHorario(Class<T> claseRespuesta) {
        String mensaje = mensajeBO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_SIMULADOR_PRESTAMOS)
                .getMensaje();
        mensaje = MessageFormat.format(mensaje, horaInicioOperaciones, horaFinOperaciones);
        return respuestaFactory.crearRespuestaErrorPersonalizado(claseRespuesta, mensaje, FUERA_DE_HORARIO);
    }

    /**
     * busco las ofertas comerciales para el destino de prestamo seleccionado,
     * si no se encuentra ninguna oferta, retorna null.
     *
     * @param solicitud the solicitud
     * @return the web content view
     */
    private WebContentView obtenerContenidoLink(SolicitudSimulacionView solicitud) {
        WebContentView contenido = null;
        List<String> urls = new ArrayList<String>();
        LOGGER.info("Obtener contenido Link para el motivo {}:{}, del prestamo solicitado",
                solicitud.getMotivoSeleccionado().getId(), solicitud.getMotivoSeleccionado().getDescripcion());
        urls.addAll(segurosUrl.getUrls(solicitud.getMotivoSeleccionado().getId()));
        urls.add(segurosUrl.getOfertaDefault());
        LOGGER.info("Urls a procesar {}", urls);
        List<OfertaComercialDTO> ofertasComerciales = sesionParametros.getOfertasComerciales().getOfertas();
        Iterator<String> itUrls = urls.iterator();
        while (itUrls.hasNext()) {
            String url = itUrls.next();
            Iterator<OfertaComercialDTO> itOfertas = ofertasComerciales.iterator();
            while (itOfertas.hasNext()) {
                OfertaComercialDTO oferta = itOfertas.next();
                LinkView linkView = oferta.getGotoLink().obtenerLinkContent();
                if (linkView != null && url.equals(linkView.getUrl())) {
                    contenido = new WebContentView();
                    contenido.setTitulo(oferta.getTitulo());
                    contenido.setDescripcion(oferta.getDescripcion());
                    contenido.setLink(linkView.getUrl());
                    contenido.setBoton(LABEL_BOTON);
                    LOGGER.info("Para el motivo {} seleccionado se retorna la oferta {}",
                            solicitud.getMotivoSeleccionado().getDescripcion(), oferta.getIdInterno());
                    return contenido;
                }
            }
        }
        LOGGER.info("NO hay ofertas para el motivo {} seleccionado", solicitud.getMotivoSeleccionado().getId());
        return contenido;
    }


    /**
     * Guardo los datos en la respuesta del comprobante
     * para persistir datos en servicio de prestamos
     *
     * @param solSimulacionView,
     * @param respDesafio
     */
    private void setDatosRespuestaComprobante(SolicitudSimulacionView solSimulacionView, ComprobanteFeedbackView respDesafio) {
        respDesafio.setCbu(solSimulacionView.getCbu());
        respDesafio.setImporteSeleccionado(solSimulacionView.getImporteSeleccionado());
        respDesafio.setCuotaSeleccionada(solSimulacionView.getCuotaSeleccionada());
        respDesafio.setFechaSeleccionada(solSimulacionView.getFechaSeleccionada());
        respDesafio.setMotivoSeleccionado(solSimulacionView.getMotivoSeleccionado());
        respDesafio.setReintentar(solSimulacionView.getReintentar());
        respDesafio.setUva(solSimulacionView.isUva());
        respDesafio.setTasaFija(solSimulacionView.isTasaFija());
        respDesafio.setIdRangoSeleccionado(solSimulacionView.getIdRangoSeleccionado());
        respDesafio.setLegal(solSimulacionView.getLegal());
        respDesafio.setCantiCuentas(solSimulacionView.getCantiCuentas());
        respDesafio.setDestino(solSimulacionView.getDestino());
    }

    @Override
    public Boolean chequearSiEstaEnHorarioOperacion() {
        return simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion();
    }

}