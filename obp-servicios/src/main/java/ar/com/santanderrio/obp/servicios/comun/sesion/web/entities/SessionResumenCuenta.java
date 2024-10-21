/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Interface SessionResumenCuenta.
 */
public interface SessionResumenCuenta {

	/**
	 * Sets the resumenes mensuales disponibles by cuenta.
	 *
	 * @param resumenesMensualesDisponibles
	 *            the resumenes mensuales disponibles
	 * @param cuenta
	 *            the cuenta
	 */
	void setResumenesMensualesDisponiblesByCuenta(Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDisponibles,
			AbstractCuenta cuenta);

	/**
	 * Gets the resumenes by cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the resumenes by cuenta
	 */
	Respuesta<List<ResumenMensualCuenta>> getResumenesByCuenta(AbstractCuenta cuenta);

	/**
	 * Gets the resumenes by ids.
	 *
	 * @param ids
	 *            the ids
	 * @param cuenta
	 *            the cuenta
	 * @return the resumenes by ids
	 */
	List<ResumenMensualCuenta> getResumenesByIds(List<String> ids, AbstractCuenta cuenta);

	/**
	 * Marcar vistos.
	 *
	 * @param resumenesVistos
	 *            the resumenes vistos
	 * @param cuenta
	 *            the cuenta
	 */
	void marcarVistos(List<ResumenMensualCuenta> resumenesVistos, AbstractCuenta cuenta);
	
	void setResumenesMensualesDisponiblesByCuentaBP(
			Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDisponibles, AbstractCuenta cuenta);
	
	Respuesta<List<ResumenMensualCuenta>> getResumenesByCuentaBP(AbstractCuenta cuenta);
	
	List<ResumenMensualCuenta> getResumenesBPByIds(List<String> ids, AbstractCuenta cuenta);

}