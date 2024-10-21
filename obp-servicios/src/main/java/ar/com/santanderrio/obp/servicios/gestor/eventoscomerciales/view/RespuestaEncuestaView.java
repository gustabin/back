/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class RespuestaEncuestaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RespuestaEncuestaView {

	/** The orden pregunta. */
    private String ordenPregunta;

    /** The respuesta. */
    private String respuesta;
    
    /**
     * Instantiates a new respuesta encuesta view.
     */
    public RespuestaEncuestaView() {
        super();
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

	/**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(ordenPregunta);
        hcb.append(respuesta);
        return hcb.toHashCode();
    }
    
    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RespuestaEncuestaView other = (RespuestaEncuestaView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(ordenPregunta, other.getOrdenPregunta());
        eb.append(respuesta, other.getRespuesta());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("ordenPregunta", ordenPregunta)
        		.append("respuesta", respuesta)
        		.toString();
    }
    
}
