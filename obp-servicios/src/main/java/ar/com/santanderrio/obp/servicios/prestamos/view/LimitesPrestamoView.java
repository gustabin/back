/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.prestamos.sei.RangoCuota;

/**
 * The Class LimitesPrestamoView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LimitesPrestamoView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5973905810192836981L;

	/** The tipo tasa. */
	private String tipoTasa;

	/** The importe min. */
	private BigDecimal importeMin;

	/** The importe max. */
	private BigDecimal importeMax;
	
	/** The cuota min. */
	private String cuotaMin;

	/** The cuota max. */
	private String cuotaMax;
	
	/**
	 * Instantiates a new limites prestamo view.
	 */
	public LimitesPrestamoView() {
		super();
	}

	/**
	 * Instantiates a new limites prestamo view.
	 *
	 * @param tipoTasa
	 *            the tipo tasa
	 * @param rangoCuota
	 *            the rango cuota
	 */
	public LimitesPrestamoView(String tipoTasa, RangoCuota rangoCuota) {
		this.tipoTasa = tipoTasa;
		this.importeMin = rangoCuota.getImporteMinimo();
		this.importeMax = rangoCuota.getImporteMaximo();
		this.cuotaMin = rangoCuota.getCantMinCuotas();
		this.cuotaMax = rangoCuota.getCantMaxCuotas();
	}

	/**
	 * Gets the tipo tasa.
	 *
	 * @return the tipo tasa
	 */
	public String getTipoTasa() {
		return tipoTasa;
	}

	/**
	 * Sets the tipo tasa.
	 *
	 * @param tipoTasa
	 *            the new tipo tasa
	 */
	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	/**
	 * Gets the importe min.
	 *
	 * @return the importe min
	 */
	public BigDecimal getImporteMin() {
		return importeMin;
	}

	/**
	 * Sets the importe min.
	 *
	 * @param importeMin
	 *            the new importe min
	 */
	public void setImporteMin(BigDecimal importeMin) {
		this.importeMin = importeMin;
	}

	/**
	 * Gets the importe max.
	 *
	 * @return the importe max
	 */
	public BigDecimal getImporteMax() {
		return importeMax;
	}

	/**
	 * Sets the importe max.
	 *
	 * @param importeMax
	 *            the new importe max
	 */
	public void setImporteMax(BigDecimal importeMax) {
		this.importeMax = importeMax;
	}

	/**
	 * Gets the cuota min.
	 *
	 * @return the cuota min
	 */
	public String getCuotaMin() {
		return cuotaMin;
	}

	/**
	 * Sets the cuota min.
	 *
	 * @param cuotaMin
	 *            the new cuota min
	 */
	public void setCuotaMin(String cuotaMin) {
		this.cuotaMin = cuotaMin;
	}
	
	/**
	 * Gets the cuota max.
	 *
	 * @return the cuota max
	 */
	public String getCuotaMax() {
		return cuotaMax;
	}

	/**
	 * Sets the cuota max.
	 *
	 * @param cuotaMax
	 *            the new cuota max
	 */
	public void setCuotaMax(String cuotaMax) {
		this.cuotaMax = cuotaMax;
	}
}
