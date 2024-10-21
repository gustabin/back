/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class GestorEventoComercialInEntity.
 *
 * @author florencia.n.martinez
 */
public class ParametroGestorComercialInEntity {

	/** The canal. */
	@JsonProperty("canal")
	private String canal;

	/** The sub canal. */
	@JsonProperty("subCanal")
	private String subCanal;

	/** The nup. */
	@JsonProperty("nup")
	private String nup;

	/** The datos firmados. */
	@JsonProperty("datosFirmados")
	private String datosFirmados;

	/**
	 * Instantiates a new parametro gestor comercial in entity.
	 */
	public ParametroGestorComercialInEntity() {
		super();
	}

	/**
	 * Instantiates a new parametro gestor comercial in entity.
	 *
	 * @param nup
	 *            the nup
	 * @param firma
	 *            the firma
	 */
	public ParametroGestorComercialInEntity(String nup, String firma) {
		this.canal = "04";
		this.subCanal = "99";
		this.nup = nup;
		this.datosFirmados = firma;
	}
	
	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public final String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public final void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the sub canal.
	 *
	 * @return the subCanal
	 */
	public final String getSubCanal() {
		return subCanal;
	}

	/**
	 * Sets the sub canal.
	 *
	 * @param subCanal
	 *            the subCanal to set
	 */
	public final void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public final String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public final void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the datos firmados.
	 *
	 * @return the datosFirmados
	 */
	public final String getDatosFirmados() {
		return datosFirmados;
	}

	/**
	 * Sets the datos firmados.
	 *
	 * @param datosFirmados
	 *            the datosFirmados to set
	 */
	public final void setDatosFirmados(String datosFirmados) {
		this.datosFirmados = datosFirmados;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(canal);
		hcb.append(datosFirmados);
		hcb.append(nup);
		hcb.append(subCanal);
		return hcb.toHashCode();
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametroGestorComercialInEntity other = (ParametroGestorComercialInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(canal, other.getCanal());
		eb.append(datosFirmados, other.getDatosFirmados());
		eb.append(nup, other.getNup());
		eb.append(subCanal, other.getSubCanal());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("canal", canal).append("subCanal", subCanal).append("nup", nup)
				.append("datosFirmados", datosFirmados).toString();
	}

}