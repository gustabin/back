package ar.com.santanderrio.obp.servicios.debinrecurrente.entity;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class ConsultaDebinOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;
	
	/** The cantidad registros. */
	@Field
	private Long cantReg;
	
	@Segment(occursRef = "cantReg")
	private List<DebinEntity> listaDebines;
	
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

	public List<DebinEntity> getListaDebines() {
		return listaDebines;
	}

	public void setListaDebines(List<DebinEntity> listaDebines) {
		this.listaDebines = listaDebines;
	}

	public Long getCantReg() {
		return cantReg;
	}

	public void setCantReg(Long cantReg) {
		this.cantReg = cantReg;
	}
	
}
