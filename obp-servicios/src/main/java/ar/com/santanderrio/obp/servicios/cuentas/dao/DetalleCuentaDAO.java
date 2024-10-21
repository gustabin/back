/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaInexistenteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaMigradaException;

/**
 * The Interface DetalleCuentaDAO.
 */
/**
 * @author B043069
 *
 */
public interface DetalleCuentaDAO {

	/**
	 * Obtener detalle cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<DetalleCuentaEntity> obtenerDetalleCuenta(Cuenta cuenta);

	/**
	 * Obtiene cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param sucursalCuenta
	 *            the sucursal cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the cliente
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaMigradaException
	 *             the cuenta migrada exception
	 */
	Cliente obtenerCuenta(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta)
			throws DAOException, CuentaMigradaException;

	/**
	 * Obtener datos cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param sucursalCuenta
	 *            the sucursal cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the datos cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	DatosCliente obtenerDatosCliente(Cliente cliente, TipoCuenta tipoCuenta, String sucursalCuenta, String nroCuenta)
			throws DAOException;

	/**
	 * Actualizar saldo.
	 *
	 * @param cuentasMonetarias
	 *            the cuentas monetarias
	 * @param cliente
	 *            the cliente
	 * @param cantidadCuentas
	 *            the cantidad cuentas
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Cuenta> actualizarSaldo(List<Cuenta> cuentasMonetarias, Cliente cliente, int cantidadCuentas)
			throws DAOException;

	/**
	 * Consulta detalle cuenta.
	 *
	 * @param consultaDetalleCuentaInEntity
	 *            the consulta detalle cuenta in entity
	 * @return the consulta detalle cuenta out entity
	 * @throws DAOException
	 *             the DAO exception
	 */

	ConsultaDetalleCuentaOutEntity consultaDetalleCuenta(ConsultaDetalleCuentaInEntity consultaDetalleCuentaInEntity)
			throws DAOException;

	/**
	 * Obtener cuenta 2.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param sucursalCuenta
	 *            the sucursal cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the cliente
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaInexistenteException
	 *             the cuenta inexistente exception
	 * @throws CuentaMigradaException
	 *             the cuenta migrada exception
	 */
	Cliente obtenerCuenta2(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta)
			throws DAOException, CuentaInexistenteException, CuentaMigradaException;
}
