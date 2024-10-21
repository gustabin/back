/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity;

import java.util.Date;

import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * The Class OrdenDTO. Esta clase contiene todas las propiedades utilizadas para
 * transportar los datos de ordenes y operaciones entre el DAO y el Manager.
 *
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @see {@link OrdenesView}
 * @since Mon 23, 2017
 */
public class OrdenDTO extends OrdenBaseDTO{

	/** The tipo orden. */
	String tipoOrden;

	/** The fondo. */
	String fondo;

	/** The importe. */
	String importe;

	/**
	 * Instantiates a new orden DTO.
	 */
	public OrdenDTO() {
		super();
	}

	/**
	 * Instantiates a new orden DTO.
	 *
	 * @param numero
	 *            the numero
	 * @param fecha
	 *            the fecha
	 * @param estado
	 *            the estado
	 * @param tipoOrden
	 *            the tipo orden
	 * @param fondo
	 *            the fondo
	 * @param importe
	 *            the importe
	 */
	public OrdenDTO(String numero, Date fecha, String estado, String tipoOrden, String fondo, String importe) {
		super();
		this.setNumero(numero);
		this.setFecha(fecha);
		this.setEstado(estado);
		this.tipoOrden = tipoOrden;
		this.fondo = fondo;
		this.importe = importe;
	}


	/**
	 * Gets the tipo orden.
	 *
	 * @return the tipo orden
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}

	/**
	 * Sets the tipo orden.
	 *
	 * @param tipoOrden
	 *            the new tipo orden
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	/**
	 * Gets the fondo.
	 *
	 * @return the fondo
	 */
	public String getFondo() {
		return fondo;
	}

	/**
	 * Sets the fondo.
	 *
	 * @param fondo
	 *            the new fondo
	 */
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(OrdenBaseDTO o) {
        if (this.getFecha().before(o.getFecha())) {
            return -1;
        }
        if (this.getFecha().after(o.getFecha())) {
            return 1;
        }
        return 0;
	}

}
