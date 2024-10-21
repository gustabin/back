package ar.com.santanderrio.obp.servicios.inversiones.maps.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.InicioServiciosDeInversionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;

public interface ServiciosDeInversionBO {

	Respuesta<InicioServiciosDeInversionDTO> inicioServiciosDeInversion(Cliente cliente);

    Respuesta<FormularioControl> altaServicio(String nup, FormulariosAltaInicioInView inView);
        
    Respuesta<FormularioControl> obtenerControlesDisponibles(Cliente cliente, String banca);

	Respuesta<FormularioControl> altaServicioFlujo(String nup, FormularioControl inView);
	
	Respuesta<GrillaConsultaAdhesionDTO> consultaAdhesion(Cliente cliente, String banca);

    Respuesta<Reporte> descargaComprobanteAltaAdhesion(FormularioControl formularioControl);
    
    Respuesta<FormularioControl> obtenerDetalleSuscripcion(Cliente cliente, DetalleSuscripcionView detalleSuscripcionView);
    
    void vaciarCacheSuscripcionesMaps();
    
    Respuesta<FormularioControl> bajaAdhesion(Cliente cliente, BajaAdhesionView bajaAdhesionView);
    
    Respuesta<FormularioControl> bajaAdhesion(FormularioControl formularioControl, Cliente cliente);

	Respuesta<Reporte> descargaComprobanteBajaAdhesion(FormularioControl formularioControl);
    
}
