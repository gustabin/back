/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class MonederoActivacionInEntities.
 *
 * @author alejandro_leal
 */
public class MonederoActivacionInEntities {
	/** The cliente. */
	private Cliente cliente;

	/** Sucursal *. */
	private String sucursal;

	/** Cantidad de tarjetas TAG a Activar *. */
	private String cantTarjetaTagPorActivar;

	/** Numero de la tarjeta por activar *. */
	private String numTarjetaPorActivar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cliente).append(this.sucursal).append(this.cantTarjetaTagPorActivar)
				.append(this.numTarjetaPorActivar).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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

		MonederoActivacionInEntities other = (MonederoActivacionInEntities) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cliente, other.cliente).append(this.sucursal, other.sucursal)
				.append(this.cantTarjetaTagPorActivar, other.cantTarjetaTagPorActivar)
				.append(this.numTarjetaPorActivar, other.numTarjetaPorActivar).isEquals();
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
		return new ToStringBuilder(this).append(cliente).append(this.sucursal).append(this.cantTarjetaTagPorActivar)
				.append(this.numTarjetaPorActivar).toString();
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the cant tarjeta tag por activar.
	 *
	 * @return the cantTarjetaTagPorActivar
	 */
	public String getCantTarjetaTagPorActivar() {
		return cantTarjetaTagPorActivar;
	}

	/**
	 * Sets the cant tarjeta tag por activar.
	 *
	 * @param cantTarjetaTagPorActivar
	 *            the cantTarjetaTagPorActivar to set
	 */
	public void setCantTarjetaTagPorActivar(String cantTarjetaTagPorActivar) {
		this.cantTarjetaTagPorActivar = cantTarjetaTagPorActivar;
	}

	/**
	 * Gets the num tarjeta por activar.
	 *
	 * @return the numTarjetaPorActivar
	 */
	public String getNumTarjetaPorActivar() {
		return numTarjetaPorActivar;
	}

	/**
	 * Sets the num tarjeta por activar.
	 *
	 * @param numTarjetaPorActivar
	 *            the numTarjetaPorActivar to set
	 */
	public void setNumTarjetaPorActivar(String numTarjetaPorActivar) {
		this.numTarjetaPorActivar = numTarjetaPorActivar;
	}

}
