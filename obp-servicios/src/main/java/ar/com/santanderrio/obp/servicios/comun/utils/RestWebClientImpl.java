/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.keystore.KSStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.BonificacionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;
import ar.com.santanderrio.obp.servicios.exception.GeneralMapper;

// TODO: Auto-generated Javadoc
/**
 * Gestionar desde un unico punto la creacion de los clientes cxf.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component
public class RestWebClientImpl implements RestWebClient {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestWebClientImpl.class);

	/** The environment. */
	@Autowired
	private Environment environment;

	/** The key store factory. */
	@Autowired
	private KeyStoreFactory keyStoreFactory;

	/** The Constant CLIENTE_URL. */
	private static final String CLIENTE_URL = ".URL";

	/** timeout para httpconduit si no esta la prop se setean 5000ms. */
	private static final String CLIENTE_TIMEOUT = ".TIMEOUT";

	/** The Constant CLIENTE_PALABRAS_SENSIBLES_IN. */
	private static final String CLIENTE_PALABRAS_SENSIBLES_IN = ".PALABRAS.SENSIBLES.IN";

	/** The Constant CLIENTE_PALABRAS_SENSIBLE_OUT. */
	private static final String CLIENTE_PALABRAS_SENSIBLE_OUT = ".PALABRAS.SENSIBLES.OUT";

	/** jks para keystore. */
	private static final String CLIENTE_JKS = ".JKS";
	
	private static final String ERROR_SERVICIO_PROMOCIONES ="Error en el servicio obtenerPromociones";
	
	private static final String ERROR_SERVICIO_BONIFICACIONES ="Error en el servicio obtenerBonificaciones";

	/**
	 * limite del json a loguear, el doble del limite por default, por eso el
	 * nro raro :P.
	 */
	@Value("${REST_LOG_LIMIT:98304}")
	private int OBP_DEFAULT_LIMIT;
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${API.KEY.BONIFICACIONES}")
	private String apiKeyBonificaciones;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient#
	 * obtenerClienteRest(java.lang.String)
	 */
	@Override
	public WebClient obtenerClienteRest(String nombreWSRest) throws DAOException {
		String url = environment.getProperty(nombreWSRest + CLIENTE_URL);

		String tiempoProp = nombreWSRest + CLIENTE_TIMEOUT;
		Long timeOut = Long.parseLong(environment.getProperty(tiempoProp, "5000"));

		String[] palabrasSensibleIn = null;
		String tag = environment.getProperty(nombreWSRest + CLIENTE_PALABRAS_SENSIBLES_IN);

		if (!StringUtils.isEmpty(tag)) {
			palabrasSensibleIn = tag.split(",");
		}

		String[] palabrasSensibleOut = null;
		tag = environment.getProperty(nombreWSRest + CLIENTE_PALABRAS_SENSIBLE_OUT);

		if (!StringUtils.isEmpty(tag)) {
			palabrasSensibleOut = tag.split(",");
		}
		WebClient webClient = obtenerClienteRest(url, timeOut, palabrasSensibleIn, palabrasSensibleOut);

		configurarSsl(nombreWSRest, url, webClient);
		return webClient;
	}

	/**
	 * Generar un cliente jaxrs a una url recibida como parametro con
	 * interceptores de i/o que enmascaran ciertos datos del payload. <br>
	 * El cliente generado tendra un timeout indicado para realizar la tarea.
	 *
	 * @param url
	 *            the url
	 * @param timeOut
	 *            the time out
	 * @param palabrasSensibleIn
	 *            the palabras sensible in
	 * @param palabrasSensibleOut
	 *            the palabras sensible out
	 * @return WebClient
	 * @throws DAOException
	 *             the DAO exception
	 */
	private WebClient obtenerClienteRest(String url, Long timeOut, String[] palabrasSensibleIn,
			String[] palabrasSensibleOut) throws DAOException {
		LOGGER.info(
				"Se configura el cliente rest a la url {} , se le setean {}ms de timeout con las palabras sensible de entrada {} y de salida {}.",
				url, timeOut, palabrasSensibleIn, palabrasSensibleOut);
		JAXRSClientFactoryBean bean = obtenerJaxrsClientFactory(url);
		agregarInterceptorDeEntrada(bean, palabrasSensibleIn);
		agregarInterceptorDeSalida(bean, palabrasSensibleOut);
		return generarClienteConTimeouts(bean, timeOut);
	}

	/**
	 * Se crea el factory de clientes y se le agregan los providers
	 * correspondientes.
	 *
	 * @param baseUrl
	 *            the base url
	 * @return JAXRSClientFactoryBean
	 */
	private static JAXRSClientFactoryBean obtenerJaxrsClientFactory(String baseUrl) {
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		bean.setAddress(baseUrl);
		JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
		jacksonJaxbJsonProvider.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jacksonJaxbJsonProvider.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		bean.setProviders(Arrays.asList(jacksonJaxbJsonProvider, new GeneralMapper()));
		return bean;
	}

	/**
	 * Agregar interceptor para loguear de entrada.
	 *
	 * @param bean
	 *            the bean
	 * @param palabrasSensibleIn
	 *            the palabras sensible in
	 */
	private void agregarInterceptorDeEntrada(JAXRSClientFactoryBean bean, String[] palabrasSensibleIn) {
		RestMaskLoggingInInterceptor logInInterceptor = new RestMaskLoggingInInterceptor(palabrasSensibleIn,
				OBP_DEFAULT_LIMIT);
		logInInterceptor.setPrettyLogging(true);
		bean.getInInterceptors().clear();
		bean.getInInterceptors().add(logInInterceptor);
	}

	/**
	 * Agregar interceptor para loguear de salida.
	 *
	 * @param bean
	 *            the bean
	 * @param palabrasSensibleOut
	 *            the palabras sensible out
	 */
	private void agregarInterceptorDeSalida(JAXRSClientFactoryBean bean, String[] palabrasSensibleOut) {
		RestMaskLoggingOutInterceptor logOutInterceptor = new RestMaskLoggingOutInterceptor(palabrasSensibleOut,
				OBP_DEFAULT_LIMIT);
		logOutInterceptor.setPrettyLogging(true);
		bean.getOutInterceptors().clear();
		bean.getOutInterceptors().add(logOutInterceptor);
	}

	/**
	 * Agregarle timeout en milisegundos a la url por consumir.
	 *
	 * @param bean
	 *            the bean
	 * @param timeOutMs
	 *            the time out ms
	 * @return WebClient
	 */
	private WebClient generarClienteConTimeouts(JAXRSClientFactoryBean bean, Long timeOutMs) {
		WebClient webClient = bean.createWebClient();
		HTTPConduit conduit = (HTTPConduit) WebClient.getConfig(webClient).getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(timeOutMs);
		policy.setReceiveTimeout(timeOutMs);
		conduit.setClient(policy);
		return webClient;
	}

	/**
	 * Configurar ssl.
	 *
	 * @param nombreWSRest
	 *            the nombre WS rest
	 * @param url
	 *            the url
	 * @param cliente
	 *            the cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void configurarSsl(String nombreWSRest, String url, WebClient cliente) throws DAOException {
		if (Boolean.valueOf(url.contains("https:"))) {
			FileInputStream fis = null;
			TLSClientParameters tlsParams = new TLSClientParameters();
			tlsParams.setDisableCNCheck(true);

			String propJKS = nombreWSRest + CLIENTE_JKS;
			KSStore kSStore = new KSStore();
			try {
				kSStore.setKeyStore(keyStoreFactory.createKeyStore(environment.getProperty(propJKS)));
				KeyStore keyStore = KeyStore.getInstance(kSStore.getKeyStore().getKeystoreType());
				fis = new FileInputStream(new File(kSStore.getKeyStore().getKeystorePath()));
				keyStore.load(fis, kSStore.getKeyStore().getKeystorePassword().toCharArray());

				TrustManagerFactory trustFactory = TrustManagerFactory
						.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				trustFactory.init(keyStore);
				TrustManager[] tm = trustFactory.getTrustManagers();
				tlsParams.setTrustManagers(tm);

				KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				keyFactory.init(keyStore, kSStore.getKeyStore().getKeystorePassword().toCharArray());
				KeyManager[] km = keyFactory.getKeyManagers();
				tlsParams.setKeyManagers(km);

				FiltersType filter = new FiltersType();
				filter.getInclude().add(".*_WITH_3DES_.*");
				filter.getInclude().add("SSL_RSA_WITH_RC4_128_SHA");
				filter.getInclude().add(".*_EXPORT_.*");
				filter.getInclude().add(".*_EXPORT1024_.*");
				filter.getInclude().add(".*_WITH_DES_.*");
				filter.getInclude().add(".*_WITH_NULL_.*");
				filter.getInclude().add(".*_RSA_WITH_AES_256_.*");
				filter.getExclude().add(".*_DH_anon_.*");
				tlsParams.setCipherSuitesFilter(filter);

				((HTTPConduit) WebClient.getConfig(cliente).getConduit()).setTlsClientParameters(tlsParams);
			} catch (Exception e) {
				LOGGER.error("Error al intentar obtener las credenciales en la base de claves.", e);
				throw new DAOException(e);
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					LOGGER.error("Error al cerrar el file {}.", kSStore.getKeyStore().getKeystorePath(), e);
				}
			}
		}

	}

	@Override
	public ListPromocionDto obtenerPromociones(String url) throws DAOException {
		LOGGER.info("Init call to obtenerPromociones");
        ListPromocionDto promocionListDTO = null;
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        try {
	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	            ResponseEntity<String> response = restTemplate.exchange(
	                    url,
	                    HttpMethod.GET,
	                    entity,
	                    String.class
	            );
        
	            ObjectMapper mapper = new ObjectMapper();    
        
	     promocionListDTO = mapper.readValue(response.getBody(), ListPromocionDto.class);
		} catch (JsonParseException e) {
			LOGGER.info(ERROR_SERVICIO_PROMOCIONES, url);
		} catch (JsonMappingException e) {
			LOGGER.info(ERROR_SERVICIO_PROMOCIONES, url);
		} catch (IOException e) {
			LOGGER.info(ERROR_SERVICIO_PROMOCIONES, url);
		} catch (HttpClientErrorException e) {
			LOGGER.error(ERROR_SERVICIO_PROMOCIONES, e);	
		} catch (Exception e) {
			LOGGER.error(ERROR_SERVICIO_PROMOCIONES, e);
		}
        
        return promocionListDTO;
        
            
        
     }

	@Override
	public List<BonificacionDto> obtenerBonificacionesSeguros(String url) throws DAOException {
		LOGGER.info("Init call to obtenerBonificacionesSeguros");
        List<BonificacionDto> bonificacionesList = null;
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        headers.add("x-api-key", apiKeyBonificaciones);
        try {
	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	            ResponseEntity<String> response = restTemplate.exchange(
	                    url,
	                    HttpMethod.GET,
	                    entity,
	                    String.class
	            );
	        
	        ObjectMapper mapper = new ObjectMapper();    
	        
        	BonificacionDto[] arrayList = mapper.readValue(response.getBody(), BonificacionDto[].class);
        	bonificacionesList = Arrays.asList(arrayList);

		} catch (JsonParseException e) {
			LOGGER.error(ERROR_SERVICIO_BONIFICACIONES, e);
    	} catch (JsonMappingException e) {
			LOGGER.error(ERROR_SERVICIO_BONIFICACIONES, e);
    	} catch (IOException e) {
			LOGGER.error(ERROR_SERVICIO_BONIFICACIONES, e);
    	} catch (HttpClientErrorException e) {
    		LOGGER.error(ERROR_SERVICIO_BONIFICACIONES, e);
		} catch (Exception e) {
			LOGGER.error(ERROR_SERVICIO_BONIFICACIONES , e);
		}
        
        return bonificacionesList;
	}   
        
	

	
}
