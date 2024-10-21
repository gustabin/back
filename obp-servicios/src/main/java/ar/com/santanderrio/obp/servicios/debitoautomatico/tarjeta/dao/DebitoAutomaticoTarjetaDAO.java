/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao;

import org.springframework.dao.QueryTimeoutException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.ClienteDebitoTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.SolicitudAdhesionDebitoTarjetaEntity;

/**
 * The Interface DebitoAutomaticoTarjetaDAO.
 *
 * @author florencia.n.martinez
 */
public interface DebitoAutomaticoTarjetaDAO {

	/**
	 * Solicita la adhesion de un servicio a la tarjeta de credito.
	 *
	 * @param datosClienteDebito
	 *            the datos cliente debito
	 * @return the solicitud adhesion debito tarjeta entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws QueryTimeoutException
	 *             the query timeout exception
	 */
	SolicitudAdhesionDebitoTarjetaEntity solicitarAdhesionTarjeta(ClienteDebitoTarjetaInEntity datosClienteDebito)
			throws DAOException, QueryTimeoutException;
}
