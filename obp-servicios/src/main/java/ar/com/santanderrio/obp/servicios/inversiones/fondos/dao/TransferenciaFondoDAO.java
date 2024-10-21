/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaOutEntity;

/**
 * The Interface TransferenciaFondoDAO.
 *
 * @author
 */
public interface TransferenciaFondoDAO {

	/**
	 * Transferir.
	 *
	 * @param entity
	 *            the entity
	 * @return the transferencia out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	TransferenciaOutEntity transferir(TransferenciaInEntity entity) throws DAOException;

	/**
	 * Obtener comprobante.
	 *
	 * @param comprobanteTransferenciaFondoInEntity
	 *            the comprobante transferencia fondo in entity
	 * @return the comprobante transferencia fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteTransferenciaFondoOutEntity obtenerComprobante(
			ComprobanteTransferenciaFondoInEntity comprobanteTransferenciaFondoInEntity) throws DAOException;
}
