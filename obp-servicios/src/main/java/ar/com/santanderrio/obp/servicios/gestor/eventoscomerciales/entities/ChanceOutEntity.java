/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ChanceOutEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceOutEntity{
	
	/** The nup. */
    @Pattern(regexp = "^\\d*$")
    @NotNull
    @JsonProperty("nup")
    private String nup;
    
    /** The codigo error. */
    @Pattern(regexp = "ERROR000")
    @NotNull
    @JsonProperty("codigoError")
    private String codigoError;

    /** The descripcion error. */
    @JsonProperty("descripcionError")
    private String descripcionError;
	
    /** The total premiaciones. */
    @JsonProperty("total_premiaciones")
	private String total_premiaciones;
	
    /** The chances grilla out entity. */
    @JsonProperty("premiaciones")
    private List<ChanceGrillaOutEntity> chancesGrillaOutEntity;

	/**
	 *Gets nup
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 *Sets nup
	 *
	 * @param the nup 
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 *Gets codigoError
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 *Sets codigoError
	 *
	 * @param the codigoError 
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 *Gets descripcionError
	 *
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 *Sets descripcionError
	 *
	 * @param the descripcionError 
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 *Gets total_premiaciones
	 *
	 * @return the total_premiaciones
	 */
	public String getTotal_premiaciones() {
		return total_premiaciones;
	}

	/**
	 *Sets total_premiaciones
	 *
	 * @param the total_premiaciones 
	 */
	public void setTotal_premiaciones(String total_premiaciones) {
		this.total_premiaciones = total_premiaciones;
	}

	/**
	 *Gets chancesGrillaOutEntity
	 *
	 * @return the chancesGrillaOutEntity
	 */
	public List<ChanceGrillaOutEntity> getChancesGrillaOutEntity() {
		return chancesGrillaOutEntity;
	}

	/**
	 *Sets chancesGrillaOutEntity
	 *
	 * @param the chancesGrillaOutEntity 
	 */
	public void setChancesGrillaOutEntity(List<ChanceGrillaOutEntity> chancesGrillaOutEntity) {
		this.chancesGrillaOutEntity = chancesGrillaOutEntity;
	}
    
}