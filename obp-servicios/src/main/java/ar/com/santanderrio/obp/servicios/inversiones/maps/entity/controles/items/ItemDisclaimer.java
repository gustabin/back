package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ItemDisclaimer {

    @NotNull
    @JsonProperty("Tipodisclaimer")
    private Integer tipoDisclaimer;

    @JsonProperty("Valor")
    private Object valor;

    @JsonProperty("Desc")
    private String desc;
    @JsonProperty("ValorPadre")
    private String valorPadre;


    /**
     * @return the tipoDisclaimer
     */
    public Integer getTipoDisclaimer() {
        return tipoDisclaimer;
    }

    /**
     * @param tipoDisclaimer
     *            the tipoDisclaimer to set
     */
    public void setTipoDisclaimer(Integer tipoDisclaimer) {
        this.tipoDisclaimer = tipoDisclaimer;
    }

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


    public void validate() throws ControlMapValidationException {

        if (!ValidationEntity.validate(this)) {
            throw new ControlMapValidationException("Validation constraints");
        	
        }
    }

}
