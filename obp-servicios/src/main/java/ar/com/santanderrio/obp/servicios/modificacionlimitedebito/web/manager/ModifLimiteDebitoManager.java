/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager;


import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.DatosModificacionLimiteDebitoView;

/**
 * The Interface ModifLimiteDebitoManager.
 */
public interface ModifLimiteDebitoManager {

	/**
	 * Obtener datos modif limite debito.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosModificacionLimiteDebitoView> obtenerDatosModifLimiteDebito();

	/**
	 * Modificar limites extraccion.
	 *
	 * @param comprobanteModificacionLimiteDebitoView 
	 * 		the comprobante modificacion limite debito view
	 * @param mc
	 * 		the mc
	 * @return the respuesta
	 */
	Respuesta<ComprobanteModificacionLimiteDebitoView> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, MessageContext mc);

	/**
	 * Comprobante modif limites extraccion.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> comprobanteModifLimitesExtraccion(ComprobanteDescargaCambioLimiteView comprobanteView);

	/**
	 * Vaciar desafio.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> vaciarDesafio();
}
