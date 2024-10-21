/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionEntity;

/**
 * The Interface ClienteHabilitadoChequesDAO.
 */
public interface ClienteHabilitadoChequesDAO  {

    /**
	 * Obtener habilitado cesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos cesion entity
	 * @throws DAOException
	 *             the DAO exception
	 */
    DatosCesionEntity obtenerHabilitadoCesion(Cliente cliente) throws DAOException;
    
}
