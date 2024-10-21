/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Interface AltaDestinatarioAliasBO.
 *
 * @author florencia.n.martinez
 */
public interface AltaDestinatarioAliasBO {

	/**
	 * Continuar alta destinatario alias.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 * @param isPesos
	 *            the is pesos
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasInexistenteEliminadoException
	 *             the validacion alias inexistente eliminado exception
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> continuarAltaDestinatarioAlias(Cliente cliente, Cuenta cuenta,
			String alias, Boolean isPesos, String userAgent)
			throws BusinessException, ValidacionAliasInexistenteEliminadoException;

}
