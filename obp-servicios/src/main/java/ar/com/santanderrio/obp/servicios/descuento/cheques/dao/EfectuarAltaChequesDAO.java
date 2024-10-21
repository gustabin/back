/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EfectivizacionAltaChequesEntity;

/**
 * The Interface EfectuarAltaChequesDAO.
 */
public interface EfectuarAltaChequesDAO {

	/**
	 * Efectuar alta cheques DAO.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @return the efectivizacion alta cheques entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	EfectivizacionAltaChequesEntity efectuarAltaChequesDAO(String nroTramite, Cliente cliente) throws DAOException;
}
