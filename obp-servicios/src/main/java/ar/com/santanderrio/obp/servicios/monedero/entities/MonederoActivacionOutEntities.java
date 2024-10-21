/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class MonederoActivacionOutEntities.
 *
 * @author alejandro_leal
 */
@Record
public class MonederoActivacionOutEntities {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** Cantidad de Tarjetas TAG Habilitadas *. */
	private String cantTarjetasTAGHabilitadas;

	/** Numero de tarjeta Habilitada *. */
	private String numTarjetaTAGHabilitada;

	/** Id de Sistema *. */
	private String idSistema;

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
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido)
				.append(cantTarjetasTAGHabilitadas).append(numTarjetaTAGHabilitada).append(idSistema).toHashCode();
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

		MonederoActivacionOutEntities other = (MonederoActivacionOutEntities) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(headerTrama, other.headerTrama).append(codigoRetornoExtendido, other.codigoRetornoExtendido)
				.append(this.cantTarjetasTAGHabilitadas, other.cantTarjetasTAGHabilitadas)
				.append(this.numTarjetaTAGHabilitada, other.numTarjetaTAGHabilitada).isEquals();
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
		return new ToStringBuilder(this).append("HeaderTrama", headerTrama)
				.append("CodigoRetornoExtendido", codigoRetornoExtendido)
				.append("CantTarjetasTAGHabilitadas", this.cantTarjetasTAGHabilitadas)
				.append("NumeroDeTarjetaHabilitada", this.numTarjetaTAGHabilitada).toString();
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the cant tarjetas TAG habilitadas.
	 *
	 * @return the cantTarjetasTAGHabilitadas
	 */
	public String getCantTarjetasTAGHabilitadas() {
		return cantTarjetasTAGHabilitadas;
	}

	/**
	 * Sets the cant tarjetas TAG habilitadas.
	 *
	 * @param cantTarjetasTAGHabilitadas
	 *            the cantTarjetasTAGHabilitadas to set
	 */
	public void setCantTarjetasTAGHabilitadas(String cantTarjetasTAGHabilitadas) {
		this.cantTarjetasTAGHabilitadas = cantTarjetasTAGHabilitadas;
	}

	/**
	 * Gets the num tarjeta TAG habilitada.
	 *
	 * @return the numTarjetaTAGHabilitada
	 */
	public String getNumTarjetaTAGHabilitada() {
		return numTarjetaTAGHabilitada;
	}

	/**
	 * Sets the num tarjeta TAG habilitada.
	 *
	 * @param numTarjetaTAGHabilitada
	 *            the numTarjetaTAGHabilitada to set
	 */
	public void setNumTarjetaTAGHabilitada(String numTarjetaTAGHabilitada) {
		this.numTarjetaTAGHabilitada = numTarjetaTAGHabilitada;
	}

	/**
	 * Gets the id sistema.
	 *
	 * @return the idSistema
	 */
	public String getIdSistema() {
		return idSistema;
	}

	/**
	 * Sets the id sistema.
	 *
	 * @param idSistema
	 *            the idSistema to set
	 */
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

}
