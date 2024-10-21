/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.NotificationsServiceApi;
import ar.com.santanderrio.obp.servicios.api.offersservice.OffersServiceApi;
import ar.com.santanderrio.obp.servicios.api.offersservice.entities.Offer;
import ar.com.santanderrio.obp.servicios.api.offersservice.entities.OffersEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ProcessRequestDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ProcessResultResponseDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing.OfferDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing.OfferResponseDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing.RefinancingProductDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing.TermResponseDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;

/**
 * The Class GestorEventoComercialDAOImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class GestorEventoComercialDAOImpl implements GestorEventoComercialDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestorEventoComercialDAOImpl.class);

    /** The Constant JKS. */
    private String jks = "GESTOREVENTOSCOMERCIALES";

    /** The Constant COD_OK. */
    private static final CharSequence COD_OK = "error000";

    /** The Constant ERROR_FIRMA. */
    private static final String ERROR_FIRMA = "Error generando firma: ";

    private static final String ERROR_LOG = "Error al obtener las notificaciones";

    private static final String DISPOSITIVO = "dispositivo";

    private static final String DESKTOP = "DESKTOP";

    private static final String ID_NOTIFICACION = "id_notificacion";

    private static final String DATOS_PARA_FIRMA = "Datos para armar la firma: {}.";

    private static final String FIRMA = "Firma: {}.";

    /** The Constant FINANCIACIONES. */
    private static final String FINANCIACIONES = "FINANCIACIONES";

    /** The Constant PATH_OFFERS. */
    private static final String PATH_OFFERS = "/offers";

    /** The Constant PATH_RENEGOCIACIONES_PROCESSES. */
    private static final String PATH_PROCESSES = "/processes";

    /** The Constant OBP_CHANNEL. */
    private static final String OBP_CHANNEL = "OBP";
    /** The rest web client. */

    @Autowired
    private OffersServiceApi offersService;

    @Autowired
    private RestWebClient restWebClient;

    /** The app encoding. */
    @Value("${APP.ENCODING}")
    private String appEncoding;

    /** The sign. */
    @Autowired
    private Sign sign;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The financiaciones sec id. */
    @Value("${FINANCIACIONES.SEC.ID}")
    private String financiacionesSecId;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    @Autowired
    private NotificationsServiceApi notificationsApi;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.
     * GestorEventoComercialDAO#obtenerCantidadNotificaciones(ar.com.
     * santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public GestionEventoComercialOutEntity obtenerCantidadNotificaciones(Cliente cliente) throws DAOException {
        LOGGER.info("Entro al DAO para consultar la cantidad de notificaciones");
        Map<String, String> datosFirma = cargaParametrosFirma(cliente, "2", "obtenerCantNotificaciones");
        datosFirma.put(DISPOSITIVO, DESKTOP);
        String firma = generarDatosFirmadosJson(datosFirma, jks);

        try {
            return obtenerClienteRest().post(new ParametroGestorComercialInEntity(cliente.getNup(), firma),
                    GestionEventoComercialOutEntity.class);
        } catch (RuntimeException e) {
            LOGGER.error("Error al obtener la cantidad de notificaciones");
            throw new DAOException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.
     * GestorEventoComercialDAO#obtenerNotificaciones(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public GestionEventoComercialOutEntity obtenerNotificaciones(Cliente cliente) throws DAOException {
        LOGGER.info("Entro al DAO para consultar la cantidad de notificaciones");
        Map<String, String> datosFirma = cargaParametrosFirma(cliente, "1", "obtenerNotificaciones");
        datosFirma.put(DISPOSITIVO, DESKTOP);
        String firma = generarDatosFirmadosJson(datosFirma, jks);

        try {
            GestionEventoComercialOutEntity gestionEventoComercial = obtenerClienteRest().post(
                    new ParametroGestorComercialInEntity(cliente.getNup(), firma),
                    GestionEventoComercialOutEntity.class);

            if (!gestionEventoComercial.sinErrorDeWS()) {
                LOGGER.info(ERROR_LOG);
                throw new DAOException(gestionEventoComercial.getDescripcionError(),
                        gestionEventoComercial.getCodigoError());
            }

            // Se filtran los links permitidos
            for (NotificacionOutEntity notificacion : gestionEventoComercial.getNotificaciones()) {
                String notLink = "";
                if (isNotificacionFormUMAUFI(notificacion.getLink())) {
                    notLink = armarlinkNotificacionFormUMAUFI(notificacion);
                } else {
                    notLink = notificacion.getLink();
                }
                notificacion.setGotoLink(new GotoLink(notLink));
            }
            return gestionEventoComercial;
        } catch (RuntimeException e) {
            LOGGER.error(ERROR_LOG);
            throw new DAOException(e);
        }
    }

    @Override
    public GestionEventoComercialOutEntity borrarNotificacion(Cliente cliente, String id) throws DAOException {
        LOGGER.info("Entro al DAO para borrar la notificacion");
        Map<String, String> datosFirma = cargaParametrosFirma(cliente, "3", "inactivarNotificacion");
        datosFirma.put(ID_NOTIFICACION, id);
        LOGGER.info(DATOS_PARA_FIRMA, datosFirma);
        String firma = generarDatosFirmadosJson(datosFirma, jks);
        LOGGER.info(FIRMA, firma);

        GestionEventoComercialOutEntity entity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), GestionEventoComercialOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - inactivarNotificacion: {}.", entity);
        return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.
     * GestorEventoComercialDAO#obtenerNotificacionesPerfil(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public GestionEventoComercialOutEntity obtenerNotificacionesPerfil(Cliente cliente) throws DAOException {
        LOGGER.info("Entro al DAO para consultar la cantidad de notificaciones perfil");
        Map<String, String> datosFirma = cargaParametrosFirma(cliente, "20", "obtenerACPerfil");
        datosFirma.put(DISPOSITIVO, DESKTOP);
        String firma = generarDatosFirmadosJson(datosFirma, jks);

        try {
            GestionEventoComercialOutEntity gestionEventoComercial = obtenerClienteRest().post(
                    new ParametroGestorComercialInEntity(cliente.getNup(), firma),
                    GestionEventoComercialOutEntity.class);
            if (!gestionEventoComercial.sinErrorDeWS()) {
                LOGGER.error(ERROR_LOG);
                throw new DAOException(gestionEventoComercial.getDescripcionError(),
                        gestionEventoComercial.getCodigoError());
            }

            // Se filtran los links permitidos y tomo solo la primer notificacion
            for (NotificacionOutEntity notificacion : gestionEventoComercial.getNotificaciones()) {
                String notLink = "";
                if (isNotificacionFormUMAUFI(notificacion.getLink())) {
                    notLink = armarlinkNotificacionFormUMAUFI(notificacion);
                    notificacion.setGotoLink(new GotoLink(notLink));
                } else {
                    notLink = notificacion.getLink();
                    notificacion.setGotoLink(new GotoLink(notLink));
                }
            }
            return gestionEventoComercial;
        } catch (RuntimeException e) {
            LOGGER.error(ERROR_LOG);
            throw new DAOException(e);
        }
    }

    /**
     * Obtener cliente rest.
     *
     * @return the web client
     * @throws DAOException
     *                      the DAO exception
     */
    private WebClient obtenerClienteRest() throws DAOException {
        WebClient clientLogin = restWebClient.obtenerClienteRest(jks);
        clientLogin.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        return clientLogin;
    }

    /**
     * Carga parametros firma.
     *
     * @param cliente
     *                     the cliente
     * @param codigoEvento
     *                     the codigo evento
     * @param evento
     *                     the evento
     * @return the map
     */
    private Map<String, String> cargaParametrosFirma(Cliente cliente, String codigoEvento, String evento) {
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("nup", cliente.getNup());
        parametros.put("cod_evento", codigoEvento);
        parametros.put("nombre_evento", evento);
        return parametros;
    }

    /**
     * Gets the datos firmados.
     *
     * @param cliente
     *                    the cliente
     * @param dispositivo
     *                    the dispositivo
     * @return the datos firmados
     * @throws DAOException
     *                      the DAO exception
     */
    @Override
    public OfertasComercialesEntity obtenerOfertasComerciales(Cliente cliente, String dispositivo) throws DAOException {
        OfertasComercialesEntity ofertasComerciales = null;
        
        LOGGER.info("Se consulta para conocer las ofertas comerciales que posee el cliente.");
        try {
            String firma = generarFirmaOfertaComercial(cliente, dispositivo);
            LOGGER.info("La firma obtenida: {}.", firma);
            ParametroGestorComercialInEntity request = new ParametroGestorComercialInEntity(cliente.getNup(), firma);
            LOGGER.info("Request: {}.", request);
            
            WebClient service = this.obtenerClienteRest();
        
            ofertasComerciales = service.post(request, OfertasComercialesEntity.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        if (ofertasComerciales == null || !StringUtils.equalsIgnoreCase(COD_OK, ofertasComerciales.getCodigoError())) {
            if(ofertasComerciales != null) {
                LOGGER.error("El WS devolvio un error con codigo: {} y descripcion: {}.",
                ofertasComerciales.getCodigoError(), ofertasComerciales.getDescripcionError());
            }
            LOGGER.error("Ocurrio un error al llamar al WS, se van a setear parametros por defecto a OfertasComercialesEntity.");
            ofertasComerciales = new OfertasComercialesEntity();
            ofertasComerciales.setNup(cliente.getNup());
            ofertasComerciales.setJsessionId("");
            ofertasComerciales.setDatosOfertaSc("");
            ofertasComerciales.setPuntosSuperclub("");
            ofertasComerciales.setCodigoError("ERROR000");
            ofertasComerciales.setDescripcionError("");
            ofertasComerciales.setIdEncuesta("0");
        }
        // get offers from new api
        LOGGER.info("Llamando a la API de Offer Service para cargar las ofertas comerciales.");
        OffersEntity offersEntity = offersService.getCommercialOffers(cliente.getNup(), dispositivo, "04", "99");
        
        // map new offers to old object
        mapOffersToOfertasComercialesEntity(offersEntity.getOffers(), ofertasComerciales);
        
        /*
        if (!StringUtils.equalsIgnoreCase(COD_OK, ofertasComerciales.getCodigoError())) {
            LOGGER.error("El WS devolvio un error con codigo: {} y descripcion: {}.",
            ofertasComerciales.getCodigoError(), ofertasComerciales.getDescripcionError());
            throw new DAOException(ofertasComerciales.getDescripcionError(), ofertasComerciales.getCodigoError());
        }
        
        
        for (OfertaComercialEntity ofertas : ofertasComerciales.getOfertas()) {
            ofertas.setGotoLink(new GotoLink(ofertas.getLink()));
        }
        */
        
        LOGGER.info("Respuesta OK del WS: {}.", ofertasComerciales);
        return ofertasComerciales;
    }
    
    private void mapOffersToOfertasComercialesEntity(List<Offer> offers, OfertasComercialesEntity ofertasComercialesEntity) {
        List<OfertaComercialEntity> ofertasComerciales = new ArrayList<OfertaComercialEntity>();
        for(Offer offer: offers) {
            OfertaComercialEntity oferta = new OfertaComercialEntity();
            oferta.setIdOfertaInterno(castNumberToString(offer.getId()));
            oferta.setIdOfertaRtd(castNumberToString(offer.getCommercialAction().getId()));
            oferta.setCategoriaOferta(offer.getCampaign().getDescription());
            oferta.setUrl(offer.getCommercialAction().getImage());
            oferta.setTitulo(offer.getCommercialAction().getTitle());
            oferta.setOrdenPrioridad(castNumberToString(offer.getOrder()));
            oferta.setLink(offer.getCommercialAction().getLink().getGoTo());
            oferta.setDescripcion("");
            oferta.setUbicacionCarrusel("");
            oferta.setUbicacionSeccion("");
            oferta.setDescripcionSeccion("");
            oferta.setIndicadorClicSeccion("");
            oferta.setFeedbackAvailable(offer.getFeedbackAvailable());
            oferta.setGotoLink(new GotoLink(oferta.getLink()));
            oferta.setTipoOferta(castNumberToString(offer.getCampaign().getId()));
            oferta.setTipoProductoLoyalty(offer.getCampaign().getProduct() != null ? offer.getCampaign().getProduct().getName() : null);
            if (offer.getSection().equalsIgnoreCase("MOBP")) {
                oferta.setDescripcion(offer.getCommercialAction().getMessage());
                oferta.setUbicacionCarrusel("S");
            } else {
                oferta.setUbicacionSeccion(offer.getSection());
                oferta.setSeccionOferta(offer.getSection());
                oferta.setDescripcionSeccion(offer.getCommercialAction().getMessage());
                oferta.setIndicadorClicSeccion(offer.getCommercialAction().getLink().getGoTo() != null ? "S" : "");
                oferta.setOrigen(offer.getSegmentationOrigin());
            }
            ofertasComerciales.add(oferta);   
        }
        ofertasComercialesEntity.setOfertas(ofertasComerciales);
        ofertasComercialesEntity.setCantOfertas(String.valueOf(ofertasComerciales.size()));
    }

    private String castNumberToString(Object num){
        return num!=null ? num.toString() : null;
    }
  
    /**
     * Genera la firma de los datos necesario para consultar ofertas comerciales.
     *
     * @param cliente
     *                    the cliente
     * @param dispositivo
     *                    the dispositivo
     * @return the string
     * @throws DAOException
     *                      the DAO exception
     */
    private String generarFirmaOfertaComercial(Cliente cliente, String dispositivo) throws DAOException {
        ParametroFirmaOfertaComercial parametrosFirma = new ParametroFirmaOfertaComercial(cliente, dispositivo);
        return generarDatosFirmadosJson(parametrosFirma, jks);
    }

    /**
     * Generar datos firmados json.
     *
     * @param object
     *               the object
     * @param jks
     *               the jks
     * @return the string
     * @throws DAOException
     *                      the DAO exception
     */
    public String generarDatosFirmadosJson(Object object, String jks) throws DAOException {
        LOGGER.info("Generando Firma");
        byte[] firma;
        ObjectMapper mapper = new ObjectMapper();
        String msgException;

        try {

            String datosSinFirmar = mapper.writeValueAsString(object);
            LOGGER.info("Datos a firmar: {}", datosSinFirmar);
            firma = sign.buildB64Signature(datosSinFirmar.getBytes(appEncoding), jks, true);
            return new String(firma);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error(ERROR_FIRMA, e);
            msgException = e.getMessage();
        } catch (JsonGenerationException e1) {
            LOGGER.error(ERROR_FIRMA, e1);
            msgException = e1.getMessage();
        } catch (JsonMappingException e2) {
            LOGGER.error(ERROR_FIRMA, e2);
            msgException = e2.getMessage();
        } catch (IOException e3) {
            LOGGER.error(ERROR_FIRMA, e3);
            msgException = e3.getMessage();
        }
        throw new DAOException(msgException);

    }

    /**
     * Generar datos firmados json default encoding.
     *
     * @param object
     *               the object
     * @param jks
     *               the jks
     * @return the string
     * @throws DAOException
     *                      the DAO exception
     */
    public String generarDatosFirmadosJsonDefEnc(Object object, String jks) throws DAOException {
        LOGGER.info("Generando Firma");
        byte[] firma;
        ObjectMapper mapper = new ObjectMapper();
        String msgException;

        try {

            String datosSinFirmar = mapper.writeValueAsString(object);
            LOGGER.info("Datos a firmar: {}", datosSinFirmar);
            firma = sign.buildB64Signature(datosSinFirmar.getBytes(), jks, true);
            return new String(firma);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error(ERROR_FIRMA, e);
            msgException = e.getMessage();
        } catch (JsonGenerationException e1) {
            LOGGER.error(ERROR_FIRMA, e1);
            msgException = e1.getMessage();
        } catch (JsonMappingException e2) {
            LOGGER.error(ERROR_FIRMA, e2);
            msgException = e2.getMessage();
        } catch (IOException e3) {
            LOGGER.error(ERROR_FIRMA, e3);
            msgException = e3.getMessage();
        }
        throw new DAOException(msgException);

    }

    /**
     * Actualiza el estado de las notificaciones en caso de ser leidas.
     *
     * @param cliente
     *                the cliente
     * @return the notificaciones out entity
     * @throws DAOException
     *                      the DAO exception
     */
    @Override
    public GestionEventoComercialOutEntity actualizarNotificaciones(Cliente cliente) throws DAOException {
        LOGGER.info("Actualizando notificaciones leidas.");
        Map<String, String> datosFirma = cargaParametrosFirma(cliente, "4", "actNotifLeidas");
        LOGGER.info(DATOS_PARA_FIRMA, datosFirma);
        String firma = generarDatosFirmadosJson(datosFirma, jks);
        LOGGER.info(FIRMA, firma);

        GestionEventoComercialOutEntity entity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), GestionEventoComercialOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - actualizarNotificaciones: {}.", entity);
        return entity;
    }

    /**
     * Informa el interes que tiene un cliente en una notificacion.
     *
     * @param cliente
     *                       the cliente
     * @param idNotificacion
     *                       the id notificacion
     * @return the notificaciones out entity
     * @throws DAOException
     *                      the DAO exception
     */
    @Override
    public GestionEventoComercialOutEntity informarInteresEnNotificacion(Cliente cliente, String idNotificacion)
            throws DAOException {
        LOGGER.info("Informando interes en una notificacion.");
        Map<String, String> datosFirma = cargaParametrosFirma(cliente, "6", "informarInteresNotif");
        datosFirma.put("id_notificacion", idNotificacion);
        LOGGER.info(DATOS_PARA_FIRMA, datosFirma);
        String firma = generarDatosFirmadosJson(datosFirma, jks);
        LOGGER.info(FIRMA, firma);

        GestionEventoComercialOutEntity entity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), GestionEventoComercialOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - informarInteresNotif: {}.", entity);
        return entity;
    }


    @Override
    public Boolean informarFeedbackOferta(Cliente cliente, String seccion, OfertaComercialDTO ofertaComercialDTO, String action)
            throws DAOException {
        LOGGER.info("Informando feedback de una oferta.");
        seccion = seccion == null ? "MOBP" : seccion;
        FeedbackMessage feedback = new FeedbackMessage(
                "MOBP",
                cliente.getNup(),
                seccion,
                action,
                Long.parseLong(ofertaComercialDTO.getIdInterno()),
                Integer.parseInt(ofertaComercialDTO.getIdRtd()),
                Integer.parseInt(ofertaComercialDTO.getOrdenPrioridad())
        );

        LOGGER.info("Feedback - informarFeedbackOferta: {}.", feedback);

        LOGGER.info(
            "Respuesta de Notifications Service - informarFeedbackOferta: {}.",
            notificationsApi.sendMessageToTopic(feedback, "XSELL-feedback_commercials-offers")
        );
        return true;
    }

    /**
     * Registrar encuesta.
     *
     * @param cliente
     *                              the cliente
     * @param eventosComercialesDTO
     *                              the eventos comerciales DTO
     * @return true si es COD OK
     * @throws DAOException
     *                      the DAO exception
     */
    @Override
    public Boolean registrarEncuesta(Cliente cliente, EventosComercialesDTO eventosComercialesDTO)
            throws DAOException {
        LOGGER.info("Registrando encuesta.");
        RegistrarEncuestaEntity parametrosFirma = new RegistrarEncuestaEntity(cliente.getNup(), eventosComercialesDTO);

        LOGGER.info(DATOS_PARA_FIRMA, parametrosFirma);
        String firma = generarDatosFirmadosJsonDefEnc(parametrosFirma, jks);
        LOGGER.info(FIRMA, firma);

        GestionEventoComercialOutEntity entity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), GestionEventoComercialOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - registrarEncuesta: {}.", entity);
        return StringUtils.equalsIgnoreCase(COD_OK, entity.getCodigoError());
    }

    /**
     * Checks if is notificacion form UMAUFI.
     *
     * @param gotoLink the goto link
     * @return the boolean
     * @throws DAOException the DAO exception
     */
    public Boolean isNotificacionFormUMAUFI(String gotoLink)
            throws DAOException {
        return "gotoFormularioIMAUIF()".equals(gotoLink);
    }

    /**
     * Armarlink notificacion form UMAUFI.
     *
     * @param notificacion the notificacion
     * @return the string
     * @throws DAOException the DAO exception
     */
    public String armarlinkNotificacionFormUMAUFI(NotificacionOutEntity notificacion)
            throws DAOException {
        String linkNotificacion = notificacion.getLinkPortal().replaceAll("\\\\", "/");

        String[] linkPortal = notificacion.getLink().split("\\(");
        String gotoLink = linkPortal[0];

        return gotoLink + "(" + linkNotificacion + ")";
    }

    /**
     * Informar gestion AC.
     *
     * @param cliente              the cliente
     * @param domicilioDeReemplazo the domicilio de reemplazo
     * @param ofertaRecambioChip   the oferta recambio chip
     * @return true si es COD OK
     * @throws DAOException the DAO exception
     */
    @Override
    public Boolean informarGestionAC(Cliente cliente, DatosDeDomicilioDTO domicilioDeReemplazo,
            OfertaComercialDTO ofertaRecambioChip)
            throws DAOException {
        LOGGER.info("Informando gestion AC.");
        InformarGestionACEntity parametrosFirma = new InformarGestionACEntity(cliente.getNup(), domicilioDeReemplazo,
                ofertaRecambioChip);

        LOGGER.info(DATOS_PARA_FIRMA, parametrosFirma);
        String firma = generarDatosFirmadosJsonDefEnc(parametrosFirma, jks);
        LOGGER.info(FIRMA, firma);

        GestionEventoComercialOutEntity entity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), GestionEventoComercialOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - informarGestionAC: {}.", entity);
        return StringUtils.equalsIgnoreCase(COD_OK, entity.getCodigoError());
    }

    /**
     * Informar gestion AC.
     *
     * @param cliente the cliente
     * @param oferta  the oferta
     * @return the boolean
     * @throws DAOException the DAO exception
     */
    @Override
    public Boolean informarGestionAC(Cliente cliente, OfertaComercialDTO oferta)
            throws DAOException {
        LOGGER.info("Informando gestion AC.");
        InformarGestionACEntity parametrosFirma = new InformarGestionACEntity(cliente.getNup(), oferta, "");

        LOGGER.info(DATOS_PARA_FIRMA, parametrosFirma);
        String firma = generarDatosFirmadosJsonDefEnc(parametrosFirma, jks);
        LOGGER.info(FIRMA, firma);

        GestionEventoComercialOutEntity entity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), GestionEventoComercialOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - informarGestionAC: {}.", entity);
        return StringUtils.equalsIgnoreCase(COD_OK, entity.getCodigoError());
    }

    /**
     * obtener premiaciones.
     *
     * @param cliente     the cliente
     * @param dispositivo
     * @return the chance out entity
     * @throws DAOException the DAO exception
     */
    @Cacheable(cacheNames = { CacheConstants.Names.CACHE_CHANCES })
    @Override
    public ChanceOutEntity obtenerPremiaciones(String dispositivo, Cliente cliente) throws DAOException {
        LOGGER.info("Informando obtener premiaciones.");
        ChanceEntity parametrosFirma = new ChanceEntity(dispositivo, cliente);

        LOGGER.info(DATOS_PARA_FIRMA, parametrosFirma);
        String firma = generarDatosFirmadosJsonDefEnc(parametrosFirma, jks);
        LOGGER.info(FIRMA, firma);

        ChanceOutEntity chanceOutEntity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), ChanceOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - obtenerPremiaciones: {}.", chanceOutEntity);
        return chanceOutEntity;
    }

    /**
     * obtener premiaciones periodo actual.
     *
     * @param cliente the cliente
     * @return the periodo actual out entity
     * @throws DAOException the DAO exception
     */
    @Override
    public PeriodoActualOutEntity premiacionesPeriodoActual(Cliente cliente) throws DAOException {
        LOGGER.info("Informando premiaciones periodo actual.");
        PeriodoActualEntity parametrosFirma = new PeriodoActualEntity(cliente);

        LOGGER.info(DATOS_PARA_FIRMA, parametrosFirma);
        String firma = generarDatosFirmadosJsonDefEnc(parametrosFirma, jks);
        LOGGER.info(FIRMA, firma);

        PeriodoActualOutEntity periodoActualOutEntity = obtenerClienteRest().post(
                new ParametroGestorComercialInEntity(cliente.getNup(), firma), PeriodoActualOutEntity.class);
        LOGGER.info("Respuesta del WS GEC - premiacionesPeriodoActual: {}.", periodoActualOutEntity);
        return periodoActualOutEntity;
    }

    @Override
    public FinalizarPromesaEntity finalizarPromesaPago(Cliente cliente, FinalizarPromesaPagoInView inView)
            throws DAOException {
        LOGGER.info("Entro al DAO para Finalizar Promesa De Pago");
        Map<String, String> datosFirma = cargaParametrosFirmaFinalizar(cliente, inView);
        datosFirma.put(DISPOSITIVO, DESKTOP);
        String firma = generarDatosFirmadosJson(datosFirma, jks);
        try {
            FinalizarPromesaEntity finalizarPromesaEntity = obtenerClienteRest().post(
                    new ParametroGestorComercialInEntity(cliente.getNup(), firma),
                    FinalizarPromesaEntity.class);
            if (!StringUtils.equalsIgnoreCase(COD_OK, finalizarPromesaEntity.getCodigoError())) {
                LOGGER.error("El WS devolvio un error con codigo: {} y descripcion: {}.",
                        finalizarPromesaEntity.getCodigoError(), finalizarPromesaEntity.getDescripcionError());
                throw new DAOException(finalizarPromesaEntity.getDescripcionError(),
                        finalizarPromesaEntity.getCodigoError());
            }
            return finalizarPromesaEntity;

        } catch (RuntimeException e) {
            LOGGER.error("Error al finalizar promesa de pago");
            throw new DAOException(e);
        }
    }

    private Map<String, String> cargaParametrosFirmaFinalizar(Cliente cliente, FinalizarPromesaPagoInView inView) {
        Map<String, String> parametros = new HashMap<String, String>();

        String fechaActual = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date());
        String[] fecha = fechaActual.split("-");
        String fecha1 = fecha[0];
        String fecha2 = fecha[1];
        String[] formatoDias = fecha1.split("/");
        String[] formatoHoras = fecha2.split(":");
        String[] montoConFormato = inView.getImportePromesa().replaceAll("\\$", "").replaceAll("\\.", "").trim()
                .split("\\,");
        String[] fechaEnvio = inView.getFechaPromesa().split("/");
        parametros.put("nup", cliente.getNup());
        parametros.put("cod_evento", "24");
        parametros.put("nombre_evento", "gestionarPromesa");
        parametros.put("cod_notificacion", "PR0023");
        parametros.put("id_notificacion", sesionParametros.getIdNotificacionPromesaPago());
        parametros.put("fecha_promesa", fechaEnvio[0] + fechaEnvio[1] + fechaEnvio[2]);
        parametros.put("fecha_envio",
                formatoDias[0] + formatoDias[1] + formatoDias[2].trim() + formatoHoras[0].trim() + formatoHoras[1]);
        parametros.put("importe_promesa", montoConFormato[0]);
        parametros.put("observaciones", "");
        return parametros;
    }

    /**
     * Obtener ofertas financiacion.
     *
     * @param nup the nup
     * @return the list
     * @throws DAOException the DAO exception
     */
    @Override
    public List<OfertaFinanciacionEntity> obtenerOfertasFinanciacion(String nup) throws DAOException {
        LOGGER.info("obtenerOfertasFinanciacion()");

        WebClient service = getRefinanciacionesService();

        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(PATH_OFFERS);
        service.query("nup", nup);
        service.query("state", "VALIDA");

        OfferResponseDto response = service.get(OfferResponseDto.class);

        checkGetOffersError(service, response);

        return mapToEntity(response.getResults());
    }

    private WebClient getRefinanciacionesService() throws DAOException {
        WebClient service = restWebClient.obtenerClienteRest(FINANCIACIONES);
        Credential credential;
        credential = new Credential();
        credential.setUsuario("X-API-KEY");
        credential.setPassword("123456");
        try {
            credential = credentialSecurityFactory.getCredentialById(financiacionesSecId);
        } catch (SQLException e) {
            LOGGER.error("Error al obtener credenciales: {}", e);
            throw new DAOException(e);
        }
        String header = credential.getUsuario();
        String password = credential.getPassword();
        service.header(header, password);
        service.header("X-CLIENT", OBP_CHANNEL);
        return service;
    }

    private void checkGetOffersError(WebClient service, OfferResponseDto response) throws DAOException {

        int statusCode = service.getResponse().getStatus();
        if (statusCode != Response.Status.OK.getStatusCode()) {
            LOGGER.error("Error al obtener ofertas de financiacion");
            if (statusCode == Response.Status.NOT_FOUND.getStatusCode() ||
                    statusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                OfertasFinanciacionErrorEntity error = response.getErrors().get(0);
                LOGGER.error("Detalle de error: {} - {}", error.getCode(), error.getMessage());
                throw new DAOException(error.getMessage());
            }
            throw new DAOException("Error al obtener ofertas de financiacion");
        }
    }

    private List<OfertaFinanciacionEntity> mapToEntity(List<OfferDto> offers) {
        ArrayList offerList = new ArrayList();
        for (OfferDto offer : offers) {
            for (TermResponseDto term : offer.getTerms()) {
                OfertaFinanciacionEntity entity = new OfertaFinanciacionEntity();
                entity.setOfferId(offer.getOfferId().toString() + "0" + term.getTerm());
                entity.setNup(offer.getNup());
                entity.setProductDescription(offer.getRefinancing().getDescription());
                entity.setRefinancingType(offer.getRefinancing().getType());
                entity.setTerm(term.getTerm().toString());
                entity.setValidityDate(offer.getValidityDate());
                entity.setState(offer.getState());
                entity.setMaxAmount(offer.getMaxAmount().toEngineeringString());
                entity.setRate(term.getTna().toString());
                entity.setFirstQuotaValue(term.getFirstQuotaValue().toString());
                entity.setInterest(term.getInterest().toString());
                entity.setIva(term.getIva().toString());
                entity.setInsurance(term.getInsurance().toString());
                entity.setOtherCharges(term.getOtherCharges().toString());
                entity.setPureQuotaValue(term.getPureQuotaValue().toString());
                entity.setCft(term.getCft().toString());
                entity.setRateType(offer.getRefinancing().getRateType());
                entity.setTna(term.getTna().toString());
                entity.setTea(term.getTea().toString());
                RefinancingProductDto productDto = offer.getProducts().get(0);
                entity.setCreditCardDescription(productDto.getBrand() + " - " + productDto.getLastDigits());
                offerList.add(entity);
            }

        }
        return offerList;
    }

    /**
     * Solicitar financiacion.
     *
     * @param cliente                     the cliente
     * @param solicitarFinanciacionEntity the solicitar financiacion entity
     * @return the boolean
     * @throws DAOException the DAO exception
     * @throws IOException  Signals that an I/O exception has occurred.
     */
    @Override
    public Boolean solicitarFinanciacion(Cliente cliente, SolicitarFinanciacionEntity solicitarFinanciacionEntity)
            throws DAOException, IOException {
        LOGGER.info("solicitarFinanciacion()");
        WebClient service = getRefinanciacionesService();
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(PATH_PROCESSES);

        List<ProcessRequestDto> processesRequest = toProcessRequest(cliente.getNup(), solicitarFinanciacionEntity);
        for (ProcessRequestDto processRequest : processesRequest) {
            checkCreateProcessError(service, service.post(processRequest), processRequest, cliente);
        }
        return Boolean.TRUE;
    }

    private void checkCreateProcessError(WebClient service, Response response, ProcessRequestDto processRequest,
            Cliente cliente) throws IOException, DAOException {
        int statusCode = service.getResponse().getStatus();
        if (statusCode != Response.Status.OK.getStatusCode()) {
            LOGGER.error("Error al solicitar financiacion");
            if (statusCode == Response.Status.NOT_FOUND.getStatusCode() ||
                    statusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {

                estadisticaManager.add(obtenerestadisticaSolicitarFinanciacion(cliente),
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, processRequest.getOfferId().toString(),
                        processRequest.getAmountRequested(), MonedaEspecieEnum.ARS.getCodigo());

                Object entity = response.getEntity();
                InputStream is = InputStream.class.cast(entity);
                if (is != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    OfertasFinanciacionResponse respuesta = mapper.readValue(is, OfertasFinanciacionResponse.class);
                    OfertasFinanciacionErrorEntity error = respuesta.getErrors().get(0);
                    LOGGER.error("Detalle de error: {} - {}", error.getCode(), error.getMessage());
                    throw new DAOException(error.getMessage());
                }
                throw new DAOException("Error al solicitar ofertas de financiacion");
            }
            throw new DAOException("Error al solicitar ofertas de financiacion");
        } else {
            estadisticaManager.add(obtenerestadisticaSolicitarFinanciacion(cliente),
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK, processRequest.getOfferId().toString(),
                    processRequest.getAmountRequested(), MonedaEspecieEnum.ARS.getCodigo());
        }
    }

    private String obtenerestadisticaSolicitarFinanciacion(Cliente cliente) {

        String tipoOfertaRefinanciacion = cliente.getTipoOfertaRefinanciacion();
        if (Cliente.CUOTAPHONE.equals(tipoOfertaRefinanciacion))
            return EstadisticasConstants.SOLICITAR_FINANCIACION_CUOTAPHONE;

        if (Cliente.EARLY_CURRENT.equals(tipoOfertaRefinanciacion))
            return EstadisticasConstants.SOLICITAR_FINANCIACION_EARLY_CURRENT;

        return EstadisticasConstants.SOLICITAR_FINANCIACION_EARLY_REGULAR;
    }


    private List<ProcessRequestDto> toProcessRequest(String nup,
            SolicitarFinanciacionEntity solicitarFinanciacionEntity) {

        List<SolicitudFinanciacionEntity> offersRequested = solicitarFinanciacionEntity.getOffers();
        List<ProcessRequestDto> processesRequest = new ArrayList<ProcessRequestDto>();
        for (SolicitudFinanciacionEntity offerRequested : offersRequested) {
            ProcessRequestDto processRequest = new ProcessRequestDto(
                    toOfferId(offerRequested.getOfferId()),
                    nup,
                    Integer.valueOf(offerRequested.getTermRequested()),
                    LocalDate.parse(offerRequested.getFirstDueDate()),
                    BigDecimal.valueOf(Double.parseDouble(offerRequested.getAmountRequested())),
                    BigDecimal.valueOf(Double.parseDouble(offerRequested.getDeclaredIncome())),
                    offerRequested.getMail());
            processesRequest.add(processRequest);
        }
        return processesRequest;
    }

    // eliminar cuando el front deje de concatenar el offerId + 0 + term
    private long toOfferId(String offerId) {
        return Long.valueOf(offerId.substring(0, offerId.length() - 3));
    }

    public ProcessResultResponseDto getProcessesCreatedToday(String nup) throws DAOException {

        WebClient service = getRefinanciacionesService();
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(PATH_PROCESSES);
        service.query("nup", nup);
        service.query("creationDate", LocalDate.now());

        return service.get(ProcessResultResponseDto.class);

    }
}
