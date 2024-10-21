package ar.com.santanderrio.obp.servicios.perfil.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;

public interface ActualizarNombreDAO {

	String actualizarNombreCliente(String nombre, Long nup) throws DAOException;
	
}
