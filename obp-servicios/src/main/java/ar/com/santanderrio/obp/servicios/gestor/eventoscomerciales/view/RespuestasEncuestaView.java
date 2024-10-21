/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class RespuestasEncuestaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RespuestasEncuestaView {

	/** The orden pregunta. */
    private List<RespuestaEncuestaView> respuestas = new ArrayList<RespuestaEncuestaView>();
    
    /**
     * Instantiates a new respuesta encuesta view.
     */
    public RespuestasEncuestaView() {
        super();
    }

    /**
     * Gets the respuestas.
     *
     * @return the respuestas
     */
	public List<RespuestaEncuestaView> getRespuestas() {
		return respuestas;
	}

	/**
     * Sets the respuestas.
     *
     * @param respuestas
     *            the new respuestas
     */
	public void setRespuestas(List<RespuestaEncuestaView> respuestas) {
		this.respuestas = respuestas;
	}
	
	/**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(respuestas);
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
        RespuestasEncuestaView other = (RespuestasEncuestaView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(respuestas, other.getRespuestas());
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
        return new ToStringBuilder(this).append("respuestas", respuestas).toString();
    }
    
}
