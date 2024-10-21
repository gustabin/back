package ar.com.santanderrio.obp.servicios.ejecutivoselect.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioFlujoEMailOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.InicioOperadorEjecutivoOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionWhatsappInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailInView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailOutView;

/**
 * The Interface EjectivoSelectSEI.
 */
@Path("/ejecutivoOnline")

public interface EjectivoSelectSEI {

	/**
	 * Inicio operador ejecutivo.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/inicioOperadorEjecutivo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<InicioOperadorEjecutivoOutView> InicioOperadorEjecutivo();

	/**
	 * Notificar mensaje whatsapp.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	@POST
	@Path("/notificarMensajeWhatsapp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> notificarMensajeWhatsapp(NotificacionWhatsappInView inView);
	
	
	/**
	 * Notificar llamada por tel.
	 *
	 * @param mc the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/notificarLlamadaPorTel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<NotificacionLLamadaPorTelOutView> notificarLlamadaPorTel(@Context MessageContext mc);
	
	/**
	 * Inicio flujo E mail.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/inicioFlujoEMail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<InicioFlujoEMailOutView> inicioFlujoEMail();
	
	/**
	 * Enviar email.
	 *
	 * @param inView the in view
	 * @param mc the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/enviarEmail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OperadorEjecutivoEnviarMailOutView> enviarEmail(OperadorEjecutivoEnviarMailInView inView ,@Context MessageContext mc);



}
