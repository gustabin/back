package ar.com.santanderrio.obp.servicios.campaniabeneficios.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class CampaniaBeneficiosInView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CampaniaBeneficiosInView {
    /** The idcampania. */
    private String idcampania;
    
    /** The numCampania. */
    private String numCampania;

    /** The respuesta. */
    private String respuesta;

    /** The programa. */
    private String programa;
    
    /** The variable 1 char. */
    private String variable1Char;

    /**
     * Gets the variable 1 char.
     *
     * @return the variable 1 char
     */
    public String getVariable1Char() {
		return variable1Char;
	}

	/**
	 * Sets the variable 1 char.
	 *
	 * @param variable1Char the new variable 1 char
	 */
	public void setVariable1Char(String variable1Char) {
		this.variable1Char = variable1Char;
	}

	/**
     * Gets the idcampania.
     *
     * @return the idcampania
     */
    public String getIdcampania() {
        return idcampania;
    }

    /**
     * Sets the idcampania.
     *
     * @param idcampania
     *            the new idcampania
     */
    public void setIdcampania(String idcampania) {
        this.idcampania = idcampania;
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
     *            the new respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Gets the programa.
     *
     * @return the programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Sets the programa.
     *
     * @param programa
     *            the new programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(idcampania).append(respuesta).append(programa).append(numCampania).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        CampaniaBeneficiosInView other = (CampaniaBeneficiosInView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(idcampania, other.getIdcampania());
        eb.append(respuesta, other.getRespuesta());
        eb.append(programa, other.getPrograma());
        eb.append(variable1Char, other.getVariable1Char());
        eb.append(numCampania, other.getNumCampania());
        
        return eb.isEquals();
    }

    
    public String getNumCampania() {
		return numCampania;
	}

	public void setNumCampania(String numCampania) {
		this.numCampania = numCampania;
	}

	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idcampania", idcampania).append("respuesta", respuesta)
                .append("programa", programa).append("variable1Char", variable1Char).append("numCampania", numCampania).toString();
    }

}
