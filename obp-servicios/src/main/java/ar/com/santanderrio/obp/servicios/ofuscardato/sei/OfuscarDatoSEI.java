/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ofuscardato.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MailDesafioView;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MostrarMailView;

/**
 * The Interface CompraVentaSEI.
 *
 * @author Emanuel Diaz
 */
@Path("/mostrar")
public interface OfuscarDatoSEI {

	/**
	 * Obtiene los datos iniciales para ofuscar el mail del usuario
	 *
	 * @param mailDesafioView
	 *            
	 */
	@POST
	@Path("/getMail")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MostrarMailView> mostrarMail(MailDesafioView mailDesafioView);

	

}
