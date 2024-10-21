/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.generated.webservices.rsa.AccountType;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class RsaRequestVisitorImpl.
 * 
 * @author ignacio.valek
 * @author emilio.watemberg
 * @see {@link RsaRequestElement}
 * @since Nov 8, 2016.
 * 
 */
// TODO cambiar para que arme todo el request y no solo el EventData
public abstract class AbstractRsaRequestBuilder implements RsaRequestBuilder {

    /** The Constant BLANCO. */
    public static final String BLANCO = " ";

    /** The Constant CURRENCY. */
    public static final String CURRENCY = "ARS";

    /** The Constant GUION_MEDIO. */
    protected static final String GUION_MEDIO = "-";

    /** The Constant DATE_FORMATTER. */
    protected static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /** The Constant FACTOR_CENTAVOS. */
    protected static final BigDecimal FACTOR_CENTAVOS = new BigDecimal(100);

    /** The Constant HORAS_48 es el plazo de acreditacion. */
    protected static final String HORAS_48 = "48 Horas";

    // FIXME formateo cross de cuenta segun cultura... resolver codigo duplicado
    /**
     * Formatear numero cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    // por toda la aplicacions
    protected String formatearNumeroCuenta(Cuenta cuenta) {
        return formatearNumeroCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto());
    }

    /**
     * Formatear numero cuenta.
     *
     * @param sucursal
     *            the sucursal
     * @param nroCta
     *            the nro cta
     * @return the string
     */
    protected String formatearNumeroCuenta(String sucursal, String nroCta) {
        String sucursalforAppend;
        String nroCtaforAppend;
        sucursalforAppend = ISBANStringUtils.formatearSucursal(sucursal);
        nroCtaforAppend = ISBANStringUtils.formatearNumeroCuenta(nroCta);

        return new StringBuilder().append(sucursalforAppend).append(GUION_MEDIO).append(nroCtaforAppend).toString();
    }

    /**
     * Decodificar account type.
     *
     * @param tipoCuentaEnum
     *            the tipo cuenta enum
     * @return the account type
     */
    protected AccountType decodificarAccountType(TipoCuenta tipoCuentaEnum) {
        AccountType resultado = null;
        switch (tipoCuentaEnum) {
        case CAJA_AHORRO_PESOS:
        case CAJA_AHORRO_DOLARES:
            resultado = AccountType.SAVINGS;
            break;
        case CUENTA_CORRIENTE_PESOS:
        case CUENTA_CORRIENTE_DOLARES:
            resultado = AccountType.CHECKING;
            break;
        case CUENTA_UNICA:
        case CUENTA_UNICA_PESOS:
        case CUENTA_UNICA_DOLARES:
            resultado = AccountType.USER_DEFINED;
            break;
        default:
            // TODO resolver que sucede con otro tipo de cuenta.
            resultado = null;
        }
        return resultado;
    }

}
