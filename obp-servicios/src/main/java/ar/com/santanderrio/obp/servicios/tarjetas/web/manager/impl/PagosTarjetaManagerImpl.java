/*
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.*;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO;
import ar.com.santanderrio.obp.servicios.seguros.dto.*;
import ar.com.santanderrio.obp.servicios.seguros.entities.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
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
import ar.com.santanderrio.obp.servicios.cotizacion.bo.CotizacionBO;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.StopDebitIn;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoMonedaPagoTCEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoPagoTCEnum;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.StopDebitTarjetasBO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.StopDebitTarjetasDAO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.BajaAdhesionTarjConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaPago;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaPagoSeleccionView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.PagosTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.DetalleTarjetaPagoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CompraProtegidaView;

/**
 * Clase PagosTarjetaManagerImpl.
 *
 * El servicio de cotizacion maneja los pesos con el codigo ARP, no con ARS como
 * se maneja en el resto de la aplicacion. Si, feo feo feo.
 */
@Component
public class PagosTarjetaManagerImpl implements PagosTarjetaManager {

    /**
     * Constante LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagosTarjetaManagerImpl.class);

    /**
     * Constante CONTRATO_ACEPTADO.
     */
    private static final String CONTRATO_ACEPTADO = "1";

    /**
     * Constante PESO_COTI.
     */
    public static final String PESO_COTI = "ARP";

    /**
     * Constante NRO_TC.
     */
    public static final String NRO_TC = "00000000000000000000";

    /**
     * Constante MENSAJE_PAGOS_PROGRAMADOS.
     */
    public static final String MENSAJE_PAGOS_PROGRAMADOS = "1177";

    /**
     * Constante MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO.
     */
    public static final String MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO = "310021";

    /** The Constant MENSAJE_STOP_DEBIT_OK. */
    public static final String MENSAJE_STOP_DEBIT_OK = "1127";

    /**
     * Constante MENSAJE_ERROR_STOP_DEBIT.
     */
    public static final String MENSAJE_ERROR_STOP_DEBIT = "1171";

    /**
     * Constante MENSAJE_ERROR_STOP_DEBIT_YA_SE_HIZO_OTRO_EN_EL_DIA.
     */
    public static final String MENSAJE_ERROR_STOP_DEBIT_YA_SE_HIZO_OTRO_EN_EL_DIA = "1172";

    /** solicitud de stop debit ya pedida. */
    public static final String MENSAJE_STOP_DEBIT_YA_SOLICITADO = "1110";

    /** Stop debit pedido fuera de horario. */
    public static final String MENSAJE_STOP_DEBIT_FUERA_DE_HORARIO = "1109";

    /** error generico stop debit. */
    public static final String MENSAJE_ERROR_GENERICO_STOP_DEBIT = "1128";

    /**
     * Constante TIME_FORMAT_PATTERN.
     */
    public static final String TIME_FORMAT_PATTERN = "HH:mm";

    /**
     * Constante MONEDA_PESO.
     */
    private static final String MONEDA_PESO = "0";

    /**
     * Constante MONEDA_DOLAR.
     */
    private static final String MONEDA_DOLAR = "1";

    /** The Constant TEXTO_MONEDA_AMBAS. */
    private static final String TEXTO_MONEDA_AMBAS = "Pesos y Dólares";

    /** The Constant TEXTO_MONEDA_PESO. */
    private static final String TEXTO_MONEDA_PESO = "Pesos";

    /** The Constant TEXTO_MONEDA_DOLAR. */
    private static final String TEXTO_MONEDA_DOLAR = "Dólares";

    /**
     * Constante DEBITO_CC_MINIMO.
     */
    private static final String DEBITO_CC_MINIMO = "02";

    /** The Constant PAGO_MINIMO. */
    private static final String PAGO_MINIMO = "Pago Mínimo";

    /** The Constant PAGO_TOTAL. */
    private static final String PAGO_TOTAL = "Pago Total";

    /**
     * Constante DEBITO_CA_MINIMO.
     */
    private static final String DEBITO_CA_MINIMO = "04";

    /**
     * Constante DEBITO_CC_TOTAL.
     */
    private static final String DEBITO_CC_TOTAL = "03";

    /**
     * Constante DEBITO_CA_TOTAL.
     */
    private static final String DEBITO_CA_TOTAL = "05";

    /** Mensaje para asegurarse de relizar stop debit. */
    private static final String MENSAJE_RELIZAR_STOP_DEBIT = "1284";

    /** Mensaje de baja de Adhesion a tarjeta de credito. */
    private static final String MENSAJE_RELIZAR_BAJA_ADHES_TARJ = "1286";

    /** Constante CODIGO_MENSAJE_DEBITO_MINIMO. */
    private static final String CODIGO_DEBITO_MINIMO_SW_ON = "1208";

    /** The Constant CODIGO_DEBITO_MINIMO_SW_OFF. */
    private static final String CODIGO_DEBITO_MINIMO_SW_OFF = "1374";

    /** The Constant CODIGO_DEBITO_TOTAL_SW_OFF. */
    private static final String CODIGO_DEBITO_TOTAL_SW_OFF = "1372";

    /**
     * Constante CODIGO_MENSAJE_DEBITO_TOTAL.
     */
    private static final String CODIGO_DEBITO_TOTAL_SW_ON = "1207";

    /** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
    private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

    /** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
    private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

    /** The Constant CODIGO_MENSAJE_ADHRIDO_DEBITO_AUTOMATICO. */
    private static final String CODIGO_MENSAJE_ADHRIDO_DEBITO_AUTOMATICO = "1272";

    /** The Constant DOS_ENTERO. */
    private static final int DOS_ENTERO = 2;

    /**
     * The Constant ACCESO_PAGO_TARJETA_MAP. Indica el codigo de estadistica a
     * grabar para el acceso a pago de tarjetas.
     */
    private static final Map<String, String> ACCESO_PAGO_TARJETA_MAP;
    static {
        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("0", EstadisticasConstants.ACCESO_PAGO_TARJETA_ACTION_SHEET_CALENDARIO);
        tempMap.put("1", EstadisticasConstants.ACCESO_PAGO_TARJETA_EVENTO_CALENDARIO);
        tempMap.put("2", EstadisticasConstants.ACCESO_PAGO_TARJETA_ACTION_SHEET_TARJETAS);
        tempMap.put("3", EstadisticasConstants.ACCESO_PAGO_TARJETA_EVENTO_TARJETAS);
        tempMap.put("4", EstadisticasConstants.ACCESO_CONTROLLER_TRANSACCIONES_PAGO_TARJETAS);
        ACCESO_PAGO_TARJETA_MAP = Collections.unmodifiableMap(tempMap);
    }

    /**
     * Variable respuestaFactory.
     */
    @Autowired
    RespuestaFactory respuestaFactory;

    /**
     * Variable cotizacionBO.
     */
    @Autowired
    CotizacionBO cotizacionBO;

    /**
     * Variable estadtisticaManager.
     */
    @Autowired
    EstadisticaManager estadisticaManager;

    /**
     * Variable mensajeService.
     */
    @Autowired
    MensajeBO mensajeBO;

    /**
     * Variable cuentaManager.
     */
    @Autowired
    CuentaManager cuentaManager;

    /** The contratos BO. */
    @Autowired
    ContratoBO contratosBO;

    /**
     * Variable pagoTarjetaCreditoBO.
     */
    @Autowired
    PagoTarjetaCreditoBO pagoTarjetaCreditoBO;

    /**
     * Variable stopDebitTarjetasBO.
     */
    @Autowired
    StopDebitTarjetasBO stopDebitTarjetasBO;

    /**
     * The pagos pendientes BO.
     */
    @Autowired
    private PagosPendientesBO pagosPendientesBO;

    /**
     * obtener los legales.
     */
    @Autowired
    private LegalBO legalBO;

    /** The reporte BO. */
    @Autowired
    private ReporteComprobantePDFBO reporteBO;

    /**
     * The sesion cliente.
     */
    @Autowired
    public SesionCliente sesionCliente;

    /**
     * Llamar a pago protegido.
     */
    @Autowired
    public SeguroTenenciasBO seguroTenenciasBO;

    /**
     * Variable horaHastaPTC. Su valor se extrae desde el archivo de propiedades
     * HBConfig.properties
     */
    @Value("${TRANFERENCIAS.HORAHASTAPTC}")
    private String horaHastaPTC;

    /**
     * Variable horaDesdePTC. Su valor se extrae desde el archivo de propiedades
     * HBConfig.properties
     */
    @Value("${TRANFERENCIAS.HORADESDEPTC}")
    private String horaDesdePTC;

    /**
     * Variable sessionParametros.
     */
    @Autowired
    private SesionParametros sessionParametros;

    /**
     * Obtiene los datos de las tarjetas y de las cuentas de un cliente.
     *
     * @param reintentar the reintentar
     * @return the datos pago tarjeta
     */
    @Override
    public Respuesta<PagoTarjetaInfoView> obtenerDatosInicialesPagoTarjeta(Reintentar reintentar) {

        Cliente cliente = sesionCliente.getCliente();
        if (!"true".equals(reintentar.getReintentar())) {
            sessionParametros.setContador(new ContadorIntentos(2));
        }
        Respuesta<PagoTarjetaInfoView> respuesta;
        Respuesta<List<PagoTarjetaView>> tarjetas = getDatosTarjetas(cliente);

        if (EstadoRespuesta.ERROR.equals(tarjetas.getEstadoRespuesta())) {
            respuesta = respuestaFactory.crearRespuestaError(PagoTarjetaInfoView.class,
                    tarjetas.getItemsMensajeRespuesta());
        } else if (tarjetas.getRespuesta().isEmpty()) {
            String mensaje = mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_NO_HAY_TARJETAS_ACTIVAS)
                    .getMensaje();
            respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(PagoTarjetaInfoView.class, mensaje,
                    "ERROR_NO_TARJETAS");
        } else {
            Respuesta<CuentasView> cuentas = cuentaManager.getCuentasSaldo();
            if (EstadoRespuesta.ERROR.equals(cuentas.getEstadoRespuesta())) {
                respuesta = respuestaFactory.crearRespuestaError(PagoTarjetaInfoView.class,
                        cuentas.getItemsMensajeRespuesta());
            } else if (cuentas.getRespuesta() == null) {
                String mensaje = mensajeBO
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_NO_HAY_CUENTAS_ACTIVAS)
                        .getMensaje();
                respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(PagoTarjetaInfoView.class, mensaje,
                        "ERROR_NO_CUENTAS");
            } else {
                try {
                    String resultadoContratoAceptado = contratosBO.buscarContratoAceptadoOld(
                            cliente.getFechaNacimiento(), cliente.getDni(), CampoEnum.OPPROG, SinonimoEnum.NO_REPETIDO);
                    respuesta = respuestaFactory.crearRespuestaOk(PagoTarjetaInfoView.class,
                            new PagoTarjetaInfoView(CONTRATO_ACEPTADO.equals(resultadoContratoAceptado),
                                    tarjetas.getRespuesta(), cuentas.getRespuesta().getCuentas()));
                } catch (DAOException e) {
                    LOGGER.info(e.getMessage(), e);
                    respuesta = respuestaFactory.crearRespuestaError(PagoTarjetaInfoView.class, mensajeBO
                                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_GENERAL).getMensaje(),
                            null);
                }
            }
        }

        if (ACCESO_PAGO_TARJETA_MAP.containsKey(reintentar.getPuntoDeAcceso())) {
            if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                estadisticaManager.add(ACCESO_PAGO_TARJETA_MAP.get(reintentar.getPuntoDeAcceso()),
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                estadisticaManager.add(ACCESO_PAGO_TARJETA_MAP.get(reintentar.getPuntoDeAcceso()),
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } else {
            LOGGER.debug(
                    "Se intenta grabar una estadistica no valida al obtener datos iniciales en pago de tarjeta: {}.",
                    reintentar.getPuntoDeAcceso());
        }

        return respuesta;

    }

    private Respuesta<FlagCompraProtegidaView> getFlagCompraProtegida() {
        Cliente cliente = sesionCliente.getCliente();
        Respuesta<FlagCompraProtegidaDTO> compraProtegidaDTO = seguroTenenciasBO.flagCompraProtegida(cliente);
        Respuesta<FlagCompraProtegidaView> respuesta = new Respuesta<FlagCompraProtegidaView>();
        FlagCompraProtegidaView flagCompraProtegidaView = new FlagCompraProtegidaView();
        flagCompraProtegidaView = generateFlagCompraProtegidaView(compraProtegidaDTO.getRespuesta());
        respuesta.setRespuesta(flagCompraProtegidaView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    /**
     * Obtiene los datos de las tarjetas.
     *
     * @param cliente El cliente a usar
     * @return Los datos de las tarjetas
     */
    private Respuesta<List<PagoTarjetaView>> getDatosTarjetas(Cliente cliente) {
        Respuesta<List<PagoTarjetaView>> respuesta = new Respuesta<List<PagoTarjetaView>>();
        List<PagoTarjetaView> pagosTarjetas = new ArrayList<PagoTarjetaView>();
        LOGGER.info("Obteniendo datos de tarjetas para el cliente : {}.", cliente);
        try {
            for (DatosTarjeta data : pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente)) {
                PagoTarjetaView pagoTarjetaView = generatePagoTarjetaView(data);
                if (pagosPendientesBO.tienePagosProgramados(cliente, data, obtenerFechaLimite())) {
                    pagoTarjetaView.setTienePagosProgramados(true);
                    pagoTarjetaView.setMensajePagosProgramados(
                            mensajeBO.obtenerMensajePorCodigo(MENSAJE_PAGOS_PROGRAMADOS).getMensaje());
                } else {
                    pagoTarjetaView.setTienePagosProgramados(false);
                }
                pagosTarjetas.add(pagoTarjetaView);
            }

        } catch (BusinessException e) {
            LOGGER.info(e.getMessage(), e);
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuesta.setRespuestaVacia(true);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_GENERAL)
                    .getMensaje());
            item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuesta.add(item);
            return respuesta;
        }

        respuesta.setRespuesta(pagosTarjetas);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    /**
     * Obtiene la fecha limite.
     *
     * @return the string
     */
    private String obtenerFechaLimite() {
        SimpleDateFormat dateFormatterPagoProgramado = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        return dateFormatterPagoProgramado.format(cal.getTime());
    }

    private FlagCompraProtegidaView generateFlagCompraProtegidaView(FlagCompraProtegidaDTO flagCompraProtegidaDTO) {
        FlagCompraProtegidaView flagCompraProtegidaView = new FlagCompraProtegidaView();
        flagCompraProtegidaView.setFlagCompraProtegida(flagCompraProtegidaDTO.getRespuesta());
        return flagCompraProtegidaView;
    }

    /**
     * Arma un objeto PagoTarjetaView a partir de un objeto DatosTarjeta
     *
     * @param data          the data
     * @return the pago tarjeta view
     */
    private PagoTarjetaView generatePagoTarjetaView(DatosTarjeta data) {
        PagoTarjetaView pago = new PagoTarjetaView("--");

        BeanUtils.copyProperties(data, pago);

        pago.setFechaVencimientoLiquidacionTC(ISBANStringUtils.formatearFecha(data.getFechaVencimientoLiquidacionTC()));
        pago.setFechaVencimientoVencida(setearFechaVencimientoVencida(data.getFechaVencimientoLiquidacionTC()));

        pago = cargarMontosUltimoResumen(pago, data);
        pago = cargarMontosPagosRealizados(pago, data);
        pago = cargarMontosSaldoAPagar(pago, data);

        if (data.getCotizacionVendedor() != null) {
            pago.setServicioCotizacionDisponible(true);
            pago.setCotizacionDolares(ISBANStringUtils.formatearSaldo(data.getCotizacionVendedor()));

            BigDecimal cotizacionComprador = data.getCotizacionComprador();
            BigDecimal cotizacionVendedor = data.getCotizacionVendedor();

            BigDecimal saldoTotalConvertidoAPesos = BigDecimal.ZERO;
            BigDecimal saldoTotalConvertidoADolares = BigDecimal.ZERO;

			if (data.getSaldoDolaresTC() != null) {
                BigDecimal saldoDolaresTC = data.getSaldoDolaresTC().negate();
                saldoTotalConvertidoADolares = saldoTotalConvertidoADolares.add(saldoDolaresTC);
                saldoTotalConvertidoAPesos = saldoTotalConvertidoAPesos.add(saldoDolaresTC.multiply(cotizacionVendedor));

                pago.setSaldoSinSiguienteCierreDolaresPositivo(obtenerDescripcionDeSigno(saldoDolaresTC));
            }

			if (data.getSaldoPesosTC() != null) {
                BigDecimal saldoPesosTC = data.getSaldoPesosTC().negate();
                saldoTotalConvertidoAPesos = saldoTotalConvertidoAPesos.add(saldoPesosTC);
                saldoTotalConvertidoADolares = saldoTotalConvertidoADolares
                        .add(saldoPesosTC.divide(cotizacionComprador, DOS_ENTERO, BigDecimal.ROUND_HALF_UP));

                pago.setSaldoSinSiguienteCierrePesosPositivo(obtenerDescripcionDeSigno(saldoPesosTC));
            }

            pago.setSaldoAPagarConvertidoADolares(ISBANStringUtils.formatearSaldo(saldoTotalConvertidoADolares));
            pago.setSaldoAPagarConvertidoAPesos(ISBANStringUtils.formatearSaldo(saldoTotalConvertidoAPesos));

            if (data.getSaldoDolaresTC() != null && data.getSaldoPesosTC() != null) {

                BigDecimal saldoEnPesos;
                if (data.getSaldoUltimoCierreDolaresTC().signum() == 1) {
                    saldoEnPesos = saldoTotalConvertidoAPesos.abs();
                } else {
                    saldoEnPesos = data.getSaldoPesosTC().negate();
                }

                BigDecimal saldoEnDolares;
                if (data.getSaldoUltimoCierrePesosTC().signum() == 1) {
                    saldoEnDolares = saldoTotalConvertidoADolares.abs();
                } else {
                    saldoEnDolares = data.getSaldoDolaresTC().negate();
                }

                if (saldoEnDolares.signum() == 1) {
                    pago.setSaldoSinSiguienteCierreDolaresConvertidoAPesos(
                            ISBANStringUtils.formatearSaldoSinAbs(saldoEnDolares.multiply(cotizacionVendedor)));
                } else {
                    pago.setSaldoSinSiguienteCierreDolaresConvertidoAPesos("0,00");
                }

                if (saldoEnPesos.signum() == 1) {
                    pago.setSaldoSinSiguienteCierrePesosConvertidoADolares(ISBANStringUtils
                            .formatearSaldoSinAbs(saldoEnPesos.divide(cotizacionComprador, DOS_ENTERO, RoundingMode.HALF_UP)));
                } else {
                    pago.setSaldoSinSiguienteCierrePesosConvertidoADolares("0,00");
                }

                BigDecimal importeDolaresConvertidoPesos = BigDecimal.ZERO;
                BigDecimal saldoUltimoCierreDolaresTC = data.getSaldoUltimoCierreDolaresTC();
                if (saldoUltimoCierreDolaresTC.signum() < 0) {
                	importeDolaresConvertidoPesos = saldoUltimoCierreDolaresTC.abs().multiply(cotizacionVendedor);
                }
                BigDecimal importePesosConvertidoDolares = BigDecimal.ZERO;
                BigDecimal saldoUltimoCierrePesosTC = data.getSaldoUltimoCierrePesosTC();
                if (saldoUltimoCierrePesosTC.signum() < 0) {
                	importePesosConvertidoDolares = saldoUltimoCierrePesosTC.abs().divide(cotizacionComprador, DOS_ENTERO, RoundingMode.HALF_UP);
                }
                BigDecimal totalConvDolares = BigDecimal.ZERO;
                if (data.getSaldoTotalConvDolares().signum() < 0) {
                	totalConvDolares = data.getSaldoTotalConvDolares().negate();
                }
                BigDecimal totalConvPesos = BigDecimal.ZERO;
                if (data.getSaldoTotalConvPesos().signum() < 0) {
                	totalConvPesos = data.getSaldoTotalConvPesos().negate();
                }
                pago.setImporteDolaresConvertidoAPesos(ISBANStringUtils.formatearSaldoSinAbs(importeDolaresConvertidoPesos));
                pago.setImportePesosConvertidoADolares(ISBANStringUtils.formatearSaldoSinAbs(importePesosConvertidoDolares));
                pago.setTotalAPagarEnPesos(ISBANStringUtils.formatearSaldoSinAbs(totalConvPesos));
                pago.setTotalAPagarEnDolares(ISBANStringUtils.formatearSaldoSinAbs(totalConvDolares));
            }

        }

        if (data.getSaldoUltimoCierreDolaresTC() != null) {
            pago.setSaldoDolaresTCPositivo(data.getSaldoUltimoCierreDolaresTC().negate().signum() == 1);
        }

        if (data.getSaldoUltimoCierrePesosTC() != null) {
            pago.setSaldoPesosTCPositivo(data.getSaldoUltimoCierrePesosTC().negate().signum() == 1);
        }

        if (data.getPagoMinimoPesosTC() == null) {
            pago.setPagoMinimoPesosTC("0,00");
        } else {
            pago.setPagoMinimoPesosTC(ISBANStringUtils.formatearSaldo(data.getPagoMinimoPesosTC()));
        }

        pago.setNumeroTarjeta(data.getTipoCuentaTarjeta().getAbreviatura() + ISBANStringUtils.ESPACIO_STRING
                + TarjetaUtils.crearMascaraNroTarjeta(data.getNumeroTarjeta(), data.getTipoCuentaTarjeta()));
        pago.setNumeroTarjetaSinMarca(
                TarjetaUtils.crearMascaraNroTarjeta(data.getNumeroTarjeta(), data.getTipoCuentaTarjeta()));
        pago.setAlias(StringUtils.isEmpty(data.getAlias()) ? StringUtils.EMPTY
                : data.getTipoCuentaTarjeta().getAbreviatura() + ISBANStringUtils.ESPACIO_STRING + "\""
                + data.getAlias() + "\"");

        if (DEBITO_CC_MINIMO.equals(pago.getFormaPagoTarjetaCredito())
                || DEBITO_CA_MINIMO.equals(pago.getFormaPagoTarjetaCredito())) {
            pago.setMensajeSwitchOn(mensajeBO.obtenerMensajePorCodigo(CODIGO_DEBITO_MINIMO_SW_ON).getMensaje());
            pago.setMensajeSwitchOff(mensajeBO.obtenerMensajePorCodigo(CODIGO_DEBITO_MINIMO_SW_OFF).getMensaje());
            pago.setTieneDebitoAutomatico(true);
            pago.setMensajeDebitoAutomatico(MessageFormat.format(
                    mensajeBO.obtenerMensajePorCodigo(CODIGO_MENSAJE_ADHRIDO_DEBITO_AUTOMATICO).getMensaje(),
                    "mínimo"));
        } else if (DEBITO_CC_TOTAL.equals(pago.getFormaPagoTarjetaCredito())
                || DEBITO_CA_TOTAL.equals(pago.getFormaPagoTarjetaCredito())) {
            pago.setMensajeSwitchOn(mensajeBO.obtenerMensajePorCodigo(CODIGO_DEBITO_TOTAL_SW_ON).getMensaje());
            pago.setMensajeSwitchOff(mensajeBO.obtenerMensajePorCodigo(CODIGO_DEBITO_TOTAL_SW_OFF).getMensaje());
            pago.setTieneDebitoAutomatico(true);
            pago.setMensajeDebitoAutomatico(MessageFormat.format(
                    mensajeBO.obtenerMensajePorCodigo(CODIGO_MENSAJE_ADHRIDO_DEBITO_AUTOMATICO).getMensaje(), "total"));
        }

        pago.setCodigoTitularidad(data.getCodigoTitularidad());
        Cuenta tarjetaElegida = sesionCliente.getCliente().getTarjetaDesdeNroTarjeta(data.getNumeroTarjeta());
        pago.setTipoTarjeta(tarjetaElegida.getTipoCuentaEnum().getCodigo().toString());

        // formato a 4 digitos
        pago.setSucursalCuentaTarjeta(String.format("%04d", data.getSucursalBancoPesos()));

        // Tipo de cambio unificado
        pago.setCotizacionVendedor(ISBANStringUtils.formatearSaldo(data.getCotizacionVendedor()));
        pago.setCotizacionComprador(ISBANStringUtils.formatearSaldo(data.getCotizacionComprador()));
        pago.setSaldoPendienteDolares(ISBANStringUtils.formatearSaldoSinAbs(data.getSaldoPendienteDolares()));
        pago.setSaldoPendientePesos(ISBANStringUtils.formatearSaldoSinAbs(data.getSaldoPendientePesos()));

        return pago;
    }

    /**
     * Cargar montos ultimo resumen.
     *
     * @param pago the pago
     * @param data the data
     * @return the pago tarjeta view
     */
    private PagoTarjetaView cargarMontosUltimoResumen(PagoTarjetaView pago, DatosTarjeta data) {
        if (data.getSaldoDolaresTC() != null) {
            pago.setSaldoDolaresTC(
                    ISBANStringUtils.formatearSaldoSinAbs(data.getSaldoUltimoCierreDolaresTC().negate()));
        }

        if (data.getSaldoPesosTC() != null) {
            pago.setSaldoPesosTC(ISBANStringUtils.formatearSaldoSinAbs(data.getSaldoUltimoCierrePesosTC().negate()));
        }

        return pago;
    }

    /**
     * Cargar montos saldo A pagar.
     *
     * @param pago the pago
     * @param data the data
     * @return the pago tarjeta view
     */
    private PagoTarjetaView cargarMontosSaldoAPagar(PagoTarjetaView pago, DatosTarjeta data) {

        if (data.getSaldoPesosTC() != null) {
            pago.setSaldoSinSiguienteCierrePesos(
                    ISBANStringUtils.formatearSaldoSinAbs(data.getSaldoPesosTC().negate()));
        }

        if (data.getSaldoDolaresTC() != null) {
            pago.setSaldoSinSiguienteCierreDolares(
                    ISBANStringUtils.formatearSaldoSinAbs(data.getSaldoDolaresTC().negate()));
        }

        return pago;
    }

    /**
     * Cargar montos pagos realizados.
     *
     * @param pago the pago
     * @param data the data
     * @return the pago tarjeta view
     */
    private PagoTarjetaView cargarMontosPagosRealizados(PagoTarjetaView pago, DatosTarjeta data) {

        if (data.getPagosPesosTC() != null) {
            pago.setPagoAcumuladoMensualPesos(ISBANStringUtils.formatearSaldo(data.getPagosPesosTC()));
        } else {
            pago.setPagoAcumuladoMensualPesos("0,00");
        }

        if (data.getPagosDolaresTC() != null) {
            pago.setPagoAcumuladoMensualDolares(ISBANStringUtils.formatearSaldo(data.getPagosDolaresTC()));
        } else {
            pago.setPagoAcumuladoMensualDolares("0,00");
        }

        return pago;
    }

    /**
     * Obtener descripcion de signo.
     *
     * @param valor the valor
     * @return the string
     */
    private String obtenerDescripcionDeSigno(BigDecimal valor) {
        return valor.signum() == 1 ? "positivo" : valor.signum() == 0 ? "cero" : "negativo";
    }

    /**
     * Setear fecha vencimiento vencida.
     *
     * @param fechaVencimiento the fecha vencimiento
     * @return the boolean
     */
    private Boolean setearFechaVencimientoVencida(Date fechaVencimiento) {

        DateTime fechaVencimientoJoda = new DateTime(fechaVencimiento);
        return fechaVencimientoJoda.isBeforeNow();
    }

    /**
     * verifica si la fecha de pago es mayor a la actual.
     *
     * @param fechaPago the fecha pago
     * @return true, if successful
     * @throws ParseException the parse exception
     */
    private boolean fechaPagoMayorAActual(String fechaPago) throws ParseException {
        SimpleDateFormat formatoFechaP = new SimpleDateFormat("dd/MM/yyyy");
        Date dateFechaP = formatoFechaP.parse(fechaPago);
        return FechaUtils.fecha1MayorFecha2(dateFechaP, new Date());
    }

    /**
     * Setea el mensaje exitoso para agregarle la parte del feedback de stopDebit.
     *
     * @param resp the new eo mensaje exitoso con stop debit
     */
    private void seteoMensajeExitosoConStopDebit(Respuesta<ComprobantePagoTarjeta> resp) {
        String conExit = "se realizó con éxito.</p>";
        String mensaje = resp.getRespuesta().getMensaje();

        mensaje = mensaje.replace(conExit, " ") + "y el <b>Stop Debit</b> se realizaron con éxito";
        resp.getRespuesta().setMensaje(mensaje);
    }

    /**
     * Realiza el pago de una tarjeta de acuerdo a los datos pasados de UX, si se
     * seteo la opcion de stop debit, se realiza el mismo.
     *
     * @param pagoTarjetaView the pago tarjeta view
     * @param cliente         the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobantePagoTarjeta> pagarTarjeta(PagoTarjetaCreditoView pagoTarjetaView, Cliente cliente) {
        validarHashDeLaVistaEnSesion(pagoTarjetaView);
        Respuesta<ComprobantePagoTarjeta> respuesta;
        boolean esPagoProgramado = false;
        sessionParametros.setPagoTarjetaView(pagoTarjetaView);
        try {
            esPagoProgramado = fechaPagoMayorAActual(pagoTarjetaView.getFechaDePago());
            if (esPagoProgramado) {
                respuesta = pagoTarjetaCreditoBO.programarPago(generateDatosPagoProgramado(pagoTarjetaView, cliente),
                        cliente);
            } else {
                String pagoProgramadoMsg = horarioBancario();
                if (StringUtils.isBlank(pagoProgramadoMsg)) {

                    DatosTarjeta datosTarjeta = pagoTarjetaCreditoBO.obtenerDatosTarjetaPago(pagoTarjetaView.getNumeroTarjeta());
                    respuesta = pagoTarjetaCreditoBO.pagar(generateDatosPagoTC(pagoTarjetaView, cliente, datosTarjeta), cliente);
                } else {
                    respuesta = respuestaFactory.crearRespuestaWarningOk(ComprobantePagoTarjeta.class,
                            pagoProgramadoMsg, null); // popup fuera horario
                }
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(ComprobantePagoTarjeta.class,
                    mensajeBO.obtenerMensajePorCodigo("1459").getMensaje(), null);
        }
        TipoPagoTCEnum tipoPagoEnum = TipoPagoTCEnum.fromValue(pagoTarjetaView.getTipoPagoTC());
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            estadisticaManager.add(tipoPagoEnum.getEstadisticaPago(esPagoProgramado),
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

            if (Boolean.parseBoolean(pagoTarjetaView.getStopDebit())) {
                Respuesta<StopDebitOut> resStop = stopDebit(pagoTarjetaView, cliente);
                respuesta = generarRespuesta(resStop.getRespuesta(), respuesta.getRespuesta());
            } else {
                estadisticaManager.add(EstadisticasConstants.PAGO_TARJETA_SIN_STOP_DEBIT,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
        } else {
            estadisticaManager.add(tipoPagoEnum.getEstadisticaPago(esPagoProgramado),
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

            estadisticaManager.add(EstadisticasConstants.PAGO_TARJETA_SIN_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        sessionParametros.setComprobantePagoTarjeta(respuesta.getRespuesta());
        return respuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * PagosTarjetaManager#llamarStopDebit(ar.com.santanderrio.obp.servicios.
     * pagos.entities.StopDebitIn,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<StopDebitOut> llamarStopDebit(StopDebitIn stopDebitIn, Cliente cliente) {
        if (!"true".equals(stopDebitIn.getReintentar())) {
            sessionParametros.setContador(new ContadorIntentos(2));
        }

        String fechaActual = FechaUtils.obtenerFechaActual();

        if (fechaActual.equals(stopDebitIn.getVencimiento())) {
            try {
                stopDebitTarjetasBO.revisarHorarioSolicitud(stopDebitIn.getVencimiento());
            } catch (DAOException ex) {
                LOGGER.info(ex.getMessage(), ex);
                Respuesta<StopDebitOut> respError = new Respuesta<StopDebitOut>();
                StopDebitOut stop = new StopDebitOut();
                stop.setErrorCode(1);
                respError.setRespuesta(stop);

                estadisticaManager.add(EstadisticasConstants.CONFIRMACION_STOP_DEBIT,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return armarRespuestaError(respError, stopDebitIn);
            }
        }

        PagoTarjetaCreditoView pagoTarjetaView = new PagoTarjetaCreditoView();
        pagoTarjetaView.setNumeroTarjeta(stopDebitIn.getNroTarjeta());

        Respuesta<StopDebitOut> resStop = stopDebit(pagoTarjetaView, cliente);
        StopDebitOut stopDebit = resStop.getRespuesta();

        if (stopDebit != null && "OK".equals(stopDebit.getResultado())) {
            resStop.setEstadoRespuesta(EstadoRespuesta.OK);

            String msj = mensajeBO.obtenerMensajePorCodigo(MENSAJE_STOP_DEBIT_OK).getMensaje();
            stopDebit.setMensajeFeedback(MessageFormat.format(msj, "<b>" + stopDebitIn.getNroTarjeta() + "</b>"));

            stopDebit.setFechaHora(FechaUtils.obtenerFechaYHoraActual());
            stopDebit.setLegalesSEUO(legalBO.buscarLegal("1005").getRespuesta());

            resStop.setRespuesta(stopDebit);
            estadisticaManager.add(EstadisticasConstants.CONFIRMACION_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

            return resStop;

        } else {
            estadisticaManager.add(EstadisticasConstants.CONFIRMACION_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

            return armarRespuestaError(resStop, stopDebitIn);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * PagosTarjetaManager#estadisticaVisualizacionComprobanteStopDebit()
     */
    @Override
    public void estadisticaVisualizacionComprobanteStopDebit() {
        estadisticaManager.add(EstadisticasConstants.COMPROBANTE_STOP_DEBIT,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * PagosTarjetaManager#estadisticaVisualizacionComprobanteBajaAdhesion()
     */
    @Override
    public void estadisticaVisualizacionComprobanteBajaAdhesion() {
        estadisticaManager.add(EstadisticasConstants.COMPROBANTE_BAJA_ADHESION_TARJETA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * arma la respuesta error.
     *
     * @param resp        the resp
     * @param stopDebitIn the stop debit in
     * @return the respuesta
     */
    private Respuesta<StopDebitOut> armarRespuestaError(Respuesta<StopDebitOut> resp, StopDebitIn stopDebitIn) {
        StopDebitOut stopDebit = resp.getRespuesta();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();

        if (stopDebit != null && stopDebit.getErrorCode() == 1) {
            // fuera de horario
            resp.setEstadoRespuesta(EstadoRespuesta.ERROR);

            item.setTipoError(TipoError.FUERA_DE_HORARIO.getDescripcion());

            String mensaje = mensajeBO.obtenerMensajePorCodigo(MENSAJE_STOP_DEBIT_FUERA_DE_HORARIO).getMensaje();
            String msj = MessageFormat.format(mensaje, stopDebitIn.getNroTarjeta());

            item.setMensaje(msj);

        } else if (stopDebit != null && (stopDebit.getErrorCode() == StopDebitTarjetasDAO.CODIGO_ERROR_STOP_DEBIT_PEDIDO
                || stopDebit.getErrorCode() == StopDebitTarjetasDAO.CODIGO_ERROR_STOP_DEBIT_PEDIDO2)) {
            resp.setEstadoRespuesta(EstadoRespuesta.ERROR);

            item.setTipoError(TipoError.STOP_DEBIT_EXISTENTE.getDescripcion());
            item.setMensaje(mensajeBO.obtenerMensajePorCodigo(MENSAJE_STOP_DEBIT_YA_SOLICITADO).getMensaje());

        } else {

            resp.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

            item.setMensaje(mensajeBO.obtenerMensajePorCodigo(MENSAJE_ERROR_GENERICO_STOP_DEBIT).getMensaje());
            if (!sessionParametros.getContador().permiteReintentar()) {
                // no reintenta
                resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
                item.setTipoError(TipoError.ERROR_REINTENTOS_OPERACION.getDescripcion());

            } else {
                resp.setEstadoRespuesta(EstadoRespuesta.WARNING);
                item.setTipoError(TipoError.ERROR_REINTENTAR.getDescripcion());
            }

        }
        resp.add(item);
        return resp;
    }

    /**
     * Realiza el pago programado de una tarjeta de credito.
     *
     * @param pagoTarjetaView the pago tarjeta view
     * @param cliente         the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobantePagoTarjeta> programarPago(PagoTarjetaCreditoView pagoTarjetaView, Cliente cliente) {
        try {
            String fechaMasUno = FechaUtils.getFechaMasUnDia(pagoTarjetaView.getFechaDePago());
            pagoTarjetaView.setFechaDePago(fechaMasUno);
            return pagoTarjetaCreditoBO.programarPago(generateDatosPagoProgramado(pagoTarjetaView, cliente), cliente);
        } catch (ParseException e) {
            LOGGER.info(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError(ComprobantePagoTarjeta.class, e.getMessage(), null);
        }
    }

    /**
     * Inicia el flujo que acepta el contrato de pago programado.
     *
     * @param cliente the cliente
     * @return OK o ERROR de acuerdo al resultado
     */
    @Override
    public Respuesta<String> aceptacionContratoPagoProgramado(Cliente cliente) {

        try {
            String aceptacion = contratosBO.confirmarAceptacionContratoOld(cliente.getFechaNacimiento(),
                    cliente.getDni(), CampoEnum.OPPROG, SinonimoEnum.NO_REPETIDO);

            if ("OK".equals(aceptacion)) {
                estadisticaManager.add(EstadisticasConstants.ACEPTACION_CONTRATO_PAGO_PROGRAMADO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                return respuestaFactory.crearRespuestaOk(String.class, aceptacion);
            } else if ("ERROR".equals(aceptacion)) {
                estadisticaManager.add(EstadisticasConstants.ACEPTACION_CONTRATO_PAGO_PROGRAMADO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaOk(String.class, aceptacion);
            }
            return respuestaFactory.crearRespuestaError(String.class, "No acepto el contrato", "");
        } catch (DAOException e) {
            LOGGER.info(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError(String.class, e.getMessage(), "");
        }
    }

    /**
     * Obtener datos tarjeta.
     *
     * @param cliente    El cliente en cuestion
     * @param nroTarjeta El numero de la tarjeta puntual (enmascarado)
     * @return El objeto view de pagoTarjeta
     */
    private PagoTarjetaView obtenerDatosTarjeta(Cliente cliente, String nroTarjeta) {
        List<PagoTarjetaView> list = this.getDatosTarjetas(cliente).getRespuesta();

        for (PagoTarjetaView ob : list) {
            if (ob.getNumeroTarjeta().equals(nroTarjeta)) {
                return ob;
            }
        }
        return null;
    }

    /**
     * Crear e inicializar datos.
     *
     * @return el objeto DatosPagoTC
     */
    private DatosPagoTC crearEInicializarDatos() {
        DatosPagoTC datosPagoTC = new DatosPagoTC();

        datosPagoTC.setImporteDolares(StringUtils.repeat("0", 15));
        datosPagoTC.setImportePagoTC(StringUtils.repeat("0", 15));
        datosPagoTC.setTipoCuentaBancoDolares(StringUtils.repeat("0", 2));
        datosPagoTC.setSucursalBancoDolares(StringUtils.repeat("0", 3));
        datosPagoTC.setNroCuentaBancoDolares(StringUtils.repeat("0", 7));
        datosPagoTC.setSucursalCuentaDebito(StringUtils.repeat("0", 3));
        datosPagoTC.setTipoCuentaDebito(StringUtils.repeat("0", 2));
        datosPagoTC.setNroCuentaDebito(StringUtils.repeat("0", 7));

        return datosPagoTC;
    }

    /**
     * Armo el objeto DatosPagoTC para pago programado PagoTarjetaView.
     *
     * @param pagoTarjeta the pago tarjeta
     * @param cliente     the cliente
     * @return the datos pago tc
     */
    private DatosPagoTC generateDatosPagoProgramado(PagoTarjetaCreditoView pagoTarjeta, Cliente cliente) {
        DatosPagoTC datosPagoTC = crearEInicializarDatos();
        Cuenta cuentaPesos = cliente.getCuenta(pagoTarjeta.getCbuPesos());
        Cuenta cuentaDolares = cliente.getCuenta(pagoTarjeta.getCbuDolares());
        Cuenta tarjeta = cliente.getTarjeta(pagoTarjeta.getNumeroTarjeta());

        datosPagoTC.setStopDebit("true".equals(pagoTarjeta.getStopDebit()));

        String monedaSeleccionada = getMonedaSeleccionada(pagoTarjeta.getMonedaSeleccionado());
        PagoTarjetaView pagoTarjetaView = obtenerDatosTarjeta(cliente, pagoTarjeta.getNumeroTarjeta());

        datosPagoTC.setCodigoMoneda(monedaSeleccionada);

        datosPagoTC.setNroCuentaTarjeta(tarjeta.getNroCuentaProducto());

        // En caso de ser VISA vendrá el valor 07 convertirlo a 7
        // En caso de ser AMEX vendrá el valor 42 convertirlo a 6

        if ("07".equals(tarjeta.getTipoCuentaSinUnificar())) {
            datosPagoTC.setTipoTarjeta("7");
        } else if ("42".equals(tarjeta.getTipoCuentaSinUnificar())) {
            datosPagoTC.setTipoTarjeta("6");
        } else if ("06".equals(tarjeta.getTipoCuentaSinUnificar())) {
            datosPagoTC.setTipoTarjeta("6");
        }
        datosPagoTC.setNroTarjeta(tarjeta.getNroTarjetaCredito());

        datosPagoTC.setFechaPagoProgramado(pagoTarjeta.getFechaDePago());
        datosPagoTC.setTipoPagoTC(
                TipoPagoTCEnum.SALDO.getValue().equals(pagoTarjeta.getTipoPagoTC()) ? TipoPagoTCEnum.OTRO.getValue()
                        : pagoTarjeta.getTipoPagoTC());
        datosPagoTC.setMomentoPagoTC("2");
        datosPagoTC.setImporteMinimo(pagoTarjeta.getImporteMinimo());

        datosPagoTC.setTotalAPagarEnDolares(pagoTarjeta.getTotalAPagarEnDolares());
        datosPagoTC.setTotalAPagarEnPesos(pagoTarjeta.getTotalAPagarEnPesos());
        datosPagoTC.setNroTarjetaFormateado(pagoTarjeta.getNumeroTarjeta());

        if (TipoPagoTCEnum.TOTAL.getValue().equals(pagoTarjeta.getTipoPagoTC())) {

            if (monedaSeleccionada.equals(MONEDA_PESO)) {
                datosPagoTC.setImportePagoTC(pagoTarjetaView.getTotalAPagarEnPesos());

            } else if (monedaSeleccionada.equals(MONEDA_DOLAR)) {
                datosPagoTC.setImporteDolares(pagoTarjetaView.getTotalAPagarEnDolares());

            } else {
                // Se agregan los montos parciales en pesos y dolares
                datosPagoTC.setImportePagoTC(pagoTarjetaView.getSaldoPesosTC());
                datosPagoTC.setImporteDolares(pagoTarjetaView.getSaldoDolaresTC());
            }

        } else if (TipoPagoTCEnum.MINIMO.getValue().equals(pagoTarjeta.getTipoPagoTC())) {
            datosPagoTC.setImportePagoTC(pagoTarjetaView.getPagoMinimoPesosTC());
            datosPagoTC.setTipoPagoTC(TipoPagoTCEnum.MINIMO.getValue());

        } else if (TipoPagoTCEnum.SALDO.getValue().equals(pagoTarjeta.getTipoPagoTC())) {
            if (TipoMonedaPagoTCEnum.AMBOS.getValue().equals(monedaSeleccionada)) {
                datosPagoTC.setImportePagoDolares(ISBANStringUtils
                        .convertirABigDecimal(pagoTarjeta.getSaldoSinSiguienteCierreDolares()).toString());
                datosPagoTC.setImportePagoPesos(ISBANStringUtils
                        .convertirABigDecimal(pagoTarjeta.getSaldoSinSiguienteCierrePesos()).toString());
            } else {
                datosPagoTC.setImportePagoDolares(ISBANStringUtils
                        .convertirABigDecimal(pagoTarjeta.getSaldoAPagarConvertidoADolares()).toString());
                datosPagoTC.setImportePagoPesos(
                        ISBANStringUtils.convertirABigDecimal(pagoTarjeta.getSaldoAPagarConvertidoAPesos()).toString());
            }
            datosPagoTC.setImporteDolares(datosPagoTC.getImportePagoDolares());
            datosPagoTC.setImportePagoTC(datosPagoTC.getImportePagoPesos());
            datosPagoTC.setEsSaldoAPagar(true);
        } else {
            // OTRO IMPORTE
            if (TEXTO_MONEDA_DOLAR.equals(pagoTarjeta.getMonedaSeleccionado())
                    || TEXTO_MONEDA_AMBAS.equals(pagoTarjeta.getMonedaSeleccionado())) {
                String otroImporteDolares = pagoTarjeta.getImportePagoDolares().getNum();
                datosPagoTC.setImportePagoDolares(otroImporteDolares);
                datosPagoTC.setImporteDolares(otroImporteDolares);
            }
            if (TEXTO_MONEDA_PESO.equals(pagoTarjeta.getMonedaSeleccionado())
                    || TEXTO_MONEDA_AMBAS.equals(pagoTarjeta.getMonedaSeleccionado())) {
                String otroImportePesos = pagoTarjeta.getImportePagoPesos().getNum();
                datosPagoTC.setImportePagoPesos(otroImportePesos);
                datosPagoTC.setImportePagoTC(otroImportePesos);
            }
        }

        if (TipoMonedaPagoTCEnum.AMBOS.getValue().equals(monedaSeleccionada)) {
            datosPagoTC.setSucursalCuentaDebito(cuentaPesos.getNroSucursal());
            datosPagoTC.setTipoCuentaDebito(cuentaPesos.getTipoCuentaSinUnificar());
            datosPagoTC.setNroCuentaDebito(cuentaPesos.getNroCuentaProducto());
            datosPagoTC.setSucursalCuentaTarjeta(cuentaPesos.getSucursalPaquete());
            datosPagoTC.setTipoCuentaBancoDolares(cuentaDolares.getTipoCuentaSinUnificarDls());
            datosPagoTC.setSucursalBancoDolares(cuentaDolares.getNroSucursal());
            datosPagoTC.setNroCuentaBancoDolares(cuentaDolares.getNroCuentaProducto());
        } else {
            setIfOnlyPesosODolares(monedaSeleccionada, datosPagoTC, cuentaDolares, cuentaPesos);
        }

        datosPagoTC.setPagoProgramado(true);

        return datosPagoTC;
    }

    /**
     * Sets the if only pesos O dolares.
     *
     * @param monedaSeleccionada the moneda seleccionada
     * @param datosPagoTC        the datos pago TC
     * @param cuentaDolares      the cuenta dolares
     * @param cuentaPesos        the cuenta pesos
     */
    private void setIfOnlyPesosODolares(String monedaSeleccionada, DatosPagoTC datosPagoTC, Cuenta cuentaDolares,
                                        Cuenta cuentaPesos) {
        if (TipoMonedaPagoTCEnum.DOLARES.getValue().equals(monedaSeleccionada)) {
            datosPagoTC.setTipoCuentaBancoDolares(cuentaDolares.getTipoCuentaSinUnificarDls());
            datosPagoTC.setSucursalBancoDolares(cuentaDolares.getNroSucursal());
            datosPagoTC.setNroCuentaBancoDolares(cuentaDolares.getNroCuentaProducto());

        } else if (TipoMonedaPagoTCEnum.PESOS.getValue().equals(monedaSeleccionada)) {
            datosPagoTC.setSucursalCuentaDebito(cuentaPesos.getNroSucursal());
            datosPagoTC.setTipoCuentaDebito(cuentaPesos.getTipoCuentaSinUnificar());
            datosPagoTC.setNroCuentaDebito(cuentaPesos.getNroCuentaProducto());
            datosPagoTC.setSucursalCuentaTarjeta(cuentaPesos.getSucursalPaquete());
        }

    }

    /**
     * Armo el objeto DatosPagoTC a partir del objeto enviado de la vista
     * PagoTarjetaView.
     *
     * @param pagoTarjeta the pago tarjeta
     * @param cliente     the cliente
     * @param datosTarjeta     the datosTarjeta
     * @return the datos pago tc
     */
    private DatosPagoTC generateDatosPagoTC(PagoTarjetaCreditoView pagoTarjeta, Cliente cliente, DatosTarjeta datosTarjeta) {
        DatosPagoTC datosPagoTC = new DatosPagoTC();
        Cuenta cuentaPesos = cliente.getCuenta(pagoTarjeta.getCbuPesos());
        Cuenta cuentaDolares = cliente.getCuenta(pagoTarjeta.getCbuDolares());
        Cuenta tarjeta = cliente.getTarjeta(pagoTarjeta.getNumeroTarjeta());
        TipoPagoTCEnum tipoPagoEnum = TipoPagoTCEnum.fromValue(pagoTarjeta.getTipoPagoTC());
        datosPagoTC.setFechaPagoProgramado(pagoTarjeta.getFechaDePago());

        datosPagoTC.setStopDebit("true".equals(pagoTarjeta.getStopDebit()));

        String monedaSeleccionada = getMonedaSeleccionada(pagoTarjeta.getMonedaSeleccionado());

        datosPagoTC.setCodigoMoneda(monedaSeleccionada);
        datosPagoTC.setImporteMinimo(pagoTarjeta.getImporteMinimo());

        datosPagoTC.setImportePagoDolares(pagoTarjeta.obtenerImporteIngresadoDolares());
        datosPagoTC.setImportePagoPesos(pagoTarjeta.obtenerImporteIngresadoPesos());

        if (TipoPagoTCEnum.TOTAL.equals(tipoPagoEnum)) {
            if (TipoMonedaPagoTCEnum.AMBOS.getValue().equals(monedaSeleccionada)) {
                datosPagoTC.setTotalAPagarEnDolares(pagoTarjeta.getSaldoDolaresTC());
                datosPagoTC.setTotalAPagarEnPesos(pagoTarjeta.getSaldoPesosTC());
            } else {
                datosPagoTC.setTotalAPagarEnDolares(pagoTarjeta.getTotalAPagarEnDolares());
                datosPagoTC.setTotalAPagarEnPesos(pagoTarjeta.getTotalAPagarEnPesos());
            }
        }

        if (TipoPagoTCEnum.SALDO.equals(tipoPagoEnum)) {
            if (TipoMonedaPagoTCEnum.AMBOS.getValue().equals(monedaSeleccionada)) {
                datosPagoTC.setImportePagoDolares(ISBANStringUtils
                        .convertirABigDecimal(pagoTarjeta.getSaldoSinSiguienteCierreDolares()).toString());
                datosPagoTC.setImportePagoPesos(ISBANStringUtils
                        .convertirABigDecimal(pagoTarjeta.getSaldoSinSiguienteCierrePesos()).toString());
            } else {
                datosPagoTC.setImportePagoDolares(ISBANStringUtils
                        .convertirABigDecimal(pagoTarjeta.getSaldoAPagarConvertidoADolares()).toString());
                datosPagoTC.setImportePagoPesos(
                        ISBANStringUtils.convertirABigDecimal(pagoTarjeta.getSaldoAPagarConvertidoAPesos()).toString());
            }
            datosPagoTC.setEsSaldoAPagar(true);
        }

        datosPagoTC.setNroTarjetaFormateado(pagoTarjeta.getNumeroTarjeta());

        datosPagoTC.setTipoPagoTC(tipoPagoEnum.getValorServicio());

        if (TipoMonedaPagoTCEnum.AMBOS.getValue().equals(monedaSeleccionada)) {
            datosPagoTC.setTipoCuentaBancoDolares(cuentaDolares.obtenerTipoCuentaSinUnificarDolares());
            datosPagoTC.setSucursalBancoDolares(cuentaDolares.getNroSucursal());
            datosPagoTC.setNroCuentaBancoDolares(cuentaDolares.getNroCuentaProducto());
            datosPagoTC.setSucursalCuentaDebito(cuentaPesos.getNroSucursal());
            datosPagoTC.setTipoCuentaDebito(cuentaPesos.getTipoCuentaSinUnificar());
            datosPagoTC.setNroCuentaDebito(cuentaPesos.getNroCuentaProducto());
            datosPagoTC.setSucursalCuentaTarjeta(cuentaPesos.getSucursalPaquete());
        } else {
            datosPagoTC.setTipoCuentaBancoDolares(StringUtils.repeat(" ", 2));
            datosPagoTC.setSucursalBancoDolares(StringUtils.repeat(" ", 3));
            datosPagoTC.setNroCuentaBancoDolares(StringUtils.repeat(" ", 7));
            if (TipoMonedaPagoTCEnum.DOLARES.getValue().equals(monedaSeleccionada)) {
                datosPagoTC.setSucursalCuentaDebito(cuentaDolares.getNroSucursal());
                datosPagoTC.setSucursalCuentaTarjeta(cuentaDolares.getSucursalPaquete());
                datosPagoTC.setNroCuentaDebito(cuentaDolares.getNroCuentaProducto());
                datosPagoTC.setTipoCuentaDebito(cuentaDolares.obtenerTipoCuentaSinUnificarDolares());

            } else if (TipoMonedaPagoTCEnum.PESOS.getValue().equals(monedaSeleccionada)) {
                if ("09".equals(cuentaPesos.getSucursalPaquete())) {
                    datosPagoTC.setSucursalCuentaDebito("03");
                } else {
                    datosPagoTC.setSucursalCuentaDebito(cuentaPesos.getNroSucursal());
                }
                datosPagoTC.setTipoCuentaDebito(cuentaPesos.getTipoCuentaSinUnificar());
                datosPagoTC.setNroCuentaDebito(cuentaPesos.getNroCuentaProducto());
                datosPagoTC.setSucursalCuentaTarjeta(cuentaPesos.getSucursalPaquete());
            }
        }

        // Datos TC
        datosPagoTC.setNroTarjeta(tarjeta.getNroTarjetaCredito());
        datosPagoTC.setTipoTarjeta(TipoCuenta.MASTERCARD.equals(tarjeta.getTipoCuentaEnum()) ? "41"
                : String.valueOf(tarjeta.getTipoCuentaEnum().getCodigo()));

        datosPagoTC.setCodigoTitularidad(getTitularidad(tarjeta.getCodigoTitularidad()));
        datosPagoTC.setNroCuentaTarjeta(tarjeta.getNroCuentaProducto());
        datosPagoTC.setPagoProgramado(false);
 
        // Tipo de cambio unificado
        datosPagoTC.setCotizacionComprador(datosTarjeta.getCotizacionComprador().toString());
        datosPagoTC.setCotizacionVendedor(datosTarjeta.getCotizacionVendedor().toString());
        datosPagoTC.setSaldoPendienteDolares(datosTarjeta.getSaldoPendienteDolares().toString());
        datosPagoTC.setSaldoPendientePesos(datosTarjeta.getSaldoPendientePesos().toString());

        return datosPagoTC;
    }

    /**
     * Retorna T o A segun corresponda (titular o adicional).
     *
     * @param codigoTitularidad the codigo titularidad
     * @return the titularidad
     */
    private String getTitularidad(String codigoTitularidad) {
        if (TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(codigoTitularidad)) {
            return "T";
        } else {
            return "A";
        }
    }

    /**
     * Retorna el codigo correspondiente a la moneda de pago.
     *
     * @param moneda the moneda
     * @return the moneda seleccionada
     */
    private String getMonedaSeleccionada(String moneda) {
        if ("Pesos".equals(moneda)) {
            return "0";
        } else if ("Dólares".equals(moneda)) {
            return "1";
        } else {
            return "2";
        }
    }

    /**
     * Verifica que nos encontremos en horario bancario.
     *
     * @return El mensaje si corresponde, caso contrario un string vacio.
     */
    public String horarioBancario() {

        DateTimeFormatter df = DateTimeFormat.forPattern(TIME_FORMAT_PATTERN);

        DateTime horarioBancarioFinal = df.parseLocalTime(horaHastaPTC).toDateTimeToday();
        DateTime horarioBancarioInicio = df.parseLocalTime(horaDesdePTC).toDateTimeToday().plusDays(1);

        DateTime horaActual = new DateTime();

        Interval intervaloFueraHorario = new Interval(horarioBancarioFinal, horarioBancarioInicio);

        if (intervaloFueraHorario.contains(horaActual)) {
            return mensajeBO.obtenerMensajePorCodigo(MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO).getMensaje();
        }
        return StringUtils.EMPTY;
    }

    /**
     * Inicializa el flujo de stopDebit. Previamente se ejecuta un servicio IATX que
     * busca debitos automaticos para luego ejecutar el de stopDebit propiamente
     * descripto.
     *
     * @param pagoTarjetaView the pago tarjeta view
     * @param cliente         the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<StopDebitOut> stopDebit(PagoTarjetaCreditoView pagoTarjetaView, Cliente cliente) {
        DatosStopDebit datos = new DatosStopDebit();
        StopDebitOut respuesta = new StopDebitOut();

        respuesta.setAdherirDebitoAutom(false);
        Cuenta tarjetaElegida = cliente.getTarjeta(pagoTarjetaView.getNumeroTarjeta());

        for (Cuenta cuenta : cliente.getCuentasParaEfectuarPago()) {
            try {
                List<DebitoAutomatico> listaDebitos = pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente,
                        cuenta);
                for (DebitoAutomatico debitoAutom : listaDebitos) {
                    String numeroPartidaDebitoFormateado = ISBANStringUtils
                            .eliminarCeros(debitoAutom.getNumeroPartida());
                    String numeroCuentaTarjetaFormateado = ISBANStringUtils
                            .eliminarCeros(tarjetaElegida.getNroCuentaProducto());
                    if (numeroPartidaDebitoFormateado.equals(numeroCuentaTarjetaFormateado)) {
                        datos.setCodigoServicio(debitoAutom.getCodigoServicio());
                        datos.setNroPartida(debitoAutom.getNumeroPartida());
                        datos.setNroCuenta(cuenta.getNroCuentaProducto());
                        datos.setSucursalCuenta(cuenta.getNroSucursal());
                        datos.setTipoCuenta(
                                cuenta.esCuentaUnica() ? debitoAutom.getTipoSubcuentaCU() : cuenta.getTipoCuenta());
                        respuesta = stopDebitTarjetasBO.realizarStopDebitTarjeta(cliente, datos);
                    }
                }
            } catch (DAOException e) {
                LOGGER.info(e.getMessage(), e);
                return respuestaFactory.crearRespuestaError(StopDebitOut.class, e.getMessage(), null);
            } catch (BusinessException e) {
                LOGGER.error("Error obteniendo deudas con debito automatico", e);
                return respuestaFactory.crearRespuestaError(StopDebitOut.class, e.getMessage(), null);
            }
        }

        return respuestaFactory.crearRespuestaOk(StopDebitOut.class, respuesta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<StopDebitConfirmacion> obtenerDatosStopDebit(String nroTarjeta, Cliente cliente) {
        try {
            Cuenta tarjetaElegida = cliente.getTarjeta(nroTarjeta);
            List<DatosTarjeta> datosTarjetas = pagosPendientesBO.getDatosTarjetas(cliente);
            DatosTarjeta datosTarj = null;

            for (DatosTarjeta datosTarjeta : datosTarjetas) {
                // compara contra los ultimos 4 digitos
                if (datosTarjeta.getNumeroTarjeta().equals(tarjetaElegida.getNroTarjetaCredito())) {
                    datosTarj = datosTarjeta;
                }
            }

            for (Cuenta cuenta : cliente.getCuentasParaEfectuarPago()) {
            	if (!tarjetaElegida.getTipoCuenta().equals("06")) {
	                List<DebitoAutomatico> listaDebitos = pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente,
	                        cuenta);
	
	                if (CollectionUtils.isNotEmpty(listaDebitos)) {
	                    for (DebitoAutomatico debitoAutom : listaDebitos) {
	                        if (debitoAutom.esProductoAdherido(tarjetaElegida.getNroCuentaProducto())) {
	                            StopDebitConfirmacion stopDebitConfirmacion = new StopDebitConfirmacion();
	
	                            String anexoMensaje;
	                            if (StringUtils.isNotBlank(tarjetaElegida.getAlias())) {
	                                String abreviaturaTarjeta = StringUtils
	                                        .upperCase(tarjetaElegida.getTipoCuentaEnum().getAbreviatura());
	                                String comillaDoble = "\"";
	                                anexoMensaje = abreviaturaTarjeta + " " + comillaDoble + tarjetaElegida.getAlias()
	                                        + comillaDoble;
	                            } else {
	                                anexoMensaje = nroTarjeta;
	                            }
	                            String mensaje = mensajeBO.obtenerMensajePorCodigo(MENSAJE_RELIZAR_STOP_DEBIT).getMensaje();
	                            mensaje = MessageFormat.format(mensaje, anexoMensaje);
	
	                            stopDebitConfirmacion.setMensaje(mensaje);
	                            stopDebitConfirmacion.setNroTarjeta(nroTarjeta);
	
	                            if (datosTarj.getFechaVencimientoLiquidacionTC() != null) {
	                                stopDebitConfirmacion.setFechaVencimiento(ISBANStringUtils.formatearFecha(
	                                        datosTarj.getFechaVencimientoLiquidacionTC(), ISBANStringUtils.FORMATO_FECHA));
	                                stopDebitConfirmacion.setFechaVencimientoMobile(
	                                        ISBANStringUtils.formatearFecha(datosTarj.getFechaVencimientoLiquidacionTC(),
	                                                ISBANStringUtils.FORMATO_FECHA_ANIO_CORTO));
	                            }
	                            stopDebitConfirmacion.setTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
	                            stopDebitConfirmacion.setAliasCuenta(cuenta.getAlias());
	
	                            stopDebitConfirmacion
	                                    .setNroCuenta(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
	                                            + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
	
	                            if ("02".equals(datosTarj.getFormaPagoTarjetaCredito())
	                                    || "04".equals(datosTarj.getFormaPagoTarjetaCredito())) {
	                                // pago minimo
	                                stopDebitConfirmacion.setTipoPago(PAGO_MINIMO);
	
	                            } else if ("03".equals(datosTarj.getFormaPagoTarjetaCredito())
	                                    || "05".equals(datosTarj.getFormaPagoTarjetaCredito())) {
	                                // pago total
	                                stopDebitConfirmacion.setTipoPago(PAGO_TOTAL);
	                            }
	
	                            stopDebitConfirmacion.setImporte(
	                                    obtenerImporteStopDebit(datosTarj, cliente, stopDebitConfirmacion.getTipoPago()));
	
	                            estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_STOP_DEBIT,
	                                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	                            return respuestaFactory.crearRespuestaOk(stopDebitConfirmacion);
	                        }
	                    }
	                }
            	} else {
            		StopDebitConfirmacion stopDebitConfirmacion = new StopDebitConfirmacion();
            		
                    String anexoMensaje;
                    if (StringUtils.isNotBlank(tarjetaElegida.getAlias())) {
                        String abreviaturaTarjeta = StringUtils
                                .upperCase(tarjetaElegida.getTipoCuentaEnum().getAbreviatura());
                        String comillaDoble = "\"";
                        anexoMensaje = abreviaturaTarjeta + " " + comillaDoble + tarjetaElegida.getAlias()
                                + comillaDoble;
                    } else {
                        anexoMensaje = nroTarjeta;
                    }
                    String mensaje = mensajeBO.obtenerMensajePorCodigo(MENSAJE_RELIZAR_STOP_DEBIT).getMensaje();
                    mensaje = MessageFormat.format(mensaje, anexoMensaje);

                    stopDebitConfirmacion.setMensaje(mensaje);
                    stopDebitConfirmacion.setNroTarjeta(nroTarjeta);

                    if (datosTarj.getFechaVencimientoLiquidacionTC() != null) {
                        stopDebitConfirmacion.setFechaVencimiento(ISBANStringUtils.formatearFecha(
                                datosTarj.getFechaVencimientoLiquidacionTC(), ISBANStringUtils.FORMATO_FECHA));
                        stopDebitConfirmacion.setFechaVencimientoMobile(
                                ISBANStringUtils.formatearFecha(datosTarj.getFechaVencimientoLiquidacionTC(),
                                        ISBANStringUtils.FORMATO_FECHA_ANIO_CORTO));
                    }
                    stopDebitConfirmacion.setTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
                    stopDebitConfirmacion.setAliasCuenta(cuenta.getAlias());

                    stopDebitConfirmacion
                            .setNroCuenta(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
                                    + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));

                    if ("02".equals(datosTarj.getFormaPagoTarjetaCredito())
                            || "04".equals(datosTarj.getFormaPagoTarjetaCredito())) {
                        // pago minimo
                        stopDebitConfirmacion.setTipoPago(PAGO_MINIMO);

                    } else if ("03".equals(datosTarj.getFormaPagoTarjetaCredito())
                            || "05".equals(datosTarj.getFormaPagoTarjetaCredito())) {
                        // pago total
                        stopDebitConfirmacion.setTipoPago(PAGO_TOTAL);
                    }

                    stopDebitConfirmacion.setImporte(
                            obtenerImporteStopDebit(datosTarj, cliente, stopDebitConfirmacion.getTipoPago()));

                    estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_STOP_DEBIT,
                            EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                    return respuestaFactory.crearRespuestaOk(stopDebitConfirmacion);
            	}
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
        estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_STOP_DEBIT,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "1198");
    }

    /**
     * obtiene el importe dependiendo de los consumos en pesos y dolares.
     *
     * @param datosTarj the datos tarj
     * @param cliente   the cliente
     * @param tipoPago  the tipo pago
     * @return the string
     */
    private String obtenerImporteStopDebit(DatosTarjeta datosTarj, Cliente cliente, String tipoPago) {
        CotizacionDTO cotizacionARS = null;
        String importe = "";

        if (PAGO_TOTAL.equals(tipoPago)) {
            if (datosTarj.getSaldoUltimoCierreDolaresTC() != null) {
                // tengo consumos en dolares
                try {
                    cotizacionARS = cotizacionBO.obtenerDatosCotizacion(cliente, datosTarj, PESO_COTI);
                } catch (BusinessException e) {
                    LOGGER.info(e.getMessage(), e);
                }

                importe = ISBANStringUtils.formatearSaldosConCerosYSignos(cotizacionARS.getImporteTotalConvertido());

            } else {
                // SACAR LA CHANCHADA cuando este bien analizado
                if (datosTarj.getSaldoUltimoCierrePesosTC() != null) {
                    importe = ISBANStringUtils
                            .agregadorPuntoDivisor(datosTarj.getSaldoUltimoCierrePesosTC().toString());
                }
            }
        } else {
            // SACAR LA CHANCHADA cuando este bien analizado
            if (datosTarj.getPagoMinimoPesosTC() != null) {
                importe = ISBANStringUtils.agregadorPuntoDivisor(datosTarj.getPagoMinimoPesosTC().toString());
            }
        }

        return importe;
    }

    /**
     * obtiene los datos de la pantalla de configuracion de la baja de adhesion de
     * tarjeta de credito.
     *
     * @param nroTarjeta the nro tarjeta
     * @param cliente    the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaAdhesionTarjeta(String nroTarjeta, Cliente cliente) {
        Respuesta<BajaAdhesionTarjConfirmacion> respuesta = new Respuesta<BajaAdhesionTarjConfirmacion>();

        String ultimosDigitos = null;
        
        try {
            Cuenta tarjetaElegida = cliente.getTarjeta(nroTarjeta);
            if (tarjetaElegida.getTipoCuenta().equals("06")) {
            	ultimosDigitos = nroTarjeta.substring(12);
			} else {
				ultimosDigitos = nroTarjeta.substring(10);
			}
            List<DatosTarjeta> datosTarjetas = pagosPendientesBO.getDatosTarjetas(cliente);
            DatosTarjeta datosTarj = null;

            for (DatosTarjeta datosTarjeta : datosTarjetas) {
                // compara contra los ultimos 4 digitos
                if (datosTarjeta.getNumeroTarjeta().contains(ultimosDigitos)) {
                    datosTarj = datosTarjeta;
                }
            }

            for (Cuenta cuenta : cliente.getCuentasParaEfectuarPago()) {
            	if (!tarjetaElegida.getTipoCuenta().equals("06")) {
	                List<DebitoAutomatico> listaDebitos = pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente,
	                        cuenta);
	
	                if (CollectionUtils.isNotEmpty(listaDebitos)) {
	                    for (DebitoAutomatico debitoAutom : listaDebitos) {
	                        if (debitoAutom.esProductoAdherido(tarjetaElegida.getNroCuentaProducto())) {
	                            BajaAdhesionTarjConfirmacion confirmacion = new BajaAdhesionTarjConfirmacion();
	                            String mensaje = mensajeBO.obtenerMensajePorCodigo(MENSAJE_RELIZAR_BAJA_ADHES_TARJ)
	                                    .getMensaje();
	                            String anexoMensaje;
	
	                            if (tarjetaElegida.getAlias() != null && !tarjetaElegida.getAlias().isEmpty()) {
	                                anexoMensaje = tarjetaElegida.getTipoCuentaEnum().name() + " \""
	                                        + tarjetaElegida.getAlias() + "\"";
	                            } else {
	                                anexoMensaje = nroTarjeta;
	                            }
	                            String msj = MessageFormat.format(mensaje, "<b>" + anexoMensaje + "</b>");
	
	                            confirmacion.setMensaje(msj);
	                            confirmacion.setNroTarjeta(nroTarjeta);
	                            confirmacion.setFechaVencimiento(ISBANStringUtils.formatearFecha(
	                                    datosTarj.getFechaVencimientoLiquidacionTC(), ISBANStringUtils.FORMATO_FECHA));
	                            confirmacion.setTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
	                            confirmacion.setAliasCuenta(cuenta.getAlias());
	                            if (datosTarj.getTipoCuentaTarjeta() != null) {
	                                confirmacion.setTipoTarjeta(datosTarj.getTipoCuentaTarjeta().getCodigo());
	                            }
	                            confirmacion.setNroCuenta(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
	                                    + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
	                            confirmacion.setNroCuentaProducto(cuenta.getNroCuentaProducto());
	
	                            if ("02".equals(datosTarj.getFormaPagoTarjetaCredito())
	                                    || "04".equals(datosTarj.getFormaPagoTarjetaCredito())) {
	                                // pago minimo
	                                confirmacion.setTipoPago(PAGO_MINIMO);
	
	                            } else if ("03".equals(datosTarj.getFormaPagoTarjetaCredito())
	                                    || "05".equals(datosTarj.getFormaPagoTarjetaCredito())) {
	                                // pago total
	                                confirmacion.setTipoPago(PAGO_TOTAL);
	                            }
	
	                            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
	                            respuesta.setRespuesta(confirmacion);
	
	                            estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_BAJA_ADHESION_TARJ,
	                                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	                            return respuesta;
	                        }
	                    }
	
	                }
            	} else {
            	     BajaAdhesionTarjConfirmacion confirmacion = new BajaAdhesionTarjConfirmacion();
                     String mensaje = mensajeBO.obtenerMensajePorCodigo(MENSAJE_RELIZAR_BAJA_ADHES_TARJ)
                             .getMensaje();
                     String anexoMensaje;

                     if (tarjetaElegida.getAlias() != null && !tarjetaElegida.getAlias().isEmpty()) {
                         anexoMensaje = tarjetaElegida.getTipoCuentaEnum().name() + " \""
                                 + tarjetaElegida.getAlias() + "\"";
                     } else {
                         anexoMensaje = nroTarjeta;
                     }
                     String msj = MessageFormat.format(mensaje, "<b>" + anexoMensaje + "</b>");

                     confirmacion.setMensaje(msj);
                     confirmacion.setNroTarjeta(nroTarjeta);
                     confirmacion.setFechaVencimiento(ISBANStringUtils.formatearFecha(
                             datosTarj.getFechaVencimientoLiquidacionTC(), ISBANStringUtils.FORMATO_FECHA));
                     confirmacion.setTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
                     confirmacion.setAliasCuenta(cuenta.getAlias());
                     if (datosTarj.getTipoCuentaTarjeta() != null) {
                         confirmacion.setTipoTarjeta(datosTarj.getTipoCuentaTarjeta().getCodigo());
                     }
                     confirmacion.setNroCuenta(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
                             + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
                     confirmacion.setNroCuentaProducto(cuenta.getNroCuentaProducto());

                     if ("02".equals(datosTarj.getFormaPagoTarjetaCredito())
                             || "04".equals(datosTarj.getFormaPagoTarjetaCredito())) {
                         // pago minimo
                         confirmacion.setTipoPago(PAGO_MINIMO);

                     } else if ("03".equals(datosTarj.getFormaPagoTarjetaCredito())
                             || "05".equals(datosTarj.getFormaPagoTarjetaCredito())) {
                         // pago total
                         confirmacion.setTipoPago(PAGO_TOTAL);
                     }

                     respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
                     respuesta.setRespuesta(confirmacion);

                     estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_BAJA_ADHESION_TARJ,
                             EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                     return respuesta;
            	}
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            item.setMensaje("Error al obtener los datos de Baja de Adhesion Tarjeta");
            respuesta.add(item);
        }

        return respuesta;
    }

    /**
     * Generar respuesta.
     *
     * @param stopDebitOut the stop debit out
     * @param comprobante  the comprobante
     * @return the respuesta
     */
    private Respuesta<ComprobantePagoTarjeta> generarRespuesta(StopDebitOut stopDebitOut,
                                                               ComprobantePagoTarjeta comprobante) {
        Respuesta<ComprobantePagoTarjeta> respuesta = new Respuesta<ComprobantePagoTarjeta>();
        respuesta.setRespuesta(comprobante);
        if (stopDebitOut != null && StopDebitTarjetasDAO.ESTADO_OK.equals(stopDebitOut.getResultado())) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.getRespuesta().setNroComprobanteStopDebit(stopDebitOut.getNroDeComprobante());
            seteoMensajeExitosoConStopDebit(respuesta);
            estadisticaManager.add(EstadisticasConstants.PAGO_TARJETA_CON_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (stopDebitOut != null
                && stopDebitOut.getErrorCode() == StopDebitTarjetasDAO.CODIGO_ERROR_STOP_DEBIT_PEDIDO) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setTipoError(TipoError.ERROR_STOP_DEBIT_EXISTENTE_PAGO_TARJETA.getDescripcion());
            item.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(MENSAJE_ERROR_STOP_DEBIT_YA_SE_HIZO_OTRO_EN_EL_DIA).getMensaje());
            respuesta.add(item);
            estadisticaManager.add(EstadisticasConstants.PAGO_TARJETA_SIN_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (stopDebitOut != null
                && stopDebitOut.getErrorCode() == StopDebitTarjetasDAO.CODIGO_ERROR_STOP_DEBIT_PEDIDO2) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setTipoError(TipoError.ERROR_STOP_DEBIT_EXISTENTE_PAGO_TARJETA.getDescripcion());
            item.setMensaje(mensajeBO.obtenerMensajePorCodigo(MENSAJE_STOP_DEBIT_YA_SOLICITADO).getMensaje());
            respuesta.add(item);
        } else {
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setTipoError(TipoError.ERROR_STOP_DEBIT_PAGO_TARJETA.getDescripcion());
            item.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ERROR_STOP_DEBIT_TIMEOUT)
                    .getMensaje());
            respuesta.add(item);
            estadisticaManager.add(EstadisticasConstants.PAGO_TARJETA_SIN_STOP_DEBIT,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        return respuesta;
    }

    /**
     * Llamar estadistica ultimo resumen nuevo pago.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<PagoTarjetaInfoView> llamarEstadisticaUltimoResumenNuevoPago() {
        estadisticaManager.add(EstadisticasConstants.CODIGO_ACCESO_PAGO_TARJETA_SANTANDER,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(PagoTarjetaInfoView.class, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * PagosTarjetaManager#detalleDePago(ar.com.santanderrio.obp.servicios.
     * tarjetas.view.TarjetaPagoSeleccionView)
     */
    @Override
    public Respuesta<DetalleTarjetaPagoView> detalleDePago(TarjetaPagoSeleccionView tarjetaPagoSeleccionView) {
        LOGGER.info("Validacion tipoDePago existente");
        TipoDePagoEnum tipoDePagoEnum = TipoDePagoEnum.getByString(tarjetaPagoSeleccionView.getTipoDePago());

        if (tipoDePagoEnum != null) {
            DateTime fechaDateTime = DateTime.parse(tarjetaPagoSeleccionView.getFechaVencimiento(),
                    DateTimeFormat.forPattern("dd/MM/yyyy"));
            try {
                DetalleTarjetaPago datosTarjetaPago = pagoTarjetaCreditoBO.obtenerDetalleTarjetaPago(
                        tarjetaPagoSeleccionView.getNumeroTarjeta(), fechaDateTime.toDate(), tipoDePagoEnum);
                LOGGER.info("Validacion información encontrada");
                if (datosTarjetaPago != null) {
                    return respuestaFactory.crearRespuestaOk(new DetalleTarjetaPagoView(datosTarjetaPago));
                } else {
                    LOGGER.info("No se encontro la informacion de detalle para: {}", tarjetaPagoSeleccionView);
                }
            } catch (BusinessException e) {
                LOGGER.debug("Error al obtener datos de tarjeta");
            }
        } else {
            LOGGER.debug("Tipo de pago inexistente");
        }
        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.VER_DETALLE_PAGO_TARJETA_ERROR_GENERICO);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.manager.
     * ComprobantesManager#descargarComprobantePDF()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobantePDF() {
        Respuesta<Reporte> reporte;
        Respuesta<ReporteView> respuestaView;
        reporte = reporteBO.obtenerComprobantePDF(sessionParametros.getPagoTarjetaView(),
                TipoOperacionComprobanteEnum.COMPROBANTE_PAGO_TARJETA, sesionCliente.getCliente().getCuentas(),
                sessionParametros.getComprobantePagoTarjeta());

        String estadistica = EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_TARJETA;
        if ("6".equals(sessionParametros.getPagoTarjetaView().getTipoTarjeta())) {
            estadistica = EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_TARJETA_MASTER;
        }
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
            estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
            estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
     * PagosTarjetaManager#estadisticaVisualizacionDetalleImportePago()
     */
    @Override
    public void estadisticaVisualizacionDetalleImportePago() {
        estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_VISUALIZACION_DETALLE_IMPORTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.web.manager.PagosTarjetaManager#
     * confirmarPagarTarjeta(ar.com.santanderrio.obp.servicios.pagos.entities.
     * PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<ComprobantePagoTarjeta> confirmarPagarTarjeta(PagoTarjetaCreditoView pagoTarjeta) {
        guardarHashEnSesion(pagoTarjeta);
        return null;
    }

    public Respuesta<EmisionOfertaIntegradaView> emitirPolizaOfertaIntegrada(EmisionOfertaIntegrada emisionOfertaIntegrada) {
        Cliente cliente = sesionCliente.getCliente();
        Cuenta tarjetaElegida = cliente.getTarjeta(emisionOfertaIntegrada.getNumeroTarjeta());


        if (tarjetaElegida == null) {
            LOGGER.info("La tarjeta no pertence a la cuenta");
            Respuesta<EmisionOfertaIntegradaView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    StringUtils.EMPTY, StringUtils.EMPTY);
            return respuesta;
        }

        Respuesta<EmisionOfertaIntegradaView> polizaEmitida = getEmisionOfertaIntegrada(cliente, emisionOfertaIntegrada,
                tarjetaElegida.getNroTarjetaCredito(), tarjetaElegida.getNroCuentaProducto(), tarjetaElegida);
        return polizaEmitida;
    }

    public Respuesta<CompraProtegidaView> consultarCompraProtegida(CompraProtegida compraProtegida) {

        Cliente cliente = sesionCliente.getCliente();
        Cuenta tarjetaElegida = cliente.getTarjeta(compraProtegida.getNumeroTarjeta());
        if (cliente.getSegmento() != null && cliente.getSegmento().isSelect()) {
            LOGGER.info("El cliente es Black o Platinum");
            Respuesta<CompraProtegidaView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    StringUtils.EMPTY, StringUtils.EMPTY);
            return respuesta;
        }
        if (tarjetaElegida == null) {
            LOGGER.info("La tarjeta no pertence a la cuenta");
            Respuesta<CompraProtegidaView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    StringUtils.EMPTY, StringUtils.EMPTY);
            return respuesta;
        }
        if(StringUtils.isEmpty(tarjetaElegida.getNroCuentaProducto()) || StringUtils.isEmpty(tarjetaElegida.getNroTarjetaCredito())) {
            LOGGER.info("Faltan datos de NroCuentaProducto o NroTarjetaDeCredito");
            Respuesta<CompraProtegidaView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    StringUtils.EMPTY, StringUtils.EMPTY);
            return respuesta;
        }
        Respuesta<FlagCompraProtegidaDTO> flagCompraProtegida = seguroTenenciasBO.flagCompraProtegida(cliente);
        if(flagCompraProtegida != null && flagCompraProtegida.getRespuesta() != null && !flagCompraProtegida.getRespuesta().getRespuesta()){
            Respuesta<CompraProtegidaView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    StringUtils.EMPTY, StringUtils.EMPTY);
            return respuesta;
        }

        Respuesta<PolizaDTO> polizasEnCurso = seguroTenenciasBO.consultarPoliza(flagCompraProtegida.getRespuesta().getTokenJwt(), cliente);
        if(polizasEnCurso != null && polizasEnCurso.getRespuesta() != null) {
            for(Poliza polizas : polizasEnCurso.getRespuesta().getPoliza()) {
                if(polizas.getTitulo().equalsIgnoreCase("COMPRA PROTEGIDA") && polizas.getEstadoPoliza() == 1){
                    LOGGER.info("El cliente ya tiene un seguro de Compra Protegida");
                    Respuesta<CompraProtegidaView> respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                            StringUtils.EMPTY, StringUtils.EMPTY);
                    return respuesta;
                }
            }
        }

        Respuesta<CompraProtegidaView> respuesta = new Respuesta<CompraProtegidaView>();
        Respuesta<CompraProtegidaDTO> compraProtegidaDTORespuesta = seguroTenenciasBO
                .consultarCompraProtegida(cliente, tarjetaElegida.getNroTarjetaCredito(), tarjetaElegida.getNroCuentaProducto(), compraProtegida.getTipoTarjeta(), flagCompraProtegida.getRespuesta().getTokenJwt());
        CompraProtegidaView compraProtegidaView = new CompraProtegidaView();
        compraProtegidaView = generateCompraProtegida(compraProtegidaDTORespuesta.getRespuesta(), compraProtegida);
        respuesta.setRespuesta(compraProtegidaView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;

    }

    private CompraProtegidaView generateCompraProtegida(CompraProtegidaDTO compraProtegidaDTO,
                                                        CompraProtegida compraProtegida) {


        Cliente cliente = sesionCliente.getCliente();
        CompraProtegidaView compraProtegidaView = new CompraProtegidaView();
        EmisionOfertaIntegrada emisionOfertaIntegrada = new EmisionOfertaIntegrada();
        String texto1 = "";
        String texto2 = "";
        emisionOfertaIntegrada.setCodigoRamo(compraProtegidaDTO.getDatosCotizacion().getCodigoRamo());
        emisionOfertaIntegrada.setTipoTarjeta(compraProtegida.getTipoTarjeta());
        emisionOfertaIntegrada.setCargoPEP(compraProtegidaDTO.getValoresDefault().getIndicadorPEP());
        emisionOfertaIntegrada.setCodigoRamo(compraProtegidaDTO.getDatosPlan().getCodigoRamo());
        emisionOfertaIntegrada.setNumeroCuenta(compraProtegida.getNumeroCuenta());
        emisionOfertaIntegrada.setOrigenFondos(compraProtegidaDTO.getValoresDefault().getOrigenFondos());
        emisionOfertaIntegrada.setNumeroTarjeta(compraProtegida.getNumeroTarjeta());
        emisionOfertaIntegrada.setCodigoPlan(compraProtegidaDTO.getDatosPlan().getCodigoPlan());
        emisionOfertaIntegrada.setCodigoProducto(compraProtegidaDTO.getDatosPlan().getCodigoProducto());
        if (compraProtegidaDTO != null && compraProtegidaDTO.getDatosCotizacion() != null) {
            compraProtegidaView.setCuota(ISBANStringUtils.formatearConComaYDosDecimales2(compraProtegidaDTO.getDatosCotizacion().getCuota().toString()));
            compraProtegidaView.setSumaAsegurada
                    (ISBANStringUtils.formatearConComaYDosDecimales2(compraProtegidaDTO.getDatosCotizacion().getSumaAsegurada().toString()));
            emisionOfertaIntegrada.setNumeroCotizacion(
                    Long.parseLong(compraProtegidaDTO.getDatosCotizacion().getNumeroCotizacion().toString()));
        }
        for (ItemsCoberturaDTO items : compraProtegidaDTO.getTextos()) {
            if (items.getCodTexto() != null
                    && items.getCodTexto().equalsIgnoreCase("1")) {
                compraProtegidaView.setMensajeSwitchOff(items.getTexto());
            }
            if (items.getCodTexto() != null
                    && items.getCodTexto().equalsIgnoreCase("2")) {
                texto1 = items.getTexto();
            }
            if (items.getCodTexto() != null
                    && items.getCodTexto().equalsIgnoreCase("3")) {
                texto2 = items.getTexto();
            }
        }

        if (compraProtegidaView.getMensajeSwitchOff() != null) {
            compraProtegidaView.setShowOfertaIntegrada(true);
            compraProtegidaView.setStopOfertaIntegrada(false);
            compraProtegidaView.setShowContrato(false);
            compraProtegidaView.setCompraProtegidaTitulo("Activá la protección para tus compras");
            compraProtegidaView.setContrato(texto1 + "\n" + texto2);
            compraProtegidaView.setEmisionOfertaIntegrada(emisionOfertaIntegrada);
            if(compraProtegidaDTO.getDatosAsegurado() != null && compraProtegidaDTO.getDatosAsegurado().getCodigoOcupacion() != null) {
                compraProtegidaView.setCodigoOcupacion(compraProtegidaDTO.getDatosAsegurado().getCodigoOcupacion());
            }
            if(compraProtegidaDTO.getValoresDefault() != null) {
                compraProtegidaView.setTipoPoliza(compraProtegidaDTO.getValoresDefault().getTipoPoliza() != null ? compraProtegidaDTO.getValoresDefault().getTipoPoliza() : "");
                compraProtegidaView.setIndicadorPEP(compraProtegidaDTO.getValoresDefault().getIndicadorPEP() != null ? compraProtegidaDTO.getValoresDefault().getIndicadorPEP() : "");
                compraProtegidaView.setOrigenFondos(compraProtegidaDTO.getValoresDefault().getOrigenFondos() != null ? compraProtegidaDTO.getValoresDefault().getOrigenFondos() : "");
            }
        } else {
            compraProtegidaView.setShowOfertaIntegrada(false);
        }

        return compraProtegidaView;
    }

    private Respuesta<EmisionOfertaIntegradaView> getEmisionOfertaIntegrada(Cliente cliente,
                                                                            EmisionOfertaIntegrada emisionOfertaIntegradaIn, String numeroTarjeta, String numeroCuenta, Cuenta tarjetaElegida) {
        Respuesta<EmisionOfertaIntegradaDTO> emisionOfertaIntegradaDTORespuesta = seguroTenenciasBO
                .emisionOfertaIntegrada(emisionOfertaIntegradaIn, cliente, numeroTarjeta, numeroCuenta, tarjetaElegida);
        Respuesta<EmisionOfertaIntegradaView> respuesta = new Respuesta<EmisionOfertaIntegradaView>();
        EmisionOfertaIntegradaView emisionOfertaIntegrada = new EmisionOfertaIntegradaView();
        emisionOfertaIntegrada = generateEmisionOfertaIntegradaView(emisionOfertaIntegradaDTORespuesta.getRespuesta());
        respuesta.setRespuesta(emisionOfertaIntegrada);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    private EmisionOfertaIntegradaView generateEmisionOfertaIntegradaView(
            EmisionOfertaIntegradaDTO emisionOfertaIntegradaDTO) {
        EmisionOfertaIntegradaView emisionOfertaIntegradaView = new EmisionOfertaIntegradaView();
        emisionOfertaIntegradaView.setCodigoRamo(emisionOfertaIntegradaDTO.getCodigoRamo());
        emisionOfertaIntegradaView.setFecha(emisionOfertaIntegradaDTO.getFecha());
        emisionOfertaIntegradaView.setHora(emisionOfertaIntegradaDTO.getHora());
        emisionOfertaIntegradaView.setNumeroCertificado(emisionOfertaIntegradaDTO.getNumeroCertificado());
        emisionOfertaIntegradaView.setNumeroMedioPago(emisionOfertaIntegradaDTO.getNumeroMedioPago());
        emisionOfertaIntegradaView.setNumeroPoliza(emisionOfertaIntegradaDTO.getNumeroPoliza());
        emisionOfertaIntegradaView.setTipoMedioPago(emisionOfertaIntegradaDTO.getTipoMedioPago());
        emisionOfertaIntegradaView.setVigenciaHasta(emisionOfertaIntegradaDTO.getVigenciaHasta());
        emisionOfertaIntegradaView.setVigenciaInicio(emisionOfertaIntegradaDTO.getVigenciaInicio());
        return emisionOfertaIntegradaView;
    }

    /**
     * Guardar hash en sesion.
     *
     * @param pagoTarjeta the pago tarjeta
     */
    private void guardarHashEnSesion(PagoTarjetaCreditoView pagoTarjeta) {
        String hashView = HashUtils.obtenerHash(crearMapaDePagoTarjetaCreditoView(pagoTarjeta));
        LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
        sessionParametros.setValidacionHash(hashView);
    }

    /**
     * Crear mapa de pago tarjeta credito view.
     *
     * @param entity the entity
     * @return the map
     */
    private Map<String, String> crearMapaDePagoTarjetaCreditoView(PagoTarjetaCreditoView entity) {
        LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
        Map<String, String> mapaAtributos = new HashMap<String, String>();
        mapaAtributos.put("cbuDolares", entity.getCbuDolares());
        mapaAtributos.put("cbuPesos", entity.getCbuPesos());
        mapaAtributos.put("fechaDePago", entity.getFechaDePago());
        mapaAtributos.put("importeMinimo", entity.getImporteMinimo());
        mapaAtributos.put("monedaSeleccionado", entity.getMonedaSeleccionado());
        mapaAtributos.put("numeroTarjeta", entity.getNumeroTarjeta());
        mapaAtributos.put("saldoAPagarConvertidoADolares", entity.getSaldoAPagarConvertidoADolares());
        mapaAtributos.put("saldoAPagarConvertidoAPesos", entity.getSaldoAPagarConvertidoAPesos());
        mapaAtributos.put("saldoDolaresTC", entity.getSaldoDolaresTC());
        mapaAtributos.put("saldoPesosTC", entity.getSaldoPesosTC());
        mapaAtributos.put("saldoSinSiguienteCierreDolares", entity.getSaldoSinSiguienteCierreDolares());
        mapaAtributos.put("saldoSinSiguienteCierrePesos", entity.getSaldoSinSiguienteCierrePesos());
        mapaAtributos.put("stopDebit", entity.getStopDebit());
        mapaAtributos.put("tienePagosProgramados", entity.getTienePagosProgramados());
        mapaAtributos.put("tipoPagoTC", entity.getTipoPagoTC());
        mapaAtributos.put("tipoTarjeta", entity.getTipoTarjeta());
        mapaAtributos.put("totalAPagarEnDolares", entity.getTotalAPagarEnDolares());
        mapaAtributos.put("totalAPagarEnPesos", entity.getTotalAPagarEnPesos());
        if (entity.getImportePagoDolares() != null) {
            mapaAtributos.put("importePagoPesos", entity.getImportePagoDolares().getMonto());
        }
        if (entity.getImportePagoPesos() != null) {
            mapaAtributos.put("importePagoDolares", entity.getImportePagoPesos().getMonto());
        }
        LOGGER.info("String mapa vista: " + mapaAtributos.toString());
        return mapaAtributos;
    }

    /**
     * Validar hash de la vista en sesion.
     *
     * @param view the view
     */
    private void validarHashDeLaVistaEnSesion(PagoTarjetaCreditoView view) {
        String hashViewInput = HashUtils.obtenerHash(crearMapaDePagoTarjetaCreditoView(view));
        HashUtils.compareHash(sessionParametros.getValidacionHash(), hashViewInput);
    }
}