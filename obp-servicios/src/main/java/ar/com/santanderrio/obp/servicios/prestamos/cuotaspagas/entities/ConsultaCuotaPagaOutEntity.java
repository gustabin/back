/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class ConsultaCuotaPagaOutEntity.
 *
 * @author florencia.n.martinez
 */
@Record
public class ConsultaCuotaPagaOutEntity {

	/** The Constant LETRA_S. */
	private static final Object LETRA_S = "S";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The marca rellamada. */
	@Field
	private String marcaRellamada;

	/** The timestamp. */
	@Field
	private String timestamp;

	/** The codconli. */
	@Field
	private String codconli;

	/** The num secuencia. */
	@Field
	private String numSecuencia;

	/** The cantidad ocurrencias. */
	@Field
	private Long cantidadOcurrencias;

	/** The cuotas pagas. */
	@Segment(occursRef = "cantidadOcurrencias")
	private List<PrestamoCuotaPagaOutEntity> cuotasPagas = new ArrayList<PrestamoCuotaPagaOutEntity>();

	/** The error rellamada. */
	private Boolean errorRellamada = Boolean.FALSE;

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
	 * Gets the marca rellamada.
	 *
	 * @return the marcaRellamada
	 */
	public String getMarcaRellamada() {
		return marcaRellamada;
	}

	/**
	 * Sets the marca rellamada.
	 *
	 * @param marcaRellamada
	 *            the marcaRellamada to set
	 */
	public void setMarcaRellamada(String marcaRellamada) {
		this.marcaRellamada = marcaRellamada;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the codconli.
	 *
	 * @return the codconli
	 */
	public String getCodconli() {
		return codconli;
	}

	/**
	 * Sets the codconli.
	 *
	 * @param codconli
	 *            the codconli to set
	 */
	public void setCodconli(String codconli) {
		this.codconli = codconli;
	}

	/**
	 * Gets the num secuencia.
	 *
	 * @return the numSecuencia
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}

	/**
	 * Sets the num secuencia.
	 *
	 * @param numSecuencia
	 *            the numSecuencia to set
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidadOcurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the cantidadOcurrencias to set
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the cuotas pagas.
	 *
	 * @return the cuotasPagas
	 */
	public List<PrestamoCuotaPagaOutEntity> getCuotasPagas() {
		return cuotasPagas;
	}

	/**
	 * Sets the cuotas pagas.
	 *
	 * @param cuotasPagas
	 *            the cuotasPagas to set
	 */
	public void setCuotasPagas(List<PrestamoCuotaPagaOutEntity> cuotasPagas) {
		this.cuotasPagas = cuotasPagas;
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
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadOcurrencias);
		hcb.append(codconli);
		hcb.append(codigoRetornoExtendido);
		hcb.append(headerTrama);
		hcb.append(marcaRellamada);
		hcb.append(numSecuencia);
		hcb.append(timestamp);
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
		ConsultaCuotaPagaOutEntity other = (ConsultaCuotaPagaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadOcurrencias, other.getCantidadOcurrencias());
		eb.append(codconli, other.getCodconli());
		eb.append(codigoRetornoExtendido, other.getCodigoRetornoExtendido());
		eb.append(headerTrama, other.getHeaderTrama());
		eb.append(marcaRellamada, other.getMarcaRellamada());
		eb.append(numSecuencia, other.getNumSecuencia());
		eb.append(timestamp, other.getTimestamp());
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
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido).append("marcaRellamada", marcaRellamada)
				.append("timestamp", timestamp).append("codconli", codconli).append("numSecuencia", numSecuencia)
				.append("cantidadOcurrencias", cantidadOcurrencias).append("cuotasPagas", cuotasPagas)
				.append("errorRellamada", errorRellamada).toString();
	}

	/**
	 * Verifica si tiene que efectuar la rellamada al servicio.
	 *
	 * @return the hay mas registros
	 */
	public Boolean getHayMasRegistros() {
		return LETRA_S.equals(this.getMarcaRellamada());
	}

	/**
	 * Agregar resultados.
	 *
	 * @param consultaCuotaPagaOutEntityRellamada
	 *            the consulta cuota paga out entity rellamada
	 */
	public void agregarResultados(ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntityRellamada) {
		this.timestamp = consultaCuotaPagaOutEntityRellamada.getTimestamp();
		this.codconli = consultaCuotaPagaOutEntityRellamada.getCodconli();
		this.numSecuencia = consultaCuotaPagaOutEntityRellamada.getNumSecuencia();
		this.marcaRellamada = consultaCuotaPagaOutEntityRellamada.getMarcaRellamada();
		if (!consultaCuotaPagaOutEntityRellamada.getCuotasPagas().isEmpty()) {
			this.cantidadOcurrencias += consultaCuotaPagaOutEntityRellamada.getCantidadOcurrencias();
			this.cuotasPagas.addAll(consultaCuotaPagaOutEntityRellamada.getCuotasPagas());
		}
	}

}