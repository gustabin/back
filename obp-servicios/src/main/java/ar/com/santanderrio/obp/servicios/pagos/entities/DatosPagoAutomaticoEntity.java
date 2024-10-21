/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class DatosPagoAutomatico.
 */
public class DatosPagoAutomaticoEntity {

	/** The empresa. */
	private String empresa;

	/** The id cliente empresa. */
	private String idClienteEmpresa;

	/** The vencimiento. */
	private Date vencimiento;

	/** The divisa. */
	private DivisaEnum divisa;

	/** The importe. */
	private BigDecimal importe;

	/** The factura. */
	private String factura;

	/** The fecha stop debit. */
	private Date fechaStopDebit;

	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;

	/** The identificacion cuenta. */
	private IdentificacionCuenta identificacionCuenta;

	/** The tope. */
	private BigDecimal tope;

	/** The descripciones de error. */
	private List<String> descripcionesDeError;

	/** The sistema asociado al error. */
	private String sistemaAsociadoAlError;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The codigo estado DDI. */
	// DatosEntity de CNSDDIDERE
	private String codigoEstadoDDI;

	/** The id debito DDI. */
	private String idDebitoDDI;

	/** The importe debito original DDI. */
	private BigDecimal importeDebitoOriginalDDI;

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the id cliente empresa.
	 *
	 * @return the id cliente empresa
	 */
	public String getIdClienteEmpresa() {
		return idClienteEmpresa;
	}

	/**
	 * Sets the id cliente empresa.
	 *
	 * @param idClienteEmpresa
	 *            the new id cliente empresa
	 */
	public void setIdClienteEmpresa(String idClienteEmpresa) {
		this.idClienteEmpresa = idClienteEmpresa;
	}

	/**
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public Date getVencimiento() {
		return vencimiento == null ? null : new Date(vencimiento.getTime());
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the new vencimiento
	 */
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento == null ? null : new Date(vencimiento.getTime());
		;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura
	 *            the new factura
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	/**
	 * Gets the fecha stop debit.
	 *
	 * @return the fecha stop debit
	 */
	public Date getFechaStopDebit() {
		return fechaStopDebit == null ? null : new Date(fechaStopDebit.getTime());
	}

	/**
	 * Sets the fecha stop debit.
	 *
	 * @param fechaStopDebit
	 *            the new fecha stop debit
	 */
	public void setFechaStopDebit(Date fechaStopDebit) {
		this.fechaStopDebit = fechaStopDebit == null ? null : new Date(fechaStopDebit.getTime());
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the identificacion cuenta.
	 *
	 * @return the identificacion cuenta
	 */
	public IdentificacionCuenta getIdentificacionCuenta() {
		return identificacionCuenta;
	}

	/**
	 * Sets the identificacion cuenta.
	 *
	 * @param identificacionCuenta
	 *            the new identificacion cuenta
	 */
	public void setIdentificacionCuenta(IdentificacionCuenta identificacionCuenta) {
		this.identificacionCuenta = identificacionCuenta;
	}

	/**
	 * Gets the tope.
	 *
	 * @return the tope
	 */
	public BigDecimal getTope() {
		return tope;
	}

	/**
	 * Sets the tope.
	 *
	 * @param tope
	 *            the new tope
	 */
	public void setTope(BigDecimal tope) {
		this.tope = tope;
	}

	/**
	 * Gets the descripciones de error.
	 *
	 * @return the descripciones de error
	 */
	public List<String> getDescripcionesDeError() {
		return descripcionesDeError;
	}

	/**
	 * Sets the descripciones de error.
	 *
	 * @param descripcionesDeError
	 *            the new descripciones de error
	 */
	public void setDescripcionesDeError(List<String> descripcionesDeError) {
		this.descripcionesDeError = descripcionesDeError;
	}

	/**
	 * Gets the sistema asociado al error.
	 *
	 * @return the sistema asociado al error
	 */
	public String getSistemaAsociadoAlError() {
		return sistemaAsociadoAlError;
	}

	/**
	 * Sets the sistema asociado al error.
	 *
	 * @param sistemaAsociadoAlError
	 *            the new sistema asociado al error
	 */
	public void setSistemaAsociadoAlError(String sistemaAsociadoAlError) {
		this.sistemaAsociadoAlError = sistemaAsociadoAlError;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the codigo estado DDI.
	 *
	 * @return the codigo estado DDI
	 */
	public String getCodigoEstadoDDI() {
		return codigoEstadoDDI;
	}

	/**
	 * Sets the codigo estado DDI.
	 *
	 * @param codigoEstadoDDI
	 *            the new codigo estado DDI
	 */
	public void setCodigoEstadoDDI(String codigoEstadoDDI) {
		this.codigoEstadoDDI = codigoEstadoDDI;
	}

	/**
	 * Gets the id debito DDI.
	 *
	 * @return the id debito DDI
	 */
	public String getIdDebitoDDI() {
		return idDebitoDDI;
	}

	/**
	 * Sets the id debito DDI.
	 *
	 * @param idDebitoDDI
	 *            the new id debito DDI
	 */
	public void setIdDebitoDDI(String idDebitoDDI) {
		this.idDebitoDDI = idDebitoDDI;
	}

	/**
	 * Gets the importe debito original DDI.
	 *
	 * @return the importe debito original DDI
	 */
	public BigDecimal getImporteDebitoOriginalDDI() {
		return importeDebitoOriginalDDI;
	}

	/**
	 * Sets the importe debito original DDI.
	 *
	 * @param importeDebitoOriginalDDI
	 *            the new importe debito original DDI
	 */
	public void setImporteDebitoOriginalDDI(BigDecimal importeDebitoOriginalDDI) {
		this.importeDebitoOriginalDDI = importeDebitoOriginalDDI;
	}

}
