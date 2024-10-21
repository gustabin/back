/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * 
 * Objeto utilizado para retornar datos del DAO.
 */

@Record
public class ConsultaSaldoCtasConAperturaOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;
	
	
	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;
	
	
	/** The saldo. */
	@Field
	private String saldo;
	
	/** The saldo pendiente confirmacion. */
	@Field
	private String saldoPendienteConfirmacion;
	
	/** The deposito efectivo dia. */
	@Field
	private String depositoEfectivoDia;
	
	/** The dep 24. */
	@Field
	private String dep24;
	
	/** The dep 48. */
	@Field
	private String dep48;
	
	/** The dep 72. */
	@Field
	private String dep72;
	
	/** The limite credito. */
	@Field
	private String limiteCredito;
	
	/** The intereses. */
	@Field
	private String intereses;
	
	/** The cant rechazados. */
	@Field
	private String cantRechazados;
	
	/** The saldo dolares. */
	@Field
	private String saldoDolares;
	
	/** The saldo pend conf dolares. */
	@Field
	private String saldoPendConfDolares;
	
	/** The dep efect dia dolares. */
	@Field
	private String depEfectDiaDolares;
	
	/** The dep 24 dolares. */
	@Field
	private String dep24Dolares;
	
	/** The dep 48 dolares. */
	@Field
	private String dep48Dolares;
	
	/** The dep 72 dolares. */
	@Field
	private String dep72Dolares;
	
	/** The limite credito dolares. */
	@Field
	private String limiteCreditoDolares;
	
	/** The intereses dolares. */
	@Field
	private String interesesDolares;
	
	/** The cant rechazos dolares. */
	@Field
	private String cantRechazosDolares;
	
	/** The ind sobregreido. */
	@Field
	private String indSobregreido;
	
	/** The saldo dispo ACTE. */
	@Field
	private String saldoDispoACTE;
	
	/** The saldo dispo ACAH. */
	@Field
	private String saldoDispoACAH;
	
	/** The saldo dispo ACCD. */
	@Field
	private String saldoDispoACCD;
	
	/** The saldo dispo ACAD. */
	@Field
	private String saldoDispoACAD;
	
	/** The error en consulta. */
	private Boolean errorEnConsulta = false;

	
	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}
	
	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}


	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the saldo pendiente confirmacion.
	 *
	 * @return the saldoPendienteConfirmacion
	 */
	public String getSaldoPendienteConfirmacion() {
		return saldoPendienteConfirmacion;
	}

	/**
	 * Sets the saldo pendiente confirmacion.
	 *
	 * @param saldoPendienteConfirmacion
	 *            the saldoPendienteConfirmacion to set
	 */
	public void setSaldoPendienteConfirmacion(String saldoPendienteConfirmacion) {
		this.saldoPendienteConfirmacion = saldoPendienteConfirmacion;
	}

	/**
	 * Gets the deposito efectivo dia.
	 *
	 * @return the depositoEfectivoDia
	 */
	public String getDepositoEfectivoDia() {
		return depositoEfectivoDia;
	}

	/**
	 * Sets the deposito efectivo dia.
	 *
	 * @param depositoEfectivoDia
	 *            the depositoEfectivoDia to set
	 */
	public void setDepositoEfectivoDia(String depositoEfectivoDia) {
		this.depositoEfectivoDia = depositoEfectivoDia;
	}

	/**
	 * Gets the dep 24.
	 *
	 * @return the dep24
	 */
	public String getDep24() {
		return dep24;
	}

	/**
	 * Sets the dep 24.
	 *
	 * @param dep24
	 *            the dep24 to set
	 */
	public void setDep24(String dep24) {
		this.dep24 = dep24;
	}

	/**
	 * Gets the dep 48.
	 *
	 * @return the dep48
	 */
	public String getDep48() {
		return dep48;
	}

	/**
	 * Sets the dep 48.
	 *
	 * @param dep48
	 *            the dep48 to set
	 */
	public void setDep48(String dep48) {
		this.dep48 = dep48;
	}

	/**
	 * Gets the dep 72.
	 *
	 * @return the dep72
	 */
	public String getDep72() {
		return dep72;
	}

	/**
	 * Sets the dep 72.
	 *
	 * @param dep72
	 *            the dep72 to set
	 */
	public void setDep72(String dep72) {
		this.dep72 = dep72;
	}

	/**
	 * Gets the limite credito.
	 *
	 * @return the limiteCredito
	 */
	public String getLimiteCredito() {
		return limiteCredito;
	}

	/**
	 * Sets the limite credito.
	 *
	 * @param limiteCredito
	 *            the limiteCredito to set
	 */
	public void setLimiteCredito(String limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the intereses to set
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}


	/**
	 * Gets the cant rechazados.
	 *
	 * @return the cantRechazados
	 */
	public String getCantRechazados() {
		return cantRechazados;
	}

	/**
	 * Sets the cant rechazados.
	 *
	 * @param cantRechazados
	 *            the cantRechazados to set
	 */
	public void setCantRechazados(String cantRechazados) {
		this.cantRechazados = cantRechazados;
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
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the saldoDolares to set
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the saldo pend conf dolares.
	 *
	 * @return the saldoPendConfDolares
	 */
	public String getSaldoPendConfDolares() {
		return saldoPendConfDolares;
	}

	/**
	 * Sets the saldo pend conf dolares.
	 *
	 * @param saldoPendConfDolares
	 *            the saldoPendConfDolares to set
	 */
	public void setSaldoPendConfDolares(String saldoPendConfDolares) {
		this.saldoPendConfDolares = saldoPendConfDolares;
	}

	/**
	 * Gets the dep efect dia dolares.
	 *
	 * @return the depEfectDiaDolares
	 */
	public String getDepEfectDiaDolares() {
		return depEfectDiaDolares;
	}

	/**
	 * Sets the dep efect dia dolares.
	 *
	 * @param depEfectDiaDolares
	 *            the depEfectDiaDolares to set
	 */
	public void setDepEfectDiaDolares(String depEfectDiaDolares) {
		this.depEfectDiaDolares = depEfectDiaDolares;
	}

	/**
	 * Gets the dep 24 dolares.
	 *
	 * @return the dep24Dolares
	 */
	public String getDep24Dolares() {
		return dep24Dolares;
	}

	/**
	 * Sets the dep 24 dolares.
	 *
	 * @param dep24Dolares
	 *            the dep24Dolares to set
	 */
	public void setDep24Dolares(String dep24Dolares) {
		this.dep24Dolares = dep24Dolares;
	}

	/**
	 * Gets the dep 48 dolares.
	 *
	 * @return the dep48Dolares
	 */
	public String getDep48Dolares() {
		return dep48Dolares;
	}

	/**
	 * Sets the dep 48 dolares.
	 *
	 * @param dep48Dolares
	 *            the dep48Dolares to set
	 */
	public void setDep48Dolares(String dep48Dolares) {
		this.dep48Dolares = dep48Dolares;
	}

	/**
	 * Gets the dep 72 dolares.
	 *
	 * @return the dep72Dolares
	 */
	public String getDep72Dolares() {
		return dep72Dolares;
	}

	/**
	 * Sets the dep 72 dolares.
	 *
	 * @param dep72Dolares
	 *            the dep72Dolares to set
	 */
	public void setDep72Dolares(String dep72Dolares) {
		this.dep72Dolares = dep72Dolares;
	}

	/**
	 * Gets the limite credito dolares.
	 *
	 * @return the limiteCreditoDolares
	 */
	public String getLimiteCreditoDolares() {
		return limiteCreditoDolares;
	}

	/**
	 * Sets the limite credito dolares.
	 *
	 * @param limiteCreditoDolares
	 *            the limiteCreditoDolares to set
	 */
	public void setLimiteCreditoDolares(String limiteCreditoDolares) {
		this.limiteCreditoDolares = limiteCreditoDolares;
	}

	/**
	 * Gets the intereses dolares.
	 *
	 * @return the interesesDolares
	 */
	public String getInteresesDolares() {
		return interesesDolares;
	}

	/**
	 * Sets the intereses dolares.
	 *
	 * @param interesesDolares
	 *            the interesesDolares to set
	 */
	public void setInteresesDolares(String interesesDolares) {
		this.interesesDolares = interesesDolares;
	}

	/**
	 * Gets the cant rechazos dolares.
	 *
	 * @return the cantRechazosDolares
	 */
	public String getCantRechazosDolares() {
		return cantRechazosDolares;
	}

	/**
	 * Sets the cant rechazos dolares.
	 *
	 * @param cantRechazosDolares
	 *            the cantRechazosDolares to set
	 */
	public void setCantRechazosDolares(String cantRechazosDolares) {
		this.cantRechazosDolares = cantRechazosDolares;
	}

	/**
	 * Gets the ind sobregreido.
	 *
	 * @return the indSobregreido
	 */
	public String getIndSobregreido() {
		return indSobregreido;
	}

	/**
	 * Sets the ind sobregreido.
	 *
	 * @param indSobregreido
	 *            the indSobregreido to set
	 */
	public void setIndSobregreido(String indSobregreido) {
		this.indSobregreido = indSobregreido;
	}

	/**
	 * Gets the saldo dispo ACTE.
	 *
	 * @return the saldoDispoACTE
	 */
	public String getSaldoDispoACTE() {
		return saldoDispoACTE;
	}

	/**
	 * Sets the saldo dispo ACTE.
	 *
	 * @param saldoDispoACTE
	 *            the saldoDispoACTE to set
	 */
	public void setSaldoDispoACTE(String saldoDispoACTE) {
		this.saldoDispoACTE = saldoDispoACTE;
	}

	/**
	 * Gets the saldo dispo ACAH.
	 *
	 * @return the saldoDispoACAH
	 */
	public String getSaldoDispoACAH() {
		return saldoDispoACAH;
	}

	/**
	 * Sets the saldo dispo ACAH.
	 *
	 * @param saldoDispoACAH
	 *            the saldoDispoACAH to set
	 */
	public void setSaldoDispoACAH(String saldoDispoACAH) {
		this.saldoDispoACAH = saldoDispoACAH;
	}

	/**
	 * Gets the saldo dispo ACCD.
	 *
	 * @return the saldoDispoACCD
	 */
	public String getSaldoDispoACCD() {
		return saldoDispoACCD;
	}

	/**
	 * Sets the saldo dispo ACCD.
	 *
	 * @param saldoDispoACCD
	 *            the saldoDispoACCD to set
	 */
	public void setSaldoDispoACCD(String saldoDispoACCD) {
		this.saldoDispoACCD = saldoDispoACCD;
	}

	/**
	 * Gets the saldo dispo ACAD.
	 *
	 * @return the saldoDispoACAD
	 */
	public String getSaldoDispoACAD() {
		return saldoDispoACAD;
	}

	/**
	 * Sets the saldo dispo ACAD.
	 *
	 * @param saldoDispoACAD
	 *            the saldoDispoACAD to set
	 */
	public void setSaldoDispoACAD(String saldoDispoACAD) {
		this.saldoDispoACAD = saldoDispoACAD;
	}

	/**
	 * Gets the error en consulta.
	 *
	 * @return the error en consulta
	 */
	public Boolean getErrorEnConsulta() {
		return errorEnConsulta;
	}

	/**
	 * Sets the error en consulta.
	 *
	 * @param errorEnConsulta
	 *            the new error en consulta
	 */
	public void setErrorEnConsulta(Boolean errorEnConsulta) {
		this.errorEnConsulta = errorEnConsulta;
	}	
	
}