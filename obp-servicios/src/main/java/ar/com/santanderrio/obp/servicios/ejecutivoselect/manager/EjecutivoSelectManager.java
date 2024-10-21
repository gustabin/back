package ar.com.santanderrio.obp.servicios.ejecutivoselect.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioFlujoEMailOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioOperadorEjecutivoOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionWhatsappInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailOutView;

/**
 * The Interface EjecutivoSelectManager.
 */
public interface EjecutivoSelectManager {

	/**
	 * Inicio operador ejecutivo.
	 *
	 * @return the respuesta
	 */
	Respuesta<InicioOperadorEjecutivoOutView> InicioOperadorEjecutivo();

	/**
	 * Notificar mensaje whatsapp.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<Void> notificarMensajeWhatsapp(NotificacionWhatsappInView inView);
	
	/**
	 * Notificar llamada por tel.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<NotificacionLLamadaPorTelOutView> notificarLlamadaPorTel(NotificacionLLamadaPorTelInView inView);

	/**
	 * Inicio flujo E mail.
	 *
	 * @return the respuesta
	 */
	Respuesta<InicioFlujoEMailOutView> inicioFlujoEMail();
	
	/**
	 * Enviar email.
	 *
	 * @param inView the in view
	 * @param jSessionId the j session id
	 * @return the respuesta
	 */
	Respuesta<OperadorEjecutivoEnviarMailOutView> enviarEmail(OperadorEjecutivoEnviarMailInView inView ,String jSessionId);

}
