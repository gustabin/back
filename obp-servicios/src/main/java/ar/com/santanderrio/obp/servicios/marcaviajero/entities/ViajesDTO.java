/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.List;

/**
 * The Class ViajesDTO.
 */
public class ViajesDTO {

	/** The identificador viaje. */
	private String identificadorViaje;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The fecha fin. */
	private String fechaFin;

	/** The status. */
	private String status;

	/** The pais DTO. */
	private PaisDTO paisDTO;

	/** The tarjetas DTO. */
	private TarjetasDTO tarjetasDTO;

	/** The paises. */
	private List<PaisDTO> paises;

	/** The tarjetas. */
	private List<TarjetasDTO> tarjetas;

	/** The Acciones permitidas DTO. */
	private AccionesPermitidasDTO AccionesPermitidasDTO;

	/** The acciones permitidas. */
	private List<AccionesPermitidasDTO> accionesPermitidas;

	/**
	 * Gets the identificador viaje.
	 *
	 * @return the identificador viaje
	 */
	public String getIdentificadorViaje() {
		return identificadorViaje;
	}

	/**
	 * Sets the identificador viaje.
	 *
	 * @param identificadorViaje
	 *            the new identificador viaje
	 */
	public void setIdentificadorViaje(String identificadorViaje) {
		this.identificadorViaje = identificadorViaje;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin
	 *            the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the pais DTO.
	 *
	 * @return the pais DTO
	 */
	public PaisDTO getPaisDTO() {
		return paisDTO;
	}

	/**
	 * Sets the pais DTO.
	 *
	 * @param paisDTO
	 *            the new pais DTO
	 */
	public void setPaisDTO(PaisDTO paisDTO) {
		this.paisDTO = paisDTO;
	}

	/**
	 * Gets the tarjetas DTO.
	 *
	 * @return the tarjetas DTO
	 */
	public TarjetasDTO getTarjetasDTO() {
		return tarjetasDTO;
	}

	/**
	 * Sets the tarjetas DTO.
	 *
	 * @param tarjetasDTO
	 *            the new tarjetas DTO
	 */
	public void setTarjetasDTO(TarjetasDTO tarjetasDTO) {
		this.tarjetasDTO = tarjetasDTO;
	}

	/**
	 * Gets the paises.
	 *
	 * @return the paises
	 */
	public List<PaisDTO> getPaises() {
		return paises;
	}

	/**
	 * Sets the paises.
	 *
	 * @param paises
	 *            the new paises
	 */
	public void setPaises(List<PaisDTO> paises) {
		this.paises = paises;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetasDTO> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetasDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the acciones permitidas DTO.
	 *
	 * @return the acciones permitidas DTO
	 */
	public AccionesPermitidasDTO getAccionesPermitidasDTO() {
		return AccionesPermitidasDTO;
	}

	/**
	 * Sets the acciones permitidas DTO.
	 *
	 * @param accionesPermitidasDTO
	 *            the new acciones permitidas DTO 
	 */
	public void setAccionesPermitidasDTO(AccionesPermitidasDTO accionesPermitidasDTO) {
		AccionesPermitidasDTO = accionesPermitidasDTO;
	}   

	/**
	 * Gets the acciones permitidas.
	 *
	 * @return the acciones permitidas
	 */
	public List<AccionesPermitidasDTO> getAccionesPermitidas() {
		return accionesPermitidas;
	}

	/**
	 * Sets the acciones permitidas.
	 *
	 * @param accionesPermitidas
	 *            the new acciones permitidas
	 */
	public void setAccionesPermitidas(List<AccionesPermitidasDTO> accionesPermitidas) {
		this.accionesPermitidas = accionesPermitidas;
	}

}
