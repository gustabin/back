/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXOutEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * La clase CNSTJCPDODAOImpl.
 * 
 * @author dante.omar.olmedo
 *
 */
@Component
public class ComprobantesPagoTarjetaDAOImpl extends IatxBaseDAO implements ComprobantesPagoTarjetaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesPagoTarjetaDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoTarjetaDAO#consultarAsync(ar.com.santanderrio.obp.
	 * servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity)
	 */
	@Async
	@Override
	public Future<ComprobanteVisaAmexIATXOutEntity> consultarAsync(ComprobanteVisaAmexIATXInEntity inEntity)
			throws DAOException {
		return new AsyncResult<ComprobanteVisaAmexIATXOutEntity>(consultar(inEntity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoTarjetaDAO#obtenerOutEntityErrorAsync()
	 */
	@Async
	@Override
	public Future<ComprobanteVisaAmexIATXOutEntity> obtenerOutEntityErrorAsync() {
		return new AsyncResult<ComprobanteVisaAmexIATXOutEntity>(obtenerOutEntityError());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoTarjetaDAO#consultar(ar.com.santanderrio.obp.servicios.
	 * comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity)
	 */
	@Override
	public ComprobanteVisaAmexIATXOutEntity consultar(ComprobanteVisaAmexIATXInEntity inEntity) throws DAOException {
		ComprobanteVisaAmexIATXOutEntity outEntity = new ComprobanteVisaAmexIATXOutEntity();
		String servicio = "CNSTJCPDO_";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSTJCPDO(inEntity);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				outEntity = processTrama(iatxResponse.getTrama(), ComprobanteVisaAmexIATXOutEntity.class);
			} else {
				LOGGER.error("Codigo de error no esperado de iatx en servicio CNSTJCPDO_. ");
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return outEntity;
	}

	/**
	 * Generate request data CNSTJCPDO.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSTJCPDO(ComprobanteVisaAmexIATXInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(inEntity.getCliente());
		// N04
		iatxRequestData.addBodyValue(inEntity.getSucursalCuentaTarjeta());
		// N16
		iatxRequestData.addBodyValue(inEntity.getNroTarjeta());
		// A01
		iatxRequestData.addBodyValue(inEntity.getCodTitularidad());
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaDesde());
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaHasta());
		return iatxRequestData;
	}

	/**
	 * Obtener out entity error.
	 *
	 * @return the comprobante visa amex IATX out entity
	 */
	private ComprobanteVisaAmexIATXOutEntity obtenerOutEntityError() {
		ComprobanteVisaAmexIATXOutEntity comprobanteError = new ComprobanteVisaAmexIATXOutEntity();
		comprobanteError.setCodigoRetornoExtendido("1");
		return comprobanteError;
	}

}
