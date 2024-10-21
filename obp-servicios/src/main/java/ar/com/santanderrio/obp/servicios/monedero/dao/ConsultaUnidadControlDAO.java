/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;

/**
 * The Interface ConsultaUnidadControlDAO.
 *
 * @author alejandro_leal
 */
public interface ConsultaUnidadControlDAO {

	/**
	 * Consulta UC.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta unidad control out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaUnidadControlOutEntity consultaUC(ConsultaUnidadControlInEntity entity) throws DAOException;

}
