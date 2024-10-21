package ar.com.santanderrio.obp.base.errores.entities;

import ar.com.santanderrio.obp.base.entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * Esta clase se encargaria del manejo de errores. key - key de la Error
 * keyHumana - Representaria la key humana. class - Excepcion que se genera con
 * todo el trace. descripcionExcepcion - Error que devuelve la aplicacion.
 * puntoDeOcurrencia - Punto de Ocurrencia. message - Mensaje que se devuelve el
 * usuario.
 *
 * @author B039541
 * 
 */
public class ErrorType extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The key humana. */
	private String keyHumana;

	/** The excepcion. */
	private Class excepcion;

	/** The desc excepcion. */
	private String descExcepcion;

	/** The punto de ocurrencia. */
	private String puntoDeOcurrencia;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new error type.
	 */
	public ErrorType() {
		super();
	};

	/** The errors. */
	// TODO: FIX No deberia haber errores funcionales especificos- como
	// ERROR_CUENTA_VENCIDA_PARA_CONSULTAR_SALDO"
	private static ErrorType[] errors = new ErrorType[] {
			new ErrorType(Long.valueOf(0), "ERROR_GENERICO", Exception.class, "", "",
					"Error Generico en la aplicacion"),
			new ErrorType(Long.valueOf(1), "ERROR_CUENTA_VENCIDA_PARA_CONSULTAR_SALDO", Exception.class,
					"ar.com.santanderrio.obp.comun.estadistica.Estadistica",
					"ar.com.santanderrio.obp.comun.estadistica.Estadistica.getCodigoTransaccion1",
					"Error en cuenta de saldo"),
			new ErrorType(Long.valueOf(2), "ERROR_LOGIN_CREDENCIALES", Exception.class,
					"ar.com.santanderrio.obp.clientes.manager.impl.ClienteManagerImpl",
					"ar.com.santanderrio.obp.clientes.manager.impl.ClienteManagerImpl.validarCredenciales1",
					"Error en la validacion de credenciales") };

	/**
	 * Instantiates a new error type.
	 *
	 * @param key
	 *            the key
	 * @param keyHumana
	 *            the key humana
	 * @param excepcion
	 *            the excepcion
	 * @param descExcepcion
	 *            the desc excepcion
	 * @param puntoDeOcurrencia
	 *            the punto de ocurrencia
	 * @param message
	 *            the message
	 */
	private ErrorType(Long key, String keyHumana, Class excepcion, String descExcepcion, String puntoDeOcurrencia,
			String message) {
		this.id = key;
		this.keyHumana = keyHumana;
		this.excepcion = excepcion;
		this.descExcepcion = descExcepcion;
		this.puntoDeOcurrencia = puntoDeOcurrencia;
		this.message = message;
	}

	/**
	 * Compare type.
	 *
	 * @param keyHumana
	 *            the key humana
	 * @param excepcion
	 *            the excepcion
	 * @param descExcepcion
	 *            the desc excepcion
	 * @param puntoDeOcurrencia
	 *            the punto de ocurrencia
	 * @return true, if successful
	 */
	private static boolean compareType(String keyHumana, Class excepcion, String descExcepcion,
			String puntoDeOcurrencia) {
		if (excepcion.getClass().isInstance(excepcion) && keyHumana.contentEquals(keyHumana)
				&& descExcepcion.contentEquals(descExcepcion) && puntoDeOcurrencia.contentEquals(puntoDeOcurrencia)) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo por el cual busca en el enumerado el mensaje por id numerico.
	 *
	 * @param key
	 *            the key
	 * @return the message type
	 */
	private static String getMessageType(Integer key) {
		for (ErrorType errorType : errors) {
			if (errorType.getId().equals(key.longValue())) {
				return errorType.message;
			}
		}
		return "";
	}

	/**
	 * * Metodo que devuelve el mensaje de acuerdo a loa parametros ingresados.
	 *
	 * @param keyHumana
	 *            the key humana
	 * @param excepcion
	 *            the excepcion
	 * @param descExcepcion
	 *            the desc excepcion
	 * @param puntoDeOcurrencia
	 *            the punto de ocurrencia
	 * @return the message type
	 */
	public static String getMessageType(String keyHumana, Class excepcion, String descExcepcion,
			String puntoDeOcurrencia) {
		for (ErrorType errorType : errors) {
			if (errorType.compareType(keyHumana, excepcion, descExcepcion, puntoDeOcurrencia)) {
				return errorType.message;
			}
		}
		return getMessageType(0);
	}

	/**
	 * * Metodo que devuelve el mensaje de acuerdo a loa parametros ingresados.
	 *
	 * @param excepcion
	 *            the excepcion
	 * @param descExcepcion
	 *            the desc excepcion
	 * @return the message type
	 */
	public String getMessageType(Class excepcion, String descExcepcion) {
		for (ErrorType errorType : errors) {
			if (excepcion.getClass().isInstance(this.excepcion) && descExcepcion.contentEquals(this.descExcepcion)) {
				return errorType.message;
			}
		}
		return getMessageType(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.base.entities.Entity#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Gets the key humana.
	 *
	 * @return the key humana
	 */
	public String getKeyHumana() {
		return keyHumana;
	}

	/**
	 * Setter para key humana.
	 *
	 * @param keyHumana
	 *            el nuevo key humana
	 */
	public void setKeyHumana(String keyHumana) {
		this.keyHumana = keyHumana;
	}

	/**
	 * Gets the punto de ocurrencia.
	 *
	 * @return the punto de ocurrencia
	 */
	public String getPuntoDeOcurrencia() {
		return puntoDeOcurrencia;
	}

	/**
	 * Setter para punto de ocurrencia.
	 *
	 * @param puntoDeOcurrencia
	 *            el nuevo punto de ocurrencia
	 */
	public void setPuntoDeOcurrencia(String puntoDeOcurrencia) {
		this.puntoDeOcurrencia = puntoDeOcurrencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descExcepcion == null) ? 0 : descExcepcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keyHumana == null) ? 0 : keyHumana.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((puntoDeOcurrencia == null) ? 0 : puntoDeOcurrencia.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		ErrorType other = (ErrorType) obj;
		if (descExcepcion == null) {
			if (other.descExcepcion != null) {
				return false;
			}
		} else if (!descExcepcion.equals(other.descExcepcion)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (keyHumana == null) {
			if (other.keyHumana != null) {
				return false;
			}
		} else if (!keyHumana.equals(other.keyHumana)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (puntoDeOcurrencia == null) {
			if (other.puntoDeOcurrencia != null) {
				return false;
			}
		} else if (!puntoDeOcurrencia.equals(other.puntoDeOcurrencia)) {
			return false;
		}
		return true;
	}

}
