/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface TransaccionesControllerHomeBO.
 */
public interface TransaccionesControllerHomeBO {

    /**
     * Aplica transferencias.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaTransferencias(Cliente cliente);

    /**
     * Aplica calendario pagos.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaCalendarioPagos(Cliente cliente);

    /**
     * Aplica pago tarjeta.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaPagoTarjeta(Cliente cliente);

    /**
     * Aplica envio efectivo.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaEnvioEfectivo(Cliente cliente);

    /**
     * Aplica billetera virtual.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaBilleteraVirtual(Cliente cliente);

    /**
     * Aplica cesion cheques.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaCesionCheques(Cliente cliente);

    /**
     * Aplica pago haberes.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaPagoHaberes(Cliente cliente);

    /**
	 * Aplica salto a prisma para debin.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
    Boolean aplicaDebin(Cliente cliente);

    /**
     * Aplica dolares.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean aplicaDolares(Cliente cliente);

	/**
	 * Aplica descuento cheques.
	 *
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	boolean aplicaDescuentoCheques(Cliente cliente);
	
	/**
     * Aplica transferencias al exterior.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
	Boolean aplicaTransferenciasExterior(Cliente cliente);
	
	/**
     * Aplica transferencias banca privada.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
	Boolean aplicaTransferenciasBancaPrivada(Cliente cliente);

	/**
     * Aplica Echeq.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
	Boolean aplicaECheq(Cliente cliente);

	/**
	 * Aplica orden de pago del exterior
	 * @param cliente
	 * @return
	 */
	Boolean aplicaOrdenDePagoDelExterior(Cliente cliente);

	
	/**
     * Aplica dolares banca privada.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
	Boolean aplicaDolaresBP(Cliente cliente);
	
	Boolean aplicaExtraccionEfectivo(Cliente cliente);

	/**
     * Aplica extracción en santander express.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
	Boolean aplicaExtraccionEnSantanderExpress(Cliente cliente);
}
