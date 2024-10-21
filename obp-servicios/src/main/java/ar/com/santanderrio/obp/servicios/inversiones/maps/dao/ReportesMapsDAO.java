package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;

public interface ReportesMapsDAO {
    
    
    Reporte descargaComprobanteAltaAdhesion(FormularioControl formulario) throws DAOException;

	Reporte descargaComprobanteBajaAdhesion(FormularioControl formulario) throws DAOException;

}


// Análisis del archivo ReportesMapsDAO.java

// El archivo ReportesMapsDAO.java define una interfaz para un DAO (Data Access Object) que se encarga de interactuar con la capa de datos para realizar operaciones relacionadas con la generación de reportes en el contexto de la adhesión y baja de adhesión.

// Observaciones

// La interfaz define dos métodos:
// descargaComprobanteAltaAdhesion: devuelve un reporte de comprobante de alta de adhesión.
// descargaComprobanteBajaAdhesion: devuelve un reporte de comprobante de baja de adhesión.
// Ambos métodos reciben un objeto FormularioControl como parámetro y lanzan una excepción DAOException en caso de error.
// La interfaz no define la implementación de los métodos, solo su firma.
// Preguntas para avanzar

// ¿Cuál es la implementación actual de esta interfaz en tu proyecto Java?
// ¿Qué tipo de reportes se generan en la implementación de ReportesMapsDAO?
// ¿Cómo se manejan las excepciones en la implementación de ReportesMapsDAO?