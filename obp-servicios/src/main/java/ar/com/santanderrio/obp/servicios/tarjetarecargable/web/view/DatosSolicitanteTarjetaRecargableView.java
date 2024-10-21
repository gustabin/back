/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.TipoDocumentoDTO;

/**
 * The Class DatosSolicitanteTarjetaRecargableView.
 */
public class DatosSolicitanteTarjetaRecargableView {

	/** The apellido. */
	private String apellido;

	/** The nombre. */
	private String nombre;

	/** The tipo documento. */
	private List<TipoDocumentoDTO> tipoDocumento;

	/** The nro documento. */
	private String nroDocumento;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The nacionalidad. */
	private List<NacionalidadView> nacionalidad;

	/** The sexo. */
	private List<SexoView> sexo;

	/** The estado civil. */
	private List<EstadoCivilView> estadoCivil;

	/** The domicilio. */
	private String domicilio;

	/** The nro. */
	private String nro;

	/** The piso depto. */
	private String pisoDepto;

	/** The localidad barrio. */
	private String localidadBarrio;

	/** The codigo postal. */
	private String codigoPostal;

	/** The provincia. */
	private List<ProvinciaView> provincia;

	/** The cod area. */
	private String codArea;

	/** The telefono. */
	private String telefono;

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
	 *            the new apellido
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
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento
	 *            the new nro documento
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the nacionalidad.
	 *
	 * @return the nacionalidad
	 */
	public List<NacionalidadView> getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * Sets the nacionalidad.
	 *
	 * @param nacionalidad
	 *            the new nacionalidad
	 */
	public void setNacionalidad(List<NacionalidadView> nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public List<SexoView> getSexo() {
		return sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo
	 *            the new sexo
	 */
	public void setSexo(List<SexoView> sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the estado civil.
	 *
	 * @return the estado civil
	 */
	public List<EstadoCivilView> getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Sets the estado civil.
	 *
	 * @param estadoCivil
	 *            the new estado civil
	 */
	public void setEstadoCivil(List<EstadoCivilView> estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * Gets the domicilio.
	 *
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Sets the domicilio.
	 *
	 * @param domicilio
	 *            the new domicilio
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Gets the nro.
	 *
	 * @return the nro
	 */
	public String getNro() {
		return nro;
	}

	/**
	 * Sets the nro.
	 *
	 * @param nro
	 *            the new nro
	 */
	public void setNro(String nro) {
		this.nro = nro;
	}

	/**
	 * Gets the piso depto.
	 *
	 * @return the piso depto
	 */
	public String getPisoDepto() {
		return pisoDepto;
	}

	/**
	 * Sets the piso depto.
	 *
	 * @param pisoDepto
	 *            the new piso depto
	 */
	public void setPisoDepto(String pisoDepto) {
		this.pisoDepto = pisoDepto;
	}

	/**
	 * Gets the localidad barrio.
	 *
	 * @return the localidad barrio
	 */
	public String getLocalidadBarrio() {
		return localidadBarrio;
	}

	/**
	 * Sets the localidad barrio.
	 *
	 * @param localidadBarrio
	 *            the new localidad barrio
	 */
	public void setLocalidadBarrio(String localidadBarrio) {
		this.localidadBarrio = localidadBarrio;
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
	 * Gets the cod area.
	 *
	 * @return the cod area
	 */
	public String getCodArea() {
		return codArea;
	}

	/**
	 * Sets the cod area.
	 *
	 * @param codArea
	 *            the new cod area
	 */
	public void setCodArea(String codArea) {
		this.codArea = codArea;
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
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public List<TipoDocumentoDTO> getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
	public void setTipoDocumento(List<TipoDocumentoDTO> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public List<ProvinciaView> getProvincia() {
		return provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia
	 *            the new provincia
	 */
	public void setProvincia(List<ProvinciaView> provincia) {
		this.provincia = provincia;
	}
}
