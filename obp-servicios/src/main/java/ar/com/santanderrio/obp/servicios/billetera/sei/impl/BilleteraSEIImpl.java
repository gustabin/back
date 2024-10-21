/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI;
import ar.com.santanderrio.obp.servicios.billetera.web.manager.AdhesionBilleteraManager;
import ar.com.santanderrio.obp.servicios.billetera.web.manager.BilleteraManager;
import ar.com.santanderrio.obp.servicios.billetera.web.manager.ConfiguracionBilleteraManager;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ComprobanteBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.CrearUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.IngresoBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.RecuperoClaveBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Class BilleteraSEIImpl.
 */
@Component("BilleteraSEI")
public class BilleteraSEIImpl implements BilleteraSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraSEIImpl.class);

	/** The Constant MENSAJE_RESPUESTA. */
	private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The adhesion billetera manager. */
	@Autowired
	@Qualifier("adhesionBilleteraManager")
	private AdhesionBilleteraManager adhesionBilleteraManager;

	/** The billetera manager. */
	@Autowired
	@Qualifier("billeteraManager")
	private BilleteraManager billeteraManager;

	/** The configuracion billetera manager. */
	@Autowired
	@Qualifier("configuracionBilleteraManager")
	private ConfiguracionBilleteraManager configuracionBilleteraManager;

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * confirmarAltaUsuario(ar.com.santanderrio.obp.servicios.billetera.web.view
	 * .AltaUsuarioBilleteraView)
	 */
	@Override
	public Respuesta<BilleteraView> confirmarAltaUsuario(BilleteraView viewRequest) {
		Respuesta<BilleteraView> respuesta = adhesionBilleteraManager.confirmarAltaUsuario(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * confirmarConfiguracion(ar.com.santanderrio.obp.servicios.billetera.web.
	 * view.BilleteraView)
	 */
	@Override
	public Respuesta<BilleteraView> confirmarConfiguracion(BilleteraView viewRequest) {
		Respuesta<BilleteraView> respuesta = configuracionBilleteraManager.confirmarConfiguracion(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#crearUsuario
	 * ()
	 */
	@Override
	public Respuesta<CrearUsuarioBilleteraView> crearUsuario() {
		Respuesta<CrearUsuarioBilleteraView> respuesta = adhesionBilleteraManager.crearUsuario();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * descargaComprobanteAdhesion(ar.com.santanderrio.obp.servicios.billetera.
	 * web.view.ComprobanteBilleteraView)
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteAdhesion(ComprobanteBilleteraView viewRequest) {
		Respuesta<ReporteView> respuesta = adhesionBilleteraManager.descargaComprobanteAdhesion();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * descargaComprobanteConfiguracion(ar.com.santanderrio.obp.servicios.
	 * billetera.web.view.ComprobanteBilleteraView)
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteConfiguracion(ComprobanteBilleteraView viewRequest) {
		Respuesta<ReporteView> respuesta = configuracionBilleteraManager.descargaComprobanteConfiguracion();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * preConfirmarAltaUsuario(ar.com.santanderrio.obp.servicios.billetera.web.
	 * view.ConfirmarAltaUsuarioBilleteraView)
	 */
	@Override
	public Respuesta<PreConfirmarBilleteraView> preConfirmarAltaUsuario(PreConfirmarBilleteraView viewRequest) {
		Respuesta<PreConfirmarBilleteraView> respuesta = adhesionBilleteraManager.preConfirmarAltaUsuario(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * preConfirmarConfiguracion(ar.com.santanderrio.obp.servicios.billetera.web
	 * .view.ConfirmarAltaUsuarioBilleteraView)
	 */
	@Override
	public Respuesta<PreConfirmarBilleteraView> preConfirmarConfiguracion(PreConfirmarBilleteraView viewRequest) {
		Respuesta<PreConfirmarBilleteraView> respuesta = configuracionBilleteraManager
				.preConfirmarConfiguracion(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * primerIngreso(ar.com.santanderrio.obp.servicios.billetera.web.view.
	 * IngresoBilleteraInView)
	 */
	@Override
	public Respuesta<ValidarUsuarioBilleteraView> primerIngreso(IngresoBilleteraInView viewRequest) {
		Respuesta<ValidarUsuarioBilleteraView> respuesta = adhesionBilleteraManager.primerIngreso(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * recuperoClaveBilletera(ar.com.santanderrio.obp.servicios.billetera.web.
	 * view.RecuperoClaveBilleteraView)
	 */
	@Override
	public Respuesta<RecuperoClaveBilleteraView> recuperoClaveBilletera(RecuperoClaveBilleteraView viewRequest) {
		Respuesta<RecuperoClaveBilleteraView> respuesta = adhesionBilleteraManager.recuperoClaveBilletera(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.sei.BilleteraSEI#
	 * validarUsuario(ar.com.santanderrio.obp.servicios.billetera.web.view.
	 * ValidarUsuarioBilleteraInView)
	 */
	@Override
	public Respuesta<ValidarUsuarioBilleteraView> validarUsuario(ValidarUsuarioBilleteraInView viewRequest) {
		Respuesta<ValidarUsuarioBilleteraView> respuesta = billeteraManager.validarUsuario(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

}
