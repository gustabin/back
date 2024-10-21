/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;

/**
 * The Interface UltimosMovimientosBO.
 */
public interface MovimientosBO {

	/**
	 * Obtener ultimos movimientos.
	 *
	 * @param filtroMovimientos
	 *            the filtro movimientos
	 * @return the respuesta
	 */
	Respuesta<UltimosMovimientosDTO> obtenerMovimientos(ConsultaUltimosMovimientos filtroMovimientos);

	/**
	 * Limpiar cache.
	 *
	 * @param filtroMovimientos
	 *            the filtro movimientos
	 */
	void limpiarCache(ConsultaUltimosMovimientos filtroMovimientos);
	
	
	/**
	 * Obtener movimientos sin cache.
	 *
	 * @param filtroMovimientos
	 *            the filtro movimientos
	 * @return the respuesta
	 */
	Respuesta<UltimosMovimientosDTO> obtenerMovimientosSinCache(ConsultaUltimosMovimientos filtroMovimientos);

}