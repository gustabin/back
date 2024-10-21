package ar.com.santanderrio.obp.servicios.campaniabeneficios.dto;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


// TODO: Auto-generated Javadoc
/**
 * The Class ChequeraOutDTO.
 */
public class CampaniaBeneficiosOutDTO {

    /** The suscripto. */
    private String suscripto;
    
    /** The error tec. */
    private String errorTec;
    
    /** The error amig. */
    private String errorAmig;
    

	/**
	 * Gets the suscripto.
	 *
	 * @return the suscripto
	 */
	public String getSuscripto() {
		return suscripto;
	}

	/**
	 * Sets the suscripto.
	 *
	 * @param suscripto the new suscripto
	 */
	public void setSuscripto(String suscripto) {
		this.suscripto = suscripto;
	}

	/**
	 * Gets the error tec.
	 *
	 * @return the error tec
	 */
	public String getErrorTec() {
		return errorTec;
	}

	/**
	 * Sets the error tec.
	 *
	 * @param errorTec the new error tec
	 */
	public void setErrorTec(String errorTec) {
		this.errorTec = errorTec;
	}

	/**
	 * Gets the error amig.
	 *
	 * @return the error amig
	 */
	public String getErrorAmig() {
		return errorAmig;
	}

	/**
	 * Sets the error amig.
	 *
	 * @param errorAmig the new error amig
	 */
	public void setErrorAmig(String errorAmig) {
		this.errorAmig = errorAmig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(suscripto).append(errorTec).append(errorAmig).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("suscripto", suscripto).append("errorTec", errorTec)
				.append("errorAmig", errorAmig).toString();
	}

}
