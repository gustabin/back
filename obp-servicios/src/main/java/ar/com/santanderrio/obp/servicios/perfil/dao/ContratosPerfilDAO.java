/**
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dao;

import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;

/**
 * The interface ContratosPerfilDAO.
 */
public interface ContratosPerfilDAO {

	/**
	 * Consultar contratos.
	 *
	 * @return Respuesta<List<ContratoPerfil>>
	 */
	ContratosPerfil consultarContratos();

}
