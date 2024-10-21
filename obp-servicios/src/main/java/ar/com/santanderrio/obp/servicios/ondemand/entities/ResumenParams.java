/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;

/**
 * The Class ResumenParams.
 */
public class ResumenParams {

	/** The fecha puntual. */
	private String fechaPuntual;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The fecha hasta. */
	private String fechaActual;

	/** The cuenta. */
	private AbstractCuenta cuenta;

	/** The folder. */
	private String folder;

	/** The motivo consulta. */
	private String motivoConsulta;

	/** The usuario consulta. */
	private String usuarioConsulta;

	/** The paquete. */
	private String paquete;

	/** The producto. */
	private String producto;

	/** The proveedor tarjeta. */
	private String proveedorTarjeta;

	/** The idCuenta. */
	private String idCta;

	/** The idCuenta. */
	private String aplicacion;

	/** The moneda. */
	private String moneda;

	/** The soporte. */
	private String soporte;
	
	private TipoPDFEnum tipoPDF;

	/**
	 * Gets the folder.
	 *
	 * @return the folder
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Gets the paquete.
	 *
	 * @return the paquete
	 */
	public String getPaquete() {
		return paquete;
	}

	/**
	 * Gets the id cta.
	 *
	 * @return the idCta
	 */
	public String getIdCta() {
		return idCta;
	}

	/**
	 * Sets the id cta.
	 *
	 * @param idCta
	 *            the idCta to set
	 */
	public void setIdCta(String idCta) {
		this.idCta = idCta;
	}

	/**
	 * Sets the paquete.
	 *
	 * @param paquete
	 *            the new paquete
	 */
	public void setPaquete(String paquete) {
		this.paquete = paquete;
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
	 * Sets the folder.
	 *
	 * @param folder
	 *            the new folder
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

	/**
	 * Gets the fecha puntual.
	 *
	 * @return the fecha puntual
	 */
	public String getFechaPuntual() {
		return fechaPuntual;
	}

	/**
	 * Sets the fecha puntual.
	 *
	 * @param fechaPuntual
	 *            the new fecha puntual
	 */
	public void setFechaPuntual(String fechaPuntual) {
		this.fechaPuntual = fechaPuntual;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public AbstractCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(AbstractCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the motivo consulta.
	 *
	 * @return the motivo consulta
	 */
	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	/**
	 * Sets the motivo consulta.
	 *
	 * @param motivoConsulta
	 *            the new motivo consulta
	 */
	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	/**
	 * Gets the usuario consulta.
	 *
	 * @return the usuario consulta
	 */
	public String getUsuarioConsulta() {
		return usuarioConsulta;
	}

	/**
	 * Sets the usuario consulta.
	 *
	 * @param usuarioConsulta
	 *            the new usuario consulta
	 */
	public void setUsuarioConsulta(String usuarioConsulta) {
		this.usuarioConsulta = usuarioConsulta;
	}

	/**
	 * Gets the proveedor tarjeta.
	 *
	 * @return the proveedor tarjeta
	 */
	public String getProveedorTarjeta() {
		return proveedorTarjeta;
	}

	/**
	 * Sets the proveedor tarjeta.
	 *
	 * @param proveedorTarjeta
	 *            the new proveedor tarjeta
	 */
	public void setProveedorTarjeta(String proveedorTarjeta) {
		this.proveedorTarjeta = proveedorTarjeta;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the soporte.
	 *
	 * @return the soporte
	 */
	public String getSoporte() {
		return soporte;
	}

	/**
	 * Sets the soporte.
	 *
	 * @param soporte
	 *            the new soporte
	 */
	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}

	/**
	 * Gets the filtro aplicacion.
	 *
	 * @return the aplicacion
	 */
	public String getFiltroAplicacion() {
		return aplicacion;
	}

	/**
	 * Sets the filtro aplicacion.
	 *
	 * @param aplicacion
	 *            the new filtro aplicacion
	 */
	public void setFiltroAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

    /**
	 * Gets the fecha actual.
	 *
	 * @return the fechaActual
	 */
    public String getFechaActual() {
        return fechaActual;
    }

    /**
	 * Sets the fecha actual.
	 *
	 * @param fechaActual
	 *            the fechaActual to set
	 */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

	public TipoPDFEnum getTipoPDF() {
		return tipoPDF;
	}

	public void setTipoPDF(TipoPDFEnum tipoPDF) {
		this.tipoPDF = tipoPDF;
	}

}
