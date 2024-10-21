/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.tarjetas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;

/**
 * TarjetasDAO.
 *
 * @author Silvina_Luque
 */
public interface ConsultaTarjetasDAO {

    /**
	 * consultaDatosTarjetas.
	 *
	 * @param in
	 *            the in
	 * @return the consulta datos tarjetas out
	 * @throws DAOException
	 *             the DAO exception
	 */
    ConsultaDatosTarjetasOut consultaDatosTarjetas(ConsultaDatosTarjetasIn in) throws DAOException ;

}
