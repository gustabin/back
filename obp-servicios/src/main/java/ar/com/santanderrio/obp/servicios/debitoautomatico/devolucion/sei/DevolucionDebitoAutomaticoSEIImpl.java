package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.manager.DevolucionDebitoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoOutView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAInView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAOutView;

/**
 * The Class DevolucionDebitoAutomaticoSEIImpl.
 */
@Component
public class DevolucionDebitoAutomaticoSEIImpl implements DevolucionDebitoAutomaticoSEI{

	/** The devolucion debito automatico manager. */
	@Autowired
	private DevolucionDebitoAutomaticoManager devolucionDebitoAutomaticoManager;
	
	
	/**
	 * Solicitar devolucion debito automatico.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DevolucionDebitoAutomaticoOutView> solicitarDevolucionDebitoAutomatico(DevolucionDebitoAutomaticoView devolucionView) {
		return devolucionDebitoAutomaticoManager.solicitarDevolucionDebitoAutomatico(devolucionView);
	}

	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SolicitarDevolucionDAOutView> ejecutarSolicitudDevolucionDA(SolicitarDevolucionDAInView devolucionView) {
		return devolucionDebitoAutomaticoManager.ejecutarSolicitudDevolucionDA(devolucionView);
	}
	
	/**
	 * Generar comprobante PDF.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> generarComprobantePDF(DevolucionDebitoAutomaticoView devolucionView) {
		return devolucionDebitoAutomaticoManager.generarComprobantePDF(devolucionView);
	}

	/**
	 * Grabar estadistica ver comprobante.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaVerComprobante() {
		return devolucionDebitoAutomaticoManager.grabarEstadisticaVerComprobante();
	}
}
