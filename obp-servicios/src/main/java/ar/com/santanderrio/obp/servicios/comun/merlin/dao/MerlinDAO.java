/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.merlin.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;

/**
 * The Interface MerlinDAO.
 */
public interface MerlinDAO {

    /**
     * Gets the resultado merlin.
     *
     * @param datosDeDomicilio
     *            the datos de domicilio
     * @return the resultado merlin
     * @throws DAOException
     *             the DAO exception
     * @throws MerlinError1Exception
     *             the merlin error 1 exception
     */
    ResultadoMerlinEntity getResultadoMerlin(DatosMerlinInEntity datosDeDomicilio)
            throws DAOException, MerlinError1Exception;

    /**
	 * Busqueda merlin.
	 *
	 * @param datosDeDomicilio
	 *            the datos de domicilio
	 * @return the resultado merlin entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws MerlinError1Exception
	 *             the merlin error 1 exception
	 */
    ResultadoMerlinEntity busquedaMerlin(DatosMerlinInEntity datosDeDomicilio)
            throws DAOException, MerlinError1Exception;

}
