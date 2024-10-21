package ar.com.santanderrio.obp.servicios.chat.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatConfigView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEnviarEmailInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEstadisticaInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatOutView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;

@Path("/chat")
public interface ChatSEI {

	@POST
	@Path("/obtenerChat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ChatConfigView> obtenerConfiguracion();
	
	@POST
	@Path("/conectarChat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//Respuesta<ChatOutView> conectar(@Context MessageContext mc, ChatInView in);
	Respuesta<ChatOutView> conectar(@RequestParam(required = false) OfertaComercialView oferta, @Context MessageContext mc);
	
	
	@POST
	@Path("/desconectarChat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ChatOutView> desconectar();
	
	@POST
	@Path("/enviarEmail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> enviarEmail(ChatEnviarEmailInView chatEnviarEmailInView, @Context MessageContext mc);	
	
	@POST
	@Path("/grabarEstadistica")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> grabarEstadistica(ChatEstadisticaInView chatEstadisticaInView);	
}
