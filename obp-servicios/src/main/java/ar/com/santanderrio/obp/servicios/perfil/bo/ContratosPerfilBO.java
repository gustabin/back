/**
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;

/**
 * The interface ContratosPerfilBO.
 */
public interface ContratosPerfilBO {

	/**
	 * Consulta contratos desde perfil.
	 *
	 * @return the respuesta
	 */
	Respuesta<ContratosPerfil> consultaContratos();

}
