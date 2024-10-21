package ar.com.santanderrio.obp.servicios.campaniabeneficios.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class SuscCampaniaBeneficiosInDTO.
 */
public class SuscCampaniaBeneficiosInDTO {

    /** The idcampania. */
    private String idcampania;

    /** The respuesta. */
    private String respuesta;

    /** The programa. */
    private String programa;

    private String nup;
    
    private String variable1Char;

    public String getIdcampania() {
        return idcampania;
    }

    public void setIdcampania(String idcampania) {
        this.idcampania = idcampania;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPrograma() {
        return programa;
    }

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
        return new HashCodeBuilder().append(idcampania).append(respuesta).append(programa).append(nup).toHashCode();
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

        SuscCampaniaBeneficiosInDTO other = (SuscCampaniaBeneficiosInDTO) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(idcampania, other.getIdcampania());
        eb.append(respuesta, other.getRespuesta());
        eb.append(programa, other.getPrograma());
        eb.append(nup, other.getNup());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idcampania", idcampania).append("respuesta", respuesta)
                .append("programa", programa).append("nup", nup).toString();
    }

    /**
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * @param nup
     *            the nup to set
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

	/**
	 * @return the variable1Char
	 */
	public String getVariable1Char() {
		return variable1Char;
	}

	/**
	 * @param variable1Char the variable1Char to set
	 */
	public void setVariable1Char(String variable1Char) {
		this.variable1Char = variable1Char;
	}

	
}
