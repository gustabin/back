/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * The Class ConsumoTarjetaDTO.
 *
 *
 */
public class ConsumoTarjetaDTO extends ConsumoGeneralEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The consumo en pesos. */
	private BigDecimal consumoPesos;

	/** The consumo en dolares. */
	private BigDecimal consumoDolares;

	/** The consumo pesos pendientes. */
	private BigDecimal consumoPesosPendientes;

	/** The consumo dolares pendientes. */
	private BigDecimal consumoDolaresPendientes;
	
	/** The is tarjeta recargable. */
	private Boolean isTarjetaRecargable;

	/** The lineas. */
	private List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();

	/** The prioridad. */
	private Integer prioridad;

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public BigDecimal getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(BigDecimal consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Gets the consumo dolares.
	 *
	 * @return the consumo dolares
	 */
	public BigDecimal getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumoDolares
	 *            the new consumo dolares
	 */
	public void setConsumoDolares(BigDecimal consumoDolares) {
		this.consumoDolares = consumoDolares;
	}

	/**
	 * Gets the consumo pesos pendientes.
	 *
	 * @return the consumoPesosPendientes
	 */
	public BigDecimal getConsumoPesosPendientes() {
		return consumoPesosPendientes;
	}

	/**
	 * Sets the consumo pesos pendientes.
	 *
	 * @param consumoPesosPendientes
	 *            the consumoPesosPendientes to set
	 */
	public void setConsumoPesosPendientes(BigDecimal consumoPesosPendientes) {
		this.consumoPesosPendientes = consumoPesosPendientes;
	}

	/**
	 * Gets the consumo dolares pendientes.
	 *
	 * @return the consumoDolaresPendientes
	 */
	public BigDecimal getConsumoDolaresPendientes() {
		return consumoDolaresPendientes;
	}

	/**
	 * Sets the consumo dolares pendientes.
	 *
	 * @param consumoDolaresPendientes
	 *            the consumoDolaresPendientes to set
	 */
	public void setConsumoDolaresPendientes(BigDecimal consumoDolaresPendientes) {
		this.consumoDolaresPendientes = consumoDolaresPendientes;
	}

	/**
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaDetalleConsumoTarjetaDTO> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the new lineas
	 */
	public void setLineas(List<LineaDetalleConsumoTarjetaDTO> lineas) {
		this.lineas = lineas;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the new prioridad
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	

	/**
	 * Gets the checks if is tarjeta recargable.
	 *
	 * @return the checks if is tarjeta recargable
	 */
	public Boolean getIsTarjetaRecargable() {
		return isTarjetaRecargable;
	}

	/**
	 * Sets the checks if is tarjeta recargable.
	 *
	 * @param isTarjetaRecargable
	 *            the new checks if is tarjeta recargable
	 */
	public void setIsTarjetaRecargable(Boolean isTarjetaRecargable) {
		this.isTarjetaRecargable = isTarjetaRecargable;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hcb.append(consumoDolaresPendientes);
		hcb.append(consumoPesosPendientes);
		hashFiller(hcb, true);
		hcb.append(lineas);
		hashFiller(hcb, false);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		ConsumoTarjetaDTO other = (ConsumoTarjetaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(consumoDolares, other.consumoDolares);
		eb.append(consumoPesos, other.consumoPesos);
		eb.append(consumoDolaresPendientes, other.consumoDolaresPendientes);
		eb.append(consumoPesosPendientes, other.consumoPesosPendientes);
		equalsFiller(eb, null, other, true);
		eb.append(lineas, other.lineas);
		equalsFiller(eb, null, other, false);
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("marca", getMarca()).append("numero", getNumero())
				.append("nombreAdicional", getNombreAdicional()).append("consumoPesos", consumoPesos)
				.append("consumoDolares", consumoDolares).append("consumoPesosPendientes", consumoPesosPendientes)
				.append("consumoDolaresPendientes", consumoDolaresPendientes).append("isTitular", getIsTitular())
				.append("isAdicional", getIsAdicional()).append("hasConsumoPesosCero", getHasConsumoPesosCero())
				.append("hasConsumoDolaresCero", getHasConsumoDolaresCero()).append("hasError", getHasError())
				.append("fechaDesde", getFechaDesde()).append("prioridad", prioridad).append("lineas", lineas)
				.toString();
	}

}