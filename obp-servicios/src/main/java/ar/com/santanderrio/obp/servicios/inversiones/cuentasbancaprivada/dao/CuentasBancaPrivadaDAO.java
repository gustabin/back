/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;

/**
 * The Interface CuentasBancaPrivadaDAO.
 * 
 * @author pablo.d.gargaglione
 *
 */ 
public interface CuentasBancaPrivadaDAO {

	
	/**
	 * consulta saldo de cuentas con Apertura.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @param cuentaSaldoInEntity
	 *            the cuenta saldo in entity
	 * @return the consulta saldo ctas con apertura out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	
	ConsultaSaldoCtasConAperturaOutEntity consultarSaldoCtasConApertura(ConsultaSaldoCtasConAperturaInEntity entity, Cliente cliente, CuentaSaldoInEntity cuentaSaldoInEntity) throws DAOException;
	
}
