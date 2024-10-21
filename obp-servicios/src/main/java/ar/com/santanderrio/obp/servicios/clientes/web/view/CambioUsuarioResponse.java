/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MarcaAPNHResponse.
 */
public class CambioUsuarioResponse {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioUsuarioResponse.class);

	/** The marca APNH. */
	private String marcaAPNH;

	/** The resultado campo usuario. */
	private String resultadoCampoUsuario;

	/** The resultado codigo campo usuario. */
	private String resultadoCodigoCampoUsuario;

	/**
	 * Checks if is marca APNH.
	 *
	 * @return the string
	 */
	public String isMarcaAPNH() {
		return marcaAPNH;
	}

	/**
	 * Sets the marca APNH.
	 *
	 * @param marcaAPNH
	 *            the new marca APNH
	 */
	public void setMarcaAPNH(String marcaAPNH) {
		this.marcaAPNH = marcaAPNH;
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Gets the resultado campo usuario.
	 *
	 * @return the resultado campo usuario
	 */
	public String getResultadoCampoUsuario() {
		return resultadoCampoUsuario;
	}

	/**
	 * Sets the resultado campo usuario.
	 *
	 * @param resultadoCampoUsuario
	 *            the new resultado campo usuario
	 */
	public void setResultadoCampoUsuario(String resultadoCampoUsuario) {
		this.resultadoCampoUsuario = resultadoCampoUsuario;
	}

	/**
	 * Gets the resultado codigo campo usuario.
	 *
	 * @return the resultado codigo campo usuario
	 */
	public String getResultadoCodigoCampoUsuario() {
		return resultadoCodigoCampoUsuario;
	}

	/**
	 * Sets the resultado codigo campo usuario.
	 *
	 * @param resultadoCodigoCampoUsuario
	 *            the new resultado codigo campo usuario
	 */
	public void setResultadoCodigoCampoUsuario(String resultadoCodigoCampoUsuario) {
		this.resultadoCodigoCampoUsuario = resultadoCodigoCampoUsuario;
	}

}
