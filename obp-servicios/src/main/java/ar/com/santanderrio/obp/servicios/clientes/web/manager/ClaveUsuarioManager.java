/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.manager;

import javax.servlet.http.HttpServletRequest;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.ComprobanteClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface ClaveUsuarioManager.
 */
public interface ClaveUsuarioManager {

	/**
	 * Cambio usuario clave.
	 *
	 * @param request
	 *            the request
	 * @param cambioClaveUsuarioView
	 *            the cambio clave usuario view
	 * @return the respuesta
	 */
	Respuesta<CambioClaveUsuarioView> cambioUsuarioClave(HttpServletRequest request, CambioClaveUsuarioView cambioClaveUsuarioView);

	/**
	 * Gets the datos contacto.
	 *
	 * @return the datos contacto
	 */
	Respuesta<ComprobanteClaveUsuarioView> getComprobanteClaveUsuario();

	/**
	 * Descargar comprobante.
	 *
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobante(String tipoComprobante);
}
