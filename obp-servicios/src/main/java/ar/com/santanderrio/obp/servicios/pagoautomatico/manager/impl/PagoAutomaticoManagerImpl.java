/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
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
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.bo.DebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.NuevoPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.bo.PagoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.pagoautomatico.manager.PagoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnCuentaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.terminoscondiciones.bo.TerminosCondicionesBO;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO;
import ar.com.santanderrio.obp.servicios.seguros.dto.*;

/**
 * The Class NuevoPagoAutomaticoManagerImpl.
 */
@Component
public class PagoAutomaticoManagerImpl implements PagoAutomaticoManager {

    /** Logger del manager. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoAutomaticoManagerImpl.class);

    /** The terminos condiciones BO. */
    @Autowired
    private TerminosCondicionesBO terminosCondicionesBO;

    /** The nuevo pago automatico BO. */
    @Autowired
    private PagoAutomaticoBO pagoAutomaticoBO;

    /** The debito automatico BO. */
    @Autowired
    private DebitoAutomaticoBO debitoAutomaticoBO;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The session parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The medios pago bo. */
    @Autowired
    private MediosPagoBO mediosPagoBO;
    
    @Autowired
    private SeguroTenenciasBO seguroTenenciasBO;

    /** The autentificacion manager. */
    @Autowired
    private AutentificacionManager autentificacionManager;

    /** The Constant ERROR_CUENTAS. */
    private static final String ERROR_CUENTAS = "ERROR_CUENTAS";

    /** The valor desafio. */
    @Value("${TRJCOORD.OPERAINDISTINTO.NUEVOPAGO}")
    private String valorDesafio;

    /** The mensaje DAO. */
    @Autowired
    private MensajeBO mensajeBO;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<TerminosCondiciones> cargarTerminosCondiciones() {

        try {
            return terminosCondicionesBO.cargarTerminosCondiciones();
        } catch (BusinessException e) {
            LOGGER.error("Error al recuperar el archivo de terminos", e);
            return respuestaFactory.crearRespuestaWarning(TerminosCondiciones.class,
                    "Error al recuperar el archivo de terminos", null);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Respuesta<MedioPagoSelectionView> obtenerCuentas(Cliente cliente) {
        LOGGER.info("Entro al manager para obtener cuentas debito");

        List<Cuenta> listaCuentas;
        try {
            listaCuentas = cuentaBO.obtenerCuentasDebito(cliente);

            MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
            medioPagoSelectionView.setListaCuentasAView(listaCuentas);

            if (medioPagoSelectionView.getCantidadCuentas() > 0) {
                sesionParametros.setContador(null);
                return respuestaFactory.crearRespuestaOk(medioPagoSelectionView);
            }
        } catch (BusinessException e) {
            LOGGER.error("Error al obtener las cuentas para el cliente {}.", cliente.getNup());
        }

        return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(ERROR_CUENTAS,
                CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagoautomatico.manager.
     * PagoAutomaticoManager#confirmarAdhesionPagoAutomatico(ar.com.santanderrio
     * .obp .servicios.pagoautomatico.entities.AdhesionPagoAutomatico)
     */
    @Override
    public Respuesta<AdhesionPagoAutomatico> confirmarAdhesionPagoAutomatico(
            AdhesionPagoAutomatico adhesionPagoAutomatico) {
        LOGGER.info("Entro al manager para confirmar la adhesion a pago automatico");
        estadisticaManager.add(EstadisticasConstants.TERMINOS_CONDICIONES_ACEPTADOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(adhesionPagoAutomatico.getFiid());
        adhesionPagoAutomatico.setNombreEmpresa(medioPago.getNombreFantasia());

        LOGGER.info("Ejecutando modulo de RSA");
        Respuesta<AdhesionPagoAutomatico> respuestaRSA = ejecutarValidacionRSA(adhesionPagoAutomatico);
        if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta())
                || (!adhesionPagoAutomatico.getIsFromCalendario()
                        && EstadoRespuesta.WARNING.equals(respuestaRSA.getEstadoRespuesta()))) {
            return respuestaRSA;
        }
        LOGGER.info("Validacion RSA Exitosa. Ejecutando la confirmacion de una adhesion a pago automatico..");

        // inicializo el contador de reintentos
        sesionParametros.inicializarContadorReintentos(2);

        adhesionPagoAutomatico.setFechaHora(FechaUtils.obtenerFechaYHoraActual());

        Respuesta<AdhesionPagoAutomatico> respuestaAdhesion = pagoAutomaticoBO
                .ejecutarAdhesionPagoAutomatico(sesionCliente.getCliente(), adhesionPagoAutomatico);

        if (EstadoRespuesta.OK.equals(respuestaAdhesion.getEstadoRespuesta())) {
            estadisticaManager.add(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        return respuestaAdhesion;
    }

    /**
     * Ejecutar validacion RSA.
     *
     * @param adhesionPagoAutomatico
     *            the adhesion pago automatico
     * @return the respuesta
     */
    private Respuesta<AdhesionPagoAutomatico> ejecutarValidacionRSA(AdhesionPagoAutomatico adhesionPagoAutomatico) {
        LOGGER.info("Se ejecuta la validacion por RCA");
        AutentificacionDTO autentificacionDTO;

        Respuesta<AdhesionPagoAutomatico> estadoDesafio = autentificacionManager
                .verificarEstadoDesafio(adhesionPagoAutomatico.getDesafio(), Integer.parseInt(valorDesafio));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = adhesionPagoAutomatico.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = generarAutentificacionDTO();
        } else {
            return estadoDesafio;
        }

        // Transformacion respuesta RSA
        Respuesta<AutentificacionDTO> rstaAutentificacionDTO = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        adhesionPagoAutomatico.setDesafio(rstaAutentificacionDTO.getRespuesta());
        return respuestaFactory.transformar(adhesionPagoAutomatico, rstaAutentificacionDTO);
    }

    /**
     * Generar autentificacion DTO.
     *
     * @return the autentificacion DTO
     */
    private AutentificacionDTO generarAutentificacionDTO() {
        LOGGER.info("Se carga la informacion de autentificationDTO");
        Integer operacion = Integer.parseInt(valorDesafio);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        // Datos informados a RSA
        autentificacionDTO.setRsaDTO(new NuevoPagoAutomatico());

        // carga de estadisticas
        autentificacionDTO
                .setCodigoEstadisticaSolicitudToken(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_SOLICITUD_TOKEN);
        autentificacionDTO
                .setCodigoEstadisticaValidacionToken(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_VALIDACION_TOKEN);
        autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
                EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_SOLICITUD_COORDENADAS);
        autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
                EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_VALIDACION_COORDENADAS);
        autentificacionDTO
                .setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_SOLICITUD_BANELCO);
        autentificacionDTO.setCodigoEstadisticaValidacionBanelco(
                EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_VALIDACION_BANELCO);
        autentificacionDTO.setCodigoEstadisticaSinDesafios(EstadisticasConstants.PAGO_AUTOMATICO_SIN_DESAFIO);
        return autentificacionDTO;
    }

    /**
     * Adhiere un servicio a Debito Automatico.
     *
     * @param registroSesion
     *            the registro sesion
     * @param adhesionDebitoAutomaticoView
     *            the adhesion debito automatico view
     * @return the respuesta
     */
    @Override
    public Respuesta<AdhesionDebitoAutomaticoEnCuentaView> adherirDebitoEnCuenta(RegistroSesion registroSesion,
            AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoView) {

        if (!"true".equals(adhesionDebitoAutomaticoView.getReintentar())) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }
       

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> respuestaValidacion = respuestaFactory
                .validate(adhesionDebitoAutomaticoView);
        if (EstadoRespuesta.WARNING.equals(respuestaValidacion.getEstadoRespuesta())) {
            return respuestaValidacion;
        }
        AdhesionDebitoAutomatico adhesionDebitoAutomatico = popularAdhesionDebitoAutomatico(
                adhesionDebitoAutomaticoView);
        try {
            Respuesta<AdhesionDebitoAutomatico> respuestaServicio = debitoAutomaticoBO
                    .adherir(sesionCliente.getCliente(), registroSesion, adhesionDebitoAutomatico);

            Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resMetodo = armarRespuestaSiOkUError(respuestaServicio,
                    adhesionDebitoAutomaticoView);
            if (resMetodo != null) {
                NuevoPago nuevoPagoView = null;
                // encaso exitoso, se guarda en session un pagoview para despues recuperarlo en
                // otro stack
                if (resMetodo.getRespuesta() != null) {
                    nuevoPagoView = generarNuevoPagoView(resMetodo.getRespuesta());
                }
                nuevoPagoView.setCodigoPagoElectronico(adhesionDebitoAutomaticoView.getCodigoPagoElectronico());
                sesionParametros.setNuevoPagoView(nuevoPagoView);
                return resMetodo;
            }
            return respuestaFactory.crearRespuestaError(AdhesionDebitoAutomaticoEnCuentaView.class,
                    respuestaServicio.getItemsMensajeRespuesta());

        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError(null, TipoError.NUEVO_PAGO_AUTOMATICO_TIME_OUT, "1283",
                    adhesionDebitoAutomatico.getNombreServicioEmpresa());
        }
    }

    /**
     * Armar respuesta si ok U error.
     *
     * @param respuestaServicio
     *            the respuesta servicio
     * @param adhesionDebitoAutomaticoView
     *            the adhesion debito automatico view
     * @return the respuesta
     */
    private Respuesta<AdhesionDebitoAutomaticoEnCuentaView> armarRespuestaSiOkUError(
            Respuesta<AdhesionDebitoAutomatico> respuestaServicio,
            AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoView) {

        if (respuestaServicio.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            estadisticaManager.add(EstadisticasConstants.ADHESION_DEBITO_AUTOMATICO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            BeanUtils.copyProperties(respuestaServicio.getRespuesta(), adhesionDebitoAutomaticoView);

            if(adhesionDebitoAutomaticoView != null && adhesionDebitoAutomaticoView.getEmisionGastosProtegido() != null) {

                Cliente cliente = sesionCliente.getCliente();
                Cuenta cuenta = cliente.getCuenta(adhesionDebitoAutomaticoView.getEmisionGastosProtegido().getCbu());
                if(cuenta == null){
                    LOGGER.info("El Cbu no pertence a la cuenta");
                    adhesionDebitoAutomaticoView.setNroPolizaGastosProtegido(null);
                }
                else {
                    Respuesta<EmisionOfertaIntegradaDTO> emitirGastosProtegidos = seguroTenenciasBO.emisionOfertaIntegradaGastoProtegido(adhesionDebitoAutomaticoView.getEmisionGastosProtegido(), cliente, cuenta.getNroSucursal(), cuenta.getTipoCuenta(), cuenta.getNroCuentaProducto(), adhesionDebitoAutomaticoView.getNombreEmpresa());

                    if (emitirGastosProtegidos != null && emitirGastosProtegidos.getRespuesta() != null && emitirGastosProtegidos.getRespuesta().getNumeroPoliza() != null) {
                        adhesionDebitoAutomaticoView.setNroPolizaGastosProtegido(emitirGastosProtegidos.getRespuesta().getNumeroPoliza().toString());
                    }
                }
            }

            return respuestaFactory.crearRespuestaOk(AdhesionDebitoAutomaticoEnCuentaView.class,
                    adhesionDebitoAutomaticoView);
        } else if (respuestaServicio.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            if (sesionParametros.getContador().permiteReintentar()) {
                Respuesta<AdhesionDebitoAutomaticoEnCuentaView> respuestaReintentar = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
                respuestaReintentar.setEstadoRespuesta(EstadoRespuesta.WARNING);
                ItemMensajeRespuesta item = new ItemMensajeRespuesta();
                item.setTipoError(TipoError.WARNING_REINTENTOS_OPERACION.getDescripcion());
                item.setMensaje(respuestaServicio.getItemsMensajeRespuesta().get(0).getMensaje());
                respuestaReintentar.add(item);
                return respuestaReintentar;
            } else {
                estadisticaManager.add(EstadisticasConstants.ADHESION_DEBITO_AUTOMATICO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }

        return null;
    }

    /**
     * Genero comprobante de la adhesion a debito automatico.
     *
     * @param comprobanteDebitoAutomatico
     *            the comprobante debito automatico
     * @return the respuesta
     */
    @Override
    public Respuesta<ReporteView> generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico) {
    	comprobanteDebitoAutomatico.setPesIdentificacion(sesionParametros.getNuevoPagoView().getCodigoPagoElectronico());
    	comprobanteDebitoAutomatico.setFechaHora(sesionParametros.getNuevoPagoView().getFechaHora());
        Respuesta<Reporte> reporteRespuesta = pagoAutomaticoBO.generarComprobanteAdhesion(comprobanteDebitoAutomatico);
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
     * Popular adhesion debito automatico.
     *
     * @param adhesionDebitoAutomaticoView
     *            the adhesion debito automatico view
     * @return the adhesion debito automatico
     */
    private AdhesionDebitoAutomatico popularAdhesionDebitoAutomatico(
            AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoView) {
        AdhesionDebitoAutomatico adhesionDebitoAutomatico = new AdhesionDebitoAutomatico();

        MedioPago medioPago;
        if (adhesionDebitoAutomaticoView.getFiid() != null) {
            medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(adhesionDebitoAutomaticoView.getFiid());
        } else {
            medioPago = mediosPagoBO
                    .obtenerPorNombreFantasiaPagoAutomatico(adhesionDebitoAutomaticoView.getNombreEmpresa());
        }

        adhesionDebitoAutomatico.setCuit(medioPago.getCuit());
        adhesionDebitoAutomatico.setNombreServicioEmpresa(medioPago.getServicio());
        adhesionDebitoAutomatico.setLimiteAdhesion(adhesionDebitoAutomaticoView.getImporteLimite());
        adhesionDebitoAutomatico.setNroPartidaServicioEmpresa(adhesionDebitoAutomaticoView.getCodigoPagoElectronico());
        adhesionDebitoAutomatico.setNombreFantasia(medioPago.getNombreFantasia());

        Cuenta cuenta = sesionCliente.getCliente().getCuenta(adhesionDebitoAutomaticoView.getCuentaSeleccionada());

        if (cuenta != null) {

            adhesionDebitoAutomatico.setearTipoCuentaDebito(cuenta.getTipoCuentaEnum().getAbreviatura());
            adhesionDebitoAutomatico.setSucursalCuentaDebito(cuenta.getNroSucursal());
            adhesionDebitoAutomatico.setNroCuentaProductoDebito(cuenta.getNroCuentaProducto());
            adhesionDebitoAutomatico.setNroOrdenFirmante(cuenta.getNroOrdenFirmante());
        }
        return adhesionDebitoAutomatico;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.manager.
     * NuevoPagoAutomaticoManager#estadisticaComprobanteNuevoPagoAutomatico()
     */
    @Override
    public void estadisticaComprobanteNuevoPagoAutomatico() {
        LOGGER.info("Grabo estadistica de comprobante para adhesion a pago automatico");
        estadisticaManager.add(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_VISUALIZACION_COMPROBANTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.manager.
     * NuevoPagoAutomaticoManager#obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(
     * java .lang.String)
     */
    @Override
    public Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(
            String nombreFantasia) {
    	
		//Manganeta temporal, borrar cuando el nombre del colegio aparezca bien en el medioDePago.txt
        if(nombreFantasia.equalsIgnoreCase("COL SAN ESTEBAN - CUIT 30672972961")){
        	nombreFantasia = "COL SAN ESTEBAN";
        }
        
        MedioPago empresa = mediosPagoBO.obtenerPorNombreFantasiaExactoTarjeta(nombreFantasia);
        Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> respuesta;

        if (empresa == null) {
            respuesta = respuestaFactory.crearRespuestaError(AdhesionDebitoAutomaticoEnTarjetaView.class,
                    "No se encontro la empresa", StringUtils.EMPTY);
        } else {
            List<Cuenta> cuentaTarjetas = new ArrayList<Cuenta>();
            if ("S".equals(empresa.getVisaHabilitado())) {
                cuentaTarjetas.addAll(sesionCliente.getCliente().getCuentasTarjetaDeCreditoVISA());
            }
            if ("S".equals(empresa.getAmexHabilitado())) {
                cuentaTarjetas.addAll(sesionCliente.getCliente().getCuentasTarjetaDeCreditoAMEX());
            }
            if (cuentaTarjetas.isEmpty()) {
                respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_TARJETAS_COMPATIBLES,
                        CodigoMensajeConstantes.ADHESION_DEBITO_TARJETA_NO_HAY_TARJETAS_COMPATIBLES);
            } else {
                AdhesionDebitoAutomaticoEnTarjetaView datosConfiguracion = new AdhesionDebitoAutomaticoEnTarjetaView(
                        empresa, cuentaTarjetas);
                datosConfiguracion.setMensajeInformacionPagoAdebitar(mensajeBO
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_PAGO_PENDIENTE_DE_CONFIRMACION ).getMensaje());
                respuesta = respuestaFactory.crearRespuestaOk(datosConfiguracion);
            }
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.manager.
     * NuevoPagoAutomaticoManager#generarHashSeguridad(ar.com.santanderrio.obp.
     * servicios.nuevopagoautomatico.view.HashDebitoAutomaticoTarjetaView)
     */
    @Override
    public Boolean generarHashSeguridad(HashDebitoAutomaticoTarjetaView datosParaHash) {

        sesionParametros.setValidacionHash(HashUtils.obtenerHash(datosParaHash));

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagoautomatico.manager.
     * PagoAutomaticoManager#validarAutenticacionPagoAutomatico()
     */
    @Override
    public Respuesta<Boolean> validarAutenticacionPagoAutomatico() {
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(Integer.parseInt(valorDesafio));
        return autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(autentificacionDTO);
    }

    @Override
    public Respuesta<ReporteView> generarComprobanteAdhesionPagoAutomatico(
            ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico) {

        Respuesta<Reporte> reporteRespuesta = pagoAutomaticoBO
                .generarComprobanteAdhesionPagoAutomatico(comprobanteAdhesionDebitoAutomatico);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }

        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
    }

    public NuevoPago generarNuevoPagoView(AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoEnCuentaView) {
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setNombreEmpresa(adhesionDebitoAutomaticoEnCuentaView.getNombreEmpresa());
        nuevoPago.setIdentificacion(adhesionDebitoAutomaticoEnCuentaView.getCodigoPagoElectronico());
        nuevoPago.setImporteLimite(adhesionDebitoAutomaticoEnCuentaView.getImporteLimite());
        nuevoPago.setNroDeComprobante(adhesionDebitoAutomaticoEnCuentaView.getNroDeComprobante());
        nuevoPago.setFiid(adhesionDebitoAutomaticoEnCuentaView.getFiid());
        nuevoPago.setCuentaSeleccionada(adhesionDebitoAutomaticoEnCuentaView.getCuentaSeleccionada());
        nuevoPago.setFechaHora(adhesionDebitoAutomaticoEnCuentaView.getFechaHora());
        nuevoPago.setNombreMedioDePago(adhesionDebitoAutomaticoEnCuentaView.getNombreMedioDePago());
        nuevoPago.setMedioPago(adhesionDebitoAutomaticoEnCuentaView.getMedioPago());
        nuevoPago.setFromAdhesionDebitoAutomatico(
                adhesionDebitoAutomaticoEnCuentaView.getisFromAdhesionDebitoAutomatico());
        return nuevoPago;
    }
}
