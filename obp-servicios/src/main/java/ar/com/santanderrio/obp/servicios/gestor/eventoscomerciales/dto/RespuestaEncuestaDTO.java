/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.RespuestaEncuestaView;

/**
 * The Class RespuestaEncuestaDTO.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RespuestaEncuestaDTO {

	/** The orden pregunta. */
	@JsonProperty("orden_pregunta")
    private String ordenPregunta;

    /** The respuesta. */
    @JsonProperty("respuesta")
    private String respuesta;
    
    /**
	 * Instantiates a new respuesta encuesta DTO.
	 *
	 * @param respuestaEncuestaView
	 *            the respuesta encuesta view
	 */
    public RespuestaEncuestaDTO(RespuestaEncuestaView respuestaEncuestaView) {
        this.ordenPregunta = respuestaEncuestaView.getOrdenPregunta();
        this.respuesta = respuestaEncuestaView.getRespuesta();
    }

    /**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public String getOrdenPregunta() {
		return ordenPregunta;
	}

	/**
     * Sets the orden pregunta.
     *
     * @param ordenPregunta
     *            the new orden pregunta
     */
	public void setOrdenPregunta(String ordenPregunta) {
		this.ordenPregunta = ordenPregunta;
	}

	/**
     * Gets the respuesta.
     *
     * @return the respuesta
     */
	
	public String getRespuesta() {
		return respuesta;
	}

	/**
     * Sets the respuesta.
     *
     * @param respuesta
     *            the respuesta
     */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
