/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsultaFinanciacionDTO.
 */
public class ConsultaFinanciacionDetalleDTO {

	/** The marca. */
	private String marca;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The financiaciones. */
	private List<ConsultaFinanciacionLineaDTO> financiaciones = new ArrayList<ConsultaFinanciacionLineaDTO>();

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the financiaciones.
	 *
	 * @return the financiaciones
	 */
	public List<ConsultaFinanciacionLineaDTO> getFinanciaciones() {
		return financiaciones;
	}

	/**
	 * Sets the financiaciones.
	 *
	 * @param financiaciones
	 *            the new financiaciones
	 */
	public void setFinanciaciones(List<ConsultaFinanciacionLineaDTO> financiaciones) {
		this.financiaciones = financiaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(marca);
		hcb.append(numeroTarjeta);
		hcb.append(financiaciones);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConsultaFinanciacionDetalleDTO other = (ConsultaFinanciacionDetalleDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(marca, other.marca);
		eb.append(numeroTarjeta, other.numeroTarjeta);
		eb.append(financiaciones, other.financiaciones);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaFinanciacionDetalleDTO [marca=" + marca + ", numeroTarjeta=" + numeroTarjeta
				+ ", financiaciones=" + financiaciones + "]";
	}

}
