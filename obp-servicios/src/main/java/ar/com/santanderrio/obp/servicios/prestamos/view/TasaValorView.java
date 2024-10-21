/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.PrestamoCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;

/**
 * The Class TasaValorView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class TasaValorView {

	/** The tasa efectiva anual. */
	private String tasaEfectivaAnual;

	/** The tasa nominal anual. */
	private String tasaNominalAnual;

	/** The costo financiero total anual. */
	private String costoFinancieroTotalAnual;

	/** The costo financiero total anual sin impuestos. */
	private String costoFinancieroTotalAnualSinImpuestos;

	/** The Constant VALOR_CERO. */
	private static final String VALOR_CERO = "000000000";

	/**
	 * Instantiates a new tasa valor view.
	 */
	public TasaValorView() {
		super();
	}

	/**
	 * Instantiates a new tasa valor view.
	 *
	 * @param cuotaPrestamoEntity
	 *            the cuota prestamo entity
	 */
	public TasaValorView(CuotaPrestamoEntity cuotaPrestamoEntity) {
		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getTasaRec())) {
			this.setTasaNominalAnual(formatearBigDecimal(cuotaPrestamoEntity.getTasaRec()));
		}

		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getTae())) {
			this.setTasaEfectivaAnual(formatearBigDecimal(cuotaPrestamoEntity.getTae()));
		}

		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getCftna())) {
			this.setCostoFinancieroTotalAnual(formatearBigDecimal(cuotaPrestamoEntity.getCftna()));
		}

		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getCftnasi())) {
			this.setCostoFinancieroTotalAnualSinImpuestos(formatearBigDecimal(cuotaPrestamoEntity.getCftnasi()));
		}

	}

	/**
	 * Instantiates a new tasa valor view.
	 *
	 * @param cuotaPrestamoEntity
	 *            the cuota prestamo entity
	 */
	public TasaValorView(PrestamoCuotaPagaOutEntity cuotaPrestamoEntity) {
		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getTna())) {
			this.setTasaNominalAnual(formatearBigDecimal(cuotaPrestamoEntity.getTna()));
		}

		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getTae())) {
			this.setTasaEfectivaAnual(formatearBigDecimal(cuotaPrestamoEntity.getTae()));
		}

		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getCftna())) {
			this.setCostoFinancieroTotalAnual(formatearBigDecimal(cuotaPrestamoEntity.getCftna()));
		}

		if (!VALOR_CERO.equals(cuotaPrestamoEntity.getCftnasi())) {
			this.setCostoFinancieroTotalAnualSinImpuestos(formatearBigDecimal(cuotaPrestamoEntity.getCftnasi()));
		}

	}

	/**
	 * Instantiates a new tasa valor view.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public TasaValorView(Prestamo prestamo, PreFormalizacion preFormalizacion) {

        if (prestamo.isInformeCoeficienteNoDisponible()) {
            return;
        }

		if (BigDecimal.ZERO.compareTo(prestamo.getTasaPrestamo()) != 0) {
			this.setTasaNominalAnual(formatearValorTasaBigDecimal(prestamo.getTasaPrestamo()));
		}

		if (BigDecimal.ZERO.compareTo(prestamo.getTasaAnualEfectiva()) != 0) {
			this.setTasaEfectivaAnual(formatearValorTasaBigDecimal(prestamo.getTasaAnualEfectiva()));
		}

		if (BigDecimal.ZERO.compareTo(preFormalizacion.getPrestamoDebitoAdherido().getCft()) != 0) {
			this.setCostoFinancieroTotalAnual(
					formatearValorTasaBigDecimal(preFormalizacion.getPrestamoDebitoAdherido().getCft()));
		}

		if (BigDecimal.ZERO.compareTo(preFormalizacion.getPrestamoDebitoAdherido().getCftsimp()) != 0) {
			this.setCostoFinancieroTotalAnualSinImpuestos(
					formatearValorTasaBigDecimal(preFormalizacion.getPrestamoDebitoAdherido().getCftsimp()));
		}

	}

	/**
	 * Gets the tasa efectiva anual.
	 *
	 * @return the tasa efectiva anual
	 */
	public String getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	/**
	 * Sets the tasa efectiva anual.
	 *
	 * @param tasaEfectivaAnual
	 *            the new tasa efectiva anual
	 */
	public void setTasaEfectivaAnual(String tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	/**
	 * Gets the tasa nominal anual.
	 *
	 * @return the tasa nominal anual
	 */
	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	/**
	 * Sets the tasa nominal anual.
	 *
	 * @param tasaNominalAnual
	 *            the new tasa nominal anual
	 */
	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	/**
	 * Gets the costo financiero total anual.
	 *
	 * @return the costo financiero total anual
	 */
	public String getCostoFinancieroTotalAnual() {
		return costoFinancieroTotalAnual;
	}

	/**
	 * Sets the costo financiero total anual.
	 *
	 * @param costoFinancieroTotalAnual
	 *            the new costo financiero total anual
	 */
	public void setCostoFinancieroTotalAnual(String costoFinancieroTotalAnual) {
		this.costoFinancieroTotalAnual = costoFinancieroTotalAnual;
	}

	/**
	 * Gets the costo financiero total anual sin impuestos.
	 *
	 * @return the costo financiero total anual sin impuestos
	 */
	public String getCostoFinancieroTotalAnualSinImpuestos() {
		return costoFinancieroTotalAnualSinImpuestos;
	}

	/**
	 * Sets the costo financiero total anual sin impuestos.
	 *
	 * @param costoFinancieroTotalAnualSinImpuestos
	 *            the new costo financiero total anual sin impuestos
	 */
	public void setCostoFinancieroTotalAnualSinImpuestos(String costoFinancieroTotalAnualSinImpuestos) {
		this.costoFinancieroTotalAnualSinImpuestos = costoFinancieroTotalAnualSinImpuestos;
	}

	/**
	 * Formatear valor tasa.
	 *
	 * @param tasa
	 *            the tasa
	 * @return the string
	 */
	private String formatearValorTasa(String tasa) {
		return ISBANStringUtils.formatearSaldoTasaString(tasa) + " %";
	}

	/**
	 * Formatear valor tasa big decimal.
	 *
	 * @param tasa
	 *            the tasa
	 * @return the string
	 */
	private String formatearValorTasaBigDecimal(BigDecimal tasa) {
		return ISBANStringUtils.formatearSaldo(tasa) + " %";
	}
	
	/**
	 * Formatear big decimal.
	 *
	 * @param tasa
	 *            the tasa
	 * @return the string
	 */
	private String formatearBigDecimal(String tasa) {
		return ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(ISBANStringUtils.formatearSaldoTasaString(tasa))) + " %";
	}

}
