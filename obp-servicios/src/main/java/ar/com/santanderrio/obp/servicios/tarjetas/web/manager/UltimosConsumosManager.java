/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;

/**
 * The Interface UltimosConsumosManager.
 *
 * @author sabrina.cis
 *
 */
public interface UltimosConsumosManager {

	/**
	 * Selector de Tarjetas: Obtener ultimos consumos.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @return the respuesta
	 */
	Respuesta<ConsumosView> obtenerUltimosConsumos(String idCuenta);

	/**
	 * Inicio Tarjetas: obtenerUltimosConsumos.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the respuesta
	 */
	Respuesta<ConsumosView> obtenerUltimosConsumos(IdentificacionCuenta identificacionCuenta);

	/**
	 * obtenemos la lista de ultimos consumos almacenados en la session del
	 * usario que luego seran utilizados para la expor5tacion a excel.
	 *
	 * @return the respuesta
	 */
	Respuesta<Reporte> exportarUltimosConsumos();

	/**
	 * Graba estadística en cada consulta de detalle de ultimos consumos.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> verDetalleUltimosConsumos();
	
	/**
	 * Descarga los últimos consumos en formato Excel.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarExcelUltimosConsumos();

    Respuesta<ReporteView> exportarUltimosConsumosPDF();

}
