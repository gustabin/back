/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;


import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.customers.model.Customers;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.ScoringApi;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.exception.ScoringApiException;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityType;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.*;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.*;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO;
import ar.com.santanderrio.obp.servicios.pagohaberes.common.PagoHaberesUtils;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.PagoHaberesManager;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class PagoHaberesManagerImpl.
 */
@Component
public class PagoHaberesManagerImpl implements PagoHaberesManager {

    /** The Constant LOG_ESTADISTICA_RESULTADO. */
    private static final String LOG_ESTADISTICA_RESULTADO = "Codigo transaccion estadÃ­stica: {} - Resultado: {}";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoHaberesManagerImpl.class);

    /** The Constant SUCURSAL_PAD_SIZE. */
    private static final int SUCURSAL_PAD_SIZE = 3;

    /** The Constant RESETEO_RSA. */
    private static final String RESETEO_RSA = "Reseteando desafio en curso";

    /** The Constant ZERO_STR. */
    public static final String ZERO_STR = "0";

    /** The Constant FORMATTER. */
    public static final String FORMATTER = "dd/MM/yyyy";
    
    /** The Constant FORMATTER. */
    public static final String COD_RET_CUENTA_CERRADA = "0000114";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The pago de Haberes BO. */
    @Autowired
    private PagoHaberesBO pagoHaberesBO;

    /** The sesion cliente. */
    @Autowired
    protected SesionCliente sesionCliente;

    @Autowired
    private CustomersApi customersApi;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The cuenta manager. */
    @Autowired
    private CuentaManager cuentaManager;

    /** The autentificacion manager. */
    @Autowired
    private AutentificacionManager autentificacionManager;

    /** The legal dao. */
    @Autowired
    private LegalDAO legalDao;

    /** The mensaje bo. */
    @Autowired
    private MensajeBO mensajeBo;
    
    @Autowired
    private ClienteBO clienteBO;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<ComprobantePagoHaberesCBUEntity> desafioOperacionRSASimple;

    @Autowired
    private BiocatchManager biocatchManager;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<ComprobantePagoHaberesPagoSimpleMultipleEntity> desafioOperacionRSAMultiple;
    
    /** The rsa manager. */
    @Autowired
    private RsaManager rsaManager;

    /** The valor desafio pago haberes. */
    @Value("${TRJCOORD.OPERAINDISTINTO.PAGOHABERES:3}")
    private Integer valorDesafioPagoHaberes;

    /** The pago haberes limite. */
    @Value("${PAGOHABERES.LIMITE}")
    private String pagoHaberesLimite;

    private static final float DEFAULT_DESTINATION_SCORING = -1;

    @Autowired
    private ScoringApi scoringApi;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#obtenerPagoHaberes()
     */
    public Respuesta<PagoHaberesView> obtenerPagoHaberes() {
        LOGGER.info("Obtiene pagos informados y programados");
        Respuesta<PagoHaberesView> respuesta = new Respuesta<PagoHaberesView>();

        Respuesta<PagoHaberesEntity> pagoHaberes = pagoHaberesBO
                .obtenerConsultaAgendamiento7x24(sesionCliente.getCliente());

        if (EstadoRespuesta.ERROR.equals(pagoHaberes.getEstadoRespuesta())) {
            LOGGER.debug("Error al obtener datos de pagos informados y programados");
            respuesta.setEstadoRespuesta(pagoHaberes.getEstadoRespuesta());
            List<ItemMensajeRespuesta> item = pagoHaberes.getItemsMensajeRespuesta();
            respuesta.setItemMensajeRespuesta(item);
            return respuesta;
        }

        LOGGER.info("Obteniendo datos de pagos informados y programados");
        respuesta = getPagoHaberesView(pagoHaberes.getRespuesta());

        sesionParametros.getEmpleadoPagoHaberesList().clear();

        if(respuesta.getRespuesta() != null && respuesta.getRespuesta().getPagoHaberesEmpleadosView() != null) {

            for (PagoInformadoView pagoInformadoView : respuesta.getRespuesta().getPagoHaberesEmpleadosView()) {

                EmpleadoPagoHaberes empleadoPagoHaberes = new EmpleadoPagoHaberes();
                empleadoPagoHaberes.setId(pagoInformadoView.getId());
                empleadoPagoHaberes.setNumeroCuenta(pagoInformadoView.getCuentaDestino());

                sesionParametros.getEmpleadoPagoHaberesList().add(empleadoPagoHaberes);
            }

        }

        // Guardo en sesion la grilla completa
        sesionParametros.setPagoHaberesInformadosView(respuesta.getRespuesta().getPagoHaberesEmpleadosView());

        estadisticaManager.add(EstadisticasConstants.SOLICITUD_PAGO_HABERES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuesta;
    }

    /**
     * Gets the pago haberes view.
     *
     * @param pagoHaberesDTO
     *            the pago haberes DTO
     * @return the pago haberes view
     */
    private Respuesta<PagoHaberesView> getPagoHaberesView(PagoHaberesEntity pagoHaberesDTO) {

        Respuesta<PagoHaberesView> respuesta = new Respuesta<PagoHaberesView>();
        PagoHaberesView pagoHaberesView = new PagoHaberesView();

        if (pagoHaberesDTO.getPagosInformadosList().isEmpty()) {
            // RESPUESTA EMPLEADOS VACIOS

            respuesta = respuestaFactory.crearRespuestaWarning(pagoHaberesView, "", TipoError.USUARIO_SIN_EMPLEADOS,
                    CodigoMensajeConstantes.CODIGO_PAGO_HABERES_SIN_EMPLEADO);
            return respuesta;
        } else {
            // Arma lista de pagos informados
            List<PagoInformadoView> pagosInformadosViewList = getPagosInformadosViewList(
                    pagoHaberesDTO.getPagosInformadosList());
            pagoHaberesView.setPagoHaberesEmpleadosView(pagosInformadosViewList);
            pagoHaberesView.setTotalNomina(PagoHaberesUtils.formateadorImporte(pagoHaberesDTO.getTotalNomina()));
            if (pagoHaberesDTO.getPagosProgramadosList().isEmpty()) {
                // RESPUESTA PAGOS PROGRAMADOS VACIOS
                respuesta = respuestaFactory.crearRespuestaWarning(null, TipoError.USUARIO_SIN_PAGO_PROGRAMADO,
                        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_SIN_PAGO_PROGRAMADO);
                respuesta.setRespuesta(pagoHaberesView);
            } else {
                // Arma lista de pagos programados
                List<PagoProgramadoView> pagosProgramadosViwList = getPagosProgramadosViewList(
                        pagoHaberesDTO.getPagosProgramadosList());
                pagoHaberesView.setPagoHaberesAgendadosView(pagosProgramadosViwList);
                pagoHaberesView.setTotalPagosProgramados(
                        PagoHaberesUtils.formateadorImporte(pagoHaberesDTO.getTotalPagosProgramados()));
                respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            }
        }
        respuesta.setRespuesta(pagoHaberesView);
        return respuesta;
    }

    /**
     * Gets the pagos informados view list.
     *
     * @param pagosInformados
     *            the pagos informados
     * @return the pagos informados view list
     */
    private List<PagoInformadoView> getPagosInformadosViewList(List<PagosInformadosEntity> pagosInformados) {
        List<PagoInformadoView> piViewList = new ArrayList<PagoInformadoView>();
        int c = 0;
        for (Iterator<PagosInformadosEntity> iterator = pagosInformados.iterator(); iterator.hasNext();) {
            ++c;
            PagosInformadosEntity pi = (PagosInformadosEntity) iterator.next();
            PagoInformadoView piView = obtenerPagoInformadoView(pi);
            piView.setId(String.valueOf(c));
            piViewList.add(piView);
        }
        return piViewList;
    }

    /**
     * Gets the pagos programados view list.
     *
     * @param pagosProgramados
     *            the pagos programados
     * @return the pagos programados view list
     */
    private List<PagoProgramadoView> getPagosProgramadosViewList(List<PagosProgramadosEntity> pagosProgramados) {
        List<PagoProgramadoView> ppViewList = new ArrayList<PagoProgramadoView>();
        int c = 0;
        for (Iterator<PagosProgramadosEntity> iterator = pagosProgramados.iterator(); iterator.hasNext();) {
            ++c;
            PagosProgramadosEntity pp = (PagosProgramadosEntity) iterator.next();
            PagoProgramadoView ppView = obtenerPagoProgramadoView(pp);
            ppView.setId(String.valueOf(c));
            ppViewList.add(ppView);
        }
        return ppViewList;
    }

    /**
     * Obtener pago informado view.
     *
     * @param pagosInformadosDTO
     *            the pagos informados DTO
     * @return the pago informado view
     */
    private PagoInformadoView obtenerPagoInformadoView(PagosInformadosEntity pagosInformadosDTO) {
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView
                .setDescripcionEmpleado(ISBANStringUtils.convertirStringToCamelcase(pagosInformadosDTO.getEmpleado()));
        pagoInformadoView.setTipoDocumento(PagoHaberesUtils.obtieneTipoDocumento(pagosInformadosDTO.getTipoCuitCuil()));
        pagoInformadoView.setDocumento(ISBANStringUtils.formatearCuil(pagosInformadosDTO.getCuitCuil()));
        pagoInformadoView.setTipoCuentaDestino(
                TipoCuenta.fromCodigo(pagosInformadosDTO.getTipoCtaCredito()).getDescripcionConMoneda());
        pagoInformadoView
                .setAbreviaturaDestino(TipoCuenta.fromCodigo(pagosInformadosDTO.getTipoCtaCredito()).getAbreviatura());
        String sucursalDestino = StringUtils.leftPad(
                String.valueOf(Long.parseLong(pagosInformadosDTO.getSucCtaCredito())), SUCURSAL_PAD_SIZE, ZERO_STR);
        String numeroCuentaDestino = ISBANStringUtils.formatearNumeroCuenta(pagosInformadosDTO.getNroCtaCredito());
        pagoInformadoView.setCuentaDestino(
                new StringBuilder().append(sucursalDestino).append("-").append(numeroCuentaDestino).toString());
        pagoInformadoView.setTipoPago(PagoHaberesUtils.obtieneTipoPago(pagosInformadosDTO.getTipoPago()));
        pagoInformadoView.setConcepto(pagosInformadosDTO.getDescripcionConcepto());
        pagoInformadoView.setImporte(PagoHaberesUtils.formateadorImporte(pagosInformadosDTO.getImporte()));
        pagoInformadoView.setTipoPersona(pagosInformadosDTO.getTipoPersona());
        pagoInformadoView.setTipoId(pagosInformadosDTO.getTipoId());
        pagoInformadoView.setIdCliente(pagosInformadosDTO.getIdCliente());
        pagoInformadoView.setFechaNac(pagosInformadosDTO.getFechaNac());
        pagoInformadoView.setNup(pagosInformadosDTO.getNup());
        pagoInformadoView.setIdTransaccion(pagosInformadosDTO.getIdTransaccion());

        pagoInformadoView.setDivisa(pagosInformadosDTO.getDivisa());
        String sucursalOrigen = StringUtils.leftPad(
                String.valueOf(Long.parseLong(pagosInformadosDTO.getSucCtaDebito())), SUCURSAL_PAD_SIZE, ZERO_STR);
        String numeroCuentaOrigen = ISBANStringUtils.formatearNumeroCuenta(pagosInformadosDTO.getNroCtaDebito());
        pagoInformadoView.setCuentaOrigen(
                new StringBuilder().append(sucursalOrigen).append("-").append(numeroCuentaOrigen).toString());
        pagoInformadoView.setAliasOrigen(obtenerAlias(pagoInformadoView.getCuentaOrigen()));
        pagoInformadoView.setTipoCuentaOrigen(
                TipoCuenta.fromCodigo(pagosInformadosDTO.getTipoCtaDebito()).getDescripcionConMoneda());
        pagoInformadoView
                .setAbreviaturaOrigen(TipoCuenta.fromCodigo(pagosInformadosDTO.getTipoCtaDebito()).getAbreviatura());
        pagoInformadoView.setNroRecurrencia(pagosInformadosDTO.getNroRecurrencia());
        pagoInformadoView.setTipoCuil(pagosInformadosDTO.getTipoCuitCuil());
        pagoInformadoView.setCuil(ISBANStringUtils.eliminarGuionesDeCuil(pagosInformadosDTO.getCuitCuil()));
        pagoInformadoView.setFechaBaseRecurrencia(pagosInformadosDTO.getFechaBaseRecurrencia());
        pagoInformadoView.setFrecRecurrencia(pagosInformadosDTO.getFrecRecurrencia());
        pagoInformadoView.setMaxRecurrencia(pagosInformadosDTO.getMaxRecurrencia());
        pagoInformadoView.setTipoRecurrencia(pagosInformadosDTO.getTipoRecurrencia());
        pagoInformadoView.setTienePagoProgramado(pagosInformadosDTO.getTienePagoProgramado());

        return pagoInformadoView;
    }

    /**
     * obtener Alias.
     *
     * @param nroCuenta
     *            the nro cuenta
     * @return alias
     */
    private String obtenerAlias(String nroCuenta) {
        String alias = null;
        AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(nroCuenta);
        if (cuenta != null && cuenta.getAlias() != null) {
            alias = cuenta.getAlias().trim().isEmpty() ? null : cuenta.getAlias();
        }
        return alias;
    }

    /**
     * Obtener pago programado view.
     *
     * @param pagosProgramadosDTO
     *            the pagos programados DTO
     * @return the pago programado view
     */
    private PagoProgramadoView obtenerPagoProgramadoView(PagosProgramadosEntity pagosProgramadosDTO) {
        PagoProgramadoView pagoProgramadoView = new PagoProgramadoView();
        if (pagosProgramadosDTO.getFechaEjecucion() != null) {
            pagoProgramadoView.setFecha(ISBANStringUtils.formatearFecha(pagosProgramadosDTO.getFechaEjecucion()));
        }
        pagoProgramadoView.setDestinatarioNombre(pagosProgramadosDTO.getEmpleado());
        pagoProgramadoView.setDestinatarioTipoDocumento(
                PagoHaberesUtils.obtieneTipoDocumento(pagosProgramadosDTO.getTipoCuitCuil()));
        pagoProgramadoView.setDestinatarioDocumento(ISBANStringUtils.formatearCuil(pagosProgramadosDTO.getCuitCuil()));
        pagoProgramadoView.setDestinatarioTipoCuenta(
                TipoCuenta.fromCodigo(pagosProgramadosDTO.getTipoCtaCredito()).getDescripcionConMoneda());
        String sucursalDestino = StringUtils.leftPad(
                String.valueOf(Long.parseLong(pagosProgramadosDTO.getSucCtaCredito())), SUCURSAL_PAD_SIZE, ZERO_STR);
        String numeroCuentaDestino = ISBANStringUtils.formatearNumeroCuenta(pagosProgramadosDTO.getNroCtaCredito());
        pagoProgramadoView.setCuentaDestino(
                new StringBuilder().append(sucursalDestino).append("-").append(numeroCuentaDestino).toString());
        pagoProgramadoView.setTipoPago(PagoHaberesUtils.obtieneTipoPago(pagosProgramadosDTO.getTipoPago()));
        pagoProgramadoView.setConcepto(pagosProgramadosDTO.getDescripcionConcepto());
        if (pagosProgramadosDTO.getImporte() != null) {
            pagoProgramadoView.setImporte(PagoHaberesUtils.formateadorImporte(pagosProgramadosDTO.getImporte()));
        }
        pagoProgramadoView.setTipoAgendamiento(
                PagoHaberesUtils.obtieneTipoAgendamiento(pagosProgramadosDTO.getTipoAgendamiento()));

        pagoProgramadoView.setDivisa(pagosProgramadosDTO.getDivisa());
        String sucursalOrigen = StringUtils.leftPad(
                String.valueOf(Long.parseLong(pagosProgramadosDTO.getSucCtaDebito())), SUCURSAL_PAD_SIZE, ZERO_STR);
        String numeroCuentaOrigen = ISBANStringUtils.formatearNumeroCuenta(pagosProgramadosDTO.getNroCtaDebito());
        pagoProgramadoView.setCuentaOrigen(
                new StringBuilder().append(sucursalOrigen).append("-").append(numeroCuentaOrigen).toString());
        pagoProgramadoView.setAliasOrigen(obtenerAlias(pagoProgramadoView.getCuentaOrigen()));
        pagoProgramadoView.setFechaNac(pagosProgramadosDTO.getFechaNac());
        pagoProgramadoView.setIdCliente(pagosProgramadosDTO.getIdCliente());
        pagoProgramadoView.setIdTransaccion(pagosProgramadosDTO.getIdTransaccion());
        pagoProgramadoView.setNup(pagosProgramadosDTO.getNup());
        pagoProgramadoView.setTipoCuentaOrigen(
                TipoCuenta.fromCodigo(pagosProgramadosDTO.getTipoCtaDebito()).getDescripcionConMoneda());
        pagoProgramadoView.setTipoId(pagosProgramadosDTO.getTipoId());
        pagoProgramadoView.setTipoPersona(pagosProgramadosDTO.getTipoPersona());
        pagoProgramadoView.setNroRecurrencia(pagosProgramadosDTO.getNroRecurrencia());

        return pagoProgramadoView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#isClienteHabilitado()
     */
    public Respuesta<Boolean> isClienteHabilitado() {
        Respuesta<Boolean> respuesta = pagoHaberesBO.isClienteAdheridoPagoHaberes(sesionCliente.getCliente());
        if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            return respuesta;
        } else {
            if (respuesta.getRespuesta()) {
                LOGGER.info("Cliente adherido al servicio pago de haberes");
                respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
                estadisticaManager.add(EstadisticasConstants.SOLICITUD_PAGO_HABERES,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                LOGGER.info("Cliente no adherido al servicio pago de haberes");
                respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
                Mensaje respMensaje = mensajeManager
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.USUARIO_NO_AFILIADO);
                ItemMensajeRespuesta item = new ItemMensajeRespuesta();
                item.setMensaje(respMensaje.getMensaje());
                respuesta.add(item);
                estadisticaManager.add(EstadisticasConstants.SOLICITUD_PAGO_HABERES,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#agregarEmpleado(ar.com.santanderrio.obp.servicios.
     * pagohaberes.web.view.PagoHaberesValidarView)
     */
    @Override
    public Respuesta<PagoInformadoView> validarEmpleado(PagoInformadoView pagoInformadoView) {
        Respuesta<PagoInformadoView> respuesta = new Respuesta<PagoInformadoView>();

        Respuesta<PagoInformadoView> respuestaValidarEmpleado = pagoHaberesBO
                .validarEmpleado(sesionCliente.getCliente(), pagoInformadoView);

        if (EstadoRespuesta.ERROR.equals(respuestaValidarEmpleado.getEstadoRespuesta())) {
            LOGGER.debug("Error al validar empleado en modulo pago de haberes");
            respuesta.setEstadoRespuesta(respuestaValidarEmpleado.getEstadoRespuesta());
            List<ItemMensajeRespuesta> item = respuestaValidarEmpleado.getItemsMensajeRespuesta();
            respuesta.setItemMensajeRespuesta(item);
            return respuesta;
        }

        LOGGER.info("Empleado validado en modulo pago de haberes");
        respuesta.setEstadoRespuesta(respuestaValidarEmpleado.getEstadoRespuesta());
        respuesta.setRespuesta(respuestaValidarEmpleado.getRespuesta());

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#agregarEmpleado(ar.com.santanderrio.obp.servicios.
     * pagohaberes.web.view.PagoHaberesAgregarView)
     */
    @Override
    public Respuesta<ComprobanteAdhesionEmpleadoEntity> agregarEmpleado(PagoInformadoView pagoInformadoView) {
        Respuesta<ComprobanteAdhesionEmpleadoEntity> respuesta = new Respuesta<ComprobanteAdhesionEmpleadoEntity>();

        if (pagoInformadoView.getPrimeraVez()) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        Respuesta<ComprobanteAdhesionEmpleadoEntity> respuestaAgregarEmpleado = pagoHaberesBO
                .agregarEmpleado7x24(sesionCliente.getCliente(), pagoInformadoView);

        if (EstadoRespuesta.ERROR.equals(respuestaAgregarEmpleado.getEstadoRespuesta())) {
            LOGGER.debug("Error al agregar empleado en modulo pago de haberes");
            respuesta.setEstadoRespuesta(respuestaAgregarEmpleado.getEstadoRespuesta());
            List<ItemMensajeRespuesta> item = respuestaAgregarEmpleado.getItemsMensajeRespuesta();
            respuesta.setItemMensajeRespuesta(item);

            if ("S".equals(pagoInformadoView.getTipoPago())) {
                LOGGER.info("Grabando estadistica - Pago de haberes alta de empleado - Sueldo");
                estadisticaManager.add(EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_SUELDO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_SUELDO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else {
                LOGGER.info("Grabando estadistica - Pago de haberes alta de empleado - Honorario");
                estadisticaManager.add(EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_HONORARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_HONORARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
            return respuesta;
        }
        if ("S".equals(pagoInformadoView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de haberes alta de empleado - Sueldo");
            estadisticaManager.add(EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_SUELDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_SUELDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            LOGGER.info("Grabando estadistica - Pago de haberes alta de empleado - Honorario");
            estadisticaManager.add(EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_HONORARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.PAGO_HABERES_ALTA_EMPLEADO_HONORARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        respuesta.setEstadoRespuesta(respuestaAgregarEmpleado.getEstadoRespuesta());
        respuesta.setRespuesta(respuestaAgregarEmpleado.getRespuesta());
        LOGGER.info("Empleado agregado en modulo pago de haberes");

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#eliminarEmpleado(ar.com.santanderrio.obp.servicios.
     * pagohaberes.web.view.PagoHaberesEliminarView)
     */
    @Override
    public Respuesta<FeedbackMensajeView> eliminarEmpleado(PagoHaberesEliminarView pagoHaberesEliminarView) {
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();

        if (pagoHaberesEliminarView.getPrimeraVez()) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        Respuesta<FeedbackMensajeView> respuestaEliminarEmpleado = pagoHaberesBO
                .eliminarEmpleado7x24(sesionCliente.getCliente(), pagoHaberesEliminarView);

        if (EstadoRespuesta.ERROR.equals(respuestaEliminarEmpleado.getEstadoRespuesta())) {
            LOGGER.debug("Error al eliminar empleado en modulo pago de haberes");
            respuesta.setEstadoRespuesta(respuestaEliminarEmpleado.getEstadoRespuesta());
            List<ItemMensajeRespuesta> item = respuestaEliminarEmpleado.getItemsMensajeRespuesta();
            respuesta.setItemMensajeRespuesta(item);
            if ("Sueldo".equals(pagoHaberesEliminarView.getTipoPago())) {
                LOGGER.info("Grabando estadistica - Pago de haberes eliminar empleado - Sueldo");
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_SUELDO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_SUELDO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else if ("Honorario".equals(pagoHaberesEliminarView.getTipoPago())) {
                LOGGER.info("Grabando estadistica - Pago de haberes eliminar empleado - Honorario");
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_HONORARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_HONORARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

            }
            return respuesta;
        }

        LOGGER.info("Empleado eliminado en modulo pago de haberes");
        respuesta.setEstadoRespuesta(respuestaEliminarEmpleado.getEstadoRespuesta());
        respuesta.setRespuesta(respuestaEliminarEmpleado.getRespuesta());
        if ("Sueldo".equals(pagoHaberesEliminarView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de haberes eliminar empleado - Sueldo");
            estadisticaManager.add(EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_SUELDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_SUELDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if ("Honorario".equals(pagoHaberesEliminarView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de haberes eliminar empleado - Honorario");
            estadisticaManager.add(EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_HONORARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_EMPLEADO_TIPO_PAGO_HONORARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        estadisticaManager.add(EstadisticasConstants.SOLICITUD_PAGO_HABERES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#eliminarPagoProgramado(ar.com.santanderrio.obp.
     * servicios.pagohaberes.web.view.PagoHaberesEliminarView)
     */
    @Override
    public Respuesta<FeedbackMensajeView> eliminarPagoProgramado(PagoHaberesEliminarView pagoHaberesEliminarView) {
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();

        if (pagoHaberesEliminarView.getPrimeraVez()) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        Respuesta<FeedbackMensajeView> respuestaEliminarPagoProgramado = pagoHaberesBO
                .eliminarPagoProgramado7x24(sesionCliente.getCliente(), pagoHaberesEliminarView);

        if (EstadoRespuesta.ERROR.equals(respuestaEliminarPagoProgramado.getEstadoRespuesta())) {
            LOGGER.debug("Error al eliminar pago programado en modulo pago de haberes");
            respuesta.setEstadoRespuesta(respuestaEliminarPagoProgramado.getEstadoRespuesta());
            List<ItemMensajeRespuesta> item = respuestaEliminarPagoProgramado.getItemsMensajeRespuesta();
            respuesta.setItemMensajeRespuesta(item);
            if ("Sueldo".equals(pagoHaberesEliminarView.getTipoPago())) {
                LOGGER.info("Grabando estadistica - Pago de haberes eliminar pago programado - Sueldo");
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_SUELDO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_SUELDO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else if ("Honorario".equals(pagoHaberesEliminarView.getTipoPago())) {
                LOGGER.info("Grabando estadistica - Pago de haberes eliminar pago programado - Honorario");
                estadisticaManager.add(EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_HONORARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                LOGGER.info(LOG_ESTADISTICA_RESULTADO,
                        EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_HONORARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
            return respuesta;
        }

        LOGGER.info("Pago Programado eliminado en modulo pago de haberes");
        respuesta.setEstadoRespuesta(respuestaEliminarPagoProgramado.getEstadoRespuesta());
        respuesta.setRespuesta(respuestaEliminarPagoProgramado.getRespuesta());

        if ("Sueldo".equals(pagoHaberesEliminarView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de haberes eliminar pago programado - Sueldo");
            estadisticaManager.add(EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_SUELDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_SUELDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if ("Honorario".equals(pagoHaberesEliminarView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de haberes eliminar pago programado - Honorario");
            estadisticaManager.add(EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_HONORARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.ELIMINAR_PAGO_PROGRAMADO_TIPO_PAGO_HONORARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#eliminarPagoProgramado(ar.com.santanderrio.obp.
     * servicios.pagohaberes.web.view.verDetalleEmpleado)
     */
    @Override
    public Respuesta<Void> verDetalleEmpleado() {
        LOGGER.info("Grabando estadistica de ver detalle empleado mobile");
        estadisticaManager.add(EstadisticasConstants.VER_DETALLE_EMPLEADO_MOBILE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.VER_DETALLE_EMPLEADO_MOBILE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#eliminarPagoProgramado(ar.com.santanderrio.obp.
     * servicios.pagohaberes.web.view.verDetallePagoAgendado)
     */
    @Override
    public Respuesta<Void> verDetallePagoAgendado() {
        LOGGER.info("Grabando estadistica de ver detalle pago programado mobile o desktop");
        estadisticaManager.add(EstadisticasConstants.VER_DETALLE_PAGO_PROGRAMADO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        LOGGER.info(LOG_ESTADISTICA_RESULTADO, EstadisticasConstants.VER_DETALLE_PAGO_PROGRAMADO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#obtenerCuentas()
     */
    @Override
    public Respuesta<AgregarEmpleadoView> obtenerCuentas() {
        LOGGER.info("Obteniendo listado de cuentas disponibles.");
        Respuesta<AgregarEmpleadoView> respuesta = new Respuesta<AgregarEmpleadoView>();
        AgregarEmpleadoView view = new AgregarEmpleadoView();
        try {
            List<TipoDeCuentaView> lista = new ArrayList<TipoDeCuentaView>();
            lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura(),
                    TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda()));
            lista.add(new TipoDeCuentaView(TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura(),
                    TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda()));
            lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura(),
                    TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcionConMoneda()));
            view.setTiposDeCuentasView(lista);

            // Obtenemos las cuentas
            Respuesta<CuentasView> cuentas = cuentaManager.getCuentasSaldo();
            if (cuentas.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
                // Error generico.
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
                        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
            }
            view.setCuentasView(armarCuentasView(cuentas));
            respuesta.setRespuesta(view);
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            return respuesta;
        } catch (Exception ex) {
            LOGGER.error(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES, ex);
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        return null;
    }

    /**
     * Armar cuentas view.
     *
     * @param cuentas
     *            the cuentas
     * @return the cuentas view
     */
    /*
     * PagoHaberesManager#armarCuentasView() Cambia la descripcion con moneda en
     * descripcion sin moneda.
     */
    private CuentasView armarCuentasView(Respuesta<CuentasView> cuentas) {
        CuentasView cuentasView = cuentas.getRespuesta();
        for (Iterator<CuentasAdhesionDebitoView> iterator = cuentasView.getCuentas().iterator(); iterator.hasNext();) {
            CuentasAdhesionDebitoView cuentaAdhesionDebitoView = iterator.next();
            cuentaAdhesionDebitoView
                    .setAbreviaturaTipoCuenta("CU".equals(cuentaAdhesionDebitoView.getAbreviaturaTipoCuenta()) ? "CUP"
                            : cuentaAdhesionDebitoView.getAbreviaturaTipoCuenta());
            cuentaAdhesionDebitoView.setDescripcionTipoCuenta(TipoCuenta
                    .fromAbreviatura(cuentaAdhesionDebitoView.getAbreviaturaTipoCuenta()).getDescripcionConMoneda());
        }
        return cuentasView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#validacionesPagoPorCBU()
     */
    @Override
    public Respuesta<DatosDestinatarioView> validacionesPagoPorCBU(ValidacionesPagoPorCBUView validacionesPagoPorCBU) {
        Respuesta<DatosDestinatarioView> respuesta = new Respuesta<DatosDestinatarioView>();
        Respuesta<DatosDestinatarioView> respuestaValidaciones = new Respuesta<DatosDestinatarioView>();

        // limar desafios en cursos
        resetearDesafioEnCurso();

        if(pagoHaberesBO.esTipoDeClaveCVU(validacionesPagoPorCBU)){
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_CBU_INVALIDO,
                    CodigoMensajeConstantes.CODIGO_PAGO_HABERES_TIPO_CUENTA_CVU_DESHABILITADO);
        }

        if(pagoHaberesBO.esCBUSantander(validacionesPagoPorCBU)) {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_CBU_INVALIDO,
                    CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_OTROS_BANCOS_CBU_SANTANDER);
        }

        respuestaValidaciones = pagoHaberesBO.validarImporte(sesionCliente.getCliente(),
                validacionesPagoPorCBU.getTipoCuentaOrigen(), validacionesPagoPorCBU.getCuentaOrigen(),
                validacionesPagoPorCBU.getImporte());

        if (EstadoRespuesta.ERROR.equals(respuestaValidaciones.getEstadoRespuesta())) {
            LOGGER.error("Error al validar Importe en Pago de Haberes por CBU");
            respuesta.setEstadoRespuesta(respuestaValidaciones.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(respuestaValidaciones.getItemsMensajeRespuesta());
            return respuesta;
        }

        if (validacionesPagoPorCBU.getIngresoDatosManual()) {
            // validar cuit ingresado por el usuario
            LOGGER.info("Validando datos ingresados manualmente en Pago de Haberes");
            respuestaValidaciones = validarIgresoDatosManual(validacionesPagoPorCBU);
        } else {
            LOGGER.info("Validando CBU ingresado por el cliente en Pago de Haberes");
            respuestaValidaciones = pagoHaberesBO.validarCBU(sesionCliente.getCliente(), validacionesPagoPorCBU);
        }

        if (EstadoRespuesta.ERROR.equals(respuestaValidaciones.getEstadoRespuesta())) {
            LOGGER.debug("Error en alguna validacion en Pago de Haberes");
            respuesta.setEstadoRespuesta(respuestaValidaciones.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(respuestaValidaciones.getItemsMensajeRespuesta());
            return respuesta;
        }

        LOGGER.info("CBU Validado en Pago de Haberes");
        respuesta.setEstadoRespuesta(respuestaValidaciones.getEstadoRespuesta());
        respuesta.setRespuesta(respuestaValidaciones.getRespuesta());

        return respuesta;
    }

    /**
     * Resetear desafio en curso.
     */
    private void resetearDesafioEnCurso() {
        LOGGER.info(RESETEO_RSA);
        sesionParametros.setExisteDesafioEnCurso(false);
        sesionParametros.setDesafioEnCurso(null);
    }

    /**
     * Validar igreso datos manual.
     *
     * @param validacionesPagoPorCBU
     *            the validaciones pago por CBU
     * @return the respuesta
     */
    private Respuesta<DatosDestinatarioView> validarIgresoDatosManual(
            ValidacionesPagoPorCBUView validacionesPagoPorCBU) {
        Respuesta<DatosDestinatarioView> respuestaValidarCBU = new Respuesta<DatosDestinatarioView>();
        Boolean cuitCuilValido = ISBANStringUtils
                .validarCuilConPrefijoPersonaFisica(validacionesPagoPorCBU.getCuitCuil());
        try {
            if (cuitCuilValido) {
                respuestaValidarCBU.setEstadoRespuesta(EstadoRespuesta.OK);
            } else {
                respuestaValidarCBU = respuestaFactory.crearRespuestaError(null, TipoError.CUIT_CUIL_INVALIDO,
                        CodigoMensajeConstantes.PAGO_MULTIPLE_CUIT_CUIL_INVALIDO);
            }
        } catch (StringIndexOutOfBoundsException e) {
            respuestaValidarCBU = respuestaFactory.crearRespuestaError(null, TipoError.CUIT_CUIL_INVALIDO,
                    CodigoMensajeConstantes.PAGO_MULTIPLE_CUIT_CUIL_INVALIDO);
        }
        return respuestaValidarCBU;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#obtenerCuentas()
     */
    @Override
    public Respuesta<ComprobantePagoHaberesCBUEntity> pagoHaberesCBU(
            ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
        Respuesta<ComprobantePagoHaberesCBUEntity> respuesta = new Respuesta<ComprobantePagoHaberesCBUEntity>();

        respuesta = validarRSAPagoHaberesCBU(comprobantePagoHaberesCBU);
        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                respuesta.getItemsMensajeRespuesta().get(0)
                        .setTipoError(TipoError.ERROR_PAGAR_CBU_NO_PERMITE_REINTENTAR.getDescripcion());
            }
            return respuesta;
        }

        if (comprobantePagoHaberesCBU.getDatosDestinatarioView().getPrimeraVez()) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        Cuenta cuentaOrigen;
        AbstractCuenta tempAbsCuenta = null;
        tempAbsCuenta = cuentaManager
                .obtenerCuentaById(comprobantePagoHaberesCBU.getDatosDestinatarioView().getCuentaOrigen());
        if (tempAbsCuenta instanceof Cuenta) {
            cuentaOrigen = (Cuenta) tempAbsCuenta;
        } else {
            // Error generico.
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
        }

        Respuesta<ComprobantePagoHaberesCBUEntity> respuestaComprobantePagoHaberesCBU = pagoHaberesBO.pagoHaberesCBU(
                sesionCliente.getCliente(), comprobantePagoHaberesCBU.getDatosDestinatarioView(), cuentaOrigen);

        if (EstadoRespuesta.ERROR.equals(respuestaComprobantePagoHaberesCBU.getEstadoRespuesta())) {
            LOGGER.debug("Error al realizar el Pago de Haberes por CBU");
            respuesta.setEstadoRespuesta(respuestaComprobantePagoHaberesCBU.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(respuestaComprobantePagoHaberesCBU.getItemsMensajeRespuesta());
            grabarEstadisticaPagoCbu(comprobantePagoHaberesCBU.getDatosDestinatarioView(),
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuesta;
        }

        LOGGER.info("Se realizo el Pago de Haberes por CBU");
        respuesta.setEstadoRespuesta(respuestaComprobantePagoHaberesCBU.getEstadoRespuesta());
        respuesta.setRespuesta(respuestaComprobantePagoHaberesCBU.getRespuesta());

        grabarEstadisticaPagoCbu(comprobantePagoHaberesCBU.getDatosDestinatarioView(),
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuesta;
    }

    /**
     * Validar RSA pago haberes CBU.
     *
     * @param comprobantePagoHaberesCBU
     *            the comprobante pago haberes CBU
     * @return the respuesta
     */
    private Respuesta<ComprobantePagoHaberesCBUEntity> validarRSAPagoHaberesCBU(
            ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        UltimosDiasCambioClaveTokenView ultimosDias = cargarUltimosDiasDesdeCambioDeClaveToken();
        comprobantePagoHaberesCBU.setCantDiasUltimoCambioClave(ultimosDias.getCantDiasUltimoCambioClave());
        comprobantePagoHaberesCBU.setCantDiasUltimoCambioToken(ultimosDias.getCantDiasUltimoCambioToken());

        comprobantePagoHaberesCBU.setScoringDestinatario(getDestinationScoring(comprobantePagoHaberesCBU.getDatosDestinatarioView().getCuilCuitDestinatario()));
        comprobantePagoHaberesCBU.setBiocatchResponseData(getBiocatchDataSinglePayment());

        comprobantePagoHaberesCBU.setCantDiasUltimoCambioMail(getCantDiasUltimoCambioMail().intValue());

        return desafioOperacionRSASimple
                .validarOperacionRSA(comprobantePagoHaberesCBU, valorDesafioPagoHaberes,
                        autentificacionCodEstDTO);
    }

    /**
     * Grabar estadistica pago cbu.
     *
     * @param datosDestinatarioView
     *            the datos destinatario view
     * @param codigoEstadistica
     *            the codigo estadistica
     */
    private void grabarEstadisticaPagoCbu(DatosDestinatarioView datosDestinatarioView, String codigoEstadistica) {
        String estadisticaConstasts = "";
        if ("S".equals(datosDestinatarioView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Sueldo");
            if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura()
                    .equals(datosDestinatarioView.getTipoCuentaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Cuenta Corriente en Pesos");
                estadisticaConstasts = "11603";
            } else if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura()
                    .equals(datosDestinatarioView.getTipoCuentaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Caja de Ahorro en Pesos");
                estadisticaConstasts = "11604";
            } else if (TipoCuenta.CUENTA_UNICA.getAbreviatura().equals(datosDestinatarioView.getTipoCuentaOrigen())
                    || "CUP".equals(datosDestinatarioView.getTipoCuentaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Cuenta Unica");
                estadisticaConstasts = "11605";
            }
        } else {
            LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Honorario");
            if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura()
                    .equals(datosDestinatarioView.getTipoCuentaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Cuenta Corriente en Pesos");
                estadisticaConstasts = "11606";
            } else if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura()
                    .equals(datosDestinatarioView.getTipoCuentaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Caja de Ahorro en Pesos");
                estadisticaConstasts = "11607";
            } else if (TipoCuenta.CUENTA_UNICA.getAbreviatura().equals(datosDestinatarioView.getTipoCuentaOrigen())
                    || "CUP".equals(datosDestinatarioView.getTipoCuentaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes por CBU - Cuenta Unica");
                estadisticaConstasts = "11608";
            }
        }

        estadisticaManager.add(estadisticaConstasts, codigoEstadistica);
        LOGGER.info(LOG_ESTADISTICA_RESULTADO, estadisticaConstasts, codigoEstadistica);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#generarComprobanteAdhesion()
     */
    @Override
    public Respuesta<ReporteView> generarComprobanteAdhesion(
            ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado) {
        Respuesta<Reporte> reporteRespuesta = pagoHaberesBO.generarComprobanteAdhesion(comprobanteAdhesionEmpleado);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }

        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#generarComprobantePagoPorCBU(ar.com.santanderrio.obp.
     * servicios.pagohaberes.entities.ComprobantePagoHaberesCBU)
     */
    @Override
    public Respuesta<ReporteView> generarComprobantePagoPorCBU(
            ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
        Respuesta<Reporte> reporteRespuesta = pagoHaberesBO.generarComprobantePagoPorCBU(comprobantePagoHaberesCBU);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }

        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#validacionesPagoSimpleMultiple()
     */
    @Override
    public Respuesta<ValidacionesRespuestaEntity> validacionesPagoSimpleMultiple(
            ValidarPagoSimpleMultipleView validarPagoSimpleMultipleView) {
        Respuesta<ValidacionesRespuestaEntity> respuesta = new Respuesta<ValidacionesRespuestaEntity>();
        Respuesta<Boolean> respuestaValidarImporte = new Respuesta<Boolean>();

        // limar desafios en cursos
        resetearDesafioEnCurso();

        respuestaValidarImporte = pagoHaberesBO
                .validarImporteCuentas(validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView());
        if (EstadoRespuesta.ERROR.equals(respuestaValidarImporte.getEstadoRespuesta())) {
            respuesta.setEstadoRespuesta(respuestaValidarImporte.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(respuestaValidarImporte.getItemsMensajeRespuesta());
            return respuesta;
        }

        Date date = ISBANStringUtils.formatearFecha(validarPagoSimpleMultipleView.getFecha(), FORMATTER);
        Date today = ISBANStringUtils.dateWithoutTime(FORMATTER);

        respuesta = pagoHaberesBO.validacionesPagoSimpleMultiple(date, today, validarPagoSimpleMultipleView);
        if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
            return respuesta;
        }

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#pagoHaberesSimpleMultiple()
     */
    @Override
    public Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> realizarPagoHaberes(
            ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple) {

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        LOGGER.info("Validando pago de haberes 0: validarRSAPagoHaberesSimpleMultiple");

       validarPosibleFraudePagoHaberes(comprobantePagoHaberesPagoSimpleMultiple
                .getPagoHaberesPagoSimpleMultipleView()
                .getPagoHaberesEmpleadosView());

        respuesta = validarRSAPagoHaberesSimpleMultiple(comprobantePagoHaberesPagoSimpleMultiple);
        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                respuesta.getItemsMensajeRespuesta().get(0).setTipoError(
                        TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR.getDescripcion());
            }
            return respuesta;
        }

        // limar desafios en cursos
        resetearDesafioEnCurso();

        inicializarContadorReintentos(comprobantePagoHaberesPagoSimpleMultiple);

        Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> respuestaComprobanteEmpleado = new Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
        ComprobantePagoHaberesPagoSimpleMultipleEntity comprobante = new ComprobantePagoHaberesPagoSimpleMultipleEntity();
        List<DatosEmpleadoPagoHaberesSimpleMultipleEntity> datosEmpleadosList = new ArrayList<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
        boolean estadoOK = false;
        boolean estadoERROR = false;

        String modoEjecucion = comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView()
                .getModoEjecucion() ? "IA" : "A";
        String fecha = comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getFecha();

        List<PagoInformadoView> listaNuevaParaPagar = new ArrayList<PagoInformadoView>();
        cargarNuevaLista(listaNuevaParaPagar, comprobantePagoHaberesPagoSimpleMultiple
                .getPagoHaberesPagoSimpleMultipleView().getPagoHaberesEmpleadosView());

        for (PagoInformadoView pagoInformado : listaNuevaParaPagar) {
            respuestaComprobanteEmpleado = pagoHaberesBO.realizarPagoSimple(sesionCliente.getCliente(), pagoInformado,
                    modoEjecucion, fecha);
            respuestaComprobanteEmpleado.getRespuesta().setId(pagoInformado.getId());
            if (EstadoRespuesta.OK.equals(respuestaComprobanteEmpleado.getEstadoRespuesta())) {
                LOGGER.info("Pago haberes - Simple/Muliple - Se pudo realizar el pago al empleado "
                        + respuestaComprobanteEmpleado.getRespuesta().getDescripcionEmpleado());
                estadoOK = true;
                grabarEstadisticaPagoSimpleMultiple(pagoInformado, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                if (comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getIsPagoSimple()) {
                    if ("A".equals(modoEjecucion)) {
                        Mensaje mensaje = mensajeBo.obtenerMensajePorCodigo("1310");
                        respuestaComprobanteEmpleado.getRespuesta()
                                .setMensaje(MessageFormat.format(mensaje.getMensaje(),
                                        respuestaComprobanteEmpleado.getRespuesta().getImporte(), fecha,
                                        respuestaComprobanteEmpleado.getRespuesta().getDescripcionEmpleado()));
                    } else {
                        Mensaje mensaje = mensajeBo
                                .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_CBU_OK);
                        respuestaComprobanteEmpleado.getRespuesta()
                                .setMensaje(MessageFormat.format(mensaje.getMensaje(),
                                        respuestaComprobanteEmpleado.getRespuesta().getImporte(),
                                        respuestaComprobanteEmpleado.getRespuesta().getDescripcionEmpleado()));
                    }
                }
            } else {
                LOGGER.info("Pago haberes - Simple/Muliple - No se pudo realizar el pago al empleado "
                        + respuestaComprobanteEmpleado.getRespuesta().getDescripcionEmpleado());
                estadoERROR = true;
                grabarEstadisticaPagoSimpleMultiple(pagoInformado, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
            datosEmpleadosList.add(respuestaComprobanteEmpleado.getRespuesta());
        }

        if (estadoOK && estadoERROR) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            comprobante.setTodosPagosOk(false);
            comprobante.setModoEjecucion(
                    comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getModoEjecucion());
            comprobante.setPagos(datosEmpleadosList);
            comprobante.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
            try {
                comprobante.setLegalesSEO(legalDao.obtenerLegal("1005"));
            } catch (DAOException e) {
                LOGGER.error("ERROR al recuperar Legales SEO");
                comprobante.setLegalesSEO("");
            }
            respuesta.setRespuesta(comprobante);
            Boolean permiteReintentar = false;
            if (sesionParametros.getContador().permiteReintentar()) {
                permiteReintentar = true;
            }
            for (DatosEmpleadoPagoHaberesSimpleMultipleEntity datosEmpleadoPagoHaberesSimpleMultiple : datosEmpleadosList) {
                if (!datosEmpleadoPagoHaberesSimpleMultiple.isEsPagoOk()) {
                    datosEmpleadoPagoHaberesSimpleMultiple.setReintentar(permiteReintentar);
                }
            }
        } else if (!estadoOK && estadoERROR) {
        	
            if (datosEmpleadosList.size() == 1 && comprobantePagoHaberesPagoSimpleMultiple
                    .getPagoHaberesPagoSimpleMultipleView().getIsPagoSimple()) {
                if (sesionParametros.getContador().permiteReintentar()) {
                	
                	String mensajeErrorCtaCerrada = datosEmpleadosList.get(0).getMensaje();
                	LOGGER.info("Mensaje Error CUENTA CERRADA: " + mensajeErrorCtaCerrada);
                	LOGGER.info("COD RET CUENTA CERRADA: " + datosEmpleadosList.get(0).getConcepto());
                	if (datosEmpleadosList.get(0).getConcepto().equals(COD_RET_CUENTA_CERRADA)) {
                		// TIPO ERROR CUENTA CERRADA
                		respuesta = respuestaFactory.crearRespuestaError(null,
                				TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR,
                				CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_CUENTA_DEST_CERRADA); 
                        
                	} else {
                		// TIPO ERROR QUE PERMITE REINTENTAR
                		respuesta = respuestaFactory.crearRespuestaError(null,
                				TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_PERMITE_REINTENTAR,
                				CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGO_SIMPLE);                		
                	}
                	
                } else {
                    sesionParametros.setContador(null);
                    // TIPO ERROR QUE NO PERMITE REINTENTAR
                    respuesta = respuestaFactory.crearRespuestaError(null,
                            TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR,
                            CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGO_SIMPLE);
                }
            } else {
                if (sesionParametros.getContador().permiteReintentar()) {
                    // TIPO ERROR QUE PERMITE REINTENTAR
                    respuesta = respuestaFactory.crearRespuestaError(null,
                            TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_PERMITE_REINTENTAR,
                            CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGO_MULTIPLE);
                } else {
                    sesionParametros.setContador(null);
                    // TIPO ERROR QUE NO PERMITE REINTENTAR
                    respuesta = respuestaFactory.crearRespuestaError(null,
                            TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR,
                            CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGO_MULTIPLE);
                }
            }
        } else {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            comprobante.setTodosPagosOk(true);
            comprobante.setPagos(datosEmpleadosList);
            comprobante.setModoEjecucion(
                    comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getModoEjecucion());
            comprobante.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
            try {
                comprobante.setLegalesSEO(legalDao.obtenerLegal("1005"));
            } catch (DAOException e) {
                LOGGER.error("ERROR al recuperar Legales SEO");
                comprobante.setLegalesSEO("");
            }
            respuesta.setRespuesta(comprobante);
        }

        return respuesta;
    }

    private void validarPosibleFraudePagoHaberes(List<PagoInformadoView> pagoInformadoViewList) {

        boolean posibleFraude = false;

        for (PagoInformadoView pagoInformadoView : pagoInformadoViewList) {

            if (!sesionParametros.getEmpleadoPagoHaberesList().isEmpty()) {

                EmpleadoPagoHaberes empleadoPagoHaberes = sesionParametros.getEmpleadoPagoHaberesList()
                        .get(Integer.parseInt(pagoInformadoView.getId()) - 1);

                boolean coincideNroCuenta = pagoInformadoView.getCuentaDestino().equals(empleadoPagoHaberes.getNumeroCuenta());

                if (!coincideNroCuenta) {

                    LOGGER.info("PIF - Se detecta posible fraude por manipulación de datos.");
                    LOGGER.info("PagoInformadoView(Datos Destino Fraudulento) - Id Empleado: {}, Numero Cuenta: {}", pagoInformadoView.getId() , pagoInformadoView.getCuentaDestino());
                    LOGGER.info("EmpleadoPagoHaberes(Datos Destino Original) - Id Empleado: {}, Numero Cuenta: {}", empleadoPagoHaberes.getId() , empleadoPagoHaberes.getNumeroCuenta());

                    posibleFraude = true;
                    break;
                }
            }

        }

        if(posibleFraude) {

            for (PagoInformadoView pagoInformadoView : pagoInformadoViewList) {

                pagoInformadoView.setPif(true);
            }
        }
    }

    private void setRSAChallenge(ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple) {
        LOGGER.info("Seteo challenge False");
        comprobantePagoHaberesPagoSimpleMultiple
                .getPagoHaberesPagoSimpleMultipleView().getPagoHaberesEmpleadosView().get(0).setChallenge(Boolean.FALSE);
    }


    /**
     * Cargar nueva lista.
     *
     * @param listaNuevaParaPagar
     *            the lista nueva para pagar
     * @param pagoHaberesEmpleadosView
     *            Metodo que busca los empleados a pagar en la lista que fue
     *            guardada en sesion al ingresar al modulo Pago de Haberes. De
     *            esta manera nos aseguramos que vamos a abonar al empleado que
     *            fue agregado y no que se modifique durante la operacion. Las
     *            unicas variables que se modifican desde el Front son el
     *            importe y el concepto. Como referencia para la busqueda
     *            tomamos el nombre del empleado y la cuenta origen, cosa que no
     *            cambian nunca.
     */
    private void cargarNuevaLista(List<PagoInformadoView> listaNuevaParaPagar,
            List<PagoInformadoView> pagoHaberesEmpleadosView) {
        List<PagoInformadoView> pagosInformadoListEnSesion = sesionParametros.getPagoHaberesInformadosView();
        for (PagoInformadoView pagoInformadoView : pagoHaberesEmpleadosView) {
            for (PagoInformadoView pagoInformadoEnSesion : pagosInformadoListEnSesion) {
                if (pagoInformadoView.getId().equals(pagoInformadoEnSesion.getId())) {
                    // Modifico solo los 2 campos que pueden modificarse en la
                    // vista.
                    pagoInformadoEnSesion.setImporte(pagoInformadoView.getImporte());
                    pagoInformadoEnSesion.setId(pagoInformadoView.getId());
                    pagoInformadoEnSesion.setConcepto(pagoInformadoView.getConcepto());
                    pagoInformadoEnSesion.setChallenge(pagoInformadoView.getChallenge());
                    listaNuevaParaPagar.add(pagoInformadoEnSesion);
                    break;
                }
            }
        }

    }

    /**
     * Inicializar contador reintentos.
     *
     * @param comprobantePagoHaberesPagoSimpleMultiple
     *            the comprobante pago haberes pago simple multiple
     */
    private void inicializarContadorReintentos(
            ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple) {
        if (comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getPrimeraVez()) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }
    }

    /**
     * Validar RSA pago haberes simple multiple.
     *
     * @param comprobantePagoHaberesPagoSimpleMultiple
     *            the comprobante pago haberes pago simple multiple
     * @return the respuesta
     */
    private Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> validarRSAPagoHaberesSimpleMultiple(
            ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple) {

        Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> respuesta = new Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity>();
        RsaGenericRequestData challengeRsaGenericRequestData = null;
        RsaGenericResponseData challengeRsaGenericResponseData = null;

        if (null == sesionParametros.getDesafioEnCurso()) {
            LOGGER.info("sesionParametros.getDesafioEnCurso = null .... Consultando RSA y evaluando metodo de autentificacion");
            boolean hasChallenge = false;
            AutentificacionDTO dto = new AutentificacionDTO();
            dto.setOperacion(valorDesafioPagoHaberes);
            TipoDesafioEnum desafioAplicable = autentificacionManager.obtenerDesafioHabilitadoOperacion(dto);
            UltimosDiasCambioClaveTokenView ultimosDias = cargarUltimosDiasDesdeCambioDeClaveToken();
            setRSAChallenge(comprobantePagoHaberesPagoSimpleMultiple);

            Long cantDiasUltimoCambioMail = getCantDiasUltimoCambioMail();

            BiocatchResponseDataDTO biocatchResponseDataDTO = getBiocatchData(comprobantePagoHaberesPagoSimpleMultiple);

            for (PagoInformadoView pagoInformadoView : comprobantePagoHaberesPagoSimpleMultiple
                    .getPagoHaberesPagoSimpleMultipleView().getPagoHaberesEmpleadosView()) {

                pagoInformadoView.setScoringDestinatario(getDestinationScoring(pagoInformadoView.getCuil()));
                pagoInformadoView.setBiocatchResponseData(biocatchResponseDataDTO);
                pagoInformadoView.setCantDiasUltimoCambioMail(cantDiasUltimoCambioMail.intValue());
                pagoInformadoView.setPagoProgramado(isPagoProgramado(comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView()));
                pagoInformadoView.setFechaPago(comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getFecha());

                if (pagoInformadoView.getChallenge()) {
                    LOGGER.info("pagoInformadoView.getChallenge() = true");
                    AutentificacionDTO autentificacionDTO = cargarAutentificacionDTOSimpleMultiple(pagoInformadoView, ultimosDias);
                    LOGGER.info("Analize con RSA el pago {}", pagoInformadoView);
                    Respuesta<ActionCode> respuestaRiesgo = rsaManager.analizar(autentificacionDTO.getRsaDTO(), desafioAplicable);
                    LOGGER.info("RSA analize el pago id {} con riesgo {}", pagoInformadoView.getId(),
                            respuesta.getRespuesta());
                    if (ActionCode.DENY.equals(respuestaRiesgo.getRespuesta())) {
                        sesionParametros.setExisteDesafioEnCurso(false);
                        pagoInformadoView.setDesafio(null);
                        LOGGER.info("Se detienen los analize a rsa ya que hay un deny, pago id {}",
                                pagoInformadoView.getId());
                        return respuestaFactory.crearRespuestaError(
                                ComprobantePagoHaberesPagoSimpleMultipleEntity.class, StringUtils.EMPTY,
                                TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR,
                                CodigoMensajeConstantes.DENY_RSA);
                    } else {
                        LOGGER.info("analize a rsa para el pago id {} informa {}", pagoInformadoView.getId(),
                                respuestaRiesgo.getRespuesta());
                    }
                } else {
                    LOGGER.debug("Analize con RSA previo resultado de challenge el pago");
                    //valido que el desafío que me viene del front sea != null
                    if(pagoInformadoView.getDesafio() != null &&
                            pagoInformadoView.getDesafio().getTokenOperacion() != null &&
                            pagoInformadoView.getDesafio().getTokenOperacion().getIngresoToken() != null
                    ){
                        LOGGER.info("Pago de haberes - desadio view pisa desafio padre",
                                pagoInformadoView.getDesafio());
                        comprobantePagoHaberesPagoSimpleMultiple
                                .setDesafio(pagoInformadoView.getDesafio());
                    }else if (comprobantePagoHaberesPagoSimpleMultiple.getDesafio() != null &&
                            comprobantePagoHaberesPagoSimpleMultiple.getDesafio().getTokenOperacion() != null &&
                            comprobantePagoHaberesPagoSimpleMultiple.getDesafio().getTokenOperacion().getIngresoToken() != null
                    )
                    {
                        LOGGER.info("Pago de haberes - desadio padre pisa desafio view",
                                comprobantePagoHaberesPagoSimpleMultiple.getDesafio());
                        pagoInformadoView.setDesafio(comprobantePagoHaberesPagoSimpleMultiple.getDesafio());
                    }
                    else {
                        LOGGER.info("Pago de haberes -desafio vacío en padre",
                                comprobantePagoHaberesPagoSimpleMultiple.getDesafio());
                        LOGGER.info("Pago de haberes -desafio vacío en view",
                                pagoInformadoView.getDesafio());
                    }
                    Respuesta<PagoInformadoView> respuestaPagoInformadoView = analizarRsaObteniendoUnDesafioSimpleMultiple(
                            pagoInformadoView, ultimosDias);
                    if (!EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())
                            && respuestaPagoInformadoView != null
                            && EstadoRespuesta.WARNING.equals(respuestaPagoInformadoView.getEstadoRespuesta())) {
                        respuesta.setEstadoRespuesta(respuestaPagoInformadoView.getEstadoRespuesta());
                        respuesta.setItemMensajeRespuesta(respuestaPagoInformadoView.getItemsMensajeRespuesta());
                        comprobantePagoHaberesPagoSimpleMultiple
                                .setDesafio(respuestaPagoInformadoView.getRespuesta().getDesafio());
                        respuesta.setRespuesta(comprobantePagoHaberesPagoSimpleMultiple);
                        hasChallenge = true;
                        LOGGER.info("Analize con RSA identifico challenge para el pago id {}",
                                pagoInformadoView.getId());
                    }
                    if (respuestaPagoInformadoView != null
                            && EstadoRespuesta.ERROR.equals(respuestaPagoInformadoView.getEstadoRespuesta())) {
                        LOGGER.info("Analize con RSA presento un error para el pago id {} detiene el flujo",
                                pagoInformadoView.getId());
                        respuestaPagoInformadoView.getItemsMensajeRespuesta().get(0).setTipoError(
                                TipoError.ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR.getDescripcion());
                        return Respuesta.copy(ComprobantePagoHaberesPagoSimpleMultipleEntity.class,
                                respuestaPagoInformadoView);
                    }
                    LOGGER.info("Guardando los valores de request y response de RsaGeneric Data si el challenge es false");
                    if(null != sesionParametros.getRsaGenericRequestData()) {
                        LOGGER.info("sesionParametros.getRsaGenericRequestData is not null. TransactionId: {}", sesionParametros.getRsaGenericRequestData().getTransactionId());
                        challengeRsaGenericRequestData = sesionParametros.getRsaGenericRequestData();
                    }
                    if(null != sesionParametros.getRsaGenericResponseData()) {
                        LOGGER.info("sesionParametros.getRsaGenericResponseData is not null. TransactionId() {}", sesionParametros.getRsaGenericResponseData().getTransactionId());
                        challengeRsaGenericResponseData = sesionParametros.getRsaGenericResponseData();
                    }
                }

            }
            setSessionParametrosRequestAndResponseDataWhenChallengeIsFalse(challengeRsaGenericRequestData, challengeRsaGenericResponseData);
            if (!hasChallenge) {
                LOGGER.info(" hasChallenge is true - Fin de Validación");
                respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            }
            return respuesta;
        }
        LOGGER.info("sesionParametros.getDesafioEnCurso != null");
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        LOGGER.info("llamando a desafioOperacionRSAMultiple.validarOperacionRSA... ");
        return desafioOperacionRSAMultiple.validarOperacionRSA(comprobantePagoHaberesPagoSimpleMultiple,
                valorDesafioPagoHaberes, autentificacionCodEstDTO);
    }

    private boolean isPagoProgramado(PagoHaberesPagoSimpleMultipleView pagoHaberesPagoSimpleMultipleView){
        Date date = ISBANStringUtils.formatearFecha(pagoHaberesPagoSimpleMultipleView.getFecha(), FORMATTER);
        Date today = ISBANStringUtils.dateWithoutTime(FORMATTER);

        return date.after(today);
    }

    private BiocatchResponseDataDTO getBiocatchData(ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple){

        if (comprobantePagoHaberesPagoSimpleMultiple.isPagoSimple()){
           return getBiocatchDataSinglePayment();
        }else{
            return getBiocatchDataList(comprobantePagoHaberesPagoSimpleMultiple);
        }
    }

    private BiocatchResponseDataDTO getBiocatchDataSinglePayment() {
        return biocatchManager.getScore(sesionCliente.getCliente().getNup(),  sesionCliente.getIpCliente(),
                ActivityName.PAGO_HABERES, ActivityType.WIRE_PAYMENT);
    }

    private BiocatchResponseDataDTO getBiocatchDataList(ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple) {
        return biocatchManager.getScorePagoHaberes(sesionCliente.getCliente().getNup(),  sesionCliente.getIpCliente(),
                ActivityName.PAGO_HABERES, ActivityType.BULK_AUTHORIZE_PAYMENT,comprobantePagoHaberesPagoSimpleMultiple);
    }

    private float getDestinationScoring(String cuit) {

        try {

            return scoringApi.getScoring(cuit);

        } catch (ScoringApiException e) {

            LOGGER.error("Error al obtener scoring del cuit " + cuit, e);

            return DEFAULT_DESTINATION_SCORING;
        }
    }

    private Long getCantDiasUltimoCambioMail(){

        try {

            String nup = sesionCliente.getCliente().getNup();
            LOGGER.info("Conectando con customers-api para obtener cantDiasUltimoCambioMail. Nup: {}", nup);
            Customers customers = customersApi.getCustomerById(nup);
            LOGGER.info("Fechas obtenidas cantDiasUltimoCambioMail para el nup: {}. createdAt: [{}] , updateAt: [{}]", nup, customers.getCreatedAt(), customers.getUpdatedAt());
            Long cantDiasUltimoCambioMail = TransferenciaUtil.obtenerCantDiasUltimoCambioMail(customers.getCreatedAt(), customers.getUpdatedAt(), new Date());
            LOGGER.info("CantDiasUltimoCambioMail: {}. Nup: {}", cantDiasUltimoCambioMail, nup);
            return cantDiasUltimoCambioMail;

        }catch (Exception e){

            LOGGER.error(String.format("[Error conexión customers-api]: %s", e.getMessage()));
            return -1L;

        }
    }


    private void setSessionParametrosRequestAndResponseDataWhenChallengeIsFalse(RsaGenericRequestData challengeRsaGenericRequestData, RsaGenericResponseData challengeRsaGenericResponseData) {
        if (null != challengeRsaGenericRequestData && null != challengeRsaGenericResponseData)
        {
            sesionParametros.setRsaGenericRequestData(challengeRsaGenericRequestData);
            sesionParametros.setRsaGenericResponseData(challengeRsaGenericResponseData);
        }
    }

    /**
     * Analizar rsa obteniendo un desafio simple multiple.
     *
     * @param pagoInformadoView
     *            the pago informado view
     * @return the respuesta
     */
    private Respuesta<PagoInformadoView> analizarRsaObteniendoUnDesafioSimpleMultiple(
            PagoInformadoView pagoInformadoView, UltimosDiasCambioClaveTokenView ultimosDias) {

        Respuesta<PagoInformadoView> respuesta = new Respuesta<PagoInformadoView>();

        asignarEstadisticasDeAutenticacion();
        LOGGER.info("Llamando a analizarRSAyObtenerAutenticacionValida");
        Respuesta<AutentificacionDTO> respuestaAutentificacion = autentificacionManager
                .analizarRSAyObtenerAutenticacionValida(cargarAutentificacionDTOSimpleMultiple(pagoInformadoView, ultimosDias));
        EstadoRespuesta resp = respuestaAutentificacion.getEstadoRespuesta();
        switch (resp) {
        case OK:
            LOGGER.info("La operacion no requiere un desafio adicional");
            sesionParametros.setExisteDesafioEnCurso(false);
            respuesta.setRespuestaVacia(false);
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuesta(pagoInformadoView);
            return respuesta;
        case WARNING:
            LOGGER.info("Desafiando al usuario con {}", respuestaAutentificacion.getRespuesta().getTipoDesafio().getDescripcion());
            respuesta.setEstadoRespuesta(respuestaAutentificacion.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(respuestaAutentificacion.getItemsMensajeRespuesta());
            boolean hacerChallenge = !TipoError.SIN_METODO_DESAFIO.getDescripcion()
                    .equals(respuestaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError());
            sesionParametros.setExisteDesafioEnCurso(hacerChallenge);
            pagoInformadoView.setDesafio(respuestaAutentificacion.getRespuesta());
            respuesta.setRespuestaVacia(false);
            respuesta.setRespuesta(pagoInformadoView);
            return respuesta;
        case ERROR:
            LOGGER.info("Error al obtener el desafío ");
            respuesta.setEstadoRespuesta(respuestaAutentificacion.getEstadoRespuesta());
            respuesta.setItemMensajeRespuesta(respuestaAutentificacion.getItemsMensajeRespuesta());
            sesionParametros.setExisteDesafioEnCurso(false);
            respuesta.setRespuestaVacia(false);
            pagoInformadoView.setDesafio(null);
            respuesta.setRespuesta(pagoInformadoView);
            return respuesta;
        default:
            LOGGER.info("Error genérico al obtener el desafío ");
            // Si pasa por aca, retornara un error generico
            return null;
        }
    }

    /**
     * Asignar estadisticas de autenticacion.
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.OCHO_DIGITOS_SOLICITUD_PAGO_HABERES);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.OCHO_DIGITOS_VALIDACION_PAGO_HABERES);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.COORDENADAS_SOLICITUD_PAGO_HABERES);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.COORDENADAS_VALIDACION_PAGO_HABERES);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOFT_TOKEN_SOLICITUD_PAGO_HABERES);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionToken(EstadisticasConstants.SOFT_TOKEN_VALIDACION_PAGO_HABERES);
        return autentificacionCodEstDTO;
    }

    /**
     * Cargar autentificacion DTO simple multiple.
     *
     * @param pagoInformadoView
     *            the pago informado view
     * @return the autentificacion DTO
     */
    private AutentificacionDTO cargarAutentificacionDTOSimpleMultiple(PagoInformadoView pagoInformadoView, UltimosDiasCambioClaveTokenView ultimosDias) {

        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(valorDesafioPagoHaberes);

        if (pagoInformadoView.getDesafio() != null) {
            autentificacionDTO = pagoInformadoView.getDesafio();
        } else {
            pagoInformadoView.setNickName(
                    sesionCliente.getCliente().getNombre() + " " + sesionCliente.getCliente().getApellido1());
        }

        // TODO **** LIMPIAR CUANDO SE UNIFIQUE OPERACION COORDENADA, ver
        // @leonardo.medina
        PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
        pedidoCoordenada.setIp(sesionCliente.obtenerIpV4SinPuntos());
        if (autentificacionDTO.getCoordenadasOperacion() != null) {
            autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
        } else {
            CoordenadasOperacionDTO coordenadasOperacionDTO = new CoordenadasOperacionDTO();
            coordenadasOperacionDTO.setPedidoCoordenada(pedidoCoordenada);
            autentificacionDTO.setCoordenadasOperacion(coordenadasOperacionDTO);
        }
        // ************************
        pagoInformadoView.setIgnorarRSA(pagoInformadoView.getChallenge());
        pagoInformadoView.setCantDiasUltimoCambioClave(ultimosDias.getCantDiasUltimoCambioClave());
        pagoInformadoView.setCantDiasUltimoCambioToken(ultimosDias.getCantDiasUltimoCambioToken());
        autentificacionDTO.setRsaDTO(pagoInformadoView);
        autentificacionDTO.setValorNotificarRSA(!pagoInformadoView.getChallenge());
        return autentificacionDTO;
    }

    /**
     * Grabar estadistica pago simple multiple.
     *
     * @param pagoInformadoView
     *            the pago informado view
     * @param codigoEstadistica
     *            the codigo estadistica
     */
    private void grabarEstadisticaPagoSimpleMultiple(PagoInformadoView pagoInformadoView, String codigoEstadistica) {
        String estadisticaConstasts = "";
        if ("Sueldo".equals(pagoInformadoView.getTipoPago())) {
            LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Sueldo");
            if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura().equals(pagoInformadoView.getAbreviaturaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Corriente en Pesos");
                estadisticaConstasts = "10538";
            } else if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura().equals(pagoInformadoView.getAbreviaturaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Caja de Ahorro en Pesos");
                estadisticaConstasts = "10539";
            } else if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura()
                    .equals(pagoInformadoView.getAbreviaturaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Unica");
                estadisticaConstasts = "10540";
            }
        } else {
            LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Honorario");
            if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura().equals(pagoInformadoView.getAbreviaturaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Corriente en Pesos");
                estadisticaConstasts = "10542";
            } else if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura().equals(pagoInformadoView.getAbreviaturaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Caja de Ahorro en Pesos");
                estadisticaConstasts = "10543";
            } else if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura()
                    .equals(pagoInformadoView.getAbreviaturaOrigen())) {
                LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Unica");
                estadisticaConstasts = "10544";
            }
        }

        estadisticaManager.add(estadisticaConstasts, codigoEstadistica);
        LOGGER.info(LOG_ESTADISTICA_RESULTADO, estadisticaConstasts, codigoEstadistica);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#obtenerImportesCuentasDebitar(java.util.List)
     */
    @Override
    public Respuesta<List<ImporteCuentasView>> obtenerImportesCuentasDebitar(
            List<ImporteCuentasView> importeCuentasView) {
        return completarImportePorCuenta(importeCuentasView);
    }

    /**
     * Completar importe por cuenta.
     *
     * @param importeCuentasView
     *            the importe cuentas view
     * @return the respuesta
     */
    private Respuesta<List<ImporteCuentasView>> completarImportePorCuenta(List<ImporteCuentasView> importeCuentasView) {

        Respuesta<CuentasView> cuentas = cuentaManager.getCuentasSaldo();
        if (cuentas.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            // Error generico.
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
        }

        for (ImporteCuentasView cuentaDebitar : importeCuentasView) {
            for (CuentasAdhesionDebitoView cuentaUsuario : cuentas.getRespuesta().getCuentas()) {
                if (cuentaDebitar.getNroCuenta().equals(cuentaUsuario.getNumero())) {
                    cuentaDebitar.setImporte("$ "
                            + (cuentaUsuario.getIsSaldoPesosNegativo() == null
                                    || !cuentaUsuario.getIsSaldoPesosNegativo() ? "" : "-")
                            + cuentaUsuario.getSaldoPesos());
                    cuentaDebitar.setIsSaldoPesosNegativo(
                            cuentaUsuario.getIsSaldoPesosNegativo() != null && cuentaUsuario.getIsSaldoPesosNegativo());
                    cuentaDebitar
                            .setAlias(StringUtils.isEmpty(cuentaUsuario.getAlias()) ? null : cuentaUsuario.getAlias());
                }
            }
        }
        Respuesta<List<ImporteCuentasView>> respuesta = new Respuesta<List<ImporteCuentasView>>();
        respuesta.setRespuesta(importeCuentasView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.
     * PagoHaberesManager#generarComprobantePagoSimpleMultiple(ar.com.
     * santanderrio.obp.servicios.pagohaberes.entities.
     * ComprobantePagoHaberesCBUEntity)
     */
    @Override
    public Respuesta<ReporteView> generarComprobantePagoSimpleMultiple(ComprobantePagoHaberesCBUEntity comprobante) {
        Respuesta<Reporte> reporteRespuesta = pagoHaberesBO.generarComprobantePagoSimpleMultiple(comprobante);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }

        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuestaView;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.PagoHaberesManager#obtenerLimitesHorarios()
     */
    @Override
    public Respuesta<String> obtenerLimitesHorarios() {
        String mensaje = pagoHaberesLimite;
        return respuestaFactory.crearRespuestaOk(mensaje);
    }
    
	private UltimosDiasCambioClaveTokenView cargarUltimosDiasDesdeCambioDeClaveToken() {
		
		UltimosDiasCambioClaveTokenView ultimosDias = new UltimosDiasCambioClaveTokenView();
		
		try {
			Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						ultimosDias.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
						LOGGER.info("Valor de ultimos dias cambio clave: {}", antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						ultimosDias.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
						LOGGER.info("Valor de ultimos dias cambio token: {}", antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.error("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.error("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}
		
		return ultimosDias;
		
	}
    
}
