/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

/**
 * Objeto utilizado para retornar del DAO.
 *
 * @author desa
 */
public class TenenciasDetalleOutEntity {

	/** The detalle cuotas prestamos entities. */
	private List<DetalleCuotasPrestamosEntity> detalleCuotasPrestamosEntities;

	/** The detalle plazo fijo entities. */
	private List<DetallePlazoFijoEntity> detallePlazoFijoEntities;

	/** The detalle tarjeta credito entities. */
	private List<DetalleTarjetaCreditoEntity> detalleTarjetaCreditoEntities;

	/** The detalle impuesto moneda extranjera entities. */
	private List<DetalleImpuestoMonedaExtranjeraEntity> detalleImpuestoMonedaExtranjeraEntities;

	/** The detalle mensual impuestos entity. */
	private List<DetalleMensualImpuestosEntity> detalleMensualImpuestosEntity;

	/** The resumen cuenta inversiones entities. */
	private List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntities;

	/** The resumen cuenta inversiones entities MON. */
	private List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesMON;

	/** The resumen cuenta inversiones entities SHS. */
	private List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesSHS;

	/** The resumen cuenta inversiones entities FON. */
	private List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesFON;

	/** The resumen cuenta inversiones entities BON. */
	private List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesBON;

	/** The resumen cuenta inversiones entities CEF. */
	private List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesCEF;
	
	/** The participantes pl. */
	private List<DatosParticipantesEntity> participantesPL;

	/**
	 * Gets the detalle cuotas prestamos entities.
	 *
	 * @return the detalleCuotasPrestamosEntities
	 */
	public List<DetalleCuotasPrestamosEntity> getDetalleCuotasPrestamosEntities() {
		return detalleCuotasPrestamosEntities;
	}

	/**
	 * @return the participantesPL
	 */
	public List<DatosParticipantesEntity> getParticipantesPL() {
		return participantesPL;
	}

	/**
	 * @param participantesPL the participantesPL to set
	 */
	public void setParticipantesPL(List<DatosParticipantesEntity> participantesPL) {
		this.participantesPL = participantesPL;
	}

	/**
	 * Sets the detalle cuotas prestamos entities.
	 *
	 * @param detalleCuotasPrestamosEntities
	 *            the detalleCuotasPrestamosEntities to set
	 */
	public void setDetalleCuotasPrestamosEntities(List<DetalleCuotasPrestamosEntity> detalleCuotasPrestamosEntities) {
		this.detalleCuotasPrestamosEntities = detalleCuotasPrestamosEntities;
	}

	/**
	 * Gets the detalle plazo fijo entities.
	 *
	 * @return the detallePlazoFijoEntities
	 */
	public List<DetallePlazoFijoEntity> getDetallePlazoFijoEntities() {
		return detallePlazoFijoEntities;
	}

	/**
	 * Sets the detalle plazo fijo entities.
	 *
	 * @param detallePlazoFijoEntities
	 *            the detallePlazoFijoEntities to set
	 */
	public void setDetallePlazoFijoEntities(List<DetallePlazoFijoEntity> detallePlazoFijoEntities) {
		this.detallePlazoFijoEntities = detallePlazoFijoEntities;
	}

	/**
	 * Gets the detalle tarjeta credito entities.
	 *
	 * @return the detalleTarjetaCreditoEntities
	 */
	public List<DetalleTarjetaCreditoEntity> getDetalleTarjetaCreditoEntities() {
		return detalleTarjetaCreditoEntities;
	}

	/**
	 * Sets the detalle tarjeta credito entities.
	 *
	 * @param detalleTarjetaCreditoEntities
	 *            the detalleTarjetaCreditoEntities to set
	 */
	public void setDetalleTarjetaCreditoEntities(List<DetalleTarjetaCreditoEntity> detalleTarjetaCreditoEntities) {
		this.detalleTarjetaCreditoEntities = detalleTarjetaCreditoEntities;
	}

	/**
	 * Gets the detalle impuesto moneda extranjera entities.
	 *
	 * @return the detalleImpuestoMonedaExtranjeraEntities
	 */
	public List<DetalleImpuestoMonedaExtranjeraEntity> getDetalleImpuestoMonedaExtranjeraEntities() {
		return detalleImpuestoMonedaExtranjeraEntities;
	}

	/**
	 * Sets the detalle impuesto moneda extranjera entities.
	 *
	 * @param detalleImpuestoMonedaExtranjeraEntities
	 *            the detalleImpuestoMonedaExtranjeraEntities to set
	 */
	public void setDetalleImpuestoMonedaExtranjeraEntities(
			List<DetalleImpuestoMonedaExtranjeraEntity> detalleImpuestoMonedaExtranjeraEntities) {
		this.detalleImpuestoMonedaExtranjeraEntities = detalleImpuestoMonedaExtranjeraEntities;
	}

	/**
	 * Gets the resumen cuenta inversiones entities.
	 *
	 * @return the resumenCuentaInversionesEntities
	 */
	public List<ResumenCuentaInversionesEntity> getResumenCuentaInversionesEntities() {
		return resumenCuentaInversionesEntities;
	}

	/**
	 * Sets the resumen cuenta inversiones entities.
	 *
	 * @param resumenCuentaInversionesEntities
	 *            the resumenCuentaInversionesEntities to set
	 */
	public void setResumenCuentaInversionesEntities(
			List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntities) {
		this.resumenCuentaInversionesEntities = resumenCuentaInversionesEntities;
	}

	/**
	 * Gets the detalle mensual impuestos entity.
	 *
	 * @return the detalleMensualImpuestosEntity
	 */
	public List<DetalleMensualImpuestosEntity> getDetalleMensualImpuestosEntity() {
		return detalleMensualImpuestosEntity;
	}

	/**
	 * Sets the detalle mensual impuestos entity.
	 *
	 * @param detalleMensualImpuestosEntity
	 *            the detalleMensualImpuestosEntity to set
	 */
	public void setDetalleMensualImpuestosEntity(List<DetalleMensualImpuestosEntity> detalleMensualImpuestosEntity) {
		this.detalleMensualImpuestosEntity = detalleMensualImpuestosEntity;
	}

	/**
	 * Gets the resumen cuenta inversiones entities MON.
	 *
	 * @return the resumenCuentaInversionesEntitiesMON
	 */
	public List<ResumenCuentaInversionesEntity> getResumenCuentaInversionesEntitiesMON() {
		return resumenCuentaInversionesEntitiesMON;
	}

	/**
	 * Sets the resumen cuenta inversiones entities MON.
	 *
	 * @param resumenCuentaInversionesEntitiesMON
	 *            the resumenCuentaInversionesEntitiesMON to set
	 */
	public void setResumenCuentaInversionesEntitiesMON(
			List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesMON) {
		this.resumenCuentaInversionesEntitiesMON = resumenCuentaInversionesEntitiesMON;
	}

	/**
	 * Gets the resumen cuenta inversiones entities SHS.
	 *
	 * @return the resumenCuentaInversionesEntitiesSHS
	 */
	public List<ResumenCuentaInversionesEntity> getResumenCuentaInversionesEntitiesSHS() {
		return resumenCuentaInversionesEntitiesSHS;
	}

	/**
	 * Sets the resumen cuenta inversiones entities SHS.
	 *
	 * @param resumenCuentaInversionesEntitiesSHS
	 *            the resumenCuentaInversionesEntitiesSHS to set
	 */
	public void setResumenCuentaInversionesEntitiesSHS(
			List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesSHS) {
		this.resumenCuentaInversionesEntitiesSHS = resumenCuentaInversionesEntitiesSHS;
	}

	/**
	 * Gets the resumen cuenta inversiones entities FON.
	 *
	 * @return the resumenCuentaInversionesEntitiesFON
	 */
	public List<ResumenCuentaInversionesEntity> getResumenCuentaInversionesEntitiesFON() {
		return resumenCuentaInversionesEntitiesFON;
	}

	/**
	 * Sets the resumen cuenta inversiones entities FON.
	 *
	 * @param resumenCuentaInversionesEntitiesFON
	 *            the resumenCuentaInversionesEntitiesFON to set
	 */
	public void setResumenCuentaInversionesEntitiesFON(
			List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesFON) {
		this.resumenCuentaInversionesEntitiesFON = resumenCuentaInversionesEntitiesFON;
	}

	/**
	 * Gets the resumen cuenta inversiones entities BON.
	 *
	 * @return the resumenCuentaInversionesEntitiesBON
	 */
	public List<ResumenCuentaInversionesEntity> getResumenCuentaInversionesEntitiesBON() {
		return resumenCuentaInversionesEntitiesBON;
	}

	/**
	 * Sets the resumen cuenta inversiones entities BON.
	 *
	 * @param resumenCuentaInversionesEntitiesBON
	 *            the resumenCuentaInversionesEntitiesBON to set
	 */
	public void setResumenCuentaInversionesEntitiesBON(
			List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesBON) {
		this.resumenCuentaInversionesEntitiesBON = resumenCuentaInversionesEntitiesBON;
	}

	/**
	 * Gets the resumen cuenta inversiones entities CEF.
	 *
	 * @return the resumenCuentaInversionesEntitiesCEF
	 */
	public List<ResumenCuentaInversionesEntity> getResumenCuentaInversionesEntitiesCEF() {
		return resumenCuentaInversionesEntitiesCEF;
	}

	/**
	 * Sets the resumen cuenta inversiones entities CEF.
	 *
	 * @param resumenCuentaInversionesEntitiesCEF
	 *            the resumenCuentaInversionesEntitiesCEF to set
	 */
	public void setResumenCuentaInversionesEntitiesCEF(
			List<ResumenCuentaInversionesEntity> resumenCuentaInversionesEntitiesCEF) {
		this.resumenCuentaInversionesEntitiesCEF = resumenCuentaInversionesEntitiesCEF;
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
		result = prime * result
				+ ((detalleCuotasPrestamosEntities == null) ? 0 : detalleCuotasPrestamosEntities.hashCode());
		result = prime * result
				+ ((detalleMensualImpuestosEntity == null) ? 0 : detalleMensualImpuestosEntity.hashCode());
		result = prime * result + ((detalleImpuestoMonedaExtranjeraEntities == null) ? 0
				: detalleImpuestoMonedaExtranjeraEntities.hashCode());
		result = prime * result + ((detallePlazoFijoEntities == null) ? 0 : detallePlazoFijoEntities.hashCode());
		result = prime * result
				+ ((detalleTarjetaCreditoEntities == null) ? 0 : detalleTarjetaCreditoEntities.hashCode());
		result = prime * result
				+ ((resumenCuentaInversionesEntities == null) ? 0 : resumenCuentaInversionesEntities.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenenciasDetalleOutEntity other = (TenenciasDetalleOutEntity) obj;
		if (detalleCuotasPrestamosEntities == null) {
			if (other.detalleCuotasPrestamosEntities != null)
				return false;
		} else if (!detalleCuotasPrestamosEntities.equals(other.detalleCuotasPrestamosEntities))
			return false;
		if (detalleMensualImpuestosEntity == null) {
			if (other.detalleMensualImpuestosEntity != null)
				return false;
		} else if (!detalleMensualImpuestosEntity.equals(other.detalleMensualImpuestosEntity))
			return false;
		if (detalleImpuestoMonedaExtranjeraEntities == null) {
			if (other.detalleImpuestoMonedaExtranjeraEntities != null)
				return false;
		} else if (!detalleImpuestoMonedaExtranjeraEntities.equals(other.detalleImpuestoMonedaExtranjeraEntities))
			return false;
		if (detallePlazoFijoEntities == null) {
			if (other.detallePlazoFijoEntities != null)
				return false;
		} else if (!detallePlazoFijoEntities.equals(other.detallePlazoFijoEntities))
			return false;
		if (detalleTarjetaCreditoEntities == null) {
			if (other.detalleTarjetaCreditoEntities != null)
				return false;
		} else if (!detalleTarjetaCreditoEntities.equals(other.detalleTarjetaCreditoEntities))
			return false;
		if (resumenCuentaInversionesEntities == null) {
			if (other.resumenCuentaInversionesEntities != null)
				return false;
		} else if (!resumenCuentaInversionesEntities.equals(other.resumenCuentaInversionesEntities))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenenciasDetalleOutEntity [detalleCuotasPrestamosEntities=" + detalleCuotasPrestamosEntities
				+ ", detallePlazoFijoEntities=" + detallePlazoFijoEntities + ", detalleTarjetaCreditoEntities="
				+ detalleTarjetaCreditoEntities + ", detalleImpuestoMonedaExtranjeraEntities="
				+ detalleImpuestoMonedaExtranjeraEntities + ", detalleMensualImpuestosEntity="
				+ detalleMensualImpuestosEntity + ", resumenCuentaInversionesEntities="
				+ resumenCuentaInversionesEntities + ", resumenCuentaInversionesEntitiesMON="
				+ resumenCuentaInversionesEntitiesMON + ", resumenCuentaInversionesEntitiesSHS="
				+ resumenCuentaInversionesEntitiesSHS + ", resumenCuentaInversionesEntitiesFON="
				+ resumenCuentaInversionesEntitiesFON + ", resumenCuentaInversionesEntitiesBON="
				+ resumenCuentaInversionesEntitiesBON + ", resumenCuentaInversionesEntitiesCEF="
				+ resumenCuentaInversionesEntitiesCEF + "]";
	}

}
