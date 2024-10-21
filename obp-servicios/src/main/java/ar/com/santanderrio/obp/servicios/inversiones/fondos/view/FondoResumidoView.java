/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * Clase que modela una cuenta titulo para ser representada en UI.
 *
 * @author marcelo.ruiz
 */
public class FondoResumidoView {

	/** The nombre fondo. */
	private String nombreFondo;

	/** The importe minimo. */
	private String importeMinimo;

	/** The importe maximo. */
	private String importeMaximo;

	/** The moneda. */
	private String moneda;

	/** The saldo. */
	private String saldo;

	/** The grupo. */
	private String grupo;

	/** The codigo fondo. */
	private String codigoFondo;

	/** The ID legales. */
	private String idMensajeGastos;

	/** The tiene legales. */
	private boolean tieneGastos;

	/** The plazo efectivo. */
	private String plazoEfectivo;

	/** The descripcion larga. */
	private String descripcionLarga;

	/** The horario. */
	private String horario;
	
	/** The ex citi. */
	private String exCiti;
	
	/** The fondo nuevo */
	private boolean fondoNuevo;
	
	private String soloEspecie;
	
	private boolean repatriacion;
	
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
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the new importe minimo
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importe maximo
	 */
	public String getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Sets the importe maximo.
	 *
	 * @param importeMaximo
	 *            the new importe maximo
	 */
	public void setImporteMaximo(String importeMaximo) {
		this.importeMaximo = importeMaximo;
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
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Sets the grupo.
	 *
	 * @param grupo
	 *            the new grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
	 * Gets the id mensaje gastos.
	 *
	 * @return the id mensaje gastos
	 */
	public String getIdMensajeGastos() {
		return idMensajeGastos;
	}

	/**
	 * Sets the id mensaje gastos.
	 *
	 * @param idMensajeGastos
	 *            the new id mensaje gastos
	 */
	public void setIdMensajeGastos(String idMensajeGastos) {
		this.idMensajeGastos = idMensajeGastos;
	}

	/**
	 * Checks if is tiene gastos.
	 *
	 * @return true, if is tiene gastos
	 */
	public boolean isTieneGastos() {
		return tieneGastos;
	}

	/**
	 * Sets the tiene gastos.
	 *
	 * @param tieneGastos
	 *            the new tiene gastos
	 */
	public void setTieneGastos(boolean tieneGastos) {
		this.tieneGastos = tieneGastos;
	}

	/**
	 * Gets the plazo efectivo.
	 *
	 * @return the plazoEfectivo
	 */
	public String getPlazoEfectivo() {
		return plazoEfectivo;
	}

	/**
	 * Sets the plazo efectivo.
	 *
	 * @param plazoEfectivo
	 *            the plazoEfectivo to set
	 */
	public void setPlazoEfectivo(String plazoEfectivo) {
		this.plazoEfectivo = plazoEfectivo;
	}

	/**
	 * Gets the descripcion larga.
	 *
	 * @return the descripcion larga
	 */
	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	/**
	 * Sets the descripcion larga.
	 *
	 * @param descripcionLarga
	 *            the new descripcion larga
	 */
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	/**
	 * Gets the horario.
	 *
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * Sets the horario.
	 *
	 * @param horario
	 *            the new horario
	 */
	public void setHorario(String horario) {
		this.horario = horario;
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

	public boolean isFondoNuevo() {
		return fondoNuevo;
	}

	public void setFondoNuevo(boolean fondoNuevo) {
		this.fondoNuevo = fondoNuevo;
	}

	public String getSoloEspecie() {
		return soloEspecie;
	}

	public void setSoloEspecie(String soloEspecie) {
		this.soloEspecie = soloEspecie;
	}

	public boolean isRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(boolean repatriacion) {
		this.repatriacion = repatriacion;
	}

	
	
    
}
