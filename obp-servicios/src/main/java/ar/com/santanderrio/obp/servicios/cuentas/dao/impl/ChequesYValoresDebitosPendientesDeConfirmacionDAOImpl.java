/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ChequesYValoresDebitosPendientesDeConfirmacionDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * Conector con el servicio CNSVALDEBI.
 *
 * @author b039542
 */
@Component
public class ChequesYValoresDebitosPendientesDeConfirmacionDAOImpl extends AbstractChequesYValoresDAOImpl
		implements ChequesYValoresDebitosPendientesDeConfirmacionDAO {

	/** The servicio cnsvaldebi. */
	@Value("${SERVICIO.PREFIJO.CNSVALDEBI}")
	private String servicioCnsvaldebi;

	/** The version cnsvaldebi. */
	@Value("${SERVICIO.VERSION.CNSVALDEBI}")
	private String versionCnsvaldebi;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.dao.
	 * ChequesYValoresDebitosPendientesDeConfirmacionDAO#
	 * obtenerDebitosPendientesDeConfirmacionPorCuenta(ar.com.santanderrio.obp.
	 * cuentas.entities.AbstractCuenta)
	 */
	@Override
	public List<DetalleMovimientoChequesYValoresEntity> obtenerDebitosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta) throws DAOException {
		List<DetalleMovimientoChequesYValoresEntity> movimientos = null;
		IatxRequest request = new IatxRequest(servicioCnsvaldebi, versionCnsvaldebi);
		movimientos = invocarServicio(cuenta, request, true);
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
		// S2. Total_Valores_debitados_$ N14(12.2) Total Valores debitados $
		iatxResponse.getNextData();
		// S3. Total_Valores_debitados_U$S N14(12.2) Total Valores debitados U$S
		iatxResponse.getNextData();

	}

}
