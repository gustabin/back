/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaOB;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class TransInmediataOBBuilder.
 */
public class TransInmediataOBBuilder extends AltaComprobanteRequestBuilder {

	/** The transferencia view. */
	private TransferenciaView transferenciaView;

	/**
	 * Instantiates a new trans inmediata OB builder.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public TransInmediataOBBuilder(Cliente cliente) {
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
		AltaComprobanteRequest request = obtenerAltaBase("2", "10");
		request.setComprobante(obtenerComprobanteTransfRioOB());
		return request;
	}

	/**
	 * Sets the transferencia view.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the trans inmediata OB builder
	 */
	public TransInmediataOBBuilder setTransferenciaView(TransferenciaView transferencia) {
		transferenciaView = transferencia;
		return this;
	}

	/**
	 * Obtener comprobante transf rio OB.
	 *
	 * @return the comprobante transf inm rio OB
	 */
	private ComprobanteTransfInmRioOB obtenerComprobanteTransfRioOB() {
		ComprobanteTransfInmRioOB inEntity = new ComprobanteTransfInmRioOB();
		rellenarComprobanteBase(inEntity, "2", "10");
		inEntity.setIdMedioPago(null);
		inEntity.setDescMedioPago(null);
		inEntity.setFechaOper(formatearFecha(transferenciaView.getFechaOperacion()));
		inEntity.setHoraOper(formatearHora(transferenciaView.getFechaOperacion()));
		inEntity.setTransferencia(obtenerTransferencia());
		/// para transferencia banca privada
		String numSucursalOrigen = inEntity.getTransferencia().getSucursalCuentaOrigen();
		if(CuentasBancaPrivadaUtil.isCuentaBP(numSucursalOrigen)) {
			inEntity.setCanal("79");
			inEntity.setSubCanal("00");
		}
		return inEntity;
	}

	/**
	 * Obtener transferencia.
	 *
	 * @return the transferencia OB
	 */
	private TransferenciaOB obtenerTransferencia() {
		TransferenciaOB transferencia = new TransferenciaOB();
		transferencia.setAlias(StringUtils.defaultString(transferenciaView.getReferenciaApodo()));
		transferencia.setAliasCBU(StringUtils.defaultString(transferenciaView.getAliasDestino()));
		transferencia.setNombreDestinatario(transferenciaView.getTitular());
		transferencia.setImporte(transferenciaView.getImporte().replace(".", "").replace(",", "."));
		transferencia.setTipoCuentaOrigen(
				obtenerTipoCuenta(transferenciaView.getTipoCuentaDescripcion(), transferenciaView.getMoneda()));
		transferencia.setSucursalCuentaOrigen(StringUtils.left(transferenciaView.getNroCuenta(), 3));
		transferencia.setNumeroCuentaOrigen(obtenerNumeroCuentaSinSucursal(transferenciaView.getNroCuenta()));
		transferencia.setBanco(transferenciaView.getBanco());
		transferencia.setCbu(transferenciaView.getCbu());
		transferencia.setCuitCuil(transferenciaView.getCuitCuil().replaceAll("-", ""));
		transferencia.setConcepto(transferenciaView.getConcepto().getDescripcionAbreviada());
		transferencia.setDescripcionConcepto(transferenciaView.getDescripcion());
		transferencia.setPlazoAcreditacion(transferenciaView.getFechaAcreditacion());
		transferencia.setEmailDestinatario(transferenciaView.getEmail());
		transferencia.setMensaje(transferenciaView.getMensajeEmail());
		transferencia.setNroComprobante(transferenciaView.getNumeroComprobante());
		return transferencia;
	}

}
