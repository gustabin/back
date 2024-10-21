/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCVEP;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Class PagoMisCuentasVEPBuilder.
 */
public class PagoMisCuentasVEPBuilder extends AltaComprobanteRequestBuilder {

	/** The pago response. */
	private PagoPMC pagoResponse;

	/** The pago in entity. */
	private PagoInEntity pagoInEntity;

	/** The identificacion 2. */
	private String identificacion2;

	/**
	 * Instantiates a new pago mis cuentas VEP builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public PagoMisCuentasVEPBuilder(Cliente cliente) {
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
		AltaComprobanteRequest request = obtenerAltaBase("2", "13");
		request.setComprobante(obtenerComprobantePMCVEP());
		return request;
	}

	/**
	 * Obtener comprobante PMCVEP.
	 *
	 * @return the comprobante PMCVEP
	 */
	private ComprobantePMCVEP obtenerComprobantePMCVEP() {
		ComprobantePMCVEP inEntity = new ComprobantePMCVEP();
		rellenarComprobanteBase(inEntity, "2", "13");
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
	 * @return the transferencia PMCVEP
	 */
	private TransferenciaPMCVEP obtenerTransferencia() {
		TransferenciaPMCVEP transferencia = new TransferenciaPMCVEP();
		transferencia.setEmpresa(pagoInEntity.getEmpresaNombreFantasia());
		transferencia.setMoneda("$");
		transferencia.setImporte(
				formatearImportePMC(ISBANStringUtils.sacarCerosYBlancosIzq(pagoInEntity.getMonto()).replace(".", "")));
		transferencia.setFechaHoraPago(pagoResponse.getFechaHoraBody());
		if(StringUtils.isNotBlank(pagoInEntity.getFechaDeVencimiento())){
		    transferencia.setFechaVencimiento(pagoInEntity.getFechaDeVencimiento());
		}
		transferencia.setIdentificacion(pagoInEntity.getIdentificacion().replace("-", ""));
		transferencia.setIdentificacion2(StringUtils.replace(identificacion2, "-", ""));
		transferencia.setNumeroVep(pagoInEntity.getNumeroFactura().substring(0, 12));
		transferencia.setPeriodo(pagoInEntity.getNumeroFactura().substring(12, 16));
		transferencia.setAnticipoCuota(pagoInEntity.getNumeroFactura().substring(16, 19));
		transferencia.setTipoCuentaDebito(pagoInEntity.getTipoCuenta());
		transferencia.setSucursalCuentaDebito(StringUtils.right(pagoInEntity.getSucursalCuenta(), 3));
		transferencia.setNumeroCuentaDebito(StringUtils.right(pagoInEntity.getNumeroCuenta(), 7));
		transferencia.setNumControl(pagoResponse.getNumeroControl());
		transferencia.setNroComprobante(StringUtils.right(pagoResponse.getComprobantePorServicio(), 8));
		return transferencia;
	}

	/**
	 * Sets the pago response.
	 *
	 * @param pagoResponse
	 *            the pago response
	 * @return the pago mis cuentas VEP builder
	 */
	public PagoMisCuentasVEPBuilder setPagoResponse(PagoPMC pagoResponse) {
		this.pagoResponse = pagoResponse;
		return this;
	}

	/**
	 * Sets the pago in entity.
	 *
	 * @param pagoInEntity
	 *            the pago in entity
	 * @return the pago mis cuentas VEP builder
	 */
	public PagoMisCuentasVEPBuilder setPagoInEntity(PagoInEntity pagoInEntity) {
		this.pagoInEntity = pagoInEntity;
		return this;
	}

	/**
	 * Sets the identificacion 2.
	 *
	 * @param identificacion2
	 *            the identificacion 2
	 * @return the pago mis cuentas VEP builder
	 */
	public PagoMisCuentasVEPBuilder setIdentificacion2(String identificacion2) {
		this.identificacion2 = identificacion2;
		return this;
	}

}
