/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class TenenciaProductosMonedaDTO.
 */
public class TenenciaProductosMonedaDTO {

	/** The Constant GUION_MEDIO. */
	private static final Object GUION_MEDIO = "-";
	
	/** The totalTenenciaValuadaHoy. */
	private String totalTenenciaValuadaHoyParcial ="0";
	

	
		
	/**
	 * Gets the total tenencia valuada hoy parcial.
	 *
	 * @return the total tenencia valuada hoy parcial
	 */
	public String getTotalTenenciaValuadaHoyParcial() {
		return totalTenenciaValuadaHoyParcial;
	}



	/**
	 * Sets the total tenencia valuada hoy parcial.
	 *
	 * @param totalTenenciaValuadaHoyParcial
	 *            the new total tenencia valuada hoy parcial
	 */
	public void setTotalTenenciaValuadaHoyParcial(String totalTenenciaValuadaHoyParcial) {
		this.totalTenenciaValuadaHoyParcial = totalTenenciaValuadaHoyParcial;
	}



	/**
	 * Acumular totaltotal tenencia valuada hoy parcial.
	 *
	 * @param valor
	 *            the valor
	 */
	public void acumularTotaltotalTenenciaValuadaHoyParcial(String valor){

		if(!GUION_MEDIO.equals(valor)){
			BigDecimal valorASumar = new BigDecimal(valor);
			BigDecimal totalResultadoBigDecimal = new BigDecimal(this.totalTenenciaValuadaHoyParcial);
			this.totalTenenciaValuadaHoyParcial = valorASumar.add(totalResultadoBigDecimal).toString();		
			
		}
	}
	
	
	
	/**
	 * Obtener el porcentaje de tenencia de cada producto .
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 * @return the double
	 */
	public BigDecimal obtenerPorcentaje(BigDecimal valor1, BigDecimal valor2) {
		if (valor2.compareTo(BigDecimal.valueOf(0)) == 0) {
			return BigDecimal.valueOf(0);
		}

		valor1 = valor1.multiply(BigDecimal.valueOf(100)).divide(valor2, 2, RoundingMode.HALF_UP);

		if (valor1.compareTo(BigDecimal.valueOf(1)) == -1) {
			if (valor1.compareTo(BigDecimal.valueOf(0)) == 1) {
				return BigDecimal.valueOf(1);
			}
		}
		return valor1;
	}
	
	
}
