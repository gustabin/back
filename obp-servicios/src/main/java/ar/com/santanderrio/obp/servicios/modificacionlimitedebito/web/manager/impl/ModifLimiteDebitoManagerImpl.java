/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.ModificacionLimiteDebitoBO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.ConsultaDatosTarjetaDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.LimitesExtraccionDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimitesClasesCompras;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimitesTarjetaBanelco;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.service.ModificacionLimiteDebitoService;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.ModifLimiteDebitoManager;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.CuentaDebito;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.DatosModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ModifLimiteDebitoManagerImpl.
 */
@Component
public class ModifLimiteDebitoManagerImpl implements ModifLimiteDebitoManager {

    /** The LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifLimiteDebitoManagerImpl.class);

    /** The Constant CODIGO_MODIFICACION_ERROR_GENERICO. */
    private static final String CODIGO_MODIFICACION_ERROR_GENERICO = "ERROR generico modificación limite debito";

    /** The Constant ERROR_STRING. */
    private static final String ERROR_STRING = "ERROR";

    /** The Constant CODIGO_APLICACION_DEBITO. */
    private static final String CODIGO_APLICACION_DEBITO = "ABAE";

    /** The Constant MARCA_VISA. */
    public static final String MARCA_VISA = "VISA";

    /** The Constant FORMATO_FECHA_COMPROBANTE. */
    private static final String FORMATO_FECHA_COMPROBANTE = "dd/MM/yyyy HH:mm";

    /** The Constant USER_AGENT. */
    private static final String USER_AGENT = "User-Agent";

    /** The respuesta factory. */
    @Autowired
    RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    protected SesionCliente sesionCliente;

    /** The modificacion limite debito BO. */
    @Autowired
    private ModificacionLimiteDebitoBO modificacionLimiteDebitoBO;

    /** The modificacion limite debito service. */
    @Autowired
    private ModificacionLimiteDebitoService modificacionLimiteDebitoService;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<ComprobanteModificacionLimiteDebitoView> desafioOperacionRSA;

    /** The valor desafio cambio limite extraccion. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOLIMITEEXTRACCION:3}")
    private Integer valorDesafioCambioLimiteExtraccion;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.
     * ModifLimiteDebitoManager#obtenerDatosModifLimiteDebito()
     */
    @Override
    public Respuesta<DatosModificacionLimiteDebitoView> obtenerDatosModifLimiteDebito() {
        Respuesta<ConsultaDatosTarjetaDebitoDTO> respuestaDTO;
        Respuesta<LimitesExtraccionDebitoDTO> respuestaLimitesDebito = null;
        vaciarDesafio();

        try {
            Cliente cliente = sesionCliente.getCliente();
            List<Cuenta> cuentasBanelco = obtenerCuentaDebitoHabilitada(cliente);

            List<CuentaDebito> cuentasDebitoFormateadas = new ArrayList<CuentaDebito>();
            List<String> limitesExtraccionActual = new ArrayList<String>();
            List<String> limitesCompraActual = new ArrayList<String>();
            List<LimitesTarjetaBanelco> limitesExtraccion = new ArrayList<LimitesTarjetaBanelco>();
            List<LimitesClasesCompras> limitesComprasClases = new ArrayList<LimitesClasesCompras>();

            for (Cuenta cuenta : cuentasBanelco) {

                String nroSucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
                String nroTarjeta = StringUtils.rightPad(cuenta.getNroTarjetaCredito().substring(4), 20, ' ');
                respuestaDTO = modificacionLimiteDebitoService.getClaseTarjetaDebito(nroSucursal, nroTarjeta, cliente);

                if (!respuestaDTO.getEstadoRespuesta().name().equalsIgnoreCase(ERROR_STRING)
                        && respuestaDTO.getRespuesta() != null
                        && StringUtils.isNotBlank(respuestaDTO.getRespuesta().getClaseTarjetaDebito())) {
                    respuestaLimitesDebito = obtenerLimitesArchivo(respuestaDTO.getRespuesta().getClaseTarjetaDebito());
                } else {
                    return (Respuesta<DatosModificacionLimiteDebitoView>) respuestaError(respuestaDTO);
                }

                cuentasDebitoFormateadas.add(formatearCuentaBanelco(cuenta));
                LimitesExtraccionDebitoDTO limitesCuenta = respuestaLimitesDebito.getRespuesta();
                limitesExtraccionActual.add(limitesCuenta.getLimiteExtraccionActual());
                limitesCompraActual.add(limitesCuenta.getLimiteCompraActual());
                limitesComprasClases.add(new LimitesClasesCompras(limitesCuenta.getLimitesCompletosCompra()));
                limitesExtraccion.add(new LimitesTarjetaBanelco(limitesCuenta.getLimitesExtraccion().getValores()));
            }

            return crearRespuestaDatosModifLimiteDebito(limitesExtraccionActual, limitesCompraActual, limitesExtraccion, 
            		limitesComprasClases, cuentasDebitoFormateadas);
        } catch (Exception e) {
            LOGGER.error("Error al Obtener Datos Tarjeta Debito.", e);
            return getRespuestaFactory().crearRespuestaError(DatosModificacionLimiteDebitoView.class, ERROR_STRING,
                    ERROR_STRING);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.
     * ModifLimiteDebitoManager#modificarLimitesExtraccion(ar.com.santanderrio.
     * obp.servicios.modificacionlimitedebito.web.view.
     * ComprobanteModificacionLimiteDebitoView, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<ComprobanteModificacionLimiteDebitoView> modificarLimitesExtraccion(
            ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, MessageContext mc) {

        Respuesta<ComprobanteModificacionLimiteDebitoView> respuestaFinal = validarModificacionLimiteDebito(
                comprobanteModificacionLimiteDebitoView);

        if (respuestaFinal != null && !EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta())) {
                vaciarDesafio();
            }
            return respuestaFinal;
        }

        if (sesionParametros.getContador() == null) {
            ContadorIntentos contador = new ContadorIntentos();
            contador.setIdView(comprobanteModificacionLimiteDebitoView.getIdSesion(), 2,
                    CODIGO_MODIFICACION_ERROR_GENERICO);
            sesionParametros.setContador(contador);
        } else {
            sesionParametros.getContador().setIdView(comprobanteModificacionLimiteDebitoView.getIdSesion(), 2,
                    CODIGO_MODIFICACION_ERROR_GENERICO);
        }

        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuentaBanelco = obtenerCuentaSeleccionada(obtenerCuentaDebitoHabilitada(cliente),
                comprobanteModificacionLimiteDebitoView.getIdCuenta());
        if (cuentaBanelco == null) {
            vaciarDesafio();
            return getRespuestaFactory().crearRespuestaError(ComprobanteModificacionLimiteDebitoView.class,
                    ERROR_STRING, ERROR_STRING);
        }

        Respuesta<ResultadoTransaccion> respuestaModificacion;
        try {
            String nroTarjeta = StringUtils.rightPad(cuentaBanelco.getNroTarjetaCredito().substring(4), 19, ' ');
            String ip = NetworkUtil.getRemoteIp(mc.getHttpServletRequest());
            String userAgent = mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0);
            respuestaModificacion = modificacionLimiteDebitoService
                    .modificarLimitesExtraccion(comprobanteModificacionLimiteDebitoView, nroTarjeta, cliente, ip, userAgent);

            EstadoRespuesta resp = respuestaModificacion.getEstadoRespuesta();

            switch (resp) {
            case OK:
                LOGGER.info("La modificación del limite debito se realizo satisfactoriamente.");
                mapearEjecucionView(comprobanteModificacionLimiteDebitoView, respuestaModificacion.getRespuesta());
                respuestaFinal = crearRespuestaModifLimiteDebito(respuestaModificacion,
                        TarjetaBOUtils.formatearNumeroTarjeta(cuentaBanelco.getNroTarjetaCredito(), MARCA_VISA),
                        comprobanteModificacionLimiteDebitoView);
                respuestaFinal.setRespuesta(comprobanteModificacionLimiteDebitoView);
                vaciarDesafio();
                return respuestaFinal;
            case WARNING:
                LOGGER.info("Ha ocurrido un error parcial al modificar el limite debito.");
                respuestaFinal = respuestaFactory.crearRespuestaWarning(ComprobanteModificacionLimiteDebitoView.class,
                        comprobanteModificacionLimiteDebitoView, respuestaModificacion.getItemsMensajeRespuesta());
                vaciarDesafio();
                return sesionParametros.getContador()
                        .excedeReintentos(comprobanteModificacionLimiteDebitoView.getIdSesion(), respuestaFinal);
            case ERROR:
                LOGGER.error("Ha ocurrido un error inesperado");
                respuestaFinal = respuestaFactory.crearRespuestaError(ComprobanteModificacionLimiteDebitoView.class,
                        ERROR_STRING, ERROR_STRING);
                comprobanteModificacionLimiteDebitoView
                        .setMensaje("Ha ocurrido un error inesperado al modificar el limite debito");
                respuestaFinal.setRespuesta(comprobanteModificacionLimiteDebitoView);
                vaciarDesafio();
                return respuestaFinal;
            default:
                LOGGER.error("Ha ocurrido un error inesperado");
                vaciarDesafio();
                throw new RobotException("Error inesperado");
            }
        } catch (Exception e) {
            LOGGER.error("Error al Modificar Limite Debito.", e);
            vaciarDesafio();
            return getRespuestaFactory().crearRespuestaError(ComprobanteModificacionLimiteDebitoView.class,
                    ERROR_STRING, ERROR_STRING);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.
     * ModifLimiteDebitoManager#comprobanteModifLimitesExtraccion(ar.com.
     * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
     * ComprobanteDescargaCambioLimiteView)
     */
    @Override
    public Respuesta<Void> vaciarDesafio() {
        sesionParametros.resetearDesafioEnCurso();
        return new Respuesta<Void>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.
     * ModifLimiteDebitoManager#comprobanteModifLimitesExtraccion(ar.com.
     * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
     * ComprobanteDescargaCambioLimiteView)
     */
    @Override
    public Respuesta<ReporteView> comprobanteModifLimitesExtraccion(
            ComprobanteDescargaCambioLimiteView comprobanteView) {
        Respuesta<Reporte> reporteRespuesta = modificacionLimiteDebitoBO
                .comprobanteModifLimitesExtraccion(comprobanteView);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }

        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
    }

    /**
     * Respuesta error.
     *
     * @param respuestaDTO
     *            the respuesta DTO
     * @return the respuesta
     */
    @SuppressWarnings("rawtypes")
    private Respuesta<?> respuestaError(Respuesta<ConsultaDatosTarjetaDebitoDTO> respuestaDTO) {
        Respuesta<?> respuesta = new Respuesta();
        respuesta.setEstadoRespuesta(respuestaDTO.getEstadoRespuesta());
        respuesta.setRespuestaVacia(true);
        respuesta.setItemMensajeRespuesta(respuestaDTO.getItemsMensajeRespuesta());
        return respuesta;
    }

    /**
     * Validar modificacion limite debito.
     *
     * @param comprobanteModificacionLimiteDebitoView
     *            the comprobante modificacion limite debito view
     * @return the respuesta
     */
    private Respuesta<ComprobanteModificacionLimiteDebitoView> validarModificacionLimiteDebito(
            ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView) {

        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        Respuesta<ComprobanteModificacionLimiteDebitoView> respEjecucionMetodoAutentificacion = desafioOperacionRSA
                .validarOperacionRSA(comprobanteModificacionLimiteDebitoView, valorDesafioCambioLimiteExtraccion,
                        autentificacionCodEstDTO);

        return respEjecucionMetodoAutentificacion;

    }

    /**
     * Asignar estadisticas de autenticacion.
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(
                EstadisticasConstants.SOFT_TOKEN_SOLICITUD_MODIFICACION_LIMITE_EXTRACCION);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(
                EstadisticasConstants.SOFT_TOKEN_VALIDACION_MODIFICACION_LIMITE_EXTRACCION);
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(
                EstadisticasConstants.COORDENADAS_SOLICITUD_MODIFICACION_LIMITE_EXTRACCION);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(
                EstadisticasConstants.COORDENADAS_VALIDACION_MODIFICACION_LIMITE_EXTRACCION);
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco(
                EstadisticasConstants.OCHO_DIGITOS_SOLICITUD_MODIFICACION_LIMITE_EXTRACCION);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(
                EstadisticasConstants.OCHO_DIGITOS_VALIDACION_MODIFICACION_LIMITE_EXTRACCION);
        return autentificacionCodEstDTO;
    }

    /**
     * Mapear ejecucion view.
     *
     * @param comprobanteModificacionLimiteDebitoView
     *            the comprobante modificacion limite debito view
     * @param resultadoTransaccion
     *            the resultado transaccion
     */
    private void mapearEjecucionView(ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView,
            ResultadoTransaccion resultadoTransaccion) {
        comprobanteModificacionLimiteDebitoView.setComprobante(resultadoTransaccion.getNumeroComprobante());
        comprobanteModificacionLimiteDebitoView.setFechaHora(
                ISBANStringUtils.formatearFecha(resultadoTransaccion.getFechaTransaccion(), FORMATO_FECHA_COMPROBANTE));
        if (comprobanteModificacionLimiteDebitoView.getDesafio() != null) {
            // Eliminamos informacion que no se usa en la vista.
            comprobanteModificacionLimiteDebitoView.setDesafio(null);
        }
    }

    /**
     * Crear respuesta modif limite debito.
     *
     * @param respuesta
     *            the respuesta
     * @param numeroTarjetaFormateado
     *            the numero tarjeta formateado
     * @param respuestaView
     *            the respuesta view
     * @return the respuesta
     */
    private Respuesta<ComprobanteModificacionLimiteDebitoView> crearRespuestaModifLimiteDebito(
            Respuesta<ResultadoTransaccion> respuesta, String numeroTarjetaFormateado,
            ComprobanteModificacionLimiteDebitoView respuestaView) {

        respuestaView.setComprobante(respuesta.getRespuesta().getNumeroComprobante());
        Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo("1685");
        if (mensaje != null) {
            respuestaView.setMensaje(MessageFormat.format(mensaje.getMensaje(), numeroTarjetaFormateado));
        }
        return respuestaFactory.crearRespuestaOk(ComprobanteModificacionLimiteDebitoView.class, respuestaView);
    }

    /**
     * Obtener cuenta debito habilitada.
     *
     * @param cliente
     *            the cliente
     * @return the list
     */
    private List<Cuenta> obtenerCuentaDebitoHabilitada(Cliente cliente) {
        List<Cuenta> cuentasBanelco = new ArrayList<Cuenta>();
        List<Cuenta> cuentasCliente = cliente.getCuentas();
        for (Cuenta cuenta : cuentasCliente) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
                    && CODIGO_APLICACION_DEBITO.equalsIgnoreCase(cuenta.getCodigoAplicacion())) {
                cuentasBanelco.add(cuenta);
            }
        }
        return cuentasBanelco;
    }

    /**
     * Obtener cuenta seleccionada.
     *
     * @param cuentasBanelco
     *            the cuentas banelco
     * @param idCuenta
     *            the id cuenta
     * @return the cuenta
     */
    private Cuenta obtenerCuentaSeleccionada(List<Cuenta> cuentasBanelco, String idCuenta) {
        for (Cuenta cuenta : cuentasBanelco) {
            if (cuenta.getId().equals(Long.valueOf(idCuenta))) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Formatear cuenta banelco.
     *
     * @param cuentaBanelco
     *            the cuenta banelco
     * @return the cuenta debito
     */
    private CuentaDebito formatearCuentaBanelco(Cuenta cuentaBanelco) {
        TipoCuenta tipoCuentaEnum = cuentaBanelco.getTipoCuentaEnum();
        Boolean isBanelco = TipoCuenta.BANELCO.equals(tipoCuentaEnum);
        return new CuentaDebito(cuentaBanelco.getId().toString(),
                TarjetaBOUtils.formatearNumeroTarjeta(cuentaBanelco.getNroTarjetaCredito(),
                        isBanelco ? MARCA_VISA : TarjetaUtils.getMarca(cuentaBanelco)),
                cuentaBanelco.getAlias());
    }

    /**
     * Crear respuesta datos modif limite debito.
     *
     * @param limitesExtraccionActual
     *            the limites extraccion actual
     * @param limitesCompraActual
     *            the limites compra actual
     * @param limitesExtraccion
     *            the limites extraccion
     * @param cuentasDebitoFormateadas
     *            the cuentas debito formateadas
     * @return the respuesta
     */
    private Respuesta<DatosModificacionLimiteDebitoView> crearRespuestaDatosModifLimiteDebito(
            List<String> limitesExtraccionActual, List<String> limitesCompraActual,
            List<LimitesTarjetaBanelco> limitesExtraccion, List<LimitesClasesCompras> limitesComprasClases, 
            List<CuentaDebito> cuentasDebitoFormateadas) {
        DatosModificacionLimiteDebitoView respuestaView = new DatosModificacionLimiteDebitoView();

        respuestaView.setLimitesCompletosCompra(limitesComprasClases);
        respuestaView.setLimiteCompraActual(limitesCompraActual);
        respuestaView.setLimiteExtraccionActual(limitesExtraccionActual);
        respuestaView.setLimitesExtraccion(limitesExtraccion);
        respuestaView.setCuentasDebito(cuentasDebitoFormateadas);

        return getRespuestaFactory().crearRespuestaOk(DatosModificacionLimiteDebitoView.class, respuestaView);
    }

    /**
     * Obtener limites archivo.
     *
     * @param clase
     *            the clase
     * @return the respuesta
     */
    private Respuesta<LimitesExtraccionDebitoDTO> obtenerLimitesArchivo(String clase) {
        Respuesta<LimitesExtraccionDebitoDTO> respuestaLimites = new Respuesta<LimitesExtraccionDebitoDTO>();
        try {
            respuestaLimites = modificacionLimiteDebitoBO.getLimitesArchivo(clase);
        } catch (Exception e) {
            LOGGER.error("Error al Obtener Limites Desde Archivo.", e);
        }
        return respuestaLimites;
    }

    /**
     * Gets the respuesta factory.
     *
     * @return the respuesta factory
     */
    public RespuestaFactory getRespuestaFactory() {
        return respuestaFactory;
    }

    /**
     * Sets the respuesta factory.
     *
     * @param respuestaFactory
     *            the new respuesta factory
     */
    public void setRespuestaFactory(RespuestaFactory respuestaFactory) {
        this.respuestaFactory = respuestaFactory;
    }

}
