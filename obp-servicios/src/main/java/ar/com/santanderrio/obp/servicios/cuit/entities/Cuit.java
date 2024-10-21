/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuit.entities;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class Cuit.
 *
 * @author leonardo.a.medina
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cuit {

	/**
	 * The Enum MensajeValor.
	 */
	private enum MensajeValor {

		/** The digito verificador correcto. */
		DIGITO_VERIFICADOR_CORRECTO(0),
		/** The error longitud. */
		ERROR_LONGITUD(1),
		/** The cuit no numerico. */
		CUIT_NO_NUMERICO(2),
		/** The prefijo invalido. */
		PREFIJO_INVALIDO(3),
		/** The digito verificador invalido. */
		DIGITO_VERIFICADOR_INVALIDO(4);

		/** The Constant map. */
		private static final Map<Integer, MensajeValor> map = new HashMap<Integer, MensajeValor>();

		/** The value. */
		private Integer value;

		// ----------------------------------------------------------------------
		// constructors
		// ----------------------------------------------------------------------

		static {
			for (MensajeValor type : MensajeValor.values()) {
				map.put(type.value, type);
			}
		}

		// ----------------------------------------------------------------------
		// static method
		// ----------------------------------------------------------------------

		/**
		 * Instantiates a new mensaje valor.
		 *
		 * @param value
		 *            the value
		 */
		MensajeValor(Integer value) {
			this.value = value;
		}

		/**
		 * Gets the mensaje valor.
		 *
		 * @return the mensaje valor
		 */
		public Integer getMensajeValor() {
			return value;
		}
	}

	/** The codigo. */
	private Integer codigo;

	/** The mensaje. */
	private String mensaje;

	/** The numero. */
	private String numero;

	/**
	 * Instantiates a new cuit.
	 */
	public Cuit() {
		super();
	}

	/**
	 * Instantiates a new cuit.
	 *
	 * @param numero
	 *            the numero
	 */
	public Cuit(String numero) {
		this.numero = numero;
		procesar();
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Es cuit valido.
	 *
	 * @return true, if successful
	 */
	public boolean esCuitValido() {
		return codigo.equals(MensajeValor.DIGITO_VERIFICADOR_CORRECTO.getMensajeValor());
	}

	/**
	 * Procesar.
	 */
	private void procesar() {
		final Integer longitud = 11;
		final Integer[] prefijos = { 20, 23, 24, 27, 30, 33, 34, 50, 55 };
		boolean existePrefijo = false;

		if (longitud.equals(StringUtils.length(numero))) {
			final Integer prefijoCuit = Integer.valueOf(StringUtils.trim(numero.substring(0, 2)));
			for (Integer unPrefijo : prefijos) {
				if (unPrefijo.equals(prefijoCuit)) {
					existePrefijo = true;
					break;
				}
			}
		}

		if (!longitud.equals(StringUtils.length(numero))) {
			codigo = MensajeValor.ERROR_LONGITUD.getMensajeValor();
			mensaje = "CUIT: Error de longitud";
		} else if (!NumberUtils.isNumber(numero)) {
			codigo = MensajeValor.CUIT_NO_NUMERICO.getMensajeValor();
			mensaje = "CUIT no numerico";
		} else if (!existePrefijo) {
			codigo = MensajeValor.PREFIJO_INVALIDO.getMensajeValor();
			mensaje = "Prefijo invalido";
		} else if (validarPorAlgoritmo(numero, longitud)) {
			codigo = MensajeValor.DIGITO_VERIFICADOR_CORRECTO.getMensajeValor();
			mensaje = "Digito Verificador Correcto";
		} else {
			codigo = MensajeValor.DIGITO_VERIFICADOR_INVALIDO.getMensajeValor();
			mensaje = "Digito Verificador Invalido";
		}
	}

	/**
	 * Validar por algoritmo.
	 *
	 * @param cuit
	 *            the cuit
	 * @param longitud
	 *            the longitud
	 * @return true, if successful
	 */
	private boolean validarPorAlgoritmo(String cuit, final Integer longitud) {
		final int[] numerales = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2, 1 };
		int suma = 0;

		for (int index = 0; index < longitud; index++) {
			suma += Integer.valueOf(Character.toString(cuit.charAt(index))) * numerales[index];
		}

		return suma % longitud == 0;
	}

}
