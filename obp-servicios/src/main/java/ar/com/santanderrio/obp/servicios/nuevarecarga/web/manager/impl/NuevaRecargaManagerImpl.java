/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.impl;

import java.text.MessageFormat;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
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
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.nuevarecarga.bo.AgendaCelularBO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.CuentaOrigenRSADTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.entity.CompaniaCelularEnum;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.NuevaRecargaManager;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class NuevaRecargaManagerImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class NuevaRecargaManagerImpl implements NuevaRecargaManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NuevaRecargaManagerImpl.class);

    /** The valor desafio. */
    @Value("${TRJCOORD.OPERAINDISTINTO.NUEVOPAGO}")
    private String valorDesafio;

    /** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
    private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

    /** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
    private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

    /** The Constant MSJ_INFO_VALIDANDO_HASH. */
    private static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The nuevo pago BO. */
    @Autowired
    private NuevoPagoBO nuevoPagoBO;

    /** The autentificacion manager. */
    @Autowired
    private AutentificacionManager autentificacionManager;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The medios pago BO. */
    @Autowired
    private MediosPagoBO mediosPagoBO;

    /** The estadistica BO. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The reporte BO. */
    @Autowired
    private ReporteComprobantePDFBO reporteBO;
    
    @Autowired
    private AgendaCelularBO agendaCelularBO;
    
    @Autowired
    private NuevoPagoManager nuevoPagoManager;
    

    /**
     * Obtiene la confirmacion de la nueva recarga.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @return the respuesta
     */
    @Override
    public Respuesta<ConfirmacionRecargaView> obtenerConfirmacionNuevaRecarga(
            ConfirmacionRecargaView datosConfirmacionRecarga) {
        inicializarReintentos(datosConfirmacionRecarga);
        validarHashNuevoPago(datosConfirmacionRecarga);

        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuenta = cliente.getCuenta(datosConfirmacionRecarga.getCbuCuenta());
        Respuesta<ConfirmacionRecargaView> respuesta;
        if (cuenta == null) {
            LOGGER.error("No se encontro la cuenta buscada con el cbu {}.", datosConfirmacionRecarga.getCbuCuenta());
            return errorGenerico(datosConfirmacionRecarga);
        }
        
        if (!datosConfirmacionRecarga.getVieneDeAgenda()) {
	        LOGGER.info("Ejecutando validacion de RSA.");
	        respuesta = ejecutarValidacionRSA(datosConfirmacionRecarga);
	        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
	            return respuesta;
	        }
	        LOGGER.info("Validacion RSA Exitosa. Ejecutando la confirmacion de una nueva recarga..");
        }
        
        MedioPago medioPago = mediosPagoBO.obtenerMedioPagoPorCodigo(datosConfirmacionRecarga.getCodigoEmpresa());
        NuevaRecargaOutDTO nuevaRecargaOutDTO = null;
        try {
            nuevaRecargaOutDTO = nuevoPagoBO.pagarRecarga(cliente, cuenta,
                    new NuevaRecargaInDTO(datosConfirmacionRecarga, medioPago.getNombreFantasia(), cuenta));

            if (nuevaRecargaOutDTO.getRespuestaOK()) {
            	if ("RECARGA SUBE".equals(datosConfirmacionRecarga.getNombreEmpresa())) {
            		LOGGER.info("La recarga SUBE fue exitosa");
            		respuesta = recargaExitosaSube(datosConfirmacionRecarga, nuevaRecargaOutDTO);
            	} else {
            		LOGGER.info("La recarga de celular fue exitosa");
            		respuesta = recargaExitosa(datosConfirmacionRecarga, nuevaRecargaOutDTO);
            	}
                sesionParametros.setComprobanteRecarga(respuesta.getRespuesta());
            } else {
            	if (nuevaRecargaOutDTO.getTipoError().equals(TipoError.ERROR_SALDO_INSUFICIENTE)) {
            		String msjError = this.mensajeBO
        					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SALDO_INSUFICIENTE_RECARGA_CELULAR)
        					.getMensaje();
            		respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ConfirmacionRecargaView.class, msjError, TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());

            	} else if(nuevaRecargaOutDTO.getTipoError().equals(TipoError.ERROR_GENERICO)) {
            		String mensajeTexto = MessageFormat.format(nuevaRecargaOutDTO.getMensaje(), "Recarga",
                            medioPago.getNombreFantasia(), datosConfirmacionRecarga.obtenerMontoFeedback());
                    nuevaRecargaOutDTO.setMensaje(mensajeTexto);
                    respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(
                            ConfirmacionRecargaView.class, nuevaRecargaOutDTO.getMensaje(),
                            nuevaRecargaOutDTO.getTipoError().getDescripcion());
            	} else if (datosConfirmacionRecarga.getVieneDeAgenda()) {
                    return respuestaFactory.crearRespuestaError("", TipoError.ERROR_BANELCO,
                    		CodigoMensajeConstantes.ERROR_BANELCO);
            	} else {
            		String msjErrorBanelco = this.mensajeBO
        					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_BANELCO)
        					.getMensaje();
            		respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ConfirmacionRecargaView.class, msjErrorBanelco, TipoError.ERROR_BANELCO.name());
            	}
            }
        } catch (BusinessException e) {
            LOGGER.error("Error al tratar de efectuar un pago de recarga", e);
            respuesta = errorGenerico(datosConfirmacionRecarga);
        }

        if (reintentosAgotados(datosConfirmacionRecarga)) {
            respuesta.getItemsMensajeRespuesta().get(0)
                    .setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
        }

        LOGGER.info("Respuesta de la nueva recarga: {}.", respuesta);
        cargarEstadisticasPagoMisCuentas(datosConfirmacionRecarga, respuesta, nuevaRecargaOutDTO);
        respuesta.setRespuesta(datosConfirmacionRecarga);
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }
        
    /**
	 * Error generico.
	 *
	 * @param datosConfirmacionRecarga
	 *            the datos confirmacion recarga
	 * @return the respuesta
	 */
    private Respuesta<ConfirmacionRecargaView> errorGenerico(ConfirmacionRecargaView datosConfirmacionRecarga) {
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.RECARGA_PAGO_ERROR_GENERICO, "Recarga",
                datosConfirmacionRecarga.getNombreEmpresa(), datosConfirmacionRecarga.obtenerMontoFeedback());
    }

    /**
     * Graba la estadistica de ver comprobante de nueva recarga.
     *
     * @return true, if successful
     */
    @Override
    public boolean estadisticaVerComprobante() {
        return estadisticaManager.add(EstadisticasConstants.VISUALIZACION_COMPROBANTE_RECARGA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

	@Override
	public Respuesta<Void> agregarCelular(CelularView celular) {
		Cliente cliente = sesionCliente.getCliente();
		return agendaCelularBO.agregarCelular(cliente, celular.getNumero(), celular.getAlias(), CompaniaCelularEnum.buscarCompania(celular.getCompania()).getNombreEmpresa());
	}

	@Override
	public Respuesta<Void> actualizarAliasCelular(CelularView celular) {
		Cliente cliente = sesionCliente.getCliente();
		return agendaCelularBO.actualizarAliasCelular(cliente, celular.getNumero(), celular.getAlias());
	}

	@Override
	public Respuesta<Void> eliminarCelular(CelularView celular) {
		Cliente cliente = sesionCliente.getCliente();
		return agendaCelularBO.eliminarCelular(cliente, celular.getNumero());
	}

	@Override
	public Respuesta<List<CelularView>> obtenerCelulares() {
		Cliente cliente = sesionCliente.getCliente();
		return agendaCelularBO.consultarCelulares(cliente);
	}
	
	@Override
	public Respuesta<String> obtenerAlias(String numeroCelular){
		Cliente cliente = sesionCliente.getCliente();
		return agendaCelularBO.obtenerAlias(cliente, numeroCelular);
		
	}
    
    /**
     * Envia los datos a RSA y transfoma la respuesta de Respuesta
     * <AutentificacionDTO> a Respuesta <ConfirmacionRecargaView>.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @return the respuesta
     */
    private Respuesta<ConfirmacionRecargaView> ejecutarValidacionRSA(ConfirmacionRecargaView datosConfirmacionRecarga) {
        LOGGER.info("Se ejecuta la validacion por RCA");
        AutentificacionDTO autentificacionDTO;
  
        Respuesta<ConfirmacionRecargaView> estadoDesafio = autentificacionManager
                .verificarEstadoDesafio(datosConfirmacionRecarga.getDesafio(), Integer.parseInt(valorDesafio));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = datosConfirmacionRecarga.getDesafio();
        } else if ( Boolean.TRUE.equals(datosConfirmacionRecarga.getIsFromCalendario()) || EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = generarAutentificacionDTO(datosConfirmacionRecarga);
        } else {
            return estadoDesafio;
        }

        // Transformacion respuesta RSA
        if (Boolean.TRUE.equals(datosConfirmacionRecarga.getIsFromCalendario())) {
            autentificacionDTO.getRsaDTO().setIgnorarRSA(Boolean.TRUE);
        }
        Respuesta<AutentificacionDTO> rstaAutentificacionDTO = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        datosConfirmacionRecarga.setDesafio(rstaAutentificacionDTO.getRespuesta());

        Respuesta<ConfirmacionRecargaView> respuesta = respuestaFactory.transformar(datosConfirmacionRecarga,
                rstaAutentificacionDTO);

        if (datosConfirmacionRecarga.getIsFromCalendario()
                && EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        }

        return respuesta;
    }

    /**
     * Generar autentificacion DTO.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @return the autentificacion DTO
     */
    private AutentificacionDTO generarAutentificacionDTO(ConfirmacionRecargaView datosConfirmacionRecarga) {
        LOGGER.info("Se carga la informacion de autentificationDTO");
        Integer operacion = Integer.parseInt(valorDesafio);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        // Estadisticas
        autentificacionDTO.setCodigoEstadisticaSinDesafios(EstadisticasConstants.NUEVO_PAGO_VALIDACION_SIN_METODO_RSA);
        autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_NUEVA_RECARGA);
        autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_NUEVA_RECARGA);
        autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
                EstadisticasConstants.SOLICITUD_TARJETA_COORDENADAS_NUEVA_RECARGA);
        autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
                EstadisticasConstants.VALIDACION_TARJETA_COORDENADAS_NUEVA_RECARGA);
        autentificacionDTO
                .setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.SOLICITUD_OCHO_DIGITOS_NUEVA_RECARGA);
        autentificacionDTO
                .setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.VALIDACION_OCHO_DIGITOS_NUEVA_RECARGA);
        //

        // Datos informados a RSA
        NuevaRecargaRSADTO nuevaRecargaRSADTO = new NuevaRecargaRSADTO(operacion,
                obtenerMonto(datosConfirmacionRecarga),
                new CuentaOrigenRSADTO(obtenerSaldoCuentaOrigen(datosConfirmacionRecarga.getSaldoCuenta()),
                        datosConfirmacionRecarga.getNumeroCuenta(), obtenerNombreApellido(sesionCliente.getCliente())),
                datosConfirmacionRecarga.getNombreEmpresa(), Boolean.TRUE, datosConfirmacionRecarga.getIsFromCalendario());
        nuevaRecargaRSADTO.setCodigoPagoElectronico(datosConfirmacionRecarga.getIdentificacionCliente());
        
        nuevoPagoManager.cargarUltimosDiasDesdeCambioDeClaveToken(nuevaRecargaRSADTO);
        
        autentificacionDTO.setRsaDTO(nuevaRecargaRSADTO);
        return autentificacionDTO;
    }

    /**
     * Recarga exitosa.
     * Llama primeramente al procedimiento para verificar que exista, si el mismo existe ... lo agenda con otro procedimiento.
     * Si no se puede agendar, se avanza de todos modos. 
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @param medioPago
     *            the medio pago
     * @param nuevaRecargaOutDTO
     *            the nueva recarga out DTO
     * @return the respuesta
     */
    private Respuesta<ConfirmacionRecargaView> recargaExitosa(ConfirmacionRecargaView datosConfirmacionRecarga, NuevaRecargaOutDTO nuevaRecargaOutDTO) {
        datosConfirmacionRecarga.generarComprobante(nuevaRecargaOutDTO.getNroControl(), nuevaRecargaOutDTO.getNroComprobante());
        Cliente cliente = sesionCliente.getCliente();
        String numero = datosConfirmacionRecarga.getIdentificacionCliente().replaceAll(" ", "");
        String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.RECARGA_CELULAR_FEEDBACK_OK).getMensaje();
        
        Respuesta<Void> respuestaExiste = agendaCelularBO.existeCelular(cliente, numero);
        
        if (EstadoRespuesta.ERROR.equals(respuestaExiste.getEstadoRespuesta())) {
        	LOGGER.info("El celular {} ya existe, no se procedera a agendarlo", numero);
        	datosConfirmacionRecarga.setMensajeFeedback(mensaje);
        } else {
        	LOGGER.info("El celular {} existe. Se agenda", numero);
        	Respuesta<Void> respuestaAgendar = agregarCelular(new CelularView(numero, datosConfirmacionRecarga.getAliasCelular(), datosConfirmacionRecarga.getNombreEmpresa()));
        	if(!EstadoRespuesta.ERROR.equals(respuestaAgendar.getEstadoRespuesta())) {
				datosConfirmacionRecarga.setMensajeFeedback(mensaje + "Guardamos el número para que puedas recargarlo más rápido la próxima vez");
        	} else {
        		datosConfirmacionRecarga.setMensajeFeedback(mensaje);
        	}
        }

        return respuestaFactory.crearRespuestaOk(ConfirmacionRecargaView.class, datosConfirmacionRecarga);
    }
    
    private Respuesta<ConfirmacionRecargaView> recargaExitosaSube(ConfirmacionRecargaView datosConfirmacionRecarga, NuevaRecargaOutDTO nuevaRecargaOutDTO) {
        datosConfirmacionRecarga.generarComprobante(nuevaRecargaOutDTO.getNroControl(), nuevaRecargaOutDTO.getNroComprobante());
        String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.RECARGA_SUBE_OK).getMensaje();
		datosConfirmacionRecarga.setMensajeFeedback(mensaje);
        return respuestaFactory.crearRespuestaOk(ConfirmacionRecargaView.class, datosConfirmacionRecarga);
    }

    /**
     * Cargar estadisticas pago mis cuentas.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @param respuesta
     *            the respuesta
     * @param nuevaRecargaOutDTO
     *            the nueva recarga out DTO
     */
    private void cargarEstadisticasPagoMisCuentas(ConfirmacionRecargaView datosConfirmacionRecarga,
            Respuesta<ConfirmacionRecargaView> respuesta, NuevaRecargaOutDTO nuevaRecargaOutDTO) {
        estadisticaManager.add(EstadisticasConstants.NUEVA_RECARGA_PAGO_FEEDBACK,
                EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                        : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                nuevaRecargaOutDTO != null ? nuevaRecargaOutDTO.getNumeroCuenta() : null,
                        datosConfirmacionRecarga.getMontoId(), TipoMonedaInversionEnum.ARG.getSimbolo());
    }

    /**
     * Reintentos agotados.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @return the boolean
     */
    private Boolean reintentosAgotados(ConfirmacionRecargaView datosConfirmacionRecarga) {
        Boolean permiteReintentos = sesionParametros.getContador().permiteReintentar();
        datosConfirmacionRecarga.setReintentar(String.valueOf(permiteReintentos));
        return !permiteReintentos;
    }

    /**
     * Inicializar reintentos.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     */
    private void inicializarReintentos(ConfirmacionRecargaView datosConfirmacionRecarga) {
        if ("false".equals(datosConfirmacionRecarga.getReintentar())) {
        	// TODO CHECK!
            sesionParametros.setContador(new ContadorIntentos(2));
            datosConfirmacionRecarga.setReintentar("true");
        }
    }
    
//    private void inicializarReintentos() {
//        if (sesionParametros.getPrimerAcceso()) {
//            sesionParametros.setContador(new ContadorIntentos(1));
//        }
//    }

    /**
     * Obtiene el nombre y el apellido del cliente.
     *
     * @param cliente
     *            the cliente
     * @return the string
     */
    private String obtenerNombreApellido(Cliente cliente) {
        return (cliente.getNombre()).trim().concat(" ").concat((cliente.getApellido1()).trim());
    }

    /**
     * Obtiene el saldo en la cuenta origen.
     *
     * @param saldoCuentaOrigen
     *            the saldo cuenta origen
     * @return the long
     */
    private Long obtenerSaldoCuentaOrigen(String saldoCuentaOrigen) {
    	if (saldoCuentaOrigen.contains("$")) {
    		return Long.valueOf(StringUtils.remove(StringUtils.remove(StringUtils.remove(saldoCuentaOrigen, "."), ","), "$").trim());
    	}
        return Long.valueOf(StringUtils.remove(StringUtils.remove(saldoCuentaOrigen, ".").trim(), ","));
    }
    
    /**
     * Obtiene el monto a cargar.
     *
     * @param datosConfirmacionRecarga
     *            the datos confirmacion recarga
     * @return the long
     */
    private Long obtenerMonto(ConfirmacionRecargaView datosConfirmacionRecarga) {
    	String monto = datosConfirmacionRecarga.getMontoId();
    	int cantDecimals = monto.length() - monto.indexOf('.');
    	if(cantDecimals > monto.length()) {
    		return Long.valueOf(monto + StringUtils.repeat("0",2));
    	}
    	monto = monto.replace(".","");
    	return Long.valueOf(monto + StringUtils.repeat("0",3- cantDecimals));
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
        reporte = reporteBO.obtenerComprobantePDF(sesionParametros.getComprobanteRecarga());
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
            estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_PMC,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
            estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_PMC,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager#
     * estadisticaComprobanteNuevoPago()
     */
    @Override
    public Respuesta<Void> continuarPago(ConfirmacionRecargaView nuevoPago) {
        String hashView = HashUtils.obtenerHash(crearMapaDeLaVista(nuevoPago));
        LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
        sesionParametros.setValidacionHash(hashView);
        return new Respuesta<Void>();
    }

    /**
	 * Crear mapa de la vista.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 * @return the map
	 */
    private Map<String, Object> crearMapaDeLaVista(ConfirmacionRecargaView nuevoPago) {
        LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("numeroIdentificacion", nuevoPago.getIdentificacionCliente());
        mapaAtributos.put("numeroDeCuenta", nuevoPago.getNumeroCuenta());
        mapaAtributos.put("importe", nuevoPago.getMontoId());
        LOGGER.info("String mapa vista: " + mapaAtributos.toString());
        return mapaAtributos;
    }

    /**
	 * Validar hash nuevo pago.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 */
    private void validarHashNuevoPago(ConfirmacionRecargaView nuevoPago) {
        String inputHash = HashUtils.obtenerHash(crearMapaDeLaVista(nuevoPago));
        LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sesionParametros.getValidacionHash(), inputHash);
        HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
    }

}
