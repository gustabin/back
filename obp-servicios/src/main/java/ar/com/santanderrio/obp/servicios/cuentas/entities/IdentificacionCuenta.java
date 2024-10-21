/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * obtiene los atributos que identifican .
 *
 * @author B039543
 */
public class IdentificacionCuenta implements Serializable {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IdentificacionCuenta.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant SUCURSAL_SEPARADOR. */
	private final static String SUCURSAL_SEPARADOR = "-";

	/** The Constant DIGITO_VERIFICADOR_SEPARADOR. */
	private final static String DIGITO_VERIFICADOR_SEPARADOR = "/";

	/** The Constant ZERO_STRING. */
	private final static String ZERO_STRING = "0";

	/** The Constant SUCURSAL_LENGTH. */
	private final static int SUCURSAL_LENGTH = 3;

	/** The Constant CUENTA_LENGTH. */
	private final static int CUENTA_LENGTH = 7;

	/** The Constant EMPTY. */
	private static final String EMPTY = "";

	/** The nro sucursal. */
	private String nroSucursal = "";

	/** The nro cuenta producto. */
	// sin barra
	private String nroCuentaProducto = "";

	/**
	 * Instantiates a new identificacion cuenta.
	 */
	public IdentificacionCuenta() {
		super();
	}

	/**
	 * Instantiates a new identificacion cuenta.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 */
	public IdentificacionCuenta(String nroSucursal, String nroCuentaProducto) {
		super();
		this.nroSucursal = nroSucursal;
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Representacion de numero de cuenta Rio. Ejemplo: sucursal: 221,
	 * numeroCuenta: 2579806/6, IdentificacionCuenta: 221-579806/6. Desde los
	 * datos de una Cuenta: nroCuentaProducto=0000000025798066, nroSucursal=0221
	 *
	 * @param identificacionCuenta
	 *            formato aceptado:
	 *            sucursal-numeroDeCuenta/digitoVerificador,regex:
	 *            [0-9]{3}-[0-9]{6}/[0-9] ejemplo: 033-000231/4
	 * @throws IllegalArgumentException
	 *             si no conincide el formato
	 */
	public IdentificacionCuenta(String identificacionCuenta) {
		String[] numeroCuenta = identificacionCuenta.split("-");
		StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
		cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);
		this.setNroSucursal(numeroCuenta[0]);
		this.setNroCuentaProducto(cuentaProducto.toString());
		LOGGER.info("IdentificacionCuenta {}", this.getNroSucursal(), " ", this.getNroCuentaProducto());
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nroSucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Setter para nro sucursal.
	 *
	 * @param nroSucursal
	 *            the nroSucursal to set
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nroCuentaProducto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Setter para nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the nroCuentaProducto to set
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.nroSucursal == null || this.nroCuentaProducto == null || this.nroSucursal.isEmpty()
				|| this.nroCuentaProducto.isEmpty()) {
			return EMPTY;
		}
		return formatearNumeroSucursal() + SUCURSAL_SEPARADOR + formatearNumeroCuenta();

	}

	/**
	 * Formatear numero sucursal.
	 *
	 * @return the string
	 */
	private String formatearNumeroSucursal() {
		return StringUtils.leftPad(StringUtils.right(this.nroSucursal, SUCURSAL_LENGTH), SUCURSAL_LENGTH, ZERO_STRING);
	}

	/**
	 * Formatear numero cuenta.
	 *
	 * @return the string
	 */
	private String formatearNumeroCuenta() {
		String numero = StringUtils.leftPad(StringUtils.right(this.nroCuentaProducto, CUENTA_LENGTH), CUENTA_LENGTH,
				ZERO_STRING);
		numero = numero.substring(0, numero.length() - 1) + DIGITO_VERIFICADOR_SEPARADOR
				+ numero.substring(numero.length() - 1);
		return numero;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.toString().hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		return this.toString().equalsIgnoreCase(obj.toString());
	}

}
