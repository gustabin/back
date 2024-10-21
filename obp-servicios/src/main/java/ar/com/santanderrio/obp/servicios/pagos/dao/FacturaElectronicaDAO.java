/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.pagos.entities.ConsultaFacturaElectronica;
import ar.com.santanderrio.obp.servicios.pagos.entities.FacturaElectronica;

/**
 * The Interface FacturaElectronicaDAO.
 */
public interface FacturaElectronicaDAO {

	/**
	 * Consultar factura electronica.
	 *
	 * @param request
	 *            the request
	 * @return the factura electronica
	 * @throws DAOException
	 *             the DAO exception
	 */
	FacturaElectronica consultarFacturaElectronica(ConsultaFacturaElectronica request) throws DAOException;

}
