/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.env.Environment;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.keystore.KSStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;

/**
 * Ej de como hacer un llamado desde un DAO:
 * 
 * <pre>
 *  <code>
 *      &#64;Autowired
 *      private GestionarWS<PlanVService> wsSoap;
 * 
 *      PlanVService cliente = null;
 *      InformacionPlanV response = null;
 *      InformacionPlanV response2 = null;
 *      try {
 *          cliente = wsSoap.obtenerPort();
 *          response = cliente.getInformacionPlanV("");
 *          response2 = cliente.getInformacionPlanV("");
 *      } finally {
 *          wsSoap.liberarPort(cliente);
 *      }
 *  </code>
 * </pre>
 *
 * @author sergio.e.goldentair
 * @param <T>
 *            the generic type
 */
public abstract class GestionarWSAbstract<T> implements GestionarWS<T> {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionarWSAbstract.class);

	/** Generar cliente o solicitarle al pool. */
	private static final String CLIENTE_POOL_ACTIVO = ".POOL.ACTIVO";

	/** The Constant CLIENTE_POOL_SIZE. */
	private static final String CLIENTE_POOL_SIZE = ".POOL.SIZE";

	/** The Constant CLIENTE_POOL_MAXWAIT. */
	private static final String CLIENTE_POOL_MAXWAIT = ".POOL.MAXWAIT";

	/** url al ws que se llamara. */
	private static final String CLIENTE_URL = ".URL";

	/** timeout para httpconduit. */
	private static final String CLIENTE_TIMEOUT = ".TIMEOUT";

	/** jks para keystore. */
	private static final String CLIENTE_JKS = ".JKS";

	/** The environment. */
	@Autowired
	private Environment environment;

	/** The key store factory. */
	@Autowired
	private KeyStoreFactory keyStoreFactory;

	/** Clase del generico para la clase abstracte. */
	private Class<T> genericType;

	/** Pool de ports. */
	@SuppressWarnings({ "rawtypes" })
	private ObjectPool portPool = null;

	/**
	 * Obtener la clase del generic que hereda de esta clase.
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initial() {
		genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(this.getClass(), GestionarWS.class);
		LOGGER.debug("El generic sobre el que se trabaja es {}.", genericType);
		portPool = initPool();
	}

	/**
	 * 1) Obtener el port (ya sea crear uno nuevo o solicitarlo al pool). 2)
	 * Generar el cliente desde el port. 3) Asignar los interceptors. 4) Cargar
	 * los timeouts. 5) Configurar el cifrado del mensaje.
	 *
	 * @return port type T
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public T obtenerPort() throws DAOException {
		LOGGER.info("El cliente va trabajar con las props del arbol {}.", getCodigoWS());
		LOGGER.info("1-5 Obtener el port (ya sea crear uno nuevo o solicitarlo al pool).");
		T port = administrarPort();
		LOGGER.info("2-5 Generar el cliente desde el port.");
		Client cliente = configurarCliente(port);
		LOGGER.info("3-5 Asignar los interceptors.");
		asignarInterceptors(cliente);
		LOGGER.info("4-5 Cargar los timeouts.");
		asignarTimeoutAlCliente(cliente);
		LOGGER.info("5-5 Configurar el cifrado del mensaje.");
		configurarSsl(cliente);

		return port;
	}

	/**
	 * Obtener el cliente en base a la prop rsa.usapool para determinar si la
	 * creacion se efectua directa o por medio del pool.
	 *
	 * @return T
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	private T administrarPort() throws DAOException {
		try {
			if (isPoolActivo()) {
				LOGGER.info("Se utiliza el pool para el servicio {}.", genericType);
				return (T) portPool.borrowObject();
			} else {
				LOGGER.debug("No utiliza pool para el servicio {}, se crea una nueva instancia.", genericType);
				return (T) new GestionarWSPoolableObjectFactory<T>(getUrlValue(), genericType, getBindingId()).makeObject();
			}
		} catch (NoSuchElementException e) {
			LOGGER.error("Error al solicitar el port al pool.", e);
			throw new DAOException(e);
		} catch (IllegalStateException e) {
			LOGGER.error(
					"Error al invocar al metodo, el momento en el cual fue realizada la llamada no esta preparado.", e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error("Error general al crear el port.", e);
			throw new DAOException(e);
		}
	}

	/**
	 * Apartir del port recibido se genera el cliente.
	 *
	 * @param port
	 *            the port
	 * @return the client
	 */
	private Client configurarCliente(T port) {
		return ClientProxy.getClient(port);
	}

	/**
	 * Gestionar los interceptores que se agregan al cliente. Ej: Loggin. Este
	 * metodo se puede especializar en la clase concreta si fuera necesario.
	 *
	 * @param cliente
	 *            the cliente
	 */
	protected void asignarInterceptors(Client cliente) {
		cliente.getOutInterceptors().clear();
		LOGGER.debug("Interceptor de {} de Out en fase {}", getCodigoWS(), Phase.PRE_STREAM);
		cliente.getOutInterceptors().add(new BaseLoggingInterceptor(Phase.PRE_STREAM));
		cliente.getInInterceptors().clear();
		LOGGER.debug("Interceptor de {} de In en fase {}", getCodigoWS(), Phase.RECEIVE);
		cliente.getInInterceptors().add(new BaseLoggingInterceptor(Phase.RECEIVE));
	}

	/**
	 * Asignar los timeouts por medio del httpConduit, si no se cargo la prop
	 * por default se le asigna 10 minutos de timeout.
	 *
	 * @param cliente
	 *            the cliente
	 */
	protected void asignarTimeoutAlCliente(Client cliente) {
		HTTPConduit httpConduit = (HTTPConduit) cliente.getConduit();
		String tiempoProp = getCodigoWS() + CLIENTE_TIMEOUT;
		long timeout = Long.parseLong(environment.getProperty(tiempoProp));
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(timeout);
		policy.setReceiveTimeout(timeout);
		httpConduit.setClient(policy);
		LOGGER.debug("Prop {} con valor {}ms.", tiempoProp, timeout);
	}

	/**
	 * Configurar el cifrado del mensaje. cargar el keystore y truststore.
	 *
	 * @param cliente
	 *            the cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	protected void configurarSsl(Client cliente) throws DAOException {
		if (Boolean.valueOf(environment.getProperty(getCodigoWS() + CLIENTE_URL).contains("https:"))) {
			LOGGER.info("configuracion de SSL, Activa para el servicio {}.", genericType);
			FileInputStream fis = null;
			TLSClientParameters tlsParams = new TLSClientParameters();
			tlsParams.setDisableCNCheck(true);

			String propJKS = getCodigoWS() + CLIENTE_JKS;
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

				((HTTPConduit) cliente.getConduit()).setTlsClientParameters(tlsParams);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWS#liberarPort(java.lang
	 * .Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void liberarPort(T port) throws DAOException {
		LOGGER.info("Liberar recurso {}, pool activo {}.", genericType, isPoolActivo());
		if (isPoolActivo() && port != null) {
			LOGGER.debug("Liberar Port {}.", port);
			try {
				portPool.returnObject(port);
			} catch (Exception e) {
				LOGGER.error("Error al devolver el port {} al pool.", port, e);
				throw new DAOException(e);
			}
		} else {
			LOGGER.debug("Servicio {}, NO requiere liberar, port {}", genericType, port);
		}
	}

	/**
	 * Inits the pool.
	 *
	 * @return the object pool
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ObjectPool initPool() {
		if (portPool == null && isPoolActivo()) {
			LOGGER.info("Iniciar el pool para {}", genericType);
			String poolSize = getCodigoWS() + CLIENTE_POOL_SIZE;
			String poolSizeValue = environment.getProperty(poolSize);
			String poolMaxwait = getCodigoWS() + CLIENTE_POOL_MAXWAIT;
			String poolMaxwaitValue = environment.getProperty(getCodigoWS() + CLIENTE_POOL_MAXWAIT, "5000");
			LOGGER.debug("Props utilizadas para ajustar el pool, {}:{}, {}:{}ms", poolSize, poolSizeValue, poolMaxwait,
					poolMaxwaitValue);
			Long milisegEnSegundos = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(poolMaxwaitValue));
			GenericObjectPoolFactory pf = new GenericObjectPoolFactory(
					(PoolableObjectFactory) new GestionarWSPoolableObjectFactory(getUrlValue(), genericType, getBindingId()),
					Integer.valueOf(poolSizeValue), GenericObjectPool.WHEN_EXHAUSTED_BLOCK, milisegEnSegundos, -1);
			portPool = pf.createPool();
			LOGGER.info("Pool para {} incializado OK.", genericType);
		}
		return portPool;
	}

	/**
	 * Validar si el pool esta activo o no. Ver valor de propiedad finalizada en
	 * .POOL.ACTIVO.
	 *
	 * @return true, if is pool activo
	 */
	private boolean isPoolActivo() {
		String usaPool = getCodigoWS() + CLIENTE_POOL_ACTIVO;
		String usaPoolValue = environment.getProperty(usaPool);
		LOGGER.debug("Validar la Propiedad {}:{} para determinar si el pool de {} esta activo o no.", usaPool,
				usaPoolValue, genericType);
		return Boolean.valueOf(usaPoolValue);
	}

	/**
	 * Devuelve url del servicio a consultar.
	 *
	 * @return the url value
	 */
	public String getUrlValue() {
		String url = getCodigoWS() + CLIENTE_URL;
		String urlValue = environment.getProperty(getCodigoWS() + CLIENTE_URL);
		LOGGER.info("Url del servicio a consumir, {}:{}", url, urlValue);
		return urlValue;
	}

	/**
	 * Prefijo para buscar las propiedades en los diferentes archivos de props.
	 * 
	 * @return String
	 */
	public abstract String getCodigoWS();

	/**
	 * Gets the binding id.
	 *
	 * @return the binding id
	 */
	public String getBindingId() {
		return null;
	}
}
