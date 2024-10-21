/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

import java.util.concurrent.Future;

import org.beanio.BeanIOException;
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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaDetallePMCOutEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * La clase CNSPESPAGCDAOImpl.
 * 
 * @author dante.omar.olmedo
 *
 */
@Component
public class ComprobantesPagoMisCuentasDAOImpl extends IatxBaseDAO implements ComprobantesPagoMisCuentasDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesPagoMisCuentasDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoMisCuentasDAO#consultarAsync(ar.com.santanderrio.obp.
	 * servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity)
	 */
	@Async
	@Override
	public Future<ConsultaComprobanteOutEntity> consultarAsync(ConsultaComprobanteInEntity inEntity)
			throws DAOException {
		return new AsyncResult<ConsultaComprobanteOutEntity>(consultar(inEntity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoMisCuentasDAO#obtenerOutEntityErrorAsync()
	 */
	@Async
	@Override
	public Future<ConsultaComprobanteOutEntity> obtenerOutEntityErrorAsync() {
		return new AsyncResult<ConsultaComprobanteOutEntity>(obtenerOutEntityError());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoMisCuentasDAO#consultar(ar.com.santanderrio.obp.servicios
	 * .comprobantes.dao.entity.ConsultaComprobanteInEntity)
	 */
	@Override
	public ConsultaComprobanteOutEntity consultar(ConsultaComprobanteInEntity inEntity) throws DAOException {
		ConsultaComprobanteOutEntity outEntity = new ConsultaComprobanteOutEntity();
		String servicio = "CNSPESPAGC";
		String version = "100";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSPESPAGC(inEntity);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				outEntity = processTrama(iatxResponse.getTrama(), ConsultaComprobanteOutEntity.class);
			} else {
				LOGGER.error("Codigo de error no esperado de iatx en servicio CNSPESPAGC. ");
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return outEntity;
	}

	/**
	 * Generate request data CNSPESPAGC.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSPESPAGC(ConsultaComprobanteInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(inEntity.getCliente());
		// A02
		iatxRequestData.addBodyValue(inEntity.getTipoCuenta());
		// A04
		iatxRequestData.addBodyValue(inEntity.getSucursalCuenta());
		// A16
		iatxRequestData.addBodyValue(inEntity.getNroCuenta());
		// N03
		iatxRequestData.addBodyValue(inEntity.getNroOrdenFirmante());
		// N20
		iatxRequestData.addBodyValue(inEntity.getNroTarjetaBanelco());
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaDesde());
		// N08
		iatxRequestData.addBodyValue(inEntity.getFechaHasta());
		return iatxRequestData;
	}

	/**
	 * Obtener out entity error.
	 *
	 * @return the consulta comprobante out entity
	 */
	private ConsultaComprobanteOutEntity obtenerOutEntityError() {
		ConsultaComprobanteOutEntity outEntity = new ConsultaComprobanteOutEntity();
		outEntity.setCodigoRetornoExtendido("1");
		return outEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ComprobantesPagoMisCuentasDAO#consultarDetalle(ar.com.santanderrio.obp.
	 * servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity)
	 */
	@Override
	public ConsultaDetallePMCOutEntity consultarDetalle(ConsultaComprobanteInEntity inEntity) throws DAOException {
		ConsultaDetallePMCOutEntity outEntity = new ConsultaDetallePMCOutEntity();
		String servicio = "CNSPESPAGE";
		String version = "120";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSPESPAGE(inEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				outEntity = processTrama(iatxResponse.getTrama(), ConsultaDetallePMCOutEntity.class);
			} else {
				LOGGER.error("Codigo de error no esperado de iatx en servicio CNSPESPAGE. ");
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (BeanIOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return outEntity;
	}

	/**
	 * Generate request data CNSPESPAGE.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSPESPAGE(ConsultaComprobanteInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(inEntity.getCliente());
		// A04
		iatxRequestData.addBodyValue(inEntity.getEmpresa());
		// AN01
		iatxRequestData.addBodyValue(inEntity.getTipoMonto());
		return iatxRequestData;
	}

}
