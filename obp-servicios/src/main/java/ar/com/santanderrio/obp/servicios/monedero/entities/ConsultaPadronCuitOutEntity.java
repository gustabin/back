/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class ConsultaPadronCuitOutEntity.
 *
 * @author alejandro_leal
 */
@Record
public class ConsultaPadronCuitOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** Nro de Cuit BCRA *. */
	@Field
	private String cantidadBcra;

	/** Nro de Cuit BCRA *. */
	@Field
	private String bcraNroCuit;

	/** Denominación en BCRA *. */
	@Field
	private String bcraApellidoNombre1;

	/** Cantidad de ocurrencias encontradas en este padrón *. */
	@Field
	private String cantidadABA;

	/** Nro de CUIT ABA *. */
	@Field
	private String abaNroCuit;

	/** Tipo Documento *. */
	@Field
	private String abaTipoDocumento;

	/** Nro de documento *. */
	@Field
	private String abaNroDocumento;

	/** Nombre *. */
	@Field
	private String abaNombre;

	/** Apellido *. */
	@Field
	private String abaApellido;

	/** Fecha de nacimiento *. */
	@Field
	private String abaFechaNacimiento;

	/** Sexo *. */
	@Field
	private String abaSexo;

	/** Tipo de Persona *. */
	@Field
	private String abaTipoPersona;

	/** The tiene error. */
	@Field
	private boolean tieneError;

	/**
	 * Instantiates a new consulta padron cuit out entity.
	 */
	public ConsultaPadronCuitOutEntity() {
		// vacio
	}

	/**
	 * Instantiates a new consulta padron cuit out entity.
	 *
	 * @param i
	 *            the i
	 * @param tieneError
	 *            the tiene error
	 */
	public ConsultaPadronCuitOutEntity(String i, boolean tieneError) {
		this.codigoRetornoExtendido = i;
		this.tieneError = tieneError;
	}

	/**
	 * Checks if is tiene error.
	 *
	 * @return true, if is tiene error
	 */
	public boolean isTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(boolean tieneError) {
		this.tieneError = tieneError;
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the bcra nro cuit.
	 *
	 * @return the bcra nro cuit
	 */
	public String getBcraNroCuit() {
		return bcraNroCuit;
	}

	/**
	 * Sets the bcra nro cuit.
	 *
	 * @param bcraNroCuit
	 *            the new bcra nro cuit
	 */
	public void setBcraNroCuit(String bcraNroCuit) {
		this.bcraNroCuit = bcraNroCuit;
	}

	/**
	 * Gets the bcra apellido nombre 1.
	 *
	 * @return the bcra apellido nombre 1
	 */
	public String getBcraApellidoNombre1() {
		return bcraApellidoNombre1;
	}

	/**
	 * Sets the bcra apellido nombre 1.
	 *
	 * @param bcraApellidoNombre1
	 *            the new bcra apellido nombre 1
	 */
	public void setBcraApellidoNombre1(String bcraApellidoNombre1) {
		this.bcraApellidoNombre1 = bcraApellidoNombre1;
	}

	/**
	 * Gets the cantidad ABA.
	 *
	 * @return the cantidad ABA
	 */
	public String getCantidadABA() {
		return cantidadABA;
	}

	/**
	 * Sets the cantidad ABA.
	 *
	 * @param cantidadABA
	 *            the new cantidad ABA
	 */
	public void setCantidadABA(String cantidadABA) {
		this.cantidadABA = cantidadABA;
	}

	/**
	 * Gets the aba nro cuit.
	 *
	 * @return the aba nro cuit
	 */
	public String getAbaNroCuit() {
		return abaNroCuit;
	}

	/**
	 * Sets the aba nro cuit.
	 *
	 * @param abaNroCuit
	 *            the new aba nro cuit
	 */
	public void setAbaNroCuit(String abaNroCuit) {
		this.abaNroCuit = abaNroCuit;
	}

	/**
	 * Gets the aba tipo documento.
	 *
	 * @return the aba tipo documento
	 */
	public String getAbaTipoDocumento() {
		return abaTipoDocumento;
	}

	/**
	 * Sets the aba tipo documento.
	 *
	 * @param abaTipoDocumento
	 *            the new aba tipo documento
	 */
	public void setAbaTipoDocumento(String abaTipoDocumento) {
		this.abaTipoDocumento = abaTipoDocumento;
	}

	/**
	 * Gets the aba nro documento.
	 *
	 * @return the aba nro documento
	 */
	public String getAbaNroDocumento() {
		return abaNroDocumento;
	}

	/**
	 * Sets the aba nro documento.
	 *
	 * @param abaNroDocumento
	 *            the new aba nro documento
	 */
	public void setAbaNroDocumento(String abaNroDocumento) {
		this.abaNroDocumento = abaNroDocumento;
	}

	/**
	 * Gets the aba nombre.
	 *
	 * @return the aba nombre
	 */
	public String getAbaNombre() {
		return abaNombre;
	}

	/**
	 * Sets the aba nombre.
	 *
	 * @param abaNombre
	 *            the new aba nombre
	 */
	public void setAbaNombre(String abaNombre) {
		this.abaNombre = abaNombre;
	}

	/**
	 * Gets the aba apellido.
	 *
	 * @return the aba apellido
	 */
	public String getAbaApellido() {
		return abaApellido;
	}

	/**
	 * Sets the aba apellido.
	 *
	 * @param abaApellido
	 *            the new aba apellido
	 */
	public void setAbaApellido(String abaApellido) {
		this.abaApellido = abaApellido;
	}

	/**
	 * Gets the aba fecha nacimiento.
	 *
	 * @return the aba fecha nacimiento
	 */
	public String getAbaFechaNacimiento() {
		return abaFechaNacimiento;
	}

	/**
	 * Sets the aba fecha nacimiento.
	 *
	 * @param abaFechaNacimiento
	 *            the new aba fecha nacimiento
	 */
	public void setAbaFechaNacimiento(String abaFechaNacimiento) {
		this.abaFechaNacimiento = abaFechaNacimiento;
	}

	/**
	 * Gets the aba sexo.
	 *
	 * @return the aba sexo
	 */
	public String getAbaSexo() {
		return abaSexo;
	}

	/**
	 * Sets the aba sexo.
	 *
	 * @param abaSexo
	 *            the new aba sexo
	 */
	public void setAbaSexo(String abaSexo) {
		this.abaSexo = abaSexo;
	}

	/**
	 * Gets the aba tipo persona.
	 *
	 * @return the aba tipo persona
	 */
	public String getAbaTipoPersona() {
		return abaTipoPersona;
	}

	/**
	 * Sets the aba tipo persona.
	 *
	 * @param abaTipoPersona
	 *            the new aba tipo persona
	 */
	public void setAbaTipoPersona(String abaTipoPersona) {
		this.abaTipoPersona = abaTipoPersona;
	}

	/**
	 * Gets the cantidad bcra.
	 *
	 * @return the cantidad bcra
	 */
	public String getCantidadBcra() {
		return cantidadBcra;
	}

	/**
	 * Sets the cantidad bcra.
	 *
	 * @param cantidadBcra
	 *            the new cantidad bcra
	 */
	public void setCantidadBcra(String cantidadBcra) {
		this.cantidadBcra = cantidadBcra;
	}
}
