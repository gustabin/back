/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * DatosDomicilioEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class DatosDomicilioEntity {

	/** The nup. */
	@Field
	private String nup;

	/** The secuencia. */
	@Field
	private String secuencia;

	/** The tipo domicilio. */
	@Field
	private String tipoDomicilio;

	/** The tipo via. */
	@Field
	private String tipoVia;

	/** The calle. */
	@Field
	private String calle;

	/** The tipo construccion. */
	@Field
	private String tipoConstruccion;

	/** The tipo nucleo urbano. */
	@Field
	private String tipoNucleoUrbano;

	/** The observaciones 1. */
	@Field
	private String observaciones1;

	/** The observaciones 2. */
	@Field
	private String observaciones2;

	/** The sucursal casilla. */
	@Field
	private String sucursalCasilla;

	/** The apt. */
	@Field
	private String apt;

	/** The localidad. */
	@Field
	private String localidad;

	/** The desc localidad. */
	@Field
	private String descLocalidad;

	/** The comuna. */
	@Field
	private String comuna;

	/** The desc comuna. */
	@Field
	private String descComuna;

	/** The codigo postal. */
	@Field
	private String codigoPostal;

	/** The ruta cartero. */
	@Field
	private String rutaCartero;

	/** The codigo provincia. */
	@Field
	private String codigoProvincia;

	/** The codigo pais. */
	@Field
	private String codigoPais;

	/** The titu domicilio. */
	@Field
	private String tituDomicilio;

	/** The fecha verificacion. */
	@Field
	private String fechaVerificacion;

	/** The marca normmalizacion. */
	@Field
	private String marcaNormmalizacion;

	/** The marca dom erroneo. */
	@Field
	private String marcaDomErroneo;

	/** The marca corresp devuel. */
	@Field
	private String marcaCorrespDevuel;

	/** The motivo devolucion. */
	@Field
	private String motivoDevolucion;

	/** The usuario alta. */
	@Field
	private String usuarioAlta;

	/** The fecha alta registro. */
	@Field
	private String fechaAltaRegistro;

	/** The usuario ultima mod. */
	@Field
	private String usuarioUltimaMod;

	/** The nro terminal ult mod. */
	@Field
	private String nroTerminalUltMod;

	/** The sucursal ult mod. */
	@Field
	private String sucursalUltMod;

	/** The timestamp ult mod. */
	@Field
	private String timestampUltMod;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the tipo domicilio.
	 *
	 * @return the tipo domicilio
	 */
	public String getTipoDomicilio() {
		return tipoDomicilio;
	}

	/**
	 * Sets the tipo domicilio.
	 *
	 * @param tipoDomicilio
	 *            the new tipo domicilio
	 */
	public void setTipoDomicilio(String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

	/**
	 * Gets the tipo via.
	 *
	 * @return the tipo via
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * Sets the tipo via.
	 *
	 * @param tipoVia
	 *            the new tipo via
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * Gets the calle.
	 *
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Sets the calle.
	 *
	 * @param calle
	 *            the new calle
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Gets the tipo construccion.
	 *
	 * @return the tipo construccion
	 */
	public String getTipoConstruccion() {
		return tipoConstruccion;
	}

	/**
	 * Sets the tipo construccion.
	 *
	 * @param tipoConstruccion
	 *            the new tipo construccion
	 */
	public void setTipoConstruccion(String tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}

	/**
	 * Gets the tipo nucleo urbano.
	 *
	 * @return the tipo nucleo urbano
	 */
	public String getTipoNucleoUrbano() {
		return tipoNucleoUrbano;
	}

	/**
	 * Sets the tipo nucleo urbano.
	 *
	 * @param tipoNucleoUrbano
	 *            the new tipo nucleo urbano
	 */
	public void setTipoNucleoUrbano(String tipoNucleoUrbano) {
		this.tipoNucleoUrbano = tipoNucleoUrbano;
	}

	/**
	 * Gets the observaciones 1.
	 *
	 * @return the observaciones 1
	 */
	public String getObservaciones1() {
		return observaciones1;
	}

	/**
	 * Sets the observaciones 1.
	 *
	 * @param observaciones1
	 *            the new observaciones 1
	 */
	public void setObservaciones1(String observaciones1) {
		this.observaciones1 = observaciones1;
	}

	/**
	 * Gets the observaciones 2.
	 *
	 * @return the observaciones 2
	 */
	public String getObservaciones2() {
		return observaciones2;
	}

	/**
	 * Sets the observaciones 2.
	 *
	 * @param observaciones2
	 *            the new observaciones 2
	 */
	public void setObservaciones2(String observaciones2) {
		this.observaciones2 = observaciones2;
	}

	/**
	 * Gets the sucursal casilla.
	 *
	 * @return the sucursal casilla
	 */
	public String getSucursalCasilla() {
		return sucursalCasilla;
	}

	/**
	 * Sets the sucursal casilla.
	 *
	 * @param sucursalCasilla
	 *            the new sucursal casilla
	 */
	public void setSucursalCasilla(String sucursalCasilla) {
		this.sucursalCasilla = sucursalCasilla;
	}

	/**
	 * Gets the apt.
	 *
	 * @return the apt
	 */
	public String getApt() {
		return apt;
	}

	/**
	 * Sets the apt.
	 *
	 * @param apt
	 *            the new apt
	 */
	public void setApt(String apt) {
		this.apt = apt;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the new localidad
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the desc localidad.
	 *
	 * @return the desc localidad
	 */
	public String getDescLocalidad() {
		return descLocalidad;
	}

	/**
	 * Sets the desc localidad.
	 *
	 * @param descLocalidad
	 *            the new desc localidad
	 */
	public void setDescLocalidad(String descLocalidad) {
		this.descLocalidad = descLocalidad;
	}

	/**
	 * Gets the comuna.
	 *
	 * @return the comuna
	 */
	public String getComuna() {
		return comuna;
	}

	/**
	 * Sets the comuna.
	 *
	 * @param comuna
	 *            the new comuna
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	/**
	 * Gets the desc comuna.
	 *
	 * @return the desc comuna
	 */
	public String getDescComuna() {
		return descComuna;
	}

	/**
	 * Sets the desc comuna.
	 *
	 * @param descComuna
	 *            the new desc comuna
	 */
	public void setDescComuna(String descComuna) {
		this.descComuna = descComuna;
	}

	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigo postal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal
	 *            the new codigo postal
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Gets the ruta cartero.
	 *
	 * @return the ruta cartero
	 */
	public String getRutaCartero() {
		return rutaCartero;
	}

	/**
	 * Sets the ruta cartero.
	 *
	 * @param rutaCartero
	 *            the new ruta cartero
	 */
	public void setRutaCartero(String rutaCartero) {
		this.rutaCartero = rutaCartero;
	}

	/**
	 * Gets the codigo provincia.
	 *
	 * @return the codigo provincia
	 */
	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	/**
	 * Sets the codigo provincia.
	 *
	 * @param codigoProvincia
	 *            the new codigo provincia
	 */
	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigo pais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais
	 *            the new codigo pais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the titu domicilio.
	 *
	 * @return the titu domicilio
	 */
	public String getTituDomicilio() {
		return tituDomicilio;
	}

	/**
	 * Sets the titu domicilio.
	 *
	 * @param tituDomicilio
	 *            the new titu domicilio
	 */
	public void setTituDomicilio(String tituDomicilio) {
		this.tituDomicilio = tituDomicilio;
	}

	/**
	 * Gets the marca normmalizacion.
	 *
	 * @return the marca normmalizacion
	 */
	public String getMarcaNormmalizacion() {
		return marcaNormmalizacion;
	}

	/**
	 * Sets the marca normmalizacion.
	 *
	 * @param marcaNormmalizacion
	 *            the new marca normmalizacion
	 */
	public void setMarcaNormmalizacion(String marcaNormmalizacion) {
		this.marcaNormmalizacion = marcaNormmalizacion;
	}

	/**
	 * Gets the marca dom erroneo.
	 *
	 * @return the marca dom erroneo
	 */
	public String getMarcaDomErroneo() {
		return marcaDomErroneo;
	}

	/**
	 * Sets the marca dom erroneo.
	 *
	 * @param marcaDomErroneo
	 *            the new marca dom erroneo
	 */
	public void setMarcaDomErroneo(String marcaDomErroneo) {
		this.marcaDomErroneo = marcaDomErroneo;
	}

	/**
	 * Gets the marca corresp devuel.
	 *
	 * @return the marca corresp devuel
	 */
	public String getMarcaCorrespDevuel() {
		return marcaCorrespDevuel;
	}

	/**
	 * Sets the marca corresp devuel.
	 *
	 * @param marcaCorrespDevuel
	 *            the new marca corresp devuel
	 */
	public void setMarcaCorrespDevuel(String marcaCorrespDevuel) {
		this.marcaCorrespDevuel = marcaCorrespDevuel;
	}

	/**
	 * Gets the motivo devolucion.
	 *
	 * @return the motivo devolucion
	 */
	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}

	/**
	 * Sets the motivo devolucion.
	 *
	 * @param motivoDevolucion
	 *            the new motivo devolucion
	 */
	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}

	/**
	 * Gets the usuario alta.
	 *
	 * @return the usuario alta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * Sets the usuario alta.
	 *
	 * @param usuarioAlta
	 *            the new usuario alta
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * Gets the fecha alta registro.
	 *
	 * @return the fecha alta registro
	 */
	public String getFechaAltaRegistro() {
		return fechaAltaRegistro;
	}

	/**
	 * Sets the fecha alta registro.
	 *
	 * @param fechaAltaRegistro
	 *            the new fecha alta registro
	 */
	public void setFechaAltaRegistro(String fechaAltaRegistro) {
		this.fechaAltaRegistro = fechaAltaRegistro;
	}

	/**
	 * Gets the usuario ultima mod.
	 *
	 * @return the usuario ultima mod
	 */
	public String getUsuarioUltimaMod() {
		return usuarioUltimaMod;
	}

	/**
	 * Sets the usuario ultima mod.
	 *
	 * @param usuarioUltimaMod
	 *            the new usuario ultima mod
	 */
	public void setUsuarioUltimaMod(String usuarioUltimaMod) {
		this.usuarioUltimaMod = usuarioUltimaMod;
	}

	/**
	 * Gets the nro terminal ult mod.
	 *
	 * @return the nro terminal ult mod
	 */
	public String getNroTerminalUltMod() {
		return nroTerminalUltMod;
	}

	/**
	 * Sets the nro terminal ult mod.
	 *
	 * @param nroTerminalUltMod
	 *            the new nro terminal ult mod
	 */
	public void setNroTerminalUltMod(String nroTerminalUltMod) {
		this.nroTerminalUltMod = nroTerminalUltMod;
	}

	/**
	 * Gets the sucursal ult mod.
	 *
	 * @return the sucursal ult mod
	 */
	public String getSucursalUltMod() {
		return sucursalUltMod;
	}

	/**
	 * Sets the sucursal ult mod.
	 *
	 * @param sucursalUltMod
	 *            the new sucursal ult mod
	 */
	public void setSucursalUltMod(String sucursalUltMod) {
		this.sucursalUltMod = sucursalUltMod;
	}

	/**
	 * Gets the timestamp ult mod.
	 *
	 * @return the timestamp ult mod
	 */
	public String getTimestampUltMod() {
		return timestampUltMod;
	}

	/**
	 * Sets the timestamp ult mod.
	 *
	 * @param timestampUltMod
	 *            the new timestamp ult mod
	 */
	public void setTimestampUltMod(String timestampUltMod) {
		this.timestampUltMod = timestampUltMod;
	}

	/**
	 * Gets the fecha verificacion.
	 *
	 * @return the fecha verificacion
	 */
	public String getFechaVerificacion() {
		return fechaVerificacion;
	}

	/**
	 * Sets the fecha verificacion.
	 *
	 * @param fechaVerificacion
	 *            the new fecha verificacion
	 */
	public void setFechaVerificacion(String fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}

}
