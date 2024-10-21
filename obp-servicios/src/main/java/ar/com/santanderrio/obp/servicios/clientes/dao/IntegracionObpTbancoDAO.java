/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao;

/**
 * Mas que nada esta funcionalidad esta dada hasta que todos los usuarios de Obp
 * se migren a TBanco.<br/>
 * 
 * @author sergio.e.goldentair
 *
 */
public interface IntegracionObpTbancoDAO {

	/**
	 * Acceder al storeprocedure que valida si el usuario se puede loguear en
	 * obp o tbanco.<br/>
	 * True sigue en Tbanco y False va a OBP.<br/>
	 * Si hubiera algun fallo del store se devuelve False para que al usuario se
	 * lo redireccione a Obp.
	 *
	 * @param nup
	 *            the nup
	 * @return the boolean
	 */
	Boolean usuarioHabilitadoAccederTbanco(String nup);
}
