/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.util.List;

/**
 * The Class ComprobanteComexDTO.
 *
 * @author B040619
 */
public class ComprobanteComexDTO {

	/** The nombre. */
	private String nombre;

	/** The nombreEmisor. */
	private String nombreEmisor;

	/** The importe. */
	private String importe;

	/** The motivo. */
	private String motivo;

	/** The idConcepto. */
	private String idConcepto;

	/** The vinculo. */
	private String vinculo;

	/** The cuentaOrigen. */
	private String cuentaOrigen;

	/** The descripcionCuentaOrigen. */
	private String descripcionCuentaOrigen;

	/** The cuentaDestino. */
	private String cuentaDestino;

	/** The codigoBancario. */
	private String codigoBancario;

	/** The idGastosACargo. */
	private String idGastosACargo;

	/** The gastosACargo. */
	private String gastosACargo;

	/** The bancoIntermediario. */
	private String bancoIntermediario;

	/** The codigoIntermediario. */
	private String codigoIntermediario;

	/** The numeroComprobante. */
	private String numeroComprobante;

	/** The domicilio. */
	private String domicilio;

	/** The pais. */
	private String pais;

	/** The nombreDocumentacion. */
	private String nombreDocumentacion;

	/** The legales. */
	private String legales;

	/** The fechaHora. */
	private String fechaHora;
	
	/** The codigo concepto. */
	private String conceptoCodigo;

	/** The codigo concepto. */
	private String moneda;
	
	private List<String> archivos;
	
	private Boolean vinculante;
	
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
	 * Gets the nombre emisor.
	 *
	 * @return the nombreEmisor
	 */
	public String getNombreEmisor() {
		return nombreEmisor;
	}

	/**
	 * Sets the nombre emisor.
	 *
	 * @param nombreEmisor
	 *            the nombreEmisor to set
	 */
	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	public List<String> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<String> archivos) {
		this.archivos = archivos;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Gets the id concepto.
	 *
	 * @return the idConcepto
	 */
	public String getIdConcepto() {
		return idConcepto;
	}

	/**
	 * Sets the id concepto.
	 *
	 * @param idConcepto
	 *            the idConcepto to set
	 */
	public void setIdConcepto(String idConcepto) {
		this.idConcepto = idConcepto;
	}

	/**
	 * Gets the vinculo.
	 *
	 * @return the vinculo
	 */
	public String getVinculo() {
		return vinculo;
	}

	/**
	 * Sets the vinculo.
	 *
	 * @param vinculo
	 *            the vinculo to set
	 */
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the descripcion cuenta origen.
	 *
	 * @return the descripcionCuentaOrigen
	 */
	public String getDescripcionCuentaOrigen() {
		return descripcionCuentaOrigen;
	}

	/**
	 * Sets the descripcion cuenta origen.
	 *
	 * @param descripcionCuentaOrigen
	 *            the descripcionCuentaOrigen to set
	 */
	public void setDescripcionCuentaOrigen(String descripcionCuentaOrigen) {
		this.descripcionCuentaOrigen = descripcionCuentaOrigen;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuentaDestino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the cuentaDestino to set
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the codigo bancario.
	 *
	 * @return the codigoBancario
	 */
	public String getCodigoBancario() {
		return codigoBancario;
	}

	/**
	 * Sets the codigo bancario.
	 *
	 * @param codigoBancario
	 *            the codigoBancario to set
	 */
	public void setCodigoBancario(String codigoBancario) {
		this.codigoBancario = codigoBancario;
	}

	/**
	 * Gets the id gastos A cargo.
	 *
	 * @return the idGastosACargo
	 */
	public String getIdGastosACargo() {
		return idGastosACargo;
	}

	/**
	 * Sets the id gastos A cargo.
	 *
	 * @param idGastosACargo
	 *            the idGastosACargo to set
	 */
	public void setIdGastosACargo(String idGastosACargo) {
		this.idGastosACargo = idGastosACargo;
	}

	/**
	 * Gets the gastos A cargo.
	 *
	 * @return the gastosACargo
	 */
	public String getGastosACargo() {
		return gastosACargo;
	}

	/**
	 * Sets the gastos A cargo.
	 *
	 * @param gastosACargo
	 *            the gastosACargo to set
	 */
	public void setGastosACargo(String gastosACargo) {
		this.gastosACargo = gastosACargo;
	}

	/**
	 * Gets the banco intermediario.
	 *
	 * @return the bancoIntermediario
	 */
	public String getBancoIntermediario() {
		return bancoIntermediario;
	}

	/**
	 * Sets the banco intermediario.
	 *
	 * @param bancoIntermediario
	 *            the bancoIntermediario to set
	 */
	public void setBancoIntermediario(String bancoIntermediario) {
		this.bancoIntermediario = bancoIntermediario;
	}

	/**
	 * Gets the codigo intermediario.
	 *
	 * @return the codigoIntermediario
	 */
	public String getCodigoIntermediario() {
		return codigoIntermediario;
	}

	/**
	 * Sets the codigo intermediario.
	 *
	 * @param codigoIntermediario
	 *            the codigoIntermediario to set
	 */
	public void setCodigoIntermediario(String codigoIntermediario) {
		this.codigoIntermediario = codigoIntermediario;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
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
	 *            the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the pais.
	 *
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Gets the nombre documentacion.
	 *
	 * @return the nombreDocumentacion
	 */
	public String getNombreDocumentacion() {
		return nombreDocumentacion;
	}

	/**
	 * Sets the nombre documentacion.
	 *
	 * @param nombreDocumentacion
	 *            the nombreDocumentacion to set
	 */
	public void setNombreDocumentacion(String nombreDocumentacion) {
		this.nombreDocumentacion = nombreDocumentacion;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the conceptoCodigo
	 */
	public String getConceptoCodigo() {
		return conceptoCodigo;
	}

	/**
	 * @param conceptoCodigo the conceptoCodigo to set
	 */
	public void setConceptoCodigo(String conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the vinculante
	 */
	public Boolean getVinculante() {
		return vinculante;
	}

	/**
	 * @param vinculante the vinculante to set
	 */
	public void setVinculante(Boolean vinculante) {
		this.vinculante = vinculante;
	}

}
