/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.entities;

/**
 * The Class DatosSolicitanteEntity.
 */
public class DatosSolicitanteEntity {

	/** The nup. */
	private String nup;

	/** The apellido. */
	private String apellido;

	/** The nombre. */
	private String nombre;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The doc tipo. */
	private String docTipo;

	/** The doc. */
	private String doc;

	/** The pais nacimiento. */
	private String paisNacimiento;

	/** The sexo. */
	private String sexo;

	/** The estado civil. */
	private String estadoCivil;

	/** The nacionalidad. */
	private String nacionalidad;

	/** The calle. */
	private String calle;

	/** The calle nro. */
	private String calleNro;

	/** The piso. */
	private String piso;

	/** The depto. */
	private String depto;

	/** The localidad. */
	private String localidad;

	/** The cp. */
	private String cp;

	/** The cp manzana. */
	private String cpManzana;

	/** The cp patente. */
	private String cpPatente;

	/** The provincia. */
	private String provincia;

	/** The telefono. */
	private String telefono;

	/** The acepto terminos condiciones. */
	private boolean aceptoTerminosCondiciones;

	/** The cod error. */
	private Integer codError;

	/** The tiene error. */
	private Boolean tieneError = Boolean.FALSE;

	/** The codigo sujeto. */
	private String codigoSujeto;
	
	/** The codigo pais. */
	private String codigoPais;
	
	/**
	 * Instantiates a new datos solicitante entity.
	 */
	public DatosSolicitanteEntity() {
		super();
		// vacio
	}

	/**
	 * Instantiates a new datos solicitante entity.
	 *
	 * @param codError
	 *            the cod error
	 * @param tieneError
	 *            the tiene error
	 */
	public DatosSolicitanteEntity(Integer codError, Boolean tieneError) {
		super();
		this.codError = codError;
		this.tieneError = tieneError;
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
	 * Gets the doc tipo.
	 *
	 * @return the doc tipo
	 */
	public String getDocTipo() {
		return docTipo;
	}

	/**
	 * Sets the doc tipo.
	 *
	 * @param docTipo
	 *            the new doc tipo
	 */
	public void setDocTipo(String docTipo) {
		this.docTipo = docTipo;
	}

	/**
	 * Gets the doc.
	 *
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * Sets the doc.
	 *
	 * @param doc
	 *            the new doc
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}

	/**
	 * Gets the pais nacimiento.
	 *
	 * @return the pais nacimiento
	 */
	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	/**
	 * Sets the pais nacimiento.
	 *
	 * @param paisNacimiento
	 *            the new pais nacimiento
	 */
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	/**
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo
	 *            the new sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the estado civil.
	 *
	 * @return the estado civil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Sets the estado civil.
	 *
	 * @param estadoCivil
	 *            the new estado civil
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
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
	 * Gets the calle nro.
	 *
	 * @return the calle nro
	 */
	public String getCalleNro() {
		return calleNro;
	}

	/**
	 * Sets the calle nro.
	 *
	 * @param calleNro
	 *            the new calle nro
	 */
	public void setCalleNro(String calleNro) {
		this.calleNro = calleNro;
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
	 * Gets the depto.
	 *
	 * @return the depto
	 */
	public String getDepto() {
		return depto;
	}

	/**
	 * Sets the depto.
	 *
	 * @param depto
	 *            the new depto
	 */
	public void setDepto(String depto) {
		this.depto = depto;
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
	 * Gets the cp.
	 *
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * Sets the cp.
	 *
	 * @param cp
	 *            the new cp
	 */
	public void setCp(String cp) {
		this.cp = cp;
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
	 * Checks if is acepto terminos condiciones.
	 *
	 * @return true, if is acepto terminos condiciones
	 */
	public boolean isAceptoTerminosCondiciones() {
		return aceptoTerminosCondiciones;
	}

	/**
	 * Sets the acepto terminos condiciones.
	 *
	 * @param aceptoTerminosCondiciones
	 *            the new acepto terminos condiciones
	 */
	public void setAceptoTerminosCondiciones(boolean aceptoTerminosCondiciones) {
		this.aceptoTerminosCondiciones = aceptoTerminosCondiciones;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * Gets the tiene error.
	 *
	 * @return the tiene error
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

	/**
	 * Gets the cp patente.
	 *
	 * @return the cp patente
	 */
	public String getCpPatente() {
		return cpPatente;
	}

	/**
	 * Sets the cp patente.
	 *
	 * @param cpPatente
	 *            the new cp patente
	 */
	public void setCpPatente(String cpPatente) {
		this.cpPatente = cpPatente;
	}

	/**
	 * Gets the cp manzana.
	 *
	 * @return the cp manzana
	 */
	public String getCpManzana() {
		return cpManzana;
	}

	/**
	 * Sets the cp manzana.
	 *
	 * @param cpManzana
	 *            the new cp manzana
	 */
	public void setCpManzana(String cpManzana) {
		this.cpManzana = cpManzana;
	}

	/**
	 * Gets the codigo sujeto.
	 *
	 * @return the codigo sujeto
	 */
	public String getCodigoSujeto() {
		return codigoSujeto;
	}

	/**
	 * Sets the codigo sujeto.
	 *
	 * @param codigoSujeto
	 *            the new codigo sujeto
	 */
	public void setCodigoSujeto(String codigoSujeto) {
		this.codigoSujeto = codigoSujeto;
	}
	
	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigo pais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais
	 *            the new codigo pais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
}
