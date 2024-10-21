/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequesEntity;

/**
 * The Interface SimulacionAltaChequeDAO.
 */
public interface SimulacionAltaChequeDAO {

	/**
	 * Simular alta cheques DAO.
	 *
	 * @param cheques
	 *            the cheques
	 * @param cliente
	 *            the cliente
	 * @return the simulacion alta cheques entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionAltaChequesEntity simularAltaChequesDAO(ChequesASimularDTO cheques, Cliente cliente) throws DAOException;
}
