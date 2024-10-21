/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ChancesDTO.
 *
 */
public class ChancesDTO {
	
	/** The listaChances. */
	private List<ChanceDTO> listaChances = new ArrayList<ChanceDTO>();
	
	/** The header. */
	private ChanceDTO header;
	
	/** The total. */
	private String total;
	
	/** The legal. */
	private String legal;

	/**
	 *Gets listaChances
	 *
	 * @return the listaChances
	 */
	public List<ChanceDTO> getListaChances() {
		return listaChances;
	}

	/**
	 *Sets listaChances
	 *
	 * @param the listaChances 
	 */
	public void setListaChances(List<ChanceDTO> listaChances) {
		this.listaChances = listaChances;
	}

	/**
	 *Gets header
	 *
	 * @return the header
	 */
	public ChanceDTO getHeader() {
		return header;
	}

	/**
	 *Sets header
	 *
	 * @param the header 
	 */
	public void setHeader(ChanceDTO header) {
		this.header = header;
	}

	/**
	 *Gets total
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 *Sets total
	 *
	 * @param the total 
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 *Gets legal
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 *Sets legal
	 *
	 * @param the legal 
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}
	
}
