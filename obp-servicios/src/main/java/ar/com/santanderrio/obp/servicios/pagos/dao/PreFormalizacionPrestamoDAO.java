/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Interface PreFormalizacionPrestamoDAO.
 */
@Component
public interface PreFormalizacionPrestamoDAO {

	/**
	 * Obtener pre formalizacion.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @return the pre formalizacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	PreFormalizacion obtenerPreFormalizacion(Prestamo prestamo) throws DAOException;

	/**
	 * Obtener pre formalizacion.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the pre formalizacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	PreFormalizacion obtenerPreFormalizacion(Cuenta cuenta) throws DAOException;

	/**
	 * Preformalizacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroSucursal
	 *            the nro sucursal
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @return the pre formalizacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	PreFormalizacion obtenerPreFormalizacion(Cliente cliente, String nroSucursal, String nroCuentaProducto)
			throws DAOException;

}