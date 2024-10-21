/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConcepto;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDocumentoConcepto;

/**
 * The Class ConceptoPorTipoOutDTO.
 */
public class ConceptoOutDTO {
	
	/** The id Concepto. */
	private BigDecimal idConcepto;
	
	/** The descripcion Concepto. */
	private String descripcionConcepto;
	
	/** The tipo Concepto. */
	private String tipoConcepto;
	
	/** The ingresa despachos. */
	private String ingresaDespachos;
	
	/** The ingresa nif. */
	private String ingresaNif;
	
	/** The declara impuestos. */
	private String declaraImpuestos;
	
	/** The con fecha embargue. */
	private String conFechaEmbargue;
	
	/** The con fecha emb est. */
	private String conFechaEmbEst;
	
	/** The mostrar com 4834. */
	private String mostrarCom4834;
	
	/** The monto com 4834. */
	private BigDecimal montoCom4834;
	
	/** The mostrar emp vinculada. */
	private String mostrarEmpVinculada;
	
	/** The monto emp vinculada. */
	private BigDecimal montoEmpVinculada;
	
	/** The declaracion jurada. */
	private String declaracionJurada;
	
	/** The ayuda documentacion. */
	private String ayudaDocumentacion;
	
	/** The error interface. */
	private String errorInterface;
	
	/** The error sistema. */
	private String errorSistema;
	
	/** The con 4237. */
	private String con4237;
	
	/** The con 4237. */
	private String conCuitBenef;
	
	/** The con nif gan. */
	private String conNifGan;
	
	/** The con pos aran. */
	private String conPosAran;
	
	/** The declaraciones. */
	private ArrayOfConcepto declaraciones;
	
	/** The declaraciones. */
	private ArrayOfDocumentoConcepto documentos;
	
	/** The edita impo. */
	private String editaImpo;
	
	/** The inv acre. */
	private String invAcre;
	
	/**
	 * Gets the id concepto.
	 *
	 * @return the idConcepto
	 */
	public BigDecimal getIdConcepto() {
		return idConcepto;
	}

	/**
	 * Sets the id concepto.
	 *
	 * @param idConcepto
	 *            the idConcepto to set
	 */
	public void setIdConcepto(BigDecimal idConcepto) {
		this.idConcepto = idConcepto;
	}

	/**
	 * Gets the descripcion concepto.
	 *
	 * @return the descripcion concepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}

	/**
	 * Sets the descripcion concepto.
	 *
	 * @param descripcion concepto
	 *            the descripcion concepto to set
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}
	
	/**
	 * Gets the tipo concepto.
	 *
	 * @return the tipoConcepto
	 */
	public String getTipoConcepto() {
		return tipoConcepto;
	}

	/**
	 * Sets the tipo concepto.
	 *
	 * @param tipoConcepto
	 *            the tipoConcepto to set
	 */
	public void setTipoConcepto(String tipoConcepto) {
		this.tipoConcepto = tipoConcepto;
	}
	
	/**
	 * Gets the ingresa despachos.
	 *
	 * @return the ingresa despachos
	 */
	public String getIngresaDespachos() {
		return ingresaDespachos;
	}

	/**
	 * Sets the ingresa despachos.
	 *
	 * @param ingresa despachos
	 *            the ingresa despachos to set
	 */
	public void setIngresaDespachos(String ingresaDespachos) {
		this.ingresaDespachos = ingresaDespachos;
	}
	
	/**
	 * Gets the ingresa nif.
	 *
	 * @return the ingresa nif
	 */
	public String getIngresaNif() {
		return ingresaNif;
	}

	/**
	 * Sets the ingresa nif.
	 *
	 * @param ingresa nif
	 *            the ingresa nif to set
	 */
	public void setIngresaNif(String ingresaNif) {
		this.ingresaNif = ingresaNif;
	}
	
	/**
	 * Gets the declara impuestos.
	 *
	 * @return the declara impuestos
	 */
	public String getDeclaraImpuestos() {
		return declaraImpuestos;
	}

	/**
	 * Sets the declara impuestos.
	 *
	 * @param declara impuestos
	 *            the declara impuestos to set
	 */
	public void setDeclaraImpuestos(String declaraImpuestos) {
		this.declaraImpuestos = declaraImpuestos;
	}
	
	/**
	 * Gets the con fecha embargue.
	 *
	 * @return the con fecha embargue
	 */
	public String getConFechaEmbargue() {
		return conFechaEmbargue;
	}

	/**
	 * Sets the con fecha embargue.
	 *
	 * @param con fecha embargue
	 *            the con fecha embargue to set
	 */
	public void setConFechaEmbargue(String conFechaEmbargue) {
		this.conFechaEmbargue = conFechaEmbargue;
	}
	
	/**
	 * Gets the con fecha emb est.
	 *
	 * @return the con fecha emb est
	 */
	public String getConFechaEmbEst() {
		return conFechaEmbEst;
	}

	/**
	 * Sets the con fecha emb est.
	 *
	 * @param con fecha emb est
	 *            the con fecha emb est to set
	 */
	public void setConFechaEmbEst(String conFechaEmbEst) {
		this.conFechaEmbEst = conFechaEmbEst;
	}
	
	/**
	 * Gets the mostrar com 4834.
	 *
	 * @return the mostrar com 4834
	 */
	public String getMostrarCom4834() {
		return mostrarCom4834;
	}

	/**
	 * Sets the mostrar com 4834.
	 *
	 * @param mostrar com 4834
	 *            the mostrar com 4834 to set
	 */
	public void setMostrarCom4834(String mostrarCom4834) {
		this.mostrarCom4834 = mostrarCom4834;
	}
	
	/**
	 * Gets the monto com 4834.
	 *
	 * @return the monto com 4834
	 */
	public BigDecimal getMontoCom4834() {
		return montoCom4834;
	}

	/**
	 * Sets the monto com 4834.
	 *
	 * @param monto com 4834
	 *            the monto com 4834 to set
	 */
	public void setMontoCom4834(BigDecimal montoCom4834) {
		this.montoCom4834 = montoCom4834;
	}
	
	/**
	 * Gets the mostrar emp vinculada.
	 *
	 * @return the mostrar emp vinculada
	 */
	public String getMostrarEmpVinculada() {
		return mostrarEmpVinculada;
	}

	/**
	 * Sets the mostrar emp vinculada.
	 *
	 * @param mostrar emp vinculada
	 *            the mostrar emp vinculada to set
	 */
	public void setMostrarEmpVinculada(String mostrarEmpVinculada) {
		this.mostrarEmpVinculada = mostrarEmpVinculada;
	}

	/**
	 * Gets the monton emp vinculada.
	 *
	 * @return the monto emp vinculada
	 */
	public BigDecimal getMontoEmpVinculada() {
		return montoEmpVinculada;
	}

	/**
	 * Sets the monton emp vinculada.
	 *
	 * @param monto emp vinculada
	 *            the monto emp vinculada to set
	 */
	public void setMontoEmpVinculada(BigDecimal montoEmpVinculada) {
		this.montoEmpVinculada = montoEmpVinculada;
	}

	/**
	 * Gets the declaracion jurada.
	 *
	 * @return the declaracion jurada
	 */
	public String getDeclaracionJurada() {
		return declaracionJurada;
	}

	/**
	 * Sets the declaracion jurada
	 *
	 * @param declaracion jurada
	 *            the declaracion jurada to set
	 */
	public void setDeclaracionJurada(String declaracionJurada) {
		this.declaracionJurada = declaracionJurada;
	}

	/**
	 * Gets the ayuda documentacion.
	 *
	 * @return the ayuda documentacion
	 */
	public String getAyudaDocumentacion() {
		return ayudaDocumentacion;
	}

	/**
	 * Sets the ayuda documentacion.
	 *
	 * @param ayuda documentacion
	 *            the ayuda documentacion to set
	 */
	public void setAyudaDocumentacion(String ayudaDocumentacion) {
		this.ayudaDocumentacion = ayudaDocumentacion;
	}

	/**
	 * Gets the error interface.
	 *
	 * @return the error interface
	 */
	public String getErrorInterface() {
		return errorInterface;
	}

	/**
	 * Sets the error interface.
	 *
	 * @param error interface
	 *            the error interface to set
	 */
	public void setErrorInterface(String errorInterface) {
		this.errorInterface = errorInterface;
	}

	/**
	 * Gets the error sistema.
	 *
	 * @return the error sistema
	 */
	public String getErrorSistema() {
		return errorSistema;
	}

	/**
	 * Sets the error sistema.
	 *
	 * @param error sistema
	 *            the error sistema to set
	 */
	public void setErrorSistema(String errorSistema) {
		this.errorSistema = errorSistema;
	}

	/**
	 * Gets the con 4237.
	 *
	 * @return the con 4237
	 */
	public String getCon4237() {
		return con4237;
	}

	/**
	 * Sets the con 4237.
	 *
	 * @param con 4237
	 *            the con 4237 to set
	 */
	public void setCon4237(String con4237) {
		this.con4237 = con4237;
	}

	/**
	 * Gets the con cuit benef.
	 *
	 * @return the con cuit benef
	 */
	public String getConCuitBenef() {
		return conCuitBenef;
	}

	/**
	 * Sets the con cuit benef.
	 *
	 * @param con cuit benef
	 *            the con cuit benef to set
	 */
	public void setConCuitBenef(String conCuitBenef) {
		this.conCuitBenef = conCuitBenef;
	}

	/**
	 * Gets the con nif gan.
	 *
	 * @return the con nif gan
	 */
	public String getConNifGan() {
		return conNifGan;
	}

	/**
	 * Sets the con nif gan.
	 *
	 * @param con nif gan
	 *            the con nif gan to set
	 */
	public void setConNifGan(String conNifGan) {
		this.conNifGan = conNifGan;
	}

	/**
	 * Gets the con pos aran.
	 *
	 * @return the con pos aran
	 */
	public String getConPosAran() {
		return conPosAran;
	}

	/**
	 * Sets the con pos aran.
	 *
	 * @param con pos aran
	 *            the con pos aran to set
	 */
	public void setConPosAran(String conPosAran) {
		this.conPosAran = conPosAran;
	}

	/**
	 * Gets the declaraciones.
	 *
	 * @return the declaraciones
	 */
	public ArrayOfConcepto getDeclaraciones() {
		return declaraciones;
	}

	/**
	 * Sets the declaraciones.
	 *
	 * @param declaraciones
	 *            the declaraciones to set
	 */
	public void setDeclaraciones(ArrayOfConcepto declaraciones) {
		this.declaraciones = declaraciones;
	}

	/**
	 * Gets the documentos.
	 *
	 * @return the documentos
	 */
	public ArrayOfDocumentoConcepto getDocumentos() {
		return documentos;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param documentos
	 *            the documentos to set
	 */
	public void setDocumentos(ArrayOfDocumentoConcepto documentos) {
		this.documentos = documentos;
	}

	/**
	 * Gets the edita impo.
	 *
	 * @return the edita impo
	 */
	public String getEditaImpo() {
		return editaImpo;
	}

	/**
	 * Sets the edita impo.
	 *
	 * @param edita impo
	 *            the edita impo to set
	 */
	public void setEditaImpo(String editaImpo) {
		this.editaImpo = editaImpo;
	}

	/**
	 * Gets the inv acre.
	 *
	 * @return the inv acre
	 */
	public String getInvAcre() {
		return invAcre;
	}

	/**
	 * Sets the inv acre.
	 *
	 * @param inv acre
	 *            the inv acre to set
	 */
	public void setInvAcre(String invAcre) {
		this.invAcre = invAcre;
	}

}