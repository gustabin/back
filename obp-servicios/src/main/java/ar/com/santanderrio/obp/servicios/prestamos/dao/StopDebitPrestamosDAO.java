/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CompStopDebitPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.StopDebitPrestamosInEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface StopDebitPrestamosDAO.
 */
public interface StopDebitPrestamosDAO {
	
	/**
	 * Confirmar stop debit prestamos.
	 *
	 * @param stopDebitPrestamosInEntity the stop debit prestamos in entity
	 * @param sesionCliente the sesion cliente
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	CompStopDebitPrestamoOutEntity confirmarStopDebitPrestamos(StopDebitPrestamosInEntity stopDebitPrestamosInEntity, SesionCliente sesionCliente)  throws DAOException;
	
}
