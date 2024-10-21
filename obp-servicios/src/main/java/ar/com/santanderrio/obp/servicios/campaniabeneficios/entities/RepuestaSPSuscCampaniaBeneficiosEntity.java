package ar.com.santanderrio.obp.servicios.campaniabeneficios.entities;


/**
 * The Class RepuestaSPSuscCampaniaBeneficiosEntity.
 */
public class RepuestaSPSuscCampaniaBeneficiosEntity {

    /** The suscripto. */
    private String suscripto;
    
    /** The error tec. */
    private String errorTec;
    
    /** The error amig. */
    private String errorAmig;
    
    /**
     * Instantiates a new repuesta SP susc campania beneficios entity.
     *
     * @param suscripto the suscripto
     * @param errorTec the error tec
     * @param errorAmig the error amig
     */
    public RepuestaSPSuscCampaniaBeneficiosEntity(String suscripto, String errorTec, String errorAmig) {  
    	this.suscripto = suscripto;
    	this.errorTec = errorTec;
    	this.errorAmig = errorAmig;
    }

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
	
	
	
}
