package ar.com.santanderrio.obp.servicios.comun.mail;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.config.mail.MailConfig;
import ar.com.santanderrio.obp.servicios.comun.mail.MailIT.MailITConfiguration;

/**
 * The Class CuentaSaldoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MailITConfiguration.class,
		MailConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SMTP.MAILSERVER = mailext.ar.bsch"})
public class MailIT {
	
	/** The obp mail sender. */
	@Autowired
	private OBPMailSender obpMailSender;

	/**
     * The Class CuentaSaldoDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.mail" })
    public static class MailITConfiguration {
        
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
     * Test del envio de mails
     *
     * @throws MessagingException the messaging exception
     */
    @Test
    public void sendMail() throws MessagingException {
    	String from = "noreply@santanderrio.com.ar";
    	String to = "epe@gmail.com";
    	String subject = "prueba";
    	String body = "test";
    	
    	obpMailSender.sendMail(from, to, subject, body);
    }
}
