package ar.com.santanderrio.obp.servicios.transferencias.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatariosFrecuentesView;

@Path("/destinatariosFrecuentes")
public interface DestinatariosFrecuentesSEI {

	@POST
	@Path("/obtenerDestinatariosFrecuentes")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<DestinatariosFrecuentesView> obtenerDestinatariosFrecuentes();

	@POST
	@Path("/altaFavorito")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> altaFavorito(DestinatarioView destinatarioView);

	@POST
	@Path("/bajaFavorito")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> bajaFavorito(DestinatarioView destinatarioView);

}
