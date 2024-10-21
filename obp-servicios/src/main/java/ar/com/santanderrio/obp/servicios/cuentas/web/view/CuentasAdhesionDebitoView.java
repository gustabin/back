/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class Cuenta.
 */
public class CuentasAdhesionDebitoView {

	/** The descripcion tipo cuenta. */
	// parametros concensuados conb Diego y Agustina
	private String descripcionTipoCuenta;

	/** The abreviatura tipo cuenta. */
	// parametros concensuados conb Nacho y Cristian
	private String abreviaturaTipoCuenta;

	/** The numero. */
	private String numero;

	/** The cbu. */
	private String cbu;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The signo saldo pesos. */
	private String signoSaldoPesos;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The signo saldo dolares. */
	private String signoSaldoDolares;

	/** The saldo descubierto. */
	private String saldoDescubierto;

	/** The saldo cuenta corriente. */
	private String saldoCuentaCorriente;

	/** The signo saldo cuenta corriente. */
	private String signoSaldoCuentaCorriente;

	/** The saldo caja ahorro. */
	private String saldoCajaAhorro;

	/** The signo saldo caja ahorro. */
	private String signoSaldoCajaAhorro;

	/** The is saldo pesos negativo. */
	private Boolean isSaldoPesosNegativo;

	/** The is saldo pesos cero. */
	private Boolean isSaldoPesosCero;

	/** The is saldo dolares negativo. */
	private Boolean isSaldoDolaresNegativo;

	/** The is saldo dolares cero. */
	private Boolean isSaldoDolaresCero;

	/** The is saldo caja ahorro negativo. */
	private Boolean isSaldoCajaAhorroNegativo;

	/** The is saldo cuenta corriente negativo. */
	private Boolean isSaldoCuentaCorrienteNegativo;

	/** The is saldo descubierto negativo. */
	private Boolean isSaldoDescubiertoNegativo;

	/** The is saldo descubierto cero. */
	private Boolean isSaldoDescubiertoCero;

	/** The is cerrada. */
	private Boolean isCerrada;

	/** The is cuenta unica. */
	private Boolean isCuentaUnica;

	/** The is saldo cuenta corriente cero. */
	private Boolean isSaldoCuentaCorrienteCero;

	/** The is saldo caja ahorro cero. */
	private Boolean isSaldoCajaAhorroCero;

	/** The is traspaso automatico. */
	private Boolean isTraspasoAutomatico;

	/** The is cuenta sueldo. */
	private Boolean isCuentaSueldo;

	/** The url reporte cbu. */
	private String urlReporteCBU;

	/** The nombre cliente. */
	private String nombreCliente;

	/** The apellido cliente. */
	private String apellidoCliente;

	/** The tipo identificacion. */
	private String tipoIdentificacion;

	/** The numero identificacion. */
	private String numeroIdentificacion;

	/** The is descubierto utilizado. */
	private Boolean isDescubiertoUtilizado;

	/** The show descubierto. */
	private Boolean showDescubierto;

	/** The show saldo pesos. */
	private Boolean showSaldoPesos;

	/** The show saldo dolares. */
	private Boolean showSaldoDolares;

	/** The alias. */
	private String alias;

	/** The Boolean hasAlias. */
	private Boolean hasAlias;

	/** The is favorito. */
	private Boolean isFavorito;

	/** The fecha desde movimiento. */
	private String fechaDesdeMovimiento;

	/** The fecha hasta movimiento. */
	private String fechaHastaMovimiento;

	/** The alias cbu. */
	private String aliasCbu;

	/** The show solicitar traspaso. */
	private Boolean showSolicitarTraspaso;

	/** The show realizar traspaso. */
	private Boolean showRealizarTraspaso;

	/** The poder compra. */
	private PoderCompraView poderCompra;
	
	/** The poder compra. */
	private Boolean repatriacion;
	

	/**
	 * Gets the poder compra.
	 *
	 * @return the poderCompra
	 */
	public PoderCompraView getPoderCompra() {
		return poderCompra;
	}

	/**
	 * Sets the poder compra.
	 *
	 * @param poderCompra
	 *            the poderCompra to set
	 */
	public void setPoderCompra(PoderCompraView poderCompra) {
		this.poderCompra = poderCompra;
	}

	/**
	 * Gets the alias.
	 * 
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the checks if is favorito.
	 *
	 * @return the checks if is favorito
	 */
	public Boolean getIsFavorito() {
		return isFavorito;
	}

	/**
	 * Sets the checks if is favorito.
	 *
	 * @param isFavorito
	 *            the new checks if is favorito
	 */
	public void setIsFavorito(Boolean isFavorito) {
		this.isFavorito = isFavorito;
	}

	/**
	 * Gets the checks if is descubierto utilizado.
	 *
	 * @return the checks if is descubierto utilizado
	 */
	public Boolean getIsDescubiertoUtilizado() {
		return isDescubiertoUtilizado;
	}

	/**
	 * Setter para checks if is descubierto utilizado.
	 *
	 * @param isDescubiertoUtilizado
	 *            el nuevo checks if is descubierto utilizado
	 */
	public void setIsDescubiertoUtilizado(Boolean isDescubiertoUtilizado) {
		this.isDescubiertoUtilizado = isDescubiertoUtilizado;
	}

	/**
	 * Gets the show descubierto.
	 *
	 * @return the show descubierto
	 */
	public Boolean getShowDescubierto() {
		return showDescubierto;
	}

	/**
	 * Setter para show descubierto.
	 *
	 * @param showDescubierto
	 *            el nuevo show descubierto
	 */
	public void setShowDescubierto(Boolean showDescubierto) {
		this.showDescubierto = showDescubierto;
	}

	/**
	 * Gets the show saldo pesos.
	 *
	 * @return the show saldo pesos
	 */
	public Boolean getShowSaldoPesos() {
		return showSaldoPesos;
	}

	/**
	 * Setter para show saldo pesos.
	 *
	 * @param showSaldoPesos
	 *            el nuevo show saldo pesos
	 */
	public void setShowSaldoPesos(Boolean showSaldoPesos) {
		this.showSaldoPesos = showSaldoPesos;
	}

	/**
	 * Gets the show saldo dolares.
	 *
	 * @return the show saldo dolares
	 */
	public Boolean getShowSaldoDolares() {
		return showSaldoDolares;
	}

	/**
	 * Setter para show saldo dolares.
	 *
	 * @param showSaldoDolares
	 *            el nuevo show saldo dolares
	 */
	public void setShowSaldoDolares(Boolean showSaldoDolares) {
		this.showSaldoDolares = showSaldoDolares;
	}

	/**
	 * Gets the nombre cliente.
	 *
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Setter para nombre cliente.
	 *
	 * @param nombreCliente
	 *            the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Gets the apellido cliente.
	 *
	 * @return the apellidoCliente
	 */
	public String getApellidoCliente() {
		return apellidoCliente;
	}

	/**
	 * Setter para apellido cliente.
	 *
	 * @param apellidoCliente
	 *            the apellidoCliente to set
	 */
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	/**
	 * Gets the tipo identificacion.
	 *
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Setter para tipo identificacion.
	 *
	 * @param tipoIdentificacion
	 *            the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Gets the numero identificacion.
	 *
	 * @return the numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * Setter para numero identificacion.
	 *
	 * @param numeroIdentificacion
	 *            the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Setter para descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the descripcionTipoCuenta to set
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter para numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Setter para cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Setter para saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the signo saldo pesos.
	 *
	 * @return the signoSaldoPesos
	 */
	public String getSignoSaldoPesos() {
		return signoSaldoPesos;
	}

	/**
	 * Setter para signo saldo pesos.
	 *
	 * @param signoSaldoPesos
	 *            the signoSaldoPesos to set
	 */
	public void setSignoSaldoPesos(String signoSaldoPesos) {
		this.signoSaldoPesos = signoSaldoPesos;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldoDolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Setter para saldo dolares.
	 *
	 * @param saldoDolares
	 *            the saldoDolares to set
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the signo saldo dolares.
	 *
	 * @return the signoSaldoDolares
	 */
	public String getSignoSaldoDolares() {
		return signoSaldoDolares;
	}

	/**
	 * Setter para signo saldo dolares.
	 *
	 * @param signoSaldoDolares
	 *            the signoSaldoDolares to set
	 */
	public void setSignoSaldoDolares(String signoSaldoDolares) {
		this.signoSaldoDolares = signoSaldoDolares;
	}

	/**
	 * Gets the saldo descubierto.
	 *
	 * @return the saldoDescubierto
	 */
	public String getSaldoDescubierto() {
		return saldoDescubierto;
	}

	/**
	 * Setter para saldo descubierto.
	 *
	 * @param saldoDescubierto
	 *            the saldoDescubierto to set
	 */
	public void setSaldoDescubierto(String saldoDescubierto) {
		this.saldoDescubierto = saldoDescubierto;
	}

	/**
	 * Gets the saldo cuenta corriente.
	 *
	 * @return the saldoCuentaCorriente
	 */
	public String getSaldoCuentaCorriente() {
		return saldoCuentaCorriente;
	}

	/**
	 * Setter para saldo cuenta corriente.
	 *
	 * @param saldoCuentaCorriente
	 *            the saldoCuentaCorriente to set
	 */
	public void setSaldoCuentaCorriente(String saldoCuentaCorriente) {
		this.saldoCuentaCorriente = saldoCuentaCorriente;
	}

	/**
	 * Gets the signo saldo cuenta corriente.
	 *
	 * @return the signoSaldoCuentaCorriente
	 */
	public String getSignoSaldoCuentaCorriente() {
		return signoSaldoCuentaCorriente;
	}

	/**
	 * Setter para signo saldo cuenta corriente.
	 *
	 * @param signoSaldoCuentaCorriente
	 *            the signoSaldoCuentaCorriente to set
	 */
	public void setSignoSaldoCuentaCorriente(String signoSaldoCuentaCorriente) {
		this.signoSaldoCuentaCorriente = signoSaldoCuentaCorriente;
	}

	/**
	 * Gets the saldo caja ahorro.
	 *
	 * @return the saldoCajaAhorro
	 */
	public String getSaldoCajaAhorro() {
		return saldoCajaAhorro;
	}

	/**
	 * Setter para saldo caja ahorro.
	 *
	 * @param saldoCajaAhorro
	 *            the saldoCajaAhorro to set
	 */
	public void setSaldoCajaAhorro(String saldoCajaAhorro) {
		this.saldoCajaAhorro = saldoCajaAhorro;
	}

	/**
	 * Gets the signo saldo caja ahorro.
	 *
	 * @return the signoSaldoCajaAhorro
	 */
	public String getSignoSaldoCajaAhorro() {
		return signoSaldoCajaAhorro;
	}

	/**
	 * Setter para signo saldo caja ahorro.
	 *
	 * @param signoSaldoCajaAhorro
	 *            the signoSaldoCajaAhorro to set
	 */
	public void setSignoSaldoCajaAhorro(String signoSaldoCajaAhorro) {
		this.signoSaldoCajaAhorro = signoSaldoCajaAhorro;
	}

	/**
	 * Gets the checks if is saldo pesos negativo.
	 *
	 * @return the isSaldoPesosNegativo
	 */
	public Boolean getIsSaldoPesosNegativo() {
		return isSaldoPesosNegativo;
	}

	/**
	 * Setter para checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the isSaldoPesosNegativo to set
	 */
	public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
		this.isSaldoPesosNegativo = isSaldoPesosNegativo;
	}

	/**
	 * Gets the checks if is saldo pesos cero.
	 *
	 * @return the isSaldoPesosCero
	 */
	public Boolean getIsSaldoPesosCero() {
		return isSaldoPesosCero;
	}

	/**
	 * Setter para checks if is saldo pesos cero.
	 *
	 * @param isSaldoPesosCero
	 *            the isSaldoPesosCero to set
	 */
	public void setIsSaldoPesosCero(Boolean isSaldoPesosCero) {
		this.isSaldoPesosCero = isSaldoPesosCero;
	}

	/**
	 * Gets the checks if is saldo dolares negativo.
	 *
	 * @return the isSaldoDolaresNegativo
	 */
	public Boolean getIsSaldoDolaresNegativo() {
		return isSaldoDolaresNegativo;
	}

	/**
	 * Setter para checks if is saldo dolares negativo.
	 *
	 * @param isSaldoDolaresNegativo
	 *            the isSaldoDolaresNegativo to set
	 */
	public void setIsSaldoDolaresNegativo(Boolean isSaldoDolaresNegativo) {
		this.isSaldoDolaresNegativo = isSaldoDolaresNegativo;
	}

	/**
	 * Gets the checks if is saldo dolares cero.
	 *
	 * @return the isSaldoDolaresCero
	 */
	public Boolean getIsSaldoDolaresCero() {
		return isSaldoDolaresCero;
	}

	/**
	 * Setter para checks if is saldo dolares cero.
	 *
	 * @param isSaldoDolaresCero
	 *            the isSaldoDolaresCero to set
	 */
	public void setIsSaldoDolaresCero(Boolean isSaldoDolaresCero) {
		this.isSaldoDolaresCero = isSaldoDolaresCero;
	}

	/**
	 * Gets the checks if is saldo caja ahorro negativo.
	 *
	 * @return the isSaldoCajaAhorroNegativo
	 */
	public Boolean getIsSaldoCajaAhorroNegativo() {
		return isSaldoCajaAhorroNegativo;
	}

	/**
	 * Setter para checks if is saldo caja ahorro negativo.
	 *
	 * @param isSaldoCajaAhorroNegativo
	 *            the isSaldoCajaAhorroNegativo to set
	 */
	public void setIsSaldoCajaAhorroNegativo(Boolean isSaldoCajaAhorroNegativo) {
		this.isSaldoCajaAhorroNegativo = isSaldoCajaAhorroNegativo;
	}

	/**
	 * Gets the checks if is saldo cuenta corriente negativo.
	 *
	 * @return the isSaldoCuentaCorrienteNegativo
	 */
	public Boolean getIsSaldoCuentaCorrienteNegativo() {
		return isSaldoCuentaCorrienteNegativo;
	}

	/**
	 * Setter para checks if is saldo cuenta corriente negativo.
	 *
	 * @param isSaldoCuentaCorrienteNegativo
	 *            the isSaldoCuentaCorrienteNegativo to set
	 */
	public void setIsSaldoCuentaCorrienteNegativo(Boolean isSaldoCuentaCorrienteNegativo) {
		this.isSaldoCuentaCorrienteNegativo = isSaldoCuentaCorrienteNegativo;
	}

	/**
	 * Gets the checks if is saldo descubierto negativo.
	 *
	 * @return the isSaldoDescubiertoNegativo
	 */
	public Boolean getIsSaldoDescubiertoNegativo() {
		return isSaldoDescubiertoNegativo;
	}

	/**
	 * Setter para checks if is saldo descubierto negativo.
	 *
	 * @param isSaldoDescubiertoNegativo
	 *            the isSaldoDescubiertoNegativo to set
	 */
	public void setIsSaldoDescubiertoNegativo(Boolean isSaldoDescubiertoNegativo) {
		this.isSaldoDescubiertoNegativo = isSaldoDescubiertoNegativo;
	}

	/**
	 * Gets the checks if is saldo descubierto cero.
	 *
	 * @return the isSaldoDescubiertoCero
	 */
	public Boolean getIsSaldoDescubiertoCero() {
		return isSaldoDescubiertoCero;
	}

	/**
	 * Setter para checks if is saldo descubierto cero.
	 *
	 * @param isSaldoDescubiertoCero
	 *            the isSaldoDescubiertoCero to set
	 */
	public void setIsSaldoDescubiertoCero(Boolean isSaldoDescubiertoCero) {
		this.isSaldoDescubiertoCero = isSaldoDescubiertoCero;
	}

	/**
	 * Gets the checks if is cerrada.
	 *
	 * @return the isCerrada
	 */
	public Boolean getIsCerrada() {
		return isCerrada;
	}

	/**
	 * Setter para checks if is cerrada.
	 *
	 * @param isCerrada
	 *            the isCerrada to set
	 */
	public void setIsCerrada(Boolean isCerrada) {
		this.isCerrada = isCerrada;
	}

	/**
	 * Gets the checks if is cuenta unica.
	 *
	 * @return the isCuentaUnica
	 */
	public Boolean getIsCuentaUnica() {
		return isCuentaUnica;
	}

	/**
	 * Setter para checks if is cuenta unica.
	 *
	 * @param isCuentaUnica
	 *            the isCuentaUnica to set
	 */
	public void setIsCuentaUnica(Boolean isCuentaUnica) {
		this.isCuentaUnica = isCuentaUnica;
	}

	/**
	 * Gets the checks if is saldo cuenta corriente cero.
	 *
	 * @return the isSaldoCuentaCorrienteCero
	 */
	public Boolean getIsSaldoCuentaCorrienteCero() {
		return isSaldoCuentaCorrienteCero;
	}

	/**
	 * Setter para checks if is saldo cuenta corriente cero.
	 *
	 * @param isSaldoCuentaCorrienteCero
	 *            the isSaldoCuentaCorrienteCero to set
	 */
	public void setIsSaldoCuentaCorrienteCero(Boolean isSaldoCuentaCorrienteCero) {
		this.isSaldoCuentaCorrienteCero = isSaldoCuentaCorrienteCero;
	}

	/**
	 * Gets the checks if is saldo caja ahorro cero.
	 *
	 * @return the isSaldoCajaAhorroCero
	 */
	public Boolean getIsSaldoCajaAhorroCero() {
		return isSaldoCajaAhorroCero;
	}

	/**
	 * Setter para checks if is saldo caja ahorro cero.
	 *
	 * @param isSaldoCajaAhorroCero
	 *            the isSaldoCajaAhorroCero to set
	 */
	public void setIsSaldoCajaAhorroCero(Boolean isSaldoCajaAhorroCero) {
		this.isSaldoCajaAhorroCero = isSaldoCajaAhorroCero;
	}

	/**
	 * Gets the checks if is traspaso automatico.
	 *
	 * @return the isTraspasoAutomatico
	 */
	public Boolean getIsTraspasoAutomatico() {
		return isTraspasoAutomatico;
	}

	/**
	 * Setter para checks if is traspaso automatico.
	 *
	 * @param isTraspasoAutomatico
	 *            the isTraspasoAutomatico to set
	 */
	public void setIsTraspasoAutomatico(Boolean isTraspasoAutomatico) {
		this.isTraspasoAutomatico = isTraspasoAutomatico;
	}

	/**
	 * Gets the checks if is cuenta sueldo.
	 *
	 * @return the isCuentaSueldo
	 */
	public Boolean getIsCuentaSueldo() {
		return isCuentaSueldo;
	}

	/**
	 * Setter para checks if is cuenta sueldo.
	 *
	 * @param isCuentaSueldo
	 *            the isCuentaSueldo to set
	 */
	public void setIsCuentaSueldo(Boolean isCuentaSueldo) {
		this.isCuentaSueldo = isCuentaSueldo;
	}

	/**
	 * Gets the url reporte cbu.
	 *
	 * @return the urlReporteCBU
	 */
	public String getUrlReporteCBU() {
		return urlReporteCBU;
	}

	/**
	 * Setter para url reporte cbu.
	 *
	 * @param urlReporteCBU
	 *            the urlReporteCBU to set
	 */
	public void setUrlReporteCBU(String urlReporteCBU) {
		this.urlReporteCBU = urlReporteCBU;
	}

	/**
	 * Gets the abreviatura tipo cuenta.
	 *
	 * @return the abreviatura tipo cuenta
	 */
	public String getAbreviaturaTipoCuenta() {
		return abreviaturaTipoCuenta;
	}

	/**
	 * Sets the abreviatura tipo cuenta.
	 *
	 * @param abreviaturaTipoCuenta
	 *            the new abreviatura tipo cuenta
	 */
	public void setAbreviaturaTipoCuenta(String abreviaturaTipoCuenta) {
		this.abreviaturaTipoCuenta = abreviaturaTipoCuenta;
	}

	/**
	 * Gets the fecha desde movimiento.
	 *
	 * @return the fecha desde movimiento
	 */
	public String getFechaDesdeMovimiento() {
		return fechaDesdeMovimiento;
	}

	/**
	 * Sets the fecha desde movimiento.
	 *
	 * @param fechaDesdeMovimiento
	 *            the new fecha desde movimiento
	 */
	public void setFechaDesdeMovimiento(String fechaDesdeMovimiento) {
		this.fechaDesdeMovimiento = fechaDesdeMovimiento;
	}

	/**
	 * Gets the fecha hasta movimiento.
	 *
	 * @return the fecha hasta movimiento
	 */
	public String getFechaHastaMovimiento() {
		return fechaHastaMovimiento;
	}

	/**
	 * Sets the fecha hasta movimiento.
	 *
	 * @param fechaHastaMovimiento
	 *            the new fecha hasta movimiento
	 */
	public void setFechaHastaMovimiento(String fechaHastaMovimiento) {
		this.fechaHastaMovimiento = fechaHastaMovimiento;
	}

	/**
	 * Gets the show solicitar traspaso.
	 *
	 * @return the showSolicitarTraspaso
	 */
	public Boolean getShowSolicitarTraspaso() {
		return showSolicitarTraspaso;
	}

	/**
	 * Sets the show solicitar traspaso.
	 *
	 * @param showSolicitarTraspaso
	 *            the showSolicitarTraspaso to set
	 */
	public void setShowSolicitarTraspaso(Boolean showSolicitarTraspaso) {
		this.showSolicitarTraspaso = showSolicitarTraspaso;
	}

	/**
	 * Gets the show realizar traspaso.
	 *
	 * @return the showRealizarTraspaso
	 */
	public Boolean getShowRealizarTraspaso() {
		return showRealizarTraspaso;
	}

	/**
	 * Sets the show realizar traspaso.
	 *
	 * @param showRealizarTraspaso
	 *            the showRealizarTraspaso to set
	 */
	public void setShowRealizarTraspaso(Boolean showRealizarTraspaso) {
		this.showRealizarTraspaso = showRealizarTraspaso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(abreviaturaTipoCuenta);
		hcb.append(alias);
		hcb.append(apellidoCliente);
		hcb.append(cbu);
		hcb.append(descripcionTipoCuenta);
		hcb.append(fechaDesdeMovimiento);
		hcb.append(fechaHastaMovimiento);
		hcb.append(isCerrada);
		hcb.append(isCuentaSueldo);
		hcb.append(isCuentaUnica);
		hcb.append(isDescubiertoUtilizado);
		hcb.append(isFavorito);
		hcb.append(isSaldoCajaAhorroCero);
		hcb.append(isSaldoCajaAhorroNegativo);
		hcb.append(isSaldoCuentaCorrienteCero);
		hcb.append(isSaldoCuentaCorrienteNegativo);
		hcb.append(isSaldoDescubiertoCero);
		hcb.append(isSaldoDescubiertoNegativo);
		hcb.append(isSaldoDolaresCero);
		hcb.append(isSaldoDolaresNegativo);
		hcb.append(isSaldoPesosCero);
		hcb.append(isSaldoPesosNegativo);
		hcb.append(isTraspasoAutomatico);
		hcb.append(nombreCliente);
		hcb.append(numero);
		hcb.append(numeroIdentificacion);
		hcb.append(saldoCajaAhorro);
		hcb.append(saldoCuentaCorriente);
		hcb.append(saldoDescubierto);
		hcb.append(saldoDolares);
		hcb.append(saldoPesos);
		hcb.append(showDescubierto);
		hcb.append(showSaldoDolares);
		hcb.append(showSaldoPesos);
		hcb.append(signoSaldoCajaAhorro);
		hcb.append(signoSaldoCuentaCorriente);
		hcb.append(signoSaldoDolares);
		hcb.append(signoSaldoPesos);
		hcb.append(tipoIdentificacion);
		hcb.append(urlReporteCBU);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public boolean equals(Object obj) {

		CuentasAdhesionDebitoView other = (CuentasAdhesionDebitoView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(abreviaturaTipoCuenta, other.getAbreviaturaTipoCuenta());
		eb.append(alias, other.getAlias());
		eb.append(apellidoCliente, other.getApellidoCliente());
		eb.append(cbu, other.getCbu());
		eb.append(descripcionTipoCuenta, other.getDescripcionTipoCuenta());
		eb.append(fechaDesdeMovimiento, other.getFechaDesdeMovimiento());
		eb.append(fechaHastaMovimiento, other.getFechaHastaMovimiento());
		eb.append(isCerrada, other.getIsCerrada());
		eb.append(isCuentaSueldo, other.getIsCuentaSueldo());
		eb.append(isCuentaUnica, other.getIsCuentaUnica());
		eb.append(isDescubiertoUtilizado, other.getIsDescubiertoUtilizado());
		eb.append(isFavorito, other.getIsFavorito());
		eb.append(isSaldoCajaAhorroCero, other.getIsSaldoCajaAhorroCero());
		eb.append(isSaldoCajaAhorroNegativo, other.getIsSaldoCajaAhorroNegativo());
		eb.append(isSaldoCuentaCorrienteCero, other.getIsSaldoCuentaCorrienteCero());
		eb.append(isSaldoCuentaCorrienteNegativo, other.getIsSaldoCuentaCorrienteNegativo());
		eb.append(isSaldoDescubiertoCero, other.getIsSaldoDescubiertoCero());
		eb.append(isSaldoDescubiertoNegativo, other.getIsSaldoDescubiertoNegativo());
		eb.append(isSaldoDolaresCero, other.getIsSaldoDolaresCero());
		eb.append(isSaldoDolaresNegativo, other.getIsSaldoDolaresNegativo());
		eb.append(isSaldoPesosCero, other.getIsSaldoPesosCero());
		eb.append(isSaldoPesosNegativo, other.getIsSaldoPesosNegativo());
		eb.append(isTraspasoAutomatico, other.getIsTraspasoAutomatico());
		eb.append(nombreCliente, other.getNombreCliente());
		eb.append(numero, other.getNumero());
		eb.append(numeroIdentificacion, other.getNumeroIdentificacion());
		eb.append(saldoCajaAhorro, other.getSaldoCajaAhorro());
		eb.append(saldoCuentaCorriente, other.getSaldoCuentaCorriente());
		eb.append(saldoDescubierto, other.getSaldoDescubierto());
		eb.append(saldoDolares, other.getSaldoDolares());
		eb.append(saldoPesos, other.getSaldoPesos());
		eb.append(showDescubierto, other.getShowDescubierto());
		eb.append(showSaldoPesos, other.getShowSaldoPesos());
		eb.append(signoSaldoCajaAhorro, other.getSignoSaldoCajaAhorro());
		eb.append(signoSaldoCuentaCorriente, other.getSignoSaldoCuentaCorriente());
		eb.append(signoSaldoDolares, other.getSignoSaldoDolares());
		eb.append(signoSaldoPesos, other.getSignoSaldoPesos());
		eb.append(tipoIdentificacion, other.getTipoIdentificacion());
		eb.append(urlReporteCBU, other.getUrlReporteCBU());
		eb.append(showSaldoPesos, other.getShowSaldoPesos());
		return eb.isEquals();
	}

	/**
	 * Getter de tiene alias.
	 * 
	 * @return the hasAlias
	 */
	public Boolean getHasAlias() {
		return hasAlias;
	}

	/**
	 * Setter de tiene alias.
	 * 
	 * @param hasAlias
	 *            the hasAlias to set
	 */
	public void setHasAlias(Boolean hasAlias) {
		this.hasAlias = hasAlias;
	}
	
	

	public Boolean getRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(Boolean repatriacion) {
		this.repatriacion = repatriacion;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CuentaView [descripcionTipoCuenta=" + descripcionTipoCuenta + ", abreviaturaTipoCuenta="
				+ abreviaturaTipoCuenta + ", numero=" + numero + ", cbu=" + cbu + ", saldoPesos=" + saldoPesos
				+ ", signoSaldoPesos=" + signoSaldoPesos + ", saldoDolares=" + saldoDolares + ", signoSaldoDolares="
				+ signoSaldoDolares + ", saldoDescubierto=" + saldoDescubierto + ", saldoCuentaCorriente="
				+ saldoCuentaCorriente + ", signoSaldoCuentaCorriente=" + signoSaldoCuentaCorriente
				+ ", saldoCajaAhorro=" + saldoCajaAhorro + ", signoSaldoCajaAhorro=" + signoSaldoCajaAhorro
				+ ", isSaldoPesosNegativo=" + isSaldoPesosNegativo + ", isSaldoPesosCero=" + isSaldoPesosCero
				+ ", isSaldoDolaresNegativo=" + isSaldoDolaresNegativo + ", isSaldoDolaresCero=" + isSaldoDolaresCero
				+ ", isSaldoCajaAhorroNegativo=" + isSaldoCajaAhorroNegativo + ", isSaldoCuentaCorrienteNegativo="
				+ isSaldoCuentaCorrienteNegativo + ", isSaldoDescubiertoNegativo=" + isSaldoDescubiertoNegativo
				+ ", isSaldoDescubiertoCero=" + isSaldoDescubiertoCero + ", isCerrada=" + isCerrada + ", isCuentaUnica="
				+ isCuentaUnica + ", isSaldoCuentaCorrienteCero=" + isSaldoCuentaCorrienteCero
				+ ", isSaldoCajaAhorroCero=" + isSaldoCajaAhorroCero + ", isTraspasoAutomatico=" + isTraspasoAutomatico
				+ ", isCuentaSueldo=" + isCuentaSueldo + ", urlReporteCBU=" + urlReporteCBU + ", nombreCliente="
				+ nombreCliente + ", apellidoCliente=" + apellidoCliente + ", tipoIdentificacion=" + tipoIdentificacion
				+ ", numeroIdentificacion=" + numeroIdentificacion + ", isDescubiertoUtilizado="
				+ isDescubiertoUtilizado + ", showDescubierto=" + showDescubierto + ", showSaldoPesos=" + showSaldoPesos
				+ ", showSaldoDolares=" + showSaldoDolares + ", alias=" + alias + ", isFavorito=" + isFavorito
				+ ", fechaDesdeMovimiento=" + fechaDesdeMovimiento + ", fechaHastaMovimiento=" + fechaHastaMovimiento
				+ ", repatriacion=" + repatriacion+"]";
	}

	/**
	 * Gets the alias cbu.
	 *
	 * @return the alias cbu
	 */
	public String getAliasCbu() {
		return aliasCbu;
	}

	/**
	 * Sets the alias cbu.
	 *
	 * @param aliasCbu
	 *            the new alias cbu
	 */
	public void setAliasCbu(String aliasCbu) {
		this.aliasCbu = aliasCbu;
	}

}
