package ar.com.santanderrio.obp.servicios.turnosweb.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.OperadorEjecutivoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoRemotoView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesOutView;

/**
 * The Interface TurnosWebManager.
 *
 * @author IT Resources
 */
public interface TurnosWebManager {

	/**
	 * Consulta cita.
	 *
	 * @param primerLlamado the primer llamado
	 * @return the respuesta
	 */
	Respuesta<CitaOutView> consultaCita(Boolean primerLlamado);

	/**
	 * Obtiene las sucursales Select disponibles.
	 *
	 * @return the respuesta
	 */
	Respuesta<SucursalesOutView> consultaSucursales();

	/**
	 * Obtiene los horarios disponibles habilitados.
	 *
	 * @param horariosDisponiblesInView
	 *            the horarios disponibles in view
	 * @return the respuesta
	 */
	Respuesta<HorariosDisponiblesOutView> consultaHorariosDisponibles(HorariosDisponiblesInView horariosDisponiblesInView);

	/**
	 * Da de baja un turno.
	 *
	 * @param bajaTurnoInView
	 *            the baja turno in view
	 * @return the respuesta
	 */
	Respuesta<Void> bajaTurno(BajaTurnoInView bajaTurnoInView);

	/**
	 * Da de alta o modifica una cita.
	 *
	 * @param altaModificacionCitaInView
	 *            the alta modificacion cita in view
	 * @return the respuesta
	 */
	Respuesta<AltaModificacionCitaOutView> altaModificacionCita(AltaModificacionCitaInView altaModificacionCitaInView);

	/**
	 * Obtiene el comprobante PDF.
	 *
	 * @param comprobanteTurnoInView
	 *            the comprobante turno in view
	 * @return the respuesta
	 */
    Respuesta<ReporteView> descargaComprobanteTurnoPDF(ComprobanteTurnoInView comprobanteTurnoInView );
    
    /**
	 * Obtiene el comprobante Remoto PDF.
	 *
	 * @param comprobanteTurnoRemotoView
	 *            the comprobante turno remoto view
	 * @return the respuesta
	 */
    Respuesta<ReporteView> descargaComprobanteTurnoRemotoPDF(ComprobanteTurnoRemotoView comprobanteTurnoRemotoView );
	
    /**
     * Retorna el Operador Ejecutivo obtenido desde la sesión del cliente.
     * 
     * @param
     * 
     * @return the json object respuesta
     */
	Respuesta<OperadorEjecutivoView>obtenerOperadorEjecutivoSelectOnline();
	

     /**
	 * Obtiene el setting de la url de corresponsalia de consulta de santander express
     * 
     * @return the setting
     */
    Respuesta<String> consultaSantanderExpress();
}
