package ar.com.santanderrio.obp.servicios.prestamos.utils;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.view.LimitePrestamoPreaprobadoView;

public class PrestamoPreaprobadoUtils {

	/**
	 * Obtiene datos de la oferta en sesion en base al monto seleccionado
	 * @param limitesPreaprobado
	 * @param montoSolicitado
	 * @param nroCuotas 
	 * @return
	 */
	public static LimitePrestamoPreaprobadoView obtenerOfertaSeleccionada(List<LimitePrestamoPreaprobadoView> limitesPreaprobado, BigDecimal montoSolicitado, String nroCuotas) {
		
		LimitePrestamoPreaprobadoView limiteSeleccionado = new LimitePrestamoPreaprobadoView();
		for (LimitePrestamoPreaprobadoView limite : limitesPreaprobado) {
			if((montoSolicitado.compareTo(limite.getImporteMin()) == 0 || montoSolicitado.compareTo(limite.getImporteMin()) == 1)
					&& (montoSolicitado.compareTo(limite.getImporteMax()) < 0 || montoSolicitado.compareTo(limite.getImporteMax()) == 0)) {
				if(nroCuotas != null) { // si viene null, es porque solo buscamos las fechas de vigencia
					Integer cuotas = Integer.valueOf(nroCuotas);
					Integer cuotaMinima = Integer.valueOf(limite.getCuotaMin());
					Integer cuotaMaxima = Integer.valueOf(limite.getCuotaMax());
					if(cuotas >= cuotaMinima &&  cuotas <= cuotaMaxima) {
						limiteSeleccionado = limite;
						break;
					}
				} else { // solo buscamos las fechas de vigencia, los rangos tienen todos las mismas fechas
					limiteSeleccionado = limite;
					break;
				}
			}
		}
		return limiteSeleccionado;
	}
}
