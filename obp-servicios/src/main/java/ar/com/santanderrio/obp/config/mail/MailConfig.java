/*
 * 
 */
package ar.com.santanderrio.obp.config.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Armado de las clases para el envio de mails.
 */
@Configuration
public class MailConfig {

	/**
	 * Bean para el envio de mails.
	 *
	 * @return the mail sender
	 */
	@Bean
	public JavaMailSenderImpl javaMailService() {
		return new JavaMailSenderImpl();
	}

}
