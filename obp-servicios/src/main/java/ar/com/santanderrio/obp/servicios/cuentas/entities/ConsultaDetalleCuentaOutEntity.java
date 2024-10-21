/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class DetalleCuenta.
 */
@Record
public class ConsultaDetalleCuentaOutEntity {

	/** The Constant TRASPASO_AUTOMATICO_ACTIVO. */
	private static final String TRASPASO_AUTOMATICO_ACTIVO = "A";

	/** The Constant MODO_NOCTURNO. */
	private static final Object MODO_NOCTURNO = "N";

	/** The Constant MODO_NOCTURNO. */
	private static final Object NO_ACTIVO = "N";

	/** The Constant STRING_ESPACIO. */
	private static final Object STRING_ESPACIO = " ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

//	/** The clase paquete. */
//	@Field
//	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
//	private String clasePaquete;
//
//	/** The tipo cuenta. */
//	@Field
//	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
//	private String tipoCuenta;
//
//	/** The sucursal cuenta. */
//	@Field
//	@Pattern(regexp = "^[a-zA-Z0-9]{3}|[ ]{3}$")
//	private String sucursalCuenta;
//
//	/** The nro cuenta. */
//	@Field
//	@Pattern(regexp = "^[a-zA-Z0-9]{7}|[ ]{7}$")
//	private String nroCuenta;
//
//	/** The nombre titular cuenta. */
//	@Field
//	@Pattern(regexp = "^[a-zA-Z0-9]{30}|[ ]{30}$")
//	private String nombreTitularCuenta;

	/** The tipo cobertura cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{1}|[ ]{1}$")
	private String tipoCoberturaCuenta;

	/** The tipo posicionamiento cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
	private String tipoPosicionamientoCuenta;

	/** The saldo cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoCuenta;

	/** The saldo impago. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoImpago;

	/** The saldo por conformar. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoPorConformar;

	/** The deposito efectivo. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositoEfectivo;

	/** The depositos 24 HSCC. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositos24HSCC;

	/** The depositos 48 HSCC. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositos48HSCC;

	/** The depositos 72 HSCC. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositos72HSCC;

	/** The intereses ganados CA. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String interesesGanadosCA;

	/** The limite acuerdo CC. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String limiteAcuerdoCC;

	/** The cheques rechazos. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{5}|[ ]{5}$")
	private String chequesRechazos;

	/** The fecha apertura. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{8}|[ ]{8}$")
	private String fechaApertura;

	/** The saldo cuenta USD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoCuentaUSD;

	/** The saldo impago USD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoImpagoUSD;

	/** The saldo por conformar USD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoPorConformarUSD;

	/** The depositos efectivo USD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositosEfectivoUSD;

	/** The depositos 24 HSCCUSD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositos24HSCCUSD;

	/** The depositos 48 HSCCUSD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositos48HSCCUSD;

	/** The depositos 72 HSCCUSD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String depositos72HSCCUSD;

	/** The intereses ganados CAUSD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String interesesGanadosCAUSD;

	/** The limite acuerdo CCUSD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String limiteAcuerdoCCUSD;

	/** The cheques rechazados USD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{5}|[ ]{5}$")
	private String chequesRechazadosUSD;

	/** The fecha apertura USD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{8}|[ ]{8}$")
	private String fechaAperturaUSD;

	/** The indicador direcciona CA peticion cambio. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{2}|[ ]{2}$")
	private String indicadorDireccionaCAPeticionCambio;

	/** The saldo ACTE. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoACTE;

	/** The saldo ACAH. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoACAH;

	/** The saldo ACCD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoACCD;

	/** The saldo ACAD. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String saldoACAD;

	/** The tope USD cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String topeUSDCuenta;

	/** The tope ARS cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String topeARSCuenta;

	/** The acumulado USD cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String acumuladoUSDCuenta;

	/** The acumulado ARS cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String acumuladoARSCuenta;

	/** The disponible USD cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String disponibleUSDCuenta;

	/** The disponible ARS cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String disponibleARSCuenta;

	/** The disponible USD menor cuenta conjunto. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String disponibleUSDMenorCuentaConjunto;

	/** The disponible ARS menor cuenta conjunto. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String disponibleARSMenorCuentaConjunto;

	/** The dispuesto USD cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String dispuestoUSDCuenta;

	/** The dispuesto ARS cuenta. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{15}|[ ]{15}$")
	private String dispuestoARSCuenta;

	/** The ind sobregiro. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{1}|[ ]{1}$")
	private String indSobregiro;

	/** The estado. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{1}|[ ]{1}$")
	private String estado;

	
	
//	/**
//	 * Gets the clase paquete.
//	 *
//	 * @return the clase paquete
//	 */
//	public String getClasePaquete() {
//		return clasePaquete;
//	}
//
//	/**
//	 * Sets the clase paquete.
//	 *
//	 * @param clasePaquete
//	 *            the new clase paquete
//	 */
//	public void setClasePaquete(String clasePaquete) {
//		this.clasePaquete = clasePaquete;
//	}
//
//	/**
//	 * Gets the tipo cuenta.
//	 *
//	 * @return the tipo cuenta
//	 */
//	public String getTipoCuenta() {
//		return tipoCuenta;
//	}
//
//	/**
//	 * Sets the tipo cuenta.
//	 *
//	 * @param tipoCuenta
//	 *            the new tipo cuenta
//	 */
//	public void setTipoCuenta(String tipoCuenta) {
//		this.tipoCuenta = tipoCuenta;
//	}
//
//	/**
//	 * Gets the sucursal cuenta.
//	 *
//	 * @return the sucursal cuenta
//	 */
//	public String getSucursalCuenta() {
//		return sucursalCuenta;
//	}
//
//	/**
//	 * Sets the sucursal cuenta.
//	 *
//	 * @param sucursalCuenta
//	 *            the new sucursal cuenta
//	 */
//	public void setSucursalCuenta(String sucursalCuenta) {
//		this.sucursalCuenta = sucursalCuenta;
//	}
//
//	/**
//	 * Gets the nro cuenta.
//	 *
//	 * @return the nro cuenta
//	 */
//	public String getNroCuenta() {
//		return nroCuenta;
//	}
//
//	/**
//	 * Sets the nro cuenta.
//	 *
//	 * @param nroCuenta
//	 *            the new nro cuenta
//	 */
//	public void setNroCuenta(String nroCuenta) {
//		this.nroCuenta = nroCuenta;
//	}
//
//	/**
//	 * Gets the nombre titular cuenta.
//	 *
//	 * @return the nombre titular cuenta
//	 */
//	public String getNombreTitularCuenta() {
//		return nombreTitularCuenta;
//	}
//
//	/**
//	 * Sets the nombre titular cuenta.
//	 *
//	 * @param nombreTitularCuenta
//	 *            the new nombre titular cuenta
//	 */
//	public void setNombreTitularCuenta(String nombreTitularCuenta) {
//		this.nombreTitularCuenta = nombreTitularCuenta;
//	}
	
	/**
	 * Gets the tipo cobertura cuenta.
	 *
	 * @return the tipo cobertura cuenta
	 */
	public String getTipoCoberturaCuenta() {
		return tipoCoberturaCuenta;
	}
	
	
	/**
	 * Gets the tipo codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}


	/**
	 * Sets the tipo codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}


	/**
	 * Sets the tipo cobertura cuenta.
	 *
	 * @param tipoCoberturaCuenta
	 *            the new tipo cobertura cuenta
	 */
	public void setTipoCoberturaCuenta(String tipoCoberturaCuenta) {
		this.tipoCoberturaCuenta = tipoCoberturaCuenta;
	}

	/**
	 * Gets the tipo posicionamiento cuenta.
	 *
	 * @return the tipo posicionamiento cuenta
	 */
	public String getTipoPosicionamientoCuenta() {
		return tipoPosicionamientoCuenta;
	}

	/**
	 * Sets the tipo posicionamiento cuenta.
	 *
	 * @param tipoPosicionamientoCuenta
	 *            the new tipo posicionamiento cuenta
	 */
	public void setTipoPosicionamientoCuenta(String tipoPosicionamientoCuenta) {
		this.tipoPosicionamientoCuenta = tipoPosicionamientoCuenta;
	}

	/**
	 * Gets the saldo cuenta.
	 *
	 * @return the saldo cuenta
	 */
	public String getSaldoCuenta() {
		return saldoCuenta;
	}

	/**
	 * Sets the saldo cuenta.
	 *
	 * @param saldoCuenta
	 *            the new saldo cuenta
	 */
	public void setSaldoCuenta(String saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	/**
	 * Gets the saldo impago.
	 *
	 * @return the saldo impago
	 */
	public String getSaldoImpago() {
		return saldoImpago;
	}

	/**
	 * Sets the saldo impago.
	 *
	 * @param saldoImpago
	 *            the new saldo impago
	 */
	public void setSaldoImpago(String saldoImpago) {
		this.saldoImpago = saldoImpago;
	}

	/**
	 * Gets the saldo por conformar.
	 *
	 * @return the saldo por conformar
	 */
	public String getSaldoPorConformar() {
		return saldoPorConformar;
	}

	/**
	 * Sets the saldo por conformar.
	 *
	 * @param saldoPorConformar
	 *            the new saldo por conformar
	 */
	public void setSaldoPorConformar(String saldoPorConformar) {
		this.saldoPorConformar = saldoPorConformar;
	}

	/**
	 * Gets the deposito efectivo.
	 *
	 * @return the deposito efectivo
	 */
	public String getDepositoEfectivo() {
		return depositoEfectivo;
	}

	/**
	 * Sets the deposito efectivo.
	 *
	 * @param depositoEfectivo
	 *            the new deposito efectivo
	 */
	public void setDepositoEfectivo(String depositoEfectivo) {
		this.depositoEfectivo = depositoEfectivo;
	}

	/**
	 * Gets the depositos 24 HSCC.
	 *
	 * @return the depositos 24 HSCC
	 */
	public String getDepositos24HSCC() {
		return depositos24HSCC;
	}

	/**
	 * Sets the depositos 24 HSCC.
	 *
	 * @param depositos24hscc
	 *            the new depositos 24 HSCC
	 */
	public void setDepositos24HSCC(String depositos24hscc) {
		depositos24HSCC = depositos24hscc;
	}

	/**
	 * Gets the depositos 48 HSCC.
	 *
	 * @return the depositos 48 HSCC
	 */
	public String getDepositos48HSCC() {
		return depositos48HSCC;
	}

	/**
	 * Sets the depositos 48 HSCC.
	 *
	 * @param depositos48hscc
	 *            the new depositos 48 HSCC
	 */
	public void setDepositos48HSCC(String depositos48hscc) {
		depositos48HSCC = depositos48hscc;
	}

	/**
	 * Gets the depositos 72 HSCC.
	 *
	 * @return the depositos 72 HSCC
	 */
	public String getDepositos72HSCC() {
		return depositos72HSCC;
	}

	/**
	 * Sets the depositos 72 HSCC.
	 *
	 * @param depositos72hscc
	 *            the new depositos 72 HSCC
	 */
	public void setDepositos72HSCC(String depositos72hscc) {
		depositos72HSCC = depositos72hscc;
	}

	/**
	 * Gets the intereses ganados CA.
	 *
	 * @return the intereses ganados CA
	 */
	public String getInteresesGanadosCA() {
		return interesesGanadosCA;
	}

	/**
	 * Sets the intereses ganados CA.
	 *
	 * @param interesesGanadosCA
	 *            the new intereses ganados CA
	 */
	public void setInteresesGanadosCA(String interesesGanadosCA) {
		this.interesesGanadosCA = interesesGanadosCA;
	}

	/**
	 * Gets the limite acuerdo CC.
	 *
	 * @return the limite acuerdo CC
	 */
	public String getLimiteAcuerdoCC() {
		return limiteAcuerdoCC;
	}

	/**
	 * Sets the limite acuerdo CC.
	 *
	 * @param limiteAcuerdoCC
	 *            the new limite acuerdo CC
	 */
	public void setLimiteAcuerdoCC(String limiteAcuerdoCC) {
		this.limiteAcuerdoCC = limiteAcuerdoCC;
	}

	/**
	 * Gets the cheques rechazos.
	 *
	 * @return the cheques rechazos
	 */
	public String getChequesRechazos() {
		return chequesRechazos;
	}

	/**
	 * Sets the cheques rechazos.
	 *
	 * @param chequesRechazos
	 *            the new cheques rechazos
	 */
	public void setChequesRechazos(String chequesRechazos) {
		this.chequesRechazos = chequesRechazos;
	}

	/**
	 * Gets the fecha apertura.
	 *
	 * @return the fecha apertura
	 */
	public String getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * Sets the fecha apertura.
	 *
	 * @param fechaApertura
	 *            the new fecha apertura
	 */
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	/**
	 * Gets the saldo cuenta USD.
	 *
	 * @return the saldo cuenta USD
	 */
	public String getSaldoCuentaUSD() {
		return saldoCuentaUSD;
	}

	/**
	 * Sets the saldo cuenta USD.
	 *
	 * @param saldoCuentaUSD
	 *            the new saldo cuenta USD
	 */
	public void setSaldoCuentaUSD(String saldoCuentaUSD) {
		this.saldoCuentaUSD = saldoCuentaUSD;
	}

	/**
	 * Gets the saldo impago USD.
	 *
	 * @return the saldo impago USD
	 */
	public String getSaldoImpagoUSD() {
		return saldoImpagoUSD;
	}

	/**
	 * Sets the saldo impago USD.
	 *
	 * @param saldoImpagoUSD
	 *            the new saldo impago USD
	 */
	public void setSaldoImpagoUSD(String saldoImpagoUSD) {
		this.saldoImpagoUSD = saldoImpagoUSD;
	}

	/**
	 * Gets the saldo por conformar USD.
	 *
	 * @return the saldo por conformar USD
	 */
	public String getSaldoPorConformarUSD() {
		return saldoPorConformarUSD;
	}

	/**
	 * Sets the saldo por conformar USD.
	 *
	 * @param saldoPorConformarUSD
	 *            the new saldo por conformar USD
	 */
	public void setSaldoPorConformarUSD(String saldoPorConformarUSD) {
		this.saldoPorConformarUSD = saldoPorConformarUSD;
	}

	/**
	 * Gets the depositos efectivo USD.
	 *
	 * @return the depositos efectivo USD
	 */
	public String getDepositosEfectivoUSD() {
		return depositosEfectivoUSD;
	}

	/**
	 * Sets the depositos efectivo USD.
	 *
	 * @param depositosEfectivoUSD
	 *            the new depositos efectivo USD
	 */
	public void setDepositosEfectivoUSD(String depositosEfectivoUSD) {
		this.depositosEfectivoUSD = depositosEfectivoUSD;
	}

	/**
	 * Gets the depositos 24 HSCCUSD.
	 *
	 * @return the depositos 24 HSCCUSD
	 */
	public String getDepositos24HSCCUSD() {
		return depositos24HSCCUSD;
	}

	/**
	 * Sets the depositos 24 HSCCUSD.
	 *
	 * @param depositos24hsccusd
	 *            the new depositos 24 HSCCUSD
	 */
	public void setDepositos24HSCCUSD(String depositos24hsccusd) {
		depositos24HSCCUSD = depositos24hsccusd;
	}

	/**
	 * Gets the depositos 48 HSCCUSD.
	 *
	 * @return the depositos 48 HSCCUSD
	 */
	public String getDepositos48HSCCUSD() {
		return depositos48HSCCUSD;
	}

	/**
	 * Sets the depositos 48 HSCCUSD.
	 *
	 * @param depositos48hsccusd
	 *            the new depositos 48 HSCCUSD
	 */
	public void setDepositos48HSCCUSD(String depositos48hsccusd) {
		depositos48HSCCUSD = depositos48hsccusd;
	}

	/**
	 * Gets the depositos 72 HSCCUSD.
	 *
	 * @return the depositos 72 HSCCUSD
	 */
	public String getDepositos72HSCCUSD() {
		return depositos72HSCCUSD;
	}

	/**
	 * Sets the depositos 72 HSCCUSD.
	 *
	 * @param depositos72hsccusd
	 *            the new depositos 72 HSCCUSD
	 */
	public void setDepositos72HSCCUSD(String depositos72hsccusd) {
		depositos72HSCCUSD = depositos72hsccusd;
	}

	/**
	 * Gets the intereses ganados CAUSD.
	 *
	 * @return the intereses ganados CAUSD
	 */
	public String getInteresesGanadosCAUSD() {
		return interesesGanadosCAUSD;
	}

	/**
	 * Sets the intereses ganados CAUSD.
	 *
	 * @param interesesGanadosCAUSD
	 *            the new intereses ganados CAUSD
	 */
	public void setInteresesGanadosCAUSD(String interesesGanadosCAUSD) {
		this.interesesGanadosCAUSD = interesesGanadosCAUSD;
	}

	/**
	 * Gets the limite acuerdo CCUSD.
	 *
	 * @return the limite acuerdo CCUSD
	 */
	public String getLimiteAcuerdoCCUSD() {
		return limiteAcuerdoCCUSD;
	}

	/**
	 * Sets the limite acuerdo CCUSD.
	 *
	 * @param limiteAcuerdoCCUSD
	 *            the new limite acuerdo CCUSD
	 */
	public void setLimiteAcuerdoCCUSD(String limiteAcuerdoCCUSD) {
		this.limiteAcuerdoCCUSD = limiteAcuerdoCCUSD;
	}

	/**
	 * Gets the cheques rechazados USD.
	 *
	 * @return the cheques rechazados USD
	 */
	public String getChequesRechazadosUSD() {
		return chequesRechazadosUSD;
	}

	/**
	 * Sets the cheques rechazados USD.
	 *
	 * @param chequesRechazadosUSD
	 *            the new cheques rechazados USD
	 */
	public void setChequesRechazadosUSD(String chequesRechazadosUSD) {
		this.chequesRechazadosUSD = chequesRechazadosUSD;
	}

	/**
	 * Gets the fecha apertura USD.
	 *
	 * @return the fecha apertura USD
	 */
	public String getFechaAperturaUSD() {
		return fechaAperturaUSD;
	}

	/**
	 * Sets the fecha apertura USD.
	 *
	 * @param fechaAperturaUSD
	 *            the new fecha apertura USD
	 */
	public void setFechaAperturaUSD(String fechaAperturaUSD) {
		this.fechaAperturaUSD = fechaAperturaUSD;
	}

	/**
	 * Gets the indicador direcciona CA peticion cambio.
	 *
	 * @return the indicador direcciona CA peticion cambio
	 */
	public String getIndicadorDireccionaCAPeticionCambio() {
		return indicadorDireccionaCAPeticionCambio;
	}

	/**
	 * Sets the indicador direcciona CA peticion cambio.
	 *
	 * @param indicadorDireccionaCAPeticionCambio
	 *            the new indicador direcciona CA peticion cambio
	 */
	public void setIndicadorDireccionaCAPeticionCambio(String indicadorDireccionaCAPeticionCambio) {
		this.indicadorDireccionaCAPeticionCambio = indicadorDireccionaCAPeticionCambio;
	}

	/**
	 * Gets the saldo ACTE.
	 *
	 * @return the saldo ACTE
	 */
	public String getSaldoACTE() {
		return saldoACTE;
	}

	/**
	 * Sets the saldo ACTE.
	 *
	 * @param saldoACTE
	 *            the new saldo ACTE
	 */
	public void setSaldoACTE(String saldoACTE) {
		this.saldoACTE = saldoACTE;
	}

	/**
	 * Gets the saldo ACAH.
	 *
	 * @return the saldo ACAH
	 */
	public String getSaldoACAH() {
		return saldoACAH;
	}

	/**
	 * Sets the saldo ACAH.
	 *
	 * @param saldoACAH
	 *            the new saldo ACAH
	 */
	public void setSaldoACAH(String saldoACAH) {
		this.saldoACAH = saldoACAH;
	}

	/**
	 * Gets the saldo ACCD.
	 *
	 * @return the saldo ACCD
	 */
	public String getSaldoACCD() {
		return saldoACCD;
	}

	/**
	 * Sets the saldo ACCD.
	 *
	 * @param saldoACCD
	 *            the new saldo ACCD
	 */
	public void setSaldoACCD(String saldoACCD) {
		this.saldoACCD = saldoACCD;
	}

	/**
	 * Gets the saldo ACAD.
	 *
	 * @return the saldo ACAD
	 */
	public String getSaldoACAD() {
		return saldoACAD;
	}

	/**
	 * Sets the saldo ACAD.
	 *
	 * @param saldoACAD
	 *            the new saldo ACAD
	 */
	public void setSaldoACAD(String saldoACAD) {
		this.saldoACAD = saldoACAD;
	}

	/**
	 * Gets the tope USD cuenta.
	 *
	 * @return the tope USD cuenta
	 */
	public String getTopeUSDCuenta() {
		return topeUSDCuenta;
	}

	/**
	 * Sets the tope USD cuenta.
	 *
	 * @param topeUSDCuenta
	 *            the new tope USD cuenta
	 */
	public void setTopeUSDCuenta(String topeUSDCuenta) {
		this.topeUSDCuenta = topeUSDCuenta;
	}

	/**
	 * Gets the tope ARS cuenta.
	 *
	 * @return the tope ARS cuenta
	 */
	public String getTopeARSCuenta() {
		return topeARSCuenta;
	}

	/**
	 * Sets the tope ARS cuenta.
	 *
	 * @param topeARSCuenta
	 *            the new tope ARS cuenta
	 */
	public void setTopeARSCuenta(String topeARSCuenta) {
		this.topeARSCuenta = topeARSCuenta;
	}

	/**
	 * Gets the acumulado USD cuenta.
	 *
	 * @return the acumulado USD cuenta
	 */
	public String getAcumuladoUSDCuenta() {
		return acumuladoUSDCuenta;
	}

	/**
	 * Sets the acumulado USD cuenta.
	 *
	 * @param acumuladoUSDCuenta
	 *            the new acumulado USD cuenta
	 */
	public void setAcumuladoUSDCuenta(String acumuladoUSDCuenta) {
		this.acumuladoUSDCuenta = acumuladoUSDCuenta;
	}

	/**
	 * Gets the acumulado ARS cuenta.
	 *
	 * @return the acumulado ARS cuenta
	 */
	public String getAcumuladoARSCuenta() {
		return acumuladoARSCuenta;
	}

	/**
	 * Sets the acumulado ARS cuenta.
	 *
	 * @param acumuladoARSCuenta
	 *            the new acumulado ARS cuenta
	 */
	public void setAcumuladoARSCuenta(String acumuladoARSCuenta) {
		this.acumuladoARSCuenta = acumuladoARSCuenta;
	}

	/**
	 * Gets the disponible USD cuenta.
	 *
	 * @return the disponible USD cuenta
	 */
	public String getDisponibleUSDCuenta() {
		return disponibleUSDCuenta;
	}

	/**
	 * Sets the disponible USD cuenta.
	 *
	 * @param disponibleUSDCuenta
	 *            the new disponible USD cuenta
	 */
	public void setDisponibleUSDCuenta(String disponibleUSDCuenta) {
		this.disponibleUSDCuenta = disponibleUSDCuenta;
	}

	/**
	 * Gets the disponible ARS cuenta.
	 *
	 * @return the disponible ARS cuenta
	 */
	public String getDisponibleARSCuenta() {
		return disponibleARSCuenta;
	}

	/**
	 * Sets the disponible ARS cuenta.
	 *
	 * @param disponibleARSCuenta
	 *            the new disponible ARS cuenta
	 */
	public void setDisponibleARSCuenta(String disponibleARSCuenta) {
		this.disponibleARSCuenta = disponibleARSCuenta;
	}

	/**
	 * Gets the disponible USD menor cuenta conjunto.
	 *
	 * @return the disponible USD menor cuenta conjunto
	 */
	public String getDisponibleUSDMenorCuentaConjunto() {
		return disponibleUSDMenorCuentaConjunto;
	}

	/**
	 * Sets the disponible USD menor cuenta conjunto.
	 *
	 * @param disponibleUSDMenorCuentaConjunto
	 *            the new disponible USD menor cuenta conjunto
	 */
	public void setDisponibleUSDMenorCuentaConjunto(String disponibleUSDMenorCuentaConjunto) {
		this.disponibleUSDMenorCuentaConjunto = disponibleUSDMenorCuentaConjunto;
	}

	/**
	 * Gets the disponible ARS menor cuenta conjunto.
	 *
	 * @return the disponible ARS menor cuenta conjunto
	 */
	public String getDisponibleARSMenorCuentaConjunto() {
		return disponibleARSMenorCuentaConjunto;
	}

	/**
	 * Sets the disponible ARS menor cuenta conjunto.
	 *
	 * @param disponibleARSMenorCuentaConjunto
	 *            the new disponible ARS menor cuenta conjunto
	 */
	public void setDisponibleARSMenorCuentaConjunto(String disponibleARSMenorCuentaConjunto) {
		this.disponibleARSMenorCuentaConjunto = disponibleARSMenorCuentaConjunto;
	}

	/**
	 * Gets the dispuesto USD cuenta.
	 *
	 * @return the dispuesto USD cuenta
	 */
	public String getDispuestoUSDCuenta() {
		return dispuestoUSDCuenta;
	}

	/**
	 * Sets the dispuesto USD cuenta.
	 *
	 * @param dispuestoUSDCuenta
	 *            the new dispuesto USD cuenta
	 */
	public void setDispuestoUSDCuenta(String dispuestoUSDCuenta) {
		this.dispuestoUSDCuenta = dispuestoUSDCuenta;
	}

	/**
	 * Gets the dispuesto ARS cuenta.
	 *
	 * @return the dispuesto ARS cuenta
	 */
	public String getDispuestoARSCuenta() {
		return dispuestoARSCuenta;
	}

	/**
	 * Sets the dispuesto ARS cuenta.
	 *
	 * @param dispuestoARSCuenta
	 *            the new dispuesto ARS cuenta
	 */
	public void setDispuestoARSCuenta(String dispuestoARSCuenta) {
		this.dispuestoARSCuenta = dispuestoARSCuenta;
	}

	/**
	 * Gets the ind sobregiro.
	 *
	 * @return the ind sobregiro
	 */
	public String getIndSobregiro() {
		return indSobregiro;
	}

	/**
	 * Sets the ind sobregiro.
	 *
	 * @param indSobregiro
	 *            the new ind sobregiro
	 */
	public void setIndSobregiro(String indSobregiro) {
		this.indSobregiro = indSobregiro;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Tiene solicitud traspaso automatico pendiente. Verificacion indicadores
	 * traspaso automatico
	 * 
	 * @return the boolean
	 */
	public Boolean tieneSolicitudTraspasoAutomaticoPendiente() {

		if (indicadoresTraspasoValidos()) {

			return (TRASPASO_AUTOMATICO_ACTIVO.equals(getIndicador(0)) && NO_ACTIVO.equals(getIndicador(1)))
					|| (NO_ACTIVO.equals(getIndicador(0)) && TRASPASO_AUTOMATICO_ACTIVO.equals(getIndicador(1)));
		}
		return Boolean.FALSE;
	}

	/**
	 * Puede realizar solicitud de traspaso automatico. Valida indicadores de
	 * traspaso y modo nocturno
	 * 
	 * @return the boolean
	 */
	public Boolean puedeRealizarSolicitudTraspasoAutomatico() {

		if (indicadoresTraspasoValidos()) {

			return !STRING_ESPACIO.equals(getIndicador(0)) && STRING_ESPACIO.equals(getIndicador(0))
					&& !isModoNocturno();
		}
		return Boolean.FALSE;
	}

	/**
	 * Indicadores traspaso validos.
	 *
	 * @return true, if successful
	 */
	private boolean indicadoresTraspasoValidos() {
		return !StringUtils.isEmpty(getIndicadorDireccionaCAPeticionCambio())
				&& getIndicadorDireccionaCAPeticionCambio().length() == 2;
	}

	/**
	 * Gets the indicador. Obtener indicador por indice
	 * 
	 * @param i
	 *            the i
	 * @return the indicador
	 */
	private String getIndicador(int i) {

		return getIndicadorDireccionaCAPeticionCambio().substring(i, i + 1);

	}

	/**
	 * Checks if is modo nocturno.
	 *
	 * @return the boolean
	 */
	public Boolean isModoNocturno() {

		return MODO_NOCTURNO.equals(getEstado());
	}

	/**
	 * Gets the traspaso automatico activo.
	 *
	 * @return the traspaso automatico activo
	 */
	public Boolean getTraspasoAutomaticoActivo() {
		if (indicadoresTraspasoValidos()) {
			return TRASPASO_AUTOMATICO_ACTIVO.equals(getIndicador(0));
		}
		return Boolean.FALSE;
	}

	/**
	 * Gets the solicitud pendiente traspaso automatico.
	 *
	 * @return the solicitud pendiente traspaso automatico
	 */
	public Boolean getSolicitudPendienteTraspasoAutomatico() {
		if (indicadoresTraspasoValidos()) {
			return TRASPASO_AUTOMATICO_ACTIVO.equals(getIndicador(1)) || NO_ACTIVO.equals(getIndicador(1));
		}
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaDetalleCuentaOutEntity [codigoRetornoExtendido=" + codigoRetornoExtendido 
				+ ", clasePaquete="
//				+ clasePaquete + ", tipoCuenta=" + tipoCuenta + ", sucursalCuenta=" + sucursalCuenta + ", nroCuenta="
//				+ nroCuenta + ", nombreTitularCuenta=" + nombreTitularCuenta 
				+ ", tipoCoberturaCuenta="
				+ tipoCoberturaCuenta + ", tipoPosicionamientoCuenta=" + tipoPosicionamientoCuenta + ", saldoCuenta="
				+ saldoCuenta + ", saldoImpago=" + saldoImpago + ", saldoPorConformar=" + saldoPorConformar
				+ ", depositoEfectivo=" + depositoEfectivo + ", depositos24HSCC=" + depositos24HSCC
				+ ", depositos48HSCC=" + depositos48HSCC + ", depositos72HSCC=" + depositos72HSCC
				+ ", interesesGanadosCA=" + interesesGanadosCA + ", limiteAcuerdoCC=" + limiteAcuerdoCC
				+ ", chequesRechazos=" + chequesRechazos + ", fechaApertura=" + fechaApertura + ", saldoCuentaUSD="
				+ saldoCuentaUSD + ", saldoImpagoUSD=" + saldoImpagoUSD + ", saldoPorConformarUSD="
				+ saldoPorConformarUSD + ", depositosEfectivoUSD=" + depositosEfectivoUSD + ", depositos24HSCCUSD="
				+ depositos24HSCCUSD + ", depositos48HSCCUSD=" + depositos48HSCCUSD + ", depositos72HSCCUSD="
				+ depositos72HSCCUSD + ", interesesGanadosCAUSD=" + interesesGanadosCAUSD + ", limiteAcuerdoCCUSD="
				+ limiteAcuerdoCCUSD + ", chequesRechazadosUSD=" + chequesRechazadosUSD + ", fechaAperturaUSD="
				+ fechaAperturaUSD + ", indicadorDireccionaCAPeticionCambio=" + indicadorDireccionaCAPeticionCambio
				+ ", saldoACTE=" + saldoACTE + ", saldoACAH=" + saldoACAH + ", saldoACCD=" + saldoACCD + ", saldoACAD="
				+ saldoACAD + ", topeUSDCuenta=" + topeUSDCuenta + ", topeARSCuenta=" + topeARSCuenta
				+ ", acumuladoUSDCuenta=" + acumuladoUSDCuenta + ", acumuladoARSCuenta=" + acumuladoARSCuenta
				+ ", disponibleUSDCuenta=" + disponibleUSDCuenta + ", disponibleARSCuenta=" + disponibleARSCuenta
				+ ", disponibleUSDMenorCuentaConjunto=" + disponibleUSDMenorCuentaConjunto
				+ ", disponibleARSMenorCuentaConjunto=" + disponibleARSMenorCuentaConjunto + ", dispuestoUSDCuenta="
				+ dispuestoUSDCuenta + ", dispuestoARSCuenta=" + dispuestoARSCuenta + 
				", indSobregiro=" + indSobregiro + ", estado=" + estado + "]";
	}

}
