/*
 * 
 */
package ar.com.santanderrio.obp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import ar.com.santanderrio.obp.config.proxy.ProxyServlet;
import org.apache.commons.codec.CharEncoding;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.config.filter.AuthenticationFilter;
import ar.com.santanderrio.obp.config.filter.CharsetFilter;
import ar.com.santanderrio.obp.config.filter.ReleaseSessionFilter;
import ar.com.santanderrio.obp.config.ws.RootConfig;
import ar.com.santanderrio.obp.servicios.comun.seguridad.web.helpers.ListaNegraFilter;
import ar.com.santanderrio.obp.servicios.comun.seguridad.web.helpers.XssFilter;

/**
 * Programaticamente reemplazar el web.xml
 * 
 * <pre>
 * {@code
 * 
 *     <env-entry>
 *        <env-entry-name>config/OBP_UBICACION_PROPIEDADES</env-entry-name>
 *        <env-entry-type>java.lang.String</env-entry-type>
 *    </env-entry>
 *    
 *    <context-param>
 *        <param-name>spring.profiles.active</param-name>
 *        <param-value>production</param-value>
 *    </context-param>
 *        
 *    <listener>
 *        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 *    </listener>
 *
 *    <context-param>
 *        <!-- Define the location of the spring's configuration file. -->
 *        <param-name>contextConfigLocation</param-name>
 *        <param-value>WEB-INF/classes/obp-cxf-beans.xml</param-value>
 *    </context-param>
 *
 *    <servlet>
 *        <display-name>CXF Servlet</display-name>
 *        <servlet-name>CXFServlet</servlet-name>        
 *        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
 *        <load-on-startup>1</load-on-startup>
 *    </servlet>
 *
 *    <servlet-mapping>
 *        <servlet-name>CXFServlet</servlet-name>
 *        <url-pattern>/*</url-pattern>
 *    </servlet-mapping>
 * }
 * </pre>
 * 
 * Si se desea reemplazar la clase de configuracion de spring por un xml cambiar
 * la imple de getContext actual por la comentada a continuacion:
 * 
 * <pre>
 *     <code>
 *  private XmlWebApplicationContext getContext() {
 *        XmlWebApplicationContext context = new XmlWebApplicationContext();
 *      context.setConfigLocations(new String[] { "/WEB-INF/classes/prueba-beans.xml" });
 *      context.getEnvironment().setDefaultProfiles("production");
 *      return context;
 *  }
 *     </code>
 * </pre>
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
public class ServletInitalizer implements WebApplicationInitializer {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletInitalizer.class);

	/** The Constant MAPPING. */
	private static final String MAPPING = "/*";
	
	/**
	 * The Constant EXCLUDE_URLS. URLs path separadas por coma que seran excluidas
	 * en el filtro de lista negra. Ejemplo: en un futuro se desea excluir del
	 * filtro "Fondos" -> comexCanales,fondos
	 */
	private static final String EXCLUDE_URLS = "comexCanales,transferenciaExterior,liquidacionOrdenPago";

	/** The Constant REDUCED_WORDLIST_URLS. */
	private static final String REDUCED_WORDLIST_URLS = "/transferencias/confirmarAltaDestinatario,/transferencias/confirmarEdicionDestinatario,"
			+ "/transferencias/consultarTitularidad,/transferencias/ejecutarNuevaTransferencia,/transferencias/nuevaTransferencia,"
			+ "/transferencias/obtenerInformacionDestinatario,/destinatariosFrecuentes/altaFavorito,/destinatariosFrecuentes/bajaFavorito,"
			+ "/agendaTransferencias/solicitarAgendarTransferenciaDesdeFeedback,/agendaTransferencias/ejecutarAgendamientoTransferencia";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Se fuerza el seteo a Locale.ENGLISH para poder tener un unico Locale
		// dentro de la aplicacion.
		Locale.setDefault(Locale.ENGLISH);

		LOGGER.info("Inicia carga WebApplicationInitializer para obp-servicios!!!!!");
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new RequestContextListener());
		servletContext.addListener(new HttpSessionEventPublisher());

		FilterRegistration.Dynamic releaseSessionFilter = servletContext.addFilter("ReleaseSessionFilter", ReleaseSessionFilter.class);
		releaseSessionFilter.addMappingForUrlPatterns(null, true, MAPPING);

		FilterRegistration.Dynamic xssFilter = servletContext.addFilter("XssFilter", XssFilter.class);
		xssFilter.addMappingForUrlPatterns(null, true, MAPPING);

		FilterRegistration.Dynamic authFilter = servletContext.addFilter("AuthenticationFilter",
				AuthenticationFilter.class);
		authFilter.addMappingForUrlPatterns(null, true, MAPPING);

		FilterRegistration.Dynamic listaNegraFilter = servletContext.addFilter("ListaNegraFilter",
				ListaNegraFilter.class);
		listaNegraFilter.setInitParameter("excludeUrls", EXCLUDE_URLS);
		listaNegraFilter.setInitParameter("reducedWordlistUrls", REDUCED_WORDLIST_URLS);
		listaNegraFilter.addMappingForUrlPatterns(null, true, MAPPING);

		LOGGER.info("encodingFilter con encoding {}.", CharEncoding.UTF_8);
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",
				CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", CharEncoding.UTF_8);
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, MAPPING);

		LOGGER.info("charsetFilter con encoding {}.", CharEncoding.UTF_8);
		FilterRegistration.Dynamic charsetFilter = servletContext.addFilter("CharsetFilter", CharsetFilter.class);
		charsetFilter.setInitParameter("requestEncoding", CharEncoding.UTF_8);
		charsetFilter.addMappingForUrlPatterns(null, true, MAPPING);

		FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain",
				DelegatingFilterProxy.class);
		springSecurityFilterChain.addMappingForUrlPatterns(null, true, MAPPING);

        ConfigurableEnvironment environment = ((AnnotationConfigWebApplicationContext) context).getEnvironment();

        servletContext.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,
                setProfile(environment));
        LOGGER.info("Profile [spring.profiles.active] activo = {}.",
                servletContext.getInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME));

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);

        Properties prop = getFileFromResources(environment.getProperty("config/OBP_UBICACION_PROPIEDADES") + "/" +
                servletContext.getInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME) +
                "/routes-config.properties");

        ProxyServlet proxyServlet = servletContext.createServlet(ProxyServlet.class);
        ServletRegistration.Dynamic registration = servletContext.addServlet("ProxyServlet", proxyServlet);
        registration.setLoadOnStartup(10);
        registration.addMapping(prop.getProperty("BFF_MAPPING_PATTERN","/bff/*"));
        registration.setInitParameter("targetUri", prop.getProperty("BFF_URL","https://obp-api-rules-api-rules.apps.ocpprd.ar.bsch"));
        registration.setInitParameter(ProxyServlet.P_LOG, "true");
        registration.setInitParameter(ProxyServlet.P_PRESERVEHOST, "false");
        registration.setInitParameter(ProxyServlet.P_PRESERVECOOKIES, "true");

        CXFServlet cxfServlet = servletContext.createServlet(CXFServlet.class);
        servletContext.addServlet("CXFServlet", cxfServlet).addMapping(MAPPING);

        LOGGER.info("Fin carga WebApplicationInitializer para obp-servicios!!!!!");
    }

	/**
	 * Levanta por jndi el profile cargado en el was, si no esta cargado el
	 * valor levanta PRODUCTION.
	 * 
	 * referencia:
	 * http://xkcb.blogspot.com.ar/2013/03/environment-specific-spring-profiles-
	 * on.html No se utiliza la forma de referencia ya que de esa forma cuando
	 * tiene que levantar las propiedades en ObpPropertiesReaderConfiguration no
	 * puede resolver ${spring.profiles.active}.
	 * 
	 * @author sergio.e.goldentair
	 * @param environment
	 *            el env provisto por spring con todas las props.
	 * @return profile que corresponda.
	 *
	 */
	private String setProfile(ConfigurableEnvironment environment) {
		String serverProfileEnv = environment.getProperty("persistent/NS_SERVER_ENV");
		LOGGER.info("Profile cargado en el servidor {}.", serverProfileEnv);
		if (null != serverProfileEnv) {
			return serverProfileEnv;
		} else {
			return Profiles.PRODUCTION;
		}
	}

	/**
	 * Levantar la configuracion mediante anotaciones. Comienza desde la
	 * exposicion de servicios jaxrs con implementacion de cxf.
	 * 
	 * Agrego el refresh para que el contexto se cargue al instanciar
	 * ServletInitalizer, sino levanta esta clase y luego carga todo el
	 * contexto.
	 * 
	 * @return el contexto mediante anotaciones.
	 */
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(RootConfig.class);
		return context;
	}

    private Properties getFileFromResources(String filePath) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filePath));
        } catch (IOException e) {
            return prop;
        }

        return prop;
    }

}