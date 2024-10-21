/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.ConsultaPerfilInversorRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ConsultaPerfilInversorViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosTestPerfilViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.InicioFondoView;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface InversionesSEI.
 */
@Path("/inversiones")
public interface InversionesSEI {

	/**
	 * Consultar perfil de inversor.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarPerfilInversor")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ConsultaPerfilInversorViewResponse> consultarPerfilInversor(ConsultaPerfilInversorRequest request);

	/**
	 * Obtener datos para realizar el test de perfil de inversor.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosTestPerfil")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DatosTestPerfilViewResponse> obtenerDatosTestPerfil();

	/**
	 * Obtener datos para realizar el test de perfil de inversor
	 * para Banca Privada.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosTestPerfilBP")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DatosTestPerfilViewResponse> obtenerDatosTestPerfilBP();

	/**
	 * Devuelve los datos necesarios para la pagina de inicio de fondos.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/inicio")
	@CustomPreAuthorize(AccionController.IR_INICIO_FONDOS_COMUNES_INVERSION)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<InicioFondoView> inicioInversiones(InicioInversionesViewRequest request);

	/**
	 * Obtener tenencia consolidada por producto.
	 *
	 * @param estadisticasTotales
	 *            the estadisticas totales
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTenenciaConsolidada")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciaConsolidadaView> obtenerTenenciaConsolidadaPorProducto(
			TenenciaPorProductoInView estadisticasTotales);

	
	/**
	 * Obtener tenencia consolidada por producto.
	 *
	 * @param estadisticasTotales
	 *            the estadisticas totales
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTenenciaConsolidadaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciaConsolidadaBPrivView> obtenerTenenciaConsolidadaPorProductoBPriv(
			TenenciaPorProductoInView estadisticasTotales);
	
	
	
	/**
	 * Obtener tenencia consolidada por producto, para banca personal
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTenenciaConsolidadaHome")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TarjetaTenenciaConsolidadaView> obtenerTenenciaConsolidadaHome(InicioInversionesViewRequest requestView);

	/**
	 * Obtener detalle cuenta custodia por producto.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDetalleCuentaCustodiaPorProducto")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<DetalleCustodiaView> obtenerDetalleCuentaCustodiaPorProducto(DetalleCustodiaInView detalleIn);
	
	@POST
	@Path("/exportarTenenciaConsolidadaRTL")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarTenenciaConsolidadaRTL();
	
	@POST
	@Path("/exportarTenenciaConsolidadaPriv")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarTenenciaConsolidadaBP();
	
	@POST
	@Path("/exportarGrillaFondosRTL")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarGrillaFondosRTL();
	
	@POST
	@Path("/exportarGrillaFondosPriv")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarGrillaFondosBP();	
	
	/**
	 * Obtener tenencia consolidada por producto, para banca privada
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTenenciaConsolidadaHomeBP")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TarjetaTenenciaConsolidadaView> obtenerTenenciaConsolidadaHomeBP(InicioInversionesViewRequest requestView);

	
}
