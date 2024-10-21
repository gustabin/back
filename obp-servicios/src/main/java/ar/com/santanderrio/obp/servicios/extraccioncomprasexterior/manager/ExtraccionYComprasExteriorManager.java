/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.CuentaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.DatosTarjetasExtraccionYComprasExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.TarjetaOperacionExteriorView;

/**
 * ExtraccionYComprasExteriorManager.
 *
 * @author Silvina_Luque
 */
public interface ExtraccionYComprasExteriorManager {

	/**
	 * Obtener cuentas relacionadas a una tarjeta de debito.
	 *
	 * @param tarjetaOperaExteriorView
	 *            the tarjeta opera exterior view
	 * @return the respuesta
	 */
	Respuesta<List<CuentaOperacionExteriorView>> consultarCuentasOperacionExterior(
			TarjetaOperacionExteriorView tarjetaOperaExteriorView);

	/**
	 * Obtener tarjetas de debito para operar en el exterior.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosTarjetasExtraccionYComprasExteriorView> consultarTarjetasOperacionExterior();

	/**
	 * Habilitar una tarjeta para operar en el exterior.
	 *
	 * @param modifTarjetaOperaExteriorView
	 *            the modif tarjeta opera exterior view
	 * @return the respuesta
	 */
	Respuesta<ModifTarjetaOperaExtraccionView> modificarTarjetaOperacionExterior(
			ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView);

	/**
	 * Descargar comprobante ExtraccionYComprasExterior.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobante();

}
