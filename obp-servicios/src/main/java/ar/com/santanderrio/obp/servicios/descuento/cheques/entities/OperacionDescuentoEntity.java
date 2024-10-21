/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.beanio.annotation.Field;

/**
 * The Class OperacionDescuentoEntity.
 */
public class OperacionDescuentoEntity {

	/** The nro tramite. */
	@Field
	private String nroTramite;

	/** The habilita detalle. */
	@Field
	private String habilitaDetalle;
	
	/** The importe A acreditar. */
	@Field
	private String importeAAcreditar;
	
	/** The importe bruto. */
	@Field
	private String importeBruto;
	
	/** The cantidad cheques. */
	@Field
	private String cantidadCheques;
	
	/** The importe bruto rechazado. */
	@Field
	private String importeBrutoRechazado;
	
	/** The cantidad cheques rechazados. */
	@Field
	private String cantidadChequesRechazados;
	
	/** The estado tramite. */
	@Field
	private String estadoTramite;
	
	/** The descripcion estado. */
	@Field
	private String descripcionEstado;
	
	/** The id ultima modificacion. */
	@Field
	private String idUltimaModificacion;
	
	/** The fecha alta. */
	@Field
	private String fechaAlta;
	
	/** The fecha recalculo. */
	@Field
	private String fechaRecalculo;
	
	/** The apellido cliente. */
	@Field
	private String apellidoCliente;
	
	/** The nombre cliente. */
	@Field
	private String nombreCliente;
	
	/** The tipo documento. */
	@Field
	private String tipoDocumento;
	
	/** The nro documento. */
	@Field
	private String nroDocumento;
	
	/** The segmento. */
	@Field
	private String segmento;
	
	/** The fecha ultima modificacion. */
	@Field
	private String fechaUltimaModificacion;
	
	/** The canal. */
	@Field
	private String canal;
	
	/** The marca sell station. */
	@Field
	private String marcaSellStation;
	
	/** The marca gerente. */
	@Field
	private String marcaGerente;
	
	/** The marca productos. */
	@Field
	private String marcaProductos;

	/**
	 * Gets the nro tramite.
	 *
	 * @return the nro tramite
	 */
	public String getNroTramite() {
		return nroTramite;
	}

	/**
	 * Sets the nro tramite.
	 *
	 * @param nroTramite
	 *            the new nro tramite
	 */
	public void setNroTramite(String nroTramite) {
		this.nroTramite = nroTramite;
	}

	/**
	 * Gets the importe A acreditar.
	 *
	 * @return the importe A acreditar
	 */
	public String getImporteAAcreditar() {
		return importeAAcreditar;
	}

	/**
	 * Sets the importe A acreditar.
	 *
	 * @param importeAAcreditar
	 *            the new importe A acreditar
	 */
	public void setImporteAAcreditar(String importeAAcreditar) {
		this.importeAAcreditar = importeAAcreditar;
	}

	/**
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * Gets the cantidad cheques.
	 *
	 * @return the cantidad cheques
	 */
	public String getCantidadCheques() {
		return cantidadCheques;
	}

	/**
	 * Sets the cantidad cheques.
	 *
	 * @param cantidadCheques
	 *            the new cantidad cheques
	 */
	public void setCantidadCheques(String cantidadCheques) {
		this.cantidadCheques = cantidadCheques;
	}

	/**
	 * Gets the importe bruto rechazado.
	 *
	 * @return the importe bruto rechazado
	 */
	public String getImporteBrutoRechazado() {
		return importeBrutoRechazado;
	}

	/**
	 * Sets the importe bruto rechazado.
	 *
	 * @param importeBrutoRechazado
	 *            the new importe bruto rechazado
	 */
	public void setImporteBrutoRechazado(String importeBrutoRechazado) {
		this.importeBrutoRechazado = importeBrutoRechazado;
	}

	/**
	 * Gets the cantidad cheques rechazados.
	 *
	 * @return the cantidad cheques rechazados
	 */
	public String getCantidadChequesRechazados() {
		return cantidadChequesRechazados;
	}

	/**
	 * Sets the cantidad cheques rechazados.
	 *
	 * @param cantidadChequesRechazados
	 *            the new cantidad cheques rechazados
	 */
	public void setCantidadChequesRechazados(String cantidadChequesRechazados) {
		this.cantidadChequesRechazados = cantidadChequesRechazados;
	}

	/**
	 * Gets the estado tramite.
	 *
	 * @return the estado tramite
	 */
	public String getEstadoTramite() {
		return estadoTramite;
	}

	/**
	 * Sets the estado tramite.
	 *
	 * @param estadoTramite
	 *            the new estado tramite
	 */
	public void setEstadoTramite(String estadoTramite) {
		this.estadoTramite = estadoTramite;
	}

	/**
	 * Gets the descripcion estado.
	 *
	 * @return the descripcion estado
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	/**
	 * Sets the descripcion estado.
	 *
	 * @param descripcionEstado
	 *            the new descripcion estado
	 */
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	/**
	 * Gets the id ultima modificacion.
	 *
	 * @return the id ultima modificacion
	 */
	public String getIdUltimaModificacion() {
		return idUltimaModificacion;
	}

	/**
	 * Sets the id ultima modificacion.
	 *
	 * @param idUltimaModificacion
	 *            the new id ultima modificacion
	 */
	public void setIdUltimaModificacion(String idUltimaModificacion) {
		this.idUltimaModificacion = idUltimaModificacion;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the fecha recalculo.
	 *
	 * @return the fecha recalculo
	 */
	public String getFechaRecalculo() {
		return fechaRecalculo;
	}

	/**
	 * Sets the fecha recalculo.
	 *
	 * @param fechaRecalculo
	 *            the new fecha recalculo
	 */
	public void setFechaRecalculo(String fechaRecalculo) {
		this.fechaRecalculo = fechaRecalculo;
	}

	/**
	 * Gets the apellido cliente.
	 *
	 * @return the apellido cliente
	 */
	public String getApellidoCliente() {
		return apellidoCliente;
	}

	/**
	 * Sets the apellido cliente.
	 *
	 * @param apellidoCliente
	 *            the new apellido cliente
	 */
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	/**
	 * Gets the nombre cliente.
	 *
	 * @return the nombre cliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the new nombre cliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
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
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the fecha ultima modificacion.
	 *
	 * @return the fecha ultima modificacion
	 */
	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	/**
	 * Sets the fecha ultima modificacion.
	 *
	 * @param fechaUltimaModificacion
	 *            the new fecha ultima modificacion
	 */
	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the marca sell station.
	 *
	 * @return the marca sell station
	 */
	public String getMarcaSellStation() {
		return marcaSellStation;
	}

	/**
	 * Sets the marca sell station.
	 *
	 * @param marcaSellStation
	 *            the new marca sell station
	 */
	public void setMarcaSellStation(String marcaSellStation) {
		this.marcaSellStation = marcaSellStation;
	}

	/**
	 * Gets the marca gerente.
	 *
	 * @return the marca gerente
	 */
	public String getMarcaGerente() {
		return marcaGerente;
	}

	/**
	 * Sets the marca gerente.
	 *
	 * @param marcaGerente
	 *            the new marca gerente
	 */
	public void setMarcaGerente(String marcaGerente) {
		this.marcaGerente = marcaGerente;
	}

	/**
	 * Gets the marca productos.
	 *
	 * @return the marca productos
	 */
	public String getMarcaProductos() {
		return marcaProductos;
	}

	/**
	 * Sets the marca productos.
	 *
	 * @param marcaProductos
	 *            the new marca productos
	 */
	public void setMarcaProductos(String marcaProductos) {
		this.marcaProductos = marcaProductos;
	}

	/**
	 * Gets the habilita detalle.
	 *
	 * @return the habilita detalle
	 */
	public String getHabilitaDetalle() {
		return habilitaDetalle;
	}

	/**
	 * Sets the habilita detalle.
	 *
	 * @param habilitaDetalle
	 *            the new habilita detalle
	 */
	public void setHabilitaDetalle(String habilitaDetalle) {
		this.habilitaDetalle = habilitaDetalle;
	}
	
	
}
