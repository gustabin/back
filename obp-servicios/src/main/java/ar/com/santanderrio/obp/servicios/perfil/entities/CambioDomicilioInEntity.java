/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * CambioDomicilioInEntity.
 *
 * @author Silvina_Luque
 */

@Record
public class CambioDomicilioInEntity {

	/** The cliente. */
	@Field
	private Cliente cliente;

	/** The accion. */
	@Field
	private String accion;

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

	/** The descripcion provincia. */
	@Field
	private String descripcionProvincia;

	/** The codigo pais. */
	@Field
	private String codigoPais;

	/** The titu domicilio. */
	@Field
	private String tituDomicilio;

	/** The fecha verificacion. */
	@Field
	private String fechaVerificacion;

	/** The marca normalizacion. */
	@Field
	private String marcaNormalizacion;

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

	/** The nombre programa. */
	@Field
	private String nombrePrograma;

	/** The secuencia telefono. */
	@Field
	private String secuenciaTelefono;

	/** The tipo telefono. */
	@Field
	private String tipoTelefono;

	/** The clase telefono. */
	@Field
	private String claseTelefono;

	/** The prefijo. */
	@Field
	private String prefijo;

	/** The caracteristica. */
	@Field
	private String caracteristica;

	/** The nro telefono. */
	@Field
	private String nroTelefono;

	/** The nro interno. */
	@Field
	private String nroInterno;

	/** The observaciones semaforo. */
	@Field
	private String observacionesSemaforo;

	/** The usuario alta tel. */
	@Field
	private String usuarioAltaTel;

	/** The fecha alta registro tel. */
	@Field
	private String fechaAltaRegistroTel;

	/** The usuario ultima mod tel. */
	@Field
	private String usuarioUltimaModTel;

	/** The nro terminal ult mod tel. */
	@Field
	private String nroTerminalUltModTel;

	/** The sucursal ult mod tel. */
	@Field
	private String sucursalUltModTel;

	/** The timestamp ult mod tel. */
	@Field
	private String timestampUltModTel;

	/** The codigo provincia tel. */
	@Field
	private String codigoProvinciaTel;

	/** The calle anterior. */
	@Field
	private String calleAnterior;

	/** The observaciones 1 anterior. */
	@Field
	private String observaciones1Anterior;

	/** The apt anterior. */
	@Field
	private String aptAnterior;

	/** The desc localidad anterior. */
	@Field
	private String descLocalidadAnterior;

	/** The desc comuna anterior. */
	@Field
	private String descComunaAnterior;

	/** The codigo postal anterior. */
	@Field
	private String codigoPostalAnterior;

	/** The codigo provincia anterior. */
	@Field
	private String codigoProvinciaAnterior;

	/** The descripcion provincia anterior. */
	@Field
	private String descripcionProvinciaAnterior;

	/** The prefijo anterior. */
	@Field
	private String prefijoAnterior;

	/** The caracteristica anterior. */
	@Field
	private String caracteristicaAnterior;

	/** The nro telefono anterior. */
	@Field
	private String nroTelefonoAnterior;

	/** The cantidad productos dom. */
	@Field
	private String cantidadProductosDom;

	/** The listaproductos. */
	private List<ProductoEntity> listaproductos;

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
	 * Gets the descripcion provincia.
	 *
	 * @return the descripcion provincia
	 */
	public String getDescripcionProvincia() {
		return descripcionProvincia;
	}

	/**
	 * Sets the descripcion provincia.
	 *
	 * @param descripcionProvincia
	 *            the new descripcion provincia
	 */
	public void setDescripcionProvincia(String descripcionProvincia) {
		this.descripcionProvincia = descripcionProvincia;
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

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Gets the nombre programa.
	 *
	 * @return the nombre programa
	 */
	public String getNombrePrograma() {
		return nombrePrograma;
	}

	/**
	 * Sets the nombre programa.
	 *
	 * @param nombrePrograma
	 *            the new nombre programa
	 */
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	/**
	 * Gets the secuencia telefono.
	 *
	 * @return the secuencia telefono
	 */
	public String getSecuenciaTelefono() {
		return secuenciaTelefono;
	}

	/**
	 * Sets the secuencia telefono.
	 *
	 * @param secuenciaTelefono
	 *            the new secuencia telefono
	 */
	public void setSecuenciaTelefono(String secuenciaTelefono) {
		this.secuenciaTelefono = secuenciaTelefono;
	}

	/**
	 * Gets the tipo telefono.
	 *
	 * @return the tipo telefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}

	/**
	 * Sets the tipo telefono.
	 *
	 * @param tipoTelefono
	 *            the new tipo telefono
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	/**
	 * Gets the clase telefono.
	 *
	 * @return the clase telefono
	 */
	public String getClaseTelefono() {
		return claseTelefono;
	}

	/**
	 * Sets the clase telefono.
	 *
	 * @param claseTelefono
	 *            the new clase telefono
	 */
	public void setClaseTelefono(String claseTelefono) {
		this.claseTelefono = claseTelefono;
	}

	/**
	 * Gets the prefijo.
	 *
	 * @return the prefijo
	 */
	public String getPrefijo() {
		return prefijo;
	}

	/**
	 * Sets the prefijo.
	 *
	 * @param prefijo
	 *            the new prefijo
	 */
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	/**
	 * Gets the caracteristica.
	 *
	 * @return the caracteristica
	 */
	public String getCaracteristica() {
		return caracteristica;
	}

	/**
	 * Sets the caracteristica.
	 *
	 * @param caracteristica
	 *            the new caracteristica
	 */
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	/**
	 * Gets the nro telefono.
	 *
	 * @return the nro telefono
	 */
	public String getNroTelefono() {
		return nroTelefono;
	}

	/**
	 * Sets the nro telefono.
	 *
	 * @param nroTelefono
	 *            the new nro telefono
	 */
	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	/**
	 * Gets the nro interno.
	 *
	 * @return the nro interno
	 */
	public String getNroInterno() {
		return nroInterno;
	}

	/**
	 * Sets the nro interno.
	 *
	 * @param nroInterno
	 *            the new nro interno
	 */
	public void setNroInterno(String nroInterno) {
		this.nroInterno = nroInterno;
	}

	/**
	 * Gets the observaciones semaforo.
	 *
	 * @return the observaciones semaforo
	 */
	public String getObservacionesSemaforo() {
		return observacionesSemaforo;
	}

	/**
	 * Sets the observaciones semaforo.
	 *
	 * @param observacionesSemaforo
	 *            the new observaciones semaforo
	 */
	public void setObservacionesSemaforo(String observacionesSemaforo) {
		this.observacionesSemaforo = observacionesSemaforo;
	}

	/**
	 * Gets the usuario alta tel.
	 *
	 * @return the usuario alta tel
	 */
	public String getUsuarioAltaTel() {
		return usuarioAltaTel;
	}

	/**
	 * Sets the usuario alta tel.
	 *
	 * @param usuarioAltaTel
	 *            the new usuario alta tel
	 */
	public void setUsuarioAltaTel(String usuarioAltaTel) {
		this.usuarioAltaTel = usuarioAltaTel;
	}

	/**
	 * Gets the fecha alta registro tel.
	 *
	 * @return the fecha alta registro tel
	 */
	public String getFechaAltaRegistroTel() {
		return fechaAltaRegistroTel;
	}

	/**
	 * Sets the fecha alta registro tel.
	 *
	 * @param fechaAltaRegistroTel
	 *            the new fecha alta registro tel
	 */
	public void setFechaAltaRegistroTel(String fechaAltaRegistroTel) {
		this.fechaAltaRegistroTel = fechaAltaRegistroTel;
	}

	/**
	 * Gets the usuario ultima mod tel.
	 *
	 * @return the usuario ultima mod tel
	 */
	public String getUsuarioUltimaModTel() {
		return usuarioUltimaModTel;
	}

	/**
	 * Sets the usuario ultima mod tel.
	 *
	 * @param usuarioUltimaModTel
	 *            the new usuario ultima mod tel
	 */
	public void setUsuarioUltimaModTel(String usuarioUltimaModTel) {
		this.usuarioUltimaModTel = usuarioUltimaModTel;
	}

	/**
	 * Gets the nro terminal ult mod tel.
	 *
	 * @return the nro terminal ult mod tel
	 */
	public String getNroTerminalUltModTel() {
		return nroTerminalUltModTel;
	}

	/**
	 * Sets the nro terminal ult mod tel.
	 *
	 * @param nroTerminalUltModTel
	 *            the new nro terminal ult mod tel
	 */
	public void setNroTerminalUltModTel(String nroTerminalUltModTel) {
		this.nroTerminalUltModTel = nroTerminalUltModTel;
	}

	/**
	 * Gets the sucursal ult mod tel.
	 *
	 * @return the sucursal ult mod tel
	 */
	public String getSucursalUltModTel() {
		return sucursalUltModTel;
	}

	/**
	 * Sets the sucursal ult mod tel.
	 *
	 * @param sucursalUltModTel
	 *            the new sucursal ult mod tel
	 */
	public void setSucursalUltModTel(String sucursalUltModTel) {
		this.sucursalUltModTel = sucursalUltModTel;
	}

	/**
	 * Gets the timestamp ult mod tel.
	 *
	 * @return the timestamp ult mod tel
	 */
	public String getTimestampUltModTel() {
		return timestampUltModTel;
	}

	/**
	 * Sets the timestamp ult mod tel.
	 *
	 * @param timestampUltModTel
	 *            the new timestamp ult mod tel
	 */
	public void setTimestampUltModTel(String timestampUltModTel) {
		this.timestampUltModTel = timestampUltModTel;
	}

	/**
	 * Gets the codigo provincia tel.
	 *
	 * @return the codigo provincia tel
	 */
	public String getCodigoProvinciaTel() {
		return codigoProvinciaTel;
	}

	/**
	 * Sets the codigo provincia tel.
	 *
	 * @param codigoProvinciaTel
	 *            the new codigo provincia tel
	 */
	public void setCodigoProvinciaTel(String codigoProvinciaTel) {
		this.codigoProvinciaTel = codigoProvinciaTel;
	}

	/**
	 * Gets the calle anterior.
	 *
	 * @return the calle anterior
	 */
	public String getCalleAnterior() {
		return calleAnterior;
	}

	/**
	 * Sets the calle anterior.
	 *
	 * @param calleAnterior
	 *            the new calle anterior
	 */
	public void setCalleAnterior(String calleAnterior) {
		this.calleAnterior = calleAnterior;
	}

	/**
	 * Gets the observaciones 1 anterior.
	 *
	 * @return the observaciones 1 anterior
	 */
	public String getObservaciones1Anterior() {
		return observaciones1Anterior;
	}

	/**
	 * Sets the observaciones 1 anterior.
	 *
	 * @param observaciones1Anterior
	 *            the new observaciones 1 anterior
	 */
	public void setObservaciones1Anterior(String observaciones1Anterior) {
		this.observaciones1Anterior = observaciones1Anterior;
	}

	/**
	 * Gets the apt anterior.
	 *
	 * @return the apt anterior
	 */
	public String getAptAnterior() {
		return aptAnterior;
	}

	/**
	 * Sets the apt anterior.
	 *
	 * @param aptAnterior
	 *            the new apt anterior
	 */
	public void setAptAnterior(String aptAnterior) {
		this.aptAnterior = aptAnterior;
	}

	/**
	 * Gets the desc localidad anterior.
	 *
	 * @return the desc localidad anterior
	 */
	public String getDescLocalidadAnterior() {
		return descLocalidadAnterior;
	}

	/**
	 * Sets the desc localidad anterior.
	 *
	 * @param descLocalidadAnterior
	 *            the new desc localidad anterior
	 */
	public void setDescLocalidadAnterior(String descLocalidadAnterior) {
		this.descLocalidadAnterior = descLocalidadAnterior;
	}

	/**
	 * Gets the desc comuna anterior.
	 *
	 * @return the desc comuna anterior
	 */
	public String getDescComunaAnterior() {
		return descComunaAnterior;
	}

	/**
	 * Sets the desc comuna anterior.
	 *
	 * @param descComunaAnterior
	 *            the new desc comuna anterior
	 */
	public void setDescComunaAnterior(String descComunaAnterior) {
		this.descComunaAnterior = descComunaAnterior;
	}

	/**
	 * Gets the codigo postal anterior.
	 *
	 * @return the codigo postal anterior
	 */
	public String getCodigoPostalAnterior() {
		return codigoPostalAnterior;
	}

	/**
	 * Sets the codigo postal anterior.
	 *
	 * @param codigoPostalAnterior
	 *            the new codigo postal anterior
	 */
	public void setCodigoPostalAnterior(String codigoPostalAnterior) {
		this.codigoPostalAnterior = codigoPostalAnterior;
	}

	/**
	 * Gets the codigo provincia anterior.
	 *
	 * @return the codigo provincia anterior
	 */
	public String getCodigoProvinciaAnterior() {
		return codigoProvinciaAnterior;
	}

	/**
	 * Sets the codigo provincia anterior.
	 *
	 * @param codigoProvinciaAnterior
	 *            the new codigo provincia anterior
	 */
	public void setCodigoProvinciaAnterior(String codigoProvinciaAnterior) {
		this.codigoProvinciaAnterior = codigoProvinciaAnterior;
	}

	/**
	 * Gets the descripcion provincia anterior.
	 *
	 * @return the descripcion provincia anterior
	 */
	public String getDescripcionProvinciaAnterior() {
		return descripcionProvinciaAnterior;
	}

	/**
	 * Sets the descripcion provincia anterior.
	 *
	 * @param descripcionProvinciaAnterior
	 *            the new descripcion provincia anterior
	 */
	public void setDescripcionProvinciaAnterior(String descripcionProvinciaAnterior) {
		this.descripcionProvinciaAnterior = descripcionProvinciaAnterior;
	}

	/**
	 * Gets the prefijo anterior.
	 *
	 * @return the prefijo anterior
	 */
	public String getPrefijoAnterior() {
		return prefijoAnterior;
	}

	/**
	 * Sets the prefijo anterior.
	 *
	 * @param prefijoAnterior
	 *            the new prefijo anterior
	 */
	public void setPrefijoAnterior(String prefijoAnterior) {
		this.prefijoAnterior = prefijoAnterior;
	}

	/**
	 * Gets the caracteristica anterior.
	 *
	 * @return the caracteristica anterior
	 */
	public String getCaracteristicaAnterior() {
		return caracteristicaAnterior;
	}

	/**
	 * Sets the caracteristica anterior.
	 *
	 * @param caracteristicaAnterior
	 *            the new caracteristica anterior
	 */
	public void setCaracteristicaAnterior(String caracteristicaAnterior) {
		this.caracteristicaAnterior = caracteristicaAnterior;
	}

	/**
	 * Gets the nro telefono anterior.
	 *
	 * @return the nro telefono anterior
	 */
	public String getNroTelefonoAnterior() {
		return nroTelefonoAnterior;
	}

	/**
	 * Sets the nro telefono anterior.
	 *
	 * @param nroTelefonoAnterior
	 *            the new nro telefono anterior
	 */
	public void setNroTelefonoAnterior(String nroTelefonoAnterior) {
		this.nroTelefonoAnterior = nroTelefonoAnterior;
	}

	/**
	 * Gets the cantidad productos dom.
	 *
	 * @return the cantidad productos dom
	 */
	public String getCantidadProductosDom() {
		return cantidadProductosDom;
	}

	/**
	 * Sets the cantidad productos dom.
	 *
	 * @param cantidadProductosDom
	 *            the new cantidad productos dom
	 */
	public void setCantidadProductosDom(String cantidadProductosDom) {
		this.cantidadProductosDom = cantidadProductosDom;
	}

	/**
	 * Gets the listaproductos.
	 *
	 * @return the listaproductos
	 */
	public List<ProductoEntity> getListaproductos() {
		return listaproductos;
	}

	/**
	 * Sets the listaproductos.
	 *
	 * @param listaproductos
	 *            the new listaproductos
	 */
	public void setListaproductos(List<ProductoEntity> listaproductos) {
		this.listaproductos = listaproductos;
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

}
