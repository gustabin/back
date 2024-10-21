package ar.com.santanderrio.obp.servicios.getnet.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class GetNetInformacionPersonalEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GetnetInformacionPersonalEntity {
	
	/** The nup. */
	@JsonProperty("nup")
	private String nup;
	
	/** The tipo documento. */
	@JsonProperty("document_type")
	private String tipoDocumento;
	
	/** The numero documento. */
	@JsonProperty("document_number")
	private Integer numeroDocumento;
	
	/** The fecha nacimiento. */
	@JsonProperty("birth_date")
	private String fechaNacimiento;
	
	/** The nombre. */
	@JsonProperty("first_name")
	private String nombre;
	
	/** The apellido. */
	@JsonProperty("last_name")
	private String apellido;
	
	/** The genero. */
	@JsonProperty("gender")
	private String genero;
	
	/** The cuit. */
	@JsonProperty("cuit")
	private String cuit;
	
	/** The nacionalidad. */
	@JsonProperty("nationality")
	private String nacionalidad;
	
	/** The fecha incorporacion. */
	@JsonProperty("incorporation_date")
	private String fechaIncorporacion;
	
	/** The expuesto politicamente. */
	@JsonProperty("politically_exposed")
	private Boolean expuestoPoliticamente;
	
	/** The VACIO. */
	private static final String VACIO = "";
	
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
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * Gets the numero documento.
	 *
	 * @return the numeroDocumento
	 */
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento
	 *            the new numero documento
	 */
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * Gets the genero.
	 *
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Sets the genero.
	 *
	 * @param genero
	 *            the new genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
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
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	/**
	 * Gets the nacionalidad.
	 *
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * Sets the nacionalidad.
	 *
	 * @param nacionalidad
	 *            the new nacionalidad
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * Gets the fecha incorporacion.
	 *
	 * @return the fechaIncorporacion
	 */
	public String getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	/**
	 * Sets the fecha incorporacion.
	 *
	 * @param fechaIncorporacion
	 *            the new fecha incorporacion
	 */
	public void setFechaIncorporacion(String fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
	
	/**
	 * Is expuesto politicamente.
	 *
	 * @return the expuestoPoliticamente
	 */
	public Boolean isExpuestoPoliticamente() {
		return expuestoPoliticamente;
	}

	/**
	 * Sets the expuesto politicamente.
	 *
	 * @param expuestoPoliticamente
	 *            the new expuesto politicamente
	 */
	public void setExpuestoPoliticamente(Boolean expuestoPoliticamente) {
		this.expuestoPoliticamente = expuestoPoliticamente;
	}
	
	/**
	 * 
	 */
	public GetnetInformacionPersonalEntity() {
		this.fechaIncorporacion = VACIO;
	}
	
}
