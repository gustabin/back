/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.chat.ISecurity;
import ar.com.santanderrio.obp.generated.webservices.chat.Result;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatClientesHabilitadosDAO;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatDAO;
import ar.com.santanderrio.obp.servicios.chat.dto.ConectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatConfigEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailRequestEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatTokenEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import oracle.jdbc.OracleTypes;

/**
 * The Class ChatDAOImpl.
 */
@Component
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class ChatDAOImpl extends BaseDatoDAOImpl implements ChatDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatDAOImpl.class);

    /** The Constant CHAT_JKS. */
    private static final String CHAT_JKS = "CHAT";

    /** The chat msg. */
    private static final String CHAT_DATA = "CHAT.DATA";

    /** The chat mail. */
    private static final String CHAT_MAIL = "CHAT.MAIL";

    /** The Constant SOLICITUD_SCHEMA. */
    private static final String CHAT_SCHEMA = "hbank";

    /** The Constant SOLICITUD_PACKAGE. */
    private static final String CHAT_PACKAGE = "HB_PKG_CLIENTE_CHAT";

    /** The Constant CONSULTAR_CLIENTE_CHAT. */
    private static final String CONSULTAR_CLIENTE_CHAT = "CONSULTAR_CLIENTE_CHAT";

    /** The Constant IN_NUP. */
    private static final String IN_NUP = "L_NUP";

    /** The Constant OUT_RESULTADO. */
    private static final String OUT_RESULTADO = "p_resultado";

    /** The Constant OUT_ERROR_TECNICO. */
    private static final String OUT_ERROR_TECNICO = "p_err_tecnico";

    /** The Constant OUT_ERROR_AMIGABLE. */
    private static final String OUT_ERROR_AMIGABLE = "p_err_amigable";

    /** The ws chat client. */
    @Autowired
    @Qualifier("chatSecurityGestor")
    private GestionarWS<ISecurity> wsChatClient;

    /** The sign. */
    @Autowired
    private Sign sign;

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

    /** The clientes habilitados. */
    @Autowired
    private ChatClientesHabilitadosDAO clientesHabilitados;

    /** The hora desde. */
    @Value("${CHAT.HORA.DESDE}")
    private String horaDesde;

    /** The hora hasta. */
    @Value("${CHAT.HORA.HASTA}")
    private String horaHasta;

    /** The hora hasta msj. */
    @Value("${CHAT.HORA.HASTA.MSJ}")
    private String horaHastaMsj;

    /** The mail url. */
    @Value("${CHAT.MAIL.URL}")
    private String mailUrl;

    /** The data url. */
    @Value("${CHAT.DATA.URL}")
    private String dataUrl;

    /** The csrf url. */
    @Value("${CHAT.CSRF.URL}")
    private String csrfUrl;

    /** The close timeout. */
    @Value("${CHAT.CLOSE.TIMEOUT}")
    private Integer closeTimeout;

    /** The connection timeout. */
    @Value("${CHAT.CONNECTION.TIMEOUT}")
    private Integer connectionTimeout;

    /** The session timeout. */
    @Value("${CHAT.SESSION.TIMEOUT}")
    private Integer sessionTimeout;

    /** The Constant CANAL. */
    @Value("${CHAT.DATA.CANAL}")
    private String canal;

    /** The Constant CANAL. */
    @Value("${CHAT.WATSON.NICKNAME}")
    private String watsonNickname;
    
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;

    /**
	 * Levantar properties y clientes habilitados.
	 *
	 * @param nup
	 *            the nup
	 * @return the ChatConfiguracionEntity
	 * @throws DAOException
	 *             the DAO exception
	 * Se agrega modulo permisos para la llamada al SP. Se habilita el chat a todos los clientes 081019 
	 */
    public ChatConfigEntity obtenerConfiguracion(String nup) throws DAOException {

        LOGGER.info("Obtener configuración Chat");

        Boolean isChatHabilitado = false;

        if (!moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.CHAT_ALL)
                .tienePermisoDeVisibilidad()) {
        	try {
                List<SqlParameter> parametros = new ArrayList<SqlParameter>();
                parametros.add(new SqlParameter(IN_NUP, OracleTypes.NUMBER));
                parametros.add(new SqlOutParameter(OUT_RESULTADO, OracleTypes.NUMBER));
                parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, OracleTypes.VARCHAR));
                parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, OracleTypes.VARCHAR));

                SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, Integer.parseInt(nup));
                Map<String, Object> respuesta = consultar(CHAT_SCHEMA, CHAT_PACKAGE, CONSULTAR_CLIENTE_CHAT, in,
                        parametros.toArray(new SqlParameter[parametros.size()]));

                LOGGER.info("Cliente tiene chat habilitado: {} .", respuesta.get(OUT_RESULTADO));
                BigDecimal resultado = (BigDecimal) respuesta.get(OUT_RESULTADO);
                if (resultado.intValue() > 0) {
                    isChatHabilitado = true;
                } else if (resultado.intValue() == -1) {
                    throw new DAOException("No se pudo verificar la habilitacion de Chat.");
                }

            } catch (SQLException e) {
                LOGGER.error("Error al consumir el store {}.{}.{}.", CHAT_SCHEMA, CHAT_PACKAGE, CONSULTAR_CLIENTE_CHAT, e);
                throw new DAOException(e);
            } catch (UncategorizedSQLException e) {
                LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", CHAT_SCHEMA, CHAT_PACKAGE,
                        CONSULTAR_CLIENTE_CHAT, e);
                throw new DAOException(e);
            } catch (InvalidDataAccessApiUsageException e) {
                LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", CHAT_SCHEMA, CHAT_PACKAGE,
                        CONSULTAR_CLIENTE_CHAT, e);
                throw new DAOException(e);
            }
        }else {
        	isChatHabilitado = true;
        }
       

        ChatConfigEntity chatConfiguracion = new ChatConfigEntity();
        chatConfiguracion.setIsChatHabilitado(isChatHabilitado);
        chatConfiguracion.setHoraDesde(horaDesde);
        chatConfiguracion.setHoraHasta(horaHasta);
        chatConfiguracion.setHoraHastaMsj(horaHastaMsj);
        chatConfiguracion.setMailUrl(mailUrl);
        chatConfiguracion.setDataUrl(dataUrl);
        chatConfiguracion.setCsrfUrl(csrfUrl);
        chatConfiguracion.setCloseTimeout(closeTimeout);
        chatConfiguracion.setConnectionTimeout(connectionTimeout);
        chatConfiguracion.setSessionTimeout(sessionTimeout);
        chatConfiguracion.setWatsonNickname(watsonNickname);

        return chatConfiguracion;
    }

    /**
     * Conectar genesys.
     *
     * @param conectarInDTO
     *            the conectar in DTO
     * @return the object
     * @throws DAOException
     *             the DAO exception
     */
    public ChatResponseEntity conectarGenesys(ConectarInDTO conectarInDTO) throws DAOException {

        LOGGER.info("Conectando Genesys");

        ISecurity services = null;
        try {
            services = wsChatClient.obtenerPort();

            conectarInDTO.getToken().setCanal(canal);
            String token = tokenToString(conectarInDTO.getToken());

            LOGGER.info("Chat : Se firmara : " + token);

            String tokenFirmado = sign.buildPemSignature(token.getBytes(), CHAT_JKS, true);
            Result respuesta = services.addToken(conectarInDTO.getNup(), conectarInDTO.getJSessionId(), tokenFirmado);
            LOGGER.info("Respuesta Chat firma token: " + respuesta.getCode());

            if (respuesta.getCode() != 0) {
                LOGGER.error("Hubo un error al firmar el token. Codigo: " + respuesta.getCode());
                throw new DAOException("Hubo un error al firmar el token");
            }
        } catch (RuntimeException e) {
            LOGGER.error("Hubo un error al invocar al ws de seguridad de Chat.", e);
            throw new DAOException("Hubo un error al firmar el token");
        } finally {
            wsChatClient.liberarPort(services);
        }

        ChatResponseEntity response;
        try {
            Form request = generarRequestConexion(conectarInDTO);
            LOGGER.info("Invocacion WS: {}.", request.asMap());

            WebClient service = this.obtenerClienteRestList(CHAT_DATA);

            response = service.post(request, ChatResponseEntity.class);

            LOGGER.info("Respuesta WS: {}.", response);

        } catch (ResponseProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException(e);
        }

        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.chat.dao.ChatDAO#desconectarGenesys(ar.com.
     * santanderrio.obp.servicios.chat.entities.ChatSessionEntity)
     */
    @Override
    public ChatResponseEntity desconectarGenesys(DesconectarInDTO desconectarInDto) throws DAOException {

        ChatResponseEntity response;

        Form request = generarRequestChatDisconnect(desconectarInDto);
        LOGGER.info("Invocacion WS: {}.", request.asMap());
        try {

            WebClient service = this.obtenerClienteRestList(CHAT_DATA);
            service.path(desconectarInDto.getChatId() + " /disconnect");

            response = service.post(request, ChatResponseEntity.class);

            LOGGER.info("Respuesta WS: {}.", response);

        } catch (ResponseProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException(e);
        }
        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.chat.dao.ChatDAO#enviarEmailGenesys(ar.com.
     * santanderrio.obp.servicios.chat.entities.RequestChatEmailEntity)
     */
    @Override
    public ChatEmailResponseEntity enviarEmailGenesys(ChatEmailRequestEntity requestChatEmailEntity)
            throws DAOException {
        ChatEmailResponseEntity response;

        Form request = generarRequestChatEmail(requestChatEmailEntity);

        LOGGER.info("Invocacion WS: {}.", request);

        try {
            WebClient service = this.obtenerClienteRestList(CHAT_MAIL);

            response = service.post(request, ChatEmailResponseEntity.class);

            LOGGER.info("Respuesta WS: {}.", response);
        } catch (ResponseProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException(e);
        }
        return response;
    }

    /**
     * Obtener cliente rest.
     *
     * @param endpoint
     *            the endpoint
     * @return the web client
     * @throws DAOException
     *             the DAO exception
     */

    private WebClient obtenerClienteRestList(String endpoint) throws DAOException {
        WebClient cliente = restWebClient.obtenerClienteRest(endpoint);
        cliente.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept(MediaType.APPLICATION_JSON_TYPE);
        return cliente;
    }

    /**
     * Generar request chat.
     *
     * @param conectarInDTO
     *            the request
     * @return the form
     */
    private Form generarRequestConexion(ConectarInDTO conectarInDTO) {
        Form form = new Form();

        form.param("firstName", conectarInDTO.getNombre());
        form.param("lastName", conectarInDTO.getApellido());
        form.param("userData[NUP]", conectarInDTO.getNup());
        form.param("userData[JSESSIONID]", conectarInDTO.getJSessionId());
        form.param("userData[USR_31_source_channel]", canal);

        return form;
    }

    /**
     * Generar request chat.
     *
     * @param request
     *            the request
     * @return the map
     */
    private Form generarRequestChatDisconnect(DesconectarInDTO request) {
        Form form = new Form();

        form.param("userId", request.getUserId());
        form.param("alias", request.getAlias());
        form.param("secureKey", request.getSecureKey());

        return form;
    }

    /**
     * Generar request chat email.
     *
     * @param request
     *            the request
     * @return the form
     */
    private Form generarRequestChatEmail(ChatEmailRequestEntity request) {
        Form form = new Form();

        form.param("firstName", request.getFirstName());
        form.param("fromAddress", request.getFromAddress());
        form.param("lastName", request.getLastName());
        form.param("subject", request.getSubject());
        form.param("tenantName", request.getTenantName());
        form.param("text", request.getText());
        if (!ISBANStringUtils.isEmptyOrNull(request.getMailBox())) {
        	form.param("mailbox", request.getMailBox());	
        }
        form.param("userData", request.getUserData());
        form.param("userData", request.getUserData2());

        return form;
    }

    /**
     * Token to string.
     *
     * @param token
     *            the token
     * @return the string
     */
    private String tokenToString(ChatTokenEntity token) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("NUP=");
        buffer.append(token.getNup());
        buffer.append("|");
        buffer.append("JSESSIONID=");
        buffer.append(token.getJSessionID());
        buffer.append("|");
        buffer.append("DOCUMENTO=");
        buffer.append(token.getDocumento());
        buffer.append("|");
        buffer.append("APELLIDO=");
        buffer.append(token.getApellido());
        buffer.append("|");
        buffer.append("NOMBRE=");
        buffer.append(token.getNombre());
        buffer.append("|");
        buffer.append("SEGMENTOOBP=");
        buffer.append(token.getSegmento());
        buffer.append("|");
        buffer.append("EMAILMYA=");
        buffer.append(token.getMail());
        buffer.append("|");
        buffer.append("FECHANACIMIENTO=");
        buffer.append(token.getFechaNacimiento());
        buffer.append("|");
        buffer.append("NROTARJETAVISA=");
        buffer.append(token.getTarjetaVisa());
        buffer.append("|");
        buffer.append("NROTARJETAAMEX=");
        buffer.append(token.getTarjetaAmex());
        buffer.append("|");
        buffer.append("NROTARJETADEBITO=");
        buffer.append(token.getTarjetaDebito());
        buffer.append("|");
        buffer.append("NROCUENTA=");
        buffer.append(token.getNumeroCuenta());
        buffer.append("|");
        buffer.append("USR_31_source_channel=");
        buffer.append(token.getCanal());
        buffer.append("|");
        buffer.append("intencionGoto=");
        buffer.append(token.getIntencionGoto());
        buffer.append("|");
        buffer.append("idGEC=");
        buffer.append(token.getIdGEC());
        buffer.append("|");
        buffer.append("idInternoGEC=");
        buffer.append(token.getIdInternoGEC());
        buffer.append("|");
        return buffer.toString();
    }

}
