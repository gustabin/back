/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalView;

/**
 * The Class InicioFondoView.
 *
 * @author marcelo.ruiz
 */
public class InicioFondoView {

	/**
	 * Mensaje de error si no se encuentasn cuentas para el usuario, recuperado
	 * de la base.
	 */
	private String mensajeErrorSinCuentas = "";

	/** The mensaje error parcial. */
	private String mensajeErrorParcial = "";

	/** datos de banca privada. */
	private BancaView bancaPrivada = new BancaView();

	/** datos de banca personal. */
	private BancaView bancaPersonal = new BancaView();

	/** The tenencia B priv dolares. */
	private String tenenciaBPrivDolares;

	/** The tenencia B priv pesos. */
	private String tenenciaBPrivPesos;

	/** The tenencia B pers dolares. */
	private String tenenciaBPersDolares;

	/** The tenencia B pers pesos. */
	private String tenenciaBPersPesos;

	/** Indicador S/N si tiene cuentas monetarias o no. */
	private String tieneCuentasMonetarias;
	
	/** The deshabilitar BP. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private Boolean deshabilitarBP;

	/** The es parcial BPRIV. */
	private boolean esParcialBPRIV;

	/** The Es parcial RTL. */
	private boolean EsParcialRTL;

	/** The mostrar rendimiento tenencia. */
	private boolean mostrarRendimientoTenencia;

	/** The error cuentas bloqueadas. */
	private boolean errorCuentasBloqueadas = false;

	/** The mensaje error cuentas. */
	private String mensajeErrorCuentas;
	
	/** The error cuentas B priv. */
	private boolean errorCuentasBPriv = false;
	
	/** The rentabilidad total. */
	private RentabilidadTotalView rentabilidadTotal;
	
	/** The rentabilidad total. */
	private boolean nuevaApertura;

	/**
	 * Gets the mensaje error sin cuentas.
	 *
	 * @return the mensaje error sin cuentas
	 */
	public String getMensajeErrorSinCuentas() {
		return mensajeErrorSinCuentas;
	}

	/**
	 * Sets the mensaje error sin cuentas.
	 *
	 * @param mensajeErrorSinCuentas
	 *            the new mensaje error sin cuentas
	 */
	public void setMensajeErrorSinCuentas(String mensajeErrorSinCuentas) {
		this.mensajeErrorSinCuentas = mensajeErrorSinCuentas;
	}

	/**
	 * Gets the banca privada.
	 *
	 * @return the banca privada
	 */
	public BancaView getBancaPrivada() {
		return bancaPrivada;
	}

	/**
	 * Sets the banca privada.
	 *
	 * @param bancaPrivada
	 *            the new banca privada
	 */
	public void setBancaPrivada(BancaView bancaPrivada) {
		this.bancaPrivada = bancaPrivada;
	}

	/**
	 * Gets the banca personal.
	 *
	 * @return the banca personal
	 */
	public BancaView getBancaPersonal() {
		return bancaPersonal;
	}

	/**
	 * Sets the banca personal.
	 *
	 * @param bancaPersonal
	 *            the new banca personal
	 */
	public void setBancaPersonal(BancaView bancaPersonal) {
		this.bancaPersonal = bancaPersonal;
	}

	/**
	 * Gets the tenencia B priv dolares.
	 *
	 * @return the tenencia B priv dolares
	 */
	public String getTenenciaBPrivDolares() {
		return tenenciaBPrivDolares;
	}

	/**
	 * Sets the tenencia B priv dolares.
	 *
	 * @param tenenciaBPrivDolares
	 *            the new tenencia B priv dolares
	 */
	public void setTenenciaBPrivDolares(String tenenciaBPrivDolares) {
		this.tenenciaBPrivDolares = tenenciaBPrivDolares;
	}

	/**
	 * Gets the tenencia B priv pesos.
	 *
	 * @return the tenencia B priv pesos
	 */
	public String getTenenciaBPrivPesos() {
		return tenenciaBPrivPesos;
	}

	/**
	 * Sets the tenencia B priv pesos.
	 *
	 * @param tenenciaBPrivPesos
	 *            the new tenencia B priv pesos
	 */
	public void setTenenciaBPrivPesos(String tenenciaBPrivPesos) {
		this.tenenciaBPrivPesos = tenenciaBPrivPesos;
	}

	/**
	 * Gets the tenencia B pers dolares.
	 *
	 * @return the tenencia B pers dolares
	 */
	public String getTenenciaBPersDolares() {
		return tenenciaBPersDolares;
	}

	/**
	 * Sets the tenencia B pers dolares.
	 *
	 * @param tenenciaBPersDolares
	 *            the new tenencia B pers dolares
	 */
	public void setTenenciaBPersDolares(String tenenciaBPersDolares) {
		this.tenenciaBPersDolares = tenenciaBPersDolares;
	}

	/**
	 * Gets the tenencia B pers pesos.
	 *
	 * @return the tenencia B pers pesos
	 */
	public String getTenenciaBPersPesos() {
		return tenenciaBPersPesos;
	}

	/**
	 * Sets the tenencia B pers pesos.
	 *
	 * @param tenenciaBPersPesos
	 *            the new tenencia B pers pesos
	 */
	public void setTenenciaBPersPesos(String tenenciaBPersPesos) {
		this.tenenciaBPersPesos = tenenciaBPersPesos;
	}

	/**
	 * Gets the tiene cuentas monetarias.
	 *
	 * @return the tiene cuentas monetarias
	 */
	public String getTieneCuentasMonetarias() {
		return tieneCuentasMonetarias;
	}

	/**
	 * Sets the tiene cuentas monetarias.
	 *
	 * @param tieneCuentasMonetarias
	 *            the new tiene cuentas monetarias
	 */
	public void setTieneCuentasMonetarias(String tieneCuentasMonetarias) {
		this.tieneCuentasMonetarias = tieneCuentasMonetarias;
	}
	
	/**
	 * Gets the mensaje error parcial.
	 *
	 * @return the mensaje error parcial
	 */
	public String getMensajeErrorParcial() {
		return mensajeErrorParcial;
	}

	/**
	 * Sets the mensaje error parcial.
	 *
	 * @param mensajeErrorParcial
	 *            the new mensaje error parcial
	 */
	public void setMensajeErrorParcial(String mensajeErrorParcial) {
		this.mensajeErrorParcial = mensajeErrorParcial;
	}

	/**
	 * Checks if is es parcial BPRIV.
	 *
	 * @return true, if is es parcial BPRIV
	 */
	public boolean isEsParcialBPRIV() {
		return esParcialBPRIV;
	}

	/**
	 * Sets the es parcial BPRIV.
	 *
	 * @param esParcialBPRIV
	 *            the new es parcial BPRIV
	 */
	public void setEsParcialBPRIV(boolean esParcialBPRIV) {
		this.esParcialBPRIV = esParcialBPRIV;
	}

	/**
	 * Checks if is es parcial RTL.
	 *
	 * @return true, if is es parcial RTL
	 */
	public boolean isEsParcialRTL() {
		return EsParcialRTL;
	}

	/**
	 * Sets the es parcial RTL.
	 *
	 * @param esParcialRTL
	 *            the new es parcial RTL
	 */
	public void setEsParcialRTL(boolean esParcialRTL) {
		EsParcialRTL = esParcialRTL;
	}

	/**
	 * Checks if is mostrar rendimiento tenencia.
	 *
	 * @return true, if is mostrar rendimiento tenencia
	 */
	public boolean isMostrarRendimientoTenencia() {
		return mostrarRendimientoTenencia;
	}

	/**
	 * Sets the mostrar rendimiento tenencia.
	 *
	 * @param mostrarRendimientoTenencia
	 *            the new mostrar rendimiento tenencia
	 */
	public void setMostrarRendimientoTenencia(boolean mostrarRendimientoTenencia) {
		this.mostrarRendimientoTenencia = mostrarRendimientoTenencia;
	}

	/**
	 * Gets the deshabilitar BP.
	 *
	 * @return the deshabilitar BP
	 */
	public Boolean getDeshabilitarBP() {
		return deshabilitarBP;
	}

	/**
	 * Sets the deshabilitar BP.
	 *
	 * @param deshabilitarBP
	 *            the new deshabilitar BP
	 */
	public void setDeshabilitarBP(Boolean deshabilitarBP) {
		this.deshabilitarBP = deshabilitarBP;
	}

	/**
	 * Checks if is error cuentas bloqueadas.
	 *
	 * @return true, if is error cuentas bloqueadas
	 */
	public boolean isErrorCuentasBloqueadas() {
		return errorCuentasBloqueadas;
	}

	/**
	 * Sets the error cuentas bloqueadas.
	 *
	 * @param errorCuentasBloqueadas
	 *            the new error cuentas bloqueadas
	 */
	public void setErrorCuentasBloqueadas(boolean errorCuentasBloqueadas) {
		this.errorCuentasBloqueadas = errorCuentasBloqueadas;
	}

	/**
	 * Gets the mensaje error cuentas.
	 *
	 * @return the mensaje error cuentas
	 */
	public String getMensajeErrorCuentas() {
		return mensajeErrorCuentas;
	}

	/**
	 * Sets the mensaje error cuentas.
	 *
	 * @param mensajeErrorCuentas
	 *            the new mensaje error cuentas
	 */
	public void setMensajeErrorCuentas(String mensajeErrorCuentas) {
		this.mensajeErrorCuentas = mensajeErrorCuentas;
	}

	/**
	 * Checks if is error cuentas B priv.
	 *
	 * @return true, if is error cuentas B priv
	 */
	public boolean isErrorCuentasBPriv() {
		return errorCuentasBPriv;
	}

	/**
	 * Sets the error cuentas B priv.
	 *
	 * @param errorCuentasBPriv
	 *            the new error cuentas B priv
	 */
	public void setErrorCuentasBPriv(boolean errorCuentasBPriv) {
		this.errorCuentasBPriv = errorCuentasBPriv;
	}

	/**
	 * Gets the rentabilidad total.
	 *
	 * @return the rentabilidad total
	 */
	public RentabilidadTotalView getRentabilidadTotal() {
		return rentabilidadTotal;
	}

	/**
	 * Sets the rentabilidad total.
	 *
	 * @param rentabilidadTotal
	 *            the new rentabilidad total
	 */
	public void setRentabilidadTotal(RentabilidadTotalView rentabilidadTotal) {
		this.rentabilidadTotal = rentabilidadTotal;
	}

	public boolean isNuevaApertura() {
		return nuevaApertura;
	}

	public void setNuevaApertura(boolean nuevaApertura) {
		this.nuevaApertura = nuevaApertura;
	}
	
	
	
}
