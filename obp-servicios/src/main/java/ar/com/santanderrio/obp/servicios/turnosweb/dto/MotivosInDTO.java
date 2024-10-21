package ar.com.santanderrio.obp.servicios.turnosweb.dto;

/**
 * The Class MotivosInDTO.
 */
public class MotivosInDTO {
	
	/** The sector. */
	private String sector;
	
	/** The nro suc. */
	private String nroSuc;

	/**
	 * Instantiates a new motivos in DTO.
	 *
	 * @param nroSuc the nro suc
	 * @param sector the sector
	 */
	public MotivosInDTO(String nroSuc, String sector) {
		super();
		this.nroSuc = nroSuc;
		this.sector = sector;
	}

	/**
	 * Gets the sector.
	 *
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Sets the sector.
	 *
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * Gets the nro suc.
	 *
	 * @return the nroSuc
	 */
	public String getNroSuc() {
		return nroSuc;
	}

	/**
	 * Sets the nro suc.
	 *
	 * @param nroSuc
	 *            the nroSuc to set
	 */
	public void setNroSuc(String nroSuc) {
		this.nroSuc = nroSuc;
	}

}
