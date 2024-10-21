/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.List;

/**
 * The Class InicioFondoDTO.
 */
/**
 * @author A308071
 *
 */
public class InicioFondoDTO {

	/**
	 * Mensaje de error si no se encuentasn cuentas para el usuario, recuperado
	 * de la base.
	 */
	private String mensajeErrorSinCuentas = "";

	/** Lista de cuentas de banca privada. */
	private List<CuentaTituloDTO> cuentasBancaPrivada = null;

	/** Lista de cuentas de banca personal. */
	private List<CuentaTituloDTO> cuentasBancaPersonal = null;

	/** The descripcion perfil inversor. */
	private String descripcionPerfilInversor;

	/** The id perfil inveror. */
	private String idPerfilInveror;

	/** Indicador S/N si tiene cuentas monetarias o no. */
	private String tieneCuentasMonetarias;

	/** The error cuentas bloqueadas. */
	private boolean errorCuentasBloqueadas = false;

	/** The error cuentas B priv. */
	private boolean errorCuentasBPriv = false;
	
	private boolean nuevaApertura;

	/**
	 * Gets the mensaje error sin cuentas.
	 *
	 * @return the mensaje error sin cuentas
	 */
	public String getMensajeErrorSinCuentas() {
		return mensajeErrorSinCuentas;
	}

	/**
	 * Sets the mensaje error sin cuentas.
	 *
	 * @param mensajeErrorSinCuentas
	 *            the new mensaje error sin cuentas
	 */
	public void setMensajeErrorSinCuentas(String mensajeErrorSinCuentas) {
		this.mensajeErrorSinCuentas = mensajeErrorSinCuentas;
	}

	/**
	 * Gets the cuentas banca privada.
	 *
	 * @return the cuentas banca privada
	 */
	public List<CuentaTituloDTO> getCuentasBancaPrivada() {
		return cuentasBancaPrivada;
	}

	/**
	 * Sets the cuentas banca privada.
	 *
	 * @param cuentasBancaPrivada
	 *            the new cuentas banca privada
	 */
	public void setCuentasBancaPrivada(List<CuentaTituloDTO> cuentasBancaPrivada) {
		this.cuentasBancaPrivada = cuentasBancaPrivada;
	}

	/**
	 * Gets the cuentas banca personal.
	 *
	 * @return the cuentas banca personal
	 */
	public List<CuentaTituloDTO> getCuentasBancaPersonal() {
		return cuentasBancaPersonal;
	}

	/**
	 * Sets the cuentas banca personal.
	 *
	 * @param cuentasBancaPersonal
	 *            the new cuentas banca personal
	 */
	public void setCuentasBancaPersonal(List<CuentaTituloDTO> cuentasBancaPersonal) {
		this.cuentasBancaPersonal = cuentasBancaPersonal;
	}

	/**
	 * Gets the descripcion perfil inversor.
	 *
	 * @return the descripcion perfil inversor
	 */
	public String getDescripcionPerfilInversor() {
		return descripcionPerfilInversor;
	}

	/**
	 * Sets the descripcion perfil inversor.
	 *
	 * @param descripcionPerfilInversor
	 *            the new descripcion perfil inversor
	 */
	public void setDescripcionPerfilInversor(String descripcionPerfilInversor) {
		this.descripcionPerfilInversor = descripcionPerfilInversor;
	}

	/**
	 * Gets the id perfil inveror.
	 *
	 * @return the id perfil inveror
	 */
	public String getIdPerfilInveror() {
		return idPerfilInveror;
	}

	/**
	 * Sets the id perfil inveror.
	 *
	 * @param idPerfilInveror
	 *            the new id perfil inveror
	 */
	public void setIdPerfilInveror(String idPerfilInveror) {
		this.idPerfilInveror = idPerfilInveror;
	}

	/**
	 * Gets the tiene cuentas monetarias.
	 *
	 * @return the tiene cuentas monetarias
	 */
	public String getTieneCuentasMonetarias() {
		return tieneCuentasMonetarias;
	}

	/**
	 * Sets the tiene cuentas monetarias.
	 *
	 * @param tieneCuentasMonetarias
	 *            the new tiene cuentas monetarias
	 */
	public void setTieneCuentasMonetarias(String tieneCuentasMonetarias) {
		this.tieneCuentasMonetarias = tieneCuentasMonetarias;
	}

	/**
	 * Checks if is error cuentas bloqueadas.
	 *
	 * @return true, if is error cuentas bloqueadas
	 */
	public boolean isErrorCuentasBloqueadas() {
		return errorCuentasBloqueadas;
	}

	/**
	 * Sets the error cuentas bloqueadas.
	 *
	 * @param errorCuentasBloqueadas
	 *            the new error cuentas bloqueadas
	 */
	public void setErrorCuentasBloqueadas(boolean errorCuentasBloqueadas) {
		this.errorCuentasBloqueadas = errorCuentasBloqueadas;
	}

	/**
	 * Checks if is error cuentas B priv.
	 *
	 * @return true, if is error cuentas B priv
	 */
	public boolean isErrorCuentasBPriv() {
		return errorCuentasBPriv;
	}

	/**
	 * Sets the error cuentas B priv.
	 *
	 * @param errorCuentasBPriv
	 *            the new error cuentas B priv
	 */
	public void setErrorCuentasBPriv(boolean errorCuentasBPriv) {
		this.errorCuentasBPriv = errorCuentasBPriv;
	}

	public boolean isNuevaApertura() {
		return nuevaApertura;
	}

	public void setNuevaApertura(boolean nuevaApertura) {
		this.nuevaApertura = nuevaApertura;
	}
	
	
}
