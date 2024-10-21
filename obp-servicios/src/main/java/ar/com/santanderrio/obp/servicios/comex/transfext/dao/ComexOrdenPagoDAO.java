package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarOrPagoOBPInEntity;

public interface ComexOrdenPagoDAO {

	/**
	 * Procesar orden de pago
	 * 
	 * @param request
	 * @return ProcesarOrPagoOBPResponse
	 * @throws DAOException
	 */
	ProcesarOrPagoOBPResponse procesarOrdenPago(ProcesarOrPagoOBPInEntity inEntity) throws DAOException;

}
