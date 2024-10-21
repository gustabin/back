/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePagoComprasView;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.CuentaOrigenRSADTO;
import ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasRSADTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasManager;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class PagoComprasManagerImpl.
 */
@Component
public class PagoComprasManagerImpl implements PagoComprasManager {
    /** The LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoComprasManagerImpl.class);

    /** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
    private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creand hash...";

    /** The Constant MSJ_DEUDAS_PENDIENTES. */
    private static final String MSJ_DEUDAS_PENDIENTES = "Obtengo las deudas pendientes del cliente";

    /** The Constant MSJ_ERROR_SERVICIO_DEUDAS. */
    private static final String MSJ_ERROR_SERVICIO_DEUDAS = "Error del servicio para obtener deudas.";

    /** The Constant MSJ_CUENTAS_CLIENTE. */
    private static final String MSJ_CUENTAS_CLIENTE = "Obtengo las cuentas del cliente";

    /** The Constant MSJ_INFO_VALIDANDO_HASH. */
    private static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";

    /** The Constant PAGO. */
    private static final String PAGO = "Pago";

    /** The Constant COD_MSJ_ERROR_SERVICIO. */
    private static final String COD_MSJ_ERROR_SERVICIO = "1268";

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The medios pago BO. */
    @Autowired
    private MediosPagoBO mediosPagoBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The mensaje DAO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The pago compras BO. */
    @Autowired
    private PagoComprasBO pagoComprasBO;

    /** The autentificacion manager. */
    @Autowired
    private AutentificacionManager autentificacionManager;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The reporte BO. */
    @Autowired
    private ReporteComprobantePDFBO reporteBO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The valor desafio. */
    @Value("${TRJCOORD.OPERAINDISTINTO.PAGOCOMPRAS}")
    private String valorDesafio;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasManager#
     * obtenerDeudas(ar.com.santanderrio.obp.servicios.pagos.entities.
     * MedioPagoView)
     */
    @Override
    public Respuesta<MedioPagoSelectionView> obtenerDeudas(MedioPagoView medioPagoView) {
        LOGGER.info(MSJ_DEUDAS_PENDIENTES);
        Cliente cliente = sesionCliente.getCliente();
        MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(medioPagoView.getPagoComprasId());
        List<DeudaPagoComprasEntity> deudas = null;
        try {
            deudas = pagoComprasBO.obtenerDeudas(cliente, medioPago, medioPagoView.getCodigoPagoElectronico());
        } catch (BusinessException e) {
            LOGGER.error(MSJ_ERROR_SERVICIO_DEUDAS);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
                    CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
        }
        if (CollectionUtils.isEmpty(deudas)) {
            return respuestaFactory.crearRespuestaError("", TipoError.NO_EXISTE_DEUDA,
                    CodigoMensajeConstantes.PAGO_COMPRAS_NO_EXISTE_DEUDA);
        }
        MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
        medioPagoSelectionView.setListaFacturasAView(deudas, pagoComprasBO.obtenerDivisa(medioPago));
        medioPagoSelectionView.setTextoSeleccionMultiplesFacturas(
                mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_COMPRAS_MULTIPLE_DEUDA).getMensaje());
        medioPagoSelectionView.setAvisoCantidadMaximaFacturas(
        		mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_COMPRAS_MULTIPLE_DEUDA_AVISO).getMensaje());
        return respuestaFactory.crearRespuestaOk(medioPagoSelectionView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasManager#
     * obtenerConfiguracion(ar.com.santanderrio.obp.servicios.pagos.entities.
     * MedioPagoView)
     */
    @Override
    public Respuesta<MedioPagoSelectionView> obtenerConfiguracion(MedioPagoView medioPagoView) {
        LOGGER.info(MSJ_CUENTAS_CLIENTE);
        Cliente cliente = sesionCliente.getCliente();
        MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(medioPagoView.getPagoComprasId());
        DivisaEnum divisa = pagoComprasBO.obtenerDivisa(medioPago);
        List<Cuenta> cuentas = null;
        try {
            cuentas = pagoComprasBO.obtenerCuentas(cliente, divisa);
        } catch (BusinessException e) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
        }
        if (CollectionUtils.isEmpty(cuentas)) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIN_CUENTAS_DEBITO,
                    CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);
        }
        MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
        medioPagoSelectionView.setListaCuentasAView(cuentas);
        medioPagoSelectionView.setMoneda(divisa.getMoneda());
        return respuestaFactory.crearRespuestaOk(medioPagoSelectionView);
    }

    /**
     * Continuar pago de compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    @Override
    public Respuesta<Boolean> continuarPagoCompras(NuevoPago pagoCompras) {
        String hashView = HashUtils.obtenerHash(crearMapaView(pagoCompras));
        sesionParametros.setValidacionHash(hashView);
        return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
    }

    /**
     * Crear mapa view.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the map
     */
    private Map<String, Object> crearMapaView(NuevoPago pagoCompras) {
        LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("empresa", pagoCompras.getNombreEmpresa());
        mapaAtributos.put("fechaVencimiento", pagoCompras.getFechaVencimiento());
        mapaAtributos.put("numeroIdentificacion", pagoCompras.getCodigoPagoElectronico());
        mapaAtributos.put("importe", pagoCompras.getMonto());
        mapaAtributos.put("numeroCuenta", pagoCompras.getCuentaSeleccionada());
        LOGGER.info("String mapa vista: {}.", mapaAtributos);
        return mapaAtributos;
    }

    /**
     * Ejecutar pago de compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    @Override
    public Respuesta<NuevoPago> ejecutarPagoCompras(NuevoPago pagoCompras) {
        validarHashPagoCompras(pagoCompras);
        inicializarReintentos(pagoCompras);
        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuenta = cliente.getCuenta(pagoCompras.getCuentaSeleccionada());
        MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(pagoCompras.getPagoComprasId());
        DivisaEnum divisa = pagoComprasBO.obtenerDivisa(medioPago);

        Respuesta<NuevoPago> respuesta = ejecutarValidacionRSA(pagoCompras, cuenta,
                divisa);
        LOGGER.info("Ejecutando validacion de RSA con respuesta: {}.", respuesta);
        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            return respuesta;
        }

        PagoComprasOutDTO pagoPC = null;
        try {
            if (StringUtils.isBlank(pagoCompras.getFechaVencimiento())) {
                PagoComprasInDTO inDTO = new PagoComprasInDTO(cuenta, medioPago, pagoCompras,
                        sesionParametros.getRegistroSession().getPid(), divisa);
                pagoPC = pagoComprasBO.ejecutarPagoComprasSinDeuda(cliente, inDTO);
            } else {
                pagoPC = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta, divisa);
            }
        } catch (BusinessException e) {
            estadisticaManager.add(EstadisticasConstants.FEEDBACK_PAGO_COMPRAS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT, COD_MSJ_ERROR_SERVICIO, PAGO,
                    medioPago.getNombreFantasia(),
                    divisa.getSimbolo() + ISBANStringUtils.formatearSaldo(new BigDecimal(pagoCompras.getMonto())));
        }

        if (!pagoPC.getTieneError()) {
            respuesta = generarRespuestaExitosa(pagoCompras, pagoPC, medioPago, divisa);
            guardarDetalleComprobante(respuesta.getRespuesta(), medioPago, cuenta, divisa);
        } else {
            respuesta = generarRespuestaErrorEsperado(pagoPC, pagoCompras, medioPago, divisa);
        }

        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && reintentosAgotados(pagoCompras)) {
            respuesta.getItemsMensajeRespuesta().get(0)
                    .setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
        }

        estadisticaManager.add(EstadisticasConstants.FEEDBACK_PAGO_COMPRAS,
                EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                        : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

        return respuesta;
    }

    /**
     * Reintentos agotados.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the boolean
     */
    private Boolean reintentosAgotados(NuevoPago pagoCompras) {
        Boolean permiteReintentos = sesionParametros.getContador().permiteReintentar();
        pagoCompras.setReintentar(String.valueOf(permiteReintentos));
        return !permiteReintentos;
    }

    /**
     * Generar respuesta error esperado.
     *
     * @param pagoPC
     *            the pago PC
     * @param pagoCompras
     *            the pago compras
     * @param medioPago
     *            the medio pago
     * @param divisa
     *            the divisa
     * @return the respuesta
     */
    private Respuesta<NuevoPago> generarRespuestaErrorEsperado(PagoComprasOutDTO pagoPC, NuevoPago pagoCompras,
            MedioPago medioPago, DivisaEnum divisa) {
        switch (Integer.valueOf(pagoPC.getCodError())) {
        case 11262122:
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE, "1273");
        case 10740127:
            return respuestaFactory.crearRespuestaError("", TipoError.MONEDA_INCORRECTA, "1760");
        case 10009079:
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD);
        case 10009080:
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA);
        default:
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, COD_MSJ_ERROR_SERVICIO, PAGO,
                    medioPago.getNombreFantasia(),
                    divisa.getSimbolo() + ISBANStringUtils.formatearSaldo(new BigDecimal(pagoCompras.getMonto())));
        }
    }

    /**
     * Generar respuesta exitosa.
     *
     * @param pagoCompras
     *            the pago compras
     * @param pagoPC
     *            the pago PC
     * @param medioPago
     *            the medio pago
     * @param divisa
     *            the divisa
     * @return the respuesta
     */
    private Respuesta<NuevoPago> generarRespuestaExitosa(NuevoPago pagoCompras, PagoComprasOutDTO pagoPC,
            MedioPago medioPago, DivisaEnum divisa) {
        String mensajeOK = this.mensajeBO.obtenerMensajePorCodigo("1267").getMensaje();
        mensajeOK = MessageFormat.format(mensajeOK, PAGO, medioPago.getNombreFantasia(),
                divisa.getSimbolo() + ISBANStringUtils.formatearSaldo(new BigDecimal(pagoCompras.getMonto())));

        pagoCompras.cargarComprobantePagoCompras(pagoPC);
        pagoCompras.setMensajeFeedback(mensajeOK);
        if (StringUtils.isBlank(pagoCompras.getFechaVencimiento())) {
            pagoCompras.setNroBoleta(pagoPC.getNumeroBoleta());
        } else {
            pagoCompras.setFacturaNumero(pagoPC.getNumeroOrden());
        }

        return respuestaFactory.crearRespuestaOk(pagoCompras);
    }

    /**
     * Inicializar reintentos.
     *
     * @param pagoCompras
     *            the pago compras
     */
    private void inicializarReintentos(NuevoPago pagoCompras) {
        if (!"true".equals(pagoCompras.getReintentar())) {
            sesionParametros.setContador(new ContadorIntentos(3));
            pagoCompras.setReintentar("true");
        }
    }

    /**
     * Validar hash pago compras.
     *
     * @param pagoCompras
     *            the pago compras
     */
    private void validarHashPagoCompras(NuevoPago pagoCompras) {
        String inputHash = HashUtils.obtenerHash(crearMapaView(pagoCompras));
        String sesionHash = sesionParametros.getValidacionHash();
        LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sesionHash, inputHash);
        HashUtils.compareHash(sesionHash, inputHash);
    }

    /**
     * Ejecutar validacion RSA.
     *
     * @param pagoCompras
     *            the pago compras
     * @param nombreEmpresa
     *            the nombre empresa
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     * @return the respuesta
     */
    private Respuesta<NuevoPago> ejecutarValidacionRSA(NuevoPago pagoCompras, Cuenta cuenta,
            DivisaEnum divisa) {
        AutentificacionDTO autentificacionDTO;

        Respuesta<NuevoPago> estadoDesafio = autentificacionManager.verificarEstadoDesafio(pagoCompras.getDesafio(),
                Integer.parseInt(valorDesafio));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = pagoCompras.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = generarAutentificacionDTO(pagoCompras.getNombreEmpresa(), pagoCompras.getMonto(),
                    cuenta, divisa);
        } else {
            return estadoDesafio;
        }

        Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        pagoCompras.setDesafio(rstaAutentificacion.getRespuesta());

        return respuestaFactory.transformar(pagoCompras, rstaAutentificacion);
    }

    /**
     * Generar autentificacion DTO.
     *
     * @param pagoCompras
     *            the pago compras
     * @param nombreFantasia
     *            the nombre fantasia
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     * @return the autentificacion DTO
     */
    private AutentificacionDTO generarAutentificacionDTO(String nombreFantasia, String monto, Cuenta cuenta,
            DivisaEnum divisa) {
        Integer operacion = Integer.parseInt(valorDesafio);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        // Carga de estadisticas
        autentificacionDTO.setCodigoEstadisticaSinDesafios(EstadisticasConstants.SIN_DESAFIOS_PAGO_COMPRAS);
        autentificacionDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLICITUD_COORDENADAS_PAGO_COMPRAS);
        autentificacionDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_PAGO_COMPRAS);
        autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_PAGO_COMPRAS);
        autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_PAGO_COMPRAS);
        autentificacionDTO
                .setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.SOLICITUD_OCHO_DIGITOS_PAGO_COMPRAS);
        autentificacionDTO
                .setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.VALIDACION_OCHO_DIGITOS_PAGO_COMPRAS);

        // Datos informados a RSA
        autentificacionDTO.setRsaDTO(new PagoComprasRSADTO(operacion,
                StringUtils.isNotBlank(monto) ? new BigDecimal(monto).movePointRight(2).longValue() : null,
                new CuentaOrigenRSADTO(cuenta, divisa), nombreFantasia, Boolean.TRUE, divisa));

        return autentificacionDTO;
    }

    /**
     * Grabar estadistica comprobante pago compras.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Boolean> grabarEstadisticaComprobantePagoCompras() {
        return respuestaFactory.crearRespuestaOk(Boolean.class, estadisticaManager
                .add(EstadisticasConstants.COMPROBANTE_PAGO_COMPRAS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasManager#
     * descargarComprobantePDF()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobantePDF() {
        Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(sesionParametros.getDetalleComprobanteView());
        Respuesta<ReporteView> respuestaView;
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
            estadisticaManager.add("13726", EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_COMPROBANTE,
                    CodigoMensajeConstantes.PAGO_COMPRAS_DESCARGA_COMPROBANTE_ERROR);
            estadisticaManager.add("13726", EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }
    
    /**
     * Guardar detalle comprobante pago compras view.
     *
     * @param nuevoPagoView
     *            the nuevo pago view
     * @param medioPago
     *            the medio pago
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     */
    private void guardarDetalleComprobante(NuevoPago nuevoPagoView, MedioPago medioPago, Cuenta cuenta,
            DivisaEnum divisa) {
        sesionParametros.setListaDetalleComprobanteView(null);
        try {
            DetalleComprobantePagoComprasView detalleView = new DetalleComprobantePagoComprasView(nuevoPagoView, medioPago, cuenta, divisa);
            sesionParametros.setDetalleComprobanteView(detalleView);
        } catch (RuntimeException e) {
            LOGGER.error("Error al generar detalle para el comprobante PDF.", e);
            sesionParametros.setDetalleComprobanteView(null);
        }
    }   
}