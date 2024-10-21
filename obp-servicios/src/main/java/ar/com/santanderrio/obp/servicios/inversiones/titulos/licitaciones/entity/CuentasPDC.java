/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CuentasPDC.
 */
public class CuentasPDC {
	
	/** The cuenta titulos. */
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The indicador PDC. */
	@JsonProperty("IndicadorPdc")
	private String indicadorPDC;
	
	/** The lista datos saldo. */
	@JsonProperty("DatosSaldos")
	private List<SaldosPDC> listaDatosSaldo = new ArrayList<SaldosPDC>();

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuentaTitulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the cuentaTitulos to set
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
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
	 * Gets the indicador PDC.
	 *
	 * @return the indicadorPDC
	 */
	public String getIndicadorPDC() {
		return indicadorPDC;
	}

	/**
	 * Sets the indicador PDC.
	 *
	 * @param indicadorPDC
	 *            the indicadorPDC to set
	 */
	public void setIndicadorPDC(String indicadorPDC) {
		this.indicadorPDC = indicadorPDC;
	}

	/**
	 * Gets the lista datos saldo.
	 *
	 * @return the listaDatosSaldo
	 */
	public List<SaldosPDC> getListaDatosSaldo() {
		return listaDatosSaldo;
	}

	/**
	 * Sets the lista datos saldo.
	 *
	 * @param listaDatosSaldo
	 *            the listaDatosSaldo to set
	 */
	public void setListaDatosSaldo(List<SaldosPDC> listaDatosSaldo) {
		this.listaDatosSaldo = listaDatosSaldo;
	}

}
