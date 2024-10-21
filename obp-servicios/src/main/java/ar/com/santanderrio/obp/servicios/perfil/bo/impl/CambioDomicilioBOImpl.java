/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DudaEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dao.CambioDomicilioDAO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelInDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelOutDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosMerlinDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ResultadoModificacionDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosDomicilioEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosTelefonoEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DomicilioEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ProductoEntity;
import ar.com.santanderrio.obp.servicios.perfil.utils.PerfilUtils;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;

/**
 * Clase CambioDomicilioBOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class CambioDomicilioBOImpl implements CambioDomicilioBO {

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The datos cambioDomicilioDAO. */
    @Autowired
    private CambioDomicilioDAO cambioDomicilioDAO;

    /** The descripciones por id. */
    @Autowired
    private DatosSelectoresDAO descripcionesPorId;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The datos selectores DAO. */
    @Autowired
    private DatosSelectoresDAO datosSelectoresDAO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The caja ahorro pesos. */
    @Value("${DOMICILIO.PRODUCTO.02}")
    private String cajaAhorroPesos;

    /** The caja ahorro dolares. */
    @Value("${DOMICILIO.PRODUCTO.03}")
    private String cajaAhorroDolares;

    /** The cuenta. */
    @Value("${DOMICILIO.PRODUCTO.04}")
    private String cuenta;

    /** The cuenta corriente pesos. */
    @Value("${DOMICILIO.PRODUCTO.05}")
    private String cuentaCorrientePesos;

    /** The cuenta corriente dolares. */
    @Value("${DOMICILIO.PRODUCTO.06}")
    private String cuentaCorrienteDolares;

    /** The cuenta unica. */
    @Value("${DOMICILIO.PRODUCTO.07}")
    private String cuentaUnica;

    /** The prestamo nro. */
    @Value("${DOMICILIO.PRODUCTO.30}")
    private String prestamoNro;

    /** The prestamo personal nro. */
    @Value("${DOMICILIO.PRODUCTO.35}")
    private String prestamoPersonalNro;

    /** The prestamo hipotecario nro. */
    @Value("${DOMICILIO.PRODUCTO.37}")
    private String prestamoHipotecarioNro;

    /** The prestamo prendario nro. */
    @Value("${DOMICILIO.PRODUCTO.38}")
    private String prestamoPrendarioNro;

    /** The visa. */
    @Value("${DOMICILIO.PRODUCTO.40}")
    private String visa;

    /** The mastercard. */
    @Value("${DOMICILIO.PRODUCTO.41}")
    private String mastercard;

    /** The amex. */
    @Value("${DOMICILIO.PRODUCTO.42}")
    private String amex;

    /** The prestamo nro 2. */
    @Value("${DOMICILIO.PRODUCTO.61}")
    private String prestamoNro2;

    /** The prestamo nro 3. */
    @Value("${DOMICILIO.PRODUCTO.71}")
    private String prestamoNro3;

    /** The tarjeta rio debito. */
    @Value("${DOMICILIO.PRODUCTO.81}")
    private String tarjetaRioDebito;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CambioDomicilioBOImpl.class);

    /** The Constant TAG_PROVINCIA. */
    private static final String TAG_PROVINCIA = "provincias";

    /** The Constant TAG_PAIS. */
    private static final String TAG_PAIS = "Nacionalidades";

    /** The Constant OPCION_CONSULTA. */
    private static final String OPCION_CONSULTA = "C";

    /** The Constant SECUENCIA_TELEFONO_DEFAULT. */
    private static final String SECUENCIA_TELEFONO_DEFAULT = "000";

    /** The Constant TIPO_TELEFONO_DEFAULT. */
    private static final String TIPO_TELEFONO_DEFAULT = "000";

    /** The Constant CLASE_TELEFONO_DEFAULT. */
    private static final String CLASE_TELEFONO_DEFAULT = "000";

    /** The merlin dao. */
    @Autowired
    private MerlinDAO merlinDao;

    /** The Constant MERLIN_DUDCPA_AI. */
    private static final String MERLIN_DUDCPA_AI = "AI";

    /** The Constant MERLIN_DUDCPA_LI. */
    private static final String MERLIN_DUDCPA_LI = "LI";

    /** The Constant MERLIN_DUDCPA_CA. */
    private static final String MERLIN_DUDCPA_CA = "CA";

    /** The Constant ACCION_CAMBIO_DOM_TEL. */
    private static final String ACCION_CAMBIO_DOM_TEL = "A";

    /** The Constant ACCION_CAMBIO_DOM. */
    private static final String ACCION_CAMBIO_DOM = "D";

    /** The Constant ACCION_CAMBIO_TEL. */
    private static final String ACCION_CAMBIO_TEL = "T";

    /** The Constant MARCA_NORMALIZACION_OK. */
    private static final String MARCA_NORMALIZACION_OK = "S";

    /** The Constant MARCA_NORMALIZACION_PENDIENTE. */
    private static final String MARCA_NORMALIZACION_PENDIENTE = "N";

    /** The Constant TIPO_DOMICILIO_CONTACTO_EXTERNO. */
    private static final String TIPO_DOMICILIO_CONTACTO_EXTERNO = "CON";

    /** The Constant TIPO_DOMICILIO_DIRECCION_EMAIL. */
    private static final String TIPO_DOMICILIO_DIRECCION_EMAIL = "ELE";

    /** The Constant TIPO_DOMICILIO_EXTRANJERO. */
    private static final String TIPO_DOMICILIO_EXTRANJERO = "EXT";

    /** The Constant DOMICILIO_BANCA_PRIVADA. */
    private static final String DOMICILIO_BANCA_PRIVADA = "BPR";

    /** The Constant DOMICILIO_PRENDARIO. */
    private static final String DOMICILIO_PRENDARIO = "PRE";

    /** The Constant TIPO_PRODUCTO_VISA. */
    private static final String TIPO_PRODUCTO_VISA = "40";

    /** The Constant TIPO_PRODUCTO_MASTER. */
    private static final String TIPO_PRODUCTO_MASTER = "41";

    /** The Constant TIPO_PRODUCTO_AMEX. */
    private static final String TIPO_PRODUCTO_AMEX = "42";

    /** The Constant TRES_CEROS. */
    private static final String TRES_CEROS = "000";

    /** The Constant VEINTE_ESPACIOS. */
    private static final String VEINTE_ESPACIOS = "                    ";

    /** The Constant TREINTA_ESPACIOS. */
    private static final String TREINTA_ESPACIOS = "                              ";

    /** The Constant ERROR_NORMALIZACION_17. */
    private static final String ERROR_NORMALIZACION_17 = "20000017";

    /** The Constant ERROR_NORMALIZACION_16. */
    private static final String ERROR_NORMALIZACION_16 = "20000016";

    /** The Constant LOCALIDAD. */
    private static final String LOCALIDAD = "LOCALIDAD";

    /** The Constant CALLE. */
    private static final String CALLE = "CALLE";

    /** The Constant ALTURA. */
    private static final String ALTURA = "ALTURA";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO#
     * consultarDomiciliosRegistrados()
     */
    @Override
    public Respuesta<List<CambioDomicilioDTO>> consultarDomiciliosRegistrados() {
        LOGGER.info("CambioDomicilioBO _ Iniciando metodo consultarDomiciliosRegistrados ");
        Cliente cliente = sesionCliente.getCliente();
        ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
        consultaDomiciliosInEntity.setCliente(cliente);
        List<CambioDomicilioDTO> lCdDTO = new ArrayList<CambioDomicilioDTO>();
        Respuesta<List<CambioDomicilioDTO>> rtaDom = new Respuesta<List<CambioDomicilioDTO>>();
        Map<String, String> listaProductosDescripcion = new HashMap<String, String>();
        Respuesta<ConsultaDomiciliosOutEntity> consultaDomiciliosOutEntity;
        boolean mensajePrivadoSeteado = false;
        boolean mensajePrendarioSeteado = false;
        try {
            listaProductosDescripcion = obtenerListadoDeProductosDom();

            LOGGER.debug("CambioDomicilioBO _ Iniciando llamada a CambioDomicilioDAO ");
            consultaDomiciliosOutEntity = cambioDomicilioDAO.consultaDomiciliosRegistrados(consultaDomiciliosInEntity);
            LOGGER.debug("CambioDomicilioBO _ Finalizando llamada a CambioDomicilioDAO ");

            if (EstadoRespuesta.WARNING.equals(consultaDomiciliosOutEntity.getEstadoRespuesta())) {
                estadisticaManager.add(EstadisticasConstants.VISUALIZACION_DATOS_DE_DOMICILIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
            }

            if (!consultaDomiciliosOutEntity.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
                    && consultaDomiciliosOutEntity.getRespuesta().getListaDomicilios() != null) {

                for (Iterator<DomicilioEntity> iterator = consultaDomiciliosOutEntity.getRespuesta()
                        .getListaDomicilios().iterator(); iterator.hasNext();) {
                    DomicilioEntity domEntity = iterator.next();
                    if (domEntity.getTipoDomicilio().equals(TIPO_DOMICILIO_CONTACTO_EXTERNO)
                            || domEntity.getTipoDomicilio().equals(TIPO_DOMICILIO_DIRECCION_EMAIL)
                            || domEntity.getTipoDomicilio().equals(TIPO_DOMICILIO_EXTRANJERO))
                        continue;

                    CambioDomicilioDTO cdDTO = new CambioDomicilioDTO();
                    this.completarCambioDomicilioDTOOriginal(domEntity, cdDTO,
                            consultaDomiciliosOutEntity.getRespuesta().getListaproductos(), listaProductosDescripcion);
                    cdDTO.setDomicilioId(UUID.randomUUID().toString());
                    if (domEntity.getTipoDomicilio().equals(DOMICILIO_BANCA_PRIVADA) && !mensajePrivadoSeteado) {
                        consultaDomiciliosOutEntity.getItemsMensajeRespuesta()
                                .addAll(respuestaFactory.obtenerListItemMensaje(
                                        CodigoMensajeConstantes.TRANSFERENCIA_ERROR_CUENTA_DESTINO,
                                        TipoError.CAMBIO_DOMICILIO_DOM_PRIVADO.getDescripcion()));
                        mensajePrivadoSeteado = true;
                    } else if (domEntity.getTipoDomicilio().equals(DOMICILIO_PRENDARIO) && !mensajePrendarioSeteado) {
                        consultaDomiciliosOutEntity.getItemsMensajeRespuesta()
                                .addAll(respuestaFactory.obtenerListItemMensaje(
                                        CodigoMensajeConstantes.CODIGO_DETALLE_PRENDARIO_CAMBIO_DOMICILIO,
                                        TipoError.CAMBIO_DOMICILIO_DOM_PRENDARIO.getDescripcion()));
                        mensajePrendarioSeteado = true;
                    }
                    lCdDTO.add(cdDTO);
                }

            } else {
                rtaDom.setItemMensajeRespuesta(consultaDomiciliosOutEntity.getItemsMensajeRespuesta());
                rtaDom.setEstadoRespuesta(EstadoRespuesta.ERROR);
                grabarEstadisticaDomicilios(consultaDomiciliosOutEntity.getEstadoRespuesta());
                return rtaDom;
            }

        } catch (DAOException e) {
            LOGGER.error("ConsultarDomiciliosRegistrados", e);
            estadisticaManager.add(EstadisticasConstants.VISUALIZACION_DATOS_DE_DOMICILIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return this.respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
        }
        LOGGER.debug("CambioDomicilioBO _ Finalizando llamada a consultarDomiciliosRegistrados ");
        sesionParametros.setDomiciliosCliente(lCdDTO);
        rtaDom.setEstadoRespuesta(EstadoRespuesta.OK);
        rtaDom.setRespuesta(lCdDTO);
        rtaDom.setItemMensajeRespuesta(consultaDomiciliosOutEntity.getItemsMensajeRespuesta());
        grabarEstadisticaDomicilios(consultaDomiciliosOutEntity.getEstadoRespuesta());
        return rtaDom;

    }

    /**
	 * Grabar estadistica domicilios.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 */
    private void grabarEstadisticaDomicilios(EstadoRespuesta estadoRespuesta) {
        if (EstadoRespuesta.OK.equals(estadoRespuesta)) {
            estadisticaManager.add(EstadisticasConstants.VISUALIZACION_DATOS_DE_DOMICILIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (EstadoRespuesta.ERROR.equals(estadoRespuesta)) {
            estadisticaManager.add(EstadisticasConstants.VISUALIZACION_DATOS_DE_DOMICILIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
        }
    }

    /**
     * Obtener listado de productos dom.
     *
     * @return the map
     */
    private Map<String, String> obtenerListadoDeProductosDom() {
        Map<String, String> listaDescripcionProducto = new HashMap<String, String>();
        listaDescripcionProducto.put("02", cajaAhorroPesos);
        listaDescripcionProducto.put("03", cajaAhorroDolares);
        listaDescripcionProducto.put("04", cuenta);
        listaDescripcionProducto.put("05", cuentaCorrientePesos);
        listaDescripcionProducto.put("06", cuentaCorrienteDolares);
        listaDescripcionProducto.put("07", cuentaUnica);
        listaDescripcionProducto.put("30", prestamoNro);
        listaDescripcionProducto.put("35", prestamoPersonalNro);
        listaDescripcionProducto.put("37", prestamoHipotecarioNro);
        listaDescripcionProducto.put("38", prestamoPrendarioNro);
        listaDescripcionProducto.put("40", visa);
        listaDescripcionProducto.put("41", mastercard);
        listaDescripcionProducto.put("42", amex);
        listaDescripcionProducto.put("61", prestamoNro2);
        listaDescripcionProducto.put("71", prestamoNro3);
        listaDescripcionProducto.put("81", tarjetaRioDebito);

        return listaDescripcionProducto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO#
     * obtenerInfoAdicionalDomTel(java.lang.String)
     */
    @Override
    public Respuesta<DatosDomTelOutDTO> obtenerInfoAdicionalDomTel(String cambioDomicilioID) {
        LOGGER.info("CambioDomicilioBO _ Iniciando llamada a obtenerInfoAdicionalDomTel");
        Respuesta<DatosDomTelOutDTO> rtaDatosDomTelOutDTO = new Respuesta<DatosDomTelOutDTO>();
        // obtengo el domicilio a modificar de la lista de domicilios en sesion
        CambioDomicilioDTO cambioDomicilioDTO = getCambioDomicilioById(cambioDomicilioID);
        if (cambioDomicilioDTO != null) {
            DatosDomTelInDTO ddtInDTO = new DatosDomTelInDTO();
            ddtInDTO.setSecuenciaDomicilio(cambioDomicilioDTO.getSecuenciaDomicilio());
            rtaDatosDomTelOutDTO = obtenerDatosDomTel(ddtInDTO);
            if (rtaDatosDomTelOutDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                actualizarDomicilioSesion(rtaDatosDomTelOutDTO.getRespuesta(), cambioDomicilioID);
            }

        } else {
            LOGGER.debug("CambioDomicilioBO _obtenerInfoAdicionalDomTel , no se encontro domicilio en sesion");
            return respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
        }
        LOGGER.debug("CambioDomicilioBO _ Finalizando llamada a obtenerInfoAdicionalDomTel ");
        return rtaDatosDomTelOutDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO#
     * guardarCambioDomicilio(ar.com.santanderrio.obp.servicios.perfil.web.view.
     * CambioDomicilioView)
     */
    @Override
    public Respuesta<ResultadoModificacionDomicilioDTO> guardarCambioDomicilio(
            CambioDomicilioView cambioDomicilioView) {
        LOGGER.debug("CambioDomicilioBO _ Iniciando  guardarCambioDomicilio"); 
        CambioDomicilioDTO domicilioOriginal = getCambioDomicilioById(cambioDomicilioView.getDomicilioId());
        ModificacionCambioDomicilioDTO domicilioModif = this.completartDomicilioModificado(cambioDomicilioView);
        ResultadoModificacionDomicilioDTO resultadoModificacion = new ResultadoModificacionDomicilioDTO();
        if (domicilioOriginal == null || domicilioModif == null) { 
            return this.respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
        } else {
            try {
                CambioDomicilioInEntity cambioDomicilioInEntity = null;
                if(domicilioModif.getDatosMerlin() != null
                        && domicilioModif.getDatosMerlin().getOpcionesDomicilio() != null
                        && domicilioModif.getDatosMerlin().getOpcionesDomicilio().size() > 1 
                        && StringUtils.isBlank(cambioDomicilioView.getCodigoPostal())) { 
                    LOGGER.info("Viene de lista de domicilios y se vuelve a invocar a merlin para validar que este correctamente normalizado.");
                    DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
                    completarDatosDomicilioInEntity(datosMerlinInEntity, cambioDomicilioView, domicilioModif);
                    ResultadoMerlinEntity resultadoMerlinEntity = merlinDao.getResultadoMerlin(datosMerlinInEntity);
                    if(resultadoMerlinEntity.getCantidadDeDudas() == 0) {
                        LOGGER.info("La respuesta de merlin contiene una unica respuesta, se continua con la cargada por el cliente.");
                        Respuesta<List<CambioDomicilioDTO>> respuesta = mapearResultadoMerlin(resultadoMerlinEntity, domicilioModif.getCodigoPostal());
                        CambioDomicilioDTO cambioDomicilioDTO = respuesta.getRespuesta().get(0);
                        cambioDomicilioView.setCodigoPostal(cambioDomicilioDTO.getCodigoPostal());
                    } else {
                        LOGGER.info("La respuesta de merlin contiene mas de un domicilio por lo que se utiliza el seleccionado con el codigo postal cargado por cliente y marca de normalizacion en N.");
                        domicilioModif.setDatosMerlin(null);
                        CambioDomicilioView customCambioDomicilioView = sesionParametros.getDatosMerlinSinNormalizar();
                        if(StringUtils.isBlank(cambioDomicilioView.getCodigoPostal())) {
                            cambioDomicilioView.setCodigoPostal(customCambioDomicilioView.getCodigoPostal());
                        }
                    }
                }
                cambioDomicilioInEntity = completarDatosCambioDomInEntity(cambioDomicilioView, domicilioOriginal, domicilioModif);
                LOGGER.debug("CambioDomicilioBO _ Iniciando llamada a CambioDomicilioDAO ");
                quitarMarcaMya(cambioDomicilioInEntity);
                CambioDomicilioOutEntity cambioDomicilioOutEntity = cambioDomicilioDAO
                        .guardarCambioDomicilio(cambioDomicilioInEntity);
                LOGGER.debug("CambioDomicilioBO _ Finalizando llamada a CambioDomicilioDAO  {} ",
                        cambioDomicilioOutEntity.getCodigoRetornoExtendido());
                resultadoModificacion = new ResultadoModificacionDomicilioDTO(cambioDomicilioOutEntity);
                domicilioModif.setResultadoModificacion(resultadoModificacion);
            } catch (DAOException e) {
                LOGGER.error("guardarCambioDomicilio", e);
                return this.respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO,
                        CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
            } catch (MerlinError1Exception e) {
                LOGGER.error("Error al volver a normalizar domicilio por faltante de codigo postal", e);
                return this.respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO,
                        CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
            }
        }
        LOGGER.debug("CambioDomicilioBO _ Finalizando llamada a guardarCambioDomicilio ");
        return this.respuestaFactory.crearRespuestaOk(resultadoModificacion);
    }
    
    private void quitarMarcaMya(CambioDomicilioInEntity cambioDomicilioInEntity) {
    	
    	if ((revisarSiSonDistintos(cambioDomicilioInEntity.getPrefijo(), cambioDomicilioInEntity.getPrefijoAnterior()) ||
    		revisarSiSonDistintos(cambioDomicilioInEntity.getCaracteristica(), cambioDomicilioInEntity.getCaracteristicaAnterior()) ||
    		revisarSiSonDistintos(cambioDomicilioInEntity.getNroTelefono(), cambioDomicilioInEntity.getNroTelefonoAnterior())) && 
    		cambioDomicilioInEntity.getObservacionesSemaforo().contains("MYA")) {
    		
    		cambioDomicilioInEntity.setObservacionesSemaforo(PerfilUtils.llenaConBlancos(30));
    		
    	}
    	
    }
    
    private boolean revisarSiSonDistintos(String valor, String valorAnterior) {
    	
    	boolean distintos = false;
    	
    	if (valor != null && valorAnterior != null) {
    		return !valor.equals(valorAnterior);
    	}
    	
    	return distintos;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO#
     * normalizarDomicilio(ar.com.santanderrio.obp.servicios.perfil.web.view.
     * CambioDomicilioView)
     */
    @Override
    public Respuesta<List<CambioDomicilioDTO>> normalizarDomicilio(CambioDomicilioView domModificadoView)
            throws MerlinError1Exception {
        Respuesta<List<CambioDomicilioDTO>> respuesta = new Respuesta<List<CambioDomicilioDTO>>();
        ResultadoMerlinEntity resultadoMerlinEntity = null;
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        ModificacionCambioDomicilioDTO domicilioDTOModif = sesionParametros.getDomicilioModificado();
        completarDatosDomicilioInEntity(datosMerlinInEntity, domModificadoView, domicilioDTOModif);
        sesionParametros.setDatosMerlinSinNormalizar(domModificadoView);
        DatosMerlinDTO dMerlin = new DatosMerlinDTO();
        dMerlin.setResultadoMerlin(MARCA_NORMALIZACION_PENDIENTE);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        try {
            resultadoMerlinEntity = merlinDao.getResultadoMerlin(datosMerlinInEntity);
            if (resultadoMerlinEntity != null && resultadoMerlinEntity.getDudas() != null) {
                respuesta = mapearResultadoMerlin(resultadoMerlinEntity, domModificadoView.getCodigoPostal());
                if (respuesta.getRespuesta() != null && !respuesta.getRespuesta().isEmpty()) {
                    dMerlin.setResultadoMerlin(MARCA_NORMALIZACION_OK);
                    dMerlin.setOpcionesDomicilio(respuesta.getRespuesta());
                }
                if (ERROR_NORMALIZACION_17.equals(resultadoMerlinEntity.getCodigoRetornoExtendido())
                        || ERROR_NORMALIZACION_16.equals(resultadoMerlinEntity.getCodigoRetornoExtendido())) {
                    if (!resultadoMerlinEntity.getNoLoc().trim().isEmpty()) {

                        item.setMensaje("Por favor verifique los datos ingresados. Localidad inválida");
                        item.setTag(LOCALIDAD);
                        item.setTipoError(TipoError.CAMBIO_DOMICILIO_ERROR_NO_EXISTE.getDescripcion());
                    } else if (!resultadoMerlinEntity.getNoCal().trim().isEmpty()) {
                        item.setMensaje("Por favor verifique los datos ingresados. Calle inválida");
                        item.setTag(CALLE);
                        item.setTipoError(TipoError.CAMBIO_DOMICILIO_ERROR_NO_EXISTE.getDescripcion());
                    } else if (!resultadoMerlinEntity.getNoAlt().trim().isEmpty()) {
                        item.setMensaje("Por favor verifique los datos ingresados. Altura inválida");
                        item.setTag(ALTURA);
                        item.setTipoError(TipoError.CAMBIO_DOMICILIO_ERROR_NO_EXISTE.getDescripcion());
                    }
                    respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
                    respuesta.add(item);
                } else {
                    respuesta = respuestaFactory.crearRespuestaOk(respuesta.getRespuesta());
                }
            } else {
                respuesta = respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_NO_EXISTE,
                        CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_NO_EXISTE);
            }
            if (domicilioDTOModif != null) {
                domicilioDTOModif.setDatosMerlin(dMerlin);
            }
        } catch (DAOException e) {
            LOGGER.error("getResultadoMerlin - DAOException", e);
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_NO_EXISTE,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_NO_EXISTE);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO#
     * descargarComprobante(ar.com.santanderrio.obp.servicios.perfil.entities.
     * DatosComprobanteEntity)
     */
    @Override
    public Respuesta<Reporte> descargarComprobante(DatosComprobanteEntity datos) {
        LOGGER.debug("CambioDomicilioBO _ iniciando descargarComprobante");
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();

        try {
            Reporte reporte = cambioDomicilioDAO.descargarComprobante(datos);

            respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaReporte.setRespuesta(reporte);
        } catch (Exception e) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

            respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaReporte.add(itemMensajeRespuesta);

        }
        LOGGER.debug("CambioDomicilioBO _ finalizando descargarComprobante");
        return respuestaReporte;
    }

    /**
	 * Merlin caso varias dudas.
	 *
	 * @param resultadoMerlinEntity
	 *            the resultado merlin entity
	 * @return the respuesta
	 */
    private Respuesta<List<CambioDomicilioDTO>> merlinCasoDudas(ResultadoMerlinEntity resultadoMerlinEntity) {
        Respuesta<List<CambioDomicilioDTO>> respuesta = null;
        if (!resultadoMerlinEntity.getDudas().isEmpty()) {
            List<CambioDomicilioDTO> domiciliosDTO = new ArrayList<CambioDomicilioDTO>();
            for (DudaEntity duda : resultadoMerlinEntity.getDudas()) {
                if (!esDudaConWarning(duda)) {
                    CambioDomicilioDTO domicilioDTO = new CambioDomicilioDTO();
                    domicilioDTO.setCodigoPostal(duda.getDudCPA());
                    domicilioDTO.setProvincia(duda.getDudProv());
                    domicilioDTO.setDescProvincia(
                            descripcionesPorId.obtenerOpcionDescripcion(TAG_PROVINCIA, duda.getDudProv()));
                    domicilioDTO.setLocalidad(duda.getDudLocal());
                    domicilioDTO.setCalle(duda.getDudNomcal());
                    domicilioDTO.setApt(resultadoMerlinEntity.getNumeroBloque());
                    domicilioDTO.setPiso(resultadoMerlinEntity.getPiso());
                    domicilioDTO.setDepartamento(resultadoMerlinEntity.getDepartamento());
                    domiciliosDTO.add(domicilioDTO);
                }
            }
            if (domiciliosDTO.isEmpty()) {
                String dudaCPA = resultadoMerlinEntity.getDudas().get(0).getDudCPA();
                if (MERLIN_DUDCPA_AI.equals(dudaCPA)) {
                    respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_MERLIN_DUDCPA_AI,
                            CodigoMensajeConstantes.MENSAJE_ERROR_MERLIN_DUDCPA_AI);
                } else if (MERLIN_DUDCPA_LI.equals(dudaCPA)) {
                    respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_MERLIN_DUDCPA_LI,
                            CodigoMensajeConstantes.MENSAJE_ERROR_MERLIN_DUDCPA_LI);
                } else if (MERLIN_DUDCPA_CA.equals(dudaCPA)) {
                    respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_MERLIN_DUDCPA_CA,
                            CodigoMensajeConstantes.MENSAJE_ERROR_MERLIN_DUDCPA_CA);
                }
            } else {
                respuesta = respuestaFactory.crearRespuestaOk(domiciliosDTO);
            }
        }
        return respuesta;
    }

    /**
	 * Es duda con warning.
	 *
	 * @param duda
	 *            the duda
	 * @return true, if successful
	 */
    private boolean esDudaConWarning(DudaEntity duda) {
        return MERLIN_DUDCPA_AI.equals(duda.getDudCPA()) || MERLIN_DUDCPA_LI.equals(duda.getDudCPA())
                || MERLIN_DUDCPA_CA.equals(duda.getDudCPA());
    }

    /**
	 * Mapear resultado merlin.
	 *
	 * @param resultadoMerlinEntity
	 *            the resultado merlin entity
	 * @param codigoPostalView
	 *            the codigo postal view
	 * @return the respuesta
	 */
    private Respuesta<List<CambioDomicilioDTO>> mapearResultadoMerlin(ResultadoMerlinEntity resultadoMerlinEntity,
            String codigoPostalView) {
        // hay dudas
        Respuesta<List<CambioDomicilioDTO>> respuesta = merlinCasoDudas(resultadoMerlinEntity);
        if (respuesta == null) {
            // no hay dudas
            List<CambioDomicilioDTO> domiciliosDTO = new ArrayList<CambioDomicilioDTO>();
            CambioDomicilioDTO domicilioDTO = new CambioDomicilioDTO();
            
            sesionParametros.setBarrioNormalizacionDomicilio(resultadoMerlinEntity.getBarrio());
            domicilioDTO.setCodigoPostal(resultadoMerlinEntity.getCpa());
//                    obtenerCodigoPostalMatcheadoDeViewYMerlin(resultadoMerlinEntity, codigoPostalView));
            domicilioDTO.setDescProvincia(
                    descripcionesPorId.obtenerOpcionDescripcion(TAG_PROVINCIA, resultadoMerlinEntity.getProvincia()));
            domicilioDTO.setProvincia(resultadoMerlinEntity.getProvincia());
            domicilioDTO.setCalle(resultadoMerlinEntity.getNombreCalle());
            domicilioDTO.setDepartamento(resultadoMerlinEntity.getDepartamento());
            domicilioDTO.setPiso(resultadoMerlinEntity.getPiso());
            domicilioDTO.setLocalidad(resultadoMerlinEntity.getLocalidad());
            domicilioDTO.setApt(resultadoMerlinEntity.getNumeroBloque());
            domiciliosDTO.add(domicilioDTO);
            return respuestaFactory.crearRespuestaOk(domiciliosDTO);
        }
        return respuesta;
    }

    /**
	 * Obtener codigo postal matcheado de view Y merlin.
	 *
	 * @param resultadoMerlinEntity
	 *            the resultado merlin entity
	 * @param codigoPostalView
	 *            the codigo postal view
	 * @return the string
	 */
    private String obtenerCodigoPostalMatcheadoDeViewYMerlin(ResultadoMerlinEntity resultadoMerlinEntity,
            String codigoPostalView) {
        if (resultadoMerlinEntity.getCpa().trim().equals(codigoPostalView.trim())) {
            return resultadoMerlinEntity.getCpa();
        } else {
            return resultadoMerlinEntity.getCp4();
        }
    }

    /**
     * Completar datos domicilio in entity.
     *
     * @param datosMerlinInEntity
     *            the datos merlin in entity
     * @param domModificadoView
     *            the dom modificado view
     * @param domicilioDTOOriginal
     *            the domicilio DTO original
     */
    private void completarDatosDomicilioInEntity(DatosMerlinInEntity datosMerlinInEntity,
            CambioDomicilioView domModificadoView, ModificacionCambioDomicilioDTO domicilioDTOOriginal) {
        datosMerlinInEntity.setCliente(sesionCliente.getCliente());
        datosMerlinInEntity.setCodigoPostal(domModificadoView.getCodigoPostal());
        datosMerlinInEntity.setDepartamento(domModificadoView.getDepartamento());
        datosMerlinInEntity.setPiso(domModificadoView.getPiso());
        datosMerlinInEntity.setLocalidad(domModificadoView.getLocalidad());
        datosMerlinInEntity.setNombreCalle(domModificadoView.getCalle());
        datosMerlinInEntity.setNup(datosMerlinInEntity.getCliente().getNup());
        datosMerlinInEntity.setProvincia(
                datosSelectoresDAO.obtenerIdOpcionPorDescripcion("provincias", domModificadoView.getDescProvincia()));
        datosMerlinInEntity.setSecuenciaDeDomicilio(
                domicilioDTOOriginal == null ? "" : domicilioDTOOriginal.getSecuenciaDomicilio());
        datosMerlinInEntity
                .setTipoDeDomicilio(domicilioDTOOriginal == null ? "" : domicilioDTOOriginal.getTipoDomicilio());
        datosMerlinInEntity.setBarrio(domicilioDTOOriginal == null ? "" : domicilioDTOOriginal.getComuna());
        datosMerlinInEntity.setNumeroBloque(domModificadoView.getApt());
        datosMerlinInEntity.setTimestamp(PerfilUtils.llenaConBlancos(26));
        datosMerlinInEntity.setTelediscado("");
        datosMerlinInEntity.setTelefono(domicilioDTOOriginal == null ? "" : domicilioDTOOriginal.getTelefono());

    }

    /**
	 * Completar cambio domicilio DTO original.
	 *
	 * @param cdEntity
	 *            the cd entity
	 * @param cdDTO
	 *            the cd DTO
	 * @param listaProductos
	 *            the lista productos
	 * @param listaProductosDescripcion
	 *            the lista productos descripcion
	 */
    private void completarCambioDomicilioDTOOriginal(DomicilioEntity cdEntity, CambioDomicilioDTO cdDTO,
            List<ProductoEntity> listaProductos, Map<String, String> listaProductosDescripcion) {
        cdDTO.setApt(cdEntity.getApt());
        cdDTO.setCalle(cdEntity.getCalle());
        cdDTO.setCodigoPostal(cdEntity.getCodigoPostal());
        cdDTO.setComuna(cdEntity.getComuna());
        cdDTO.setPiso(cdEntity.getObservaciones().substring(28, 31).replaceAll("\\s", ""));
        cdDTO.setDepartamento(cdEntity.getObservaciones().substring(32, 36).replaceAll("\\s", ""));
        cdDTO.setLocalidad(cdEntity.getLocalidad());
        cdDTO.setMarcaDomErroneo(cdEntity.getMarcaDomErroneo());
        cdDTO.setObservaciones(cdEntity.getObservaciones());
        cdDTO.setPais(cdEntity.getPais());
        cdDTO.setSecuenciaDomicilio(cdEntity.getSecuenciaDomicilio());
        cdDTO.setSucursal(cdEntity.getSucursal());
        cdDTO.setProvincia(cdEntity.getProvincia());
        cdDTO.setTelefono(cdEntity.getTelefono());
        cdDTO.setPrefijo(cdEntity.getTelefono().substring(0, 7).replaceAll("\\s", ""));
        cdDTO.setCaracteristica(cdEntity.getTelefono().substring(8, 12).replaceAll("\\s", ""));
        cdDTO.setNumeroTelefono(cdEntity.getTelefono().substring(13, 21).replaceAll("\\s", ""));
        cdDTO.setTipoDomicilio(cdEntity.getTipoDomicilio());
        cdDTO.setDescProvincia(descripcionesPorId.obtenerOpcionDescripcion(TAG_PROVINCIA, cdDTO.getProvincia()));
        cdDTO.setDescPais(descripcionesPorId.obtenerOpcionDescripcion(TAG_PAIS, cdDTO.getPais()));
        cdDTO.setListaProductos(new ArrayList<ProductoDTO>());
        for (Iterator<ProductoEntity> iterator = listaProductos.iterator(); iterator.hasNext();) {
            ProductoEntity pEntity = iterator.next();
            if (pEntity.getSecuenciaDomicilio().equals(cdDTO.getSecuenciaDomicilio())) {
                // TODO : 26/06/2018 Quitar los seteosdeProductoEntity, hasta
                // donde se llego a ver solo se va a utilizar la
                // descripcionProducto, los otros datos no se usan
                ProductoDTO pDTO = new ProductoDTO();
                String key = pEntity.getProducto();
                if (listaProductosDescripcion.containsKey(key)) {
                    pDTO.setDescripcionProducto(
                            obtenerDescripcionProducto(listaProductosDescripcion.get(key), pEntity));
                } else {
                    continue;
                }
                pDTO.setCuenta(pEntity.getCuenta());
                pDTO.setDescripcionOficina(pEntity.getDescripcionOficina());
                pDTO.setOficina(pEntity.getOficina());
                pDTO.setProducto(pEntity.getProducto());
                pDTO.setSubproducto(pEntity.getSubproducto());
                pDTO.setSecuenciaDomicilio(pEntity.getSecuenciaDomicilio());

                cdDTO.getListaProductos().add(pDTO);
            }
        }
    }

    /**
     * Obtener descripcion producto.
     *
     * @param descripcion
     *            the descripcion
     * @param pEntity
     *            the entity
     * @return the string
     */
    private String obtenerDescripcionProducto(String descripcion, ProductoEntity pEntity) {
        String tipoProducto = pEntity.getProducto();
        if (tipoProducto.equals(TIPO_PRODUCTO_VISA) || tipoProducto.equals(TIPO_PRODUCTO_MASTER)
                || tipoProducto.equals(TIPO_PRODUCTO_AMEX))
            return descripcion;
        else
            return descripcion + " "
                    + pEntity.getOficina().substring(pEntity.getOficina().length() - 3, pEntity.getOficina().length())
                    + "-" + pEntity.getCuenta();
    }

    /**
     * Completar datos domicilio DTO.
     *
     * @param datosDomTelDTO
     *            the datos dom tel DTO
     * @param dDomicilioEntity
     *            the d domicilio entity
     */
    private void completarDatosDomicilioDTO(DatosDomTelOutDTO datosDomTelDTO, DatosDomicilioEntity dDomicilioEntity) {
        datosDomTelDTO.setSecuenciaDomicilio(dDomicilioEntity.getSecuencia());
        datosDomTelDTO.setTipoDomicilio(dDomicilioEntity.getTipoDomicilio());
        datosDomTelDTO.setTipoVia(dDomicilioEntity.getTipoVia());
        datosDomTelDTO.setTipoConstruccion(dDomicilioEntity.getTipoConstruccion());
        datosDomTelDTO.setTipoNucleoUrbano(dDomicilioEntity.getTipoNucleoUrbano());
        datosDomTelDTO.setObservaciones2(dDomicilioEntity.getObservaciones2());
        datosDomTelDTO.setSucursalCasilla(dDomicilioEntity.getSucursalCasilla());
        datosDomTelDTO.setLocalidad(dDomicilioEntity.getLocalidad());
        datosDomTelDTO.setComuna(dDomicilioEntity.getComuna());
        datosDomTelDTO.setRutaCartero(dDomicilioEntity.getRutaCartero());
        datosDomTelDTO.setCodigoPais(dDomicilioEntity.getCodigoPais());
        datosDomTelDTO.setTituDomicilio(dDomicilioEntity.getTituDomicilio());
        datosDomTelDTO.setFechaVerificacion(dDomicilioEntity.getFechaVerificacion());
        datosDomTelDTO.setMotivoDevolucion(dDomicilioEntity.getMotivoDevolucion());
        datosDomTelDTO.setUsuarioAlta(dDomicilioEntity.getUsuarioAlta());
        datosDomTelDTO.setFechaAltaRegistro(dDomicilioEntity.getFechaAltaRegistro());
        datosDomTelDTO.setUsuarioUltimaMod(dDomicilioEntity.getUsuarioUltimaMod());
        datosDomTelDTO.setNroTerminalUltMod(dDomicilioEntity.getNroTerminalUltMod());
        datosDomTelDTO.setSucursalUltMod(dDomicilioEntity.getSucursalUltMod());
        datosDomTelDTO.setTimestampUltMod(dDomicilioEntity.getTimestampUltMod());
    }

    /**
     * Completar datos telefono DTO.
     *
     * @param datosDomTelDTO
     *            the datos dom tel DTO
     * @param dTelefonoEntity
     *            the d telefono entity
     */
    private void completarDatosTelefonoDTO(DatosDomTelOutDTO datosDomTelDTO, DatosTelefonoEntity dTelefonoEntity) {
        datosDomTelDTO.setSecuenciaTelefono(dTelefonoEntity.getSecuenciaTelefono());
        datosDomTelDTO.setTipoTelefono(dTelefonoEntity.getTipoTelefono());
        datosDomTelDTO.setClaseTelefono(dTelefonoEntity.getClaseTelefono());
        datosDomTelDTO.setNroInterno(dTelefonoEntity.getNroInterno());
        datosDomTelDTO.setObservacionesSemaforo(dTelefonoEntity.getObservacionesSemaforo());
        datosDomTelDTO.setTimestamp(dTelefonoEntity.getTimestamp());
    }

    /**
     * Completar observaciones piso depto.
     *
     * @param modificado
     *            the modificado
     * @param observacionesOriginal
     *            the observaciones original
     * @return the string
     */
    private String completarObservacionesPisoDepto(CambioDomicilioView modificado, String observacionesOriginal) {
        String observaciones = "";
        String dpto = "";
        String piso = "";
        if (modificado.getDepartamento() != null) {
            dpto = modificado.getDepartamento();
        }
        if (modificado.getPiso() != null) {
            piso = modificado.getPiso();
        }
        piso = ISBANStringUtils.fillStr(piso, 4);
        dpto = ISBANStringUtils.fillStr(dpto, 5);
        observaciones = PerfilUtils.llenaConBlancos(28) + piso + dpto + observacionesOriginal.substring(37, 100);
        return observaciones;
    }

    /**
     * Determinar accion cambio domicilio.
     *
     * @param modificado
     *            the modificado
     * @param original
     *            the original
     * @return the string
     */
    private String determinarAccionCambioDomicilio(CambioDomicilioView modificado, CambioDomicilioDTO original) {
        if (seModificoTelefonoYDomicilio(modificado, original)) {
            return ACCION_CAMBIO_DOM_TEL;
        } else {
            if (seModificoTelefono(modificado, original)) {
                return ACCION_CAMBIO_TEL;
            } else { // si se modifico solo el domicilio
                return ACCION_CAMBIO_DOM;
            }
        }
    }

    /**
     * Se modifico telefono Y domicilio.
     *
     * @param modificado
     *            the modificado
     * @param original
     *            the original
     * @return true, if successful
     */
    private boolean seModificoTelefonoYDomicilio(CambioDomicilioView modificado, CambioDomicilioDTO original) {
        return (!original.getCaracteristica().equals(modificado.getCaracteristica())
                || !original.getPrefijo().equals(modificado.getPrefijo())
                || !original.getNumeroTelefono().equals(modificado.getNumeroTelefono()))
                && (!original.getCalle().replaceAll("\\s+$", "").equals(modificado.getCalle().trim())
                        || !original.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", "")
                                .equals(modificado.getApt().trim())
                        || !original.getPiso().trim().equals(modificado.getPiso().trim())
                        || !original.getDepartamento().trim().equals(modificado.getDepartamento().trim())
                        || !original.getCodigoPostal().replaceAll("\\sA-Za-z", "").trim()
                                .equals(modificado.getCodigoPostal().trim())
                        || !original.getLocalidad().trim().equals(modificado.getLocalidad().trim())
                        || !original.getProvincia().replaceAll("\\s", "").equals(modificado.getProvincia().trim()));

    }

    /**
     * Se modifico telefono.
     *
     * @param modificado
     *            the modificado
     * @param original
     *            the original
     * @return true, if successful
     */
    private boolean seModificoTelefono(CambioDomicilioView modificado, CambioDomicilioDTO original) {
        return !original.getCaracteristica().equals(modificado.getCaracteristica())
                || !original.getPrefijo().equals(modificado.getPrefijo())
                || !original.getNumeroTelefono().equals(modificado.getNumeroTelefono());

    }

    /**
     * Completart domicilio modificado.
     *
     * @param cambioDomicilioView
     *            the cambio domicilio view
     * @return the modificacion cambio domicilio DTO
     */
    private ModificacionCambioDomicilioDTO completartDomicilioModificado(CambioDomicilioView cambioDomicilioView) {
        ModificacionCambioDomicilioDTO domicilioModif = sesionParametros.getDomicilioModificado();
        if (domicilioModif != null) {
            domicilioModif.setApt(cambioDomicilioView.getApt());
            domicilioModif.setCalle(cambioDomicilioView.getCalle());
            domicilioModif.setPiso(cambioDomicilioView.getPiso());
            domicilioModif.setDepartamento(cambioDomicilioView.getDepartamento());
            domicilioModif.setCodigoPostal(cambioDomicilioView.getCodigoPostal());
            domicilioModif.setComuna(cambioDomicilioView.getComuna());
            domicilioModif.setLocalidad(cambioDomicilioView.getLocalidad());
            domicilioModif.setDescProvincia(cambioDomicilioView.getDescProvincia());
            domicilioModif.setProvincia(cambioDomicilioView.getProvincia());
            domicilioModif.setPrefijo(cambioDomicilioView.getPrefijo());
            domicilioModif.setCaracteristica(cambioDomicilioView.getCaracteristica());
            domicilioModif.setNumeroTelefono(cambioDomicilioView.getNumeroTelefono());
            domicilioModif.setTelefono(cambioDomicilioView.getTelefono());
            domicilioModif.setDescPais(cambioDomicilioView.getDescPais());
//            domicilioModif.set

        }
        return domicilioModif;
    }

    /**
     * Completar datos cambio dom in entity.
     *
     * @param modificado
     *            the modificado
     * @param original
     *            the original
     * @param domicilioModif
     *            the domicilio modif
     * @return the cambio domicilio in entity
     */
    private CambioDomicilioInEntity completarDatosCambioDomInEntity(CambioDomicilioView modificado,
            CambioDomicilioDTO original, ModificacionCambioDomicilioDTO domicilioModif) {
        String accion = determinarAccionCambioDomicilio(modificado, original);
        CambioDomicilioInEntity cambioDomicilioInEntity = new CambioDomicilioInEntity();
        cambioDomicilioInEntity.setAccion(accion); // 1
        DatosDomTelOutDTO adicionales = domicilioModif.getDatosAdicionales();
        cambioDomicilioInEntity.setSecuencia(adicionales.getSecuenciaDomicilio());// 2
        cambioDomicilioInEntity.setTipoDomicilio(adicionales.getTipoDomicilio()); // 3
        cambioDomicilioInEntity.setTipoVia(adicionales.getTipoVia());// 4
        cambioDomicilioInEntity.setCalle(modificado.getCalle()); // 5
        cambioDomicilioInEntity.setTipoConstruccion(adicionales.getTipoConstruccion());// 6
        cambioDomicilioInEntity.setTipoNucleoUrbano(adicionales.getTipoNucleoUrbano()); // 7
        cambioDomicilioInEntity
                .setObservaciones1(completarObservacionesPisoDepto(modificado, original.getObservaciones()));// 8
        cambioDomicilioInEntity.setObservaciones2(adicionales.getObservaciones2()); // 9
        cambioDomicilioInEntity.setSucursalCasilla(adicionales.getSucursalCasilla());// 10
        cambioDomicilioInEntity.setApt(modificado.getApt());// 11 MODIFICADO
        cambioDomicilioInEntity.setLocalidad(adicionales.getLocalidad());// 12
                                                                         // Localidad_Ciudad
        cambioDomicilioInEntity.setDescLocalidad(modificado.getLocalidad()); // 13
                                                                             // MODIFICADO
        cambioDomicilioInEntity.setComuna(adicionales.getComuna());// 14
        cambioDomicilioInEntity.setDescComuna(original.getComuna());// 15
                                                                    // Descripcion
                                                                    // comuna
        cambioDomicilioInEntity.setCodigoPostal(modificado.getCodigoPostal());// 16
                                                                              // MODIFICADO
        cambioDomicilioInEntity.setRutaCartero(adicionales.getRutaCartero());// 17
        cambioDomicilioInEntity.setCodigoProvincia(modificado.getProvincia()); // 18
                                                                               // MODIFICADO
        cambioDomicilioInEntity.setCodigoPais(adicionales.getCodigoPais()); // 19
        cambioDomicilioInEntity.setTituDomicilio(adicionales.getTituDomicilio()); // 20
        cambioDomicilioInEntity.setFechaVerificacion(adicionales.getFechaVerificacion());// 21
        if (domicilioModif.getDatosMerlin() != null && domicilioModif.getDatosMerlin().getResultadoMerlin() != null) { // 22
            cambioDomicilioInEntity.setMarcaNormalizacion(domicilioModif.getDatosMerlin().getResultadoMerlin());
        } else {
            cambioDomicilioInEntity.setMarcaNormalizacion(MARCA_NORMALIZACION_PENDIENTE);
        }
        if(!modificado.getNormalizar()) {
            cambioDomicilioInEntity.setMarcaNormalizacion(MARCA_NORMALIZACION_PENDIENTE);
        }
        if (cambioDomicilioInEntity.getMarcaNormalizacion().equals(MARCA_NORMALIZACION_OK)) { // 23
            cambioDomicilioInEntity.setMarcaDomErroneo("N");
        } else {
            cambioDomicilioInEntity.setMarcaDomErroneo("P");
        }
        cambioDomicilioInEntity.setMarcaCorrespDevuel(MARCA_NORMALIZACION_PENDIENTE); // 24
                                                                                      // Siempre
                                                                                      // se
                                                                                      // manda
                                                                                      // N
        cambioDomicilioInEntity.setMotivoDevolucion(adicionales.getMotivoDevolucion()); // 25
        cambioDomicilioInEntity.setUsuarioAlta(adicionales.getUsuarioAlta());// 26
        cambioDomicilioInEntity.setFechaAltaRegistro(adicionales.getFechaAltaRegistro()); // 27
        cambioDomicilioInEntity.setUsuarioUltimaMod(adicionales.getUsuarioUltimaMod()); // 28
        cambioDomicilioInEntity.setNroTerminalUltMod(adicionales.getNroTerminalUltMod()); // 29
        cambioDomicilioInEntity.setSucursalUltMod(adicionales.getSucursalUltMod());// 30
        cambioDomicilioInEntity.setTimestampUltMod(adicionales.getTimestampUltMod());// 31
        cambioDomicilioInEntity.setNombrePrograma(PerfilUtils.llenaConBlancos(8));// 32
        cambioDomicilioInEntity.setSecuenciaTelefono(StringUtils.defaultString(adicionales.getSecuenciaTelefono()) == ""
                ? TRES_CEROS : StringUtils.defaultString(adicionales.getSecuenciaTelefono())); // 33
        cambioDomicilioInEntity.setTipoTelefono(StringUtils.defaultString(adicionales.getTipoTelefono()) == ""
                ? TRES_CEROS : StringUtils.defaultString(adicionales.getTipoTelefono()));// 34
        cambioDomicilioInEntity.setClaseTelefono(StringUtils.defaultString(adicionales.getClaseTelefono()) == ""
                ? TRES_CEROS : StringUtils.defaultString(adicionales.getClaseTelefono())); // 35
        cambioDomicilioInEntity.setPrefijo(modificado.getPrefijo());// 36
                                                                    // MODIFICADO
        cambioDomicilioInEntity.setCaracteristica(modificado.getCaracteristica()); // 37
                                                                                   // MODIFICADO
        cambioDomicilioInEntity.setNroTelefono(modificado.getNumeroTelefono()); // 38
                                                                                // MODIFICADO
        cambioDomicilioInEntity.setNroInterno(StringUtils.defaultString(adicionales.getNroInterno()) == ""
                ? VEINTE_ESPACIOS : StringUtils.defaultString(adicionales.getNroInterno())); // 39
        cambioDomicilioInEntity
                .setObservacionesSemaforo(StringUtils.defaultString(adicionales.getObservacionesSemaforo()) == ""
                        ? TREINTA_ESPACIOS : StringUtils.defaultString(adicionales.getObservacionesSemaforo()));// 40
        cambioDomicilioInEntity.setUsuarioAltaTel(adicionales.getUsuarioAlta());// 41
        cambioDomicilioInEntity.setFechaAltaRegistroTel(adicionales.getFechaAltaRegistro()); // 42
        cambioDomicilioInEntity.setUsuarioUltimaModTel(adicionales.getUsuarioUltimaMod());// 43
        cambioDomicilioInEntity.setNroTerminalUltModTel(adicionales.getNroTerminalUltMod()); // 44
        cambioDomicilioInEntity.setSucursalUltModTel(adicionales.getSucursalUltMod());// 45
    //    cambioDomicilioInEntity.setTimestampUltModTel(adicionales.getTimestampUltMod());// 46
        cambioDomicilioInEntity.setTimestampUltModTel(adicionales.getTimestamp());// 46
        cambioDomicilioInEntity.setDescripcionProvincia(modificado.getDescProvincia()); // 47
                                                                                        // MODIFICADO
        cambioDomicilioInEntity.setCalleAnterior(original.getCalle());// 48
        cambioDomicilioInEntity.setObservaciones1Anterior(original.getObservaciones()); // 49
        cambioDomicilioInEntity.setAptAnterior(original.getApt());// 50
        cambioDomicilioInEntity.setDescLocalidadAnterior(original.getLocalidad()); // 51
        cambioDomicilioInEntity.setDescComunaAnterior(original.getComuna()); // 52
        cambioDomicilioInEntity.setCodigoPostalAnterior(original.getCodigoPostal());// 53
        cambioDomicilioInEntity.setCodigoProvinciaAnterior(original.getProvincia());// 54
        cambioDomicilioInEntity.setDescripcionProvinciaAnterior(original.getDescProvincia());// 55
        cambioDomicilioInEntity.setPrefijoAnterior(original.getPrefijo());// 56
        cambioDomicilioInEntity.setCaracteristicaAnterior(original.getCaracteristica()); // 57
        cambioDomicilioInEntity.setNroTelefonoAnterior(original.getNumeroTelefono()); // 58
        cambioDomicilioInEntity.setCantidadProductosDom(String.valueOf(original.getListaProductos().size())); // 59
        cambioDomicilioInEntity.setListaproductos(new ArrayList<ProductoEntity>()); // 60
        this.copiarProductosEntity(original.getListaProductos(), cambioDomicilioInEntity);
        cambioDomicilioInEntity.setCliente(sesionCliente.getCliente());
        return cambioDomicilioInEntity;
    }

    /**
     * Copiar productos entity.
     *
     * @param original
     *            the original
     * @param cambioDomicilioInEntity
     *            the cambio domicilio in entity
     */
    private void copiarProductosEntity(List<ProductoDTO> original, CambioDomicilioInEntity cambioDomicilioInEntity) {
        for (Iterator<ProductoDTO> iterator = original.iterator(); iterator.hasNext();) {
            ProductoEntity pEntity = new ProductoEntity();
            ProductoDTO pDTO = iterator.next();
            pEntity.setCuenta(pDTO.getCuenta());
            pEntity.setDescripcionOficina(pDTO.getDescripcionOficina());
            pEntity.setDescripcionProducto(pDTO.getDescripcionProducto());
            pEntity.setOficina(pDTO.getOficina());
            pEntity.setProducto(pDTO.getProducto());
            pEntity.setSecuenciaDomicilio(pDTO.getSecuenciaDomicilio());
            pEntity.setSubproducto(pDTO.getSubproducto());
            cambioDomicilioInEntity.getListaproductos().add(pEntity);
        }
    }

    /**
     * Actualizar domicilio sesion.
     *
     * @param datosDomTel
     *            the datos dom tel
     * @param cambioDomicilioID
     *            the cambio domicilio ID
     */
    private void actualizarDomicilioSesion(DatosDomTelOutDTO datosDomTel, String cambioDomicilioID) {

        List<CambioDomicilioDTO> lCdDTO = sesionParametros.getDomiciliosCliente();
        for (Iterator<CambioDomicilioDTO> iterator = lCdDTO.iterator(); iterator.hasNext();) {
            CambioDomicilioDTO cambioDomicilioDTO = iterator.next();
            if (cambioDomicilioDTO.getDomicilioId().equals(cambioDomicilioID)) {
                ModificacionCambioDomicilioDTO domicilioModif = new ModificacionCambioDomicilioDTO();
                domicilioModif.setDatosAdicionales(datosDomTel);
                domicilioModif.setDomicilioId(cambioDomicilioDTO.getDomicilioId());
                domicilioModif.setSecuenciaDomicilio(cambioDomicilioDTO.getSecuenciaDomicilio());
                domicilioModif.setTelefono(cambioDomicilioDTO.getTelefono());
                domicilioModif.setTipoDomicilio(cambioDomicilioDTO.getTipoDomicilio());
                domicilioModif.setComuna(cambioDomicilioDTO.getComuna());
                sesionParametros.setDomicilioModificado(domicilioModif);
            }
        }

    }

    /**
     * Gets the cambio domicilio by id.
     *
     * @param cambioDomicilioID
     *            the cambio domicilio ID
     * @return the cambio domicilio by id
     */
    private CambioDomicilioDTO getCambioDomicilioById(String cambioDomicilioID) {
        List<CambioDomicilioDTO> lCdDTO = sesionParametros.getDomiciliosCliente();
        if (lCdDTO != null) {
            for (Iterator<CambioDomicilioDTO> iterator = lCdDTO.iterator(); iterator.hasNext();) {
                CambioDomicilioDTO cambioDomicilioDTO = iterator.next();
                if (cambioDomicilioDTO.getDomicilioId().equals(cambioDomicilioID)) {
                    return cambioDomicilioDTO;
                }
            }
        }
        LOGGER.debug("CambioDomicilioBO _ No se encontro domicilio en sesion");
        return null;
    }

    /**
     * Obtener datos dom tel.
     *
     * @param dDomTelInDTO
     *            the d dom tel in DTO
     * @return the respuesta
     */
    private Respuesta<DatosDomTelOutDTO> obtenerDatosDomTel(DatosDomTelInDTO dDomTelInDTO) {
        LOGGER.info("CambioDomicilioBO _ Iniciando llamada a obtenerDatosDomTel");
        DatosDomTelOutDTO ddtDTO = new DatosDomTelOutDTO();
        try {
            ConsultaDatosDomicilioInEntity consultaDatosDomicilioInEntity = new ConsultaDatosDomicilioInEntity();
            consultaDatosDomicilioInEntity.setCliente(sesionCliente.getCliente());
            consultaDatosDomicilioInEntity.setOpcion(OPCION_CONSULTA);
            consultaDatosDomicilioInEntity.setSecuenciaDomicilio(dDomTelInDTO.getSecuenciaDomicilio());
            ConsultaDatosTelefonoInEntity consultaDatosTelefonoInEntity = new ConsultaDatosTelefonoInEntity();
            consultaDatosTelefonoInEntity.setCliente(sesionCliente.getCliente());
            consultaDatosTelefonoInEntity.setOpcion(OPCION_CONSULTA);
            consultaDatosTelefonoInEntity.setSecuenciaDom(dDomTelInDTO.getSecuenciaDomicilio());
            consultaDatosTelefonoInEntity.setSecuenciaTel(SECUENCIA_TELEFONO_DEFAULT);
            consultaDatosTelefonoInEntity.setTelTipo(TIPO_TELEFONO_DEFAULT);
            consultaDatosTelefonoInEntity.setTelClase(CLASE_TELEFONO_DEFAULT);
            LOGGER.debug("CambioDomicilioBO _ Iniciando llamada a CambioDomicilioDAO  consultaDatosDomicilio ");
            ConsultaDatosDomicilioOutEntity consultaDatosDomicilioOutEntity = cambioDomicilioDAO
                    .consultaDatosDomicilio(consultaDatosDomicilioInEntity);
            LOGGER.debug("CambioDomicilioBO _ Iniciando llamada a CambioDomicilioDAO  consultaDatosTelefono ");
            ConsultaDatosTelefonoOutEntity consultaDatosTelefonoOutEntity = cambioDomicilioDAO
                    .consultaDatosTelefono(consultaDatosTelefonoInEntity);
            if (consultaDatosDomicilioOutEntity.getListaDatosDomicilio() != null
                    && !consultaDatosDomicilioOutEntity.getListaDatosDomicilio().isEmpty()) {
                DatosDomicilioEntity dDomicilioEntity = consultaDatosDomicilioOutEntity.getListaDatosDomicilio().get(0);
                completarDatosDomicilioDTO(ddtDTO, dDomicilioEntity);
            }
            if (consultaDatosTelefonoOutEntity.getListaTelefonos() != null
                    && !consultaDatosTelefonoOutEntity.getListaTelefonos().isEmpty()) {
                DatosTelefonoEntity dTelefonoEntity = consultaDatosTelefonoOutEntity.getListaTelefonos().get(0);
                completarDatosTelefonoDTO(ddtDTO, dTelefonoEntity);
            }
        } catch (DAOException e) {
            LOGGER.error("Consulta domicilios y telefono", e);
            return this.respuestaFactory.crearRespuestaError("", TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
        }
        LOGGER.debug("CambioDomicilioBO _ Finalizando llamada a obtenerDatosDomTel");
        return this.respuestaFactory.crearRespuestaOk(ddtDTO);

    }

}
