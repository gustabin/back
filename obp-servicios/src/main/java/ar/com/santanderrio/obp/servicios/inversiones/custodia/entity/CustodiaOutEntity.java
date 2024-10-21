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
public class CustodiaOutEntity {

	/** The respuesta. */
	private List<Tenencia> listaTenencia;

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public List<Tenencia> getListaTenencia() {
		return listaTenencia;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setListaTenencia(List<Tenencia> respuesta) {
		this.listaTenencia = respuesta;
	}

	/*
	 * (non-Javadoc) (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustodiaOutEntity [respuesta=" + listaTenencia + "]";
	}

	/*
	 * (non-Javadoc) (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(listaTenencia);
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
		CustodiaOutEntity other = (CustodiaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(listaTenencia, other.listaTenencia);
		return eb.isEquals();
	}

}
