/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteFinanciacionResumen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TarjetaFinanciacionResumen;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class FinanciacionResumenVISAAMEXBuilder.
 */
public class FinanciacionResumenVISAAMEXBuilder extends AltaComprobanteRequestBuilder {

	/**
	 * Instantiates a new financiacion resumen VISAAMEX builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public FinanciacionResumenVISAAMEXBuilder(Cliente cliente) {
		super(cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.entities.
	 * AltaComprobanteRequestBuilder#buildComprobanteRequest()
	 */
	@Override
	public AltaComprobanteRequest buildComprobanteRequest() {
		AltaComprobanteRequest request = obtenerAltaBase("2", "19");
		request.setComprobante(obtenerComprobanteFinanciacion());
		return request;
	}

	/**
	 * Obtener comprobante financiacion.
	 *
	 * @return the comprobante
	 */
	private Comprobante obtenerComprobanteFinanciacion() {
		ComprobanteFinanciacionResumen inEntity = new ComprobanteFinanciacionResumen();
		rellenarComprobanteBase(inEntity, "2", "19");
		inEntity.setFechaOper("");
		inEntity.setHoraOper("");
		inEntity.setTarjeta(obtenerTarjeta());
		return inEntity;
	}

	/**
	 * Obtener tarjeta.
	 *
	 * @return the tarjeta financiacion resumen
	 */
	private TarjetaFinanciacionResumen obtenerTarjeta() {
		TarjetaFinanciacionResumen resumen = new TarjetaFinanciacionResumen();
		resumen.setTipoTarjeta("");
		resumen.setNumTarjeta("");
		resumen.setImporteFinanciado("");
		resumen.setImporteCuota("");
		resumen.setCantCuotas("");
		resumen.setTasaNominalAnual("");
		resumen.setCostoFinancieroTotal("");
		resumen.setNumComprobante("");
		return resumen;
	}

}
