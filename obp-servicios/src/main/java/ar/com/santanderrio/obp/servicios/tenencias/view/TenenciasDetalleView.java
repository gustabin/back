/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.tenencias.entity.ParticipantesEntity;;

/**
 * Objeto utilizado para el input de TenenciasDetalleSEI.
 *
 * @author desa
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenenciasDetalleView {

	/** The detalle cuotas prestamos views. */
	private List<DetalleCuotasPrestamosView> detalleCuotasPrestamosViews;

	/** The detalleimpuesto moneda extranjera views. */
	private List<DetalleimpuestoMonedaExtranjeraView> detalleimpuestoMonedaExtranjeraViews;

	/** The detalle tarjeta credito views. */
	private List<DetalleTarjetaCreditoView> detalleTarjetaCreditoViews;

	/** The detalle plazo fijo views. */
	private List<DetallePlazoFijoView> detallePlazoFijoViews;

	/** The detalle mensual impuestos views. */
	private List<DetalleMensualImpuestosView> detalleMensualImpuestosViews;

	/** The resumen cuenta inversiones views. */
	private List<ResumenCuentaInversionesView> resumenCuentaInversionesViews;

	/** The resumen cuenta inversiones views MON. */
	private List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsMON;

	/** The resumen cuenta inversiones views CEF. */
	private List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsCEF;

	/** The resumen cuenta inversiones views BON. */
	private List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsBON;

	/** The resumen cuenta inversiones views SHS. */
	private List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsSHS;

	/** The resumen cuenta inversiones views FON. */
	private List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsFON;
	
	/** The participantes pl. */
	private List<DatosParticipantesPLView> participantesViews;

	/**
	 * Gets the detalle cuotas prestamos views.
	 *
	 * @return the detalleCuotasPrestamosViews
	 */
	public List<DetalleCuotasPrestamosView> getDetalleCuotasPrestamosViews() {
		return detalleCuotasPrestamosViews;
	}

	/**
	 * @return the participantesViews
	 */
	public List<DatosParticipantesPLView> getParticipantesViews() {
		return participantesViews;
	}

	/**
	 * @param participantesViews the participantesViews to set
	 */
	public void setParticipantesViews(List<DatosParticipantesPLView> participantesViews) {
		this.participantesViews = participantesViews;
	}

	/**
	 * Sets the detalle cuotas prestamos views.
	 *
	 * @param detalleCuotasPrestamosViews
	 *            the detalleCuotasPrestamosViews to set
	 */
	public void setDetalleCuotasPrestamosViews(List<DetalleCuotasPrestamosView> detalleCuotasPrestamosViews) {
		this.detalleCuotasPrestamosViews = detalleCuotasPrestamosViews;
	}

	/**
	 * Gets the detalleimpuesto moneda extranjera views.
	 *
	 * @return the detalleimpuestoMonedaExtranjeraViews
	 */
	public List<DetalleimpuestoMonedaExtranjeraView> getDetalleimpuestoMonedaExtranjeraViews() {
		return detalleimpuestoMonedaExtranjeraViews;
	}

	/**
	 * Sets the detalleimpuesto moneda extranjera views.
	 *
	 * @param detalleimpuestoMonedaExtranjeraViews
	 *            the detalleimpuestoMonedaExtranjeraViews to set
	 */
	public void setDetalleimpuestoMonedaExtranjeraViews(
			List<DetalleimpuestoMonedaExtranjeraView> detalleimpuestoMonedaExtranjeraViews) {
		this.detalleimpuestoMonedaExtranjeraViews = detalleimpuestoMonedaExtranjeraViews;
	}

	/**
	 * Gets the detalle tarjeta credito views.
	 *
	 * @return the detalleTarjetaCreditoViews
	 */
	public List<DetalleTarjetaCreditoView> getDetalleTarjetaCreditoViews() {
		return detalleTarjetaCreditoViews;
	}

	/**
	 * Sets the detalle tarjeta credito views.
	 *
	 * @param detalleTarjetaCreditoViews
	 *            the detalleTarjetaCreditoViews to set
	 */
	public void setDetalleTarjetaCreditoViews(List<DetalleTarjetaCreditoView> detalleTarjetaCreditoViews) {
		this.detalleTarjetaCreditoViews = detalleTarjetaCreditoViews;
	}

	/**
	 * Gets the detalle plazo fijo views.
	 *
	 * @return the detallePlazoFijoViews
	 */
	public List<DetallePlazoFijoView> getDetallePlazoFijoViews() {
		return detallePlazoFijoViews;
	}

	/**
	 * Sets the detalle plazo fijo views.
	 *
	 * @param detallePlazoFijoViews
	 *            the detallePlazoFijoViews to set
	 */
	public void setDetallePlazoFijoViews(List<DetallePlazoFijoView> detallePlazoFijoViews) {
		this.detallePlazoFijoViews = detallePlazoFijoViews;
	}

	/**
	 * Gets the detalle mensual impuestos views.
	 *
	 * @return the detalleMensualimpuestosViews
	 */
	public List<DetalleMensualImpuestosView> getDetalleMensualImpuestosViews() {
		return detalleMensualImpuestosViews;
	}

	/**
	 * Sets the detalle mensual impuestos views.
	 *
	 * @param detalleMensualimpuestosViews
	 *            the detalleMensualimpuestosViews to set
	 */
	public void setDetalleMensualImpuestosViews(List<DetalleMensualImpuestosView> detalleMensualimpuestosViews) {
		this.detalleMensualImpuestosViews = detalleMensualimpuestosViews;
	}

	/**
	 * Gets the resumen cuenta inversiones views.
	 *
	 * @return the resumenCuentaInversionesViews
	 */
	public List<ResumenCuentaInversionesView> getResumenCuentaInversionesViews() {
		return resumenCuentaInversionesViews;
	}

	/**
	 * Sets the resumen cuenta inversiones views.
	 *
	 * @param resumenCuentaInversionesViews
	 *            the resumenCuentaInversionesViews to set
	 */
	public void setResumenCuentaInversionesViews(List<ResumenCuentaInversionesView> resumenCuentaInversionesViews) {
		this.resumenCuentaInversionesViews = resumenCuentaInversionesViews;
	}

	/**
	 * Gets the resumen cuenta inversiones views MON.
	 *
	 * @return the resumenCuentaInversionesViewsMON
	 */
	public List<ResumenCuentaInversionesView> getResumenCuentaInversionesViewsMON() {
		return resumenCuentaInversionesViewsMON;
	}

	/**
	 * Sets the resumen cuenta inversiones views MON.
	 *
	 * @param resumenCuentaInversionesViewsMON
	 *            the resumenCuentaInversionesViewsMON to set
	 */
	public void setResumenCuentaInversionesViewsMON(
			List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsMON) {
		this.resumenCuentaInversionesViewsMON = resumenCuentaInversionesViewsMON;
	}

	/**
	 * Gets the resumen cuenta inversiones views CEF.
	 *
	 * @return the resumenCuentaInversionesViewsCEF
	 */
	public List<ResumenCuentaInversionesView> getResumenCuentaInversionesViewsCEF() {
		return resumenCuentaInversionesViewsCEF;
	}

	/**
	 * Sets the resumen cuenta inversiones views CEF.
	 *
	 * @param resumenCuentaInversionesViewsCEF
	 *            the resumenCuentaInversionesViewsCEF to set
	 */
	public void setResumenCuentaInversionesViewsCEF(
			List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsCEF) {
		this.resumenCuentaInversionesViewsCEF = resumenCuentaInversionesViewsCEF;
	}

	/**
	 * Gets the resumen cuenta inversiones views BON.
	 *
	 * @return the resumenCuentaInversionesViewsBON
	 */
	public List<ResumenCuentaInversionesView> getResumenCuentaInversionesViewsBON() {
		return resumenCuentaInversionesViewsBON;
	}

	/**
	 * Sets the resumen cuenta inversiones views BON.
	 *
	 * @param resumenCuentaInversionesViewsBON
	 *            the resumenCuentaInversionesViewsBON to set
	 */
	public void setResumenCuentaInversionesViewsBON(
			List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsBON) {
		this.resumenCuentaInversionesViewsBON = resumenCuentaInversionesViewsBON;
	}

	/**
	 * Gets the resumen cuenta inversiones views SHS.
	 *
	 * @return the resumenCuentaInversionesViewsSHS
	 */
	public List<ResumenCuentaInversionesView> getResumenCuentaInversionesViewsSHS() {
		return resumenCuentaInversionesViewsSHS;
	}

	/**
	 * Sets the resumen cuenta inversiones views SHS.
	 *
	 * @param resumenCuentaInversionesViewsSHS
	 *            the resumenCuentaInversionesViewsSHS to set
	 */
	public void setResumenCuentaInversionesViewsSHS(
			List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsSHS) {
		this.resumenCuentaInversionesViewsSHS = resumenCuentaInversionesViewsSHS;
	}

	/**
	 * Gets the resumen cuenta inversiones views FON.
	 *
	 * @return the resumenCuentaInversionesViewsFON
	 */
	public List<ResumenCuentaInversionesView> getResumenCuentaInversionesViewsFON() {
		return resumenCuentaInversionesViewsFON;
	}

	/**
	 * Sets the resumen cuenta inversiones views FON.
	 *
	 * @param resumenCuentaInversionesViewsFON
	 *            the resumenCuentaInversionesViewsFON to set
	 */
	public void setResumenCuentaInversionesViewsFON(
			List<ResumenCuentaInversionesView> resumenCuentaInversionesViewsFON) {
		this.resumenCuentaInversionesViewsFON = resumenCuentaInversionesViewsFON;
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
		result = prime * result + ((detalleCuotasPrestamosViews == null) ? 0 : detalleCuotasPrestamosViews.hashCode());
		result = prime * result
				+ ((detalleMensualImpuestosViews == null) ? 0 : detalleMensualImpuestosViews.hashCode());
		result = prime * result + ((detallePlazoFijoViews == null) ? 0 : detallePlazoFijoViews.hashCode());
		result = prime * result + ((detalleTarjetaCreditoViews == null) ? 0 : detalleTarjetaCreditoViews.hashCode());
		result = prime * result + ((detalleimpuestoMonedaExtranjeraViews == null) ? 0
				: detalleimpuestoMonedaExtranjeraViews.hashCode());
		result = prime * result
				+ ((resumenCuentaInversionesViews == null) ? 0 : resumenCuentaInversionesViews.hashCode());
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
		TenenciasDetalleView other = (TenenciasDetalleView) obj;
		if (detalleCuotasPrestamosViews == null) {
			if (other.detalleCuotasPrestamosViews != null)
				return false;
		} else if (!detalleCuotasPrestamosViews.equals(other.detalleCuotasPrestamosViews))
			return false;
		if (detalleMensualImpuestosViews == null) {
			if (other.detalleMensualImpuestosViews != null)
				return false;
		} else if (!detalleMensualImpuestosViews.equals(other.detalleMensualImpuestosViews))
			return false;
		if (detallePlazoFijoViews == null) {
			if (other.detallePlazoFijoViews != null)
				return false;
		} else if (!detallePlazoFijoViews.equals(other.detallePlazoFijoViews))
			return false;
		if (detalleTarjetaCreditoViews == null) {
			if (other.detalleTarjetaCreditoViews != null)
				return false;
		} else if (!detalleTarjetaCreditoViews.equals(other.detalleTarjetaCreditoViews))
			return false;
		if (detalleimpuestoMonedaExtranjeraViews == null) {
			if (other.detalleimpuestoMonedaExtranjeraViews != null)
				return false;
		} else if (!detalleimpuestoMonedaExtranjeraViews.equals(other.detalleimpuestoMonedaExtranjeraViews))
			return false;
		if (resumenCuentaInversionesViews == null) {
			if (other.resumenCuentaInversionesViews != null)
				return false;
		} else if (!resumenCuentaInversionesViews.equals(other.resumenCuentaInversionesViews))
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
		return "TenenciasDetalleView [detalleCuotasPrestamosViews=" + detalleCuotasPrestamosViews
				+ ", detalleimpuestoMonedaExtranjeraViews=" + detalleimpuestoMonedaExtranjeraViews
				+ ", detalleTarjetaCreditoViews=" + detalleTarjetaCreditoViews + ", detallePlazoFijoViews="
				+ detallePlazoFijoViews + ", detalleMensualimpuestosViews=" + detalleMensualImpuestosViews
				+ ", resumenCuentaInversionesViews=" + resumenCuentaInversionesViews + "]";
	}

}
