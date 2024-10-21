/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class IdentificadorClienteOutEntity.
 */
@Record
public class IdentificadorClienteOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The nup. */
	@Field
	private String nup;

	/** The sesion. */
	@Field
	private String sesion;

	/** The ciclos. */
	@Field
	private String ciclos;

	/** The preg ciclo. */
	@Field
	private String pregCiclo;

	/** The tipo aut. */
	@Field
	private String tipoAut;

	/** The numero. */
	@Field
	private String numero;

	/** The cantidad ocurrencias. */
	@Field
	private Long cantidadOcurrencias;

	/** The productos. */
	@Segment(occursRef = "cantidadOcurrencias")
	private List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
	
	
	

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
	 * @param headerTrama
	 *            the new header trama
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
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
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
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the sesion.
	 *
	 * @return the sesion
	 */
	public String getSesion() {
		return sesion;
	}

	/**
	 * Sets the sesion.
	 *
	 * @param sesion
	 *            the new sesion
	 */
	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	/**
	 * Gets the ciclos.
	 *
	 * @return the ciclos
	 */
	public String getCiclos() {
		return ciclos;
	}

	/**
	 * Sets the ciclos.
	 *
	 * @param ciclos
	 *            the new ciclos
	 */
	public void setCiclos(String ciclos) {
		this.ciclos = ciclos;
	}

	/**
	 * Gets the preg ciclo.
	 *
	 * @return the preg ciclo
	 */
	public String getPregCiclo() {
		return pregCiclo;
	}

	/**
	 * Sets the preg ciclo.
	 *
	 * @param pregCiclo
	 *            the new preg ciclo
	 */
	public void setPregCiclo(String pregCiclo) {
		this.pregCiclo = pregCiclo;
	}

	/**
	 * Gets the tipo aut.
	 *
	 * @return the tipo aut
	 */
	public String getTipoAut() {
		return tipoAut;
	}

	/**
	 * Sets the tipo aut.
	 *
	 * @param tipoAut
	 *            the new tipo aut
	 */
	public void setTipoAut(String tipoAut) {
		this.tipoAut = tipoAut;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidad ocurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the new cantidad ocurrencias
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	public List<ProductoEntity> getProductos() {
		return productos;
	}

	/**
	 * Sets the productos.
	 *
	 * @param productos
	 *            the new productos
	 */
	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}

}
