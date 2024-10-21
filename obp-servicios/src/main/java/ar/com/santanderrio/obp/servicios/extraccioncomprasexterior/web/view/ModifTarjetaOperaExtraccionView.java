/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * ModifTarjetaOperaExtraccionView.
 *
 * @author Silvina_Luque
 */
@SuppressWarnings("serial")
public class ModifTarjetaOperaExtraccionView extends RsaDTOParaDesafio {

	/** The id tarjeta. */
	private String idTarjeta;

	/** The id cuenta. */
	private String idCuenta;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The mensaje. */
	private String mensaje;

	/** The tiene celular my A. Se usa en RSA. */
	private boolean tieneCelularMyA = false;
	
	private String moneda;
	
    private Integer cantDiasUltimoCambioClave;
    
    private Integer cantDiasUltimoCambioToken;
    

	/**
	 * Instantiates a new modif tarjeta opera extraccion view.
	 */
	public ModifTarjetaOperaExtraccionView() {
		super(OperacionesRSAEnum.EXTCOMPRASEXTERIOR);
	}

	/**
	 * Gets the id tarjeta.
	 *
	 * @return the id tarjeta
	 */
	public String getIdTarjeta() {
		return idTarjeta;
	}

	/**
	 * Sets the id tarjeta.
	 *
	 * @param idTarjeta
	 *            the new id tarjeta
	 */
	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return the id cuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param idCuenta
	 *            the new id cuenta
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Checks if is tiene celular my A.
	 *
	 * @return true, if is tiene celular my A
	 */
	public boolean isTieneCelularMyA() {
		return tieneCelularMyA;
	}

	/**
	 * Sets the tiene celular my A.
	 *
	 * @param tieneCelularMyA
	 *            the new tiene celular my A
	 */
	public void setTieneCelularMyA(boolean tieneCelularMyA) {
		this.tieneCelularMyA = tieneCelularMyA;
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

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

}
