/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualOutEntity;

/**
 * The Interface TraspasoManualDAO.
 */
public interface TraspasoManualDAO {

	/**
	 * Ejecutar traspaso manual.
	 *
	 * @param consultaTraspasoManualInEntity
	 *            the consulta traspaso manual in entity
	 * @return the consulta traspaso manual out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaTraspasoManualOutEntity ejecutarTraspasoManual(
			ConsultaTraspasoManualInEntity consultaTraspasoManualInEntity) throws DAOException;
}