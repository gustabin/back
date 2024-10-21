/**
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.List;

/**
 * The class ContratosPerfil.
 */
public class ContratosPerfil {

	/** The contratosServicios. */
	private List<ContratoPerfil> contratosServicios;

	/** The contratosProductos. */
	private List<ContratoPerfil> contratosProductos;

	/** The contratosComunicacionesBcra. */
	private List<ContratoPerfil> contratosComunicacionesBcra;
	
	private Boolean sinProductos = Boolean.FALSE;

	/**
	 * Gets the contratos servicios.
	 *
	 * @return the contratosServicios
	 */
	public List<ContratoPerfil> getContratosServicios() {
		return contratosServicios;
	}

	/**
	 * Gets the contratos productos.
	 *
	 * @return the contratosProductos
	 */
	public List<ContratoPerfil> getContratosProductos() {
		return contratosProductos;
	}

	/**
	 * Gets the contratos comunicaciones bcra.
	 *
	 * @return the contratosComunicacionesBcra
	 */
	public List<ContratoPerfil> getContratosComunicacionesBcra() {
		return contratosComunicacionesBcra;
	}

	/**
	 * Sets the contratos servicios.
	 *
	 * @param contratosServicios
	 *            the contratosServicios to set
	 */
	public void setContratosServicios(List<ContratoPerfil> contratosServicios) {
		this.contratosServicios = contratosServicios;
	}

	/**
	 * Sets the contratos productos.
	 *
	 * @param contratosProductos
	 *            the contratosProductos to set
	 */
	public void setContratosProductos(List<ContratoPerfil> contratosProductos) {
		this.contratosProductos = contratosProductos;
	}

	/**
	 * Sets the contratos comunicaciones bcra.
	 *
	 * @param contratosComunicacionesBcra
	 *            the contratosComunicacionesBcra to set
	 */
	public void setContratosComunicacionesBcra(List<ContratoPerfil> contratosComunicacionesBcra) {
		this.contratosComunicacionesBcra = contratosComunicacionesBcra;
	}

	public Boolean getSinProductos() {
		return sinProductos;
	}

	public void setSinProductos(Boolean sinProductos) {
		this.sinProductos = sinProductos;
	}
		
}
