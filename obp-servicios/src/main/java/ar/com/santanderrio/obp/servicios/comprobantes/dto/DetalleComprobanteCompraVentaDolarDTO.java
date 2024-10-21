/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class DetalleComprobanteCompraVentaDolar.
 */
public class DetalleComprobanteCompraVentaDolarDTO extends DetalleComprobanteDTO {

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The tipo cuenta destino. */
	private TipoCuenta tipoCuentaDestino;

	/** The cotizacion aplicada. */
	private BigDecimal cotizacionAplicada;

	/** The label debito. */
	private String labelDebito;

	/** The label credito. */
	private String labelCredito;

	/** The nro operacion. */
	private String nroOperacion;

	/** The saldo debitado. */
	private BigDecimal saldoDebitado;

	/** The hora. */
	private String hora;

	/** The legal. */
	private String legal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobanteDTO) {
		DetalleCompraVentaDolarView view = new DetalleCompraVentaDolarView();
		setearNumeroTipoYTitulo(view);
		setearNumeroYTipoCuentaOrigen(view, comprobanteDTO);
		view.setNroCuentaDestino(getNroCuentaDestino());
		view.setTipoCuentaDestino(getTipoCuentaDestino().getDescripcion());
		view.setCotizacionAplicada(
				"U$S 1 = $ " + ISBANStringUtils.formatearConComaYDosDecimales(getCotizacionAplicada().toString()));
		view.setLabelCredito(getLabelCredito());
		view.setLabelDebito(getLabelDebito());
		setearCreditoYDebito(view, comprobanteDTO);
		view.setNroOperacion(getNroOperacion());
		view.setFecha(setearHora(comprobanteDTO.getFecha(), false));
		view.setHora(getHora());
		view.setLegales(getLegal());
		return view;
	}

	/**
	 * Setear credito Y debito.
	 *
	 * @param view
	 *            the view
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	private void setearCreditoYDebito(DetalleCompraVentaDolarView view, ComprobanteDTO comprobanteDTO) {
		if (comprobanteDTO.getTipoOperacion().equals(TipoOperacionComprobanteEnum.COMPRA_DOLARES)) {
			view.setSaldoAcreditado("u$s "
					+ ISBANStringUtils.formatearConComaYDosDecimales(comprobanteDTO.getImporteDolares().toString()));
			view.setSaldoDebitado("$ " + ISBANStringUtils.formatearConComaYDosDecimales(getSaldoDebitado().toString()));
		} else {
			view.setSaldoAcreditado(
					"$ " + ISBANStringUtils.formatearConComaYDosDecimales(comprobanteDTO.getImportePesos().toString()));
			view.setSaldoDebitado(
					"u$s " + ISBANStringUtils.formatearConComaYDosDecimales(getSaldoDebitado().toString()));
		}
	}

	/**
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public TipoCuenta getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(TipoCuenta tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the cotizacion aplicada.
	 *
	 * @return the cotizacion aplicada
	 */
	public BigDecimal getCotizacionAplicada() {
		return cotizacionAplicada;
	}

	/**
	 * Sets the cotizacion aplicada.
	 *
	 * @param cotizacionAplicada
	 *            the new cotizacion aplicada
	 */
	public void setCotizacionAplicada(BigDecimal cotizacionAplicada) {
		this.cotizacionAplicada = cotizacionAplicada;
	}

	/**
	 * Gets the label debito.
	 *
	 * @return the label debito
	 */
	public String getLabelDebito() {
		return labelDebito;
	}

	/**
	 * Sets the label debito.
	 *
	 * @param labelDebito
	 *            the new label debito
	 */
	public void setLabelDebito(String labelDebito) {
		this.labelDebito = labelDebito;
	}

	/**
	 * Gets the label credito.
	 *
	 * @return the label credito
	 */
	public String getLabelCredito() {
		return labelCredito;
	}

	/**
	 * Sets the label credito.
	 *
	 * @param labelCredito
	 *            the new label credito
	 */
	public void setLabelCredito(String labelCredito) {
		this.labelCredito = labelCredito;
	}

	/**
	 * Gets the nro operacion.
	 *
	 * @return the nro operacion
	 */
	public String getNroOperacion() {
		return nroOperacion;
	}

	/**
	 * Sets the nro operacion.
	 *
	 * @param nroOperacion
	 *            the new nro operacion
	 */
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

	/**
	 * Gets the saldo debitado.
	 *
	 * @return the saldo debitado
	 */
	public BigDecimal getSaldoDebitado() {
		return saldoDebitado;
	}

	/**
	 * Sets the saldo debitado.
	 *
	 * @param saldoDebitado
	 *            the new saldo debitado
	 */
	public void setSaldoDebitado(BigDecimal saldoDebitado) {
		this.saldoDebitado = saldoDebitado;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}
	
	@Override
    public String obtenerIdentificacionHistorial() {
    	return nroCuentaDestino;
    }

	
	

}
