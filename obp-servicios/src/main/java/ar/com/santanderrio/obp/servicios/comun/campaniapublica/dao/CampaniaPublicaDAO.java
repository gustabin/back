package ar.com.santanderrio.obp.servicios.comun.campaniapublica.dao;

import java.util.Map;

import ar.com.santanderrio.obp.base.dao.DAOException;

public interface CampaniaPublicaDAO {

	Map<String, String> obtenerGoToCampaniasPublicas() throws DAOException;
	
	void limpiarCampaniasPublicas();
	
}
