/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleGrillaRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DistribucionRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResponseDashboard;

/**
 * The Class AnalisisCarteraSEIImpl.
 */
@Component
public class AnalisisCarteraSEIImpl implements AnalisisCarteraSEI {
 
	/** The analisis cartera manager. */
	@Autowired
	private AnalisisCarteraManager analisisCarteraManager;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerDashboard(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<ResponseDashboard> obtenerDashboard(RequestDashboard request) {
		return analisisCarteraManager.obtenerDashboard(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerDashboardBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<ResponseDashboard> obtenerDashboardBPriv(RequestDashboard request) {
		return analisisCarteraManager.obtenerDashboardBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#grabarEstadisticaRentabilidadMobileRTL()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRentabilidadMobileRTL() {
		return analisisCarteraManager.grabarEstadisticaRentabilidadMobileRTL();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#grabarEstadisticaRentabilidadMobileBP()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRentabilidadMobileBP() {
		return analisisCarteraManager.grabarEstadisticaRentabilidadMobileBP();
	}

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerRentabilidadRendimiento(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimiento(RequestDashboard request) {
		return analisisCarteraManager.obtenerRentabilidadRendimiento(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerRentabilidadRendimientoBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimientoBPriv(RequestDashboard request) {
		return analisisCarteraManager.obtenerRentabilidadRendimientoBPriv(request);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerGraficoRendimiento(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRendimientoView> obtenerGraficoRendimiento(RequestDashboard request) {
		return analisisCarteraManager.obtenerGraficoRendimiento(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerGraficoRendimientoBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRendimientoView> obtenerGraficoRendimientoBPriv(RequestDashboard request) {
		return analisisCarteraManager.obtenerGraficoRendimientoBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerDetalleGrillaRTL(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaRTL(RequestDashboard request) {
		return analisisCarteraManager.obtenerDetalleGrillaRTL(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerDetalleGrillaBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaBPriv(RequestDashboard request) {
		return analisisCarteraManager.obtenerDetalleGrillaBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerGraficoRentabilidadRTL(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadRTL(RequestDashboard request) {
		return analisisCarteraManager.obtenerGraficoRentabilidadRTL(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#obtenerGraficoRentabilidadBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadBPriv(RequestDashboard request) {
		return analisisCarteraManager.obtenerGraficoRentabilidadBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#recargarGraficoRentabilidadRTL(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadRTL(RequestDashboard request) {
		return analisisCarteraManager.recargarGraficoRentabilidadRTL(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.sei.AnalisisCarteraSEI#recargarGraficoRentabilidadBP(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadBP(RequestDashboard request) {
		return analisisCarteraManager.recargarGraficoRentabilidadBP(request);

	}
}
