package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/** 
 * The class FondosRimpEntity de Resumen Impositivo
 * 
 * @author A308895
 *
 */
public class FondosRimpEntity {
	
	/** The anio. */
	@JsonProperty("Año")
	private String anio;

	/** The cod fondo. */
	@JsonProperty("CodigoFondo")
	private String codFondo;

	/** The descripcion. */
	@JsonProperty("Descripcion")
	private String descripcion;

	/** The cuenta. */
	@JsonProperty("CuentaTitulo")
	private String cuenta;

	/** The divisa. */
	@JsonProperty("Divisa")
	private String divisa;

	/** The saldo cuotas. */
	@JsonProperty("SaldoCuotas")
	private String saldoCuotas;

	/** The valor cuota. */
	@JsonProperty("ValorCuota")
	private String valorCuota;

	/** The coti afip. */
	@JsonProperty("CotizacionAfip")
	private String cotiAfip;

	/** The importe. */
	@JsonProperty("Importe")
	private String importe;

	/** The sum renta. */
	@JsonProperty("SumRenta")
	private String sumRenta;

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
	 * Gets the cod fondo.
	 *
	 * @return the codFondo
	 */
	public String getCodFondo() {
		return codFondo;
	}

	/**
	 * Sets the cod fondo.
	 *
	 * @param codFondo
	 *            the codFondo to set
	 */
	public void setCodFondo(String codFondo) {
		this.codFondo = codFondo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Gets the saldo cuotas.
	 *
	 * @return the saldoCuotas
	 */
	public String getSaldoCuotas() {
		return saldoCuotas;
	}

	/**
	 * Sets the saldo cuotas.
	 *
	 * @param saldoCuotas
	 *            the saldoCuotas to set
	 */
	public void setSaldoCuotas(String saldoCuotas) {
		this.saldoCuotas = saldoCuotas;
	}

	/**
	 * Gets the valor cuota.
	 *
	 * @return the valorCuota
	 */
	public String getValorCuota() {
		return valorCuota;
	}

	/**
	 * Sets the valor cuota.
	 *
	 * @param valorCuota
	 *            the valorCuota to set
	 */
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
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

	/**
	 * Gets the sum renta.
	 *
	 * @return the sumRenta
	 */
	public String getSumRenta() {
		return sumRenta;
	}

	/**
	 * Sets the sum renta.
	 *
	 * @param sumRenta
	 *            the sumRenta to set
	 */
	public void setSumRenta(String sumRenta) {
		this.sumRenta = sumRenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FondosRimpEntity [anio=" + anio + ", codFondo=" + codFondo + ", descripcion=" + descripcion
				+ ", cuenta=" + cuenta + ", divisa=" + divisa + ", saldoCuotas=" + saldoCuotas + ", valorCuota="
				+ valorCuota + ", cotiAfip=" + cotiAfip + ", importe=" + importe + ", sumRenta=" + sumRenta + "]";
	}
	

}
