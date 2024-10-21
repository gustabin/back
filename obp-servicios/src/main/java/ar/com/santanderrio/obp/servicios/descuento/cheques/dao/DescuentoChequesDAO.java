/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DescuentoChequesEntity;

/**
 * The Interface DescuentoChequesDAO.
 */
public interface DescuentoChequesDAO {

	/**
	 * Obtener operaciones.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @param esPrecargada
	 *            the es precargada
	 * @param filtro
	 *            the filtro
	 * @return the descuento cheques entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DescuentoChequesEntity obtenerOperaciones(String nroTramite, Cliente cliente, boolean esPrecargada, String filtro) throws DAOException;

}
