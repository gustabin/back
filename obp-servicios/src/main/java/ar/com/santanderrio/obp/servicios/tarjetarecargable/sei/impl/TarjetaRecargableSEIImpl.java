/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.TarjetaRecargablelSEI;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.TarjetaRecargableManager;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosSolicitanteTarjetaRecargableView;

/**
 * The Class TarjetaRecargableSEIImpl.
 */
@Component
public class TarjetaRecargableSEIImpl implements TarjetaRecargablelSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaRecargableSEIImpl.class);

	/** The tarjeta recargable manager. */
	@Autowired
	private TarjetaRecargableManager tarjetaRecargableManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.
	 * TarjetaRecargablelSEI#obtenerDatosIniciales()
	 */
	@Override
	public Respuesta<DatosSolicitanteTarjetaRecargableView> obtenerDatosIniciales() {
		LOGGER.info("Post OK: /obtenerDatosIniciales.");
		return tarjetaRecargableManager.obtenerDatosIniciales();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.
	 * TarjetaRecargablelSEI#altaSolicitudTarjetaRecargable(ar.com.santanderrio.
	 * obp.servicios.tarjetarecargable.web.view.
	 * ComprobanteAltaSolicitudTarjetaRecargableView)
	 */
	@Override
	public Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> altaSolicitudTarjetaRecargable(
			ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaSolicitudTarjetaRecargableView) {
		LOGGER.info("Post OK: /altaSolicitudTarjetaRecargable.");
		return tarjetaRecargableManager.altaSolicitudTarjetaRecargable(comprobanteAltaSolicitudTarjetaRecargableView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.
	 * TarjetaRecargablelSEI#comprobanteSolicitudTarjetaRecargable()
	 */
	@Override
	public Respuesta<ReporteView> comprobanteSolicitudTarjetaRecargable() {
		LOGGER.info("Post OK: /comprobanteSolicitudTarjetaRecargable.");
		return tarjetaRecargableManager.comprobanteSolicitudTarjetaRecargable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.
	 * TarjetaRecargablelSEI#vaciarDesafio()
	 */
	@Override
	public Respuesta<Void> vaciarDesafio() {
		return tarjetaRecargableManager.vaciarDesafio();
	}
}
