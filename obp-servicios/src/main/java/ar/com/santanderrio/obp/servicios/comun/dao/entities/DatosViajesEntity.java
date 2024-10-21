/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.marcaviajero.entities.DetalleViajeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosViajesEntity.
 */
public class DatosViajesEntity {

	/** The tipo documento. */
	private String tipoDocumento;

	/** The numero documento. */
	private String numeroDocumento;

	/** The sexo. */
	private String sexo;

	/** The codigo error. */
	private String codigoError;

	/** The detalle viaje DTO. */
	private DetalleViajeDTO detalleViajeDTO;

	/** The lista viajes. */
	private List<DetalleViajeDTO> listaViajes;

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento the new numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo the new sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigo error
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError the new codigo error
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the detalle viaje DTO.
	 *
	 * @return the detalle viaje DTO
	 */
	public DetalleViajeDTO getDetalleViajeDTO() {
		return detalleViajeDTO;
	}

	/**
	 * Sets the detalle viaje DTO.
	 *
	 * @param detalleViajeDTO the new detalle viaje DTO
	 */
	public void setDetalleViajeDTO(DetalleViajeDTO detalleViajeDTO) {
		this.detalleViajeDTO = detalleViajeDTO;
	}

	/**
	 * Gets the lista viajes.
	 *
	 * @return the lista viajes
	 */
	public List<DetalleViajeDTO> getListaViajes() {
		return listaViajes;
	}

	/**
	 * Sets the lista viajes.
	 *
	 * @param listaViajes the new lista viajes
	 */
	public void setListaViajes(List<DetalleViajeDTO> listaViajes) {
		this.listaViajes = listaViajes;
	}

}
