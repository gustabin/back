/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.ConsultaTitularInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPinEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TitularOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPOutEntity;

/**
 * The Interface CuentaSaldoDAO.
 *
 * @author pablo.d.gargaglione
 */
public interface CuentaSaldoDAO {

	/**
	 * Devuelve el saldo de las cuentas de banca privada de un cliente.
	 *
	 * @param in
	 *            the in
	 * @return CuentaSaldoOutEntity
	 */

	CuentaSaldoOutEntity verSaldos(CuentaSaldoInEntity in);
	
	/**
	 * Ver saldos cuentas banca privada.
	 *
	 * @param in
	 *            the in
	 * @return the cuenta saldo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CuentaSaldoOutEntity verSaldosCuentasBancaPrivada(CuentaSaldoInEntity in) throws DAOException;

	/**
	 * Ver movimientos cuenta banca privada.
	 *
	 * @param in
	 *            the in
	 * @return the movimientos cuenta BP out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	MovimientosCuentaBPOutEntity verMovimientosCuentaBancaPrivada (MovimientosCuentaBPinEntity in) throws DAOException; 
	
	/**
	 * Consulta titular.
	 *
	 * @param in
	 *            the in
	 * @return the titular out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	TitularOutEntity consultaTitular (ConsultaTitularInEntity in) throws DAOException;
	
	/**
	 * Ejecutar transferencia banca privada.
	 *
	 * @param in
	 *            the in
	 * @return the transferencia BP out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	TransferenciaBPOutEntity ejecutarTransferenciaBancaPrivada (TransferenciaBPInEntity in) throws DAOException;
	
}
