/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class ConsultaTiposPlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class ConsultaTiposPlazoFijoEntity {
	/** The Tipo PF. */
	@Field()
	private String tipoPF;

	/** The Descripcion tipo PF. */
	@Field()
	private String descripcionTipoPF;

	/** The indicador corralito. */
	@Field()
	private String indicadorCorralito;

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipo PF
	 */
	public String getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the new tipo PF
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
	}

	/**
	 * Gets the descripcion tipo PF.
	 *
	 * @return the descripcion tipo PF
	 */
	public String getDescripcionTipoPF() {
		return descripcionTipoPF;
	}

	/**
	 * Sets the descripcion tipo PF.
	 *
	 * @param descripcionTipoPF
	 *            the new descripcion tipo PF
	 */
	public void setDescripcionTipoPF(String descripcionTipoPF) {
		this.descripcionTipoPF = descripcionTipoPF;
	}

	/**
	 * Gets the indicador corralito.
	 *
	 * @return the indicador corralito
	 */
	public String getIndicadorCorralito() {
		return indicadorCorralito;
	}

	/**
	 * Sets the indicador corralito.
	 *
	 * @param indicadorCorralito
	 *            the new indicador corralito
	 */
	public void setIndicadorCorralito(String indicadorCorralito) {
		this.indicadorCorralito = indicadorCorralito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("tipoPF", tipoPF).append("descripcionTipoPF", descripcionTipoPF)
				.append("indicadorCorralito", indicadorCorralito).toString();
	}

}
