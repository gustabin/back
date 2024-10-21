/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.blanqueopin.dto.BlanqueoPinRSADTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.ExtraccionYComprasExteriorBO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorInDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorOutDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CuentaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.DatosExtraccionYComprasExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.TarjetaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.ExtraccionYComprasExteriorManager;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.utils.ExtraccionYComprasExteriorUtils;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.CuentaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.DatosTarjetasExtraccionYComprasExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.TarjetaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * ExtraccionYComprasExteriorManagerImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class ExtraccionYComprasExteriorManagerImpl implements ExtraccionYComprasExteriorManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtraccionYComprasExteriorManagerImpl.class);

    /** The ExtraccionYComprasExteriorBO BO. */
    @Autowired
    private ExtraccionYComprasExteriorBO extraccionYComprasExteriorBO;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The ion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;
    
	@Autowired
	private ClienteManager clienteManager;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<ModifTarjetaOperaExtraccionView> desafioOperacionRSA;

    /** The valor . */
    @Value("${TRJCOORD.OPERAINDISTINTO.EXTCOMPRASEXTERIOR:3}")
    private Integer valorDesafioExtraccionYComprasExterior;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.
     * ExtraccionYComprasExteriorManager#consultarCuentasOperacionExterior(ar.
     * com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.
     * TarjetaOperacionExteriorView)
     */
    @Override
    public Respuesta<List<CuentaOperacionExteriorView>> consultarCuentasOperacionExterior(
            TarjetaOperacionExteriorView tarjetaOperaExteriorView) {
        LOGGER.info("ExtraccionYComprasExteriorManagerImpl iniciando consulta de cuentas");
        String numeroTarjeta = "";
        TarjetaOperacionExteriorDTO tarjetaDTO = obtenerTarjetaDTO(tarjetaOperaExteriorView.getId());
        if (tarjetaDTO != null) {
            numeroTarjeta = tarjetaDTO.getNumeroTarjeta();
        } else {
            LOGGER.info(
                    "ExtraccionYComprasExteriorManagerImpl finalizando consulta de cuentas, no se encontro la tarjeta en sesion");
            return respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_CUENTAS,
                    CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CONSULTA_CUENTAS);

        }
        Respuesta<List<CuentaOperacionExteriorDTO>> respuestaBO = extraccionYComprasExteriorBO
                .consultarCuentasOperaExterior(numeroTarjeta);
        if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
            sesionParametros.getDatosExtraccionYComprasExterior()
                    .setCuentasOperacionExterior(respuestaBO.getRespuesta());
            List<CuentaOperacionExteriorView> cuentasView = generarCuentasView(respuestaBO.getRespuesta());
            LOGGER.info("ExtraccionYComprasExteriorManagerImpl finalizando consulta de cuentas Ok");
            return respuestaFactory.crearRespuestaOk(cuentasView);
        }
        LOGGER.info("ExtraccionYComprasExteriorManagerImpl finalizando consulta de cuentas Error");
        return respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_CUENTAS,
                CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CONSULTA_CUENTAS);
    }

    /**
     * Obiente la descripcion de la cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    private String obtenerDescripcionCuenta(String cuenta) {
        String moneda = ExtraccionYComprasExteriorUtils.getMonedaCta(cuenta);
        return ExtraccionYComprasExteriorUtils.getDescCta(moneda);
    }

    /**
     * Generacion de cuentas view.
     *
     * @param cuentasDTO
     *            the cuentas DTO
     * @return the list
     */
    private List<CuentaOperacionExteriorView> generarCuentasView(List<CuentaOperacionExteriorDTO> cuentasDTO) {
        List<CuentaOperacionExteriorView> cuentasView = new ArrayList<CuentaOperacionExteriorView>();
        for (CuentaOperacionExteriorDTO cuentaDTO : cuentasDTO) {
            CuentaOperacionExteriorView cuentaView = new CuentaOperacionExteriorView();
            String nroCuenta = ExtraccionYComprasExteriorUtils.getNroCta(cuentaDTO.getCuentaRelacionada());
            String descCta = obtenerDescripcionCuenta(cuentaDTO.getCuentaRelacionada());
            cuentaView.setDescripcion(descCta);
            cuentaView.setCuenta(nroCuenta);
            cuentaView.setCuentaPreferida(cuentaDTO.isCuentaPreferida());
            cuentaView.setId(cuentaDTO.getId());
            cuentaView.setAlias(obtenerAliasCuentaCliente(
                    ExtraccionYComprasExteriorUtils.getNroCtaAltair(cuentaDTO.getCuentaRelacionada())));
            if (validarCuentaDuplicada(cuentaView, cuentasView)) {
                cuentasView.add(cuentaView);
            }
        }
        return cuentasView;
    }

    /**
     * Valida si una cuenta ya fue agregada a la lista.
     *
     * @param cuentaDuplicadaView
     *            the cuenta duplicada view
     * @param lCtasView
     *            the l ctas view
     * @return true, if successful
     */
    private boolean validarCuentaDuplicada(CuentaOperacionExteriorView cuentaDuplicadaView,
            List<CuentaOperacionExteriorView> lCtasView) {
        for (CuentaOperacionExteriorView cuentaView : lCtasView) {
            if (cuentaView.getCuenta().equals(cuentaDuplicadaView.getCuenta())
                    && cuentaView.getDescripcion().equals(cuentaDuplicadaView.getDescripcion())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna TarjetaOperacionExteriorDTO a partir de id.
     *
     * @param idTarjeta
     *            the id tarjeta
     * @return the tarjeta operacion exterior DTO
     */
    private TarjetaOperacionExteriorDTO obtenerTarjetaDTO(String idTarjeta) {
        if (sesionParametros.getDatosExtraccionYComprasExterior() != null
                && sesionParametros.getDatosExtraccionYComprasExterior().getTarjetasOperacionExterior() != null) {
            for (TarjetaOperacionExteriorDTO tarjetaDTO : sesionParametros.getDatosExtraccionYComprasExterior()
                    .getTarjetasOperacionExterior()) {
                if (tarjetaDTO.getId().equals(idTarjeta)) {
                    return tarjetaDTO;
                }
            }
        }
        return null;
    }

    /**
     * Retorna el nuemero de cuenta a partir de id.
     *
     * @param idCuenta
     *            the id cuenta
     * @return the string
     */
    private String obtenerNumeroCuenta(String idCuenta) {
        if (sesionParametros.getDatosExtraccionYComprasExterior() != null
                && sesionParametros.getDatosExtraccionYComprasExterior().getCuentasOperacionExterior() != null) {
            for (CuentaOperacionExteriorDTO cuentaDTO : sesionParametros.getDatosExtraccionYComprasExterior()
                    .getCuentasOperacionExterior()) {
                if (cuentaDTO.getId().equals(idCuenta)) {
                    return cuentaDTO.getCuentaRelacionada();
                }
            }
        }
        return null;
    }

    /**
     * Carga de datos en sesion.
     *
     * @param datosOperaExteriorView
     *            the datos opera exterior view
     * @param tarjetasDTO
     *            the tarjetas DTO
     */
    private void cargarDatosOperacionExteriorSesion(DatosTarjetasExtraccionYComprasExteriorView datosOperaExteriorView,
            List<TarjetaOperacionExteriorDTO> tarjetasDTO) {
        DatosExtraccionYComprasExteriorDTO datosExteriorDTO = new DatosExtraccionYComprasExteriorDTO();
        datosExteriorDTO.setTarjetaSeleccionada(datosOperaExteriorView.getTarjetas().get(0).getNumeroTarjeta());
        datosExteriorDTO.setTarjetasOperacionExterior(tarjetasDTO);
        sesionParametros.setDatosExtraccionYComprasExterior(datosExteriorDTO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.
     * ExtraccionYComprasExteriorManager#consultarTarjetasOperacionExterior()
     */
    @Override
    public Respuesta<DatosTarjetasExtraccionYComprasExteriorView> consultarTarjetasOperacionExterior() {
        LOGGER.info("ExtraccionYComprasExteriorManagerImpl iniciando consulta de tarjetas");
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuestaTarjetasBO = extraccionYComprasExteriorBO
                .consultarTarjetasOperaExterior();
        if (EstadoRespuesta.OK.equals(respuestaTarjetasBO.getEstadoRespuesta())) {
            DatosTarjetasExtraccionYComprasExteriorView datosOperacionExteriorView = new DatosTarjetasExtraccionYComprasExteriorView();
            datosOperacionExteriorView.setTarjetas(generarTarjetasView(respuestaTarjetasBO.getRespuesta()));

            if (respuestaTarjetasBO.getRespuesta() != null && !respuestaTarjetasBO.getRespuesta().isEmpty()) {
                cargarDatosOperacionExteriorSesion(datosOperacionExteriorView, respuestaTarjetasBO.getRespuesta());
                Respuesta<List<CuentaOperacionExteriorDTO>> respuestaCuentasBO = extraccionYComprasExteriorBO
                        .consultarCuentasOperaExterior(respuestaTarjetasBO.getRespuesta().get(0).getNumeroTarjeta());
                if (EstadoRespuesta.OK.equals(respuestaCuentasBO.getEstadoRespuesta())) {
                    datosOperacionExteriorView.setCuentas(generarCuentasView(respuestaCuentasBO.getRespuesta()));
                    sesionParametros.getDatosExtraccionYComprasExterior()
                            .setCuentasOperacionExterior(respuestaCuentasBO.getRespuesta());
                } else {
                    LOGGER.info("ExtraccionYComprasExteriorManagerImpl finalizando consulta de cuentas Error");
                    return respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_CUENTAS,
                            CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CONSULTA_CUENTAS);

                }
            }
            datosOperacionExteriorView.setInfoLegal(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.OPERACIONES_EXTERIOR_INFO_LEGAL).getMensaje());
            datosOperacionExteriorView.setLegalCuenta(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.OPERACIONES_EXTERIOR_LEGAL_CUENTA).getMensaje());
            LOGGER.debug("ExtraccionYComprasExteriorManagerImpl finalizando consulta de tarjetas Ok");
            return respuestaFactory.crearRespuestaOk(datosOperacionExteriorView);
        }
        LOGGER.debug("ExtraccionYComprasExteriorManagerImpl finalizando consulta tarjetas Error");
        return respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_TARJETAS,
                CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CONSULTA_TARJETAS);
    }

    /**
     * Generacion de tarjetas view.
     *
     * @param tarjetasDTO
     *            the tarjetas DTO
     * @return Lista de tarjetas view
     */
    private List<TarjetaOperacionExteriorView> generarTarjetasView(List<TarjetaOperacionExteriorDTO> tarjetasDTO) {
        List<TarjetaOperacionExteriorView> tarjetasView = new ArrayList<TarjetaOperacionExteriorView>();
        for (TarjetaOperacionExteriorDTO tarjetaDTO : tarjetasDTO) {
            TarjetaOperacionExteriorView tarjetaView = new TarjetaOperacionExteriorView();
            tarjetaView.setNumeroTarjeta(
                    ExtraccionYComprasExteriorUtils.crearMascaraNroTarjeta(tarjetaDTO.getNumeroTarjeta()));
            tarjetaView.setId(tarjetaDTO.getId());
            tarjetasView.add(tarjetaView);
        }
        return tarjetasView;

    }

    /**
     * Obtener Alias de cuetna.
     *
     * @param nroCuenta
     *            the nro cuenta
     * @return Alias
     */
    private String obtenerAliasCuentaCliente(String nroCuenta) {
        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuentaCliente = cliente.getCuentaPorNumero(nroCuenta);
        if (cuentaCliente != null && cuentaCliente.getAlias() != null) {
            return cuentaCliente.getAlias();
        }
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.
     * ExtraccionYComprasExteriorManager#modificarTarjetaOperacionExterior(ar.
     * com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.
     * ModifTarjetaOperaExtraccionView)
     */
    @Override
    public Respuesta<ModifTarjetaOperaExtraccionView> modificarTarjetaOperacionExterior(
            ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView) {
        LOGGER.info("ExtraccionYComprasExteriorManagerImpl iniciando modificar tarjeta opera exterior");

        if (modifTarjetaOperaExteriorView.getDesafio() == null) {
            cargarHashValidacion(crearMapaDeLaVista(modifTarjetaOperaExteriorView));
        }

        CambioTarjetaOperaExteriorInDTO cambioTarjetaInDTO = generarCambioTarjetaDTO(modifTarjetaOperaExteriorView);
        String monedaCuenta = sesionParametros.getDatosExtraccionYComprasExterior().getDescripcionCuenta();
        if (monedaCuenta.contains("Pesos")) {
        	modifTarjetaOperaExteriorView.setMoneda("ARS");
        } else {
        	modifTarjetaOperaExteriorView.setMoneda("USD");
        }
        cargarDatosClaveToken(modifTarjetaOperaExteriorView);
        
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        Respuesta<ModifTarjetaOperaExtraccionView> respuestaRsa = desafioOperacionRSA.validarOperacionRSA(
                modifTarjetaOperaExteriorView, valorDesafioExtraccionYComprasExterior, autentificacionCodEstDTO);
        if (respuestaRsa == null || !EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {
            return respuestaRsa;
        }

        validarHash(crearMapaDeLaVista(modifTarjetaOperaExteriorView));

        String nroCuentaFormat = ExtraccionYComprasExteriorUtils.getNroCta(cambioTarjetaInDTO.getCuenta());
        String descCta = obtenerDescripcionCuenta(cambioTarjetaInDTO.getCuenta());

        estadisticaManager.add(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_CONFIRMAR,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        Respuesta<CambioTarjetaOperaExteriorOutDTO> rtaCambioTarjetasBO = extraccionYComprasExteriorBO
                .cambioTarjetaOperaExterior(cambioTarjetaInDTO);
        if (EstadoRespuesta.OK.equals(rtaCambioTarjetasBO.getEstadoRespuesta())
                && rtaCambioTarjetasBO.getRespuesta() != null) {
            estadisticaManager.add(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_CMBRIOTARJ,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            modifTarjetaOperaExteriorView.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            modifTarjetaOperaExteriorView.setHora(new SimpleDateFormat("HH:mm").format(new Date()));
            modifTarjetaOperaExteriorView.setNroComprobante(rtaCambioTarjetasBO.getRespuesta().getNumeroComprobante());
            sesionParametros.setNroComprobante(rtaCambioTarjetasBO.getRespuesta().getNumeroComprobante());
            sesionParametros.getDatosExtraccionYComprasExterior().setCuentaSeleccionada(nroCuentaFormat);
            LOGGER.info("ExtraccionYComprasExteriorManagerImpl finalizando modificar tarjeta opera exterior Ok");
            modifTarjetaOperaExteriorView.setMensaje(MessageFormat.format(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.OPERACIONES_EXTERIOR_OK_CAMBIO_TARJETA)
                    .getMensaje(), descCta + " " + nroCuentaFormat));
            return respuestaFactory.crearRespuestaOk(ModifTarjetaOperaExtraccionView.class,
                    modifTarjetaOperaExteriorView);
        }
        LOGGER.info("ExtraccionYComprasExteriorManagerImpl finalizando modificar tarjeta opera exterior Error");
        estadisticaManager.add(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_CMBRIOTARJ,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_CMBTARJETA,
                CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CAMBIO_TARJETA, descCta + " " + nroCuentaFormat);

    }
    
    
	private void cargarDatosClaveToken(ModifTarjetaOperaExtraccionView modifTarjetaOperaExtraccionView) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				modifTarjetaOperaExtraccionView.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				modifTarjetaOperaExtraccionView.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }

    /**
     * Generacion de CambioTarjetaOperaExteriorInDTO.
     *
     * @param modificacionTarjetaView
     *            the modificacion tarjeta view
     * @return the cambio tarjeta opera exterior in DTO
     */
    private CambioTarjetaOperaExteriorInDTO generarCambioTarjetaDTO(
            ModifTarjetaOperaExtraccionView modificacionTarjetaView) {
        LOGGER.debug("ExtraccionYComprasExteriorManagerImpl generando informacion para modificacion de tarjeta");
        CambioTarjetaOperaExteriorInDTO cambioTarjetaInDTO = new CambioTarjetaOperaExteriorInDTO();
        TarjetaOperacionExteriorDTO tarjetaDTO = obtenerTarjetaDTO(modificacionTarjetaView.getIdTarjeta());
        if (tarjetaDTO != null) {
            cambioTarjetaInDTO.setNumeroTarjeta(tarjetaDTO.getNumeroTarjeta());
            cambioTarjetaInDTO.setCuenta(obtenerNumeroCuenta(modificacionTarjetaView.getIdCuenta()));
            cambioTarjetaInDTO.setNumeroDocumento(tarjetaDTO.getNumeroDocumento());
            cambioTarjetaInDTO.setTipoDocumento(tarjetaDTO.getTipoDocumento());
            cargarDescripcionCuentaSeleccionada(cambioTarjetaInDTO);
        }
        return cambioTarjetaInDTO;

    }

    /**
     * Carga en sesion de la descripcion de la tarjeta seleccionada.
     *
     * @param cambioTarjetaInDTO
     *            the cambio tarjeta in DTO
     */
    private void cargarDescripcionCuentaSeleccionada(CambioTarjetaOperaExteriorInDTO cambioTarjetaInDTO) {
        String descCta = obtenerDescripcionCuenta(cambioTarjetaInDTO.getCuenta());
        sesionParametros.getDatosExtraccionYComprasExterior().setDescripcionCuenta(descCta);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.
     * ExtraccionYComprasExteriorManager#descargarComprobante()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobante() {
        LOGGER.debug("ExtraccionYComprasExteriorManagerImpl _  iniciando descargarComprobante");
        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        Respuesta<Reporte> respuestaReporte = extraccionYComprasExteriorBO.descargarComprobante();
        respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
        if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        } else {
            LOGGER.debug("ExtraccionYComprasExteriorManagerImpl _ Error descargando comprobante");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
        }
        LOGGER.debug("ExtraccionYComprasExteriorManagerImpl _  finalizando descargarComprobante");
        return respuestaView;
    }

    /**
     * Crear mapa de la vista.
     *
     * @param operaExteriorView
     *            the opera exterior view
     * @return the map
     */
    public Map<String, Object> crearMapaDeLaVista(ModifTarjetaOperaExtraccionView operaExteriorView) {
        LOGGER.info("Creando hash de los atributos...");
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("idTarjeta",
                operaExteriorView.getIdTarjeta() != null ? operaExteriorView.getIdTarjeta() : "");
        mapaAtributos.put("idCuenta", operaExteriorView.getIdCuenta() != null ? operaExteriorView.getIdCuenta() : "");
        return mapaAtributos;
    }

    /**
     * Cargar hash.
     *
     * @param viewMap
     *            the view map
     */
    public void cargarHashValidacion(Map<String, Object> viewMap) {
        String hashView = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Se guarda el hash en sesion.");
        sesionParametros.setValidacionHash(hashView);
    }

    /**
     * Validar hash.
     *
     * @param viewMap
     *            the view map
     */
    public void validarHash(Map<String, Object> viewMap) {
        String inputHash = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Validando el hash {} de la sesion con el hash de la entidad {}",
                sesionParametros.getValidacionHash(), inputHash);
        HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
    }

    /**
     * Asignar estadisticas de autenticacion.
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudToken(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_SOLICITUD_TOKEN);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionToken(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_VALIDACION_TOKEN);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_SOLICITUD_COORD);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_VALIDACION_COORD);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.EXTYCOMPRAS_EXTERIOR_VALIDACION_DIGITOS);
        return autentificacionCodEstDTO;
    }

}
