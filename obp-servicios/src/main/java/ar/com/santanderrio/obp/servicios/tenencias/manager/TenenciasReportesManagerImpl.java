/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasRepotesBO;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasEstadisticaHelper;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasExcelView;

/**
 * The Class TenenciasReportesManagerImpl.
 */
@Component("tenenciasReportesManager")
public class TenenciasReportesManagerImpl implements TenenciasReportesManager {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasReportesManagerImpl.class);

	/** The tenencias repotes BO. */
	@Autowired
	private TenenciasRepotesBO tenenciasRepotesBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The Estadistica helper *. */
	@Autowired
	private TenenciasEstadisticaHelper estHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.manager.
	 * TenenciasReportesManager#exportarExcelTenencias(ar.com.santanderrio.obp.
	 * servicios.tenencias.view.TenenciasExcelView)
	 */
	@Override
	public Respuesta<ReporteView> exportarExcelTenencias(TenenciasExcelView respuestaView) {
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		estHelper.setAnio(respuestaView.getAnio());
		try {
			LOGGER.info("Inicio para generar el excel de las tenencias");
			Respuesta<Reporte> respuestaReporte = getExcelTenencias(respuestaView);
			Reporte reporte = respuestaReporte.getRespuesta();
			if (reporte != null) {
				ReporteView resumenesView = ReporteView.fromReporte(reporte);
				respuesta.setRespuesta(resumenesView);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				estHelper.repEstadisticaTenencia(false);
			} else {
				respuesta.setRespuestaVacia(true);
				respuesta.setRespuesta(null);
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				estHelper.repEstadisticaTenencia(true);
			}
		} catch (RuntimeException e) {
			respuesta.setRespuestaVacia(true);
			respuesta.setRespuesta(null);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			estHelper.repEstadisticaTenencia(true);

		}
		// LOGGER.info(respuesta.getRespuesta().getBytes());
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.manager.
	 * TenenciasReportesManager#getExcelTenencias(ar.com.santanderrio.obp.
	 * servicios.tenencias.view.TenenciasExcelView)
	 */
	@Override
	public Respuesta<Reporte> getExcelTenencias(TenenciasExcelView respuestaView) {
		return tenenciasRepotesBO.generarReporteExcelTenencias(respuestaView,
				sesionParametros.getRegistroSession().getDispositivo(), sesionCliente.getCliente());
	}
}
