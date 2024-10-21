/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChanceDTO;

/**
 * The Class ChanceGrillaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceOutView {
	
	/** The label. */
	private String label;
	
	/** The valorAnterior. */
	private String valorAnterior;
	
	/** The valorActual. */
	private String valorActual;

	/**
	 *Constructor Chance Out View
	 *
	 * @param the chance DTO
	 */
	public ChanceOutView(ChanceDTO chanceDTO) {
		this.label = chanceDTO.getLabel();
		this.valorActual = chanceDTO.getValorActual();
		this.valorAnterior = chanceDTO.getValorAnterior();
	}

	/**
	 *Gets label
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 *Sets label
	 *
	 * @param the label 
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 *Gets valorAnterior
	 *
	 * @return the valorAnterior
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 *Sets valorAnterior
	 *
	 * @param the valorAnterior 
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 *Gets valorActual
	 *
	 * @return the valorActual
	 */
	public String getValorActual() {
		return valorActual;
	}

	/**
	 *Sets valorActual
	 *
	 * @param the valorActual 
	 */
	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	}
	
}
