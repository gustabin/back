/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.ConsultaTarjetaTitularDAO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;

/**
 * The Class ConsultaTarjetaTitularDAOImpl.
 */
@Component("cnsTarjTitDAO")
public class ConsultaTarjetaTitularDAOImpl extends IatxBaseDAO implements ConsultaTarjetaTitularDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaTarjetaTitularDAOImpl.class);

	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.CNSTJCDET_}")
	private String servicioConsTarjTit;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.CNSTJCDET_}")
	private String versionConsTarjTit;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/**
	 * Obtener detalle datos tarjeta.
	 *
	 * @param consultaDetalleDatosTarjetaInEntity
	 *            the consulta detalle datos tarjeta in entity
	 * @return the consulta detalle datos tarjeta out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ConsultaDetalleDatosTarjetaOutEntity obtenerDetalleDatosTarjeta(
			ConsultaDetalleDatosTarjetaInEntity consultaDetalleDatosTarjetaInEntity) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioConsTarjTit, versionConsTarjTit);
		ConsultaDetalleDatosTarjetaOutEntity consultaDetalleDatosTarjetaOutEntity = new ConsultaDetalleDatosTarjetaOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSTJCDET(consultaDetalleDatosTarjetaInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaDetalleDatosTarjetaOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaDetalleDatosTarjetaOutEntity.class);
			} else {
				// manejar respuesta ERROR
			    throw new DAOException(String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

		return consultaDetalleDatosTarjetaOutEntity;
	}

	/**
	 * Generate request data CNSTJCDET.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSTJCDET(ConsultaDetalleDatosTarjetaInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTipoTarjeta());
		iatxRequestData.addBodyValue(entity.getNroCuentaTarjeta());
		return iatxRequestData;
	}

}
