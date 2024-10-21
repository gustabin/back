/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.entity;

/**
 * The Class ConfirmarClienteCompraEntity.
 *
 * @author sabrina.cis
 */
public class ConfirmarClienteCompraEntity extends ConfirmarClienteCompraVenta {

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return hashBuilder().toHashCode();
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
		ConfirmarClienteCompraEntity other = (ConfirmarClienteCompraEntity) obj;

		return equalsBuilder(other, null).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfirmarClienteCompra [" + toStringCompraVenta() + "]";
	}

}
