/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;

/**
 * The Interface ConsultaTarjetaTitularDAO.
 */
public interface ConsultaTarjetaTitularDAO {

	/**
	 * Obtener detalle datos tarjeta.
	 *
	 * @param consultaDetalleDatosTarjetaInEntity
	 *            the consulta detalle datos tarjeta in entity
	 * @return the consulta detalle datos tarjeta out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDetalleDatosTarjetaOutEntity obtenerDetalleDatosTarjeta(
			ConsultaDetalleDatosTarjetaInEntity consultaDetalleDatosTarjetaInEntity) throws DAOException;

}
