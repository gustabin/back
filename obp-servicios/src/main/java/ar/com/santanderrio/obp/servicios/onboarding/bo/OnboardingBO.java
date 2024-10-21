/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.bo;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface OnboardingBO.
 */
public interface OnboardingBO {

	/**
	 * Obtener secciones activas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dispositivo
	 *            the dispositivo
	 * @return the list
	 */
	List<String> obtenerSeccionesActivas(Cliente cliente, String dispositivo);

	/**
	 * Informar listo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dispositivo
	 *            the dispositivo
	 * @param seccion
	 *            the seccion
	 * @return the boolean
	 */
	Boolean informarListo(Cliente cliente, String dispositivo, String seccion);
}
