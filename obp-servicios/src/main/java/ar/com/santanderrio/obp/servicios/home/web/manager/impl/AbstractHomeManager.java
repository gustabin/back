/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.entitites.HomeDTO;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class AbstractHomeManager.
 */
@Component
public abstract class AbstractHomeManager {

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/**
	 * Obtener grupo elementos.
	 *
	 * @return the grupo caja home view
	 */
	public GrupoCajaHomeView obtenerGrupoElementos() {
		return obtenerCajas();
	}

	/**
	 * Cantidad ingresos.
	 *
	 * @return the long
	 */
	public long cantidadIngresos() {
		HomeDTO homeDTO = sesionParametros.getHomeDTO();
		if (homeDTO != null) {
			return homeDTO.getCantidadIngresos();
		}
		return -1;
	}

	/**
	 * Obtener cajas.
	 *
	 * @return the list
	 */
	protected abstract GrupoCajaHomeView obtenerCajas();

}
