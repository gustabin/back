/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionResponse;

/**
 * The Interface PoderCompraDAO.
 */
public interface PoderCompraDAO {

	/**
	 * Adherir poder compra.
	 *
	 * @param request
	 *            the request
	 * @return the crear adhesion response
	 * @throws DAOException
	 *             the DAO exception
	 */
	CrearAdhesionResponse adherirPoderCompra(CrearAdhesionRequestEntity request) throws DAOException;
}
