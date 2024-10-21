/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperacionesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperacionesResponse;

/**
 * The Interface TitulosMercadoCanalDAO.
 */
public interface TitulosMercadoCanalDAO {
	
	
	/**
	 * Consultar operaciones text.
	 *
	 * @param request
	 *            the request
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	//ConsultaOperacionesResponse consultarOperacionesText(ConsultaOperaciones request)throws DAOException;
	List<DatosConsultaOperacionesResponse> consultarOperacionesText(ConsultaOperacionesRequestEntity request)throws DAOException;
	
}
