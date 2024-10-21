/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;

/**
 * The Interface DatosAdicionalesDAO.
 *
 * @author B039636
 */
public interface DatosAdicionalesDAO {

	/**
	 * Alta informacion adicional.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param info
	 *            the info
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<Boolean> altaInformacionAdicional(Cliente respuesta, InformacionAdicional info) throws DAOException;

	/**
	 * Modificacion informacion adicional.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param info
	 *            the info
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<Boolean> modificacionInformacionAdicional(Cliente respuesta, InformacionAdicional info)
			throws DAOException;

}
