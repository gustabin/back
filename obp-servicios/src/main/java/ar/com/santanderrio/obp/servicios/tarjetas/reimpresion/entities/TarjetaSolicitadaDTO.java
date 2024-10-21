/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

/**
 * The Class TarjetaSolicitadaDTO.
 */
public class TarjetaSolicitadaDTO {

	/** Input *. */
	private String nroTarjetaConFormato;

	/** The nro. */
	private String nro;

	/** The alias. */
	private String alias;

	/** The titular. */
	private String titular;

	/** The tipoDescripcion. */
	private String tipoCuentaDescripcion;

	/** output *. */

	private String solicitudNro;

	/** The motivo reimpresion. */
	private String motivoReimpresion;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The isOk. */
	private Boolean isOk;

	/** The solicitud en curso. */
	private boolean solicitudEnCurso = Boolean.FALSE;

	/** The mensaje. */
	private String mensaje;
	
	private boolean esContactless = Boolean.FALSE;
	
	

	/**
	 * Gets the nro tarjeta con formato.
	 *
	 * @return the nro tarjeta con formato
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nro tarjeta con formato.
	 *
	 * @param nroTarjetaConFormato
	 *            the new nro tarjeta con formato
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Gets the nro.
	 *
	 * @return the nro
	 */
	public String getNro() {
		return nro;
	}

	/**
	 * Sets the nro.
	 *
	 * @param nro
	 *            the new nro
	 */
	public void setNro(String nro) {
		this.nro = nro;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the tipo cuenta descripcion.
	 *
	 * @return the tipo cuenta descripcion
	 */
	public String getTipoCuentaDescripcion() {
		return tipoCuentaDescripcion;
	}

	/**
	 * Sets the tipo cuenta descripcion.
	 *
	 * @param tipoCuentaDescripcion
	 *            the new tipo cuenta descripcion
	 */
	public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
		this.tipoCuentaDescripcion = tipoCuentaDescripcion;
	}

	/**
	 * Gets the solicitud nro.
	 *
	 * @return the solicitud nro
	 */
	public String getSolicitudNro() {
		return solicitudNro;
	}

	/**
	 * Sets the solicitud nro.
	 *
	 * @param solicitudNro
	 *            the new solicitud nro
	 */
	public void setSolicitudNro(String solicitudNro) {
		this.solicitudNro = solicitudNro;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the motivo reimpresion.
	 *
	 * @return the motivo reimpresion
	 */
	public String getMotivoReimpresion() {
		return motivoReimpresion;
	}

	/**
	 * Sets the motivo reimpresion.
	 *
	 * @param motivoReimpresion
	 *            the new motivo reimpresion
	 */
	public void setMotivoReimpresion(String motivoReimpresion) {
		this.motivoReimpresion = motivoReimpresion;
	}

	/**
	 * Gets the checks if is ok.
	 *
	 * @return the checks if is ok
	 */
	public Boolean getIsOk() {
		return isOk;
	}

	/**
	 * Sets the checks if is ok.
	 *
	 * @param isOk
	 *            the new checks if is ok
	 */
	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}


	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}


	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isSolicitudEnCurso() {
		return solicitudEnCurso;
	}

	public void setSolicitudEnCurso(boolean solicitudEnCurso) {
		this.solicitudEnCurso = solicitudEnCurso;
	}

	public boolean isEsContactless() {
		return esContactless;
	}

	public void setEsContactless(boolean esContactless) {
		this.esContactless = esContactless;
	}



	
	
	
}
