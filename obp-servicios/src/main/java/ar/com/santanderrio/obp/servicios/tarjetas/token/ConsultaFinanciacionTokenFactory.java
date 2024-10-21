/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.token;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.signer.factory.TokenFactory;
import ar.com.santanderrio.obp.base.signer.token.Token;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * Crear y popular el token para consulta financiacion.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component("consultaFinanciacionTokenFactory")
public class ConsultaFinanciacionTokenFactory implements TokenFactory {

	/** The Constant TIPO_PLANV. */
	private static final String TIPO_PLANV = "S";

	/** The Constant FUNCIONARIO. */
	private static final String FUNCIONARIO = ".FUNCIONARIO";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = ".PASSWORD";

	/** The Constant VISA. */
	private static final String VISA = ".VISA";

	/** The Constant AMEX. */
	private static final String AMEX = ".AMEX";

	/** The keystore. */
	@Value("${CONSULTAFINANCIACION.JKS}")
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
		Cuenta cuenta = (Cuenta) entidades[0];

		ConsultaFinanciacionToken token = new ConsultaFinanciacionToken();
		token.setJks(keystore);
		token.setRequiereCertificado(Boolean.TRUE);
		token.setCodigoBanco(TarjetaUtils.obtenerCodigoBanco(cuenta.getTipoCuentaEnum()));
		token.setFuncionario(getFuncionario(cuenta, tokenCode));
		token.setPassword(getPassword(cuenta, tokenCode));
		token.setNumeroCuenta(TarjetaUtils.obtenerNroCuenta(cuenta));
		token.setTipoPlanV(TIPO_PLANV);
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
	 * Ej: CONSULTAFINANCIACION.PASSWORD=
	 * CONSULTAFINANCIACION.PASSWORD.VISA=BANCO072
	 * CONSULTAFINANCIACION.PASSWORD.AMEX=BANCO472
	 * 
	 * Si el ambiente es produccion CONSULTAFINANCIACION.PASSWORD debe tener
	 * valor en los demas ambientes se tomara segun el tipo de tarjeta una de
	 * las otras dos props.
	 * 
	 * Si produccion cambia y CONSULTAFINANCIACION.PASSWORD puede tener valor
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
