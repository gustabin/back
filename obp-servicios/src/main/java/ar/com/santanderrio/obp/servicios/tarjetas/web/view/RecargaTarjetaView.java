/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class RecargaTarjetaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecargaTarjetaView extends RsaDTO {

	/**
	 * Instantiates a new recarga tarjeta view.
	 */
	public RecargaTarjetaView() {
		super(OperacionesRSAEnum.RECARGA_TARJETA);
	}

	/** The desafio. */
	AutentificacionDTO desafio;

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The importe recarga pesos. */
	private String importeRecargaPesos;

	/** The sucursal cuenta destino. */
	private String sucursalCuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The sucursal cuenta origen. */
	private String sucursalCuentaOrigen;

	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;

	/** The abreviatura tipo cuenta. */
	private String abreviaturaTipoCuenta;

	/** The nro cuenta origen. */
	private String nroCuentaOrigen;

	/** The divisa. */
	private String divisa;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The fecha de recarga. */
	private String fechaDeRecarga;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The fecha de modificacion. */
	private String fechaDeModificacion;

	/** The fecha prox recarga. */
	private String fechaProxRecarga;

	/** The frecuencia. */
	private String frecuencia;

	/**
	 * Gets the importe recarga pesos.
	 *
	 * @return the importe recarga pesos
	 */
	public String getImporteRecargaPesos() {
		return importeRecargaPesos;
	}

	/**
	 * Sets the importe recarga pesos.
	 *
	 * @param importeRecargaPesos
	 *            the new importe recarga pesos
	 */
	public void setImporteRecargaPesos(String importeRecargaPesos) {
		this.importeRecargaPesos = importeRecargaPesos;
	}

	/**
	 * Gets the sucursal cuenta destino.
	 *
	 * @return the sucursal cuenta destino
	 */
	public String getSucursalCuentaDestino() {
		return sucursalCuentaDestino;
	}

	/**
	 * Sets the sucursal cuenta destino.
	 *
	 * @param sucursalCuentaDestino
	 *            the new sucursal cuenta destino
	 */
	public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
		this.sucursalCuentaDestino = sucursalCuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Gets the sucursal cuenta origen.
	 *
	 * @return the sucursal cuenta origen
	 */
	public String getSucursalCuentaOrigen() {
		return sucursalCuentaOrigen;
	}

	/**
	 * Sets the sucursal cuenta origen.
	 *
	 * @param sucursalCuentaOrigen
	 *            the new sucursal cuenta origen
	 */
	public void setSucursalCuentaOrigen(String sucursalCuentaOrigen) {
		this.sucursalCuentaOrigen = sucursalCuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipo cuenta origen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the new tipo cuenta origen
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the nro cuenta origen.
	 *
	 * @return the nro cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Sets the nro cuenta origen.
	 *
	 * @param nroCuentaOrigen
	 *            the new nro cuenta origen
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the fecha de recarga.
	 *
	 * @return the fecha de recarga
	 */
	public String getFechaDeRecarga() {
		return fechaDeRecarga;
	}

	/**
	 * Sets the fecha de recarga.
	 *
	 * @param fechaDeRecarga
	 *            the new fecha de recarga
	 */
	public void setFechaDeRecarga(String fechaDeRecarga) {
		this.fechaDeRecarga = fechaDeRecarga;
	}

	/**
	 * Gets the abreviatura tipo cuenta.
	 *
	 * @return the abreviaturaTipoCuenta
	 */
	public String getAbreviaturaTipoCuenta() {
		return abreviaturaTipoCuenta;
	}

	/**
	 * Sets the abreviatura tipo cuenta.
	 *
	 * @param abreviaturaTipoCuenta
	 *            the abreviaturaTipoCuenta to set
	 */
	public void setAbreviaturaTipoCuenta(String abreviaturaTipoCuenta) {
		this.abreviaturaTipoCuenta = abreviaturaTipoCuenta;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Gets the fecha prox recarga.
	 *
	 * @return the fecha prox recarga
	 */
	public String getFechaProxRecarga() {
		return fechaProxRecarga;
	}

	/**
	 * Sets the fecha prox recarga.
	 *
	 * @param fechaProxRecarga
	 *            the new fecha prox recarga
	 */
	public void setFechaProxRecarga(String fechaProxRecarga) {
		this.fechaProxRecarga = fechaProxRecarga;
	}

	/**
	 * Gets the fecha de modificacion.
	 *
	 * @return the fecha de modificacion
	 */
	public String getFechaDeModificacion() {
		return fechaDeModificacion;
	}

	/**
	 * Sets the fecha de modificacion.
	 *
	 * @param fechaDeModificacion
	 *            the new fecha de modificacion
	 */
	public void setFechaDeModificacion(String fechaDeModificacion) {
		this.fechaDeModificacion = fechaDeModificacion;
	}

}
