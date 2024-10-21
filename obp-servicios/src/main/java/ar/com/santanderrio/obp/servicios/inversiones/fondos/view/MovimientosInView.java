/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Class MovimientosInView.
 */
public class MovimientosInView {

	/** codigo fondo. */
	private String codigoFondo;

	/** cuenta titulo. */
	private String cuentaTitulo;

	/** fecha en formato ddMMaaaa. */
	private String fechaDesde;

	/** fecha en formato ddMMaaaa. */
	private String fechaHasta;

	/** importe minimo. */
	private String importeMinimo;

	/** importe Maximo. */
	private String importeMaximo;
	
	/** fondo citi. */
	private String exCiti;

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importeMinimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the importeMinimo to set
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importeMaximo
	 */
	public String getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Sets the importe maximo.
	 *
	 * @param importeMaximo
	 *            the importeMaximo to set
	 */
	public void setImporteMaximo(String importeMaximo) {
		this.importeMaximo = importeMaximo;
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
