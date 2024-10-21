/*
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
 * The Class DetalleCuotaPendienteEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/
 */
@XmlRootElement(name = "cuota")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleCuotaPendienteEntity {

	/** The establecimiento. */
	@XmlElement(name = "establecimiento")
	private String establecimiento;

	/** The fecha. */
	@XmlElement(name = "fecha")
	private String fecha;
	/** The comprobante. */
	@XmlElement(name = "comprobante")
	private String comprobante;

	/** The cuotas. */
	@XmlElement(name = "cuotas")
	private String cuotas;

	/** The cuotas pendientes. */
	@XmlElement(name = "cuotasPendientes")
	private String cuotasPendientes;

	/** The moneda. */
	@XmlElement(name = "moneda")
	private String moneda;

	/** The importe. */
	@XmlElement(name = "importe")
	private String importe;

	/**
	 * Gets the establecimiento.
	 *
	 * @return the establecimiento
	 */
	public String getEstablecimiento() {
		return establecimiento;
	}

	/**
	 * Sets the establecimiento.
	 *
	 * @param establecimiento
	 *            the new establecimiento
	 */
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public String getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the new cuotas
	 */
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	/**
	 * Gets the cuotas pendientes.
	 *
	 * @return the cuotas pendientes
	 */
	public String getCuotasPendientes() {
		return cuotasPendientes;
	}

	/**
	 * Sets the cuotas pendientes.
	 *
	 * @param cuotasPendientes
	 *            the new cuotas pendientes
	 */
	public void setCuotasPendientes(String cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(comprobante);
		hcb.append(cuotas);
		hcb.append(cuotasPendientes);
		hcb.append(establecimiento);
		hcb.append(fecha);
		hcb.append(importe);
		hcb.append(moneda);
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
		DetalleCuotaPendienteEntity other = (DetalleCuotaPendienteEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(comprobante, other.getComprobante());
		eb.append(cuotas, other.getCuotas());
		eb.append(cuotasPendientes, other.getCuotasPendientes());
		eb.append(establecimiento, other.getEstablecimiento());
		eb.append(fecha, other.getFecha());
		eb.append(importe, other.getImporte());
		eb.append(moneda, other.getMoneda());
		return eb.isEquals();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotaPendiente [establecimiento=" + establecimiento + ", fecha=" + fecha + ", comprobante="
				+ comprobante + ", cuotas=" + cuotas + ", cuotasPendientes=" + cuotasPendientes + ", moneda=" + moneda
				+ ", importe=" + importe + "]";
	}

}
