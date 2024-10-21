package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class ConsultaTiposPlazoFijoBPrivOutEntity {
	
	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The cantidad tipo pf. */
	@Field()
	private Long cantTipoPF;

	/** The tipo plazo fijo. */
	@Segment(occursRef = "cantTipoPF")
	private List<ConsultaTiposPlazoFijoBPrivEntity> tipoPF = new ArrayList<ConsultaTiposPlazoFijoBPrivEntity>();

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the cant tipo PF.
	 *
	 * @param cantTipoPF
	 *            the new cant tipo PF
	 */
	public void setCantTipoPF(Long cantTipoPF) {
		this.cantTipoPF = cantTipoPF;
	}

	/**
	 * Gets the cant tipo PF.
	 *
	 * @return the cant tipo PF
	 */
	public Long getCantTipoPF() {
		return cantTipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the new tipo PF
	 */
	public void setTipoPF(List<ConsultaTiposPlazoFijoBPrivEntity> tipoPF) {
		this.tipoPF = tipoPF;
	}

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipo PF
	 */
	public List<ConsultaTiposPlazoFijoBPrivEntity> getTipoPF() {
		return tipoPF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido).append(cantTipoPF)
				.append(tipoPF).toHashCode();
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

		ConsultaTiposPlazoFijoBPrivOutEntity other = (ConsultaTiposPlazoFijoBPrivOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append("headerTrama", other.headerTrama)
				.append("codigoRetornoExtendido", other.codigoRetornoExtendido).append("cantTipoPF", other.cantTipoPF)
				.append("tipoPF", other.tipoPF);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido).append("cantTipoPF", cantTipoPF)
				.append("tipoPF", tipoPF).toString();
	}


}
