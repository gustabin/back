/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;

/**
 * The Interface ConsultaPaquetesDAO.
 */
public interface ConsultaPaquetesDAO {

	/**
	 * Consultar.
	 *
	 * @param consultaPaquetesInEntity
	 *            the consulta paquetes in entity
	 * @return the consulta paquetes out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaPaquetesOutEntity consultar(ConsultaPaquetesInEntity consultaPaquetesInEntity) throws DAOException;

}
