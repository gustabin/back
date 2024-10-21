/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteRescateCitiEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteRescateEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoCitiInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoOutEntity;

/**
 * The Interface RescateDAO.
 *
 * @author b039920
 */
public interface RescateDAO {

	/**
	 * Obtener comprobrante de un rescate de un fondo, este servicio se debe
	 * invocar luego de aceptar la confirmacion del rescate de un fondo.
	 *
	 * @param entity
	 *            the entity
	 * @return ComprobanteRescateEntity el comprobante del rescate
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteRescateEntity comprobanteRescate(FondoInEntity entity) throws DAOException;

	/**
	 * confirmación(simulacion) del rescate de fondos comunes de Inversión. Este
	 * servicio debe invocarce en el momento de la confirmacion de un rescate de
	 * fondo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param entity
	 *            the entity
	 * @return the rescate fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */

	RescateFondoOutEntity simulacionRescate(Cliente cliente, RescateFondoInEntity entity) throws DAOException;

    /**
	 * Comprobante rescate citi.
	 *
	 * @param entity
	 *            the entity
	 * @return the comprobante rescate citi entity
	 * @throws DAOException
	 *             the DAO exception
	 */
    ComprobanteRescateCitiEntity comprobanteRescateCiti(FondoCitiInEntity entity) throws DAOException;
    
    /**
	 * consultaFondosAgendamiento 
	 *
	 * @param codigoFondo
	 * @return Boolean
	 * @throws DAOException
	 *             the DAO exception
	 */
    boolean consultaFondosAgendamiento(String codigoFondo);

}
