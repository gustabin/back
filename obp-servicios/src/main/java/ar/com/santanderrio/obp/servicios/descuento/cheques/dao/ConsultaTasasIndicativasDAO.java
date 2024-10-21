/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasEntity;

/**
 * The Interface ConsultaTasasIndicativasDAO.
 */
public interface ConsultaTasasIndicativasDAO {

    /**
	 * Obtener tasas indicativas.
	 *
	 * @param subProdPaquete
	 *            the sub prod paquete
	 * @param cliente
	 *            the cliente
	 * @return the tasas indicativas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
    TasasIndicativasEntity obtenerTasasIndicativas(String subProdPaquete, Cliente cliente) throws DAOException;
}
