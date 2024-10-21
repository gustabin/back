/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * Objeto utilizado para retornar datos del DAO.
 * 
 * @author juan.pablo.picate
 *
 */
@Record
public class ConsultaGralPlazoFijoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The cantidad tipo pf. */
	@Field
	private String indicadorRellamada;

	/** The cantidad tipo pf. */
	@Field()
	private Long cantRegistros;

	/** The tipo plazo fijo. */
	@Segment(occursRef = "cantRegistros")
	private List<PFGeneralEntity> gralPF = new ArrayList<PFGeneralEntity>();

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
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
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the indicador rellamada.
	 *
	 * @return the indicador rellamada
	 */
	public String getIndicadorRellamada() {
		return indicadorRellamada;
	}

	/**
	 * Sets the indicador rellamada.
	 *
	 * @param indicadorRellamada
	 *            the new indicador rellamada
	 */
	public void setIndicadorRellamada(String indicadorRellamada) {
		this.indicadorRellamada = indicadorRellamada;
	}

	/**
	 * Gets the cant registros.
	 *
	 * @return the cant registros
	 */
	public Long getCantRegistros() {
		return cantRegistros;
	}

	/**
	 * Sets the cant registros.
	 *
	 * @param cantRegistros
	 *            the new cant registros
	 */
	public void setCantRegistros(Long cantRegistros) {
		this.cantRegistros = cantRegistros;
	}

	/**
	 * Gets the gral PF.
	 *
	 * @return the gral PF
	 */
	public List<PFGeneralEntity> getGralPF() {
		return gralPF;
	}

	/**
	 * Sets the gral PF.
	 *
	 * @param gralPF
	 *            the new gral PF
	 */
	public void setGralPF(List<PFGeneralEntity> gralPF) {
		this.gralPF = gralPF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido).append(indicadorRellamada)
				.append(cantRegistros).append(gralPF).toHashCode();
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

		ConsultaGralPlazoFijoOutEntity other = (ConsultaGralPlazoFijoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append("headerTrama", other.headerTrama)
				.append("codigoRetornoExtendido", other.codigoRetornoExtendido)
				.append("indicadorRellamada", other.indicadorRellamada).append("cantRegistros", other.cantRegistros)
				.append("gralPF", gralPF);
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
				.append("codigoRetornoExtendido", codigoRetornoExtendido)
				.append("indicadorRellamada", indicadorRellamada).append("cantRegistros", cantRegistros)
				.append("gralPF", gralPF).toString();
	}

}
