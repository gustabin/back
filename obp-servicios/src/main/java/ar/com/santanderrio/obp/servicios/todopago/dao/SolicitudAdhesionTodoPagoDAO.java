/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.todopago.entity.AdhesionTodoPagoInEntity;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;

/**
 * Dao para ejecutar los procedure del package de TodoPago.
 */
public interface SolicitudAdhesionTodoPagoDAO {

	/**
	 * The Enum TipoReporte.
	 */
	public enum TipoReporte {
		/** The adhesion. */
		ADHESION,
		/** The modificacion. */
		MODIFICACION
	}

	/**
	 * Invocacion al store procedure de grabar solicitud de TodoPago.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ResultadoTransaccion> grabarSolicitud(AdhesionTodoPagoInEntity inEntity) throws DAOException;

	/**
	 * Generar comprobante.
	 *
	 * @param view
	 *            the view
	 * @return the reporte
	 * @throws DAOException
	 *             the DAO exception
	 */
	Reporte generarComprobante(ComprobanteAdhesionTodoPagoView view) throws DAOException;
}
