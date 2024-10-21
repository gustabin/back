/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.CuentaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.DatosTarjetasExtraccionYComprasExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.TarjetaOperacionExteriorView;

/**
 * ExtraccionYComprasExteriorManagerSEI.
 *
 * @author Silvina_Luque
 */
@Path("/extraccionYComprasExterior")
public interface ExtraccionYComprasExteriorSEI {

	/**
	 * Consulta tarjetas.
	 *
	 * @return DatosTarjetasExtraccionYComprasExteriorView
	 */
	@POST
	@Path("/consultarTarjetas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosTarjetasExtraccionYComprasExteriorView> consultarTarjetasOperacionExterior();

	/**
	 * Consulta cuentas.
	 *
	 * @param tarjetaOperaExteriorView
	 *            the tarjeta opera exterior view
	 * @return Lista cuentas
	 */
	@POST
	@Path("/consultarCuentas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<List<CuentaOperacionExteriorView>> consultarCuentasOperacionExterior(
			TarjetaOperacionExteriorView tarjetaOperaExteriorView);

	/**
	 * Modificacion de tarjeta para operar en el exterior.
	 *
	 * @param modifTarjetaOperaExteriorView
	 *            the modif tarjeta opera exterior view
	 * @return FeedBackModifTarjetaOperaExtraccionView
	 */
	@POST
	@Path("/modificarTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ModifTarjetaOperaExtraccionView> modificarTarjetaOperacionExterior(
			ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView);

	/**
	 * Descarga de comprobante de modifiacion de tarjeta para operar en el
	 * exterior.
	 *
	 * @return ReporteView
	 */
	@POST
	@Path("/descargarComprobante")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobante();

}
