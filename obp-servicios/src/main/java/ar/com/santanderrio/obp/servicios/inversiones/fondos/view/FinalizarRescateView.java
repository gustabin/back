/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * Representa el rescate de un cliente, banca personal.
 * 
 * @author pablo.d.gargaglione
 *
 */
public class FinalizarRescateView {

	/** The moneda. */
	private String moneda;

	/** The nombre fondo. */
	private String nombreFondo;

	/** The nro rescate. */
	private String nroRescate;

	/** The importe rescate neto. */
	private String importeRescateNeto;

	/** The total cuotas partes. */
	private String totalCuotasPartes;

	/** The mensaje suscripcion. */
	private String mensajeSuscripcion;

	/** The fechaHora. */
	private String fechaHora;
	
	/** The nroOrden. */
	private String nroOrden;

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
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * Gets the nro rescate.
	 *
	 * @return the nro rescate
	 */
	public String getNroRescate() {
		return nroRescate;
	}

	/**
	 * Sets the nro rescate.
	 *
	 * @param nroRescate
	 *            the new nro rescate
	 */
	public void setNroRescate(String nroRescate) {
		this.nroRescate = nroRescate;
	}

	/**
	 * Gets the importe rescate neto.
	 *
	 * @return the importe rescate neto
	 */
	public String getImporteRescateNeto() {
		return importeRescateNeto;
	}

	/**
	 * Sets the importe rescate neto.
	 *
	 * @param importeRescateNeto
	 *            the new importe rescate neto
	 */
	public void setImporteRescateNeto(String importeRescateNeto) {
		this.importeRescateNeto = importeRescateNeto;
	}

	/**
	 * Gets the total cuotas partes.
	 *
	 * @return the total cuotas partes
	 */
	public String getTotalCuotasPartes() {
		return totalCuotasPartes;
	}

	/**
	 * Sets the total cuotas partes.
	 *
	 * @param totalCuotasPartes
	 *            the new total cuotas partes
	 */
	public void setTotalCuotasPartes(String totalCuotasPartes) {
		this.totalCuotasPartes = totalCuotasPartes;
	}

	/**
	 * Gets the mensaje suscripcion.
	 *
	 * @return the mensaje suscripcion
	 */
	public String getMensajeSuscripcion() {
		return mensajeSuscripcion;
	}

	/**
	 * Sets the mensaje suscripcion.
	 *
	 * @param mensajeSuscripcion
	 *            the new mensaje suscripcion
	 */
	public void setMensajeSuscripcion(String mensajeSuscripcion) {
		this.mensajeSuscripcion = mensajeSuscripcion;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

    /**
     * @return the nroOrden
     */
    public String getNroOrden() {
        return nroOrden;
    }

    /**
     * @param nroOrden the nroOrden to set
     */
    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

}
