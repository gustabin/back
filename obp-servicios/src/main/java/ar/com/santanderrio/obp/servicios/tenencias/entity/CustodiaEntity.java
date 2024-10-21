/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CustodiaEntity.
 *
 * @author desa
 */
public class CustodiaEntity {

	/** The anio. */
	@JsonProperty("Anio")
	private String anio;

	/** The espe tipo. */
	@JsonProperty("TipoEspecie")
	private String espeTipo;

	/** The espe nom. */
	@JsonProperty("DescripcionEspecie")
	private String espeNom;

	/** The espe cod. */
	@JsonProperty("CodEspecie")
	private String espeCod;

	/** The cuenta. */
	@JsonProperty("CuentaTitulos")
	private String cuenta;

	/** The divisa. */
	@JsonProperty("MonedaEmision")
	private String divisa;

	/** The valor nominal. */
	@JsonProperty("ValorNominal")
	private String valorNominal;

	/** The porc residual. */
	@JsonProperty("PorcentajeResidual")
	private String porcResidual;

	/** The precio. */
	@JsonProperty("Precio")
	private String precio;

	/** The coti afip. */
	@JsonProperty("CotizacionAFIP")
	private String cotiAfip;

	/** The importe. */
	@JsonProperty("Importe")
	private String importe;

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the espe tipo.
	 *
	 * @return the espeTipo
	 */
	public String getEspeTipo() {
		return espeTipo;
	}

	/**
	 * Sets the espe tipo.
	 *
	 * @param espeTipo
	 *            the espeTipo to set
	 */
	public void setEspeTipo(String espeTipo) {
		this.espeTipo = espeTipo;
	}

	/**
	 * Gets the espe nom.
	 *
	 * @return the espeNom
	 */
	public String getEspeNom() {
		return espeNom;
	}

	/**
	 * Sets the espe nom.
	 *
	 * @param espeNom
	 *            the espeNom to set
	 */
	public void setEspeNom(String espeNom) {
		this.espeNom = espeNom;
	}

	/**
	 * Gets the espe cod.
	 *
	 * @return the espeCod
	 */
	public String getEspeCod() {
		return espeCod;
	}

	/**
	 * Sets the espe cod.
	 *
	 * @param espeCod
	 *            the espeCod to set
	 */
	public void setEspeCod(String espeCod) {
		this.espeCod = espeCod;
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
	 * Gets the valor nominal.
	 *
	 * @return the valorNominal
	 */
	public String getValorNominal() {
		return valorNominal;
	}

	/**
	 * Sets the valor nominal.
	 *
	 * @param valorNominal
	 *            the valorNominal to set
	 */
	public void setValorNominal(String valorNominal) {
		this.valorNominal = valorNominal;
	}

	/**
	 * Gets the porc residual.
	 *
	 * @return the porcResidual
	 */
	public String getPorcResidual() {
		return porcResidual;
	}

	/**
	 * Sets the porc residual.
	 *
	 * @param porcResidual
	 *            the porcResidual to set
	 */
	public void setPorcResidual(String porcResidual) {
		this.porcResidual = porcResidual;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * Gets the coti afip.
	 *
	 * @return the cotiAfip
	 */
	public String getCotiAfip() {
		return cotiAfip;
	}

	/**
	 * Sets the coti afip.
	 *
	 * @param cotiAfip
	 *            the cotiAfip to set
	 */
	public void setCotiAfip(String cotiAfip) {
		this.cotiAfip = cotiAfip;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustodiaEntity [anio=" + anio + ", espeTipo=" + espeTipo + ", espeNom=" + espeNom + ", espeCod="
				+ espeCod + ", cuenta=" + cuenta + ", divisa=" + divisa + ", valorNominal=" + valorNominal
				+ ", porcResidual=" + porcResidual + ", precio=" + precio + ", cotiAfip=" + cotiAfip + ", importe="
				+ importe + "]";
	}

}
