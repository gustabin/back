package ar.com.santanderrio.obp.servicios.loggedinusercache.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHeaders;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.loggedinusercache.entities.BodyCache;
import ar.com.santanderrio.obp.servicios.loggedinusercache.entities.CacheEntity;
import ar.com.santanderrio.obp.servicios.loggedinusercache.entities.CacheServiceEnum;

@Component
public class UserCacheDAOImpl implements UserCacheDAO {

    private static final String ERROR_JSON = "Error json: {}";

	private static final String OBP_API_USERS_INVOCANDO_A_OBP_API_USERS = "[obp-api-users] Invocando a obp-api-users {}";

	private static final String CACHE_GET = "/cache/get/";

    private static final String CACHE_SET = "/cache/set/";

    private static final String CLEAR_CACHE = "/cache/clearcache";

    @Autowired
    private RestWebClient restWebClient;

    /** The Constant NOMBRE_WS. */
    private static final String NOMBRE_WS = "USERCACHE";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCacheDAOImpl.class);

    private static final String BEARER = "Bearer ";

    @Value("${USERCACHE.URL}")
    private String userCacheUrl;
    
    /**
     * @param pathDeConsulta
     * @return
     * @throws DAOException
     */
    private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

        WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
        service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);
        return service;
    }

    @Override
    public <T> T getCache(String token, CacheServiceEnum cacheService) throws DAOException {
        LOGGER.info(OBP_API_USERS_INVOCANDO_A_OBP_API_USERS, cacheService.getServiceName());
        WebClient service = crearLlamadaAWebService(CACHE_GET + cacheService.getServiceName());
        service.header(HttpHeaders.AUTHORIZATION, BEARER + token);
        Response respuesta = service.get();
        Object entity = respuesta.getEntity();
        InputStream is = InputStream.class.cast(entity);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            List<CacheEntity> response = Arrays.asList(mapper.readValue(IOUtils.toString(is, "UTF-8"), CacheEntity[].class));
            if(response.isEmpty()) {
                return null;
            }
            String body = response.get(0).getBody();
            BodyCache<T> bodyCache = parseCache(mapper, body, cacheService.getServiceClass());
            return bodyCache.getBody();
        } catch (IOException e) {
            LOGGER.error(ERROR_JSON, e.getMessage());
            throw new DAOException();
        }
    }

    @SuppressWarnings("unchecked")
    private <T> BodyCache<T> parseCache(ObjectMapper mapper, String body, Class<?> class1)
            throws IOException {
        BodyCache<T> bodyCache = new BodyCache<T>();
        bodyCache.setBody((T)mapper.readValue(body, class1));
        return bodyCache;
    }
    
    @Override
    public void setCache(String token, Object body) throws DAOException {
        CacheServiceEnum cacheService = CacheServiceEnum.get(body.getClass());
        HashMap<String, String> request;
        request = generarSetRequestMap(body);
        LOGGER.info(OBP_API_USERS_INVOCANDO_A_OBP_API_USERS, cacheService.getServiceName());
        WebClient service = crearLlamadaAWebService(CACHE_SET + cacheService.getServiceName());
        service.header(HttpHeaders.AUTHORIZATION, BEARER + token);
        Response respuesta = service.post(request);
        if (respuesta.getStatus() != 200) {
            LOGGER.error("[obp-api-users] Error Invocando a obp-api-users");
            throw new DAOException("Error obp-api-users");
        }
    }
    
    @Override
    public void clearCache(String token) throws DAOException {
        LOGGER.info(OBP_API_USERS_INVOCANDO_A_OBP_API_USERS);
        WebClient service = crearLlamadaAWebService(CLEAR_CACHE);
        service.header(HttpHeaders.AUTHORIZATION, BEARER + token);
        Response respuesta = service.delete();
        if (respuesta.getStatus() != 200) {
            LOGGER.error("[obp-api-users] Error Invocando a obp-api-users");
            throw new DAOException("Error obp-api-users");
        }
    }
        
    
    private HashMap<String, String> generarSetRequestMap(Object body) throws DAOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String bodyCache = mapper.writeValueAsString(body);
            map.put("value", bodyCache);
            return map;
        } catch (JsonGenerationException e) {
            LOGGER.error(ERROR_JSON, e.getMessage());
        } catch (JsonMappingException e) {
            LOGGER.error(ERROR_JSON, e.getMessage());
        } catch (IOException e) {
            LOGGER.error(ERROR_JSON, e.getMessage());
        }
        throw new DAOException();
    }
    
    
    /**
     * @param cacheService
     * @param is
     * @throws DAOException
     */
    public Class<?> readValue(String is, Class<?> respuestaClass) throws DAOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return (Class<?>) mapper.readValue(is, respuestaClass);
        } catch (JsonParseException e) {
            
            throw new DAOException();
        } catch (JsonMappingException e) {
            throw new DAOException();
        } catch (IOException e) {
            throw new DAOException();
        } catch (Exception e) {
            throw new DAOException();
        }
    }
}
