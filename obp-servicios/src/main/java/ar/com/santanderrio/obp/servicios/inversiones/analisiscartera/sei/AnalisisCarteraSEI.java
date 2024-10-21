/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleGrillaRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DistribucionRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResponseDashboard;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface AnalisisCarteraSEI.
 */
@Path("/analisisCartera")
public interface AnalisisCarteraSEI {
	
	/**
	 * Obtener dashboard.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDashboardRTL")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ResponseDashboard> obtenerDashboard(RequestDashboard request);
	
	/**
	 * Obtener dashboard B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDashboardBPriv")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ResponseDashboard> obtenerDashboardBPriv(RequestDashboard request);
	
	/**
	 * Grabar estadistica rentabilidad mobile RTL.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaRentabilidadRTL")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	Respuesta<Void> grabarEstadisticaRentabilidadMobileRTL();

	/**
	 * Grabar estadistica rentabilidad mobile BP.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaRentabilidadBpriv")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	Respuesta<Void> grabarEstadisticaRentabilidadMobileBP();
	
	/**
	 * Obtener rentabilidad rendimiento.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerProductosRTL")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimiento(RequestDashboard request);	
	
	/**
	 * Obtener rentabilidad rendimiento B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerProductosBPriv")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimientoBPriv(RequestDashboard request);
	
	/**
	 * Obtener grafico rendimiento.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerGraficoRendimientoRTL")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<GraficoRendimientoView> obtenerGraficoRendimiento(RequestDashboard request);
	
	/**
	 * Obtener grafico rendimiento B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerGraficoRendimientoBPriv")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<GraficoRendimientoView> obtenerGraficoRendimientoBPriv(RequestDashboard request);

	/**
	 * Obtener detalle grilla RTL.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/detalleGrillaRTL")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaRTL(RequestDashboard request);
	
	/**
	 * Obtener detalle grilla B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/detalleGrillaBPriv")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaBPriv(RequestDashboard request);
	
	/**
	 * Obtener grafico rentabilidad RTL.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerGraficoRentabilidadRTL")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadRTL(RequestDashboard request);
	
	/**
	 * Obtener grafico rentabilidad B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerGraficoRentabilidadBPriv")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadBPriv(RequestDashboard request);
	
	/**
	 * Recargar grafico rentabilidad RTL.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/recargarGraficoRentabilidadRTL")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadRTL(RequestDashboard request);

	/**
	 * Recargar grafico rentabilidad BP.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/recargarGraficoRentabilidadBPriv")
	@CustomPreAuthorize(AccionController.IR_INICIO_ANALISIS_CARTERA)
	@Produces(value = { MediaType.APPLICATION_JSON})
	Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadBP(RequestDashboard request);
	
}
