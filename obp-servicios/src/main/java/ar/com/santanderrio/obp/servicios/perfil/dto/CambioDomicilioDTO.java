/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dto;

import java.util.List;

/**
 * The Class DomicilioDTO.
 * 
 * @author Silvina_Luque
 */
public class CambioDomicilioDTO {

	/** The domicilioId. */
	private String domicilioId;

	/** The secuencia. */
	private String secuenciaDomicilio;

	/** The tipo. */
	private String tipoDomicilio;

	/** The nombre calle. */

	private String calle;
	/** The observ. */
	private String observaciones;

	/** The piso. */
	private String piso;

	/** The departamento. */
	private String departamento;

	/** The sucursal. */
	private String sucursal;

	/** The apt. */
	private String apt;

	/** The desc localidad. */
	private String localidad;

	/** The desc comuna. */
	private String comuna;

	/** The cod postal. */
	private String codigoPostal;

	/** The cod prov. */
	private String provincia;

	/** The cod pais. */
	private String pais;

	/** The marca. */
	private String marcaDomErroneo;

	/** The telefono. */
	private String telefono;

	/** The prefijo. */
	private String prefijo;

	/** The caracteristica. */
	private String caracteristica;

	/** The numero telefono. */
	private String numeroTelefono;

	/** The telefono. */
	private String descProvincia;

	/** The telefono. */
	private String descPais;

	/** The lista productos. */
	private List<ProductoDTO> listaProductos;
	
	/** The domicilio O producto privado. */
	private Boolean domicilioOProductoPrivado = Boolean.FALSE;
	
	/** The domicilio O producto prendario. */
	private Boolean domicilioOProductoPrendario = Boolean.FALSE;

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
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones
	 *            the new observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Gets the piso.
	 *
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * Sets the piso.
	 *
	 * @param piso
	 *            the new piso
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	/**
	 * Gets the departamento.
	 *
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * Sets the departamento.
	 *
	 * @param departamento
	 *            the new departamento
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia
	 *            the new provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the pais.
	 *
	 * @param pais
	 *            the new pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
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
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the desc provincia.
	 *
	 * @return the desc provincia
	 */
	public String getDescProvincia() {
		return descProvincia;
	}

	/**
	 * Sets the desc provincia.
	 *
	 * @param descProvincia
	 *            the new desc provincia
	 */
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	/**
	 * Gets the desc pais.
	 *
	 * @return the desc pais
	 */
	public String getDescPais() {
		return descPais;
	}

	/**
	 * Sets the desc pais.
	 *
	 * @param descPais
	 *            the new desc pais
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	/**
	 * Gets the domicilio id.
	 *
	 * @return the domicilio id
	 */
	public String getDomicilioId() {
		return domicilioId;
	}

	/**
	 * Sets the domicilio id.
	 *
	 * @param domicilioId
	 *            the new domicilio id
	 */
	public void setDomicilioId(String domicilioId) {
		this.domicilioId = domicilioId;
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
	 * Gets the numero telefono.
	 *
	 * @return the numero telefono
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	/**
	 * Sets the numero telefono.
	 *
	 * @param numeroTelefono
	 *            the new numero telefono
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<ProductoDTO> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<ProductoDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Gets the domicilio O producto privado.
	 *
	 * @return the domicilio O producto privado
	 */
	public Boolean getDomicilioOProductoPrivado() {
		return domicilioOProductoPrivado;
	}

	/**
	 * Sets the domicilio O producto privado.
	 *
	 * @param domicilioOProductoPrivado the new domicilio O producto privado
	 */
	public void setDomicilioOProductoPrivado(Boolean domicilioOProductoPrivado) {
		this.domicilioOProductoPrivado = domicilioOProductoPrivado;
	}

	/**
	 * Gets the domicilio O producto prendario.
	 *
	 * @return the domicilio O producto prendario
	 */
	public Boolean getDomicilioOProductoPrendario() {
		return domicilioOProductoPrendario;
	}

	/**
	 * Sets the domicilio O producto prendario.
	 *
	 * @param domicilioOProductoPrendario the new domicilio O producto prendario
	 */
	public void setDomicilioOProductoPrendario(Boolean domicilioOProductoPrendario) {
		this.domicilioOProductoPrendario = domicilioOProductoPrendario;
	}
	
	/**
	 * Checks if is datos no modificables.
	 *
	 * @return true, if is datos no modificables
	 */
	public boolean isDatosNoModificables() {
		return domicilioOProductoPrendario || domicilioOProductoPrivado;
	}
	
	/**
	 * Consultar prioridad.
	 *
	 * @return the integer
	 */
	public Integer consultarPrioridad() {
		if ("PRI".equals(this.tipoDomicilio)) {
			return 0;
		} else if ("LAB".equals(this.tipoDomicilio)) {
			return 1;
		} else if ("ALT".equals(this.tipoDomicilio)) {
			return 2;
		} else if ("BRP".equals(this.tipoDomicilio)) {
			return 3;
		} else {
			return 4;
		}
	} 

}
