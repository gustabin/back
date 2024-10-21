/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

/**
 * @author B042134
 * 
 *         Checked SI NO bolean Valor por defecto del checkbox <br/>
 *         TextoLink NO NO string Texto que se muestra al hacer clic en la
 *         etiqueta del checkbox, de no venir este campo significa que no hay
 *         texto para mostrar al hacer clic en la etiqueta del checkbox<br/>
 *         Etiqueta SI NO string Descripción del elemento<br/>
 *         Posicion SI NO string Posición del elemento<br/>
 *         Requerido SI NO string Indica que para poder avanzar se debe aceptar
 *         esta condicion necesariamente<br/>
 *
 */
public class ItemLegal {
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonProperty("TextoLink")
    private String textoLink;
    @NotNull
    @JsonProperty("Etiqueta")
    private String etiqueta;
    @NotNull
    @JsonProperty("Posicion")
    private String posicion;
    @NotNull
    @JsonProperty("Requerido")
    private Boolean requerido;
    @NotNull
    @JsonProperty("Checked")
    private Boolean checked;

    /**
     * @return the textoLink
     */
    public String getTextoLink() {
        return textoLink;
    }

    /**
     * @param textoLink
     *            the textoLink to set
     */
    public void setTextoLink(String textoLink) {
        this.textoLink = textoLink;
    }

    /**
     * @return the etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta
     *            the etiqueta to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the posicion
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * @param posicion
     *            the posicion to set
     */
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the requerido
     */
    public Boolean getRequerido() {
        return requerido;
    }

    /**
     * @param requerido
     *            the requerido to set
     */
    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    /**
     * @return the checked
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    
    public Integer posicionInt() {
        try {
            return new Integer(this.getPosicion());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

	public void validate() throws ControlMapValidationException{
        
        if(!ValidationEntity.validate(this)) {
            throw new ControlMapValidationException("Validation constraints");
        }
	}
    
}
