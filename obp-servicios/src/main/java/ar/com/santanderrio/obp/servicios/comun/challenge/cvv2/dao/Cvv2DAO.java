/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface Cvv2DAO.
 */
public interface Cvv2DAO {

	/**
	 * Ejecutar validacion cvv2.
	 *
	 * @param marcaTarjeta
	 *            the marca tarjeta
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param vencTarjeta
	 *            the venc tarjeta
	 * @param codSeguridad
	 *            the cod seguridad
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	boolean ejecutarValidacionCvv2(String marcaTarjeta, String nroTarjeta, String vencTarjeta, String codSeguridad,
			Cliente cliente) throws DAOException;

}
