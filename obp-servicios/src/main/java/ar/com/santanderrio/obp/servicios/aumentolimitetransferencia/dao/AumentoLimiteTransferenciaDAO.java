/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;

/**
 * The Interface AumentoLimiteTransferenciaDAO.
 */
public interface AumentoLimiteTransferenciaDAO {

	/**
	 * altaSolicitudAumentoLimiteTransferencia da de alta la gestion de
	 * solicitud de aumento del limite de transferencia.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return resultado de la operacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<AumentoLimiteTransferenciaOutEntity> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInEntity inEntity) throws DAOException;

	/**
	 * Genera el comprobante de la operacion exitosa.
	 *
	 * @param datosComprobante
	 *            the datos comprobante
	 * @return reporte
	 */
	Reporte generarComprobanteAumentoLimiteTransferencia(DatosComprobanteAumentoLimiteTransferencia datosComprobante);

}
