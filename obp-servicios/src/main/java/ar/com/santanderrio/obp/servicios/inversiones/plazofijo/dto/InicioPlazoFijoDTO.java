/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;

/**
 * The Class InicioPlazoFijoDTO.
 */
public class InicioPlazoFijoDTO {
	/** Lista de cuentas de banca privada. */
	private List<CuentaTituloDTO> cuentasBancaPrivada;

	/**
	 * Gets the cuentas banca privada.
	 *
	 * @return the cuentasBancaPrivada
	 */
	public List<CuentaTituloDTO> getCuentasBancaPrivada() {
		return cuentasBancaPrivada;
	}

	/**
	 * Sets the cuentas banca privada.
	 *
	 * @param cuentasBancaPrivada
	 *            the cuentasBancaPrivada to set
	 */
	public void setCuentasBancaPrivada(List<CuentaTituloDTO> cuentasBancaPrivada) {
		this.cuentasBancaPrivada = cuentasBancaPrivada;
	}
}
