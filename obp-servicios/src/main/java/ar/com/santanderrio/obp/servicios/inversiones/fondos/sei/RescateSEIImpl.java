/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ComprobanteRescateFondo;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateCitiInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateOutView;

/**
 * The Class RescateSEIImpl.
 *
 * @author b039920
 */
@Component("rescateSEI")
public class RescateSEIImpl implements RescateSEI {

	/** The rescate manager. */
	@Autowired
	private RescateManager rescateManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * configuracionRescate(ar.com.santanderrio.obp.servicios.inversiones.fondos
	 * .view.ConfiguracionRescateInView)
	 */
	@Override
	public Respuesta<ConfiguracionRescateOutView> configuracionRescate(ConfiguracionRescateInView viewRequest) {
		return rescateManager.configuracionRescate(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * finalizarRescate(ar.com.santanderrio.obp.servicios.inversiones.fondos.
	 * view.FinalizarRescateInView)
	 */
	@Override
	public Respuesta<FinalizarRescateView> finalizarRescate(FinalizarRescateInView viewRequest) {
		return rescateManager.finalizarRescate(viewRequest);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#finalizarRescateCiti(ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateCitiInView)
	 */
	@Override
	public Respuesta<FinalizarRescateView> finalizarRescateCiti(FinalizarRescateCitiInView viewRequest) {
		return rescateManager.finalizarRescateCiti(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * simularRescateFondo(ar.com.santanderrio.obp.servicios.inversiones.fondos.
	 * view.SimulacionRescateInView)
	 */
	@Override
	public Respuesta<SimulacionRescateOutView> simularRescateFondo(SimulacionRescateInView viewRequest) {
		return rescateManager.simularRescateFondo(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * simularRescateFondoBPriv(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.SimulacionRescateInView)
	 */
	@Override
	public Respuesta<SimulacionRescateOutView> simularRescateFondoBPriv(SimulacionRescateInView viewRequest) {
		return rescateManager.simularRescateFondoBPriv(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * obtenerFondosSuscriptos(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.RescateInView)
	 */
	@Override
	public Respuesta<RescateView> obtenerFondosSuscriptos(RescateInView viewRequest) {
		// throw new RuntimeException();
		return rescateManager.obtenerFondosSuscriptos(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * configuracionRescateBPriv(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.ConfiguracionRescateBPrivInView)
	 */
	@Override
	public Respuesta<ConfiguracionRescateOutView> configuracionRescateBPriv(
			ConfiguracionRescateBPrivInView viewRequest) {
		return rescateManager.configuracionRescateBPriv(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * obtenerFondosSuscriptosBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.RescateInView)
	 */
	@Override
	public Respuesta<RescateView> obtenerFondosSuscriptosBPriv(RescateInView viewRequest) {
		// throw new RuntimeException();
		return rescateManager.obtenerFondosSuscriptosBPriv(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * finalizarRescateBPriv(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.FinalizarRescateBPrivInView)
	 */
	@Override
	public Respuesta<FinalizarRescateView> finalizarRescateBPriv(FinalizarRescateBPrivInView viewRequest) {
		return rescateManager.finalizarRescateBPriv(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * verComprobante(ar.com.santanderrio.obp.servicios.inversiones.comun.view.
	 * DatosComprobante)
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteRescate(DatosComprobante viewRequest) {
		return rescateManager.verComprobante(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * verComprobanteBPriv(ar.com.santanderrio.obp.servicios.inversiones.comun.
	 * view.DatosComprobante)
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteRescateBPriv(DatosComprobante viewRequest) {
		return rescateManager.verComprobanteBPriv(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * obtenerRescateDesdeGrilla(ar.com.santanderrio.obp.servicios.inversiones.
	 * comun.view.RescateDesdeGrillaInView)
	 */
	@Override
	public Respuesta<RescateDesdeGrilla> obtenerRescateDesdeGrilla(RescateDesdeGrillaInView viewRequest) {
		return rescateManager.obtenerRescateDesdeGrilla(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#
	 * descargarComprobanteRescatePDF(ar.com.santanderrio.obp.servicios.
	 * inversiones.comun.view.ComprobanteRescateFondo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteRescatePDF(ComprobanteRescateFondo viewRequest) {
		return rescateManager.descargarComprobanteRescatePDF(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEI#continuarConfiguracionRescateExciti(ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateCitiInView)
	 */
	@Override
	public Respuesta<Void> continuarConfiguracionAConfirmacionRescateExciti(FinalizarRescateCitiInView viewRequest) {
		return rescateManager.continuarConfiguracionAConfirmacionRescateExciti(viewRequest);
	}
}
