/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.TransferenciaFondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteTransferenciaFondo;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TransferenciaView;

/**
 * The Class TransferenciaFondoSEIImpl.
 *
 * @author
 */
@Component
public class TransferenciaFondoSEIImpl implements TransferenciaFondoSEI {

	/** The transferencia manager. */
	@Autowired
	private TransferenciaFondoManager transferenciaManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> iniciarTransferencia(CuentasConsultaFondoView viewRequest) {
		return transferenciaManager.iniciarTransferencia(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> iniciarTransferenciaBpriv(CuentasConsultaFondoView viewRequest) {
		return transferenciaManager.iniciarTransferenciaBpriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigTransferenciaView> obtenerDatosConfig(ConfigTransferenciaInView configTransferenciaInView) {
		return transferenciaManager.obtenerDatosConfig(configTransferenciaInView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<TransferenciaView> finalizarTransferenciaFondos(TransferenciaView viewRequest) {
		return transferenciaManager.finalizarTransferenciaFondos(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimulacionTransferenciaView> simularTransferencia(
			SimulacionTransferenciaInView simulacionTransferenciaInView) {
		return transferenciaManager.simularTransferencia(simulacionTransferenciaInView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimulacionTransferenciaView> simularTransferenciaBpriv(SimulacionInView viewRequest) {
		return transferenciaManager.simularTransferenciaBpriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void grabarEstadisticaGastosBPriv(ConfigTransferenciaInView requestView) {
		transferenciaManager.grabarEstadisticaGastosBPriv(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest) {
		return transferenciaManager.verComprobante(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest) {
		return transferenciaManager.verComprobanteBPriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FinalizarTransferenciaFondoView> finalizarTransferenciaFondosBpriv(
			FinalizarTransferenciaFondoInView viewRequest) {
		return transferenciaManager.finalizarTransferenciaFondosBpriv(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.
	 * TransferenciaFondoSEI#descargarComprobanteTransferenciaPDF(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.view.
	 * ComprobanteTransferenciaFondo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteTransferenciaPDF(ComprobanteTransferenciaFondo viewRequest) {
		return transferenciaManager.descargarComprobanteTransferenciaPDF(viewRequest);
	}
}
