/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

import java.util.List;

/**
 * The Class PerfilView.
 */
public class PerfilView {

	/** The nombre cliente. */
	private String nombreCliente;

	/** The ultima conexion. */
	private String ultimaConexion;

	/** The imagen perfil. */
	private String imagenPerfil;

	/** The is select. */
	private Boolean isSelect;

	/** The cantidad sucursales. */
	private String cantidadSucursales;
	
	/** The mostrar sorpresa. */
	private Boolean mostrarSorpresa;

	/** The mostrar sorpresa. */
	private Boolean mostrarAlertaGala;
	
	/** tiene debito flag. */
	private Boolean tieneTarjetaDebito = Boolean.FALSE;
	
	/** mostrar cierre flag. */
	private Boolean mostrarCierreMobile = Boolean.FALSE;
	
	/** iU flag. */
	private Boolean isIU;
	
	private Boolean mostrarBonificaciones = Boolean.FALSE;
	

	/** The cajas. */
	private List<CajaPerfil> cajas;
	
	private Boolean sinProductos =  Boolean.FALSE;
	
	/**
	 * Gets the nombre cliente.
	 *
	 * @return the nombre cliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the new nombre cliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Gets the ultima conexion.
	 *
	 * @return the ultima conexion
	 */
	public String getUltimaConexion() {
		return ultimaConexion;
	}

	/**
	 * Sets the ultima conexion.
	 *
	 * @param ultimaConexion
	 *            the new ultima conexion
	 */
	public void setUltimaConexion(String ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}

	/**
	 * Gets the imagen perfil.
	 *
	 * @return the imagen perfil
	 */
	public String getImagenPerfil() {
		return imagenPerfil;
	}

	/**
	 * Sets the imagen perfil.
	 *
	 * @param imagenPerfil
	 *            the new imagen perfil
	 */
	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	/**
	 * Gets the checks if is select.
	 *
	 * @return the checks if is select
	 */
	public Boolean getIsSelect() {
		return isSelect;
	}

	/**
	 * Sets the checks if is select.
	 *
	 * @param isSelect
	 *            the new checks if is select
	 */
	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 * Gets the cantidad sucursales.
	 *
	 * @return the cantidad sucursales
	 */
	public String getCantidadSucursales() {
		return cantidadSucursales;
	}

	/**
	 * Sets the cantidad sucursales.
	 *
	 * @param cantidadSucursales
	 *            the new cantidad sucursales
	 */
	public void setCantidadSucursales(String cantidadSucursales) {
		this.cantidadSucursales = cantidadSucursales;
	}

	/**
	 * Gets the cajas.
	 *
	 * @return the cajas
	 */
	public List<CajaPerfil> getCajas() {
		return cajas;
	}

	/**
	 * Sets the cajas.
	 *
	 * @param cajas
	 *            the new cajas
	 */
	public void setCajas(List<CajaPerfil> cajas) {
		this.cajas = cajas;
	}

    /**
	 * Gets the mostrar sorpresa.
	 *
	 * @return the mostrar sorpresa
	 */
    public Boolean getMostrarSorpresa() {
        return mostrarSorpresa;
    }

    /**
	 * Sets the mostrar sorpresa.
	 *
	 * @param mostrarSorpresa
	 *            the new mostrar sorpresa
	 */
    public void setMostrarSorpresa(Boolean mostrarSorpresa) {
        this.mostrarSorpresa = mostrarSorpresa;
    }

	/**
	 * Gets the mostrar alerta gala.
	 *
	 * @return the mostrar alerta gala
	 */
	public Boolean getMostrarAlertaGala() {
		return mostrarAlertaGala;
	}

	/**
	 * Sets the mostrar alerta gala.
	 *
	 * @param mostrarAlertaGala the new mostrar alerta gala
	 */
	public void setMostrarAlertaGala(Boolean mostrarAlertaGala) {
		this.mostrarAlertaGala = mostrarAlertaGala;
	}

	public Boolean getSinProductos() {
		return sinProductos;
	}

	public void setSinProductos(Boolean sinProductos) {
		this.sinProductos = sinProductos;
	}

	public Boolean getTieneTarjetaDebito() {
		return tieneTarjetaDebito;
	}

	public void setTieneTarjetaDebito(Boolean tieneTarjetaDebito) {
		this.tieneTarjetaDebito = tieneTarjetaDebito;
	}

	public Boolean getMostrarCierreMobile() {
		return mostrarCierreMobile;
	}

	public void setMostrarCierreMobile(Boolean mostrarCierreMobile) {
		this.mostrarCierreMobile = mostrarCierreMobile;
	}

	public Boolean getIsIU() {
		return isIU;
	}

	public void setIsIU(Boolean isIU) {
		this.isIU = isIU;
	}

	public Boolean getMostrarBonificaciones() {
		return mostrarBonificaciones;
	}

	public void setMostrarBonificaciones(Boolean mostrarBonificaciones) {
		this.mostrarBonificaciones = mostrarBonificaciones;
	}
		
}
