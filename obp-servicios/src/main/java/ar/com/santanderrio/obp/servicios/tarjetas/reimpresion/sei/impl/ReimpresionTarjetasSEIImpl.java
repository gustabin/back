/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.ReimpresionTarjetasManager;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei.ReimpresionTarjetasSEI;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.SolicitudReimpresionTarjetasView;

/**
 * The Class ReimpresionTarjetasSEIImpl.
 */
@Component
public class ReimpresionTarjetasSEIImpl implements ReimpresionTarjetasSEI {

	/** The reimpresion tarjetas manager. */
	@Autowired
	private ReimpresionTarjetasManager reimpresionTarjetasManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei.
	 * ReimpresionTarjetasSEI#getDatosIniciales()
	 */
	@Override
	public Respuesta<SolicitudReimpresionTarjetasView> getDatosIniciales() {
		return reimpresionTarjetasManager.getDatosIniciales();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei.
	 * ReimpresionTarjetasSEI#getDomiciliosDisponibles()
	 */
	@Override
	public Respuesta<DomiciliosDisponiblesView> getDomiciliosDisponibles() {
		return reimpresionTarjetasManager.getDomiciliosDisponibles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei.
	 * ReimpresionTarjetasSEI#altaReimpresionTarjetas(ar.com.santanderrio.obp.
	 * servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetasView)
	 */
	@Override
	public Respuesta<AltaDatosReimpresionTarjetasView> altaReimpresionTarjetas(
			AltaDatosReimpresionTarjetasView altaDatosReimpresionTarjetas) {
		return reimpresionTarjetasManager.altaReimpresionTarjetas(altaDatosReimpresionTarjetas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei.
	 * ReimpresionTarjetasSEI#getComprobante()
	 */
	@Override
	public Respuesta<ReporteView> getComprobante() {
		return reimpresionTarjetasManager.descargarComprobante();
	}

}
