/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.OperadorEjecutivoView;
import ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager;
import ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoRemotoView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesOutView;

/**
 * The Class TurnosWebSEIImpl.
 *
 * @author IT Resources
 */
@Component("turnosWebSEI")
public class TurnosWebSEIImpl implements TurnosWebSEI {
	
	
	/** The turnos web manager. */
	@Autowired
	private TurnosWebManager turnosWebManager;	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI#consultaCita(CitaInView)
	 */
	@Override
	public Respuesta<CitaOutView> consultaCita(CitaInView citaInview) {		
		return turnosWebManager.consultaCita(citaInview.getPrimerLlamado());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI#consultaHorarioDisponibles(ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView)
	 */
	@Override
	public Respuesta<HorariosDisponiblesOutView> consultaHorarioDisponibles(HorariosDisponiblesInView horariosDisponiblesInView){
		return turnosWebManager.consultaHorariosDisponibles(horariosDisponiblesInView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI#consultaSucursales()
	 */
	@Override
	public Respuesta<SucursalesOutView> consultaSucursales() {
		return turnosWebManager.consultaSucursales();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI#bajaTurno(ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView)
	 */
	@Override
	public Respuesta<Void> bajaTurno(BajaTurnoInView bajaTurnoInView) {
		return turnosWebManager.bajaTurno(bajaTurnoInView);
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI#descargarComprobantePDF(ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(ComprobanteTurnoInView comprobanteTurnoInView) {
		return turnosWebManager.descargaComprobanteTurnoPDF(comprobanteTurnoInView);
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteRemotoPDF(ComprobanteTurnoRemotoView comprobanteTurnoRemotoView) {
		return turnosWebManager.descargaComprobanteTurnoRemotoPDF(comprobanteTurnoRemotoView);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEI#altaModificacionCita(ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView)
	 */
	@Override
	public Respuesta<AltaModificacionCitaOutView> altaModificacionCita(
			AltaModificacionCitaInView altaModificacionCitaInView) {
		return turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
	} 
	/**
     * Retorna el Operador Ejecutivo 
     * obtenido desde la sesión del cliente.     
     */
	@Override
	public Respuesta<OperadorEjecutivoView> obtenerOperadorEjecutivoSelectOnline() {				
		return turnosWebManager.obtenerOperadorEjecutivoSelectOnline();
	}	
				
	/**
	 * Consulta consultaSantanderExpress
	 * Autogestion
	 */
	@Override
	public Respuesta<String> consultaSantanderExpress() {						
		return turnosWebManager.consultaSantanderExpress();
	}		
}
