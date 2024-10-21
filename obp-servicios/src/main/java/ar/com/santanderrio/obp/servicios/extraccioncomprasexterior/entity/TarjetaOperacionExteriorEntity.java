/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * TarjetaOperacionExteriorEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class TarjetaOperacionExteriorEntity {

	/** The nro pan. */
	@Field
	private String nroPan;

	/** The sucursal admin. */
	@Field
	private String sucursalAdmin;

	/** The tipo tarjeta. */
	@Field
	private String tipoTarjeta;

	/** The adicional tipo. */
	@Field
	private String adicionalTipo;

	/** The clase tarjeta. */
	@Field
	private String claseTarjeta;

	/** The estado tarjeta. */
	@Field
	private String estadoTarjeta;

	/** The cuenta principal. */
	@Field
	private String cuentaPrincipal;

	/** The fecha vencimiento. */
	@Field
	private String fechaVencimiento;

	/** The datos alta. */
	@Field
	private String datosAlta;

	/** The fecha habilitacion. */
	@Field
	private String fechaHabilitacion;

	/** The fecha embozado. */
	@Field
	private String fechaEmbozado;

	/** The datos modificacion. */
	@Field
	private String datosModificacion;

	/** The datos banelco. */
	@Field
	private String datosBanelco;

	/** The fecha cambio sucursal. */
	@Field
	private String fechaCambioSucursal;

	/** The tipo caf. */
	@Field
	private String tipoCaf;

	/** The caf alta. */
	@Field
	private String cafAlta;

	/** The coodigo distribucion. */
	@Field
	private String coodigoDistribucion;

	/** The codigo comercial. */
	@Field
	private String codigoComercial;

	/** The ide comercial. */
	@Field
	private String ideComercial;

	/** The cuarta linea. */
	@Field
	private String cuartaLinea;

	/** The nro pan anterior. */
	@Field
	private String nroPanAnterior;

	/** The sin uso. */
	@Field
	private String sinUso;

	/** The mas datos tarjeta. */
	@Field
	private String masDatosTarjeta;

	/** The producto. */
	@Field
	private String producto;

	/** The sub producto. */
	@Field
	private String subProducto;

	/** The titular. */
	@Field
	private String titular;

	/** The tipo documento. */
	@Field
	private String tipoDocumento;

	/** The numero documento. */
	@Field
	private String numeroDocumento;

	/** The domicilio. */
	@Field
	private String domicilio;

	/** The localidad. */
	@Field
	private String localidad;

	/** The mas datos filiatorios. */
	@Field
	private String masDatosFiliatorios;

	/**
	 * Gets the nro pan.
	 *
	 * @return the nro pan
	 */
	public String getNroPan() {
		return nroPan;
	}

	/**
	 * Sets the nro pan.
	 *
	 * @param nroPan
	 *            the new nro pan
	 */
	public void setNroPan(String nroPan) {
		this.nroPan = nroPan;
	}

	/**
	 * Gets the sucursal admin.
	 *
	 * @return the sucursal admin
	 */
	public String getSucursalAdmin() {
		return sucursalAdmin;
	}

	/**
	 * Sets the sucursal admin.
	 *
	 * @param sucursalAdmin
	 *            the new sucursal admin
	 */
	public void setSucursalAdmin(String sucursalAdmin) {
		this.sucursalAdmin = sucursalAdmin;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the adicional tipo.
	 *
	 * @return the adicional tipo
	 */
	public String getAdicionalTipo() {
		return adicionalTipo;
	}

	/**
	 * Sets the adicional tipo.
	 *
	 * @param adicionalTipo
	 *            the new adicional tipo
	 */
	public void setAdicionalTipo(String adicionalTipo) {
		this.adicionalTipo = adicionalTipo;
	}

	/**
	 * Gets the clase tarjeta.
	 *
	 * @return the clase tarjeta
	 */
	public String getClaseTarjeta() {
		return claseTarjeta;
	}

	/**
	 * Sets the clase tarjeta.
	 *
	 * @param claseTarjeta
	 *            the new clase tarjeta
	 */
	public void setClaseTarjeta(String claseTarjeta) {
		this.claseTarjeta = claseTarjeta;
	}

	/**
	 * Gets the estado tarjeta.
	 *
	 * @return the estado tarjeta
	 */
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}

	/**
	 * Sets the estado tarjeta.
	 *
	 * @param estadoTarjeta
	 *            the new estado tarjeta
	 */
	public void setEstadoTarjeta(String estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}

	/**
	 * Gets the cuenta principal.
	 *
	 * @return the cuenta principal
	 */
	public String getCuentaPrincipal() {
		return cuentaPrincipal;
	}

	/**
	 * Sets the cuenta principal.
	 *
	 * @param cuentaPrincipal
	 *            the new cuenta principal
	 */
	public void setCuentaPrincipal(String cuentaPrincipal) {
		this.cuentaPrincipal = cuentaPrincipal;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the datos alta.
	 *
	 * @return the datos alta
	 */
	public String getDatosAlta() {
		return datosAlta;
	}

	/**
	 * Sets the datos alta.
	 *
	 * @param datosAlta
	 *            the new datos alta
	 */
	public void setDatosAlta(String datosAlta) {
		this.datosAlta = datosAlta;
	}

	/**
	 * Gets the fecha habilitacion.
	 *
	 * @return the fecha habilitacion
	 */
	public String getFechaHabilitacion() {
		return fechaHabilitacion;
	}

	/**
	 * Sets the fecha habilitacion.
	 *
	 * @param fechaHabilitacion
	 *            the new fecha habilitacion
	 */
	public void setFechaHabilitacion(String fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}

	/**
	 * Gets the fecha embozado.
	 *
	 * @return the fecha embozado
	 */
	public String getFechaEmbozado() {
		return fechaEmbozado;
	}

	/**
	 * Sets the fecha embozado.
	 *
	 * @param fechaEmbozado
	 *            the new fecha embozado
	 */
	public void setFechaEmbozado(String fechaEmbozado) {
		this.fechaEmbozado = fechaEmbozado;
	}

	/**
	 * Gets the datos modificacion.
	 *
	 * @return the datos modificacion
	 */
	public String getDatosModificacion() {
		return datosModificacion;
	}

	/**
	 * Sets the datos modificacion.
	 *
	 * @param datosModificacion
	 *            the new datos modificacion
	 */
	public void setDatosModificacion(String datosModificacion) {
		this.datosModificacion = datosModificacion;
	}

	/**
	 * Gets the datos banelco.
	 *
	 * @return the datos banelco
	 */
	public String getDatosBanelco() {
		return datosBanelco;
	}

	/**
	 * Sets the datos banelco.
	 *
	 * @param datosBanelco
	 *            the new datos banelco
	 */
	public void setDatosBanelco(String datosBanelco) {
		this.datosBanelco = datosBanelco;
	}

	/**
	 * Gets the fecha cambio sucursal.
	 *
	 * @return the fecha cambio sucursal
	 */
	public String getFechaCambioSucursal() {
		return fechaCambioSucursal;
	}

	/**
	 * Sets the fecha cambio sucursal.
	 *
	 * @param fechaCambioSucursal
	 *            the new fecha cambio sucursal
	 */
	public void setFechaCambioSucursal(String fechaCambioSucursal) {
		this.fechaCambioSucursal = fechaCambioSucursal;
	}

	/**
	 * Gets the tipo caf.
	 *
	 * @return the tipo caf
	 */
	public String getTipoCaf() {
		return tipoCaf;
	}

	/**
	 * Sets the tipo caf.
	 *
	 * @param tipoCaf
	 *            the new tipo caf
	 */
	public void setTipoCaf(String tipoCaf) {
		this.tipoCaf = tipoCaf;
	}

	/**
	 * Gets the caf alta.
	 *
	 * @return the caf alta
	 */
	public String getCafAlta() {
		return cafAlta;
	}

	/**
	 * Sets the caf alta.
	 *
	 * @param cafAlta
	 *            the new caf alta
	 */
	public void setCafAlta(String cafAlta) {
		this.cafAlta = cafAlta;
	}

	/**
	 * Gets the coodigo distribucion.
	 *
	 * @return the coodigo distribucion
	 */
	public String getCoodigoDistribucion() {
		return coodigoDistribucion;
	}

	/**
	 * Sets the coodigo distribucion.
	 *
	 * @param coodigoDistribucion
	 *            the new coodigo distribucion
	 */
	public void setCoodigoDistribucion(String coodigoDistribucion) {
		this.coodigoDistribucion = coodigoDistribucion;
	}

	/**
	 * Gets the codigo comercial.
	 *
	 * @return the codigo comercial
	 */
	public String getCodigoComercial() {
		return codigoComercial;
	}

	/**
	 * Sets the codigo comercial.
	 *
	 * @param codigoComercial
	 *            the new codigo comercial
	 */
	public void setCodigoComercial(String codigoComercial) {
		this.codigoComercial = codigoComercial;
	}

	/**
	 * Gets the ide comercial.
	 *
	 * @return the ide comercial
	 */
	public String getIdeComercial() {
		return ideComercial;
	}

	/**
	 * Sets the ide comercial.
	 *
	 * @param ideComercial
	 *            the new ide comercial
	 */
	public void setIdeComercial(String ideComercial) {
		this.ideComercial = ideComercial;
	}

	/**
	 * Gets the cuarta linea.
	 *
	 * @return the cuarta linea
	 */
	public String getCuartaLinea() {
		return cuartaLinea;
	}

	/**
	 * Sets the cuarta linea.
	 *
	 * @param cuartaLinea
	 *            the new cuarta linea
	 */
	public void setCuartaLinea(String cuartaLinea) {
		this.cuartaLinea = cuartaLinea;
	}

	/**
	 * Gets the nro pan anterior.
	 *
	 * @return the nro pan anterior
	 */
	public String getNroPanAnterior() {
		return nroPanAnterior;
	}

	/**
	 * Sets the nro pan anterior.
	 *
	 * @param nroPanAnterior
	 *            the new nro pan anterior
	 */
	public void setNroPanAnterior(String nroPanAnterior) {
		this.nroPanAnterior = nroPanAnterior;
	}

	/**
	 * Gets the sin uso.
	 *
	 * @return the sin uso
	 */
	public String getSinUso() {
		return sinUso;
	}

	/**
	 * Sets the sin uso.
	 *
	 * @param sinUso
	 *            the new sin uso
	 */
	public void setSinUso(String sinUso) {
		this.sinUso = sinUso;
	}

	/**
	 * Gets the mas datos tarjeta.
	 *
	 * @return the mas datos tarjeta
	 */
	public String getMasDatosTarjeta() {
		return masDatosTarjeta;
	}

	/**
	 * Sets the mas datos tarjeta.
	 *
	 * @param masDatosTarjeta
	 *            the new mas datos tarjeta
	 */
	public void setMasDatosTarjeta(String masDatosTarjeta) {
		this.masDatosTarjeta = masDatosTarjeta;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the sub producto.
	 *
	 * @return the sub producto
	 */
	public String getSubProducto() {
		return subProducto;
	}

	/**
	 * Sets the sub producto.
	 *
	 * @param subProducto
	 *            the new sub producto
	 */
	public void setSubProducto(String subProducto) {
		this.subProducto = subProducto;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
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
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento
	 *            the new numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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
	 * Gets the mas datos filiatorios.
	 *
	 * @return the mas datos filiatorios
	 */
	public String getMasDatosFiliatorios() {
		return masDatosFiliatorios;
	}

	/**
	 * Sets the mas datos filiatorios.
	 *
	 * @param masDatosFiliatorios
	 *            the new mas datos filiatorios
	 */
	public void setMasDatosFiliatorios(String masDatosFiliatorios) {
		this.masDatosFiliatorios = masDatosFiliatorios;
	}

}
