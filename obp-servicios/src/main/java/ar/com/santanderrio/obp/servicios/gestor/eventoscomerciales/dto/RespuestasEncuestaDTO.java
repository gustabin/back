/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.RespuestaEncuestaView;

/**
 * The Class RespuestasEncuestaDTO.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RespuestasEncuestaDTO {

	/** The respuesta encuesta. */
	@JsonProperty("respuesta_enc")
    private RespuestaEncuestaDTO respuestaEncuesta;
    
    /**
	 * Instantiates a new respuestas encuesta DTO.
	 *
	 * @param respuestaEncuestaView
	 *            the respuesta encuesta view
	 */
    public RespuestasEncuestaDTO(RespuestaEncuestaView respuestaEncuestaView) {
    	this.respuestaEncuesta = new RespuestaEncuestaDTO(respuestaEncuestaView);
    }
    
	/**
     * Gets the respuesta encuesta.
     *
     * @return the respuestaEncuesta
     */
	
	public RespuestaEncuestaDTO getRespuestaEncuesta() {
		return respuestaEncuesta;
	}

	/**
     * Sets the respuesta encuesta.
     *
     * @param respuestaEncuesta
     *            the respuesta encuesta
     */
	public void setRespuestaEncuesta(RespuestaEncuestaDTO respuestaEncuesta) {
		this.respuestaEncuesta = respuestaEncuesta;
	}
}
