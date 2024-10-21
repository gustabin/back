/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.annotate.JsonTypeName;

/**
 * The Class PreguntaEncuestaEntity.
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonTypeName("pregunta_enc")
public class PreguntaEncuestaEntity {

    /** The orden pregunta. */
    @JsonProperty("orden_pregunta")
    private String ordenPregunta;

    /** The pregunta. */
    @JsonProperty("pregunta")
    private String pregunta;

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
     * Gets the pregunta.
     *
     * @return the pregunta
     */
	public String getPregunta() {
		return pregunta;
	}

	/**
     * Sets the pregunta.
     *
     * @param pregunta
     *            the new pregunta
     */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
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
		hcb.append(pregunta);
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
		PreguntaEncuestaEntity other = (PreguntaEncuestaEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(ordenPregunta, other.getOrdenPregunta());
		eb.append(pregunta, other.getPregunta());
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
				.append("pregunta", pregunta)
				.toString();
	}
}
