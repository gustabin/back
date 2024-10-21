/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;

/**
 * The Interface ConsultarSucursalesService.
 */
public interface ConsultarSucursalesService extends Service {

	/**
	 * Busca una sucursal por numero de identificacion.
	 *
	 * @param id
	 *            debe tener 4 caracteres, completar con 0 (cero) a la izquierda
	 * @return Respuesta con sucursal
	 */
	Respuesta<Sucursal> consultarSucursalPorId(String id);

}
