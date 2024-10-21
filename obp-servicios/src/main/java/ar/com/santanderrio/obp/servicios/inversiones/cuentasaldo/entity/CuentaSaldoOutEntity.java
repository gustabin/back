/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadSaldo;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */

public class CuentaSaldoOutEntity {

	/** The respuesta. */
	private List<RtaLoadSaldo> respuesta;
	
	/** The error en consulta. */
	private Boolean errorEnConsulta = false;

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public List<RtaLoadSaldo> getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setRespuesta(List<RtaLoadSaldo> respuesta) {
		this.respuesta = respuesta;
	}

	/*
	 * (non-Javadoc) (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuentaSaldoOutEntity [respuesta=" + respuesta + "]";
	}

	/*
	 * (non-Javadoc) (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(respuesta);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc) (non-Javadoc)
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
		CuentaSaldoOutEntity other = (CuentaSaldoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(respuesta, other.respuesta);
		return eb.isEquals();
	}

	/**
	 * Gets the error en consulta.
	 *
	 * @return the error en consulta
	 */
	public Boolean getErrorEnConsulta() {
		return errorEnConsulta;
	}

	/**
	 * Sets the error en consulta.
	 *
	 * @param errorEnConsulta
	 *            the new error en consulta
	 */
	public void setErrorEnConsulta(Boolean errorEnConsulta) {
		this.errorEnConsulta = errorEnConsulta;
	}

}