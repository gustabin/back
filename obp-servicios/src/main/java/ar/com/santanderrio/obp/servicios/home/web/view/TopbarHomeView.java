/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import java.util.List;

/**
 * The Class TopbarHomeView.
 */
public class TopbarHomeView {
	
	/** The is nova. */
	private Boolean isNova;

	/** The is duo. */
	private Boolean isDuo;
	
	/** The is pyme. */
	private Boolean isPyme;

	/** The is select. */
	private Boolean isSelect;

	/** The is universidad. */
	private Boolean isUniversidad;

	/** The controller. */
	private ControllerView controller;

	/** The imagen perfil. */
	private String imagenPerfil;
	
	/** The operador ejecutivo. */
	private OperadorEjecutivoView operadorEjecutivo;
	
	/** The sin productos. */
	private Boolean sinProductos = Boolean.FALSE;

	/** The mfs. */
	private List<MicroFEView> mfs;

	/**
	 * Indicador iU
	 */
	private Boolean isIU;

	/** The unicaCuentaMf value */
	private Boolean unicaCuentaMf;

	/**
	 * Gets the checks if is unicaCuentaMf.
	 *
	 * @return the checks if is unicaCuentaMf
	 */
	public Boolean getUnicaCuentaMf() {
		return unicaCuentaMf;
	}
	/**
	 * Sets the checks if is unicaCuentaMf.
	 *
	 * @param unicaCuentaMf the new checks if is unicaCuentaMf
	 */
	 public void setUnicaCuentaMf(Boolean unicaCuentaMf) {
		this.unicaCuentaMf = unicaCuentaMf;
	}

	/**
	 * Gets the checks if is duo.
	 *
	 * @return the checks if is duo
	 */
	public Boolean getIsDuo() {
		return isDuo;
	}
	
	public Boolean getIsNova() {
		return isNova;
	}

	public void setIsNova(Boolean isNova) {
		this.isNova = isNova;
	}

	/**
	 * Sets the checks if is duo.
	 *
	 * @param isDuo the new checks if is duo
	 */
	public void setIsDuo(Boolean isDuo) {
		this.isDuo = isDuo;
	}

	/**
	 * Gets the checks if is pyme.
	 *
	 * @return the checks if is pyme
	 */
	public Boolean getIsPyme() {
		return isPyme;
	}

	/**
	 * Sets the checks if is pyme.
	 *
	 * @param isPyme the new checks if is pyme
	 */
	public void setIsPyme(Boolean isPyme) {
		this.isPyme = isPyme;
	}

	/**
	 * Gets the checks if is select.
	 *
	 * @return the checks if is select
	 */
	public Boolean getIsSelect() {
		return isSelect;
	}

	/**
	 * Sets the checks if is select.
	 *
	 * @param isSelect
	 *            the new checks if is select
	 */
	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 * Gets the checks if is universidad.
	 *
	 * @return the checks if is universidad
	 */
	public Boolean getIsUniversidad() {
		return isUniversidad;
	}

	/**
	 * Sets the checks if is universidad.
	 *
	 * @param isUniversidad
	 *            the new checks if is universidad
	 */
	public void setIsUniversidad(Boolean isUniversidad) {
		this.isUniversidad = isUniversidad;
	}

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public ControllerView getController() {
		return controller;
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *            the new controller
	 */
	public void setController(ControllerView controller) {
		this.controller = controller;
	}

	/**
	 * Gets the imagen perfil.
	 *
	 * @return the imagen perfil
	 */
	public String getImagenPerfil() {
		return imagenPerfil;
	}

	/**
	 * Sets the imagen perfil.
	 *
	 * @param imagenPerfil
	 *            the new imagen perfil
	 */
	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	/**
	 * Gets the sin productos.
	 *
	 * @return the sin productos
	 */
	public Boolean getSinProductos() {
		return sinProductos;
	}

	/**
	 * Sets the sin productos.
	 *
	 * @param sinProductos the new sin productos
	 */
	public void setSinProductos(Boolean sinProductos) {
		this.sinProductos = sinProductos;
	}

	/**
	 * Getter isIU
	 * @return
	 */
	public Boolean getIsIU() {
		return isIU;
	}

	/**
	 * Setter isIU
	 * @param isIU
	 */
	public void setIsIU(Boolean isIU) {
		this.isIU = isIU;
	}

	/**
	 * Gets the operador ejecutivo.
	 *
	 * @return the operador ejecutivo
	 */
	public OperadorEjecutivoView getOperadorEjecutivo() {
		return operadorEjecutivo;
	}

	/**
	 * Sets the operador ejecutivo.
	 *
	 * @param operadorEjecutivo the new operador ejecutivo
	 */
	public void setOperadorEjecutivo(OperadorEjecutivoView operadorEjecutivo) {
		this.operadorEjecutivo = operadorEjecutivo;
	}

	/**
	 * Gets the mfs.
	 *
	 * @return the mfs
	 */
	public List<MicroFEView> getMfs() {
		return mfs;
	}

	/**
	 * Sets the mfs.
	 *
	 * @param mfs the new mfs
	 */
	public void setMfs(List<MicroFEView> mfs) {
		this.mfs = mfs;
	}

}
