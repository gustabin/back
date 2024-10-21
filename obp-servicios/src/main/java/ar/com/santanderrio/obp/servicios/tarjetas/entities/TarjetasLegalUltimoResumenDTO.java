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
 * The Class TarjetasLegalUltimoResumenDTO.
 *
 * @author florencia.n.martinez
 */
public class TarjetasLegalUltimoResumenDTO {

	/** The tarjetas ultimo resumen. */
	private List<UltimoResumenTarjetaDTO> tarjetasUltimoResumen;

	/** The terminos Y condiciones. */
	private String terminosYCondiciones;

	/** The otrosConceptos. */
	private List<LineaUltimoResumenTarjetaDTO> otrosConceptos;

	/**
	 * Gets the tarjetas ultimo resumen.
	 *
	 * @return the tarjetasUltimoResumen
	 */
	public List<UltimoResumenTarjetaDTO> getTarjetasUltimoResumen() {
		return tarjetasUltimoResumen;
	}

	/**
	 * Sets the tarjetas ultimo resumen.
	 *
	 * @param tarjetasUltimoResumen
	 *            the tarjetasUltimoResumen to set
	 */
	public void setTarjetasUltimoResumen(List<UltimoResumenTarjetaDTO> tarjetasUltimoResumen) {
		this.tarjetasUltimoResumen = tarjetasUltimoResumen;
	}

	/**
	 * Gets the terminos Y condiciones.
	 *
	 * @return the terminosYCondiciones
	 */
	public String getTerminosYCondiciones() {
		return terminosYCondiciones;
	}

	/**
	 * Sets the terminos Y condiciones.
	 *
	 * @param terminosYCondiciones
	 *            the terminosYCondiciones to set
	 */
	public void setTerminosYCondiciones(String terminosYCondiciones) {
		this.terminosYCondiciones = terminosYCondiciones;
	}

	/**
	 * Gets the otros conceptos.
	 *
	 * @return the otrosConceptos
	 */
	public List<LineaUltimoResumenTarjetaDTO> getOtrosConceptos() {
		return otrosConceptos;
	}

	/**
	 * Sets the otros conceptos.
	 *
	 * @param otrosConceptos
	 *            the otrosConceptos to set
	 */
	public void setOtrosConceptos(List<LineaUltimoResumenTarjetaDTO> otrosConceptos) {
		this.otrosConceptos = otrosConceptos;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(terminosYCondiciones);
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
		TarjetasLegalUltimoResumenDTO other = (TarjetasLegalUltimoResumenDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(terminosYCondiciones, other.getTerminosYCondiciones());
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
		return new ToStringBuilder(this).append("tarjetasUltimoResumen", tarjetasUltimoResumen)
				.append("terminosYCondiciones", terminosYCondiciones).append("otrosConceptos", otrosConceptos)
				.toString();
	}

}