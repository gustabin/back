/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

/**
 * @author B042134
 * 
 *         Valor SI NO string Código o valor único que identifica al elemento,es
 *         el valor que realmente se envía al backend <br/>
 *         Desc SI NO string<br/>
 *         Descripción del Servicio<br/>
 *         ValorPadre NO SI string Id del control padre<br/>
 *         Seleccionado SI NO boolean
 *
 */
public class ItemGenericoMaps {
    
    @NotNull
    @JsonProperty("Valor")
    private Object valor;
    
    @NotNull
    @JsonProperty("Desc")
    private String desc;
    @JsonProperty("ValorPadre")
    private String valorPadre;
    
    @NotNull
    @JsonProperty("Seleccionado")
    private Boolean seleccionado;
    
    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the valorPadre
     */
    public String getValorPadre() {
        return valorPadre;
    }

    /**
     * @param valorPadre
     *            the valorPadre to set
     */
    public void setValorPadre(String valorPadre) {
        this.valorPadre = valorPadre;
    }

    /**
     * @return the seleccionado
     */
    public Boolean getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado
     *            the seleccionado to set
     */
    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    

	public void validate() throws ControlMapValidationException {
	    
	    if(!ValidationEntity.validate(this)) {
            throw new ControlMapValidationException("Validation constraints");
        }
	}
}
