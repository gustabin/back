/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class SolicitudAdhesionDebitoTarjetaEntity.
 *
 * @author florencia.n.martinez
 */
public class SolicitudAdhesionDebitoTarjetaEntity extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id solicitud. */
	private BigDecimal idSolicitud;

	/**
	 * Instantiates a new solicitud adhesion debito tarjeta entity.
	 */
	public SolicitudAdhesionDebitoTarjetaEntity() {
		super();
	}

	/**
	 * Instantiates a new solicitud adhesion debito tarjeta entity.
	 *
	 * @param idSolicitud
	 *            the id solicitud
	 */
	public SolicitudAdhesionDebitoTarjetaEntity(BigDecimal idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * Gets the id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public BigDecimal getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * Sets the id solicitud.
	 *
	 * @param idSolicitud
	 *            the idSolicitud to set
	 */
	public void setIdSolicitud(BigDecimal idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(idSolicitud);
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitudAdhesionDebitoTarjetaEntity other = (SolicitudAdhesionDebitoTarjetaEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(idSolicitud, other.getIdSolicitud());
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
		return new ToStringBuilder(this).append("idSolicitud", idSolicitud).toString();
	}
}
