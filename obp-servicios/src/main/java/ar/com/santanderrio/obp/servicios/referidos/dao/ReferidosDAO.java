package ar.com.santanderrio.obp.servicios.referidos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;

public interface ReferidosDAO {

	/**
	 * Invocamos al servicio de referidos para obtener el token de seguridad (handshake)
	 * Referidos es quien maneja la generacion de token 
	 * @param nup
	 * @return
	 * @throws DAOException
	 */
	public String obtenerTokenReferidos(String nup) throws DAOException;
	
	public List<String> obtenerNups(String archivo) throws DAOException;
	
}
