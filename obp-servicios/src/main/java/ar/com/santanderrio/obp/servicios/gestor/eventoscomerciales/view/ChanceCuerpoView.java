/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChanceDTO;

/**
 * The Class ChanceView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceCuerpoView {
	
	/** The listaChances. */
	private List<ChanceOutView> listaChances = new ArrayList<ChanceOutView>();
	
	/** The header. */
	private ChanceOutView header;

	/**
	 *Constructor Chance Cuerpo View
	 *
	 * @param the lista Chance DTO
	 */
	public ChanceCuerpoView(List<ChanceDTO> listaChanceDTO, ChanceDTO header) {
		
		this.header = new ChanceOutView(header);
		
		for (ChanceDTO chanceDTO : listaChanceDTO) {
			this.listaChances.add( new ChanceOutView(chanceDTO) );
		}
	}

	/**
	 *Gets listaChances
	 *
	 * @return the listaChances
	 */
	public List<ChanceOutView> getListaChances() {
		return listaChances;
	}

	/**
	 *Sets listaChances
	 *
	 * @param the listaChances 
	 */
	public void setListaChances(List<ChanceOutView> listaChances) {
		this.listaChances = listaChances;
	}

	/**
	 *Gets header
	 *
	 * @return the header
	 */
	public ChanceOutView getHeader() {
		return header;
	}

	/**
	 *Sets header
	 *
	 * @param the header 
	 */
	public void setHeader(ChanceOutView header) {
		this.header = header;
	}

}
