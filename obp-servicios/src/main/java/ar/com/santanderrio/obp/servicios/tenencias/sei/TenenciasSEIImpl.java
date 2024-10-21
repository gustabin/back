/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.sei;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.ResumenInversionesManager;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasPMCView;
import ar.com.santanderrio.obp.servicios.tenencias.manager.ResumenImpositivoTokenManager;
import ar.com.santanderrio.obp.servicios.tenencias.manager.TenenciasDetalleManager;
import ar.com.santanderrio.obp.servicios.tenencias.manager.TenenciasManager;
import ar.com.santanderrio.obp.servicios.tenencias.manager.TenenciasReportesManager;
import ar.com.santanderrio.obp.servicios.tenencias.view.DesafioPresentarView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteFinancieroView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasExcelView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;

/**
 * The Class TenenciasSEIImpl.
 *
 * @author
 */
@Component("tenenciasSEI")
public class TenenciasSEIImpl implements TenenciasSEI {

	/** The tenencias manager. */
	@Autowired
	private TenenciasManager tenenciasManager;

	/** The tenencias reportes manager. */
	@Autowired
	private TenenciasReportesManager tenenciasReportesManager;

	/** The tenencias detalle manager. */
	@Autowired
	private TenenciasDetalleManager tenenciasDetalleManager;

	/** The resumen impositivo manager. */
	@Autowired
	private ResumenImpositivoTokenManager resumenImpositivoManager;
	
	/** The resumen inversiones manager. */
	@Autowired
	private ResumenInversionesManager resumenInversionesManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasSEI#
	 * consultarTenencias(ar.com.santanderrio.obp.servicios.tenencias.view.
	 * TenenciasInView)
	 */
	@Override
	public Respuesta<TenenciasView> consultarTenencias(TenenciasInView viewRequest) {
		return tenenciasManager.consultarTenencias(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasSEI#
	 * reporteTenenciasExcel(ar.com.santanderrio.obp.servicios.tenencias.view.
	 * TenenciasInView)
	 */
	@Override
	public Respuesta<ReporteView> reporteTenenciasExcel(TenenciasInView viewRequest) {
		TenenciasDetalleInView det = new TenenciasDetalleInView();
		det.setAnioDesde(viewRequest.getAnio());
		det.setAnioHasta(viewRequest.getAnio());
		det.setTipoDetalle(0);

		TenenciasExcelView excel = new TenenciasExcelView();

		excel.setAnio(viewRequest.getAnio());
		excel.setResumen(tenenciasManager.consultarTenencias(viewRequest));

		Respuesta<TenenciasDetalleView> resp = tenenciasDetalleManager.consultarAllDetalleTenencias(det);
		excel.setDetalle(resp);
		return tenenciasReportesManager.exportarExcelTenencias(excel);
	}
	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasSEI#exportarResumenFinanciero(ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView)
	 */
	@Override
	public Respuesta<ReporteFinancieroView> exportarResumenFinanciero(TenenciasInView viewRequest) {	
		
		return resumenInversionesManager.obtenerResumenFinancieroTenenciasPDF(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasSEI#challenge(ar
	 * .com.santanderrio.obp.servicios.tenencias.view.DesafioPresentarView)
	 */
	@Override
	public Respuesta<DesafioPresentarView> challenge(DesafioPresentarView viewRequest) {
		return tenenciasManager.challengePresentar(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasSEI#generarVep(ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasPMCView, org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<TenenciasPMCView> generarVep(TenenciasPMCView tenencias, MessageContext mc) {
		return resumenImpositivoManager.obtenerTokenEncriptado(tenencias, mc);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasSEI#habilitado()
	 */
	@Override
	public Respuesta<Boolean> habilitado() {
		return resumenImpositivoManager.habilitado();
	}

}
