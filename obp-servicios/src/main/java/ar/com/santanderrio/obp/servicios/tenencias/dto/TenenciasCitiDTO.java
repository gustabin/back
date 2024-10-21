/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dto;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author desa
 *
 */
public class TenenciasCitiDTO {

	/** The detalle cuota prestamo DTO. */
	private List<DetalleCuotaPrestamoDTO> detalleCuotaPrestamoDTO;

	/** The detalle tarjeta DTO. */
	private List<DetalleTarjetaCreditoDTO> detalleTarjetaDTO;

	/** The detalle impuesto moneda extranjera DTO. */
	private List<DetalleImpuestoMonedaExtranjeraDTO> detalleImpuestoMonedaExtranjeraDTO;

	/** The detalle mensual impuestos DTO. */
	private List<DetalleMensualImpuestosDTO> detalleMensualImpuestosDTO;

	/** The detalle plazo fijo DTO. */
	private List<DetallePlazoFijoDTO> detallePlazoFijoDTO;

	/** The resumen cuenta inversion DTO. */
	private List<ResumenCuentaInversionDTO> resumenCuentaInversionDTO;

	/** The resumen cuenta inversiones MONDTO. */
	private List<ResumenCuentaInversionDTO> resumenCuentaInversionesMONDTO;

	/** The resumen cuenta inversiones CEFDTO. */
	private List<ResumenCuentaInversionDTO> resumenCuentaInversionesCEFDTO;

	/** The resumen cuenta inversiones BONDTO. */
	private List<ResumenCuentaInversionDTO> resumenCuentaInversionesBONDTO;

	/** The resumen cuenta inversiones SHSDTO. */
	private List<ResumenCuentaInversionDTO> resumenCuentaInversionesSHSDTO;

	/** The resumen cuenta inversiones FONDTO. */
	private List<ResumenCuentaInversionDTO> resumenCuentaInversionesFONDTO;

	/**
	 * Gets the detalle cuota prestamo DTO.
	 *
	 * @return the detalleCuotaPrestamoDTO
	 */
	public List<DetalleCuotaPrestamoDTO> getDetalleCuotaPrestamoDTO() {
		return detalleCuotaPrestamoDTO;
	}

	/**
	 * Sets the detalle cuota prestamo DTO.
	 *
	 * @param detalleCuotaPrestamoDTO
	 *            the detalleCuotaPrestamoDTO to set
	 */
	public void setDetalleCuotaPrestamoDTO(List<DetalleCuotaPrestamoDTO> detalleCuotaPrestamoDTO) {
		this.detalleCuotaPrestamoDTO = detalleCuotaPrestamoDTO;
	}

	/**
	 * Gets the detalle impuesto moneda extranjera DTO.
	 *
	 * @return the detalleImpuestoMonedaExtranjeraDTO
	 */
	public List<DetalleImpuestoMonedaExtranjeraDTO> getDetalleImpuestoMonedaExtranjeraDTO() {
		return detalleImpuestoMonedaExtranjeraDTO;
	}

	/**
	 * Sets the detalle impuesto moneda extranjera DTO.
	 *
	 * @param detalleImpuestoMonedaExtranjeraDTO
	 *            the detalleImpuestoMonedaExtranjeraDTO to set
	 */
	public void setDetalleImpuestoMonedaExtranjeraDTO(
			List<DetalleImpuestoMonedaExtranjeraDTO> detalleImpuestoMonedaExtranjeraDTO) {
		this.detalleImpuestoMonedaExtranjeraDTO = detalleImpuestoMonedaExtranjeraDTO;
	}

	/**
	 * Gets the detalle mensual impuestos DTO.
	 *
	 * @return the detalleMensualImpuestosDTO
	 */
	public List<DetalleMensualImpuestosDTO> getDetalleMensualImpuestosDTO() {
		return detalleMensualImpuestosDTO;
	}

	/**
	 * Sets the detalle mensual impuestos DTO.
	 *
	 * @param detalleMensualImpuestosDTO
	 *            the detalleMensualImpuestosDTO to set
	 */
	public void setDetalleMensualImpuestosDTO(List<DetalleMensualImpuestosDTO> detalleMensualImpuestosDTO) {
		this.detalleMensualImpuestosDTO = detalleMensualImpuestosDTO;
	}

	/**
	 * Gets the detalle plazo fijo DTO.
	 *
	 * @return the detallePlazoFijoDTO
	 */
	public List<DetallePlazoFijoDTO> getDetallePlazoFijoDTO() {
		return detallePlazoFijoDTO;
	}

	/**
	 * Sets the detalle plazo fijo DTO.
	 *
	 * @param detallePlazoFijoDTO
	 *            the detallePlazoFijoDTO to set
	 */
	public void setDetallePlazoFijoDTO(List<DetallePlazoFijoDTO> detallePlazoFijoDTO) {
		this.detallePlazoFijoDTO = detallePlazoFijoDTO;
	}

	/**
	 * Gets the resumen cuenta inversion DTO.
	 *
	 * @return the resumenCuentaInversionDTO
	 */
	public List<ResumenCuentaInversionDTO> getResumenCuentaInversionDTO() {
		return resumenCuentaInversionDTO;
	}

	/**
	 * Sets the resumen cuenta inversion DTO.
	 *
	 * @param resumenCuentaInversionDTO
	 *            the resumenCuentaInversionDTO to set
	 */
	public void setResumenCuentaInversionDTO(List<ResumenCuentaInversionDTO> resumenCuentaInversionDTO) {
		this.resumenCuentaInversionDTO = resumenCuentaInversionDTO;
	}

	/**
	 * Gets the detalle tarjeta DTO.
	 *
	 * @return the detalleTarjetaDTO
	 */
	public List<DetalleTarjetaCreditoDTO> getDetalleTarjetaDTO() {
		return detalleTarjetaDTO;
	}

	/**
	 * Sets the detalle tarjeta DTO.
	 *
	 * @param detalleTarjetaDTO
	 *            the detalleTarjetaDTO to set
	 */
	public void setDetalleTarjetaDTO(List<DetalleTarjetaCreditoDTO> detalleTarjetaDTO) {
		this.detalleTarjetaDTO = detalleTarjetaDTO;
	}

	/**
	 * Gets the resumen cuenta inversiones MONDTO.
	 *
	 * @return the resumenCuentaInversionesMONDTO
	 */
	public List<ResumenCuentaInversionDTO> getResumenCuentaInversionesMONDTO() {
		return resumenCuentaInversionesMONDTO;
	}

	/**
	 * Sets the resumen cuenta inversiones MONDTO.
	 *
	 * @param resumenCuentaInversionesMONDTO
	 *            the resumenCuentaInversionesMONDTO to set
	 */
	public void setResumenCuentaInversionesMONDTO(List<ResumenCuentaInversionDTO> resumenCuentaInversionesMONDTO) {
		this.resumenCuentaInversionesMONDTO = resumenCuentaInversionesMONDTO;
	}

	/**
	 * Gets the resumen cuenta inversiones CEFDTO.
	 *
	 * @return the resumenCuentaInversionesCEFDTO
	 */
	public List<ResumenCuentaInversionDTO> getResumenCuentaInversionesCEFDTO() {
		return resumenCuentaInversionesCEFDTO;
	}

	/**
	 * Sets the resumen cuenta inversiones CEFDTO.
	 *
	 * @param resumenCuentaInversionesCEFDTO
	 *            the resumenCuentaInversionesCEFDTO to set
	 */
	public void setResumenCuentaInversionesCEFDTO(List<ResumenCuentaInversionDTO> resumenCuentaInversionesCEFDTO) {
		this.resumenCuentaInversionesCEFDTO = resumenCuentaInversionesCEFDTO;
	}

	/**
	 * Gets the resumen cuenta inversiones BONDTO.
	 *
	 * @return the resumenCuentaInversionesBONDTO
	 */
	public List<ResumenCuentaInversionDTO> getResumenCuentaInversionesBONDTO() {
		return resumenCuentaInversionesBONDTO;
	}

	/**
	 * Sets the resumen cuenta inversiones BONDTO.
	 *
	 * @param resumenCuentaInversionesBONDTO
	 *            the resumenCuentaInversionesBONDTO to set
	 */
	public void setResumenCuentaInversionesBONDTO(List<ResumenCuentaInversionDTO> resumenCuentaInversionesBONDTO) {
		this.resumenCuentaInversionesBONDTO = resumenCuentaInversionesBONDTO;
	}

	/**
	 * Gets the resumen cuenta inversiones SHSDTO.
	 *
	 * @return the resumenCuentaInversionesSHSDTO
	 */
	public List<ResumenCuentaInversionDTO> getResumenCuentaInversionesSHSDTO() {
		return resumenCuentaInversionesSHSDTO;
	}

	/**
	 * Sets the resumen cuenta inversiones SHSDTO.
	 *
	 * @param resumenCuentaInversionesSHSDTO
	 *            the resumenCuentaInversionesSHSDTO to set
	 */
	public void setResumenCuentaInversionesSHSDTO(List<ResumenCuentaInversionDTO> resumenCuentaInversionesSHSDTO) {
		this.resumenCuentaInversionesSHSDTO = resumenCuentaInversionesSHSDTO;
	}

	/**
	 * Gets the resumen cuenta inversiones FONDTO.
	 *
	 * @return the resumenCuentaInversionesFONDTO
	 */
	public List<ResumenCuentaInversionDTO> getResumenCuentaInversionesFONDTO() {
		return resumenCuentaInversionesFONDTO;
	}

	/**
	 * Sets the resumen cuenta inversiones FONDTO.
	 *
	 * @param resumenCuentaInversionesFONDTO
	 *            the resumenCuentaInversionesFONDTO to set
	 */
	public void setResumenCuentaInversionesFONDTO(List<ResumenCuentaInversionDTO> resumenCuentaInversionesFONDTO) {
		this.resumenCuentaInversionesFONDTO = resumenCuentaInversionesFONDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detalleCuotaPrestamoDTO == null) ? 0 : detalleCuotaPrestamoDTO.hashCode());
		result = prime * result
				+ ((detalleImpuestoMonedaExtranjeraDTO == null) ? 0 : detalleImpuestoMonedaExtranjeraDTO.hashCode());
		result = prime * result + ((detalleMensualImpuestosDTO == null) ? 0 : detalleMensualImpuestosDTO.hashCode());
		result = prime * result + ((detallePlazoFijoDTO == null) ? 0 : detallePlazoFijoDTO.hashCode());
		result = prime * result + ((detalleTarjetaDTO == null) ? 0 : detalleTarjetaDTO.hashCode());
		result = prime * result + ((resumenCuentaInversionDTO == null) ? 0 : resumenCuentaInversionDTO.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenenciasDetalleDTO [detalleCuotaPrestamoDTO=" + detalleCuotaPrestamoDTO + ", detalleTarjetaDTO="
				+ detalleTarjetaDTO + ", detalleImpuestoMonedaExtranjeraDTO=" + detalleImpuestoMonedaExtranjeraDTO
				+ ", detalleMensualImpuestosDTO=" + detalleMensualImpuestosDTO + ", detallePlazoFijoDTO="
				+ detallePlazoFijoDTO + ", resumenCuentaInversionDTO=" + resumenCuentaInversionDTO
				+ ", resumenCuentaInversionesMONDTO=" + resumenCuentaInversionesMONDTO
				+ ", resumenCuentaInversionesCEFDTO=" + resumenCuentaInversionesCEFDTO
				+ ", resumenCuentaInversionesBONDTO=" + resumenCuentaInversionesBONDTO
				+ ", resumenCuentaInversionesSHSDTO=" + resumenCuentaInversionesSHSDTO
				+ ", resumenCuentaInversionesFONDTO=" + resumenCuentaInversionesFONDTO + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		TenenciasCitiDTO other = (TenenciasCitiDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(detalleCuotaPrestamoDTO, other.getDetalleCuotaPrestamoDTO())
				.append(detalleTarjetaDTO, other.getDetalleTarjetaDTO())
				.append(detalleImpuestoMonedaExtranjeraDTO, other.getDetalleImpuestoMonedaExtranjeraDTO())
				.append(detalleMensualImpuestosDTO, other.getDetalleMensualImpuestosDTO())
				.append(detallePlazoFijoDTO, other.getDetallePlazoFijoDTO())
				.append(resumenCuentaInversionDTO, other.getResumenCuentaInversionDTO())
				.append(resumenCuentaInversionesMONDTO, other.getResumenCuentaInversionesMONDTO())
				.append(resumenCuentaInversionesCEFDTO, other.getResumenCuentaInversionesCEFDTO())
				.append(resumenCuentaInversionesBONDTO, other.getResumenCuentaInversionesBONDTO())
				.append(resumenCuentaInversionesSHSDTO, other.getResumenCuentaInversionesSHSDTO())
				.append(resumenCuentaInversionesFONDTO, other.getResumenCuentaInversionesFONDTO()).isEquals();
	}
}
