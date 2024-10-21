package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.FechaException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenW2W;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenW2WFactory;

/**
 * The Class TokenW2WFactoryImpl.
 */
@Component("tokenW2WFactory")
public class TokenW2WFactoryImpl implements TokenW2WFactory {

	/** The Constant EXPIRACION_MINUTOS. */
	private static final Long EXPIRACION_MINUTOS = 5L;

	/** The Constant MENU_PERMISOS. */
	private static final String MENU_PERMISOS_CONSULTA = "CONSULTA";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenW2WFactoryImpl.class);

	/** The Constant DNI. */
	private static final String DNI = "DNI";
	
	/** The Constant LC. */
	private static final String LC = "LC";
	
	/** The Constant LE. */
	private static final String LE = "LE";
	
	/** The Constant PS. */
	private static final String PS = "PS";
	
	/** The Constant CI. */
	private static final String CI = "CI";

	/** The ip cliente. */
	@Value("${PROXY.IP}")
	private String ipCliente;

	/** The tiempo. */
	@Value("${VISA.AMEX.TIEMPO}")
	private Integer tiempo;

	/** The mes. */
	@Value("${VISA.AMEX.MES}")
	private String mes;

	/**
	 * Instantiates a new TokenW2WFactoryImpl.
	 */
	public TokenW2WFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.tarjetas.util.TokenW2WFactory#createToken(ar.com.santanderrio.cuentas.entities.Cuenta,
	 * ar.com.santanderrio.clientes.entities.Cliente)
	 */
	@Override
	public TokenW2W createToken(Cuenta cuenta, Cliente cliente) {
		LOGGER.info("crear Token W2W");
		TokenW2W token = new TokenW2W.TokenW2WBuilder()
				.setDirCliente(ipCliente)
				.setExpiracion(obtenerFechaExpiracion(EXPIRACION_MINUTOS))
				.setTipoDocumento(obtenerTipoDocumento(cliente.getTipoDocumento()))
				.setDocumento(ISBANStringUtils.sacarCerosYBlancosIzq(cliente.getDni()))
				//.setSexo("H".equals(cliente.getSexo()) ? "M" : "F")
				.setNumeroCuenta(obtenerNroCuenta(cuenta))
				.setTarjeta(obtenerTarjeta(cuenta))
				.setCodigoBanco(TarjetaUtils.CODIGO_BANCO_VISA)
				.setMenuPermisos(MENU_PERMISOS_CONSULTA)
		        .setVisaNavBarW2W("false")
		        .build();
		LOGGER.info("Token W2W creado.");
		return token;
	}

	/**
	 * Obtener tipo documento.
	 *
	 * @param tipoDocumento the tipo documento
	 * @return the string
	 */
	private String obtenerTipoDocumento(String tipoDocumento) {
		String tipoDoc = "";
        if ("N".equals(tipoDocumento)) {
            tipoDoc = DNI;
        } else if ("C".equals(tipoDocumento)) {
            tipoDoc = LC;
        } else if ("E".equals(tipoDocumento)) {
            tipoDoc = LE;
        } else if ("P".equals(tipoDocumento)) {
            tipoDoc = PS;
        } else if ("D".equals(tipoDocumento)) {
            tipoDoc = CI;
        } else if ("X".equals(tipoDocumento)) {
            tipoDoc = DNI;
        }
        return tipoDoc;
	}

	/**
	 * Obtener tarjeta.
	 *
	 * @param cuenta the cuenta
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
	 * @param cuenta the cuenta
	 * @return the string
	 */
	private String obtenerNroCuenta(Cuenta cuenta) {
	    return StringUtils.leftPad(TarjetaUtils.sacarCerosDeAdelante(cuenta.getNroCuentaProducto()), 10, "0");
	}

	/**
	 * Obtener fecha expiracion.
	 *
	 * @param n the n
	 * @return the string
	 */
	private String obtenerFechaExpiracion(Long n) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, n.intValue());
		FechaUtils a;
		try {
			a = new FechaUtils(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND));
			a.setSeparadorFecha(" ");
			return a.getDateTimeYYYYMMDDHHMMSS();
		} catch (NumberFormatException e) {
			LOGGER.error("Error de formato obtener la fecha actual mas {} minutos para el campo Expiracion.", n, e);
		} catch (FechaException e) {
			LOGGER.error("Error al obtener la fecha actual mas {} minutos para el campo Expiracion.", n, e);
		}
		return null;
	}
}
