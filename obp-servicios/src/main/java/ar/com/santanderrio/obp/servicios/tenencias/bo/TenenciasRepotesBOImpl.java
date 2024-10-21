/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TendenciasExcelEntity;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasExcelView;

/**
 * The Class TenenciasRepotesBOImpl.
 */
@Component("tenenciasRepotesBO")
public class TenenciasRepotesBOImpl implements TenenciasRepotesBO {

	/** The reporte dao. */
	@Autowired
	private ReporteDAO reporteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasRepotesBO#
	 * generarReporteExcelTenencias(ar.com.santanderrio.obp.servicios.tenencias.
	 * view.TenenciasExcelView, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> generarReporteExcelTenencias(TenenciasExcelView respuestaTenencia, String dispositivo,
			Cliente cliente) {
		TendenciasExcelEntity base = new TendenciasExcelEntity();

		base.setTenenciasExcelView(respuestaTenencia);

		List<String> listadoArc = new ArrayList<String>();
		listadoArc.add("Tenencias_santander");
		listadoArc.add("TenenciasResumen");
		listadoArc.add("TenenciasDetalle1");
		listadoArc.add("TenenciasDetalle2");
		listadoArc.add("TenenciasDetalle3");
		listadoArc.add("TenenciasDetalle4");

		Respuesta<Reporte> reporte = reporteDAO.obtenerReporte(base, listadoArc, cliente);

		return reporte;
	}

}
