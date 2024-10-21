/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dto;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoIdEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoPersonaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;

/**
 * The Class MyaDTOIn.
 */
public class MyaDTOIn {

	/** The nup. */
	private String nup;

	/** The lista destinos. */
	private List<MyaDestino> listaDestinos;

	/** The mya tipo id enum. */
	private MyaTipoIdEnum myaTipoIdEnum;

	/** The dni. */
	private String dni;

	/** The mya tipo persona enum. */
	private MyaTipoPersonaEnum myaTipoPersonaEnum;

	/** The fecha de nacimiento. */
	private String fechaDeNacimiento;

	/** The nombre. */
	private String nombre;

	/** The primer apellido. */
	private String primerApellido;

	/** The segundo apellido. */
	private String segundoApellido;

	/** The tipo documento. */
	private TipoDocumentoEnum tipoDocumento;

	/** The solo principales. */
	private boolean soloPrincipales;

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
	 * Gets the lista destinos.
	 *
	 * @return the lista destinos
	 */
	public List<MyaDestino> getListaDestinos() {
		return listaDestinos;
	}

	/**
	 * Sets the lista destinos.
	 *
	 * @param listaDestinos
	 *            the new lista destinos
	 */
	public void setListaDestinos(List<MyaDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}

	/**
	 * Gets the mya tipo id enum.
	 *
	 * @return the mya tipo id enum
	 */
	public MyaTipoIdEnum getMyaTipoIdEnum() {
		return myaTipoIdEnum;
	}

	/**
	 * Sets the mya tipo id enum.
	 *
	 * @param myaTipoIdEnum
	 *            the new mya tipo id enum
	 */
	public void setMyaTipoIdEnum(MyaTipoIdEnum myaTipoIdEnum) {
		this.myaTipoIdEnum = myaTipoIdEnum;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the fecha de nacimiento.
	 *
	 * @return the fecha de nacimiento
	 */
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Sets the fecha de nacimiento.
	 *
	 * @param fechaDeNacimiento
	 *            the new fecha de nacimiento
	 */
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Gets the segundo apellido.
	 *
	 * @return the segundo apellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * Sets the segundo apellido.
	 *
	 * @param segundoApellido
	 *            the new segundo apellido
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the primer apellido.
	 *
	 * @return the primer apellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * Sets the primer apellido.
	 *
	 * @param primerApellido
	 *            the new primer apellido
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
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
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the mya tipo persona enum.
	 *
	 * @return the tipoPersonaEnum
	 */
	public MyaTipoPersonaEnum getMyaTipoPersonaEnum() {
		return myaTipoPersonaEnum;
	}

	/**
	 * Sets the mya tipo persona enum.
	 *
	 * @param myaTipoPersonaEnum
	 *            the new mya tipo persona enum
	 */
	public void setMyaTipoPersonaEnum(MyaTipoPersonaEnum myaTipoPersonaEnum) {
		this.myaTipoPersonaEnum = myaTipoPersonaEnum;
	}

	/**
	 * Checks if is solo principales.
	 *
	 * @return true, if is solo principales
	 */
	public boolean isSoloPrincipales() {
		return soloPrincipales;
	}

	/**
	 * Sets the solo principales.
	 *
	 * @param soloPrincipales
	 *            the new solo principales
	 */
	public void setSoloPrincipales(boolean soloPrincipales) {
		this.soloPrincipales = soloPrincipales;
	}

}
