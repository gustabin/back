package ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.impl;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.EjecutivoSelectManager;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.EjectivoSelectSEI;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioFlujoEMailOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioOperadorEjecutivoOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionWhatsappInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailOutView;


/**
 * The Class EjecutivoSelectSEIImpl.
 */
@Component("ejecutivoSelectSEI")
public class EjecutivoSelectSEIImpl implements EjectivoSelectSEI{
	
	/** The ejecutivo select manager. */
	@Autowired
	EjecutivoSelectManager ejecutivoSelectManager;

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.EjectivoSelectSEI#InicioOperadorEjecutivo()
	 */
	@Override
	public Respuesta<InicioOperadorEjecutivoOutView> InicioOperadorEjecutivo() {
		return ejecutivoSelectManager.InicioOperadorEjecutivo();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.EjectivoSelectSEI#notificarMensajeWhatsapp()
	 */
	@Override
	public Respuesta<Void> notificarMensajeWhatsapp(NotificacionWhatsappInView inView) {
		return ejecutivoSelectManager.notificarMensajeWhatsapp(inView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.EjectivoSelectSEI#notificarLlamadaPorTel(org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<NotificacionLLamadaPorTelOutView> notificarLlamadaPorTel(MessageContext mc) {
		NotificacionLLamadaPorTelInView inView = new NotificacionLLamadaPorTelInView();
		inView.setSessionId(mc.getHttpServletRequest().getSession().getId());
		return ejecutivoSelectManager.notificarLlamadaPorTel(inView);

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.EjectivoSelectSEI#inicioFlujoEMail()
	 */
	@Override
	public Respuesta<InicioFlujoEMailOutView> inicioFlujoEMail() {
		return ejecutivoSelectManager.inicioFlujoEMail();

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.sei.EjectivoSelectSEI#enviarEmail(ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailInView, org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<OperadorEjecutivoEnviarMailOutView> enviarEmail(OperadorEjecutivoEnviarMailInView inView, MessageContext mc) {
		return ejecutivoSelectManager.enviarEmail(inView ,mc.getHttpServletRequest().getSession().getId());

	}


}
