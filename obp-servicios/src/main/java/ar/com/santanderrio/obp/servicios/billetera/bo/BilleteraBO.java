/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.dto.ValidarUsuarioBilleteraDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;

/**
 * The Interface BilleteraBO.
 */
public interface BilleteraBO {

	/**
	 * Marcar adhesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param registroSesion
	 *            the registro sesion
	 * @return true, if successful
	 */
	boolean marcarAdhesion(Cliente cliente, RegistroSesion registroSesion);

	/**
	 * Validar usuario.
	 *
	 * @param idxCtaTp
	 *            the idx cta tp
	 * @param registroSesion
	 *            the registro sesion
	 * @return the respuesta
	 */
	Respuesta<ValidarUsuarioBilleteraDTO> validarUsuario(int idxCtaTp, RegistroSesion registroSesion);

}