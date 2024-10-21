/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

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
 * @author pablo.d.gargaglione
 *
 */
@Record
public class ConsultaTenenciaFCIOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The cantidad tenecias. */
	@Field()
	private Long cantidadTenecias;

	/** The lista result. */
	@Segment(occursRef = "cantidadTenecias")
	private List<ConsultaTenenciaFCIEntity> listaTenencia = new ArrayList<ConsultaTenenciaFCIEntity>();

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
	 * Gets the cantidad tenecias.
	 *
	 * @return the cantidad tenecias
	 */
	public Long getCantidadTenecias() {
		return cantidadTenecias;
	}

	/**
	 * Sets the cantidad tenecias.
	 *
	 * @param cantidadTenecias
	 *            the new cantidad tenecias
	 */
	public void setCantidadTenecias(Long cantidadTenecias) {
		this.cantidadTenecias = cantidadTenecias;
	}

	/**
	 * Gets the lista result.
	 *
	 * @return the lista result
	 */
	public List<ConsultaTenenciaFCIEntity> getListaTenencia() {
		return listaTenencia;
	}

	/**
	 * Sets the lista result.
	 *
	 * @param listaResult
	 *            the new lista result
	 */
	public void setListaTenencia(List<ConsultaTenenciaFCIEntity> listaResult) {
		this.listaTenencia = listaResult;
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
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido).append("cantidadTenecias", cantidadTenecias)
				.append("listaResult", listaTenencia).toString();
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
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoRetornoExtendido);
		hcb.append(cantidadTenecias);
		hcb.append(listaTenencia);
		return hcb.toHashCode();
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
		ConsultaTenenciaFCIOutEntity other = (ConsultaTenenciaFCIOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigoRetornoExtendido, other.codigoRetornoExtendido);
		eb.append(listaTenencia, other.listaTenencia);
		eb.append(cantidadTenecias, other.cantidadTenecias);
		return eb.isEquals();
	}

}
