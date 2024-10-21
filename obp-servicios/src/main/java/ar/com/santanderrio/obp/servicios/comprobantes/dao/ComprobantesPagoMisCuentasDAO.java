/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaDetallePMCOutEntity;

/**
 * Acceso al servicio de comprobantes de pago mis cuentas y tarjetas de credito.
 * 
 * @author dante.omar.olmedo
 *
 */
public interface ComprobantesPagoMisCuentasDAO {

	/**
	 * Realiza la consulta llamando al servicio de IATX correspondiente.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta comprobante out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaComprobanteOutEntity consultar(ConsultaComprobanteInEntity entity) throws DAOException;

	/**
	 * Realiza la consulta de manera asincronica.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the future
	 * @throws DAOException
	 *             the DAO exception
	 */
	Future<ConsultaComprobanteOutEntity> consultarAsync(ConsultaComprobanteInEntity inEntity) throws DAOException;

	/**
	 * Retorna un outentity con error.
	 *
	 * @return the future
	 */
	Future<ConsultaComprobanteOutEntity> obtenerOutEntityErrorAsync();

	/**
	 * Consultar detalle.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the consulta detalle PMC out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDetallePMCOutEntity consultarDetalle(ConsultaComprobanteInEntity inEntity) throws DAOException;

}
