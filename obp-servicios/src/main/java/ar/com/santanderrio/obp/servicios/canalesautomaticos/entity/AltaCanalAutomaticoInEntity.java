/*
 * 
 */
package ar.com.santanderrio.obp.servicios.canalesautomaticos.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AltaCanalAutomaticoInEntity.
 *
 * @author alejandro_leal
 */
public class AltaCanalAutomaticoInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The tipo documento TIT. */
	/* inicio obligatorios */
	private String tipoDocumentoTIT;

	/** The num documento TIT. */
	private String numDocumentoTIT;

	/** The apellido. */
	private String apellido;

	/** The segundo apellido. */
	private String segundoApellido;

	/** The nombre. */
	private String nombre;

	/** The estado civil. */
	private String estadoCivil;

	/** The sexo. */
	private String sexo;

	/** The fec nacimiento. */
	private String fecNacimiento;

	/** The pais nacimiento. */
	private String paisNacimiento;

	/** The titular pais residencia. */
	private String titularPaisResidencia;

	/** The nacionalidad. */
	private String nacionalidad;

	/** The dom part calle. */
	private String domPartCalle;

	/** The dom part nro. */
	private String domPartNro;
	/* fin obligatorios */

	/** The dom part CP patente. */
	/* inicio obligatorios */
	private String domPartCPPatente;

	/** The dom part CP cod postal. */
	private String domPartCPCodPostal;

	/** The dom part CP manzana. */
	private String domPartCPManzana;

	/** The dom part localidad. */
	private String domPartLocalidad;

	/** The dom part provincia. */
	private String domPartProvincia;

	/** The tipo inscripcion cuit cuil. */
	private String tipoInscripcionCuitCuil;

	/** The nro inscripcion. */
	private String nroInscripcion;

	/** The banca. */
	private String banca;

	/** The division. */
	private String division;

	/** The team leader. */
	private String teamLeader;

	/** The ejecutivo cta. */
	private String ejecutivoCta;

	/** The jefe area. */
	private String jefeArea;

	/** The gerente. */
	private String gerente;

	/** The telefono 1 DDN. */
	private String telefono1DDN;

	/** The telefono 1 caracteristica. */
	private String telefono1Caracteristica;

	/** The telefono 1 numero. */
	private String telefono1Numero;

	/** The sucursal. */
	private String sucursal;

	/** The domCodPais. */
	private String domCodPais;

	/**
	 * Gets the tipo documento TIT.
	 *
	 * @return the tipoDocumentoTIT
	 */
	public String getTipoDocumentoTIT() {
		return tipoDocumentoTIT;
	}

	/**
	 * Sets the tipo documento TIT.
	 *
	 * @param tipoDocumentoTIT
	 *            the tipoDocumentoTIT to set
	 */
	public void setTipoDocumentoTIT(String tipoDocumentoTIT) {
		this.tipoDocumentoTIT = tipoDocumentoTIT;
	}

	/**
	 * Gets the num documento TIT.
	 *
	 * @return the numDocumentoTIT
	 */
	public String getNumDocumentoTIT() {
		return numDocumentoTIT;
	}

	/**
	 * Sets the num documento TIT.
	 *
	 * @param numDocumentoTIT
	 *            the numDocumentoTIT to set
	 */
	public void setNumDocumentoTIT(String numDocumentoTIT) {
		this.numDocumentoTIT = numDocumentoTIT;
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
	 * Gets the segundo apellido.
	 *
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * Sets the segundo apellido.
	 *
	 * @param segundoApellido
	 *            the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
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
	 * Gets the estado civil.
	 *
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Sets the estado civil.
	 *
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
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
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the fec nacimiento.
	 *
	 * @return the fecNacimiento
	 */
	public String getFecNacimiento() {
		return fecNacimiento;
	}

	/**
	 * Sets the fec nacimiento.
	 *
	 * @param fecNacimiento
	 *            the fecNacimiento to set
	 */
	public void setFecNacimiento(String fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	/**
	 * Gets the pais nacimiento.
	 *
	 * @return the paisNacimiento
	 */
	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	/**
	 * Sets the pais nacimiento.
	 *
	 * @param paisNacimiento
	 *            the paisNacimiento to set
	 */
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	/**
	 * Gets the titular pais residencia.
	 *
	 * @return the titularPaisResidencia
	 */
	public String getTitularPaisResidencia() {
		return titularPaisResidencia;
	}

	/**
	 * Sets the titular pais residencia.
	 *
	 * @param titularPaisResidencia
	 *            the titularPaisResidencia to set
	 */
	public void setTitularPaisResidencia(String titularPaisResidencia) {
		this.titularPaisResidencia = titularPaisResidencia;
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
	 *            the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * Gets the dom part calle.
	 *
	 * @return the domPartCalle
	 */
	public String getDomPartCalle() {
		return domPartCalle;
	}

	/**
	 * Sets the dom part calle.
	 *
	 * @param domPartCalle
	 *            the domPartCalle to set
	 */
	public void setDomPartCalle(String domPartCalle) {
		this.domPartCalle = domPartCalle;
	}

	/**
	 * Gets the dom part nro.
	 *
	 * @return the domPartNro
	 */
	public String getDomPartNro() {
		return domPartNro;
	}

	/**
	 * Sets the dom part nro.
	 *
	 * @param domPartNro
	 *            the domPartNro to set
	 */
	public void setDomPartNro(String domPartNro) {
		this.domPartNro = domPartNro;
	}

	/**
	 * Gets the dom part CP patente.
	 *
	 * @return the domPartCPPatente
	 */
	public String getDomPartCPPatente() {
		return domPartCPPatente;
	}

	/**
	 * Sets the dom part CP patente.
	 *
	 * @param domPartCPPatente
	 *            the domPartCPPatente to set
	 */
	public void setDomPartCPPatente(String domPartCPPatente) {
		this.domPartCPPatente = domPartCPPatente;
	}

	/**
	 * Gets the dom part CP cod postal.
	 *
	 * @return the domPartCPCodPostal
	 */
	public String getDomPartCPCodPostal() {
		return domPartCPCodPostal;
	}

	/**
	 * Sets the dom part CP cod postal.
	 *
	 * @param domPartCPCodPostal
	 *            the domPartCPCodPostal to set
	 */
	public void setDomPartCPCodPostal(String domPartCPCodPostal) {
		this.domPartCPCodPostal = domPartCPCodPostal;
	}

	/**
	 * Gets the dom part CP manzana.
	 *
	 * @return the domPartCPManzana
	 */
	public String getDomPartCPManzana() {
		return domPartCPManzana;
	}

	/**
	 * Sets the dom part CP manzana.
	 *
	 * @param domPartCPManzana
	 *            the domPartCPManzana to set
	 */
	public void setDomPartCPManzana(String domPartCPManzana) {
		this.domPartCPManzana = domPartCPManzana;
	}

	/**
	 * Gets the dom part localidad.
	 *
	 * @return the domPartLocalidad
	 */
	public String getDomPartLocalidad() {
		return domPartLocalidad;
	}

	/**
	 * Sets the dom part localidad.
	 *
	 * @param domPartLocalidad
	 *            the domPartLocalidad to set
	 */
	public void setDomPartLocalidad(String domPartLocalidad) {
		this.domPartLocalidad = domPartLocalidad;
	}

	/**
	 * Gets the dom part provincia.
	 *
	 * @return the domPartProvincia
	 */
	public String getDomPartProvincia() {
		return domPartProvincia;
	}

	/**
	 * Sets the dom part provincia.
	 *
	 * @param domPartProvincia
	 *            the domPartProvincia to set
	 */
	public void setDomPartProvincia(String domPartProvincia) {
		this.domPartProvincia = domPartProvincia;
	}

	/**
	 * Gets the tipo inscripcion cuit cuil.
	 *
	 * @return the tipoInscripciónCuitCuil
	 */
	public String getTipoInscripcionCuitCuil() {
		return tipoInscripcionCuitCuil;
	}

	/**
	 * Sets the tipo inscripcion cuit cuil.
	 *
	 * @param tipoInscripcionCuitCuil
	 *            the tipoInscripciónCuitCuil to set
	 */
	public void setTipoInscripcionCuitCuil(String tipoInscripcionCuitCuil) {
		this.tipoInscripcionCuitCuil = tipoInscripcionCuitCuil;
	}

	/**
	 * Gets the nro inscripcion.
	 *
	 * @return the nroInscripción
	 */
	public String getNroInscripcion() {
		return nroInscripcion;
	}

	/**
	 * Sets the nro inscripcion.
	 *
	 * @param nroInscripcion
	 *            the nroInscripción to set
	 */
	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

	/**
	 * Gets the banca.
	 *
	 * @return the banca
	 */
	public String getBanca() {
		return banca;
	}

	/**
	 * Sets the banca.
	 *
	 * @param banca
	 *            the banca to set
	 */
	public void setBanca(String banca) {
		this.banca = banca;
	}

	/**
	 * Gets the division.
	 *
	 * @return the división
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * Sets the division.
	 *
	 * @param division
	 *            the división to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * Gets the team leader.
	 *
	 * @return the teamLeader
	 */
	public String getTeamLeader() {
		return teamLeader;
	}

	/**
	 * Sets the team leader.
	 *
	 * @param teamLeader
	 *            the teamLeader to set
	 */
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	/**
	 * Gets the ejecutivo cta.
	 *
	 * @return the ejecutivoCta
	 */
	public String getEjecutivoCta() {
		return ejecutivoCta;
	}

	/**
	 * Sets the ejecutivo cta.
	 *
	 * @param ejecutivoCta
	 *            the ejecutivoCta to set
	 */
	public void setEjecutivoCta(String ejecutivoCta) {
		this.ejecutivoCta = ejecutivoCta;
	}

	/**
	 * Gets the jefe area.
	 *
	 * @return the jefeArea
	 */
	public String getJefeArea() {
		return jefeArea;
	}

	/**
	 * Sets the jefe area.
	 *
	 * @param jefeArea
	 *            the jefeArea to set
	 */
	public void setJefeArea(String jefeArea) {
		this.jefeArea = jefeArea;
	}

	/**
	 * Gets the gerente.
	 *
	 * @return the gerente
	 */
	public String getGerente() {
		return gerente;
	}

	/**
	 * Sets the gerente.
	 *
	 * @param gerente
	 *            the gerente to set
	 */
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	/**
	 * Gets the telefono 1 DDN.
	 *
	 * @return the teléfono1DDN
	 */
	public String getTelefono1DDN() {
		return telefono1DDN;
	}

	/**
	 * Sets the telefono 1 DDN.
	 *
	 * @param telefono1ddn
	 *            the teléfono1DDN to set
	 */
	public void setTelefono1DDN(String telefono1ddn) {
		telefono1DDN = telefono1ddn;
	}

	/**
	 * Gets the telefono 1 caracteristica.
	 *
	 * @return the teléfono1Característica
	 */
	public String getTelefono1Caracteristica() {
		return telefono1Caracteristica;
	}

	/**
	 * Sets the telefono 1 caracteristica.
	 *
	 * @param telefono1Caracteristica
	 *            the teléfono1Característica to set
	 */
	public void setTelefono1Caracteristica(String telefono1Caracteristica) {
		this.telefono1Caracteristica = telefono1Caracteristica;
	}

	/**
	 * Gets the telefono 1 numero.
	 *
	 * @return the teléfono1Numero
	 */
	public String getTelefono1Numero() {
		return telefono1Numero;
	}

	/**
	 * Sets the telefono 1 numero.
	 *
	 * @param telefono1Numero
	 *            the teléfono1Numero to set
	 */
	public void setTelefono1Numero(String telefono1Numero) {
		this.telefono1Numero = telefono1Numero;
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
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the dom cod pais.
	 *
	 * @return the dom cod pais
	 */
	public String getDomCodPais() {
		return domCodPais;
	}

	/**
	 * Sets the dom cod pais.
	 *
	 * @param domCodPais
	 *            the new dom cod pais
	 */
	public void setDomCodPais(String domCodPais) {
		this.domCodPais = domCodPais;
	}
}