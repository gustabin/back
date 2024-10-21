/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.transferencias.entities.InsertarTransferenciaDTO;

/**
 * The Interface OperacionBancaPrivadaTransferenciaDAO.
 * 
 */
public interface OperacionBancaPrivadaTransferenciaDAO {
	
	/**
	 * insertar Transferencia RIO RIO OB.
	 *
	 * @param parameter the InsertarTransferenciaDTO
	 * @return the string
	 */
	String insertarTransferenciaRIORIOOB(InsertarTransferenciaDTO insertarTransferenciaDTO) throws DAOException;
	
	/**
	 * insertar Transferencia Entre Bancos OB.
	 *
	 * @param parameter the InsertarTransferenciaDTO
	 * @return the string
	 */
	String insertarTransferenciaEntreBancosOB(InsertarTransferenciaDTO insertarTransferenciaDTO) throws DAOException;

}
