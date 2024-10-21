/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ComprobantesDTO.
 * 
 * @author sabrina.cis
 */
public class ComprobantesDTO {

	/** The comprobantes. */
	@NotNull
	private List<ComprobanteDTO> comprobantes;

	/** The error. */
	private Boolean tieneError = false;

	/** The codigo estadistica. */
	private String codigoEstadistica;

	/** The codigo error. */
	private String codigoError;

	/** The limites PMC. */
	private String limitesPMC;

	/** The limite transferencias. */
	private String limiteTransferencias;

	/** The limite pago tarjetas. */
	private String limitePagoTarjetas;

	/**
	 * Instantiates a new comprobantes DTO.
	 */
	public ComprobantesDTO() {
		super();
		comprobantes = new LinkedList<ComprobanteDTO>();
	}

	/**
	 * Instantiates a new comprobantes DTO.
	 * 
	 * @param comprobantes
	 *            the comprobantes
	 */
	public ComprobantesDTO(List<ComprobanteDTO> comprobantes) {
		super();
		int contadorSize = 0;
		if (comprobantes != null && !comprobantes.isEmpty()) {
			for (ComprobanteDTO comprobante : comprobantes) {
				comprobante.setIndice(contadorSize);
				contadorSize++;
			}
			this.comprobantes = comprobantes;
		} else {
			this.comprobantes = new LinkedList<ComprobanteDTO>();
		}
	}

	/**
	 * Instantiates a new comprobantes DTO.
	 * 
	 * @param comprobantes
	 *            the comprobantes
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param codigoError
	 *            the codigo error
	 */
	public ComprobantesDTO(List<ComprobanteDTO> comprobantes, String codigoEstadistica, String codigoError) {
		super();
		int contadorSize = 0;
		for (ComprobanteDTO comprobante : comprobantes) {
			comprobante.setIndice(contadorSize);
			contadorSize++;
		}
		this.comprobantes = comprobantes;
		this.codigoError = codigoError;
		this.codigoEstadistica = codigoEstadistica;
	}

	/**
	 * Instantiates a new comprobantes DTO.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param codigoError
	 *            the codigo error
	 * @param listaDeLimites
	 *            the lista de limites
	 */
	public ComprobantesDTO(List<ComprobanteDTO> comprobantes, String codigoEstadistica, String codigoError,
			List<String> listaDeLimites) {
		super();
		int contadorSize = 0;
		for (ComprobanteDTO comprobante : comprobantes) {
			comprobante.setIndice(contadorSize);
			contadorSize++;
		}
		this.comprobantes = comprobantes;
		this.codigoError = codigoError;
		this.codigoEstadistica = codigoEstadistica;
		this.limitesPMC = listaDeLimites.get(0);
		this.limiteTransferencias = listaDeLimites.get(1);
		this.limitePagoTarjetas = listaDeLimites.get(2);

	}

	/**
	 * Instantiates a new comprobantes DTO. Genera una instancia de
	 * ComprobantesDTO con error
	 * 
	 * @param b
	 *            the b
	 */
	public ComprobantesDTO(boolean b) {
		super();
		this.comprobantes = new LinkedList<ComprobanteDTO>();
		this.tieneError = b;
	}

	/**
	 * Instantiates a new comprobantes DTO.
	 * 
	 * @param informes
	 *            the informes
	 * @param b
	 *            the b
	 */
	public ComprobantesDTO(List<ComprobanteDTO> informes, boolean b) {
		super();
		this.comprobantes = informes;
		this.tieneError = b;
	}

	/**
	 * Gets the comprobantes.
	 * 
	 * @return the comprobantes
	 */
	public List<ComprobanteDTO> getComprobantes() {
		return comprobantes;
	}

	/**
	 * Sets the comprobantes.
	 * 
	 * @param comprobantes
	 *            the comprobantes to set
	 */
	public void setComprobantes(List<ComprobanteDTO> comprobantes) {
		this.comprobantes = comprobantes;
	}

	/**
	 * Gets the tiene error.
	 * 
	 * @return the error
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 * 
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

	/**
	 * Gets the codigo estadistica.
	 * 
	 * @return the codigo estadistica
	 */
	public String getCodigoEstadistica() {
		return codigoEstadistica;
	}

	/**
	 * Sets the codigo estadistica.
	 * 
	 * @param codigoEstadistica
	 *            the new codigo estadistica
	 */
	public void setCodigoEstadistica(String codigoEstadistica) {
		this.codigoEstadistica = codigoEstadistica;
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
	 * @param codigoError
	 *            the new codigo error
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the limites PMC.
	 *
	 * @return the limites PMC
	 */
	public String getLimitesPMC() {
		return limitesPMC;
	}

	/**
	 * Sets the limites PMC.
	 *
	 * @param limitesPMC
	 *            the new limites PMC
	 */
	public void setLimitesPMC(String limitesPMC) {
		this.limitesPMC = limitesPMC;
	}

	/**
	 * Gets the limite transferencias.
	 *
	 * @return the limite transferencias
	 */
	public String getLimiteTransferencias() {
		return limiteTransferencias;
	}

	/**
	 * Sets the limite transferencias.
	 *
	 * @param limiteTransferencias
	 *            the new limite transferencias
	 */
	public void setLimiteTransferencias(String limiteTransferencias) {
		this.limiteTransferencias = limiteTransferencias;
	}

	/**
	 * Gets the limite pago tarjetas.
	 *
	 * @return the limite pago tarjetas
	 */
	public String getLimitePagoTarjetas() {
		return limitePagoTarjetas;
	}

	/**
	 * Sets the limite pago tarjetas.
	 *
	 * @param limitePagoTarjetas
	 *            the new limite pago tarjetas
	 */
	public void setLimitePagoTarjetas(String limitePagoTarjetas) {
		this.limitePagoTarjetas = limitePagoTarjetas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(comprobantes);
		return hcb.toHashCode();
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ComprobantesDTO other = (ComprobantesDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(comprobantes, other.getComprobantes());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(comprobantes);
		return sb.toString();
	}

}
