/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;

/**
 * The Interface ConsultarSucursalesBO.
 *
 * @author B039542
 */
public interface ConsultarSucursalesBO extends BusinessObject {

	/**
	 * Busca una sucursal por numero de identificacion.
	 *
	 * @param id
	 *            debe tener 4 caracteres, completar con 0 (cero) a la izquierda
	 * @return Respuesta con sucursal
	 */
	Respuesta<Sucursal> consultarSucursalPorId(String id);

}
