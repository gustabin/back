/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cache.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Informacion de estadistica de la cache.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CacheView {

	/** Nombre de la cache *. */
	private String nombreCache;

	/** Numero de elementos *. */
	private String numeroElementos;

	/** Numero de invocaciones realizadas a la cache *. */
	private String numeroHintsRealizados;

	/** Numero de limpiezas de la cache *. */
	private String numeroEvicts;

	/**
	 * Gets the nombre cache.
	 *
	 * @return the nombreCache
	 */
	public String getNombreCache() {
		return nombreCache;
	}

	/**
	 * Sets the nombre cache.
	 *
	 * @param nombreCache
	 *            the nombreCache to set
	 */
	public void setNombreCache(String nombreCache) {
		this.nombreCache = nombreCache;
	}

	/**
	 * Gets the numero elementos.
	 *
	 * @return the numeroElementos
	 */
	public String getNumeroElementos() {
		return numeroElementos;
	}

	/**
	 * Sets the numero elementos.
	 *
	 * @param numeroElementos
	 *            the numeroElementos to set
	 */
	public void setNumeroElementos(String numeroElementos) {
		this.numeroElementos = numeroElementos;
	}

	/**
	 * Gets the numero hints realizados.
	 *
	 * @return the numeroHintsRealizados
	 */
	public String getNumeroHintsRealizados() {
		return numeroHintsRealizados;
	}

	/**
	 * Sets the numero hints realizados.
	 *
	 * @param numeroHintsRealizados
	 *            the numeroHintsRealizados to set
	 */
	public void setNumeroHintsRealizados(String numeroHintsRealizados) {
		this.numeroHintsRealizados = numeroHintsRealizados;
	}

	/**
	 * Gets the numero evicts.
	 *
	 * @return the numeroEvicts
	 */
	public String getNumeroEvicts() {
		return numeroEvicts;
	}

	/**
	 * Sets the numero evicts.
	 *
	 * @param numeroEvicts
	 *            the numeroEvicts to set
	 */
	public void setNumeroEvicts(String numeroEvicts) {
		this.numeroEvicts = numeroEvicts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombreCache);
		hcb.append(numeroElementos);
		hcb.append(numeroHintsRealizados);
		hcb.append(numeroEvicts);
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

		CacheView other = (CacheView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nombreCache, other.getNombreCache());
		eb.append(numeroElementos, other.getNumeroElementos());
		eb.append(numeroHintsRealizados, other.getNumeroHintsRealizados());
		eb.append(numeroEvicts, other.getNumeroEvicts());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(nombreCache).append(numeroElementos).append(numeroHintsRealizados)
				.append(numeroEvicts).toString();
	}

}
