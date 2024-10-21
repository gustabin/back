/**
 * 
 */
package ar.com.santanderrio.obp.servicios.logoutmobile.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.logoutmobile.entities.LogoutMobileOutEntity;

/**
 * @author sergio.e.goldentair
 *
 */
public interface LogoutMobileDAO {

	/**
	 * WebService para cerrar la session en la app mobile, retorna respuesta espera
	 * o exception por no poder crear cliente rest o robotexception por cualquier
	 * error que no permita continuar el flujo.
	 * 
	 * @param datoEncriptado
	 * @return
	 * @throws DAOException
	 */
	LogoutMobileOutEntity logoutMobile(String datoEncriptado) throws DAOException;

	/**
	 * Obtener el json encriptado para ser enviado en el cierre de sesion mobile
	 * {@link LogoutMobileDAO#logoutMobile(String)}.
	 * 
	 * @param cliente
	 * @return
	 * @throws DAOException
	 */
	String getDatoEncriptado(Cliente cliente) throws DAOException;
}
