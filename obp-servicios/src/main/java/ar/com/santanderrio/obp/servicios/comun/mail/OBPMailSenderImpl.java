/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * The Class OBPMailSenderImpl.
 */
@Component
public class OBPMailSenderImpl implements OBPMailSender {

	/** The host. */
	@Value("${SMTP.MAILSERVER}")
	private String host;

	/** The mail sender. */
	@Autowired
	private JavaMailSenderImpl mailSender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.config.mail.OBPMailSender#sendMail(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(String from, String to, String subject, String body) throws MessagingException {
		this.sendMail(from, to, null, null, null, subject, body);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.config.mail.OBPMailSender#sendMail(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(String from, String to, String cc, String bcc, String replyTo, String subject, String body)
			throws MessagingException {

		mailSender.setHost(host);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(from);

		if (StringUtils.isNotEmpty(replyTo)) {
			helper.setReplyTo(replyTo);
		}

		helper.setTo(to);

		if (StringUtils.isNotEmpty(cc)) {
			helper.setCc(cc);
		}

		if (StringUtils.isNotEmpty(bcc)) {
			helper.setBcc(bcc);
		}

		helper.setSubject(subject);

		helper.setText(body);

		mailSender.send(message);
	}

}
