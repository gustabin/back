/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class PlazoFijoEntity.
 *
 * @author desa
 */
public class PlazoFijoEntity {

	/** The anio. */
	@JsonProperty("Anio")
	private String anio;

	/** The divisa. */
	@JsonProperty("Divisa")
	private String divisa;

	/** The pecodofi. */
	@JsonProperty("CodigoFondo")
	private String pecodofi;

	/** The penumcon. */
	@JsonProperty("NumeroComprobante")
	private String penumcon;

	/** The tipo. */
	@JsonProperty("Tipo")
	private String tipo;

	/** The orden. */
	@JsonProperty("Orden")
	private String orden;

	/** The concepto. */
	@JsonProperty("Concepto")
	private String concepto;

	/** The importe. */
	@JsonProperty("Importe")
	private String importe;

	/** The importe pesos. */
	@JsonProperty("ImportePesos")
	private String importePesos;

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
	 * Gets the pecodofi.
	 *
	 * @return the pecodofi
	 */
	public String getPecodofi() {
		return pecodofi;
	}

	/**
	 * Sets the pecodofi.
	 *
	 * @param pecodofi
	 *            the pecodofi to set
	 */
	public void setPecodofi(String pecodofi) {
		this.pecodofi = pecodofi;
	}

	/**
	 * Gets the penumcon.
	 *
	 * @return the penumcon
	 */
	public String getPenumcon() {
		return penumcon;
	}

	/**
	 * Sets the penumcon.
	 *
	 * @param penumcon
	 *            the penumcon to set
	 */
	public void setPenumcon(String penumcon) {
		this.penumcon = penumcon;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
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

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importePesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the importePesos to set
	 */
	public void setImportePesos(String importePesos) {
		this.importePesos = importePesos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlazoFijoEntity [anio=" + anio + ", divisa=" + divisa + ", pecodofi=" + pecodofi + ", penumcon="
				+ penumcon + ", tipo=" + tipo + ", orden=" + orden + ", concepto=" + concepto + ", importe=" + importe
				+ ", importePesos=" + importePesos + "]";
	}

}
