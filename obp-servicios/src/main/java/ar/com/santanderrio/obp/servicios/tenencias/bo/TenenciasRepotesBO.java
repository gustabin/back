/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasExcelView;

/**
 * The Interface TenenciasRepotesBO.
 */
public interface TenenciasRepotesBO {

	/**
	 * Generar reporte excel tenencias.
	 *
	 * @param respuestaTenencia
	 *            the respuesta tenencia
	 * @param dispositivo
	 *            the dispositivo
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarReporteExcelTenencias(TenenciasExcelView respuestaTenencia, String dispositivo,
			Cliente cliente);

}
