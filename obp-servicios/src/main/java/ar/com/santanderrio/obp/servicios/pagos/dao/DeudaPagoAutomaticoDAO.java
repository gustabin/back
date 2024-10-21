/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.DeudaPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaAdheridaDebitoAutomatico;

/**
 * The Interface DeudaPagoAutomaticoDAO.
 */
public interface DeudaPagoAutomaticoDAO {

	/**
	 * Consultar deuda pago automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DeudaPagoAutomaticoEntity> consultarDeudaPagoAutomatico(Cliente cliente) throws DAOException;

	/**
	 * Obtener deudas con debito automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DebitoAutomatico> obtenerDeudasConDebitoAutomatico(Cliente cliente, Cuenta cuenta) throws DAOException;

	/**
	 * Devuelve la cuenta aherida al pago de una tarjeta por debito automaticoda
	 * a una sola cuenta de pago.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @return the cuenta adherida debito automatico
	 * @throws DAOException
	 *             the DAO exception
	 */
	CuentaAdheridaDebitoAutomatico obtenerCuentaAdheridaDebitoAutomatico(Cliente cliente, String numeroTarjeta)
			throws DAOException;

}
