/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.citi.dao.CuentasCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiInEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiOutEntity;
import ar.com.santanderrio.obp.servicios.citi.exceptions.ConsultaCuentaCitiException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class TraspasoManualDAOImpl.
 */
@Component
public class CuentasCitiDAOImpl extends IatxBaseDAO implements CuentasCitiDAO {

	/** The Constant VERSION_TRFCTACRU. */
	private static final String VERSION_CNSCTACONV = "100";

	/** The Constant SERVICIO_TRFCTACRU. */
	private static final String SERVICIO_CNSCTACONV = "CNSCTACONV";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentasCitiDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant ERROR_SALDO_INSUFICIENTE. */
	private static final int ERROR_SALDO_INSUFICIENTE = 10000515;

	/** The Constant ERROR_NO_ACCESO_INFORMACION. */
	private static final int ERROR_NO_ACCESO_INFORMACION = 19999999;
	
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/**
	 * Generate request data TRFCTACRU.
	 *
	 * @param consultaCuentasCitiInEntity
	 *            the consulta traspaso manual in entity
	 * @param moneda
	 *            the moneda
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestData(
	        CuentasCitiInEntity consultaCuentasCitiInEntity, String moneda) {
		IatxRequestData iatxRequestData = new IatxRequestData(consultaCuentasCitiInEntity.getCliente());

		//agregar variables de entrada
		
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getAcceso());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getCuentaCiti());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getEntidad());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getSucursal());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getCuentaAltair());
		
		iatxRequestData.addBodyValue(moneda);
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getAplicacion());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getSucursalRio());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getCuentaRio());
		iatxRequestData.addBodyValue(consultaCuentasCitiInEntity.getCbuCuentaCiti());
		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.TraspasoManualDAO#
	 * ejecutarTraspasoManual(ar.com.santanderrio.obp.servicios.cuentas.entities
	 * .ConsultaTraspasoManualInEntity)
	 */
	@Override
	public CuentasCitiOutEntity ejecutarConsultaCuentaCity(
	        CuentasCitiInEntity consultaTraspasoManualInEntity, String moneda) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSCTACONV, VERSION_CNSCTACONV);

		CuentasCitiOutEntity consultaTraspasoManualOutEntity = new CuentasCitiOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestData(consultaTraspasoManualInEntity, moneda);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (ERROR_NO_ACCESO_INFORMACION == errorCode) {
				LOGGER.error("Error en el servicio, " + iatxResponse.getErrorMessage());
				throw new ConsultaCuentaCitiException(iatxResponse.getErrorMessage());
			}
			if (OK_CODIGO_RETORNO == errorCode) {
			    consultaTraspasoManualOutEntity = processTrama(iatxResponse.getTrama(),
			            CuentasCitiOutEntity.class);
			} else {
			    LOGGER.error("Error en el servicio, " + iatxResponse.getErrorMessage());
			    throw new DAOException(iatxResponse.getErrorMessage());
			}
		} catch (IatxException e) {
		    LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		return consultaTraspasoManualOutEntity;
	}
}
