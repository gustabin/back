/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;

/**
 * The Interface ContratosManager.
 */
public interface ContratosManager {

	/**
	 * Consulta contratos.
	 *
	 * @return the respuesta
	 */
	Respuesta<ContratosPerfil> consultaContratos();

	/**
	 * Grabado de estadistica de descarga de contratos.
	 *
	 * @param error
	 *            the error
	 * @return the respuesta
	 */
	Respuesta<Void> estadisticaDescargaContrato(Boolean error);

}
