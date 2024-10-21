/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Clase que modela los datos de entrada para el servicio de comprobante de
 * suscripcion de fondo SUSFCI_150.
 *
 * @author marcelo.ruiz
 */
public class ComprobanteSuscripcionFondoInEntity extends SuscripcionFondoInEntity {

	/** N10 Número de Certificado a Reversar. */
	private String nroCertificReversar;

	/**
	 * N14(12.2) Monto a Reversar la Operación para KU
	 */
	private String montoReversar;

	/**
	 * Gets the nro certific reversar.
	 *
	 * @return the nro certific reversar
	 */
	public String getNroCertificReversar() {
		return nroCertificReversar;
	}

	/**
	 * Sets the nro certific reversar.
	 *
	 * @param nroCertificReversar
	 *            the new nro certific reversar
	 */
	public void setNroCertificReversar(String nroCertificReversar) {
		this.nroCertificReversar = nroCertificReversar;
	}

	/**
	 * Gets the monto reversar.
	 *
	 * @return the monto reversar
	 */
	public String getMontoReversar() {
		return montoReversar;
	}

	/**
	 * Sets the monto reversar.
	 *
	 * @param montoReversar
	 *            the new monto reversar
	 */
	public void setMontoReversar(String montoReversar) {
		this.montoReversar = montoReversar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.
	 * SuscripcionFondoInEntity#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nroCertificReversar).append(montoReversar).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.
	 * SuscripcionFondoInEntity#equals(java.lang.Object)
	 */
	/*
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ComprobanteSuscripcionFondoInEntity other = (ComprobanteSuscripcionFondoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(nroCertificReversar, other.nroCertificReversar).append(montoReversar, other.montoReversar)
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.
	 * SuscripcionFondoInEntity#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("NroCertificReversar", nroCertificReversar)
				.append("Monto A Reversar", montoReversar).toString();
	}

}
