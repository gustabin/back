/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaOutEntity;

/**
 * The Interface CambioGrupoAfinidadConsultaTarjetaDAO.
 */
public interface CambioGrupoAfinidadConsultaTarjetaDAO {

	/**
	 * Consulta datos tarjetas.
	 *
	 * @param in
	 *            the in
	 * @return the consulta datos tarjeta out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDatosTarjetaOutEntity consultaDatosTarjetas(ConsultaDatosTarjetaInEntity in) throws DAOException;
	
}
