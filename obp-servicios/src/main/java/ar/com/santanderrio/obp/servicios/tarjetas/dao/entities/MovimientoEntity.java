/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class MovimientoEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/movimientos/tarjeta/movimiento/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "movimiento")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovimientoEntity {

	/** The Class Establecimiento. */
	@XmlElement(name = "establecimiento")
	private EstablecimientoEntity establecimiento;

	/** The Class Importe. */
	@XmlElement(name = "importe")
	private ImporteEntity importe;

	/** The String fecha. */
	@XmlElement(name = "fecha")
	private String fecha;

	/** The String ticket. */
	@XmlElement(name = "ticket")
	private String ticket;

	/** The String tipoMoneda. */
	@XmlElement(name = "tipoMoneda")
	private String tipoMoneda;
	
	@XmlElement(name = "detallePromocion")
	private DetallePromocionEntity detallePromocion;

	/**
	 * Getter de establecimiento.
	 * 
	 * @return the establecimiento
	 */
	public EstablecimientoEntity getEstablecimiento() {
		return establecimiento;
	}

	/**
	 * Setter de establecimiento.
	 * 
	 * @param establecimiento
	 *            the establecimiento to set
	 */
	public void setEstablecimiento(EstablecimientoEntity establecimiento) {
		this.establecimiento = establecimiento;
	}

	/**
	 * Getter de importe.
	 * 
	 * @return the importe
	 */
	public ImporteEntity getImporte() {
		return importe;
	}

	/**
	 * Setter de importe.
	 * 
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(ImporteEntity importe) {
		this.importe = importe;
	}

	/**
	 * Getter de fecha.
	 * 
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Setter de fecha.
	 * 
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Getter de ticket.
	 * 
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * Setter de ticket.
	 * 
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * Getter de tipoMoneda.
	 * 
	 * @return the tipoMoneda
	 */
	public String getTipoMoneda() {
		return tipoMoneda;
	}

	/**
	 * Setter de tipoMoneda.
	 * 
	 * @param tipoMoneda
	 *            the tipoMoneda to set
	 */
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	/**
	 * Gets the detalle promocion.
	 *
	 * @return the detalle promocion
	 */
	public DetallePromocionEntity getDetallePromocion() {
		return detallePromocion;
	}

	/**
	 * Sets the detalle promocion.
	 *
	 * @param detallePromocion the new detalle promocion
	 */
	public void setDetallePromocion(DetallePromocionEntity detallePromocion) {
		this.detallePromocion = detallePromocion;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(establecimiento);
		hcb.append(fecha);
		hcb.append(importe);
		hcb.append(ticket);
		hcb.append(tipoMoneda);
		hcb.append(detallePromocion);
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
		MovimientoEntity other = (MovimientoEntity) obj;
		return new EqualsBuilder().append(establecimiento, other.establecimiento).append(fecha, other.fecha)
				.append(importe, other.importe).append(ticket, other.ticket).append(tipoMoneda, other.tipoMoneda)
				.append(detallePromocion, other.detallePromocion).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Movimiento [establecimiento=" + establecimiento + ", importe=" + importe + ", fecha=" + fecha
				+ ", ticket=" + ticket + ", tipoMoneda=" + tipoMoneda + ", detallePromocion=" + detallePromocion + "]";
	}

}
