/*
 * 
 */
package ar.com.santanderrio.obp.servicios.crm.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.crm.AlertasCrmSoap;
import ar.com.santanderrio.obp.generated.webservices.crm.RequestGrupal;
import ar.com.santanderrio.obp.generated.webservices.crm.ResponseGrupal;
import ar.com.santanderrio.obp.servicios.crm.dao.AlertasCRMDAO;

/**
 * The Class AlertasCRMDAOImpl.
 */
@Component
public class AlertasCRMDAOImpl implements AlertasCRMDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AlertasCRMDAOImpl.class);

	/** The gestor plan V. */
	@Autowired
	@Qualifier("alertasCRM")
	private GestionarWSAbstract<AlertasCrmSoap> gestorAlertasCRM;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.crm.dao.AlertasCRMDAO#consultarCRM(java
	 * .lang.String)
	 */
	@Override
	public ResponseGrupal consultarCRM(String nup) throws DAOException {
		return consultaGrupal(nup);
	}

	/**
	 * Consulta grupal.
	 *
	 * @param nup
	 *            the nup
	 * @return the response grupal
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ResponseGrupal consultaGrupal(String nup) throws DAOException {
		AlertasCrmSoap alertasCRM = null;
		try {
			alertasCRM = gestorAlertasCRM.obtenerPort();
			RequestGrupal requestGrupal = new RequestGrupal();
			requestGrupal.setEstGestion("2");
			requestGrupal.setTipoCons("TOTA");
			requestGrupal.setNup(nup);
			requestGrupal.setCanal("04");
			requestGrupal.setSubCanal("99");
			requestGrupal.setUsuario("");
			return alertasCRM.consultaGrupal(requestGrupal);
		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} finally {
			gestorAlertasCRM.liberarPort(alertasCRM);
		}
	}

}
