/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class UltimosConsumosDTO.
 *
 * @author sabrina.cis
 */
public class UltimosConsumosDTO {

	/** The ultimos consumos. */
	private List<ConsumoTarjetaDTO> ultimosConsumos;

	/** The muestra tarjetas con cabecera. */
	private Boolean muestraTarjetasConCabecera = Boolean.FALSE;

	/**
	 * Instantiates a new ultimos consumos DTO.
	 */
	public UltimosConsumosDTO() {
		super();
	}

	/**
	 * Instantiates a new ultimos consumos DTO.
	 *
	 * @param ultimosConsumos
	 *            the ultimos consumos
	 * @param muestraTarjetasConCabecera
	 *            the muestra tarjetas con cabecera
	 */
	public UltimosConsumosDTO(List<ConsumoTarjetaDTO> ultimosConsumos, Boolean muestraTarjetasConCabecera) {
		super();
		this.ultimosConsumos = ultimosConsumos;
		this.muestraTarjetasConCabecera = muestraTarjetasConCabecera;
	}

	/**
	 * Instantiates a new ultimos consumos DTO.
	 *
	 * @param ultimosConsumos
	 *            the ultimos consumos
	 */
	public UltimosConsumosDTO(List<ConsumoTarjetaDTO> ultimosConsumos) {
		super();
		this.setUltimosConsumos(ultimosConsumos);
	}

	/**
	 * Gets the ultimos consumos.
	 *
	 * @return the ultimos consumos
	 */
	public List<ConsumoTarjetaDTO> getUltimosConsumos() {
		return ultimosConsumos;
	}

	/**
	 * Sets the ultimos consumos.
	 *
	 * @param ultimosConsumos
	 *            the new ultimos consumos
	 */
	public void setUltimosConsumos(List<ConsumoTarjetaDTO> ultimosConsumos) {
		this.ultimosConsumos = ultimosConsumos;
	}

	/**
	 * Gets the muestra tarjetas con cabecera.
	 *
	 * @return the muestraTarjetasConCabecera
	 */
	public Boolean getMuestraTarjetasConCabecera() {
		return muestraTarjetasConCabecera;
	}

	/**
	 * Sets the muestra tarjetas con cabecera.
	 *
	 * @param muestraTarjetasConCabecera
	 *            the muestraTarjetasConCabecera to set
	 */
	public void setMuestraTarjetasConCabecera(Boolean muestraTarjetasConCabecera) {
		this.muestraTarjetasConCabecera = muestraTarjetasConCabecera;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(muestraTarjetasConCabecera);
		hcb.append(ultimosConsumos);
		return hcb.hashCode();
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
		UltimosConsumosDTO other = (UltimosConsumosDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(muestraTarjetasConCabecera, other.getMuestraTarjetasConCabecera());
		eb.append(ultimosConsumos, other.getUltimosConsumos());
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
		return new ToStringBuilder(this).append("ultimosConsumos", ultimosConsumos)
				.append("muestraTarjetasConCabecera", muestraTarjetasConCabecera).toString();
	}

}