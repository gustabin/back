/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * ConsultaDomiciliosOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaDomiciliosOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The hay mas datos. */
	@Field
	private String hayMasDatos;

	/** The cantidad total domicilios. */
	@Field
	private String cantidadTotalDomicilios;

	/** The cantidad domicilios informados. */
	@Field
	private Long cantidadDomiciliosInformados;

	/** The lista domicilios. */
	@Segment(occursRef = "cantidadDomiciliosInformados")
	private List<DomicilioEntity> listaDomicilios = new ArrayList<DomicilioEntity>();

	/** The cantidad productos. */
	@Field
	private Long cantidadProductos;

	/** The listaproductos. */
	@Segment(occursRef = "cantidadProductos")
	private List<ProductoEntity> listaproductos = new ArrayList<ProductoEntity>();

	/** The codigo retorno. */
	@Field
	private String codigoRetorno;

	/** The nombre apellido. */
	@Field
	private String nombreApellido;

	/** The tipo cuil. */
	@Field
	private String tipoCuil;

	/** The numero cuil. */
	@Field
	private String numeroCuil;

	/** The nup. */
	@Field
	private String nup;
	
	/** The ultima secuencia domicilio. */
	private String ultimaSecuenciaDomicilio;

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
	 * Gets the hay mas datos.
	 *
	 * @return the hay mas datos
	 */
	public String getHayMasDatos() {
		return hayMasDatos;
	}

	/**
	 * Sets the hay mas datos.
	 *
	 * @param hayMasDatos
	 *            the new hay mas datos
	 */
	public void setHayMasDatos(String hayMasDatos) {
		this.hayMasDatos = hayMasDatos;
	}

	/**
	 * Gets the cantidad total domicilios.
	 *
	 * @return the cantidad total domicilios
	 */
	public String getCantidadTotalDomicilios() {
		return cantidadTotalDomicilios;
	}

	/**
	 * Sets the cantidad total domicilios.
	 *
	 * @param cantidadTotalDomicilios
	 *            the new cantidad total domicilios
	 */
	public void setCantidadTotalDomicilios(String cantidadTotalDomicilios) {
		this.cantidadTotalDomicilios = cantidadTotalDomicilios;
	}

	/**
	 * Gets the cantidad domicilios informados.
	 *
	 * @return the cantidad domicilios informados
	 */
	public Long getCantidadDomiciliosInformados() {
		return cantidadDomiciliosInformados;
	}

	/**
	 * Sets the cantidad domicilios informados.
	 *
	 * @param cantidadDomiciliosInformados
	 *            the new cantidad domicilios informados
	 */
	public void setCantidadDomiciliosInformados(Long cantidadDomiciliosInformados) {
		this.cantidadDomiciliosInformados = cantidadDomiciliosInformados;
	}

	/**
	 * Gets the lista domicilios.
	 *
	 * @return the lista domicilios
	 */
	public List<DomicilioEntity> getListaDomicilios() {
		return listaDomicilios;
	}

	/**
	 * Sets the lista domicilios.
	 *
	 * @param listaDomicilios
	 *            the new lista domicilios
	 */
	public void setListaDomicilios(List<DomicilioEntity> listaDomicilios) {
		this.listaDomicilios = listaDomicilios;
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
	 * Gets the codigo retorno.
	 *
	 * @return the codigo retorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the new codigo retorno
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the nombre apellido.
	 *
	 * @return the nombre apellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * Sets the nombre apellido.
	 *
	 * @param nombreApellido
	 *            the new nombre apellido
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * Gets the tipo cuil.
	 *
	 * @return the tipo cuil
	 */
	public String getTipoCuil() {
		return tipoCuil;
	}

	/**
	 * Sets the tipo cuil.
	 *
	 * @param tipoCuil
	 *            the new tipo cuil
	 */
	public void setTipoCuil(String tipoCuil) {
		this.tipoCuil = tipoCuil;
	}

	/**
	 * Gets the numero cuil.
	 *
	 * @return the numero cuil
	 */
	public String getNumeroCuil() {
		return numeroCuil;
	}

	/**
	 * Sets the numero cuil.
	 *
	 * @param numeroCuil
	 *            the new numero cuil
	 */
	public void setNumeroCuil(String numeroCuil) {
		this.numeroCuil = numeroCuil;
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
	 * Gets the cantidad productos.
	 *
	 * @return the cantidad productos
	 */
	public Long getCantidadProductos() {
		return cantidadProductos;
	}

	/**
	 * Sets the cantidad productos.
	 *
	 * @param cantidadProductos
	 *            the new cantidad productos
	 */
	public void setCantidadProductos(Long cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	/**
	 * Gets the ultima secuencia domicilio.
	 *
	 * @return the ultima secuencia domicilio
	 */
	public String getUltimaSecuenciaDomicilio() {
		if (!listaDomicilios.isEmpty()) {
			return getListaDomicilios().get(listaDomicilios.size()-1).getSecuenciaDomicilio();
		} else {
			return "000";
		}
	}

	/**
	 * Sets the ultima secuencia domicilio.
	 *
	 * @param ultimaSecuenciaDomicilio
	 *            the new ultima secuencia domicilio
	 */
	public void setUltimaSecuenciaDomicilio(String ultimaSecuenciaDomicilio) {
		this.ultimaSecuenciaDomicilio = ultimaSecuenciaDomicilio;
	}

}
