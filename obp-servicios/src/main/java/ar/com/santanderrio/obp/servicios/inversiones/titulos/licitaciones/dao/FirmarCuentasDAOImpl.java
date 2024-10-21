/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPorFirmarEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FirmarCuentasInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FirmarCuentasOutEntity;

/**
 * The Class FirmarCuentasDAOImpl.
 *
 * @author
 */
@Component("firmarCuentasDAO")
public class FirmarCuentasDAOImpl extends IatxBaseDAO implements FirmarCuentasDAO {

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FondoDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant TRES. */
	private static final int TRES = 3;

	/** The Constant CERO. */
	private static final String CERO = "0";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * FirmarCuentasDAO#firmar(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity
	 * .FirmarCuentasInEntity)
	 */
	@Override
	public FirmarCuentasOutEntity firmar(Cliente cliente, FirmarCuentasInEntity request) throws DAOException {
		String servicio = "ALTATITCTA";
		String version = "100";

		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		FirmarCuentasOutEntity firmaCuentasOutEntity = new FirmarCuentasOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataALTATITCTA(cliente, request);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				firmaCuentasOutEntity = processTrama(iatxResponse.getTrama(), FirmarCuentasOutEntity.class);
			} else {
				LOGGER.error("Codigo de retorno de IATX distinto de OK. Codigo: ", errorCode);
				throw new DAOException();
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return firmaCuentasOutEntity;
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio iatx.
	 *
	 * @param cliente
	 *            the cliente
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataALTATITCTA(Cliente cliente, FirmarCuentasInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getEmail());
		iatxRequestData.addBodyValue(entity.getRazonSocial());
		iatxRequestData.addBodyValue(StringUtils.leftPad(String.valueOf(entity.getCuentasPorFirmarList().size()), TRES, CERO));
		for (CuentasPorFirmarEntity cuentaPorFirmar : entity.getCuentasPorFirmarList()) {
			iatxRequestData.addBodyValue(cuentaPorFirmar.getNumeroDeSucursal());
			iatxRequestData.addBodyValue(cuentaPorFirmar.getNumeroDeCuenta());
		}
		return iatxRequestData;
	}
}
