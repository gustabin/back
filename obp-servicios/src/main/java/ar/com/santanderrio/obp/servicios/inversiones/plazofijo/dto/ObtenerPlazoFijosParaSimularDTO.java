/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;

/**
 * The Class ObtenerPlazoFijosParaSimularDTO.
 */
public class ObtenerPlazoFijosParaSimularDTO {

	/** The plazos fijos para simular. */
	List<PlazoFijoEntity> plazosFijosParaSimular = new ArrayList<PlazoFijoEntity>();

	/** The plazos fijos de venta. */
	List<PlazoFijoEntity> plazosFijosDeVenta = new ArrayList<PlazoFijoEntity>();

	/**
	 * Gets the plazos fijos para simular.
	 *
	 * @return the plazos fijos para simular
	 */
	public List<PlazoFijoEntity> getPlazosFijosParaSimular() {
		return plazosFijosParaSimular;
	}

	/**
	 * Sets the plazos fijos para simular.
	 *
	 * @param plazosFijosParaSimular
	 *            the new plazos fijos para simular
	 */
	public void setPlazosFijosParaSimular(List<PlazoFijoEntity> plazosFijosParaSimular) {
		this.plazosFijosParaSimular = plazosFijosParaSimular;
	}

	/**
	 * Gets the plazos fijos de venta.
	 *
	 * @return the plazos fijos de venta
	 */
	public List<PlazoFijoEntity> getPlazosFijosDeVenta() {
		return plazosFijosDeVenta;
	}

	/**
	 * Sets the plazos fijos de venta.
	 *
	 * @param plazosFijosDeVenta
	 *            the new plazos fijos de venta
	 */
	public void setPlazosFijosDeVenta(List<PlazoFijoEntity> plazosFijosDeVenta) {
		this.plazosFijosDeVenta = plazosFijosDeVenta;
	}

}