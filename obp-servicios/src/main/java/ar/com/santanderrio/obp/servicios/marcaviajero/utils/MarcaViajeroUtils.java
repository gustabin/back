/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class MarcaViajeroUtils.
 */
public class MarcaViajeroUtils {

    /** The Constant DNI. */
    private static final String DNI = "DNI";

    /** The Constant LC. */
    private static final String LC = "LC";

    /** The Constant LE. */
    private static final String LE = "LE";

    /** The Constant _. */
    private static final String _ = "_";

    /** The Constant PAS. */
    private static final String PAS = "PAS";

    /** The Constant CI. */
    private static final String CI = "CI";

    /** The Constant FORMATO_FECHA_DESDE_HASTA. */
    private static final String FORMATO_FECHA_DESDE_HASTA = "dd/MM/yyyy";

    /** The Constant FORMATO_FECHA_VIAJE. */
    private static final String FORMATO_FECHA_DDMMMM = "dd-MMMMM";

    /**
	 * Instantiates a new marca viajero utils.
	 */
    private MarcaViajeroUtils() {}
    
    /**
     * Formatear destino camel case.
     *
     * @param descripcionDestino
     *            the descripcion destino
     * @return the string
     */
    public static String formatearDestinoCamelCase(String descripcionDestino) {
        return ISBANStringUtils
                .convertirStringToCamelcase(descripcionDestino != null
                        ? descripcionDestino.replaceAll("#", "Ñ") : null);
    }

    /**
     * Formatear destino mayusculas.
     *
     * @param descripcionDestino
     *            the descripcion destino
     * @return the string
     */
    public static String formatearDestinoMayusculas(String descripcionDestino) {
        return descripcionDestino != null
                ? descripcionDestino.toUpperCase().replaceAll("Ñ", "#") : null;
    }

    /**
     * Gets the sexo.
     *
     * @param sexo
     *            the sexo
     * @return the sexo
     */
    public static String getSexo(String sexo) {
        return "H".equals(sexo) ? "M" : "F";
    }

    /**
     * Gets the tipo documento.
     *
     * @param tipoDocumento
     *            the tipo documento
     * @return the tipo documento
     */
    public static String getTipoDocumento(String tipoDocumento) {
        String tipoDoc = "";
        if ("N".equals(tipoDocumento)) {
            tipoDoc = DNI;
        }
        if ("C".equals(tipoDocumento)) {
            tipoDoc = LC;
        }
        if ("E".equals(tipoDocumento)) {
            tipoDoc = LE;
        }
        if ("I".equals(tipoDocumento)) {
            tipoDoc = _;
        }
        if ("P".equals(tipoDocumento)) {
            tipoDoc = PAS;
        }
        if ("T".equals(tipoDocumento)) {
            tipoDoc = _;
        }
        if ("L".equals(tipoDocumento)) {
            tipoDoc = _;
        }
        if ("D".equals(tipoDocumento)) {
            tipoDoc = CI;
        }
        if ("X".equals(tipoDocumento)) {
            tipoDoc = DNI;
        }
        if ("F".equals(tipoDocumento)) {
            tipoDoc = _;
        }
        return tipoDoc;
    }

    /**
     * Aplicar formato fecha DDMMYYYY.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    public static String aplicarFormatoFechaDDMMYYYY(Date fecha) {
        String fechaString = "";
        SimpleDateFormat formatoFecha = new SimpleDateFormat(
                FORMATO_FECHA_DESDE_HASTA);
        fechaString = formatoFecha.format(fecha);
        return fechaString;
    }

    /**
     * Aplicar formato fecha viaje.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    public static String aplicarFormatoFechaDDMMMM(Date fecha) {
        String fechaString = "";
        SimpleDateFormat formatoFecha = new SimpleDateFormat(
                FORMATO_FECHA_DDMMMM, new Locale("es", "ES"));
        String[] strings = formatoFecha.format(fecha).split("-");
        fechaString = strings[0] + " de " + strings[1];
        return fechaString;
    }

}
