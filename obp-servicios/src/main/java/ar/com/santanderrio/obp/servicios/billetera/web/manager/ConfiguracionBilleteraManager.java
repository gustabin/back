/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface ConfiguracionBilleteraManager.
 */
public interface ConfiguracionBilleteraManager {

	/**
	 * Confirmacion de configuracion de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<BilleteraView> confirmarConfiguracion(BilleteraView viewRequest);

	/**
	 * Descarga de comprobante de configuracion a Billetera.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargaComprobanteConfiguracion();

	/**
	 * Inicia proceso de configuracion de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<PreConfirmarBilleteraView> preConfirmarConfiguracion(PreConfirmarBilleteraView viewRequest);

}
