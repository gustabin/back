/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class GraficoRendimientoRespuesta.
 */
public class GraficoRendimientoRespuesta {
	
	/** The guid. */
	@JsonProperty("GUIDError")
	private String guid;
	
	/** The codigo cabecera. */
	@JsonProperty("CodigoCabecera")
	private String codigoCabecera;
	
	/** The descripcion cabecera. */
	@JsonProperty("DescripcionCabecera")
	private String descripcionCabecera;
	
	/** The detalle. */
	@JsonProperty("Detalle")
	private List<GraficoRendPorFecha> detalle;

	/**
	 * Gets the guid.
	 *
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid
	 *            the new guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Gets the codigo cabecera.
	 *
	 * @return the codigo cabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * Sets the codigo cabecera.
	 *
	 * @param codigoCabecera
	 *            the new codigo cabecera
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * Gets the descripcion cabecera.
	 *
	 * @return the descripcion cabecera
	 */
	public String getDescripcionCabecera() {
		return descripcionCabecera;
	}

	/**
	 * Sets the descripcion cabecera.
	 *
	 * @param descripcionCabecera
	 *            the new descripcion cabecera
	 */
	public void setDescripcionCabecera(String descripcionCabecera) {
		this.descripcionCabecera = descripcionCabecera;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public List<GraficoRendPorFecha> getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the new detalle
	 */
	public void setDetalle(List<GraficoRendPorFecha> detalle) {
		this.detalle = detalle;
	}
	
	
	
}
