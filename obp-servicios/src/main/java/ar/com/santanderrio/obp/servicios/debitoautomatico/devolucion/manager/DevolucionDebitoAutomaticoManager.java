package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoOutView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAInView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAOutView;

/**
 * The Interface DevolucionDebitoAutomaticoManager.
 */
public interface DevolucionDebitoAutomaticoManager {

    /**
     * Solicitar devolucion debito automatico.
     *
     * @param devolucionView the devolucion view
     * @return the respuesta
     */
    Respuesta<DevolucionDebitoAutomaticoOutView> solicitarDevolucionDebitoAutomatico(DevolucionDebitoAutomaticoView devolucionView);

	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	Respuesta<SolicitarDevolucionDAOutView> ejecutarSolicitudDevolucionDA(SolicitarDevolucionDAInView devolucionView);

	/**
	 * Generar comprobante PDF.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobantePDF(DevolucionDebitoAutomaticoView devolucionView);
	
	/**
	 * Grabar estadistica ver comprobante.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaVerComprobante();
}
