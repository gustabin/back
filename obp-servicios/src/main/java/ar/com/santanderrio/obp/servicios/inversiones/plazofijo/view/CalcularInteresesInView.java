/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class CalcularInteresesInView.
 *
 * @author pablo.d.gargaglione
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcularInteresesInView {

	/** The numero cuenta debito. */
	@NotNull
	private String numeroCuentaDebito;

	/** The plazo. */
	@NotNull
	private String plazo;

	/** The importe plazo fijo. */
	@NotNull
	private BigDecimal importePlazoFijo;

	/** The divisa. */
	@NotNull
	private String divisa;

	/** The cantidad de dias. */
	@NotNull
	private String cantidadDeDias;

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numeroCuentaDebito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the numeroCuentaDebito to set
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the importe plazo fijo.
	 *
	 * @return the importePlazoFijo
	 */
	public BigDecimal getImportePlazoFijo() {
		return importePlazoFijo;
	}

	/**
	 * Sets the importe plazo fijo.
	 *
	 * @param importePlazoFijo
	 *            the importePlazoFijo to set
	 */
	public void setImportePlazoFijo(BigDecimal importePlazoFijo) {
		this.importePlazoFijo = importePlazoFijo;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the cantidad de dias.
	 *
	 * @return the cantidad de dias
	 */
	public String getCantidadDeDias() {
		return cantidadDeDias;
	}

	/**
	 * Sets the cantidad de dias.
	 *
	 * @param cantidadDeDias
	 *            the new cantidad de dias
	 */
	public void setCantidadDeDias(String cantidadDeDias) {
		this.cantidadDeDias = cantidadDeDias;
	}

}
