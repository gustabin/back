/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;

/**
 * The Class ConsultaFinanciacionView.
 */
public class ConsultaFinanciacionView {

	/** The lista consulta financiaciones. */
	List<ConsultaFinanciacionDetalleView> listaConsultaFinanciaciones;

	/**
	 * Instantiates a new consulta financiacion view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ConsultaFinanciacionView(ConsultaFinanciacionDTO dto) {
		super();
		List<ConsultaFinanciacionDetalleView> lista = new ArrayList<ConsultaFinanciacionDetalleView>();
		for (ConsultaFinanciacionDetalleDTO detalle : dto.getDetallesFinanciaciones()) {
			ConsultaFinanciacionDetalleView consultaFinanciacionDetalleView = new ConsultaFinanciacionDetalleView(
					detalle);
			lista.add(consultaFinanciacionDetalleView);
		}
		this.listaConsultaFinanciaciones = lista;
	}

	/**
	 * Instantiates a new consulta financiacion view.
	 */
	public ConsultaFinanciacionView() {
		super();
	}
	
	/**
	 * Instantiates a new consulta financiacion view.
	 *
	 * @param lista
	 *            the lista
	 */
	public ConsultaFinanciacionView(List<ConsultaFinanciacionDetalleView> lista) {
		super();
		listaConsultaFinanciaciones = lista;
	}

	/**
	 * Gets the lista consulta financiaciones.
	 *
	 * @return the lista consulta financiaciones
	 */
	public List<ConsultaFinanciacionDetalleView> getListaConsultaFinanciaciones() {
		return listaConsultaFinanciaciones;
	}

	/**
	 * Sets the lista consulta financiaciones.
	 *
	 * @param listaConsultaFinanciaciones
	 *            the new lista consulta financiaciones
	 */
	public void setListaConsultaFinanciaciones(List<ConsultaFinanciacionDetalleView> listaConsultaFinanciaciones) {
		this.listaConsultaFinanciaciones = listaConsultaFinanciaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(listaConsultaFinanciaciones);
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
		ConsultaFinanciacionView other = (ConsultaFinanciacionView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(listaConsultaFinanciaciones, other.listaConsultaFinanciaciones);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaFinanciacionView [listaConsultaFinanciaciones=" + listaConsultaFinanciaciones + "]";
	}

}
