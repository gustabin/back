/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

/**
 * Listar todos los servicios y sus versiones soportados en 7x24.
 *
 * @author sergio.e.goldentair
 * @version 1 manuel.vargas para ejecutar transferencias se agrego:
 *          TRANSF_BCO_RIO_110 TRANSF_TRFCCI_130
 */
public enum SietePorVeintiCuatroServicioEnum {

	/** The cnsagenta 100. */
	CNSAGENTA_100("CNSAGENDA", "100"),
	/** The cancel ocurrencia 100. */
	CANCEL_OCURRENCIA_100("CANCEL_OCURRENCIA", "100"),
	/** The transf bco rio 120. */
	TRANSF_BCO_RIO_120("TRANSF_BCO_RIO", "120"),
	/** The transf bco rio 120. */
	TRANSF_BCO_RIO_110("TRANSF_BCO_RIO", "110"),
	/** The transf trfcci 160. */
	TRANSF_TRFCCI_160("TRFCCI", "160"),
	/** The transf trfcci 160. */
	TRANSF_TRFCCI_130("TRFCCI", "130"),
	/** The trfcci 160. */
	TRFCCI_160("TRFCCI", "160");

	/** The servicio. */
	String servicio;

	/** The version. */
	String version;

	/**
	 * Instantiates a new siete por veinti cuatro servicio enum.
	 *
	 * @param servicio
	 *            the servicio
	 * @param version
	 *            the version
	 */
	SietePorVeintiCuatroServicioEnum(String servicio, String version) {
		this.servicio = servicio;
		this.version = version;
	}

	/**
	 * Gets the servicio.
	 *
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

}
