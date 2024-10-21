/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ConsultaDatosDomicilioInEntity.
 *
 * @author Silvina_Luque
 */
public class ConsultaDatosDomicilioInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The opcion. */
	private String opcion;

	/** The secuencia domicilio. */
	private String secuenciaDomicilio;

	/** The tipo domicilio. */
	private String tipoDomicilio = StringUtils.leftPad("", 3);

	/** The tipo via. */
	private String tipoVia = StringUtils.leftPad("", 2);

	/** The nombre calle. */
	private String nombreCalle = StringUtils.leftPad("", 50);

	/** The tipo construccion. */
	private String tipoConstruccion = StringUtils.leftPad("", 4);

	/** The tipo nucleo urbano. */
	private String tipoNucleoUrbano = StringUtils.leftPad("", 4);

	/** The observaciones 1. */
	private String observaciones1 = StringUtils.leftPad("", 100);

	/** The observaciones 2. */
	private String observaciones2 = StringUtils.leftPad("", 100);

	/** The sucursal casilla. */
	private String sucursalCasilla = StringUtils.leftPad("", 4);

	/** The apt. */
	private String apt = StringUtils.leftPad("", 10);

	/** The localidad ciudad. */
	private String localidadCiudad = StringUtils.leftPad("", 7);

	/** The desc localidad. */
	private String descLocalidad = StringUtils.leftPad("", 30);

	/** The comuna. */
	private String comuna = StringUtils.leftPad("", 5);

	/** The desc comuna. */
	private String descComuna = StringUtils.leftPad("", 30);

	/** The codigo postal. */
	private String codigoPostal = StringUtils.leftPad("", 8);

	/** The ruta cartero. */
	private String rutaCartero = StringUtils.leftPad("", 9);

	/** The codigo provincia. */
	private String codigoProvincia = StringUtils.leftPad("", 2);

	/** The codigo pais. */
	private String codigoPais = StringUtils.leftPad("", 3);

	/** The titu domicilio. */
	private String tituDomicilio = StringUtils.leftPad("", 2);

	/** The fecha verificacion. */
	private String fechaVerificacion = StringUtils.leftPad("", 10);

	/** The marca normalizacion. */
	private String marcaNormalizacion = StringUtils.leftPad("", 1);

	/** The marca dom erroneo. */
	private String marcaDomErroneo = StringUtils.leftPad("", 1);

	/** The marca corresp devuel. */
	private String marcaCorrespDevuel = StringUtils.leftPad("", 1);

	/** The marca devolucion. */
	private String marcaDevolucion = StringUtils.leftPad("", 2);

	/** The usuario alta. */
	private String usuarioAlta = StringUtils.leftPad("", 8);

	/** The fecha alta registro. */
	private String fechaAltaRegistro = StringUtils.leftPad("", 10);

	/** The usuario ultima mod. */
	private String usuarioUltimaMod = StringUtils.leftPad("", 8);

	/** The nro terminal ult modif. */
	private String nroTerminalUltModif = StringUtils.leftPad("", 4);

	/** The sucursal ult mod reg. */
	private String sucursalUltModReg = StringUtils.leftPad("", 4);

	/** The timestamp ult mod reg. */
	private String timestampUltModReg = StringUtils.leftPad("", 26);

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
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuencia domicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio
	 *            the new secuencia domicilio
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

	/**
	 * Gets the opcion.
	 *
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * Sets the opcion.
	 *
	 * @param opcion
	 *            the new opcion
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
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
	 * Gets the nombre calle.
	 *
	 * @return the nombre calle
	 */
	public String getNombreCalle() {
		return nombreCalle;
	}

	/**
	 * Sets the nombre calle.
	 *
	 * @param nombreCalle
	 *            the new nombre calle
	 */
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
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
	 * Gets the localidad ciudad.
	 *
	 * @return the localidad ciudad
	 */
	public String getLocalidadCiudad() {
		return localidadCiudad;
	}

	/**
	 * Sets the localidad ciudad.
	 *
	 * @param localidadCiudad
	 *            the new localidad ciudad
	 */
	public void setLocalidadCiudad(String localidadCiudad) {
		this.localidadCiudad = localidadCiudad;
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

	/**
	 * Gets the marca normalizacion.
	 *
	 * @return the marca normalizacion
	 */
	public String getMarcaNormalizacion() {
		return marcaNormalizacion;
	}

	/**
	 * Sets the marca normalizacion.
	 *
	 * @param marcaNormalizacion
	 *            the new marca normalizacion
	 */
	public void setMarcaNormalizacion(String marcaNormalizacion) {
		this.marcaNormalizacion = marcaNormalizacion;
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
	 * Gets the marca devolucion.
	 *
	 * @return the marca devolucion
	 */
	public String getMarcaDevolucion() {
		return marcaDevolucion;
	}

	/**
	 * Sets the marca devolucion.
	 *
	 * @param marcaDevolucion
	 *            the new marca devolucion
	 */
	public void setMarcaDevolucion(String marcaDevolucion) {
		this.marcaDevolucion = marcaDevolucion;
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
	 * Gets the nro terminal ult modif.
	 *
	 * @return the nro terminal ult modif
	 */
	public String getNroTerminalUltModif() {
		return nroTerminalUltModif;
	}

	/**
	 * Sets the nro terminal ult modif.
	 *
	 * @param nroTerminalUltModif
	 *            the new nro terminal ult modif
	 */
	public void setNroTerminalUltModif(String nroTerminalUltModif) {
		this.nroTerminalUltModif = nroTerminalUltModif;
	}

	/**
	 * Gets the sucursal ult mod reg.
	 *
	 * @return the sucursal ult mod reg
	 */
	public String getSucursalUltModReg() {
		return sucursalUltModReg;
	}

	/**
	 * Sets the sucursal ult mod reg.
	 *
	 * @param sucursalUltModReg
	 *            the new sucursal ult mod reg
	 */
	public void setSucursalUltModReg(String sucursalUltModReg) {
		this.sucursalUltModReg = sucursalUltModReg;
	}

	/**
	 * Gets the timestamp ult mod reg.
	 *
	 * @return the timestamp ult mod reg
	 */
	public String getTimestampUltModReg() {
		return timestampUltModReg;
	}

	/**
	 * Sets the timestamp ult mod reg.
	 *
	 * @param timestampUltModReg
	 *            the new timestamp ult mod reg
	 */
	public void setTimestampUltModReg(String timestampUltModReg) {
		this.timestampUltModReg = timestampUltModReg;
	}

}
