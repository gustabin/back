/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePagoComprasView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
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
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasRSADTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;
import ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasMultipleManager;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class PagoComprasManagerImpl.
 */
@Component
public class PagoComprasMultipleManagerImpl implements PagoComprasMultipleManager {
    /** The LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoComprasMultipleManagerImpl.class);

    /** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
    private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creand hash...";

    /** The Constant MSJ_INFO_VALIDANDO_HASH. */
    private static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";

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

    @Autowired
    private RsaManager rsaManager;

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

    @Override
    public void grabarEstadisticaPagoComprasMultiple() {
        estadisticaManager.add(EstadisticasConstants.PAGO_COMPRAS_MULTIPLE_CONFIGURACION,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    @Override
    public Respuesta<Boolean> continuarPagoComprasMultiple(PagoComprasMultiple pagoCompras) {
        estadisticaManager.add(EstadisticasConstants.PAGO_COMPRAS_MULTIPLE_CONFIRMACION,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        String hashView = HashUtils.obtenerHash(crearMapaView(pagoCompras));
        sesionParametros.setValidacionHash(hashView);
        return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
    }

    private void validarHashPagoCompras(PagoComprasMultiple pagoCompras) {
        String inputHash = HashUtils.obtenerHash(crearMapaView(pagoCompras));
        String sesionHash = sesionParametros.getValidacionHash();
        LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sesionHash, inputHash);
        HashUtils.compareHash(sesionHash, inputHash);
    }

    private Map<String, Object> crearMapaView(PagoComprasMultiple pagoCompras) {
        LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("empresa", pagoCompras.getPagoComprasId());
        mapaAtributos.put("numeroIdentificacion", pagoCompras.getCodigoPagoElectronico());
        String hashLista = "";
        for (DeudaPagoComprasView deuda : pagoCompras.getDeudas()) {
            mapaAtributos = new HashMap<String, Object>();
            mapaAtributos.put("numeroFactura", deuda.getNumeroFactura());
            mapaAtributos.put("importe", deuda.getMontoSinFormatear());
            mapaAtributos.put("numeroCuenta", deuda.getCuentaSeleccionada().getCbu());
            hashLista += HashUtils.obtenerHash(mapaAtributos);
        }
        mapaAtributos.put("hashLista", hashLista);
        LOGGER.info("String mapa vista: {}.", mapaAtributos);
        return mapaAtributos;
    }

    @Override
    public Respuesta<PagoComprasMultiple> ejecutarPagoComprasMultiple(PagoComprasMultiple pagoCompras) {
        validarHashPagoCompras(pagoCompras);
        inicializarReintentos(pagoCompras);

        Cliente cliente = sesionCliente.getCliente();
        MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(pagoCompras.getPagoComprasId());
        DivisaEnum divisa = pagoComprasBO.obtenerDivisa(medioPago);

        Respuesta<PagoComprasMultiple> respuesta = ejecutarValidacionRSA(cliente, pagoCompras, divisa);

        LOGGER.info("Ejecutando validacion de RSA con respuesta: {}.", respuesta);
        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            return respuesta;
        }

        try {
            PagoComprasOutDTO pagoPC = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago,
                    divisa);

            if (!pagoPC.getTieneError()) {
                respuesta = generarRespuestaExitosa(pagoCompras, pagoPC, divisa);
                guardarDetalleComprobante(respuesta.getRespuesta(), medioPago, divisa, cliente);
            } else {
                respuesta = generarRespuestaErrorEsperado(pagoPC);
            }
        } catch (BusinessException e) {
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT,
                    CodigoMensajeConstantes.PAGO_COMPRAS_MULTIPLE_TIMEOUT);
        }

        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && reintentosAgotados(pagoCompras)) {
            respuesta.getItemsMensajeRespuesta().get(0)
                    .setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
        }

        estadisticaManager.add(EstadisticasConstants.PAGO_COMPRAS_MULTIPLE_FEEDBACK,
                EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                        : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuesta;
    }

    private void inicializarReintentos(PagoComprasMultiple pagoCompras) {
        if (!pagoCompras.isReintentar()) {
            sesionParametros.setContador(new ContadorIntentos(2));
            pagoCompras.setReintentar(false);
        }
    }

    private Boolean reintentosAgotados(PagoComprasMultiple pagoCompras) {
        Boolean permiteReintentos = sesionParametros.getContador().permiteReintentar();
        pagoCompras.setReintentar(permiteReintentos);
        return !permiteReintentos;
    }

    private Respuesta<PagoComprasMultiple> ejecutarValidacionRSA(Cliente cliente, PagoComprasMultiple pagoCompras,
            DivisaEnum divisa) {
        AutentificacionDTO autentificacionDTO = null;

        Respuesta<PagoComprasMultiple> estadoDesafio = autentificacionManager
                .verificarEstadoDesafio(pagoCompras.getDesafio(), Integer.parseInt(valorDesafio));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = pagoCompras.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            AutentificacionDTO dto = new AutentificacionDTO();
            dto.setOperacion(Integer.parseInt(valorDesafio));
            TipoDesafioEnum desafioAplicable = autentificacionManager.obtenerDesafioHabilitadoOperacion(dto);
            for (DeudaPagoComprasView deuda : pagoCompras.getDeudas()) {
                Cuenta cuenta = cliente.getCuenta(deuda.getCuentaSeleccionada().getCbu());
                AutentificacionDTO autDTO = generarAutentificacionDTO(pagoCompras.getNombreEmpresa(),
                        deuda.getMontoSinFormatear().toString(), cuenta, divisa);
                Respuesta<ActionCode> respuestaRiesgo = rsaManager.analizar(autDTO.getRsaDTO(), desafioAplicable);
                autDTO.setCnsActionCodePrevioRsaAnalizar(respuestaRiesgo.getRespuesta());
                if (ActionCode.DENY.equals(respuestaRiesgo.getRespuesta())) {
                    autentificacionDTO = autDTO;
                    break;
                } else if (autentificacionDTO == null
                        || ActionCode.ALLOW.equals(autentificacionDTO.getCnsActionCodePrevioRsaAnalizar())) {
                    autentificacionDTO = autDTO;
                }
            }
        } else {
            return estadoDesafio;
        }

        Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        pagoCompras.setDesafio(rstaAutentificacion.getRespuesta());
        return respuestaFactory.transformar(pagoCompras, rstaAutentificacion);
    }

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

    private Respuesta<PagoComprasMultiple> generarRespuestaExitosa(PagoComprasMultiple pagoCompras,
            PagoComprasOutDTO pagoPC, DivisaEnum divisa) {
        String mensajeOK = this.mensajeBO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOS_OK_CABECERA_PAGO_MULTIPLE).getMensaje();
        pagoCompras.setMensajeFeedback(mensajeOK);
        pagoCompras.cargarComprobantes(pagoPC, divisa);
        return respuestaFactory.crearRespuestaOk(pagoCompras);
    }

    private Respuesta<PagoComprasMultiple> generarRespuestaErrorEsperado(PagoComprasOutDTO pagoPC) {
        if ("11262122".equals(pagoPC.getCodError())) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
                    CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_SALDO_INSUFICIENTE);
        } else if ("10009079".equals(pagoPC.getCodError())) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD);
        } else if ("10009080".equals(pagoPC.getCodError())) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA);
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.TODOS_ERRORES_CABECERA_PAGO_MULTIPLE);
        }
    }

    @Override
    public Respuesta<ReporteView> descargarComprobantePDF(DeudaPagoComprasView deuda) {
        DetalleComprobanteView comprobante = sesionParametros.getDetalleComprobanteViewPorId(deuda.getNumeroFactura());
        Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(comprobante);
        Respuesta<ReporteView> respuestaView;
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
            estadisticaManager.add(EstadisticasConstants.PAGO_COMPRAS_MULTIPLE_DESCARGA_COMPROBANTE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_COMPROBANTE,
                    CodigoMensajeConstantes.PAGO_COMPRAS_DESCARGA_COMPROBANTE_ERROR);
            estadisticaManager.add(EstadisticasConstants.PAGO_COMPRAS_MULTIPLE_DESCARGA_COMPROBANTE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    private void guardarDetalleComprobante(PagoComprasMultiple pagoCompras, MedioPago medioPago, DivisaEnum divisa,
            Cliente cliente) {
        sesionParametros.setDetalleComprobanteView(null);
        List<DetalleComprobanteView> comprobantes = new ArrayList<DetalleComprobanteView>();
        for (DeudaPagoComprasView deuda : pagoCompras.getDeudas()) {
            try {
                comprobantes.add(new DetalleComprobantePagoComprasView(pagoCompras, deuda, medioPago,
                        cliente.getCuenta(deuda.getCuentaSeleccionada().getCbu()), divisa));
            } catch (RuntimeException e) {
                LOGGER.error("Error al generar detalle para el comprobante PDF.", e);
            }
        }
        sesionParametros.setListaDetalleComprobanteView(comprobantes);
    }

    @Override
    public Respuesta<Boolean> grabarEstadisticaComprobantePagoCompras() {
        return respuestaFactory.crearRespuestaOk(estadisticaManager.add(
                EstadisticasConstants.PAGO_COMPRAS_MULTIPLE_COMPROBANTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK));
    }
}