/**
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.mya.entities.GetEstadoClienteV3DTOOut;
import ar.com.santanderrio.obp.servicios.mya.entities.GetStatusClienteDTOOut;

/**
 * The Interface MyaDAO.
 *
 * @author sergio.e.goldentair
 */
public interface MyaDAO {

	/**
	 * Invocar las operaciones del ws de Mya.
	 *
	 * @param request
	 *            the request
	 * @return MyaXmlResponse
	 * @throws DAOException
	 *             the DAO exception
	 */
	MyaXmlResponse invocarMya(MyaXmlRequest request) throws DAOException;
	
	GetStatusClienteDTOOut invocarGetStatusCliente (MyaXmlRequest request) throws DAOException;

	String invocarConfirmarEmail(MyaXmlRequest request) throws DAOException;

	GetEstadoClienteV3DTOOut invocarGetEstadoClienteV3 (MyaXmlRequest request) throws DAOException;

}
