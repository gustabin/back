/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndicesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.VariacionInfoMercadoView;

/**
 * The Interface InfoMercadoSEI.
 */
@Path("/informacionMercado")
public interface InfoMercadoSEI {

	/**
	 * Inicio de informacion de mercado.
	 *
	 * @param view
	 *            the view
	 * @return Respuesta<InfoMercadoView>
	 */
	@POST
	@Path("/inicio")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<InfoMercadoView> inicioInfoMercado(InfoMercadoViewIn view);

	/**
	 * Indices informacion mercado.
	 *
	 * @param view
	 *            the view
	 * @return Respuesta<IndicesView>
	 */
	@POST
	@Path("/obtenerIndices")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<IndicesView> obtenerIndices(InfoMercadoViewIn view);

	/**
	 * Grilla informacion de mercado.
	 *
	 * @param view
	 *            the view
	 * @return Respuesta<IndicesView>
	 */
	@POST
	@Path("/obtenerInfoMercado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<InfoMercadoView> obtenerInfoMercado(InfoMercadoViewIn view);

	/**
	 * Obtener variacion info mercado.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDetalleMercado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<VariacionInfoMercadoView> obtenerVariacionInfoMercado(InfoMercadoViewIn view);

	/**
	 * Estadistica go to compra banca personal.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/goToOrdenCompraBancaPersonal")
	Respuesta<Void> estadisticaGoToCompraBancaPersonal();
	
	/**
	 * Estadistica go to compra banca privada.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/goToOrdenCompraBancaPrivada")
	Respuesta<Void> estadisticaGoToCompraBancaPrivada();


}
