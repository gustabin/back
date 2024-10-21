/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaCambioDireccionamientoInEntity;

/**
 * The Interface CambioDireccionamientoDAO.
 */
public interface CambioDireccionamientoDAO {

	/**
	 * Cambiar direccionamiento.
	 *
	 * @param consultaCambioDireccionamientoInEntity
	 *            the consulta cambio direccionamiento in entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	void cambiarDireccionamiento(ConsultaCambioDireccionamientoInEntity consultaCambioDireccionamientoInEntity)
			throws DAOException;

}
