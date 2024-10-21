/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mail;

import javax.mail.MessagingException;

/**
 * Interface encargada de definir el envio de mails en sus diferentes versiones.
 */
public interface OBPMailSender {

	/**
	 * Envio de mails con los parametros basicos.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param subject
	 *            the subject
	 * @param body
	 *            the body
	 * @throws MessagingException
	 *             lanzado cuando ocurre alguna excepcion al enviar el mail.
	 */
	void sendMail(String from, String to, String subject, String body) throws MessagingException;

	/**
	 * Envio de mails con todos los parametros.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param cc
	 *            the cc
	 * @param bcc
	 *            the bcc
	 * @param replyto
	 *            the replyto
	 * @param subject
	 *            the subject
	 * @param body
	 *            the body
	 * @throws MessagingException
	 *             the messaging exception
	 */
	void sendMail(String from, String to, String cc, String bcc, String replyto, String subject, String body)
			throws MessagingException;

}
