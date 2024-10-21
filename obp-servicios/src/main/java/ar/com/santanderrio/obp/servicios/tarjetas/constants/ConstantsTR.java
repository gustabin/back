/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.constants;

/**
 * The Class ConstantsTR.
 */
public final class ConstantsTR {

    /**
     * Instantiates a new constants TR.
     */
    private ConstantsTR() {

    }

    /** The Constant CU. */
    public static final String CU = "CU";

    /** The Constant TXT_00. */
    public static final String TXT_00 = "00";

    /** The Constant DATE_PATERNyyyyMMddHHmmss. */
    public static final String DATE_PATERNyyyyMMddHHmmss = "yyyyMMddHHmmss";

    /** The Constant ERROR. */
    public static final String ERROR = "ERROR";

    /**
	 * Gets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigenAbreviatura
	 *            the tipo cuenta origen abreviatura
	 * @return the tipo cuenta origen
	 */
    public static String getTipoCuentaOrigen(String tipoCuentaOrigenAbreviatura) {
        String tipoCuentaOrigen = null;
        if ("CCP".equals(tipoCuentaOrigenAbreviatura) || "CCD".equals(tipoCuentaOrigenAbreviatura)) {
            tipoCuentaOrigen = "00";
        } else if ("CAP".equals(tipoCuentaOrigenAbreviatura) || "CAD".equals(tipoCuentaOrigenAbreviatura)) {
            tipoCuentaOrigen = "01";
        } else if ("CU".equals(tipoCuentaOrigenAbreviatura) || "CUP".equals(tipoCuentaOrigenAbreviatura)
                || "CUD".equals(tipoCuentaOrigenAbreviatura)) {
            tipoCuentaOrigen = "00";
        }
        return tipoCuentaOrigen;
    }

}
