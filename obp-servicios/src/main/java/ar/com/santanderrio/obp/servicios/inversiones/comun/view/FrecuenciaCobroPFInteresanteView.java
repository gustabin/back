/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.FrecuenciaCobroPFInteresanteOutEntity;

/**
 * The Class FrecuenciaCobroPFInteresanteView.
 */
public class FrecuenciaCobroPFInteresanteView {

	/** The fecha liquidacion. */
	private String fechaLiquidacion;
	
	/** The capital. */
	private String capital;
	
	/** The intereses. */
	private String intereses;
	
	/** The impuestos. */
	private String impuestos;
	
	/** The total. */
	private String total;

	/**
	 * Instantiates a new frecuencia cobro PF interesante view.
	 */
	public FrecuenciaCobroPFInteresanteView() {
		super();
	}
	
	/**
	 * Instantiates a new frecuencia cobro PF interesante view.
	 *
	 * @param frecuenciaEntity
	 *            the frecuencia entity
	 * @param detalleIn
	 *            the detalle in
	 */
	public FrecuenciaCobroPFInteresanteView(FrecuenciaCobroPFInteresanteOutEntity frecuenciaEntity, DetallePFInteresanteInView detalleIn) {
	
		String simboloMoneda = DivisaEnum.fromCodigoString(detalleIn.getMoneda()).getSimbolo() + " "; 
		
		this.fechaLiquidacion = formatearFecha(frecuenciaEntity.getFechaLiquidacion());
		this.capital = simboloMoneda + ISBANStringUtils.formatearConComaYDosDecimales(frecuenciaEntity.getCapital().trim());
		this.impuestos = simboloMoneda + ISBANStringUtils.formatearConComaYDosDecimales(frecuenciaEntity.getImpuestos().trim());
		this.intereses = simboloMoneda + ISBANStringUtils.formatearConComaYDosDecimales(frecuenciaEntity.getIntereses().trim());
		this.total = simboloMoneda + ISBANStringUtils.formatearConComaYDosDecimales(frecuenciaEntity.getTotal().trim());
		
	}
	
	
	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the new intereses
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	
	/**
	 * Formatear fecha.
	 *
	 * @param fechaSinFormato
	 *            the fecha sin formato
	 * @return the string
	 */
	private String formatearFecha (String fechaSinFormato) {
		
		String dia = fechaSinFormato.substring(0, 2);
		String mes = fechaSinFormato.substring(2, 4);
		String anio = fechaSinFormato.substring(4, fechaSinFormato.length());
		
		return dia + "/" + mes + "/" + anio;
	}
	
}