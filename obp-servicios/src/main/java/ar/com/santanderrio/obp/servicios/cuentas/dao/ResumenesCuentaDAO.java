/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaOnDemandDTO;

/**
 * The Interface ResumenesCuentaDAO.
 */
public interface ResumenesCuentaDAO {
	
	/**
	 * Ejecutar CTAXNUP.
	 *
	 * @param cliente the cliente
	 * @return the iatx response
	 */
	public List<CuentaOnDemandDTO> ejecutarCTAXNUP(Cliente cliente);
}
