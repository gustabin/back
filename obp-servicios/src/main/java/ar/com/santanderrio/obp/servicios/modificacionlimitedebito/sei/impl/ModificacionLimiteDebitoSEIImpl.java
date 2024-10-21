/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei.impl;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei.ModificacionLimiteDebitoSEI;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.ModifLimiteDebitoManager;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.DatosModificacionLimiteDebitoView;

/**
 * The Class ModificacionLimiteDebitoSEIImpl.
 */
@Component
public class ModificacionLimiteDebitoSEIImpl implements ModificacionLimiteDebitoSEI {

	/** The manager. */
	@Autowired
	private ModifLimiteDebitoManager modificacionLimiteDebitoManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ModificacionLimiteDebitoSEIImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei.
	 * ModificacionLimiteDebitoSEI#obtenerDatosModificacionLimiteDebito()
	 */
	@Override
	public Respuesta<DatosModificacionLimiteDebitoView> obtenerDatosModificacionLimiteDebito() {
		LOGGER.info("Post OK: /obtenerDatosModificacionLimiteDebito.");
		return modificacionLimiteDebitoManager.obtenerDatosModifLimiteDebito();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei.
	 * ModificacionLimiteDebitoSEI#modificarLimitesExtraccion(ar.com.
	 * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteModificacionLimiteDebitoView, org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<ComprobanteModificacionLimiteDebitoView> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, MessageContext mc) {
		LOGGER.info("Post OK: /modificarLimitesExtraccion.");
		return modificacionLimiteDebitoManager.modificarLimitesExtraccion(comprobanteModificacionLimiteDebitoView, mc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei.
	 * ModificacionLimiteDebitoSEI#comprobanteModifLimitesExtraccion(ar.com.
	 * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteDescargaCambioLimiteView)
	 */
	@Override
	public Respuesta<ReporteView> comprobanteModifLimitesExtraccion(
			ComprobanteDescargaCambioLimiteView comprobanteView) {
		LOGGER.info("Post OK: /comprobanteModifLimitesExtraccion.");
		return modificacionLimiteDebitoManager.comprobanteModifLimitesExtraccion(comprobanteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei.
	 * ModificacionLimiteDebitoSEI#vaciarDesafio()
	 */
	@Override
	public Respuesta<Void> vaciarDesafio() {
		return modificacionLimiteDebitoManager.vaciarDesafio();
	}
}
