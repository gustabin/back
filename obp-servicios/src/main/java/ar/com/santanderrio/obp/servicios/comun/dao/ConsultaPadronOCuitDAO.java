/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronesCuitOutEntity;

/**
 * The Interface ConsultaPadronCuitDAO.
 *
 * @author alejandro_leal
 */
public interface ConsultaPadronOCuitDAO {

	/**
	 * Consulta padron.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta padron cuit out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
    ConsultaPadronesCuitOutEntity consultaPadrones(ConsultaPadronCuitInEntity entity) throws DAOException;


	
   /**
     * Consulta padron retornando lista coincidencias.
     *
     * @param entity
     *            the entity
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
	List<ConsultaPadronCuitOutEntity> consultaPadronRetornandoListaCoincidencias(
            ConsultaPadronCuitInEntity entity) throws DAOException;
	
	ConsultaPadronCuitOutEntity consultaPadron(ConsultaPadronCuitInEntity entity) throws DAOException;

}
