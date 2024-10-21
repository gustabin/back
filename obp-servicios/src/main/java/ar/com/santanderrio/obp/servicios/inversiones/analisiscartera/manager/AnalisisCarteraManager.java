/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleGrillaRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DistribucionRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResponseDashboard;

/**
 * The Interface AnalisisCarteraManager.
 */
public interface AnalisisCarteraManager {

	/**
	 * Obtener rentabilidad total.
	 *
	 * @param rentabilidadView
	 *            the rentabilidad view
	 * @return the respuesta
	 */
	Respuesta<RentabilidadTotalView> obtenerRentabilidadTotal(RentabilidadTotalInView rentabilidadView);
	
	/**
	 * Obtener dashboard.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<ResponseDashboard> obtenerDashboard(RequestDashboard request);
	
	/**
	 * Obtener dashboard B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<ResponseDashboard> obtenerDashboardBPriv(RequestDashboard request);
	
	/**
	 * Grabar estadistica rentabilidad mobile RTL.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaRentabilidadMobileRTL();
	
	/**
	 * Grabar estadistica rentabilidad mobile BP.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaRentabilidadMobileBP();
	
	/**
	 * Obtener rentabilidad rendimiento.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimiento(RequestDashboard request);
	
	/**
	 * Obtener rentabilidad rendimiento B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimientoBPriv(RequestDashboard request);

	/**
	 * Recargar grafico rentabilidad RTL.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadRTL(RequestDashboard request);

	/**
	 * Recargar grafico rentabilidad BP.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadBP(RequestDashboard request);

	/**
	 * Obtener grafico rendimiento.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<GraficoRendimientoView> obtenerGraficoRendimiento(RequestDashboard request);
	
	/**
	 * Obtener grafico rendimiento B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<GraficoRendimientoView> obtenerGraficoRendimientoBPriv(RequestDashboard request);
	
	/**
	 * Obtener detalle grilla RTL.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaRTL(RequestDashboard request);
	
	/**
	 * Obtener detalle grilla B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaBPriv(RequestDashboard request);
	
	/**
	 * Obtener grafico rentabilidad RTL.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadRTL(RequestDashboard request);
	
	/**
	 * Obtener grafico rentabilidad B priv.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadBPriv(RequestDashboard request);
	
}
