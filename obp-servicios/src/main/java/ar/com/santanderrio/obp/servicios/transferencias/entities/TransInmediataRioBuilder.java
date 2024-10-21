/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaRio;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class TransInmediataRioBuilder.
 */
public class TransInmediataRioBuilder extends AltaComprobanteRequestBuilder {

	/** The transferencia view. */
	private TransferenciaView transferenciaView;

	/**
	 * Instantiates a new trans inmediata rio builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public TransInmediataRioBuilder(Cliente cliente) {
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
		AltaComprobanteRequest request = obtenerAltaBase("2", "9");
		request.setComprobante(obtenerComprobanteTrfcciInmRioRio());
		return request;
	}

	/**
	 * Obtener comprobante trfcci inm rio rio.
	 *
	 * @return the comprobante transf inm rio rio
	 */
	private ComprobanteTransfInmRioRio obtenerComprobanteTrfcciInmRioRio() {
		ComprobanteTransfInmRioRio inEntity = new ComprobanteTransfInmRioRio();
		rellenarComprobanteBase(inEntity, "2", "9");
		inEntity.setFechaOper(formatearFecha(transferenciaView.getFechaOperacion()));
		inEntity.setHoraOper(formatearHora(transferenciaView.getFechaOperacion()));
		inEntity.setTransferencia(obtenerTransferencia());
		/// para transferencia banca privada
		String numSucursalOrigen = inEntity.getTransferencia().getSucursalCuentaOrigen();
		String numSucursalDestino = inEntity.getTransferencia().getSucursalCuentaDestino();
		if(CuentasBancaPrivadaUtil.isCuentaBP(numSucursalOrigen) || CuentasBancaPrivadaUtil.isCuentaBP(numSucursalDestino)) {
			inEntity.setCanal("79");
			inEntity.setSubCanal("00");
		}
		return inEntity;
	}

	/**
	 * Obtener transferencia.
	 *
	 * @return the transferencia rio
	 */
	private TransferenciaRio obtenerTransferencia() {
		TransferenciaRio transferencia = new TransferenciaRio();
		transferencia.setAlias(StringUtils.defaultString(transferenciaView.getReferenciaApodo()));
		transferencia.setAliasCBU(StringUtils.defaultString(transferenciaView.getAliasDestino()));
		transferencia.setNombreDestinatario(transferenciaView.getTitular());
		transferencia.setImporte(transferenciaView.getImporte().replace(".", ""));
		transferencia.setTipoCuentaOrigen(
				obtenerTipoCuenta(transferenciaView.getTipoCuentaDescripcion(), transferenciaView.getMoneda()));
		transferencia.setSucursalCuentaOrigen(StringUtils.left(transferenciaView.getNroCuenta(), 3));
		transferencia.setNumeroCuentaOrigen(obtenerNumeroCuentaSinSucursal(transferenciaView.getNroCuenta()));
		transferencia.setBanco(transferenciaView.getBanco());
		transferencia.setTipoCuentaDestino(
				obtenerTipoCuenta(transferenciaView.getTipoCuentaDestino(), transferenciaView.getMoneda()));
		transferencia.setSucursalCuentaDestino(StringUtils.left(transferenciaView.getNroCuentaDestino(), 3));
		transferencia.setNumeroCuentaDestino(obtenerNumeroCuentaSinSucursal(transferenciaView.getNroCuentaDestino()));
		transferencia.setConcepto(transferenciaView.getConcepto().getDescripcionAbreviada());
		transferencia.setDescripcionConcepto(transferenciaView.getDescripcion());
		transferencia.setPlazoAcreditacion(transferenciaView.getFechaAcreditacion());
		if (StringUtils.isNotBlank(transferenciaView.getEmail())) {
			transferencia.setEmailDestinatario(transferenciaView.getEmail());
		}
		if (StringUtils.isNotBlank(transferenciaView.getMensajeEmail())) {
			transferencia.setMensaje(transferenciaView.getMensajeEmail());
		}
		transferencia.setNroComprobante(transferenciaView.getNumeroComprobante());
		return transferencia;
	}

	/**
	 * Sets the transferencia view.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the trans inmediata rio builder
	 */
	public TransInmediataRioBuilder setTransferenciaView(TransferenciaView transferencia) {
		transferenciaView = transferencia;
		return this;
	}
}