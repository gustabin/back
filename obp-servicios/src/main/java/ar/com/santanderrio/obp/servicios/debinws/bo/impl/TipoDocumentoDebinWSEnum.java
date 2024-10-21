/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.bo.impl;


/**
 * TipoDocumentoDebinWSEnum.
 */
public enum TipoDocumentoDebinWSEnum {

    /** The dni. */
    DNI("DNI", "N", "1"),

    /** The lc. */
    LC("LC", "C","4"),

    /** The le. */
    LE("LE", "E","5"),

    /** The ext. */
    EXT("EXT", "X","1"),

    /** The pas. */
    PAS("PAS", "P", "3"),

    /** The ci. */
    CI("CI", "I", "2");

    /** The campo. */
    String descripcion;

    /** The codigo letra. */
    String codigoLetra;
    
    /** The codigo numero. */
    String codigoNumero;

    /**
	 * getDescripcion.
	 *
	 * @return descripcion
	 */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * setDescripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * getCodigoLetra
     * @return codigoLetra
     */
    public String getCodigoLetra() {
        return codigoLetra;
    }

    /**
     * 
     * @param codigoLetra
     */
    public void setCodigoLetra(String codigoLetra) {
        this.codigoLetra = codigoLetra;
    }

    /**
     * getCodigoNumero
     * @return CodigoNumero
     */
    public String getCodigoNumero() {
        return codigoNumero;
    }

    /**
     * 
     * @param codigoNumero
     */
    public void setCodigoNumero(String codigoNumero) {
        this.codigoNumero = codigoNumero;
    }


    /**
	 * Instantiates a new tipo documento debin WS enum.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @param codigoLetra
	 *            the codigo letra
	 * @param codigoNumero
	 *            the codigo numero
	 */
    TipoDocumentoDebinWSEnum(String descripcion, String codigoLetra, String codigoNumero) {
        this.descripcion = descripcion;
        this.codigoLetra = codigoLetra;
        this.codigoNumero = codigoNumero;
    }

   
    /**
	 * Gets the tipo documento debin WS.
	 *
	 * @param codigoLetra
	 *            the codigo letra
	 * @return the tipo documento debin WS
	 */
    public static TipoDocumentoDebinWSEnum getTipoDocumentoDebinWS(String codigoLetra) {
        TipoDocumentoDebinWSEnum[] values = TipoDocumentoDebinWSEnum.values();
        for (TipoDocumentoDebinWSEnum tipoDoc : values) {
            if (codigoLetra.trim().equalsIgnoreCase(tipoDoc.getCodigoLetra())) {
                return tipoDoc;
            }
        }
        return null;
    }
    
    
}
