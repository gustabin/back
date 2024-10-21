/*
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AccessDeniedException;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.IntegracionObpTbancoException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.exception.GeneralMapper;
import ar.com.santanderrio.obp.servicios.exception.IntegracionObpTbancoMapper;
import ar.com.santanderrio.obp.servicios.exception.PermisoMapper;

/**
 * Configurar programaticamente el servidor de jaxrs como si fuera con un xml.
 * 
 * <pre>
 *  {@code 
 *  <context:annotation-config />
 *  <context:component-scan base-package="ar.com.santanderrio" />
 *  <import resource="classpath:META-INF/cxf/cxf.xml" />
 *  <bean id="wadlGenerator"
 *      class="org.apache.cxf.jaxrs.model.wadl.WadlGenerator">
 *     <property name="linkJsonToXmlSchema" value="true" />
 *           </bean>
 *           <jaxrs:server id="pruebaCXF" address="/xxx">
 *           <jaxrs:serviceBeans>
 *           <ref bean="pruebaSEI" />
 *      </jaxrs:serviceBeans>
 *      <jaxrs:providers>
 *          <bean class="org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider" />
 *          <ref bean="wadlGenerator" />
 *     </jaxrs:providers>
 *</jaxrs:server>
 * }
 * </pre>
 * 
 * @author sergio.e.goldentair
 *
 */

@Configuration
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class CxfSpringConfig {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CxfSpringConfig.class);

    /**
     * address que se agrega al contexto para que cxf escuche el ws.
     */
    @Value("${CXF.ADDRESS}")
    private String cxfAddress;
    /**
     * address que se agrega al contexto para que cxf escuche el ws con una api de
     * operaciones internas.
     */
    @Value("${CXF.API.ADDRESS:/api}")
    private String cxfApiAddress;

    /**
     * Maneja las excepciones unchecked que lance la app (Provider).
     *
     * @return capturador de excepciones.
     */
    @Bean
    public ExceptionMapper<Throwable> generalMapper() {
        return new GeneralMapper();
    }

    /**
     * Maneja las excepciones cuando no tiene permisos una url y no es retenida por
     * el filtro (Provider).
     *
     * @return capturador de excepciones para los permisos por anotacion.
     */
    @Bean
    public ExceptionMapper<AccessDeniedException> permisoMapper() {
        return new PermisoMapper();
    }

    /**
     * Utilizar Jackson para generar los json (Provider).
     * 
     * @return provider de jackson.
     */
    @Bean
    public JacksonJaxbJsonProvider jacksonJsonProvider() {
        return new JacksonJaxbJsonProvider();
    }

    /**
     * Lista de providers utilizado por el server de jaxrs.
     * 
     * @return lista de todos los providers que va utilizar el servidor cxf.
     */
    @Bean
    public List<?> providers() {
        return Arrays.asList(jacksonJsonProvider(), permisoMapper(), obpTbancoMapper(), generalMapper());
    }

    /**
     * Utilizar mientras dure el salto entre obp y tbanco (Provider).<br/>
     * Corta con las capas e indica que el flujo se redirecciones a obp cuando no
     * esta habilitado a tbanco desde la base.
     * 
     * @return provider de jackson.
     */
    @Bean(name = "obpTbancoMapper")
    @Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA, Profiles.MOCK, Profiles.TOMCAT })
    public ExceptionMapper<IntegracionObpTbancoException> obpTbancoMapper() {
        return new IntegracionObpTbancoMapper();
    }

    /**
	 * Este metodo se ejecuta en los test de integracion para no tener que
	 * agregar en todos los correspondientes a los sei los autowired que se
	 * incluyen en IntegracionObpTbancoMapper.
	 *
	 * @return the exception mapper
	 */
    @Bean(name = "obpTbancoMapper")
    @Profile({ Profiles.SEI, Profiles.TEST })
    public ExceptionMapper<IntegracionObpTbancoException> obpTbancoMapperTest() {
        LOGGER.debug("Invoca a la implementacion para test con el perfil {}", Profiles.SEI);
        return new ExceptionMapper<IntegracionObpTbancoException>() {

            @Override
            public Response toResponse(IntegracionObpTbancoException arg0) {
                Respuesta<String> respuestaWarning = new Respuesta<String>();
                respuestaWarning.setEstadoRespuesta(EstadoRespuesta.ERROR);
                ItemMensajeRespuesta item = new ItemMensajeRespuesta();
                item.setMensaje("ERROR desde mapper de test.");
                item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
                respuestaWarning.add(item);

                return Response.status(Response.Status.OK).entity(respuestaWarning)
                        .header("Content-Type", MediaType.APPLICATION_JSON).build();
            }
        };
    }

    /**
     * Interceptor para capturar el inbound.
     *
     * @return interceptor de los mensajes de entrada al ws.
     */
    @Bean
    public LoggingInInterceptor createLoggingInInterceptor() {
        LoggingInInterceptor l = new LoggingInInterceptor();
        l.setPrettyLogging(true);
        return l;
    }

    /**
     * Interceptor para capturar el outbound.
     * 
     * @return interceptor de los mensajes de salida del ws.
     */
    @Bean
    public LoggingOutInterceptor createLoggingOutInterceptor() {
        LoggingOutInterceptor l = new LoggingOutInterceptor();
        l.setPrettyLogging(true);
        return l;
    }

    /**
     * Configurar el servidor de jaxrs. Incluye providers de json, wadl, mapper,
     * cors.
     * 
     * Como variante del getBean se podria utilizar
     * getBeansWithAnnotation(Path.class) pero la clase deberia tener la anotacion
     * Path a nivel de clase ya que si solo esta a nivel de metodos no la encuentra.
     * Ej:
     * 
     * <pre>
     * <code>
     *    jaxrsServerFactoryBean.setServiceBeans(Collections.singletonList(applicationContext.getBean("pruebaSEI")));
     *  </code>
     * </pre>
     * 
     * Importante!!!
     * 
     * <u>Las clases marcadas con path son cargadas dinamicamente</u>
     * 
     * @author sergio.e.goldentair
     * @param applicationContext
     *            de la aplicacion.
     * @return servidor cxf.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean(initMethod = "create")
    public JAXRSServerFactoryBean jaxrsServerFactoryBean(ApplicationContext applicationContext) {
        LOGGER.info("Cargar servidor jaxrs!!!");
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();

        List<Object> serviceBeans = new ArrayList(applicationContext.getBeansWithAnnotation(Path.class).values());
        jaxrsServerFactoryBean.setServiceBeans(serviceBeans);
        return configServer(applicationContext, jaxrsServerFactoryBean, cxfAddress);
    }

    /**
     * Servidor para Operaciones internas que no seran expuestas a la red.
     *
     * @param applicationContext
     *            the application context
     * @return the JAXRS server factory bean
     */
    @Bean(initMethod = "create")
    @Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA, Profiles.MOCK, Profiles.TOMCAT })
    public JAXRSServerFactoryBean jaxrsInternalBean(ApplicationContext applicationContext) {
        LOGGER.info("Cargar servidor jaxrs para consultas internas!!!");
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();

        jaxrsServerFactoryBean.setServiceBeans(getInternalApi(applicationContext));
        return configServer(applicationContext, jaxrsServerFactoryBean, cxfApiAddress);
    }

    /**
     * Listado de SEI que seran parte de la api interna.
     *
     * @param applicationContext
     *            the application context
     * @return lista de sei
     */
    private List<Object> getInternalApi(ApplicationContext applicationContext) {
        List<Object> seis = new ArrayList<Object>();
        seis.add(applicationContext.getBean("statusSEI"));
        seis.add(applicationContext.getBean("cacheSEI"));
        seis.add(applicationContext.getBean("customConfigSEI"));
        return seis;
    }

    /**
     * Configuracion del servidor jaxrs.
     *
     * @param applicationContext
     *            the application context
     * @param jaxrsServerFactoryBean
     *            the jaxrs server factory bean
     * @param address
     *            the address
     * @return the JAXRS server factory bean
     */
    @SuppressWarnings({ "unchecked" })
    private JAXRSServerFactoryBean configServer(ApplicationContext applicationContext,
            JAXRSServerFactoryBean jaxrsServerFactoryBean, String address) {
        SpringBus springBus = applicationContext.getBean(SpringBus.class);
        springBus.setInInterceptors(Arrays.<Interceptor<? extends Message>>asList(createLoggingInInterceptor()));
        springBus.setOutInterceptors(Arrays.<Interceptor<? extends Message>>asList(createLoggingOutInterceptor()));
        springBus.setInFaultInterceptors(Arrays.<Interceptor<? extends Message>>asList(createLoggingInInterceptor()));
        springBus.setOutFaultInterceptors(Arrays.<Interceptor<? extends Message>>asList(createLoggingOutInterceptor()));
        jaxrsServerFactoryBean.setBus(springBus);
        jaxrsServerFactoryBean.setProviders(providers());
        jaxrsServerFactoryBean.setAddress(address);
        return jaxrsServerFactoryBean;
    }
}