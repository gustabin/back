/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.custodia.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */
public class CustodiaBPOutEntity {

	/** The respuesta. */
	private List<TenenciaBP> respuesta;

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public List<TenenciaBP> getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setRespuesta(List<TenenciaBP> respuesta) {
		this.respuesta = respuesta;
	}

	/*
	 * (non-Javadoc) (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustodiaOutEntity [respuesta=" + respuesta + "]";
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
		CustodiaBPOutEntity other = (CustodiaBPOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(respuesta, other.respuesta);
		return eb.isEquals();
	}

}
