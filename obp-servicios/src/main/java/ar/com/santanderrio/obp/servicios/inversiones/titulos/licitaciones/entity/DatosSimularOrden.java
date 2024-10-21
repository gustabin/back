/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosSimularOrden.
 */
public class DatosSimularOrden {

	/** The tipo pliego. */
	@JsonProperty("TipoPliego")
	private String tipoPliego;

	/** The canal. */
	private String canal;

	/** The subcanal. */
	private String subcanal;

	/** The usuario. */
	private String usuario;

	/** The ip. */
	private String ip;

	/** The nup. */
	private String nup;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	/** codigoTramoCanal. */
	@JsonProperty("CodTramoCanal")
	private long codTramoCanal;

	/** codigoPliego. */
	@JsonProperty("CodPliego")
	private long codPliego;

	/** codigoTramo. */
	@JsonProperty("CodTramo")
	private long codTramo;

	/** tipoCuenta. */
	@JsonProperty("TipoCuenta")
	private String tipoCuenta;

	/** tipoCuentaOper. */
	@JsonProperty("TipoCuentaOper")
	private String tipoCuentaOper;

	/** cuentaOper. */
	@JsonProperty("CuentaOper")
	private String cuentaOper;
	
	/** cuentaCust. */
	@JsonProperty("CuentaCust")
	private String cuentaCust;

	/** sucursal. */
	@JsonProperty("Sucursal")
	private String sucursal;

	/** codigoEspecie. */
	@JsonProperty("Especie")
	private String especie;

	/** moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** precio. */
	@JsonProperty("Precio")
	private String precio;

	/** correoElectronico. */
	@JsonProperty("CorreoElect")
	private String correoElect;

	/** cuentaTit. */
	@JsonProperty("CuentaTIt")
	private String cuentaTit;

	/** cantidad. */
	@JsonProperty("Cantidad")
	private String cantidad;

	/** monto. */
	private String monto;

	/** renovacion. */
	@JsonProperty("Renovacion")
	private String renovacion;

	/** fechaOrden. */
	@JsonProperty("FechaOrden")
	private Date fechaOrden = new Date();

	/** renovacion. */
	@JsonProperty("UsuarioRacf")
	private String usuarioRacf;

	/** renovacion. */
	@JsonProperty("PasswordRacf")
	private String passwordRacf;

	/** The especie renovacion. */
	private String especieRenovacion;

	/** The cantidad renovable. */
	private double cantidadRenovable;

	/** The lugar renovacion. */
	private short lugarRenovacion;
	
	@JsonProperty("TipoEjecucion")
	private String tipoEjecucion;
	
	@JsonProperty("Coeficiente")
	private Double coeficiente;

	/**
	 * Gets the tipo pliego.
	 *
	 * @return the tipoPliego
	 */
	public String getTipoPliego() {
		return tipoPliego;
	}

	/**
	 * Sets the tipo pliego.
	 *
	 * @param tipoPliego
	 *            the tipoPliego to set
	 */
	public void setTipoPliego(String tipoPliego) {
		this.tipoPliego = tipoPliego;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the subcanal.
	 *
	 * @return the subcanal
	 */
	public String getSubcanal() {
		return subcanal;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param subcanal
	 *            the subcanal to set
	 */
	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

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
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the codigo tramo canal.
	 *
	 * @return the codigo tramo canal
	 */
	public long getCodigoTramoCanal() {
		return codTramoCanal;
	}

	/**
	 * Gets the codigo pliego.
	 *
	 * @return the codigo pliego
	 */
	public long getCodigoPliego() {
		return codPliego;
	}

	/**
	 * Gets the codigo tramo.
	 *
	 * @return the codigo tramo
	 */
	public long getCodigoTramo() {
		return codTramo;
	}

	/**
	 * Gets the tipo cuenta oper.
	 *
	 * @return the tipo cuenta oper
	 */
	public String getTipoCuentaOper() {
		return tipoCuentaOper;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigo especie
	 */
	public String getCodigoEspecie() {
		return especie;
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
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Gets the correo electronico.
	 *
	 * @return the correo electronico
	 */
	public String getCorreoElectronico() {
		return correoElect;
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
	 * Sets the codigo tramo canal.
	 *
	 * @param codigoTramoCanal
	 *            the new codigo tramo canal
	 */
	public void setCodigoTramoCanal(long codigoTramoCanal) {
		this.codTramoCanal = codigoTramoCanal;
	}

	/**
	 * Sets the codigo pliego.
	 *
	 * @param codigoPliego
	 *            the new codigo pliego
	 */
	public void setCodigoPliego(long codigoPliego) {
		this.codPliego = codigoPliego;
	}

	/**
	 * Sets the codigo tramo.
	 *
	 * @param codigoTramo
	 *            the new codigo tramo
	 */
	public void setCodigoTramo(long codigoTramo) {
		this.codTramo = codigoTramo;
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
	 * Sets the tipo cuenta oper.
	 *
	 * @param tipoCuentaOper
	 *            the new tipo cuenta oper
	 */
	public void setTipoCuentaOper(String tipoCuentaOper) {
		this.tipoCuentaOper = tipoCuentaOper;
	}

	/**
	 * Gets the cuenta oper.
	 *
	 * @return the cuenta oper
	 */
	public String getCuentaOper() {
		return cuentaOper;
	}

	/**
	 * Sets the cuenta oper.
	 *
	 * @param cuentaOper
	 *            the new cuenta oper
	 */
	public void setCuentaOper(String cuentaOper) {
		this.cuentaOper = cuentaOper;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the new codigo especie
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.especie = codigoEspecie;
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
	 * Sets the precio.
	 *
	 * @param precio
	 *            the new precio
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * Sets the correo electronico.
	 *
	 * @param correoElectronico
	 *            the new correo electronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElect = correoElectronico;
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
	 * Gets the cuenta tit.
	 *
	 * @return the cuenta tit
	 */
	public String getCuentaTit() {
		return cuentaTit;
	}

	/**
	 * Sets the cuenta tit.
	 *
	 * @param cuentaTit
	 *            the new cuenta tit
	 */
	public void setCuentaTit(String cuentaTit) {
		this.cuentaTit = cuentaTit;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the fecha orden.
	 *
	 * @return the fecha orden
	 */
	public Date getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden
	 *            the new fecha orden
	 */
	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the usuario racf.
	 *
	 * @return the usuario racf
	 */
	public String getUsuarioRacf() {
		return usuarioRacf;
	}

	/**
	 * Gets the password racf.
	 *
	 * @return the password racf
	 */
	public String getPasswordRacf() {
		return passwordRacf;
	}

	/**
	 * Sets the usuario racf.
	 *
	 * @param usuarioRacf
	 *            the new usuario racf
	 */
	public void setUsuarioRacf(String usuarioRacf) {
		this.usuarioRacf = usuarioRacf;
	}

	/**
	 * Sets the password racf.
	 *
	 * @param passwordRacf
	 *            the new password racf
	 */
	public void setPasswordRacf(String passwordRacf) {
		this.passwordRacf = passwordRacf;
	}

	/**
	 * Gets the cod tramo canal.
	 *
	 * @return the cod tramo canal
	 */
	public long getCodTramoCanal() {
		return codTramoCanal;
	}

	/**
	 * Sets the cod tramo canal.
	 *
	 * @param codTramoCanal
	 *            the new cod tramo canal
	 */
	public void setCodTramoCanal(long codTramoCanal) {
		this.codTramoCanal = codTramoCanal;
	}

	/**
	 * Gets the cod pliego.
	 *
	 * @return the cod pliego
	 */
	public long getCodPliego() {
		return codPliego;
	}

	/**
	 * Sets the cod pliego.
	 *
	 * @param codPliego
	 *            the new cod pliego
	 */
	public void setCodPliego(long codPliego) {
		this.codPliego = codPliego;
	}

	/**
	 * Gets the cod tramo.
	 *
	 * @return the cod tramo
	 */
	public long getCodTramo() {
		return codTramo;
	}

	/**
	 * Sets the cod tramo.
	 *
	 * @param codTramo
	 *            the new cod tramo
	 */
	public void setCodTramo(long codTramo) {
		this.codTramo = codTramo;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the correo elect.
	 *
	 * @return the correo elect
	 */
	public String getCorreoElect() {
		return correoElect;
	}

	/**
	 * Sets the correo elect.
	 *
	 * @param correoElect
	 *            the new correo elect
	 */
	public void setCorreoElect(String correoElect) {
		this.correoElect = correoElect;
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
	 * Gets the cantidad renovable.
	 *
	 * @return the cantidad renovable
	 */
	public double getCantidadRenovable() {
		return cantidadRenovable;
	}

	/**
	 * Sets the cantidad renovable.
	 *
	 * @param cantidadRenovable
	 *            the new cantidad renovable
	 */
	public void setCantidadRenovable(double cantidadRenovable) {
		this.cantidadRenovable = cantidadRenovable;
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

	/**
	 * Gets the cuenta cust.
	 *
	 * @return the cuenta cust
	 */
	public String getCuentaCust() {
		return cuentaCust;
	}

	/**
	 * Sets the cuenta cust.
	 *
	 * @param cuentaCust
	 *            the new cuenta cust
	 */
	public void setCuentaCust(String cuentaCust) {
		this.cuentaCust = cuentaCust;
	}

	public String getTipoEjecucion() {
		return tipoEjecucion;
	}

	public void setTipoEjecucion(String tipoEjecucion) {
		this.tipoEjecucion = tipoEjecucion;
	}

	public Double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Double coeficiente) {
		this.coeficiente = coeficiente;
	}
	
	
}
