/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ListaAperturaEspecie.
 */
public class ListaAperturaEspecie {
	
	/** The descripcion especie. */
	private String descripcionEspecie;

	/** The codigo amigable. */
	private String codigoAmigable;

	/** The codigo especie rossi. */
	private String codigoEspecieRossi;

	/** The tipo especie. */
	private String tipoEspecie;

	/** The instrumento. */
	private String instrumento;

	/** The emision moneda especie. */
	private String emisionMonedaEspecie;

	/** The leyenda legal. */
	private String leyendaLegal;

	/** The plazos. */
	private List<AperturaEspecie> plazos = new ArrayList<AperturaEspecie>();
	
	/** The fuera horario. */
	private boolean fueraHorario = false;
	
	/** The especie pesos. */
	private boolean especiePesos = false;
	
	/** The especie dolares. */
	private boolean especieDolares = false;
	/**
	 * Bandera en la cual evalua si el cliente tiene alguna cuenta debito en la moneda con que se pueda realizar la compra de cierta especie.
	 */
	private boolean disponeMonedaParaComprar = false;

	private String legalCNV;
	
	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the codigo amigable.
	 *
	 * @return the codigo amigable
	 */
	public String getCodigoAmigable() {
		return codigoAmigable;
	}

	/**
	 * Sets the codigo amigable.
	 *
	 * @param codigoAmigable
	 *            the new codigo amigable
	 */
	public void setCodigoAmigable(String codigoAmigable) {
		this.codigoAmigable = codigoAmigable;
	}

	/**
	 * Gets the codigo especie rossi.
	 *
	 * @return the codigo especie rossi
	 */
	public String getCodigoEspecieRossi() {
		return codigoEspecieRossi;
	}

	/**
	 * Sets the codigo especie rossi.
	 *
	 * @param codigoEspecieRossi
	 *            the new codigo especie rossi
	 */
	public void setCodigoEspecieRossi(String codigoEspecieRossi) {
		this.codigoEspecieRossi = codigoEspecieRossi;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipo especie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the instrumento.
	 *
	 * @return the instrumento
	 */
	public String getInstrumento() {
		return instrumento;
	}

	/**
	 * Sets the instrumento.
	 *
	 * @param instrumento
	 *            the new instrumento
	 */
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	/**
	 * Gets the emision moneda especie.
	 *
	 * @return the emision moneda especie
	 */
	public String getEmisionMonedaEspecie() {
		return emisionMonedaEspecie;
	}

	/**
	 * Sets the emision moneda especie.
	 *
	 * @param emisionMonedaEspecie
	 *            the new emision moneda especie
	 */
	public void setEmisionMonedaEspecie(String emisionMonedaEspecie) {
		this.emisionMonedaEspecie = emisionMonedaEspecie;
	}

	/**
	 * Gets the leyenda legal.
	 *
	 * @return the leyenda legal
	 */
	public String getLeyendaLegal() {
		return leyendaLegal;
	}

	/**
	 * Sets the leyenda legal.
	 *
	 * @param leyendaLegal
	 *            the new leyenda legal
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the plazos.
	 *
	 * @return the plazos
	 */
	public List<AperturaEspecie> getPlazos() {
		return plazos;
	}

	/**
	 * Sets the plazos.
	 *
	 * @param plazos
	 *            the new plazos
	 */
	public void setPlazos(List<AperturaEspecie> plazos) {
		this.plazos = plazos;
	}

	/**
	 * Checks if is fuera horario.
	 *
	 * @return true, if is fuera horario
	 */
	public boolean isFueraHorario() {
		return fueraHorario;
	}

	/**
	 * Sets the fuera horario.
	 *
	 * @param fueraHorario
	 *            the new fuera horario
	 */
	public void setFueraHorario(boolean fueraHorario) {
		this.fueraHorario = fueraHorario;
	}

	/**
	 * Checks if is dispone moneda para comprar.
	 *
	 * @return true, if is dispone moneda para comprar
	 */
	public boolean isDisponeMonedaParaComprar() {
		return disponeMonedaParaComprar;
	}

	/**
	 * Sets the dispone moneda para comprar.
	 *
	 * @param disponeMonedaParaComprar
	 *            the new dispone moneda para comprar
	 */
	public void setDisponeMonedaParaComprar(boolean disponeMonedaParaComprar) {
		this.disponeMonedaParaComprar = disponeMonedaParaComprar;
	}

	/**
	 * Checks if is especie pesos.
	 *
	 * @return true, if is especie pesos
	 */
	public boolean isEspeciePesos() {
		return especiePesos;
	}

	/**
	 * Sets the especie pesos.
	 *
	 * @param especiePesos
	 *            the new especie pesos
	 */
	public void setEspeciePesos(boolean especiePesos) {
		this.especiePesos = especiePesos;
	}

	/**
	 * Checks if is especie dolares.
	 *
	 * @return true, if is especie dolares
	 */
	public boolean isEspecieDolares() {
		return especieDolares;
	}

	/**
	 * Sets the especie dolares.
	 *
	 * @param especieDolares
	 *            the new especie dolares
	 */
	public void setEspecieDolares(boolean especieDolares) {
		this.especieDolares = especieDolares;
	}

	public String getLegalCNV() {
		return legalCNV;
	}

	public void setLegalCNV(String legalCNV) {
		this.legalCNV = legalCNV;
	}
	
	

}
