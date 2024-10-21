/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaOutEntity;

/**
 * The Interface FondoDAO para banca privada.
 *
 * @author
 */
public interface FondoBPrivDAO {

	/**
	 * Suscribir banca privada.
	 *
	 * @param suscripcionInEntity
	 *            the suscripcion in entity
	 * @return the suscripcion fondo banca privada out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionFondoBancaPrivadaOutEntity simularSuscripcionBPriv(SimulacionFondoBancaPrivadaInEntity suscripcionInEntity)
			throws DAOException;

	SimulacionFondoBancaPrivadaOutEntity simularRescateBPriv(SimulacionFondoBancaPrivadaInEntity rescateInEntity)
			throws DAOException;

	SimulacionFondoBancaPrivadaOutEntity simularTransferenciaBPriv(SimulacionFondoBancaPrivadaInEntity transferirInEntity)
			throws DAOException;

	/**
	 * Suscribir.
	 *
	 * @param entity
	 *            the entity
	 * @return the ejecucion fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	EjecucionFondoBancaPrivadaOutEntity suscribir(EjecucionFondoBancaPrivadaInEntity entity) throws DAOException;
	EjecucionFondoBancaPrivadaOutEntity rescatar(EjecucionFondoBancaPrivadaInEntity entity) throws DAOException;
	EjecucionFondoBancaPrivadaOutEntity transferir(EjecucionFondoBancaPrivadaInEntity entity) throws DAOException;
}
