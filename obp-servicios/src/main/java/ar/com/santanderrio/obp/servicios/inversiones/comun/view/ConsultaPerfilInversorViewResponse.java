/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class ConsultaPerfilInversorViewResponse.
 */
public class ConsultaPerfilInversorViewResponse {

	/** The perfil. */
	private String perfil;

	/** The id perfil. */
	private String idPerfil;

	/** The descripcion larga. */
	private String descripcionLarga;

	/** Texto Vigencia */
	private String textoVigencia;
	
	/** Texto Mensaje Perfil no realizado */
	private String descripcionLargaOperatoria;

	
	/**
	 * @return the descripcionLargaOperatoria
	 */
	public String getDescripcionLargaOperatoria() {
		return descripcionLargaOperatoria;
	}

	/**
	 * @param descripcionLargaOperatoria the descripcionLargaOperatoria to set
	 */
	public void setDescripcionLargaOperatoria(String descripcionLargaOperatoria) {
		this.descripcionLargaOperatoria = descripcionLargaOperatoria;
	}

	/**
	 * Gets the perfil.
	 *
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}

	/**
	 * Sets the perfil.
	 *
	 * @param perfil
	 *            the new perfil
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
	 * Sets the id perfil.
	 *
	 * @param idPerfil
	 *            the new id perfil
	 */
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	/**
	 * Gets the descripcion larga.
	 *
	 * @return the descripcion larga
	 */
	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	/**
	 * Sets the descripcion larga.
	 *
	 * @param descripcionLarga
	 *            the new descripcion larga
	 */
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	/**
	 * Gets the Texto Vigencia.
	 *
	 * @return the textoVigencia
	 */
	public String getTextoVigencia() {
		return textoVigencia;
	}

	/**
	 * Sets the Texto vigencia.
	 *
	 * @param perfil the new textoVigencia
	 */
	public void setTextoVigencia(String textoVigencia) {
		this.textoVigencia = textoVigencia;
	}

}
