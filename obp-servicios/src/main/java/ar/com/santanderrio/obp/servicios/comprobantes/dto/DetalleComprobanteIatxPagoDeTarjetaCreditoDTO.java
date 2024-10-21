/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang.StringUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePagoTarjetaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;

/**
 * The Class DetalleComprobanteIatxPagoDeTarjetaCreditoDTO.
 */
public class DetalleComprobanteIatxPagoDeTarjetaCreditoDTO extends DetalleComprobanteDTO {

	private String nroTarjetaCredito;

	private TipoCuenta tipoCuenta;

	/**
	 * @return the nroTarjetaCredito
	 */
	public String getNroTarjetaCredito() {
		return nroTarjetaCredito;
	}

	/**
	 * @param nroTarjetaCredito
	 *            the nroTarjetaCredito to set
	 */
	public void setNroTarjetaCredito(String nroTarjetaCredito) {
		this.nroTarjetaCredito = nroTarjetaCredito;
	}

	/**
	 * @return the tipoCuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobantePagoTarjetaView view = new DetalleComprobantePagoTarjetaView();
		setearImportes(view, comprobante);
		setearNumeroTipoYTitulo(view);
		view.setEfectuada(!comprobante.isNoEfectuada());
		TipoCuentaTarjeta tipoCuentaTarjeta = TipoCuentaTarjeta
				.getTipoCuentaTarjetaFromTipoCuenta(comprobante.getTipoCuentaDestino());
		view.setDestinatario(comprobante.getDestinatario().replace(tipoCuentaTarjeta.getDescripcion(),
				tipoCuentaTarjeta.getAbreviatura()));
		view.setFecha(setearHora(comprobante.getFecha(), false));
		view.setTransaccion(comprobante.getCtaMedioDePagoPesos());
		view.setTipoTarjeta(comprobante.getTipoCuentaDestino().getCodigo().toString());
		return view;
	}

	@Override
	public String obtenerIdentificacionHistorial() {
		if (TipoCuenta.AMEX.equals(tipoCuenta)) {
			return "XXXX-" + StringUtils.right(nroTarjetaCredito, 5);
		}
		return "XXXX-" + StringUtils.right(nroTarjetaCredito, 4);

	}

}
