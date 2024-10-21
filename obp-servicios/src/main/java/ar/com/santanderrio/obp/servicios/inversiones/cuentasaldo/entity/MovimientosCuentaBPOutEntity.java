/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaMovimientosCuentaBP;

/**
 * Objeto utilizado para retornar del DAO.
 *
 */


public class MovimientosCuentaBPOutEntity {

	/** The respuesta. */
	private List<RtaMovimientosCuentaBP> respuesta;
	
	/** The error en consulta. */
	private Boolean errorEnConsulta = false;

	
	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public List<RtaMovimientosCuentaBP> getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(List<RtaMovimientosCuentaBP> respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Gets the error en consulta.
	 *
	 * @return the errorEnConsulta
	 */
	public Boolean getErrorEnConsulta() {
		return errorEnConsulta;
	}

	/**
	 * Sets the error en consulta.
	 *
	 * @param errorEnConsulta
	 *            the errorEnConsulta to set
	 */
	public void setErrorEnConsulta(Boolean errorEnConsulta) {
		this.errorEnConsulta = errorEnConsulta;
	}

	
	
	/*
	 * (non-Javadoc) (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MovimientosCuentaBPOutEntity [respuesta=" + respuesta + "]";
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(respuesta);
		return hcb.toHashCode();
	}

	/* (non-Javadoc)
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
		MovimientosCuentaBPOutEntity other = (MovimientosCuentaBPOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(respuesta, other.respuesta);
		return eb.isEquals();
	}
		
}
