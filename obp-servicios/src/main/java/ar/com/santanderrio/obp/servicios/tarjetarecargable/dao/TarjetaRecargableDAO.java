/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableInEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;

/**
 * The Interface TarjetaRecargableDAO.
 */
public interface TarjetaRecargableDAO {

	/**
	 * Da de alta la gestion de solicitud de una tarjeta recargable.
	 *
	 * @param solicitudTarjetaRecargableInEntity
	 *            the solicitud tarjeta recargable in entity
	 * @return SolicitudTarjetaRecargableOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<SolicitudTarjetaRecargableOutEntity> altaSolicitudTarjetaRecargable(
			SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity) throws DAOException;

	/**
	 * Comprobante solicitud tarjeta recargable.
	 *
	 * @param datosComprobante
	 *            the datos comprobante
	 * @return the reporte
	 */
	Reporte comprobanteSolicitudTarjetaRecargable(DatosComprobanteSolicitudTarjetaRecargableView datosComprobante);
}
