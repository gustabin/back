/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;

/**
 * Clase con constantes y utilitarias para modulo de tarjetas.
 * 
 * @author sergio.e.goldentair
 *
 */
public final class TarjetaUtils {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaUtils.class);

    /** Codigo-Banco VISA 042. */
    public static final String CODIGO_BANCO_VISA = "072";

    /** Codigo-Banco AMEX 4/2. */
    public static final String CODIGO_BANCO_AMEX = "472";

    /** Codigo-Banco MASTER. */
    public static final String CODIGO_BANCO_MASTER = "172";

    /** The Constant TITULARIDAD_TITULAR. */
    public static final String CODIGO_TITULARIDAD_TITULAR = "TI";

    /** The Constant TITULARIDAD_ADICIONAL. */
    public static final String CODIGO_TITULARIDAD_ADICIONAL = "AD";

    /** The Constant VISA_RECARGABLE. */
    public static final String VISA_RECARGABLE = "T";

    /** The Constant FECHA_CIERRE . */
    public static final String FECHA_CIERRE = "proximo";

    /** The Constant FECHA_PROXIMO_VENCIMIENTO . */
    public static final String FECHA_VENCIMIENTO = "proximo";

    /** The Constant DESCRIPCION_ACTUAL. */
    public static final String DESCRIPCION_ACTUAL = "actual";

    /** The Constant DESCRIPCION_ANTERIOR. */
    public static final String DESCRIPCION_ANTERIOR = "anterior";

    /** The Constant DESCRIPCION_PAGO_MINIMO. */
    public static final String DESCRIPCION_PAGO_MINIMO = "minimo";

    /** The Constant TIPO_MONEDA_PAGO_MINIMO. */
    public static final String TIPO_MONEDA_PAGO_MINIMO = "pesos";

    /** The Constant DESCRIPCION_LIMITE_FINANCIAMIENTO. */
    public static final String DESCRIPCION_LIMITE_FINANCIAMIENTO = "financiero";

    /** The Constant DESCRIPCION_LIMITE_COMPRA. */
    public static final String DESCRIPCION_LIMITE_COMPRA = "compra";

    /** The Constant DESCRIPCION_LIMITE_COMPRA_CUOTAS. */
    public static final String DESCRIPCION_LIMITE_COMPRA_CUOTAS = "compracuotas";

    /** The Constant LIMITE_UNIFICADO . */
    public static final String LIMITE_UNIFICADO = "S";

    /** The Constant DISPONIBLE_COMPRAS . */
    public static final String DISPONIBLE_COMPRAS = "compradisp";

    /** The Constant DISPONIBLE_CUOTAS. */
    public static final String DISPONIBLE_CUOTAS = "compracuotasdisp";

    /** The Constant LIMITE_COMPRAS. */
    public static final String LIMITE_COMPRAS = "compra";

    /** The Constant LIMITE_CUOTAS. */
    public static final String LIMITE_CUOTAS = "compracuotas";

    /** The Constant ADELANTO_EFECTIVO. */
    public static final String ADELANTO_EFECTIVO = "compadeldisp";

    /** The Constant LIMITE_ADELANTO. */
    public static final String LIMITE_ADELANTO = "adelantos";

    /** The Constant LIMITE_ADELANTO_VALOR. */
    public static final BigDecimal LIMITE_ADELANTO_VALOR = new BigDecimal(15000);

    /** The Constant KEY_STORE_VISAAMEX. */
    public static final String KEY_STORE_VISAAMEX = "VISAAMEX";

    /** The Constant TARJETA_VISA_PAD_SIZE. */
    private static final int TARJETA_VISA_PAD_SIZE = 8;

    /** The Constant TARJETA_AMEX_PAD_SIZE. */
    private static final int TARJETA_AMEX_PAD_SIZE = 9;

    /** The Constant TARJETA_AMEX_PAD_SIZE. */
    private static final int TARJETA_BANELCO_PAD_SIZE = 8;

    /** The Constant SEPARATOR_GUION_MEDIO. */
    private static final String GUION_MEDIO = "-";

    /** The Constant MARCA_VISA. */
    public static final String MARCA_VISA = "VISA";

    /** The Constant MARCA_MASTERCARD. */
    public static final String MARCA_MASTERCARD = "MASTER";
    
    /** The Constant MARCA_MASTERCARD_AUX. */
    public static final String MARCA_MASTERCARD_AUX = "MASTERCARD";

    /** The Constant MARCA_VISA_RECARGABLE. */
    public static final String MARCA_VISA_RECARGABLE = "VISA Recargable";

    /** The Constant MARCA_AMEX. */
    public static final String MARCA_AMEX = "AMEX";

    /** The Constant MARCA_BANELCO. */
    public static final String MARCA_BANELCO = "VISA Debito";

    /** The Constant CATEGORIA_TITULAR. */
    public static final String CATEGORIA_TITULAR = "0";

    /** The Constant CATEGORIA_ADICIONAL. */
    public static final String CATEGORIA_ADICIONAL = "1";



    /** The Constant BLANCO. */
    public static final String BLANCO = "";

    /** The Constant NO_FECHA. */
    public static final String NO_FECHA = "--/--";

    /** The Constant TEXTO_LOGGER. */
    private static final String TEXTO_LOGGER = "Para el tipo de cuenta {} con clase {} la marca obtenida es {}";

    /** The Constant LENGTH_TARJETA_AMEX. */
    private static final Integer LENGTH_TARJETA_AMEX = 17;

    /** The Constant LENGTH_TARJETA_VISA. */
    private static final Integer LENGTH_TARJETA_VISA = 16;

    /** The Constant LEFTPAD_MARCA_VISA. */
    private static final Integer LEFTPAD_MARCA_VISA = 4;

    /** The Constant LEFTPAD_MARCA_AMEX. */
    public static final Integer LEFTPAD_MARCA_AMEX = 5;

    /** The Constant LEFTPAD_MARCA_VISA. */
    private static final Integer LEFTPAD_MARCA_BANELCO = 4;

    /** The Constant CERO_ENTERO_ENTERO. */
    public static final Integer CERO_ENTERO_ENTERO = 0;

    /** The Constant UNO_ENTERO. */
    public static final Integer UNO_ENTERO = 1;

    /** The Constant DOS_ENTERO. */
    public static final Integer DOS_ENTERO = 2;

    /** The Constant TRES_ENTERO. */
    public static final Integer TRES_ENTERO = 3;

    /** The Constant SEIS_ENTERO. */
    private static final Integer SEIS_ENTERO = 6;

    /** The Constant CUATRO_ENTERO. */
    public static final Integer CUATRO_ENTERO = 4;

    /** The Constant CINCO_ENTERO. */
    public static final Integer CINCO_ENTERO = 5;

    /** The Constant CERO_ENTERO. */
    public static final String CERO_ENTERO = "0";

    /** The Constant SIETE_ENTERO. */
    public static final Integer SIETE_ENTERO = 7;

    /** The Constant DIEZ_ENTERO. */
    public static final Integer DIEZ_ENTERO = 10;

    /** The Constant DIECIOCHO_ENTERO. */
    public static final Integer DIECIOCHO_ENTERO = 18;

    /** The Constant CLASE_CUENTA_L. */
    public static final String CLASE_CUENTA_L = "L";

    /** The Constant CLASE_CUENTA_H. */
    public static final String CLASE_CUENTA_H = "H";

    /** The Constant CLASE_CUENTA_S. */
    public static final String CLASE_CUENTA_S = "S";

    /** The Constant ESPACIO_BLANCO. */
    private static final String ESPACIO_BLANCO = " ";

    /** The Constant DESCRIPCION_TNA. */
    public static final String DESCRIPCION_TNA = "TNA";

    /** The Constant DESCRIPCION_TEM. */
    public static final String DESCRIPCION_TEM = "TEM";

    /** The Constant MENSUALMENTE. */
    public static final String MENSUALMENTE = "mensualmente";
    /** The Constant SEMANALMENTE. */
    public static final String SEMANALMENTE = "semanalmente";
    /** The Constant QUINCENALMENTE. */
    public static final String QUINCENALMENTE = "quincenalmente";

    /** The Constant QUINCENALMENTE. */
    public static final String SIN_AGENDA = "01";

    /** The Constant CUENTA_RECARGABLE. */
    public static final String CUENTA_RECARGABLE = "PREN";

    /** The Constant CONSUMO_PESOS . */
    public static final String CONSUMO_PESOS = "consumopesos";

    /** The Constant CONSUMO_DOLARES . */
    public static final String CONSUMO_DOLARES = "consumodolares";

    /** The Constant SALDO_CUENTA. */
    public static final String SALDO_CUENTA = "saldocuenta";
    
    /** The Constant ESTADO_TARJETA_CREDITO. */
    public static final String ESTADO_TARJETA_CREDITO = "20";
    
    /** The Constant ESTADO_TARJETA_CREDITO_ACTIVA_NO_RENOVAR. */
    public static final String ESTADO_TARJETA_CREDITO_ACTIVA_NO_RENOVAR = "22";
    
    /** The Constant ESTADO_TARJETA_CREDITO_CON_PROBLEMAS. */
    public static final String ESTADO_TARJETA_CREDITO_CON_PROBLEMAS = "25";

    /** The Constant ESTADO_TARJETA_CREDITO_CERRADA. */
    public static final String ESTADO_TARJETA_CREDITO_CERRADA = "29";

    /** The Constant FECHA_VACIA_TARJETA. */
    private static final String FECHA_VACIA_TARJETA = "-/-/-";

    private static final String PRISMA_SETTLEMENT_INVALID_DATE = "01/01/1970";

    /**
     * Instantiates a new tarjeta utils.
     */
    private TarjetaUtils() {
        super();
    }

    /**
     * Quitar guiones de la tarjeta. Como normalizarNroTarjeta pero agrega el 0 al
     * final de amex (igual a la del fep C++) Modificada del obp anterior.
     * 
     * @param nrotar
     *            the nrotar
     * @return String
     */
    public static String sacarGuionesTarjeta(final String nrotar) {
        StringBuilder tjSinGuiones = new StringBuilder("");
        tjSinGuiones.append(StringUtils.remove(nrotar, "-"));
        if (nrotar.length() == LENGTH_TARJETA_AMEX) {
            tjSinGuiones.append('0');
        }
        return tjSinGuiones.toString();
    }

    /**
     * Sacar ceros de adelante.
     *
     * @param value
     *            the value
     * @return the string
     */
    public static String sacarCerosDeAdelante(final String value) {
        int cursor;
        for (cursor = 0; cursor != value.length() && value.charAt(cursor) == '0'; ++cursor) {
            continue;
        }
        if (cursor == value.length()) {
            return "0";
        } else {
            return value.substring(cursor);
        }
    }

    /**
     * Se quitan los ceros delanta del numero de tarjeta, segun la cantidad de
     * digitos dependiendo de la marca de la tarjeta.
     * 
     * @param nroTarjeta
     *            the nro tarjeta
     * @param marca
     *            the marca
     * @return the string
     */
    public static String cortarNumeroTarjeta(String nroTarjeta, String marca) {
        String nroTarjetaFormateado = "";

        if (marca.equals(MARCA_VISA) || marca.equals(MARCA_VISA_RECARGABLE)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LEFTPAD_MARCA_VISA);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - LEFTPAD_MARCA_VISA,
                    nroTarjetaFormateado.length());
        }

        if (marca.equals(MARCA_MASTERCARD)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LEFTPAD_MARCA_VISA);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - LEFTPAD_MARCA_VISA,
                    nroTarjetaFormateado.length());
        }

        if (marca.equals(MARCA_AMEX)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LEFTPAD_MARCA_AMEX);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - SEIS_ENTERO,
                    nroTarjetaFormateado.length() - 1);
        }

        if (marca.equals(MARCA_BANELCO)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LEFTPAD_MARCA_BANELCO);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - LEFTPAD_MARCA_BANELCO,
                    nroTarjetaFormateado.length());
        }

        return nroTarjetaFormateado;
    }

    /**
     * Cortar numero tarjeta como tarjeta activa.
     *
     * @param nroTarjeta
     *            the nro tarjeta
     * @param marca
     *            the marca
     * @return the string
     */
    public static String cortarNumeroTarjetaComoTarjetaActiva(String nroTarjeta, String marca) {
        String nroTarjetaFormateado = "";

        if (marca.equals(MARCA_VISA) || marca.equals(MARCA_VISA_RECARGABLE)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LENGTH_TARJETA_VISA);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - LENGTH_TARJETA_VISA,
                    nroTarjetaFormateado.length());
        }

        if (marca.equals(MARCA_AMEX)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LENGTH_TARJETA_VISA);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - LENGTH_TARJETA_VISA,
                    nroTarjetaFormateado.length());
        }

        if (marca.equals(MARCA_MASTERCARD)) {
            nroTarjetaFormateado = StringUtils.leftPad(nroTarjeta, LENGTH_TARJETA_VISA);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(nroTarjetaFormateado.length() - LENGTH_TARJETA_VISA,
                    nroTarjetaFormateado.length());
        }

        return nroTarjetaFormateado;
    }

    /**
     * Formatear el numero tarjeta segun la marca.
     * 
     * @author florencia.n.martinez
     * @param numeroTarjeta
     *            the número de tarjeta
     * @param marca
     *            the marca
     * @return the string
     */
    public static String formatearNumeroTarjeta(String numeroTarjeta, String marca) {
        String nroTarjetaFormateado = "";

        if (marca.equals(MARCA_VISA) || marca.equals(MARCA_VISA_RECARGABLE) || marca.equals(MARCA_MASTERCARD)) {
            nroTarjetaFormateado = ISBANStringUtils.formatearNumeroTarjeta(numeroTarjeta, TARJETA_VISA_PAD_SIZE);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(0, nroTarjetaFormateado.length() - CUATRO_ENTERO)
                    + GUION_MEDIO
                    + nroTarjetaFormateado.substring(CUATRO_ENTERO, nroTarjetaFormateado.length());
        }

        if (marca.equals(MARCA_AMEX)) {
            nroTarjetaFormateado = ISBANStringUtils.formatearNumeroTarjeta(numeroTarjeta, TARJETA_AMEX_PAD_SIZE);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(0, nroTarjetaFormateado.length() - CINCO_ENTERO)
                    + GUION_MEDIO
                    + nroTarjetaFormateado.substring(CUATRO_ENTERO, nroTarjetaFormateado.length());
        }

        if (marca.equals(MARCA_BANELCO)) {
            nroTarjetaFormateado = ISBANStringUtils.formatearNumeroTarjeta(numeroTarjeta, TARJETA_BANELCO_PAD_SIZE);
            nroTarjetaFormateado = nroTarjetaFormateado.substring(0, nroTarjetaFormateado.length() - CUATRO_ENTERO)
                    + GUION_MEDIO
                    + nroTarjetaFormateado.substring(CUATRO_ENTERO, nroTarjetaFormateado.length());
        }

        return nroTarjetaFormateado;
    }

    /**
     * Cortar Y formatear numero tarjeta. Ejemplo: nroTarjetaConFormato= XXXX-20312
     *
     * @param nroTarjeta
     *            the nro tarjeta
     * @param marca
     *            the marca
     * @return the string
     */
    public static String cortarYFormatearNumeroTarjeta(String nroTarjeta, String marca) {
        if (nroTarjeta != null && marca != null) {
            String nroTarjetaCortado = cortarNumeroTarjeta(nroTarjeta, marca);
            return formatearNumeroTarjeta(nroTarjetaCortado, marca);
        }
        return null;
    }

    /**
     * Crea la mascara del nro de tarjeta para enviar a la vista.
     * 
     * @author mariano.g.pulera
     * @param nroTarjeta
     *            the nro tarjeta
     * @param marca
     *            the marca
     * @return the string
     */
    public static String crearMascaraNroTarjeta(String nroTarjeta, TipoCuentaTarjeta marca) {

        String nroTarjetaSinCeros = ISBANStringUtils.eliminarCeros(nroTarjeta);

        if (TipoCuentaTarjeta.TIPOCTA_AMEX.equals(marca)) {
            nroTarjetaSinCeros = nroTarjetaSinCeros.substring(10, nroTarjetaSinCeros.length() - 1);
        } else if (TipoCuentaTarjeta.TIPOCTA_VISA.equals(marca) || (TipoCuentaTarjeta.TIPOCTA_MASTER.equals(marca))) {
            nroTarjetaSinCeros = nroTarjetaSinCeros.substring(12, nroTarjetaSinCeros.length());
        }

        return "XXXX-" + nroTarjetaSinCeros;
    }

    /**
     * Obtiene la marca de la tarjeta a partir de la cuenta.
     * 
     * VALIDAR QUE SOLO PUEDAN LLEGAR LOS 3 TIPOS ESPERADOS SINO VA SER AMEX EL
     * DEFAULT.
     *
     * @param cuenta
     *            the cuenta
     * @return the marca
     */
    public static String getMarca(Cuenta cuenta) {
        TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
        Boolean isVisa = TipoCuenta.VISA.equals(tipoCuentaEnum);
        Boolean isMastercard = TipoCuenta.MASTERCARD.equals(tipoCuentaEnum);
        if (isVisa) {
            if (VISA_RECARGABLE.equals(cuenta.getClaseCuenta())) {
                return MARCA_VISA_RECARGABLE;
            } else {
                return MARCA_VISA;
            }
        }
        if (isMastercard) {
            return MARCA_MASTERCARD;
        }
        return MARCA_AMEX;
    }
    
    /**
     * Retorna las marcas de tarjeta, en este caso se replica el metodo cambiando 
     * el retorno de MASTER por MASTERCARD y no se hace en el otro metodo porque se llama
     * de muchos lados. 
     *
     * @param cuenta the cuenta
     * @return the marca aux
     */
    public static String getMarcaAux(Cuenta cuenta) {
        TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
        Boolean isVisa = TipoCuenta.VISA.equals(tipoCuentaEnum);
        Boolean isMastercard = TipoCuenta.MASTERCARD.equals(tipoCuentaEnum);
        if (isVisa) {
            if (VISA_RECARGABLE.equals(cuenta.getClaseCuenta())) {
                return MARCA_VISA_RECARGABLE;
            } else {
                return MARCA_VISA;
            }
        }
        if (isMastercard) {
            return MARCA_MASTERCARD_AUX;
        }
        return MARCA_AMEX;
    }

    /**
     * Dado un tipo de cuenta y su clase devolvemos la marca.
     *
     * VALIDAR QUE SOLO PUEDAN LLEGAR LOS 3 TIPOS ESPERADOS SINO VA SER AMEX EL
     * DEFAULT.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param claseCuenta
     *            the clase cuenta
     * @return the marca
     */
    /**
     * Deprecado: Utilizar {@link #getMarca(Cuenta cuenta) getComponentAt} metodo
     * 
     * @deprecated
     * @return the tipoCuenta
     */
    @Deprecated
    public static String getMarca(String tipoCuenta, String claseCuenta) {
        TipoCuenta tipoCuentaEnum = TipoCuenta.fromCodigo(tipoCuenta);
        Boolean isVisa = TipoCuenta.VISA.equals(tipoCuentaEnum);
        if (isVisa) {
            if (VISA_RECARGABLE.equals(claseCuenta)) {
                LOGGER.info(TEXTO_LOGGER, tipoCuentaEnum, claseCuenta, MARCA_VISA_RECARGABLE);
                return MARCA_VISA_RECARGABLE;
            } else {
                LOGGER.info(TEXTO_LOGGER, tipoCuentaEnum, claseCuenta, MARCA_VISA);
                return MARCA_VISA;
            }
        } else {
            LOGGER.info(TEXTO_LOGGER, tipoCuentaEnum, claseCuenta, MARCA_AMEX);
            return MARCA_AMEX;
        }
    }

    /**
     * Es Marca Visa.
     * 
     * @param marca
     *            the marca
     * @return the boolean
     */
    public static Boolean esMarcaVisa(String marca) {
        return MARCA_VISA.equals(marca);
    }

    /**
     * Es Marca Amex.
     * 
     * @param marca
     *            the marca
     * @return the boolean
     */
    public static Boolean esMarcaAmex(String marca) {
        return MARCA_AMEX.equals(marca);
    }

    /**
     * Es Marca Amex segun la cuenta que recibe.
     * 
     * @param cuenta
     *            the cuenta
     * @return the boolean
     */
    @Deprecated
    public static Boolean esMarcaAmex(Cuenta cuenta) {
        TipoCuenta tipo = cuenta.getTipoCuentaEnum();
        if (tipo == null) {
            return Boolean.FALSE;
        }
        return TipoCuenta.AMEX.equals(tipo);
    }

    /**
     * Obtener nro cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */

    public static String obtenerNroCuenta(Cuenta cuenta) {
    	switch (cuenta.getTipoCuentaEnum()) {
        case VISA:
            return StringUtils.leftPad(TarjetaUtils.sacarCerosDeAdelante(cuenta.getNroCuentaProducto()), DIEZ_ENTERO,
                    CERO_ENTERO);
        case AMEX:
            return (cuenta.getNroCuentaProducto() + cuenta.getCbu().charAt(DIECIOCHO_ENTERO)).substring(SIETE_ENTERO);
        case MASTERCARD:
            return StringUtils.leftPad(TarjetaUtils.sacarCerosDeAdelante(cuenta.getNroCuentaProducto()), DIEZ_ENTERO,
					CERO_ENTERO) + cuenta.getCbu().charAt(18) ;
        default:
            return null;
        }
    }

    /**
     * Si es VISA enviar 072, Si es AMEX enviar 472.
     * 
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    public static String obtenerCodigoBanco(Cuenta cuenta) {
        return esMarcaAmex(cuenta) ? TarjetaUtils.CODIGO_BANCO_AMEX : TarjetaUtils.CODIGO_BANCO_VISA;
    }

    /**
     * Si es VISA enviar 072, Si es AMEX enviar 472.
     * 
     * @param isAmex
     *            the is amex
     * @return the string
     */
    public static String obtenerCodigoBanco(Boolean isAmex) {
        return isAmex ? TarjetaUtils.CODIGO_BANCO_AMEX : TarjetaUtils.CODIGO_BANCO_VISA;
    }

    /**
	 * Obtener codigo banco.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return CodigoBanco
	 */
    public static String obtenerCodigoBanco(TipoCuenta tipoCuenta) {
        switch (tipoCuenta) {
        case VISA:
            return TarjetaUtils.CODIGO_BANCO_VISA;
        case AMEX:
            return TarjetaUtils.CODIGO_BANCO_AMEX;
        case MASTERCARD:
            return TarjetaUtils.CODIGO_BANCO_MASTER;
        default:
            return null;
        }
    }

    /**
     * Obtener el numero de cuenta.
     * 
     * @param isAmex
     *            the is amex
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    public static String obtenerNroCuenta(Boolean isAmex, Cuenta cuenta) {
        if (isAmex) {
            return (cuenta.getNroCuentaProducto() + cuenta.getCbu().charAt(DIECIOCHO_ENTERO)).substring(SIETE_ENTERO);
        } else {
            return StringUtils.leftPad(TarjetaUtils.sacarCerosDeAdelante(cuenta.getNroCuentaProducto()), DIEZ_ENTERO,
                    CERO_ENTERO);
        }
    }

    /**
     * Obtener nombre formateado. Ejemplo: de un string: GARAY O/FEDERICO N, lo
     * convierte a: Garay O/Federico N
     *
     * @param nombreApellido
     *            the nombre apellido
     * @return the string
     */
    public static String obtenerNombreFormateado(String nombreApellido) {
        String nombre = StringUtils.substringAfter(nombreApellido, "/");
        String apellido = StringUtils.substringBefore(nombreApellido, "/");
        String nombreCompleto = nombre + ESPACIO_BLANCO + apellido;
        return WordUtils.capitalizeFully(nombreCompleto);
    }

    /**
     * Checks if is recargable adicional.
     *
     * @param cuenta
     *            the cuenta
     * @return true, if is recargable adicional
     */
    public static boolean isRecargableAdicional(Cuenta cuenta) {
        if (!"001".equals(cuenta.getNroOrdenFirmante())) {
            return true;
        }
        return false;
    }

    /**
     * Codigo frecuncia recargable.
     *
     * @param frecuencia
     *            the frecuencia
     * @param fecha
     *            the fecha
     * @return the int
     */
    public static int codigoFrecunciaRecargable(String frecuencia, Date fecha) {
        int codFrec = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (MENSUALMENTE.equalsIgnoreCase(frecuencia)) {
            codFrec = 90;
        } else if (SEMANALMENTE.equalsIgnoreCase(frecuencia)) {
            codFrec = 70 + dayOfWeek - 1;
            if (codFrec == 70 || codFrec == 76) {
                codFrec = 71;
            }

        } else if (QUINCENALMENTE.equalsIgnoreCase(frecuencia)) {
            codFrec = 80 + dayOfWeek - 1;
            if (codFrec == 80 || codFrec == 86) {
                codFrec = 81;
            }
        } else if (SIN_AGENDA.equals(frecuencia)) {
            codFrec = 1;
        }
        return codFrec;

    }

    /**
	 * Obtener nro tarjeta enmascarada.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
    public static String obtenerNroTarjetaEnmascarada(Cuenta cuenta) {
        return obtenerNroTarjetaEnmascarada(cuenta.getNroTarjetaCredito(), cuenta.getTipoCuentaEnum());
    }

    /**
	 * Obtener nro tarjeta enmascarada.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the string
	 */
    public static String obtenerNroTarjetaEnmascarada(String nroTarjeta, TipoCuenta tipoCuenta) {
        String nroTarjetaCortado;
        if (TipoCuenta.VISA.equals(tipoCuenta)) {
            nroTarjetaCortado = StringUtils.substring(nroTarjeta, -4);
        } else if (TipoCuenta.AMEX.equals(tipoCuenta)) {
            nroTarjetaCortado = StringUtils.substring(nroTarjeta, -6, -1);
        } else if (TipoCuenta.MASTERCARD.equals(tipoCuenta)) {
            nroTarjetaCortado = StringUtils.substring(nroTarjeta, -4);
        } else if (TipoCuenta.BANELCO.equals(tipoCuenta)) {
            nroTarjetaCortado = StringUtils.substring(nroTarjeta, -4);
        } else {
            nroTarjetaCortado = nroTarjeta;
        }
        return "XXXX-" + nroTarjetaCortado;
    }

    /**
	 * Obtener nro tarjeta con formato.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
    public static String obtenerNroTarjetaConFormato(Cuenta cuenta) {
        String nroTarjeta = cuenta.getNroTarjetaCredito();
        return new StringBuilder().append(StringUtils.substring(nroTarjeta, -16, -12)).append(GUION_MEDIO)
                .append(StringUtils.substring(nroTarjeta, -12, -8)).append(GUION_MEDIO)
                .append(StringUtils.substring(nroTarjeta, -8, -4)).append(GUION_MEDIO)
                .append(StringUtils.substring(nroTarjeta, -4)).toString();
    }
    
    /**
	 * Es tarjeta de credito.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return true, if successful
	 */
    public static boolean esTarjetaDeCredito(TipoCuenta tipoCuenta) {
        return TipoCuenta.VISA.equals(tipoCuenta) || TipoCuenta.AMEX.equals(tipoCuenta)
                || TipoCuenta.MASTERCARD.equals(tipoCuenta);
    }

    /**
     * Permite resumen anual.
     *
     * @param cuenta the cuenta
     * @return true, if successful
     */
    public static boolean permiteResumenAnual(Cuenta cuenta) {
        return TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum())
            && TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad()) && !TarjetaUtils.VISA_RECARGABLE.equals(cuenta.getClaseCuenta())
            && (TarjetaUtils.ESTADO_TARJETA_CREDITO.equals(cuenta.getEstadoTarjetaCredito()) || TarjetaUtils.ESTADO_TARJETA_CREDITO_ACTIVA_NO_RENOVAR.equals(cuenta.getEstadoTarjetaCredito()) 
                || TarjetaUtils.ESTADO_TARJETA_CREDITO_CON_PROBLEMAS.equals(cuenta.getEstadoTarjetaCredito()) || TarjetaUtils.ESTADO_TARJETA_CREDITO_CERRADA.equals(cuenta.getEstadoTarjetaCredito()));
    }

    /**
     * Obtener marca.
     *
     * @param cuenta the cuenta
     * @return the string
     */
    public static String obtenerMarca(Cuenta cuenta) {

        String marca = null;
        Integer tipo = Integer.parseInt(cuenta.getTipoCuenta().trim());
        if (tipo.equals(ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.VISA.getCodigo())) {
            marca = "Visa";
        }
        if (tipo.equals(ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.AMEX.getCodigo())) {
            marca = "Amex";
        }

        if (tipo.equals(ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.MASTERCARD.getCodigo())) {
            marca = "Mastercard";
        }

        if (tipo.equals(ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.RECARGABLE.getCodigo())) {
            marca = "Visa Recargable";
        }
        return marca;
    }

    public static String mapSettlementDateDate(String date) {
        return (StringUtils.isNotBlank(date) && !PRISMA_SETTLEMENT_INVALID_DATE.equals(date)) ? date : FECHA_VACIA_TARJETA;
    }

    public static boolean esRecargable(Cuenta cuenta) {
        return MARCA_VISA_RECARGABLE.equalsIgnoreCase(TarjetaUtils.obtenerMarca(cuenta));
    }
}
