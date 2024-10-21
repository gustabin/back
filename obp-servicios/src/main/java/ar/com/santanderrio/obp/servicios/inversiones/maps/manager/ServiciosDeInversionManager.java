package ar.com.santanderrio.obp.servicios.inversiones.maps.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetallePerfilInversorView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.InicioServiciosDeInversionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.ObtenerDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.VerDetallePerfilInversorView;

public interface ServiciosDeInversionManager {

	Respuesta<InicioServiciosDeInversionView> inicioServiciosDeInversion();

	Respuesta<ObtenerDisponiblesOutView> obtenerControlesDisponibles(BancaInversionesView inView);

	Respuesta<FormularioControl> altaServicio(FormulariosAltaInicioInView inView);
	
	Respuesta<FormularioControl> altaServicioFlujo(FormularioControl formularioControl);

	Respuesta<DetallePerfilInversorView> verDetallePerfil(VerDetallePerfilInversorView inView);

    Respuesta<Void> notificarGotoTenenciaConsolidada(BancaInversionesView inView);
    
    Respuesta<GrillaConsultaAdhesionDTO> consultaAdhesion(BancaInversionesView inView);

	Respuesta<Void> accesoComprobanteAltaAdhesion(BancaInversionesView inView);

    Respuesta<ReporteView> descargaComprobanteAltaAdhesion(FormularioControl formularioControl);
    
    Respuesta<FormularioControl> obtenerDetalleSuscripcion(DetalleSuscripcionView detalleSuscripcionView);
    
    Respuesta<FormularioControl> bajaAdhesion(BajaAdhesionView bajaAdhesionView);
    
    Respuesta<FormularioControl> bajaAdhesion(FormularioControl inView);
    
    Respuesta<Void> grabarEstadisticaComprobanteBaja(BancaInversionesView inView);
    
    Respuesta<ReporteView> descargaComprobanteBajaAdhesion(FormularioControl formularioControl);

}
