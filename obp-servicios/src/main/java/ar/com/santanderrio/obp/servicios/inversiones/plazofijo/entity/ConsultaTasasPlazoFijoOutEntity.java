/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

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
public class ConsultaTasasPlazoFijoOutEntity {

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

	/**
	 * Lista de todas las tasas, de todos los periodos, de todos los plazos
	 * fijos.
	 */
	@Segment(occursRef = "cantTipoPF")
	private List<TasasPlazoFijoEntity> listaTasas = new ArrayList<TasasPlazoFijoEntity>();

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
	public List<TasasPlazoFijoEntity> getListaTasas() {
		return listaTasas;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param listaTasas
	 *            the tipoPF to set
	 */
	public void setListaTasas(List<TasasPlazoFijoEntity> listaTasas) {
		this.listaTasas = listaTasas;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				// .append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido)
				// .append("cantTipoPF", cantTipoPF)
				// .append("tipoPF", tipoPF)
				.toString();
	}

}
