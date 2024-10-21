package ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.web.manager.impl.ChatManagerImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComboView;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.bo.EjecutivoSelectBO;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.dto.NotificacionLlamadaPorTelDTOIn;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.EjecutivoSelectManager;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioFlujoEMailOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioOperadorEjecutivoOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionWhatsappInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;

/**
 * The Class EjecutivoSelectManagerImpl.
 */
@Component
public class EjecutivoSelectManagerImpl implements EjecutivoSelectManager {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatManagerImpl.class);


	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensajeBO. */
	@Autowired
	private MensajeBO mensajeBO;
	
	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	/** The sesion cliente. */
	@Autowired
	private EjecutivoSelectBO ejecutivoSelectBO;
	
	/** The selectores BO. */
	@Autowired
	private DatosSelectoresBO selectoresBO;
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.
	 * EjecutivoSelectManager#InicioOperadorEjecutivo()
	 */
	@Override
	public Respuesta<InicioOperadorEjecutivoOutView> InicioOperadorEjecutivo() {
		if (sesionCliente.getCliente().getSegmento().getOperadorEjecutivo() != null) {
			estadisticaManager.add(EstadisticasConstants.INICIO_OPERADOR_EJECUTIVO, "1");
		} else {
			estadisticaManager.add(EstadisticasConstants.INICIO_OPERADOR_EJECUTIVO, "2");
		}
		InicioOperadorEjecutivoOutView inicioOperadorEjecutivoOutView = new InicioOperadorEjecutivoOutView();
		String mensajeError = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_WHATSAPP_OPERADOR_EJECUTIVO)
				.getMensaje();
		String mensajeLlamadaPorTel = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_LLAMADA_POR_TEL_EJECUTIVO).getMensaje();
		inicioOperadorEjecutivoOutView.setMensajeErrorWhatsapp(mensajeError);
		inicioOperadorEjecutivoOutView.setMensajeLlamadaPorTel(mensajeLlamadaPorTel);
		return respuestaFactory.crearRespuestaOk(InicioOperadorEjecutivoOutView.class, inicioOperadorEjecutivoOutView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.
	 * EjecutivoSelectManager#notificarMensajeWhatsapp()
	 */
	@Override
	public Respuesta<Void> notificarMensajeWhatsapp(NotificacionWhatsappInView inVie) {
		if (inVie.getFeedbackMensajeWhatsapp()) {
			estadisticaManager.add(EstadisticasConstants.NOTIFICACION_WHATSAPP_OPERADOR_EJECTUVO, "1");
		} else {
			estadisticaManager.add(EstadisticasConstants.NOTIFICACION_WHATSAPP_OPERADOR_EJECTUVO, "2");

		}
		return respuestaFactory.crearRespuestaOk(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.
	 * EjecutivoSelectManager#notificarLlamadaPorTel(ar.com.santanderrio.obp.
	 * servicios.ejecutivoselect.view.NotificacionLLamadaPorTelInView)
	 */
	@Override
	public Respuesta<NotificacionLLamadaPorTelOutView> notificarLlamadaPorTel(NotificacionLLamadaPorTelInView inView) {
		return ejecutivoSelectBO.notificarLlamadaPorTel(new NotificacionLlamadaPorTelDTOIn(inView.getSessionId()));
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.EjecutivoSelectManager#inicioFlujoEMail()
	 */
	@Override
	public Respuesta<InicioFlujoEMailOutView> inicioFlujoEMail() {
		InicioFlujoEMailOutView outView = new  InicioFlujoEMailOutView();
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		List<ComboView> comboMotivosEmail = new ArrayList<ComboView>();
		List<Opcion> motivosEmail;
		motivosEmail = selectoresBO.obtenerMotivosEmail();
		for (Opcion motivoEmail : motivosEmail) {
			ComboView motivo = new ComboView();
			motivo.setDescripcion(motivoEmail.getOpcion());
			motivo.setId(motivoEmail.getId());
			comboMotivosEmail.add(motivo);
		}
		outView.setMensajeTooltip(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.CODIGO_LEGALES_GENESYS_EMAIL_TOOLTIP));
		outView.setListaMotivos(comboMotivosEmail);
		outView.setEmailCliente(credencialesMya.getEmail());
		outView.setEmailOperador(sesionCliente.getCliente().getSegmento().getOperadorEjecutivo().getEmail());
		
		return respuestaFactory.crearRespuestaOk(InicioFlujoEMailOutView.class, outView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.manager.EjecutivoSelectManager#enviarEmail(ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailInView, java.lang.String)
	 */
	@Override
	public Respuesta<OperadorEjecutivoEnviarMailOutView> enviarEmail(OperadorEjecutivoEnviarMailInView inView,
			String jSessionId) {
		ChatEmailInDTO dto = new ChatEmailInDTO();
		Respuesta<OperadorEjecutivoEnviarMailOutView> respuesta;
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		if (credencialesMya != null) {
			dto.setEmail(credencialesMya.getEmail());
		} else {
			LOGGER.info("Chat : No se pudo cargar el mail de MyA");
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL_ERRONEO);
			return respuesta;
		}
		List<Opcion> motivosEmail;
		motivosEmail = selectoresBO.obtenerMotivosEmail();
		String motivoSeleccionado = null;
		for (Opcion motivoEmail : motivosEmail) {
			if (inView.getMotivoSeleccionado().equals(motivoEmail.getId())) {
				motivoSeleccionado = motivoEmail.getOpcion();
			}
		}
		dto.setSubject(motivoSeleccionado);
		dto.setMensaje(inView.getMensajeTexto());
		dto.setUserData("WASID=" + jSessionId);
		dto.setUserData2("NUP=" + sesionCliente.getCliente().getNup());
		dto.setMailBox(sesionCliente.getCliente().getSegmento().getOperadorEjecutivo().getEmail());
		return ejecutivoSelectBO.enviarEMail(dto);
	}



}
