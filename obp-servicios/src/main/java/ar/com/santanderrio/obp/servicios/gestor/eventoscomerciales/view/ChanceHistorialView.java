/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * the class ChanceHistorialView
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceHistorialView {

	/** The anioMax. */
	private Integer anioMax;
	
	/** The anioMin. */
	private Integer anioMin;
	
	/** The mesMax. */
	private Integer mesMax;
	
	/** The mesMin. */
	private Integer mesMin;
	
	/** The constructor ChanceHistorialView. */
	public ChanceHistorialView() {
		super();
	}

	/** The constructor ChanceHistorialView parametrisado. */
	public ChanceHistorialView(String cotaMin, String cotaMax) {
		//formato de la fecha: aaaaMM
		this.anioMin = Integer.valueOf(cotaMin.substring(0, 4));
		this.mesMin = Integer.valueOf(cotaMin.substring(4));
		this.anioMax = Integer.valueOf(cotaMax.substring(0, 4));
		this.mesMax = Integer.valueOf(cotaMax.substring(4));
	}

	/**
	 *Gets anioMax
	 *
	 * @return the anioMax
	 */
	public Integer getAnioMax() {
		return anioMax;
	}

	/**
	 *Sets anioMax
	 *
	 * @param the anioMax 
	 */
	public void setAnioMax(Integer anioMax) {
		this.anioMax = anioMax;
	}

	/**
	 *Gets anioMin
	 *
	 * @return the anioMin
	 */
	public Integer getAnioMin() {
		return anioMin;
	}

	/**
	 *Sets anioMin
	 *
	 * @param the anioMin 
	 */
	public void setAnioMin(Integer anioMin) {
		this.anioMin = anioMin;
	}

	/**
	 *Gets mesMax
	 *
	 * @return the mesMax
	 */
	public Integer getMesMax() {
		return mesMax;
	}

	/**
	 *Sets mesMax
	 *
	 * @param the mesMax 
	 */
	public void setMesMax(Integer mesMax) {
		this.mesMax = mesMax;
	}

	/**
	 *Gets mesMin
	 *
	 * @return the mesMin
	 */
	public Integer getMesMin() {
		return mesMin;
	}

	/**
	 *Sets mesMin
	 *
	 * @param the mesMin 
	 */
	public void setMesMin(Integer mesMin) {
		this.mesMin = mesMin;
	}
	
	
}
