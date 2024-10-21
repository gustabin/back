package ar.com.santanderrio.obp.servicios.convenioempleados.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Path("/convenioEmpleadoPublico")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public interface ConvenioEmpleadosSEI {

	@POST
	@Path("/obtenerTokenEncriptado")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TokenView> obtenerTokenEncriptado();
	
}
