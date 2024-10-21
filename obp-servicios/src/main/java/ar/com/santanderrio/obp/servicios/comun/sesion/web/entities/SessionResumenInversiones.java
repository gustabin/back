package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;

/**
 * The Interface SessionResumenInversiones.
 */
public interface SessionResumenInversiones {

	/**
	 * Sets the resumenes mensuales disponibles by cuenta.
	 *
	 * @param resumenesMensualesDisponibles the resumenes mensuales disponibles
	 * @param cuenta the cuenta
	 */
	void setResumenesMensualesDisponiblesByCuenta(Respuesta<List<ResumenMensualInversiones>> resumenesMensualesDisponibles,
			AbstractCuenta cuenta);

	/**
	 * Gets the resumenes by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the resumenes by cuenta
	 */
	Respuesta<List<ResumenMensualInversiones>> getResumenesByCuenta(AbstractCuenta cuenta);

	/**
	 * Gets the resumenes by ids.
	 *
	 * @param ids the ids
	 * @param cuenta the cuenta
	 * @return the resumenes by ids
	 */
	List<ResumenMensualInversiones> getResumenesByIds(List<String> ids, AbstractCuenta cuenta);

	/**
	 * Marcar vistos.
	 *
	 * @param resumenesVistos the resumenes vistos
	 * @param cuenta the cuenta
	 */
	void marcarVistos(List<ResumenMensualInversiones> resumenesVistos, AbstractCuenta cuenta);

}