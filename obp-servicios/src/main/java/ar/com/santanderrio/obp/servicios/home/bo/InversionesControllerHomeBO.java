/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface InversionesControllerHomeBO.
 */
public interface InversionesControllerHomeBO {

	/**
	 * Aplica plazo fijo.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaPlazoFijo(Cliente cliente);

	/**
	 * Aplica acciones bonos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaAccionesBonos(Cliente cliente);

	/**
	 * Aplica fondos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaFondos(Cliente cliente);

	/**
	 * Aplica licitaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaLicitaciones(Cliente cliente);

	/**
	 * Aplica consolidado.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaConsolidado(Cliente cliente);

	/**
	 * Aplica titulos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaTitulos(Cliente cliente);
	
	/**
	 * Aplica pyl.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaPyl(Cliente cliente);

	Boolean aplicaServiciosInversion(Cliente cliente);
	
	Boolean aplicaDinamico(Cliente cliente);
}
