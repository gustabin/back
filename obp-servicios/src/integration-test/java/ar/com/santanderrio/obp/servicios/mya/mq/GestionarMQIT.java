
package ar.com.santanderrio.obp.servicios.mya.mq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.mq.webservices.GestionarMQ;
import ar.com.santanderrio.obp.base.mq.webservices.ISBANMQOperacion;
import ar.com.santanderrio.obp.servicios.mya.mq.GestionarMQIT.MyaMQITConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MyaMQITConfiguration.class })
@TestPropertySource(properties = { "MQ.EXTRACCIONES.HOSTNAME = mvscpum.ar.bsch", 
        "MQ.EXTRACCIONES.PORT = 1415",
        "MQ.EXTRACCIONES.CHANNEL = RIOSMS.CH.MYA", 
        "MQ.EXTRACCIONES.QMANAGER = CSQ2", 
        "MQ.EXTRACCIONES.QUEUENAME = RIOMSG.QUEUE.MYA_MF_1ST" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GestionarMQIT {
	
	/**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Autowired
    private GestionarMQ gestionarMQ;

    @Configuration 
    @ComponentScan(basePackageClasses = { GestionarMQ.class })
    public static class MyaMQITConfiguration {
    	
        
        /**	
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }
    
    /**
     * Testea la cola de MQ para la operacion de ISBAN EXRACCIONES.
     * @throws Exception
     */
    @Test
    public void testExtraccionesMQ() throws Exception {
		this.gestionarMQ.sendMessage(ISBANMQOperacion.EXTRACCIONES, "texto");
			
		Assert.assertTrue(true);
    }

}
