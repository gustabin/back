/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenOutEntity;

/**
 * The Interface OrdenDAO.
 */
public interface OrdenDAOBpriv {

	/**
	 * Cargar ordenes.
	 *
	 * @param ordenInEntity
	 *            the orden in entity
	 * @return the orden out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	OrdenOutEntity cargarOrdenes(OrdenInEntity ordenInEntity) throws DAOException;
	
	
	/**
	 * Confirmar Ordenes.
	 *
	 * @param confirmarOrdenInEntity
	 *            the confirmar orden in entity
	 * @return the confirmar orden out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConfirmarOrdenOutEntity confirmarOrden (ConfirmarOrdenInEntity confirmarOrdenInEntity)throws DAOException;

}
