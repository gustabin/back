package ar.com.santanderrio.obp.base.security.credential;

// TODO: Auto-generated Javadoc
/**
 * Database Enum.
 *
 * @author B025331
 */
public enum DataBase {

	/**
	 * Estadisticas.
	 */
	SEGURIDAD("SEGURIDAD"),

	/**
	 * Estadisticas.
	 */
	ESTADISTICAS("ESTADISTICAS"),

	/** Banca Privada. */
	BPRIV("BPRIV"),

	/** OnDemand. */
	ONDEMAND("ONDEMAND.CONN.SECURITY"),

	/**
	 * RSA.
	 */
	RSA("RSA"),

	/** Banca Privada. */
	BPRIVRACF("BPRIVRACF"),

	/** Impuestos. */
	IMPUESTOS("IMPUESTOS"),

	/** Clasificacion de deuda. */
	CLASIFDEUDORES("CLASIFDEUDORES"),
	/** JWT SECRET. */
	JWTSECRET("JWT"),

	/** The portal. */
	PORTAL("PORTAL"),

	/** The reclamos. */
	RECLAMOS("RECLAMOS"),

	/** The citi. */
    CITI("CITI"),
    
	/** The advantage. */
	ADVANTAGE("ADVANTAGE"),
	
	/**  base estadisticas obp prod. */
    HBOBP("HBOBP"),
    
    /**  Pass base seguridad sin FreeUser. */
    RACFINICIAL("RACFINICIAL");
    
	/** The Constant ID_TOKEN. */
	private static final String ID_TOKEN = ".ID";

	/** The Constant DS_TOKEN. */
	private static final String DS_TOKEN = ".DS";

	/** The Constant DB_TOKEN. */
	private static final String DB_TOKEN = "DB.";

	/** The id base datos. */
	private String idBaseDatos;

	/** The nombre target. */
	private String nombreTarget;

	/** The data source. */
	private String dataSource;

	/**
	 * Constructor del enum.
	 *
	 * @param idBaseDatos
	 *            the id base datos
	 */
	private DataBase(String idBaseDatos) {
		this.idBaseDatos = DB_TOKEN + idBaseDatos + ID_TOKEN;
		this.dataSource = DB_TOKEN + idBaseDatos + DS_TOKEN;
		this.nombreTarget = idBaseDatos;
	}

	/**
	 * Getter idBaseDatos.
	 *
	 * @return the id base datos
	 */
	public String getIdBaseDatos() {
		return idBaseDatos;
	}

	/**
	 * Getter nombreTarget.
	 *
	 * @return the nombre target
	 */
	public String getNombreTarget() {
		return nombreTarget;
	}

	/**
	 * Getter dataSource.
	 *
	 * @return the data source
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * Retorna el DataBase por Sistema.
	 *
	 * @param sistema
	 *            the sistema
	 * @return the data base por sistema
	 */
	public static DataBase getDataBasePorSistema(String sistema) {
		String idABuscar = DB_TOKEN + sistema + ID_TOKEN;

		for (DataBase tipoCuenta : DataBase.values()) {
			if (tipoCuenta.getIdBaseDatos().equals(idABuscar)) {
				return tipoCuenta;
			}
		}
		return null;
	}

	/**
	 * Retorna DataBase por el nombre del target.
	 *
	 * @param nombreTarget
	 *            the nombre target
	 * @return the data base por nombre target
	 */
	public static DataBase getDataBasePorNombreTarget(String nombreTarget) {

		for (DataBase tipoCuenta : DataBase.values()) {
			if (tipoCuenta.getNombreTarget().equals(nombreTarget)) {
				return tipoCuenta;
			}
		}
		return null;
	}

	/**
	 * Retorna DataBase por el id.
	 *
	 * @param idBaseDatos
	 *            the id base datos
	 * @return the data base por id
	 */
	public static DataBase getDataBasePorId(String idBaseDatos) {

		for (DataBase tipoCuenta : DataBase.values()) {
			if (tipoCuenta.getIdBaseDatos().equals(idBaseDatos)) {
				return tipoCuenta;
			}
		}
		return null;
	}

}
