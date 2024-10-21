package ar.com.santanderrio.obp.servicios.nuevarecarga.entity;

/**
 * The Enum CompaniaCelularEnum.
 */
public enum CompaniaCelularEnum {
	
	/** The claro. */
	CLARO ("Claro", "RECL", "claro", "RECARGA CLARO"),
	
	/** The personal. */
	PERSONAL ("Personal", "REPE", "personal", "RECARGA PERSONAL"),
	
	/** The movistar. */
	MOVISTAR ("Movistar", "REMO", "movistar", "RECARGA MOVISTAR"),
	
	/** The nextel. */
	NEXTEL ("Nextel", "RENX", "nextel", "RECARGA NEXTEL"),
	
	/** The tuenti. */
	TUENTI ("Tuenti", "QUAM", "tuenti", "RECARGA TUENTI");
	
	/** The nombre empresa. */
	private String nombreEmpresa;
	
	/** The fiid. */
	private String fiid;
	
	/** The codigo empresa. */
	private String codigoEmpresa;
	
	private String nombreFantasia;

	/**
	 * Instantiates a new compania celular enum.
	 *
	 * @param nombreEmpresa the nombre empresa
	 * @param fiid the fiid
	 * @param codigoEmpresa the codigo empresa
	 */
	CompaniaCelularEnum (String nombreEmpresa, String fiid, String codigoEmpresa, String nombreFantasia) {
		this.nombreEmpresa = nombreEmpresa;
		this.fiid = fiid;
		this.codigoEmpresa = codigoEmpresa;
		this.nombreFantasia = nombreFantasia;
	}
	
	
	/**
	 * Buscar compania.
	 *
	 * @param codigo the codigo
	 * @return the compania celular enum
	 */
	public static CompaniaCelularEnum buscarCompania(String codigo) {
		CompaniaCelularEnum[] values = CompaniaCelularEnum.values();

		CompaniaCelularEnum response = null;

		for (CompaniaCelularEnum compania : values) {
			if (codigo.toLowerCase().contains(compania.getCodigoEmpresa())) {
				response = compania;
			}
		}
		return response;
	}
	
	
	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombre empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
	public String getFiid() {
		return fiid;
	}

	/**
	 * Gets the codigo empresa.
	 *
	 * @return the codigo empresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}


	public String getNombreFantasia() {
		return nombreFantasia;
	}
	
}
