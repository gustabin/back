/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineClaveRepeException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineClaveTrivialException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineUsuRepeException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineUsuTrivialException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueadoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;

/**
 * The Interface AltaSGIClaveDAO.
 */
public interface AltaSGIClaveDAO {

    /**
	 * Alta SGI clave.
	 *
	 * @param altaSGIClaveIn
	 *            the alta SGI clave in
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ClienteBloqueadoException
	 *             the cliente bloqueado exception
	 * @throws ClaveOnlineClaveTrivialException
	 *             the clave online clave trivial exception
	 * @throws ClaveOnlineUsuTrivialException
	 *             the clave online usu trivial exception
	 * @throws ClaveOnlineClaveRepeException
	 *             the clave online clave repe exception
	 * @throws ClaveOnlineUsuRepeException
	 *             the clave online usu repe exception
     * @throws ErrorRutinaFechasException 
     * @throws ErrorDb2Exception 
     * @throws ErrorCicsException 
     * @throws FuncionInvalidaException 
     * @throws ErrorModuloException 
	 */
    void altaSGIClave(AltaSGIClaveInEntity altaSGIClaveIn)
            throws DAOException, ClienteBloqueadoException, ClaveOnlineClaveTrivialException,
            ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException;

    /**
     * Alta SGI clave proxy login.
     *
     * @param altaSGIClaveIn the alta SGI clave in
     * @throws DAOException the DAO exception
     * @throws ClienteBloqueadoException the cliente bloqueado exception
     * @throws ClaveOnlineClaveTrivialException the clave online clave trivial exception
     * @throws ClaveOnlineUsuTrivialException the clave online usu trivial exception
     * @throws ClaveOnlineClaveRepeException the clave online clave repe exception
     * @throws ClaveOnlineUsuRepeException the clave online usu repe exception
     * @throws ErrorRutinaFechasException the error rutina fechas exception
     * @throws ErrorDb2Exception the error db 2 exception
     * @throws ErrorCicsException the error cics exception
     * @throws FuncionInvalidaException the funcion invalida exception
     * @throws ErrorModuloException the error modulo exception
     */
    void altaSGIClaveProxyLogin(AltaSGIClaveInEntity altaSGIClaveIn)
            throws DAOException, ClienteBloqueadoException, ClaveOnlineClaveTrivialException,
            ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException;

}
