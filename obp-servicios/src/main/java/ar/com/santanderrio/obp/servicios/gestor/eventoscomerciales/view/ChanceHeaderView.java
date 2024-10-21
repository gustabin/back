/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ChanceHeaderView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceHeaderView {
	
	/** The total. */
	private String total;
	
	/** The ayuda. */
	private String ayuda;

	/**
	 *Constructor Chance Header View
	 *
	 * @param the ayuda
	 * @param the total
	 */
	public ChanceHeaderView(String ayuda, String total) {
		this.ayuda = ayuda;
		this.total = total;
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
	 *Gets ayuda
	 *
	 * @return the ayuda
	 */
	public String getAyuda() {
		return ayuda;
	}

	/**
	 *Sets ayuda
	 *
	 * @param the ayuda 
	 */
	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}
	
	

}
