/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.util.List;

import org.apache.cxf.jaxrs.client.WebClient;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.BonificacionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;

/**
 * The Interface RestWebClient.
 */
public interface RestWebClient {

	/**
	 * Obtener cliente rest.
	 *
	 * @param nombreWSRest
	 *            the nombre WS rest
	 * @return the web client
	 * @throws DAOException
	 *             the DAO exception
	 */
	WebClient obtenerClienteRest(String nombreWSRest) throws DAOException;
	
	ListPromocionDto obtenerPromociones(String url ) throws DAOException;
	
	List<BonificacionDto> obtenerBonificacionesSeguros(String url) throws DAOException;

}
