/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * Objeto utilizado para retornar del DAO.
 *
 * @author
 *
 */
public class TipClienteCitiTenenciasOutEntity {

	/** The cod_resultado. */
	private String codResultado;
	/** The desc_error_tec. */
	private String descErrorTec;
	/** The desc_error_amig. */
	private String descErrorAmig;
	/** The desc_error_amig. */
	private String tipCliente;
	



	/**
	 * Gets the cod resultado.
	 *
	 * @return the cod resultado
	 */
	public String getCodResultado() {
		return codResultado;
	}

	/**
	 * Sets the cod resultado.
	 *
	 * @param codResultado the new cod resultado
	 */
	public void setCodResultado(String codResultado) {
		this.codResultado = codResultado;
	}

	/**
	 * Gets the desc error tec.
	 *
	 * @return the desc error tec
	 */
	public String getDescErrorTec() {
		return descErrorTec;
	}

	/**
	 * Sets the desc error tec.
	 *
	 * @param descErrorTec the new desc error tec
	 */
	public void setDescErrorTec(String descErrorTec) {
		this.descErrorTec = descErrorTec;
	}

	/**
	 * Gets the desc error amig.
	 *
	 * @return the desc error amig
	 */
	public String getDescErrorAmig() {
		return descErrorAmig;
	}

	/**
	 * Sets the desc error amig.
	 *
	 * @param descErrorAmig the new desc error amig
	 */
	public void setDescErrorAmig(String descErrorAmig) {
		this.descErrorAmig = descErrorAmig;
	}
	

	/**
	 * Gets the tip cliente.
	 *
	 * @return the tip cliente
	 */
	public String getTipCliente() {
		return tipCliente;
	}

	/**
	 * Sets the tip cliente.
	 *
	 * @param tipCliente the new tip cliente
	 */
	public void setTipCliente(String tipCliente) {
		this.tipCliente = tipCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(codResultado).append(descErrorTec).append(descErrorAmig)
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
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

		TipClienteCitiTenenciasOutEntity other = (TipClienteCitiTenenciasOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codResultado, other.getCodResultado());
		eb.append(descErrorTec, other.getDescErrorTec());
		eb.append(descErrorAmig, other.getDescErrorAmig());
	

		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this).append("codResultado", codResultado).append("descErrorTec", descErrorTec)
				.append("descErrorAmig", descErrorAmig)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

}