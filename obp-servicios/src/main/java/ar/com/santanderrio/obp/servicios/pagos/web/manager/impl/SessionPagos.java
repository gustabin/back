/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Class SessionPagos.
 *
 * @author B039637
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionPagos {

	/** The listaPagosPendientes. */
	private List<Respuesta<List<PagoPendiente>>> listaPagosPendientes;

	/**
	 * Gets the lista pagos pendientes.
	 *
	 * @return the lista pagos pendientes
	 */
	public List<Respuesta<List<PagoPendiente>>> getListaPagosPendientes() {
		return listaPagosPendientes;
	}

	/**
	 * Sets the lista pagos pendientes.
	 *
	 * @param listaPagosPendientes
	 *            the new lista pagos pendientes
	 */
	public void setListaPagosPendientes(List<Respuesta<List<PagoPendiente>>> listaPagosPendientes) {
		this.listaPagosPendientes = listaPagosPendientes;
	}

}
