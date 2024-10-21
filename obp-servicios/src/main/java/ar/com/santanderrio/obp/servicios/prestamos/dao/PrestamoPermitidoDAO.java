/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;

/**
 * The Interface PrestamoPermitidoDAO.
 *
 * @author
 */
public interface PrestamoPermitidoDAO {

	/**
	 * Consultar prestamos permitidos.
	 *
	 * @param entity
	 *            the entity
	 * @return the prestamo permitido out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	PrestamoPermitidoOutEntity consultarPrestamosPermitidosPreacordados(PrestamoPermitidoInEntity entity) throws DAOException;


	PrestamoPermitidoOutEntity consultarPrestamosPermitidosPreaprobados(PrestamoPermitidoInEntity entity) throws DAOException;

}
