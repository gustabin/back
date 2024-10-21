/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.FechaException;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenVisaAmex;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenVisaAmexFactory;

/**
 * The Class TokenVisaAmexFactoryImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("tokenVisaAmexFactory")
public class TokenVisaAmexFactoryImpl implements TokenVisaAmexFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenVisaAmexFactoryImpl.class);

	/** The ip cliente. */
	@Value("${PROXY.IP}")
	private String ipCliente;

	/**
	 * El valor esta dado en milisegundos por lo que hay que hacer la
	 * conversion.
	 */
	@Value("${VISA.AMEX.TIEMPO}")
	private Integer tiempo;

	/** The mes. */
	@Value("${VISA.AMEX.MES}")
	private String mes;

	/** The Constant CERO_ENTERO. */
	public static final String CERO_ENTERO = "0";

	/** The Constant SIETE_ENTERO. */
	public static final Integer SIETE_ENTERO = 7;

	/** The Constant DIEZ_ENTERO. */
	public static final Integer DIEZ_ENTERO = 10;

	/** The Constant DIECIOCHO_ENTERO. */
	public static final Integer DIECIOCHO_ENTERO = 18;

	/**
	 * Default constructor.
	 */
	public TokenVisaAmexFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.tarjetas.util.TokenVisaAmexFactory#createToken(ar
	 * .com.santanderrio.base.entities.Entity,
	 * ar.com.santanderrio.obp.tarjetas.enums.OperacionTarjetaWSEnum)
	 */
	@Override
	public TokenVisaAmex createToken(Cuenta cuenta, OperacionTarjetaWSEnum operacionTarjetaWSEnum) {
		LOGGER.info("Crear token VisaAmex para ser firmado.");
		Boolean isAmex = Boolean.FALSE;
		if (cuenta.getTipoCuentaEnum() != null) {
			isAmex = TipoCuenta.AMEX.equals(cuenta.getTipoCuentaEnum());
		}
		Long milisegEnMinutos = TimeUnit.MILLISECONDS.toMinutes(tiempo);
		TokenVisaAmex tokenVisaAmex = new TokenVisaAmex.TokenVisaAmexBuilder().setDirCliente(ipCliente)
				.setExpiracion(minutosFuturo(milisegEnMinutos)).setNumeroCuenta(obtenerNroCuenta(isAmex, cuenta))
				.setTarjeta(obtenerTarjeta(cuenta))
				.setCodigoBanco(TarjetaUtils.obtenerCodigoBanco(cuenta.getTipoCuentaEnum())).build();
		LOGGER.info("Token VisaAmex creado.");
		return tokenVisaAmex;
	}

	/**
	 * Obtener tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String obtenerTarjeta(Cuenta cuenta) {
		if (StringUtils.isNotEmpty(cuenta.getNroTarjetaCredito())) {
			return TarjetaUtils.sacarCerosDeAdelante(cuenta.getNroTarjetaCredito());
		}
		return null;
	}

	/**
	 * Obtener nro cuenta.
	 *
	 * @param isAmex
	 *            the is amex
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String obtenerNroCuenta(Boolean isAmex, Cuenta cuenta) {
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
	 * @param isAmex
	 *            the is amex
	 * @return the string
	 */
	private String obtenerCodigoBanco(Boolean isAmex) {
		return isAmex ? TarjetaUtils.CODIGO_BANCO_AMEX : TarjetaUtils.CODIGO_BANCO_VISA;
	}

	/**
	 * Funcion del OBP (viejo) que agrega minutos y mes a la fecha actual. Ej 09
	 * 03 2016 16:04:43
	 *
	 * @param n
	 *            the n
	 * @return String
	 */
	private String minutosFuturo(Long n) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, n.intValue());
		FechaUtils a;
		try {
			a = new FechaUtils(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + Integer.valueOf(mes),
					calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND));
			a.setSeparadorFecha(" ");
			return a.getDateTimeDDMMYYYYHHMMSS();
		} catch (NumberFormatException e) {
			LOGGER.error("Error de formato obtener la fecha actual mas {} minutos para el campo Expiracion.", n, e);
		} catch (FechaException e) {
			LOGGER.error("Error al obtener la fecha actual mas {} minutos para el campo Expiracion.", n, e);
		}
		return null;
	}
}
