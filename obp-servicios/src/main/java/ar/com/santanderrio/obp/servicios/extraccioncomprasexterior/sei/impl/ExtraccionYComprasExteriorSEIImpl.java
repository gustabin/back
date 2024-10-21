/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.ExtraccionYComprasExteriorManager;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.ExtraccionYComprasExteriorSEI;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.CuentaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.DatosTarjetasExtraccionYComprasExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.TarjetaOperacionExteriorView;

/**
 * ExtraccionYComprasExteriorManagerSEIImpl.
 *
 * @author Silvina_Luque
 */
@Component("extraccionYComprasExteriorSEI")
public class ExtraccionYComprasExteriorSEIImpl implements ExtraccionYComprasExteriorSEI {

	/** The extraccion Y compras exterior manager. */
	@Autowired
	private ExtraccionYComprasExteriorManager extraccionYComprasExteriorManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.
	 * ExtraccionYComprasExteriorSEI#consultarTarjetasOperacionExterior()
	 */
	@Override
	public Respuesta<DatosTarjetasExtraccionYComprasExteriorView> consultarTarjetasOperacionExterior() {
		return extraccionYComprasExteriorManager.consultarTarjetasOperacionExterior();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.
	 * ExtraccionYComprasExteriorSEI#consultarCuentasOperacionExterior(ar.com.
	 * santanderrio.obp.servicios.extraccioncomprasexterior.web.view.
	 * TarjetaOperacionExteriorView)
	 */
	@Override
	public Respuesta<List<CuentaOperacionExteriorView>> consultarCuentasOperacionExterior(
			TarjetaOperacionExteriorView tarjetaOperaExteriorView) {
		return extraccionYComprasExteriorManager.consultarCuentasOperacionExterior(tarjetaOperaExteriorView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.
	 * ExtraccionYComprasExteriorSEI#modificarTarjetaOperacionExterior(ar.com.
	 * santanderrio.obp.servicios.extraccioncomprasexterior.web.view.
	 * ModifTarjetaOperaExtraccionView)
	 */
	@Override
	public Respuesta<ModifTarjetaOperaExtraccionView> modificarTarjetaOperacionExterior(
			ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView) {
		return extraccionYComprasExteriorManager.modificarTarjetaOperacionExterior(modifTarjetaOperaExteriorView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.
	 * ExtraccionYComprasExteriorSEI#descargarComprobante()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobante() {
		return extraccionYComprasExteriorManager.descargarComprobante();
	}

}
