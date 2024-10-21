/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ChequesYValoresCreditosPendientesDeConfirmacionDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * Conector con el servicio CNSVALACRE.
 *
 * @author b039542
 */
@Component
public class ChequesYValoresCreditosPendientesDeConfirmacionDAOImpl extends AbstractChequesYValoresDAOImpl
		implements ChequesYValoresCreditosPendientesDeConfirmacionDAO {

	/** The servicio cnsvalacre. */
	@Value("${SERVICIO.PREFIJO.CNSVALACRE}")
	private String servicioCnsvalacre;

	/** The version cnsvalacre. */
	@Value("${SERVICIO.VERSION.CNSVALACRE}")
	private String versionCnsvalacre;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.dao.
	 * ChequesYValoresCreditosPendientesDeConfirmacionDAO#
	 * obtenerCreditosPendientesDeConfirmacionPorCuenta(ar.com.santanderrio.obp.
	 * cuentas.entities.AbstractCuenta)
	 */
	@Override
	public List<DetalleMovimientoChequesYValoresEntity> obtenerCreditosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta) throws DAOException {
		List<DetalleMovimientoChequesYValoresEntity> movimientos = null;
		IatxRequest request = new IatxRequest(servicioCnsvalacre, versionCnsvalacre);
		movimientos = invocarServicio(cuenta, request, false);
		return movimientos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.dao.impl.AbstractChequesYValoresDAOImpl#
	 * leerDatosNoComunes(ar.com.santanderrio.obp.iatx.dao.IatxResponse)
	 */
	@Override
	protected void leerDatosNoComunes(IatxResponse iatxResponse) {
		// FIXME no utilizados, quizas haya que crear una clase contenedora con
		// estos datos
		// S1. Depositos_24hs_CC_$ N14 (12+2) depositos 24hs pesos
		iatxResponse.getNextData();
		// S2. Depositos_48hs_CC_$ N14 (12+2) depositos 48hs pesos
		iatxResponse.getNextData();
		// S3. Depositos_72hs_CC_$ N14 (12+2) depositos 72hs pesos
		iatxResponse.getNextData();
		// S4. Depositos_24hs_CC_U$S N14 (12+2) depositos 24hs dolares
		iatxResponse.getNextData();
		// S5. Depositos_48hs_CC_U$S N14 (12+2) depositos 48hs dolares
		iatxResponse.getNextData();
		// S6. Depositos_72hs_CC_U$S N14 (12+2) depositos 72hs dolares
		iatxResponse.getNextData();

	}

}
