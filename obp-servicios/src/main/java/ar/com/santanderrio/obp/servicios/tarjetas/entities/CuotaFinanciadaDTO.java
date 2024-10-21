/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Cuota y Tasa Nominal Anual para la financiacion de un importe.
 * 
 * @author emilio.watemberg
 * @see {@link FinanciacionTarjetaView}
 * @since Dec 6, 2016
 */
public class CuotaFinanciadaDTO {

	/** The cuota. */
	private String cuota;

	/** The tna. */
	private String tna;

	/**
	 * Instantiates a new cuota financiada DTO.
	 *
	 * @param cuota
	 *            the cuota
	 * @param tna
	 *            the tna
	 */
	public CuotaFinanciadaDTO(String cuota, String tna) {
		super();
		this.cuota = cuota;
		this.tna = tna;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param cuota
	 *            the new cuota
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuota);
		hcb.append(tna);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		CuotaFinanciadaDTO other = (CuotaFinanciadaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuota, other.getCuota());
		eb.append(tna, other.getTna());
		return eb.isEquals();
	}

}
