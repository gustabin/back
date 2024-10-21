/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaInDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaOutDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;

/**
 * The Interface AumentoLimiteTransferenciaBO.
 */
public interface AumentoLimiteTransferenciaBO {

	/**
	 * Da de alta la gestion de solicitud de aumento de limite de transferencia.
	 *
	 * @param inDTO
	 *            datos de la solicitud
	 * @return resultado de la gestion
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<AumentoLimiteTransferenciaOutDTO> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInDTO inDTO) throws DAOException;

	/**
	 * Genera el comprobante de la operacion exitosa.
	 *
	 * @param datosComprobante
	 *            the datos comprobante
	 * @return reporte en PDF del comprobante
	 */
	Respuesta<Reporte> generarComprobanteAumentoLimiteTransferencia(
			DatosComprobanteAumentoLimiteTransferencia datosComprobante);
}
