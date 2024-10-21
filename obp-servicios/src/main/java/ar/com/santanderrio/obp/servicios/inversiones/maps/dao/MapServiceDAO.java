package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaFeriadosRequestEntity;

public interface MapServiceDAO {

	AltaAdhesionMapsResponse altaAdhesionMaps(AltaAdhesionMapsRequestEntity request) throws DAOException;
	
	ConsultaAdhesionMapsResponse consultaAdhesionMaps(ConsultaAdhesionRequestEntity request) throws DAOException;
	
	ConsultaAdhesionMapsResponse bajaAdhesionMaps(BajaAdhesionRequestEntity request) throws DAOException;

	List<String> consultaFeriados(ConsultaFeriadosRequestEntity request) throws DAOException;
	
}

// Análisis del archivo MapServiceDAO.java

// El archivo MapServiceDAO.java define una interfaz para un DAO (Data Access Object) que se encarga de interactuar con la capa de datos para realizar operaciones relacionadas con la adhesión y consulta de feriados.

// Observaciones

// La interfaz define cuatro métodos:
// altaAdhesionMaps: crea una nueva adhesión y devuelve una respuesta.
// consultaAdhesionMaps: consulta la adhesión y devuelve una respuesta.
// bajaAdhesionMaps: elimina una adhesión y devuelve una respuesta.
// consultaFeriados: consulta los feriados y devuelve una lista de resultados.
// Cada método lanza una excepción DAOException en caso de error.
// La interfaz no define la implementación de los métodos, solo su firma.
// Preguntas para avanzar

// ¿Cuál es la implementación actual de esta interfaz en tu proyecto Java?
// ¿Qué tipo de datos se almacenan en la capa de datos que interactúa con esta interfaz?
// ¿Qué tecnología de base de datos se utiliza en tu proyecto actual?