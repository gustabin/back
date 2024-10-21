/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Logica relacionada a la seccion Consultas del Controller de OBP.
 *
 * @author B039543
 */
public interface ConsultasControllerHomeBO {

	/**
	 * Aplica la opcion cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaCuentas(Cliente cliente);
	
	/**
	 * Aplica la opcion cuentas banca privada.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaCuentasBancaPrivada(Cliente cliente);

	/**
	 * Aplica la opcion tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaTarjetas(Cliente cliente);

	/**
	 * Aplica la opcion prestamos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaPrestamos(Cliente cliente);

	/**
	 * Aplica la opcion comprobantes.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaComprobantes(Cliente cliente);

	/**
	 * Aplica la opcion ahorros y beneficios.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaAhorrosBeneficios(Cliente cliente);

	/**
	 * Aplica la opcion seguros.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaSeguros(Cliente cliente);

	/**
	 * Aplica la opcion tarjeta monedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaTarjetaMonedero(Cliente cliente);

	/**
	 * Aplica la opcion resumen impositivo.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaResumenImpositivo(Cliente cliente);
	
	/**
	 * Tiene una sola cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean tieneUnaSolaCuenta(Cliente cliente);
	
	/**
	 * Tiene una sola cuenta banca privada.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean tieneUnaSolaCuentaBancaPrivada(Cliente cliente);

	/**
	 * Consulta si tiene por lo menos un producto monedero
	 * 
	 * @param cliente
	 * @return the boolean
	 */
	Boolean aplicaMonedero(Cliente cliente);

	/**
     * Aplica Echeq.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
	Boolean aplicaECheq(Cliente cliente);

	
	/**
	 * Aplica reclamos.
	 *
	 * @param cliente the cliente
	 * @return the boolean
	 */
	Boolean aplicaReclamos(Cliente cliente);

	
	/**
	 * Aplica gestor deudas
	 * @return
	 */
	Boolean aplicaRecuperaciones();
	
	Boolean aplicaTurnosOnline(Cliente cliente);

}
