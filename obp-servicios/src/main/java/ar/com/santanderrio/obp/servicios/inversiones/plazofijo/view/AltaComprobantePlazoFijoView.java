/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class AltaComprobantePlazoFijoView.
 */
public class AltaComprobantePlazoFijoView {
    
    /** The codigo plazo. */
    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String codigoPlazo;

    /**
	 * Gets the codigo plazo.
	 *
	 * @return the codigoPlazo
	 */
    public String getCodigoPlazo() {
        return codigoPlazo;
    }

    /**
	 * Sets the codigo plazo.
	 *
	 * @param codigoPlazo
	 *            the codigoPlazo to set
	 */
    public void setCodigoPlazo(String codigoPlazo) {
        this.codigoPlazo = codigoPlazo;
    }
  

}
