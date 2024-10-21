/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.DeudaInformada;

/**
 * The Interface DeudaInformadaDAO.
 */
public interface DeudaInformadaDAO {

	/**
	 * /** Consulta las deudas informadas para un cliente.
	 *
	 * @param cliente the cliente
	 * @return lista de deudas informadas
	 * @throws DAOException the DAO exception
	 */
	List<DeudaInformada> consultarDeudaInformada(Cliente cliente) throws DAOException;
}
