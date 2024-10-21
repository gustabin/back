/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SimularLicitacionRequest.
 *
 * @author B039920
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimularLicitacionRequest{

	/** codigoTramoCanal. */
	@NotNull
	private long codigoTramoCanal;

	/** codigoPliego. */
	@NotNull
	private long codigoPliego;

	/** codigoTramo. */
	@NotNull
	private long codigoTramo;

	/** sucursalCuenta. */
	@NotNull
	private String sucursal;

	/** numeroCuenta. */
	@NotNull
	private String numeroCuenta;

	/** tipoCuenta. */
	@NotNull
	private String tipoCuenta;

	/** sucursalCuentaTitulo. */
	@NotNull
	private String sucursalCuentaTitulo;

	/** numeroCuentaTitulo. */
	@NotNull
	private String numeroCuentaTitulo;

	/** codigoEspecie. */
	@NotNull
	private String codigoEspecie;

	/** moneda. */
	@NotNull
	@Pattern(regexp = "(ARS)|(USD)")
	private String moneda;

	/** precio - puede ser null (para tramo no competitivo). */
	private String precioTasa;

	/** correoElectronico. */
	@NotNull
	private String correoElectronico;

	/** tipoOferta. */
	@NotNull
	private String tipoOferta;

	/** valorTipoOferta. */
	@NotNull
	private String valorTipoOferta;

	/** Nombre del archivo PDF de las condiciones de emision a descargar. */
	private String nombreArchivoCondiciones;

	/** The renovacion. */
	private String renovacion;

	/** The especie renovacion. */
	private String especieRenovacion;

	/** The cantidad renovacion. */
	private double cantidadRenovacion;

	/** The lugar renovacion. */
	private short lugarRenovacion;

	/**
	 * Gets the codigo tramo canal.
	 *
	 * @return the codigo tramo canal
	 */
	public long getCodigoTramoCanal() {
		return codigoTramoCanal;
	}

	/**
	 * Sets the codigo tramo canal.
	 *
	 * @param codigoTramoCanal
	 *            the new codigo tramo canal
	 */
	public void setCodigoTramoCanal(long codigoTramoCanal) {
		this.codigoTramoCanal = codigoTramoCanal;
	}

	/**
	 * Gets the codigo pliego.
	 *
	 * @return the codigo pliego
	 */
	public long getCodigoPliego() {
		return codigoPliego;
	}

	/**
	 * Sets the codigo pliego.
	 *
	 * @param codigoPliego
	 *            the new codigo pliego
	 */
	public void setCodigoPliego(long codigoPliego) {
		this.codigoPliego = codigoPliego;
	}

	/**
	 * Gets the codigo tramo.
	 *
	 * @return the codigo tramo
	 */
	public long getCodigoTramo() {
		return codigoTramo;
	}

	/**
	 * Sets the codigo tramo.
	 *
	 * @param codigoTramo
	 *            the new codigo tramo
	 */
	public void setCodigoTramo(long codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	/**
	 * Gets the sucursal cuenta .
	 *
	 * @return the sucursal cuenta 
	 */
	public String getSucursalCuenta() {
		return sucursal;
	}

	/**
	 * Sets the sucursal cuenta .
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta 
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursal = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta .
	 *
	 * @return the numero cuenta 
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta .
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta 
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tipo cuenta .
	 *
	 * @return the tipo cuenta 
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta .
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta 
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta titulo.
	 *
	 * @return the sucursal cuenta titulo
	 */
	public String getSucursalCuentaTitulo() {
		return sucursalCuentaTitulo;
	}

	/**
	 * Gets the numero cuenta titulo.
	 *
	 * @return the numero cuenta titulo
	 */
	public String getNumeroCuentaTitulo() {
		return numeroCuentaTitulo;
	}

	/**
	 * Sets the sucursal cuenta titulo.
	 *
	 * @param sucursalCuentaTitulo
	 *            the new sucursal cuenta titulo
	 */
	public void setSucursalCuentaTitulo(String sucursalCuentaTitulo) {
		this.sucursalCuentaTitulo = sucursalCuentaTitulo;
	}

	/**
	 * Sets the numero cuenta titulo.
	 *
	 * @param numeroCuentaTitulo
	 *            the new numero cuenta titulo
	 */
	public void setNumeroCuentaTitulo(String numeroCuentaTitulo) {
		this.numeroCuentaTitulo = numeroCuentaTitulo;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigo especie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the new codigo especie
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the precio tasa.
	 *
	 * @return the precio tasa
	 */
	public String getPrecioTasa() {
		return precioTasa;
	}

	/**
	 * Sets the precio tasa.
	 *
	 * @param precioTasa
	 *            the new precio tasa
	 */
	public void setPrecioTasa(String precioTasa) {
		this.precioTasa = precioTasa;
	}

	/**
	 * Gets the correo electronico.
	 *
	 * @return the correo electronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Sets the correo electronico.
	 *
	 * @param correoElectronico
	 *            the new correo electronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Gets the tipo oferta.
	 *
	 * @return the tipo oferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * Sets the tipo oferta.
	 *
	 * @param tipoOferta
	 *            the new tipo oferta
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * Gets the valor tipo oferta.
	 *
	 * @return the valor tipo oferta
	 */
	public String getValorTipoOferta() {
		return valorTipoOferta;
	}

	/**
	 * Sets the valor tipo oferta.
	 *
	 * @param valorTipoOferta
	 *            the new valor tipo oferta
	 */
	public void setValorTipoOferta(String valorTipoOferta) {
		this.valorTipoOferta = valorTipoOferta;
	}

	/**
	 * Gets the nombre archivo condiciones.
	 *
	 * @return the nombre archivo condiciones
	 */
	public String getNombreArchivoCondiciones() {
		return nombreArchivoCondiciones;
	}

	/**
	 * Sets the nombre archivo condiciones.
	 *
	 * @param nombreArchivoCondiciones
	 *            the new nombre archivo condiciones
	 */
	public void setNombreArchivoCondiciones(String nombreArchivoCondiciones) {
		this.nombreArchivoCondiciones = nombreArchivoCondiciones;
	}

	/**
	 * Gets the renovacion.
	 *
	 * @return the renovacion
	 */
	public String getRenovacion() {
		return renovacion;
	}

	/**
	 * Sets the renovacion.
	 *
	 * @param renovacion
	 *            the new renovacion
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the especie renovacion.
	 *
	 * @return the especie renovacion
	 */
	public String getEspecieRenovacion() {
		return especieRenovacion;
	}

	/**
	 * Sets the especie renovacion.
	 *
	 * @param especieRenovacion
	 *            the new especie renovacion
	 */
	public void setEspecieRenovacion(String especieRenovacion) {
		this.especieRenovacion = especieRenovacion;
	}

	/**
	 * Gets the cantidad renovacion.
	 *
	 * @return the cantidad renovacion
	 */
	public double getCantidadRenovacion() {
		return cantidadRenovacion;
	}

	/**
	 * Sets the cantidad renovacion.
	 *
	 * @param cantidadRenovacion
	 *            the new cantidad renovacion
	 */
	public void setCantidadRenovacion(double cantidadRenovacion) {
		this.cantidadRenovacion = cantidadRenovacion;
	}

	/**
	 * Gets the lugar renovacion.
	 *
	 * @return the lugar renovacion
	 */
	public short getLugarRenovacion() {
		return lugarRenovacion;
	}

	/**
	 * Sets the lugar renovacion.
	 *
	 * @param lugarRenovacion
	 *            the new lugar renovacion
	 */
	public void setLugarRenovacion(short lugarRenovacion) {
		this.lugarRenovacion = lugarRenovacion;
	}

}
