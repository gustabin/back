package ar.com.santanderrio.obp.servicios.inversiones.maps.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.manager.ServiciosDeInversionManager;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetallePerfilInversorView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.InicioServiciosDeInversionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.ObtenerDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.VerDetallePerfilInversorView;

@Component("serviciosDeInversionSEI")
public class ServiciosDeInversionSEIImpl implements ServiciosDeInversionSEI {

	@Autowired
	private ServiciosDeInversionManager serviciosDeInversionManager;

	@Override
	public Respuesta<InicioServiciosDeInversionView> inicioServiciosDeInversion() {
		return serviciosDeInversionManager.inicioServiciosDeInversion();
	}
	
	@Override
	public Respuesta<ObtenerDisponiblesOutView> obtenerDisponibles(BancaInversionesView inView) {
		return serviciosDeInversionManager.obtenerControlesDisponibles(inView);
	}

	@Override
	public Respuesta<FormularioControl> altaServicio(FormulariosAltaInicioInView inView) {
		return serviciosDeInversionManager.altaServicio(inView);
	}

	@Override
	public Respuesta<FormularioControl> altaServicioFlujo(ControlMaps inView) {
		return serviciosDeInversionManager.altaServicioFlujo((FormularioControl)inView);
	}

	@Override
	public Respuesta<DetallePerfilInversorView> verDetallePerfil(VerDetallePerfilInversorView inView) {
		return serviciosDeInversionManager.verDetallePerfil(inView);
	}

	@Override
	public Respuesta<Void> notificarGotoTenenciaConsolidada(BancaInversionesView inView) {
	    return serviciosDeInversionManager.notificarGotoTenenciaConsolidada(inView);
	}

	@Override
	public Respuesta<GrillaConsultaAdhesionDTO> consultaAdhesion(BancaInversionesView inView) {
		return serviciosDeInversionManager.consultaAdhesion(inView);
	}

	@Override
	public Respuesta<Void> accesoComprobanteAltaAdhesion(BancaInversionesView inView) {
	    return serviciosDeInversionManager.accesoComprobanteAltaAdhesion(inView);
	}

    @Override
    public Respuesta<ReporteView> descargaComprobanteAltaAdhesion(FormularioControl formularioControl) {
        return serviciosDeInversionManager.descargaComprobanteAltaAdhesion(formularioControl);
    }

	@Override
	public Respuesta<FormularioControl> obtenerDetalleSuscripcion(DetalleSuscripcionView detalleSuscripcionView) {
		return serviciosDeInversionManager.obtenerDetalleSuscripcion(detalleSuscripcionView);
	}

	@Override
	public Respuesta<FormularioControl> bajaAdhesion(BajaAdhesionView bajaAdhesionView) {
		return serviciosDeInversionManager.bajaAdhesion(bajaAdhesionView);
	}

	@Override
	public Respuesta<FormularioControl> bajaAdhesion(ControlMaps inView) {
		return serviciosDeInversionManager.bajaAdhesion((FormularioControl) inView);
	}

	@Override
	public Respuesta<Void> grabarEstadisticaComprobanteBaja(BancaInversionesView inView) {
		return serviciosDeInversionManager.grabarEstadisticaComprobanteBaja(inView);
	}

	@Override
	public Respuesta<ReporteView> descargaComprobanteBajaAdhesion(FormularioControl formularioControl) {
		return serviciosDeInversionManager.descargaComprobanteBajaAdhesion(formularioControl);
	}
    
    
}
