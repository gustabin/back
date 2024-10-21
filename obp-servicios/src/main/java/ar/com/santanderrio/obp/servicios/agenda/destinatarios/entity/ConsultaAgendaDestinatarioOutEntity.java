/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author
 *
 */
@Record
public class ConsultaAgendaDestinatarioOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** Indicador de Agendado. */
	@Field
	private String indAgendado;

	/** Campo agregado a modo de fix temporal del servicio de agenda. */
	@Field
	private String variableExtra;

	/** Indicador de mas registros. */
	@Field
	private String indMasReg;

	/** Cantidad de registros. */
	@Field
	private Long cantidadRegistros;

	/** The fondos. */
	@Segment(occursRef = "cantidadRegistros")
	private List<DestinatarioEntity> destinatarios = new ArrayList<DestinatarioEntity>();

	/**
	 * Letra S.
	 */
	private static final String LETRA_S = "S";

	/**
	 * Indica si hay error de rellamada.
	 */
	private Boolean errorRellamada = Boolean.FALSE;

	/**
	 * Instantiates a new consulta agenda destinatario out entity.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigo retorno extendido
	 */
	public ConsultaAgendaDestinatarioOutEntity(String codigoRetornoExtendido) {
		super();
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Instantiates a new consulta agenda destinatario out entity.
	 */
	public ConsultaAgendaDestinatarioOutEntity() {
		super();
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

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
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido).toHashCode();
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

		ConsultaAgendaDestinatarioOutEntity other = (ConsultaAgendaDestinatarioOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(headerTrama, other.headerTrama).append(codigoRetornoExtendido, other.codigoRetornoExtendido)
				.isEquals();
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
		return new ToStringBuilder(this).append("indAgendado", indAgendado)
				.append("CodigoRetornoExtendido", codigoRetornoExtendido).append("indMasReg", indMasReg)
				.append("cantidadRegistros", cantidadRegistros).append("destinatarios", destinatarios).toString();
	}

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
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the ind agendado.
	 *
	 * @return the indAgendado
	 */
	public String getIndAgendado() {
		return indAgendado;
	}

	/**
	 * Sets the ind agendado.
	 *
	 * @param indAgendado
	 *            the indAgendado to set
	 */
	public void setIndAgendado(String indAgendado) {
		this.indAgendado = indAgendado;
	}

	/**
	 * Gets the ind mas reg.
	 *
	 * @return the indMasReg
	 */
	public String getIndMasReg() {
		return indMasReg;
	}

	/**
	 * Sets the ind mas reg.
	 *
	 * @param indMasReg
	 *            the indMasReg to set
	 */
	public void setIndMasReg(String indMasReg) {
		this.indMasReg = indMasReg;
	}

	/**
	 * Gets the cantidad registros.
	 *
	 * @return the cantidadRegistros
	 */
	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}

	/**
	 * Sets the cantidad registros.
	 *
	 * @param cantidadRegistros
	 *            the cantidadRegistros to set
	 */
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * Gets the destinatarios.
	 *
	 * @return the destinatarios
	 */
	public List<DestinatarioEntity> getDestinatarios() {
		return destinatarios;
	}

	/**
	 * Sets the destinatarios.
	 *
	 * @param destinatarios
	 *            the destinatarios to set
	 */
	public void setDestinatarios(List<DestinatarioEntity> destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * Retorna si hay mas registros 'S'.equals a indMasReg
	 *
	 * @return the hay mas registros
	 */
	public Boolean getHayMasRegistros() {
		return LETRA_S.equals(this.getIndMasReg());
	}

	/**
	 * Gets the error rellamada.
	 *
	 * @return the errorRellamada
	 */
	public Boolean getErrorRellamada() {
		return errorRellamada;
	}

	/**
	 * Sets the error rellamada.
	 *
	 * @param errorRellamada
	 *            the errorRellamada to set
	 */
	public void setErrorRellamada(Boolean errorRellamada) {
		this.errorRellamada = errorRellamada;
	}

	/**
	 * Gets the variable extra.
	 *
	 * @return the variable extra
	 */
	public String getVariableExtra() {
		return variableExtra;
	}

	/**
	 * Sets the variable extra.
	 *
	 * @param variableExtra
	 *            the new variable extra
	 */
	public void setVariableExtra(String variableExtra) {
		this.variableExtra = variableExtra;
	}

}
