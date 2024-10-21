/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCAfip;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Class PagoMisCuentasAFIPBuilder.
 */
public class PagoMisCuentasAFIPBuilder extends AltaComprobanteRequestBuilder {

	/** The pago response. */
	private PagoPMC pagoResponse;

	/** The pago in entity. */
	private PagoInEntity pagoInEntity;

	/** The identificacion 2. */
	private String identificacion2;

	/** The is domestico. */
	private Boolean isDomestico;

	/** The datos adicionales1. */
	private String datosAdicionales1;

	/** The datos adicionales2. */
	private String datosAdicionales2;

	/**
	 * Instantiates a new pago mis cuentas AFIP builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public PagoMisCuentasAFIPBuilder(Cliente cliente) {
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
		AltaComprobanteRequest request = obtenerAltaBase("2", "14");
		request.setComprobante(obtenerComprobantePMCAfip());
		return request;
	}

	/**
	 * Obtener comprobante PMC afip.
	 *
	 * @return the comprobante PMC afip
	 */
	private ComprobantePMCAfip obtenerComprobantePMCAfip() {
		ComprobantePMCAfip inEntity = new ComprobantePMCAfip();
		rellenarComprobanteBase(inEntity, "2", "14");
		inEntity.setFechaOper(formatearFechaPMC(pagoResponse.getFechaHoraBody().substring(0, 8)));
		inEntity.setHoraOper(formatearHoraConSegundos(pagoResponse.getFechaHoraBody().substring(8)));
		inEntity.setIdMedioPago(null);
		inEntity.setDescMedioPago(null);
		inEntity.setTransferencia(obtenerTransferencia());
		return inEntity;
	}

	/**
	 * Obtener transferencia.
	 *
	 * @return the transferencia PMC afip
	 */
	private TransferenciaPMCAfip obtenerTransferencia() {
		TransferenciaPMCAfip transferencia = new TransferenciaPMCAfip();
		transferencia.setEmpresa(pagoInEntity.getEmpresaNombreFantasia());
		transferencia.setMoneda("$");
		transferencia.setImporte(
				formatearImportePMC(ISBANStringUtils.sacarCerosYBlancosIzq(pagoInEntity.getMonto()).replace(".", "")));
		transferencia.setFechaHoraPago(pagoResponse.getFechaHoraBody());
		transferencia.setFechaVencimiento("00000000");
		transferencia.setIdentificacion(pagoInEntity.getIdentificacion().replace("-", ""));
		if (isDomestico) {
			if (identificacion2 == null) {
				identificacion2 = "";
			}
			transferencia.setIdentificacion2(identificacion2.replace("-", ""));// 2do
																				// factor

			transferencia.setPeriodoPago(setearCamposAdicionales());
			transferencia.setDatosAdicionales("");
		} else {
			transferencia.setIdentificacion2("");
			transferencia.setPeriodoPago("");
			transferencia.setDatosAdicionales(setearCamposAdicionales());
		}
		transferencia.setTipoCuentaDebito(pagoInEntity.getTipoCuenta());
		transferencia.setSucursalCuentaDebito(StringUtils.right(pagoInEntity.getSucursalCuenta(), 3));
		transferencia.setNumeroCuentaDebito(StringUtils.right(pagoInEntity.getNumeroCuenta(), 7));
		transferencia.setNumControl(pagoResponse.getNumeroControl());
		transferencia.setNroComprobante(StringUtils.right(pagoResponse.getComprobantePorServicio(), 8));
		return transferencia;
	}

	/**
	 * Setear campos adicionales.
	 *
	 * @return the string
	 */
	private String setearCamposAdicionales() {
		String res = "";
		if (datosAdicionales1 != null) {
			res = res + datosAdicionales1;
			if (datosAdicionales2 != null) {
				res = res + " - " + datosAdicionales2;
			}
		}
		return res;
	}

	/**
	 * Sets the pago response.
	 *
	 * @param pagoResponse
	 *            the pago response
	 * @return the pago mis cuentas AFIP builder
	 */
	public PagoMisCuentasAFIPBuilder setPagoResponse(PagoPMC pagoResponse) {
		this.pagoResponse = pagoResponse;
		return this;
	}

	/**
	 * Sets the pago in entity.
	 *
	 * @param pagoInEntity
	 *            the pago in entity
	 * @return the pago mis cuentas AFIP builder
	 */
	public PagoMisCuentasAFIPBuilder setPagoInEntity(PagoInEntity pagoInEntity) {
		this.pagoInEntity = pagoInEntity;
		return this;
	}

	/**
	 * Sets the identificacion 2.
	 *
	 * @param identificacion2
	 *            the identificacion 2
	 * @return the pago mis cuentas AFIP builder
	 */
	public PagoMisCuentasAFIPBuilder setIdentificacion2(String identificacion2) {
		this.identificacion2 = identificacion2;
		return this;
	}

	/**
	 * Checks if is domestico.
	 *
	 * @param isDomestico
	 *            the is domestico
	 * @return the pago mis cuentas AFIP builder
	 */
	public PagoMisCuentasAFIPBuilder isDomestico(boolean isDomestico) {
		this.isDomestico = isDomestico;
		return this;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales1
	 *            the datos adicionales 1
	 * @return the pago mis cuentas AFIP builder
	 */
	public PagoMisCuentasAFIPBuilder setDatosAdicionales1(String datosAdicionales1) {
		this.datosAdicionales1 = datosAdicionales1;
		return this;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales2
	 *            the datos adicionales 2
	 * @return the pago mis cuentas AFIP builder
	 */
	public PagoMisCuentasAFIPBuilder setDatosAdicionales2(String datosAdicionales2) {
		this.datosAdicionales2 = datosAdicionales2;
		return this;
	}
}
