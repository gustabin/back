/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaTasasPlazoFijoOutDTO {

	/** The codigo retorno extendido. */
	String codigoRetornoExtendido;

	/** The cant tipo PF. */
	Long cantTipoPF;

	/** The tipo PF. */
	List<ConsultaTasasPlazoFijoDTO> tipoPF = new ArrayList<ConsultaTasasPlazoFijoDTO>();

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
	 * Gets the cant tipo PF.
	 *
	 * @return the cantTipoPF
	 */
	public Long getCantTipoPF() {
		return cantTipoPF;
	}

	/**
	 * Sets the cant tipo PF.
	 *
	 * @param cantTipoPF
	 *            the cantTipoPF to set
	 */
	public void setCantTipoPF(Long cantTipoPF) {
		this.cantTipoPF = cantTipoPF;
	}

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipoPF
	 */
	public List<ConsultaTasasPlazoFijoDTO> getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the tipoPF to set
	 */
	public void setTipoPF(List<ConsultaTasasPlazoFijoDTO> tipoPF) {
		this.tipoPF = tipoPF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigoRetornoExtendido", codigoRetornoExtendido)
				// .append("cantTipoPF", cantTipoPF)
				// .append("tipoPF", tipoPF)
				.toString();
	}
}
