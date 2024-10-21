package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;

public interface NUPDAO {

	/**
	 * Obtener nup.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the consulta nup
	 * @throws DAOException
	 *             the DAO exception
	 */
	NupDTO obtenerNUP(Cliente cliente) throws DAOException;

}
