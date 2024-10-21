/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;

/**
 * The Interface SessionMovimientos.
 */
public interface SessionMovimientos {

	/**
	 * Gets the detalle ultimos movimientos original.
	 *
	 * @return the detalle ultimos movimientos original
	 */
	Respuesta<DetalleUltimosMovimientos> getDetalleUltimosMovimientosOriginal();

	/**
	 * Gets the detalle ultimos movimientos filtrado.
	 *
	 * @return the detalle ultimos movimientos filtrado
	 */
	Respuesta<DetalleUltimosMovimientos> getDetalleUltimosMovimientosFiltrado();

	/**
	 * Gets the filtro.
	 *
	 * @return the filtro
	 */
	ConsultaUltimosMovimientos getFiltro();

	/**
	 * Setter para detalle ultimos movimientos original.
	 *
	 * @param detalleUltimosMovimientosOriginal
	 *            el nuevo detalle ultimos movimientos original
	 */
	void setDetalleUltimosMovimientosOriginal(Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosOriginal);

	/**
	 * Setter para detalle ultimos movimientos filtrado.
	 *
	 * @param detalleUltimosMovimientosFiltrado
	 *            el nuevo detalle ultimos movimientos filtrado
	 */
	void setDetalleUltimosMovimientosFiltrado(Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosFiltrado);

	/**
	 * Setter para filtro.
	 *
	 * @param filtro
	 *            el nuevo filtro
	 */
	void setFiltro(ConsultaUltimosMovimientos filtro);

}
