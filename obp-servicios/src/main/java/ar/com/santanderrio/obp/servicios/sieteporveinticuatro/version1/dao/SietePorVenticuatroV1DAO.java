/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;

/**
 * The Interface SietePorVenticuatroV1DAO.
 */
public interface SietePorVenticuatroV1DAO {

	/**
	 * Ejecutar.
	 *
	 * @param xmlRequest
	 *            the consulta perfil inversor
	 * @return the XML response error
	 * @throws DAOException
	 *             the DAO exception
	 */
	XMLResponseEntity ejecutar(XMLRequestEntity xmlRequest) throws DAOException;

	/**
	 * Ejecutar async.
	 *
	 * @param request
	 *            the request
	 * @return the future
	 * @throws DAOException
	 *             the DAO exception
	 */
	Future<XMLResponseEntity> ejecutarAsync(XMLRequestEntity request) throws DAOException;

	/**
	 * Obtener comprobante erroneo async.
	 *
	 * @return the future
	 */
	Future<XMLResponseEntity> obtenerComprobanteErroneoAsync();

}
