/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.CrearUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.IngresoBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.RecuperoClaveBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface AdhesionBilleteraManager.
 */
public interface AdhesionBilleteraManager {

	/**
	 * Confirmacion de alta de usuario de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<BilleteraView> confirmarAltaUsuario(BilleteraView viewRequest);

	/**
	 * Creacion de usuario de Billetera.
	 *
	 * @return the respuesta
	 */
	Respuesta<CrearUsuarioBilleteraView> crearUsuario();

	/**
	 * Descarga de comprobante de adhesion a Billetera.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargaComprobanteAdhesion();

	/**
	 * Inicia proceso de confirmacion de alta de usuario de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<PreConfirmarBilleteraView> preConfirmarAltaUsuario(PreConfirmarBilleteraView viewRequest);

	/**
	 * Primer ingreso a Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	Respuesta<ValidarUsuarioBilleteraView> primerIngreso(IngresoBilleteraInView viewRequest);

	/**
	 * Recupero de clave Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	Respuesta<RecuperoClaveBilleteraView> recuperoClaveBilletera(RecuperoClaveBilleteraView viewRequest);

}
