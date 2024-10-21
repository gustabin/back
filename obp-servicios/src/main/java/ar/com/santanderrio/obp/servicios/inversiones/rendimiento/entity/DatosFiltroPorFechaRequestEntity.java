/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

/**
 * The Class DatosFiltroPorFechaRequestEntity.
 */
public class DatosFiltroPorFechaRequestEntity extends EntityBase{

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The lista cuentas. */
	@JsonProperty("ListaCuentas")
	private List<CuentaOPEntity> listaCuentas = new ArrayList<CuentaOPEntity>();

	/** The carteraAConsultar. */
	@JsonProperty("CarteraAConsultar")
	private String carteraAConsultar;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The datos servicios. */
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
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
	 * Gets the lista cuentas.
	 *
	 * @return the lista cuentas
	 */
	public List<CuentaOPEntity> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(List<CuentaOPEntity> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the cartera A consultar.
	 *
	 * @return the cartera A consultar
	 */
	public String getCarteraAConsultar() {
		return carteraAConsultar;
	}

	/**
	 * Sets the cartera A consultar.
	 *
	 * @param carteraAConsultar
	 *            the new cartera A consultar
	 */
	public void setCarteraAConsultar(String carteraAConsultar) {
		this.carteraAConsultar = carteraAConsultar;
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
	 * Gets the datos servicios.
	 *
	 * @return the datos servicios
	 */
	public DatosServiciosEntity getDatosServicios() {
		return datosServicios;
	}

	/**
	 * Sets the datos servicios.
	 *
	 * @param datosServicios
	 *            the new datos servicios
	 */
	public void setDatosServicios(DatosServiciosEntity datosServicios) {
		this.datosServicios = datosServicios;
	}
	
}
