/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author juan.pablo.picate
 *
 */
public class MantenimientoPlazoFijoInEntity {

	/** The certificado. */
	private String certificado;

	/** numerico*. */
	private String sucursal;

	/** The cuenta. */
	private String cuenta;

	/** numerico. */
	private String secuencia;

	/** The moneda. */
	private String moneda;

	/** The renovacion. */
	private String renovacion;

	/** 3 enteros, 5 decimales. */
	private String tasa;

	/** numerico. */
	private String circular;

	/** The forma pago. */
	private String formaPago;

	/** numerico. */
	private String secuenciaCan;

	/** The centro gestor. */
	private String centroGestor;

	/** The bloqueo cta. */
	private String bloqueoCta;

	/** numerico. */
	private String nssf;

	/** The tarifa. */
	private String tarifa;

	/** The indicador transferible. */
	private String indicadorTransferible;

	/** The cuenta credito. */
	private String cuentaCredito;

	/** The indicador capitaliza intereses. */
	private String indicadorCapitalizaIntereses;

	/**
	 * Gets the certificado.
	 *
	 * @return the certificado
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * Sets the certificado.
	 *
	 * @param certificado
	 *            the certificado to set
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the renovacion.
	 *
	 * @return the renovacion
	 */
	public String getRenovacion() {
		return renovacion;
	}

	/**
	 * Sets the renovacion.
	 *
	 * @param renovacion
	 *            the renovacion to set
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the tasa.
	 *
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 *
	 * @param tasa
	 *            the tasa to set
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	/**
	 * Gets the circular.
	 *
	 * @return the circular
	 */
	public String getCircular() {
		return circular;
	}

	/**
	 * Sets the circular.
	 *
	 * @param circular
	 *            the circular to set
	 */
	public void setCircular(String circular) {
		this.circular = circular;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Gets the secuencia can.
	 *
	 * @return the secuenciaCan
	 */
	public String getSecuenciaCan() {
		return secuenciaCan;
	}

	/**
	 * Sets the secuencia can.
	 *
	 * @param secuenciaCan
	 *            the secuenciaCan to set
	 */
	public void setSecuenciaCan(String secuenciaCan) {
		this.secuenciaCan = secuenciaCan;
	}

	/**
	 * Gets the centro gestor.
	 *
	 * @return the centroGestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}

	/**
	 * Sets the centro gestor.
	 *
	 * @param centroGestor
	 *            the centroGestor to set
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
	}

	/**
	 * Gets the bloqueo cta.
	 *
	 * @return the bloqueoCta
	 */
	public String getBloqueoCta() {
		return bloqueoCta;
	}

	/**
	 * Sets the bloqueo cta.
	 *
	 * @param bloqueoCta
	 *            the bloqueoCta to set
	 */
	public void setBloqueoCta(String bloqueoCta) {
		this.bloqueoCta = bloqueoCta;
	}

	/**
	 * Gets the nssf.
	 *
	 * @return the nSSF
	 */
	public String getNssf() {
		return nssf;
	}

	/**
	 * Sets the nssf.
	 *
	 * @param nssf
	 *            the new nssf
	 */
	public void setNssf(String nssf) {
		this.nssf = nssf;
	}

	/**
	 * Gets the tarifa.
	 *
	 * @return the tarifa
	 */
	public String getTarifa() {
		return tarifa;
	}

	/**
	 * Sets the tarifa.
	 *
	 * @param tarifa
	 *            the tarifa to set
	 */
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * Gets the indicador transferible.
	 *
	 * @return the indicadorTransferible
	 */
	public String getIndicadorTransferible() {
		return indicadorTransferible;
	}

	/**
	 * Sets the indicador transferible.
	 *
	 * @param indicadorTransferible
	 *            the indicadorTransferible to set
	 */
	public void setIndicadorTransferible(String indicadorTransferible) {
		this.indicadorTransferible = indicadorTransferible;
	}

	/**
	 * Gets the cuenta credito.
	 *
	 * @return the cuentaCredito
	 */
	public String getCuentaCredito() {
		return cuentaCredito;
	}

	/**
	 * Sets the cuenta credito.
	 *
	 * @param cuentaCredito
	 *            the cuentaCredito to set
	 */
	public void setCuentaCredito(String cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	/**
	 * Gets the indicador capitaliza intereses.
	 *
	 * @return the indicadorCapitalizaIntereses
	 */
	public String getIndicadorCapitalizaIntereses() {
		return indicadorCapitalizaIntereses;
	}

	/**
	 * Sets the indicador capitaliza intereses.
	 *
	 * @param indicadorCapitalizaIntereses
	 *            the indicadorCapitalizaIntereses to set
	 */
	public void setIndicadorCapitalizaIntereses(String indicadorCapitalizaIntereses) {
		this.indicadorCapitalizaIntereses = indicadorCapitalizaIntereses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}

}
