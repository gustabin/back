/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * The Class ConsumoTarjetaEntity.
 *
 *
 */
public class ConsumoTarjetaEntity extends ConsumoGeneralEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The consumo en pesos. */
	private String consumoPesos;

	/** The consumo en dolares. */
	private String consumoDolares;

	/** The lineas. */
	private List<LineaDetalleConsumoTarjetaEntity> lineas = new ArrayList<LineaDetalleConsumoTarjetaEntity>();

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public String getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(String consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Gets the consumo dolares.
	 *
	 * @return the consumo dolares
	 */
	public String getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumoDolares
	 *            the new consumo dolares
	 */
	public void setConsumoDolares(String consumoDolares) {
		this.consumoDolares = consumoDolares;
	}

	/**
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaDetalleConsumoTarjetaEntity> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the new lineas
	 */
	public void setLineas(List<LineaDetalleConsumoTarjetaEntity> lineas) {
		this.lineas = lineas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsumoTarjeta [marca=" + getMarca() + ", numero=" + getNumero() + ", nombreAdicional="
				+ getNombreAdicional() + ", consumoPesos=" + consumoPesos + ", consumoDolares=" + consumoDolares
				+ ", isTitular=" + getIsTitular() + ", isAdicional=" + getIsAdicional() + ", hasConsumoPesosCero="
				+ getHasConsumoPesosCero() + ", hasConsumoDolaresCero=" + getHasConsumoDolaresCero() + ", hasError="
				+ getHasError() + ", fechaDesde=" + getFechaDesde() + ", lineas=" + lineas + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hashFiller(hcb, true);
		hcb.append(lineas);
		hashFiller(hcb, false);
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
		ConsumoTarjetaEntity other = (ConsumoTarjetaEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		equalsFiller(eb, other, null, true);
		eb.append(lineas, other.lineas);
		equalsFiller(eb, other, null, false);
		return eb.isEquals();
	}

}
