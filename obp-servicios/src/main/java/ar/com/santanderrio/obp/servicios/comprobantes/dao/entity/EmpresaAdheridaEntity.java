/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import org.beanio.annotation.Field;

/**
 * The Class EmpresaAdheridaEntity.
 */
public class EmpresaAdheridaEntity {

	/** The cuit empresa(N11). */
	@Field
	private String cuitEmpresa;

	/** The nombre servicio empresa(A10). */
	@Field
	private String nombreServicioEmpresa;

	/** The nro partida servicio empresa(A22). */
	@Field
	private String nroPartidaServicioEmpresa;

	/** The limite adhesion DDI(N14). */
	@Field
	private String limiteAdhesionDDI;

	/** The estado servicio DDI(N01). */
	@Field
	private String estadoServicioDDI;

	/** The fecha vigencia DDI(N08). */
	@Field
	private String fechaVigenciaDDI;

	/** The codigo aplicacion(A04). */
	@Field
	private String codigoAplicacion;

	/** The tipo cuenta debito(N02). */
	@Field
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito(N03). */
	@Field
	private String sucursalCuentaDebito;

	/** The nro cuenta prod debito(N08). */
	@Field
	private String nroCuentaProdDebito;

	/** The nro orden firmante(N03). */
	@Field
	private String nroOrdenFirmante;

	/**
	 * Gets the cuit empresa.
	 *
	 * @return the cuit empresa
	 */
	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	/**
	 * Sets the cuit empresa.
	 *
	 * @param cuitEmpresa
	 *            the new cuit empresa
	 */
	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

	/**
	 * Gets the nombre servicio empresa.
	 *
	 * @return the nombre servicio empresa
	 */
	public String getNombreServicioEmpresa() {
		return nombreServicioEmpresa;
	}

	/**
	 * Sets the nombre servicio empresa.
	 *
	 * @param nombreServicioEmpresa
	 *            the new nombre servicio empresa
	 */
	public void setNombreServicioEmpresa(String nombreServicioEmpresa) {
		this.nombreServicioEmpresa = nombreServicioEmpresa;
	}

	/**
	 * Gets the nro partida servicio empresa.
	 *
	 * @return the nro partida servicio empresa
	 */
	public String getNroPartidaServicioEmpresa() {
		return nroPartidaServicioEmpresa;
	}

	/**
	 * Sets the nro partida servicio empresa.
	 *
	 * @param nroPartidaServicioEmpresa
	 *            the new nro partida servicio empresa
	 */
	public void setNroPartidaServicioEmpresa(String nroPartidaServicioEmpresa) {
		this.nroPartidaServicioEmpresa = nroPartidaServicioEmpresa;
	}

	/**
	 * Gets the limite adhesion DDI.
	 *
	 * @return the limite adhesion DDI
	 */
	public String getLimiteAdhesionDDI() {
		return limiteAdhesionDDI;
	}

	/**
	 * Sets the limite adhesion DDI.
	 *
	 * @param limiteAdhesionDDI
	 *            the new limite adhesion DDI
	 */
	public void setLimiteAdhesionDDI(String limiteAdhesionDDI) {
		this.limiteAdhesionDDI = limiteAdhesionDDI;
	}

	/**
	 * Gets the estado servicio DDI.
	 *
	 * @return the estado servicio DDI
	 */
	public String getEstadoServicioDDI() {
		return estadoServicioDDI;
	}

	/**
	 * Sets the estado servicio DDI.
	 *
	 * @param estadoServicioDDI
	 *            the new estado servicio DDI
	 */
	public void setEstadoServicioDDI(String estadoServicioDDI) {
		this.estadoServicioDDI = estadoServicioDDI;
	}

	/**
	 * Gets the fecha vigencia DDI.
	 *
	 * @return the fecha vigencia DDI
	 */
	public String getFechaVigenciaDDI() {
		return fechaVigenciaDDI;
	}

	/**
	 * Sets the fecha vigencia DDI.
	 *
	 * @param fechaVigenciaDDI
	 *            the new fecha vigencia DDI
	 */
	public void setFechaVigenciaDDI(String fechaVigenciaDDI) {
		this.fechaVigenciaDDI = fechaVigenciaDDI;
	}

	/**
	 * Gets the codigo aplicacion.
	 *
	 * @return the codigo aplicacion
	 */
	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	/**
	 * Sets the codigo aplicacion.
	 *
	 * @param codigoAplicacion
	 *            the new codigo aplicacion
	 */
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the nro cuenta prod debito.
	 *
	 * @return the nro cuenta prod debito
	 */
	public String getNroCuentaProdDebito() {
		return nroCuentaProdDebito;
	}

	/**
	 * Sets the nro cuenta prod debito.
	 *
	 * @param nroCuentaProdDebito
	 *            the new nro cuenta prod debito
	 */
	public void setNroCuentaProdDebito(String nroCuentaProdDebito) {
		this.nroCuentaProdDebito = nroCuentaProdDebito;
	}

	/**
	 * Gets the nro orden firmante.
	 *
	 * @return the nro orden firmante
	 */
	public String getNroOrdenFirmante() {
		return nroOrdenFirmante;
	}

	/**
	 * Sets the nro orden firmante.
	 *
	 * @param nroOrdenFirmante
	 *            the new nro orden firmante
	 */
	public void setNroOrdenFirmante(String nroOrdenFirmante) {
		this.nroOrdenFirmante = nroOrdenFirmante;
	}

}
