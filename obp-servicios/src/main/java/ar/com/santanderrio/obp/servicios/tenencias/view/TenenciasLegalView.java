/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class TenenciasLegalView.
 */
public class TenenciasLegalView {

	/** The legales principal exclusivo cuentas. */
	private List<String> legalesPrincipalExclusivoCuentas;

	/** The legales principal solapado cuentas. */
	private List<String> legalesPrincipalSolapadoCuentas;
	
	/** The legales principal solapado inversiones. */
	private List<String> legalesPrincipalSolapadoInversiones;

	/** The legales principal solapado prestamos. */
	private List<String> legalesPrincipalSolapadoPrestamos;

	/** The legales principal solapado impuestos. */
	private List<String> legalesPrincipalSolapadoImpuestos;
	
	/** The legales detalle solapado inversiones. */
	private List<String> legalesDetalleSolapadoInversiones;

	/** The legales detalle solapado prestamos. */
	private List<String> legalesDetalleSolapadoPrestamos;

	/** The legales detalle solapado impuestos. */
	private List<String> legalesDetalleSolapadoImpuestos;
	
	/** The legales detalle solapado impuestos. */
	private List<String> legalesGeneralInversiones;
	

	/**
	 * Instantiates a new tenencias legal view.
	 */
	public TenenciasLegalView() {

	}
	
	/**
	 * Gets the legales principal general Inversiones.
	 *
	 * @return the legalesInversiones
	 */
	public List<String> getLegalesInversiones() {
		return legalesGeneralInversiones;
	}

	/**
	 * Sets the legales principal General Inversiones.
	 *
	 * @param legalesGeneralInversiones
	 *            the legalesGeneralInversiones to set
	 */
	public void setLegalesInversiones(List<String> legalesGeneralInversiones) {
		this.legalesGeneralInversiones = legalesGeneralInversiones;
	}

	/**
	 * Gets the legales principal exclusivo cuentas.
	 *
	 * @return the legalesPrincipalExclusivoCuentas
	 */
	public List<String> getLegalesPrincipalExclusivoCuentas() {
		return legalesPrincipalExclusivoCuentas;
	}

	/**
	 * Sets the legales principal exclusivo cuentas.
	 *
	 * @param legalesPrincipalExclusivoCuentas
	 *            the legalesPrincipalExclusivoCuentas to set
	 */
	public void setLegalesPrincipalExclusivoCuentas(List<String> legalesPrincipalExclusivoCuentas) {
		this.legalesPrincipalExclusivoCuentas = legalesPrincipalExclusivoCuentas;
	}

	/**
	 * Gets the legales principal solapado cuentas.
	 *
	 * @return the legalesPrincipalSolapadoCuentas
	 */
	public List<String> getLegalesPrincipalSolapadoCuentas() {
		return legalesPrincipalSolapadoCuentas;
	}

	/**
	 * Sets the legales principal solapado cuentas.
	 *
	 * @param legalesPrincipalSolapadoCuentas
	 *            the legalesPrincipalSolapadoCuentas to set
	 */
	public void setLegalesPrincipalSolapadoCuentas(List<String> legalesPrincipalSolapadoCuentas) {
		this.legalesPrincipalSolapadoCuentas = legalesPrincipalSolapadoCuentas;
	}

	/**
	 * Gets the legales principal solapado inversiones.
	 *
	 * @return the legalesPrincipalSolapadoInversiones
	 */
	public List<String> getLegalesPrincipalSolapadoInversiones() {
		return legalesPrincipalSolapadoInversiones;
	}

	/**
	 * Sets the legales principal solapado inversiones.
	 *
	 * @param legalesPrincipalSolapadoInversiones
	 *            the legalesPrincipalSolapadoInversiones to set
	 */
	public void setLegalesPrincipalSolapadoInversiones(List<String> legalesPrincipalSolapadoInversiones) {
		this.legalesPrincipalSolapadoInversiones = legalesPrincipalSolapadoInversiones;
	}

	/**
	 * Gets the legales principal solapado prestamos.
	 *
	 * @return the legalesPrincipalSolapadoPrestamos
	 */
	public List<String> getLegalesPrincipalSolapadoPrestamos() {
		return legalesPrincipalSolapadoPrestamos;
	}

	/**
	 * Sets the legales principal solapado prestamos.
	 *
	 * @param legalesPrincipalSolapadoPrestamos
	 *            the legalesPrincipalSolapadoPrestamos to set
	 */
	public void setLegalesPrincipalSolapadoPrestamos(List<String> legalesPrincipalSolapadoPrestamos) {
		this.legalesPrincipalSolapadoPrestamos = legalesPrincipalSolapadoPrestamos;
	}

	/**
	 * Gets the legales principal solapado impuestos.
	 *
	 * @return the legalesPrincipalSolapadoImpuestos
	 */
	public List<String> getLegalesPrincipalSolapadoImpuestos() {
		return legalesPrincipalSolapadoImpuestos;
	}

	/**
	 * Sets the legales principal solapado impuestos.
	 *
	 * @param legalesPrincipalSolapadoImpuestos
	 *            the legalesPrincipalSolapadoImpuestos to set
	 */
	public void setLegalesPrincipalSolapadoImpuestos(List<String> legalesPrincipalSolapadoImpuestos) {
		this.legalesPrincipalSolapadoImpuestos = legalesPrincipalSolapadoImpuestos;
	}

	/**
	 * Gets the legales detalle solapado inversiones.
	 *
	 * @return the legalesDetalleSolapadoInversiones
	 */
	public List<String> getLegalesDetalleSolapadoInversiones() {
		return legalesDetalleSolapadoInversiones;
	}

	/**
	 * Sets the legales detalle solapado inversiones.
	 *
	 * @param legalesDetalleSolapadoInversiones
	 *            the legalesDetalleSolapadoInversiones to set
	 */
	public void setLegalesDetalleSolapadoInversiones(List<String> legalesDetalleSolapadoInversiones) {
		this.legalesDetalleSolapadoInversiones = legalesDetalleSolapadoInversiones;
	}

	/**
	 * Gets the legales detalle solapado prestamos.
	 *
	 * @return the legalesDetalleSolapadoPrestamos
	 */
	public List<String> getLegalesDetalleSolapadoPrestamos() {
		return legalesDetalleSolapadoPrestamos;
	}

	/**
	 * Sets the legales detalle solapado prestamos.
	 *
	 * @param legalesDetalleSolapadoPrestamos
	 *            the legalesDetalleSolapadoPrestamos to set
	 */
	public void setLegalesDetalleSolapadoPrestamos(List<String> legalesDetalleSolapadoPrestamos) {
		this.legalesDetalleSolapadoPrestamos = legalesDetalleSolapadoPrestamos;
	}

	/**
	 * Gets the legales detalle solapado impuestos.
	 *
	 * @return the legalesDetalleSolapadoImpuestos
	 */
	public List<String> getLegalesDetalleSolapadoImpuestos() {
		return legalesDetalleSolapadoImpuestos;
	}

	/**
	 * Sets the legales detalle solapado impuestos.
	 *
	 * @param legalesDetalleSolapadoImpuestos
	 *            the legalesDetalleSolapadoImpuestos to set
	 */
	public void setLegalesDetalleSolapadoImpuestos(List<String> legalesDetalleSolapadoImpuestos) {
		this.legalesDetalleSolapadoImpuestos = legalesDetalleSolapadoImpuestos;
	}

	
	
}
