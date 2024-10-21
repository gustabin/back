/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;

/**
 * The Interface CuotasPrestamoDAO.
 */
public interface CuotasPrestamoDAO {

	/**
	 * Consultar proximas cuotas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the deuda prestamo entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DeudaPrestamoEntity consultarProximasCuotas(Cliente cliente, Cuenta cuenta) throws DAOException;

}
