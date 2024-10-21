/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

/**
 * The Enum PerfilInversorEnum.
 */
public enum PerfilInversorEnum {

	/** Inversor no perfilado. */
	NO_PERFILADO("0", "No Perfilado"),

	/** Perfil conservador. */
	CONSERVADOR("1", "Conservador"),

	/** Perfil moderado. */
	MODERADO("2", "Moderado"),

	/** Perfil agresivo. */
	AGRESIVO("3", "Agresivo");

	/** The id perfil. */
	private String idPerfil;
	
	/** The descripcion perfil. */
	private String descripcion;

	/**
	 * Instantiates a new perfil inversor enum.
	 *
	 * @param idPerfil
	 *            the id perfil
	 */
	PerfilInversorEnum(String idPerfil, String descripcion) {
		this.idPerfil = idPerfil;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the id perfil.
	 *
	 * @return the id perfil
	 */
	public String getIdPerfil() {
		return idPerfil;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	public static PerfilInversorEnum obtenerPerfilPorDescripcion(String descripcion){
		for (PerfilInversorEnum perfil : PerfilInversorEnum.values()) {
			if(perfil.getDescripcion().equalsIgnoreCase(descripcion)){
				return perfil;
			}
		}
		return null;
	}
}
