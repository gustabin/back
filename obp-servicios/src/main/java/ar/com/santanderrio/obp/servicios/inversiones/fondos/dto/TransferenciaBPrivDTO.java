/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class TransferenciaBPrivDTO.
 */
public class TransferenciaBPrivDTO extends TransferenciaDTO {

	/** The importe. */
	private String importe;

	/** The nrocuentaBancaPrivada. */
	private String nroCuentaBancaPrivada;

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
	 * Gets the nro cuenta banca privada.
	 *
	 * @return the nro cuenta banca privada
	 */
	public String getNroCuentaBancaPrivada() {
		return nroCuentaBancaPrivada;
	}

	/**
	 * Sets the nro cuenta banca privada.
	 *
	 * @param nroCuentaBancaPrivada
	 *            the new nro cuenta banca privada
	 */
	public void setNroCuentaBancaPrivada(String nroCuentaBancaPrivada) {
		this.nroCuentaBancaPrivada = nroCuentaBancaPrivada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(importe).append(nroCuentaBancaPrivada).toHashCode();
	}

	/*
	 * (non-Javadoc)
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

		TransferenciaBPrivDTO other = (TransferenciaBPrivDTO) obj;
		EqualsBuilder eb = new EqualsBuilder().append(importe, other.getImporte()).append(nroCuentaBancaPrivada,
				other.getNroCuentaBancaPrivada());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("importe", getImporte())
				.append("nroCuentaBancaPrivada", getNroCuentaBancaPrivada()).toString();
	}

}
