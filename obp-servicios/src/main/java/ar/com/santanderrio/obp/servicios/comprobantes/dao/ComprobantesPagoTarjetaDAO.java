/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXOutEntity;

/**
 * Acceso al servicio para retornar los comprobantes de visa u amex.
 * 
 * @author dante.omar.olmedo
 *
 */
public interface ComprobantesPagoTarjetaDAO {
	/**
	 * Realiza la consulta llamando al servicio de IATX correspondiente.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta comprobante out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteVisaAmexIATXOutEntity consultar(ComprobanteVisaAmexIATXInEntity entity) throws DAOException;

	/**
	 * Realiza la consulta de manera asincronica.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the future
	 * @throws DAOException
	 *             the DAO exception
	 */
	Future<ComprobanteVisaAmexIATXOutEntity> consultarAsync(ComprobanteVisaAmexIATXInEntity inEntity)
			throws DAOException;

	/**
	 * Devuelve un ComprobanteVisaAmexIATXOutEntity con error.
	 *
	 * @return the future
	 */
	Future<ComprobanteVisaAmexIATXOutEntity> obtenerOutEntityErrorAsync();
}
