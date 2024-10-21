/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DatosPersonales.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosPersonalesView {
	
	/** The nombre. */
	private String nombre;
	
	/** The primerApellido. */
	private String primerApellido;
	
	/** The segundoApellido. */
	private String segundoApellido;
	
	/** The cuit. */
	private String cuit;
	
	/** The domicilio. */
	private DomicilioView domicilio;
	
	/** The prefijoTelefono. */
	private String prefijoTelefono;

	/** The telefono. */
	private String telefono;
	
	/** The mailPrimario. */
	private String mailPrimario;

	/** The mailSecundario. */
	private String mailSecundario;
	
	/** The prefijoCelular. */
	private String prefijoCelular;
	
	/** The celular. */
	private String celular;
	
	

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
	 * Gets the primer apellido.
	 *
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * Sets the primer apellido.
	 *
	 * @param primerApellido
	 *            the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
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
	 * Gets the prefijo telefono.
	 *
	 * @return the prefijoTelefono
	 */
	public String getPrefijoTelefono() {
		return prefijoTelefono;
	}

	/**
	 * Sets the prefijo telefono.
	 *
	 * @param prefijoTelefono
	 *            the prefijoTelefono to set
	 */
	public void setPrefijoTelefono(String prefijoTelefono) {
		this.prefijoTelefono = prefijoTelefono;
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
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the domicilio.
	 *
	 * @return the domicilio
	 */
	public DomicilioView getDomicilio() {
		return domicilio;
	}

	/**
	 * Sets the domicilio.
	 *
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(DomicilioView domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Gets the mail primario.
	 *
	 * @return the mailPrimario
	 */
	public String getMailPrimario() {
		return mailPrimario;
	}

	/**
	 * Sets the mail primario.
	 *
	 * @param mailPrimario
	 *            the mailPrimario to set
	 */
	public void setMailPrimario(String mailPrimario) {
		this.mailPrimario = mailPrimario;
	}

	/**
	 * Gets the mail secundario.
	 *
	 * @return the mailSecundario
	 */
	public String getMailSecundario() {
		return mailSecundario;
	}

	/**
	 * Sets the mail secundario.
	 *
	 * @param mailSecundario
	 *            the mailSecundario to set
	 */
	public void setMailSecundario(String mailSecundario) {
		this.mailSecundario = mailSecundario;
	}

	/**
	 * Gets the prefijo celular.
	 *
	 * @return the prefijoCelular
	 */
	public String getPrefijoCelular() {
		return prefijoCelular;
	}
	
	/**
	 * Sets the prefijo celular.
	 *
	 * @param prefijoCelular
	 *            the prefijoCelular to set
	 */
	public void setPrefijoCelular(String prefijoCelular) {
		this.prefijoCelular = prefijoCelular;
	}
	
	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

}