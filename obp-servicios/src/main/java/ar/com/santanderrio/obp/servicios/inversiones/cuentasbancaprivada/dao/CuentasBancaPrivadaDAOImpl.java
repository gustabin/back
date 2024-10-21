/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
 
/**
 * The Class CuentasBancaPrivadaDAOImpl.
 *
 * @author pablo.d.gargaglione
 */
@Component
@TargetSystem(DataBase.BPRIV)
public class CuentasBancaPrivadaDAOImpl extends IatxBaseDAO implements CuentasBancaPrivadaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentasBancaPrivadaDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;
	
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
	/** The canal bca priv. */
	@Value("${INVERSIONES.CANAL.BANCAPRIVADA}")
	private String canalBcaPriv;
	
	/** The sub canal bca priv. */
	@Value("${INVERSIONES.SUBCANAL.BANCAPRIVADA}")
	private String subCanalBcaPriv;
	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dao.CuentasBancaPrivadaDAO#consultarSaldoCtasConApertura(ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaInEntity, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity)
	 */
	@Override
	public ConsultaSaldoCtasConAperturaOutEntity consultarSaldoCtasConApertura(
			ConsultaSaldoCtasConAperturaInEntity entity, Cliente cliente, CuentaSaldoInEntity cuentaSaldoInEntity) throws DAOException {
		
		String servicio = "CNSSDCTABP";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaSaldoCtasConAperturaOutEntity consultaSaldoCtasConAperturaOutEntity = new ConsultaSaldoCtasConAperturaOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSSDCTABP(entity, cliente, cuentaSaldoInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaSaldoCtasConAperturaOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaSaldoCtasConAperturaOutEntity.class);
			} else {
				LOGGER.error("Error de IATX desconocido invocando a %s %s %s", servicio, version, errorCode);
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return consultaSaldoCtasConAperturaOutEntity;
		
	}

	
	
	/**
	 * Manejo las exepciones de Iatx.
	 *
	 * @param e
	 *            the e
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void handleIatxException(IatxException e) throws DAOException {
		if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
			LOGGER.error(e.getMessage(), e);
			throw new TimeOutException(e.getMessage());
		} else {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}
	
	
	
	/**
	 * Generate request data CNSSDCTABP.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @param cuentaSaldoInEntity
	 *            the cuenta saldo in entity
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSSDCTABP(ConsultaSaldoCtasConAperturaInEntity entity, Cliente cliente, CuentaSaldoInEntity cuentaSaldoInEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.setUsuarioRacfId(cuentaSaldoInEntity.getUsuario());
		iatxRequestData.setUsuarioRacfPwd(cuentaSaldoInEntity.getPass());
		iatxRequestData.setCanalTipo(canalBcaPriv);
		iatxRequestData.setSubCanalTipo("0"+subCanalBcaPriv);
		iatxRequestData.addBodyValue(entity.getCuenta());
		iatxRequestData.addBodyValue(entity.getMoneda());
		return iatxRequestData;
	}
	
}
