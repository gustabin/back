/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class ConsultaCuentaOnDemandOutEntity.
 */
@Record
public class ConsultaCuentaOnDemandOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;
	
	/** The codigo. */
	@Field
	private String codigo;
	
	/** The codigo numerico. */
	@Field
	private String codigoNumerico;
	
	/** The mensaje. */
	@Field
	private String mensaje;
	
	/** The cantidad cuentas. */
	@Field
	private String vacio;
	
	/** The codigo mensaje. */
	@Field
	private String codigoMensaje;
	
	/** The cantidad cuentas. */
	@Field
	private Long cantidadCuentas;

	/** The cuentas. */
	@Segment(occursRef = "cantidadCuentas")
	private List<CuentaOnDemandDTO> cuentas = new ArrayList<CuentaOnDemandDTO>();

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo numerico.
	 *
	 * @return the codigo numerico
	 */
	public String getCodigoNumerico() {
		return codigoNumerico;
	}

	/**
	 * Sets the codigo numerico.
	 *
	 * @param codigoNumerico the new codigo numerico
	 */
	public void setCodigoNumerico(String codigoNumerico) {
		this.codigoNumerico = codigoNumerico;
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
	 * @param mensaje the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the codigo mensaje.
	 *
	 * @return the codigo mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * Sets the codigo mensaje.
	 *
	 * @param codigoMensaje the new codigo mensaje
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
	

	/**
	 * Gets the cantidad cuentas.
	 *
	 * @return the cantidad cuentas
	 */
	public Long getCantidadCuentas() {
		return cantidadCuentas;
	}

	/**
	 * Sets the cantidad cuentas.
	 *
	 * @param cantidadCuentas the new cantidad cuentas
	 */
	public void setCantidadCuentas(Long cantidadCuentas) {
		this.cantidadCuentas = cantidadCuentas;
	}

	/**
	 * Gets the vacio.
	 *
	 * @return the vacio
	 */
	public String getVacio() {
		return vacio;
	}

	/**
	 * Sets the vacio.
	 *
	 * @param vacio
	 *            the new vacio
	 */
	public void setVacio(String vacio) {
		this.vacio = vacio;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaOnDemandDTO> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas the new cuentas
	 */
	public void setCuentas(List<CuentaOnDemandDTO> cuentas) {
		this.cuentas = cuentas;
	}

}
