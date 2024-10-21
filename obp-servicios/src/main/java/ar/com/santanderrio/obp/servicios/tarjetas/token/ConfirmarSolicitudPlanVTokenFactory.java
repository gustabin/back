/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.token;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.signer.factory.TokenFactory;
import ar.com.santanderrio.obp.base.signer.token.Token;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * Crear y popular el token para WS getInformacionPlanV.
 *
 * @author ignacio_valek@itrsa.com.ar
 */
@Component("confirmarSolicitudPlanVTokenFactory")
public class ConfirmarSolicitudPlanVTokenFactory implements TokenFactory {

	/** The Constant FUNCIONARIO. */
	private static final String FUNCIONARIO = ".FUNCIONARIO";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = ".PASSWORD";

	/** The Constant VISA. */
	private static final String VISA = ".VISA";

	/** The Constant AMEX. */
	private static final String AMEX = ".AMEX";

	/** The Constant DIGITOS_TARJETA. */
	private static final int DIGITOS_TARJETA = 16;

	/** The Constant DIGITOS_MONTO. */
	private static final int DIGITOS_MONTO = 15;

	/** The Constant MONEDA_PESOS. */
	private static final String MONEDA_PESOS = "P";

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The keystore. */
	@Value("${PLANV.JKS}")
	private String keystore;

	/** The environment. */
	@Autowired
	private Environment environment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.signer.factory.TokenFactory#crearToken(java.
	 * lang.String, ar.com.santanderrio.obp.base.entities.Entity[])
	 */
	@Override
	public Token crearToken(String tokenCode, Entity... entidades) {
		DatosConfirmacionFinanciacionTarjetaDTO datos = (DatosConfirmacionFinanciacionTarjetaDTO) entidades[0];
		Cuenta tarjeta = datos.getTarjeta();
		ConfirmarSolicitudPlanVToken token = new ConfirmarSolicitudPlanVToken();
		token.setJks(keystore);
		token.setRequiereCertificado(Boolean.TRUE);
		token.setCodigoBanco(TarjetaUtils.obtenerCodigoBanco(tarjeta));
		token.setFuncionario(getFuncionario(tarjeta, tokenCode));
		token.setPassword(getPassword(tarjeta, tokenCode));
		token.setNumeroTarjeta(StringUtils.right(tarjeta.getNroTarjetaCredito(), DIGITOS_TARJETA));
		BigDecimal montoFinanciacion = datos.getMontoFinanciacion();
		token.setMonto(StringUtils.leftPad(montoFinanciacion.toString(), DIGITOS_MONTO, CERO));
		token.setCuotas(datos.getCuotas());
		token.setMoneda(MONEDA_PESOS);
		return token;
	}

	/**
	 * Obtener el valor de la propiedad password segun el ambiente.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param tokenCode
	 *            the token code
	 * @return the password
	 */
	private String getPassword(Cuenta cuenta, String tokenCode) {
		return getPropRealValue(cuenta, tokenCode, PASSWORD);
	}

	/**
	 * Obtener el valor de la propiedad funcioanario segun el ambiente.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param tokenCode
	 *            the token code
	 * @return the funcionario
	 */
	private String getFuncionario(Cuenta cuenta, String tokenCode) {
		return getPropRealValue(cuenta, tokenCode, FUNCIONARIO);
	}

	/**
	 * Obtener el valor de la propiedad para password y funcionario. La logica
	 * es si la propiedad funcionario o password tienen valor es porque es
	 * produccion y hay que utilizar dicho valor. Ahora si no esta seteada hay q
	 * condicionar segun el tipo de tarjeta.
	 * 
	 * Ej: CONFIRMARSOLICITUDPLANV.PASSWORD=
	 * CONFIRMARSOLICITUDPLANV.PASSWORD.VISA=BANCO072
	 * CONFIRMARSOLICITUDPLANV.PASSWORD.AMEX=BANCO472
	 * 
	 * Si el ambiente es produccion CONFIRMARSOLICITUDPLANV.PASSWORD debe tener
	 * valor en los demas ambientes se tomara segun el tipo de tarjeta una de
	 * las otras dos props.
	 * 
	 * Si produccion cambia y CONFIRMARSOLICITUDPLANV.PASSWORD puede tener valor
	 * vacio las otras dos tambien deberian ser vacias.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param token
	 *            the token
	 * @param prop
	 *            the prop
	 * @return the prop real value
	 */
	private String getPropRealValue(Cuenta cuenta, String token, String prop) {
		String inicialValue = environment.getProperty(StringUtils.upperCase(token) + prop);
		if (StringUtils.isNotEmpty(inicialValue)) {
			return inicialValue;
		} else if (TarjetaUtils.esMarcaAmex(cuenta)) {
			return environment.getProperty(StringUtils.upperCase(token) + prop + AMEX);
		} else {
			return environment.getProperty(StringUtils.upperCase(token) + prop + VISA);
		}
	}
}
