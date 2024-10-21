/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;

/**
 * The Class ParametroAdhesionDebitoTarjetaInView.
 *
 * @author florencia.n.martinez
 */
public class ParametroAdhesionDebitoTarjetaInView {

	/** The datosCliente. */
	private HashDebitoAutomaticoTarjetaView datosCliente;

	/** The reintentar. */
	private Boolean reintentar = Boolean.FALSE;

	/**
	 * Gets the datosCliente.
	 *
	 * @return the datosCliente
	 */
	public HashDebitoAutomaticoTarjetaView getDatosCliente() {
		return datosCliente;
	}

	/**
	 * Sets the datosCliente.
	 *
	 * @param datosCliente
	 *            the datosCliente to set
	 */
	public void setDatosCliente(HashDebitoAutomaticoTarjetaView datosCliente) {
		this.datosCliente = datosCliente;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public Boolean getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the reintentar to set
	 */
	public void setReintentar(Boolean reintentar) {
		this.reintentar = reintentar;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(datosCliente);
		hcb.append(reintentar);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametroAdhesionDebitoTarjetaInView other = (ParametroAdhesionDebitoTarjetaInView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(datosCliente, other.getDatosCliente());
		eb.append(reintentar, other.getReintentar());
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
		return new ToStringBuilder(this).append("datosCliente", datosCliente).append("reintentar", reintentar)
				.toString();
	}

}
