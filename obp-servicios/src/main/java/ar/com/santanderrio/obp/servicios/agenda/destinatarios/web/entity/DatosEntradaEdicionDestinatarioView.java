/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DatosEntradaEdicionDestinatarioView.
 */
public class DatosEntradaEdicionDestinatarioView extends DatosEntradaAgendaDestinatario {

	/** The correo editado. */
	private String correoEditado;

	/** The descripcion editada. */
	private String descripcionEditada;

	/** The referencia titular. */
	private String referenciaTitular;

	/**
	 * Instantiates a new datos entrada edicion destinatario view.
	 *
	 * @param filtroDestinatario
	 *            the filtro destinatario
	 */
	public DatosEntradaEdicionDestinatarioView(boolean filtroDestinatario) {
		super(filtroDestinatario);
	}

	/**
	 * Gets the correo editado.
	 *
	 * @return the correo editado
	 */
	public String getCorreoEditado() {
		return correoEditado;
	}

	/**
	 * Sets the correo editado.
	 *
	 * @param correoEditado
	 *            the new correo editado
	 */
	public void setCorreoEditado(String correoEditado) {
		this.correoEditado = correoEditado;
	}

	/**
	 * Gets the descripcion editada.
	 *
	 * @return the descripcion editada
	 */
	public String getDescripcionEditada() {
		return descripcionEditada;
	}

	/**
	 * Sets the descripcion editada.
	 *
	 * @param descripcionEditada
	 *            the new descripcion editada
	 */
	public void setDescripcionEditada(String descripcionEditada) {
		this.descripcionEditada = descripcionEditada;
	}

	/**
	 * Gets the referencia titular.
	 *
	 * @return the referencia titular
	 */
	public String getReferenciaTitular() {
		return referenciaTitular;
	}

	/**
	 * Sets the referencia titular.
	 *
	 * @param referenciaTitular
	 *            the new referencia titular
	 */
	public void setReferenciaTitular(String referenciaTitular) {
		this.referenciaTitular = referenciaTitular;
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
		DatosEntradaEdicionDestinatarioView other = (DatosEntradaEdicionDestinatarioView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(getTipoDestinatario(), other.getTipoDestinatario());
		eb.append(getId(), other.getId());
		eb.append(correoEditado, other.getCorreoEditado());
		eb.append(descripcionEditada, other.getDescripcionEditada());
		eb.append(referenciaTitular, other.getReferenciaTitular());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosEntradaAgendaDestinatario [tipoDestinatario=" + getTipoDestinatario() + ", id=" + getId()
				+ ", correoEditado=" + correoEditado + ", descripcionEditada=" + descripcionEditada
				+ ", referenciaTitular=" + referenciaTitular + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(getTipoDestinatario());
		hcb.append(getId());
		hcb.append(correoEditado);
		hcb.append(descripcionEditada);
		hcb.append(referenciaTitular);
		return hcb.toHashCode();
	}

}
