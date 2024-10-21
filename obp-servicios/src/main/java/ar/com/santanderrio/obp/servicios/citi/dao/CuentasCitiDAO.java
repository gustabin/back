/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiInEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiOutEntity;

/**
 * The Interface CuentasCitiDAO.
 */
public interface CuentasCitiDAO {

	/**
	 * Ejecutar traspaso manual.
	 *
	 * @param cuentasCitiInEntity
	 *            the cuentas citi in entity
	 * @param moneda
	 *            the moneda
	 * @return the consulta traspaso manual out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CuentasCitiOutEntity ejecutarConsultaCuentaCity(
			CuentasCitiInEntity cuentasCitiInEntity, String moneda) throws DAOException;
}