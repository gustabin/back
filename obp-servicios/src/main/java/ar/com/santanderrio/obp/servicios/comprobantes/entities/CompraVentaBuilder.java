/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteCompraVentaDolar;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.CuentaDestino;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.CuentaOrigen;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;

/**
 * The Class CompraVentaBuilder.
 */
public class CompraVentaBuilder extends AltaComprobanteRequestBuilder {

	/** The comprobante DTO. */
	private ComprobanteCompraVentaDTO comprobanteDTO;

	/** The parametros. */
	private ParametrosOperacion parametros;

	/**
	 * Instantiates a new compra venta builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public CompraVentaBuilder(Cliente cliente) {
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
		AltaComprobanteRequest request = obtenerAltaBase("2", "31");
		request.setComprobante(obtenerComprobanteCompraVenta());
		return request;
	}

	/**
	 * Obtener comprobante compra venta.
	 *
	 * @return the comprobante compra venta dolar
	 */
	private ComprobanteCompraVentaDolar obtenerComprobanteCompraVenta() {
		ComprobanteCompraVentaDolar comprobanteCV = new ComprobanteCompraVentaDolar();
		rellenarComprobanteBase(comprobanteCV, "2", "31");
		comprobanteCV.setIdMedioPago(null);
		comprobanteCV.setDescMedioPago(null);
		comprobanteCV.setFechaOper(formatearFechaPMC(comprobanteDTO.getFechaHoraServicio().substring(0, 8)));
		comprobanteCV.setHoraOper(formatearHoraConSegundos(comprobanteDTO.getFechaHoraServicio().substring(8)));
		comprobanteCV.setCuentaOrigen(obtenerCuentaOrigen());
		comprobanteCV.setCuentaDestino(obtenerCuentaDestino());
		if (comprobanteDTO.getEsCompra()) {
			comprobanteCV.setImporteAcreditado("u$s ");
			comprobanteCV.setImporteDebitado("$ ");
		} else {
			comprobanteCV.setImporteAcreditado("$ ");
			comprobanteCV.setImporteDebitado("u$s ");
		}
		if (comprobanteDTO.getImportePesosAcreditado() != null) {
			comprobanteCV.setImporteAcreditado(comprobanteCV.getImporteAcreditado()
					+ comprobanteDTO.getImportePesosAcreditado().toString().replace(".", ","));
		} else {
			comprobanteCV.setImporteAcreditado(comprobanteCV.getImporteAcreditado()
					+ comprobanteDTO.getImporteDolarAcreditado().toString().replace(".", ","));
		}
		if (comprobanteDTO.getImporteDolaresDebitado() != null) {
			comprobanteCV.setImporteDebitado(comprobanteCV.getImporteDebitado()
					+ comprobanteDTO.getImporteDolaresDebitado().toString().replace(".", ","));
		} else {
			comprobanteCV.setImporteDebitado(comprobanteCV.getImporteDebitado()
					+ comprobanteDTO.getImportePesosDebitado().toString().replace(".", ","));
		}
		comprobanteCV.setCotizacionAplicada(
				"u$s 1 = $" + comprobanteDTO.getCotizacionAplicada().toString().replace(".", ","));
		comprobanteCV.setNumeroOperacion(comprobanteDTO.getNumeroOperacion());
		comprobanteCV.setNroComprobante(comprobanteDTO.getNumeroComprobante());
		return comprobanteCV;
	}

	/**
	 * Obtener cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	private CuentaDestino obtenerCuentaDestino() {
		CuentaDestino cuentaDestino = new CuentaDestino();
		cuentaDestino.setNumeroCuentaDestino(comprobanteDTO.getCuentaDestinoNumero().split("-")[1].replace("/", ""));
		cuentaDestino.setSucursalCuentaDestino(comprobanteDTO.getCuentaDestinoNumero().split("-")[0]);
		String tipoCuentaDestino = parametros.getCuentaDestino().getTipoCuenta();
		if ("02".equals(tipoCuentaDestino) && comprobanteDTO.getCuentaDestinoTipo().contains("u$s")) {
			tipoCuentaDestino = "10";
		} else if ("02".equals(tipoCuentaDestino)) {
			tipoCuentaDestino = "09";
		}
		cuentaDestino.setTipoCuentaDestino(tipoCuentaDestino);
		return cuentaDestino;
	}

	/**
	 * Obtener cuenta origen.
	 *
	 * @return the cuenta origen
	 */
	private CuentaOrigen obtenerCuentaOrigen() {
		CuentaOrigen cuentaOrigen = new CuentaOrigen();
		cuentaOrigen.setNumeroCuentaOrigen(comprobanteDTO.getCuentaOrigenNumero().split("-")[1].replace("/", ""));
		cuentaOrigen.setSucursalCuentaOrigen(comprobanteDTO.getCuentaOrigenNumero().split("-")[0]);
		String tipoCuentaOrigen = parametros.getCuentaOrigen().getTipoCuenta();
		if ("02".equals(tipoCuentaOrigen) && comprobanteDTO.getCuentaOrigenTipo().contains("u$s")) {
			tipoCuentaOrigen = "10";
		} else if ("02".equals(tipoCuentaOrigen)) {
			tipoCuentaOrigen = "09";
		}
		cuentaOrigen.setTipoCuentaOrigen(tipoCuentaOrigen);
		return cuentaOrigen;
	}

	/**
	 * Sets the comprobante DTO.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @return the compra venta builder
	 */
	public CompraVentaBuilder setComprobanteDTO(ComprobanteCompraVentaDTO comprobanteDTO) {
		this.comprobanteDTO = comprobanteDTO;
		return this;
	}

	/**
	 * Sets the parametros.
	 *
	 * @param parametros
	 *            the parametros
	 * @return the compra venta builder
	 */
	public CompraVentaBuilder setParametros(ParametrosOperacion parametros) {
		this.parametros = parametros;
		return this;
	}

}
