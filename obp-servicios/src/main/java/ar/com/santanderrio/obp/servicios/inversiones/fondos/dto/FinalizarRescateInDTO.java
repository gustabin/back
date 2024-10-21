/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class FinalizarRescateInDTO.
 */
public class FinalizarRescateInDTO {

	/** Nombre del fondo. */
	private String nombreFondo;

	/** Codigo del fondo. */
	private String codigoFondo;

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The tipo cta cred. */
	private String tipoCtaCred;

	/** The sucursal cta cred. */
	private String sucursalCtaCred;

	/** The numero cta cred. */
	private String numeroCtaCred;

	/** Tipo de Moneda $ o u$s. */
	private String moneda;

	/** Importe a suscribir. */
	private String importe;

	/** The importe rescate comision. */
	private String importeRescateComision;

	/** The importe rescate neto. */
	private String importeRescateNeto;

	/** The cuota partes. */
	private String cuotaPartes;

	/** The ex citi. */
	private String exCiti;
	
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
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the tipo cta cred.
	 *
	 * @return the tipo cta cred
	 */
	public String getTipoCtaCred() {
		return tipoCtaCred;
	}

	/**
	 * Sets the tipo cta cred.
	 *
	 * @param tipoCtaCred
	 *            the new tipo cta cred
	 */
	public void setTipoCtaCred(String tipoCtaCred) {
		this.tipoCtaCred = tipoCtaCred;
	}

	/**
	 * Gets the sucursal cta cred.
	 *
	 * @return the sucursal cta cred
	 */
	public String getSucursalCtaCred() {
		return sucursalCtaCred;
	}

	/**
	 * Sets the sucursal cta cred.
	 *
	 * @param sucursalCtaCred
	 *            the new sucursal cta cred
	 */
	public void setSucursalCtaCred(String sucursalCtaCred) {
		this.sucursalCtaCred = sucursalCtaCred;
	}

	/**
	 * Gets the numero cta cred.
	 *
	 * @return the numero cta cred
	 */
	public String getNumeroCtaCred() {
		return numeroCtaCred;
	}

	/**
	 * Sets the numero cta cred.
	 *
	 * @param numeroCtaCred
	 *            the new numero cta cred
	 */
	public void setNumeroCtaCred(String numeroCtaCred) {
		this.numeroCtaCred = numeroCtaCred;
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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the importe rescate comision.
	 *
	 * @return the importe rescate comision
	 */
	public String getImporteRescateComision() {
		return importeRescateComision;
	}

	/**
	 * Sets the importe rescate comision.
	 *
	 * @param importeRescateComision
	 *            the new importe rescate comision
	 */
	public void setImporteRescateComision(String importeRescateComision) {
		this.importeRescateComision = importeRescateComision;
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
	 * Gets the cuota partes.
	 *
	 * @return the cuota partes
	 */
	public String getCuotaPartes() {
		return cuotaPartes;
	}

	/**
	 * Sets the cuota partes.
	 *
	 * @param cuotaPartes
	 *            the new cuota partes
	 */
	public void setCuotaPartes(String cuotaPartes) {
		this.cuotaPartes = cuotaPartes;
	}

    /**
	 * Gets the ex citi.
	 *
	 * @return the ex citi
	 */
    public String getExCiti() {
        return exCiti;
    }

    /**
	 * Sets the ex citi.
	 *
	 * @param exCiti
	 *            the new ex citi
	 */
    public void setExCiti(String exCiti) {
        this.exCiti = exCiti;
    }

}
