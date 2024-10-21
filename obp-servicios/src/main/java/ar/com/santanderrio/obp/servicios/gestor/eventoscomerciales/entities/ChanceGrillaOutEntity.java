/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ChanceGrillaOutEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceGrillaOutEntity{
	
	/** The periodo. */
    @JsonProperty("periodo")
	private String periodo;
	
    /** The chances acreditacion. */
    @JsonProperty("chances_acreditacion")
	private Integer chances_acreditacion;
	
    /** The chances td. */
    @JsonProperty("chances_td")
	private Integer chances_td;
	
    /** The chances visa. */
    @JsonProperty("chances_visa")
	private Integer chances_visa;
	
    /** The chances amex. */
    @JsonProperty("chances_amex")
	private Integer chances_amex;

    /** The chances master. */
    @JsonProperty("chances_master")
    private Integer chances_master;
	
    /** The total. */
    @JsonProperty("total")
	private Integer total;
	
    /** The caducidad. */
    @JsonProperty("caducidad")
	private String caducidad;

	/**
	 * Gets the periodo.
	 * 
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 * 
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the chances acreditacion.
	 * 
	 * @return the chances_acreditacion
	 */
	public Integer getChances_acreditacion() {
		return chances_acreditacion;
	}

	/**
	 * Sets the chances acreditacion.
	 * 
	 * @param chances_acreditacion the chances_acreditacion to set
	 */
	public void setChances_acreditacion(Integer chances_acreditacion) {
		this.chances_acreditacion = chances_acreditacion;
	}

	/**
	 * Gets the chances td.
	 * 
	 * @return the chances_td
	 */
	public Integer getChances_td() {
		return chances_td;
	}

	/**
	 * Sets the chances td.
	 * 
	 * @param chances_td the chances_td to set
	 */
	public void setChances_td(Integer chances_td) {
		this.chances_td = chances_td;
	}

	/**
	 * Gets the chances visa.
	 * 
	 * @return the chances_visa
	 */
	public Integer getChances_visa() {
		return chances_visa;
	}

	/**
	 * Sets the chances visa
	 * 
	 * @param chances_visa the chances_visa to set
	 */
	public void setChances_visa(Integer chances_visa) {
		this.chances_visa = chances_visa;
	}

	/**
	 * Gets the chances amex
	 * 
	 * @return the chances_amex
	 */
	public Integer getChances_amex() {
		return chances_amex;
	}
    
	/**
	 * Sets the chances amex
	 * 
	 * @param chances_amex the chances_amex to set
	 */
	public void setChances_amex(Integer chances_amex) {
		this.chances_amex = chances_amex;
	}

	public Integer getChances_master() {
	    return chances_master;
	}
	
	public void setChances_master(Integer chancesMaster) {
        this.chances_master = chancesMaster;
    }


	/**
	 * Gets the total
	 * 
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * Sets the total
	 * 
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * Gets the caducidad
	 * 
	 * @return the caducidad
	 */
	public String getCaducidad() {
		return caducidad;
	}

	/**
	 * Sets the caducidad
	 * 
	 * @param caducidad the caducidad to set
	 */
	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	/**
	 * compara dos objetos Chance Grilla Out Entity
	 * 
	 * @param ChanceGrillaOutEntity
	 * @return the int
	 */
	public int compareTo(ChanceGrillaOutEntity chance) {
		int numChanceMes = Integer.valueOf(this.periodo.trim());
		int numChanceMesDos = Integer.valueOf(chance.periodo.trim());
		return numChanceMes - numChanceMesDos;
	}
	
}
