/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosDetalleFondoRequestEntity.
 *
 * @author marcelo.ruiz
 */
public class DatosDetalleFondoRequestEntity extends EntityBase {

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The Anio */
	@JsonProperty("Año")
	private int año;

	/** The lista cuentas. */
	@JsonProperty("ListaCuentas")
	private List<CuentaOPEntity> listaCuentas = new ArrayList<CuentaOPEntity>();

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
