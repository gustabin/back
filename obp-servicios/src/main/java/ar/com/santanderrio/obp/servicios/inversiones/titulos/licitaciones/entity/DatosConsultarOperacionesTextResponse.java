/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultarOperacionesTextResponse.
 */
public class DatosConsultarOperacionesTextResponse {
    
    /** The codigo error middleware. */
    @JsonProperty("CodigoErrorMiddleware")
    private String codigoErrorMiddleware;
    
    /** The lista operaciones text. */
    @JsonProperty("OperacionesText")
    List<OperacionesTextResponse> listaOperacionesText = new ArrayList<OperacionesTextResponse>();

    /**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigo error middleware
	 */
    public String getCodigoErrorMiddleware() {
        return codigoErrorMiddleware;
    }

    /**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the new codigo error middleware
	 */
    public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
        this.codigoErrorMiddleware = codigoErrorMiddleware;
    }

    /**
	 * Gets the lista operaciones text.
	 *
	 * @return the lista operaciones text
	 */
    public List<OperacionesTextResponse> getListaOperacionesText() {
        return listaOperacionesText;
    }

    /**
	 * Sets the lista operaciones text.
	 *
	 * @param listaOperacionesText
	 *            the new lista operaciones text
	 */
    public void setListaOperacionesText(List<OperacionesTextResponse> listaOperacionesText) {
        this.listaOperacionesText = listaOperacionesText;
    }

}
