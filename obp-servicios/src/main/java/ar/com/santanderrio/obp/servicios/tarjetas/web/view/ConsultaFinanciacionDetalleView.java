/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionLineaDTO;

/**
 * The Class ConsultaFinanciacionDetalleView.
 */
public class ConsultaFinanciacionDetalleView {

	/** The marca. */
	private String marca;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The financiaciones. */
	private List<ConsultaFinanciacionLineaView> financiaciones;

	/**
	 * Instantiates a new consulta financiacion detalle view.
	 *
	 * @param detalle
	 *            the detalle
	 */
	public ConsultaFinanciacionDetalleView(ConsultaFinanciacionDetalleDTO detalle) {
		super();
		this.setMarca(detalle.getMarca());
		this.setNumeroTarjeta(detalle.getNumeroTarjeta());
		List<ConsultaFinanciacionLineaView> financiaciones = new ArrayList<ConsultaFinanciacionLineaView>();
		for (ConsultaFinanciacionLineaDTO financiacion : detalle.getFinanciaciones()) {
			ConsultaFinanciacionLineaView consultaFinanciacionLineaView = new ConsultaFinanciacionLineaView(
					financiacion);
			financiaciones.add(consultaFinanciacionLineaView);
		}
		this.setFinanciaciones(financiaciones);
	}

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
	public List<ConsultaFinanciacionLineaView> getFinanciaciones() {
		return financiaciones;
	}

	/**
	 * Sets the financiaciones.
	 *
	 * @param financiaciones
	 *            the new financiaciones
	 */
	public void setFinanciaciones(List<ConsultaFinanciacionLineaView> financiaciones) {
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
		ConsultaFinanciacionDetalleView other = (ConsultaFinanciacionDetalleView) obj;
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
		return "ConsultaFinanciacionDetalleView [marca=" + marca + ", numeroTarjeta=" + numeroTarjeta
				+ ", financiaciones=" + financiaciones + "]";
	}

}
