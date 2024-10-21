/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;

/**
 * The Interface DeudaPrestamoDAO.
 */
public interface DeudaPrestamoDAO {

	/**
	 * /** Consulta las deudas de prestamos informados para un cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return detalles del prestamo
	 * @throws DAOException
	 *             the DAO exception
	 */
	Prestamo consultarDeudaDePrestamo(Cliente cliente, Cuenta cuenta) throws DAOException;

	/**
	 * Consultar deuda de prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @return the prestamo
	 * @throws DAOException
	 *             the DAO exception
	 */
	Prestamo consultarDeudaDePrestamo(Cliente cliente, String numeroPrestamo) throws DAOException;

	/**
	 * OLYMPUS Consultar deuda de prestamos enviando tipo de prestamo.
	 *
	 * @param consultaOpenCreditInEntity
	 *            the consulta open credit in entity
	 * @return the prestamo
	 * @throws DAOException
	 *             the DAO exception
	 */
	Prestamo consultarDeudaDePrestamoOpenCredit(ConsultaPrestamoOpenCreditInEntity consultaOpenCreditInEntity)
			throws DAOException;

}
