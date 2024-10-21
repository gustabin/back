/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface ReporteDAO.
 *
 * @author B039636
 */
public interface ReporteDAO {

	/**
	 * Obtener reporte.
	 *
	 * @param body
	 *            the body
	 * @param proceso
	 *            the proceso
	 * @param cliente
	 *            the cliente
	 * @param isHelperBasico
	 *            the is helper basico
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente,Boolean isHelperBasico);

	/**
	 * Obtener un repote de un listado de files.
	 *
	 * @param body
	 *            the body
	 * @param proceso
	 *            the proceso
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerReporte(Object body, List<String> proceso, Cliente cliente);

}
