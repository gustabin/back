/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.List;

/**
 * The Class DatosSolicitanteViewResponse.
 */
public class DatosSolicitanteViewResponse {

	/** The tarjetas. */
	private List<TarjetaCandidataView> tarjetas;

	/** The documento tipo. */
	private String documentoTipo;

	/** The documento nro. */
	private String documentoNro;

	/** The apellido. */
	private String apellido;

	/** The nombre. */
	private String nombre;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The cuit. */
	private String cuit;

	/** The pais nacimiento view. */
	private List<PaisDeNacimientoView> paisNacimientoView;

	/** The sexo view. */
	private List<SexoView> sexoView;

	/** The estado civil view. */
	private List<EstadoCivilView> estadoCivilView;

	/** The nacionalidad view. */
	private List<NacionalidadView> nacionalidadView;

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaCandidataView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<TarjetaCandidataView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the documento tipo.
	 *
	 * @return the documentoTipo
	 */
	public String getDocumentoTipo() {
		return documentoTipo;
	}

	/**
	 * Sets the documento tipo.
	 *
	 * @param documentoTipo
	 *            the documentoTipo to set
	 */
	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	/**
	 * Gets the documento nro.
	 *
	 * @return the documentoNro
	 */
	public String getDocumentoNro() {
		return documentoNro;
	}

	/**
	 * Sets the documento nro.
	 *
	 * @param documentoNro
	 *            the documentoNro to set
	 */
	public void setDocumentoNro(String documentoNro) {
		this.documentoNro = documentoNro;
	}

	/**
	 * Gets the apellido.
	 *
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the apellido.
	 *
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the pais nacimiento view.
	 *
	 * @return the paisNacimientoView
	 */
	public List<PaisDeNacimientoView> getPaisNacimientoView() {
		return paisNacimientoView;
	}

	/**
	 * Sets the pais nacimiento view.
	 *
	 * @param paisNacimientoView
	 *            the paisNacimientoView to set
	 */
	public void setPaisNacimientoView(List<PaisDeNacimientoView> paisNacimientoView) {
		this.paisNacimientoView = paisNacimientoView;
	}

	/**
	 * Gets the sexo view.
	 *
	 * @return the sexoView
	 */
	public List<SexoView> getSexoView() {
		return sexoView;
	}

	/**
	 * Sets the sexo view.
	 *
	 * @param sexoView
	 *            the sexoView to set
	 */
	public void setSexoView(List<SexoView> sexoView) {
		this.sexoView = sexoView;
	}

	/**
	 * Gets the estado civil view.
	 *
	 * @return the estadoCivilView
	 */
	public List<EstadoCivilView> getEstadoCivilView() {
		return estadoCivilView;
	}

	/**
	 * Sets the estado civil view.
	 *
	 * @param estadoCivilView
	 *            the estadoCivilView to set
	 */
	public void setEstadoCivilView(List<EstadoCivilView> estadoCivilView) {
		this.estadoCivilView = estadoCivilView;
	}

	/**
	 * Gets the nacionalidad view.
	 *
	 * @return the nacionalidadView
	 */
	public List<NacionalidadView> getNacionalidadView() {
		return nacionalidadView;
	}

	/**
	 * Sets the nacionalidad view.
	 *
	 * @param nacionalidadView
	 *            the nacionalidadView to set
	 */
	public void setNacionalidadView(List<NacionalidadView> nacionalidadView) {
		this.nacionalidadView = nacionalidadView;
	}

}
